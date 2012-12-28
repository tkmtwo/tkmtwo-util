package com.tkmtwo.util.org.joda.time;



import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;


public final class ISOPeriodConverter
  implements Converter<String, Period>
{

  private static final Logger logger = LoggerFactory.getLogger(DurationConverter.class);
  private static final ExpressionParser parser = new SpelExpressionParser();

  //Expects PyYmMwWdDThHmMsS
  public Period convert(String s)
  {
    return ISOPeriodFormat.standard().parsePeriod(s);
  }
}
