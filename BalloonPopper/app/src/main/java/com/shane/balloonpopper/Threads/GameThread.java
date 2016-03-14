package com.shane.balloonpopper.Threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.shane.balloonpopper.SurfaceViews.GameSurfaceView;

/**
 * Created by Shane on 14/12/2015.
 */
public class GameThread extends Thread {
    private final int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GameSurfaceView gameSV;
    private boolean running;
    public static Canvas canvas;
    public final int million = 1000000;//I'll be using million a lot, so I just want to make sure I don't have to check everytime if I made a typo.

    public GameThread(SurfaceHolder surfaceHolder, GameSurfaceView gameSV) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameSV = gameSV;
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
                    this.gameSV.update();
                    this.gameSV.draw(canvas);
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
