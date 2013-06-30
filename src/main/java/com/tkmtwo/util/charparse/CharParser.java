package com.tkmtwo.util.charparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.tkmtwo.util.java.util.regex.Matchers;

public class CharParser
{
  public static final char DEFAULT_SEPARATOR = ',';
  public static final int DEFAULT_LINE_LENGTH = 10;
  public static final int DEFAULT_LINE_COUNT = 100;
  public static final int DEFAULT_ENFORCE_COLUMNS = -1;

  private char charSep;
  private Pattern pattern;
  private int lineLength;
  private int lineCount;
  private int enforceColumns = DEFAULT_ENFORCE_COLUMNS;


  private Pattern csvPattern;
  private Matcher csvMatcher;

  private Pattern dqPattern;
  private Matcher dqMatcher;


  public CharParser()
  {
    this(DEFAULT_SEPARATOR,
         DEFAULT_LINE_LENGTH,
         DEFAULT_LINE_COUNT);
  }
  
  public CharParser(char c)
    throws PatternSyntaxException
  {
    this(c,
         DEFAULT_LINE_LENGTH,
         DEFAULT_LINE_COUNT);
  }
  
  
  public CharParser(char c, int ll, int lc)
  {
    charSep = c;
    lineLength = ll;
    lineCount = lc;
    compilePattern(c);
  }

  public char getCharSep() { return charSep; }
  public void setCharSep(char c) { charSep = c; }

  public Pattern getPattern() { return pattern; }
  
  public int getLineLength() { return lineLength; }
  public void setLineLength(int i) { lineLength = i; }

  public int getLineCount() { return lineCount; }
  public void setLineCount(int i) { lineCount = i; }

  public int getEnforceColumns() { return enforceColumns; }
  public void setEnforceColumns(int i) { enforceColumns = i; }
  
  private void compilePattern(char c)
    throws PatternSyntaxException
  {
    StringBuffer sb = new StringBuffer();


    sb
      .append("  \\G(?:^|").append(Pattern.quote(String.valueOf(c))).append(")       \n")
      .append("  (?:                                                                 \n")
      .append("     # Either a double-quoted field...                                \n")
      .append("     \" #field's opening double-quote                                 \n")
      .append(" (  [^\"]*+    (?: \"\"   [^\"]*+ ) *+)                               \n")
      .append("     \" #field's closing double-quote                                 \n")
      .append("  |                                                                   \n")
      .append("     # some non-quoted/non-comma text...                              \n")
      .append("     ( [^\"").append(Pattern.quote(String.valueOf(c))).append("]*+ )  \n")
      .append("  )");


    
    //"     ( [^\",]*+ )  \n")
    
    
    
    //warm it up, Chris...
    csvPattern = Pattern.compile(sb.toString(), Pattern.COMMENTS);
    csvMatcher = csvPattern.matcher("");
    
    dqPattern = Pattern.compile("\"\"");
    dqMatcher = dqPattern.matcher("");
  }
  
  
  
  public List<String> parse(String s)
  {
    if(s == null || s.length() < 1) {
      return Collections.EMPTY_LIST;
    }
    
    List<String> l = new ArrayList<String>(lineLength);

    /*
      Sure wish I could do this in the regex...TODO for later excercise?
     */
    String matchString = s;
    if (s.startsWith(String.valueOf(getCharSep()))) {
      l.add("");
      matchString = s.substring(1);
    }


    csvMatcher.reset(matchString);
    while(csvMatcher.find()) {
      //System.out.println(Matchers.reportGroupMatches(csvMatcher));
      
      String field = null;
      String first = csvMatcher.group(2); //2
      
      if (first != null) {
        field = first;
      } else {
        dqMatcher.reset(csvMatcher.group(1)); //1
        field = dqMatcher.replaceAll("\"");
      }
      
      l.add(field);
    }
    
    if (getEnforceColumns() > 0) {
      if (l.size() !=  getEnforceColumns()) {
        throw new NumberOfColumnsException("Number of columns expected was "
                                           + String.valueOf(getEnforceColumns())
                                           + " but parsed " + String.valueOf(l.size()),
                                           getEnforceColumns(), l.size());
      }
    }
    
    
    return l;
  }
  
  
  public List<List<String>> read(String s)
    throws IOException
  {
    return read(s, 0, -1);
  }
  public List<List<String>> read(String s, int skipCount)
    throws IOException
  {
    return read(new File(s), skipCount, -1);
  }
  public List<List<String>> read(String s, int skipCount, int readCount)
    throws IOException
  {
    return read(new File(s), skipCount, readCount);
  }


