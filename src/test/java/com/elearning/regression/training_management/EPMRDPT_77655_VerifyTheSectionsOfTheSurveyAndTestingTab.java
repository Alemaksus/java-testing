package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ASSIGNMENT_CONTAINER_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ELEARN_INTEGRATION_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_CHECK_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_REGISTRATION_SURVEY_SECTION;
import static java.lang.String.format;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactAdditionalInfoTabScreen;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77655_VerifyTheSectionsOfTheSurveyAndTestingTab",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_77655_VerifyTheSectionsOfTheSurveyAndTestingTab {

  private final String englishLevelCheckSection = getValueOf(
      REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_CHECK_SECTION);
  private final String assignmentContainerSection = getValueOf(
      REACT_SURVEY_AND_TESTING_TAB_SCREEN_ASSIGNMENT_CONTAINER_SECTION);
  private final String eLearnIntegrationSection = getValueOf(
      REACT_SURVEY_AND_TESTING_TAB_SCREEN_ELEARN_INTEGRATION_SECTION);
  private final String registrationSurveySection = getValueOf(
      REACT_SURVEY_AND_TESTING_TAB_SCREEN_REGISTRATION_SURVEY_SECTION);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .clickCreateNewButton();
  }

  @Test()
  public void checkAllSectionsOfSurveyAndTestingTab() {
    ReactSurveyAndTestingTabScreen reactSurveyAndTestingTabScreen = new ReactAdditionalInfoTabScreen()
        .clickSurveyAndTestingTabFromTrainingScreen();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactSurveyAndTestingTabScreen.isColumnDisplayed(englishLevelCheckSection),
        format("'%s' section is not displayed", englishLevelCheckSection));
    softAssert.assertTrue(
        reactSurveyAndTestingTabScreen.isColumnDisplayed(assignmentContainerSection),
        format("'%s' section is not displayed", assignmentContainerSection));
    softAssert.assertTrue(
        reactSurveyAndTestingTabScreen.isColumnDisplayed(eLearnIntegrationSection),
        format("'%s' section is not displayed", eLearnIntegrationSection));
    softAssert.assertTrue(
        reactSurveyAndTestingTabScreen.isColumnDisplayed(registrationSurveySection),
        format("'%s' section is not displayed", registrationSurveySection));
    softAssert.assertAll();
  }
}
