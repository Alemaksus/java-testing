package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ExaminatorService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_100740_VerifyThatAutoRejectedStatusChangesToNewAfterReAssignEnglishTest",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_100740_VerifyThatAutoRejectedStatusChangesToNewAfterReAssignEnglishTest {

  private static final String ENGLISH_NOTIFICATION_NAME = "You have been assigned English test";
  private static final String TRAINING_NAME = "Auto-assessment Self-study Training with AC";
  private static final String PARTICIPANT_NAME = "QQ AA";
  private static final String APPLICANT_STATUS_BEFORE_ENGLISH_TEST = "Auto-rejected";
  private static final String APPLICANT_STATUS_AFTER_ENGLISH_TEST = "New";
  private static final String ENGLISH_TEST_STATUS = "Assigned";

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass
  public void loginAndGoToParticipantsPage() {
    reactParticipantsTrainingScreen = goToParticipantsPageAsTrainingManager();
  }

  @Test(priority = 1)
  public void checkThatStudentHasAutoRejectedStatus() {
    assertEquals(
        reactParticipantsTrainingScreen.getApplicantStatusByParticipantName(PARTICIPANT_NAME),
        APPLICANT_STATUS_BEFORE_ENGLISH_TEST, "Applicant's status is incorrect!");
  }

  @Test(priority = 2)
  public void checkThatConfirmActionPopUpAppears() {
    reactParticipantsTrainingScreen
        .clickStudentCheckBoxByStudentName(PARTICIPANT_NAME)
        .clickAssignEnglishTestButton();
    assertTrue(reactParticipantsTrainingScreen.isConfirmActionPopUpDisplayed(),
        "Confirm action pop-up is not opened!");
  }

  @Test(priority = 3)
  public void checkThatParticipantHasAssignedStatusOnEnglishColumn() {
    reactParticipantsTrainingScreen.clickYesInEnglishTestPopUpWindow();
    reactParticipantsTrainingScreen.clickRefreshButton();
    assertEquals(reactParticipantsTrainingScreen.getStudentEnglishTestResultText(PARTICIPANT_NAME),
        ENGLISH_TEST_STATUS, "Rating status is incorrect!");
  }

  @Test(priority = 4)
  public void checkThatParticipantHasNewStatus() {
    reactParticipantsTrainingScreen.clickRefreshButton();
    assertEquals(
        reactParticipantsTrainingScreen.getApplicantStatusByParticipantName(PARTICIPANT_NAME),
        APPLICANT_STATUS_AFTER_ENGLISH_TEST, "Applicant's status is incorrect!");
  }

  @AfterMethod
  public void cancelApplicantStatusAndCheckThatItHasBeenCanceled(Method method) {
    if ("checkThatParticipantHasNewStatus".equals(method.getName())) {
      new ReactLoginService().signOut();
      new LoginService().loginAndSetLanguage(withSimplePermission());
      new ExaminatorService().failEnglishTestInNotification(ENGLISH_NOTIFICATION_NAME);
      new LoginService().logout();
      goToParticipantsPageAsTrainingManager()
          .waitApplicantStatusUpdate(PARTICIPANT_NAME, APPLICANT_STATUS_BEFORE_ENGLISH_TEST);
    }
  }

  private ReactParticipantsTrainingScreen goToParticipantsPageAsTrainingManager() {
    return reactParticipantsTrainingScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitAllTrainingNameDisplayed()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickTrainingNameByName(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }
}
