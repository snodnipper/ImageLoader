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

package com.novoda.imageloader.core.bitmap.loaders;

import android.graphics.Bitmap;
import com.novoda.imageloader.core.bitmap.CacheTag;
import com.novoda.imageloader.core.bitmap.preprocessing.ScaleAtLeast;
import com.novoda.imageloader.core.bitmap.processing.Crop;

import java.io.File;

public class CenterCrop implements FileLoader, CacheTag {

    private final int mHeight;
    private final int mWidth;

    public CenterCrop(int height, int width) {
        mHeight = height;
        mWidth = width;
    }

    @Override
    public Bitmap onLoad(File file) {

        int requiredPixels = mWidth > mHeight ? mWidth : mHeight;

        Bitmap bitmap = new ScaleAtLeast(requiredPixels).load(file);
        bitmap = new Crop(mWidth, mHeight).process(bitmap);

        return bitmap;
    }

    @Override
    public String getCacheTag() {
        return getClass().getSimpleName();
    }
}
