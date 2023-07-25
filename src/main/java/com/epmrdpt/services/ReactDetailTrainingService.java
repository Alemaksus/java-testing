package com.epmrdpt.services;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReactDetailTrainingService {

  private ReactDetailTrainingScreen reactDetailTrainingScreen = new ReactDetailTrainingScreen();

  public ReactDetailTrainingScreen enterStartDate(LocalDate localDate) {
    reactDetailTrainingScreen
        .waitAllSpinnersAreNotDisplayed()
        .clickCrossStartDate()
        .waitUntilStartDateAttributeGetsValue("value", "");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formattedLocalDate = localDate.format(formatter);
    return reactDetailTrainingScreen
        .typeDateToStartDate(formattedLocalDate)
        .waitUntilStartDateAttributeGetsValue("value", formattedLocalDate)
        .waitSaveChangesButtonEnabled();
  }
}
