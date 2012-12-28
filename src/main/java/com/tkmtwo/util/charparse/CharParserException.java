package com.tkmtwo.util.charparse;


public class CharParserException
  extends RuntimeException
{
  
  public CharParserException()
  {
  }
  
  public CharParserException(String s)
  {
    super(s);
  }
  
  public CharParserException(String s, Throwable t)
  {
    super(s, t);
  }
  
  public CharParserException(Throwable t)
  {
    super(t);
  }
}
