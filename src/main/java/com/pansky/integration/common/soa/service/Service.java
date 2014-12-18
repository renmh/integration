package com.pansky.integration.common.soa.service;

import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(serviceName="service",targetNamespace="http://www.pansky.com") 
public interface Service {
	public String service(@WebParam(name = "serviceId",targetNamespace="http://www.pansky.com")String serviceId,@WebParam(name = "xmlData",targetNamespace="http://www.pansky.com")String xmlData);
}
