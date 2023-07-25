package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML.Attribute;

public class GroupDetailsScreen extends AbstractScreen {

  private final static String TRAINER_BY_NAME_LOCATOR_PATTERN = "//div[@user-id]//user-name-show[contains(text(),'%s')]";
  private final static String INFO_POP_UP_HINT_LOCATOR_PATTERN = ".//ancestor::td//div[@class='student-preview']";
  private final static String DELETE_BUTTON_BY_TRAINER_NAME_LOCATOR_PATTERN = ""
      + "//user-name-show[contains(text(),'%s')]//..//..//..//..//..//i[contains(@class,'delete-btn')]";
  private final static String RESULT_FROM_SEARCH_BY_NAME = "//*[contains(@class,'active-result') and contains(text(),'%s')]";
  private final static String STUDENT_NAME_IN_SELECT_STUDENT_DROP_DOWN_PATTERN =
      "//div[contains(@class, 'student-info')]//div[@class='chzn-drop']//li[text()='%s']";
  private final static String STUDENT_STATUS_IN_PARTICIPANT_SECTION_BY_NAME_PATTERN =
      "//user-name-show[text()='%s']/ancestor::tr//span[contains(@class, 'application-status')]";
  private final static String STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN =
      "//table[@class='users-list']//user-name-show[text()='%s']";
  private final static String DELETE_ICON_BY_STUDENT_NAME_PATTERN =
      "//table[@class='users-list']//user-name-show[text()='%s']/ancestor::tr//td[@class='delete-col']/i";
  private final static String APPLICANT_STATUS_BY_NAME_PATTERN =
      "//user-name-show[contains(@name,'userName') and text()='%s']/ancestor::tr//span[contains(@class,'application-status')]";
  private final static String TRAINING_NAME_IN_SELECT_TRAINING_DDL_PATTERN =
      "//li[text()='%s']";
  protected final static String FORMATION_BUTTON = "start-formation";
  protected final static String LEARNING_BUTTON = "start-learning";
  protected final static String FINISHED_BUTTON = "start-finished";

