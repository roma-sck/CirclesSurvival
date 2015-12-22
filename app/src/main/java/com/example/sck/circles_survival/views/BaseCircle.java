package com.example.sck.circles_survival.views;

public class BaseCircle {

    protected int mXcoord;
    protected int mYcoord;
    protected int mRadius;
    private int mColor;

    public BaseCircle(int x, int y, int mRadius) {
        mXcoord = x;
        mYcoord = y;
        this.mRadius = mRadius;
    }

    public int getX() {
        return mXcoord;
    }

    public int getY() {
        return mYcoord;
    }

    public int getRadius() {
        return mRadius;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public BaseCircle getCircleArea() {
        return new BaseCircle(mXcoord, mYcoord, mRadius * 3);
    }

    public boolean isIntersect(BaseCircle circle) {
        return mRadius + circle.mRadius >= Math.sqrt(
                Math.pow(mXcoord - circle.mXcoord, 2) + Math.pow(mYcoord - circle.mYcoord, 2) );
    }
}
