package com.tkmtwo.util.org.joda.time;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Hours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.lang.Enums;
import com.tkmtwo.util.org.joda.time.JodaTime;

public class ISODateTimeNoMillisIntervalConverterTest
{
  private ISODateTimeNoMillisIntervalConverter intervalConverter = new ISODateTimeNoMillisIntervalConverter();
  

  @Test
  public void testInvalidStrings()
  {
    String[] ss = new String[] {
      null,
      "",
      "  ",
      "abc",
      "a34x",
      "1970-01-01T00:00:00Z",
      "1970-01-01T00:00:00/1970-01-01T01:00:00Z",
      "1970-01-01T00:00:00Z/1970-01-01T01:00:00",
      "19700101T000000/19700101T010000"
    };
    
    for (String s : ss) {
      try {
        Interval interval = intervalConverter.convert(s);
        fail("Should not have accepted " + s);
      } catch (IllegalArgumentException iae) {
        ; //do nothing
      }
    }
  }
  
  
  
  @Test
  public void testPass()
  {
    DateTime dtStart = new DateTime(0L);
    DateTime dtEnd = dtStart.plus(Hours.ONE.toStandardDuration());
    Interval interval = new Interval(dtStart, dtEnd);
    
    assertEquals(interval,
                 intervalConverter.convert("1970-01-01T00:00:00Z/1970-01-01T01:00:00Z"));

  }
  
  
  
  
}
