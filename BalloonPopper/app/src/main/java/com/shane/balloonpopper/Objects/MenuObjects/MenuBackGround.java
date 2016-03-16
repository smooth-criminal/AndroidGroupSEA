package com.shane.balloonpopper.Objects.MenuObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Shane on 10/03/2016.
 */
public class MenuBackGround {
    private Bitmap image;
    private int x;
    private int y;

    public MenuBackGround(Bitmap res)
    {
        image = res;
        x = 0;
        y = 0;
    }
    public void update()
    {    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y,null);
    }
}
