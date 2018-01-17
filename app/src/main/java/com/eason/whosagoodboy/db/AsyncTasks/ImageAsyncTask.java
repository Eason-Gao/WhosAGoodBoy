package com.eason.whosagoodboy.db.AsyncTasks;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;

import com.eason.whosagoodboy.activities.SplashActivity;
import com.eason.whosagoodboy.utils.DataSyncType;
import com.eason.whosagoodboy.utils.ImageUtils;
import com.eason.whosagoodboy.whosagoodboy.R;

import java.lang.ref.WeakReference;

/**
 * Created by Eason on 2018-01-16.
 */

public class ImageAsyncTask extends AsyncTask<Bitmap, Void, Void>
{
    private final DataSyncType dataSyncType;
    private WeakReference<SplashActivity> applicationWeakReference;

    public ImageAsyncTask(DataSyncType dataSyncType, SplashActivity context)
    {
	this.dataSyncType = dataSyncType;
	this.applicationWeakReference = new WeakReference<SplashActivity>(context);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

	LayoutInflater layoutInflater = applicationWeakReference.get().getLayoutInflater();

        // display a loading screen
	AlertDialog alertDialog = new AlertDialog.Builder(applicationWeakReference.get(), R.style.MyDialogTheme)
	    .setView(layoutInflater.inflate(R.layout.alertdialog_loading, null))
	    .setTitle("Fetching Breed")
	    .setNegativeButton("cancel", new DialogInterface.OnClickListener()
	    {
		@Override
		public void onClick(DialogInterface dialogInterface, int i)
		{
		    // dismiss
		}
	    }).show();
    }

    @Override
    protected Void doInBackground(Bitmap... Bitmaps)
    {
	for (Bitmap bitmap : Bitmaps)
	{
	    switch (dataSyncType)
	    {
		case INSERT:
		    ImageUtils.savePhotoToSDCard(bitmap, applicationWeakReference);
		    break;
		default:
		    break;
	    }
	}
	return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {


    }


}