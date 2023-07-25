package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_50957_VerifyThatUserCanUseBreadcrumbsForTrainingsOnTheTrainingJournalPage",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50957_VerifyThatUserCanUseBreadcrumbsForTrainingsOnTheTrainingJournalPage {

  private final int expectedLevelsCount = 2;
  private final String trainingName = "AutoTest_React";
  private final String groupName = "AutoTest_GroupWithEverydayClasses";

  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactTrainingJournalPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility();
    reactGroupJournalScreen = new ReactTrainingService()
        .openTrainingJournalByGroupName(groupName);
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsHasTwoLevels() {
    assertEquals(reactGroupJournalScreen.getBreadCrumbsLevelCount(), expectedLevelsCount,
        "Breadcrumbs doesn't have two levels on group journal page!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesHomeIcon() {
    assertTrue(reactGroupJournalScreen.isHomeIconDisplayed(),
        "Breadcrumbs doesn't include 'Home' icon!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesTrainingName() {
    assertEquals(reactGroupJournalScreen.getTrainingNameFromGroupJournalText(),
        trainingName, "Breadcrumbs doesn't include training name!");
  }

  @Test(priority = 2)
  public void checkThatMySchedulePageIsOpenedAfterClickHomeIcon() {
    assertTrue(reactGroupJournalScreen.clickHomeIcon()
            .isMyGroupsTabDisplayed(),
        "My schedule page doesn't opened after click home icon!");
  }
}
