package com.example.sck.circles_survival.views;

public interface IGameCanvasView {

    void drawCircle(BaseCircle circle);

    void redraw();

    void showMessage(String text);
}
