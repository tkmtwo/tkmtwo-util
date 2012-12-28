package com.tkmtwo.util.interpolate;

public interface InterpolatorCallback
{
  
  /**
   * Appends to the string.
   *
   * @param s a <code>String</code> value
   */
  void append(String s);
  
  
  
  /**
   * Replaces a portion of the string.
   *
   * @param token a <code>String</code> value of the token
   * @param tokenNumber an <code>int</code> value for the token number
   */
  void replace(String token, int tokenNumber, String intStart, String intStop);
  
  
  
  /**
   * Gets the resultant string.
   *
   * Normally called after all token processing is complete.
   *
   * @return a <code>String</code> value
   */
  String getResult();
  void reset();
}
