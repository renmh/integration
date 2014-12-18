package com.pansky.integration.modules.test.service.test1;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.pansky.integration.common.test.SpringTransactionalContextTests;
import com.pansky.integration.modules.test.entity.test1.TestEntity;

/**
 * 功能测试ServiceTest
 * @author renmh
 * @version 2014-12-17
 */
public class TestEntityServiceTest extends SpringTransactionalContextTests {

	@Autowired
	private TestService testEntityService;
	@Test
	//@Rollback(false)
	public void testAll() {
		TestEntity entity=new TestEntity();
		entity.setRemarks("remarks");
		//保存
		testEntityService.save(entity);
		//根据ID获取
		entity=testEntityService.get(entity.getId());
		//更新
		entity.setRemarks("remarks1");
		testEntityService.save(entity);
		//删除
		testEntityService.delete(entity.getId());
		//测试版本库
		TestCase.assertNotNull(entity.getCreateDate());
	}
}
