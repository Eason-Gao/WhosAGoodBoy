package com.eason.whosagoodboy.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.eason.whosagoodboy.db.Converters;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by student01 on 19/12/17.
 */

@Entity(tableName = "dog",
		indices = {@Index(value = {"dog_id"},
				unique = true)})
@TypeConverters({Converters.class})
public class Dog implements Serializable
{
	@PrimaryKey
	@ColumnInfo(name = "dog_id")
	@NonNull
	private String id;

	@ColumnInfo(name = "dog_breed")
	private String dogBreed;

	@ColumnInfo(name = "discovery_date")
	private Date discoveryDate;

	@Ignore
	public Dog()
	{
		super();

		id = UUID.randomUUID().toString();
		discoveryDate = Calendar.getInstance().getTime();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDogBreed()
	{
		return dogBreed;
	}

	public void setDogBreed(String dogBreed)
	{
		this.dogBreed = dogBreed;
	}
}
