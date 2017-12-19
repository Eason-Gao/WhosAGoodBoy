package com.eason.whosagoodboy.ViewModel;

import android.arch.lifecycle.ViewModel;

import com.eason.whosagoodboy.Entity.Dog;
import com.eason.whosagoodboy.Repository.DogRepository;

import javax.inject.Inject;

/**
 * Created by student01 on 19/12/17.
 */

public class DogViewModel extends ViewModel
{
	private DogRepository dogRepository;

	@Inject
	public DogViewModel(DogRepository dogRepository)
	{
		this.dogRepository = dogRepository;
	}

	public void insertDog(Dog dog)
	{
		dogRepository.insertDogToDb(dog);
		dogRepository.insertDogToApi(dog);
	}
}
