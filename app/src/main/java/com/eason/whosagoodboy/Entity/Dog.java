package com.eason.whosagoodboy.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

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

public class Dog implements Serializable
{
	@PrimaryKey
	@ColumnInfo(name = "dog_id")
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

	public Date getDiscoveryDate()
	{
		return discoveryDate;
	}
}
