package com.epmrdpt.smoke.edittraining;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static java.time.LocalDateTime.now;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactAssignmentContainerPopUpScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.services.LoginService;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_100709_VerifyThePossibilityOfLinkingACToAutoAssessmentSelfStudyTrainingType ",
    groups = {"full", "smoke"})
public class EPMRDPT_100709_VerifyThePossibilityOfLinkingACToAutoAssessmentSelfStudyTrainingType {

  private static final String TRAINING_NAME = "AutoTestACAutoAssessmentSelfStudy";
  private static final String ASSIGNMENT_CONTAINER_ID = "9226";
  private static final String ASSIGNMENT_CONTAINER_TITLE = "without exp";
  private static final String PERIOD_IN_DAYS = "5";
  private static final LocalDateTime EVENT_START_TIME = now();
  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
  private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

  private ReactSurveyAndTestingTabScreen reactSurveyAndTestingTabScreen;
  private ReactAssignmentContainerPopUpScreen reactAssignmentContainerPopUpScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenTrainingManagementSurveyAndTestingTab() {
    reactSurveyAndTestingTabScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickTrainingNameByName(TRAINING_NAME)
        .clickSurveyAndTestingTabFromTrainingScreen()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkThatAssignmentContainerPopUpIsOpen() {
    reactAssignmentContainerPopUpScreen = reactSurveyAndTestingTabScreen.clickLinkByIdButton();
    assertTrue(reactAssignmentContainerPopUpScreen.isScreenLoaded(),
        "The 'Assignment Container (Examinator)' pop-up is not opened");
  }

  @Test(priority = 2)
  public void checkThatAllFieldsAreFilledIn() {
    reactAssignmentContainerPopUpScreen.clickContainerInput()
        .typeIdValue(ASSIGNMENT_CONTAINER_ID)
        .clickSelectIdAndNameFromDDL(ASSIGNMENT_CONTAINER_ID, ASSIGNMENT_CONTAINER_TITLE)
        .clickSetDeadLineInput()
        .waitCalendarVisibility()
        .typeDateInSetDeadLineInput(EVENT_START_TIME.format(DATE_FORMAT))
        .typeTimeInSetDeadLineInput(EVENT_START_TIME.format(TIME_FORMAT))
        .clickCustomDeadlineCheckbox()
        .typePeriodInDaysInput(PERIOD_IN_DAYS);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactAssignmentContainerPopUpScreen.getAssignmentContainerName(),
        ASSIGNMENT_CONTAINER_ID + " - " + ASSIGNMENT_CONTAINER_TITLE,
        "Assignment container ID is incorrect");
    softAssert.assertEquals(reactAssignmentContainerPopUpScreen.getDeadlineDate(),
        EVENT_START_TIME.format(DATE_FORMAT), "Deadline date is incorrect");
    softAssert.assertEquals(reactAssignmentContainerPopUpScreen.getDeadlineTime(),
        EVENT_START_TIME.format(TIME_FORMAT), "Deadline time is incorrect");
    softAssert.assertTrue(reactAssignmentContainerPopUpScreen.isCustomDeadlineCheckboxChecked(),
        "Custom deadline checkbox is not checked");
    softAssert.assertEquals(reactAssignmentContainerPopUpScreen.getPeriodInDays(), PERIOD_IN_DAYS,
        "Period in days is incorrect");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatAssignmentContainerPopUpIsClosedWarningToastAppears() {
    reactAssignmentContainerPopUpScreen.clickConfirmationButton();
    assertTrue(reactAssignmentContainerPopUpScreen.isNotificationMessageDisplayed(),
        "The 'Assignment Container (Examinator)' pop-up is not closed, warning toast didn't appear");
  }

  @Test(priority = 4)
  public void checkThatAssignmentContainerIsLinkedToTraining() {
    reactDetailTrainingScreen = new ReactDetailTrainingScreen()
        .closeNotificationPopUp()
        .clickSaveChangesButton()
        .waitNotificationPopUpVisibility()
        .closeNotificationPopUp();
    assertTrue(reactSurveyAndTestingTabScreen.isAssignmentContainerLinkExists(),
        "The Assignment Container is not linked to the training");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void unlinkAssignmentContainer(Method method) {
    if ("checkThatAssignmentContainerIsLinkedToTraining".equals(method.getName())) {
      reactSurveyAndTestingTabScreen
          .clickBusketButton()
          .clickConfirmationDeleteButton();
      reactDetailTrainingScreen
          .closeNotificationPopUp()
          .clickSaveChangesButton()
          .waitNotificationPopUpVisibility()
          .closeNotificationPopUp();
    }
  }
}
