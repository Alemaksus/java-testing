package com.epmrdpt.services;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.text.html.HTML;

public class ReactClassService {

  private ReactGroupDetailsScreen reactGroupDetailsScreen = new ReactGroupDetailsScreen();

  public ReactGroupDetailsScreen enterStartDate(LocalDate localDate) {
    reactGroupDetailsScreen
        .waitAllSpinnersAreNotDisplayed()
        .clickStartDateInputCrossButton()
        .waitUntilStartDateAttributeValueIsEmpty();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    String formattedLocalDate = localDate.format(formatter);
    return reactGroupDetailsScreen
        .typeDateToStartDate(formattedLocalDate)
        .waitUntilStartDateAttributeGetsValue(HTML.Attribute.VALUE.toString(), formattedLocalDate);
  }
}
