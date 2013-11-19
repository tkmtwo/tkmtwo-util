package com.tkmtwo.util.org.joda.time;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Period;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.lang.Enums;
import com.tkmtwo.util.org.joda.time.JodaTime;

public class ISODateTimeNoMillisConverterTest
{
  private ISODateTimeNoMillisConverter dtConverter = new ISODateTimeNoMillisConverter();
  

  @Test
  public void testInvalidStrings()
  {
    String[] ss = new String[] {
      null,
      "",
      "  ",
      "abc",
      "a34x"
    };
    
    for (String s : ss) {
      try {
        DateTime dt = dtConverter.convert(s);
        fail("Should not have accepted " + s);
      } catch (IllegalArgumentException iae) {
        ; //do nothing
      }
    }
  }
  
  
  
  @Test
  public void testPass()
  {
    
    assertEquals(new DateTime(0L),
                 dtConverter.convert("1970-01-01T00:00:00Z"));
    
  }
  
  
  
  
}
