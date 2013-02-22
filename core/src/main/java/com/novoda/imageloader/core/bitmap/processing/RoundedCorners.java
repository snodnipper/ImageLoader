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

import android.content.Context;
import android.graphics.*;
import android.util.TypedValue;

public class RoundedCorners implements Processor {

    final int mBorderColor;
    final int mCornerPx;
    final int mBorderPx;

    public static RoundedCorners getInstance(int borderColor, int borderDips, int cornerDips, Context context) {
        int borderSizePx = getPixelsFromDips(borderDips, context);
        int cornerSizePx = getPixelsFromDips(cornerDips, context);
        return new RoundedCorners(borderColor, borderSizePx, cornerSizePx);
    }

    /**
     * @param borderColor set the border color.
     * @param borderPx set the size of the border
     * @param cornerPx set the size of the corner
     */
    public RoundedCorners(int borderColor, int borderPx, int cornerPx) {
        mBorderColor = borderColor;
        mBorderPx = borderPx;
        mCornerPx = cornerPx;
    }

    @Override
    public Bitmap process(Bitmap in) {
        Bitmap output = Bitmap.createBitmap(in.getWidth(), in.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, in.getWidth(), in.getHeight());
        final RectF rectF = new RectF(rect);

        // prepare canvas for transfer
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFFFFF);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, mCornerPx, mCornerPx, paint);

        // draw bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(in, rect, rect, paint);

        // draw border
        paint.setColor(mBorderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) mBorderPx);
        canvas.drawRoundRect(rectF, mCornerPx, mCornerPx, paint);

        return output;
    }

    private static int getPixelsFromDips(int dips, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) dips, context.getResources().getDisplayMetrics());
    }
}
