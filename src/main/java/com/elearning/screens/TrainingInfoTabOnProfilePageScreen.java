package com.epmrdpt.screens;

import static com.epmrdpt.framework.ui.element.Element.byCss;
import static com.epmrdpt.framework.ui.element.Element.byXpath;
import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import org.openqa.selenium.WebElement;

public class TrainingInfoTabOnProfilePageScreen extends AbstractScreen {

  private static final String LOCATOR_PATTERN = "//div[contains(@ng-click, '%s')]/div[not(contains(@class,'rd-table__sorting-arrow'))]";
  private static final String TRAINING_NAME_XPATH_PATTERN = "./div[1]";
  private static final String GROUP_NAME_XPATH_PATTERN = "./div[2]";
  private static final String GROUP_STATUS_XPATH_PATTERN = "./div[3]";
  private static final String LEARNING_PERIOD_XPATH_PATTERN = "./div[4]";
  private static final String STUDENT_STATUS_XPATH_PATTERN = "./div[5]";
  private static final String STUDENT_STATUS_ON_APPLICATION_TAB_XPATH_PATTERN = ".//span/span";
  private static final String REGISTRATION_DATE_XPATH_PATTERN = ".//div[@class='sub-cells']/span";
  private static final String CONTACT_PERSON_XPATH_PATTERN = "./div[@style]";
  private static final String DEADLINE_TAB_XPATH_PATTERN = ".//span";
  private static final String GO_TO_TEST_BUTTON_XPATH_PATTERN = ".//button[1]";
  private static final String TRAINING_IN_PAGINATION_SECTION_XPATH_PATTERN =
      "//div[contains(@class,'rd-table--desktop')]//div[@class='rd-table__cell']/a[contains(text(),'%s')]";
  private static final String SURVEY_ICON_FOR_TRAINING_XPATH_PATTERN =
      TRAINING_IN_PAGINATION_SECTION_XPATH_PATTERN
          + "/ancestor::div[@ng-repeat]//a[@*='item.IsUserHasSurveyAnswers']";
  private Element trainingSearcherElement = byXpath("//input");
  private Element findButton = byCss("div.training-search__find-button");
  private Element studentGroupHeaderElement = byXpath("//div[@*='studentGroups-tab']");
  private Element applicationHeaderElement = byXpath("//div[@*='applications-tab']");
  private Element currentTestingHeaderElement = byXpath("//div[@*='currentTesting-tab']");
  private Element trainingNameHeaderElement = byXpath(format(LOCATOR_PATTERN, "TrainingName"));
  private Element groupNameHeaderElement = byXpath(format(LOCATOR_PATTERN, "GroupName"));
  private Element groupStatusHeaderElement = byXpath(LOCATOR_PATTERN, "GroupStatus");
  private Element learningPeriodHeaderElement = byXpath(LOCATOR_PATTERN, "StartDate");
  private Element studentStatusHeaderOnApplicationTabElement = byXpath(
      format(LOCATOR_PATTERN, "Status"));
  private Element studentStatusHeaderOnStudentsGroupTabElement = byXpath(
      format(LOCATOR_PATTERN, "StudentStatus"));
  private Element registrationDateElement = byXpath(format(LOCATOR_PATTERN, "CreatedAt"));
  private Element contactPersonElement = byCss(
      "div.rd-table__cell.rd-table__cell--header:last-child div");
  private Element deadlineElement = byXpath(format(LOCATOR_PATTERN, "Deadline"));
  private Element rowOFTraining = byXpath("//div[@*='ng-scope']/div[@ng-repeat]");
  private Element paginationBlockElement = byXpath("//div[@uib-pagination]");

  @Override
  public boolean isScreenLoaded() {
    return isSearchBarDisplayed();
  }

  public TrainingInfoTabOnProfilePageScreen clickStudentGroupTab() {
    studentGroupHeaderElement.clickJs();
    return this;
  }

  public TrainingInfoTabOnProfilePageScreen clickTrainingNameHeaderElement() {
    trainingNameHeaderElement.click();
    return this;
  }

  public TrainingInfoTabOnProfilePageScreen clickCurrentTestingTab() {
    currentTestingHeaderElement.click();
    return this;
  }

  public TrainingInfoTabOnProfilePageScreen clickApplicationTab() {
    applicationHeaderElement.clickJs();
    return this;
  }

  public boolean isSearchBarDisplayed() {
    return trainingSearcherElement.isDisplayed();
  }

  public TrainingInfoTabOnProfilePageScreen typeTrainingIntoSearchInput(String trainingName) {
    trainingSearcherElement.type(trainingName);
    return this;
  }

