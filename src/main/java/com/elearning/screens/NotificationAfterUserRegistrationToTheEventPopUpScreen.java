package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class NotificationAfterUserRegistrationToTheEventPopUpScreen extends AbstractScreen {

  private final Element popUpOkButton = Element.byXpath("//*[@class='uui-caption'][text()='Ok']");

  @Override
  public boolean isScreenLoaded() {
    return popUpOkButton.isDisplayed();
  }

  public EventPreviewScreen clickOkButton() {
    popUpOkButton.waitForClickableAndClick();
    return new EventPreviewScreen();
  }
}
