package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_MANAGEMENT_DESCRIPTION_AGENDA_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_MANAGEMENT_DESCRIPTION_WHAT_IS_ON_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EVENTS_NAVIGATION_MEET_OUR_SPEAKER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_ADD_SPEAKER_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_DESCRIPTION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_ICON_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_REPORT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_PHOTO_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_DESCRIPTION_N_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_ENTER_TEXT_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_REPORT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_START_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_START_DATE_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_START_TIME_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_SPEAKER_NUMBER_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_SPEAKER_ROLE_POSITION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENT_DESCRIPTION_SCREEN_SAVE_CHANGES_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT;
import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import java.util.stream.Collectors;

public class ReactEventDescriptionScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String CONTAINS_TEXT_LOCATOR_PATTERN = "//div[contains(text(),'%s')]";
  private static final String DESCRIPTION_ELEMENTS_BLOCK_PATTERN =
      TEXT_LOCATOR_PATTERN + "/../../../..";
  private static final String WHATS_ON_BLOCK_PATTERN = format(
      DESCRIPTION_ELEMENTS_BLOCK_PATTERN.replace("'", "\""),
      getValueOf(EVENTS_MANAGEMENT_DESCRIPTION_WHAT_IS_ON_LABEL));
  private static final String MEET_OUR_SPEAKERS_BLOCK_PATTERN = format(
      DESCRIPTION_ELEMENTS_BLOCK_PATTERN.replace("'", "\""),
      getValueOf(EVENTS_NAVIGATION_MEET_OUR_SPEAKER));
  private static final String AGENDA_BLOCK_PATTERN = format(
      DESCRIPTION_ELEMENTS_BLOCK_PATTERN.replace("'", "\""),
      getValueOf(EVENTS_MANAGEMENT_DESCRIPTION_AGENDA_LABEL));
  private static final String DATA_ID_LOCATOR_PATTERN = "//div[@data-id='%s']";
  private static final String INPUT_BY_DATA_ID_PATTERN =
      DATA_ID_LOCATOR_PATTERN + "//input";
  private static final String TEXT_FIELD_BY_DATA_ID_PATTERN =
      DATA_ID_LOCATOR_PATTERN + "//div[@class='ql-editor']";
  private static final String INPUT_BY_ID_PATTERN = "//div[@id='%s']//input";
  private final Element addReportButton = Element.byXpath(AGENDA_BLOCK_PATTERN + TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_REPORT_BUTTON));
  private final Element agendaReportInput = Element.byXpath( AGENDA_BLOCK_PATTERN + "//input");
  private final Element agendaReportInputField = Element
      .byXpath(AGENDA_BLOCK_PATTERN + "//input[@placeholder='%s']",
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_ENTER_TEXT_PLACEHOLDER));
  private final Element agendaLabel  = Element.byXpath(TEXT_LOCATOR_PATTERN.replace("'", "\""),
      getValueOf(EVENTS_MANAGEMENT_DESCRIPTION_AGENDA_LABEL));
  private final Element agendaBucketIcon = Element
      .byXpath(AGENDA_BLOCK_PATTERN + "//div[contains(@class,'bucket-icon')]");
  private final Element agendaDescriptionLabel = Element
      .byXpath(AGENDA_BLOCK_PATTERN + TEXT_LOCATOR_PATTERN,
        getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_DESCRIPTION_N_LABEL));
  private final Element agendaDescriptionInputField = Element
      .byXpath(AGENDA_BLOCK_PATTERN + "//input[@placeholder='%s']",
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_ENTER_TEXT_PLACEHOLDER));
  private final Element agendaStartDateInputField = Element
      .byXpath(AGENDA_BLOCK_PATTERN+"//input[@placeholder='%s']",
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_START_DATE_PLACEHOLDER));
  private final Element agendaStartDateLabel = Element
      .byXpath(AGENDA_BLOCK_PATTERN+ TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_START_DATE_LABEL));
  private final Element agendaStartTimeLabel = Element
      .byXpath(String.format(TEXT_LOCATOR_PATTERN,
        getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_START_TIME_LABEL)));
  private final Element agendaStartTimeInputPlaceholder = Element
      .byXpath(AGENDA_BLOCK_PATTERN + "//input[@placeholder='0:00']");
  private final Element agendaReportLabel = Element.
      byXpath(AGENDA_BLOCK_PATTERN+ CONTAINS_TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_REPORT_LABEL));
  private final Element whatIsOnBucketIcon = Element
      .byXpath(WHATS_ON_BLOCK_PATTERN + "//div[contains(@class,'bucket-icon')]");
  private final Element addDescriptionButton = Element
      .byXpath(WHATS_ON_BLOCK_PATTERN + TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_DESCRIPTION_BUTTON));
  private final Element whatIsOnLabel = Element
      .byXpath(TEXT_LOCATOR_PATTERN.replace("'", "\""),
          getValueOf(EVENTS_MANAGEMENT_DESCRIPTION_WHAT_IS_ON_LABEL));
  private final Element whatIsOnDescriptionNLabel = Element
      .byXpath(WHATS_ON_BLOCK_PATTERN + CONTAINS_TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_DESCRIPTION_N_LABEL));
  private final Element whatIsOnEnterTextPlaceholder = Element
      .byXpath(WHATS_ON_BLOCK_PATTERN + "//input[@placeholder='%s']",
          getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT));
  private final Element speakerNumberEnterTextPlaceholder = Element
      .byXpath("(" + MEET_OUR_SPEAKERS_BLOCK_PATTERN + "//input[@placeholder='%s']" + ")[1]",
          getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT));
  private final Element speakerRoleEnterTextPlaceholder = Element
      .byXpath("(" + MEET_OUR_SPEAKERS_BLOCK_PATTERN + "//input[@placeholder='%s']" + ")[2]",
          getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT));
  private final Element speakerRoleDescriptionEnterTextPlaceholder = Element
      .byXpath("(" + MEET_OUR_SPEAKERS_BLOCK_PATTERN + "//input[@placeholder='%s']" + ")[3]",
          getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT));
    private final Element addIconLabel = Element
      .byXpath(WHATS_ON_BLOCK_PATTERN + TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_ICON_LABEL));
  private final Element selectFromTheListDdl = Element
      .byXpath(WHATS_ON_BLOCK_PATTERN + "//input[@placeholder='%s']",
          getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT));
  private final Element metaTagDescriptionInputField = Element
      .byXpath(String.format(INPUT_BY_DATA_ID_PATTERN,
          "eventMetaTagDescription"));
  private final Element metaTagTitleInput = Element
      .byXpath(String.format(INPUT_BY_DATA_ID_PATTERN,
          "eventTagTitle"));
  private final Element metaTagKeywordsInput = Element.byXpath(
      String.format(INPUT_BY_DATA_ID_PATTERN,
          "eventMetaTagsKeyWords"));
  private final Element detailsTextField = Element.byXpath(
      String.format(TEXT_FIELD_BY_DATA_ID_PATTERN,
          "eventDetailsText"));
  private final Element joinUsIfYouTextField = Element.byXpath(
      String.format(TEXT_FIELD_BY_DATA_ID_PATTERN,
          "eventJoinUsText"));
  private final Element speakerName = Element.byXpath(String.format(INPUT_BY_DATA_ID_PATTERN,
      "eventSpeakerName"));
  private final Element speakerRole = Element.byXpath(String.format(INPUT_BY_DATA_ID_PATTERN,
      "eventSpeakerRole"));
  private final Element speakerDescription = Element.byXpath(String.format(INPUT_BY_DATA_ID_PATTERN,
      "eventSpeakerDescription"));
  private final Element whatsOnIcon = Element.byXpath(String.format(INPUT_BY_DATA_ID_PATTERN,
      "eventWhatsOnVideo"));
  private final Element whatsOnDescription = Element.byXpath(String.format(INPUT_BY_ID_PATTERN,
      "eventWhatsOnDescription"));
  private final Element saveChangesButton = Element.byXpath( TEXT_LOCATOR_PATTERN, getValueOf(REACT_EVENT_DESCRIPTION_SCREEN_SAVE_CHANGES_BUTTON));
  private final Element meetOurSpeakersLabel = Element.byXpath(
      TEXT_LOCATOR_PATTERN.replace("'", "\""),
      getValueOf(EVENTS_NAVIGATION_MEET_OUR_SPEAKER));
  private final Element speakerNumberLabel = Element.byXpath(
      MEET_OUR_SPEAKERS_BLOCK_PATTERN + CONTAINS_TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_SPEAKER_NUMBER_LABEL));

  private final Element speakerRolePositionLabel = Element.byXpath(
      MEET_OUR_SPEAKERS_BLOCK_PATTERN + CONTAINS_TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_SPEAKER_ROLE_POSITION_LABEL));
  private final Element speakerRoleDescriptionLabel = Element.byXpath(
      MEET_OUR_SPEAKERS_BLOCK_PATTERN + CONTAINS_TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_DESCRIPTION_N_LABEL));
  private final Element meetOurSpeakersBucketIcon = Element.byXpath(String.format(
      MEET_OUR_SPEAKERS_BLOCK_PATTERN + "//div[contains(@class,'bucket-icon')]"));
  private final Element addPhotoButton = Element.byXpath(MEET_OUR_SPEAKERS_BLOCK_PATTERN
          + TEXT_LOCATOR_PATTERN, getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_ADD_PHOTO_BUTTON));
  private final Element toolTipTextByQuestionElement = Element.byXpath("//div[@id='app']//following::div[contains(@display, 'flex')]");
  private final Element addSpeakerButton = Element.byXpath(MEET_OUR_SPEAKERS_BLOCK_PATTERN
          + TEXT_LOCATOR_PATTERN, getValueOf(REACT_EVENTS_MANAGEMENT_ADD_SPEAKER_BUTTON));
  private Element tooltip = Element.byXpath("//div[@class='uui-tooltip-body']//div[text()]");

  @Override
  public boolean isScreenLoaded() {
    return metaTagDescriptionInputField.isDisplayed();
  }

  public boolean isAgendaReportInputFieldDisplayed() {
    return agendaReportInputField.isDisplayed();
  }

  public boolean isAgendaStartDateInputFieldDisplayed() {
    return agendaStartDateInputField.isDisplayed();
  }

  public boolean isAddReportButtonDisplayed() {
      return addReportButton.isDisplayed();
  }

  public boolean isAgendaStartDateLabelDisplayed() {
    return agendaStartDateLabel.isDisplayed();
  }

  public boolean isAgendaLabelIsDisplayed() {
    return agendaLabel.isDisplayed();
  }

  public boolean isBucketOfAgendaBlockDisplayed() {
    return agendaBucketIcon.isDisplayed();
  }

  public boolean isAgendaStartTimeLabelDisplayed() {
    return agendaStartTimeLabel.isDisplayed();
  }

  public boolean isAgendaReportLabelDisplayed() {
    return agendaReportLabel.isDisplayed();
  }

  public boolean isAgendaStartTimeInputFieldDisplayed() {
    return  agendaStartTimeInputPlaceholder.isDisplayed();
  }

  public boolean isAgendaDescriptionInputFieldDisplayed() {
    return agendaDescriptionInputField.isDisplayed();
  }

  public boolean isAgendaDescriptionLabelIsDisplayed() {
    return agendaDescriptionLabel.isDisplayed();
  }

  public ReactEventDescriptionScreen clickAddReportButton() {
      addReportButton.waitForClickableAndClick();
      return this;
  }

  public ReactEventDescriptionScreen typeMetaTagDescriptionTest(String descriptionText) {
    metaTagDescriptionInputField.clearInput();
    metaTagDescriptionInputField.type(descriptionText);
    return this;
  }

  public ReactEventDescriptionScreen typeMetaTagTitleTest(String descriptionText) {
    metaTagTitleInput.clearInputUsingBackspace();
    metaTagTitleInput.type(descriptionText);
    return this;
  }

  public ReactEventDescriptionScreen typeMetaTagKeywordsTest(String descriptionText) {
    metaTagKeywordsInput.clearInputUsingBackspace();
    metaTagKeywordsInput.type(descriptionText);
    return this;
  }

  public ReactEventDescriptionScreen typeDetailsTextField(String descriptionText) {
    detailsTextField.type(descriptionText);
    return this;
  }

    public ReactEventDescriptionScreen typeJoinUsIfYouTextField(String descriptionText) {
    joinUsIfYouTextField.type(descriptionText);
    return this;
  }

  public ReactEventDescriptionScreen typeSpeakerName(String name) {
    speakerName.clearInputUsingBackspace();
    speakerName.type(name);
    return this;
  }

  public ReactEventDescriptionScreen typeSpeakerRole(String role) {
    speakerRole.clearInputUsingBackspace();
    speakerRole.type(role);
    return this;
  }

  public ReactEventDescriptionScreen typeSpeakerDescription(String description) {
    speakerDescription.clearInputUsingBackspace();
    speakerDescription.type(description);
    return this;
  }

  public ReactEventDescriptionScreen typeAgendaReport(String description) {
    agendaReportInput.clearInputUsingBackspace();
    agendaReportInput.type(description);
    return this;
  }

  public ReactEventDescriptionScreen typeWhatIsOnDescription(String description) {
    whatIsOnEnterTextPlaceholder.clearInputUsingBackspace();
    whatIsOnEnterTextPlaceholder.type(description);
    return this;
  }

  public ReactEventDescriptionScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public ReactEventDescriptionScreen clearMetaTagDescriptionInputField() {
    metaTagDescriptionInputField.click();
    metaTagDescriptionInputField.clearInputUsingBackspace();
    return this;
  }

  public ReactEventDescriptionScreen clickAddDescriptionButton() {
    addDescriptionButton.waitForClickableAndClick();
    return new ReactEventDescriptionScreen();
  }

  public boolean isAddDescriptionButtonDisplayed() {
    return addDescriptionButton.isDisplayed();
  }

  public boolean isWhatIsOnLabelDisplayed() {
    return whatIsOnLabel.isDisplayed();
  }

  public boolean isDescriptionNLabelDisplayed() {
    return whatIsOnDescriptionNLabel.isDisplayed();
  }

  public boolean isEnterTextPlaceholderDisplayed() {
    return whatIsOnEnterTextPlaceholder.isDisplayed();
  }

  public boolean isSpeakerNumberEnterTextPlaceholderDisplayed() {
    return speakerNumberEnterTextPlaceholder.isDisplayed();
  }

  public boolean isSpeakerRoleEnterTextPlaceholderDisplayed() {
    return speakerRoleEnterTextPlaceholder.isDisplayed();
  }

  public boolean isSpeakerRoleDescriptionEnterTextPlaceholderDisplayed() {
    return speakerRoleDescriptionEnterTextPlaceholder.isDisplayed();
  }

  public boolean isAddIconLabelDisplayed() {
    return addIconLabel.isDisplayed();
  }

  public boolean isSelectFromTheListDdlDisplayed() {
    return selectFromTheListDdl.isDisplayed();
  }

  public boolean isBucketOfWhatIsOnBlockDisplayed() {
    return whatIsOnBucketIcon.isDisplayed();
  }

  public String getMetaTagTitleValue() {
    return metaTagTitleInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getMetaTagDescriptionValue() {
    return metaTagDescriptionInputField.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getMetaTagKeywordsValue() {
    return metaTagKeywordsInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getDetailsText() {
    return detailsTextField.getText();
  }

  public String getJoinUsIfYouText() {
    return joinUsIfYouTextField.getText();
  }

  public List<String> getSpeakerNameValue() {
    return speakerName.getElements().stream()
        .map(name -> name.getAttributeValue(VALUE_CSS_PROPERTY)).collect(Collectors.toList());
  }

  public List<String> getSpeakerRoleValue() {
    return speakerRole.getElements().stream()
        .map(name -> name.getAttributeValue(VALUE_CSS_PROPERTY)).collect(Collectors.toList());
  }

  public List<String> getSpeakerDescriptionValue() {
    return speakerDescription.getElements().stream()
        .map(name -> name.getAttributeValue(VALUE_CSS_PROPERTY)).collect(Collectors.toList());
  }

  public List<String> getWhatsOnIconPlaceholder() {
    return whatsOnIcon.getElements().stream()
        .map(name -> name.getAttributeValue(PLACEHOLDER_CSS_PROPERTY)).collect(Collectors.toList());
  }

  public List<String> getWhatsOnDescriptionValue() {
    return whatsOnDescription.getElements().stream()
        .map(name -> name.getAttributeValue(VALUE_CSS_PROPERTY)).collect(Collectors.toList());
  }

  public ReactEventDescriptionScreen waitWhatsOnValueNotEmpty() {
    whatsOnIcon.waitAttributeValueIsNotEmpty(PLACEHOLDER_CSS_PROPERTY);
    return this;
  }

  public boolean isMeetOurSpeakersLabelDisplayed() {
    return meetOurSpeakersLabel.isDisplayed();
  }

  public boolean isSpeakerNumberLabelDisplayed() {
    return speakerNumberLabel.isDisplayed();
  }

  public boolean isSpeakerRolePositionLabelDisplayed() {
    return speakerRolePositionLabel.isDisplayed();
  }

  public boolean isSpeakerRoleDescriptionLabelDisplayed() {
    return speakerRoleDescriptionLabel.isDisplayed();
  }

  public boolean isAddPhotoButtonDisplayed() {
    return addPhotoButton.isDisplayed();
  }

  public boolean isAddSpeakerButtonDisplayed() {
    return addSpeakerButton.isDisplayed();
  }

  public String getToolTipTextByQuestionElement() {
    return toolTipTextByQuestionElement.getTooltipText(tooltip);
  }

  public boolean isBucketOfSpeakerIconDisplayed() {
    return meetOurSpeakersBucketIcon.isDisplayed();}
}
