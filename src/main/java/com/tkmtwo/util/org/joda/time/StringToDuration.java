package com.tkmtwo.util.org.joda.time;


import org.apache.commons.lang.StringUtils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import org.joda.time.Duration;

public final class StringToDuration implements Converter<String, Duration> {

  private static final ExpressionParser parser = new SpelExpressionParser();


  public Duration convert(String s) {
    if (StringUtils.isBlank(s)) { return new Duration(0L); }
    
    Duration d = null;
    Long l = convertFromLong(s);
    
    if (l != null) {
      d = new Duration(l.longValue());
    } else {
      d = parser.parseExpression(s).getValue(Duration.class);
    }
    return d;

    //long l = parser.parseExpression(s).getValue(Long.class);
    //Duration d = new Duration(l);
    //Duration d = parser.parseExpression(s).getValue(Duration.class);
    //return d;
  }
  
  private Long convertFromLong(String s) {
    Long l = null;
    try {
      l = parser.parseExpression(s).getValue(Long.class);
    } catch (Exception ex) {
      l = null;
    }
    return l;
  }
  
}
