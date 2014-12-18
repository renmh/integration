package com.pansky.integration.modules.map.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pansky.integration.common.persistence.Page;
import com.pansky.integration.common.service.BaseService;
import com.pansky.integration.common.utils.StringUtils;
import com.pansky.integration.modules.map.dao.MapDataDao;
import com.pansky.integration.modules.map.entity.MapData;
import com.pansky.integration.modules.sys.entity.User;
import com.pansky.integration.modules.sys.utils.UserUtils;

/**
 * 地图数据处理Service
 * @author renmh
 * @version 2014-12-02
 */
@Component
@Transactional(readOnly = true)
public class MapDataService extends BaseService {
	@Autowired
	private MapDataDao mapDataDao;
	public MapData getMapData(String id) {
		return mapDataDao.get(id);
	}

	public Page<MapData> findMapData(Page<MapData> page, MapData mapData) {
		User currentUser = UserUtils.getUser();
		DetachedCriteria dc = mapDataDao.createDetachedCriteria();
		
		// 如果不是超级管理员，则不显示超级管理员用户
		if (!currentUser.isAdmin()){
			dc.add(Restrictions.ne("id", "1"));  
		}
		
		dc.add(dataScopeFilter(currentUser, "office", ""));
		dc.add(Restrictions.eq(MapData.FIELD_DEL_FLAG, MapData.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(mapData.getName())){
			dc.add(Restrictions.like("name", "%" + mapData.getName() + "%"));
		}
		if (!StringUtils.isNotEmpty(page.getOrderBy())){
			dc.addOrder(Order.asc("name"));
		}
		return mapDataDao.find(page, dc);
	}
	@Transactional(readOnly = false)
	public void save(MapData mapData) {
		mapDataDao.save(mapData);
	}
	@Transactional(readOnly = false)
	public void delete(String id) {
		mapDataDao.deleteById(id);
		
	}	
}
