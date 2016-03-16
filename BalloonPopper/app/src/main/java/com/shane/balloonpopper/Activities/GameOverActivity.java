package com.shane.balloonpopper.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.shane.balloonpopper.SurfaceViews.GameOverSurfaceView;

/**
 * Created by eddy2 on 16/03/2016.
 */
/*
Edward:
This activity is essentially a springboard so we can get to the gameover Surface View
 */
public class GameOverActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new GameOverSurfaceView(this));

    }
}
