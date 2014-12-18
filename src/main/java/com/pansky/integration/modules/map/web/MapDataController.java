package com.pansky.integration.modules.map.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pansky.integration.common.config.Global;
import com.pansky.integration.common.persistence.Page;
import com.pansky.integration.common.utils.StringUtils;
import com.pansky.integration.common.web.BaseController;
import com.pansky.integration.modules.map.entity.MapData;
import com.pansky.integration.modules.map.service.MapDataService;
import com.pansky.integration.modules.map.utils.MapUtils;

/**
 * 地图数据Controller
 * 
 * @author renmh
 * @version 2014-12-2
 */
@Controller
@RequestMapping(value = "${adminPath}/manager/mapData")
public class MapDataController extends BaseController {

	@Autowired
	private MapDataService mapDataService;

	@ModelAttribute
	public MapData get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			MapData data = mapDataService.getMapData(id);
			if (data == null) {
				return new MapData();
			}
			return data;
		} else {
			return new MapData();
		}
	}

	@RequiresPermissions("manager:map:view")
	@RequestMapping({ "list", "" })
	public String list(MapData mapData, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		logger.debug("地图数据查询");
		Page<MapData> page = mapDataService.findMapData(new Page<MapData>(
				request, response), mapData);
		model.addAttribute("page", page);
		return "modules/map/mapDataList";
	}

	@RequiresPermissions("manager:map:view")
	@RequestMapping(value = "form")
	public String form(MapData mapData, Model model) {
		model.addAttribute("mapData", mapData);
		return "modules/map/mapDataForm";
	}

	@RequiresPermissions("manager:map:edit")
	@RequestMapping(value = "save")
	public String save(MapData mapData, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath()
					+ "/manager/mapData/?repage";
		}
		if (!beanValidator(model, mapData)) {
			return form(mapData, model);
		}
		mapDataService.save(mapData);
		addMessage(redirectAttributes, "保存地图数据'" + mapData.getName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/manager/mapData/?repage";
	}

	@RequiresPermissions("manager:map:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath()
					+ "/manager/mapData/?repage";
		}
		mapDataService.delete(id);
		addMessage(redirectAttributes, "删除地图数据成功");
		return "redirect:" + Global.getAdminPath() + "/manager/mapData/?repage";
	}

	@RequiresPermissions("manager:map:edit")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file,
			RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws IOException, JSONException {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath()
					+ "/manager/mapData/?repage";
		}
		String fileName = file.getOriginalFilename();
		Integer zoom = -1;
		try {
			zoom = Integer
					.valueOf(fileName.substring(0, fileName.indexOf(".")));
		} catch (Exception e) {
			addMessage(redirectAttributes, "文件名必须为0,1,2,3,请修改");
			return "redirect:" + Global.getAdminPath()
					+ "/manager/mapData/?repage";
		}
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "temp\\mapZip\\";
		File saveFile = new File(path, file.getOriginalFilename());

		// 保存ZIP文件到磁盘
		if (saveFile.exists()) {
			saveFile.delete();
		}
		if (!saveFile.exists()) {
			saveFile.mkdirs();
			file.transferTo(saveFile);
		}
		ZipFile zipFile = new ZipFile(saveFile);
		@SuppressWarnings("rawtypes")
		Enumeration enums = zipFile.getEntries();
		ZipEntry entry = null;
		while (enums.hasMoreElements()) {
			entry = (ZipEntry) enums.nextElement();
			// 从ZIP条目获得输入流
			InputStream in = zipFile.getInputStream(entry);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in,"utf-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			List<MapData> l=MapUtils.parseSpotJs(sb.toString());
			for(MapData m:l){
				m.setZoom(zoom);
				m.setDm(m.getId());
				m.setId(null);
				m.setRemarks(entry.getName());
				mapDataService.save(m);
			}
		}
		addMessage(redirectAttributes, "导入成功！");
		return "redirect:" + Global.getAdminPath() + "/manager/mapData/?repage";
	}

}
