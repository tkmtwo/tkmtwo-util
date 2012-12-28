package com.tkmtwo.util.interpolate;


import java.util.Map;



/**
 * Convenience class to interpolate using <code>InterpolatorCallback</code>s.
 *
 * @author Tom Mahaffey
 * @version $Id$
 */
public final class Interpolator {
  
  /** Default marker to start a token. */
  public static final String INTERP_START = "${";
  /** Default marker to stop a token. */
  public static final String INTERP_STOP  = "}";
  
  
  /**
   * Interpolates a <code>String</code>.
   *
   * Processes input <code>String s</code>, using the supplied
   * <code>InterpolatorCallback ic</code> and default token markers.
   *
   * @param s a <code>String</code> value to process
   * @param ic an <code>InterpolatorCallback</code> value
   * @return a <code>String</code> value result
   */
  public static String interpolate(final String s, final InterpolatorCallback ic) {
    return interpolate(s, ic, INTERP_START, INTERP_STOP);
  }
  
  
  
  /**
   * Interpolates a <code>String</code>.
   *
   * Processes input <code>String s</code>, using the supplied
   * <code>InterpolatorCallback ic</code> and custom token markers.
   *
   *
   * @param s a <code>String</code> value
   * @param ic an <code>InterpolatorCallback</code> value
   * @param intStart a <code>String</code> value
   * @param intStop a <code>String</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(final String s,
                                   final InterpolatorCallback ic,
                                   final String intStart,
                                   final String intStop) {
    
    if ((s == null) || (s.length() < 1)) {
      return s;
    }
    int replCnt = 0;
    int begin = -1;
    int end = -1;
    int prec = 0 - intStop.length();
    //String v = null;
    
    while (((begin = s.indexOf(intStart, prec + intStop.length())) > -1)
        && ((end = s.indexOf(intStop, begin)) > -1)) {
      
      
      ic.append(s.substring(prec + intStop.length(), begin));
      ic.replace(s.substring(begin + intStart.length(), end),
                 replCnt++,
                 intStart, intStop);
      
      prec = end;
    }
    
    ic.append(s.substring(prec + intStop.length(), s.length()));
    String result = ic.getResult();
    ic.reset();
    return result;
  }
  
  
  
  public static String interpolate(final String s) {
    return interpolate(s, INTERP_START, INTERP_STOP);
  }
  public static String interpolate(final String s,
                                   final String intStart,
                                   final String intStop) {
    
    SystemPropertiesInterpolatorCallback spc = new SystemPropertiesInterpolatorCallback();
    return interpolate(s, spc, intStart, intStop);
    
  }

  
  /**
   * Interpolates a <code>String</code>.
   *
   * Processes input <code>String s</code>, using positional token
   * replacements as defined in <code>String[] ss</code> using
   * default token markers.
   *
   * @param s a <code>String</code> value
   * @param ss a <code>String[]</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(final String s,
                                   final String[] ss) {
    
    return interpolate(s, ss, INTERP_START, INTERP_STOP);
    
  }
  
  
  /**
   * Interpolates a <code>String</code>.
   *
   * Processes input <code>String s</code>, using positional token
   * replacements as defined in <code>String[] ss</code> using
   * custom token markers.
   *
   * @param s a <code>String</code> value
   * @param ss a <code>String[]</code> value
   * @param intStart a <code>String</code> value
   * @param intStop a <code>String</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(final String s,
                                   final String[] ss,
                                   final String intStart,
                                   final String intStop) {
    
    PositionalInterpolatorCallback pc = new PositionalInterpolatorCallback(ss);
    return interpolate(s, pc, intStart, intStop);
    
  }
  
  
  
  /**
   * Interpolates a <code>String</code>.
   *
   * Processes input <code>String s</code>, using mapped token
   * replacements as defined in <code>Map m</code> using
   * default token markers.
   *
   * @param s a <code>String</code> value
   * @param m a <code>Map</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(final String s,
                                   final Map<String, String> m) {
    
    return interpolate(s, m, INTERP_START, INTERP_STOP);
    
  }
  
  
  
  /**
   * Interpolates a <code>String</code>.
   *
   * Processes input <code>String s</code>, using mapped token
   * replacements as defined in <code>Map m</code> using
   * custom token markers.
   *
   * @param s a <code>String</code> value
   * @param m a <code>Map</code> value
   * @param intStart a <code>String</code> value
   * @param intStop a <code>String</code> value
   * @return a <code>String</code> value
   */
  public static String interpolate(final String s,
                                   final Map<String, String> m,
                                   final String intStart,
                                   final String intStop) {
    
    MappingInterpolatorCallback mc = new MappingInterpolatorCallback(m);
    return interpolate(s, mc, intStart, intStop);
    
  }
  
  
  
  /**
   * Gets a count of tokens present in <code>String s</code> using
   * default token markers.
   *
   * @param s a <code>String</code> value
   * @return an <code>int</code> value
   */
  public static int getTokenCount(final String s) {
    
    return getTokenCount(s, INTERP_START, INTERP_STOP);
    
  }
  
  
  
  /**
   * Gets a count of tokens present in <code>String s</code> using
   * custom token markers.
   *
   * @param s a <code>String</code> value
   * @param intStart a <code>String</code> value
   * @param intStop a <code>String</code> value
   * @return an <code>int</code> value
   */
  public static int getTokenCount(final String s,
                                  final String intStart,
                                  final String intStop) {
    
    TokenCountingInterpolatorCallback tcc = new TokenCountingInterpolatorCallback();
    interpolate(s, tcc, intStart, intStop);
    return tcc.getCount();
    
  }
  
  
}


