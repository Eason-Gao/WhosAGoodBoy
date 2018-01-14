package com.eason.whosagoodboy.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * File handling Util
 */

public class FileUtils
{
  public static File convertBitMapToFile(Context context, Bitmap bitmap, String name)
  {
    File imageFile;

    File filesDir = context.getFilesDir();
    imageFile = new File(filesDir, name + ".png");

    OutputStream os;
    try {
      os = new FileOutputStream(imageFile);
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
      os.flush();
      os.close();
    } catch (Exception e) {
      Log.e(context.getClass().getSimpleName(), "Error writing bitmap", e);
    }
    return imageFile;
  }
}
