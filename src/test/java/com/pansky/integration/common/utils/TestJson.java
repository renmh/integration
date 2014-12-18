package com.pansky.integration.common.utils;

import java.util.List;

import junit.framework.TestCase;

import org.json.JSONException;
import org.junit.Test;

import com.pansky.integration.modules.map.entity.MapData;
/**
 * 
 *@Title:
 *@Description:
 *@Author:renmh
 *@Since:2014年12月16日
 *@Version:1.1.0
 */
public class TestJson {
	/**
	 * 
	 * @throws JSONException
	 * @Description:
	 */
	@Test
	public void jaonToList() throws JSONException {
		String json = "[{id:'70830',name:'郑州电缆有限公司',lx:33428,ly:26091,cx:33428,cy:26091,group:'hotarea',coords : ['33083,26048,33585,26365,33828,26198,33825,26166,33806,26151,33795,26144,33287,25822,33274,25818,33028,25984,33029,26011,33056,26030']},{id:'70833',name:'郑州电缆有限公司',lx:33784,ly:25779,cx:33784,cy:25779,group:'hotarea',coords : ['33649,25599,33584,25633,33502,25701,33511,25740,33574,25783,33588,25777,33815,25933,33847,25920,33896,25944,33949,25922,34015,25959,34064,25924,34067,25895,34035,25872,34002,25835,33993,25808,33957,25782,33939,25779,33935,25784']},{id:'70815',name:'郑州电缆有限公司',lx:34091,ly:25660,cx:34091,cy:25660,group:'hotarea',coords : ['33833,25649,34258,25931,34471,25775,34472,25730,33944,25390,33722,25539,33711,25575,33724,25583']}]";
		json = json.replaceAll("coords", "coordsArray");
		List<MapData> list = (List<MapData>) JSONUtil.getList4Json(json, MapData.class);
		TestCase.assertNotSame(list.size(), 0);
	}

}
