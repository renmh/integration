package com.pansky.integration.modules.map.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.pansky.integration.common.persistence.IdEntity;

/**
 * 地图数据Entity
 * 
 * @author renmh
 * @version 2014-12-02
 */
@Entity
@Table(name = "geo_spots")
@DynamicInsert
@DynamicUpdate
public class MapData extends  IdEntity<MapData> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String dm;// id
	private String name;// 名称
	private String lx; // 弹窗指示位置X
	private String ly; // 弹窗指示位置Y
	private String cx; // 气泡显示位置X
	private String cy; // 气泡显示位置Y
	private String group; // 分组
	private String coords;// 坐标
	private Integer zoom;// 缩放级别
	private List<String> coordsArray;// coords是array形式的

	@Transient
	public List<String> getCoordsArray() {
		return coordsArray;
	}

	/**
	 * 给坐标赋值
	 * 
	 * @param coordsArray
	 */
	public void setCoordsArray(List<String> coordsArray) {
		this.coordsArray = coordsArray;
		String scoords="";
		if(coordsArray!=null&&coordsArray.size()>0){
			if(coordsArray.size()==1){
				coords=coordsArray.get(0);
				return;
			}
			for(String s:coordsArray){
				scoords+=s+";";
			}
		}
		coords=scoords;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public MapData() {
		super();
	}

	@Column(length = 50)
	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	@Column(length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 150)
	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	@Column(length = 150)
	public String getLy() {
		return ly;
	}

	public void setLy(String ly) {
		this.ly = ly;
	}

	@Column(length = 150)
	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	@Column(length = 150)
	public String getCy() {
		return cy;
	}

	public void setCy(String cy) {
		this.cy = cy;
	}

	@Column(name = "_group", length = 20)
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Column(length = 5000)
	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

}