package com.shane.balloonpopper.Threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.shane.balloonpopper.SurfaceViews.GameOverSurfaceView;

/**
 * Created by Shane on 10/03/2016.
 */
public class GameOverThread extends Thread {

    private final int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GameOverSurfaceView gameOverSV;
    private boolean running;
    public static Canvas canvas;
    public final int million = 1000000;//I'll be using million a lot, so I just want to make sure I don't have to check everytime if I made a typo.

    public GameOverThread(SurfaceHolder surfaceHolder, GameOverSurfaceView menuSV) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameOverSV = menuSV;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            //Try locking the canvas for editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameOverSV.update();
                    this.gameOverSV.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / million;//Time it took to go through one frame of the game
            waitTime = targetTime - timeMillis;
            try {
                sleep(waitTime);
            } catch (Exception e) {
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / million);
                frameCount = 0;
                totalTime = 0;
                System.out.println("Average FPS: " + averageFPS);
            }
        }
    }

    public void setRunning(Boolean b) {
        running = b;
    }
}
