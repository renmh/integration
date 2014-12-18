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
import com.pansky.integration.common.utils.StringUtils;
import com.pansky.integration.common.web.BaseController;
import com.pansky.integration.modules.sys.entity.User;
import com.pansky.integration.modules.sys.utils.UserUtils;
import com.pansky.integration.modules.test.entity.test1.TestEntity;
import com.pansky.integration.modules.test.service.test1.TestService;

/**
 * 功能测试Controller
 * @author renmh
 * @version 2014-12-10
 */
@Controller
@RequestMapping(value = "${adminPath}/test/test1/test")
public class TestController extends BaseController {

	@Autowired
	private TestService testService;
	
	@ModelAttribute
	public TestEntity get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return testService.get(id);
		}else{
			return new TestEntity();
		}
	}
	
	@RequiresPermissions("test:test1:test:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestEntity test, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			test.setCreateBy(user);
		}
        Page<TestEntity> page = testService.find(new Page<TestEntity>(request, response), test); 
        model.addAttribute("page", page);
		return "modules/" + "test/test1/testList";
	}

	@RequiresPermissions("test:test1:test:view")
	@RequestMapping(value = "form")
	public String form(TestEntity test, Model model) {
		model.addAttribute("test", test);
		return "modules/" + "test/test1/testForm";
	}

	@RequiresPermissions("test:test1:test:edit")
	@RequestMapping(value = "save")
	public String save(TestEntity test, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, test)){
			return form(test, model);
		}
		testService.save(test);
		addMessage(redirectAttributes, "保存功能测试'" + test.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/test1/test/?repage";
	}
	
	@RequiresPermissions("test:test1:test:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		testService.delete(id);
		addMessage(redirectAttributes, "删除功能测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/test1/test/?repage";
	}

}
