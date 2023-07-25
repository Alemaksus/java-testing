package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.utils.RandomUtils.getRandomNumber;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_38720_VerifyThatUserCanSeeTheScheduleForChosenDate",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38720_VerifyThatUserCanSeeTheScheduleForChosenDate {

  private ReactTrainingScreen reactTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupDateInCalendar() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
    int selectedDayInTheNextMonth = getRandomNumber(reactTrainingScreen
        .clickRightArrowButtonInCalendar().getDaysOfMonthList().size());
    reactTrainingScreen.clickOnDayByIndex(selectedDayInTheNextMonth);
  }

  @Test
  public void checkSelectedDayInSchedule() {
    assertEquals(reactTrainingScreen.getSelectedDayInCalendarValue(),
        reactTrainingScreen.getDayOfMonthInScheduleValue(),
        "Day of month in 'My schedule' is incorrect!");
  }

  @Test
  public void checkSelectedMonthAndYearInSchedule() {
    assertEquals(reactTrainingScreen.getMonthAndYearInCalendar(),
        reactTrainingScreen.getMonthAndYearInScheduleText(),
        "Month or year in 'My schedule' is incorrect!");
  }
}
