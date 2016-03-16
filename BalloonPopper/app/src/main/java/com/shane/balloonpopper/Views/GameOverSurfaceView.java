package com.shane.balloonpopper.Views;

import android.content.Context;
import android.content.Intent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shane.balloonpopper.Activities.GameScreenActivity;

/**
 * Created by eddy2 on 16/03/2016.
 */
public class GameOverSurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    public GameOverSurfaceView(Context context) {
        super(context);
        //add the callback to the surfaceHolder to intercept events
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("Game Over!");
        
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}