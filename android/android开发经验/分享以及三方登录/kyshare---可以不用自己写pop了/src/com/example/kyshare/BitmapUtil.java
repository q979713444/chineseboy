package com.example.kyshare;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtil {
	public static Bitmap resizeBitmap(Bitmap bitmap, int h) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int newHeight = h;
            float scaleSize = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleSize, scaleSize);
            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            return resizedBitmap;
        } else {
            return null;
        }
    }
}
