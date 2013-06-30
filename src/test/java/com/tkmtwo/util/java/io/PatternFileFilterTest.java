package com.tkmtwo.util.java.io;

import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.tkmtwo.util.org.joda.time.JodaTime;
import com.tkmtwo.util.java.lang.Strings;
import com.tkmtwo.util.java.util.regex.MatchMethod;


public class PatternFileFilterTest
{

  @Test
  public void testJodaBasicDateTimeNoMillisFileName()
  {
    PatternFileFilter pff = new PatternFileFilter(MatchMethod.FIND,
                                                  JodaTime.BASIC_DATETIME_NOMILLIS_SPEC);
    String[] ssPass = new String[] {
      "20120101T000000Z",
      "prefix-20120101T000000Z",
      "20120101T000000Z-suffix",
      "prefix-20120101T000000Z-suffix"
    };
    String[] ssFail = new String[] {
      null,
      "",
      "20120101000000",
      "nodateincluded"
    };
    
    
    for (String s : ssPass) {
      //File f = new File(s);
      assertTrue("String " + Strings.sqit(s) + " should match.", pff.acceptFileName(s));
    }
    for (String s : ssFail) {
      //File f = new File(s);
      assertFalse("String " + Strings.sqit(s) + " should not match.", pff.acceptFileName(s));
    }
    
    
    
  }
  


  @Test
  public void testJodaBasicDateTimeNoMillisFileNamePartial()
  {
    PatternFileFilter pff = new PatternFileFilter(MatchMethod.FIND,
                                                  "prefix-" + JodaTime.BASIC_DATETIME_NOMILLIS_SPEC + "-suffix");
    String[] ssPass = new String[] {
      "prefix-20120101T000000Z-suffix",
      "xyzprefix-20120101T000000Z-suffix",
      "prefix-20120101T000000Z-suffixxxx"
    };
    String[] ssFail = new String[] {
      null,
      "",
      "20120101000000",
      "nodateincluded"
    };
    
    
    for (String s : ssPass) {
      //File f = new File(s);
      assertTrue("String " + Strings.sqit(s) + " should match.", pff.acceptFileName(s));
    }
    for (String s : ssFail) {
      //File f = new File(s);
      assertFalse("String " + Strings.sqit(s) + " should not match.", pff.acceptFileName(s));
    }
    
    
    
  }
  
  
  
}

