package com.epmrdpt.regression.training_management;

import static java.lang.String.format;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.screens.ReactSurveyPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14288_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyOnTheParticipantsPage",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_14288_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyOnTheParticipantsPage {

  private static final String TRAINING_NAME = "AutoTest_REGISTRATION_OPEN_Survey";
  private static final String USER_NAME = "QQ AA";
  private static final String ANSWER_TEXT = "1";

  private SoftAssert softAssert;
  private ReactSurveyPopUpScreen reactSurveyPopUpScreen;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14288_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyOnTheParticipantsPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
    reactSurveyPopUpScreen = new ReactSurveyPopUpScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(user)
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkParticipantHasSurveyIcon() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSurveyColumnDisplayed(),
        "Survey column isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isParticipantHasSurveyIcon(USER_NAME),
        format("Participant '%s' hasn't survey icon!", USER_NAME));
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkPopUpContainsSurveyQuestionAndStudentAnswer() {
    softAssert = new SoftAssert();
    reactParticipantsTrainingScreen.clickSurveyIconOfParticipantByName(USER_NAME)
        .waitSurveyPopUpDisplayed();
    softAssert.assertTrue(reactSurveyPopUpScreen.isSurveyPopUpDisplayed(),
        "Survey pop up isn't displayed!");
    softAssert.assertEquals(reactSurveyPopUpScreen.getQuestionFromSurveyPopUpText(),
        LocaleProperties.getValueOf(LocalePropertyConst.SURVEY_SCREEN_QUESTION),
        "Question isn't correct!");
    softAssert.assertEquals(reactSurveyPopUpScreen.getAnswersFromSurveyPopUpText(), ANSWER_TEXT,
        "Answer isn't correct!");
    softAssert.assertAll();
  }
}
