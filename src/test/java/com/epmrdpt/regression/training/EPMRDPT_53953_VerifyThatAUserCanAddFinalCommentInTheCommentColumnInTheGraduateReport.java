package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactCommentPopUpScreen;
import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.services.ReactCommentPopUpService;
import com.epmrdpt.services.ReactGraduateReportService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.EColorUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_53953_VerifyThatAUserCanAddFinalCommentInTheCommentColumnInTheGraduateReport",
    groups = {"full", "react", "regression"})
public class EPMRDPT_53953_VerifyThatAUserCanAddFinalCommentInTheCommentColumnInTheGraduateReport {

  private String groupName = "GroupForDeletingStudentFromGroup";
  private String comment = "Comment from Trainer AutoTest";
  private ReactGraduateReportScreen reactGraduateReportScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenGraduateJournalCommentColumn() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreenGroupsTab(asTrainer());
    reactGraduateReportScreen = new ReactTrainingService()
        .openGraduateReportByGroupName(groupName)
        .waitAllSpinnersAreNotDisplayed();
    reactGraduateReportScreen = new ReactGraduateReportService()
        .moveScrollBarToTheEnd();
  }

  @Test(priority = 1)
  public void checkThatCommentIconStatusIsInVisible() {
    assertEquals(reactGraduateReportScreen
            .getFirstStudentsCommentIconButtonColor(),
        EColorUtils.WITHOUT_COLOR.getColorRgbaFormat(),
        "'Comment' field on the 'Section Tab' have not status 'InVisible' value!");
  }

  @Test(priority = 2)
  public void checkCommentPopUpInputFieldIsEmpty() {
    assertTrue(reactGraduateReportScreen
            .clickFirstStudentsCommentIconButton()
            .getCommentFieldText()
            .equals(""),
        "React comment popUp input field' isn't empty!");
  }

  @Test(priority = 3)
  public void checkThatCommentIconIsVisible() {
    new ReactCommentPopUpScreen().typeComment(comment);
    assertEquals(new ReactCommentPopUpService()
            .closeCommentPopUpScreen()
            .getFirstStudentsCommentIconButtonColor(),
        EColorUtils.GRAY_LIGHT.getColorRgbFormat(),
        "'Comment' field on the 'Section Tab' have not status 'Visible' value!");
  }

  @Test(priority = 4)
  public void checkThatCommentWasSavedProperly() {
    assertTrue(reactGraduateReportScreen
            .clickFirstStudentsCommentIconButton()
            .getCommentPopUpScreenText()
            .contains(comment),
        "The entered comment isn't displayed in the comment pop up screen!");
  }

  @Test(priority = 5)
  public void checkThatCommentIconStatusIsInVisibleAfterCommentsDeletion() {
    new ReactCommentPopUpService()
        .deleteAllCommitsInCommentPopUpScreen();
    assertEquals(reactGraduateReportScreen
            .getFirstStudentsCommentIconButtonColor(),
        EColorUtils.WITHOUT_COLOR.getColorRgbaFormat(),
        "'Comment' field on the 'Section Tab' have not status 'InVisible' value after comments deletion!");
  }
}
