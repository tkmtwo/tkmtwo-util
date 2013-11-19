package com.tkmtwo.util.org.joda.time;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


import static com.tkmtwo.util.java.lang.Strings.checkNotBlank;


public final class ISODateTimeNoMillisConverter
  implements Converter<String, DateTime>
{
  
  private static final Logger logger = LoggerFactory.getLogger(ISODateTimeNoMillisConverter.class);
  private DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();


  //Expects PyYmMwWdDThHmMsS
  public DateTime convert(String s)
  {
    checkNotBlank(s, "Can not convert DateTime from blank.");
    return dateTimeFormatter.parseDateTime(s);
  }
}
