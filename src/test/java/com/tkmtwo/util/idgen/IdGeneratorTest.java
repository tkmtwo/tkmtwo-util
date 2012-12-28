package com.tkmtwo.util.idgen;


import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



public class IdGeneratorTest
{


  @Test
  public void testThis() {
    IdGenerator idgen = IdGenerator.getInstance();
    assertNotNull(idgen);
    
    String idOne = idgen.getId();
    assertNotNull(idOne);
    assertTrue((idOne.length() == 32));
    
    
    String idTwo = idgen.getId();
    assertNotNull(idTwo);
    assertTrue(idTwo.length() == 32);
    
    
    
    
  }
  
}


