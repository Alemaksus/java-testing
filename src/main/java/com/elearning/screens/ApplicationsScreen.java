package com.epmrdpt.screens;

import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import javax.swing.text.html.HTML.Attribute;

public class ApplicationsScreen extends AbstractScreen {

  private static final String APPLICATION_LOCATOR_PATTERN = "//span[text()='%s']//ancestor::section[@class='training__card']";
  private static final String CANCEL_BUTTON_LOCATOR_PATTERN =
      APPLICATION_LOCATOR_PATTERN + "//button[contains(@class,'cancel')]";
  private static final String PASS_ENGLISH_TEST_LINK_LOCATOR_PATTERN =
      "//a[contains(text(),'%s')]/following-sibling::div[@ng-if='application.IsEnglishNeeded']/div[2]";
  private static final String PASS_ENGLISH_TEST_HINT_LOCATOR_PATTERN =
      "//a[contains(text(),'%s')]/following-sibling::div[@ng-if='application.IsEnglishNeeded']/p";
  private static final String PASS_REGISTRATION_TEST_LINK_LOCATOR_PATTERN =
      "//a[contains(text(),'%s')]/following-sibling::div[@ng-if='application.IsRegistrationNeeded']/div[2]";
  private static final String PASS_REGISTRATION_TEST_HINT_LOCATOR_PATTERN =
      "//a[contains(text(),'%s')]/following-sibling::div[@ng-if='application.IsRegistrationNeeded']/p";
  private static final String TRAINING_STATUS_LOCATOR_PATTERN = "//a[contains(text(),'%s')]/following-sibling::div//p";
  private static final String TRAINING_NAME_BUTTON_LOCATOR_PATTERN = "//div[@class='training__info']/a[contains(text(),'%s')]";
  private static final String DEPARTMENT_AFFILIATE_LOCATOR_PATTERN = "//span[text()='%s']/parent::div/div[@ng-show='isDepartment(application)']";
  private static final String TRAINING_LOGO_LOCATOR_PATTERN = "//span[text()='%s']/ancestor::div[@class='training__card-inner-wrap']/img";
  private static final String TRAINING_NAME_LOCATOR_PATTERN = "//div[@class='training__info']/span[text()='%s']";
  private static final String NG_HIDE_CLASS_VALUE = "ng-hide";
  private static final String ATTRIBUTE_CLASS = "class";
  private static final String ACTIVE_TAB_HTML_VALUE = "active-tab";

  private Element activeApplicationTab = Element.byXpath("//tabs/div[1]");
  private Element inactiveApplicationsTab = Element.byXpath("//tabs/div[2]");
  private Element completedApplicationsTab = Element.byXpath("//tabs/div[3]");
  private Element activeSectionStatusTitleElement = Element.byXpath("//section/h3");
  private Element activeSectionStatusFallbackElement = Element.byXpath("//section/p");
  private Element applicationsHeaderElement = Element.byCss("tabs.applications__tabs");
  private Element trainingListElement = Element.byCss(".applications-wrap");
  private Element applicationsTitleElement = Element.byXpath("//header/h2");
  private Element englishTestPopUpOkButton = Element.byXpath("//button[@ng-click='ok()']");
  private Element registrationTestPopUpOkButton = Element.byCss("button.message-btn-ok");

  @Override
  public boolean isScreenLoaded() {
    return isTrainingListDisplayed();
  }

  public String getActiveSectionTitleText() {
    return activeApplicationTab.getText();
  }

  public String getInactiveSectionTitleText() {
    return inactiveApplicationsTab.getText();
  }

  public String getCompletedSectionTitleText() {
    return completedApplicationsTab.getText();
  }

  public ApplicationsScreen clickActiveTab() {
    activeApplicationTab.waitForClickable().click();
    return this;
  }

  public ApplicationsScreen clickInactiveTab() {
    inactiveApplicationsTab.waitForClickable().click();
    return this;
  }

  public ApplicationsScreen clickCompletedTab() {
    completedApplicationsTab.waitForClickable().click();
    return this;
  }

  public String getActiveSectionStatusTitleText() {
    return activeSectionStatusTitleElement.getText();
  }

  public String getActiveSectionStatusFallbackText() {
    return activeSectionStatusFallbackElement.getText();
  }

  public ApplicationsScreen waitScreenLoading() {
    applicationsHeaderElement.waitForVisibility();
    return this;
  }

  public boolean isTrainingListDisplayed() {
    return trainingListElement.isDisplayed();
  }

  public boolean isApplicationsTitleDisplayed() {
    return applicationsTitleElement.isDisplayed();
  }

  public boolean isActiveTabDisplayed() {
    return activeApplicationTab.isDisplayed();
  }

