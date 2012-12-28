package com.tkmtwo.util.idgen;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * Generates alphanumeric ids that are 32char long.
 *
 * hostid, identityid, timeid, randomid
 *
 * @author Tom Mahaffey
 * @version $Id$
 */
public class IdGenerator
{
  
  private static IdGenerator gen;
  
  private String hostId;
  private String identityId;
  private final SecureRandom random = new SecureRandom();
  
  
  private IdGenerator() {
    setHostId();
    setIdentityId();
  }
  
  public static synchronized IdGenerator getInstance() {
    if(gen == null)
      gen = new IdGenerator();
    return gen;
  }
  
  public synchronized String getId()
  {
    StringBuffer buf = new StringBuffer();
    buf.append(getHostId()).append(getIdentityId()).append(getTimeId()).append(getRandomId());
    return buf.toString();
  }
  
  
  private String getHostId()
  {
    return hostId;
  }
  
  private void setHostId()
  {
    byte addr[] = null;
    try {
      addr = InetAddress.getLocalHost().getAddress();
    } catch(UnknownHostException uhe) {
      addr = (new byte[] {
          127, 0, 0, 1
      });
    }
    hostId = hexFormat(getInt(addr), 8);
  }
  
  private String getTimeId() {
    long l = System.currentTimeMillis();
    int i = (int)l & -1;
    return hexFormat(i, 8);
  }
  
  private String getIdentityId() {
    return identityId;
  }
  
  private void setIdentityId() {
    identityId = hexFormat(System.identityHashCode(this), 8);
  }
  
  private String getRandomId() {
    return hexFormat(random.nextInt(), 8);
  }
  
  
  private static String hexFormat(int i, int j) {
    String s = Integer.toHexString(i);
    return hexPad(s, j) + s;
  }
  
  private static String hexPad(String s, int i) {
    StringBuffer buf = new StringBuffer();
    if(s.length() < i)
    {
      for(int j = 0; j < i - s.length(); j++)
        buf.append('0');
      
    }
    return buf.toString();
  }
  
  private static int getInt(byte b[]) {
    int i = 0;
    int j = 24;
    for(int k = 0; j >= 0; k++)
    {
      int l = b[k] & 0xff;
      i += l << j;
      j -= 8;
    }
    
    return i;
  }
  
}


