package com.shane.balloonpopper.FileInputOutput;

import com.shane.balloonpopper.Objects.GameObjects.Balloon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by eddy2 on 11/03/2016.
 */
public class RandomBalloon {

    private Random randomGenerator;
    ArrayList<String> balloonRandom = new ArrayList<String>();

    public void createBalloonArray() {

        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\green_balloon");
        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\lightblue_balloon");
        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\pink_balloon");
        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\purple_balloon");
        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\red_balloon");
        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\turquoise_balloon");
        balloonRandom.add("BalloonPopper\\app\\src\\main\\res\\drawable-nodpi\\yellow_balloon");

    }


    public RandomBalloon()
    {
        balloonRandom = new ArrayList<String>();
        randomGenerator = new Random();
    }

    public String anyBalloon()
    {
        int index = randomGenerator.nextInt(balloonRandom.size());
        String string = balloonRandom.get(index);
        System.out.println("The " + string + " was used.");
        return string;
    }
}
