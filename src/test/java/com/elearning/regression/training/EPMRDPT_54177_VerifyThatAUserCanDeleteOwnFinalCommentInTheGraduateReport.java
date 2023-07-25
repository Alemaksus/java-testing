package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactCommentPopUpScreen;
import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.services.ReactGraduateReportService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_54177_VerifyThatAUserCanDeleteOwnFinalCommentInTheGraduateReport",
    groups = {"full", "react", "regression"})
public class EPMRDPT_54177_VerifyThatAUserCanDeleteOwnFinalCommentInTheGraduateReport {

  private String groupName = "GroupForDeletingStudentFromGroup";
  private String comment = "Comment from Trainer AutoTest";
  private ReactGraduateReportScreen reactGraduateReportScreen;
  private ReactCommentPopUpScreen reactCommentPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenGraduateJournalCommentColumnAndTypeAComment() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreenGroupsTab(asTrainer());
    reactGraduateReportScreen = new ReactTrainingService()
        .openGraduateReportByGroupName(groupName);
    new ReactGraduateReportService()
        .addCommentToFirstStudentAndMoveScrollBarToTheEnd(comment);
    reactCommentPopUpScreen = new ReactCommentPopUpScreen();
  }

  @Test(priority = 1)
  public void checkThatCommentIsExist() {
    assertEquals(reactGraduateReportScreen
            .clickFirstStudentsCommentIconButton()
            .getCommentPopUpScreenText(),
        comment,
        "The entered comment isn't displayed in the comment pop up screen!");
  }

  @Test(priority = 2)
  public void checkThatDeleteBinButtonDisplayed() {
    assertTrue(reactCommentPopUpScreen.isDeleteBinButtonDisplayed(),
        "Delete bin button did not display");
  }

  @Test(priority = 3)
  public void checkThatDeletionScreenAppearedAndDeleteSureButtonDisplayed() {
    reactCommentPopUpScreen.clickDeleteBinButton();
    assertTrue(reactCommentPopUpScreen.isDeleteSureButtonDisplayed(),
        "Deletion screen did not appear");
  }

  @Test(priority = 4)
  public void checkThatConfirmationOfCommentDeletionDisplayed() {
    reactCommentPopUpScreen.clickDeleteSureButton();
    assertTrue(reactCommentPopUpScreen.isConfirmationOfCommentDeletionDisplayed(),
        "Confirmation deletion screen did not appear");
  }
}
