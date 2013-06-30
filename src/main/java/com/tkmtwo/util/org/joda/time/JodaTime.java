package com.tkmtwo.util.org.joda.time;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.util.Assert;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;

import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.util.regex.Matchers;

/**
 * Utilities for Joda Time.
 *
 * @author tkmtwo
 * @version $Id$
 */
public class JodaTime
{
  public static final String  BASIC_DATETIME_NOMILLIS_SPEC    = "\\d{8}T\\d{6}Z";
  public static final Pattern BASIC_DATETIME_NOMILLIS_PATTERN = Pattern.compile(BASIC_DATETIME_NOMILLIS_SPEC);
  public static final DateTimeFormatter BASIC_DATETIME_FORMATTER = ISODateTimeFormat.basicDateTimeNoMillis();
  
  public static final DateTimeFormatter defaultDateTimeFormatter = ISODateTimeFormat.basicDateTimeNoMillis();
  
  
  public static DateTimeFormatter getDefaultFormatter()
  {
    return defaultDateTimeFormatter;
  }
  
  
  public static String print(Period p) {
    return ISOPeriodFormat.standard().print(p);
  }
  public static Period parsePeriod(String s) {
    return ISOPeriodFormat.standard().parsePeriod(s);
  }
    

  public static String print(ReadableInstant ri)
  {
    return ISODateTimeFormat.basicDateTimeNoMillis().print(ri);
  }
  public static DateTime parseDateTime(String s)
  {
    return ISODateTimeFormat.basicDateTimeNoMillis().parseDateTime(s);
  }
  
  
  
  public static String format(DateTime dt) {
    return format(dt, BASIC_DATETIME_FORMATTER);
  }
  public static String format(DateTime dt, DateTimeFormatter dtf) {
    return dtf.print(dt);
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
  



  public static DateTime getDateTime(String s)
  {
    return getDateTime(getDefaultFormatter(),
                       BASIC_DATETIME_NOMILLIS_PATTERN,
                       s);
  }



  /**
   * Describe <code>getDateTime</code> method here.
   *
   * @param dtf a <code>DateTimeFormatter</code> value
   * @param p a <code>Pattern</code> value
   * @param s a <code>String</code> value
   * @return a <code>DateTime</code> value
   */
  public static DateTime getDateTime(DateTimeFormatter dtf, Pattern p, String s)
  {
    List<DateTime> dateTimes = getDateTimes(dtf, p, s);
    if (dateTimes.isEmpty()) {
      return null;
    }
    return (dateTimes.get(dateTimes.size() - 1));
  }





  public static List<DateTime> getDateTimes(String s)
  {
    return getDateTimes(getDefaultFormatter(),
                        BASIC_DATETIME_NOMILLIS_PATTERN,
                        s);
  }


  public static List<DateTime> getDateTimes(DateTimeFormatter dtf, Pattern p, String s)
  {
    List<DateTime> dateTimes = new ArrayList<DateTime>();
    for (String dtString : Matchers.findAllMatches(s, p)) {
      dateTimes.add(dtf.parseDateTime(dtString));
    }
    return dateTimes;
  }

  
  
  
}
