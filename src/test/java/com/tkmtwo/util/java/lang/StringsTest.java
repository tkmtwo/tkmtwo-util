package com.tkmtwo.util.java.lang;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class StringsTest {
	
  /*
	@Test
	public void testInherited()
	{
		String s = Strings.capitalize("tkmtwo");
		assertEquals("Tkmtwo", s);
		
	}
	*/
	
	
	
	@Test
	public void testIts()
	{
		assertEquals("'tkmtwo'", Strings.sqit("tkmtwo"));
		assertEquals("\"tkmtwo\"", Strings.dqit("tkmtwo"));
		assertEquals("`tkmtwo'", Strings.tickit("tkmtwo"));
		
		assertEquals("{tkmtwo}", Strings.curlit("tkmtwo"));
		assertEquals("(tkmtwo)", Strings.parit("tkmtwo"));
		assertEquals("[tkmtwo]", Strings.squareit("tkmtwo"));
		
	}
	

}
