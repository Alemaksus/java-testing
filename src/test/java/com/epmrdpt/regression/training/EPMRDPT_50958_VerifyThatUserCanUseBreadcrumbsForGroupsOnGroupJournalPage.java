package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_50958_VerifyThatUserCanUseBreadcrumbsForGroupsOnGroupJournalPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50958_VerifyThatUserCanUseBreadcrumbsForGroupsOnGroupJournalPage {

  private final int expectedLevelsCount = 3;
  private final String className = "AutoTest_EditClass";
  private final String trainingName = "AutoTest_React";
  private final String groupName = "AutoTest_GroupWithEverydayClasses";

  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactGroupJournalPage() {
    reactGroupJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility()
        .clickGoToJournalButtonByClassName(className)
        .waitTableHeaderForVisibility();
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsHasThreeLevels() {
    assertEquals(reactGroupJournalScreen.getBreadCrumbsLevelCount(),
        expectedLevelsCount, "Breadcrumbs doesn't have three levels on group journal page!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesHomeIcon() {
    assertTrue(reactGroupJournalScreen.isHomeIconDisplayed(),
        "Breadcrumbs doesn't include 'Home' icon!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesTrainingNameAndGroupName() {
    assertEquals(reactGroupJournalScreen.getTextFromBredCrumbs(),
        trainingName + "\n" + groupName,
        "Breadcrumbs doesn't include training name or group name!");
  }

  @Test(priority = 2)
  public void checkThatTrainingJournalIsOpenedAfterClickTrainingName() {
    assertTrue(reactGroupJournalScreen.clickTrainingNameFromGroupJournal()
            .isGroupSwitcherDisplayed(),
        "Training Journal doesn't opened after click training name!");
  }
}
