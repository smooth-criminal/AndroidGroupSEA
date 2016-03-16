package com.shane.balloonpopper.Objects.GameOverObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.shane.balloonpopper.Objects.GameObjects.GameObject;

/**
 * Created by eddy2 on 16/03/2016.
 */
public class GameOverButton extends GameObject {


    public GameOverButton(int x, int y, int width, int height, Bitmap image) {

        this.x = x;
        this.y = y;
        this.width = image.getWidth();
        this.height = image.getHeight();

        this.image=image;
        bounds = new Rect(x, y, width+x, y+height);
    }

    public Rect getRectangle(){
        //return new Rect(x, y, (x+width), (y+height));
        return bounds;
    }

    public void update(){
        //Balloon will travel upwards
        bounds.set(x, y, width+x, y+height);
        //ADD IN DELETE OFF SCREEN
    }


    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }
}