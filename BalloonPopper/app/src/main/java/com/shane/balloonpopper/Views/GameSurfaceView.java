package com.shane.balloonpopper.Views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shane.balloonpopper.OtherEngine.GameThread;
import com.shane.balloonpopper.Objects.GameObjects.GameBackGround;
import com.shane.balloonpopper.Objects.GameObjects.Balloon;
import com.shane.balloonpopper.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Shane on 14/12/2015.
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private GameThread thread;
    private GameBackGround bg;
    private Balloon balloon;
    private ArrayList<Balloon> balloons;
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    private long balloonStartTime;
    private long balloonElapsed;
    public int million = 1000000;
    private Random rand;
    private int score;
    public static int balloonSeconds;

    float scaleFactorX;
    float scaleFactorY;

    public GameSurfaceView(Context context) {
        super(context);
        //add the callback to the surfaceHolder to intercept events
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{thread.setRunning(false);
            thread.join();
            } catch (InterruptedException e){e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        bg = new GameBackGround(BitmapFactory.decodeResource(getResources(), (R.drawable.backgroundcloud)));
        rand = new Random();

        balloons = new ArrayList<>();
        balloonStartTime = System.nanoTime();
        score = 7;
        scaleFactorX = getWidth()/(WIDTH*1.f);
        scaleFactorY = getHeight()/(HEIGHT*1.f);

        //Start the game loop here
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

           checkForCollision(event);
         return super.onTouchEvent(event);
    }

    public void checkForCollision(MotionEvent event){

        System.out.println("Pointer X: "+event.getX()+" Pointer Y: "+event.getY());
        for(int i =0; i<balloons.size(); i++){
            System.out.println(" Balloon X: "+balloons.get(i).getX()+ " Balloon Y: "+balloons.get(i).getY());
            ////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!COLLISION DETECTION!!!!!!!!!!!!!!!!!!!!!!!!!///////////////////////////////////
            if(collision((balloons.get(i).getRectangle()), (int)event.getX()/scaleFactorX, (int)event.getY()/scaleFactorY)){
                System.out.println("Collision detected");
                balloons.remove(i);
                score++;
                break;
            }else{
                System.out.println("No collision detected.");
            }
        }
    }

    public boolean collision(Rect rect, float xCoord, float yCoord){
        if(rect.contains((int)xCoord,(int) yCoord)){
            return true;
        }
        else{
            return false;
        }
    }

    public void addBalloons(){
        long balloonElapsed = (System.nanoTime()-balloonStartTime)/million;
        if(balloonElapsed>20000/score){
            int x = rand.nextInt(WIDTH);
            balloons.add(new Balloon(x, HEIGHT+50, 80, 90, score, BitmapFactory.decodeResource(getResources(), R.drawable
                    .balloon_blue)));//adds new
            // balloon to array of balloons, where x is random within screen range, and y is offscreen.
            balloonStartTime=System.nanoTime();
        }
    }

    public void update(){
        bg.update();
        for(int i =0; i<balloons.size(); i++){
            balloons.get(i).update();
            }
        addBalloons();
    }


    @Override
    public void draw(Canvas canvas){
        if(canvas!=null){
            final int savedState = canvas.save();//saved state of canvas size
            canvas.scale(scaleFactorX, scaleFactorY);//scales canvas to phone width
            //DRAW HERE IN ORDER FROM BACK TO FRONT
            bg.draw(canvas);//Background first
            for(int i =0; i<balloons.size(); i++){
                balloons.get(i).draw(canvas);
            }
            canvas.restoreToCount(savedState);//returns to unscaled dimensions
        }
    }
<<<<<<< HEAD
}
=======



}
>>>>>>> refs/remotes/origin/ShaneBranch
