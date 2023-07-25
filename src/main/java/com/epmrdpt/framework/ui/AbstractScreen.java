package com.epmrdpt.framework.ui;

import static com.epmrdpt.framework.loging.Log.logInfoMessage;
import static java.lang.String.format;

import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractScreen {

  public static final int SMALL_TIME_OUT_IN_SECONDS = 2;
  public static final int SHORT_TIME_OUT_IN_SECONDS = 5;
  public static final int DEFAULT_TIME_OUT_IN_SECONDS = 20;
  public static final int MIDDLE_TIME_OUT_IN_SECONDS = 15;
  public static final int LONG_TIME_OUT_IN_SECONDS = 30;
  public static final int DEFAULT_TIME_OUT_FOR_PAGE_REFRESH = 31;
  public static final int DEFAULT_TIMEOUT_FOR_PAGE_LOAD = 60;
  public static final int LONG_TIMEOUT_FOR_PAGE_LOAD = 90;
  public static final int ONE_DECIMAL_PLACE = 1;
  public static final int TWO_DECIMAL_PLACE = 2;
  public static final String PLACEHOLDER_CSS_PROPERTY = "placeholder";
  public static final String TEXT_CONTENT_CSS_PROPERTY = "textContent";
  public static final String DATA_PLACEHOLDER_CSS_PROPERTY = "data-placeholder";
  public static final String VALUE_CSS_PROPERTY = "value";
  public static final String FILTER_CSS_PROPERTY = "filter";
  public static final String FILL_CSS_PROPERTY = "fill";
  public static final String ATTRIBUTE_SUBJECT = "subject";
  public static final String SUBJECT_VALUE_BEFORE_PAGE_LOAD = "{{newsDetails.Subject}}";
  public static final String STYLE_PROPERTY = "style";

  protected final WebDriver webDriver = WebDriverSingleton.getInstance().getDriver();

  protected JavascriptExecutor getJavascriptExecutor() {
    return (JavascriptExecutor) webDriver;
  }

  public abstract boolean isScreenLoaded();

  public void clickBackButton() {
    logInfoMessage("Pressing browser's 'Back' button");
    webDriver.navigate().back();
  }

  public void clickRefreshButton() {
    logInfoMessage("Pressing browser's 'Refresh' button");
    webDriver.navigate().refresh();
  }

  public void scrollToTop() {
    logInfoMessage("Scroll page to top");
    getJavascriptExecutor().executeScript("window.scrollTo(0, 0);");
  }

  public void scrollToBottom() {
    logInfoMessage("Scroll page to bottom");
    getJavascriptExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight)");
  }

  public void switchToLastWindow() {
    String currentWindows = webDriver.getWindowHandle();
    List<String> listWindows = new ArrayList<>(webDriver.getWindowHandles());
    logInfoMessage("Switch to the last window from %s window", currentWindows);
    if (currentWindows.equals(listWindows.get(0))) {
      webDriver.switchTo().window(listWindows.get(listWindows.size() - 1));
    }
  }

  public void switchToFirstWindowIfMoreThanOne() {
    List<String> list = new ArrayList<>(webDriver.getWindowHandles());
    if (list.size() > 1) {
      webDriver.switchTo().window(list.get(0));
      logInfoMessage("Switch to the first window");
    }
  }

  public String getCurrentWindowUrl() {
    String currentWindowUrl = webDriver.getCurrentUrl();
    logInfoMessage("Current window url is '%s'", currentWindowUrl);
    return currentWindowUrl;
  }

  public Set<Cookie> getCookies() {
    logInfoMessage("Retrieving cookies");
    return webDriver.manage().getCookies();
  }

  public void deleteAllCookies() {
    webDriver.manage().deleteAllCookies();
    logInfoMessage("All cookies were deleted");
  }

  public int getScreenScrollHeight() {
    return ((Number) getJavascriptExecutor().executeScript("return document.body.scrollHeight"))
        .intValue();
  }

  public int getScreenClientHeight() {
    return ((Number) getJavascriptExecutor().executeScript("return document.body.clientHeight"))
        .intValue();
  }

  public void openPage(String url) {
    logInfoMessage("Loading web-page: " + url);
    webDriver.get(url);
  }

  public void openPageInNewTab(String url) {
    logInfoMessage("Opening web-page in new tab: " + url);
    getJavascriptExecutor().executeScript(format("window.open('%s');", url));
    switchToLastWindow();
  }

  public void waitTextToBePresentInUrl(String text) {
    new WebDriverWait(webDriver, Duration.ofSeconds(MIDDLE_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.urlContains(text));
  }

  public void setWindowSize(int width, int height) {
    logInfoMessage(format("Setting window size to width:%d height:%d", width, height));
    webDriver.manage().window().setSize(new Dimension(width, height));
  }

  public void switchToDefault() {
    webDriver.switchTo().defaultContent();
  }

  public void closeBrowser() {
    WebDriverSingleton.getInstance().closeDriver();
  }

  public void closeLastWindowAndSwitchToPreviousIfMoreThanOne() {
    List<String> list = new ArrayList<>(webDriver.getWindowHandles());
    if (list.size() > 1) {
      webDriver.switchTo().window(list.get(list.size() - 1)).close();
      logInfoMessage("Switched to the last window and closed");
      webDriver.switchTo().window(list.get(list.size() - 2));
      logInfoMessage("Switch to the previous window");
    }
  }

  public void waitForUrlContainsText(String text) {
    new WebDriverWait(webDriver,Duration.ofSeconds(MIDDLE_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.urlContains(text));
  }
}
