package com.eason.whosagoodboy.utils.awsutils;

import android.content.Context;
import android.util.Log;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Utils for detecting labels
 */

public class DetectLabelsUtils
{
  private final static String LOG_TAG = DetectLabelsUtils.class.getSimpleName();

  public static void detectLabels(Context context, File file, int maxLabels, float minConfidence) {
    ByteBuffer imageBytes = getImageBytes(file);
    if (imageBytes == null) return;

    DetectLabelsRequest request = new DetectLabelsRequest()
	.withImage(new Image().withBytes(imageBytes))
	.withMaxLabels(maxLabels)
	.withMinConfidence(minConfidence);

    try {
      AmazonRekognition amazonRekognition = RekognitionUtils.getRekognitionClient(context);
      DetectLabelsResult result = amazonRekognition.detectLabels(request);
      List<Label> labelList = result.getLabels();
      for (Label label : labelList) {
	Log.i(LOG_TAG, String.format("Detected labels for &s", file.getName()));
      }
    } catch (Exception ex) {
      Log.e(LOG_TAG, String.format("detectLabels() - fileName (breaking here): %s, Exception: %s", file.getName(), ex.getMessage()));
      ex.printStackTrace();
    }
  }

  private static ByteBuffer getImageBytes(File file) {
    InputStream inputStream;
    ByteBuffer byteBuffer;
    try {
      inputStream = new FileInputStream(file);
    } catch (FileNotFoundException ex) {
      Log.e(LOG_TAG, String.format("getImageBytes() - InputStream exception: %s", ex.getMessage()));
      return null;
    }

    try {
      byteBuffer = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
    } catch (IOException ex) {
      Log.e(LOG_TAG, String.format("getImageBytes() - ByteBuffer exception: %s", ex.getMessage()));
      return null;
    }

    return byteBuffer;
  }
}
