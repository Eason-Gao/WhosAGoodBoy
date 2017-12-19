package com.eason.whosagoodboy.Components;

import com.eason.whosagoodboy.activities.MainActivity;
import com.eason.whosagoodboy.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by student01 on 19/12/17.
 */

@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent
{
	void inject(MainActivity mainActivity);
}