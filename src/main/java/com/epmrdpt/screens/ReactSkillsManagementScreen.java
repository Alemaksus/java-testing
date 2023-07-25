package com.epmrdpt.screens;

import static com.epmrdpt.utils.RandomUtils.getRandomNumber;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactSkillsManagementScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";
  private static final String DELETE_BUTTON_PATTERN = TEXT_LOCATOR_PATTERN +
      "/ancestor::div[contains(@class,'uui-table-row')]//div[4]//*[local-name()='svg']";

  private Element skillsListTitle = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Skills list"));
  private Element skillName = Element.byXpath(
      "//div[contains(@class,'uui-table-row')]/descendant::a[contains(@href,'/Management#!/skill/')][1]");
  private Element createSkillDescriptionButton = Element.byXpath(
    String.format(TEXT_LOCATOR_PATTERN, "Create skill description"));
  private final Element popUpWindow = Element.byXpath(
      "//div[contains(@class, 'uui-modal-window')]");
  private final Element popUpOkButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Ok");
  private final Element spinnerContainer = Element.byXpath(
      "//*[contains(@class, 'uui-spinner-container')]");

  @Override
  public boolean isScreenLoaded() {
    return skillsListTitle.isDisplayed();
  }

  public ReactSkillsManagementScreen waitScreenLoading() {
    skillsListTitle.waitForVisibility();
    return this;
  }

  public EditSkillDescriptionScreen clickRandomSkillInSkillsList() {
    int skillIndex = getRandomNumber(skillName.getElements().size());
    skillName.getElements().get(skillIndex).click();
    return new EditSkillDescriptionScreen();
  }

  public ReactCreateSkillDescriptionScreen clickCreateSkillDescriptionButton() {
    createSkillDescriptionButton.click();
    return new ReactCreateSkillDescriptionScreen();
  }

  public ReactSkillsManagementScreen waitPopUpWindowVisibility() {
    popUpWindow.waitForVisibility();
    return this;
  }

  public ReactSkillsManagementScreen clickDeleteButtonBySkillName(String skillName) {
    Element.byXpath(DELETE_BUTTON_PATTERN, skillName).click();
    return this;
  }

  public boolean isPopUpWindowDisplayed() {
    return popUpWindow.isDisplayed();
  }

  public ReactSkillsManagementScreen clickPopUpOkButton() {
    popUpOkButton.click();
    return this;
  }

  public List<String> getSkillNames() {
    return skillName.getElementsText();
  }

  public ReactSkillsManagementScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }
}
