package com.epmrdpt.smoke.learning;

import static com.epmrdpt.bo.user.UserFactory.asStudentForLearningPage;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_HOME_SCREEN_DAYS_OF_WEEK;
import static org.testng.Assert.assertTrue;

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

@Test(description = "EPMRDPT_26748_VerifyContentOfCalendarDatesSectionOfHomeTabOfLearningPage",
    groups = {"full", "student", "smoke"})
public class EPMRDPT_26748_VerifyContentOfCalendarDatesSectionOfHomeTabOfLearningPage {

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
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentForLearningPage());
    assertTrue(headerScreen.isProfilePhotoImageDisplayed(), "User doesn't Log In!");
  }

  @Test(priority = 2)
  public void checkLearningPageLoading() {
    assertTrue(headerScreen
        .clickLearningButton()
        .isScreenLoaded(), "Learning page doesn't loaded!");
  }

  @Test(priority = 3)
  public void checkCalendarMonthBoxDisplayedByDefault() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isCalendarMonthBoxDisplayed(),
        "Month box isn't displayed!");
    String currentLanguage = LanguageSwitchingService.getLocaleLanguage().getLanguageCode();
    DateTimeFormatter monthFormatter =
        DateTimeFormatter.ofPattern("MMMM").withLocale(new Locale(currentLanguage));
    LocalDate dateOnPage = LocalDate.parse(homeTabOnLearningPageScreen.getCalendarCurrentDate(),
        DateTimeFormatter.ofPattern("d MMMM yyyy").withLocale(new Locale(currentLanguage)));
    softAssert.assertEquals(
        monthFormatter.format(dateOnPage),
        monthFormatter.format(LocalDate.now()),
        "Current month doesn't displayed by default!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCalendarCellsWithDaysNamesDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isDaysOfWeekDisplayed(),
        "Days of week aren't displayed!");
    softAssert.assertEquals(homeTabOnLearningPageScreen
            .getDaysOfWeekText()
            .replaceAll("(\r\n)|(\n)", " "),
        getValueOf(LEARNING_HOME_SCREEN_DAYS_OF_WEEK), "Days of week is displayed incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkLessonInfoInCalendarDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isEventContainerInCalendarDisplayed(),
        "Not all event containers are displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isEventTitleInCalendarDisplayed(),
        "Not all event titles are displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.isEventTimeInCalendarDisplayed(),
        "Not all event times are displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCalendarCellsWithDateDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(homeTabOnLearningPageScreen.isCalendarCellDisplayed(),
        "Not all calendar cell are displayed!");
    softAssert.assertTrue(homeTabOnLearningPageScreen.getCalendarCellText().stream()
            .allMatch(e -> e.matches("^[1-3]{0,1}\\d$")),
        "Numbers in calendar cells are displayed incorrectly!");
    softAssert.assertAll();
  }
}
