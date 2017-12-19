package com.eason.whosagoodboy.module;

import android.content.Context;

import com.eason.whosagoodboy.Interface.ApiService;
import com.eason.whosagoodboy.Repository.DogRepository;
import com.eason.whosagoodboy.ViewModel.Factory.DogViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eason on 2017-12-18.
 */

@Module
public class ApiModule
{
//	@Provides
//	@Singleton
//	ApiService providesAPIService(Context context){
//	}

	@Provides
	@Singleton
	DogViewModelFactory providesDogViewModelFactory(DogRepository dogRepository)
	{
		return new DogViewModelFactory(dogRepository);
	}

}
