package com.shane.balloonpopper.Objects.GameObjects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

<<<<<<< HEAD
import com.shane.balloonpopper.SurfaceViews.GameSurfaceView;

=======
import com.shane.balloonpopper.R;

import java.util.ArrayList;
>>>>>>> refs/remotes/origin/Edward-Branch
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
<<<<<<< HEAD
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

=======
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
>>>>>>> refs/remotes/origin/Edward-Branch
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
