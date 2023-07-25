package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactEventsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90281_VerifyTheColumnOfTheTableOnTheEventsListPage",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_90281_VerifyTheColumnOfTheTableOnTheEventsListPage {

  private ReactEventsManagementScreen reactEventsManagementScreen;
  private String nameLabel = LocaleProperties.getValueOf(REACT_EVENTS_MANAGEMENT_NAME_LABEL);
  private String locationLabel = LocaleProperties.getValueOf(
      REACT_EVENTS_MANAGEMENT_LOCATION_LABEL);
  private String dateLabel = LocaleProperties.getValueOf(REACT_EVENTS_MANAGEMENT_DATE_LABEL);
  private String numberOfRegistrationsLabel = LocaleProperties.getValueOf(
      REACT_EVENTS_MANAGEMENT_NUMBER_OF_REGISTRATIONS_LABEL);
  private String currentStatusLabel = LocaleProperties.getValueOf(
      REACT_EVENTS_MANAGEMENT_CURRENT_STATUS_LABEL);
  private String languageLabel = LocaleProperties.getValueOf(
      REACT_EVENTS_MANAGEMENT_LANGUAGE_LABEL);
  private String creatorLabel = LocaleProperties.getValueOf(REACT_EVENTS_MANAGEMENT_CREATOR_LABEL);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink();
    reactEventsManagementScreen = new ReactEventsManagementScreen();
  }

  @Test
  public void isNameLabelDisplayed() {
    assertTrue(reactEventsManagementScreen.isEventsListLabelDisplayed(nameLabel),
        "NameLabel isn't displayed");
  }

  @Test
  public void isLocationLabelDisplayed() {
    assertTrue(reactEventsManagementScreen.isEventsListLabelDisplayed(locationLabel),
        "LocationLabel isn't displayed");
  }

  @Test
  public void isDateLabelDisplayed() {
    assertTrue(reactEventsManagementScreen.isEventsListLabelDisplayed(dateLabel),
        "DateLabel isn't displayed");
  }

  @Test
  public void isNumberOfRegistrationsLabelDisplayed() {
    assertTrue(
        reactEventsManagementScreen.isEventsListLabelDisplayed(numberOfRegistrationsLabel),
        "NumberOfRegistrationsLabel isn't displayed");
  }

  @Test
  public void isCurrentStatusLabelDisplayed() {
    assertTrue(reactEventsManagementScreen.isEventsListLabelDisplayed(currentStatusLabel),
        "CurrentStatusLabel isn't displayed");
  }

  @Test
  public void isLanguageLabelDisplayed() {
    assertTrue(reactEventsManagementScreen.isEventsListLabelDisplayed(languageLabel),
        "CurrentStatusLabel isn't displayed");
  }

  @Test
  public void isCreatorLabelDisplayed() {
    assertTrue(reactEventsManagementScreen.isEventsListLabelDisplayed(creatorLabel),
        "LocationLabel isn't displayed");
  }
}
