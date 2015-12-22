package com.example.sck.circles_survival.views;

import android.graphics.Color;

import com.example.sck.circles_survival.manager.GameManager;

public class PlayerCircle extends BaseCircle {

    public static final int PLAYER_INIT_RADIUS = 50;
    public static final int PLAYER_MAIN_SPEED = 30;
    public static final int PLAYER_COLOR = Color.BLUE;

    public PlayerCircle(int x, int y) {
        super(x, y, PLAYER_INIT_RADIUS);
        setColor(PLAYER_COLOR);
    }

    public void movePlayerWhenTouch(int x1, int y1) {
        int dx = (x1 - mXcoord) * PLAYER_MAIN_SPEED / GameManager.getsCanvasWidth();
        int dy = (y1 - mYcoord) * PLAYER_MAIN_SPEED / GameManager.getsCanvasHeight();
        mXcoord += dx;
        mYcoord += dy;
    }

    public void initRadius() {
        mRadius = PLAYER_INIT_RADIUS;
    }

    public void growRadius(BaseCircle circle) {
        mRadius = (int) Math.sqrt(Math.pow(mRadius, 2) + Math.pow(circle.mRadius, 2));
    }

    public void reduceRadius() {
        mRadius = mRadius / 2;
    }
}