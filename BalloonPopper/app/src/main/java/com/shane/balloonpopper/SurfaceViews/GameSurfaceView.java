package com.shane.balloonpopper.SurfaceViews;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
import android.media.AudioManager;
import android.media.SoundPool;
=======
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.content.res.AssetManager;
import android.widget.TextView;

<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
import com.shane.balloonpopper.Threads.GameThread;
=======
import com.shane.balloonpopper.Activities.MainMenuActivity;
import com.shane.balloonpopper.FileInputOutput.RandomBalloon;
import com.shane.balloonpopper.OtherEngine.GameOver;
import com.shane.balloonpopper.OtherEngine.GameThread;
>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java
import com.shane.balloonpopper.Objects.GameObjects.GameBackGround;
import com.shane.balloonpopper.Objects.GameObjects.Balloon;
import com.shane.balloonpopper.R;
import com.shane.balloonpopper.Objects.GameObjects.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Object;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Shane on 14/12/2015.
 */
<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SoundPool popSoundPool;
    private int popID;
=======

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private int [] balloonRandom;
>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java
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
<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
    public static int balloonSeconds;
=======
    private CountDownTimer ctimer;
  //  private Button pause;
>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java

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
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
    public void surfaceCreated(SurfaceHolder holder) {
=======
    public void surfaceCreated(SurfaceHolder holder){
     //   ImageView mImageView = new ImageView;

>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java
        bg = new GameBackGround(BitmapFactory.decodeResource(getResources(), (R.drawable.backgroundcloud)));
      //  pause = new Button(WIDTH - 256,HEIGHT - 256,256,256, getResources()
      //          .getDrawable(R.drawable.pause));
        rand = new Random();

        balloons = new ArrayList<>();
      //  createBalloonArray();

        balloonStartTime = System.nanoTime();
        score = 7;
        scaleFactorX = getWidth() / (WIDTH * 1.f);
        scaleFactorY = getHeight() / (HEIGHT * 1.f);

        popSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        popID = popSoundPool.load(getContext(), R.raw.pop, 1);

        //Start the game loop here
        thread.setRunning(true);
        thread.start();

        ctimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent i = new Intent().setClass(getContext(), MainMenuActivity.class);
                getContext().startActivity(i);
            }
        }.start();
    }

    @Override
