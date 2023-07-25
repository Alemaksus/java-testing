package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactScheduleTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_50960_VerifyThatUserCanUseBreadcrumbsForGroupsOnSchedulePage",
    groups = {"full", "react", "regression"})

public class EPMRDPT_50960_VerifyThatUserCanUseBreadcrumbsForGroupsOnSchedulePage {

  private final int expectedLevelsCount = 3;
  private final String trainingName = "AutoTest_React";
  private final String groupName = "AutoTest_GroupWithEverydayClasses";

  private ReactScheduleTabScreen reactScheduleTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTasksJournalPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactTrainingService().openScheduleTabByGroupName(groupName);
    reactScheduleTabScreen = new ReactScheduleTabScreen();
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsHasThreeLevels() {
    assertEquals(reactScheduleTabScreen.getBreadCrumbsLevelCount(),
        expectedLevelsCount, "Breadcrumbs doesn't have three levels on task journal page!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesHomeIcon() {
    assertTrue(reactScheduleTabScreen.isHomeIconDisplayed(),
        "Breadcrumbs doesn't include 'Home' icon!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesTrainingNameAndGroupName() {
    assertEquals(reactScheduleTabScreen.getTextFromBredCrumbs(),
        trainingName + "\n" + groupName,
        "Breadcrumbs doesn't include training name or group name!");
  }

  @Test(priority = 2)
  public void checkThatTrainingScheduleIsOpenedAfterClickTrainingNameAndGroupSwitcherDisplayed() {
    assertTrue(
        reactScheduleTabScreen.clickTrainingNameFromScheduleJournal()
            .waitScheduleTableForVisibility()
            .isGroupSwitcherDisplayed(),
        "Training Group switcher doesn't present after click training name!");
  }

  @Test(priority = 3)
  public void checkThatGroupScheduleIsOpenedAfterClickGroupNameAndGroupSwitcherNotDisplayed() {
    assertTrue(reactScheduleTabScreen.clickGroupNameFromSwitcher()
            .waitScheduleTableForVisibility()
            .isGroupSwitcherNotDisplayed(),
        "Training Group switcher is present after click group name!");
  }
}
