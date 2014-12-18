package com.pansky.integration.modules.test.service.test1;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pansky.integration.common.persistence.Page;
import com.pansky.integration.common.service.BaseService;
import com.pansky.integration.common.utils.StringUtils;
import com.pansky.integration.modules.test.dao.test1.TestDao;
import com.pansky.integration.modules.test.entity.test1.TestEntity;

/**
 * 功能测试Service
 * @author renmh
 * @version 2014-12-10
 */
@Component
@Transactional(readOnly = true)
public class TestService extends BaseService {

	@Autowired
	private TestDao testDao;
	
	public TestEntity get(String id) {
		return testDao.get(id);
	}
	
	public Page<TestEntity> find(Page<TestEntity> page, TestEntity test) {
		DetachedCriteria dc = testDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(test.getName())){
			dc.add(Restrictions.like("name", "%"+test.getName()+"%"));
		}
		dc.add(Restrictions.eq(TestEntity.FIELD_DEL_FLAG, TestEntity.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return testDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TestEntity test) {
		testDao.save(test);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		testDao.deleteById(id);
	}
	
}
