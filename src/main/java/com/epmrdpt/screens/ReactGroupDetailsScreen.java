package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_DESCRIPTION_SCREEN_HIDE_GROUP_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUCCESSFUL_STATUS_CHANGED_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_FIND_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_ADD_PARTICIPANT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_ADD_TRAINER_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_GROUP_DETAILS_SECTION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_LEARNING_STATUS_CHANGE_BODY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_PARTICIPANTS_SECTION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_REVIEWER_FOR_COLUMN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_SELECT_PARTICIPANT_ARROW_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_TRAINER_BLOCK_INFORMATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_ADD_PARTICIPANT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_GROUP_START_DATE_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_SELECT_ALL_STUDENTS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_SELECT_PARTICIPANT_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TRAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_SKILL_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_OFFLINE_TASK_COMMENT_POP_UP_DELETE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_MANAGEMENT_FULL_NAME;
import static com.epmrdpt.framework.ui.element.Element.ATTRIBUTE_CHECKED;
import static com.epmrdpt.framework.ui.element.Element.ATTRIBUTE_CLASS;
import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML.Attribute;

public class ReactGroupDetailsScreen extends AbstractScreen {

  private final static String ADD_STUDENT_NAME_PATTERN = "//div[@class='uui-popper']//div[contains(text(), '%s')]";
  private final static String STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN =
      "//div[@data-id='training-table']//div[text()='%s']";
  private final static String TRAINER_NAME_IN_TRAINERS_SECTION_PATTERN =
      "//div[@data-id='training-table']//div[text()='%s']";
  private final static String DELETE_ICON_BY_STUDENT_NAME_PATTERN =
      STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN +
          "//following::div[contains(@class, 'uui-button-box')][1]";
  private final static String STUDENT_STATUS_IN_PARTICIPANT_SECTION_BY_NAME_PATTERN =
      STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN +
          "//ancestor::div[contains(@class, 'uui-table-row-container')]/div[2]/div/div/div[3]//div//div[text()]";
  private static final String CONCRETE_DAY_OF_MONTH_PATTERN = "//*[@class='uui-calendar-day' and text()='%s']";
  private static final String NAME_PATTERN = "//div[text()='%s']";
  private static final String DELETE_TRAINER_BY_TRAINER_NAME_PATTERN = TRAINER_NAME_IN_TRAINERS_SECTION_PATTERN
          + "/../../../../div[@role]/../../../following-sibling::div[2]//*[contains(@class,'uui-button-box')]";
  private final static String RESULT_FROM_SEARCH_BY_NAME_PATTERN = "//div[contains(@class,'uui-popper')]"
          + NAME_PATTERN;
  private final static String TRAINING_NAME_IN_SELECT_TRAINING_DDL_PATTERN =
      "//div[text()='%s']";
  private final static String APPLICANT_STATUS_BY_NAME_PATTERN =
      "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'uui-table-row-container')]//*[@color]//*[contains(@class,'text _')]";
  private final static String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String PARTICIPANT_CHECKBOX_IN_PARTICIPANT_SECTION_BY_NAME_PATTERN =
      STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN +
          "/ancestor::div[contains(@class,'uui-table-row-container uui-table-row')]//div[contains(@class,'uui-checkbox')]";
  private final static String TRAINING_NAME_IN_PARTICIPANT_SECTION_PATTERN =
      "//input[@placeholder='%s']";
  private final static String STUDENT_CHECKBOX_IN_SELECT_PARTICIPANT_DROPDOWN_PATTERN =
  "//div[text()='%s']/../../../..//div[contains(@class, 'uui-checkbox')]";

