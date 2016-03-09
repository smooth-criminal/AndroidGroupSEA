package com.shane.balloonpopper.Views;

import android.content.Context;
import android.content.Intent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shane.balloonpopper.Activities.GameScreenActivity;


/**
 * Created by Shane on 09/03/2016.
 */
public class MainMenuSurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    public MainMenuSurfaceView(Context context) {
        super(context);
        //add the callback to the surfaceHolder to intercept events
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("HELLO");
        Intent i = new Intent().setClass(getContext(), GameScreenActivity.class);//THESE TWO LINES ARE IMPORTANT
        getContext().startActivity(i);//THESE TWO LINES ARE IMPORTANT
        /*
        They allow us to swap between different Activities from within the SurfaceViews
         */
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
