package com.epmrdpt.smoke.learning;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.HomeTabOnLearningPageScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.LoginService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_26747_VerifyTheContentOfCalendarHeadingSectionOfHomeTabOfLearningPage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_26747_VerifyTheContentOfCalendarHeadingSectionOfHomeTabOfLearningPage {

  private HeaderScreen headerScreen;
  private HomeTabOnLearningPageScreen homeTabOnLearningPageScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    homeTabOnLearningPageScreen = new HomeTabOnLearningPageScreen();
  }

  @Test(priority = 1)
  public void checkUserLogIn() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asStudentForLearningPage());
    assertTrue(headerScreen.isProfilePhotoImageDisplayed(), "User doesn't Log In!");
  }

  @Test(priority = 2)
  public void checkLearningPageLoading() {
    assertTrue(
        headerScreen.clickLearningButton().isScreenLoaded(), "Learning page doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkCalendarNavigationSectionWithArrowsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isCalendarNavigationTodayLabelDisplayed(),
        "Calendar navigation isn't displayed!");
    softAssert.assertEquals(
        homeTabOnLearningPageScreen.getCalendarNavigationTodayText(),
        LocaleProperties.getValueOf(LocalePropertyConst.LEARNING_HOME_SCREEN_CALENDAR_TODAY_LABEL),
        "Text of calendar navigation is incorrect!");
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isCalendarNavigationRightArrowDisplayed(),
        "Calendar navigation right arrow isn't displayed!");
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isCalendarNavigationLeftArrowDisplayed(),
        "Calendar navigation left arrow isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCurrentDateDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isCurrentDateDisplayed(), "Date isn't displayed!");
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("d MMMM yyyy")
            .withLocale(
                new Locale(LanguageSwitchingService.getLocaleLanguage().getLanguageCode()));
    LocalDate dateOnPage =
        LocalDate.parse(homeTabOnLearningPageScreen.getCalendarCurrentDate(), formatter);
    softAssert.assertEquals(
        dateOnPage, LocalDate.now(), "Current date does not match display date!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCalendarModeSectionDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        homeTabOnLearningPageScreen.isCalendarModeSectionDisplayed(),
        "Calendar mode section isn't displayed!");
    softAssert.assertEquals(
        homeTabOnLearningPageScreen.getCalendarModeSectionText().replaceAll("\n", " "),
        LocaleProperties.getValueOf(LocalePropertyConst.LEARNING_HOME_SCREEN_CALENDAR_MODE),
        "Calendar mode is displayed incorrect!");
    softAssert.assertAll();
  }
}
