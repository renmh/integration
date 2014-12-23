package com.pansky.integration.common.utils;


import org.junit.Test;

import com.pansky.integration.common.mapper.JaxbMapper;
import com.pansky.integration.common.soa.xmlBean.IdentityType;
import com.pansky.integration.common.soa.xmlBean.TiripPackageType;

public class TestJaxb {
	/**
	 * 测试jaxb转换
	 * 
	 * @Description:
	 */
	@Test
	public void testJaxb(){
		TiripPackageType tiripPackageType=new TiripPackageType();
		IdentityType identityType=new IdentityType();
		identityType.setChannelId("11");
		tiripPackageType.setIdentity(identityType);
		String ss=JaxbMapper.toXml(tiripPackageType);
		System.out.println(ss);
	}
	/**
	 * 测试String转换
	 * 
	 * @Description:
	 */
	@Test
	public void testString(){
		String className=this.getClass().getName();
		className=className.substring(className.lastIndexOf(".")+1);
		className=className.replaceFirst(className.substring(0,1), className.substring(0,1).toLowerCase());
		System.out.println(className);
	}
}
