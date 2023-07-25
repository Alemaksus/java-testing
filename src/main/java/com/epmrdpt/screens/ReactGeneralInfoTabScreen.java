package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_DESCRIPTION_SCREEN_REQUIRED_NOTE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_DEPARTMENT_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CREATE_TRAINING_SCREEN_CREATE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_TECHNICAL_REQUIREMENTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_ADDITIONAL_SKILLS_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_CLEAR_SELECTION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_DATA_PROCESSING_CONSENT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_DISPLAYING_NAME_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_DISPLAY_TOGGLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_EDUCATIONAL_ESTABLISHMENT_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_ENDLESS_CHECKBOX;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_END_DATE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_ENROLLMENT_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_FIND_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_LABS_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_OWNER_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_PRICE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LANGUAGE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LEVEL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_REVISION_HISTORY_POPOVER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_START_DATE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_STUDENTS_COUNT_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_STUDENT_GROUP_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_SUPERVISORS_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TARGET_AUDIENCE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_CURRENCY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_FORMAT_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_NAME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_FREEMIUM_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_FEE_BASED_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PUBLIC_OFFER_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_VAT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_UPLOAD_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Attribute;

public class ReactGeneralInfoTabScreen extends ReactAdditionalInfoTabScreen {

  private final String TEXT_LOCATOR_PATTERN = "//*[not(local-name()='script') and contains(text(),'%s')]";
  private final String INPUT_LOCATOR_PATTERN = TEXT_LOCATOR_PATTERN + "/following::input[1]";
  private final String INPUT_ARROW_LOCATOR_PATTERN =
      TEXT_LOCATOR_PATTERN + "/following::*[contains(@*,'arrow')][1]";
  private final String PLACEHOLDER_VALUE_LOCATOR_PATTERN = TEXT_LOCATOR_PATTERN
      + "/following::*[contains(@*,'placeholder')]";
  private final String DROP_DOWN_LOCATOR_SELECTOR_PATTERN =
      "//div[@class='uui-popper']" + TEXT_LOCATOR_PATTERN;
  private final String REQUIRED_MARK_LOCATOR_PATTERN = TEXT_LOCATOR_PATTERN + "/span";
  private final String INPUT_DROPDOWN_PATTERN = "//*[text()='%s']/ancestor::div[@data-id]/div[2]//input";
  private final String POPOVER_VALUE_PATTERN = "//div[contains(@style,'padding')]//div[text()='%s']/../following::div[1]/div";
  private final String INPUT_VALUE = INPUT_DROPDOWN_PATTERN + "/preceding-sibling::div/div";
  private final String PUBLIC_OFFER_FILE_NAME_PATTERN =
      "//input[@id='fileCreateLesson']/../following-sibling::div" + TEXT_LOCATOR_PATTERN;
  private final String PUBLIC_OFFER_DOWNLOAD_BUTTON_PATTERN =
      PUBLIC_OFFER_FILE_NAME_PATTERN + "/../../following-sibling::div//div[contains(@class, 'uui-icon')]";
  private final String CHECKED_TOGGLE_PATTERN = TEXT_LOCATOR_PATTERN +
      "/../../../../../following-sibling::div//div[contains(@class,'uui-checked')]";
  private final String DDL_LIST_VALUE =
      "//div[@class='uui-popper']//div[contains(@class,'-clickable')]//div[@style]";
  private final String DDL_LIST_CHECKED_VALUE_PATTERN = TEXT_LOCATOR_PATTERN +
  "/../../../../../preceding-sibling::div//div[contains(@class,'uui-checked')]";
  private final String DDL_LIST_CHECKED_SUPERVISORS_PATTERN = TEXT_LOCATOR_PATTERN +
      "/../../div//div[contains(@class,'uui-checked')]";
  private final String POPUP_PATTERN =
      "//div[@class='uui-snackbar-item-wrapper-self']//following::div[2]";
  private final String DOWNLOAD_BUTTON_OF_UPLOADED_FILE_PATTERN = TEXT_LOCATOR_PATTERN +
      "/following::div[4]";
  private final String CROSS_BUTTON_OF_UPLOADED_FILE_PATTERN = TEXT_LOCATOR_PATTERN +
      "/following::div[6]";
  private final String QUESTION_ICON_PATTERN = TEXT_LOCATOR_PATTERN + "/following::*[name()='svg'][1]";
  private final String ACTIVE_ADD_TECHNICAL_REQUIREMENT_BUTTON_COLOR = "rgba(0, 158, 204, 1)";