<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
    public boolean onTouchEvent(MotionEvent event) {
        checkForCollision(event);
        return super.onTouchEvent(event);
    }

    public void checkForCollision(MotionEvent event) {

        System.out.println("Pointer X: " + event.getX() + " Pointer Y: " + event.getY());
        for (int i = 0; i < balloons.size(); i++) {
            System.out.println(" Balloon X: " + balloons.get(i).getX() + " Balloon Y: " + balloons.get(i).getY());
            ////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!COLLISION DETECTION!!!!!!!!!!!!!!!!!!!!!!!!!///////////////////////////////////
            if (collision((balloons.get(i).getRectangle()), (int) event.getX() / scaleFactorX, (int) event.getY() / scaleFactorY)) {
=======
    public boolean onTouchEvent(MotionEvent event){

        checkForCollision(event);
       // checkForPause(event);
         return super.onTouchEvent(event);
    }



    public void checkForCollision(MotionEvent event){

        System.out.println("Pointer X: " + event.getX() + " Pointer Y: " + event.getY());
        for(int i =0; i<balloons.size(); i++){
            System.out.println(" Balloon X: "+balloons.get(i).getX()+ " Balloon Y: "+balloons.get(i).getY());
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!COLLISION DETECTION!!!!!!!!!!!!!!!!!!!!!!!!!//
            if(collision((balloons.get(i).getRectangle()), (int)event.getX()/scaleFactorX, (int)event.getY()/scaleFactorY)){
>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java
                System.out.println("Collision detected");
                popSoundPool.play(popID, 1, 1, 1, 0, 1);
                balloons.get(i).setPop(1);
                balloons.get(i).setSpeed(0);
                balloons.get(i).update();
                score++;
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        Intent i = new Intent().setClass(getContext(), MainMenuActivity.class);
                        getContext().startActivity(i);
                    }
                }.start();
                break;
            } else {
                System.out.println("No collision detected.");
            }
        }
    }

<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
    public boolean collision(Rect rect, float xCoord, float yCoord) {
        return rect.contains((int) xCoord, (int) yCoord);
    }

    public void addBalloons() {
        long balloonElapsed = (System.nanoTime() - balloonStartTime) / million;
        if (balloonElapsed > 50000 / score) {
            int x = rand.nextInt(WIDTH);

            //balloons.add(new Balloon(x, HEIGHT + 50, 80, 90, score, BitmapFactory.decodeResource
              //      (getResources(), R.drawable.balloon_blue)));//adds new

            balloons.add(new Balloon(x, HEIGHT + 50, score, BitmapFactory.decodeResource(getResources(), R.drawable
                    .balloon_spritesheet)));//adds new spritesheet
            // balloon to array of balloons, where x is random within screen range, and y is offscreen.
            balloonStartTime = System.nanoTime();
=======
    /*
    public void checkForPause(MotionEvent event) {
        System.out.println("Pointer X: " + event.getX() + " Pointer Y: " + event.getY());
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!COLLISION DETECTION!!!!!!!!!!!!!!!!!!!!!!!!!//
            if(collision((pause.getRectangle()), (int)event.getX()/scaleFactorX, (int)event
                    .getY()/scaleFactorY)){
                System.out.println("Collision detected");
              pauseGame();
            }else{
                System.out.println("No collision detected.");
            }
        }

    public void pauseGame(){
        thread.suspend();
    }
    */

    public boolean collision(Rect rect, float xCoord, float yCoord){
        if(rect.contains((int)xCoord,(int) yCoord)){
            System.out.println("YEAH");
            return true;
        }
        else{
            System.out.println("NAW MATE");
            return false;
        }
    }

    public void addBalloons() throws IOException {
        long balloonElapsed = (System.nanoTime()-balloonStartTime)/million;
        if(balloonElapsed>20000/score){

            int x = rand.nextInt(WIDTH - 80) ;

            //i make it rain like its no ones business

            int random = rand.nextInt(7);
            switch (random) {
                case 0:
                    balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, getResources()
                            .getDrawable(R.drawable.yellow_balloon)));
                    break;
                case 1:
                    balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, getResources()
                            .getDrawable(R.drawable.green_balloon)));
                    break;
                case 2:
                    balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, getResources()
                            .getDrawable(R.drawable.red_balloon)));
                    break;
                case 3:
                    balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, getResources()
                            .getDrawable(R.drawable.lightblue_balloon)));
                    break;
                case 4:
                    balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, getResources()
                            .getDrawable(R.drawable.pink_balloon)));
                    break;
                case 5:
                    balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, getResources()
                            .getDrawable(R.drawable.purple_balloon)));
                    break;
                case 6:
                    balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, getResources()
                            .getDrawable(R.drawable.turquoise_balloon)));
                    break;
            }

            balloonStartTime=System.nanoTime();

>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java
        }
    }

    public void update() {
        bg.update();
        for (int i = 0; i < balloons.size(); i++) {
            balloons.get(i).update();
<<<<<<< HEAD:BalloonPopper/app/src/main/java/com/shane/balloonpopper/SurfaceViews/GameSurfaceView.java
        }
        addBalloons();
=======

            }
        try {
            addBalloons();
        } catch(IOException e) {
            e.printStackTrace();
        }




    for(int i = 0; i<balloons.size();i++)
    {
        //update balloon
        balloons.get(i).update();


        //remove any balloons lower than Y value to remove them as they are now off screen
        if(balloons.get(i).getY()<-200)
        {
            balloons.remove(i);
        }
>>>>>>> refs/remotes/origin/Edward-Branch:BalloonPopper/app/src/main/java/com/shane/balloonpopper/Views/GameSurfaceView.java
    }
}


    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            final int savedState = canvas.save();//saved state of canvas size
            canvas.scale(scaleFactorX, scaleFactorY);//scales canvas to phone width
            //DRAW HERE IN ORDER FROM BACK TO FRONT
            bg.draw(canvas);//Background first
            for (int i = 0; i < balloons.size(); i++) {
                balloons.get(i).draw(canvas);
            }
            //Draw pause button here
            //pause.draw(canvas);
            canvas.restoreToCount(savedState);//returns to unscaled dimensions
        }
    }





}




