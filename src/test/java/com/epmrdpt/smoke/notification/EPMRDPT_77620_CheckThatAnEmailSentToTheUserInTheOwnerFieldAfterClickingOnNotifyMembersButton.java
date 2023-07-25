package com.epmrdpt.smoke.notification;

import static com.epmrdpt.bo.user.UserFactory.asRecruiter;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_NOTIFICATION_POP_UP_NOTIFICATION_SENT_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_CHANGE_NOTIFICATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DETAILS_CHANGED_NOTIFICATION_TITLE;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.NotificationPopUpScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.NotificationService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.services.SettingsService;
import com.epmrdpt.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77620_CheckThatAnEmailSentToTheUserInTheOwnerFieldAfterClickingOnNotifyMembersButton",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_77620_CheckThatAnEmailSentToTheUserInTheOwnerFieldAfterClickingOnNotifyMembersButton {

  private User user;
  private String trainingName;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private NotificationPopUpScreen notificationPopUpScreen;
  private LoginService loginService;
  private NotificationService notificationService;
  private ReactTrainingManagementService reactTrainingManagementService;
  private String commentText = "Comment";
  private String notificationName = getValueOf(TRAINING_DETAILS_CHANGED_NOTIFICATION_TITLE);

  @Factory(dataProvider = "Provider of users with different trainings",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_77620_CheckThatAnEmailSentToTheUserInTheOwnerFieldAfterClickingOnNotifyMembersButton
      (User user, String trainingName) {
    this.user = user;
    this.trainingName = trainingName;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupDetailTrainingScreen() {
    loginService = new LoginService();
    loginService.loginWithoutTrainingCardAppearingWaitAndSetLanguage(asRecruiter())
        .clickSettingsButton();
    changeNotificationLanguage();
    loginService.logout()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(user)
        .clickSettingsButton();
    changeNotificationLanguage();
    new HeaderScreen().clickReactTrainingManagementLink();
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(trainingName);
    reactDetailTrainingScreen = reactTrainingManagementService
        .changeTrainingStartDateAndNotifyMembers(trainingName, commentText);
  }

  @Test(priority = 1)
  public void checkConfirmationPopUp() {
    Assert.assertTrue(reactDetailTrainingScreen.checkNotificationPopUpDisplayed(),
        "Confirmation pop-up isn't displayed!");
  }

  @Test(priority = 2)
  public void checkNotificationPopUp() {
    reactDetailTrainingScreen.clickHomeButton();
    notificationService = new NotificationService();
    notificationPopUpScreen = notificationService.openNotificationByName(notificationName);
    Assert.assertTrue(notificationPopUpScreen.getNotificationBodyInformationText().contains(
        getValueOf(TRAINING_DESCRIPTION_CHANGE_NOTIFICATION)),
        "User didn't receive notification about training description change!");
    notificationPopUpScreen.closePopUpScreen();
  }

  @Test(priority = 3)
  public void checkRecruiterNotification() {
    loginService.logout()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asRecruiter())
        .waitTrainingListNavigationLinkVisibility();
    notificationPopUpScreen = notificationService.openNotificationByName(notificationName);
    Assert.assertTrue(notificationPopUpScreen.getNotificationBodyInformationText().contains(
        getValueOf(TRAINING_DESCRIPTION_CHANGE_NOTIFICATION)),
        "User didn't receive notification about training description change!");
  }

  private void changeNotificationLanguage() {
    new SettingsService().changeNotificationLanguage(StringUtils.getCurrentLocaleLanguage())
        .waitScreenLoading();
  }
}
