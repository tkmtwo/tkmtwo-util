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

public class DateTimeDirectoryArchiver
{
  private static final Logger logger = LoggerFactory.getLogger(DateTimeDirectoryArchiver.class);

  private File rootDirectory;
  private File tmpDirectory;

  private Period zipPeriod;
  private Period deletePeriod;
  
  private String filePrefix;
  private String fileSuffix;



  public File getRootDirectory() { return rootDirectory; }
  public void setRootDirectory(File f) { rootDirectory = f; }
  
  public File getTmpDirectory() { return tmpDirectory; }
  public void setTmpDirectory(File f) { tmpDirectory = f; }
  
  public Period getZipPeriod() { return zipPeriod; }
  public void setZipPeriod(Period p) { zipPeriod = p; }

  public Period getDeletePeriod() { return deletePeriod; }
  public void setDeletePeriod(Period p) { deletePeriod = p; }
  
  public String getFilePrefix() { return filePrefix; }
  public void setFilePrefix(String s) { filePrefix = s; }
  
  public String getFileSuffix() { return fileSuffix; }
  public void setFileSuffix(String s) { fileSuffix = s; }
  
  
  private File[] getDirectoriesToZip(DateTime dt)
  {
    File[] dirs = DateTimeNamedFileFilter.listSubdirectories(getRootDirectory(),
                                                             dt,
                                                             DateTimeComparison.LTEQ);
    return dirs;
  }
  
  private File[] getFilesToDelete(DateTime dt)
  {
    File[] files = DateTimeNamedFileFilter.listFiles(getRootDirectory(),
                                                     dt,
                                                     DateTimeComparison.LTEQ,
                                                     getFilePrefix(),
                                                     getFileSuffix());
    return files;
  }

  public void archive(DateTime dt)
  {
    logger.info("Achiving directory {} using datetime {}, zip period {}, and delete period {} using tmp dir {}.",
                new Object[] {
                  getRootDirectory().getAbsolutePath(),
                  JodaTime.print(dt),
                  JodaTime.print(getZipPeriod()),
                  JodaTime.print(getDeletePeriod()),
                  getTmpDirectory().getAbsolutePath()
                });
    
    for (File f : getDirectoriesToZip(dt.minus(getZipPeriod()))) {
      File previousZip = new File(getRootDirectory(), f.getName() + ".zip");
      if (previousZip.exists()) {
        logger.info("Skipping {} because {} already exists.", f.getName(), previousZip.getName());
        continue;
      }
      
      File fZip = null;

      //
      //Zip up the source to another path
      //      
      logger.info("Zipping directory {}.", f.getName());
      try {
        fZip = Files.zipDirectoryToDirectory(f, getTmpDirectory());
        logger.info("Zipped directory  {} to {}.", f.getName(), fZip.getAbsolutePath());
      } catch (Exception ex) {
        logger.warn("Error zipping: {}.", ex.getMessage());
        continue;
      }
      
      //
      //Remove the source
      //
      logger.info("Removing {}.", f.getName());
      try {
        FileUtils.forceDelete(f);
        logger.info("Removed  {}.", f.getName());
      } catch (Exception ex) {
        logger.warn("Error removing: {}.", ex.getMessage());
      }
      
      
      //
      //Move the zipped file back to original root
      //
      logger.info("Moving {} to {}.", fZip.getAbsolutePath(), getRootDirectory());
      try {
        FileUtils.moveFileToDirectory(fZip, getRootDirectory(), false);
        logger.info("Moved  {} to {}.", fZip.getAbsolutePath(), getRootDirectory());
      } catch (FileExistsException fex) {
        logger.info("File {} already exists.", fZip.getName());
        try {
          FileUtils.forceDelete(fZip);
        } catch (IOException ioex) {
          logger.warn("Error removing {}: {}.", fZip.getAbsolutePath(), ioex.getMessage());
        }
      } catch (Exception ex) {
        logger.warn("Error moving: {}.", ex.getMessage());
      }



    }    
    
    
    for (File f : getFilesToDelete(dt.minus(getDeletePeriod()))) {

      logger.info("Removing file {}.", f.getAbsolutePath());
      try {
        FileUtils.forceDelete(f);
        logger.info("Removed  file {}.", f.getAbsolutePath());
      } catch (Exception ex) {
        logger.warn("Error removing: {}.", ex.getMessage());
      }
      
    }
    
    
  }
  
  
  //
  // rootDir zipPeriod deletePeriod
  // C:\tmp\DateTimeDirectoryArchiver\test P1D P2D
  //
  public static void main(String[] args)
    throws IOException
  {
    checkArgument(args.length > 3, "Not enough args.");
    String rootDirString = args[0];
    String tmpDirString = args[1];
    String zipPeriodString = args[2];
    String deletePeriodString = args[3];
    

    DateTime dtNow = new DateTime();
    DateTimeDirectoryArchiver dtda = new DateTimeDirectoryArchiver();
    dtda.setRootDirectory(new File(rootDirString));
    dtda.setTmpDirectory(new File(tmpDirString));
    dtda.setZipPeriod(JodaTime.parsePeriod(zipPeriodString));
    dtda.setDeletePeriod(JodaTime.parsePeriod(deletePeriodString));
    
    dtda.archive(dtNow);
  }
  
}

