package com.pansky.integration.modules.map.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pansky.integration.common.service.BaseService;
import com.pansky.integration.modules.map.entity.Bbox;
import com.pansky.integration.modules.map.entity.MapImgBean;
import com.pansky.integration.modules.map.entity.MapQueryBean;

/**
 * 三维地图展示Service
 * @author renmh
 * @version 2014-11-13
 */
@Component
@Transactional(readOnly = true)
public class Map3dService extends BaseService {
	public List<MapImgBean> queryImages(MapQueryBean model) {
        Bbox box=model.getBbox();
        double centerX=box.getCenter().getX();
        double centerY=box.getCenter().getY();
       
        int indexX = (int) Math.ceil(centerX / 256);
        int indexY = (int) Math.ceil(centerY / 256);
        List<MapImgBean> list = new ArrayList<MapImgBean>();
        for (int i = -box.getXcount(); i < box.getXcount(); i++) {// 左侧
            for(int y=-box.getYcount();y<box.getYcount();y++){
                String imgName = String.valueOf(indexX + i) + "," + String.valueOf(indexY+y);
                MapImgBean imgBean = new MapImgBean();
                imgBean.setImgUrl(imgName);
                imgBean.setZoom(model.getZoom());
                imgBean.setLeft((indexX + i) * 256);
                imgBean.setTop((indexY+y)*256);
                list.add(imgBean);
            }
        }
        return list;
    }	
}
