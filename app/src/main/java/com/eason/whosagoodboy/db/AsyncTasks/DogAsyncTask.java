package com.eason.whosagoodboy.db.AsyncTasks;

import android.os.AsyncTask;

import com.eason.whosagoodboy.Entity.Dog;
import com.eason.whosagoodboy.ViewModel.DogViewModel;
import com.eason.whosagoodboy.utils.DataSyncType;

/**
 * Created by student01 on 19/12/17.
 */

public class DogAsyncTask extends AsyncTask<Dog, Integer, Boolean>
{
	private final DogViewModel dogViewModel;

	private final DataSyncType dataSyncType;

	public DogAsyncTask(DogViewModel dogViewModel, DataSyncType dataSyncType)
	{
		this.dogViewModel = dogViewModel;

		this.dataSyncType = dataSyncType;
	}

	@Override
	protected Boolean doInBackground(Dog... dogs)
	{
		for (Dog dog : dogs)
		{
			switch (dataSyncType)
			{
				case INSERT:
					dogViewModel.insertDog(dog);
					break;
				default:
					break;
			}
		}
		return null;
	}
}