  private Element studentsInStudentInfoSection = Element.byXpath(
      "(//div[@data-id='training-table'])[2]//div[@role='none']//div[contains(@class, 'text _')]");
  private Element firstStudentsDeleteIcon = Element.byXpath(
      "(//div[@data-id='training-table'])[2]/div[2]//div[contains(@class, 'uui-icon')]");
  private Element popUpDeleteButton = Element.byXpath(
      format("//div[contains(@class, 'uui-caption') and text()='%s']",
          getValueOf(PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON)));
  private Element selectParticipantDropDown = Element.byXpath(
      format("//*[contains(@class,'uui-input-box')]//*[contains(text(),'%s')]",
          getValueOf(REACT_GROUP_SCREEN_SELECT_PARTICIPANT_SECTION)));
  private Element selectTrainingInParticipantSectionInput = Element.byXpath(
      format("//div[text()='%s']//preceding::div[contains(@class, 'uui-input-box')][1]",
          getValueOf(REACT_GROUP_SCREEN_SELECT_PARTICIPANT_SECTION)));
  private Element cancelTrainingIconInParticipantSection = Element.byXpath(
      "(//div[contains(@class, 'uui-icon-cancel')])[last()]");
  private Element selectParticipantArrowButton = Element.byXpath(NAME_PATTERN
          + "/../following-sibling::div//div",
      getValueOf(REACT_GROUP_DETAILS_SCREEN_SELECT_PARTICIPANT_ARROW_BUTTON));

