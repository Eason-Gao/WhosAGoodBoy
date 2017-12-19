package com.eason.whosagoodboy;

import android.app.Application;

import com.eason.whosagoodboy.Components.AppComponent;

/**
 * Created by student01 on 19/12/17.
 */

public class WhosAGoodBoy extends Application
{
	private AppComponent appComponent;

	public AppComponent getAppComponent()
	{
		return appComponent;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
	}
}
