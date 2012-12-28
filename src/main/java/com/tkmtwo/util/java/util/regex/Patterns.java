package com.tkmtwo.util.java.util.regex;


import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class Patterns {
  public static final List<Pattern> EMPTY_PATTERNS = Collections.emptyList();
  
  
  
  public static List<Pattern> patternsFromRegexes(final List<String> regexes) {
    return patternsFromRegexes(regexes, 0);
  }
  
  public static List<Pattern> patternsFromRegexes(final List<String> regexes,
                                                  final int flags)
    throws PatternSyntaxException {
    
    if (regexes == null || regexes.isEmpty()) {
      return EMPTY_PATTERNS;
    }
    
    List<Pattern> patterns = new ArrayList<Pattern>(regexes.size());
    for (String regex : regexes) {
      patterns.add(Pattern.compile(regex, flags));
    }
    return patterns;
  }
  
  
  
}

