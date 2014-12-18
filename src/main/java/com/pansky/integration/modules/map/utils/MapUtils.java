package com.pansky.integration.modules.map.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.pansky.integration.common.utils.JSONUtil;
import com.pansky.integration.modules.map.entity.MapData;

/**
 * 地图工具类
 * @author renmh
 * @version 2013-5-29
 */
public class MapUtils {
	public static String imgDir="D:\\workspace_ES\\mapImages\\images\\zhengzhou\\zh-chs\\mappic\\png";
	public static List<MapData> parseSpotJs(String spotJs) throws JSONException{
		List<MapData> listAll=new ArrayList<MapData>();
		spotJs=spotJs.toLowerCase();
		String[] spArray=spotJs.split(";");
		for(String sp:spArray){
			String json=sp.substring(12,sp.length()-1);
			json=json.replaceAll("coords", "coordsArray");
			List<MapData> list=(List<MapData>)JSONUtil.getList4Json(json, MapData.class);
			listAll.addAll(list);
		}
		return listAll;
	}
}
