package com.tkmtwo.util.java.util.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class MatchersTest
{
  
  
  @Test
  public void testNullFromRegexes() {
    List<String> regexes = null;
    List<Matcher> matchers = Matchers.matchersFromRegexes(regexes, "somestring");
    assertEquals(0, matchers.size());
  }
  
  
  @Test
  public void testEmptyFromRegexes() {
    List<String> regexes = new ArrayList<String>();
    List<Matcher> matchers = Matchers.matchersFromRegexes(regexes, "somestring");
    assertEquals(0, matchers.size());
  }
  
  
  @Test
  public void testNullFromPatterns() {
    List<Pattern> patterns = null;
    List<Matcher> matchers = Matchers.matchersFromPatterns(patterns, "somestring");
    assertEquals(0, matchers.size());
  }
  
  @Test
  public void testEmptyFromPatterns() {
    List<Pattern> patterns = new ArrayList<Pattern>();
    List<Matcher> matchers = Matchers.matchersFromPatterns(patterns, "somestring");
    assertEquals(0, matchers.size());
  }
  
  
  
  
}

