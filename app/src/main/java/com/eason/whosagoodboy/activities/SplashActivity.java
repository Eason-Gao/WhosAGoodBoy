package com.eason.whosagoodboy.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.eason.whosagoodboy.WhosAGoodBoy;
import com.eason.whosagoodboy.whosagoodboy.R;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Splash Activity
 */

public class SplashActivity extends AppCompatActivity
{
  @BindView(R.id.scan_button)
  Button scanButton;

  @BindView(R.id.import_button)
  Button importButton;

  @BindView(R.id.description_main)
  ImageView mainDescription;

  @BindInt(R.integer.MY_CAMERA_REQUEST_CODE)
  int cameraRequestCode;

  @BindInt(R.integer.REQUEST_IMAGE_CAPTURE)
  int requestImageCode;

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
}
