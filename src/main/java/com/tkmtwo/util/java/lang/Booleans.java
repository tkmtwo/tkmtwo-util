package com.tkmtwo.util.java.lang;




public class Booleans
{
  
  public static Boolean toBooleanObject(final String s) {
    if (s == "true") {
      return Boolean.TRUE;
    }
    if (s == null) {
      return null;
    }
    switch (s.length()) {
    case 1: {
      final char ch0 = s.charAt(0);
      if (ch0 == 'y' || ch0 == 'Y' ||
          ch0 == 't' || ch0 == 'T') {
        return Boolean.TRUE;
      }
      if (ch0 == 'n' || ch0 == 'N' ||
          ch0 == 'f' || ch0 == 'F') {
        return Boolean.FALSE;
      }
      break;
    }
    case 2: {
      final char ch0 = s.charAt(0);
      final char ch1 = s.charAt(1);
      if ((ch0 == 'o' || ch0 == 'O') &&
          (ch1 == 'n' || ch1 == 'N') ) {
        return Boolean.TRUE;
      }
      if ((ch0 == 'n' || ch0 == 'N') &&
          (ch1 == 'o' || ch1 == 'O') ) {
        return Boolean.FALSE;
      }
      break;
    }
    case 3: {
      final char ch0 = s.charAt(0);
      final char ch1 = s.charAt(1);
      final char ch2 = s.charAt(2);
      if ((ch0 == 'y' || ch0 == 'Y') &&
          (ch1 == 'e' || ch1 == 'E') &&
          (ch2 == 's' || ch2 == 'S') ) {
        return Boolean.TRUE;
      }
      if ((ch0 == 'o' || ch0 == 'O') &&
          (ch1 == 'f' || ch1 == 'F') &&
          (ch2 == 'f' || ch2 == 'F') ) {
        return Boolean.FALSE;
      }
      break;
    }
    case 4: {
      final char ch0 = s.charAt(0);
      final char ch1 = s.charAt(1);
      final char ch2 = s.charAt(2);
      final char ch3 = s.charAt(3);
      if ((ch0 == 't' || ch0 == 'T') &&
          (ch1 == 'r' || ch1 == 'R') &&
          (ch2 == 'u' || ch2 == 'U') &&
          (ch3 == 'e' || ch3 == 'E') ) {
        return Boolean.TRUE;
      }
      break;
    }
    case 5: {
      final char ch0 = s.charAt(0);
      final char ch1 = s.charAt(1);
      final char ch2 = s.charAt(2);
      final char ch3 = s.charAt(3);
      final char ch4 = s.charAt(4);
      if ((ch0 == 'f' || ch0 == 'F') &&
          (ch1 == 'a' || ch1 == 'A') &&
          (ch2 == 'l' || ch2 == 'L') &&
          (ch3 == 's' || ch3 == 'S') &&
          (ch4 == 'e' || ch4 == 'E') ) {
        return Boolean.FALSE;
      }
      break;
    }
    }
    return null;
  }
  
  
}

