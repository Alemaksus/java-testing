package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_50959_VerifyThatUserCanUseBreadcrumbsForGroupsOnTaskJournalPage",
    groups = {"full", "react", "regression"})

public class EPMRDPT_50959_VerifyThatUserCanUseBreadcrumbsForGroupsOnTaskJournalPage {

  private final int expectedLevelsCount = 3;
  private final String trainingName = "AutoTest_React";
  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private SoftAssert softAssert;
  private ReactTasksJournalScreen reactTasksJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTasksJournalPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactTrainingService().openTaskJournalByGroupName(groupName);
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsHasThreeLevels() {
    reactTasksJournalScreen = new ReactTasksJournalScreen().waitTableHeaderForVisibility();
    assertEquals(reactTasksJournalScreen.getBreadCrumbsLevelCount(),
        expectedLevelsCount, "Breadcrumbs doesn't have three levels on task journal page!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesHomeIcon() {
    assertTrue(reactTasksJournalScreen.isHomeIconDisplayed(),
        "Breadcrumbs doesn't include 'Home' icon!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesTrainingNameAndGroupName() {
    assertEquals(reactTasksJournalScreen.getTextFromBredCrumbs(),
        trainingName + "\n" + groupName,
        "Breadcrumbs doesn't include training name or group name!");
  }

  @Test(priority = 2)
  public void checkThatTrainingTasksIsOpenedAfterClickTrainingNameAndGroupSwitcherDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactTasksJournalScreen.clickTrainingNameFromTasksJournal()
        .isScreenLoaded(), "Training Group Tasks is not displayed!");
    softAssert.assertTrue(reactTasksJournalScreen.isGroupSwitcherDisplayed(),
        "Training Group switcher doesn't present after click training name!");
    softAssert.assertEquals(reactTasksJournalScreen.getTextFromBredCrumbs(), trainingName,
        "Training name isn't correct!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatGroupTasksIsOpenedAfterClickGroupNameAndGroupSwitcherIsInvisibility() {
    softAssert.assertTrue(reactTasksJournalScreen.clickGroupNameFromSwitcher()
            .waitTableHeaderForVisibility().isScreenLoaded(),
        "Training Group Tasks is not displayed!");
    softAssert.assertTrue(reactTasksJournalScreen.isGroupSwitcherInvisibility(),
        "Training Group switcher is present after click group name!");
    softAssert.assertEquals(reactTasksJournalScreen.getGroupNameFromBredCrumbs(), groupName,
        "Group name isn't correct!");
    softAssert.assertAll();
  }
}
