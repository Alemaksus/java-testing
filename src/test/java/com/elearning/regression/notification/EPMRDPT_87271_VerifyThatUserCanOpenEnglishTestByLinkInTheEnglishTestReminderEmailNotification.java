package com.epmrdpt.regression.notification;

import static com.epmrdpt.bo.user.UserFactory.asRecruiter;
import static com.epmrdpt.bo.user.UserFactory.asStudentForLearningPage;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_87271_VerifyThatUserCanOpenEnglishTestByLinkInTheEnglishTestReminderEmailNotification",
    groups = {"full", "regression"})
public class EPMRDPT_87271_VerifyThatUserCanOpenEnglishTestByLinkInTheEnglishTestReminderEmailNotification {

  private static final String TRAINING_NAME = "AutoTest_EnglishTestTraining";
  private static final String NOTIFICATION_TITLE = "English test";
  private static final String COUNTRY_OF_RESIDENCE = "AutoTestCountry";
  private static final String CITY_OF_RESIDENCE = "AutoTestCity";
  private static final String STUDENT_EMAIL = "learningpagestudent@proton.me";

  private NotificationPopUpScreen notificationPopUpScreen;

  @BeforeClass
  public void screenInitialization() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentForLearningPage());
    new HeaderScreen()
        .clickNotificationButton()
        .clickMarkAllAsReadButton();
    new TrainingBlockScreen()
        .cleanLocationFilterIfNeed()
        .waitTrainingCardsVisibility()
        .checkAndClickViewMoreTrainingsLink()
        .waitTrainingCardListDisplayed()
        .clickTrainingCardByName(TRAINING_NAME)
        .waitTrainingTitleLabelVisibility()
        .clickRegisterButton();
    new RegistrationWizardScreen()
        .chooseCountryOfResidence(COUNTRY_OF_RESIDENCE)
        .chooseCityOfResidence(CITY_OF_RESIDENCE)
        .clickNextTabButton()
        .waitEducationFormInputVisibility()
        .clickNextTabButton()
        .waitSkillsLabelVisibility()
        .clickRegisterButton()
        .clickPopUpOkButton()
        .waitCancelButtonVisibility();
    notificationPopUpScreen = new HeaderScreen()
        .clickNotificationButton()
        .clickNotificationByName(NOTIFICATION_TITLE)
        .waitForNotificationPopupVisibility();
  }

  @Test
  public void checkUserRedirectedToExaminatorSystemForPassingEnglishTestViaLink() {
    notificationPopUpScreen.clickLinkToEnglishTest()
        .switchToLastWindow();
    Assert.assertTrue(notificationPopUpScreen.isTestingSystemPageLogoDisplayed()
            && notificationPopUpScreen.isEnglishTestTitleDisplayed(),
        "User isn't redirected to Examinator system for passing English Test!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void cancelRegistrationAndReassignEnglishTestForStudent(Method method) {
    if ("checkUserRedirectedToExaminatorSystemForPassingEnglishTestViaLink"
        .equals(method.getName())) {
      notificationPopUpScreen.closeLastWindowAndSwitchToPreviousIfMoreThanOne();
      notificationPopUpScreen.closePopUpScreen();
      new RegistrationForTrainingService().cancelRegistrationForTraining();
      new LoginService().logout()
          .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asRecruiter())
          .clickSearchLink()
          .enterSearchInputText(STUDENT_EMAIL)
          .clickFindButton()
          .clickSearchResultByEmail(STUDENT_EMAIL)
          .switchToLastWindow();
      new ProfileScreen().clickAssignEnglishTestButton()
          .waitPopUpAppear()
          .clickPopupOkButton()
          .waitPopUpAppear()
          .clickPopupOkButton();
      new LoginService().logout();
    }
  }
}
