package com.epmrdpt.regression.training_management;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ParticipantsTrainingScreen;
import com.epmrdpt.screens.UserQuestionaryScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_24597_VerifyTheContentOfQuestionaryPage",
    groups = {"full", "regression", "manager", "deprecated"})
public class EPMRDPT_24597_VerifyTheContentOfQuestionaryPage {

  private final String TRAINING_NAME = "AutoTest_WithGroupAndPatricipantInProgress";
  private final String STUDENT_NAME = "QQ AA";
  private ParticipantsTrainingScreen participantsTrainingScreen;
  private UserQuestionaryScreen userQuestionaryScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    participantsTrainingScreen = new ParticipantsTrainingScreen();
    userQuestionaryScreen = new UserQuestionaryScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager()).waitProfileMenuDisplayed()
        .clickReactTrainingManagementLink()
        .waitPreloadingPictureHidden();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility()
        .waitScreenLoading()
        .waitConfigureColumnsTabHeaderVisibility();
  }

  @Test(priority = 1)
  public void checkThatParticipantPresentInTraining() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        participantsTrainingScreen
            .isApplicantHasInTrainingStatusByParticipantName(STUDENT_NAME),
        String.format(
            "Student %s isn't displayed in participant table or have INCORRECT status!",
            STUDENT_NAME));
    softAssert.assertTrue(
        participantsTrainingScreen.clickParticipantByName(STUDENT_NAME).isScreenLoaded(),
        "Questionary screen isn't loaded!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkQuestionaryPageContent() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(userQuestionaryScreen.isGeneralInformationSectionDisplayed(),
        "General Information Section doesn't displayed!!");
    softAssert.assertTrue(userQuestionaryScreen.isEducationSectionDisplayed(),
        "Education Section doesn't displayed!!");
    softAssert.assertTrue(userQuestionaryScreen.isProfessionalSkillsSectionDisplayed(),
        "Professional Skills Section doesn't displayed!");
    softAssert.assertTrue(userQuestionaryScreen.isCurrentStatusSectionDisplayed(),
        "Current Status Section doesn't displayed!");
    softAssert.assertTrue(userQuestionaryScreen.isStudentGroupSectionDisplayed(),
        "Student Group Section doesn't displayed!");
    softAssert.assertTrue(userQuestionaryScreen.isApplicationsSectionDisplayed(),
        "Applications Section doesn't displayed!");
    softAssert.assertAll();
  }
}
