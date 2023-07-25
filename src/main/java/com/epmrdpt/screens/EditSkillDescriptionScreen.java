package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class EditSkillDescriptionScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";
  private Element editSkillDescriptionTitle = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Edit skill description"));
  private Element titleField = Element.byXpath("//div[contains(@data-id,'Title')]//input");
  private Element successfulChangePopUp = Element.byXpath(TEXT_LOCATOR_PATTERN,
      "Skill description has been changed.");
  private Element saveChangesButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Save changes");

  @Override
  public boolean isScreenLoaded() {
    return editSkillDescriptionTitle.isDisplayed();
  }

  public EditSkillDescriptionScreen typeTitleField(String value) {
    titleField.nativeClearAndType(value);
    return this;
  }

  public EditSkillDescriptionScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public boolean isSuccessfulChangesPopUpDisplayed() {
    return successfulChangePopUp.isDisplayed();
  }
}
