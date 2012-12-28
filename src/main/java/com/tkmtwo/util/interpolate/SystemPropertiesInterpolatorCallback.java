package com.tkmtwo.util.interpolate;


import java.util.Map;
import java.util.HashMap;




public final class SystemPropertiesInterpolatorCallback extends AbstractInterpolatorCallback
{



  /**
   * Creates a new <code>SystemPropertiesInterpolatorCallback</code> instance.
   *
   * Initializes a new <code>SystemPropertiesInterpolatorCallback</code> backed by
   * a <code>HashMap<String, String></code>.
   *
   */
  public SystemPropertiesInterpolatorCallback() {
    ;
  }





  /**
   * @see InterpolatorCallback#replace(String, int)
   * @param token {@inheritDoc}
   * @param tokenNumber {@inheritDoc}
   */
  public void replace(final String token,
                      final int tokenNumber,
                      final String intStart,
                      final String intStop)
  {
    String pv = System.getProperty(token);
    if (pv != null) {
      append(pv);
    } else {
      append(intStart + token + intStop);
    }
  }

  
  
}
