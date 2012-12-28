package com.tkmtwo.util.org.joda.time;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;

import org.joda.time.Period;
import org.joda.time.Hours;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.lang.Enums;


public class StringToPeriodTest
{
  private StringToPeriod stp = null;
  
  @Before
  public void setup()
  {
    stp = new StringToPeriod();
  }
  
  
  @Test
  public void testPass()
  {
    //
    //P0Y0M0W0DT0H0M0S
    //PxxYxxMxxWxxDTxxHxxMxxS
    //
    assertEquals(new Period(1, 0, 1, 0),
                 stp.convert("P0Y0M0W0DT1H0M1S"));
    assertEquals(new Period(0, 0, 0, 1, 0, 0, 1, 0),
                 stp.convert("P0Y0M0W1DT0H0M1S"));
    assertEquals(new Period(0, 0, 0, 1, 1, 0, 1, 0),
                 stp.convert("P0Y0M0W1DT1H0M1S"));


    assertEquals(new Period(3601000L).normalizedStandard(),
                 stp.convert("P0Y0M0W0DT1H0M1S").normalizedStandard());
    assertEquals(new Period(86401000L).normalizedStandard(),
                 stp.convert("P0Y0M0W1DT0H0M1S").normalizedStandard());


    assertEquals(new Period(3601000L).normalizedStandard(),
                 stp.convert("PT1H0M1S").normalizedStandard());

    
    
  }
  
  /*  
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
  */
  
  
}
