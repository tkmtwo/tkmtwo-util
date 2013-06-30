package com.tkmtwo.util.java.lang;

import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class Strings
{
  
  public static String tickit(final String s) {
    return "`" + s + "'";
  }
  
  public static String sqit(final String s) {
    return "'" + s + "'";
  }
  
  public static String dqit(final String s) {
    return "\"" + s + "\"";
  }
  
  public static String parit(final String s) {
    return "(" + s + ")";
  }
  
  public static String curlit(final String s) {
    return "{" + s + "}";
  }
  
  public static String squareit(final String s) {
    return "[" + s + "]";
  }
  
  public static boolean isNotBlank(final String s) {
    return ! isBlank(s);
  }

  public static boolean isBlank(final String s) {
    return s == null || s.length() == 0 || s.trim().length() == 0;
  }

  public static String checkNotBlank(final String s)
  {
    return checkNotBlank(s, "Value can not be blank.");
  }
  public static String checkNotBlank(final String s, final String msg) {
    if (isBlank(s)) {
      throw new IllegalArgumentException(msg);
    }
    return s;
  }

  /*
  public void nothing()
  {
    String myString = "foo[]bar";
    myString = myString.replaceAll(Pattern.quote("foo[]"), Matcher.quoteReplacement("bar$"));
    System.out.println(myString);
    // output bar$bar
  }
  */

  public static String replaceAll(String source, String find, String replace)
  {
    if (find == null) { return source; }
    if (source == null) { return null; }

    String findLiteral = Pattern.quote(find);
    String replaceLiteral = Matcher.quoteReplacement(replace);
    
    Pattern p = Pattern.compile(findLiteral);
    Matcher m = p.matcher(source);
    return m.replaceAll(replaceLiteral);
  }

  public static String replaceAllInsensitive(String source, String find, String replace)
  {
    if (find == null) { return source; }
    if (source == null) { return null; }

    String findLiteral = Pattern.quote(find);
    String replaceLiteral = Matcher.quoteReplacement(replace);
    
    Pattern p = Pattern.compile(findLiteral, Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(source);
    return m.replaceAll(replaceLiteral);
  }
  
}

