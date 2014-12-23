package com.pansky.integration.common.soa.service;

import com.pansky.integration.common.service.ServiceException;

public interface SoaBaseService {
	public void service(Object request,Object response) throws ServiceException;
}
