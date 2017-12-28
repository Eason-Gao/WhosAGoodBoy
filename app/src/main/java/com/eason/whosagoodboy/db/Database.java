package com.eason.whosagoodboy.db;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.eason.whosagoodboy.Entity.Dog;
import com.eason.whosagoodboy.db.dao.DogDao;

/**
 * Created by student01 on 19/12/17.
 */

@android.arch.persistence.room.Database(entities = {Dog.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase
{
	public abstract DogDao getDogDao();
}