  private Element distributionTab = Element.byCss("a[ui-sref$='distribution']");
  private Element detailsTab = Element.byXpath("//a[contains(@href,'Edit')]");
  private Element groupNameTitle = Element.byCss("strong.group-name");
  private Element spinnerOfLoading = Element.byXpath("//i[contains(@class,'fa-spinner')]");
  private Element participantRowFromParticipantsSectionElement = Element
      .byXpath("//table[@class='users-list']//tr[@ng-repeat='student in group.Students']");
  private Element resultOfChangeMessage = Element.byCss("div.status-message");
  private Element groupNameField = Element.byName("groupName");
  private Element groupGraduationField = Element.byName("groupGraduation");
  private Element saveChangesButton = Element.byXpath("//input[contains(@type,'submit')]");
  private Element trainerRowFromTrainerSectionElement = Element
      .byXpath("//table[@class = 'trainers-list']//tr[@class='ng-scope']");
  private Element trainerNameElement = Element.byXpath(
      "//table[@class = 'trainers-list']//tr[@class='ng-scope']//div[@user-id]//user-name-show");
  private Element modalDialog = Element.byXpath("//*[contains(@class,'modal-content')]");
  private Element modalDialogConfirmButton = Element
      .byXpath(
          "//*[contains(@class,'modal-dialog ')]//button[contains(@ng-click,'removeConfirmed')]");
  private Element addSingleStudentButton = Element.byXpath("//button[@ng-click='addStudent()']");
  private Element addManyStudentsButton = Element
      .byXpath(
          "//button[contains(@ng-click,'addManyStudents')]");
  private Element checkboxFromAddManyStudentsPopUp = Element
      .byXpath("//table[@class='panel']//input[@id='checkAllStudentsPopup']/..");
  private Element checkboxStudentFromAddManyStudentsPopUp = Element
      .byXpath(
          "//table[@class='panel']//input[not(@id='checkAllStudentsPopup') and not(@type='hidden')]/..");
  private Element addStudentsButtonInPopUp = Element
      .byXpath("//*[@class='modal-content']//button[contains(@ng-click,'addManyStudents')]");
  private Element studentsTrashIcons = Element
      .byXpath("//*[contains(@class,'student-info')]//*[contains(@class,'delete-btn')]");
  private Element studentsList = Element
      .byXpath("//div[contains(@user-name,'student')]//user-name-show");
  private Element studentsCheckboxList = Element.byXpath("//td[@class='select-user']//label");
  private Element studentsInStudentInfoSection = Element
      .byXpath("//*[contains(@class,'student-info')]//*[contains(@name,'userName')]");
  private Element studentsPopUpHint = Element.byXpath("//*[@class='student-preview']");
  private Element popUpDeleteButton = Element.byCss("div.modal-footer.ng-scope > button.red");
  private Element selectTrainerInput = Element.byCss("div.trainer-info a span");
  private Element addTrainerButton = Element.byXpath("//input[contains(@name,'addTrainer')]");
  private Element selectTrainerInputAfterClick = Element
      .byXpath("//div[contains(@class,'trainer-info')]//input[@type='text']");
  private Element selectParticipantDropDown = Element.byCss(
      "div.student-info a.chzn-single.chzn-default");
  protected Element learningButton = Element.byXpath(
      "//div[@class='group-statuses-buttons']/button");
  private Element popUpOkButton = Element.byCss("div.modal-footer > button.lime-green");
  private Element popUp = Element.byCss("div.modal-content");
  private Element addStudentToGroupButton = Element.byXpath("//button[@ng-click='addStudent()']");
  private Element okButtonFromModalPopUp = Element
      .byXpath("//button[@ng-click='confirmed(); $dismiss()']");
  private Element currentStatus = Element.byXpath(
      "//span[contains(@class, 'current-group-status')]");
  protected Element formationButton = Element.byClassName(FORMATION_BUTTON);
  private Element checkboxSelectAllStudents = Element.byXpath("//label[@for='all-checkbox']");
  private Element cogwheelIcon = Element.byCss("div.setting-button");
  private Element deleteLabelInCogwheel = Element
      .byXpath("//li[@ng-click='deleteStudentsDialog()']");
  private Element checkboxSelectAllStudentsInPopUp = Element
      .byXpath("//label[@for='checkAllStudentsPopup']");
  private Element requiredNoteText = Element.byClassName("require-note");
  private Element groupNameText = Element.byXpath("//div[1]//span[contains(@class,'col-name')]");
  private Element startDateText = Element.byXpath("//div[4]//span[contains(@class,'col-name')]");
  private Element selectTrainingDropDown = Element.byXpath(
      "//div[contains(@class, 'student-info')]//a[@class='chzn-single']/span");
  private Element freeStudentsSpinnerOfLoading = Element
      .byXpath("//i[@ng-show='freeStudentLoading']");
  private Element finishedButton = Element.byClassName("start-finished");
  private Element startDateField = Element
      .byXpath("//input[contains(@ng-model,'group.startDate')]");
  private Element startDateCalendarIcon = Element
      .byXpath("//button[contains(@ng-click,'datePickerStart')]");
  private Element endDateText = Element.byXpath("//div[5]//span[contains(@class,'col-name')]");
  private Element endDateField = Element.byXpath("//input[contains(@ng-model,'group.endDate')]");
  private Element endDateCalendarIcon = Element
      .byXpath("//button[contains(@ng-click,'datePickerEnd')]");
  private Element inGroupText = Element.byXpath("//div[6]//span[contains(@class,'required')]");
  private Element inGroupCountText = Element.byXpath("//span[contains(@class,'in-group')]");
  private Element graduationCountField = Element
      .byXpath("//input[contains(@ng-model,'group.GraduationCount')]");
  private Element roomNumberText = Element.byXpath("//div[7]//span[contains(@class,'col-name')]");
  private Element roomNumberField = Element.byXpath("//ul[contains(@class,'chzn-choices')]");
  private Element trainerBlockTitle = Element.byCss("div.trainer-info div.title");
  private Element selectTrainersDDLField = Element.byCss("div.trainer-info a.chzn-single");
  private Element addTrainersButton = Element.byXpath("//input[@name='addTrainer']");
  private Element fullTrainerNameColumn = Element.byCss("table.trainers-list th.name-col");
  private Element trainersSkillsColumns = Element.byCss("table.trainers-list th.status-col");
  private Element trainersMentorForColumns = Element.byCss("table.trainers-list th.mentor-col");
  private Element participantBlockTitle = Element.byCss("div.student-info div.title");
  private Element selectTrainingDDLField = Element.byCss("div.chosen-src  a.chzn-single");
  private Element selectStudentOrStudentGroupDDLField =
      Element.byCss("div.student-info a.chzn-single.chzn-default");
  private Element addStudentToClassButton = Element
      .byXpath("//button[@ng-click='assignSubjectToGroup()']");
  private Element addManyStudentToGroupOrClassesButton = Element
      .byXpath("//button[@ng-click='addManyStudents()']");
  private Element endlessCheckBox = Element.byXpath("//*[@name='groupEndless']");
  private Element fullStudentNameOrGroupColumn = Element.byCss("table.users-list th.name-col div");
  private Element currentStatusColumn = Element.byCss("table.users-list th.status-col");
  private Element toTrainingListLink = Element.byXpath(
      "//div[@class='bottom-links']/a[@href='/Management#!']");
  private Element toGroupsListLink = Element.byCss("div.bottom-links a[href$='Groups']");
  private Element toLearningLink = Element.byCss("div.bottom-links a[href*='Platform']");
  private Element hiddenEndDateText = Element.byXpath("//div[contains(@ng-show,'!group.Endless || "
      + "!groupForm.endDate.$valid')][contains(@class,'box ng-hide')]");
  private Element backToTrainingsButton = Element
      .byXpath("//div[@ui-view='head']//a[@ng-href='/Management#!']");

