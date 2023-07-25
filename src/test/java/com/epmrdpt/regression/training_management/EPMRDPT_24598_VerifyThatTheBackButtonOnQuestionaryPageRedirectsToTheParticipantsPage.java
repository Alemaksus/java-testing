package com.epmrdpt.regression.training_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ParticipantsTrainingScreen;
import com.epmrdpt.screens.UserQuestionaryScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_24598_VerifyThatTheBackButtonOnQuestionaryPageRedirectsToTheParticipantsPage",
    groups = {"full", "regression", "manager", "deprecated"})
public class EPMRDPT_24598_VerifyThatTheBackButtonOnQuestionaryPageRedirectsToTheParticipantsPage {

  private final String TRAINING_NAME = "AutoTest_WithGroupAndPatricipantInProgress";
  private final String STUDENT_NAME = "QQ AA";
  private ParticipantsTrainingScreen participantsTrainingScreen;
  private UserQuestionaryScreen userQuestionaryScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_24598_VerifyThatTheBackButtonOnQuestionaryPageRedirectsToTheParticipantsPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    participantsTrainingScreen = new ParticipantsTrainingScreen();
    userQuestionaryScreen = new UserQuestionaryScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink()
        .waitPreloadingPictureHidden();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading()
        .waitConfigureColumnsTabHeaderVisibility();
  }

  @Test(priority = 1)
  public void checkThatQuestionaryPageIsOpened() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        participantsTrainingScreen.isApplicantHasInTrainingStatusByParticipantName(
            STUDENT_NAME),
        String.format("Student %s isn't displayed in participant table or have INCORRECT status!",
            STUDENT_NAME));
    participantsTrainingScreen.clickParticipantByName(STUDENT_NAME);
    softAssert.assertTrue(userQuestionaryScreen.isScreenLoaded(),
        "Questionary screen isn't loaded!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatParticipantPageIsOpened() {
    assertTrue(userQuestionaryScreen.clickBackButtonInQuestionaryScreen().isScreenLoaded(),
        "Participant screen isn't loaded!");
  }
}
