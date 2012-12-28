package com.tkmtwo.util.interpolate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class AbstractInterpolatorCallback
  implements InterpolatorCallback
{

  /** common logger. */
  protected final Log logger = LogFactory.getLog(getClass());

  /** dynamic string. */
  private StringBuffer stringBuffer = new StringBuffer();


  /**
   * @see InterpolatorCallback#append(String)
   * @param s {@inheritDoc}
   */
  public void append(final String s) {
    stringBuffer.append(s);
  }



  /**
   * @see InterpolatorCallback#replace(String, int)
   * @param token {@inheritDoc}
   * @param tokenNumber {@inheritDoc}
   */
  public abstract void replace(String token, int tokenNumber, String intStart, String intStop);

  /**
   * @see InterpolatorCallback#getResult()
   * @return {@inheritDoc}
   */
  public String getResult() {
    return stringBuffer.toString();
  }

  public void reset()
  {
    stringBuffer.setLength(0);
  }



}
