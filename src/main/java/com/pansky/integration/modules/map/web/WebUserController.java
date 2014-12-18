package com.pansky.integration.modules.map.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.pansky.integration.common.beanvalidator.BeanValidators;
import com.pansky.integration.common.config.Global;
import com.pansky.integration.common.config.ParameterConstant;
import com.pansky.integration.common.persistence.Page;
import com.pansky.integration.common.utils.DateUtils;
import com.pansky.integration.common.utils.StringUtils;
import com.pansky.integration.common.utils.excel.ExportExcel;
import com.pansky.integration.common.utils.excel.ImportExcel;
import com.pansky.integration.common.web.BaseController;
import com.pansky.integration.modules.sys.entity.Office;
import com.pansky.integration.modules.sys.entity.Role;
import com.pansky.integration.modules.sys.entity.User;
import com.pansky.integration.modules.sys.service.SystemService;
import com.pansky.integration.modules.sys.utils.UserUtils;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
@RequestMapping(value = "${frontPath}/user")
public class WebUserController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}

	@RequestMapping("form")
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		List<Role> list=systemService.findAllRole();
		List<Role> webRole=new ArrayList<Role>();
		List<String> roleIdList=new ArrayList<String>();
		for(Role role:list){
			if(role.getName().equals(ParameterConstant.WEB_ROLE_NAME)){
				webRole.add(role);
				roleIdList.add(role.getId());
			}
		}
		model.addAttribute("allRoles", webRole);
		user.setRoleIdList(roleIdList);
		return "modules/map/userForm";
	}

	@RequestMapping("save")
	public String save(User user, String oldLoginName, String newPassword, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/sys/user/?repage";
		}
		
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(newPassword)) {
			user.setPassword(SystemService.entryptPassword(newPassword));
		}
		if (!beanValidator(model, user)) {
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(oldLoginName, user.getLoginName()))) {
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()) {
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		
		// 保存用户信息
		systemService.saveUser(user);
		
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())) {
			UserUtils.getCacheMap().clear();
		}
		
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
		return "redirect:" + Global.getFrontPath() + "/user/map?repage";
	}

	

	@ResponseBody
	@RequestMapping("checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName != null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}
	@RequiresUser
	@RequestMapping("info")
	public String info(User user, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/map/userInfo";
			}
			
			currentUser = UserUtils.getUser(true);
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			systemService.saveUser(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		return "modules/map/userInfo";
	}

	@RequiresUser
	@RequestMapping("modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/map/userModifyPwd";
			}
			
			if (SystemService.validatePassword(oldPassword, user.getPassword())){
				systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/map/userModifyPwd";
	}
	@RequestMapping("logout")
	public String logout(User user, Model model) {
		SecurityUtils.getSubject().logout();
		return "redirect:" + Global.getFrontPath() + "/map?repage";
	}
}
