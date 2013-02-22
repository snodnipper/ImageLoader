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
 */

package com.novoda.imageloader.core.bitmap.processing;

import android.graphics.Bitmap;

public class Inverter implements Processor {

    private static final int ARGB_MASK = 0x00FFFFFF;

    @Override
    public Bitmap process(Bitmap in) {
        int width = in.getWidth();
        int height = in.getHeight();
        int totalPx = width * height;

        int[] pixels = new int[totalPx];
        in.getPixels(pixels, 0, width, 0, 0, width, height);

        // Invert pixels
        for (int i = 0; i < totalPx; i++) {
            pixels[i] ^= ARGB_MASK;
        }

        Bitmap output = Bitmap.createBitmap(in.getWidth(), in.getHeight(),
                Bitmap.Config.ARGB_8888);
        output.setPixels(pixels, 0, width, 0, 0, width, height);

        return output;
    }
}
