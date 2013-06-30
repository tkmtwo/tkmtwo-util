package com.tkmtwo.util.org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

/*

SELECT MAX(LoginHour) FROM HourlyCompanyLicenseLogin WITH (NOLOCK)
SELECT MAX(LoginHour) HourlyCompanyLogin WITH (NOLOCK)
SELECT MAX(LoginHour) HourlyLicenseLogin WITH (NOLOCK)
SELECT MAX(LoginHour) HourlyLogin WITH (NOLOCK)
SELECT MAX(LoginHour) Login WITH (NOLOCK)
SELECT MAX() FROM UserAccount WITH (NOLOCK)


 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;


import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.tkmtwo.util.java.lang.Strings;
import com.tkmtwo.util.org.joda.time.JodaTime;
import com.tkmtwo.util.org.joda.time.DateTimeComparison;

public class DateTimeNamedFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  private static final Logger logger = LoggerFactory.getLogger(DateTimeNamedFileFilter.class);

  private DateTimeFormatter dateTimeFormatter = JodaTime.getDefaultFormatter();
  private Pattern dateTimePattern = JodaTime.BASIC_DATETIME_NOMILLIS_PATTERN;
  private DateTimeComparison dateTimeComparison;
  private DateTime dateTime;
  
  public DateTimeFormatter getDateTimeFormatter() { return dateTimeFormatter; }
  public void setDateTimeFormatter(DateTimeFormatter dtf) { dateTimeFormatter = dtf; }
  
  public Pattern getDateTimePattern() { return dateTimePattern; }
  public void setDateTimePattern(Pattern p) { dateTimePattern = p; }
  
  public DateTimeComparison getDateTimeComparison() { return dateTimeComparison; }
  public void setDateTimeComparison(DateTimeComparison dtc) { dateTimeComparison = dtc; }
  
  public DateTime getDateTime() { return dateTime; }
  public void setDateTime(DateTime dt) { dateTime = dt; }


  private boolean acceptFilename(String s)
  {
    DateTime fileDt = JodaTime.getDateTime(getDateTimeFormatter(),
                                           getDateTimePattern(),
                                           s);
    if (fileDt == null) {
      return false;
    }
    
    return acceptDateTime(fileDt);
  }


    
  private boolean acceptDateTime(DateTime dt)
  {
    if (getDateTimeComparison() == null
        && getDateTime() == null) {
      return true;
    }
    
    return getDateTimeComparison().compare(dt, getDateTime());
  }


  /*
   * Checks to see if the filename matches.
   *
   * @param file  the File to check
   * @return true if the filename matches
   */
  @Override
  public boolean accept(final File file) {
    return acceptFilename(file.getName());
  }

  public boolean accept(final File dir, final String name)
  {
    return acceptFilename(name);
  }
  
  
  
  
  
  public static File[] listSubdirectories(File rootDir, DateTime dt) {
    return listSubdirectories(rootDir, dt, DateTimeComparison.LT);
  }

  public static File[] listSubdirectories(File rootDir, DateTime dt, DateTimeComparison dtc) {
    checkNotNull(rootDir, "Need a root directory.");
    checkArgument(rootDir.exists(), "Root directory %s does not exist.", rootDir.getAbsolutePath());
    checkArgument(rootDir.isDirectory(), "Root directory %s is not a directory.", rootDir.getAbsolutePath());

    checkNotNull(dt, "Need a datetime for comparison.");
    checkNotNull(dtc, "Need a datetime comparison operator.");
    
    logger.debug("Listing datetime named subdirectories in {} using datetime {} and operator {}",
                 new Object[] {
                   rootDir.getAbsolutePath(),
                   JodaTime.format(dt),
                   dtc.toString()
                 });

    DateTimeNamedFileFilter dtnff = new DateTimeNamedFileFilter();
    dtnff.setDateTime(dt);
    dtnff.setDateTimeComparison(dtc);
    
    FilenameFilter fileFilter = new AndFileFilter(DirectoryFileFilter.INSTANCE, dtnff);
    
    return rootDir.listFiles(fileFilter);
  }
  



  public static File[] listFiles(File rootDir, DateTime dt, DateTimeComparison dtc) {
    String s = null;
    return listFiles(rootDir, dt, dtc, s, s);
  }


  public static File[] listFiles(File rootDir, DateTime dt, DateTimeComparison dtc, String prefix, String suffix) {
    checkNotNull(rootDir, "Need a root directory.");
    checkArgument(rootDir.exists(), "Root directory %s does not exist.", rootDir.getAbsolutePath());
    checkArgument(rootDir.isDirectory(), "Root directory %s is not a directory.", rootDir.getAbsolutePath());

    checkNotNull(dt, "Need a datetime for comparison.");
    checkNotNull(dtc, "Need a datetime comparison operator.");
    
    logger.debug("Listing datetime named subdirectories in {} using datetime {} and operator {}",
                 new Object[] {
                   rootDir.getAbsolutePath(),
                   JodaTime.format(dt),
                   dtc.toString()
                 });

    
    
    List<IOFileFilter> fileFilters = new ArrayList<IOFileFilter>();
    fileFilters.add(FileFileFilter.FILE);
    if (Strings.isNotBlank(prefix)) {
      fileFilters.add(new PrefixFileFilter(prefix));
    }
    if (Strings.isNotBlank(suffix)) {
      fileFilters.add(new SuffixFileFilter(suffix));
    }
    
    DateTimeNamedFileFilter dtnff = new DateTimeNamedFileFilter();
    dtnff.setDateTime(dt);
    dtnff.setDateTimeComparison(dtc);
    fileFilters.add(dtnff);

    
    FilenameFilter fileFilter = new AndFileFilter(fileFilters);
    
    return rootDir.listFiles(fileFilter);
  }
  
  
  

  
  

  
  
  
  
}
