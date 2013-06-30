package com.tkmtwo.util.org.joda.time;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import org.joda.time.Duration;

import com.tkmtwo.util.java.lang.Strings;


public final class DurationConverter
  implements Converter<String, Duration>
{
  private static final Logger logger = LoggerFactory.getLogger(DurationConverter.class);
  private static final ExpressionParser parser = new SpelExpressionParser();
  
  
  public Duration convert(String s)
  {
    if (Strings.isBlank(s)) {
      throw new IllegalArgumentException("String is blank.");
    }

    Duration d = null;

    if ((d = convertFromLong(s)) != null) {
      return d;
    }
    
    if ((d = convertFromDuration(s)) != null) {
      return d;
    }
    
    throw new IllegalArgumentException("Could not convert '" + s + "' to a Duration.");
  }
  
  private static Duration convertFromLong(String s) {
    Duration d = null;
    Long l = null;
    
    try {
      l = parser.parseExpression(s).getValue(Long.class);
      d = new Duration(l.longValue());
    } catch (Exception ex) {
      logger.debug("String {} is not a long: {}.", s, ex.getMessage());
      l = null;
      d = null;
    }
    
    return d;
  }
  
  private static Duration convertFromDuration(String s) {
    Duration d = null;
    
    try {
      d = parser.parseExpression(s).getValue(Duration.class);
    } catch (Exception ex) {
      logger.debug("String {} is not a valid Duration: {}.", s, ex.getMessage());
      d = null;
    }
    return d;
  }
  
}
