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

public class ISOPeriodConverterTest
{
  private ISOPeriodConverter ipConverter = null;
  
  @Before
  public void setup()
  {
    ipConverter = new ISOPeriodConverter();
  }


  @Test
  public void testInvalidStrings()
  {
    String[] ss = new String[] {
      null,
      "",
      "  ",
      "abc",
      "a34x",
      "new org.joda.time.Duration(abc)",
      "new org.joda.time.Duration(\"abc\")"
    };

    for (String s : ss) {
      try {
        Period p = ipConverter.convert(s);
        fail("Should not have accepted " + s);
      } catch (IllegalArgumentException iae) {
        ; //do nothing
      }
    }
  }

  
  
  @Test
  public void testPass()
  {

    assertEquals(new Duration(3600000L),
                 ipConverter.convert("PT1H").toStandardDuration());

    assertEquals(new Duration(86400000L),
                 ipConverter.convert("P1D").toStandardDuration());

  }
  
  //@Test
  public void testThat()
  {
    DateTime dt = new DateTime();
    Period p = ipConverter.convert("P1D");
    
    System.out.println("Now is            " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt));
    System.out.println("Now is            " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt));
    System.out.println("Now is            " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt));

    System.out.println("Plus period is    " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.plus(p)));
    System.out.println("Plus period is    " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.plus(p)));
    System.out.println("Plus period is    " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.plus(p)));

    System.out.println("Minus period is   " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.minus(p)));
    System.out.println("Minus period is   " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.minus(p)));
    System.out.println("Minus period is   " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.minus(p)));

    System.out.println("Plus negperiod is " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.plus(p.negated())));
    System.out.println("Plus negperiod is " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.plus(p.negated())));
    System.out.println("Plus negperiod is " + JodaTime.BASIC_DATETIME_FORMATTER.print(dt.plus(p.negated())));
  }

  
  
  
}
