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


public class DateTimeComparisonTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();
  

  @Test
  public void testLt()
  {

    assertTrue(DateTimeComparison.LT.compare(dtf.parseDateTime("2013-03-02T00:00:00Z"),
                                              dtf.parseDateTime("2013-03-03T00:00:00Z")));
    assertTrue(! DateTimeComparison.LT.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                              dtf.parseDateTime("2013-03-02T00:00:00Z")));
  }



  @Test
  public void testLteq()
  {
    
    
    assertTrue(DateTimeComparison.LTEQ.compare(dtf.parseDateTime("2013-03-02T00:00:00Z"),
                                                dtf.parseDateTime("2013-03-03T00:00:00Z")));
    assertTrue(!DateTimeComparison.LTEQ.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                                 dtf.parseDateTime("2013-03-02T00:00:00Z")));
    
    
    assertTrue(DateTimeComparison.LTEQ.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                                dtf.parseDateTime("2013-03-03T00:00:00Z")));
  }
  

  @Test
  public void testEq()
  {
    
    assertTrue(DateTimeComparison.EQ.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                              dtf.parseDateTime("2013-03-03T00:00:00Z")));
    assertTrue(! DateTimeComparison.EQ.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                                dtf.parseDateTime("2013-03-02T00:00:00Z")));
    assertTrue(! DateTimeComparison.EQ.compare(dtf.parseDateTime("2013-03-02T00:00:00Z"),
                                                dtf.parseDateTime("2013-03-03T00:00:00Z")));
  }


  @Test
  public void testGteq()
  {
    
    
    assertTrue(!DateTimeComparison.GTEQ.compare(dtf.parseDateTime("2013-03-02T00:00:00Z"),
                                                 dtf.parseDateTime("2013-03-03T00:00:00Z")));
    assertTrue(DateTimeComparison.GTEQ.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                                dtf.parseDateTime("2013-03-02T00:00:00Z")));
    
    
    assertTrue(DateTimeComparison.GTEQ.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                                dtf.parseDateTime("2013-03-03T00:00:00Z")));
  }
  
  
  @Test
  public void testGt()
  {

    assertTrue(! DateTimeComparison.GT.compare(dtf.parseDateTime("2013-03-02T00:00:00Z"),
                                                dtf.parseDateTime("2013-03-03T00:00:00Z")));
    assertTrue(DateTimeComparison.GT.compare(dtf.parseDateTime("2013-03-03T00:00:00Z"),
                                              dtf.parseDateTime("2013-03-02T00:00:00Z")));
  }
  
  
}

