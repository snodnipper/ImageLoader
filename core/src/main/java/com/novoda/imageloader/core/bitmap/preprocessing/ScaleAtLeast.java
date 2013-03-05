/*
 * Copyright (C) 2012 Oliver Snowden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.novoda.imageloader.core.bitmap.preprocessing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

public class ScaleAtLeast implements Preprocessor {

    final String TAG = getClass().getSimpleName();

    final int mRequiredPixels;

    public ScaleAtLeast(int requiredPixels) {
        mRequiredPixels = requiredPixels;
        if (requiredPixels <= 0) {
            throw new IllegalStateException("Fuck off! I won't accept zero scale");
        }

    }

    @Override
    public Bitmap load(File file) {

        Log.e(TAG, "YYO Max pixels: " + mRequiredPixels);
        Log.d(TAG, "Max pixels: " + mRequiredPixels);

        // Decode image size, see:
        // http://stackoverflow.com/questions/477572/android-strange-out-of-memory-issue/823966#823966
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.toString(),opts);

        //Find the correct scale value. It should be the power of 2 for speed!
        int tmpWidth=opts.outWidth, tmpHeight=opts.outHeight;
        Log.d(TAG,"Input file dimensions = w:" + tmpWidth + " h:" + tmpHeight);
        int scale=1;
        while(true){
            if(tmpWidth / 2 < mRequiredPixels || tmpHeight / 2 < mRequiredPixels)
                break;
            tmpWidth /= 2;
            tmpHeight /= 2;
            scale *= 2;
            //############### REMOVE ####################
            //Log.d("Scale:","Scale is: " + scale);
        }

        //Decode with inSampleSize
        BitmapFactory.Options opts2 = new BitmapFactory.Options();
        opts2.inSampleSize=scale;

        Log.d(TAG,"Coarse resize = w:" + tmpWidth + " h:" + tmpHeight);
        Bitmap bm = BitmapFactory.decodeFile(file.toString(),opts2);

        // Image is a power of two larger than requested.  Final resize is requested.
        final int requiredWidth;
        final int requiredHeight;
        if (tmpWidth>tmpHeight) {
            // landscape
            requiredHeight = mRequiredPixels;
            float ratio = (float) requiredHeight / tmpHeight;
            requiredWidth = (int) (tmpWidth * ratio);
        } else {
            // portrait
            requiredWidth = mRequiredPixels;
            // The cast has higher precedence than the division, so happens before the division.
            // http://stackoverflow.com/questions/787700/java-dividing-2-ints-makes-an-int
            float ratio = (float) requiredWidth / tmpWidth;
            requiredHeight = (int) (tmpHeight * ratio);
        }

        bm = Bitmap.createScaledBitmap(bm, requiredWidth, requiredHeight, true);
        Log.d(TAG,"Returned size = w:" + requiredWidth + " h:" + requiredHeight);
        return bm;
    }
}
