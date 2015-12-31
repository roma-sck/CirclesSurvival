package com.example.sck.circles_survival.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sck.circles_survival.R;
import com.example.sck.circles_survival.manager.GameManager;

public class GameCanvasView extends View implements IGameCanvasView {

    private static int sCanvasWidth;
    private static int sCanvasHeight;
    private GameManager mGameManager;
    private Paint mPaint;
    private Canvas mCanvas;
    private static String sGameBGcolor = null;

    public GameCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initDisplaySize(context);
        initPaint();

        mGameManager = new GameManager(this, sCanvasWidth, sCanvasHeight);
    }

    public static String getGameBGcolor() {
        return sGameBGcolor;
    }

    public static void setGameBGcolor(String gameBGcolor) {
        sGameBGcolor = gameBGcolor;
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
        // shows WIN or LOSE message dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_game_end);
        dialog.setCancelable(false);
        dialog.setTitle(R.string.dialog_game_end_title);

        // set the dialog components
        TextView gameResult = (TextView) dialog.findViewById(R.id.gameResult);
        gameResult.setText(text);

        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        if(text.equals(getResources().getString(R.string.game_win))) {
            image.setImageResource(R.drawable.game_win);
        } else {
            image.setImageResource(R.drawable.game_lose);
        }

        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // if btnOk clicked - close the dialog
                dialog.dismiss();
            }
        });
        dialog.show();
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
}
