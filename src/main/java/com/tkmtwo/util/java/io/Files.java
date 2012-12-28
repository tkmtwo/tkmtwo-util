package com.tkmtwo.util.java.io;



import java.io.File;
import java.io.IOException;
//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.List;

import org.apache.commons.io.FileUtils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import com.tkmtwo.util.org.joda.time.JodaTime;

public class Files
{
  public Files() { ; }
  

  
  
  public static boolean delete(File f)
    throws IOException
  {
    if(f.exists()) {
      if(f.isDirectory()) {
        File files[] = f.listFiles();
        for(int i = 0; i < files.length; i++)
          if(!delete(files[i]))
            return false;
        
      }
      return f.delete();
    } else {
      return true;
    }
  }




  public static File newFile(String pathname,
                             DateTime dt,
                             DateTimeFormatter dtf)

  {
    return new File(JodaTime.interpolate(pathname, dt, dtf));
  }
  public static File newFile(String pathname,
                             DateTime dt)
  {
    return new File(JodaTime.interpolate(pathname, dt, JodaTime.getDefaultFormatter()));
  }
  
  public static File newFile(String parent,
                             String child,
                             DateTime dt,
                             DateTimeFormatter dtf)
  {
    return new File(JodaTime.interpolate(parent, dt, dtf),
                    JodaTime.interpolate(child, dt, dtf));
  }
  
  public static File newFile(File parent,
                             String child,
                             DateTime dt,
                             DateTimeFormatter dtf)
  {
    return new File(parent,
                    JodaTime.interpolate(child, dt, dtf));
  }
  
  
  
  
  
  
  
  public static File forceMkdir(String pathname,
                                DateTime dt)
    throws IOException
  {
    File f = newFile(pathname, dt);
    FileUtils.forceMkdir(f);
    return f;
  }
  
  public static File forceMkdir(String pathname,
                                DateTime dt,
                                DateTimeFormatter dtf)
    throws IOException
  {
    File f = newFile(pathname, dt, dtf);
    FileUtils.forceMkdir(f);
    return f;
  }
  
  
  
}

