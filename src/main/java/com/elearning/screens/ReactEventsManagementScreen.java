package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_DRAFT_STATUS_TEXT;
import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactEventsManagementScreen extends AbstractScreen {

  private static final String EVENT_NAME_LOCATOR_PATTERN = "//a[text()='%s']";
  private static final String COLUMN_LABEL_OF_EVENTS_TABLE_PATTERN =
      "//div[contains(@class,'uui-table-header-row')]//div[text()='%s']";
  private static final String NUMBER_OF_REGISTRATIONS_LOCATOR_PATTERN = EVENT_NAME_LOCATOR_PATTERN
      + "/ancestor::div[contains(@class,'uui-table-row')]//a[contains(@href,'attendees')]";
  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";
  private static final String DROPDOWN_LIST_PATTERN = TEXT_LOCATOR_PATTERN + "/following::div[3]";
  private static final String DROPDOWN_LIST_OPTION_PATTERN = "//*[contains(@class,'uui-focus')]/following::div[text()='%s']";
  private static final String DROPDOWN_LIST_LABEL_PATTERN = "//*[@class='uui-label'][text()='%s']";
  private static final String DATE_INPUT_PATTERN = "//*[contains(@class,'uui-input') and @placeholder='%s']";
  private static final String BUTTON_PATTERN = "//*[contains(@class,'uui-caption')][text()='%s']";

  private final Element createEventButton = Element
      .byXpath("//a[contains(@href,'/event/overview')]");
  private final Element firstEventInList = Element
      .byXpath("//div[contains(@class,'table-row')]//a[contains(@href,'event')]");
  private final Element eventWithDraftStatus = Element.byXpath(
      "//div[text()='%s']/ancestor::div[contains(@class, 'uui-table-row-container')]//a[contains(@href, 'overview')]",
      getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_SCREEN_DRAFT_STATUS_TEXT));
  private final Element spinnerContainer = Element.byXpath(
      "//div[contains(@class,'spinner-container')]");
  private final Element searchByEventNameField = Element.byXpath(
      "(//input[contains(@class, 'input')])[1]");
  private final Element searchInputClearButton = Element.byXpath(
      "(//input[contains(@class, 'input')]//following::div//div)[1]");
  private final Element applyButton = Element.byXpath("//div[contains(text(), \"Apply\")]");
  private final Element countryDropdown = Element.byXpath(
      "//div[@data-id='country-picker']//div[contains(@class,'uui-input-box -clickable')]");
  private final Element countryDropDownSearchField = Element.byXpath(
      "//div[@class='uui-popper']//div[contains(@class,'uui-input-box -clickable')]//input");
  private final String EVENT_COUNTRY_DROPDOWN_OPTION_LOCATOR_PATTERN = "//div[contains(text(),\"%s\")]";
  private final String EVENT_LIST_ITEM_LOCATOR_PATTERN = "//div[contains(@class,'table-row')]//a[contains(text(),\"%s\")]";
  private final Element datePicker = Element.byXpath(
      "//*[contains(@class,'uui-datepicker-container')]");
  private final Element showOnlySelectedSwitchToggle = Element.byXpath(
      "//*[@class='uui-switch-toggler']");
  private final Element myRelationLabel = Element.byXpath(TEXT_LOCATOR_PATTERN, "My relation");
  private final Element iAmCreatorCheckbox = Element.byXpath(
      "//*[text()=\"I'm creator\"]/preceding-sibling::div");

  @Override
  public boolean isScreenLoaded() {
    return isFirstEventInListDisplayed();
  }

  public boolean isFirstEventInListDisplayed() {
    return firstEventInList.isDisplayed();
  }

  public ReactEventDetailsScreen clickCreateEventButton() {
    createEventButton.click();
    return new ReactEventDetailsScreen();
  }

  public ReactEventHeaderScreen clickFirstEventInList() {
    firstEventInList.waitForClickableAndClick();
    return new ReactEventHeaderScreen();
  }

  public ReactEventHeaderScreen clickEventByName(String eventName) {
    Element.byXpath(format(EVENT_NAME_LOCATOR_PATTERN + "/parent::div/parent::div", eventName))
        .mouseOver();
    Element.byXpath(format(EVENT_NAME_LOCATOR_PATTERN, eventName)).click();
    return new ReactEventHeaderScreen();
  }

  public boolean isEventsListLabelDisplayed(String nameOfLabel) {
    return Element.byXpath(COLUMN_LABEL_OF_EVENTS_TABLE_PATTERN,
        nameOfLabel).isDisplayed();
  }

  public ReactEventAttendeesScreen clickNumberOfRegistrationsButtonByEventName(String eventName) {
    Element.byXpath(format(NUMBER_OF_REGISTRATIONS_LOCATOR_PATTERN, eventName)).clickJs();
    return new ReactEventAttendeesScreen();
  }

  public ReactEventsManagementScreen waitScreenLoaded() {
    Element.byCss("div.uui-table-header-row+div").waitForVisibility();
    return this;
  }

  public ReactEventHeaderScreen clickOnFirstEventWithDraftStatus() {
    eventWithDraftStatus.click();
    return new ReactEventHeaderScreen();
  }

  public ReactEventsManagementScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public ReactEventsManagementScreen typeEventName(String eventName) {
    searchByEventNameField.clearInputUsingBackspace();
    searchByEventNameField.type(eventName);
    searchInputClearButton.waitForVisibility();
    return this;
  }

  public ReactEventsManagementScreen clickApplyButton() {
    applyButton.click();
    return this;
  }

  public ReactEventsManagementScreen clickCountryDropDown() {
    countryDropdown.waitForClickableAndClick();
    return this;
  }

  public ReactEventsManagementScreen typeCountryInDropDownSearch(String countryName) {
    countryDropDownSearchField.waitForClickableAndClick();
    countryDropDownSearchField.type(countryName);
    return this;
  }

  public ReactEventsManagementScreen clickCountryByName(String countryName) {
    Element.byXpath(EVENT_COUNTRY_DROPDOWN_OPTION_LOCATOR_PATTERN, countryName).
        waitForClickableAndClick();
    return this;
  }

  public boolean isEventDisplayed(String eventName) {
    return Element
        .byXpath(EVENT_LIST_ITEM_LOCATOR_PATTERN, eventName)
        .isDisplayed();
  }

  public boolean isSelectedCountryDropDownSearchOptionIsDisplayed(String countryName) {
    return Element.byXpath(EVENT_COUNTRY_DROPDOWN_OPTION_LOCATOR_PATTERN, countryName)
        .isDisplayed();
  }

  public boolean isSearchByEventNameFieldDisplayed() {
    return searchByEventNameField.isDisplayedNoWait();
  }

  public boolean isDropdownListLabelDisplayed(String label) {
    return Element.byXpath(format(DROPDOWN_LIST_LABEL_PATTERN, label))
        .isDisplayedNoWait();
  }

  public boolean isDropdownListDisplayed(String dropdownName) {
    Element.byXpath(format(DROPDOWN_LIST_PATTERN, dropdownName)).click();
    return showOnlySelectedSwitchToggle.isDisplayedShortTimeOut();
  }

  public boolean isDatePickerDisplayed(String fromOrToDate) {
    Element.byXpath(format(DATE_INPUT_PATTERN, fromOrToDate)).click();
    return datePicker.isDisplayedShortTimeOut();
  }

  public ReactEventsManagementScreen clickDate(String fromOrToDate) {
    Element.byXpath(format(DATE_INPUT_PATTERN, fromOrToDate)).click();
    return this;
  }

  public boolean isIAmCreatorCheckboxDisplayed() {
    return iAmCreatorCheckbox.isDisplayedNoWait();
  }

  public boolean isMyRelationLabelDisplayed() {
    return myRelationLabel.isDisplayedNoWait();
  }

  public boolean isButtonDisplayed(String buttonName) {
    return Element.byXpath(format(BUTTON_PATTERN, buttonName)).isDisplayedNoWait();
  }

  public ReactEventsManagementScreen chooseDropdownOption(String dropdownOption) {
    Element.byXpath(format(DROPDOWN_LIST_OPTION_PATTERN, dropdownOption)).click();
    return this;
  }
}
