package com.eason.whosagoodboy.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.eason.whosagoodboy.WhosAGoodBoy;
import com.eason.whosagoodboy.db.AsyncTasks.RekognitionAsyncTask;
import com.eason.whosagoodboy.db.Constants;
import com.eason.whosagoodboy.utils.DataSyncType;
import com.eason.whosagoodboy.utils.FileUtils;
import com.eason.whosagoodboy.utils.TransferUtils;
import com.eason.whosagoodboy.whosagoodboy.R;

import java.io.File;
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

  private TransferUtility transferUtility;

  private File imageFile;

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
    // checking for camera request
    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
    {
      requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
    }
  }

  @OnClick(R.id.scan_button)
  public void onClickScan()
  {
    // launch default android camera app
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
  }

  @OnClick(R.id.upload_button)
  public void onClickUpload()
  {
    beginUpload();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      Bundle extras = data.getExtras();
      Bitmap imageBitmap = (Bitmap) extras.get("data");

      imageFile = FileUtils.convertBitMapToFile(this, imageBitmap, "test image");

      RekognitionAsyncTask newRekognitionAsyncTask = new RekognitionAsyncTask(this, DataSyncType.INSERT);
      newRekognitionAsyncTask.execute(imageFile);

      returnImage.setImageBitmap(imageBitmap);
    }
  }

  private void beginUpload() {

    transferUtility = TransferUtils.getTransferUtility(this);

    TransferObserver observer = transferUtility.upload(Constants.BUCKET_NAME, "test file",
	imageFile);
  }
}