  private Element DDL = Element.byXpath("//div[@class='uui-popper']");
  private Element createButton = Element
      .byXpath("//*[contains(@class, 'uui-caption') and text()='%s']",
          getValueOf(REACT_CREATE_TRAINING_SCREEN_CREATE_BUTTON));
  private Element languageButton = Element.byXpath(
      "(//*[contains(@class, 'uui-button-box')]/*[contains(@class, 'uui-caption')])[6]");
  private Element trainingNameInput = Element
      .byXpath("//div[contains(text(),'%s')]/following::input[1]",
          getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_NAME_LABEL));
  private Element trainingPricingInput = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_LABEL));
  private Element trainingPriceInput = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICE_LABEL));
  private Element trainingCurrencyInput = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_CURRENCY_LABEL));
  private Element trainingVatInput = Element.byXpath(INPUT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_VAT_LABEL));
  private Element trainingPriceRequiredMarkLabel = Element.byXpath(REQUIRED_MARK_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICE_LABEL));
  private Element trainingCurrencyRequiredMarkLabel = Element.byXpath(REQUIRED_MARK_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_CURRENCY_LABEL));
  private Element trainingVatRequiredMarkLabel = Element.byXpath(REQUIRED_MARK_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_VAT_LABEL));
  private Element trainingPublicOfferRequiredMarkLabel = Element.byXpath(
      TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PUBLIC_OFFER_LABEL));
  private Element feeBasedPricingButton = Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_FEE_BASED_BUTTON));
  private Element freemiumPricingButton = Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_FREEMIUM_BUTTON));
  private Element addPublicOfferButton = Element.byXpath(
      "//div[contains(@class,'button')]//div[text()='%s']",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PUBLIC_OFFER_LABEL));
  private Element spinnerContainer = Element.byXpath(
      "//div[contains(@class,'spinner-container')]");
  private Element trainingTypeDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_INPUT));
  private Element trainingFormatDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_FORMAT_INPUT));
  private Element trainingCountryDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT));
  private Element trainingCityDropDown = Element
      .byXpath("//span[text()='%s']", getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT));
  private Element trainingOwnerDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_OWNER_INPUT));
  private Element trainingSkillDropDown = Element
      .byXpath("//div[contains(text(),'%s')]/ancestor::div[@data-id]//input",
          getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT));
  private Element trainingEnrollmentTypeDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_ENROLLMENT_INPUT));
  private Element trainingProgramLanguageDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LANGUAGE_INPUT));
  private Element trainingAdditionalSkillsDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_ADDITIONAL_SKILLS_INPUT));
  private Element trainingSupervisorsDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_SUPERVISORS_INPUT));
  private Element trainingProgramLevelDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LEVEL_INPUT));
  private Element trainingPricingDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_INPUT));
  private Element endlessCheckbox = Element.byXpath(
      "//*[contains(text(), '%s')]/preceding-sibling::div",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_ENDLESS_CHECKBOX));
  private Element plannedStudentsCountField = Element.byXpath(
      "//*[contains(text(), '%s')]/../../../following-sibling::div//input",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_STUDENTS_COUNT_INPUT));
  private Element displayingNameInput = Element.byXpath(INPUT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_DISPLAYING_NAME_INPUT));
  private Element startDateInput = Element.byXpath(INPUT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_START_DATE_INPUT));
  private Element endDateInput = Element.byXpath(INPUT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_END_DATE_INPUT));
  private Element firstOwnerIcon = Element.byXpath("(//div[@class='uui-popper']//img)[1]");
  private Element findInput = Element.byXpath("//div/input[@placeholder='%s']",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_FIND_PLACEHOLDER));
  private Element trainingTypeInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_INPUT));
  private Element trainingFormatInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_FORMAT_INPUT));
  private Element trainingSkillInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT));
  private Element trainingEnrollmentTypeInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_ENROLLMENT_INPUT));
  private Element trainingProgramLevelInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LEVEL_INPUT));
  private Element trainingPricingInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_PRICING_INPUT));
  private Element trainingPriceInputField = Element.byXpath("//*[@placeholder='%s']",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_PRICE_INPUT));
  private Element ddlValue = Element.byXpath(DDL_LIST_VALUE + "//div[text()]");
  private Element ddlSupervisorsValue = Element.byXpath(
      "//div[@class='uui-popper']//div[contains(@class,'-clickable')]//div[@display]//div");
  private Element trainingCurrencyInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_CURRENCY_LABEL));
  private Element trainingTargetAudienceInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TARGET_AUDIENCE_INPUT));
  private Element linkToExternalTrainingInputField = Element.byXpath(
      "//*[@placeholder='http://example.com']");
  private Element revisionHistoryTextButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_REVISION_HISTORY_POPOVER));
  private Element popupLocator = Element.byXpath(POPUP_PATTERN);
  private Element clearSelectionButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_CLEAR_SELECTION_BUTTON));
  private Element crossIconOfPopup = Element.byXpath(POPUP_PATTERN + "//following::div[2]");
  private Element checkedSkillDisplayToggle = Element.byXpath(
      TEXT_LOCATOR_PATTERN + "/following::div[text()='%s']/preceding::div[@class='uui-switch-body uui-checked']",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_SKILL_INPUT), getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_DISPLAY_TOGGLE));
  private Element uploadFileInput = Element.byXpath("//input[@type='file']");
  private Element uploadButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_UPLOAD_BUTTON));
  private Element allRequiredFieldsAreMarkedWithLabel = Element.byXpath("//div[text()=\"%s\"]",
      getValueOf(GROUP_DESCRIPTION_SCREEN_REQUIRED_NOTE));
  private Element educationalEstablishmentInputField = Element.byXpath(INPUT_DROPDOWN_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_EDUCATIONAL_ESTABLISHMENT_INPUT));
  private Element departmentDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(PROFILE_DEPARTMENT_TITLE));
  private Element dataProcessingConsentLabel = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_DATA_PROCESSING_CONSENT_LABEL));
  private Element technicalRequirementsLabel = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_TECHNICAL_REQUIREMENTS_LABEL));
  private Element addTechnicalRequirementsButton = Element
      .byXpath("//input[@type='file']/..//div[contains(@class,'uui-caption')]");
  private Element tooltip = Element.byXpath("//div[@class='uui-tooltip-body']//div[text()]");
  private final Element trainingStudentGroupDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_STUDENT_GROUP_INPUT));
  private final Element trainingLabsDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_LABS_INPUT));
  private final Element trainingLabsInputValue = Element.byXpath(PLACEHOLDER_VALUE_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_LABS_INPUT));
  private final Element trainingCountryInputValue = Element.byXpath(PLACEHOLDER_VALUE_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_COUNTRY_INPUT));
  private final Element trainingOwnerInputValue = Element.byXpath(INPUT_LOCATOR_PATTERN,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_OWNER_INPUT));
  private final Element uploadButtonBox = Element.byXpath(
      TEXT_LOCATOR_PATTERN + "/parent::div[contains(@class,'uui-button-box')]",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_UPLOAD_BUTTON));
  private Element trainingTypeInputValue = Element.byXpath(INPUT_VALUE,
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TRAINING_TYPE_INPUT));

  public String getTrainingNameText() {
    return trainingNameInput.getAttributeValue(Attribute.VALUE.toString());
  }

  public String getPlaceholderOfInputFieldByName(String inputFieldName) {
    return Element.byXpath(INPUT_LOCATOR_PATTERN, inputFieldName)
        .getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public ReactGeneralInfoTabScreen clickPaidButtonFromPricingDdl() {
    trainingPricingInput.mouseOverAndClick();
    feeBasedPricingButton.click();
    return this;
  }

  public ReactGeneralInfoTabScreen clickFreemiumButtonFromPricingDdl() {
    trainingPricingInput.mouseOverAndClick();
    freemiumPricingButton.click();
    return this;
  }

  public boolean isTrainingPriceInputDisplayed() {
    return trainingPriceInput.isDisplayedNoWait();
  }

  public boolean isTrainingPriceRequiredMarkLabelDisplayed() {
    return trainingPriceRequiredMarkLabel.isDisplayedNoWait();
  }

  public boolean isTrainingCurrencyInputDisplayed() {
    return trainingCurrencyInput.isDisplayedNoWait();
  }

  public boolean isTrainingCurrencyRequiredMarkLabelDisplayed() {
    return trainingCurrencyRequiredMarkLabel.isDisplayedNoWait();
  }

  public boolean isTrainingVatInputDisplayed() {
    return trainingVatInput.isDisplayedNoWait();
  }

  public boolean isTrainingVatRequiredMarkLabelDisplayed() {
    return trainingVatRequiredMarkLabel.isDisplayedNoWait();
  }

  public boolean isTrainingPublicOfferRequiredMarkLabelDisplayed() {
    return trainingPublicOfferRequiredMarkLabel.isDisplayedNoWait();
  }

  public boolean isTrainingAddPublicOfferButtonDisplayed() {
    return addPublicOfferButton.isDisplayedNoWait();
  }

  public boolean isQuestionIconDisplayedByLabelName(String labelName) {
    return Element.byXpath(QUESTION_ICON_PATTERN, labelName).isDisplayed();
  }

  public boolean isRevisionHistoryTextButtonDisplayed() {
    return revisionHistoryTextButton.isDisplayed();
  }

  public boolean isElementDisplayedByName(String elementName) {
    return Element.byXpath(TEXT_LOCATOR_PATTERN, elementName).isDisplayed();
  }

  public boolean isCrossIconOfPopupDisplayed() {
    return crossIconOfPopup.isDisplayed();
  }

  public boolean isSkillDisplayToggleTurnedOn() {
    return checkedSkillDisplayToggle.isDisplayed();
  }

  public boolean isTrainingTypeDropDownDisplayed() {
    return trainingTypeDropDown.isDisplayed();
  }

  public boolean isEducationalEstablishmentInputFieldDisplayed() {
    return educationalEstablishmentInputField.isDisplayed();
  }

  public boolean isAllRequiredFieldsAreMarkedWithLabelDisplayed() {
    return allRequiredFieldsAreMarkedWithLabel.isDisplayed();
  }

  public boolean isDepartmentDropDownDisplayed() {
    return departmentDropDown.isDisplayed();
  }

  public boolean isUploadedFileDisplayedByName(String fileName) {
    return Element.byXpath(TEXT_LOCATOR_PATTERN, fileName).isDisplayed();
  }

  public boolean isTrainingNameInputDisplayed() {
    return trainingNameInput.isDisplayed();
  }

  public boolean isTrainingDisplayingNameInputDisplayed() {
    return displayingNameInput.isDisplayed();
  }

  public boolean isStartDateInputDisplayed() {
    return startDateInput.isDisplayed();
  }

  public boolean isEndDateInputDisplayed() {
    return endDateInput.isDisplayed();
  }

  public boolean isTrainingCountryDropDownDisplayed() {
    return trainingCountryDropDown.isDisplayed();
  }

  public boolean isDataProcessingConsentLabelDisplayed() {
    return dataProcessingConsentLabel.isDisplayed();
  }

  public boolean isUpLoadButtonDisplayed() {
    return uploadButton.isDisplayed();
  }

  public boolean isDownloadButtonOfUploadedFileDisplayedByName(String fileName) {
    return Element.byXpath(DOWNLOAD_BUTTON_OF_UPLOADED_FILE_PATTERN, fileName).isDisplayed();
  }

  public boolean isCrossButtonOfUploadedFileDisplayedByName(String fileName) {
    return Element.byXpath(CROSS_BUTTON_OF_UPLOADED_FILE_PATTERN, fileName).isDisplayed();
  }

  public ReactGeneralInfoTabScreen waitScreenLoading() {
    trainingNameInput.waitForVisibility();
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public ReactGeneralInfoTabScreen clearDisplayingNameInput() {
    displayingNameInput.clearInputUsingBackspace();
    return new ReactGeneralInfoTabScreen();
  }

  public ReactGeneralInfoTabScreen typeInDisplayingNameInputAfterCleanInput(String inputText) {
    clearDisplayingNameInput();
    displayingNameInput.type(inputText);
    return new ReactGeneralInfoTabScreen();
  }

  public String getTextFromDisplayingNameInput() {
    return displayingNameInput.getAttributeValue(Attribute.VALUE.toString());
  }

  public ReactGeneralInfoTabScreen waitLanguageButtonForVisibility() {
    languageButton.waitForVisibility();
    return this;
  }

  public ReactGeneralInfoTabScreen clickTrainingTypeDropDown() {
    trainingTypeDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    return this;
  }

  public ReactGeneralInfoTabScreen waitDDLForVisibility() {
    DDL.waitForVisibility();
    return this;
  }

  public ReactGeneralInfoTabScreen selectTrainingTypeByName(String trainingTypeName) {
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingTypeName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen typeTrainingName(String name) {
    trainingNameInput.click();
    trainingNameInput.type(name);
    return this;
  }

  public ReactGeneralInfoTabScreen clickTrainingFormatDropDown() {
    trainingFormatDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    return this;
  }

  public ReactGeneralInfoTabScreen selectTrainingFormatByName(String trainingFormatName) {
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingFormatName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen setTrainingCountryByName(String trainingCountryName) {
    trainingCountryDropDown.mouseOverAndClick();
    waitDDLForVisibility()
        .waitFindFieldDisplayed()
        .typeSearchPhraseInToFindInput(trainingCountryName);
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingCountryName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen setTrainingCityByName(String trainingCityName) {
    waitAllSpinnersAreNotDisplayed();
    trainingCityDropDown.mouseOverAndClick();
    waitFindFieldDisplayed()
        .typeSearchPhraseInToFindInput(trainingCityName);
    Element.byXpath(TEXT_LOCATOR_PATTERN, trainingCityName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen setFirstTrainingOwner() {
    trainingOwnerDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    firstOwnerIcon.click();
    return this;
  }

  public ReactGeneralInfoTabScreen setTrainingSkillByName(String trainingSkillName) {
    trainingSkillDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingSkillName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen selectTrainingEnrollmentTypeByName(
      String trainingEnrollmentTypeName) {
    trainingEnrollmentTypeDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingEnrollmentTypeName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen setTrainingProgramLanguageByName(
      String trainingProgramLanguageName) {
    trainingProgramLanguageDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingProgramLanguageName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen setTrainingProgramLevelByName(String trainingProgramLevelName) {
    trainingProgramLevelDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingProgramLevelName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen setEndlessCheckBox() {
    endlessCheckbox.waitForClickableAndClick();
    return this;
  }

  public ReactGeneralInfoTabScreen typePlannedStudentsCountField(String plannedStudentsCount) {
    plannedStudentsCountField.type(plannedStudentsCount);
    return this;
  }

  public ReactGeneralInfoTabScreen setTrainingPricingByName(String trainingPricingName) {
    trainingPricingDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    Element.byXpath(DROP_DOWN_LOCATOR_SELECTOR_PATTERN, trainingPricingName).click();
    return this;
  }

  public ReactDetailTrainingScreen clickCreateButton() {
    createButton.waitForClickableAndClick();
    return new ReactDetailTrainingScreen();
  }

  public ReactGeneralInfoTabScreen waitDisplayedNameHasText(String displayedNameValue) {
    displayingNameInput
        .waitUntilAttributeGetsValue(HTML.Attribute.VALUE.toString(), displayedNameValue);
    return this;
  }


  public ReactDetailTrainingCalendarScreen clickOnStartDateInput() {
    startDateInput.click();
    return new ReactDetailTrainingCalendarScreen();
  }

  public ReactDetailTrainingCalendarScreen clickOnEndDateInput() {
    endDateInput.click();
    return new ReactDetailTrainingCalendarScreen();
  }

  public ReactGeneralInfoTabScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public ReactGeneralInfoTabScreen waitFindFieldDisplayed() {
    findInput.waitForVisibility();
    return this;
  }

  public ReactGeneralInfoTabScreen typeSearchPhraseInToFindInput(String searchPhrase) {
    findInput.type(searchPhrase);
    return this;
  }

  public String getTrainingTypeValue() {
    return trainingTypeInputValue.getText();
  }

  public String getTrainingFormatValue() {
    return trainingFormatInputField.getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public String getTrainingSkillValue() {
    return trainingSkillInputField.getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public String getTrainingEnrollmentTypeValue() {
    return trainingEnrollmentTypeInputField
        .getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public String getTrainingProgramLevelValue() {
    return trainingProgramLevelInputField
        .getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public String getTrainingPricingValue() {
    return trainingPricingInputField.getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public List<String> getProgramLanguageDdlOptions() {
    trainingProgramLanguageDropDown.click();
    List<String> programDdlOptions = ddlValue.getElementsText();
    trainingEnrollmentTypeDropDown.click();
    return programDdlOptions;
  }

  public List<String> getSelectedProgramLanguages() {
    trainingProgramLanguageDropDown.click();
    List<String> programLanguages = addValueToListIfCheckBoxChecked();
    trainingProgramLanguageDropDown.click();
    return programLanguages;
  }

  private List<String> addValueToListIfCheckBoxChecked() {
    List<String> programDdlOptions = ddlValue.getElementsText();
    return programDdlOptions
        .stream()
        .filter(optionText -> Element.byXpath(DDL_LIST_CHECKED_VALUE_PATTERN, optionText)
            .isPresentNoWait())
        .collect(Collectors.toList());
  }

  public List<String> getSelectedAdditionalSkills() {
    trainingAdditionalSkillsDropDown.click();
    List<String> additionalSkills = addValueToListIfCheckBoxChecked();
    trainingAdditionalSkillsDropDown.click();
    return additionalSkills;
  }

  public List<String> getSelectedSupervisors() {
    trainingSupervisorsDropDown.click();
    List<String> additionalSkills = ddlSupervisorsValue.getElementsText()
        .stream()
        .filter(optionText -> Element.byXpath(DDL_LIST_CHECKED_SUPERVISORS_PATTERN, optionText)
            .isPresentNoWait())
        .collect(Collectors.toList());
    trainingSupervisorsDropDown.click();
    return additionalSkills;
  }

  public List<String> getEnrollmentTypeDdlOptions() {
    trainingEnrollmentTypeDropDown.click();
    List<String> enrollmentTypeDdlOptions = ddlValue.getElementsText();
    trainingEnrollmentTypeDropDown.click();
    return enrollmentTypeDdlOptions;
  }

  public List<String> getPricingDdlOptions() {
    trainingPricingDropDown.click();
    List<String> pricingDdlOptions = ddlValue.getElementsText();
    trainingPricingDropDown.click();
    return pricingDdlOptions;
  }

  public List<String> getFormatDdlOptions() {
    trainingFormatDropDown.click();
    List<String> formatDdlOptions = ddlValue.getElementsText();
    trainingFormatDropDown.click();
    return formatDdlOptions;
  }

  public List<String> getTypeDdlOptions() {
    trainingTypeDropDown.click();
    List<String> typeDdlOptions = ddlValue.getElementsText();
    trainingTypeDropDown.click();
    return typeDdlOptions;
  }

  public List<String> getProgramLevelDdlOptions() {
    trainingProgramLevelDropDown.click();
    List<String> typeDdlOptions = ddlValue.getElementsText();
    trainingProgramLevelDropDown.click();
    return typeDdlOptions;
  }

  public ReactGeneralInfoTabScreen mouseOverRevisionHistoryTextButton() {
    revisionHistoryTextButton.mouseOver();
    return this;
  }

  public String getValueOfPopoverElementByName(String elementName) {
    return Element.byXpath(POPOVER_VALUE_PATTERN, elementName).getText();
  }

  public boolean isToggleByNameChecked(String name) {
    return Element.byXpath(CHECKED_TOGGLE_PATTERN, getValueOf(name)).isPresent();
  }

  public String getTargetAudienceValue() {
    return trainingTargetAudienceInputField
        .getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public String getLinkToExternalTrainingValue() {
    return linkToExternalTrainingInputField.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public String getTextOfPopup() {
    return popupLocator.waitForVisibility().getText();
  }

  public ReactGeneralInfoTabScreen clickCrossIconOfPopup() {
    crossIconOfPopup.click();
    return this;
  }

  public ReactGeneralInfoTabScreen clearSelectedInputsOfCountryField() {
    trainingCountryDropDown.mouseOverAndClick();
    waitDDLForVisibility();
    clearSelectionButton.click();
    return this;
  }

  public ReactGeneralInfoTabScreen waitTrainingPriceForVisibility() {
    trainingPriceInputField.waitForVisibility();
    return this;
  }

  public String getTrainingPriceInputFieldValue() {
    return trainingPriceInputField.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public ReactGeneralInfoTabScreen waitTrainingCurrencyForVisibility() {
    trainingCurrencyInputField.waitForVisibility();
    return this;
  }

  public String getTrainingCurrencyInputFieldValue() {
    return trainingCurrencyInputField.getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public ReactGeneralInfoTabScreen waitTrainingValueAddedTaxForVisibility() {
    trainingVatInput.waitForVisibility();
    return this;
  }

  public String getTrainingValueAddedTaxInputFieldValue() {
    return trainingVatInput.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public boolean isTrainingPublicOfferFileNameDisplayed(String fileName) {
    return Element.byXpath(PUBLIC_OFFER_FILE_NAME_PATTERN, fileName).isDisplayed();
  }

  public ReactGeneralInfoTabScreen clickTrainingPublicOfferDownloadButton(String fileName) {
    Element.byXpath(PUBLIC_OFFER_DOWNLOAD_BUTTON_PATTERN, fileName).click();
    return this;
  }

  public ReactGeneralInfoTabScreen uploadFile(String path) {
    uploadFileInput.sendFilePath(path);
    return this;
  }

  public boolean isTechnicalRequirementsLabelDisplayed() {
    return technicalRequirementsLabel.isDisplayed();
  }

  public boolean isAddTechnicalRequirementsButtonDisplayed() {
    return addTechnicalRequirementsButton.isDisplayed();
  }

  public String getAddTechnicalRequirementsButtonText() {
    return addTechnicalRequirementsButton.getText();
  }

  public String getToolTipTextByLabelName(String labelName) {
    return Element.byXpath(QUESTION_ICON_PATTERN, labelName).getTooltipText(tooltip);
  }

  public boolean isAddTechnicalRequirementsButtonEnabled() {
    return addTechnicalRequirementsButton.getCssValue(CSS.Attribute.COLOR.toString())
        .equals(ACTIVE_ADD_TECHNICAL_REQUIREMENT_BUTTON_COLOR);
  }

  public String getTrainingNameInputFieldPlaceholder() {
    return trainingNameInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isPlannedStudentsCountFieldDisplayed() {
    return plannedStudentsCountField.isDisplayed();
  }

  public boolean isTrainingStudentGroupDropDownDisplayed() {
    return trainingStudentGroupDropDown.isDisplayed();
  }

  public boolean isTrainingCityDropDownDisplayed() {
    return trainingCityDropDown.isDisplayed();
  }

  public boolean isTrainingLabsDropDownPresent() {
    return trainingLabsDropDown.isPresent();
  }

  public boolean isTrainingOwnerDropDownDisplayed() {
    return trainingOwnerDropDown.isDisplayed();
  }

  public boolean isTrainingSupervisorsDropDownDisplayed() {
    return trainingSupervisorsDropDown.isDisplayed();
  }
  public boolean isDDLDisplayed() {
    return DDL.isDisplayed();
  }

  public String getTrainingStartDateValue() {
    return startDateInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getTrainingEndDateValue() {
    return endDateInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getPlannedStudentCountValue() {
    return plannedStudentsCountField.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getOwnerValue() {
    return trainingOwnerInputValue.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getCountryValue() {
    return trainingCountryInputValue.getText();
  }

  public String getCityValue() {
    return trainingCityDropDown.getText();
  }

  public String getLabsValue() {
    return trainingLabsInputValue.getText();
  }

  public boolean isUploadButtonBoxEnabled() {
    return uploadButtonBox.isElementClassEnabled();
  }
}
