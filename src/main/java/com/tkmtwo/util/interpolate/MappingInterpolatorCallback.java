package com.tkmtwo.util.interpolate;


import java.util.Map;
import java.util.HashMap;




public final class MappingInterpolatorCallback extends AbstractInterpolatorCallback
{

  /** internal map of token/values. */
  private Map<String, String> map;



  /**
   * Creates a new <code>MappingInterpolatorCallback</code> instance.
   *
   * Initializes a new <code>MappingInterpolatorCallback</code> backed by
   * a <code>HashMap<String, String></code>.
   *
   */
  public MappingInterpolatorCallback() {
    map = new HashMap<String, String>();
  }



  /**
   * Creates a new <code>MappingInterpolatorCallback</code> instance.
   *
   * Initializes a new <code>MappingInterpolatorCallback</code> backed by
   * a <code>Map</code> of your choice.
   *
   * @param m a <code>Map</code> of token/values
   */
  public MappingInterpolatorCallback(final Map<String, String> m) {
    this.map = m;
  }



  /**
   * Puts another token/value pair into the map.
   *
   * @param token a <code>String</code> value
   * @param value a <code>String</code> value
   */
  public void put(final String token,
                  final String value) {
    map.put(token, value);
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
    if (map.containsKey(token)) {
      append(map.get(token));
    } else {
      append(intStart + token + intStop);
    }
  }
  
  
}
