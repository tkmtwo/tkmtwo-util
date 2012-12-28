package com.tkmtwo.util.org.joda.time;

import org.apache.commons.lang.StringUtils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.tkmtwo.util.interpolate.AbstractInterpolatorCallback;


public class DateTimeInterpolatorCallback
  extends AbstractInterpolatorCallback {

  protected DateTime dateTime;
  protected DateTimeFormatter dateTimeFormatter;

  
  public DateTimeInterpolatorCallback(DateTime dt) {
    this(dt, JodaTime.getDefaultFormatter());
  }
  public DateTimeInterpolatorCallback(DateTime dt, DateTimeFormatter dtf) {
    dateTime = dt;
    dateTimeFormatter = dtf;
  }





  /**
   * @see InterpolatorCallback#replace(String, int)
   * @param token {@inheritDoc}
   * @param tokenNumber {@inheritDoc}
   */
  public void replace(String s, int i, String intStart, String intStop) {
    if (s == null) {
      return;
    }
    
    if (StringUtils.equals(s, "date")) {
      append(dateTimeFormatter.print(dateTime));
    } else if (StringUtils.equals(s, "date.second")
               || StringUtils.equals(s, "date.second.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.SECOND.roundFloor(dateTime)));
    } else if (StringUtils.equals(s, "date.minute")
               || StringUtils.equals(s, "date.minute.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.MINUTE.roundFloor(dateTime)));
    } else if (StringUtils.equals(s, "date.hour")
               || StringUtils.equals(s, "date.hour.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.HOUR.roundFloor(dateTime)));
    } else if (StringUtils.equals(s, "date.day")
               || StringUtils.equals(s, "date.day.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.DAY.roundFloor(dateTime)));
    } else if (StringUtils.equals(s, "date.month")
               || StringUtils.equals(s, "date.month.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.MONTH.roundFloor(dateTime)));
    } else if (StringUtils.equals(s, "date.year")
               || StringUtils.equals(s, "date.year.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.YEAR.roundFloor(dateTime)));
    } else if (StringUtils.equals(s, "date.century")
               || StringUtils.equals(s, "date.century.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.CENTURY.roundFloor(dateTime)));
    } else if (StringUtils.equals(s, "date.second.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.SECOND.roundCeiling(dateTime)));
    } else if (StringUtils.equals(s, "date.minute.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.MINUTE.roundCeiling(dateTime)));
    } else if (StringUtils.equals(s, "date.hour.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.HOUR.roundCeiling(dateTime)));
    } else if (StringUtils.equals(s, "date.day.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.DAY.roundCeiling(dateTime)));
    } else if (StringUtils.equals(s, "date.month.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.MONTH.roundCeiling(dateTime)));
    } else if (StringUtils.equals(s, "date.year.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.YEAR.roundCeiling(dateTime)));
    } else if (StringUtils.equals(s, "date.century.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.CENTURY.roundCeiling(dateTime)));
    } else {
      append(intStart + s + intStop);
    }      
    
    
  }
  
  
}
