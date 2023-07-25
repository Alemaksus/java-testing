package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class TimezoneMismatchPopUpScreen extends AbstractScreen {

  private static final String DDL_TIMEZONE_LOCATOR_PATTERN= "//li[text()='%s']";
  private final Element timezoneMismatchPopUp = Element.byXpath(
      "//p[contains(@class,'timezone-modal__text')]");
  private final Element timezoneSelectDropDownButton = Element.byXpath(
      "//a[@class='chosen-single']");
  private final Element timezoneDropDownMenuOpened = Element.byXpath("//div[contains(@class,'chosen-with-drop')]");
  private final Element successfulChangesPopUp = Element.byXpath(
      "//div[contains(@class,'status-popup')]");

  @Override
  public boolean isScreenLoaded() {
    return timezoneMismatchPopUp.isDisplayed();
  }

  public String getTimezoneMismatchPopUpText() {
    return timezoneMismatchPopUp.getText();
  }

  public TimezoneMismatchPopUpScreen clickTimezoneSelectDropDownButton() {
    timezoneSelectDropDownButton.click();
    return this;
  }

  public TimezoneMismatchPopUpScreen waitTimezoneDropDownMenuPresent() {
    timezoneDropDownMenuOpened.isPresent();
    return this;
  }

  public TimezoneMismatchPopUpScreen clickDropDownTimezoneItem(String timezone) {
    Element.byXpath(String.format(DDL_TIMEZONE_LOCATOR_PATTERN, timezone)).click();
    return this;
  }

  public TimezoneMismatchPopUpScreen waitSuccessfulChangesPopUpPresent() {
    successfulChangesPopUp.waitForPresence();
    return this;
  }
}
