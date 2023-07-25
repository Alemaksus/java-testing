package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_50961_VerifyThatUserCanUseBreadcrumbsForGroupsOnGraduateReportPage",
    groups = {"full", "react", "regression"})

public class EPMRDPT_50961_VerifyThatUserCanUseBreadcrumbsForGroupsOnGraduateReportPage {

  private final int expectedLevelsCount = 3;
  private final String trainingName = "AutoTest_React";
  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private SoftAssert softAssert;
  private ReactGraduateReportScreen reactGraduateReportScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactGraduateReportPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactTrainingService().openGraduateReportByGroupName(groupName);
    reactGraduateReportScreen = new ReactGraduateReportScreen();
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsHasThreeLevels() {
    assertEquals(reactGraduateReportScreen.getBreadCrumbsLevelCount(),
        expectedLevelsCount, "Breadcrumbs doesn't have three levels on task journal page!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesHomeIcon() {
    assertTrue(reactGraduateReportScreen.isHomeIconDisplayed(),
        "Breadcrumbs doesn't include 'Home' icon!");
  }

  @Test(priority = 1)
  public void checkThatBreadcrumbsIncludesTrainingNameAndGroupName() {
    assertEquals(reactGraduateReportScreen.getTextFromBredCrumbs(),
        trainingName + "\n" + groupName,
        "Breadcrumbs doesn't include training name or group name!");
  }

  @Test(priority = 2)
  public void checkThatTrainingGraduateReportIsOpenedAfterClickTrainingNameAndGroupSwitcherDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactGraduateReportScreen.clickTrainingNameFromGraduateReport().isScreenLoaded(),
        "Training Graduate Report isn't displayed!");
    softAssert.assertTrue(
        reactGraduateReportScreen.isGroupSwitcherDisplayed(),
        "Training Group switcher doesn't present after click training name!");
    softAssert.assertEquals(reactGraduateReportScreen.getTextFromBredCrumbs(), trainingName,
        "Training name isn't correct!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatGroupGraduateReportIsOpenedAfterClickGroupNameAndGroupSwitcherIsInvisibility() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGraduateReportScreen.clickGroupNameFromSwitcher().isScreenLoaded(),
        "Training Graduate Report isn't displayed!");
    softAssert.assertTrue(reactGraduateReportScreen.isGroupSwitcherInvisibility(),
        "Training Group switcher is present after click group name!");
    softAssert.assertEquals(reactGraduateReportScreen.getGroupNameFromBredCrumbs(), groupName,
        "Group name isn't correct!");
    softAssert.assertAll();
  }
}
