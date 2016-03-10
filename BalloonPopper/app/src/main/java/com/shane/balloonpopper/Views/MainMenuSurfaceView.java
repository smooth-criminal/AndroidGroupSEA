package com.shane.balloonpopper.Views;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shane.balloonpopper.Activities.GameScreenActivity;
import com.shane.balloonpopper.Objects.GameObjects.GameBackGround;
import com.shane.balloonpopper.Objects.MenuObjects.MenuBackGround;
import com.shane.balloonpopper.OtherEngine.MenuThread;
import com.shane.balloonpopper.R;


/**
 * Created by Shane on 09/03/2016.
 */
public class MainMenuSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    private MenuBackGround bg;
    private MenuThread thread;

    float scaleFactorX;
    float scaleFactorY;

    public MainMenuSurfaceView(Context context) {

        super(context);
        //add the callback to the surfaceHolder to intercept events
        thread = new MenuThread(getHolder(), this);

        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("HELLO");
        bg = new MenuBackGround(BitmapFactory.decodeResource(getResources(), (R.drawable.mainmenubackground)));

        scaleFactorX = getWidth()/(WIDTH*1.f);
        scaleFactorY = getHeight()/(HEIGHT*1.f);

        thread.setRunning(true);
        thread.start();

        // Intent i = new Intent().setClass(getContext(), GameScreenActivity.class);//THESE TWO LINES ARE IMPORTANT
        // getContext().startActivity(i);//THESE TWO LINES ARE IMPORTANT
        /*
        They allow us to swap between different Activities from within the SurfaceViews
         */
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e){e.printStackTrace();}
            retry = false;
        }
    }

    public void update(){
        bg.update();
    }

    @Override
    public void draw(Canvas canvas){
        if(canvas!=null){
            final int savedState = canvas.save();//saved state of canvas size
            canvas.scale(scaleFactorX, scaleFactorY);//scales canvas to phone width
            //DRAW HERE IN ORDER FROM BACK TO FRONT
            bg.draw(canvas);//Background first
            canvas.restoreToCount(savedState);//returns to unscaled dimensions
        }
    }
}
