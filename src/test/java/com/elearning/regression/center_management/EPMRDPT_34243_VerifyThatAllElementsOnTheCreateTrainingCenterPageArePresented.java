package com.epmrdpt.regression.center_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34243_VerifyThatAllElementsOnTheCreateTrainingCenterPageArePresented",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34243_VerifyThatAllElementsOnTheCreateTrainingCenterPageArePresented {

  private CreateOrEditTrainingCenterScreen createTrainingCenterScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34243_VerifyThatAllElementsOnTheCreateTrainingCenterPageArePresented(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupCreateTrainingCenterScreen() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(user)
        .clickCenterManagementLink()
        .clickCreateTrainingCenterButton();
    createTrainingCenterScreen = new CreateOrEditTrainingCenterScreen();
  }

  @Test
  public void checkHeaderDisplayed() {
    assertTrue(createTrainingCenterScreen.isHeaderDisplayed(),
        "Header isn't displayed in Create Training Center page!");
  }

  @Test
  public void checkNavigationLinkDisplayed() {
    assertTrue(createTrainingCenterScreen.isNavigationLinkDisplayed(),
        "Navigation link isn't displayed in Create Training Center page!");
  }

  @Test
  public void checkFirstSectionDisplayed() {
    assertTrue(createTrainingCenterScreen.isFirstSectionWithBasicInfoDisplayed(),
        "First section isn't displayed in Create Training Center page!");
  }

  @Test
  public void checkSecondSectionDisplayed() {
    assertTrue(createTrainingCenterScreen.isSecondSectionWithAdditionalInformationDisplayed(),
        "Second section isn't displayed in Create Training Center page!");
  }

  @Test
  public void checkFastFactTabDisplayed() {
    assertTrue(createTrainingCenterScreen.isFastFactTabDisplayed(),
        "Fast Fact Tab isn't displayed in Create Training Center page!");
  }

  @Test
  public void checkUniversitiesTabDisplayed() {
    assertTrue(createTrainingCenterScreen.isUniversitiesTabDisplayed(),
        "Universities Tab isn't displayed in Create Training Center page!");
  }

  @Test
  public void checkFeedbackTabDisplayed() {
    assertTrue(createTrainingCenterScreen.isFeedbackTabDisplayed(),
        "Feedback Tab isn't displayed in Create Training Center page!");
  }

  @Test
  public void checkFooterDisplayed() {
    assertTrue(createTrainingCenterScreen.isFooterDisplayed(),
        "Footer isn't displayed in Create Training Center page!");
  }
}