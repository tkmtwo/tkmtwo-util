package com.tkmtwo.util.org.joda.time;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.lang.Enums;


/*
Moon Landing: 1969-07-24T16:50:35.730Z

Tests should be run with timezone in UTC (or GMT)
 */

public class DateTimeFieldsTest
{
  DateTime dt;
  DateTimeFormatter dtf;
  

  @Before
  public void setUp()
  {
    dtf = ISODateTimeFormat.dateTime();
    dt = dtf.parseDateTime("1969-07-24T16:50:35.730Z");
  }
  


  @Test
  public void testRoundFloor()
  {
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.730Z"),
                 DateTimeFields.MILLIS.roundFloor(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.000Z"),
                 DateTimeFields.SECOND.roundFloor(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T16:50:00.000Z"),
                 DateTimeFields.MINUTE.roundFloor(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T16:00:00.000Z"),
                 DateTimeFields.HOUR.roundFloor(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T00:00:00.000Z"),
                 DateTimeFields.DAY.roundFloor(dt));

    assertEquals(dtf.parseDateTime("1969-07-01T00:00:00.000Z"),
                 DateTimeFields.MONTH.roundFloor(dt));

    assertEquals(dtf.parseDateTime("1969-01-01T00:00:00.000Z"),
                 DateTimeFields.YEAR.roundFloor(dt));
    
    assertEquals(dtf.parseDateTime("1900-01-01T00:00:00.000Z"),
                 DateTimeFields.CENTURY.roundFloor(dt));
  }
  

  @Test
  public void testRoundFloorAdd()
  {
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.730Z"),
                 DateTimeFields.MILLIS.roundFloor(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T16:50:36.000Z"),
                 DateTimeFields.SECOND.roundFloorAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-07-24T16:51:00.000Z"),
                 DateTimeFields.MINUTE.roundFloorAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-07-24T17:00:00.000Z"),
                 DateTimeFields.HOUR.roundFloorAdd(dt, 1));
    assertEquals(dtf.parseDateTime("1969-07-24T15:00:00.000Z"),
                 DateTimeFields.HOUR.roundFloorAdd(dt, -1));


    assertEquals(dtf.parseDateTime("1969-07-25T00:00:00.000Z"),
                 DateTimeFields.DAY.roundFloorAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-08-01T00:00:00.000Z"),
                 DateTimeFields.MONTH.roundFloorAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1970-01-01T00:00:00.000Z"),
                 DateTimeFields.YEAR.roundFloorAdd(dt, 1));
    
    assertEquals(dtf.parseDateTime("2000-01-01T00:00:00.000Z"),
                 DateTimeFields.CENTURY.roundFloorAdd(dt, 1));
  }
  




  @Test
  public void testRoundCeiling()
  {
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.730Z"),
                 DateTimeFields.MILLIS.roundCeiling(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T16:50:36.000Z"),
                 DateTimeFields.SECOND.roundCeiling(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T16:51:00.000Z"),
                 DateTimeFields.MINUTE.roundCeiling(dt));

    assertEquals(dtf.parseDateTime("1969-07-24T17:00:00.000Z"),
                 DateTimeFields.HOUR.roundCeiling(dt));

    assertEquals(dtf.parseDateTime("1969-07-25T00:00:00.000Z"),
                 DateTimeFields.DAY.roundCeiling(dt));

    assertEquals(dtf.parseDateTime("1969-08-01T00:00:00.000Z"),
                 DateTimeFields.MONTH.roundCeiling(dt));

    assertEquals(dtf.parseDateTime("1970-01-01T00:00:00.000Z"),
                 DateTimeFields.YEAR.roundCeiling(dt));
    
    assertEquals(dtf.parseDateTime("2000-01-01T00:00:00.000Z"),
                 DateTimeFields.CENTURY.roundCeiling(dt));
  }

  @Test
  public void testRoundCeilingAdd()
  {
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.731Z"),
                 DateTimeFields.MILLIS.roundCeilingAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-07-24T16:50:37.000Z"),
                 DateTimeFields.SECOND.roundCeilingAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-07-24T16:52:00.000Z"),
                 DateTimeFields.MINUTE.roundCeilingAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-07-24T18:00:00.000Z"),
                 DateTimeFields.HOUR.roundCeilingAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-07-26T00:00:00.000Z"),
                 DateTimeFields.DAY.roundCeilingAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1969-09-01T00:00:00.000Z"),
                 DateTimeFields.MONTH.roundCeilingAdd(dt, 1));

    assertEquals(dtf.parseDateTime("1971-01-01T00:00:00.000Z"),
                 DateTimeFields.YEAR.roundCeilingAdd(dt, 1));
    
    assertEquals(dtf.parseDateTime("2100-01-01T00:00:00.000Z"),
                 DateTimeFields.CENTURY.roundCeilingAdd(dt, 1));
  }

  @Test
  public void testAddSimple()
  {
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.731Z"),
                 DateTimeFields.MILLIS.add(dt, 1));
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.729Z"),
                 DateTimeFields.MILLIS.add(dt, -1));

    assertEquals(dtf.parseDateTime("1969-07-24T16:50:36.730Z"),
                 DateTimeFields.SECOND.add(dt, 1));
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:34.730Z"),
                 DateTimeFields.SECOND.add(dt, -1));

    assertEquals(dtf.parseDateTime("1969-07-24T16:51:35.730Z"),
                 DateTimeFields.MINUTE.add(dt, 1));
    assertEquals(dtf.parseDateTime("1969-07-24T16:49:35.730Z"),
                 DateTimeFields.MINUTE.add(dt, -1));

    assertEquals(dtf.parseDateTime("1969-07-24T17:50:35.730Z"),
                 DateTimeFields.HOUR.add(dt, 1));
    assertEquals(dtf.parseDateTime("1969-07-24T15:50:35.730Z"),
                 DateTimeFields.HOUR.add(dt, -1));

    assertEquals(dtf.parseDateTime("1969-07-25T16:50:35.730Z"),
                 DateTimeFields.DAY.add(dt, 1));
    assertEquals(dtf.parseDateTime("1969-07-23T16:50:35.730Z"),
                 DateTimeFields.DAY.add(dt, -1));

    assertEquals(dtf.parseDateTime("1969-08-24T16:50:35.730Z"),
                 DateTimeFields.MONTH.add(dt, 1));
    assertEquals(dtf.parseDateTime("1969-06-24T16:50:35.730Z"),
                 DateTimeFields.MONTH.add(dt, -1));

    assertEquals(dtf.parseDateTime("1970-07-24T16:50:35.730Z"),
                 DateTimeFields.YEAR.add(dt, 1));
    assertEquals(dtf.parseDateTime("1968-07-24T16:50:35.730Z"),
                 DateTimeFields.YEAR.add(dt, -1));

    assertEquals(dtf.parseDateTime("2069-07-24T16:50:35.730Z"),
                 DateTimeFields.CENTURY.add(dt, 1));
    assertEquals(dtf.parseDateTime("1869-07-24T16:50:35.730Z"),
                 DateTimeFields.CENTURY.add(dt, -1));


  }
  
  
  
