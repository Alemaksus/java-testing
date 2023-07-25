package com.epmrdpt.smoke.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactCommentPopUpScreen;
import com.epmrdpt.services.ReactCommentPopUpService;
import com.epmrdpt.services.ReactGraduateReportService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_53954_VerifyThatOtherTrainersOfTheDesiredGroupCanSeeAddedCommentsInTheCommentColumnInTheGraduateReport",
    groups = {"full", "smoke"})
public class EPMRDPT_53954_VerifyThatOtherTrainersOfTheDesiredGroupCanSeeAddedCommentsInTheCommentColumnInTheGraduateReport {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String comment = "Comment from one trainer to another trainer AutoTest";
  private ReactLoginService reactLoginService;
  private ReactGraduateReportService reactGraduateReportService;
  private ReactCommentPopUpScreen reactCommentPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAsTrainerTypeCommentFirstStudentsCommentPopUpScreen() {
    reactLoginService = new ReactLoginService();
    reactCommentPopUpScreen = new ReactCommentPopUpScreen();
    reactGraduateReportService = new ReactGraduateReportService();
    reactLoginService.loginAndGoToReactTrainingScreenGroupsTab(asTrainingManager());
    new ReactTrainingService().openGraduateReportByGroupName(groupName);
    reactGraduateReportService.addCommentToFirstStudentAndMoveScrollBarToTheEnd(comment);
    reactLoginService
        .signOut()
        .waitScreenLoaded()
        .deleteAllCookies();
  }

  @Test
  public void checkThatCommentsDisplayedForTheSecondTrainer() {
    new ReactLoginService().loginAndGoToReactTrainingScreenGroupsTab(asTrainer());
    new ReactTrainingService().openGraduateReportByGroupName(groupName);
    reactGraduateReportService
        .moveScrollBarToTheEnd()
        .clickFirstStudentsCommentIconButton();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactCommentPopUpScreen.isScreenLoaded(),
        "'Comment PopUp screen' isn't displayed!");
    softAssert.assertTrue(reactCommentPopUpScreen.isCommentDisplayed(comment),
        "The entered text from one trainer to another isn't displayed");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void loginAsTrainerDeleteAllCommitsInFirstStudentsCommentPopUpField() {
    reactLoginService
        .signOut()
        .waitScreenLoaded()
        .deleteAllCookies();
    new ReactLoginService().loginAndGoToReactTrainingScreenGroupsTab(asTrainingManager());
    new ReactTrainingService().openGraduateReportByGroupName(groupName);
    reactGraduateReportService
        .moveScrollBarToTheEnd()
        .clickFirstStudentsCommentIconButton();
    new ReactCommentPopUpService().deleteAllCommitsInCommentPopUpScreen();
  }
}
