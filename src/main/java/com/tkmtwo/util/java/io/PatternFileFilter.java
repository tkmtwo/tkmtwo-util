package com.tkmtwo.util.java.io;

import java.io.IOException;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import com.tkmtwo.util.java.lang.Strings;
import com.tkmtwo.util.java.util.regex.MatchMethod;

public class PatternFileFilter
    implements FileFilter
{
  protected MatchMethod matchMethod;
  protected Pattern pattern;
  public static final MatchMethod DEFAULT_MATCH_METHOD = MatchMethod.FIND;
  
  
  protected PatternFileFilter()
  {
  }
  
  public PatternFileFilter(Pattern p)
  {
    this(DEFAULT_MATCH_METHOD, p);
  }
  
  public PatternFileFilter(String p)
    throws PatternSyntaxException
  {
    this(DEFAULT_MATCH_METHOD, Pattern.compile(p));
  }
  
  public PatternFileFilter(MatchMethod m, String p)
    throws PatternSyntaxException
  {
    this(m, Pattern.compile(p));
  }
  
  public PatternFileFilter(MatchMethod m, Pattern p)
  {
    matchMethod = m;
    pattern = p;
  }
  
  public boolean accept(File f)
  {
    if (f == null) { return false; }
    return acceptFileType(f) && acceptFileName(f.getName());
  }
  
  public boolean acceptFileType(File f)
  {
    if (f == null) { return false; }
    return !f.isDirectory();
  }
  
  public boolean acceptFileName(String s)
  {
    if (s == null) { return false; }
    java.util.regex.Matcher matcher = pattern.matcher(s);
    boolean foundMatch = false;
    return matchMethod.matches(matcher);
  }
  
  public static File[] listFiles(File f, Pattern p)
  {
    return listFiles(f, DEFAULT_MATCH_METHOD, p);
  }
  
  public static File[] listFiles(File f, String p)
    throws PatternSyntaxException
  {
    return listFiles(f, DEFAULT_MATCH_METHOD, Pattern.compile(p));
  }
  
  public static File[] listFiles(File f, MatchMethod m, String p)
    throws PatternSyntaxException
  {
    return listFiles(f, DEFAULT_MATCH_METHOD, Pattern.compile(p));
  }
  
  public static File[] listFiles(File f, MatchMethod m, Pattern p)
  {
    FileFilter ff = new PatternFileFilter(m, p);
    File matches[] = f.listFiles(ff);
    return matches != null ? matches : new File[0];
  }
  
  public static File listSingleFile(File f, Pattern p)
    throws IOException
  {
    return listSingleFile(f, DEFAULT_MATCH_METHOD, p);
  }
  
  public static File listSingleFile(File f, String p)
    throws PatternSyntaxException, IOException
  {
    return listSingleFile(f, DEFAULT_MATCH_METHOD, Pattern.compile(p));
  }
  
  public static File listSingleFile(File f, MatchMethod m, String p)
    throws PatternSyntaxException, IOException
  {
    return listSingleFile(f, m, Pattern.compile(p));
  }
  
  public static File listSingleFile(File f, MatchMethod m, Pattern p)
    throws IOException
  {
    File matches[] = listFiles(f, m, p);
    if(matches.length == 0)
      throw new IOException("No file matching " + Strings.tickit(p.pattern()));
    if(matches.length > 1)
      throw new IOException("Multiple files matching " + Strings.tickit(p.pattern()));
    else
      return matches[0];
  }
  
  
}
