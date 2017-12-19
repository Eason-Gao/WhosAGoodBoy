package com.eason.whosagoodboy.Interface;

import com.eason.whosagoodboy.Entity.Dog;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by student01 on 19/12/17.
 */

public interface ApiService
{
	@POST("amazon end point")
	Call<Dog> insertDog(@Body Dog dog);
}
