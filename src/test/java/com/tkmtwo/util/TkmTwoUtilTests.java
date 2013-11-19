package com.tkmtwo.util;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{
  /*
  com.tkmtwo.util.charparse.CharParserTest.class,
    com.tkmtwo.util.idgen.IdGeneratorTest.class,
    com.tkmtwo.util.interpolate.InterpolatorTest.class,
    com.tkmtwo.util.java.io.FilesTest.class,
    com.tkmtwo.util.java.io.PatternFileFilterTest.class,
    com.tkmtwo.util.java.lang.StringsTest.class,
  */
    
    
  com.tkmtwo.util.io.DateTimeDirectoryArchiverTest.class,
      //com.tkmtwo.util.java.net.URIsTest.class,
      //com.tkmtwo.util.java.net.UserInfoTest.class,
    
      /*
    com.tkmtwo.util.java.util.ListsTest.class,
    
    com.tkmtwo.util.java.util.regex.MatchersTest.class,
    com.tkmtwo.util.java.util.regex.MatchMethodTest.class,
    com.tkmtwo.util.java.util.regex.PatternsTest.class,
      */
    com.tkmtwo.util.org.apache.commons.io.filefilter.DateTimeNamedFileFilterTest.class,
      
    com.tkmtwo.util.org.dom4j.Dom4JUtilTest.class,
      
    com.tkmtwo.util.org.joda.time.DateTimeComparisonTest.class,
    com.tkmtwo.util.org.joda.time.DateTimeFieldsTest.class,
    com.tkmtwo.util.org.joda.time.DateTimeInterpolatorCallbackTest.class,
    com.tkmtwo.util.org.joda.time.ISODateTimeNoMillisConverterTest.class,
    com.tkmtwo.util.org.joda.time.ISODateTimeNoMillisIntervalConverterTest.class,
    com.tkmtwo.util.org.joda.time.DurationConverterTest.class,
    com.tkmtwo.util.org.joda.time.ISOPeriodConverterTest.class,
    com.tkmtwo.util.org.joda.time.IntervalIteratorTest.class,
    com.tkmtwo.util.org.joda.time.JodaTimeTest.class,
    com.tkmtwo.util.org.joda.time.StringToDurationTest.class,
    com.tkmtwo.util.org.joda.time.StringToPeriodTest.class
    
    
    

    }
)
public class TkmTwoUtilTests {
  
}
