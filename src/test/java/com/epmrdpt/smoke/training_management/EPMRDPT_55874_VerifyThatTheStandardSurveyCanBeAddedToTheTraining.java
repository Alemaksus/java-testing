package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_STANDARD_SURVEY_TYPE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_LABEL;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_55874_VerifyThatTheStandardSurveyCanBeAddedToTheTraining",
    groups = {"full", "smoke", "manager"})
public class EPMRDPT_55874_VerifyThatTheStandardSurveyCanBeAddedToTheTraining {

  private static final String TRAINING_NAME = "AutoTest_PlannedStatus";
  private static final int ANSWER_OPTIONAL_LABEL_SIZE = 21;
  private static final int QUESTION_OPTIONAL_LABEL_SIZE = 3;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactSurveyAndTestingTabScreen reactSurveyAndTestingTabScreen;
  private SoftAssert softAssert;
  private String surveyTypeNameBeforeChange;
  private String surveyTypeNameAfterChange;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingWithoutAnySurveyOption() {
    reactSurveyAndTestingTabScreen = new ReactSurveyAndTestingTabScreen();
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME);
    reactSurveyAndTestingTabScreen.clickSurveyAndTestingTabFromTrainingScreen();
    if (!reactSurveyAndTestingTabScreen.isNoSurveyPlaceholderDisplayedShortTimeOut()) {
      reactSurveyAndTestingTabScreen
          .clickDropDownByLabelName(
              getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_LABEL))
          .clickSurveyTypeDropDownByName(
              getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_INPUT));
      reactDetailTrainingScreen
          .waitSaveChangesButtonEnabled()
          .clickSaveChangesButton();
      reactSurveyAndTestingTabScreen
          .waitConfirmationToasterForVisibility()
          .clickOnConfirmationToasterCross();
    }
    surveyTypeNameBeforeChange = reactSurveyAndTestingTabScreen
        .getInputFieldValueByLabelName(
            getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_LABEL));
  }

  @Test(priority = 1)
  public void verifyThatConfirmationOfSavingChangesIsDisplayed() {
    reactSurveyAndTestingTabScreen
        .clickDropDownByLabelName(
            getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_LABEL))
        .clickSurveyTypeDropDownByName(
            getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_STANDARD_SURVEY_TYPE_INPUT));
    reactDetailTrainingScreen
        .waitSaveChangesButtonEnabled()
        .clickSaveChangesButton();
    assertTrue(reactDetailTrainingScreen.isConfirmationOfSavingChangesIsDisplayed(),
        "Confirmation didn't appear, changes not saved!");
  }

  @Test(priority = 2)
  public void verifyThatSurveyTypeChanged() {
    softAssert = new SoftAssert();
    surveyTypeNameAfterChange = reactSurveyAndTestingTabScreen
        .getInputFieldValueByLabelName(
            getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_LABEL));
    softAssert.assertNotEquals(surveyTypeNameAfterChange, surveyTypeNameBeforeChange,
        "Changes for survey type didn't save!");
    softAssert.assertEquals(surveyTypeNameAfterChange,
        getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_STANDARD_SURVEY_TYPE_INPUT),
        String.format("Survey type '%s' is not displayed",
            getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_STANDARD_SURVEY_TYPE_INPUT)));
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifyThatStandardSurveyTemplateIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactSurveyAndTestingTabScreen.isSurveyTemplateDisplayed(),
        "Standard survey template didn't appear!");
    softAssert.assertEquals(reactSurveyAndTestingTabScreen.getQuestionOptionLabelsSize(),
        QUESTION_OPTIONAL_LABEL_SIZE,
        String.format("Question labels of standard survey template is not equal to '%s'",
            QUESTION_OPTIONAL_LABEL_SIZE));
    softAssert.assertEquals(reactSurveyAndTestingTabScreen.getAnswerOptionLabelsSize(),
        ANSWER_OPTIONAL_LABEL_SIZE,
        String.format("Answer labels of standard survey template is not equal to '%s'",
            ANSWER_OPTIONAL_LABEL_SIZE));
    softAssert.assertAll();
  }
}
