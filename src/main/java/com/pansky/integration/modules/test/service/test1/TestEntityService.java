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
import com.pansky.integration.modules.test.entity.test1.TestEntity;
import com.pansky.integration.modules.test.dao.test1.TestEntityDao;

/**
 * 功能测试Service
 * @author renmh
 * @version 2014-12-17
 */
@Component
@Transactional(readOnly = true)
public class TestEntityService extends BaseService {

	@Autowired
	private TestEntityDao testEntityDao;
	
	public TestEntity get(String id) {
		return testEntityDao.get(id);
	}
	
	public Page<TestEntity> find(Page<TestEntity> page, TestEntity testEntity) {
		DetachedCriteria dc = testEntityDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(testEntity.getName())){
			dc.add(Restrictions.like("name", "%"+testEntity.getName()+"%"));
		}
		dc.add(Restrictions.eq(TestEntity.FIELD_DEL_FLAG, TestEntity.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return testEntityDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TestEntity testEntity) {
		testEntityDao.save(testEntity);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		testEntityDao.deleteById(id);
	}
	
}
