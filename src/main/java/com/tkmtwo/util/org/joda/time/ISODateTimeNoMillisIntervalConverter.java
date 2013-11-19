package com.tkmtwo.util.org.joda.time;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


import static com.tkmtwo.util.java.lang.Strings.checkNotBlank;


public final class ISODateTimeNoMillisIntervalConverter
  implements Converter<String, Interval>
{
  
  private static final Logger logger = LoggerFactory.getLogger(ISODateTimeNoMillisIntervalConverter.class);
  private DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();


  //Expects Start/End in DateTimeNoMillis
  public Interval convert(String s)
  {
    DateTime intervalStart = null;
    DateTime intervalEnd = null;

    checkNotBlank(s, "Can not convert interval from blank.");
    String[] ss = s.split("/");
    if (ss.length != 2) {
      throw new IllegalArgumentException("Expected StartDateTimeNoMillis/EndDateTimeNoMillis.");
    }
    
    try {
      intervalStart = dateTimeFormatter.parseDateTime(ss[0]);
    } catch (Exception ex) {
      throw new IllegalArgumentException("Start date "
                                         + ss[0]
                                         + " is not an ISODateTimeNoMillis value.");
    }
    try {
      intervalEnd = dateTimeFormatter.parseDateTime(ss[1]);
    } catch (Exception ex) {
      throw new IllegalArgumentException("End date "
                                         + ss[1]
                                         + " is not an ISODateTimeNoMillis value.");
    }
    return new Interval(intervalStart, intervalEnd);
  }
  
}
