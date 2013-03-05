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
 *
 * Adapted from: http://stackoverflow.com/questions/5226922/crop-to-fit-image-in-android
 * Credit to Dezull: http://stackoverflow.com/users/573719/dezull
 */

package com.novoda.imageloader.core.bitmap.processing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Crop implements Processor {

    private int mOutputX;
    private int mOutputY;

    public Crop(int outputX, int outputY) {
        mOutputX = outputX;
        mOutputY = outputY;
    }

    @Override
    public Bitmap process(Bitmap in) {
        Bitmap croppedImage = Bitmap.createBitmap(mOutputX, mOutputY, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(croppedImage);

        Rect srcRect = new Rect(0,0, in.getWidth(), in.getHeight());
        Rect dstRect = new Rect(0, 0, mOutputX, mOutputY);

        int dx = (srcRect.width() - dstRect.width()) / 2;
        int dy = (srcRect.height() - dstRect.height()) / 2;

        // If the srcRect is too big, use the center part of it.
        srcRect.inset(Math.max(0, dx), Math.max(0, dy));

        // If the dstRect is too big, use the center part of it.
        dstRect.inset(Math.max(0, -dx), Math.max(0, -dy));

        // Draw the cropped bitmap in the center
        canvas.drawBitmap(in, srcRect, dstRect, null);
        return croppedImage;
    }

    @Override
    public String getCacheTag() {
        return getClass().getSimpleName();
    }
}
