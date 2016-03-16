package com.shane.balloonpopper.Activities;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.shane.balloonpopper.R;
import com.shane.balloonpopper.SurfaceViews.GameSurfaceView;

/**
 * Created by Shane on 09/03/2016.
 */
public class GameScreenActivity extends Activity{
    private SoundPool soundPool;
    private int soundID;
/*
Shane
 */
    /*
    IMPORTANT NOTE FOR PHIL:
    FOR THE FIRST PART OF DEVELOPMENT, MYSELF AND EDWARD FOLLOWED A TUTORIAL ON YOUTUBE AS TO HOW
     TO CREATE A 2D ANDROID GAME(LINK BELOW:
    https://www.youtube.com/watch?v=rJcm5Oyi3YA&ab_channel=paymonwang-lotfi

    THIS HELPED US CREATE SOMETHING WE COULD WORK OFF OF, AS WE FOUND GAGE TOO CHALLENGING TO
    WORK WITH, ESPECIALLY IN THE EARLY DAYS.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Set to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //SetupSound
        setupSound();

        setContentView(new GameSurfaceView(this));

    }

    public void setupSound(){
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        soundID = soundPool.load(this, R.raw.pop, 1);
    }
}
