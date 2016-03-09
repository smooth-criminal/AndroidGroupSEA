package com.shane.balloonpopper.FileInputOutput;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.shane.balloonpopper.OtherEngine.GameBitmap;


/**
 * Created by Shane on 20/02/2016.
 */
public class AssetManager {
//HOW TO USE: import all necessary bitmaps using importBitmaps(), then lookup a specific bitmap when you need it.
    //I have no idea if this helps at all.


    ArrayList<GameBitmap> importedGameBitmapArrayList = new ArrayList<GameBitmap>();
    Bitmap bMap;//This is used as a temporary bitmap to hold bitmaps whilst they are looked up and returned
    GameBitmap tempGameBitmap;

    public Bitmap importBitmap(String filePath) {
        //THIS METHOD IMPORTS A BITMAP AND RETURNS IT
        return bMap = BitmapFactory.decodeFile(filePath);
    }

    public void importBitmaps(String[] filePaths, String[] names) {
        for (int i = 0; i < filePaths.length; i++) {
            tempGameBitmap.setGameBitmap(importBitmap(filePaths[i]), names[i]);
            importedGameBitmapArrayList.add(tempGameBitmap);
        }
        System.out.println("Import complete");
        //System.out.println("The following images are in the GameBitmaps");
    }

    public Bitmap getBitmap(String lookupName) {
        bMap = null;
        //assuming ArrayList is not empty
        for (int i = 0; i < importedGameBitmapArrayList.size(); i++) {
            if (lookupName == importedGameBitmapArrayList.get(i).getName()) {
                bMap =  importedGameBitmapArrayList.get(i).getBitmap();
            }
        }
        return bMap;
    }


}
