package com.example.sck.circles_survival.manager;

import android.graphics.Color;

import com.example.sck.circles_survival.App;
import com.example.sck.circles_survival.R;
import com.example.sck.circles_survival.views.BaseCircle;
import com.example.sck.circles_survival.views.GameCanvasView;
import com.example.sck.circles_survival.views.EnemyCircle;
import com.example.sck.circles_survival.views.PlayerCircle;

import java.util.ArrayList;

public class GameManager {

    private PlayerCircle mPlayerCircle;
    private ArrayList<EnemyCircle> mEnemyCircles;
    private GameCanvasView mGameCanvas;
    private static int sCanvasWidth;
    private static int sCanvasHeight;
    private static int sMaxEnemies;

    public GameManager(GameCanvasView mGameCanvas, int w, int h) {
        this.mGameCanvas = mGameCanvas;
        sCanvasWidth = w;
        sCanvasHeight = h;

        int selectedColor = defineBGcolor(GameCanvasView.getGameBGcolor());
        mGameCanvas.setBackgroundColor(selectedColor);

        initMainCircle();

        initEnemyCircles();
    }

    public static int getMaxEnemies() {
        return sMaxEnemies;
    }

    public static void setMaxEnemies(int enemies) {
        sMaxEnemies = enemies;
    }

    private int defineBGcolor(String strGameBGcolor) {
        int intGameBGcolor = 0;
        switch (strGameBGcolor) {
            case "Gray" : intGameBGcolor = Color.GRAY;
                break;
            case "White" : intGameBGcolor = Color.WHITE;
                break;
            case "Black" : intGameBGcolor = Color.BLACK;
                break;
            case "Magenta" : intGameBGcolor = Color.MAGENTA;
                break;
        }
        return intGameBGcolor;
    }

    private void initMainCircle() {
        mPlayerCircle = new PlayerCircle(sCanvasWidth / 2, sCanvasHeight / 2);
    }

    private void initEnemyCircles() {
        BaseCircle mainCircleArea = mPlayerCircle.getCircleArea();
        mEnemyCircles = new ArrayList<>();
        for (int i = 0; i < getMaxEnemies(); i++) {
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isIntersect(mainCircleArea));
            mEnemyCircles.add(circle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {
        for (EnemyCircle circle : mEnemyCircles) {
            circle.setEnemyOrFoodColor(mPlayerCircle);
        }
    }

    public static int getsCanvasWidth() {
        return sCanvasWidth;
    }

    public static int getsCanvasHeight() {
        return sCanvasHeight;
    }

    public void onDraw() {
        mGameCanvas.drawCircle(mPlayerCircle);
        for (EnemyCircle circle : mEnemyCircles) {
            mGameCanvas.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mPlayerCircle.movePlayerWhenTouch(x, y);

        checkCollision();

        moveCircles();
    }

    private void checkCollision() {
        for (EnemyCircle circle : mEnemyCircles) {
            // player and enemy circles clashed
            if (mPlayerCircle.isIntersect(circle)) {
                // if it is food-circle - nom-nom-nom)
                if (circle.isSmallerThan(mPlayerCircle)) {
                    mPlayerCircle.growRadius(circle);
                    mEnemyCircles.remove(circle);
                    calculateAndSetCirclesColor();
                    break;
                } else {
                    // if it is big enemy-circle - reduceRadius(
                    mPlayerCircle.reduceRadius();
                    if(mPlayerCircle.getRadius() < PlayerCircle.PLAYER_INIT_RADIUS) {
                        // if radius smaller than INIT_RADIUS -> GAME OVER
                        gameEnd(App.getContext().getString(R.string.game_lose));
                        return;
                    } else {
                        // continue playing
                        calculateAndSetCirclesColor();
                        break;
                    }
                }
            }
        }
        if (mEnemyCircles.isEmpty()) {
            // player WINS
            gameEnd(App.getContext().getString(R.string.game_win));
        }
    }

    private void gameEnd(String text) {
        mGameCanvas.showMessage(text);
        mPlayerCircle.initRadius();
        initEnemyCircles();
        mGameCanvas.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle circle : mEnemyCircles) {
            circle.moveOneStep();
        }
    }
}
