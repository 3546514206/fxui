package cn.lichenfei.fxui.common;

import javafx.geometry.Rectangle2D;

public class CFBounds {

    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double width;
    private double height;

    public static CFBounds get(Rectangle2D r) {
        return new CFBounds(r);
    }

    private CFBounds(Rectangle2D r) {
        this.minX = r.getMinX();
        this.minY = r.getMinY();
        this.maxX = r.getMaxX();
        this.maxY = r.getMaxY();
        this.width = r.getWidth();
        this.height = r.getHeight();
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
