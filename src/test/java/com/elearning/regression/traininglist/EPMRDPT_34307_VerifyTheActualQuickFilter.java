package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_SOON;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.utils.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34307_VerifyTheActualQuickFilter",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34307_VerifyTheActualQuickFilter {

  private String soonQuickFilter = LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_SOON);
  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void trainingBlockOnStartScreenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen().waitTrainingCardsVisibility()
        .clickQuickFilterByName(soonQuickFilter);
  }

  @Test
  public void checkAllCardsAreSortedByStartDateInAscendingOrder() {
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
    softAssert.assertAll("Not all cards are sorted by start date in ascending order!");
  }
}
