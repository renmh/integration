package com.pansky.integration.modules.map.entity;

public class MapImgBean {
	private double top;
	private double left;
	private int z_index;
	private int zoom;
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	private String imgUrl;
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public int getZ_index() {
		return z_index;
	}
	public void setZ_index(int z_index) {
		this.z_index = z_index;
	}
}
