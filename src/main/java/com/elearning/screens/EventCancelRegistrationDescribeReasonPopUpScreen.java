package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_TO_EVENT_POP_UP_SCREEN_SUBMIT_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class EventCancelRegistrationDescribeReasonPopUpScreen extends AbstractScreen {

  private final Element submitButton = Element.byXpath
      (String.format("//*[@class='uui-caption'][text()='%s']",
          getValueOf(REGISTRATION_TO_EVENT_POP_UP_SCREEN_SUBMIT_BUTTON)));
  private final Element textAreaForDescribeCancelReason = Element.byXpath(
      "//textarea[contains(@class,'TextArea-module')]");

  @Override
  public boolean isScreenLoaded() {
    return submitButton.isDisplayed();
  }

  public EventCancelRegistrationDescribeReasonPopUpScreen typeReasonOfCancelRegistration(
      String reason) {
    textAreaForDescribeCancelReason.type(reason);
    return this;
  }

  public EventPreviewScreen clickSubmitButton() {
    submitButton.click();
    return new EventPreviewScreen();
  }
}
