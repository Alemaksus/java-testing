package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_RATING_VALUE_ASSIGNED;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingAssignACPopUpScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ExaminatorService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_100745_VerifyThatAutoRejectedStatusChangesToNewAfterReAssignAC",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_100745_VerifyThatAutoRejectedStatusChangesToNewAfterReAssignAC {

  private static final String REGISTRATION_NOTIFICATION_NAME = "Assigning of registration test";
  private static final String TRAINING_NAME = "Auto-assessment Self-study Training with AC";
  private static final String PARTICIPANT_NAME = "QQ AA";
  private static final String APPLICANT_STATUS_BEFORE_ASSIGN_AC = "Auto-rejected";
  private static final String APPLICANT_STATUS_AFTER_ASSIGN_AC = "New";

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private ReactParticipantsTrainingAssignACPopUpScreen reactParticipantsTrainingAssignACPopUpScreen;

  @BeforeClass
  public void loginAndGoToParticipantsPage() {
    reactParticipantsTrainingScreen = goToParticipantsPageAsTrainingManager();
  }

  @Test(priority = 1)
  public void checkThatStudentHasAutoRejectedStatus() {
    assertEquals(
        reactParticipantsTrainingScreen.getApplicantStatusByParticipantName(PARTICIPANT_NAME),
        APPLICANT_STATUS_BEFORE_ASSIGN_AC, "Applicant's status is incorrect!");
  }

  @Test(priority = 2)
  public void checkThatConfirmActionPopUpAppears() {
    reactParticipantsTrainingAssignACPopUpScreen = reactParticipantsTrainingScreen
        .clickStudentCheckBoxByStudentName(PARTICIPANT_NAME)
        .clickAssignACButton();
    assertTrue(reactParticipantsTrainingAssignACPopUpScreen.isConfirmActionPopUpOpened(),
        "Confirm action pop-up is not opened!");
  }

  @Test(priority = 3)
  public void checkThatACReassigned() {
    reactParticipantsTrainingAssignACPopUpScreen.clickYesButton();
    reactParticipantsTrainingScreen.clickRefreshButton();
    assertEquals(reactParticipantsTrainingScreen.getStudentRatingText(PARTICIPANT_NAME),
        getValueOf(PARTICIPANTS_RATING_VALUE_ASSIGNED), "Rating status is incorrect!");
  }

  @Test(priority = 4)
  public void checkThatParticipantHasNewStatus() {
    reactParticipantsTrainingScreen.clickRefreshButton();
    assertEquals(
        reactParticipantsTrainingScreen.getApplicantStatusByParticipantName(PARTICIPANT_NAME),
        APPLICANT_STATUS_AFTER_ASSIGN_AC, "Applicant's status is incorrect!");
  }

  @AfterMethod
  public void cancelApplicantStatusAndCheckThatItHasBeenCanceled(Method method) {
    if ("checkThatParticipantHasNewStatus".equals(method.getName())) {
      new ReactLoginService().signOut();
      new LoginService().loginAndSetLanguage(withSimplePermission());
      new ExaminatorService().failRegistrationTestInNotification(REGISTRATION_NOTIFICATION_NAME);
      new LoginService().logout();
      goToParticipantsPageAsTrainingManager()
          .waitApplicantStatusUpdate(PARTICIPANT_NAME, APPLICANT_STATUS_BEFORE_ASSIGN_AC);
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
