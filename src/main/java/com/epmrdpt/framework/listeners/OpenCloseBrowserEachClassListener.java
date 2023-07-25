package com.epmrdpt.framework.listeners;

import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;

import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import org.testng.IClassListener;
import org.testng.ITestClass;

public class OpenCloseBrowserEachClassListener implements IClassListener {

  @Override
  public void onBeforeClass(ITestClass testClass) {
    openHomePage();
  }

  @Override
  public void onAfterClass(ITestClass testClass) {
    Log.logInfoMessage("Close browser");
    WebDriverSingleton.getInstance().closeDriver();
  }

  public static void openHomePage() {
    WebDriverSingleton.getInstance().getDriver().get(getEnv());
    FooterScreen footerScreen = new FooterScreen();
    if (footerScreen.isAllCookiesMessageDisplayed()) {
      footerScreen
          .clickAcceptAllCookiesButton()
          .waitAllCookiesMessageForInVisibility();
    }
    new LanguageSwitchingService().setLanguageAccordingToLocaleSettings();
    Log.logInfoMessage("Open browser");
  }
}
