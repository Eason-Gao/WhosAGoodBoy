package com.eason.whosagoodboy.utils.awsutils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.eason.whosagoodboy.db.Constants;

/**
 * util class for AWS Credentials
 */

public class AWSCredentialsUtils
{
  public static AWSCredentials createAWSCredentials()
  {
    try {
      return new BasicAWSCredentials(Constants.AWS_ACCESS_KEY, Constants.AWS_SECRET_KEY);
    } catch (Exception ex) {
      throw new AmazonClientException("Credentials loading failed ", ex);
    }
  }
}
