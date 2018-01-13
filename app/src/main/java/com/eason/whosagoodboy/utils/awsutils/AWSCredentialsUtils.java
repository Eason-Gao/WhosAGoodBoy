package com.eason.whosagoodboy.utils.awsutils;

import android.content.Context;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.eason.whosagoodboy.db.Constants;

import io.reactivex.CompletableOnSubscribe;

/**
 * util class for AWS Credentials
 */

public class AWSCredentialsUtils
{
  public static AWSCredentials createAWSCredentials(Context context)
  {
    try {
      return new BasicAWSCredentials(Constants.AWS_ACCESS_KEY, Constants.AWS_SECRET_KEY);
    } catch (Exception ex) {
      throw new AmazonClientException("Credentials loading failed ", ex);
    }
  }
}
