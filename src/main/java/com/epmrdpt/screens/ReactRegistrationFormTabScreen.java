package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_EDUCATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_GENERAL_INFO_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_WORKS_EXPERIENCE_AND_SKILLS_LABEL;

import com.epmrdpt.framework.ui.element.Element;

public class ReactRegistrationFormTabScreen extends ReactAdditionalInfoTabScreen {

  private final String TEXT_LOCATOR_PATTERN = "//div[text()=\"%s\"]";
  private final String DROP_DOWN_LOCATOR_SELECTOR_PATTERN =
      "//div[@class='uui-popper']" + TEXT_LOCATOR_PATTERN;
  private final String INPUT_FORM_PATTERN = "//input[@placeholder='%s']";

  private Element registrationFormDDL = Element.byXpath(
      "//div[contains(@class, 'uui-input-box')]//div[contains(@class, 'uui-icon')]");
  private Element generalInfoLabel = Element
      .byXpath(String.format("(//div[text()='%s'])[last()]",
          getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_GENERAL_INFO_LABEL)));
  private Element educationLabel = Element
      .byXpath(TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_EDUCATION_LABEL));
  private Element worksExperienceAndSkillsLabel = Element
      .byXpath(TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_WORKS_EXPERIENCE_AND_SKILLS_LABEL));

  public boolean isElementDisplayedByName(String elementName) {
    return Element.byXpath(TEXT_LOCATOR_PATTERN, elementName).isDisplayed();
  }

  public boolean isGeneralInfoLabelDisplayed() {
    return generalInfoLabel.isDisplayed();
  }

  public boolean isEducationLabelDisplayed() {
    return educationLabel.isDisplayed();
  }

  public boolean isWorksExperienceAndSkillsLabelDisplayed() {
    return worksExperienceAndSkillsLabel.isDisplayed();
  }

  public boolean isFormDisplayedByName(String formName) {
    return Element.byXpath(INPUT_FORM_PATTERN, formName).isDisplayed();
  }

  public ReactRegistrationFormTabScreen clickRegistrationFormDDL() {
    registrationFormDDL.click();
    return this;
  }

  public ReactRegistrationFormTabScreen selectFormByName(String formName) {
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, formName).click();
    return this;
  }

  public ReactRegistrationFormTabScreen waitScreenLoading() {
    registrationFormDDL.waitForVisibility();
    return this;
  }
}
