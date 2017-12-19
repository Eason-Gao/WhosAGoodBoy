package com.eason.whosagoodboy.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.eason.whosagoodboy.WhosAGoodBoy;
import com.eason.whosagoodboy.whosagoodboy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main Activity
 */

public class MainActivity extends AppCompatActivity
{

  @BindView(R.id.return_image)
  ImageView returnImage;

  static final int REQUEST_IMAGE_CAPTURE = 1;

  private static final int MY_CAMERA_REQUEST_CODE = 100;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((WhosAGoodBoy) getApplication()).getAppComponent().inject(this);
    ButterKnife.bind(this);

    permissionCheck();
  }

  private void permissionCheck()
  {
    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
    {
      requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
    }
  }

  @OnClick(R.id.scan_button)
  public void onClickScan()
  {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      Bundle extras = data.getExtras();
      Bitmap imageBitmap = (Bitmap) extras.get("data");
      returnImage.setImageBitmap(imageBitmap);
    }
  }

}
