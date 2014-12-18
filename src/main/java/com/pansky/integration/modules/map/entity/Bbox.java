package com.pansky.integration.modules.map.entity;


public class Bbox {
    private Double left;

    private Double right;

    private Double bottom;

    private Double top;

    private Center center;

    public Center getCenter() {
        Center c=new Center();
        c.setX(Math.abs(this.left)+Math.ceil((this.right-this.left)/2));
        c.setY(Math.abs(this.top)+Math.ceil((this.bottom-this.top)/2));
        return c;
    }

    public int getXcount() {
        return Math.abs(((int) Math.ceil((this.right - this.left) / 256) + 1) / 2);
    }

    public int getYcount() {
        return Math.abs(((int) Math.ceil((this.bottom - this.top) / 256) + 1) / 2);
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Double getBottom() {
        return bottom;
    }

    public void setBottom(Double bottom) {
        this.bottom = bottom;
    }

    public Double getTop() {
        return top;
    }

    public void setTop(Double top) {
        this.top = top;
    }
}
