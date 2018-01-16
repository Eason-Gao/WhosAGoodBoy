package com.eason.whosagoodboy.db.AsyncTasks;

import android.app.VoiceInteractor;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.eason.whosagoodboy.Entity.Dog;
import com.eason.whosagoodboy.utils.DataSyncType;
import com.eason.whosagoodboy.utils.ImageUtils;
import com.eason.whosagoodboy.utils.awsutils.DetectLabelsUtils;

import java.io.File;

/**
 * Created by Eason on 2018-01-13.
 */

public class ImageAsyncTask extends AsyncTask<Bitmap, Void, Void>
{
  private final DataSyncType dataSyncType;
  private final Context context;

  public ImageAsyncTask(DataSyncType dataSyncType, Context context)
  {
    this.dataSyncType = dataSyncType;
    this.context = context;
  }

  @Override
  protected Void doInBackground(Bitmap... Bitmaps)
  {
    for (Bitmap bitmap : Bitmaps)
    {
      switch (dataSyncType)
      {
	case INSERT:
	  ImageUtils.savePhotoToSDCard(bitmap, context);
	  break;
	default:
	  break;
      }
    }
    return null;
  }
}