  public boolean isActiveTabDisplayedByDefault() {
    return activeApplicationTab.getAttributeValue(ATTRIBUTE_CLASS).contains(ACTIVE_TAB_HTML_VALUE);
  }

  public boolean isInactiveTabDisplayed() {
    return inactiveApplicationsTab.isDisplayed();
  }

  public boolean isCompletedTabDisplayed() {
    return completedApplicationsTab.isDisplayed();
  }

  public boolean isApplicationByNameDisplayed(String trainingName) {
    return Element.byXpath(APPLICATION_LOCATOR_PATTERN, trainingName).isDisplayed();
  }

  public ApplicationsScreen clickCancelButtonByTrainingName(String trainingName) {
    Element.byXpath(CANCEL_BUTTON_LOCATOR_PATTERN, trainingName).clickJs();
    return this;
  }

  public boolean isPassEnglishTestLinkByTrainingNameDisplayed(String trainingName) {
    return Element.byXpath(format(PASS_ENGLISH_TEST_LINK_LOCATOR_PATTERN, trainingName))
        .isDisplayed();
  }

  public String getPassEnglishTestByTrainingNameLinkText(String trainingName) {
    return Element.byXpath(format(PASS_ENGLISH_TEST_LINK_LOCATOR_PATTERN, trainingName))
        .getText();
  }

  public boolean isPassEnglishTestHintByTrainingNameDisplayed(String trainingName) {
    return Element.byXpath(format(PASS_ENGLISH_TEST_HINT_LOCATOR_PATTERN, trainingName))
        .isDisplayed();
  }

  public String getPassEnglishTestHintByTrainingNameText(String trainingName) {
    return Element.byXpath(format(PASS_ENGLISH_TEST_HINT_LOCATOR_PATTERN, trainingName))
        .getText();
  }

  public boolean isPassRegistrationTestLinkByTrainingNameDisplayed(String trainingName) {
    return Element.byXpath(format(PASS_REGISTRATION_TEST_LINK_LOCATOR_PATTERN, trainingName))
        .isDisplayed();
  }

  public String getPassRegistrationTestByTrainingNameLinkText(String trainingName) {
    return Element.byXpath(format(PASS_REGISTRATION_TEST_LINK_LOCATOR_PATTERN, trainingName))
        .getText();
  }

  public boolean isPassRegistrationTestHintByTrainingNameDisplayed(String trainingName) {
    return Element.byXpath(format(PASS_REGISTRATION_TEST_HINT_LOCATOR_PATTERN, trainingName))
        .isDisplayed();
  }

  public String getPassRegistrationTestHintByTrainingNameText(String trainingName) {
    return Element.byXpath(format(PASS_REGISTRATION_TEST_HINT_LOCATOR_PATTERN, trainingName))
        .getText();
  }

  public ApplicationsScreen clickPassRegistrationTestByTrainingNameLink(String trainingName) {
    Element.byXpath(format(PASS_REGISTRATION_TEST_LINK_LOCATOR_PATTERN, trainingName)).click();
    return this;
  }

  public ApplicationsScreen clickPassEnglishTestByTrainingNameLink(String trainingName) {
    Element.byXpath(format(PASS_ENGLISH_TEST_LINK_LOCATOR_PATTERN, trainingName))
        .clickJs();
    return this;
  }

  public ApplicationsScreen clickEnglishTestPopUpOkButton() {
    englishTestPopUpOkButton.clickJs();
    return this;
  }

  public String getStatusOfTrainingByTrainingNameText(String trainingName) {
    return Element.byXpath(TRAINING_STATUS_LOCATOR_PATTERN, trainingName).getText();
  }

  public boolean isTrainingNameButtonClickableByTrainingName(String trainingName) {
    return !Element.byXpath(TRAINING_NAME_BUTTON_LOCATOR_PATTERN, trainingName).getAttributeValue(
        Attribute.CLASS.toString()).contains(NG_HIDE_CLASS_VALUE);
  }

  public String getDepartmentAffiliateTrainingTextByTrainingName(String trainingName) {
    return Element.byXpath(DEPARTMENT_AFFILIATE_LOCATOR_PATTERN, trainingName).getText().trim();
  }

  public boolean isTrainingLogoDisplayedByTrainingName(String trainingName) {
    return Element.byXpath(TRAINING_LOGO_LOCATOR_PATTERN, trainingName).isDisplayed();
  }

  public boolean isDepartmentAffiliateTrainingDisplayedByTrainingName(String trainingName) {
    return Element.byXpath(TRAINING_NAME_LOCATOR_PATTERN, trainingName).isDisplayed();
  }

  public AbstractScreen clickRegistrationTestPopUpOkButton() {
    registrationTestPopUpOkButton.click();
    return this;
  }
}
