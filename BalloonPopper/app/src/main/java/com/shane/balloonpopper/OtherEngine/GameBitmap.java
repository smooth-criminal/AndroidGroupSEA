package com.shane.balloonpopper.OtherEngine;

import android.graphics.Bitmap;

/**
 * Created by Shane on 20/02/2016.
 */
public class GameBitmap {

    Bitmap bitmap;
    String name;

    public GameBitmap(Bitmap bitmap, String name){
        this.bitmap = bitmap;
        this.name = name;
    }

    public void setGameBitmap(Bitmap newBitmap, String newName){
        bitmap = newBitmap;
        name = newName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
