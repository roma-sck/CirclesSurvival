package com.example.sck.circles_survival.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.sck.circles_survival.R;

public class GameActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
    }
}
