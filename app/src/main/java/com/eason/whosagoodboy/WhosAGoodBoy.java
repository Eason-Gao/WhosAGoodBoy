package com.eason.whosagoodboy;

import android.app.Application;

import com.eason.whosagoodboy.Components.AppComponent;
import com.eason.whosagoodboy.Components.DaggerAppComponent;

/**
 * Application Class
 */

public class WhosAGoodBoy extends Application
{
  private AppComponent appComponent;

  @Override
  public void onCreate()
  {
    super.onCreate();

    /* set up dependency injection */
    createAppComponent();
  }

  private void createAppComponent()
  {
    appComponent = DaggerAppComponent.builder().build();
  }

  public AppComponent getAppComponent()
  {
    return appComponent;
  }
}
