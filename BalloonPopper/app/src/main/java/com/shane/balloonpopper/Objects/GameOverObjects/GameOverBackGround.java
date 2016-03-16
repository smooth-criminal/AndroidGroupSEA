package com.shane.balloonpopper.Objects.GameOverObjects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by eddy2 on 16/03/2016.
 */
public class GameOverBackGround {private Bitmap image;
    private int x;
    private int y;

    public GameOverBackGround(Bitmap res)
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
