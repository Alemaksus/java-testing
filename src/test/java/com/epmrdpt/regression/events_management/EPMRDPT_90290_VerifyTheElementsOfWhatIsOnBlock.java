package com.epmrdpt.regression.events_management;

import static org.testng.Assert.*;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactEventDescriptionScreen;
import com.epmrdpt.screens.ReactEventsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90290_VerifyTheElementsOfWhatIsOnBlock",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_90290_VerifyTheElementsOfWhatIsOnBlock {
  private User user;
  ReactEventDescriptionScreen reactEventDescriptionScreen;

  @Factory(dataProvider = "Provider of users with events management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_90290_VerifyTheElementsOfWhatIsOnBlock(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactEventDescriptionScreen() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickEventsManagementLink();
    reactEventDescriptionScreen = new ReactEventsManagementScreen()
        .clickFirstEventInList()
        .clickDetailsButton()
        .clickDescriptionButton();
  }

  @Test(priority = 1)
  public void checkThatAddDescriptionButtonDisplayed() {
    assertTrue(reactEventDescriptionScreen.isAddDescriptionButtonDisplayed(),
        "'AddDescription' button is not displayed");
  }

  @Test(priority = 2)
  public void checkThatWhatIsOnLabelIsDisplayed() {
    assertTrue(reactEventDescriptionScreen.isWhatIsOnLabelDisplayed(),
        "'What's on' label is not displayed");
  }

  @Test(priority = 3)
  public void checkThatWhatIsOnEnterTextPlaceholderIsDisplayed() {
    reactEventDescriptionScreen.clickAddDescriptionButton();
    assertTrue(reactEventDescriptionScreen.isEnterTextPlaceholderDisplayed(),
        "'Enter text' placeholder is not displayed");
  }

  @Test(priority = 4)
  public void checkThatWhatIsOnDescriptionNLabelIsDisplayed() {
    assertTrue(reactEventDescriptionScreen.isDescriptionNLabelDisplayed(),
        "'Description N' label is not displayed");
  }

  @Test(priority = 4)
  public void checkThatAddIconLabelIsDisplayed() {
    assertTrue(reactEventDescriptionScreen.isAddIconLabelDisplayed(),
        "'Add icon' label is not displayed");
  }

  @Test(priority = 4)
  public void checkThatSelectFromTheListDdlIsDisplayed() {
    assertTrue(reactEventDescriptionScreen.isSelectFromTheListDdlDisplayed(),
        "'Select from the list' Drop-down-list is not displayed");
  }

  @Test(priority = 4)
  public void checkThatWhatIsOnBucketIconIsDisplayed() {
    assertTrue(reactEventDescriptionScreen.isBucketOfWhatIsOnBlockDisplayed(),
        "The bucket icon of 'WhatIsOn' block is not displayed");
  }
}
