package com.eason.whosagoodboy.utils.awsutils;

import android.content.Context;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.eason.whosagoodboy.db.Constants;

/**
 * Util to create amazon rekognition endpoint
 */

public class RekognitionUtils
{
  private static AmazonRekognitionClient rekognitionClient;

  public static AmazonRekognitionClient getRekognitionClient(Context context) {
    if (rekognitionClient == null) {
      rekognitionClient = new AmazonRekognitionClient(AWSCredentialsUtils.createAWSCredentials(context));
      rekognitionClient.setRegion((Region.getRegion(Regions.fromName(Constants.BUCKET_REGION))));
      return rekognitionClient;
    } else {
      return rekognitionClient;
    }
  }
}
