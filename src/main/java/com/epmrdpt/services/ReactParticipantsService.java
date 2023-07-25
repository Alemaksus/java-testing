package com.epmrdpt.services;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.utils.RandomUtils;
import java.util.List;

public class ReactParticipantsService {

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
  private int randomIndex;

  public ReactParticipantsTrainingScreen findParticipantBySearchPhrase(String searchPhrase) {
     reactParticipantsTrainingScreen
          .typeSearchPhraseToSearchInput(searchPhrase)
          .clickApplyButton();
      if (!reactParticipantsTrainingScreen.isParticipantFindByNameDisplayed(searchPhrase)) {
        reactParticipantsTrainingScreen.clickApplyButton();
      }
    return reactParticipantsTrainingScreen.waitScreenLoading();
    }

  public String getRequiredParticipantsStatusByName(String participantsName) {
    return findParticipantBySearchPhrase(participantsName).getApplicantStatusByParticipantName(
        participantsName);
  }

  public List<String> getApplicantStatusListFromTableMenu() {
    List<String> statusList = reactParticipantsTrainingScreen
        .waitScreenLoading()
        .clickFirstStudentStatus()
        .waitChangeStatusWindowVisibility()
        .getApplicantStatusInChangeStatusWindowText();
    reactParticipantsTrainingScreen.clickFirstStudentStatus();
    return statusList;
  }

  public List<String> getEnglishLevelListFromTableMenu() {
    List<String> englishLevelList = reactParticipantsTrainingScreen
        .waitScreenLoading()
        .clickSelectApplicationEnglishLevelInput()
        .getEnglishLevelWindowText();
    reactParticipantsTrainingScreen.clickApplyButtonlabel();
    return englishLevelList;
  }

  public ReactParticipantsTrainingScreen chooseApplicantStatusInChangeStatusWindowByIndex(
      int statusIndex, List  listOfRejectionIndexes, String rejectionReason) {
    reactParticipantsTrainingScreen
        .clickApplicantStatusInChangeStatusWindowByIndex(statusIndex);
    if (listOfRejectionIndexes.contains(statusIndex)) {
        reactParticipantsTrainingScreen.clickEpamButtonPopUpInApplicantStatus()
            .clickInputRejectionReason()
            .chooseRejectionReason(rejectionReason)
            .clickStatusChangePopUpButton();
      }
    return reactParticipantsTrainingScreen.waitSuccessfulStatusChangedPopUpVisibility()
        .waitScreenLoading()
        .clickStatusPopUpCrossButton();
  }

  public ReactParticipantsTrainingScreen setApplicantStatusToFirstParticipantInParticipantTableByIndex(
      int statusIndex,List  listOfRejectionIndexes, String rejectionReason) {
    reactParticipantsTrainingScreen
        .clickFirstStudentStatus();
    return chooseApplicantStatusInChangeStatusWindowByIndex(statusIndex,  listOfRejectionIndexes, rejectionReason);
  }

  public ReactParticipantsService setEnglishLevelInWindowByIndex(
      int englishLevelIndex) {
    reactParticipantsTrainingScreen
        .clickSelectApplicationEnglishLevelInput()
        .clickEnglishLevelInWindowByIndex(englishLevelIndex)
        .clickApplyButtonlabel()
        .waitSpinnerOfLoadingInvisibility();
    return this;
  }

  public ReactParticipantsTrainingScreen setApplicantStatusToFirstParticipantViaCogwheelByIndex(
      int statusIndex, List  listOfRejectionIndexes, String rejectionReason) {
    reactParticipantsTrainingScreen
        .clickFirstStudentCheckBox()
        .clickCogwheelButton()
        .clickEditApplicantStatusButton()
        .waitChangeStatusWindowVisibility();
    return chooseApplicantStatusInChangeStatusWindowByIndex(statusIndex, listOfRejectionIndexes, rejectionReason);
  }

  public ReactParticipantsTrainingScreen clickAndSelectSortingStudentStatusByIndex(
      int applicantStatusIndex) {
    return reactParticipantsTrainingScreen
        .clickSelectApplicationStatusInput()
        .selectSortingStudentStatusByIndex(applicantStatusIndex)
        .clickOnApplicantStatus()
        .clickApplyButton()
        .waitScreenLoading();
  }

  public boolean checkStudentsStatuses(String status) {
    List<String> studentsStatuses = reactParticipantsTrainingScreen.getAllStatusParticipants();
    boolean check = true;
    for (int i = 0; i < studentsStatuses.size(); i++) {
      if (!studentsStatuses.get(i).equals(status)) {
        check = false;
        i = studentsStatuses.size();
      }
    }
    return check;
  }

  public ReactParticipantsTrainingScreen downloadParticipantsDocumentArchive() {
    return reactParticipantsTrainingScreen
        .clickExcelButton()
        .waitExcelChoicePopUpVisibility()
        .clickDownloadDocumentsArchiveButton()
        .waitForConfirmActionWindowVisibility()
        .clickConfirmActionButton();
  }

  public ReactParticipantsTrainingScreen downloadSelectedParticipantsFile() {
    return reactParticipantsTrainingScreen
        .clickExcelButton()
        .waitExcelChoicePopUpVisibility()
        .clickDownloadParticipantsListButton()
        .waitForConfirmActionWindowVisibility()
        .clickConfirmActionButton();
  }

  public ReactParticipantsTrainingScreen downloadSurveyResultsFile() {
    return reactParticipantsTrainingScreen
        .clickExcelButton()
        .waitExcelChoicePopUpVisibility()
        .clickDownloadSurveyResultsButton()
        .waitForConfirmActionWindowVisibility()
        .clickConfirmActionButton();
  }

  public List<String> selectSeveralFirstStudentsAndGetTheirNames(int numberOfStudents) {
    reactParticipantsTrainingScreen
        .clickOnSeveralStudentsCheckboxElements(numberOfStudents);
    return reactParticipantsTrainingScreen
        .getCheckedStudentsNamesList();
  }

  public ReactParticipantsTrainingScreen moveScrollBarToTheCenter() {
    return reactParticipantsTrainingScreen
        .isEmailColumnHeaderDisplayed()
        ? reactParticipantsTrainingScreen
        : reactParticipantsTrainingScreen
            .clickAfterScrollBarToTheCenter();
  }

  public void toggleCheckBoxByName(String checkBoxName) {
    reactParticipantsTrainingScreen
        .clickEditButton()
        .clickConfigureColumnsCheckBoxByName(checkBoxName)
        .clickConfigureColumnsTabApplyButton();
  }

  public void clickCheckboxInTheConfigurationPopUpScreen() {
    reactParticipantsTrainingScreen
            .clickEditButton()
            .waitConfigureColumnsTabHeaderVisibility();
    randomIndex = RandomUtils.getRandomNumberInInterval(1,
            reactParticipantsTrainingScreen.getSizeOfListColumnsConfigurationPopUp()-1);
    reactParticipantsTrainingScreen
            .clickCheckboxFromConfigurationPopUp(randomIndex)
            .clickApplyPopUpScreenButton()
            .waitScreenLoading();
  }
  public ReactParticipantsTrainingScreen waiteOfEnglishTestToBeLinked() {
    for (int i = 0; i < 10; i++) {
        reactParticipantsTrainingScreen.clickRefreshButton();
        reactParticipantsTrainingScreen.waitScreenLoading();
    }
    return new ReactParticipantsTrainingScreen();
  }
}
