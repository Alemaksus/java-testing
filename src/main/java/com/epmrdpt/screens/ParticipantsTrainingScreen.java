package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ParticipantsTrainingScreen extends AbstractScreen {

  private static final String FIND_PARTICIPANT_BY_NAME_PATTERN = "//ng-include[not(contains(@class, 'ng-hide'))]//user-name-show[text() ='%s']";
  private static final String APPLICANT_STATUS_BY_NAME_PATTERN =
      "//ng-include[not(contains(@class,'ng-hide'))]" +
          "//user-name-show[contains(@name,'userName') and text()='%s']/ancestor::*[contains(@class,'student-row')]"
          + "/descendant::*[contains(@class,'application-status-default')]";
  private static final String APPLICANT_WITH_IN_TRAINING_STATUS_BY_NAME_PATTERN =
      "//ng-include[not(contains(@class,'ng-hide'))]" +
          "//user-name-show[text()='%s']/ancestor::*[contains(@class,'student-row')]"
          + "/descendant::span[contains(@class,'application-status-id-4')]";

  private Element selectApplicationStatusField = Element
      .byCss("div.chzn-container-multi.filter-status");
  private Element dataLoadingPicture = Element
      .byXpath("//div[contains(@class,'users-panel')]/div/i[contains(@class,'fa-spinner')]");
  private Element spinnerOfLoading = Element.byXpath(" //i[contains(@class,'fa-spinner')]");
  private Element trainingName = Element.byCss("strong.plan-name");
  private Element addManyButton = Element.byXpath("//button[@ng-click='showAddManyPopup()']");
  private Element userNameOfModalWindow = Element
      .byXpath("//*[@class='modal-content']//span[contains(@class,'user-name')]");
  private Element checkboxOfModalWindow = Element.byXpath("//td//div[@class='checkbox']");
  private Element addButtonOfModalWindow = Element.byXpath("//button[@ng-click='setUsers()']");
  private Element participantsScreenContainer = Element.byCss("user-name-show");
  private Element sortByDateButton = Element.byXpath("//*[@id='users-table']/thead/tr/th[7]/a");
  private Element numberOfStudentsPerTraining = Element.byXpath(
      "//*[@id='training-participants']//div[@class='pagination-block']/span");

  @Override
  public boolean isScreenLoaded() {
    return waitDataLoading().isSelectApplicationStatusFiledDisplayed();
  }

  public ParticipantsTrainingScreen waitSelectApplicationStatusFiledPresence() {
    selectApplicationStatusField.waitForPresence();
    return this;
  }

  public boolean isSelectApplicationStatusFiledDisplayed() {
    return selectApplicationStatusField.isDisplayed();
  }

  public ParticipantsTrainingScreen waitDataLoading() {
    dataLoadingPicture.waitForInvisibilityWithPageLoadTimeout();
    participantsScreenContainer.isDisplayed();
    return this;
  }

  public boolean isParticipantFindByNameDisplayed(String participantName) {
    return Element.byXpath(String.format(FIND_PARTICIPANT_BY_NAME_PATTERN, participantName))
        .isDisplayed();
  }

  public String getApplicantStatusByParticipantName(String participantName) {
    return Element.byXpath(String.format(APPLICANT_STATUS_BY_NAME_PATTERN, participantName))
        .getText();
  }

  public boolean isApplicantHasInTrainingStatusByParticipantName(String participantName) {
    return (Element
        .byXpath(String
            .format(APPLICANT_WITH_IN_TRAINING_STATUS_BY_NAME_PATTERN, participantName))
        .getElements().size() > 0);
  }

  public ParticipantsTrainingScreen waitSpinnerOfLoadingInvisibility() {
    spinnerOfLoading.waitForInvisibility();
    return this;
  }

  public ParticipantsTrainingScreen waitForLoadingSpinnerInvisible() {
    spinnerOfLoading.waitUntilRequiredElementsAreInvisible(
        spinnerOfLoading.getWrappedWebElements(DEFAULT_TIMEOUT_FOR_PAGE_LOAD));
    return this;
  }

  public boolean isTrainingNameDisplayed() {
    return trainingName.isDisplayed();
  }

  public String getTrainingNameText() {
    return trainingName.getText();
  }

  public ParticipantsTrainingScreen clickAddManyButton() {
    addManyButton.waitForClickableAndClick();
    return this;
  }

  public List<String> getUserNameFromModalWindow() {
    return userNameOfModalWindow.getElementsText();
  }

  public ParticipantsTrainingScreen tickAllUserCheckBoxOfModalWindow() {
    checkboxOfModalWindow.getElements().forEach(Element::waitForClickableAndClick);
    return this;
  }

  public ParticipantsTrainingScreen clickAddButtonOfModalWindow() {
    addButtonOfModalWindow.click();
    return this;
  }

  public UserQuestionaryScreen clickParticipantByName(String name) {
    Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN, name).mouseOver();
    Element.byXpath(FIND_PARTICIPANT_BY_NAME_PATTERN, name).waitForClickableAndClick();
    return new UserQuestionaryScreen();
  }

  public void clickSortByDateButton() {
    sortByDateButton.click();
  }

  public boolean isExpectedNumberOfStudentsInGroup(int maximumNumberStudents) {
    return maximumNumberStudents >= Integer.parseInt(
        numberOfStudentsPerTraining.getText()
            .substring(numberOfStudentsPerTraining.getText().lastIndexOf(" ") + 1));
  }
}
