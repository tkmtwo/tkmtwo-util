package com.tkmtwo.util.java.lang;

import java.util.EnumSet;
import com.google.common.base.Objects;



public class Enums
{
  
  public static <T extends Enum<T>> T valueOf(Class<T> c, String s)
  {

    for (T t : EnumSet.allOf(c)) {
      if (Objects.equal(t.toString(), s)) {
        return t;
      }
    }

    return null;
  }
  
}

