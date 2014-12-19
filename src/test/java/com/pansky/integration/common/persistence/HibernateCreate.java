package com.pansky.integration.common.persistence;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
/**
 * 
 *@Title:
 *@Description:
 *@Author:renmh
 *@Since:2014年12月18日
 *@Version:1.1.0
 */
public class HibernateCreate {
	/**
	 * 在临时数据库表中创建table，临时数据库配置参见hbm2ddl.auto.xml
	 * @Description:
	 */
	@Test
	public void createTable(){
		Configuration cfg = new AnnotationConfiguration().configure("hbm2ddl.auto.xml"); 
		SchemaExport export=new SchemaExport(cfg);
		export.create(true, true);
	}
}
