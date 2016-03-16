package com.shane.balloonpopper.Objects.GameObjects;
//SHOULD EXTEND OBJECT

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.shane.balloonpopper.SurfaceViews.GameSurfaceView;

public class GameBackGround {

    private Bitmap image;
    private int x, y, dx, dy;
    public static final int MOVESPEED = -1;

    public GameBackGround(Bitmap res)
    {
        image = res;
        dx = MOVESPEED;
        dy = MOVESPEED;
    }
    public void update()
    {
        x+=dx;
        if(x<-GameSurfaceView.WIDTH){
            x=0;
        }

        if(y> GameSurfaceView.HEIGHT){
            y= GameSurfaceView.HEIGHT;
        }
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y,null);
        if(x<0)
        {
            canvas.drawBitmap(image, x+ GameSurfaceView.WIDTH, y, null);
        }
    }
}
