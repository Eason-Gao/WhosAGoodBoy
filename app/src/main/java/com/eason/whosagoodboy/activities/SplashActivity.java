package com.eason.whosagoodboy.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eason.whosagoodboy.WhosAGoodBoy;
import com.eason.whosagoodboy.db.AsyncTasks.ImageAsyncTask;
import com.eason.whosagoodboy.db.AsyncTasks.RekognitionAsyncTask;
import com.eason.whosagoodboy.utils.DataSyncType;
import com.eason.whosagoodboy.utils.FileUtils;
import com.eason.whosagoodboy.utils.ImageUtils;
import com.eason.whosagoodboy.utils.awsutils.DetectLabelsUtils;
import com.eason.whosagoodboy.whosagoodboy.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Splash Activity
 */

public class SplashActivity extends AppCompatActivity
{
  @BindView(R.id.take_photo_button)
  Button takePhotoButton;

  @BindView(R.id.import_button)
  Button importButton;

  @BindView(R.id.description_main)
  TextView mainDescription;

  @BindInt(R.integer.MY_CAMERA_REQUEST_CODE)
  int cameraRequestCode;

  @BindInt(R.integer.REQUEST_IMAGE_CAPTURE)
  int requestImageCode;

  @BindInt(R.integer.MY_STORAGE_REQUEST_CODE)
  int storageRequestCode;

  private final static String LOG_TAG = DetectLabelsUtils.class.getSimpleName();

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    ((WhosAGoodBoy) getApplication()).getAppComponent().inject(this);
    ButterKnife.bind(this);

    setUp();
  }

  private void setUp()
  {
    // check permissions
    permissionCheck();
  }

  private void permissionCheck()
  {
    // checking for camera request
    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
    {
      requestPermissions(new String[]{Manifest.permission.CAMERA}, cameraRequestCode);
    }
    // checking for storage reading request
    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
    {
      requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, storageRequestCode);
    }

    // checking for storage writing request
    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
    {
      requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, storageRequestCode);
    }
  }

  @OnClick(R.id.take_photo_button)
  public void onClickTakePhoto()
  {
    // launch default android camera app
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, requestImageCode);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if (requestCode == requestImageCode && resultCode == RESULT_OK) {
      Bundle extras = data.getExtras();
      Bitmap imageBitmap = (Bitmap) extras.get("data");

      //save photo to SD card
      ImageAsyncTask imageAsyncTask = new ImageAsyncTask(DataSyncType.INSERT, this);
      imageAsyncTask.execute(imageBitmap);

      Toast.makeText(this, "Photo saved to sd card!", Toast.LENGTH_SHORT).show();
    }
  }
}
