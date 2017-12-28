package com.eason.whosagoodboy.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by student01 on 19/12/17.
 */

public class Converters
{
  @TypeConverter
  public static Date fromTimestamp(Long value)
  {
    return value == null ? null : new Date(value);
  }

  @TypeConverter
  public Long dateToTimestamp(Date date)
  {
    if (date == null) {
      return null;
    } else {
      return date.getTime();
    }
  }
}
