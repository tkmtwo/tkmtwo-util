package com.tkmtwo.util.org.joda.time;

import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


/**
 * Enumeration for <code>DateTime</code> fields with behaviors.
 *
 * This is essentially a set of utility methods around <code>DateTime.Property</code>
 * which allows convenient access to rounding and adding.  The added benefit is that
 * the properties are enumerated, so the field-level access is more declarative.
 *
 * @author Tom Mahaffey
 * @version $Id$
 */
public enum DateTimeFields
{
  /** To the millisecond. */
  MILLIS {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.millisOfSecond();
    }
  },
  /** To the second. */
  SECOND {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.secondOfMinute();
    }
  },
  /** To the minute. */
  MINUTE {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.minuteOfHour();
    }
  },
  /** To the hour. */
  HOUR {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.hourOfDay();
    }
  },
  /** To the day. */
  DAY {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.dayOfMonth();
    }
  },
  /** To the month. */
  MONTH {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.monthOfYear();
    }
  },
  /** To the year. */
  YEAR {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.yearOfCentury();
    }
  },
  /** To the century. */
  CENTURY {
    public DateTime.Property getProperty(DateTime dateTime) {
      return dateTime.centuryOfEra();
    }
  };


  /**
   * Gets the <code>DateTime</code> property.
   *
   *
   * @param dateTime
   */
  public abstract DateTime.Property getProperty(final DateTime dateTime);
  
  
  
  


  /**
   * Adds field units to a <code>DateTime</code>.
   *
   * @param dateTime a <code>DateTime</code>.
   * @param i a number of units to add.
   */
  public DateTime add(final DateTime dateTime, final int i) {
    return getProperty(dateTime).addToCopy(i);
  }



  /**
   * Rounds the current <code>DateTime</code> down.
   *
   * @return a rounded <code>DateTime</code>
   */
  public DateTime current()
  {
    return currentFloor();
  }
  public String currentString()
  {
    return currentFloorString();
  }





  /**
   * Rounds the current <code>DateTime</code> down.
   *
   * @return a rounded <code>DateTime</code>
   */
  public DateTime currentFloor()
  {
    return roundFloor(new DateTime());
  }
  public String currentFloorString()
  {
    return roundFloorString(new DateTime());
  }

  /**
   * Rounds the current <code>DateTime</code> up.
   *
   * @return a rounded <code>DateTime</code>
   */
  public DateTime currentCeiling()
  {
    return roundCeiling(new DateTime());
  }
  public String currentCeilingString()
  {
    return roundCeilingString(new DateTime());
  }
  












  /**
   * Rounds a <code>DateTime</code> down.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @return a rounded <code>DateTime</code>
   */
  public DateTime roundFloor(final DateTime dateTime) {
    return getProperty(dateTime).roundFloorCopy();
  }
  public String roundFloorString(final DateTime dateTime)
  {
    return roundFloorString(JodaTime.getDefaultFormatter(), dateTime);
  }
  public String roundFloorString(final DateTimeFormatter dateTimeFormatter,
                                 final DateTime dateTime)
  {
    return dateTimeFormatter.print(roundFloor(dateTime));
  }
  
  
  
  
  
  
  
  
  /**
   * Rounds a <code>DateTime</code> down and adds field units.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @param i a number of units to add.
   * @return a rounded <code>DateTime</code> with units added.
   */
  public DateTime roundFloorAdd(final DateTime dateTime, final int i) {
    DateTime dt = roundFloor(dateTime);
    return add(dt, i);
  }
  public String roundFloorAddString(final DateTime dateTime,
                                    final int i)
  {
    return roundFloorAddString(JodaTime.getDefaultFormatter(), dateTime, i);
  }
  
  public String roundFloorAddString(final DateTimeFormatter dateTimeFormatter,
                                    final DateTime dateTime,
                                    final int i)
  {
    return dateTimeFormatter.print(roundFloorAdd(dateTime, i));
  }
  
  
  
  
  
  /**
   * Rounds a <code>DateTime</code> up.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @return a rounded <code>DateTime</code>
   */
  public DateTime roundCeiling(final DateTime dateTime) {
    return getProperty(dateTime).roundCeilingCopy();
  }
  public String roundCeilingString(final DateTime dateTime)
  {
    return roundCeilingString(JodaTime.getDefaultFormatter(), dateTime);
  }
  public String roundCeilingString(final DateTimeFormatter dateTimeFormatter,
                                   final DateTime dateTime)
  {
    return dateTimeFormatter.print(roundCeiling(dateTime));
  }
  
  
  
  /**
   * Rounds a <code>DateTime</code> up and adds field units.
   *
   * @param dateTime a <code>DateTime</code> to round.
   * @param i a number of units to add.
   * @return a rounded <code>DateTime</code> with units added.
   */
  public DateTime roundCeilingAdd(final DateTime dateTime, final int i) {
    DateTime dt = roundCeiling(dateTime);
    return add(dt, i);
  }
  public String roundCeilingAddString(final DateTime dateTime,
                                      final int i)
  {
    return roundCeilingAddString(JodaTime.getDefaultFormatter(), dateTime, i);
  }
  public String roundCeilingAddString(final DateTimeFormatter dateTimeFormatter,
                                      final DateTime dateTime,
                                      final int i)
  {
    return dateTimeFormatter.print(roundCeilingAdd(dateTime, i));
  }
  
  
}

/*
   public static DateTime floorToMinutePeriod(DateTime value, int periodInMinutes) {
        if (value == null) {
            return null;
        }
        if (periodInMinutes <= 0 || periodInMinutes > 59) {
            throw new IllegalArgumentException("period in minutes must be > 0 and <= 59");
        }
        int min = value.getMinuteOfHour();
        min = (min / periodInMinutes) * periodInMinutes;
        return new DateTime(value.getYear(), value.getMonthOfYear(), value.getDayOfMonth(), value.getHourOfDay(), min, 0, 0, value.getZone());
    }

 */
