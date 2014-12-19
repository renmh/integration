package com.pansky.integration.modules.test.web.test1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pansky.integration.common.config.Global;
import com.pansky.integration.common.persistence.Page;
import com.pansky.integration.common.web.BaseController;
import com.pansky.integration.common.utils.StringUtils;
import com.pansky.integration.modules.sys.entity.User;
import com.pansky.integration.modules.sys.utils.UserUtils;
import com.pansky.integration.modules.test.entity.test1.Test1;
import com.pansky.integration.modules.test.service.test1.Test1Service;

/**
 * 功能Controller
 * @author renmh
 * @version 2014-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/test/test1/test1")
public class Test1Controller extends BaseController {

	@Autowired
	private Test1Service test1Service;
	
	@ModelAttribute
	public Test1 get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return test1Service.get(id);
		}else{
			return new Test1();
		}
	}
	
	@RequiresPermissions("test:test1:test1:view")
	@RequestMapping(value = {"list", ""})
	public String list(Test1 test1, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			test1.setCreateBy(user);
		}
        Page<Test1> page = test1Service.find(new Page<Test1>(request, response), test1); 
        model.addAttribute("page", page);
		return "modules/" + "test/test1/test1List";
	}

	@RequiresPermissions("test:test1:test1:view")
	@RequestMapping(value = "form")
	public String form(Test1 test1, Model model) {
		model.addAttribute("test1", test1);
		return "modules/" + "test/test1/test1Form";
	}

	@RequiresPermissions("test:test1:test1:edit")
	@RequestMapping(value = "save")
	public String save(Test1 test1, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, test1)){
			return form(test1, model);
		}
		test1Service.save(test1);
		addMessage(redirectAttributes, "保存功能'" + test1.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/test1/test1/?repage";
	}
	
	@RequiresPermissions("test:test1:test1:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		test1Service.delete(id);
		addMessage(redirectAttributes, "删除功能成功");
		return "redirect:"+Global.getAdminPath()+"/test/test1/test1/?repage";
	}

}
