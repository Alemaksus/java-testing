package com.epmrdpt.regression.events_management;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90282_VerifyThatDataInTheEventListTableMatchesDataOfCreatedEvents",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_90282_VerifyThatDataInTheEventListTableMatchesDataOfCreatedEvents {

  private static final String EVENT_NAME = "AutoTestEvent2099";
  private static final String EVENT_FORMAT_TYPE = "Online";
  private static final LocalDate START_DATE = LocalDate.of(2099, 3, 1);
  private static final String EVENT_LANGUAGE = "English";
  private EventPreviewScreen eventPreviewScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void openEventPage() {
    eventPreviewScreen = new HeaderScreen()
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(EVENT_NAME);
  }

  @Test
  public void verifyNameInEventList() {
    assertEquals(eventPreviewScreen.getEventNameText(), EVENT_NAME,
        "The name of event does not match the created event");
  }

  @Test
  public void verifyFormatTypeInEventList() {
    assertEquals(eventPreviewScreen.getEventFormatText(), EVENT_FORMAT_TYPE,
        "The format of event does not match the created event");
  }

  @Test
  public void verifyStartDateInEventList() {
    assertEquals(eventPreviewScreen.getEventStartDate(), START_DATE,
        "The start date of the event does not match the created event expected");
  }

  @Test
  public void verifyLanguageInEventList() {
    assertEquals(eventPreviewScreen.getEventLanguageText(), EVENT_LANGUAGE,
        "The language of event does not match the created event");
  }
}
