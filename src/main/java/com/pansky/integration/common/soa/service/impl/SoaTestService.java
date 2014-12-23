package com.pansky.integration.common.soa.service.impl;

import org.springframework.stereotype.Service;

import com.pansky.integration.common.service.BaseService;
import com.pansky.integration.common.soa.annotation.SoaMapping;
import com.pansky.integration.common.soa.service.SoaBaseService;
import com.pansky.integration.common.soa.xmlBean.bussbusinessContent.ReturnInfo;
import com.pansky.integration.common.soa.xmlBean.bussbusinessContent.WsywTyRequest;
import com.pansky.integration.common.soa.xmlBean.bussbusinessContent.WsywTyRespBody;
import com.pansky.integration.common.soa.xmlBean.bussbusinessContent.WsywTyResponse;
/**
 * 
 *@Title:测试类
 *@Description:测试框架soa功能
 *@Author:renmh
 *@Since:2014年12月23日
 *@Version:1.1.0
 */
@SoaMapping(request=WsywTyRequest.class,response=WsywTyResponse.class)
@Service("service.wsyw.wssb.nsrxx")
public class SoaTestService extends BaseService implements SoaBaseService {

	@Override
	public void service(Object obj,Object res) {
		WsywTyRequest request=(WsywTyRequest)obj;
		WsywTyResponse response=(WsywTyResponse)res;
		response.setCnName("服务测试反馈");
		WsywTyRespBody wsywTyRespBody=new WsywTyRespBody();
		response.setWsywTyResp(wsywTyRespBody);
		wsywTyRespBody.setTyResult("服务测试结果");
		ReturnInfo returnInfo=new ReturnInfo();
		returnInfo.setReturnCode("001");
		returnInfo.setReturnMessage("return message");
		wsywTyRespBody.setReturnInfo(returnInfo);
	}

}
