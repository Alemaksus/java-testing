package com.epmrdpt.framework.ui.webdriver;

import static com.epmrdpt.framework.loging.Log.logInfoMessage;

import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.screens.HeaderScreen;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {

  public static final File TESTNG_DIRECTORY = new File("target/surefire-reports");
  private List<File> screenshotList = new ArrayList<>();

  private WebDriver wrappedDriver;

  private static ThreadLocal<WebDriverSingleton> instance = new ThreadLocal<>();

  private WebDriverSingleton() {
    wrappedDriver = WebDriverFactory.getWebDriver();
  }

  public static synchronized WebDriverSingleton getInstance() {
    if (instance.get() == null) {
      instance.set(new WebDriverSingleton());
    }
    return instance.get();
  }

  public WebDriver getDriver() {
    return wrappedDriver;
  }

  public void closeDriver() {
    Log.logDebug("Stop browser");
    try {
      wrappedDriver.quit();
    } finally {
      instance.remove();
    }
  }

  public void takeScreenshot() {
    try {
      if (!TESTNG_DIRECTORY.exists()) {
        TESTNG_DIRECTORY.mkdirs();
      }
      File screenshot = File.createTempFile("screenshot", ".png", TESTNG_DIRECTORY);
      try (FileOutputStream stream = new FileOutputStream(screenshot)) {
        stream.write(((TakesScreenshot) wrappedDriver).getScreenshotAs(OutputType.BYTES));
      }
      screenshotList.add(screenshot);
    } catch (Throwable e) {
      logInfoMessage("Unable to save screenshot", e);
    }
  }

  public void scrollPageForScreenshot() {
    HeaderScreen headerScreen = new HeaderScreen();
    JavascriptExecutor js = (JavascriptExecutor) wrappedDriver;
    js.executeScript("window.scrollTo(0,0)");
    takeScreenshot();
    int times = (int) Math.floor(
        (double) headerScreen.getScreenScrollHeight() / headerScreen.getScreenClientHeight());
    for (int scroll = 0; scroll < times; scroll++) {
      js.executeScript(
          String.format("window.scrollBy(0,%d)", headerScreen.getScreenClientHeight()));
      takeScreenshot();
    }
    String currentUrl = wrappedDriver.getCurrentUrl();
    logInfoMessage(String.format("<a href='%s'>FAILED PAGE URL</a>)", currentUrl));
    writeUrlOnScreenShot(screenshotList.get(0), currentUrl);
    attachScreenshots();
  }

  public void attachScreenshots() {
    for (File file : screenshotList) {
      try {
        logInfoMessage(
            String.format(
                "<a href='%s'><img src='%s' height='25%%' width='25%%'/></a>",
                file.getCanonicalPath(), file.getCanonicalPath()));
      } catch (IOException e) {
        logInfoMessage("File absent or path isn't correct!");
      }
      logInfoMessage(file, "Saved screenshot is attached");
    }
    screenshotList.clear();
  }

  public void writeUrlOnScreenShot(File file, String url) {
    try {
      BufferedImage image = ImageIO.read(file);
      Graphics2D graphics = image.createGraphics();
      graphics.setColor(Color.RED);
      graphics.setFont(new Font("Calibri", Font.ITALIC, 50));
      graphics.drawString(url, 20, 100);
      ImageIO.write(image, "png", file);
    } catch (IOException e) {
      logInfoMessage("File absent or path isn't correct!");
    }
  }
}
