package com.pansky.integration.modules.test.service.test1;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.pansky.integration.common.test.SpringTransactionalContextTests;
import com.pansky.integration.modules.test.entity.test1.Test1;
import com.pansky.integration.modules.test.service.test1.Test1Service;

/**
 * 功能ServiceTest
 * @author renmh
 * @version 2014-12-18
 */
public class Test1ServiceTest extends SpringTransactionalContextTests {

	@Autowired
	private Test1Service test1Service;
	@Test
	public void testAll() {
		Test1 entity=new Test1();
		entity.setRemarks("remarks");
		//保存
		test1Service.save(entity);
		//根据ID获取
		entity=test1Service.get(entity.getId());
		//更新
		entity.setRemarks("remarks1");
		test1Service.save(entity);
		//删除
		test1Service.delete(entity.getId());
		TestCase.assertNotNull(entity.getCreateDate());
	}
	@Test
	public void testFind() {
		
	}
}
