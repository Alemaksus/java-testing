package com.epmrdpt.regression.notification;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndEmptyProfile;
import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TIMEZONE_MISMATCH_POP_UP_SCREEN_POP_UP_TEXT;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TimezoneMismatchPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.SettingsService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_70430_VerifyThatTimezonePopUpIsDisplayedWhenUserSwitchesBetweenPages",
    groups = {"full", "regression"})
public class EPMRDPT_70430_VerifyThatTimezonePopUpIsDisplayedWhenUserSwitchesBetweenPages {

  private final static String TIMEZONE_WITH_MISMATCH = "(UTC-12:00) International Date Line West";
  private final static String DEFAULT_TIMEZONE = "(UTC+03:00) Minsk";
  private final static String TIMEZONE_MISMATCH_POP_UP_TEXT = getValueOf(
      TIMEZONE_MISMATCH_POP_UP_SCREEN_POP_UP_TEXT);
  private HeaderScreen headerScreen;
  private TimezoneMismatchPopUpScreen timezoneMismatchPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void loginSetTimeZoneAndGoToTrainingListScreen() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            withSimplePermissionAndEmptyProfile());
    SettingsService settingsService = new SettingsService();
    settingsService
        .navigateToSettingsScreen();
    settingsService
        .changeTimezone(TIMEZONE_WITH_MISMATCH)
        .clickApplySettingsButton();
    headerScreen = new HeaderScreen();
    headerScreen.openPageInNewTab(getEnv());
    headerScreen.switchToLastWindow();
    headerScreen
        .clickTrainingListNavigationLink();
  }

  @Test(priority = 1)
  public void checkTimezonePopUpOnTrainingListScreen() {
    timezoneMismatchPopUpScreen = new TimezoneMismatchPopUpScreen();
    assertEquals(timezoneMismatchPopUpScreen.getTimezoneMismatchPopUpText(),
        TIMEZONE_MISMATCH_POP_UP_TEXT,
        "Timezone mismatch pop-up text on 'Training List' screen is incorrect!");
  }

  @Test(priority = 2)
  public void checkTimezonePopUpOnEventsScreen() {
    headerScreen.clickEventsNavigationLink();
    assertEquals(timezoneMismatchPopUpScreen.getTimezoneMismatchPopUpText(),
        TIMEZONE_MISMATCH_POP_UP_TEXT,
        "Timezone mismatch pop-up text on 'Events' screen is incorrect!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void setTimezoneToDefault(Method method) {
    if (timezoneMismatchPopUpScreen.isScreenLoaded() && method.getName().equals("checkTimezonePopUpOnEventsScreen")) {
      timezoneMismatchPopUpScreen
          .clickTimezoneSelectDropDownButton()
          .waitTimezoneDropDownMenuPresent()
          .clickDropDownTimezoneItem(DEFAULT_TIMEZONE)
          .waitSuccessfulChangesPopUpPresent();
    }
  }
}
