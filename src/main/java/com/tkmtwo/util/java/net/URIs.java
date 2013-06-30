package com.tkmtwo.util.java.net;


import java.net.URI;


public class URIs
{
  public static URI valueOf(String s)
  {
    URI uri = null;
    try {
      uri = new URI(s);
      return uri;
    } catch (Exception ex) {
      throw new IllegalArgumentException("String " + s + " is not a valid URI.", ex);
    }
  }
  
  public static UserInfo getUserInfo(URI uri)
  {
    if (uri == null) {
      return new UserInfo();
    }
    return parseUserInfo(uri.getUserInfo());
  }
  
  public static UserInfo parseUserInfo(String s)
  {
    return new UserInfo(s);
  }
  
}
