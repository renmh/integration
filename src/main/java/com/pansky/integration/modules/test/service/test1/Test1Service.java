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
import com.pansky.integration.modules.test.entity.test1.Test1;
import com.pansky.integration.modules.test.dao.test1.Test1Dao;

/**
 * 功能Service
 * @author renmh
 * @version 2014-12-18
 */
@Component
@Transactional(readOnly = true)
public class Test1Service extends BaseService {

	@Autowired
	private Test1Dao test1Dao;
	
	public Test1 get(String id) {
		return test1Dao.get(id);
	}
	
	public Page<Test1> find(Page<Test1> page, Test1 test1) {
		DetachedCriteria dc = test1Dao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(test1.getName())){
			dc.add(Restrictions.like("name", "%"+test1.getName()+"%"));
		}
		dc.add(Restrictions.eq(Test1.FIELD_DEL_FLAG, Test1.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return test1Dao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Test1 test1) {
		test1Dao.save(test1);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		test1Dao.deleteById(id);
	}
	
}
