package com.tkmtwo.util.java.net;

import java.io.Serializable;

import com.tkmtwo.util.java.lang.Strings;

public class UserInfo
  implements Serializable
{
  private String userName;
  private String password;

  public UserInfo()
  {
    ;
  }

  public UserInfo(String s)
  {
    if (Strings.isBlank(s)) { return; }

    String[] ss = s.split(":", 2);
    System.out.println("I SEE '" + s + "' which has " + ss.length);
    if (ss != null) {
      if (ss.length > 0) {
        setUserName(ss[0]);
      }
      if (ss.length > 1) {
        setPassword(ss[1]);
      }
    }
  }


  public UserInfo(String u, String p)
  {
    setUserName(u);
    setPassword(p);
  }
  
  
  
  public String getUserName() { return userName; }
  public void setUserName(String s) { userName = s; }
  
  public String getPassword() { return password; }
  public void setPassword(String s) { password = s; }
  
}
