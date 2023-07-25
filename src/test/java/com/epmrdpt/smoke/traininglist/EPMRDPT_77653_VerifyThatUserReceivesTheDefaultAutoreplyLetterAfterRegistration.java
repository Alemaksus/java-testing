package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.bo.user.UserFactory.userForRegistrationWizard;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFICATION_SCREEN_DATE_OF_TRAINING_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFICATION_SCREEN_GRATEFULNESS_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFICATION_SCREEN_INFORM_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFICATION_SCREEN_NOTIFICATION_MASSAGE;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationModuleScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.RegistrationForTrainingService;
import com.epmrdpt.services.SettingsService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77653_VerifyThatUserReceivesTheDefaultAutoreplyLetterAfterRegistration",
    groups = {"full", "smoke"})
public class EPMRDPT_77653_VerifyThatUserReceivesTheDefaultAutoreplyLetterAfterRegistration {

  private static final String TRAINING_NAME = "TEST_RECEIVE_LATTER";
  private static final String COUNTRY_NAME = "AutoTestCountry";
  private static final String CITY_NAME = "AutoTestCity";
  private static final String MESSAGE_NAME = getValueOf(NOTIFICATION_SCREEN_NOTIFICATION_MASSAGE);
  private static final String GRATEFULNESS_MESSAGE = getValueOf(
      NOTIFICATION_SCREEN_GRATEFULNESS_MESSAGE);
  private static final String INFORM_MESSAGE = getValueOf(NOTIFICATION_SCREEN_INFORM_MESSAGE);
  private static final String DATE_OF_TRAINING_MESSAGE = getValueOf(
      NOTIFICATION_SCREEN_DATE_OF_TRAINING_MESSAGE);
  private boolean isNeedToCancel = false;
  private NotificationPopUpScreen notificationPopUpScreen;
  private NotificationModuleScreen notificationModuleScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            userForRegistrationWizard() )
        .clickSettingsButton();
    new SettingsService().changeNotificationLanguage(StringUtils.getCurrentLocaleLanguage())
        .waitScreenLoading();
    new HeaderScreen()
        .clickTrainingListNavigationLink()
        .clickViewMoreTrainings()
        .chooseTrainingByName(TRAINING_NAME)
        .chooseCountryOfResidence(COUNTRY_NAME)
        .chooseCityOfResidence(CITY_NAME)
        .clickNextTabButton()
        .waitEducationTabForVisibility()
        .clickNextTabButton()
        .waitWorkExperienceTabForVisibility()
        .clickRegisterButton()
        .waitTrainingStatusTextPresent()
        .clickPopUpOkButton();
    notificationPopUpScreen = new NotificationPopUpScreen();
    notificationModuleScreen = new NotificationModuleScreen();
  }

  @Test(priority = 1)
  public void isCancelButtonDisplayed() {
    assertTrue(new TrainingDescriptionScreen().isCancelButtonDisplayed(),
        "The cancel button is not displayed");
  }

  @Test(priority = 2)
  public void isAutoreplyLetterReceived() {
    new HeaderScreen().clickNotificationButton();
    assertTrue(notificationModuleScreen.getAllNotificationText().contains(MESSAGE_NAME),
        "The autoreply letter was not received");
  }

  @Test(priority = 3)
  public void isAllElementsOfAutoreplyLetterDisplayed() {
    notificationModuleScreen.clickNotificationByName(MESSAGE_NAME);
    isNeedToCancel = true;
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        notificationPopUpScreen.getNotificationBodyInformationText(INFORM_MESSAGE), INFORM_MESSAGE);
    softAssert.assertTrue(
        notificationPopUpScreen.isNotificationBodyInformationTextDisplayed(GRATEFULNESS_MESSAGE),
        "The gratefulness message is not displayed");
    softAssert.assertTrue(notificationPopUpScreen.isNotificationBodyInformationTextDisplayed(
        DATE_OF_TRAINING_MESSAGE), "The date ias not displayed");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void cancelRegistration() {
    if (isNeedToCancel) {
      notificationPopUpScreen.closePopUpScreen();
      new RegistrationForTrainingService().cancelRegistrationForTraining();
    }
  }
}
