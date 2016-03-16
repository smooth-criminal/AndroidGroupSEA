package com.shane.balloonpopper.SurfaceViews;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shane.balloonpopper.Activities.GameScreenActivity;
import com.shane.balloonpopper.Objects.MenuObjects.MenuBackGround;
import com.shane.balloonpopper.Objects.MenuObjects.MenuButton;
import com.shane.balloonpopper.R;
import com.shane.balloonpopper.Threads.MenuThread;


/**
 * Created by Shane on 09/03/2016.
 */
public class MainMenuSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    private MenuButton butt;
    private MenuBackGround bg;
    private MenuThread thread;


    float scaleFactorX;
    float scaleFactorY;

    public MainMenuSurfaceView(Context context) {

        super(context);
        //add the callback to the surfaceHolder to intercept events
        getHolder().addCallback(this);
        thread = new MenuThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        System.out.println("HELLO");
        bg = new MenuBackGround(BitmapFactory.decodeResource(getResources(), (R.drawable.mainmenubackground)));
        butt = new MenuButton(50, 1000, 500, 500, BitmapFactory.decodeResource(getResources(), (R.drawable.startgamebutton)));
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

    @Override
    public boolean onTouchEvent(MotionEvent event){

        checkForCollision(event);
        return super.onTouchEvent(event);
    }

    public void checkForCollision(MotionEvent event){

        System.out.println("Pointer X: " + event.getX() + " Pointer Y: " + event.getY());
        System.out.println("Button X: "+butt.getX()+" Button Y: "+ butt.getY());
            if(collision((butt.getRectangle()), (int)event.getX(), (int)event.getY())){
                System.out.println("Collision detected");
                Intent i = new Intent().setClass(getContext(), GameScreenActivity.class);//THESE TWO LINES ARE IMPORTANT
                getContext().startActivity(i);
            }else{
                System.out.println("No collision detected.");
            }
        }


    public boolean collision(Rect rect, float xCoord, float yCoord){
        return rect.contains((int) xCoord, (int) yCoord);
    }
    public void update(){
        bg.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas!=null){
            final int savedState = canvas.save();//saved state of canvas size
            //DRAW HERE IN ORDER FROM BACK TO FRONT
            bg.draw(canvas);
            butt.draw(canvas);

            canvas.scale(scaleFactorX, scaleFactorY);//scales canvas to phone width

            //Background first
            canvas.restoreToCount(savedState);//returns to unscaled dimensions
        }
    }
}
