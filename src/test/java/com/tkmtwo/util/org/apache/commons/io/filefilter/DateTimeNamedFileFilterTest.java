package com.tkmtwo.util.org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;

import java.util.List;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ParseException;

import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


import org.apache.commons.io.FileUtils;


import com.tkmtwo.util.interpolate.Interpolator;
import com.tkmtwo.util.java.lang.Enums;
import com.tkmtwo.util.java.io.Files;
import com.tkmtwo.util.org.joda.time.JodaTime;
import com.tkmtwo.util.org.joda.time.DateTimeComparison;
import com.tkmtwo.util.org.joda.time.DateTimeFields;
import com.tkmtwo.util.org.joda.time.ISOPeriodConverter;

public class DateTimeNamedFileFilterTest
{
  private static DateTimeFormatter dtf = ISODateTimeFormat.dateTimeNoMillis();

  private DateTime targetDateTime = dtf.parseDateTime("2013-03-01T00:00:00Z");
  private String fileName   = "20130401T000000Z";
  private String dirName = "/some/path/to/";
  
  
  @Test
  public void testDateOnlyDefaults()
  {
    DateTimeNamedFileFilter dtnff = new DateTimeNamedFileFilter();
    assertTrue(dtnff.accept(new File(fileName)));
    assertTrue(dtnff.accept(new File("pre-" + fileName + "-post")));

    assertTrue(dtnff.accept(new File(dirName), fileName));
    assertTrue(dtnff.accept(new File(dirName), "pre-" + fileName + "-post"));

  }
  
  @Test
  public void testComparisonDefaults()
  {
    DateTimeNamedFileFilter dtnff = new DateTimeNamedFileFilter();
    DateTime dt = dtf.parseDateTime("2013-03-02T00:00:00Z");
    dtnff.setDateTime(targetDateTime);

    //
    //left  :  "2013-04-01T00:00:00Z" 'this' filename
    //right :  "2013-03-01T00:00:00Z" 'target' datetime
    //
    dtnff.setDateTimeComparison(DateTimeComparison.LT);
    assertTrue(!dtnff.accept(new File(fileName)));
    assertTrue(!dtnff.accept(new File("pre-" + fileName + "-post")));
    assertTrue(!dtnff.accept(new File(dirName), fileName));
    assertTrue(!dtnff.accept(new File(dirName), "pre-" + fileName + "-post"));


    dtnff.setDateTimeComparison(DateTimeComparison.LTEQ);
    assertTrue(!dtnff.accept(new File(fileName)));
    assertTrue(!dtnff.accept(new File("pre-" + fileName + "-post")));
    assertTrue(!dtnff.accept(new File(dirName), fileName));
    assertTrue(!dtnff.accept(new File(dirName), "pre-" + fileName + "-post"));


    dtnff.setDateTimeComparison(DateTimeComparison.EQ);
    assertTrue(!dtnff.accept(new File(fileName)));
    assertTrue(!dtnff.accept(new File("pre-" + fileName + "-post")));
    assertTrue(!dtnff.accept(new File(dirName), fileName));
    assertTrue(!dtnff.accept(new File(dirName), "pre-" + fileName + "-post"));


    dtnff.setDateTimeComparison(DateTimeComparison.GTEQ);
    assertTrue(dtnff.accept(new File(fileName)));
    assertTrue(dtnff.accept(new File("pre-" + fileName + "-post")));
    assertTrue(dtnff.accept(new File(dirName), fileName));
    assertTrue(dtnff.accept(new File(dirName), "pre-" + fileName + "-post"));


    dtnff.setDateTimeComparison(DateTimeComparison.GT);
    assertTrue(dtnff.accept(new File(fileName)));
    assertTrue(dtnff.accept(new File("pre-" + fileName + "-post")));
    assertTrue(dtnff.accept(new File(dirName), fileName));
    assertTrue(dtnff.accept(new File(dirName), "pre-" + fileName + "-post"));
    
  }

