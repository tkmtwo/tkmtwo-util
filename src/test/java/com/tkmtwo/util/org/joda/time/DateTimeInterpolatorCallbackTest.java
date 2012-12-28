package com.tkmtwo.util.org.joda.time;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.util.interpolate.Interpolator;



/*
Moon Landing: 1969-07-24T16:50:35.730Z

Tests should be run with timezone in UTC (or GMT)
 */


public class DateTimeInterpolatorCallbackTest
{
  
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTime();

  /*
  private String mlString;
  private DateTime mlDateTime;


  @Before
  public void setUp() {
    mlString = "1969-07-24T16:50:35.730Z";
    mlDateTime = dtf.parseDateTime(mlString);
  }
  */



  @Test
  public void testDateTime() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    assertEquals("1969-07-24T16:50:35.730Z",
                 Interpolator.interpolate("${date}", dtic));
  }
  
  @Test
  public void testSecond() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);

    assertEquals("1969-07-24T16:50:35.000Z",
                 Interpolator.interpolate("${date.second}", dtic));
    assertEquals("1969-07-24T16:50:35.000Z",
                 Interpolator.interpolate("${date.second.floor}", dtic));
    assertEquals("1969-07-24T16:50:36.000Z",
                 Interpolator.interpolate("${date.second.ceiling}", dtic));
  }

  @Test
  public void testMinute() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    assertEquals("1969-07-24T16:50:00.000Z",
                 Interpolator.interpolate("${date.minute}", dtic));
    assertEquals("1969-07-24T16:50:00.000Z",
                 Interpolator.interpolate("${date.minute.floor}", dtic));
    assertEquals("1969-07-24T16:51:00.000Z",
                 Interpolator.interpolate("${date.minute.ceiling}", dtic));
  }

  @Test
  public void testHour() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    assertEquals("1969-07-24T16:00:00.000Z",
                 Interpolator.interpolate("${date.hour}", dtic));
    assertEquals("1969-07-24T16:00:00.000Z",
                 Interpolator.interpolate("${date.hour.floor}", dtic));
    assertEquals("1969-07-24T17:00:00.000Z",
                 Interpolator.interpolate("${date.hour.ceiling}", dtic));
  }

  @Test
  public void testDay() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    assertEquals("1969-07-24T00:00:00.000Z",
                 Interpolator.interpolate("${date.day}", dtic));
    assertEquals("1969-07-24T00:00:00.000Z",
                 Interpolator.interpolate("${date.day.floor}", dtic));
    assertEquals("1969-07-25T00:00:00.000Z",
                 Interpolator.interpolate("${date.day.ceiling}", dtic));
  }

  @Test
  public void testMonth() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    assertEquals("1969-07-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.month}", dtic));
    assertEquals("1969-07-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.month.floor}", dtic));
    assertEquals("1969-08-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.month.ceiling}", dtic));
  }

  @Test
  public void testYear() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    assertEquals("1969-01-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.year}", dtic));
    assertEquals("1969-01-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.year.floor}", dtic));
    assertEquals("1970-01-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.year.ceiling}", dtic));
  }

  @Test
  public void testCentury() {
    String dtString = "1969-07-24T16:50:35.730Z";
    DateTime dt = dtf.parseDateTime(dtString);

    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    assertEquals("1900-01-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.century}", dtic));
    assertEquals("1900-01-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.century.floor}", dtic));
    assertEquals("2000-01-01T00:00:00.000Z",
                 Interpolator.interpolate("${date.century.ceiling}", dtic));
  }
  
  

    
  
  
}
