package com.pansky.integration.common.soa.service.impl;

import org.springframework.stereotype.Service;

import com.pansky.integration.common.service.BaseService;
import com.pansky.integration.common.soa.annotation.SoaMapping;
import com.pansky.integration.common.soa.service.SoaBaseService;
import com.pansky.integration.modules.map.entity.MapData;
@SoaMapping(request=MapData.class,response=MapData.class)
@Service("service.wsyw.wssb.nsrxx")
public class SoaTestService extends BaseService implements SoaBaseService {

	@Override
	public Object service(Object request) {
		// TODO Auto-generated method stub
		return null;
	}

}
