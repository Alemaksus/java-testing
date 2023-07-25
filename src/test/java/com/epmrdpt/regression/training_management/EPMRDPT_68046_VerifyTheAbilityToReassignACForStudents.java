package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_AC_ASSIGNED_NOTIFICATION_TEXT;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactParticipantsTrainingAssignACPopUpScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_68046_VerifyTheAbilityToReassignACForStudents",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_68046_VerifyTheAbilityToReassignACForStudents {

  private final static String TRAINING_NAME = "AutoTest_VCH AC";
  private final static String STUDENT_NAME = "Alexandra LastName297734";
  private final User user;
  private ReactParticipantsTrainingAssignACPopUpScreen assignACPopUpScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_68046_VerifyTheAbilityToReassignACForStudents(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndReassignAC() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    assignACPopUpScreen = new ReactParticipantsService()
        .findParticipantBySearchPhrase(STUDENT_NAME)
        .clickStudentCheckBoxByStudentName(STUDENT_NAME)
        .clickAssignACButton();
  }

  @Test(priority = 1)
  public void checkACAssignmentPopUpIsLoaded() {
    assertTrue(assignACPopUpScreen.isScreenLoaded(), "AC assignment pop up is not loaded!");
  }

  @Test(priority = 2)
  public void checkDeadlineDateIsFilled() {
    assignACPopUpScreen
        .clickCalendarInput()
        .clickRightArrowDate()
        .clickLastDayOfMonth()
        .waitDateCalendarCollapsed();
    assertFalse(assignACPopUpScreen.getDateValue().isBlank(),
        "AC assignment deadline is not filled in!");
  }

  @Test(priority = 3)
  public void checkACAssignmentPopUpWindowIsClosedAndNotificationIsPresent() {
    ReactParticipantsTrainingScreen participantsTrainingScreen = assignACPopUpScreen.clickAssignButton();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(assignACPopUpScreen.isAssignRegistrationTestPopUpClosed(),
        "AC assignment pop up window is not closed!");
    softAssert.assertEquals(participantsTrainingScreen.getNotificationPopUpText(),
        getValueOf(PARTICIPANTS_AC_ASSIGNED_NOTIFICATION_TEXT),
        "AC assignment notification pop up text is incorrect!");
    softAssert.assertAll();
  }
}
