package com.eason.whosagoodboy.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.eason.whosagoodboy.WhosAGoodBoy;
import com.eason.whosagoodboy.db.AsyncTasks.DogAsyncTask;
import com.eason.whosagoodboy.db.AsyncTasks.RekognitionAsyncTask;
import com.eason.whosagoodboy.db.Constants;
import com.eason.whosagoodboy.utils.DataSyncType;
import com.eason.whosagoodboy.utils.FileUtils;
import com.eason.whosagoodboy.utils.TransferUtils;
import com.eason.whosagoodboy.utils.awsutils.DetectLabelsUtils;
import com.eason.whosagoodboy.utils.awsutils.RekognitionUtils;
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

  private AmazonS3Client s3;

  private TransferUtility transferUtility;

  private File imageFile;

  private RekognitionAsyncTask rekognitionAsyncTask;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((WhosAGoodBoy) getApplication()).getAppComponent().inject(this);
    ButterKnife.bind(this);

    permissionCheck();

    transferUtility = TransferUtils.getTransferUtility(this);

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

    TransferObserver observer = transferUtility.upload(Constants.BUCKET_NAME, "test file",
	imageFile);
        /*
         * Note that usually we set the transfer listener after initializing the
         * transfer. However it isn't required in this sample app. The flow is
         * click upload button -> start an activity for image selection
         * startActivityForResult -> onActivityResult -> beginUpload -> onResume
         * -> set listeners to in progress transfers.
         */
    // observer.setTransferListener(new UploadListener());
  }
}
