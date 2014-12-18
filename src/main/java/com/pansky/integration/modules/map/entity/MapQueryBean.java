package com.pansky.integration.modules.map.entity;


public class MapQueryBean{
    private Center center;

    private int zoom;

    private int width;

    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public Bbox getBbox() {
        return bbox;
    }

    public void setBbox(Bbox bbox) {
        this.bbox = bbox;
    }

    private Bbox bbox;
}
