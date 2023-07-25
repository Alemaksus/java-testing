package com.epmrdpt.regression.training_management;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.screens.SurveyPopUpScreen;
import com.epmrdpt.screens.TrainingInfoTabOnProfilePageScreen;
import com.epmrdpt.screens.UserQuestionaryScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13069_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyInTheUseProfile",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_13069_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyInTheUseProfile {

  private static final String TRAINING_NAME = "AutoTest_REGISTRATION_OPEN_Survey";
  private static final String USER_NAME = "QQ AA";
  private static final String ANSWER_TEXT = "1";

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private UserQuestionaryScreen userQuestionaryScreen;
  private TrainingInfoTabOnProfilePageScreen trainingInfoTabOnProfilePageScreen;
  private SurveyPopUpScreen surveyPopUpScreen;
  private User user;
  private ProfileScreen profileScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13069_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyInTheUseProfile(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
    userQuestionaryScreen = new UserQuestionaryScreen();
    trainingInfoTabOnProfilePageScreen = new TrainingInfoTabOnProfilePageScreen();
    surveyPopUpScreen = new SurveyPopUpScreen();
    profileScreen = new ProfileScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(user)
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkQuestionaryPageOfUserIsOpened() {
    reactParticipantsTrainingScreen.clickParticipantByName(USER_NAME);
    assertEquals(userQuestionaryScreen.getMainTitleText(), USER_NAME,
        format("Questioner screen '%s' isn't loaded!", USER_NAME));
  }

  @Test(priority = 2)
  public void checkProfilePageForUserIsOpened() {
    reactParticipantsTrainingScreen.clickParticipantPopUpProfileButton();
    profileScreen.switchToLastWindow();
    assertEquals(profileScreen.getUserFullNameText(), USER_NAME,
        format("Profile screen '%s' isn't loaded!", USER_NAME));
  }

  @Test(priority = 3)
  public void checkSurveyLinkDisplayedInTrainingInfo() {
    trainingInfoTabOnProfilePageScreen
        .waitApplicationHeaderDisplayed()
        .clickApplicationTab()
        .typeTrainingIntoSearchInput(TRAINING_NAME)
        .clickFindButton();
    assertTrue(trainingInfoTabOnProfilePageScreen.isTrainingHasSurvey(TRAINING_NAME),
        format("Training '%s' hasn't survey icon!", TRAINING_NAME));
  }

  @Test(priority = 4)
  public void checkSurveyPopUpContent() {
    SoftAssert softAssert = new SoftAssert();
    trainingInfoTabOnProfilePageScreen.clickSurveyIconByTrainingName(TRAINING_NAME);
    softAssert.assertTrue(surveyPopUpScreen.isSurveyPopUpDisplayed(),
        "Survey pop up isn't displayed!");
    softAssert.assertEquals(surveyPopUpScreen.getAnswersFromSurveyPopUpText(),
        ANSWER_TEXT, "Answers from survey pop up aren't correct!");
    softAssert.assertEquals(surveyPopUpScreen.getQuestionsFromSurveyPopUpText(),
        LocaleProperties.getValueOf(LocalePropertyConst.SURVEY_SCREEN_QUESTION),
        "Questions from survey pop up aren't correct!");
    softAssert.assertAll();
  }
}