  private Element addParticipantButton = Element.byXpath(
      format("//div[text()='%s']", getValueOf(REACT_GROUP_SCREEN_ADD_PARTICIPANT_BUTTON)));
  private Element addSingleStudentButton = Element.byXpath(format("//div[text()='%s']",
      getValueOf(REACT_GROUP_DETAILS_SCREEN_ADD_PARTICIPANT_BUTTON)));
  private Element searchParticipantInput = Element.byXpath("//div[@class='uui-popper']//input");
  private Element addParticipantInput = Element.byXpath("//input[@placeholder='%s']",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_FIND_PLACEHOLDER));
  private Element spinnerContainer = Element.byXpath(
      "//*[contains(@class, 'uui-spinner-container')]");
  private Element groupNameInput = Element.byXpath("//input[1]");
  private Element uuiPopper = Element.byXpath("//*[@class='uui-popper']");
  private Element startDateInput = Element.byXpath("(//input)[2]");
  private Element startDateInputCrossButton =
      Element.byXpath("(//input[@placeholder='DD.MM.YYYY'])[1]//following-sibling::div");
  private Element endDateInput = Element.byXpath("(//input)[3]");
  private Element saveChangesButton = Element.byXpath(
      format("//*[contains(@class, 'uui-button-box')]/div[text()='%s']",
          getValueOf(REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON)));
  private Element resultOfChangeMessage = Element.byXpath(
      "//*[contains(@class, 'uui-snackbar-item-wrapper')]");
  private Element resultOfChangeMessageCross = Element.byXpath(
      "//*[contains(@class, 'uui-snackbar-item-wrapper')]//div[contains(@class, 'uui-button-box')]");
  private Element requiredNote = Element.byXpath("//span[contains(text(),'*')]/..");
  private Element detailElementHeaderTitles = Element.byXpath("//div[@class='uui-label']/.");
  private Element roomNumberInput = Element.byXpath("//div[contains(@class,'uui-placeholder')]");
  private Element calendarStartDateIcon = Element.byXpath(
      "//input[@type='text']/../div[contains(@class,'uui-icon')]");
  private Element calendarEndDateIcon = Element.byXpath(
      "(//input[@type='text']/../div[contains(@class,'uui-icon')])[3]");
  private Element ongoingGroupToggle = Element.byXpath("//div[@class='uui-switch-body']");
  private Element hideGroupCheckbox = Element.byXpath("//div[text() = '%s']//parent::div//div[1]",
          getValueOf(GROUP_DESCRIPTION_SCREEN_HIDE_GROUP_TEXT));
  private Element trainerBlockTitle = Element.byXpath(NAME_PATTERN,
      getValueOf(REACT_TRAINING_CLASS_TRAINER));
  private Element trainerBlockInformation = Element
      .byXpath(NAME_PATTERN, getValueOf(REACT_GROUP_DETAILS_SCREEN_TRAINER_BLOCK_INFORMATION));
  private Element addTrainersDDL = Element.byXpath(
      "//*[text()='%s']/../preceding-sibling::div//*[contains(@class,'uui-input-box')]",
      getValueOf(REACT_GROUP_DETAILS_SCREEN_ADD_TRAINER_BUTTON));
  private Element addTrainersInput = Element.byXpath(
      "//*[contains(@class,'uui-input-box')]/descendant::input[@placeholder='%s']",
      getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_FIND_PLACEHOLDER));
  private Element addTrainerButton = Element
      .byXpath(NAME_PATTERN, getValueOf(REACT_GROUP_DETAILS_SCREEN_ADD_TRAINER_BUTTON));
  private Element fullTrainerNameColumn = Element
      .byXpath("//div[text()=\"%s\"]", getValueOf(USER_MANAGEMENT_FULL_NAME));
  private Element trainersSkillsColumn = Element
      .byXpath(NAME_PATTERN, getValueOf(REACT_TRAINING_MANAGEMENT_SKILL_LABEL));
  private Element reviewerForColumn = Element
      .byXpath(NAME_PATTERN, getValueOf(REACT_GROUP_DETAILS_SCREEN_REVIEWER_FOR_COLUMN));
  private Element toGroupsListLink = Element.byXpath("//a[contains(@href,'/groups')][@aria-current]");
  private Element toTrainingListLink = Element.byXpath("//a[contains(@href,'journals')]");
  private Element usersPopUpHint = Element.byXpath("//*[@data-id='user-info-card']");
  private Element userNameInPopUpHintLink = Element.byXpath("(//*[@data-id='user-info-card']//a)[2]");
  private Element statusPopUpCrossButton = Element.byXpath(
      "//div[@class='uui-snackbar-item-self']//div[contains(@class,'button')]//*[name()='svg']/..");
  private Element groupDetailsSection = Element.byXpath("//div[text()='%s']/../../following-sibling::div[2]",
      getValueOf(REACT_GROUP_DETAILS_SCREEN_GROUP_DETAILS_SECTION_TITLE));
  private Element trainersSection = Element.byXpath("//div[text()='%s']/../../following-sibling::div[3]",
      getValueOf(REACT_TRAINING_CLASS_TRAINER));
  private Element participantsSection = Element.byXpath("//div[text()='%s']/../../following-sibling::div[3]",
      getValueOf(REACT_GROUP_DETAILS_SCREEN_PARTICIPANTS_SECTION_TITLE));
  private Element participantsSelectedSubtitle =
      Element.byXpath(
          "(//div[text()='%s' and not(@class='uui-caption')]/../../following::div//div[@display='grid']/div)[1]",
          getValueOf(REACT_GROUP_DETAILS_SCREEN_PARTICIPANTS_SECTION_TITLE));
  private Element participantsCheckboxes =
      Element.byXpath("//div[contains(@class,'uui-table-row-container uui-table-row')]//div[contains(@class, 'uui-checkbox')]");
  private Element selectAllStudentsButton = Element.byXpath(format("//div[text()='%s']",
      getValueOf(REACT_GROUP_SCREEN_SELECT_ALL_STUDENTS_BUTTON)));
  private Element studentsNamesFrommAddDDL = Element.byXpath(
      "//*[@class='uui-popper']//*[contains(text(),'@')]/preceding-sibling::*");
  private Element selectAllParticipantsCheckbox = Element.byXpath(
      "//*[contains(@class,'uui-table-header-row')]//*[@class='uui-checkbox']");
  private Element deleteParticipantsButton = Element.byXpath(format(
      "//div[text()='%s']", getValueOf(REACT_TRAINING_OFFLINE_TASK_COMMENT_POP_UP_DELETE_BUTTON)));
  private Element settingsInParticipantSectionButton = Element.byXpath(
      "(//div[@data-id='training-table'])[2]/../../preceding-sibling::*/../div[3]//*[name()='svg']");
  private Element popUp = Element.byXpath("//div[@class='uui-popper']");
  private Element statusChangePopUp = Element.byXpath("//div[contains(@class,'uui-modal-window')]");
  private Element learningStatusButton = Element.byXpath("(//div[@role='button'])[2]");
  private Element learningStatusPopUpHeader = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE)));
  private Element closeLearningStatusChangePopUpButton = Element
      .byXpath("//div[contains(@class,'uui-modal-window')]//div[contains(@class,'uui-icon')]");
  private Element statusChangePopUpBody = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_GROUP_DETAILS_SCREEN_LEARNING_STATUS_CHANGE_BODY)));
  private Element statusChangePopUpCancelButton = Element.byXpath(String
      .format(TEXT_LOCATOR_PATTERN, getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON)));
  private Element statusChangePopUpYesButton = Element.byXpath(String
      .format(TEXT_LOCATOR_PATTERN, getValueOf(PARTICIPANTS_ACTIONS_CHECK_CONFIRMATION_BUTTON)));
  private Element invalidMessage = Element.byXpath("//div[contains(@class,'uui-invalid-message')]");
  private Element iconNextToHideGroup =
      Element.byXpath(TEXT_LOCATOR_PATTERN + "/following::div[2]",
          getValueOf(GROUP_DESCRIPTION_SCREEN_HIDE_GROUP_TEXT));
  private Element tooltip = Element.byXpath("//div[@class='uui-tooltip-body']");
  private final Element saveChangesPopup = Element
          .byXpath(NAME_PATTERN, getValueOf(PARTICIPANTS_SUCCESSFUL_STATUS_CHANGED_POP_UP));
  private final Element closeSaveChangesPopupButton = Element
          .byXpath(NAME_PATTERN + "/following::div//div ",
                  getValueOf(PARTICIPANTS_SUCCESSFUL_STATUS_CHANGED_POP_UP));

  @Override
  public boolean isScreenLoaded() {
    return addParticipantButton.isDisplayed();
  }

  public boolean isParticipantsDisplayedShortTimeOut() {
    return studentsInStudentInfoSection.isDisplayedShortTimeOut();
  }

  public ReactGroupDetailsScreen clickTrainingNameInSelectTrainingDDL(String trainingName) {
    selectTrainingInParticipantSectionInput.click();
    selectTrainingInParticipantSectionInput.typeWithoutClear(trainingName);
    Element.byXpath(String.format(TRAINING_NAME_IN_SELECT_TRAINING_DDL_PATTERN, trainingName))
        .waitForClickableAndClick();
    return this;
  }

  public ReactGroupDetailsScreen clickSelectAllStudentsButton() {
    selectAllStudentsButton.waitForClickableAndClick();
    return this;
  }

  public List<String> getStudentsNamesFromAddStudentDDL() {
    return studentsNamesFrommAddDDL.getElements()
        .stream().map(e -> e.getText()).collect(Collectors.toList());
  }

  public void deleteAllParticipants() {
    while (isParticipantsDisplayedShortTimeOut()) {
      firstStudentsDeleteIcon.mouseOverAndClick();
      clickDeleteButtonInPopUp();
    }
  }

  public ReactGroupDetailsScreen waitForStudentsInvisibility() {
    studentsInStudentInfoSection.waitForInvisibilityShortTimeout();
    return this;
  }

  public ReactGroupDetailsScreen waitResultOfChangeMessageInvisibility() {
    resultOfChangeMessage.waitForInvisibility();
    return this;
  }

  public ReactGroupDetailsScreen clickCancelTrainingIconInParticipantsSection() {
    cancelTrainingIconInParticipantSection.mouseOverAndClick();
    return this;
  }

  public ReactGroupDetailsScreen waitDataLoading() {
    studentsInStudentInfoSection.isDisplayed();
    saveChangesButton.isDisplayed();
    return this;
  }

  public boolean isStudentInParticipantSectionDisplayed(String studentName) {
    return Element.byXpath(format(STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN, studentName))
        .isDisplayed();
  }

  public ReactGroupDetailsScreen clickDeleteIconByStudentName(String studentName) {
    Element.byXpath(format(DELETE_ICON_BY_STUDENT_NAME_PATTERN, studentName)).click();
    return this;
  }

  public ReactGroupDetailsScreen clickDeleteButtonInPopUp() {
    popUpDeleteButton.click();
    return this;
  }

  public ReactGroupDetailsScreen clickSelectParticipantArrowButton() {
    selectParticipantArrowButton.waitForVisibility().click();
    return this;
  }

  public ReactGroupDetailsScreen clickStudentNameInSelectParticipantDropDownByName(String studentName) {
    searchParticipantInput.click();
    searchParticipantInput.clearInputUsingBackspace();
    searchParticipantInput.type(studentName);
    Element.byXpath(format(ADD_STUDENT_NAME_PATTERN, studentName)).waitForPresence().click();
    return this;
  }

  public ReactGroupDetailsScreen clearInputAfterTypingNameParticipantDropDown() {
    searchParticipantInput.click();
    searchParticipantInput.clearInputUsingBackspace();
    return this;
  }

  public ReactGroupDetailsScreen clickAddParticipantButton() {
    addParticipantButton.click();
    return this;
  }

  public String getStudentStatusFromParticipantSection(String studentName) {
    return Element.byXpath(format(STUDENT_STATUS_IN_PARTICIPANT_SECTION_BY_NAME_PATTERN, studentName))
        .getText();
  }

  public ReactGroupDetailsScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIMEOUT_FOR_PAGE_LOAD));
    return this;
  }

  public ReactGroupDetailsScreen waitGroupNameTextToBePresent() {
    groupNameInput.waitAttributeValueIsNotEmpty(Attribute.VALUE.toString());
    return this;
  }

  public ReactGroupDetailsScreen typeNewGroupName(String name) {
    groupNameInput.waitForClickableAndClick();
    groupNameInput.clearInputUsingBackspace();
    groupNameInput.type(name);
    return this;
  }

  public String getGroupNameText() {
    return groupNameInput.getAttributeValue(Attribute.VALUE.toString());
  }

  public String getStartDateText() {
    return startDateInput.getAttributeValue(Attribute.VALUE.toString());
  }

  public String getEndDateText() {
    return endDateInput.getAttributeValue(Attribute.VALUE.toString());
  }

  public ReactGroupDetailsScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public ReactGroupDetailsScreen clickStartDateInputField() {
    startDateInput.click();
    return this;
  }

  public ReactGroupDetailsScreen clickEndDateInputField() {
    endDateInput.click();
    return this;
  }

  public ReactGroupDetailsScreen waitResultOfChangeMessageDisplayed() {
    resultOfChangeMessage.waitForVisibility();
    return this;
  }

  public boolean isResultOfChangeMessageDisplayed() {
    return resultOfChangeMessage.getCssValue(CSS.Attribute.DISPLAY.toString())
        .equals(CSS.Attribute.DISPLAY.getDefaultValue());
  }

  public ReactGroupDetailsScreen clickDayOfMonthDateButton(int day) {
    Element.byXpath(CONCRETE_DAY_OF_MONTH_PATTERN, day).click();
    return this;
  }

  public ReactGroupDetailsScreen waitDateCalendarCollapsed() {
    uuiPopper.waitForInvisibility();
    return this;
  }

  public ReactGroupDetailsScreen clickOnResultOfChangeMessageCross() {
    resultOfChangeMessageCross.mouseOverAndClick();
    return this;
  }

  public int getStudentsCountInStudentInfoSection() {
    return getStudentsInStudentInfoSectionList().size();
  }

  public List<Element> getStudentsInStudentInfoSectionList() {
    studentsInStudentInfoSection.mouseOver();
    return studentsInStudentInfoSection.getElements();
  }

  public String getStudentNameByIndex(int index) {
    return getStudentsInStudentInfoSectionList().get(index).getText();
  }

  public ReactGroupDetailsScreen clickOnFirstStudent() {
    studentsInStudentInfoSection.click();
    return this;
  }

  public boolean isUsersPopUpHintAppeared() {
    return usersPopUpHint.isDisplayed();
  }

  public ProfileScreen clickOnUserNameInPopUpHint() {
    userNameInPopUpHintLink.waitForVisibility();
    userNameInPopUpHintLink.mouseOver();
    userNameInPopUpHintLink.click();
    return new ProfileScreen();
  }

  public String getFirstStudentName() {
    return studentsInStudentInfoSection.getText();
  }

  public ReactGroupDetailsScreen clickTrainerInTrainersSectionByName(String trainerName) {
    Element.byXpath(format(TRAINER_NAME_IN_TRAINERS_SECTION_PATTERN, trainerName))
        .waitForClickableAndClick();
    return this;
  }

  public boolean isStatusNameDisplayed(String statusName) {
    return Element.byXpath(NAME_PATTERN, statusName).isDisplayed();
  }

  public String getRequiredNoteText() {
    return requiredNote.getText();
  }

  public List<String> getDetailElementHeaderTitlesText() {
    return detailElementHeaderTitles.getElementsText();
  }

  public boolean isGroupNameInputDisplayed() {
    return groupNameInput.isDisplayed();
  }

  public boolean isRoomNumberInputDisplayed() {
    return roomNumberInput.isDisplayed();
  }

  public boolean isStartDateInputDisplayed() {
    return startDateInput.isDisplayed();
  }

  public boolean isEndDateInputDisplayed() {
    return endDateInput.isDisplayed();
  }

  public boolean isCalendarStartDateIconDisplayed() {
    return calendarStartDateIcon.isDisplayed();
  }

  public boolean isCalendarEndDateIconDisplayed() {
    return calendarEndDateIcon.isDisplayed();
  }

  public boolean isOngoingGroupToggleDisplayed() {
    return ongoingGroupToggle.isDisplayed();
  }

  public boolean isHideGroupCheckboxDisplayed() {
    return hideGroupCheckbox.isDisplayed();
  }

  public boolean isSaveChangesButtonDisplayed() {
    return saveChangesButton.isDisplayed();
  }

  public boolean isTrainerBlockTitleDisplayed() {
    return trainerBlockTitle.isDisplayed();
  }

  public boolean isTrainerBlockInformationDisplayed() {
    return trainerBlockInformation.isDisplayed();
  }

  public boolean isAddTrainersInputDisplayed() {
    return addTrainersDDL.isDisplayed();
  }

  public boolean isAddTrainerButtonDisplayed() {
    return addTrainerButton.isDisplayed();
  }

  public boolean isFullTrainerNameColumnDisplayed() {
    return fullTrainerNameColumn.isDisplayed();
  }

  public boolean isTrainersSkillsColumnDisplayed() {
    return trainersSkillsColumn.isDisplayed();
  }

  public boolean isReviewerForColumnDisplayed() {
    return reviewerForColumn.isDisplayed();
  }

  public boolean isToTrainingListLinkDisplayed() {
    return toTrainingListLink.isDisplayed();
  }

  public boolean isToGroupsListLinkDisplayed() {
    return toGroupsListLink.isDisplayed();
  }

  public String getToTrainingListLinkText(){
    return toTrainingListLink.getText();
  }

  public String getToGroupsListLinkText(){
    return toGroupsListLink.getText();
  }

  public ReactGroupDetailsScreen clickStatusPopUpCrossButton() {
    statusPopUpCrossButton.waitForClickableAndClick();
    return this;
  }

  public boolean isTrainerByNameDisplayed(String trainerName) {
    return Element.byXpath(String.format(TRAINER_NAME_IN_TRAINERS_SECTION_PATTERN, trainerName))
        .isDisplayed();
  }

  public boolean isStudentByNameDisplayed(String studentName) {
    return Element.byXpath(String.format(STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN, studentName))
        .isDisplayed();
  }

  public ReactGroupDetailsScreen clickDeleteIconByTrainerName(String trainerName) {
    Element.byXpath(String.format(DELETE_TRAINER_BY_TRAINER_NAME_PATTERN, trainerName)).click();
    return this;
  }

  public ReactGroupDetailsScreen typeAddTrainerInput(String trainerName) {
    addTrainersDDL.mouseOverAndClick();
    waitPopperVisibility();
    addTrainersInput.type(trainerName);
    return this;
  }

  public ReactGroupDetailsScreen clickResultFromSearchByName(String trainerName) {
    Element.byXpath(String.format(RESULT_FROM_SEARCH_BY_NAME_PATTERN, trainerName)).click();
    return this;
  }

  public ReactGroupDetailsScreen clickAddTrainerButton() {
    addTrainerButton.waitForPresence();
    addTrainerButton.click();
    return this;
  }

  public boolean isGroupDetailsSectionDisplayed() {
    return groupDetailsSection.isDisplayed();
  }

  public boolean isTrainersSectionDisplayed() {
    return trainersSection.isDisplayed();
  }

  public boolean isParticipantsSectionDisplayed() {
    return participantsSection.isDisplayed();
  }

  public ReactGroupDetailsScreen clickSelectParticipantDropDown() {
    scrollToBottom();
    selectParticipantDropDown.mouseOver();
    selectParticipantDropDown.click();
    return this;
  }

  public ReactGroupDetailsScreen clickDeleteAllParticipantsButton() {
    deleteParticipantsButton.clickJs();
    return this;
  }

  public ReactGroupDetailsScreen waitPopperVisibility() {
    uuiPopper.waitForVisibility();
    return this;
  }

  public boolean isStudentInPopperDropDownMenuDisplayed() {
    return studentsNamesFrommAddDDL.isDisplayed();
  }

  public List<String> getStudentsNamesStudentInfoSection() {
    return studentsInStudentInfoSection.getElements()
        .stream().map(Element::getText).collect(Collectors.toList());
  }

  public String getApplicantStatusByName(String name) {
    return Element.byXpath(String.format(APPLICANT_STATUS_BY_NAME_PATTERN, name)).getText();
  }

  public ReactGroupDetailsScreen clickCheckBoxAllStudentsInParticipants() {
    scrollToBottom();
    selectAllParticipantsCheckbox.click();
    return this;
  }

  public ReactGroupDetailsScreen clickSettingsInParticipantSectionButton() {
    settingsInParticipantSectionButton.click();
    return this;
  }

  public ReactGroupDetailsScreen clickLearningStatusButton() {
    learningStatusButton.click();
    return this;
  }

  public boolean isStatusChangePopUpDisplayed() {
    return statusChangePopUp.isDisplayed();
  }

  public String getLearningStatusChangePopUpHeaderText() {
    return learningStatusPopUpHeader.getText();
  }

  public boolean isLearningStatusChangePopUpHeaderDisplayed() {
    return learningStatusPopUpHeader.isDisplayed();
  }

  public boolean isCloseLearningStatusChangePopUpButtonDisplayed() {
    return closeLearningStatusChangePopUpButton.isDisplayed();
  }

  public boolean isCloseLearningStatusChangePopUpButtonClickable() {
    return closeLearningStatusChangePopUpButton.isClickable();
  }

  public String getLearningStatusChangePopUpBodyText() {
    return statusChangePopUpBody.getText();
  }

  public boolean isLearningStatusChangePopUpBodyTextDisplayed() {
    return statusChangePopUpBody.isDisplayed();
  }

  public boolean isLearningStatusChangePopUpCancelButtonDisplayed() {
    return statusChangePopUpCancelButton.isDisplayed();
  }

  public boolean isLearningStatusChangePopUpCancelButtonClickable() {
    return statusChangePopUpCancelButton.isClickable();
  }

  public String getLearningStatusChangePopUpCancelButtonText() {
    return statusChangePopUpCancelButton.getText();
  }

  public boolean isLearningStatusChangePopUpYesButtonDisplayed() {
    return statusChangePopUpYesButton.isDisplayed();
  }

  public boolean isLearningStatusChangePopUpYesButtonClickable() {
    return statusChangePopUpYesButton.isClickable();
  }

  public String getLearningStatusChangePopUpYesButtonText() {
    return statusChangePopUpYesButton.getText();
  }

  public ReactGroupDetailsScreen clickStartDateInputCrossButton() {
    startDateInputCrossButton.waitForClickableAndClick();
    return this;
  }

  public ReactGroupDetailsScreen waitUntilStartDateAttributeGetsValue(
      String attributeName,
      String value
  ) {
    startDateInput.waitUntilAttributeGetsValue(attributeName, value);
    return this;
  }

  public ReactGroupDetailsScreen waitUntilStartDateAttributeValueIsEmpty() {
    waitUntilStartDateAttributeGetsValue(VALUE_CSS_PROPERTY, "");
    return this;
  }

  public ReactGroupDetailsScreen typeDateToStartDate(String date) {
    startDateInput.type(date);
    return this;
  }

  public String getEndDateInputValue() {
    return endDateInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public ReactGroupDetailsScreen clickStartDateLabel() {
    Element.byXpath(
            TEXT_LOCATOR_PATTERN,
            getValueOf(REACT_GROUP_SCREEN_GROUP_START_DATE_SECTION))
        .click();
    return this;
  }

  public boolean isInvalidMessageDisplayed() {
    return invalidMessage.isDisplayed();
  }

  public ReactGroupScreen clickToGroupsListLink() {
    toGroupsListLink.waitForClickableAndClick();
    return new ReactGroupScreen();
  }

  public ReactGroupJournalScreen clickToTrainingListLink() {
    toTrainingListLink.click();
    return new ReactGroupJournalScreen();
  }

  public boolean isIconNextToHideGroupDisplayed() {
    return iconNextToHideGroup.isDisplayedShortTimeOut();
  }

  public ReactGroupDetailsScreen mouseOverIconNextToHideGroup() {
    iconNextToHideGroup.mouseOver();
    return this;
  }

  public String getTooltipTextOfIconNextToHideGroup() {
    return iconNextToHideGroup.getTooltipText(tooltip);
  }

  public String getParticipantsSelectedSubtitleText() {
    return participantsSelectedSubtitle.getText();
  }

  public boolean isSelectAllParticipantsCheckboxPresentNoWait() {
    return selectAllParticipantsCheckbox.isPresentNoWait();
  }

  public int getParticipantsCheckboxesCount() {
    return participantsCheckboxes.getElements().size();
  }

  public ReactGroupDetailsScreen clickFirstParticipantCheckbox() {
    participantsCheckboxes.click();
    return this;
  }

  public ReactGroupDetailsScreen clickParticipantCheckboxByName(String studentName) {
    Element.byXpath(PARTICIPANT_CHECKBOX_IN_PARTICIPANT_SECTION_BY_NAME_PATTERN, studentName)
        .click();
    return this;
  }

  public boolean isCheckboxByStudentNameChecked(String studentName) {
    return Element.byXpath(
            format(PARTICIPANT_CHECKBOX_IN_PARTICIPANT_SECTION_BY_NAME_PATTERN, studentName))
        .getAttributeValue(ATTRIBUTE_CLASS)
        .contains(Element.ATTRIBUTE_CHECKED);
  }

  public String getSelectedParticipantsCount() {
    return getParticipantsSelectedSubtitleText()
        .replace(getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT), "")
        .trim();
  }

  public boolean isDeleteStudentByNameButtonDisabled(String studentName) {
    return Element.byXpath(format(DELETE_ICON_BY_STUDENT_NAME_PATTERN, studentName))
        .waitForElementClassDisabled()
        .getAttributeValue(ATTRIBUTE_CLASS)
        .contains(Element.DISABLED_ATTRIBUTE);
  }

  public String getDeleteStudentByNameIconHintText(String studentName) {
    return Element.byXpath(format(DELETE_ICON_BY_STUDENT_NAME_PATTERN, studentName))
        .waitForElementClassDisabled()
        .getTooltipText(tooltip);
  }

  public boolean isDeleteButtonDisplayed() {
    return deleteParticipantsButton.isDisplayed();
  }

  public boolean isTrainingNameDisplayed(String trainingName) {
    return Element.byXpath(String.format(TRAINING_NAME_IN_PARTICIPANT_SECTION_PATTERN, trainingName))
        .isDisplayed();
  }

  public boolean isCheckboxByStudentNameInDropDownChecked(String studentName) {
    return Element.byXpath(
            format(STUDENT_CHECKBOX_IN_SELECT_PARTICIPANT_DROPDOWN_PATTERN, studentName))
        .getAttributeValue(ATTRIBUTE_CLASS)
        .contains(Element.ATTRIBUTE_CHECKED);
  }

  public ReactGroupDetailsScreen chooseStudentNameInSelectParticipantDropDownByName(String studentName) {
    selectParticipantArrowButton.click();
    addParticipantInput.type(studentName);
    Element.byXpath(format(ADD_STUDENT_NAME_PATTERN, studentName)).waitForPresence().click();
    return this;
  }

  public ReactGroupDetailsScreen clickHideGroupCheckbox() {
    hideGroupCheckbox.click();
    return this;
  }

  public boolean isHideGroupCheckboxChecked() {
    return hideGroupCheckbox.getWrappedWebElement().getAttribute("class").contains("checked");
  }

  public boolean isParticipantCheckboxChecked() {
    return participantsCheckboxes.getAttributeValue(ATTRIBUTE_CLASS).contains(ATTRIBUTE_CHECKED);
  }

  public boolean isSaveChangesPopUpDisplayed() {
    return saveChangesPopup.isDisplayed();
  }

  public ReactGroupDetailsScreen closeSaveChangesPopup() {
    closeSaveChangesPopupButton.click();
    return this;
  }
}
