package com.pansky.integration.modules.map.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pansky.integration.common.utils.HtmlUtil;
import com.pansky.integration.common.web.BaseController;
import com.pansky.integration.modules.map.entity.MapImgBean;
import com.pansky.integration.modules.map.entity.MapQueryBean;
import com.pansky.integration.modules.map.service.Map3dService;
import com.pansky.integration.modules.map.utils.MapUtils;

/**
 * 三维地图展示Controller
 * 
 * @author renmh
 * @version 2014-11-13
 */
@Controller
@RequestMapping(value = "${frontPath}/map")
public class Map3dController extends BaseController {

	@Autowired
	private Map3dService map3dService;

	@RequestMapping(value = { "init", "" })
	public String initMap(MapQueryBean mapQueryBean,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		return "modules/" + "map/map";
	}

	@RequestMapping(value = "test")
	public String test(MapQueryBean model, HttpServletResponse response) {
		return "modules/" + "map/test";
	}

	@RequestMapping(value = "mapInit")
	public String mapInit(MapQueryBean model, HttpServletResponse response) {
		return "modules/" + "map/3DMap";
	}

	@RequestMapping("/getMap")
	public void getMap(MapQueryBean mapQueryBean, HttpServletResponse response,
			Model model) {
		List<MapImgBean> list = map3dService.queryImages(mapQueryBean);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put("images", list);
		HtmlUtil.writerJson(response, result);
	}

	@RequestMapping("/getImg")
	public void getMap(MapQueryBean model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String zoom = request.getParameter("zoom");
		String imageName = request.getParameter("imgName");
		String imgUrl = MapUtils.imgDir + zoom + "\\" + imageName + ".png";
		FileInputStream fileStream = new FileInputStream(imgUrl);
		int i = fileStream.available();
		byte[] data = new byte[i];
		fileStream.read(data);
		fileStream.close();
		response.setContentType("image/*");
		OutputStream toClient = response.getOutputStream();
		toClient.write(data);
		toClient.close();
	}
}
