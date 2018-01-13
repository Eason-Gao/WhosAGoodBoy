package com.eason.whosagoodboy.utils.awsutils;

import android.content.Context;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;

/**
 * Created by Eason on 2018-01-13.
 */

public class RekognitionUtils
{
  private static AmazonRekognitionClient rekognitionClient;
  private static AWSCredentialsProvider credentialsProvider;

  public static AmazonRekognitionClient getRekognitionClient(Context context) {
    if (rekognitionClient == null) {
      rekognitionClient = new AmazonRekognitionClient()
    }
  }
}
