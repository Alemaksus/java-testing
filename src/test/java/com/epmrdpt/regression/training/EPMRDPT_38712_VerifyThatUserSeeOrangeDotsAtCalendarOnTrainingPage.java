package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_38712_VerifyThatUserSeeOrangeDotsAtCalendarOnTrainingPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38712_VerifyThatUserSeeOrangeDotsAtCalendarOnTrainingPage {

  private final int currentDayOfMonth = LocalDate.now().getDayOfMonth();
  private final int checkingDaysCount = 3;
  private final String colorDotsOrange = "rgb(252, 170, 0)";

  private final LocalDate testDate = LocalDate.now().plusYears(2);
  private final LocalDate currentWeekMonday = LocalDate.now()
      .minusDays(LocalDate.now().getDayOfWeek().getValue() - 1);
  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
  }

  @Test(priority = 1)
  public void checkThatOrangeIndicatingDotsIsDisplayedInDatesWithActivities() {
    SoftAssert softAssert = new SoftAssert();
    for (int i = 0; i < checkingDaysCount; i++) {
      LocalDate date = currentWeekMonday.plusDays(i);
      new ReactTrainingService().openScheduleByDate(date);
      if (!reactTrainingScreen.isClassCardDisplayed()) {
        fail(format(
            "%s there are wrong preconditions: at least one class must be present in the schedule!",
            date.toString()));
      }
      softAssert.assertTrue(reactTrainingScreen.isDateIndicatingDotDisplayed(date.getDayOfMonth()),
          format("Indicating dot is absent %s - the date with classes in the schedule",
              date.toString()));
      softAssert.assertEquals(
          reactTrainingScreen.getDateIndicatingDotColorByDay(date.getDayOfMonth()),
          colorDotsOrange,
          format("Date of %s with activities has incorrect dots color!", date.toString()));
      softAssert.assertAll();
    }
  }

  @Test(priority = 2)
  public void checkThatIndicatingDotsIsAbsentInDatesWithoutActivities() {
    new ReactTrainingService().openScheduleByDate(testDate);
    if (reactTrainingScreen.isClassCardDisplayed()) {
      fail(format("%s there are wrong preconditions - classes mustn't be present in the schedule!",
          testDate.toString()));
    }
    assertFalse(reactTrainingScreen.isDateIndicatingDotDisplayed(currentDayOfMonth),
        format("Indicating dot is displayed %s - the date without classes in the schedule",
            testDate.toString()));
  }
}
