package com.pansky.integration.modules.test.entity.test1;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.pansky.integration.common.persistence.IdEntity;

/**
 * 功能测试Entity
 * @author renmh
 * @version 2014-12-10
 */
@Entity
@Table(name = "test_test1_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestEntity extends IdEntity<TestEntity> {
	
	private static final long serialVersionUID = 1L;
	private String dm; 		// 代码
	private String name; 	// 名称
	private String test1;
	private String test2;
	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}


	public String getTest2() {
		return test2;
	}

	public void setTest2(String test2) {
		this.test2 = test2;
	}

	public TestEntity() {
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


