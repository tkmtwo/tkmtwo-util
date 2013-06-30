package com.tkmtwo.util.java.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class ListsTest
{
  private List<String> listOne;
  private List<String> listTwo;
  private List<String> listThree;
  
  @Before
  public void setUp()
  {
    listOne = new ArrayList<String>();
    listOne.add("A1");
    listOne.add("A2");
    listOne.add("A3");

    listTwo = new ArrayList<String>();
    listTwo.add("B1");
    listTwo.add("B2");
    listTwo.add("B3");

    listThree = new ArrayList<String>();
    listThree.add("C1");
    listThree.add("C2");
  }
  

  private void assertList(List<String> l,
                          String[] a)
  {
    assertEquals(l.size(), a.length);
    for (int i = 0; i < a.length; i++) {
      assertEquals(l.get(i), a[i]);
    }
  }
  private void assertListOfLists(List<List<String>> lists,
                                 int listsLength,
                                 int listLength)
  {
    assertEquals(listsLength, lists.size());
    for (List<String> list : lists) {
      assertEquals(listLength, list.size());
    }
  }

  
  @Test(expected = IllegalArgumentException.class)
  public void testCombinateNullLists()
  {
    Lists.combinate(null);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testCombinateEmptyLists()
  {
    Lists.combinate(new ArrayList<List<String>>());
  }
  @Test(expected = IllegalArgumentException.class)
  public void testCombinateNullList()
  {
    List<List<String>> lol = new ArrayList<List<String>>();
    lol.add(listOne);
    lol.add(null);
    Lists.combinate(lol);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testCombinateEmptyList()
  {
    List<List<String>> lol = new ArrayList<List<String>>();
    lol.add(listOne);
    lol.add(new ArrayList<String>());
    Lists.combinate(lol);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testCombinateNullElement()
  {
    List<List<String>> lol = new ArrayList<List<String>>();
    listOne.add(null);
    lol.add(listOne);
    Lists.combinate(lol);
  }
    


  @Test
  public void testCombinateDocumentation()
  {
    List<String> listA = new ArrayList<String>();
    listA.add("A1");
    listA.add("A2");
    
    List<String> listB = new ArrayList<String>();
    listB.add("B1");
    listB.add("B2");
    listB.add("B3");
    
    List<List<String>> lol = new ArrayList<List<String>>();
    lol.add(listA);
    lol.add(listB);
    
    List<List<String>> combos = Lists.combinate(lol);
    
    for (List<String> combo : combos) {
      System.out.println(StringUtils.join(combo.iterator(), ", "));
    }
  }
  
  
  
  
  
  
  @Test
  public void testCombinateOne()
  {
    List<List<String>> lol = new ArrayList<List<String>>();
    lol.add(listOne);
    List<List<String>> combos = Lists.combinate(lol);

    assertListOfLists(combos, 3, 1);
    assertList(combos.get(0), new String[] { "A1" });
    assertList(combos.get(1), new String[] { "A2" });
    assertList(combos.get(2), new String[] { "A3" });
  }
  
  
  
  
  @Test
  public void testCombinateThree()
  {
    
    List<List<String>> lol = new ArrayList<List<String>>();
    lol.add(listOne);
    lol.add(listTwo);
    lol.add(listThree);

    List<List<String>> combos = Lists.combinate(lol);

    assertListOfLists(combos, 18, 3);


    assertEquals(18, combos.size());
    for (List<String> combo : combos) {
      assertEquals(3, combo.size());
    }
    
    assertList(combos.get(0),
               new String[] {"A1", "B1", "C1"});
    assertList(combos.get(1),
               new String[] {"A1", "B1", "C2"});
    
    assertList(combos.get(2),
               new String[] {"A1", "B2", "C1"});
    assertList(combos.get(3),
               new String[] {"A1", "B2", "C2"});
    
    assertList(combos.get(4),
               new String[] {"A1", "B3", "C1"});
    assertList(combos.get(5),
               new String[] {"A1", "B3", "C2"});
    
    assertList(combos.get(6),
               new String[] {"A2", "B1", "C1"});
    assertList(combos.get(7),
               new String[] {"A2", "B1", "C2"});
    
    assertList(combos.get(8),
               new String[] {"A2", "B2", "C1"});
    assertList(combos.get(9),
               new String[] {"A2", "B2", "C2"});

    assertList(combos.get(10),
               new String[] {"A2", "B3", "C1"});
    assertList(combos.get(11),
               new String[] {"A2", "B3", "C2"});
    
    assertList(combos.get(12),
               new String[] {"A3", "B1", "C1"});
    assertList(combos.get(13),
               new String[] {"A3", "B1", "C2"});
    
    assertList(combos.get(14),
               new String[] {"A3", "B2", "C1"});
    assertList(combos.get(15),
               new String[] {"A3", "B2", "C2"});
    
    assertList(combos.get(16),
               new String[] {"A3", "B3", "C1"});
    assertList(combos.get(17),
               new String[] {"A3", "B3", "C2"});
    
  }
  
  
  
  private void assertMap(Map<String,String> map,
                         List<String> names,
                         String[] values)
  {
    assertEquals(map.size(), names.size());

    for (int i = 0; i < names.size(); i++) {
      assertEquals(map.get(names.get(i)), values[i]);
    }
  }












  @Test
  public void testMapinateDocumentation()
  {
    List<String> listA = new ArrayList<String>();
    listA.add("A1");
    listA.add("A2");
    
    List<String> listB = new ArrayList<String>();
    listB.add("B1");
    listB.add("B2");
    listB.add("B3");
    
    List<List<String>> srcLists = new ArrayList<List<String>>();
    srcLists.add(listA);
    srcLists.add(listB);
    
    List<String> srcNames = new ArrayList<String>();
    srcNames.add("valueA");
    srcNames.add("valueB");
    
    List<Map<String,String>> maps = Lists.mapinate(srcLists,
                                                   srcNames);
    
    for (int i = 0; i < maps.size(); i++) {
      Map<String,String> map = maps.get(i);
      System.out.println("Map "
                         + String.valueOf(i) + " has "
                         + String.valueOf(map.size()) + " pairs.");
      for (String srcName : srcNames) {
        System.out.println("  " + srcName + " -> " + map.get(srcName));
      }
    }
  }
  
  @Test
  public void testMapinate()
  {
    List<List<String>> lol = new ArrayList<List<String>>();
    lol.add(listOne);
    lol.add(listTwo);
    lol.add(listThree);
    
    List<String> names = new ArrayList<String>();
    names.add("aye");
    names.add("bee");
    names.add("see");
    List<Map<String,String>> maps = Lists.mapinate(lol, names);
    
    assertEquals(18, maps.size());
    for (Map<String,String> map : maps) {
      assertEquals(3, map.size());
    }

    assertMap(maps.get(0), names,
              new String[] {"A1", "B1", "C1"});
    assertMap(maps.get(1), names,
              new String[] {"A1", "B1", "C2"});
    
    assertMap(maps.get(2), names,
              new String[] {"A1", "B2", "C1"});
    assertMap(maps.get(3), names,
              new String[] {"A1", "B2", "C2"});
    
    assertMap(maps.get(4), names,
              new String[] {"A1", "B3", "C1"});
    assertMap(maps.get(5), names,
              new String[] {"A1", "B3", "C2"});
    
    assertMap(maps.get(6), names,
              new String[] {"A2", "B1", "C1"});
    assertMap(maps.get(7), names,
              new String[] {"A2", "B1", "C2"});
    
    assertMap(maps.get(8), names,
              new String[] {"A2", "B2", "C1"});
    assertMap(maps.get(9), names,
              new String[] {"A2", "B2", "C2"});
    
    assertMap(maps.get(10), names,
              new String[] {"A2", "B3", "C1"});
    assertMap(maps.get(11), names,
              new String[] {"A2", "B3", "C2"});
    
    assertMap(maps.get(12), names,
              new String[] {"A3", "B1", "C1"});
    assertMap(maps.get(13), names,
              new String[] {"A3", "B1", "C2"});
    
    assertMap(maps.get(14), names,
              new String[] {"A3", "B2", "C1"});
    assertMap(maps.get(15), names,
              new String[] {"A3", "B2", "C2"});
    
    assertMap(maps.get(16), names,
              new String[] {"A3", "B3", "C1"});
    assertMap(maps.get(17), names,
              new String[] {"A3", "B3", "C2"});
    
    
  }

  
  
}