  public boolean isFindButtonDisplayed() {
    return findButton.isDisplayed();
  }

  public TrainingInfoTabOnProfilePageScreen clickFindButton() {
    findButton.click();
    return this;
  }

  public boolean isTrainingNameHeaderDisplayed() {
    return trainingNameHeaderElement.isDisplayed();
  }

  public boolean isGroupNameHeaderDisplayed() {
    return groupNameHeaderElement.isDisplayed();
  }

  public boolean isGroupStatusHeaderDisplayed() {
    return groupStatusHeaderElement.isDisplayed();
  }

  public boolean isLearningPeriodDisplayed() {
    return learningPeriodHeaderElement.isDisplayed();
  }

  public boolean isRegistrationDateHeaderDisplayed() {
    return registrationDateElement.isDisplayed();
  }

  public boolean isContactPersonHeaderDisplayed() {
    return contactPersonElement.isDisplayed();
  }

  public boolean isStudentStatusOnApplicationTabDisplayed() {
    return studentStatusHeaderOnApplicationTabElement.isDisplayed();
  }

  public boolean isStudentStatusHeaderOnStudentGroupTabDisplayed() {
    return studentStatusHeaderOnStudentsGroupTabElement.isDisplayed();
  }

  public boolean isDeadlineHeaderDisplayed() {
    return deadlineElement.isDisplayed();
  }

  private List<Element> getAllRowOFTrainingOnCurrentTab() {
    return rowOFTraining.getElements();
  }

  public int getQuantityTrainingOnTab() {
    return getAllRowOFTrainingOnCurrentTab().size();
  }

  private WebElement getChildElementByIndexAndXpathPatternFromRowOfTraining(int index,
      String xpathPattern) {
    return getAllRowOFTrainingOnCurrentTab()
        .get(index)
        .findChild(byXpath(xpathPattern));
  }

  public boolean isTrainingNameDisplayedByIndex(int trainingNameIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(trainingNameIndex,
        TRAINING_NAME_XPATH_PATTERN).isDisplayed();
  }

  public boolean isGroupNameDisplayedByIndex(int groupNameIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(groupNameIndex,
        GROUP_NAME_XPATH_PATTERN).isDisplayed();
  }

  public boolean isGroupStatusDisplayedByIndex(int groupStatusIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(groupStatusIndex,
        GROUP_STATUS_XPATH_PATTERN).isDisplayed();
  }

  public boolean isLearningPeriodDisplayedByIndex(int learningPeriodIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(learningPeriodIndex,
        LEARNING_PERIOD_XPATH_PATTERN).isDisplayed();
  }

  public boolean isStudentStatusDisplayedByIndex(int studentStatusIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(studentStatusIndex,
        STUDENT_STATUS_XPATH_PATTERN).isDisplayed();
  }

  public boolean isGoToTestButtonDisplayedByIndex(int studentStatusIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(studentStatusIndex,
        GO_TO_TEST_BUTTON_XPATH_PATTERN).isDisplayed();
  }

  public boolean isStudentStatusApplicationTabDisplayedByIndex(int studentStatusIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(studentStatusIndex,
        STUDENT_STATUS_ON_APPLICATION_TAB_XPATH_PATTERN).isDisplayed();
  }

  public boolean isRegistrationDateDisplayedByIndex(int registrationDateIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(registrationDateIndex,
        REGISTRATION_DATE_XPATH_PATTERN).isDisplayed();
  }

  public boolean isContactPersonDisplayedByIndex(int contactPersonDateIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(contactPersonDateIndex,
        CONTACT_PERSON_XPATH_PATTERN).isDisplayed();
  }

  public boolean isDeadLineDisplayedByIndex(int deadlineIndex) {
    return getChildElementByIndexAndXpathPatternFromRowOfTraining(deadlineIndex,
        DEADLINE_TAB_XPATH_PATTERN).isDisplayed();
  }

  public TrainingInfoTabOnProfilePageScreen waitApplicationHeaderDisplayed() {
    applicationHeaderElement.waitForPresence();
    return this;
  }

  public boolean isPaginationBlockDisplayed() {
    return paginationBlockElement.isDisplayed();
  }

  private Element getTrainingWithSurveyByName(String trainingName) {
    return Element.byXpath(SURVEY_ICON_FOR_TRAINING_XPATH_PATTERN, trainingName);
  }

  public boolean isTrainingHasSurvey(String trainingName) {
    return getTrainingWithSurveyByName(trainingName).isDisplayed();
  }

  public SurveyPopUpScreen clickSurveyIconByTrainingName(String trainingName) {
    getTrainingWithSurveyByName(trainingName).click();
    return new SurveyPopUpScreen();
  }
}
