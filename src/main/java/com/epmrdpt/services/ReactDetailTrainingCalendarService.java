package com.epmrdpt.services;

import com.epmrdpt.screens.ReactDetailTrainingCalendarScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReactDetailTrainingCalendarService {

  private ReactDetailTrainingCalendarScreen reactDetailTrainingCalendarScreen = new ReactDetailTrainingCalendarScreen();

  public ReactDetailTrainingCalendarScreen openRequiredMonth(LocalDate startDateTraining) {
    int clicksQuantity = (int) ChronoUnit.MONTHS.between(startDateTraining, LocalDate.now());
    String dateValue = reactDetailTrainingCalendarScreen.getMonthAndYearDateText();
    for (int i = 0; i < Math.abs(clicksQuantity); i++) {
      if (clicksQuantity < 0) {
        reactDetailTrainingCalendarScreen.clickRightArrowDate();
      } else {
        reactDetailTrainingCalendarScreen.clickLeftArrowDate();
      }
      reactDetailTrainingCalendarScreen.waitGetMonthAndYearDateChanged(dateValue);
      dateValue = reactDetailTrainingCalendarScreen.getMonthAndYearDateText();
    }
    return new ReactDetailTrainingCalendarScreen();
  }

  public ReactGeneralInfoTabScreen setDateTraining(LocalDate trainingDate) {
    openRequiredMonth(trainingDate)
        .clickDayOfMonthDateButton(trainingDate.getDayOfMonth())
        .waitDateCalendarCollapsed();
    return new ReactGeneralInfoTabScreen();
  }

  public ReactGeneralInfoTabScreen setCurrentDateTraining() {
    reactDetailTrainingCalendarScreen
        .clickCurrentDayOfMonthDateButton()
        .waitDateCalendarCollapsed();
    return new ReactGeneralInfoTabScreen();
  }
}
