package com.epmrdpt.regression.training_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.screens.ReactSurveyPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13278_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyOnTheQuestionaryPage",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_13278_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyOnTheQuestionaryPage {

  private static final String TRAINING_NAME = "AutoTest_REGISTRATION_OPEN_Survey";
  private static final String USER_NAME = "QQ AA";
  private static final String ANSWER_TEXT = "1";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13278_VerifyThatTrainingManagerCanViewStudentAnswersToRegistrationSurveyOnTheQuestionaryPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkParticipantPassedSurvey() {
    assertTrue(reactParticipantsTrainingScreen
            .isParticipantHasSurveyIcon(USER_NAME),
        "Participant have not passed survey!");
  }

  @Test(priority = 2)
  public void checkSurveyPopUpAppeared() {
    assertTrue(reactParticipantsTrainingScreen
            .clickSurveyIconOfParticipantByName(USER_NAME)
            .isSurveyPopUpDisplayed(),
        "Survey PopUp did not appeared!");

  }

  @Test(priority = 3)
  public void checkContainsSurveyPopUp() {
    assertEquals(new ReactSurveyPopUpScreen()
            .getAnswersFromSurveyPopUpText(),
        ANSWER_TEXT,
        "Answers not equals!");
  }
}
