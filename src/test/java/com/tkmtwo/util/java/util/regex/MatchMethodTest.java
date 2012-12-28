package com.tkmtwo.util.java.util.regex;





import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;



public class MatchMethodTest {


  
  
  @Test
  public void testMatches() {
    assertTrue(MatchMethod.MATCHES.matches("\\w+", "someplainchars"));
    assertTrue(MatchMethod.MATCHES.matches("\\D+\\d+\\D+\\d+\\D+\\d+",
                                           "one1two2three3"));
  }

  @Test
  public void testLookingAt() {
    assertTrue(MatchMethod.LOOKINGAT.matches("\\D+",
                                             "someplainchars"));
    assertTrue(MatchMethod.LOOKINGAT.matches("\\D+",
                                             "someplain73chars"));
    assertTrue(MatchMethod.LOOKINGAT.matches("\\D+",
                                             "someplainchars73"));
    assertTrue(!MatchMethod.LOOKINGAT.matches("\\D+",
                                              "3someplainchars"));
  }
  
  @Test
  public void testFind() {
    assertTrue(MatchMethod.FIND.matches("\\D+",
                                        "someplainchars"));
    assertTrue(!MatchMethod.FIND.matches("\\d+",
                                         "someplainchars"));

    assertTrue(MatchMethod.FIND.matches("\\D+",
                                        "someplain73chars"));
    assertTrue(MatchMethod.FIND.matches("\\d+",
                                        "someplain73chars"));
  }

    
  @Test
  public void testMultiMatchersEmpty() {
    List<Matcher> matchers = null;
    assertNull(MatchMethod.MATCHES.matches(matchers));
    assertNull(MatchMethod.LOOKINGAT.matches(matchers));
    assertNull(MatchMethod.FIND.matches(matchers));

    matchers = new ArrayList<Matcher>();
    assertNull(MatchMethod.MATCHES.matches(matchers));
    assertNull(MatchMethod.LOOKINGAT.matches(matchers));
    assertNull(MatchMethod.FIND.matches(matchers));
  }

  @Test
  public void testMultiMatches() {
    List<String> regexes = new ArrayList<String>();
    regexes.add("\\d+");
    regexes.add("\\D+");
    regexes.add("\\w+");

    Matcher matcher = null;
    matcher =
      MatchMethod
      .MATCHES
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "one1two2three3"));
    assertNotNull(matcher);
    assertEquals("\\w+",
                 matcher.pattern().pattern());

    matcher = 
      MatchMethod
      .MATCHES
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "one1!two2three3"));
    assertNull(matcher);

  }



  @Test
  public void testMultiLookingAt() {
    List<String> regexes = new ArrayList<String>();
    regexes.add("\\d+");
    regexes.add("\\D+");
    regexes.add("\\w+");

    Matcher matcher = null;
    matcher =
      MatchMethod
      .LOOKINGAT
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "one1two2three3"));
    assertNotNull(matcher);
    assertEquals("\\D+",
                 matcher.pattern().pattern());

    matcher =
      MatchMethod
      .LOOKINGAT
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "1one2two3three"));
    assertNotNull(matcher);
    assertEquals("\\d+",
                 matcher.pattern().pattern());

    matcher =
      MatchMethod
      .LOOKINGAT
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "_1one2two3three"));
    assertNotNull(matcher);
    assertEquals("\\D+",
                 matcher.pattern().pattern());


  }
  



  @Test
  public void testMultiFind() {
    List<String> regexes = new ArrayList<String>();
    regexes.add("\\d+");
    regexes.add("\\D+");
    regexes.add("\\w+");

    Matcher matcher = null;
    matcher =
      MatchMethod
      .FIND
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "123"));
    assertNotNull(matcher);
    assertEquals("\\d+",
                 matcher.pattern().pattern());


    matcher =
      MatchMethod
      .FIND
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "one1two2three3"));
    assertNotNull(matcher);
    assertEquals("\\d+",
                 matcher.pattern().pattern());


    matcher =
      MatchMethod
      .FIND
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "onetwothree"));
    assertNotNull(matcher);
    assertEquals("\\D+",
                 matcher.pattern().pattern());


    matcher =
      MatchMethod
      .FIND
      .matches(Matchers.matchersFromRegexes(regexes,
                                            "!&$"));
    assertNotNull(matcher);
    assertEquals("\\D+",
                 matcher.pattern().pattern());

  }
  
  
  
  
}
