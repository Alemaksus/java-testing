package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_INTEGRATION_TAB_SCREEN_LINK_BY_CODE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_INTEGRATION_TAB_SCREEN_NOT_LINKED_TO_STAFFING_DESK_NOTE_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_INTEGRATION_TAB_SCREEN_SAVE_TRAINING_HINT;

import com.epmrdpt.framework.ui.element.Element;

public class ReactIntegrationTabScreen extends ReactAdditionalInfoTabScreen {

  private final String TEXT_LOCATOR_PATTERN = "//*[text()=\"%s\"]";

  private Element saveTrainingHint = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_INTEGRATION_TAB_SCREEN_SAVE_TRAINING_HINT));
  private Element linkByCodeButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_INTEGRATION_TAB_SCREEN_LINK_BY_CODE_BUTTON));
  private Element notLinkedToSDNote = Element.byXpath(TEXT_LOCATOR_PATTERN,
      REACT_INTEGRATION_TAB_SCREEN_NOT_LINKED_TO_STAFFING_DESK_NOTE_TEXT);

  public boolean isSaveTrainingHintDisplayed() {
    return saveTrainingHint.isDisplayed();
  }

  public boolean isLinkedByCodeButtonActive() {
    return linkByCodeButton.isClickable();
  }

  public boolean isLinkedToSDNoteNotDisplayed() {
    return notLinkedToSDNote.isNotDisplayed();
  }
}
