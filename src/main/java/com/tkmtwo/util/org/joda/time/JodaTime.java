package com.tkmtwo.util.org.joda.time;

import java.util.List;
import java.util.ArrayList;

import org.springframework.util.Assert;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.tkmtwo.util.interpolate.Interpolator;

/**
 * Utilities for Joda Time.
 *
 * @author tkmtwo
 * @version $Id$
 */
public class JodaTime
{
  public static final String PATTERN_BASIC_DATETIME_NOMILLIS = "\\d{8}T\\d{6}Z";

  private static DateTimeFormatter defaultDateTimeFormatter = ISODateTimeFormat.basicDateTimeNoMillis();
  
  
  public static DateTimeFormatter getDefaultFormatter()
  {
    return defaultDateTimeFormatter;
  }
  
  
  
  
  
  
  
  /**
   * Interpolate a String using the current DateTime
   * and the default DateTimeFormatter.
   *
   * @param s a <code>String</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(String s)
  {
    return interpolate(s, new DateTime());
  }


  /**
   * Interpolate a String using the supplied DateTime
   * and the default DateTimeFormatter.
   *
   * @param s a <code>String</code> value
   * @param dt a <code>DateTime</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(String s,
                                   DateTime dt)
  {
    return interpolate(s, dt, JodaTime.getDefaultFormatter());
  }


  /**
   * Interpolate a String using the supplied DateTime
   * and supplied DateTimeFormatter.
   *
   * @param s a <code>String</code> value
   * @param dt a <code>DateTime</code> value
   * @param dtf a <code>DateTimeFormatter</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(String s,
                                   DateTime dt,
                                   DateTimeFormatter dtf)
  {
    DateTimeInterpolatorCallback dtic =
      new DateTimeInterpolatorCallback(dt, dtf);
    return Interpolator.interpolate(s, dtic);
  }
  



  
  /**
   * Split a piece of time into intermediate DateTimes
   * into a List.
   *
   * @param start a <code>DateTime</code> value
   * @param stop a <code>DateTime</code> value
   * @param duration a <code>Duration</code> value
   * @return a <code>List<DateTime></code> value
   */
  public static List<DateTime> splitForward(DateTime start,
                                            DateTime stop,
                                            Duration duration)
  {
    Assert.notNull(start, "Need a start.");
    Assert.notNull(stop, "Need a stop.");
    Assert.notNull(duration, "Need a duration.");
    Assert.isTrue(duration.getMillis() > 0, "Need a positive duration.");
    Assert.isTrue(start.isBefore(stop));
    
    List<DateTime> dts = new ArrayList<DateTime>();

    for(DateTime dt = start;
        dt.isBefore(stop) || dt.isEqual(stop);
        dt = dt.plus(duration)) {
      dts.add(dt);
    }
    return dts;
  }
  
  
  
  
  
  
  /**
   * Split a piece of time into Intervals.
   *
   * @param start a <code>DateTime</code> value
   * @param stop a <code>DateTime</code> value
   * @param duration a <code>Duration</code> value
   * @return a <code>List<DateTime></code> value
   */
  public static List<Interval> intervalForward(DateTime start,
                                               DateTime stop,
                                               Duration duration)
  {
    List<DateTime> splits = splitForward(start, stop, duration);
    Assert.isTrue(splits.size() > 1, "Need at least two splits.");
    
    
    List<Interval> intervals = new ArrayList<Interval>();
    DateTime dtStart = splits.get(0);
    
    for (int i = 1; i < splits.size(); i++) {
      DateTime dtStop = splits.get(i);
      Interval interval = new Interval(dtStart, dtStop);
      intervals.add(interval);
      dtStart = dtStop;
    }
    
    return intervals;
  }
  

  /**
   * Construct a DateTime from number of seconds since 1970-01-01T00:00:00
   *
   * @param l a <code>Long</code> value
   * @return a <code>DateTime</code> value
   */
  public static DateTime dateTimeFromSecs(Long l)
  {
    if (l == null) {
      return null;
    }
    return dateTimeFromSecs(l.longValue());
  }


  /**
   * Construct a DateTime from number of seconds since 1970-01-01T00:00:00
   *
   * @param l a <code>long</code> value
   * @return a <code>DateTime</code> value
   */
  public static DateTime dateTimeFromSecs(long l)
  {
    return new DateTime(l * 1000L);
  }

  /**
   * Construct a DateTime from number of milliseconds since 1970-01-01T00:00:00
   *
   * @param l a <code>Long</code> value
   * @return a <code>DateTime</code> value
   */
  public static DateTime dateTimeFromMillis(Long l)
  {
    if (l == null) {
      return null;
    }
    return dateTimeFromMillis(l.longValue());
  }


  /**
   * Construct a DateTime from number of milliseconds since 1970-01-01T00:00:00
   *
   * @param l a <code>long</code> value
   * @return a <code>DateTime</code> value
   */
  public static DateTime dateTimeFromMillis(long l)
  {
    return new DateTime(l);
  }
  
  
  
}