  public List<List<String>> read(File f)
    throws IOException
  {
    return read(f, 0, -1);
  }
  public List<List<String>> read(File f, int skipCount)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(f);
    return read(fis, skipCount, -1);
  }
  public List<List<String>> read(File f, int skipCount, int readCount)
    throws IOException
  {
    FileInputStream fis = new FileInputStream(f);
    return read(fis, skipCount, readCount);
  }


  public List<List<String>> read(InputStream is)
    throws IOException
  {
    return read(is, 0, -1);
  }
  public List<List<String>> read(InputStream is, int skipCount)
    throws IOException
  {
    return read(is, 0, -1);
  }
  public List<List<String>> read(InputStream is, int skipCount, int readCount)
    throws IOException
  {
    List<List<String>> ls = new ArrayList<List<String>>(lineCount);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String l = null;
    
    int linesRead = 0;
    int readCountRead = 0;

    while ((l = br.readLine()) != null) {
      linesRead++;
      if (linesRead <= skipCount) { continue; }
      
      readCountRead++;
      //System.out.println("BAM BAM BAM BAM: " + String.valueOf(readCountRead));
      if (readCount > 0
          && readCountRead > readCount) {
        //System.out.println("BAM " + String.valueOf(readCount) + " -- " + String.valueOf(readCountRead));
        break;
      }
      
      if (l.startsWith("#")) { continue; }
      try {
        ls.add(parse(l));
      } catch (NumberOfColumnsException nocEx) {
        throw new CharParserException("Invalid number of columns at line " + String.valueOf(linesRead) + ".",
                                      nocEx);
      }
    }
    return ls;
  }
  
  
  
  /*
  public List<List<String>> parse(InputStream is)
    throws IOException
  {
    List<List<String>> l = new ArrayList<List<String>>(lineCount);
    BufferedReader r = new BufferedReader(new InputStreamReader(is));
    String s = null;
    do
      {
        if((s = r.readLine()) == null)
          break;
        List<String> p = parse(s);
        if(p.size() > 0)
          l.add(p);
      } while(true);
    return l;
  }
  */


  
  public String join(List<String> l)
  {
    return join((String[])l.toArray(new String[l.size()]));
  }
  
  public String join(String s[])
  {
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < s.length; i++) {
      sb.append("\"").append(s[i]).append("\"");
      if(i < s.length - 1)
        sb.append(getCharSep());
    }
    
    return sb.toString();
  }
  
  public String get(List<String> l, int i)
  {
    if (l == null
        || i < 0
        || i >= l.size()) {
      return "";
    }
    return l.get(i);
  }


  /*
  public String blah()
  {
    Pattern p = Pattern.compile("\\G(?:^|,)                       \n"
                                +  "(?:                           \n"
                                + "# Either a double-quoted field...   \n"
                                + "\" #field's opening double-quote    \n"
                                + "(  (?> [^\"]*+ ) (?> \"\" [^\"]*+ )*+ )   \n"
                                + "\" #field's closing double-quote \n"
                                + "# ... or ... \n"
                                + "|           \n"
                                + "# some non-quoted/non-comma text...  \n"
                                + "( [^\",] )   \n"
                                + ")",
                                Pattern.COMMENTS);
  }
  */
    
                                
    
  
}


/*

      .append("\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"")
      .append(Pattern.quote(String.valueOf(c)))
      .append("?|([^")
      .append(Pattern.quote(String.valueOf(c)))
      .append("]+)")
      .append(Pattern.quote(String.valueOf(c)))
      .append("?|")
      .append(Pattern.quote(String.valueOf(c)));

      \"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"
      .append(Pattern.quote(String.valueOf(c)))
      ?|([^
      .append(Pattern.quote(String.valueOf(c)))
      ]+)
      .append(Pattern.quote(String.valueOf(c)))
      ?|
      .append(Pattern.quote(String.valueOf(c)));

      \"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\" xxx ?|([^ xxx ]+) xxx ?| xxx

 */
