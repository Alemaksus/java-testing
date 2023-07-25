package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.services.SettingsService;

public class SettingsScreen extends AbstractScreen {

  private static final String DDL_ITEM_LOCATOR_PATTERN = "//ul/li[text()='%s']";
  private static final String ANGULAR_LOCALE_LOCATOR_PATTERN = "//script[contains(@src,'locale_%s')]";
  private static final String DROP_DOWN_BUTTON_LOCATOR_PATTERN = "//div[@id='%s']/a[@class='chosen-single']";

  private Element settingsTitleSpan = Element.byXpath("//span[contains(@class,'top-title')]");
  private Element settingsContainerForm = Element.byXpath("//form");
  private Element languageDropDownButton = Element.byXpath(
      String.format(DROP_DOWN_BUTTON_LOCATOR_PATTERN, "select_language_chosen"));
  private Element languageDropDownMenu = Element.byXpath(
      "//div[@id='select_language_chosen']/div[@class='chosen-drop']");
  private Element languageUnderGeneralSettings = Element
      .byXpath("//div[@id='select_language_chosen']//span");
  private Element applySettingsButton = Element
      .byXpath("//div[@class='top']/descendant::a[@ng-click='submitChanges()']");
  private Element notificationLanguageDropDownButton = Element
      .byId("select_notification_language_chosen");
  private Element notificationLanguageDropDownMenu = Element
      .byXpath(
          "//div[contains(@class,'chosen-with-drop') and @id='select_notification_language_chosen']//div[@class='chosen-drop']");
  private Element notificationLanguageUnderNotificationSettings = Element
      .byXpath("//div[@class='form-wrapper'][2]//a[@class='chosen-single']/span");
  private Element emailNotificationCheckbox = Element
      .byXpath("//div[contains(@class,'checkbox--notification')]//span");
  private Element emailNotificationCheckboxSelector = Element
      .byCss("div.form-checkbox--notification>label>input.rd-checkbox:checked");
  private Element emailNotificationCheckboxLabel = Element
      .byXpath("//div[contains(@class,'checkbox--notification')]//label");
  private Element saveSearchOptionsCheckBox = Element
      .byXpath("//input[@*='currentUserData.NeedSaveFilters']");
  private Element saveSearchOptionsLabel = Element
      .byXpath("//input[@*='currentUserData.NeedSaveFilters']//parent::label");
  private Element getSaveSearchOptionsCheckBoxChecked = Element
      .byCss("form > div:nth-child(2) > div.form-checkbox input:checked");
  private final Element timezoneDropDownButton = Element.byXpath(
      String.format(DROP_DOWN_BUTTON_LOCATOR_PATTERN, "select_time_zone_chosen"));
  private final Element timezoneDropDownMenuOpened = Element.byXpath(
      "//div[contains(@class,'chosen-with-drop')]");

  @Override
  public boolean isScreenLoaded() {
    return isSettingsTitleDisplayed() && isSettingsContainerFormDisplayed();
  }

  private boolean isSettingsContainerFormDisplayed() {
    return settingsContainerForm.isDisplayed();
  }

  private boolean isSettingsTitleDisplayed() {
    return settingsTitleSpan.isDisplayed();
  }

  public SettingsScreen waitScreenLoading() {
    settingsContainerForm.waitForVisibility();
    languageUnderGeneralSettings.waitForTextToBePresent();
    return this;
  }

  public SettingsScreen clickLanguageDropDownButton() {
    languageDropDownButton.click();
    return this;
  }

  public SettingsScreen waitLanguageDropDownDisplayed() {
    languageDropDownMenu.waitForVisibility();
    return this;
  }

  public SettingsScreen clickDropDownListItem(String dropDownListItem) {
    Element.byXpath(DDL_ITEM_LOCATOR_PATTERN, dropDownListItem).click();
    return this;
  }

  public String getLanguageUnderGeneralSettings() {
    return languageUnderGeneralSettings.getText();
  }

  public boolean isSelectedLanguagePresent(String language) {
    return getLanguageUnderGeneralSettings().equals(language);
  }

  public String getNotificationLanguageUnderNotificationSettings() {
    return notificationLanguageUnderNotificationSettings.getText();
  }

  public boolean isSelectedNotificationLanguagePresent(String language) {
    return getNotificationLanguageUnderNotificationSettings().equals(language);
  }

  public SettingsScreen clickApplySettingsButton() {
    applySettingsButton.click();
    return this;
  }

  public String getSettingsTitleSpanText() {
    return settingsTitleSpan.getText();
  }

  public void waitUntilLanguageSwitchedFromPage(String language) {
    Element.byXpath(ANGULAR_LOCALE_LOCATOR_PATTERN, new SettingsService().getLanguageCode(language))
        .waitForPresence();
  }

  public SettingsScreen waitNotificationLanguageDropDownButtonDisplayed() {
    notificationLanguageDropDownButton.waitForVisibility();
    return this;
  }

  public SettingsScreen clickNotificationLanguageDropDownButton() {
    notificationLanguageDropDownButton.waitForClickableAndClick();
    return this;
  }

  public SettingsScreen waitEmailNotificationCheckboxVisibility() {
    emailNotificationCheckbox.waitForVisibility();
    return this;
  }

  public SettingsScreen waitNotificationLanguageDropDownMenuVisibility() {
    notificationLanguageDropDownMenu.waitForVisibility();
    return this;
  }

  public boolean isNotificationLanguageDropDownMenuDisplayed() {
    return notificationLanguageDropDownMenu.isDisplayed();
  }

  public SettingsScreen clickSaveSearchOptions() {
    saveSearchOptionsCheckBox.waitForPresence();
    saveSearchOptionsLabel.waitForClickableAndClick();
    return this;
  }

  public boolean isSaveSearchOptionsChecked() {
    return getSaveSearchOptionsCheckBoxChecked.isPresent();
  }

  public SettingsScreen waitSavSearchOptionBeUnchecked() {
    getSaveSearchOptionsCheckBoxChecked.waitForAbsence();
    return this;
  }

  public boolean isEmailNotificationCheckboxDisplayed() {
    return emailNotificationCheckbox.isDisplayed();
  }

  public SettingsScreen clickEmailNotificationCheckbox() {
    emailNotificationCheckbox.clickJs();
    return this;
  }

  public boolean isEmailNotificationCheckboxLabelDisplayed() {
    return emailNotificationCheckboxLabel.isDisplayed();
  }

  public String getEmailNotificationCheckboxLabelText() {
    return emailNotificationCheckboxLabel.getText();
  }

  public boolean isEmailNotificationCheckboxChecked() {
    return emailNotificationCheckboxSelector.isPresent();
  }

  public boolean isLanguageInNotificationLanguageDropdownDisplayed(String language) {
    return Element.byXpath(DDL_ITEM_LOCATOR_PATTERN, language).isDisplayed();
  }

  public SettingsScreen clickTimezoneDropDownButton() {
    timezoneDropDownButton.click();
    return this;
  }

  public SettingsScreen waitTimezoneDropDownMenuPresent() {
    timezoneDropDownMenuOpened.waitForPresence();
    return this;
  }
}
