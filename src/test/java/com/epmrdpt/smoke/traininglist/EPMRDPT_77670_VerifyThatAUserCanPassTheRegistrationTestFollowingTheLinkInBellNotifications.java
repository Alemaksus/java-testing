package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_ASSIGNING_REGISTRATION_TEST_NOTIFICATION_TITLE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ExaminatorScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.SettingsService;
import com.epmrdpt.services.TrainingCardsSectionService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77670_VerifyThatAUserCanPassTheRegistrationTestFollowingTheLinkInBellNotifications",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_77670_VerifyThatAUserCanPassTheRegistrationTestFollowingTheLinkInBellNotifications {

  private static final String TRAINING_NAME = "AUTOTEST WITH AC";
  private static final String COUNTRY_OF_RESIDENCE = "AutoTestCountry";
  private static final String CITY_OF_RESIDENCE = "AutoTestCity";
  private NotificationPopUpScreen notificationPopUpScreen;
  private ExaminatorScreen examinatorScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void openNotificationAfterRegistrationAsStudent() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(userForRegistrationWizard())
        .waitProfilePhotoImageVisibility()
        .clickSettingsButton();
    new SettingsService().changeNotificationLanguage(StringUtils.getCurrentLocaleLanguage())
        .waitScreenLoading();
    new HeaderScreen().clickTrainingListNavigationLink();
    new TrainingCardsSectionService()
        .openTrainingDescription(TRAINING_NAME);
    new RegistrationForTrainingService()
        .generateRegistrationSuccessPopUp(CITY_OF_RESIDENCE, COUNTRY_OF_RESIDENCE)
        .clickPopUpOkButton()
        .waitCancelButtonVisibility();
    notificationPopUpScreen = new HeaderScreen().clickNotificationButton()
        .waitNotificationDropDownVisibility()
        .clickNotificationByName(
            getValueOf(TRAINING_DESCRIPTION_ASSIGNING_REGISTRATION_TEST_NOTIFICATION_TITLE));
  }

  @Test
  public void checkThatUserRedirectedToExaminator() {
    examinatorScreen = notificationPopUpScreen
        .clickLinkToRegistrationTest();
    examinatorScreen.switchToLastWindow();
    assertTrue(examinatorScreen.isRegistrationTestPageLoaded(),
        "Registration test link isn't redirected to proper registration test page!");
  }

  @AfterMethod
  public void cancelRegistration() {
    examinatorScreen.closeLastWindowAndSwitchToPreviousIfMoreThanOne();
    notificationPopUpScreen.closePopUpScreen();
    new RegistrationForTrainingService()
        .cancelRegistrationForTraining();
  }
}
