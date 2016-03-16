package com.shane.balloonpopper.Objects.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.shane.balloonpopper.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Shane on 14/12/2015.
 */
public class Balloon  extends GameObject{
    private int speed;
    private Random rand = new Random();
    BitmapDrawable temp;
    private Random randomGenerator;
    ArrayList<String> balloonRandom = new ArrayList<String>();

    public Balloon(int x, int y, int width, int height, int score, Drawable image) {

        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        temp = new BitmapDrawable();
        temp = (BitmapDrawable) image;
        this.image = temp.getBitmap();
        bounds = new Rect(x, y, width+x, y+height);

        speed = 3+(int) (rand.nextDouble()*score/30);//speed increases as score increases
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
