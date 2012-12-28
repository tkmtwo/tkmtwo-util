package com.tkmtwo.util.org.joda.time;


import org.joda.time.DateTime;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInterval;



public class ReverseIntervalIterator extends IntervalIterator {

  public ReverseIntervalIterator(ReadableInterval ri,
                                 ReadableDuration rd,
                                 boolean ie)
  {
	  setCurrent(ri.getEnd());
	  setEnd(ri.getStart());
	  setDuration(rd);
	  setIncludesEnd(ie);
  }
  
  
  protected void advance()
  {
	  DateTime dt = new DateTime(getCurrent()).minus(duration);
	  
	  if (dt.isAfter(getEnd())) {
		  setCurrent(dt);
	  } else if (dt.isEqual(getEnd())) {
		  setCurrent(dt);
		  setEndReached(true);
	  } else if (includesEnd() && dt.isBefore(getEnd())) {
		  setCurrent(getEnd());
		  setEndReached(true);
	  } else {
		  setCurrent(null);
	  }
	  
  }
  
}

