package com.tkmtwo.util.interpolate;



import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;



public class InterpolatorTest
{

  protected String inPlain;
  protected String inStart;
  protected String inEnd;
  protected String inPadded;
  protected String inNoReplacements;
  
  protected String outPlain;
  protected String outStart;
  protected String outEnd;
  protected String outPadded;
  protected String outNoReplacements;
  
  
  
  @Before
  public void setUp() {
    inPlain = "${name}${age}";
    inStart = "${name} is my name and ${age} is my age";
    inEnd = "my name is ${name} and my age is ${age}";
    inPadded = "my name is ${name} and i am ${age} years old";
    inNoReplacements = "I have not tokens for ${this}, ${that}, ${theother} to replace";
    
    outPlain = "john73";
    outStart = "john is my name and 73 is my age";
    outEnd = "my name is john and my age is 73";
    outPadded = "my name is john and i am 73 years old";
    outNoReplacements = "I have not tokens for ${this}, ${that}, ${theother} to replace";
  }
  
  
  @Test
  public void testTokenCount() {
    
    assertEquals(2, Interpolator.getTokenCount(inPlain));
    assertEquals(2, Interpolator.getTokenCount(inStart));
    assertEquals(2, Interpolator.getTokenCount(inEnd));
    assertEquals(2, Interpolator.getTokenCount(inPadded));
    assertEquals(3, Interpolator.getTokenCount(inNoReplacements));
    
  }
  
  
  
  
  @Test
  public void testMapping() {
    Map<String,String> m = new HashMap<String,String>();
    m.put("name", "john");
    m.put("age", "73");
    
    assertEquals(outPlain,
        Interpolator.interpolate(inPlain, m));
    assertEquals(outStart,
        Interpolator.interpolate(inStart, m));
    assertEquals(outEnd,
        Interpolator.interpolate(inEnd, m));
    assertEquals(outPadded,
        Interpolator.interpolate(inPadded, m));
    assertEquals(outNoReplacements,
        Interpolator.interpolate(inNoReplacements, m));
    
    
  }
  
  
  
  
  @Test
  public void testPositional()
  {
    String[] ss = new String[]{"john", "73"};
    
    assertEquals(outPlain,
        Interpolator.interpolate(inPlain, ss));
    assertEquals(outStart,
        Interpolator.interpolate(inStart, ss));
    assertEquals(outEnd,
        Interpolator.interpolate(inEnd, ss));
    assertEquals(outPadded,
        Interpolator.interpolate(inPadded, ss));
    

    assertEquals("I have not tokens for john, 73, ${theother} to replace",
                 Interpolator.interpolate(inNoReplacements, ss));
    
    
  }


  @Test
  public void testSystemProperties()
  {
    String spIn = "My encoding is always '${file.encoding}' and my TZ is always '${user.timezone}'";
    String spOut = "My encoding is always 'UTF-8' and my TZ is always 'UTC'";

    assertEquals(spOut,
                 Interpolator.interpolate(spIn));
  }
  
  
}
