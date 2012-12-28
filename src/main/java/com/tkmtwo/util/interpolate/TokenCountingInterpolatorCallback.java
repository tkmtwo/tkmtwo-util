package com.tkmtwo.util.interpolate;

public final class TokenCountingInterpolatorCallback
    extends AbstractInterpolatorCallback
{


  /** The running count of tokens seen. */
  private int tokenCount = 0;
  
  
  
  /**
   * Gets the current count of tokens seen during processing.
   *
   * @return an <code>int</code> value
   */
  public int getCount() {
    return this.tokenCount;
  }
  
  
  
  /**
   * @see InterpolatorCallback#replace(String, int)
   * @param token {@inheritDoc}
   * @param tokenNumber {@inheritDoc}
   */
  public void replace(final String token,
                      final int tokenNumber,
                      final String intStart,
                      final String intStop) {
    append(intStart + token + intStop);
    tokenCount++;
  }
}

