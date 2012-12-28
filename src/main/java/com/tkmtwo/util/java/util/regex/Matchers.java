package com.tkmtwo.util.java.util.regex;


import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;




/**
 * Utility class for Matcher objects.
 *
 * @author Tom Mahaffey
 * @version $Id$
 */
public class Matchers {
  
  /** An empty List of Matchers. */
  public static final List<Matcher> EMPTY_MATCHERS = Collections.emptyList();
  
  
  
  /**
   * Creates Matchers from String representations of regexes.
   *
   * @param regexes - List of Strings to compile into Patterns.
   * @param charSequence - the CharSequence to match against.
   * @return List<Matcher> - Matcher objects
   */
  public static List<Matcher> matchersFromRegexes(final List<String> regexes,
                                                  final CharSequence charSequence)
    throws PatternSyntaxException {
    List<Pattern> patterns = Patterns.patternsFromRegexes(regexes);
    return matchersFromPatterns(patterns, charSequence);
  }
  
  
  /**
   * Creates Matchers from compiled Patterns.
   *
   * @param patterns - List of Patterns
   * @param charSequence - the CharSequence to match against.
   * @return List<Matcher> - Matcher objects
   */
  public static List<Matcher> matchersFromPatterns(final List<Pattern> patterns,
                                                   final CharSequence charSequence) {
    if (patterns == null || patterns.isEmpty()) {
      return EMPTY_MATCHERS;
    }
    
    List<Matcher> matchers = new ArrayList<Matcher>(patterns.size());
    for (Pattern pattern : patterns) {
      matchers.add(pattern.matcher(charSequence));
    }
    
    return matchers;
  }

  public static String reportGroupMatches(Matcher m)
  {
    return reportGroupMatches(m, System.getProperty("line.separator"));
  }
  public static String reportGroupMatches(Matcher m, String separator)
  {
    if (m == null) { return null; }

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i <= m.groupCount(); i++) {
      sb
        .append(String.valueOf(i) + ": " + m.group(i))
        .append(separator);
    }
    return sb.toString();
  }

    
  
}

