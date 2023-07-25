package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactEventAttendeesScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90307_VerifyThatUserAppearsInTheAttendeesListAfterRegistration",
    groups = {"full", "smoke"})
public class EPMRDPT_90307_VerifyThatUserAppearsInTheAttendeesListAfterRegistration {

  private static final String EVENT_NAME = "Event AutoTest";

  private ReactEventAttendeesScreen reactEventAttendeesScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    reactEventAttendeesScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(EVENT_NAME)
        .clickAttendeesButton();
  }

  @Test
  public void checkAttendeesInList() {
    assertTrue(reactEventAttendeesScreen.isFirstAttendeeDisplayed(),
        "Participants are not present in the list of participants");
  }
}
