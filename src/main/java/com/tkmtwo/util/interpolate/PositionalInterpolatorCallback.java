package com.tkmtwo.util.interpolate;



public final class PositionalInterpolatorCallback
    extends AbstractInterpolatorCallback
{
  
  
  
  /** internal array of <code>String</code>s. */
  private String[] strings;
  
  
  
  /**
   * Creates a new <code>PositionalInterpolatorCallback</code> instance.
   *
   * @param ss a <code>String[]</code> value
   */
  public PositionalInterpolatorCallback(final String[] ss) {
    this.strings = ss;
  }
  
  
  
  /**
   * @see InterpolatorCallback#replace(String, int)
   * @param token {@inheritDoc}
   * @param tokenNumber {@inheritDoc}
   */
  @Override
  public void replace(final String token,
                      final int tokenNumber,
                      final String intStart,
                      final String intStop)
  {
    if (tokenNumber < strings.length) {
      append(strings[tokenNumber]);
    } else {
      append(intStart + token + intStop);
    }
  }
  
  
}