  @Test
  public void testAddComplex()
  {
    //Control
    assertEquals(dtf.parseDateTime("1969-07-24T16:50:35.730Z"),
                 DateTimeFields.MILLIS.add(dt, 0));


    //Roll a minute
    assertEquals(dtf.parseDateTime("1969-07-24T16:51:05.730Z"),
                 DateTimeFields.SECOND.add(dt, 30));
    assertEquals(dtf.parseDateTime("1969-07-24T16:49:30.730Z"),
                 DateTimeFields.SECOND.add(dt, -65));


    //Roll an hour
    assertEquals(dtf.parseDateTime("1969-07-24T17:02:35.730Z"),
                 DateTimeFields.MINUTE.add(dt, 12));
    assertEquals(dtf.parseDateTime("1969-07-24T15:49:35.730Z"),
                 DateTimeFields.MINUTE.add(dt, -61));

    
    //Roll a day
    assertEquals(dtf.parseDateTime("1969-07-25T00:50:35.730Z"),
                 DateTimeFields.HOUR.add(dt, 8));
    assertEquals(dtf.parseDateTime("1969-07-23T15:50:35.730Z"),
                 DateTimeFields.HOUR.add(dt, -25));

    
    //Roll a month
    assertEquals(dtf.parseDateTime("1969-08-01T16:50:35.730Z"),
                 DateTimeFields.DAY.add(dt, 8));
    assertEquals(dtf.parseDateTime("1969-06-29T16:50:35.730Z"),
                 DateTimeFields.DAY.add(dt, -25));

    
    //Roll a year
    assertEquals(dtf.parseDateTime("1970-01-24T16:50:35.730Z"),
                 DateTimeFields.MONTH.add(dt, 6));
    assertEquals(dtf.parseDateTime("1968-06-24T16:50:35.730Z"),
                 DateTimeFields.MONTH.add(dt, -13));


    //Roll a century
    assertEquals(dtf.parseDateTime("2000-07-24T16:50:35.730Z"),
                 DateTimeFields.YEAR.add(dt, 31));
    assertEquals(dtf.parseDateTime("1899-07-24T16:50:35.730Z"),
                 DateTimeFields.YEAR.add(dt, -70));

    
  }




  public void testResolution()
  {
    assertEquals(DateTimeFields.SECOND,
                 DateTimeFields.valueOf("SECOND"));


    assertEquals(DateTimeFields.SECOND,
                 Enums.valueOf(DateTimeFields.class, "SECOND"));
    assertEquals(DateTimeFields.MINUTE,
                 Enums.valueOf(DateTimeFields.class, "MINUTE"));
    assertEquals(DateTimeFields.HOUR,
                 Enums.valueOf(DateTimeFields.class, "HOUR"));
    assertEquals(DateTimeFields.DAY,
                 Enums.valueOf(DateTimeFields.class, "DAY"));
    assertEquals(DateTimeFields.MONTH,
                 Enums.valueOf(DateTimeFields.class, "MONTH"));
    assertEquals(DateTimeFields.YEAR,
                 Enums.valueOf(DateTimeFields.class, "YEAR"));
    assertEquals(DateTimeFields.CENTURY,
                 Enums.valueOf(DateTimeFields.class, "CENTURY"));


  }


  
  
  
}
