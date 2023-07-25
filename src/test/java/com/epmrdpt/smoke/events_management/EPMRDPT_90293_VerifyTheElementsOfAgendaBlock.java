package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactEventDescriptionScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90293_VerifyTheElementsOfAgendaBlock",
    groups = {"full", "smoke"})
public class EPMRDPT_90293_VerifyTheElementsOfAgendaBlock {

  private static final String EVENT_NAME = "nix 1";
  private ReactEventDescriptionScreen descriptionBlock;

  @BeforeClass(inheritGroups = false,alwaysRun = true)
  public void setUp(){
     descriptionBlock = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink()
        .waitAllSpinnersAreNotDisplayed()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton()
        .clickDescriptionButton()
        .clickAddReportButton();
  }

  @Test
  public void checkAgendaLabelIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaLabelIsDisplayed(),
        "Agenda label isn't displayed");
  }

  @Test
  public void checkAgendaDescriptionLabelIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaDescriptionLabelIsDisplayed(),
        "Agenda description label isn't displayed");
  }

  @Test
  public void checkAddReportButtonIsDisplayed() {
    assertTrue(descriptionBlock.isAddReportButtonDisplayed(),
        "Add report button isn't displayed");
  }

  @Test
  public void checkReportInputFieldIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaReportInputFieldDisplayed(),
        "Agenda report input field isn't displayed");
  }

  @Test
  public void checkBucketOfAgendaBlockIsDisplayed() {
    assertTrue(descriptionBlock.isBucketOfAgendaBlockDisplayed(),
        "Bucket icon isn't displayed");
  }

  @Test
  public void checkAgendaStartTimeInputFieldIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaStartTimeInputFieldDisplayed(),
        "Agenda start time field isn't displayed");
  }

  @Test
  public void checkAgendaStartDateInputFieldIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaStartDateInputFieldDisplayed(),
        "Agenda start date input field isn't displayed");
  }

  @Test
  public void checkAgendaStartTimeLabelIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaStartTimeLabelDisplayed(),
        "Agenda start time label isn't displayed");
  }

  @Test
  public void checkAgendaReportLabelIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaReportLabelDisplayed(),
        "Agenda report label isn't displayed");
  }

  @Test
  public void checkAgendaStartDateLabelIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaStartDateLabelDisplayed(),
        "Agenda start date label isn't displayed");
  }

  @Test
  public void checkAgendaDescriptionInputFieldIsDisplayed() {
    assertTrue(descriptionBlock.isAgendaDescriptionInputFieldDisplayed(),
        "Agenda description input field isn't displayed");
  }
}
