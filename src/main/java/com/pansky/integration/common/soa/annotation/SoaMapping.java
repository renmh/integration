package com.pansky.integration.common.soa.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SoaMapping {
	/**
	 * 输入报文映射类
	 * @return
	 */
	 public Class request();
	 
	 
	 /**
	 * 输出报文映射类
	 * @return
	 */
	 public Class response();
}
