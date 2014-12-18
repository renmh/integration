package com.pansky.integration.common.persistence;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

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
