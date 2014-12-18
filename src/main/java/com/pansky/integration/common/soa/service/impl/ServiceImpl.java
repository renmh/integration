package com.pansky.integration.common.soa.service.impl;

import javax.jws.WebService;

import com.pansky.integration.common.mapper.JaxbMapper;
import com.pansky.integration.common.service.BaseService;
import com.pansky.integration.common.soa.annotation.SoaMapping;
import com.pansky.integration.common.soa.service.Service;
import com.pansky.integration.common.soa.service.SoaBaseService;
import com.pansky.integration.common.soa.xmlBean.TiripPackageType;
import com.pansky.integration.common.utils.SpringContextHolder;
/**
 * SOA请求基类
 * 1.分离业务报文和技术报文
 * 2.根据serviceId 获得对应的service进行业务处理
 * 3.通过SoaMapping 注解获得解析XML实现类
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
		/**
		 * soaMapping.request() 转换业务报文
		 * soaMapping.response() 转换反馈对象
		 */
		return "服务调用成功"+serviceId+"--"+xmlData;
	}

}
