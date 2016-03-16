package com.shane.balloonpopper.Objects.MenuObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.shane.balloonpopper.Objects.GameObjects.GameObject;

import java.util.Random;

/**
 * Created by Shane on 10/03/2016.
 */
public class MenuButton extends GameObject {


    public MenuButton(int x, int y, Bitmap image) {

        this.x = x;
        this.y = y;

        this.image=image;
        this.width = image.getWidth();
        this.height = image.getHeight();
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
