package com.epmrdpt.framework.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {

  public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
    return (driver -> (Boolean) ((JavascriptExecutor) driver)
        .executeScript("return !!window.jQuery && window.jQuery.active == 0;"));
  }

  public static ExpectedCondition<Boolean> jsReadyStateCompleted() {
    return (driver -> (Boolean) ((JavascriptExecutor) driver)
        .executeScript("return document.readyState == 'complete';"));
  }
}
