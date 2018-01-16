package com.eason.whosagoodboy.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.eason.whosagoodboy.activities.SplashActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Util methods for image processing
 */

public class ImageUtils
{

  public static void savePhotoToSDCard(Bitmap bitmap, Context context){
    // for file naming
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
    File myDir = new File(root + "/WhosAGoodBoy");
    myDir.mkdirs();

    String fileName = "Image-" + timeStamp + ".jpg";
    File file = new File(myDir, fileName);
    if (file.exists())
      file.delete();
    try {
      FileOutputStream out = new FileOutputStream(file);
      bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
      out.flush();
      out.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    final Uri contentUri = Uri.fromFile(file);
    scanIntent.setData(contentUri);
    context.sendBroadcast(scanIntent);
  }
}
