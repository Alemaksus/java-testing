package com.epmrdpt.regression.home;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.utils.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34278_VerifyTheDefaultSortingOfTheTrainingCards",
    groups = {"full", "regression"})
public class EPMRDPT_34278_VerifyTheDefaultSortingOfTheTrainingCards {

  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupHomePage() {
    trainingBlockScreen = new TrainingBlockScreen()
        .waitTrainingCardsVisibility()
        .checkAndClickViewMoreTrainingsLink();
  }

  @Test(priority = 1)
  public void verifyEverySecondTrainingCardHasOngoingStatus() {
    assertTrue(trainingBlockScreen.isTrainingCardsOrdinaryAndOngoingDisplayedByTurn(),
        "Ordinary and ongoing training cards aren't displayed one by one!");
  }

  @Test(priority = 2)
  public void verifyTheDefaultSortingOfTheTrainingCards() {
    List<String> dateList = trainingBlockScreen.getTrainingStartDatesTextWithDate();
    List<Calendar> datesWithMonthAndYear = StringUtils.getCalendarListWithMonthAndYear(dateList);
    List<Calendar> datesWithFullDate = StringUtils.getCalendarListWithFullDate(dateList);
    List<List<Calendar>> dates = new ArrayList<>();
    dates.add(datesWithFullDate);
    dates.add(datesWithMonthAndYear);
    SoftAssert softAssert = new SoftAssert();
    dates.forEach(date -> softAssert.assertEquals(date, date
        .stream()
        .sorted(Calendar::compareTo)
        .collect(Collectors.toList())));
    softAssert.assertAll("Training cards default sorting is incorrect!");
  }
}

