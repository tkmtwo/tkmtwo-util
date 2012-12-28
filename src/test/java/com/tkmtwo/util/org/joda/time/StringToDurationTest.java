package com.tkmtwo.util.org.joda.time;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;

import org.joda.time.Duration;
import org.joda.time.Hours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.lang.Enums;


public class StringToDurationTest
{
  private StringToDuration std = null;
  
  @Before
  public void setup()
  {
    std = new StringToDuration();
  }
  
  
  @Test
  public void testPass()
  {
    assertEquals(new Duration(5L),
                 std.convert("new org.joda.time.Duration(5L)"));
    assertEquals(new Duration(5L),
                 std.convert("5L"));

    assertEquals(new Duration(-5L),
                 std.convert("new org.joda.time.Duration(-5L)"));
    assertEquals(new Duration(-5L),
                 std.convert("-5L"));

    assertEquals(new Duration(60L),
                 std.convert("new org.joda.time.Duration(2L * 30L)"));
    assertEquals(new Duration(60L),
                 std.convert("2L * 30L"));

    assertEquals(new Duration(3600000L),
                 std.convert("T(org.joda.time.Hours).ONE.toStandardDuration()"));
    assertEquals(new Duration(3600000L),
                 std.convert("60 * 60 * 1000"));

    assertEquals(new Duration(86400000L),
                 std.convert("T(org.joda.time.Days).ONE.toStandardDuration()"));
    assertEquals(new Duration(86400000L),
                 std.convert("24*60*60*1000"));
    
  }
  
  
  @Test
  public void testForNull() { assertEquals(new Duration(0L), std.convert(null)); }
  
  @Test
  public void testForBlank() { assertEquals(new Duration(0L), std.convert(null)); }
  
  
  @Test(expected=EvaluationException.class)
  public void testForAlph() { assertEquals(new Duration(0L), std.convert("abc")); }
  
  @Test(expected=EvaluationException.class)
  public void testForBogusCtorOne()
  {
    assertEquals(new Duration(60L),
                 std.convert("new org.joda.time.Duration(abc)"));
  }
  
  @Test(expected=EvaluationException.class)
  public void testForBogusCtorTwo()
  {
    assertEquals(new Duration(60L),
                 std.convert("new org.joda.time.Duration(\"abc\")"));
  }
  
  
  
}
