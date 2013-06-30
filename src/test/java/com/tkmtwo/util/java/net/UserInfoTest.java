package com.tkmtwo.util.java.net;

import java.net.URI;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;


public class UserInfoTest {

  @Test
  public void testValid()
  {
    UserInfo ui = new UserInfo("user:pass");
    assertEquals("user", ui.getUserName());
    assertEquals("pass", ui.getPassword());
  }
  
  @Test
  public void testDefault()
  {
    UserInfo ui = new UserInfo();
    assertNull(ui.getUserName());
    assertNull(ui.getPassword());
  }

  @Test
  public void testNull()
  {
    String s = null;
    UserInfo ui = new UserInfo(s);
    assertNull(ui.getUserName());
    assertNull(ui.getPassword());
  }

  @Test
  public void testBlank()
  {
    String s = "";
    UserInfo ui = new UserInfo(s);
    assertNull(ui.getUserName());
    assertNull(ui.getPassword());
  }

  
  @Test
  public void testNoColon()
  {
    UserInfo ui = new UserInfo("user");
    assertEquals("user", ui.getUserName());
    assertNull(ui.getPassword());
  }
  
  @Test
  public void testTwoColon()
  {
    UserInfo ui = new UserInfo("user:pass:age");
    assertEquals("user", ui.getUserName());
    assertEquals("pass:age", ui.getPassword());
  }
    
  

}

