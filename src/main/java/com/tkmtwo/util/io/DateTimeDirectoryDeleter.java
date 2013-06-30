package com.tkmtwo.util.io;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.joda.time.DateTime;
import org.joda.time.Period;
//import org.joda.time.format.ISOPeriodFormat;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.tkmtwo.util.java.io.Files;
import com.tkmtwo.util.org.apache.commons.io.filefilter.DateTimeNamedFileFilter;
import com.tkmtwo.util.org.joda.time.JodaTime;
import com.tkmtwo.util.org.joda.time.DateTimeComparison;

public class DateTimeDirectoryDeleter
{
  private static final Logger logger = LoggerFactory.getLogger(DateTimeDirectoryDeleter.class);

  private File rootDirectory;
  private Period retentionPeriod;


  public File getRootDirectory() { return rootDirectory; }
  public void setRootDirectory(File f) { rootDirectory = f; }
  
  public Period getRetentionPeriod() { return retentionPeriod; }
  public void setRetentionPeriod(Period p) { retentionPeriod = p; }

  
  private File[] getDirectoriesToDelete(DateTime dt)
  {
    File[] dirs = DateTimeNamedFileFilter.listSubdirectories(getRootDirectory(),
                                                             dt,
                                                             DateTimeComparison.LTEQ);
    return dirs;
  }
  
  public void remove(DateTime dt)
  {
    logger.info("Deleting subdirectories in {} using datetime {}.",
                getRootDirectory().getAbsolutePath(),
                JodaTime.print(dt));
    
    for (File f : getDirectoriesToDelete(dt.minus(getRetentionPeriod()))) {
      logger.info("Removing {}.", f.getName());
      try {
        FileUtils.forceDelete(f);
        logger.info("Removed  {}.", f.getName());
      } catch (Exception ex) {
        logger.warn("Error removing: {}.", ex.getMessage());
      }
      
    }
    
  }
  
  
  //
  // rootDir retPeriod
  // C:\tmp\DateTimeDirectoryArchiver\test P1D
  //
  public static void main(String[] args)
    throws IOException
  {
    checkArgument(args.length > 3, "Not enough args.");
    String rootDirString = args[0];
    String retPeriodString = args[2];

    DateTime dtNow = new DateTime();
    DateTimeDirectoryDeleter dtdd = new DateTimeDirectoryDeleter();
    dtdd.setRootDirectory(new File(rootDirString));
    dtdd.setRetentionPeriod(JodaTime.parsePeriod(retPeriodString));
    
    dtdd.remove(dtNow);
  }
  
}

