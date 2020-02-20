package dev.riotjy.mobjy.util;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceWriter {

  final static Logger log = LoggerFactory.getLogger(ResourceWriter.class);

  public static boolean write(String filePath, String content) {


    try {
      File file = new File(filePath);
      if (file.exists()) {
        file.delete();
      }

      file.createNewFile();

      FileOutputStream fos = new FileOutputStream(file);
      
      fos.write(content.getBytes(), 0, content.length());
      fos.flush();
      fos.close();

    } catch (Exception e) {
      log.error("Error trying to write file " + filePath + " Exception: " + e.getMessage());
      return false;
    }
    
    return true;
  }
  
  public static boolean mkdirs(String path) {
    
    try {
      File file = new File(path);
      if (file.exists()) {
        file.delete();
      }

      file.mkdirs();

    } catch (Exception e) {
      log.error("Error trying to create directory " + path + " Exception: " + e.getMessage());
      return false;
    }
    
    return true;
  }

}
