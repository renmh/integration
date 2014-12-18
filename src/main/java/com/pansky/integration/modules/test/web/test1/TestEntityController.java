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
import com.pansky.integration.modules.test.entity.test1.TestEntity;
import com.pansky.integration.modules.test.service.test1.TestEntityService;

/**
 * 功能测试Controller
 * @author renmh
 * @version 2014-12-17
 */
@Controller
@RequestMapping(value = "${adminPath}/test/test1/testEntity")
public class TestEntityController extends BaseController {

	@Autowired
	private TestEntityService testEntityService;
	
	@ModelAttribute
	public TestEntity get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return testEntityService.get(id);
		}else{
			return new TestEntity();
		}
	}
	
	@RequiresPermissions("test:test1:testEntity:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestEntity testEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			testEntity.setCreateBy(user);
		}
        Page<TestEntity> page = testEntityService.find(new Page<TestEntity>(request, response), testEntity); 
        model.addAttribute("page", page);
		return "modules/" + "test/test1/testEntityList";
	}

	@RequiresPermissions("test:test1:testEntity:view")
	@RequestMapping(value = "form")
	public String form(TestEntity testEntity, Model model) {
		model.addAttribute("testEntity", testEntity);
		return "modules/" + "test/test1/testEntityForm";
	}

	@RequiresPermissions("test:test1:testEntity:edit")
	@RequestMapping(value = "save")
	public String save(TestEntity testEntity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, testEntity)){
			return form(testEntity, model);
		}
		testEntityService.save(testEntity);
		addMessage(redirectAttributes, "保存功能测试'" + testEntity.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/test/test1/testEntity/?repage";
	}
	
	@RequiresPermissions("test:test1:testEntity:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		testEntityService.delete(id);
		addMessage(redirectAttributes, "删除功能测试成功");
		return "redirect:"+Global.getAdminPath()+"/test/test1/testEntity/?repage";
	}

}
