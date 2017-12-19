package com.eason.whosagoodboy.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.eason.whosagoodboy.Entity.Dog;

/**
 * Created by student01 on 19/12/17.
 */

@Dao
public interface DogDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertDog(Dog dog);
}
