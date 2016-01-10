package com.example.sck.circles_survival.views;

import android.graphics.Color;

import com.example.sck.circles_survival.manager.GameManager;

import java.util.Random;

public class EnemyCircle extends BaseCircle {

    public static final int FROM_RADIUS = 10;
    public static final int TO_RADIUS = 110;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOD_COLOR = Color.GREEN;
    public static final int RANDOM_SPEED = 10;
    private int mXdistance;
    private int mYdistance;

    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        mXdistance = dx;
        mYdistance = dy;
    }

    /**
     * create circle with random radius and random coordinates
     *
     * @return new circle
     */
    public static EnemyCircle getRandomCircle() {
        Random rdm = new Random();
        // random coordinates of center
        int x = rdm.nextInt(GameManager.getsCanvasWidth());
        int y = rdm.nextInt(GameManager.getsCanvasHeight());
        // random distance of moveOneStep()
        int dx = 1 + rdm.nextInt(RANDOM_SPEED);
        int dy = 1 + rdm.nextInt(RANDOM_SPEED);
        // random radius
        int radius = FROM_RADIUS + rdm.nextInt(TO_RADIUS - FROM_RADIUS);

        return new EnemyCircle(x, y, radius, dx, dy);
    }

    public void setEnemyOrFoodColor(PlayerCircle playerCircle) {
        if (isSmallerThan(playerCircle)) {
            setColor(FOOD_COLOR);
        } else {
            setColor(ENEMY_COLOR);
        }
    }

    /**
     * check the radius of player and enemy circles
     *
     * @param circle player circle
     * @return true if enemy radius isSmaller, false otherwise
     */
    public boolean isSmallerThan(BaseCircle circle) {
        boolean result = false;
        if (mRadius < circle.mRadius) {
            result = true;
        }
        return result;
    }

    public void moveOneStep() {
        mXcoord += mXdistance;
        mYcoord += mYdistance;

        checkBounds();
    }

    /**
     * change direction when reaches the border of gameCanvas
     */
    private void checkBounds() {
        if (mXcoord > GameManager.getsCanvasWidth() || mXcoord < 0) {
            mXdistance = -mXdistance;
        }
        if (mYcoord > GameManager.getsCanvasHeight() || mYcoord < 0) {
            mYdistance = -mYdistance;
        }
    }
}
