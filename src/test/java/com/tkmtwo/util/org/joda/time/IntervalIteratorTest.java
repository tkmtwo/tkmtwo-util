package com.tkmtwo.util.org.joda.time;

import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public class IntervalIteratorTest
{
  
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();
  
  
  
  /**
   * Verify an iteration over a time duration.
   *
   * Give an iterator, and compare the expected last DateTime and number of iterations.
   *
   * @param expectedLastDt a <code>DateTime</code> value
   * @param expectedIterations an <code>int</code> value
   */
  private void testIterations(Iterator<DateTime> it,
                              DateTime expectedLastDt,
                              int expectedIterations)
  {
    int actualIterations = 0;
    DateTime actualLastDt = null;
    while(it.hasNext()) {
      actualIterations++;
      actualLastDt = it.next();
    }
    
    assertEquals(expectedLastDt, actualLastDt);
    assertEquals(expectedIterations, actualIterations);
  }
  
  
  
  
  @Test
  public void testForward() {
    //  
    // Simple test -- count days in July
    //
    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), true),
                  dtf.parseDateTime("2009-07-31T00:00:00Z"), 31);


    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), false),
                   dtf.parseDateTime("2009-07-31T00:00:00Z"), 31);


    //
    // Count days in July, but bump the start by 1s
    //
    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:01Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), true),
                  dtf.parseDateTime("2009-07-31T00:00:00Z"), 31);
    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:01Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), false),
                  dtf.parseDateTime("2009-07-30T00:00:01Z"), 30);

    //
    // Count days in July, but bump the end by 1s
    //
    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:01Z")),
                                               Days.ONE.toStandardDuration(), true),
                   dtf.parseDateTime("2009-07-31T00:00:01Z"), 32);
                   
    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:01Z")),
                                               Days.ONE.toStandardDuration(), false),
                   dtf.parseDateTime("2009-07-31T00:00:00Z"), 31);



  //
  // Count 4-days at a time in July
  //
    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.FOUR.toStandardDuration(), true),
                   dtf.parseDateTime("2009-07-31T00:00:00Z"), 9);
                 
    testIterations(new ForwardIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.FOUR.toStandardDuration(), false),
                   dtf.parseDateTime("2009-07-29T00:00:00Z"), 8);
  
  }
  
  
  
  @Test
  public void testReverse()
  {
  
    //
    // Simple test -- count days in July
    //
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), true),
                  dtf.parseDateTime("2009-07-01T00:00:00Z"), 31);
                  
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), false),
                   dtf.parseDateTime("2009-07-01T00:00:00Z"), 31);
  
  
    //
    // Count days in July, but bump the start by 1s
    //
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:01Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), true),
                   dtf.parseDateTime("2009-07-01T00:00:01Z"), 31);
                   
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:01Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.ONE.toStandardDuration(), false),
                   dtf.parseDateTime("2009-07-02T00:00:00Z"), 30);



    //
    // Count days in July, but bump the end by 1s
    //
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:01Z")),
                                               Days.ONE.toStandardDuration(), true),
                   dtf.parseDateTime("2009-07-01T00:00:00Z"), 32);
                   
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:01Z")),
                                               Days.ONE.toStandardDuration(), false),
                  dtf.parseDateTime("2009-07-01T00:00:01Z"), 31);
    
    //
    // Count 4-days at a time in July
    //
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                              Days.FOUR.toStandardDuration(), true),
                   dtf.parseDateTime("2009-07-01T00:00:00Z"), 9);
                   
                   
    testIterations(new ReverseIntervalIterator(new Interval(dtf.parseDateTime("2009-07-01T00:00:00Z"),
                                                            dtf.parseDateTime("2009-07-31T00:00:00Z")),
                                               Days.FOUR.toStandardDuration(), false),
                   dtf.parseDateTime("2009-07-03T00:00:00Z"), 8);
    
    
    
    
    
  }
  
  
  
}

