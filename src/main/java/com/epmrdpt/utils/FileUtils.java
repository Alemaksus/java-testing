package com.epmrdpt.utils;

import static com.epmrdpt.framework.loging.Log.logInfoMessage;
import static com.epmrdpt.framework.ui.AbstractScreen.DEFAULT_TIME_OUT_FOR_PAGE_REFRESH;
import static java.lang.String.format;

import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.Duration;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUtils {

  public static final String FILE_DELIMITER = FileSystems.getDefault().getSeparator();
  private static File file;

  public static File getNewFile(String path) {
    file = new File(path);
    file.getParentFile().mkdir();
    try {
      if (file.createNewFile()) {
        logInfoMessage("File created successfully at given path: " + path);
      } else {
        logInfoMessage("File already exists!");
      }
    } catch (IOException e) {
      logInfoMessage("File couldn't be created with given path: " + path);
    }
    return file;
  }

  public static File getNewFile(String filePath, String fileName) {
    return getNewFile(filePath + FILE_DELIMITER + fileName);
  }

  public static void deleteFile(String path) {
    file = new File(path);
    if (file.exists()) {
      if (file.delete()) {
        logInfoMessage("File deleted successfully at given path: " + path);
      } else {
        logInfoMessage("File couldn't be deleted at given path: " + path);
      }
    }
  }

  public static void deleteFile(String filePath, String fileName) {
    deleteFile(filePath + FILE_DELIMITER + fileName);
  }

  public static void deleteFile(File file) {
    if (file.exists()) {
      try {
        if (file.delete()) {
          logInfoMessage("File deleted successfully at: " + file.getAbsolutePath());
        } else {
          logInfoMessage("File couldn't be deleted at: " + file.getCanonicalPath());
        }
      } catch (IOException e) {
        logInfoMessage("File absent or path isn't correct!");
      }
    }
  }

  public static void waitForFileExistence(String path) {
    waitForFileExistence(path, DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
  }

  public static void waitForFileExistence(String filePath, String fileName) {
    waitForFileExistence(filePath + FILE_DELIMITER + fileName);
  }

  public static void waitForFileExistence(String path, int timeOut) {
    file = new File(path);
    WebDriver webDriver = WebDriverSingleton.getInstance().getDriver();
    logInfoMessage("Waiting for file to exist at path: " + path);
    try {
      new WebDriverWait(webDriver, Duration.ofSeconds(timeOut))
          .until((ExpectedCondition<Boolean>) webdriver -> file.exists());
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }

  public static void waitForFileExistence(String filePath, String fileName, int timeOut) {
    waitForFileExistence(filePath + FILE_DELIMITER + fileName, timeOut);
  }

  public static boolean isFileExists(String path) {
    file = new File(path);
    logInfoMessage("Check if file exists at path: " + path);
    return file.exists();
  }

  public static boolean isFileExists(String filePath, String fileName) {
    return isFileExists(filePath + FILE_DELIMITER + fileName);
  }

  public static String convertImageToBase64(String path) {
    String encodedBase64String = null;
    File file = new File(path);
    try (FileInputStream fileInputStreamReader = new FileInputStream(file)) {
      byte[] bytes = new byte[(int) file.length()];
      fileInputStreamReader.read(bytes);
      encodedBase64String = Base64.encodeBase64String(bytes);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      logInfoMessage("File couldn't be found with given path: " + path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return encodedBase64String;
  }

  public static int getNumberOfFilesInZipArchive(String filePath, String fileName) {
    int numberOfFiles = 0;
    try (ZipFile zipFile = new ZipFile(new File(filePath, fileName))) {
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
      while (entries.hasMoreElements()) {
        if (!entries.nextElement().isDirectory()) {
          numberOfFiles++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return numberOfFiles;
  }

  public static String getDownloadedFilePath() {
    String[] pathNames = {"src", "test", "downloads"};
    String pathSecondPart = String.join(FILE_DELIMITER, pathNames);
    return format("%s%s%s", System.getProperty("user.dir"),FILE_DELIMITER, pathSecondPart);
  }

  public static String getImportDocumentPath(String fileName) {
    String pathSecondPart;
    String[] pathNames = {"src", "test", "testdata"};
    pathSecondPart =
        String.join(FILE_DELIMITER, pathNames) + FILE_DELIMITER + fileName;
    return format("%s%s%s",System.getProperty("user.dir"), FILE_DELIMITER , pathSecondPart);
  }

  public static String getImportDocumentFolderPath() {
    String pathSecondPart;
    String[] pathNames = {"src", "test", "testdata"};
    pathSecondPart =
        String.join(FILE_DELIMITER, pathNames) + FILE_DELIMITER;
    return format("%s%s%s", System.getProperty("user.dir"), FILE_DELIMITER, pathSecondPart);
  }

  public static String getFileDelimiter() {
    return FILE_DELIMITER;
  }
}
