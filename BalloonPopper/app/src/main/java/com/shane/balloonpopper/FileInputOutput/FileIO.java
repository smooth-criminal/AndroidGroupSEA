package com.shane.balloonpopper.FileInputOutput;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Shane on 03/02/2016.
 */
public class FileIO {
    /*
    The purpose of this class is to implement caching in our game, "balloon popper". Since we will be using multiple instances of the same image,
    an asset manager will first check if the image is in the in-program cache; if it is not, then it will fetch it from the drawable folder in the
    res folder. - S 03/02/16
    */
    //Variable declarations
    private ArrayList cache = new ArrayList();//This will store objects that have not been imported yet, in case they must be used later.
    private AssetManager mAssetManager;
    private Context mContext;
    private String mExternalStoragePath;
    //End of variable declarations

    public FileIO(Context context) {
        mContext = context;
        mAssetManager = context.getAssets();
        mExternalStoragePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
    }

    public InputStream readAsset(String assetName) throws IOException {
        return mAssetManager.open(assetName);
    }

    public Bitmap loadBitmap(String fileName, Bitmap.Config format)
            throws IOException {

        Options options = new Options();
        options.inPreferredConfig = format;
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = mAssetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null) {
                String message = mContext.getApplicationContext()
                        //.getResources().getString(R.string.WARNING_TAG)
                        + "Could not load bitmap [" + fileName + "]";
                throw new IOException(message);
            }
        } catch (IOException e) {
            String message = mContext.getApplicationContext().getResources()
                   // .getString(R.string.WARNING_TAG)
                    + "Could not load bitmap [" + fileName + "]";
            throw new IOException(message);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        return bitmap;
    }

}
