package com.shane.balloonpopper.Objects.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Shane on 14/12/2015.
 */
public class Balloon  extends GameObject{
    private int speed;
    private Random rand = new Random();


    public Balloon(int x, int y, int width, int height, int score, Bitmap image) {

        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;

        this.image=image;
        bounds = new Rect(x, y, width+x, y+height);

        speed = 7+(int) (rand.nextDouble()*score/30);//speed increases as score increases
        //cap balloon speed
        if(speed>=40) {speed=40;}
    }

    public void update(){
        //Balloon will travel upwards
        y=y-speed;
        bounds.set(x, y, width+x, y+height);
        //ADD IN DELETE OFF SCREEN
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }


}
