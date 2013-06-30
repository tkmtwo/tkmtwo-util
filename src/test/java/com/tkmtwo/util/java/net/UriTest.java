package com.tkmtwo.util.java.net;

import java.net.URI;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class UriTest {

  
  @Test
  public void testAr()
    throws Exception
  {
    URI arUri = new URI("arapi://myaccount:mypassword@MyArHost");
    System.out.println("Scheme is   : " + arUri.getScheme());
    System.out.println("UserInfo is : " + arUri.getUserInfo());
    System.out.println("Host is     : " + arUri.getHost());
  }

}

