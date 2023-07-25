package com.epmrdpt.framework.ui.pseudoelement;

import static java.lang.String.format;

import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PseudoElement {

  public final WebDriver webDriver = WebDriverSingleton.getInstance().getDriver();

  public String getPseudoElementCssPropertyValue(String elementCssSelector,
      String propertyName, PseudoElementEnum pseudoElement) {
    return getPseudoElementCssPropertyValueByIndex(elementCssSelector, propertyName,
        pseudoElement, 0);
  }

  public String getPseudoElementCssPropertyValueByIndex(String elementCssSelector,
      String propertyName, PseudoElementEnum pseudoElement, int index) {
    String script = format(
        "return window.getComputedStyle(document.querySelectorAll('%s')[%s], '%s').getPropertyValue('%s')",
        elementCssSelector, index, pseudoElement.getPseudoClass(), propertyName);
    JavascriptExecutor js = (JavascriptExecutor) webDriver;
    return (String) js.executeScript(script);
  }
}
