package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactEventHeaderScreen extends AbstractScreen {

  private final Element detailsButton = Element.byXpath("//a[contains(@href,'overview')]");
  private final Element attendeesButton = Element.byXpath("//a[contains(@href,'attendees')]");

  @Override
  public boolean isScreenLoaded() {
    return isDetailsButtonDisplayed();
  }

  public boolean isDetailsButtonDisplayed() {
    return detailsButton.isDisplayed();
  }

  public ReactEventDetailsScreen clickDetailsButton() {
    detailsButton.waitForClickableAndClick();
    return new ReactEventDetailsScreen();
  }

  public ReactEventAttendeesScreen clickAttendeesButton() {
    attendeesButton.waitForClickableAndClick();
    return new ReactEventAttendeesScreen();
  }

  public String getAttendeesButtonText() {
    return attendeesButton.getText();
  }

  public String getDetailsButtonText() {
    return detailsButton.getText();
  }
}
