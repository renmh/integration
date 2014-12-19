package com.pansky.integration.modules.test.entity.test1;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.pansky.integration.common.persistence.IdEntity;

/**
 * 功能Entity
 * @author renmh
 * @version 2014-12-18
 */
@Entity
@Table(name = "test_test1_test1")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Test1 extends IdEntity<Test1> {
	
	private static final long serialVersionUID = 1L;
	private String dm; 		// 代码
	private String name; 	// 名称

	public Test1() {
		super();
	}

	@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=200)
	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}
	
}


