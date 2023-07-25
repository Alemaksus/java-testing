package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactEventGeneralInfoScreen extends AbstractScreen {

  private static final String TEXT_PATTERN = "//*[text()=\"%s\"]";
  private static final String DATA_ID_LOCATOR_PATTERN = "//div[@data-id='%s']";
  private static final String INPUT_BY_DATA_ID_PATTERN =
      DATA_ID_LOCATOR_PATTERN + "//input";
  private static final String CHOSEN_ITEMS_DIV_BY_DATA_ID_PATTERN =
      DATA_ID_LOCATOR_PATTERN + "//div[@class='uui-caption']";
  private static final String CHOSEN_ITEMS_SPAN_BY_DATA_ID_PATTERN =
      DATA_ID_LOCATOR_PATTERN + "//span";
  private static final String INPUT_PLACEHOLDER_PATTERN = "//input[@placeholder='%s']";
  private static final String DROPDOWN_ICON_PATTERN = DATA_ID_LOCATOR_PATTERN
      + "//div[contains(@class,'uui-icon') and not(contains(@class,'-clickable'))]";
  private static final String INPUT_PLACEHOLDER_FOR_END_DATE_PATTERN =
      String.format("(%s)[2]", INPUT_PLACEHOLDER_PATTERN);
  private static final String ASTERISK_PATTERN = TEXT_PATTERN + "/span";
  private static final String DATE_PICKER_BUTTON_PATTERN = TEXT_PATTERN + "/ancestor::div[4]//div[contains(@class, 'uui-enabled')]";
  private static final String TOOLTIP_ICON_PATTERN = TEXT_PATTERN + "/ancestor::div[4]//div[@display='flex']";
  private static final String PLACEHOLDER_FROM_SPAN_PATTERN =
          TEXT_PATTERN + "/ancestor::div[@data-id]//span[not (contains(@class, 'asterisk'))]";
  private static final String BUTTON_TEXT_PATTERN =
          TEXT_PATTERN + "/ancestor::div[4]//div[contains(@class, 'uui-caption')]";
  private static final String PLACEHOLDER_FROM_DIV_WITH_CLASS_PLACEHOLDER_PATTERN =
          TEXT_PATTERN + "/ancestor::div[@data-id]//div[contains(@class,'uui-placeholder')]";
  private static final String PLACEHOLDER_FROM_INPUT_PATTERN = TEXT_PATTERN + "/ancestor::div[@data-id]//input";
  private static final String PLACEHOLDER_FROM_INPUT_FOR_LINK_PATTERN = TEXT_PATTERN + "/ancestor::div[@id][1]//input";
  private static final String DISPLAY_EVENT_CHECKBOX_PATTERN = TEXT_PATTERN + "/preceding-sibling::div";
  private static final String SAVE_CHANGES_BUTTON_PATTERN = TEXT_PATTERN + "/parent::div";
  private Element eventNameInput = Element.byXpath(INPUT_BY_DATA_ID_PATTERN, "eventName");
  private Element formatDDLInput = Element.byXpath(INPUT_BY_DATA_ID_PATTERN, "eventFormat");
  private Element formatDropDown = Element.byXpath(DROPDOWN_ICON_PATTERN, "eventFormat");
  private Element countryChosenItems = Element
      .byXpath(CHOSEN_ITEMS_DIV_BY_DATA_ID_PATTERN, "eventCountry");
  private Element cityChosenItems = Element
      .byXpath(CHOSEN_ITEMS_SPAN_BY_DATA_ID_PATTERN, "eventCity");
  private Element registrationTypeDDLInput = Element
      .byXpath(INPUT_BY_DATA_ID_PATTERN, "eventRegistrationType");
  private Element registrationTypeDropDown = Element
      .byXpath(DROPDOWN_ICON_PATTERN, "eventRegistrationType");
  private Element languageDDLInput = Element
      .byXpath(INPUT_BY_DATA_ID_PATTERN, "eventLanguage");
  private Element languageDropDown = Element
      .byXpath(DROPDOWN_ICON_PATTERN, "eventLanguage");
  private Element tagsChosenItems = Element
      .byXpath(CHOSEN_ITEMS_SPAN_BY_DATA_ID_PATTERN, "eventTags");
  private Element skillChosenItems = Element
      .byXpath(CHOSEN_ITEMS_DIV_BY_DATA_ID_PATTERN, "eventSkill");
  private Element linkOfEventInput = Element
      .byXpath(INPUT_BY_DATA_ID_PATTERN, "eventLinkToEvent");
  private Element contactEmailInput = Element
      .byXpath(INPUT_BY_DATA_ID_PATTERN, "eventContactEmail");
  private Element startDateInputField = Element.byXpath(INPUT_PLACEHOLDER_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_DATE_PLACEHOLDER));
  private Element startTimeInputField = Element.byXpath(INPUT_PLACEHOLDER_PATTERN, "0:00");
  private Element endDateInputField = Element.byXpath(INPUT_PLACEHOLDER_FOR_END_DATE_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_DATE_PLACEHOLDER));
  private Element endTimeInputField = Element
      .byXpath(INPUT_PLACEHOLDER_FOR_END_DATE_PATTERN, "0:00");
  private Element displayEventCheckbox = Element.byXpath(DISPLAY_EVENT_CHECKBOX_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_DISPLAY_EVENT_LABEL));
  private Element saveChangesButton = Element.byXpath(SAVE_CHANGES_BUTTON_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_GENERAL_INFO_SAVE_CHANGES_BUTTON_TEXT));

  @Override
  public boolean isScreenLoaded() {
    return eventNameInput.isDisplayed();
  }

  public String getEventNameValue() {
    return eventNameInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getEventFormatPlaceholder() {
    return formatDDLInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public List<String> getEventCountryChosenItemsText() {
    return countryChosenItems.getElementsText();
  }

  public List<String> getEventCityChosenItemsText() {
    return cityChosenItems.getElementsText();
  }

  public String getEventRegistrationTypePlaceholder() {
    return registrationTypeDDLInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getEventLanguagePlaceholder() {
    return languageDDLInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public List<String> getEventTagsChosenItemsText() {
    return tagsChosenItems.getElementsText();
  }

  public List<String> getEventSkillChosenItemsText() {
    return skillChosenItems.getElementsText();
  }

  public String getEventContactEmailValue() {
    return contactEmailInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public ReactEventGeneralInfoScreen typeEventName(String name) {
    eventNameInput.type(name);
    return this;
  }

  public ReactEventGeneralInfoScreen clickFormatDropDown() {
    formatDropDown.click();
    return this;
  }

  public ReactEventGeneralInfoScreen clickRegistrationTypeDropDown() {
    registrationTypeDropDown.click();
    return this;
  }

  public ReactEventGeneralInfoScreen clickLanguageDropDown() {
    languageDropDown.click();
    return this;
  }

  public ReactEventGeneralInfoScreen clickItemInDropDownByName(String name) {
    Element.byXpath(TEXT_PATTERN, name).click();
    return this;
  }

  public ReactEventGeneralInfoScreen typeLinkOfEvent(String link) {
    linkOfEventInput.type(link);
    return this;
  }

  public ReactEventGeneralInfoScreen typeContactEmail(String email) {
    contactEmailInput.type(email);
    return this;
  }

  public ReactEventGeneralInfoScreen typeStartDate(String date) {
    startDateInputField.type(date);
    return this;
  }

  public ReactEventGeneralInfoScreen typeStartTime(String time) {
    startTimeInputField.type(time);
    return this;
  }

  public ReactEventGeneralInfoScreen clearStartDateInputField() {
    startDateInputField.click();
    startDateInputField.clearInputUsingBackspace();
    return this;
  }

  public ReactEventGeneralInfoScreen clearStartTimeInputField() {
    startTimeInputField.click();
    startTimeInputField.clearInputUsingBackspace();
    return this;
  }

  public ReactEventGeneralInfoScreen typeEndDate(String date) {
    endDateInputField.type(date);
    return this;
  }

  public ReactEventGeneralInfoScreen typeEndTime(String time) {
    endTimeInputField.type(time);
    return this;
  }

  public ReactEventGeneralInfoScreen clearEndTimeInputField() {
    endTimeInputField.click();
    endTimeInputField.clearInputUsingBackspace();
    return this;
  }

  public ReactEventGeneralInfoScreen clickFormatDDL() {
    formatDDLInput.click();
    return this;
  }

  public boolean isLabelDisplayed(String label) {
    return Element.byXpath(TEXT_PATTERN, label).isDisplayed();
  }

  public boolean isLabelWithAsteriskDisplayed(String label) {
    return isLabelDisplayed(label)
            && Element.byXpath(ASTERISK_PATTERN, label).isDisplayed();
  }

  public String getPlaceholderFromInputDependsLabel(String label) {
    return Element.byXpath(PLACEHOLDER_FROM_INPUT_PATTERN, label)
            .getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getLinkToEventRecordingLabelPlaceholder(String label) {
    return Element.byXpath(PLACEHOLDER_FROM_INPUT_FOR_LINK_PATTERN, label)
            .getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getPlaceholderFromDivWithClassPlaceholderDependsLabel(String label) {
    return Element.byXpath(PLACEHOLDER_FROM_DIV_WITH_CLASS_PLACEHOLDER_PATTERN, label)
            .getText();
  }

  public String getTextButtonDependsLabel(String label) {
    return Element.byXpath(BUTTON_TEXT_PATTERN, label)
            .getText();
  }

  public String getPlaceholderFromSpanDependsLabel(String label) {
    return Element.byXpath(PLACEHOLDER_FROM_SPAN_PATTERN, label)
            .getText();
  }

  public boolean isDatePickerButtonDependsLabelDisplayed(String label) {
    return Element.byXpath(DATE_PICKER_BUTTON_PATTERN, label)
            .isDisplayed();
  }

  public boolean isToolTipIconDependsLabelDisplayed(String label) {
    return Element.byXpath(TOOLTIP_ICON_PATTERN, label)
            .isDisplayed();
  }

  public boolean isDisplayEventCheckboxChecked() {
    return displayEventCheckbox.getWrappedWebElement().getAttribute("class").contains("checked");
  }

  public ReactEventGeneralInfoScreen clickDisplayEventCheckBox() {
    displayEventCheckbox.click();
    return this;
  }

  public ReactEventGeneralInfoScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public ReactEventGeneralInfoScreen clickDisplayEventButton() {
    if (!isDisplayEventCheckboxChecked()) {
      clickDisplayEventCheckBox();
      clickSaveChangesButton();
    }
    return this;
  }
}
