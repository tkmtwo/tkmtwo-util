package com.tkmtwo.util.charparse;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;



public class CharParserTest
{
  private CharParser defParser;
  private CharParser csvParser;
  private CharParser psvParser;
  private CharParser tsvParser;

  @Before
  public void setUp()
  {
    defParser = new CharParser();
    csvParser = new CharParser(',');
    tsvParser = new CharParser('\t');
  }


  private void testDefault(String s, String[] ss)
  {
    List<String> l = defParser.parse(s);
    assertEquals(ss.length, l.size());
    for (int i = 0; i < ss.length; i++) {
      assertEquals(ss[i], l.get(i));
    }
  }

  @Test
  public void testOne()
  {
    testDefault("one,two,three",
                new String[] {"one", "two", "three"});
    testDefault("one,two,three,,",
                new String[] {"one", "two", "three", "", ""});
    testDefault(",five,six",
                new String[] {"", "five", "six"});
  }
  

  
  @Test
  public void testStringDefault()
  {

    List<String> l = defParser.parse("one,two,three");
    assertEquals(3, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));
    assertEquals("", defParser.get(l, -1));
    assertEquals("", defParser.get(l, 73));

    l = defParser.parse("one,two,three,");
    assertEquals(4, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));
    assertEquals("", defParser.get(l, -1));
    assertEquals("", defParser.get(l, 73));
    //assertNull(l.get(3));
  }

  @Test
  public void testStringDefaultEnforcePass()
  {
    CharParser cp = new CharParser();
    cp.setEnforceColumns(3);
    List<String> l = cp.parse("one,two,three");
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));

  }

  @Test(expected = NumberOfColumnsException.class)
  public void testStringDefaultEnforceFail()
  {
    CharParser cp = new CharParser();
    cp.setEnforceColumns(5);
    List<String> l = cp.parse("one,two,three");
  }
    


  @Test
  public void testStringCsv()
  {

    List<String> l = csvParser.parse("one,two,three");
    assertEquals(3, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));

    l = csvParser.parse("\"one\",\"two\",\"three\"");
    assertEquals(3, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));

    l = csvParser.parse("\"one\",\"two\",\"th\"\"ree\"");
    assertEquals(3, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("th\"ree", l.get(2));

    l = csvParser.parse("\"one\",\"two\",\"th,ree\"");
    assertEquals(3, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("th,ree", l.get(2));


  }



  @Test
  public void testStringTsv()
  {

    List<String> l = tsvParser.parse("one\ttwo\tthree");
    assertEquals(3, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));

    l = tsvParser.parse("\"one\"\t\"two\"\t\"three\"");
    assertEquals(3, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));

    l = tsvParser.parse("one\ttwo\tthree\t");
    assertEquals(4, l.size());
    assertEquals("one", l.get(0));
    assertEquals("two", l.get(1));
    assertEquals("three", l.get(2));
    assertEquals("", l.get(3));

  }


  //@Test
  public void testReadCommaPass()
    throws IOException
  {
    CharParser cp = new CharParser();
    //cp.setEnforceColumns(3);
    
    List<List<String>> ls = cp.read("src/test/resources/com/tkmtwo/util/charparse/comma-pass.csv");
    assertEquals(4, ls.size());

    
    List<String> lineOne = ls.get(0);
    assertEquals("one", lineOne.get(0));
    assertEquals("two", lineOne.get(1));
    assertEquals("three", lineOne.get(2));

    List<String> lineTwo = ls.get(1);
    assertEquals("four", lineTwo.get(0));
    assertEquals("five", lineTwo.get(1));
    assertEquals("six", lineTwo.get(2));

    List<String> lineThree = ls.get(2);
    assertEquals("se\"ven", lineThree.get(0));
    assertEquals("ei,ght", lineThree.get(1));
    assertEquals(" nine  ", lineThree.get(2));


    List<String> lineFour = ls.get(3);
    assertEquals("", lineFour.get(0));
    assertEquals("eleven", lineFour.get(1));
    assertEquals("twelve ", lineFour.get(2));

  }


  @Test
  public void testReadCommaPassChunk()
    throws IOException
  {
    CharParser cp = new CharParser();
    //cp.setEnforceColumns(3);
    
    List<List<String>> ls = cp.read("src/test/resources/com/tkmtwo/util/charparse/comma-pass-long.csv", 0, 4);
    assertEquals(4, ls.size());
    
    List<String> lineOne = ls.get(0);
    assertEquals("one", lineOne.get(0));
    assertEquals("two", lineOne.get(1));
    assertEquals("three", lineOne.get(2));

    List<String> lineTwo = ls.get(1);
    assertEquals("four", lineTwo.get(0));
    assertEquals("five", lineTwo.get(1));
    assertEquals("six", lineTwo.get(2));

    List<String> lineThree = ls.get(2);
    assertEquals("se\"ven", lineThree.get(0));
    assertEquals("ei,ght", lineThree.get(1));
    assertEquals(" nine  ", lineThree.get(2));


    List<String> lineFour = ls.get(3);
    assertEquals("", lineFour.get(0));
    assertEquals("eleven", lineFour.get(1));
    assertEquals("twelve ", lineFour.get(2));

  }



  //@Test
  public void testReadTabPass()
    throws IOException
  {
    CharParser cp = new CharParser('\t');
    //cp.setEnforceColumns(3);
    
    List<List<String>> ls = cp.read("src/test/resources/com/tkmtwo/util/charparse/tab-pass.csv");
    assertEquals(4, ls.size());

    
    List<String> lineOne = ls.get(0);
    assertEquals("one", lineOne.get(0));
    assertEquals("two", lineOne.get(1));
    assertEquals("three", lineOne.get(2));

    List<String> lineTwo = ls.get(1);
    assertEquals("four", lineTwo.get(0));
    assertEquals("five", lineTwo.get(1));
    assertEquals("six", lineTwo.get(2));

    List<String> lineThree = ls.get(2);
    assertEquals("se\"ven", lineThree.get(0));
    assertEquals("ei\tght", lineThree.get(1));
    assertEquals(" nine  ", lineThree.get(2));


    List<String> lineFour = ls.get(3);
    assertEquals("", lineFour.get(0));
    assertEquals("eleven", lineFour.get(1));
    assertEquals("twelve ", lineFour.get(2));

  }


  
}

