package com.tkmtwo.util.org.joda.time;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Objects;

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
    
    if (Objects.equal(s, "date")) {
      append(dateTimeFormatter.print(dateTime));
    } else if (Objects.equal(s, "date.second")
               || Objects.equal(s, "date.second.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.SECOND.roundFloor(dateTime)));
    } else if (Objects.equal(s, "date.minute")
               || Objects.equal(s, "date.minute.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.MINUTE.roundFloor(dateTime)));
    } else if (Objects.equal(s, "date.hour")
               || Objects.equal(s, "date.hour.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.HOUR.roundFloor(dateTime)));
    } else if (Objects.equal(s, "date.day")
               || Objects.equal(s, "date.day.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.DAY.roundFloor(dateTime)));
    } else if (Objects.equal(s, "date.month")
               || Objects.equal(s, "date.month.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.MONTH.roundFloor(dateTime)));
    } else if (Objects.equal(s, "date.year")
               || Objects.equal(s, "date.year.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.YEAR.roundFloor(dateTime)));
    } else if (Objects.equal(s, "date.century")
               || Objects.equal(s, "date.century.floor")) {
      append(dateTimeFormatter.print(DateTimeFields.CENTURY.roundFloor(dateTime)));
    } else if (Objects.equal(s, "date.second.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.SECOND.roundCeiling(dateTime)));
    } else if (Objects.equal(s, "date.minute.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.MINUTE.roundCeiling(dateTime)));
    } else if (Objects.equal(s, "date.hour.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.HOUR.roundCeiling(dateTime)));
    } else if (Objects.equal(s, "date.day.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.DAY.roundCeiling(dateTime)));
    } else if (Objects.equal(s, "date.month.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.MONTH.roundCeiling(dateTime)));
    } else if (Objects.equal(s, "date.year.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.YEAR.roundCeiling(dateTime)));
    } else if (Objects.equal(s, "date.century.ceiling")) {
      append(dateTimeFormatter.print(DateTimeFields.CENTURY.roundCeiling(dateTime)));
    } else {
      append(intStart + s + intStop);
    }      
    
    
  }
  
  
}
