package com.tkmtwo.util.java.io;


import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import com.tkmtwo.util.java.lang.Strings;
import com.tkmtwo.util.java.util.regex.MatchMethod;


public class PatternDirectoryFilter
  extends PatternFileFilter
{
  
  protected PatternDirectoryFilter()
  {
  }
  
  public PatternDirectoryFilter(Pattern p)
  {
    super(p);
  }
  
  public PatternDirectoryFilter(String p)
    throws PatternSyntaxException
  {
    super(p);
  }
  
  public PatternDirectoryFilter(MatchMethod m, Pattern p)
  {
    super(m, p);
  }
  
  public PatternDirectoryFilter(MatchMethod m, String p)
    throws PatternSyntaxException
  {
    super(m, p);
  }
  
  public boolean acceptFileType(File f)
  {
    return f.isDirectory();
  }
  
  public static File[] listDirectories(File f, Pattern p)
  {
    return listDirectories(f, DEFAULT_MATCH_METHOD, p);
  }
  
  public static File[] listDirectories(File f, String p)
    throws PatternSyntaxException
  {
    return listDirectories(f, DEFAULT_MATCH_METHOD, Pattern.compile(p));
  }
  
  public static File[] listDirectories(File f, MatchMethod m, String p)
    throws PatternSyntaxException
  {
    return listDirectories(f, m, Pattern.compile(p));
  }
  
  public static File[] listDirectories(File f, MatchMethod m, Pattern p)
  {
    java.io.FileFilter ff = new PatternDirectoryFilter(m, p);
    File matches[] = f.listFiles(ff);
    return matches != null ? matches : new File[0];
  }
  
  public static File listSingleDirectory(File f, Pattern p)
    throws IOException
  {
    return listSingleDirectory(f, DEFAULT_MATCH_METHOD, p);
  }
  
  public static File listSingleDirectory(File f, String p)
    throws PatternSyntaxException, IOException
  {
    return listSingleDirectory(f, DEFAULT_MATCH_METHOD, Pattern.compile(p));
  }
  
  public static File listSingleDirectory(File f, MatchMethod m, String p)
    throws PatternSyntaxException, IOException
  {
    return listSingleDirectory(f, m, Pattern.compile(p));
  }
  
  public static File listSingleDirectory(File f, MatchMethod m, Pattern p)
    throws IOException
  {
    File matches[] = listDirectories(f, m, p);
    if(matches.length == 0)
      throw new IOException("No directory matching " + Strings.sqit(p.pattern()));
    if(matches.length > 1)
      throw new IOException("Multiple directories matching " + Strings.sqit(p.pattern()));
    else
      return matches[0];
  }
}
