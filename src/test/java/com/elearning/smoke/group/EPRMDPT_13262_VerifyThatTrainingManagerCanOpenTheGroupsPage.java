package com.epmrdpt.smoke.group;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPRMDPT_13262_VerifyThatTrainingManagerCanOpenTheGroupsPage",
    groups = {"full", "manager", "smoke"})
public class EPRMDPT_13262_VerifyThatTrainingManagerCanOpenTheGroupsPage {

  private final User user;
  private ReactGroupScreen reactGroupScreen;
  private static final String TRAINING_NAME = "AutoTest_WithGroupAndPatricipantInProgress";

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPRMDPT_13262_VerifyThatTrainingManagerCanOpenTheGroupsPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupGroupTabInTraining() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink();
    reactGroupScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .waitGroupsTabDisplayed()
        .clickGroupsTabs();
  }

  @Test(priority = 1)
  public void checkThatGroupsPageIsDisplayed() {
    assertTrue(reactGroupScreen.isScreenLoaded(),
        "Groups page is not displayed");
  }

  @Test(priority = 2)
  public void checkBreadcrumbsBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupScreen.isHomeButtonIconDisplayed(),
        "Home button isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isPlanningLinkButtonDisplayed(),
        "Planning link isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isPlanningLinkButtonClickable(),
        "Planning link isn't clickable!");
    softAssert.assertTrue(reactGroupScreen.isTrainingNameDisplayed(TRAINING_NAME),
        "Training name section isn't displayed");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkNavigationBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupScreen.isAddGroupButtonDisplayed(),
        "Add group button isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isDetailsTabDisplayed(),
        "Details tab isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isGroupsTabDisplayed(),
        "Groups tab isn't displayed");
    softAssert.assertTrue(reactGroupScreen.isParticipantsTabDisplayed(),
        "Participants tab isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isAuditTabDisplayed(),
        "Audit tab isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkGroupTableColumns() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupScreen.isGroupTableNameSectionDisplayed(),
        "Group table name section isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isStartDateSectionDisplayed(),
        "Start date section isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isEndDateSectionDisplayed(),
        "End date section isn't displayed!");
    softAssert.assertTrue(reactGroupScreen.isAlreadyInGroupSectionDisplayed(),
        "Already in group section isn't displayed");
    softAssert.assertTrue(reactGroupScreen.isCurrentStatusSectionDisplayed(),
        "Current status section isn't displayed!");
    softAssert.assertAll();
  }
}

