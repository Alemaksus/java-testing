package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_ASSIGNMENT_DEADLINE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_ASSIGNMENT_ID_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_ASSIGNMENT_SUBTITLE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_ASSIGNMENT_TITLE_LABEL;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactAssignmentContainerPopUpScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13192_13193_VerifyThatUserIsAbleToLinkACToTrainingAndATableWithTheRequiredColumnsAppearsInTheACSection",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13192_13193_VerifyThatUserIsAbleToLinkACToTrainingAndATableWithTheRequiredColumnsAppearsInTheACSection {

  private HeaderScreen headerScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactTrainingManagementScreen reactTrainingManagementScreen;
  private ReactSurveyAndTestingTabScreen reactSurveyAndTestingTabScreen;
  private ReactAssignmentContainerPopUpScreen reactAssignmentContainerPopUpScreen;
  private User user;
  private String trainingName = "AutoTest Planned Status";
  private String assignmentContainerID = "7140";
  private String titleInput = "Functional Testing";
  private static final String DATE_STAMP = "30.06.2022";
  private static final String TIME_STAMP = "14:00";
  private static final String PERIOD_IN_DAYS = "5";

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13192_13193_VerifyThatUserIsAbleToLinkACToTrainingAndATableWithTheRequiredColumnsAppearsInTheACSection(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    reactAssignmentContainerPopUpScreen = new ReactAssignmentContainerPopUpScreen();
    headerScreen = new HeaderScreen();
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
  }

  @Test(priority = 1)
  public void checkThatManagerCanOpenPages() {
    SoftAssert softAssert = new SoftAssert();
    reactTrainingManagementScreen = new ReactTrainingManagementScreen();
    softAssert.assertTrue(new HeaderScreen().isScreenLoaded(),
        "Home page is not displayed!");
    headerScreen.clickReactTrainingManagementLink();
    softAssert.assertTrue(reactTrainingManagementScreen.isScreenLoaded(),
        "'Training Management' page is not displayed!");
    reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(trainingName);
    softAssert.assertTrue(reactDetailTrainingScreen.isScreenLoaded(),
        "'Details' page is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatLinkingPopUpDisplayed() {
    reactSurveyAndTestingTabScreen = new ReactSurveyAndTestingTabScreen()
        .clickSurveyAndTestingTabFromTrainingScreen()
        .waitScreenLoading();
    reactTrainingManagementService.deleteACLinkedToTheTrainingIfPresent();
    reactSurveyAndTestingTabScreen.clickLinkByIdButton();
    assertTrue(reactAssignmentContainerPopUpScreen.isScreenLoaded(),
        "'Linking pop-up' is not displayed!");
  }

  @Test(priority = 3)
  public void checkThatNotificationDisplayedAndCorrectAfterDataFilling() {
    reactAssignmentContainerPopUpScreen
        .clickContainerInput()
        .typeIdValue(assignmentContainerID)
        .clickSelectIdAndNameFromDDL(assignmentContainerID,titleInput)
        .clickSetDeadLineInput()
        .waitCalendarVisibility()
        .typeDateInSetDeadLineInput(DATE_STAMP)
        .typeTimeInSetDeadLineInput(TIME_STAMP)
        .clickCustomDeadlineCheckbox()
        .typePeriodInDaysInput(PERIOD_IN_DAYS)
        .clickConfirmationButton();
    assertTrue(reactAssignmentContainerPopUpScreen.isNotificationMessageDisplayed(),
        "Notification message is not displayed!");
  }

  @Test(priority = 4)
  public void checkThatCreatedACIsSaved() {
    reactSurveyAndTestingTabScreen.waitScreenLoading();
    reactDetailTrainingScreen
        .clickSaveChangesButton()
        .waitNotificationPopUpForACVisibility();
    assertTrue(reactDetailTrainingScreen.isNotificationPopUpForACDisplayed(),
        "Created AC is not saved!");
  }

  @Test(priority = 5)
  public void checkDetailsOfACAndItCorrection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.isColumnDisplayed(
        getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_ID_LABEL)), "'ID' column is not displayed!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.getIDColumnInputText()
        .contains(assignmentContainerID), "'ID' input is not correct!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.isColumnDisplayed(
            getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_TITLE_LABEL)),
        "'Title' column is not displayed!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.getTitleColumnInputText()
        .contains(titleInput), "'Title' input is not correct!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.isColumnDisplayed(
            getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_SUBTITLE_LABEL)),
        "'Subtitle' column is not displayed!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.getSubtitleColumnInputText()
        .contains(titleInput), "'Title' input is not correct!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.isCustomDeadlineColumnDisplayed(),
        "'Custom deadline' column is not displayed!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.getCustomDeadlineColumnInputText()
        .contains(PERIOD_IN_DAYS), "'Custom deadline' input is not correct!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.isColumnDisplayed(
            getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_DEADLINE_LABEL)),
        "'Deadline' column is not displayed!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.getDeadlineColumnInputText()
        .contains(DATE_STAMP), "'Deadline' date input is not correct!");
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.getDeadlineColumnInputText()
        .contains(TIME_STAMP), "'Deadline' time input is not correct!");
    softAssert.assertAll();
  }
}
