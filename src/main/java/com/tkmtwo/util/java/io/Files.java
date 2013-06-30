package com.tkmtwo.util.java.io;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//import java.text.DecimalFormat;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
  
  
  
  public static void forceDelete(File f)
    throws IOException
  {
    FileUtils.forceDelete(f);
  }
  public static void forceDeleteOnExit(File f)
    throws IOException
  {
    FileUtils.forceDeleteOnExit(f);
  }







  public static void zipDirectoryAndDelete(String srcDir, String trgZip)
    throws IOException
  {
    File srcFile = new File(srcDir);
    File trgFile = new File(trgZip);
    zipDirectory(srcFile, trgFile);
    FileUtils.deleteDirectory(srcFile);
  }
  public static void zipDirectoryAndDelete(String srcDir)
    throws IOException
  {
    File srcFile = new File(srcDir);
    zipDirectory(srcFile);
    FileUtils.deleteDirectory(srcFile);
  }
  public static void zipDirectoryAndDelete(File srcDir)
    throws IOException
  {
    zipDirectory(srcDir);
    FileUtils.deleteDirectory(srcDir);
  }


  public static void zipDirectory(String srcDir, String trgZip)
    throws IOException
  {
    zipDirectory(new File(srcDir), new File(trgZip));
  }
  public static void zipDirectory(String srcDir)
    throws IOException
  {
    zipDirectory(new File(srcDir));
  }
  public static void zipDirectory(File srcDir)
    throws IOException
  {
    File parentDir = srcDir.getParentFile();
    String trgFileName = srcDir.getName() + ".zip";
    
    if (parentDir == null) {
      zipDirectory(srcDir, new File(trgFileName));
    } else {
      zipDirectory(srcDir, new File(parentDir, trgFileName));
    }

    
  }
  

  public static void checkExistingDirectory(File f)
  {
    checkNotNull(f, "Directory object may not be null.");
    checkArgument(f.exists(), "Directory %s does not exist.", f.getAbsolutePath());
    checkArgument(f.isDirectory(), "File %s is not a directory.", f.getAbsolutePath());
  }
  

  public static File zipDirectoryToDirectory(File srcDir, File trgDir)
    throws IOException
  {
    checkExistingDirectory(srcDir);
    checkExistingDirectory(trgDir);

    
    String trgFileName = srcDir.getName() + ".zip";
    File trgZip = new File(trgDir, trgFileName);
    zipDirectory(srcDir, trgZip);
    return trgZip;
  }
  
  
  
  private static void zipDirectory(File srcDir,
                                   File trgZip)
    throws IOException
  {
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    ZipArchiveOutputStream zaos = null;
    try {
      fos = new FileOutputStream(trgZip);
      bos = new BufferedOutputStream(fos);
      zaos = new ZipArchiveOutputStream(bos);
      zaos.setLevel(1); //BEST_SPEED
      addToZip(zaos, srcDir, "");
    } finally {
      zaos.finish();
      zaos.close();
      bos.close();
      fos.close();
    }
  }
  
  private static void addToZip(ZipArchiveOutputStream zaos, File file, String base)
    throws IOException
  {
    String entryName = base + file.getName();
    ZipArchiveEntry zae = new ZipArchiveEntry(file, entryName);
    zaos.putArchiveEntry(zae);
    
    if (file.isFile()) {
      FileInputStream fis = null;
      try {
        fis = new FileInputStream(file);
        IOUtils.copy(fis, zaos);
        zaos.closeArchiveEntry();
      } finally {
        IOUtils.closeQuietly(fis);
      }

    } else {
      zaos.closeArchiveEntry();
      File[] childFiles = file.listFiles();
      if (childFiles != null) {
        for (File childFile : childFiles) {
          addToZip(zaos, childFile, entryName + "/");
        }
      }
    }
    
  }
  
  
  
  
}