  @Override
  public boolean isScreenLoaded() {
    return distributionTab.isDisplayed();
  }

  public GroupDetailsScreen waitDataLoading() {
    spinnerOfLoading.waitUntilRequiredElementsAreInvisible(
        spinnerOfLoading.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS),
        DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public GroupDetailsScreen waitSpinnerLoadingInvisibility() {
    spinnerOfLoading.waitForInvisibility();
    return this;
  }

  public GroupDetailsScreen waitScreenLoading() {
    distributionTab.waitForVisibility();
    return this;
  }

  private List<Element> getAllTrainerNameCells() {
    return trainerNameElement.getElements();
  }

  private Element getTrainerNameCellByName(String trainerName) {
    Element groupRow = null;
    for (Element cell : getAllTrainerNameCells()) {
      String name = cell.getText();
      if (name.equals(trainerName)) {
        groupRow = cell;
        break;
      }
    }
    return groupRow;
  }

  public boolean isTrainerHasHitAfterHoverMouseHisNameByIndex(String trainerName) {
    Element trainer = getTrainerNameCellByName(trainerName);
    trainer.mouseOver();
    return getTrainerNameCellByName(trainerName)
        .findChild(Element.byXpath(INFO_POP_UP_HINT_LOCATOR_PATTERN))
        .getCssValue(CSS.Attribute.DISPLAY.toString())
        .equals(CSS.Attribute.DISPLAY.getDefaultValue());
  }

  public GroupDetailsScreen waitResultOfChangeMessageDisplayed() {
    resultOfChangeMessage.waitForVisibility();
    return this;
  }

  public GroupDetailsScreen waitResultOfChangeMessageInvisibility() {
    resultOfChangeMessage.waitForInvisibility();
    return this;
  }

  public boolean isResultOfChangeMessageDisplayed() {
    return resultOfChangeMessage.getCssValue(CSS.Attribute.DISPLAY.toString())
        .equals(CSS.Attribute.DISPLAY.getDefaultValue());
  }

  public boolean isResultOfChangeMessagePresent() {
    return resultOfChangeMessage.isPresent();
  }

  public String getGroupNameText() {
    return groupNameField.getAttributeValue(Attribute.VALUE.toString());
  }

  public GroupDetailsScreen typeNewGroupName(String name) {
    groupNameField.waitForClickable();
    groupNameField.type(name);
    return this;
  }

  public GroupDetailsScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public GroupDetailsScreen typeNewGroupGraduation(String newGraduation) {
    groupGraduationField.type(newGraduation);
    return this;
  }

  public String getInGroupGraduationText() {
    return groupGraduationField.getAttributeValue(Attribute.VALUE.toString());
  }

  public ProfileScreen clickTrainerByName(String trainingName) {
    getTrainerNameCellByName(trainingName)
        .click();
    return new ProfileScreen();
  }

  public boolean isTrainerByNameDisplayed(String trainerName) {
    return Element.byXpath(String.format(TRAINER_BY_NAME_LOCATOR_PATTERN, trainerName))
        .isDisplayed();
  }

  public boolean isTrainerByNamePresent(String trainerName) {
    return Element.byXpath(String.format(TRAINER_BY_NAME_LOCATOR_PATTERN, trainerName)).isPresent();
  }

  public GroupDetailsScreen clickDeleteIconByTrainerName(String trainerName) {
    Element.byXpath(String.format(DELETE_BUTTON_BY_TRAINER_NAME_LOCATOR_PATTERN, trainerName))
        .click();
    return this;
  }

  public GroupDetailsScreen clickSelectParticipantDropDown() {
    selectParticipantDropDown.mouseDown();
    return this;
  }

  public GroupDetailsScreen clickFirstStudentNameCheckboxSelectParticipantsPopup() {
    studentsCheckboxList.clickJs();
    return this;
  }

  public GroupDetailsScreen clickStudentNameInSelectParticipantDropDownByName(String studentName) {
    Element.byXpath(String.format(STUDENT_NAME_IN_SELECT_STUDENT_DROP_DOWN_PATTERN, studentName))
        .click();
    return this;
  }

  public GroupDetailsScreen clickDeleteButtonInPopUp() {
    popUpDeleteButton.scrollAndClick();
    return this;
  }

  public GroupDetailsScreen clickSelectTrainerInput() {
    selectTrainerInput.mouseOver();
    selectTrainerInput.click();
    return this;
  }

  public GroupDetailsScreen typeSelectTrainerInput(String trainerName) {
    selectTrainerInput.mouseOver();
    selectTrainerInputAfterClick.waitForPresence().sendKeys(trainerName);
    return this;
  }

  public GroupDetailsScreen clickResultFromSearchByName(String trainerName) {
    Element.byXpath(String.format(RESULT_FROM_SEARCH_BY_NAME, trainerName)).click();
    return this;
  }

  public GroupDetailsScreen clickAddTrainerButton() {
    addTrainerButton.click();
    return this;
  }

  public GroupDetailsScreen clickAddStudentButton() {
    addSingleStudentButton.clickJs();
    return this;
  }

  public String getStudentStatusFromParticipantSection(String studentName) {
    return Element.byXpath(
            String.format(STUDENT_STATUS_IN_PARTICIPANT_SECTION_BY_NAME_PATTERN, studentName))
        .getText();
  }

  public GroupDetailsScreen clickDeleteIconByStudentName(String studentName) {
    Element.byXpath(String.format(DELETE_ICON_BY_STUDENT_NAME_PATTERN, studentName)).click();
    return this;
  }

  public boolean isStudentInParticipantSectionDisplayed(String studentName) {
    return Element.byXpath(String.format(STUDENT_NAME_IN_PARTICIPANT_SECTION_PATTERN, studentName))
        .isDisplayed();
  }

  public GroupDetailsScreen addSomeStudents() {
    addManyStudentsButton.waitForClickable(LONG_TIMEOUT_FOR_PAGE_LOAD).click();
    checkboxFromAddManyStudentsPopUp.isDisplayed();
    checkboxStudentFromAddManyStudentsPopUp.click();
    addStudentsButtonInPopUp.clickJs();
    return this;
  }

  public List<Element> getStudentsTrashButtons() {
    return studentsTrashIcons.getElements();
  }

  public GroupDetailsScreen confirmDeletingIfModalDialogueExist() {
    if (modalDialog.isDisplayed()) {
      modalDialogConfirmButton.clickJs();
    }
    return this;
  }

  public GroupDetailsScreen deleteStudentByIndex(Integer index) {
    getStudentsTrashButtons().get(index).click();
    confirmDeletingIfModalDialogueExist();
    return this;
  }

  public List<Element> getStudentsInStudentInfoSectionList() {
    return studentsInStudentInfoSection.getElements();
  }

  public int getStudentsCountInStudentInfoSection() {
    try {
      return getStudentsInStudentInfoSectionList().size();
    } catch (Exception e) {
      return 0;
    }
  }

  public boolean isStudentsPopUpHintAppeared() {
    return studentsPopUpHint.isDisplayed();
  }

  public String getStudentNameByIndex(int index) {
    return getStudentsInStudentInfoSectionList().get(index).getText();
  }

  public ProfileScreen clickStudentNameByIndex(int index) {
    getStudentsInStudentInfoSectionList().get(index).click();
    return new ProfileScreen();
  }

  public void moveToStudentByIndex(int index) {
    getStudentsInStudentInfoSectionList().get(index).mouseOver();
  }

  public boolean isParticipantsDisplayed() {
    return participantRowFromParticipantsSectionElement.isDisplayed(SHORT_TIME_OUT_IN_SECONDS);
  }

  public boolean isTrainersDisplayed() {
    return trainerRowFromTrainerSectionElement.isDisplayed(SHORT_TIME_OUT_IN_SECONDS);
  }

  public GroupDetailsScreen clickFinishedButton() {
    finishedButton.click();
    return this;
  }

  public GroupDetailsScreen clickLearningButton() {
    learningButton.click();
    return this;
  }

  public GroupDetailsScreen clickPopUpOkButton() {
    popUpOkButton.scrollAndClick();
    return this;
  }

  public boolean isPopUpDisplayed() {
    return popUp.isDisplayed();
  }

  public GroupDetailsScreen waitForPopupVisibility() {
    popUp.waitForVisibility();
    return this;
  }

  public String getResultOfChangeMessageText() {
    return resultOfChangeMessage.getText();
  }

  public GroupDetailsScreen waitFinishedButtonVanish() {
    finishedButton.waitForInvisibility();
    return this;
  }

  public GroupDetailsScreen waitLearningButtonDisplayed() {
    learningButton.waitForVisibility();
    return this;
  }

  public GroupDetailsScreen waitFormationButtonInvisibility() {
    formationButton.waitForInvisibility();
    return this;
  }

  public GroupDetailsScreen clickOkButtonFromModalPopup() {
    okButtonFromModalPopUp.scrollAndClick();
    return this;
  }

  public String getCurrentStatus() {
    return currentStatus.getText();
  }

  public GroupDetailsScreen clickFormationButton() {
    formationButton.click();
    return this;
  }

  public List<String> getStudentNamesList() {
    return studentsInStudentInfoSection.getElementsText();
  }

  public String getApplicantStatusByName(String name) {
    return Element.byXpath(String.format(APPLICANT_STATUS_BY_NAME_PATTERN, name))
        .getText();
  }

  public GroupDetailsScreen clickCheckboxSelectAllStudents() {
    checkboxSelectAllStudents.clickJs();
    return this;
  }

  public GroupDetailsScreen clickCogwheelIcon() {
    cogwheelIcon.clickJs();
    return this;
  }

  public GroupDetailsScreen waitForCogwheelIconVisibility() {
    cogwheelIcon.waitForPresence();
    return this;
  }

  public GroupDetailsScreen clickDeleteLabelInCogwheelIcon() {
    deleteLabelInCogwheel.click();
    return this;
  }

  public GroupDetailsScreen clickAddManyStudentsButton() {
    addManyStudentsButton.click();
    return this;
  }

  public GroupDetailsScreen clickCheckboxSelectAllStudentsInPopUp() {
    checkboxSelectAllStudentsInPopUp.clickJs();
    return this;
  }

  public GroupDetailsScreen clickAddButtonInPopUp() {
    addStudentsButtonInPopUp.click();
    return this;
  }

  public GroupDetailsScreen clickSelectTrainingDropDown() {
    selectTrainingDropDown.mouseDown();
    return this;
  }

  public GroupDetailsScreen mouseOverTrainingNameInSelectTrainingDDL(String trainingName) {
    Element.byXpath(String.format(TRAINING_NAME_IN_SELECT_TRAINING_DDL_PATTERN, trainingName))
        .mouseOver();
    return this;
  }

  public GroupDetailsScreen clickTrainingNameInSelectTrainingDDL(String trainingName) {
    Element.byXpath(String.format(TRAINING_NAME_IN_SELECT_TRAINING_DDL_PATTERN, trainingName))
        .waitForClickableAndClick();
    return this;
  }

  public ReactTrainingManagementScreen clickBackToTrainingsButton() {
    backToTrainingsButton.click();
    return new ReactTrainingManagementScreen();
  }

  public GroupDetailsScreen waitFreeStudentsSpinnerOfLoadingInvisibility() {
    freeStudentsSpinnerOfLoading.waitForInvisibility();
    return this;
  }

  public String getGroupNameTitleText() {
    return groupNameTitle.getText();
  }

  public String getRequiredNoteText() {
    return requiredNoteText.getText();
  }

  public boolean isGroupNameTextDisplayed() {
    return groupNameText.isDisplayed();
  }

  public boolean isGroupNameFieldDisplayed() {
    return groupNameField.isDisplayed();
  }

  public boolean isStartDateTextDisplayed() {
    return startDateText.isDisplayed();
  }

  public boolean isStartDateFieldDisplayed() {
    return startDateField.isDisplayed();
  }

  public boolean isStartDateCalendarIconDisplayed() {
    return startDateCalendarIcon.isDisplayed();
  }

  public boolean isEndDateTextDisplayed() {
    return endDateText.isDisplayed();
  }

  public boolean isEndDateFieldDisplayed() {
    return endDateField.isDisplayed();
  }

  public boolean isEndDateCalendarIconDisplayed() {
    return endDateCalendarIcon.isDisplayed();
  }

  public boolean isInGroupTextDisplayed() {
    return inGroupText.isDisplayed();
  }

  public boolean isInGroupCountTextDisplayed() {
    return inGroupCountText.isDisplayed();
  }

  public boolean isGraduationCountFieldDisplayed() {
    return graduationCountField.isDisplayed();
  }

  public boolean isRoomNumberTextDisplayed() {
    return roomNumberText.isDisplayed();
  }

  public boolean isRoomNumberFieldDisplayed() {
    return roomNumberField.isDisplayed();
  }

  public boolean isSaveChangesButtonDisplayed() {
    return saveChangesButton.isDisplayed();
  }

  public boolean isTrainerBlockTitleDisplayed() {
    return trainerBlockTitle.isDisplayed();
  }

  public boolean isSelectTrainersDDLFieldDisplayed() {
    return selectTrainersDDLField.isDisplayed();
  }

  public boolean isAddTrainersButtonDisplayed() {
    return addTrainersButton.isDisplayed();
  }

  public boolean isFullTrainerNameColumnDisplayed() {
    return fullTrainerNameColumn.isDisplayed();
  }

  public boolean isTrainersSkillsColumnsDisplayed() {
    return trainersSkillsColumns.isDisplayed();
  }

  public boolean isTrainersMentorForColumnsDisplayed() {
    return trainersMentorForColumns.isDisplayed();
  }

  public boolean isParticipantBlockTitleDisplayed() {
    return participantBlockTitle.isDisplayed();
  }

  public boolean isSelectTrainingDDLFieldDisplayed() {
    return selectTrainingDDLField.isDisplayed();
  }

  public boolean isSelectStudentOrStudentGroupDDLFieldDisplayed() {
    return selectStudentOrStudentGroupDDLField.isDisplayed();
  }

  public boolean isAddStudentToGroupButtonDisplayed() {
    return addStudentToGroupButton.isDisplayed();
  }

  public boolean isAddStudentToClassButtonDisplayed() {
    return addStudentToClassButton.isDisplayed();
  }

  public boolean isAddManyStudentToGroupOrClassesButtonDisplayed() {
    return addManyStudentToGroupOrClassesButton.isDisplayed();
  }

  public boolean isEndlessCheckBoxPresent() {
    return endlessCheckBox.isPresent();
  }

  public boolean isFullStudentNameOrGroupColumnDisplayed() {
    return fullStudentNameOrGroupColumn.isDisplayed();
  }

  public boolean isCurrentStatusColumnPresent() {
    return currentStatusColumn.isDisplayed();
  }

  public boolean isToTrainingListLinkDisplayed() {
    return toTrainingListLink.isDisplayed();
  }

  public boolean isToGroupsListLinkDisplayed() {
    return toGroupsListLink.isDisplayed();
  }

  public boolean isToLearningLinkDisplayed() {
    return toLearningLink.isDisplayed();
  }

  public boolean isHiddenEndDateTextPresent() {
    return hiddenEndDateText.isPresent();
  }

  public boolean isButtonDisplayed(ButtonStatus expectedStatus) {
    return Element.byClassName(expectedStatus.getLocatorName()).isDisplayed();
  }

  public enum ButtonStatus {
    LEARNING(LEARNING_BUTTON),
    FORMATION(FORMATION_BUTTON),
    FINISHED(FINISHED_BUTTON);

    private String locatorName;

    ButtonStatus(String name) {
      this.locatorName = name;
    }

    protected String getLocatorName() {
      return locatorName;
    }
  }

  public GroupDetailsScreen waitGroupNameTextToBePresent() {
    groupNameField.waitAttributeValueIsNotEmpty(Attribute.VALUE.toString());
    return this;
  }

  public boolean isNoStudentsInGroupPresent() {
    return studentsList.isAbsent();
  }

  public GroupDetailsScreen waitForStudentsInvisibility() {
    studentsList.waitForInvisibilityShortTimeout();
    return this;
  }

  public GroupDetailsScreen waitForStudentsVisibility() {
    studentsList.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen clickDetailsTab() {
    detailsTab.waitForClickableAndClick();
    return new ReactDetailTrainingScreen();
  }
}
