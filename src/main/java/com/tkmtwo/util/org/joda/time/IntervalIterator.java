package com.tkmtwo.util.org.joda.time;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.time.DateTime;
import org.joda.time.ReadableDuration;
//import org.joda.time.ReadableInterval;
//import org.joda.time.ReadableInstant;


public abstract class IntervalIterator
  implements Iterator<DateTime>
{
  
  private DateTime current;
  private DateTime end;
  
  protected ReadableDuration duration;
  
  protected boolean includesEnd = true;
  protected boolean endReached = false;
  
  
  protected DateTime getCurrent() {
    return current;
  }
  protected void setCurrent(DateTime dt) {
    current = dt;
  }
  protected DateTime getEnd() {
    return end;
  }
  protected void setEnd(DateTime dt) {
    end = dt;
  }
  protected ReadableDuration getDuration() {
    return duration;
  }
  protected void setDuration(ReadableDuration rd) {
    duration = rd;
  }
  

  
  
  
  protected boolean includesEnd()
  {
    return includesEnd;
  }
  protected void setIncludesEnd(boolean b) {
    includesEnd = b;
  }
  
  protected boolean endReached()
  {
    return endReached;
  }
  protected void setEndReached(boolean b) {
    endReached = b;
  }
  
  public boolean hasNext()
  {
    return getCurrent() != null;
  }
  
  public DateTime next()
  {
    if (getCurrent() == null) {
      throw new NoSuchElementException();
    }
    
    DateTime retDateTime = getCurrent();
    
    if (endReached()) {
      setCurrent(null);
      return retDateTime;
    }
    
    advance();
    
    return retDateTime;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
  
  protected abstract void advance();
  
}