  @Test
  public void testListSubdirs()
    throws IOException
  {
    DateTime dtToday = DateTimeFields.DAY.roundFloor(new DateTime());
    
    File rootDir = new File(System.getProperty("java.io.tmpdir")
                            + getClass().getSimpleName());
    
    
    for (int i = 0; i < 4; i++) {
      FileUtils.forceMkdir(new File(rootDir, JodaTime.format(dtToday.plus(Days.days(i).toStandardDuration()))));
      FileUtils.forceMkdir(new File(rootDir, JodaTime.format(dtToday.minus(Days.days(i).toStandardDuration()))));
    }
    
    assertEquals(3, countSubdirs(rootDir, dtToday, DateTimeComparison.LT));
    assertEquals(4, countSubdirs(rootDir, dtToday, DateTimeComparison.LTEQ));
    assertEquals(1, countSubdirs(rootDir, dtToday, DateTimeComparison.EQ));
    assertEquals(4, countSubdirs(rootDir, dtToday, DateTimeComparison.GTEQ));
    assertEquals(3, countSubdirs(rootDir, dtToday, DateTimeComparison.GT));


    assertEquals(4, countSubdirs(rootDir, dtToday.plus(Hours.ONE), DateTimeComparison.LT));
    assertEquals(4, countSubdirs(rootDir, dtToday.plus(Hours.ONE), DateTimeComparison.LTEQ));
    assertEquals(0, countSubdirs(rootDir, dtToday.plus(Hours.ONE), DateTimeComparison.EQ));
    assertEquals(3, countSubdirs(rootDir, dtToday.plus(Hours.ONE), DateTimeComparison.GTEQ));
    assertEquals(3, countSubdirs(rootDir, dtToday.plus(Hours.ONE), DateTimeComparison.GT));


    assertEquals(3, countSubdirs(rootDir, dtToday.minus(Hours.ONE), DateTimeComparison.LT));
    assertEquals(3, countSubdirs(rootDir, dtToday.minus(Hours.ONE), DateTimeComparison.LTEQ));
    assertEquals(0, countSubdirs(rootDir, dtToday.minus(Hours.ONE), DateTimeComparison.EQ));
    assertEquals(4, countSubdirs(rootDir, dtToday.minus(Hours.ONE), DateTimeComparison.GTEQ));
    assertEquals(4, countSubdirs(rootDir, dtToday.minus(Hours.ONE), DateTimeComparison.GT));
    


    DateTime dtFuture = dtToday.plus(Days.days(10));
    File[] datedDirs = DateTimeNamedFileFilter.listSubdirectories(rootDir, dtFuture, DateTimeComparison.LT);
    assertEquals(7, datedDirs.length);
    

    for (File datedDir : datedDirs) {
      Files.zipDirectoryAndDelete(datedDir);
    }

    assertEquals(7, countFiles(rootDir, dtFuture, DateTimeComparison.LT));
    assertEquals(7, countFiles(rootDir, dtFuture, DateTimeComparison.LT, null, ".zip"));
    assertEquals(0, countFiles(rootDir, dtFuture, DateTimeComparison.LT, "somePrefix", ".zip"));
    assertEquals(0, countFiles(rootDir, dtFuture, DateTimeComparison.LT, "somePrefix", null));
    
    FileUtils.forceDeleteOnExit(rootDir);
  }

  
  private int countSubdirs(File rootDir, DateTime dt, DateTimeComparison dtc) {
    File[] files = DateTimeNamedFileFilter.listSubdirectories(rootDir, dt, dtc);
    return files.length;
  }
  private int countFiles(File rootDir, DateTime dt, DateTimeComparison dtc, String prefix, String suffix)
  {
    File[] files = DateTimeNamedFileFilter.listFiles(rootDir, dt, dtc, prefix, suffix);
    return files.length;
  }
  private int countFiles(File rootDir, DateTime dt, DateTimeComparison dtc)
  {
    return countFiles(rootDir, dt, dtc, null, null);
  }
  




}

