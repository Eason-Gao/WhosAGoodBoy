package com.eason.whosagoodboy.ViewModel.Factory;

import android.arch.lifecycle.ViewModelProvider;

import com.eason.whosagoodboy.Repository.DogRepository;
import com.eason.whosagoodboy.ViewModel.DogViewModel;

import javax.inject.Inject;

/**
 * Created by student01 on 19/12/17.
 */

public class DogViewModelFactory implements ViewModelProvider.Factory
{
	private DogRepository dogRepository;

	@Inject
	public DogViewModelFactory(DogRepository dogRepository)
	{
		this.dogRepository = dogRepository;
	}

	@Override
	public DogViewModel create(Class modelClass)
	{
		return new DogViewModel(dogRepository);
	}
}
