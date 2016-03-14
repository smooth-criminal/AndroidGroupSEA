package com.shane.balloonpopper.Objects.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.shane.balloonpopper.SurfaceViews.GameSurfaceView;

import java.util.Random;

/**
 * Created by Shane on 14/12/2015.
 */
public class Balloon  extends GameObject{
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int speed;
    private Random rand = new Random();
    private int currentFrame = 0;
    private int pop;
    private Rect src, dst;


    public Balloon(int x, int y, int score, Bitmap image) {

        this.x = x;//position x
        this.y = y;//position y
        this.image=image;
        this.width=image.getWidth()/5;//5 frames per animation
        this.height=image.getHeight()/2;//two rows in spritesheet, 0 = floating(loops), 1 = pop(plays once)
        pop= 0;
        bounds = new Rect(x, y, width+x, y+height);
        speed = 7+(int) (rand.nextDouble()*score/30);//speed increases as score increases
        src = new Rect(0, 0, 0, 0);
        dst = new Rect(0, 0, 0, 0);

        //cap balloon speed
        if(speed>=40) {speed=40;}
    }

    public void update(){
        //Balloon will travel upwards
        y=y-speed;
        bounds.set(x, y, width + x, y + height);
        if(pop==1){
            if(currentFrame<=4){
                currentFrame =  ++currentFrame;
            }
        }else{
            currentFrame = ++currentFrame%5;
        }
    }
    public void draw(Canvas canvas){
        int srcY = pop * height;
        int srcX = currentFrame * width;
        src.set(srcX, srcY, srcX + width, srcY + height);
        dst.set(x, y, x + width, y + height);

        canvas.drawBitmap(image, src, dst, null);
    }

    public void setPop(int x){
        //1 = pop animation
        //0 = normal animation
        pop = x;
    }


}
