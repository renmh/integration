package com.pansky.integration.common.webservice;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TestWebservice {
	/**
	 * 测试调用webservice接口
	 * @throws Exception
	 * @Description:
	 */
	//@Test
	public void testWebService() throws Exception  {
        ClassPathXmlApplicationContext context 
            = new ClassPathXmlApplicationContext(new String[] {"cxf-client.xml"});

        Service client = (Service)context.getBean("client");
        String xmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><tiripPackage xmlns=\"http://www.chinatax.gov.cn/tirip/dataspec\" version=\"1.0\"><identity><serviceId>service.wsyw.wssb.nsrxx</serviceId><channelId>0000</channelId><globalBusinessId>b15569979310197200aca568fcddc1a8</globalBusinessId></identity><contentControl/><routerSession/><businessContent><subPackage><id>0</id><content>";
		String xmlend = "</content></subPackage></businessContent><returnState><returnCode>001</returnCode></returnState></tiripPackage>";
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><wsywTyRequest xmlns=\"http://www.chinatax.gov.cn/dataspec/\" version=\"1.0\" name=\"wsywTyRequest\" cnName=\"纳税人信息查询\"><wsywTyReqBody><zsjgDm>241019131</zsjgDm><nsrsbh>410102671651533</nsrsbh></wsywTyReqBody></wsywTyRequest>";
		String xml = xmlstart + "<![CDATA[" + content + "]]>" + xmlend;
        String response = client.service("service.wsyw.wssb.nsrxx",xml);
        TestCase.assertNotNull(response);
	}
}
