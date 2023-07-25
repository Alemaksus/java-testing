package com.epmrdpt.framework.ui.webdriver;

import static com.epmrdpt.framework.ui.AbstractScreen.DEFAULT_TIME_OUT_FOR_PAGE_REFRESH;
import static com.epmrdpt.framework.ui.AbstractScreen.SHORT_TIME_OUT_IN_SECONDS;

import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.utils.FileUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {

  private WebDriverFactory() {
  }

  private static String HOST = "http://localhost:4445";
  private static String DOWNLOADS_PATH = FileUtils.getDownloadedFilePath();

  public static WebDriver getWebDriver() {
    RemoteWebDriver webDriver = null;
    DesiredCapabilities capabilities;
    switch (System.getProperty("browser")) {
      case "chrome":
        capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-popup-blocking");
        HashMap<String, Object> chromePref = new HashMap<>();
        chromePref.put("profile.default_content_settings.popups", 0);
        chromePref.put("download.directory_upgrade", true);
        chromePref.put("download.default_directory", DOWNLOADS_PATH);
        options.setExperimentalOption("prefs", chromePref);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        try {
          webDriver = new RemoteWebDriver(new URL(HOST), options);
          webDriver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(SHORT_TIME_OUT_IN_SECONDS));
          webDriver.manage().timeouts()
              .pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH));
          webDriver.manage().timeouts()
              .scriptTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH));
          webDriver.manage().window().maximize();
          Log.logInfoMessage("Current browser is Chrome. Screen resolution is: " +
              webDriver.manage().window().getSize());
        } catch (MalformedURLException e) {
          Log.logInfoMessage("URL is not correct ");
        }
        break;
      case "firefox":
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", DOWNLOADS_PATH);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
            "application/vnd.ms-excel.12");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.openFile",
            "application/vnd.ms-excel.12");
        firefoxOptions.setProfile(profile);
        try {
          webDriver = new RemoteWebDriver(new URL(HOST), firefoxOptions);
          webDriver.manage().window().maximize();
          Log.logInfoMessage("Current browser is FireFox. Screen resolution is: " +
              webDriver.manage().window().getSize());
        } catch (MalformedURLException e) {
          Log.logInfoMessage("URL is not correct ");
        }
        break;
      default:
        throw new RuntimeException("No support ");
    }
    return webDriver;
  }
}
