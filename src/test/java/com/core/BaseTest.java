package com.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

abstract public class BaseTest {

  public void setUp() {
    WebDriverManager.chromedriver().setup();
    Configuration.browser = "chrome";
    Configuration.webdriverLogsEnabled = true;
    Configuration.browserSize = "1920x 1080";
    Configuration.headless = false;
  }

  @Before
  public void init() {
    setUp();
  }

  @After
  public void tearDown() {
    Selenide.closeWebDriver();
  }
}

