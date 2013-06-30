package com.tkmtwo.util.org.joda.time;




import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.ISOPeriodFormat;

import com.tkmtwo.util.java.lang.Strings;

public final class StringToPeriod implements Converter<String, Period>
{
  
  private static final PeriodFormatter periodFormatter = ISOPeriodFormat.standard();
  
  
  public Period convert(String s) {
    if (Strings.isBlank(s)) { return new Period(0L); }
    
    Period p = periodFormatter.parsePeriod(s);
    return p;
    
  }
  
}
