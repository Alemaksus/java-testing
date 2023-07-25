package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON;
import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactStatusChangePopUpScreen extends AbstractScreen {

  private final String POPUP_LOCATOR_PATTERN = "//div[contains(@class, 'uui-modal-window')]";
  private final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private final String BUTTON_LOCATOR_PATTERN = "//div[contains(@class,'button')]";
  private final Element statusChangeTitle = Element.byXpath(
      POPUP_LOCATOR_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP));
  private final Element crossButton = Element.byXpath(POPUP_LOCATOR_PATTERN + "//*[name()='use']");
  private final Element statusChangeMessageArea =
      Element.byXpath(POPUP_LOCATOR_PATTERN + "/div[2]/div");
  private final Element cancelButton = Element.byXpath(
      POPUP_LOCATOR_PATTERN + BUTTON_LOCATOR_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON));
  private final Element confirmationOfStatusChangingButton =
      Element.byXpath(format("(%s %s)[last()]", POPUP_LOCATOR_PATTERN, BUTTON_LOCATOR_PATTERN));

  public ReactStatusChangePopUpScreen waitScreenLoading() {
    crossButton.waitForVisibility();
    return this;
  }

  public Boolean isCrossButtonDisplayed() {
    return crossButton.isDisplayed();
  }

  public String getStatusChangeMessageAreaText() {
    return statusChangeMessageArea.getText();
  }

  public String getStatusChangeTitleText() {
    return statusChangeTitle.getText();
  }

  public String getCancelButtonText() {
    return cancelButton.getText();
  }

  public String getConfirmationOfChangingStatusButtonText() {
    return confirmationOfStatusChangingButton.getText();
  }

  public ReactDetailTrainingScreen clickConfirmationOfChangingStatusButton() {
    confirmationOfStatusChangingButton.click();
    return new ReactDetailTrainingScreen();
  }

  @Override
  public boolean isScreenLoaded() {
    return cancelButton.isDisplayed();
  }
}
