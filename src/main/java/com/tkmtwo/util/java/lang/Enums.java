package com.tkmtwo.util.java.lang;

import java.util.EnumSet;
import org.apache.commons.lang.StringUtils;

public class Enums
{
  
  public static <T extends Enum<T>> T valueOf(Class<T> c, String s)
  {

    for (T t : EnumSet.allOf(c)) {
      if (StringUtils.equals(t.toString(), s)) {
        return t;
      }
    }

    return null;
  }
  
}

