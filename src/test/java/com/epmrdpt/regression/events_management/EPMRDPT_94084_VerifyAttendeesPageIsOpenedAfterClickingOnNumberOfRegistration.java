package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactEventAttendeesScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94084_VerifyAttendeesPageIsOpenedAfterClickingOnNumberOfRegistration",
    groups = {"full", "regression"})
public class EPMRDPT_94084_VerifyAttendeesPageIsOpenedAfterClickingOnNumberOfRegistration {

  private static final String EVENT_NAME = "Event AutoTest";
  private ReactEventAttendeesScreen attendeesScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager());
    attendeesScreen = new HeaderScreen().clickEventsManagementLink()
            .typeEventName(EVENT_NAME)
            .clickApplyButton()
            .waitAllSpinnersAreNotDisplayed()
            .clickEventByName(EVENT_NAME)
            .clickAttendeesButton();
  }

  @Test
  public void checkIsAttendeesPageOpen() {
    assertTrue(attendeesScreen.isScreenLoaded(), "Attendees page is not open!");
  }
}
