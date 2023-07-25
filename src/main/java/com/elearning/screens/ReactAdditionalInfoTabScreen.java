package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ADDITIONAL_INFO_TAB_SCREEN_DESCRIPTION_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ADDITIONAL_INFO_TAB_SCREEN_AUTOREPLY_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ADDITIONAL_INFO_TAB_SCREEN_GENERAL_INFO_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ADDITIONAL_INFO_TAB_SCREEN_INTEGRATION_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ADDITIONAL_INFO_TAB_SCREEN_REGISTRATION_FORM_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_ADDITIONAL_INFO_TAB_SCREEN_SURVEY_AND_TESTING_TAB;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactAdditionalInfoTabScreen extends AbstractScreen {

  protected final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";

  protected Element generalInfoTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_ADDITIONAL_INFO_TAB_SCREEN_GENERAL_INFO_TAB));
  private Element surveyAndTestingTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_ADDITIONAL_INFO_TAB_SCREEN_SURVEY_AND_TESTING_TAB));
  private Element descriptionTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_ADDITIONAL_INFO_TAB_SCREEN_DESCRIPTION_TAB));
  private Element autoreplyTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_ADDITIONAL_INFO_TAB_SCREEN_AUTOREPLY_TAB));
  private Element integrationTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_ADDITIONAL_INFO_TAB_SCREEN_INTEGRATION_TAB));
  private Element registrationFormTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_ADDITIONAL_INFO_TAB_SCREEN_REGISTRATION_FORM_TAB));

  @Override
  public boolean isScreenLoaded() {
    return generalInfoTab.isDisplayed();
  }

  public ReactSurveyAndTestingTabScreen clickSurveyAndTestingTabFromTrainingScreen() {
    surveyAndTestingTab.click();
    return new ReactSurveyAndTestingTabScreen();
  }

  public ReactDescriptionTabScreen clickDescriptionTabFromTrainingScreen() {
    descriptionTab.click();
    return new ReactDescriptionTabScreen();
  }

  public ReactAutoreplyTabScreen clickAutoreplyTabFromTrainingScreen() {
    autoreplyTab.click();
    return new ReactAutoreplyTabScreen();
  }

  public ReactRegistrationFormTabScreen clickRegistrationFormTabFromTrainingScreen() {
    registrationFormTab.click();
    return new ReactRegistrationFormTabScreen();
  }

  public ReactGeneralInfoTabScreen clickGeneralInfoTabFromTrainingScreen() {
    generalInfoTab.click();
    return new ReactGeneralInfoTabScreen();
  }

  public boolean isIntegrationTabDisplayedNoWait() {
    return integrationTab.isDisplayedNoWait();
  }

  public ReactIntegrationTabScreen clickIntegrationTabFromTrainingScreen() {
    integrationTab.click();
    return new ReactIntegrationTabScreen();
  }

  public boolean isGeneralInfoTabDisplayedNoWait() {
    return generalInfoTab.isDisplayedNoWait();
  }

  public boolean isDescriptionTabDisplayedNoWait() {
    return descriptionTab.isDisplayedNoWait();
  }

  public boolean isAutoReplyTabDisplayedNoWait() {
    return autoreplyTab.isDisplayedNoWait();
  }

  public boolean isRegistrationFormTabDisplayedNoWait() {
    return registrationFormTab.isDisplayedNoWait();
  }

  public boolean isSurveyAndTestingTabDisplayedNoWait() {
    return surveyAndTestingTab.isDisplayedNoWait();
  }
}
