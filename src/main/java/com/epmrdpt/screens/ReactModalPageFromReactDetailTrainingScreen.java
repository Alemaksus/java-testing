package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_WARNING_MESSAGE;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactModalPageFromReactDetailTrainingScreen extends AbstractScreen {

  protected Element warningMessage = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_WARNING_MESSAGE));
  private Element confirmationButton = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_BUTTON));

  @Override
  public boolean isScreenLoaded() {
    return warningMessage.isDisplayed();
  }

  public ReactModalPageFromReactDetailTrainingScreen waitWarningMessageVisibility() {
    warningMessage.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen waitForClickableAndClickConfirmationButton() {
    confirmationButton.waitForClickableAndClick();
    return new ReactDetailTrainingScreen();
  }

  public void waitModalWindowClosed() {
    warningMessage.waitForInvisibility();
  }
}
