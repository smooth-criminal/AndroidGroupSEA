package com.shane.balloonpopper.SurfaceViews;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shane.balloonpopper.Activities.MainMenuActivity;
import com.shane.balloonpopper.Objects.GameOverObjects.GameOverBackGround;
import com.shane.balloonpopper.Objects.GameOverObjects.GameOverButton;
import com.shane.balloonpopper.R;
import com.shane.balloonpopper.Threads.GameOverThread;

/**
 * Created by eddy2 on 16/03/2016.
 */
public class GameOverSurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    private GameOverButton buttan;
    private GameOverBackGround bg;
    private GameOverThread thread;


    float scaleFactorX;
    float scaleFactorY;

    public GameOverSurfaceView(Context context) {
        super(context);
        //add the callback to the surfaceHolder to intercept events
        getHolder().addCallback(this);
        thread = new GameOverThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        System.out.println("Game Over!");
        bg = new GameOverBackGround(BitmapFactory.decodeResource(getResources(), (R.drawable
                .gameoverbackground)));
        buttan = new GameOverButton(250, 1000, 900, 300, BitmapFactory.decodeResource(getResources()
                , (R.drawable.backtomenu)));
        scaleFactorX = getWidth()/(WIDTH*1.f);
        scaleFactorY = getHeight()/(HEIGHT*1.f);

        thread.setRunning(true);
        thread.start();
        
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
        buttan.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        checkForCollision(event);
        return super.onTouchEvent(event);
    }

    public void checkForCollision(MotionEvent event){

        System.out.println("Pointer X: " + event.getX() + " Pointer Y: " + event.getY());
        System.out.println("Button X: " + buttan.getX() + " Button Y: " + buttan.getY());
        if(collision((buttan.getRectangle()), (int)event.getX(), (int)event.getY())){
            System.out.println("Collision detected.");
            Intent i = new Intent().setClass(getContext(), MainMenuActivity.class);//THESE TWO LINES ARE
            // IMPORTANT
            getContext().startActivity(i);
        }else{
            System.out.println("No collision detected.");
        }
    }
    public boolean collision(Rect rect, float xCoord, float yCoord){
        return rect.contains((int) xCoord, (int) yCoord);
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas!=null){
            final int savedState = canvas.save();//saved state of canvas size
            //DRAW HERE IN ORDER FROM BACK TO FRONT
            bg.draw(canvas);
            buttan.draw(canvas);

            canvas.scale(scaleFactorX, scaleFactorY);//scales canvas to phone width

            //Background first
            canvas.restoreToCount(savedState);//returns to unscaled dimensions
        }
    }
}