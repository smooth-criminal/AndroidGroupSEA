package com.shane.balloonpopper.Objects.GameObjects;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Shane on 14/12/2015.
 */
public abstract class GameObject {
    protected int x, y, dy, dx, width, height;
    protected Bitmap image;
    protected Rect bounds;
    //Getters and setters - will optimise at a later date.
    public Rect getRectangle(){
        //return new Rect(x, y, (x+width), (y+height));
        return bounds;
    }//returns rectangle of object - required for collision.

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
