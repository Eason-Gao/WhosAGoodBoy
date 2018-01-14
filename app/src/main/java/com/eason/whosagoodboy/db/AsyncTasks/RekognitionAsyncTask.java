package com.eason.whosagoodboy.db.AsyncTasks;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.AsyncTask;

import com.eason.whosagoodboy.Entity.Dog;
import com.eason.whosagoodboy.utils.DataSyncType;
import com.eason.whosagoodboy.utils.awsutils.DetectLabelsUtils;

import java.io.File;

/**
 * Created by Eason on 2018-01-13.
 */

public class RekognitionAsyncTask extends AsyncTask<File, Void, Void>
{
  private final DataSyncType dataSyncType;
  private final Context context;

  public RekognitionAsyncTask(Context context, DataSyncType dataSyncType)
  {
    this.dataSyncType = dataSyncType;
    this.context = context;
  }

  @Override
  protected Void doInBackground(File... Files)
  {
    for (File file : Files)
    {
      switch (dataSyncType)
      {
	case INSERT:
	  DetectLabelsUtils.detectLabels(context, file, 10, 77F);
	  break;
	default:
	  break;
      }
    }
    return null;
  }
}
