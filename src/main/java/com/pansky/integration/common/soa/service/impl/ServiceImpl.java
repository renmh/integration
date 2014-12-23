package com.pansky.integration.common.soa.service.impl;

import javax.jws.WebService;

import com.pansky.integration.common.mapper.JaxbMapper;
import com.pansky.integration.common.service.BaseService;
import com.pansky.integration.common.service.ServiceException;
import com.pansky.integration.common.soa.annotation.SoaMapping;
import com.pansky.integration.common.soa.service.Service;
import com.pansky.integration.common.soa.service.SoaBaseService;
import com.pansky.integration.common.soa.xmlBean.SubPackageType;
import com.pansky.integration.common.soa.xmlBean.TiripPackageType;
import com.pansky.integration.common.utils.Exceptions;
import com.pansky.integration.common.utils.Reflections;
import com.pansky.integration.common.utils.SpringContextHolder;
/**
 * SOA请求基类
 * 1.分离业务报文和技术报文
 * 2.根据serviceId 获得对应的service进行业务处理
 * 3.通过SoaMapping 注解获得解析XML实现类
 * 4.soaMapping.request() 转换业务报文
 * 5.soaMapping.response() 转换反馈对象
 * 
 * @author renmh
 *
 */
@org.springframework.stereotype.Service("webservice")
@WebService(targetNamespace="http://www.pansky.com") 
public class ServiceImpl extends BaseService implements Service {
	@Override
	public String service(String serviceId, String xmlData) {
		SoaBaseService service=(SoaBaseService)SpringContextHolder.getBean(serviceId);
		SoaMapping soaMapping=service.getClass().getAnnotation(SoaMapping.class);
		this.logger.debug("serviceId="+serviceId);
		this.logger.debug("xmlData="+xmlData);
		//取得业务报文
		TiripPackageType tiripPackageType=JaxbMapper.fromXml(xmlData,TiripPackageType.class);
		String businessContent=tiripPackageType.getBusinessContent().getSubPackage().get(0).getContent();
		this.logger.debug("业务报文为：="+businessContent);
		Object request=JaxbMapper.fromXml(businessContent, soaMapping.request());
		Object response=null;
		try {
			try {
				response=soaMapping.response().newInstance();
				String className=soaMapping.response().getName();
				className=className.substring(className.lastIndexOf(".")+1);
				className=className.replaceFirst(className.substring(0,1), className.substring(0,1).toLowerCase());
				Reflections.invokeSetter(response, "name", className);
				Reflections.invokeSetter(response, "version", "1.0");
			} catch (Exception e) {
				
			} 
			service.service(request,response);
			String responseXml=JaxbMapper.toXml(response);
			tiripPackageType.getBusinessContent().getSubPackage().get(0).setContent("<![CDATA["+responseXml+"]]>");
			tiripPackageType.getBusinessContent().getSubPackage().get(0).setId("0");
			tiripPackageType.getReturnState().setReturnCode("000");
			tiripPackageType.getReturnState().setReturnMessage("调用服务成功");
		} catch (ServiceException e) {
			tiripPackageType.getReturnState().setReturnCode("001");
			tiripPackageType.getReturnState().setReturnMessage(e.getMessage());
			this.logger.debug(Exceptions.getStackTraceAsString(e));
		}
		String result=JaxbMapper.toXml(tiripPackageType);
		this.logger.debug("返回结果：="+result);
		return result;
	}

}
