package com.epmrdpt.framework.listeners;

import com.epam.reportportal.testng.BaseTestNGListener;
import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import org.testng.ITestResult;

public class ReportPortalListener extends BaseTestNGListener {

  public ReportPortalListener() {
    super(new ReportPortalParameterOverrideTestNgService());
  }

  @Override
  public void onTestFailure(ITestResult testResult) {
    WebDriverSingleton.getInstance().scrollPageForScreenshot();
    super.onTestFailure(testResult);
  }

  @Override
  public void onConfigurationFailure(ITestResult testResult) {
    WebDriverSingleton.getInstance().scrollPageForScreenshot();
    super.onConfigurationFailure(testResult);
  }
}
