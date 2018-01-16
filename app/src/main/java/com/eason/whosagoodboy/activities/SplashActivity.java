package com.eason.whosagoodboy.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.eason.whosagoodboy.WhosAGoodBoy;
import com.eason.whosagoodboy.db.AsyncTasks.ImageAsyncTask;
import com.eason.whosagoodboy.utils.DataSyncType;
import com.eason.whosagoodboy.utils.awsutils.DetectLabelsUtils;
import com.eason.whosagoodboy.whosagoodboy.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

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

  @BindInt(R.integer.MY_PHOTOS_REQUEST_CODE)
  int photoRequestCode;

  private final static String LOG_TAG = DetectLabelsUtils.class.getSimpleName();

  private Bitmap imageBitmap;

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

  @OnClick(R.id.import_button)
  public void onClickImportPhoto()
  {
    // launch default gallery app
    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
    photoPickerIntent.setType("image/*");
    startActivityForResult(photoPickerIntent, photoRequestCode);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    // for camera request
    if (requestCode == requestImageCode && resultCode == RESULT_OK) {
      Bundle extras = data.getExtras();
      imageBitmap = (Bitmap) extras.get("data");

      //save photo to SD card
      ImageAsyncTask imageAsyncTask = new ImageAsyncTask(this, DataSyncType.INSERT);
      imageAsyncTask.execute(imageBitmap);
    }

    // for gallery request
    else if (requestCode == photoRequestCode && resultCode == RESULT_OK) {
      try {
        final Uri imageUri = data.getData();
        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
        imageBitmap = BitmapFactory.decodeStream(imageStream);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    detectLabels(imageBitmap);
  }

  private void detectLabels(Bitmap imageBitmap)
  {
    String result = null;

    launchIdentifyBreedActivity(imageBitmap, result);
  }

  private void launchIdentifyBreedActivity(Bitmap imageBitmap, String result)
  {
    try {
    //Write file
    String filename = "bitmap.png";
    FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

    //Cleanup
    stream.close();
    imageBitmap.recycle();

    //Pop intent
    Intent in1 = new Intent(this, IdentifyBreedActivity.class);
    in1.putExtra("image", filename);
    startActivity(in1);
  } catch (Exception e) {
    e.printStackTrace();
  }

//    Intent intent = new Intent(this, IdentifyBreedActivity.class);
//    intent.putExtra("user_photo", imageBitmap);
//    startActivity(intent);
  }
}