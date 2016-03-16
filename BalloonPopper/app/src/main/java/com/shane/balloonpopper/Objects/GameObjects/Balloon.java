package com.shane.balloonpopper.Objects.GameObjects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

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
    BitmapDrawable temp;


    public Balloon(int x, int y, int score, Drawable image) {

        this.x = x;//position x
        this.y = y;//position y
       /// this.image=image;
        pop= 0;
        bounds = new Rect(x, y, width+x, y+height);
        speed = 7+(int) (rand.nextDouble()*score/30);//speed increases as score increases
        src = new Rect(0, 0, 0, 0);
        dst = new Rect(0, 0, 0, 0);


        this.x = x;
        this.y = y;
        temp = new BitmapDrawable();
        temp = (BitmapDrawable) image;
        this.image = temp.getBitmap();
        bounds = new Rect(x, y, width+x, y+height);

        speed = 3+(int) (rand.nextDouble()*score/30);//speed increases as score increases
        //cap balloon speed
        if(speed>=40) {speed=40;}

        this.width=image.getIntrinsicWidth()/5;//5 frames per animation
        this.height=image.getIntrinsicHeight()/2;//two rows in spritesheet, 0 = floating(loops), 1 = pop(plays once)
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

    public void changeBounds(){

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
