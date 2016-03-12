package com.shane.balloonpopper.Views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.shane.balloonpopper.FileInputOutput.RandomBalloon;
import com.shane.balloonpopper.OtherEngine.GameThread;
import com.shane.balloonpopper.Objects.GameObjects.GameBackGround;
import com.shane.balloonpopper.Objects.GameObjects.Balloon;
import com.shane.balloonpopper.R;

import java.net.URL;
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

    private imageView view;

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
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!COLLISION DETECTION!!!!!!!!!!!!!!!!!!!!!!!!!//
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
            System.out.println("YEAH");
            return true;
        }
        else{
            System.out.println("NAW MATE");
            return false;
        }
    }

    public void addBalloons(){
        long balloonElapsed = (System.nanoTime()-balloonStartTime)/million;
        if(balloonElapsed>20000/score){



            //PROBLEM BELOW:make sure balloons cannot be too wide as cuts off lots of balloon

             int[] balloonRandom = {

                    R.drawable.yellow_balloon,
                    R.drawable.green_balloon,
                    R.drawable.lightblue_balloon,
                    R.drawable.pink_balloon,
                    R.drawable.turquoise_balloon,
                    R.drawable.red_balloon,
                    R.drawable.purple_balloon,
            };

            /*        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\green_balloon");
            balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\lightblue_balloon");
            balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\pink_balloon");
            balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\purple_balloon");
            balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\red_balloon");
            balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\turquoise_balloon");
            balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\yellow_balloon");*/

            int x = rand.nextInt(WIDTH);

            Drawable d = getResources().getDrawable(balloonRandom[]);





            balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, BitmapFactory
                    .decodeByteArray(balloonRandom, 0, );

            balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, BitmapFactory.decodeResource(
            (getResources(), R.drawable.pink_balloon)));//adds new

            //balloons.add(new Balloon(x, HEIGHT + 150, 80, 90, score, BitmapDrawable.createFromPath()

           // URL imageUrl = this.getClass().getResource("/drawable/green_balloon.png");
           // .setIcon(new javax.swing.ImageIcon(imageUrl));
            // balloon to array of balloons, where x is random within screen range, and y is offscreen.
            balloonStartTime=System.nanoTime();

        }
        //reset timer

    }

    public void update(){
        bg.update();
        for(int i =0; i<balloons.size(); i++){
            balloons.get(i).update();
            }
        addBalloons();
        //balloons.add(new Balloon(200, 200, 50, 50, score, BitmapFactory.decodeResource(getResources(), R.drawable.balloon_blue)));//adds new
        // balloon to array of balloons, where x is random within screen range, and y is offscreen.

        // score.setScore((score.getScore()+1));


    for(int i = 0; i<balloons.size();i++)
    {
        //update missile
        balloons.get(i).update();


        //remove missile if it is way off the screen
        if(balloons.get(i).getY()<-200)
        {
            balloons.remove(i);
            break;
        }
    }
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
}
