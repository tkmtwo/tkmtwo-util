package com.tkmtwo.util.org.joda.time;



import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public enum DateTimeComparison
{

  LT {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isBefore(rightDt);
    }
  },
  LTEQ {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isBefore(rightDt) || leftDt.isEqual(rightDt);
    }
  },
  EQ {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isEqual(rightDt);
    }
  },
  GTEQ {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isAfter(rightDt) || leftDt.isEqual(rightDt);
    }
  },
  GT {
    public  boolean compare(DateTime leftDt, DateTime rightDt) {
      if (leftDt == null || rightDt == null) { return false; }
      return leftDt.isAfter(rightDt);
    }
  };
  
  
  public abstract boolean compare(DateTime leftDt, DateTime rightDt);
}
