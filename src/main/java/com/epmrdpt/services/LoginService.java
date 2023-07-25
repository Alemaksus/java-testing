package com.epmrdpt.services;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.framework.loging.Log;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.LoginScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.TrainingBlockScreen;

public class LoginService {

  private HeaderScreen headerScreen = new HeaderScreen();
  private LoginScreen loginScreen = new LoginScreen();

  public HeaderScreen login(User user) {
    loginByUserCredentials(user);
    if (!new ProfileScreen().isConfirmInformationPopUpDisplayed()) {
      new TrainingBlockScreen().clickUniversityProgramLink().waitTrainingCardsVisibility();
    }
    return new HeaderScreen();
  }

  public HeaderScreen loginWithoutWaiting(User user) {
    loginByUserCredentials(user);
    return new HeaderScreen();
  }

  public HeaderScreen loginAndSetLanguage(User user) {
    loginByUserCredentials(user);
    if (!new ProfileScreen().isConfirmInformationPopUpDisplayed()) {
      new LanguageSwitchingService().setLanguageAccordingToLocaleSettings();
      Log.logInfoMessage("Language was set up according locale settings");
      new TrainingBlockScreen().clickUniversityProgramLink().waitTrainingCardsVisibility();
    }
    return new HeaderScreen();
  }

  public HeaderScreen loginWithoutTrainingCardAppearingWaitAndSetLanguage(User user) {
    loginByUserCredentials(user);
    if (!new ProfileScreen().isConfirmInformationPopUpDisplayed()) {
      new LanguageSwitchingService().setLanguageAccordingToLocaleSettings();
      Log.logInfoMessage("Language was set up according locale settings");
    }
    return new HeaderScreen();
  }

  public HeaderScreen loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(User user) {
    loginByUserCredentials(user);
    if (!new ProfileScreen().isConfirmInformationPopUpDisplayedNoWait()) {
      new LanguageSwitchingService().setLanguageAccordingToLocaleSettingsNoWait();
      Log.logInfoMessage("Language was set up according locale settings");
      new TrainingBlockScreen().clickUniversityProgramLink().waitTrainingCardsVisibility();
    }
    return new HeaderScreen();
  }

  private LoginService loginByUserCredentials(User user) {
    Log.logInfoMessage("Start user login as " + user.getUsername());
    headerScreen.waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickSignInButton()
        .clickToggleUserIdentities();
    if (new LoginScreen().isNextButtonDisplayed()) {
      loginByUserCredentialsNew(user);
    } else {
      loginByUserCredentialsWithoutNextButtonOldVer(user);
    }
    return this;
  }

  private LoginService loginByUserCredentialsNew(User user) {
    Log.logInfoMessage("Start new version of user login");
    loginScreen.typeLoginName(user.getUsername()).clickNext().typePassword(user.getPassword());
    passCaptchaIfItIsDisplayed().clickSignInButton().waitProfileMenuDisplayed();
    Log.logInfoMessage("User login completed");
    return this;
  }

  private LoginService loginByUserCredentialsWithoutNextButtonOldVer(User user) {
    Log.logInfoMessage("Start old version of user login");
    loginScreen.typeLoginName(user.getUsername()).typePassword(user.getPassword());
    passCaptchaIfItIsDisplayed().clickSignInButton().waitProfileMenuDisplayed();
    Log.logInfoMessage("User login completed");
    return this;
  }

  public LoginService logout() {
    headerScreen.clickProfileMenu().signOut();
    return this;
  }

  public LoginScreen passCaptchaIfItIsDisplayed() {
    if (loginScreen.isCaptchaFrameDisplayed()) {
      loginScreen.switchToCaptchaFrame().clickOnCaptchaField()
          .typeCaptcha().waitCaptchaCheckMarkVisibility().switchToDefault();
    }
    return loginScreen;
  }
}
