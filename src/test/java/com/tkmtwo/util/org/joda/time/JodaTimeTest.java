package com.tkmtwo.util.org.joda.time;

import java.util.List;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.lang.Enums;


public class JodaTimeTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();
  

  public void testForAllGoodParameters() {
    List<DateTime> l = JodaTime.splitForward(new DateTime(),
                                             new DateTime().plus(Hours.ONE),
                                             new Duration(5L));
    assertTrue(l.size() > 0);
  }    
  @Test(expected=IllegalArgumentException.class)
  public void testForNullStart() {
    List<DateTime> l = JodaTime.splitForward(null,
                                             new DateTime().plus(Hours.ONE),
                                             new Duration(5L));
  }    
  @Test(expected=IllegalArgumentException.class)
  public void testForNullStop() {
    List<DateTime> l = JodaTime.splitForward(new DateTime(),
                                             null,
                                             new Duration(5L));
  }    
  @Test(expected=IllegalArgumentException.class)
  public void testForNullDuration() {
    List<DateTime> l = JodaTime.splitForward(new DateTime(),
                                             new DateTime().plus(Hours.ONE),
                                             null);
  }    
  @Test(expected=IllegalArgumentException.class)
  public void testForNegativeDuration() {
    List<DateTime> l = JodaTime.splitForward(new DateTime(),
                                             new DateTime().plus(Hours.ONE),
                                             new Duration(-5L));
  }    
  @Test(expected=IllegalArgumentException.class)
  public void testForInvalidStartStop() {
    List<DateTime> l = JodaTime.splitForward(new DateTime().plus(Hours.ONE),
                                             new DateTime(),
                                             new Duration(5L));
  }    





  @Test
  public void testSplitForwardPass()
  {
    DateTime dtStart = dtf.parseDateTime("2012-01-01T00:00:00Z");
    DateTime dtStop  = dtf.parseDateTime("2012-01-02T00:00:00Z");
    Duration duration = new Duration(Hours.ONE.toStandardDuration());

    List<DateTime> l = JodaTime.splitForward(dtStart, dtStop, duration);

    assertTrue(l.size() == 25);
    assertEquals(dtf.parseDateTime("2012-01-01T00:00:00Z"),
                 l.get(0));
    assertEquals(dtf.parseDateTime("2012-01-01T13:00:00Z"),
                 l.get(13));
    assertEquals(dtf.parseDateTime("2012-01-02T00:00:00Z"),
                 l.get(24));

  }
  
  @Test
  public void testIntervalForwardPass()
  {
    List<Interval> intervals = JodaTime.intervalForward(dtf.parseDateTime("2012-01-01T00:00:00Z"),
                                                        dtf.parseDateTime("2012-01-02T00:00:00Z"),
                                                        new Duration(Hours.ONE.toStandardDuration()));
    assertEquals(24, intervals.size());
    assertEquals(dtf.parseDateTime("2012-01-01T00:00:00Z"),
                 intervals.get(0).getStart());
    assertEquals(dtf.parseDateTime("2012-01-01T01:00:00Z"),
                 intervals.get(0).getEnd());
    
    
    assertEquals(dtf.parseDateTime("2012-01-01T10:00:00Z"),
                 intervals.get(10).getStart());
    assertEquals(dtf.parseDateTime("2012-01-01T11:00:00Z"),
                 intervals.get(10).getEnd());
    
    
    
    assertEquals(dtf.parseDateTime("2012-01-01T23:00:00Z"),
                 intervals.get(23).getStart());
    assertEquals(dtf.parseDateTime("2012-01-02T00:00:00Z"),
                 intervals.get(23).getEnd());
    
  }



  @Test
  public void testFromSecs()
  {
    assertEquals(dtf.parseDateTime("1970-01-01T00:00:00Z"),
                 JodaTime.dateTimeFromSecs(0L));
    assertEquals(dtf.parseDateTime("1970-01-02T00:00:00Z"),
                 JodaTime.dateTimeFromSecs(86400L));
    assertNull(JodaTime.dateTimeFromSecs(null));
  }

  @Test
  public void testFromMillis()
  {
    assertEquals(dtf.parseDateTime("1970-01-01T00:00:00Z"),
                 JodaTime.dateTimeFromMillis(0L));
    assertEquals(dtf.parseDateTime("1970-01-02T00:00:00Z"),
                 JodaTime.dateTimeFromMillis(86400000L));
    assertNull(JodaTime.dateTimeFromMillis(null));
  }
  
  
  
  
  @Test
  public void testTemp()
  {
    String s = "My dob is 19691102T033333Z and today is 20130427T000345Z which makes me over 40!";

    List<DateTime> dts = JodaTime.getDateTimes(s);
    assertEquals(2, dts.size());
    assertEquals(dtf.parseDateTime("1969-11-02T03:33:33Z"),
                 dts.get(0));
    assertEquals(dtf.parseDateTime("2013-04-27T00:03:45Z"),
                 dts.get(1));
    
    
    assertEquals(dtf.parseDateTime("2013-04-27T00:03:45Z"),
                 JodaTime.getDateTime(s));
    
    
    
  }
  
  
}

