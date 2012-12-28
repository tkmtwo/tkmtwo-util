package com.tkmtwo.util.java.util.regex;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;



public class PatternsTest {

  private List<String> regexes;
  private String regexOne, regexTwo, regexThree;


  @Before
  public void setUp() {
    regexOne  = ".*";
    regexTwo  = "\\d+";
    regexThree = "\\W+";

    regexes = Arrays.asList(regexOne,
                            regexTwo,
                            regexThree);
  }

  
  @Test
  public void testNull() {
    List<Pattern> patterns = Patterns.patternsFromRegexes(null);
    assertEquals(0, patterns.size());
  }

  @Test
  public void testEmpty() {
    List<String> ls = new ArrayList<String>();
    List<Pattern> patterns = Patterns.patternsFromRegexes(ls);
    assertEquals(0, patterns.size());
  }


  @Test
  public void testPatternsFromRegexesNoFlags() {
    
    List<Pattern> patterns = Patterns.patternsFromRegexes(regexes);
    
    Pattern patternOne = patterns.get(0);
    Pattern patternTwo = patterns.get(1);
    Pattern patternThree = patterns.get(2);


    assertEquals(regexOne, patternOne.pattern());
    assertEquals(0, patternOne.flags());

    assertEquals(regexTwo, patternTwo.pattern());
    assertEquals(0, patternTwo.flags());

    assertEquals(regexThree, patternThree.pattern());
    assertEquals(0, patternThree.flags());
    
    
  }

  @Test
  public void testPatternsFromRegexesWithFlags() {
    
    List<Pattern> patterns = Patterns.patternsFromRegexes(regexes, Pattern.DOTALL);
    
    Pattern patternOne = patterns.get(0);
    Pattern patternTwo = patterns.get(1);
    Pattern patternThree = patterns.get(2);


    assertEquals(regexOne, patternOne.pattern());
    assertEquals(Pattern.DOTALL, patternOne.flags());

    assertEquals(regexTwo, patternTwo.pattern());
    assertEquals(Pattern.DOTALL, patternTwo.flags());

    assertEquals(regexThree, patternThree.pattern());
    assertEquals(Pattern.DOTALL, patternThree.flags());
    
    
  }


  
  
}
