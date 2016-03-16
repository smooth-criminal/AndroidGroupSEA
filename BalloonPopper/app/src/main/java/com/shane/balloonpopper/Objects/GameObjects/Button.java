package com.shane.balloonpopper.Objects.GameObjects;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by eddy2 on 15/03/2016.
 */
/*
Shane
 */
public class Button extends GameObject{

    BitmapDrawable temp;

    public Button(int x, int y, int width, int height, Drawable image) {

        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        bounds = new Rect(x, y, width+x, y+height);
        temp = new BitmapDrawable();
        temp = (BitmapDrawable) image;
        this.image = temp.getBitmap();
    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }
}
