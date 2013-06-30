package com.tkmtwo.util.java.net;

import java.io.File;
import java.net.URI;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class URIsTest {

  /*  
  @Test
  public void testCreate()
  {
    URI arUri = URIs.valueOf("arapi://myaccount:mypassword@MyArHost");
    System.out.println();
    System.out.println();
    System.out.println("Scheme is   : " + arUri.getScheme());
    System.out.println("UserInfo is : " + arUri.getUserInfo());
    System.out.println("Host is     : " + arUri.getHost());
    UserInfo ui = URIs.getUserInfo(arUri);
    assertEquals("myaccount", ui.getUserName());
    assertEquals("mypassword", ui.getPassword());
  }
  
  @Test
  public void testThis()
    throws Exception
  {
    URI uri = new URI("jdbc:jtds:sqlserver://DbUser:DbPass@DbServerName:1433/DbName");
    System.out.println();
    System.out.println();
    System.out.println("Scheme is   : " + uri.getScheme());
    System.out.println("UserInfo is : " + uri.getUserInfo());
    System.out.println("Host is     : " + uri.getHost());
    System.out.println("Port is     : " + uri.getPort());
    System.out.println("Path is     : " + uri.getPath());
    UserInfo ui = URIs.getUserInfo(uri);
    System.out.println("UserInfo is: " + ui.toString());

  } 
  */   

  private void confess(URI uri)
  {
    System.out.println();
    System.out.println();
    System.out.println(uri.toString());
    System.out.println("Opaque is   : " + uri.isOpaque());

    System.out.println("Scheme is   : " + uri.getScheme());
    System.out.println("Schemesp is : " + uri.getSchemeSpecificPart());
    System.out.println("Authority is: " + uri.getAuthority());
    System.out.println("UserInfo is : " + uri.getUserInfo());
    System.out.println("Host is     : " + uri.getHost());
    System.out.println("Port is     : " + uri.getPort());
    System.out.println("Path is     : " + uri.getPath());
    System.out.println("Query is    : " + uri.getQuery());
    System.out.println("Frag is     : " + uri.getFragment());
    System.out.println();
    System.out.println();
  }
  
  @Test
  public void testFile()
    throws Exception
  {
    confess(new URI("file://filehost/path/to/file.log"));
    confess(new URI("file:///path/to/file.log"));
    confess(new URI("file:file.log"));
    confess(new URI("file.log"));
  }
  
  @Test
  public void testFileCreate()
    throws Exception
  {
    File f = new File(new URI("file:///C:/tmp/tom.txt"));
    System.out.println("File is: " + f.getAbsolutePath());
    System.out.println("File is: " + f.getAbsolutePath());
    System.out.println("File is: " + f.getAbsolutePath());
    System.out.println("File is: " + f.getAbsolutePath());
    System.out.println("File is: " + f.getAbsolutePath());
    System.out.println("File is: " + f.getAbsolutePath());

    File fTwo = new File(new URI("file:/tom.txt"));
    System.out.println("File Two is: " + fTwo.getAbsolutePath());
    System.out.println("File Two is: " + fTwo.getAbsolutePath());
    System.out.println("File Two is: " + fTwo.getAbsolutePath());
    System.out.println("File Two is: " + fTwo.getAbsolutePath());
    System.out.println("File Two is: " + fTwo.getAbsolutePath());
    System.out.println("File Two is: " + fTwo.getAbsolutePath());

  }

  @Test
  public void testRtc()
    throws Exception
  {
    String uriString = "https://xml.itsm.accenture.com/ITSMXML/Change.aspx?id=RTCITSMACCENTURE";
    URI uri = new URI(uriString);
  }

}

