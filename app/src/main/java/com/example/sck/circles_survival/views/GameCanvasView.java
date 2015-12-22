package com.example.sck.circles_survival.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Toast;

import com.example.sck.circles_survival.manager.GameManager;

public class GameCanvasView extends View implements IGameCanvasView {

    private static int sCanvasWidth;
    private static int sCanvasHeight;
    private GameManager mGameManager;
    private Paint mPaint;
    private Canvas mCanvas;
    private Toast mToast;

    public GameCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initDisplaySize(context);
        initPaint();

        mGameManager = new GameManager(this, sCanvasWidth, sCanvasHeight);
    }

    private void initPaint() {
        // Paint class holds the style and color information about how to draw geometries, text and bitmaps
        mPaint = new Paint();
        // smooths out the edges
        mPaint.setAntiAlias(true);
        // circles will be filled
        mPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * get screen size of android device
     *
     * @param context to get WindowManager
     */
    private void initDisplaySize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        sCanvasWidth = point.x;
        sCanvasHeight = point.y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        mGameManager.onDraw();
    }

    @Override
    public void drawCircle(BaseCircle circle) {
        mPaint.setColor(circle.getColor());
        mCanvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), mPaint);
    }

    @Override
    public void redraw() {
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (mToast != null) {
            mToast.cancel();
        }
        // shows WIN or LOSE message toasts
        mToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mGameManager.onTouchEvent(x, y);
        }
        invalidate();
        return true;
    }


    public static int recalculateRadius(int mRadius) {
        return mRadius * 768 / sCanvasWidth < sCanvasHeight ? sCanvasWidth : sCanvasHeight;
    }
}
