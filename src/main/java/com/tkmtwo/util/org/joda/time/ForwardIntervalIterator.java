package com.tkmtwo.util.org.joda.time;


import org.joda.time.DateTime;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInterval;
//import org.joda.time.ReadableInstant;


public class ForwardIntervalIterator extends IntervalIterator {
  
  
  public ForwardIntervalIterator(ReadableInterval ri,
                                 ReadableDuration rd,
                                 boolean ie) {
    setCurrent(ri.getStart());
    setEnd(ri.getEnd());
    setDuration(rd);
    setIncludesEnd(ie);
  }
  
  
  protected void advance() {
    DateTime dt = new DateTime(getCurrent()).plus(duration);
    
    if (dt.isBefore(getEnd())) {
      setCurrent(dt);
    } else if (dt.isEqual(getEnd())) {
      setCurrent(dt);
      setEndReached(true);
    } else if (includesEnd() && dt.isAfter(getEnd())) {
      setCurrent(getEnd());
      setEndReached(true);
    } else {
      setCurrent(null);
    }
    
  }
  
}




