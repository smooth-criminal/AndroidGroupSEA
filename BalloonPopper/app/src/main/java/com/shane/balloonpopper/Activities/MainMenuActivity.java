package com.shane.balloonpopper.Activities;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.shane.balloonpopper.R;
import com.shane.balloonpopper.Views.GameSurfaceView;
import com.shane.balloonpopper.Views.MainMenuSurfaceView;

public class MainMenuActivity extends Activity {
    /*
    These activities will call a surfaceview which then
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
System.out.println("1");
        super.onCreate(savedInstanceState);


        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new MainMenuSurfaceView(this));


    }

}

