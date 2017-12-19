package com.eason.whosagoodboy.Repository;

import com.eason.whosagoodboy.Entity.Dog;
import com.eason.whosagoodboy.Interface.ApiService;
import com.eason.whosagoodboy.db.Database;
import com.eason.whosagoodboy.db.dao.DogDao;

import javax.inject.Inject;

/**
 * Created by student01 on 19/12/17.
 */

public class DogRepository
{
	private final ApiService apiService;

	private final DogDao dogDao;

	@Inject
	public DogRepository(ApiService apiService, Database database)
	{
		this.apiService = apiService;
		this.dogDao = database.getDogDao();
	}

	public void insertDogToDb(Dog dog)
	{
		dogDao.insertDog(dog);
	}

	public void insertDogToApi(Dog dog)
	{
//		apiService.insertDog();
	}
}
