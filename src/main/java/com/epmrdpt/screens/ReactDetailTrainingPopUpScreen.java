package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactDetailTrainingPopUpScreen extends AbstractScreen {

  private static final Element NOTIFICATION_POPUP = Element.byXpath("uui-snackbar-item-self");
  private Element closeNotificationPopUpButton = Element.byXpath(
      "//div[contains(@class,'snackbar-item')]//div[contains(@class,'button')]/div");

  @Override
  public boolean isScreenLoaded() {
    return NOTIFICATION_POPUP.isDisplayed();
  }

  public ReactDetailTrainingScreen closeNotificationPopUp() {
    closeNotificationPopUpButton.clickJs();
    return new ReactDetailTrainingScreen();
  }
}
