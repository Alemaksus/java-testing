package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.training_class.TrainingClassFactory.forCheckMarkAndCommentsInTheGroupJournal;
import static java.lang.String.format;

import com.epmrdpt.bo.TaskReview;
import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_51206_VerifyTheUserCanSeeCommentsInTheCommentPopoverGroupJournalGrid",
    groups = {"full", "react", "regression"})
public class EPMRDPT_51206_VerifyTheUserCanSeeCommentsInTheCommentPopoverGroupJournalGrid {

  private final String studentName = "student LastName204134";

  private TrainingClass trainingClass;
  private TaskReview taskReview;
  private ReactLoginService reactLoginService;
  private ReactGroupJournalService reactGroupJournalService;
  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingClass() {
    reactLoginService = new ReactLoginService();
    reactGroupJournalService = new ReactGroupJournalService();
    trainingClass = forCheckMarkAndCommentsInTheGroupJournal();
    taskReview = new TaskReview()
        .withStudentName(studentName)
        .withCommentForStudent("Test comment for student")
        .withCommentForTrainers("Test comment for trainers");
  }

  @Test(dataProvider = "Provider of users with React Training permissions",
      dataProviderClass = DataProviderSource.class)
  public void checkThatMarkAndCommentsAreDisplayedInTheGroupJournal(User user) {
    reactLoginService.loginAndGoToReactTrainingScreen(user).clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(trainingClass.getGroup());
    deleteClassIfNeeded();
    reactGroupJournalScreen.clickAddClassButton();
    reactGroupJournalService
        .fillAllRequiredFieldsToAddClassInGroupJournal(trainingClass)
        .createCellMarkComments(taskReview)
        .clickCommentIconByStudentName(studentName);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactGroupJournalScreen.getPopUpCommentForStudentText(trainingClass.getStartDate()),
        taskReview.getCommentForStudent(),
        format("The comment for student by %s is not displayed in the Comment pop-up!",
            user.getFirstName()));
    softAssert.assertEquals(
        reactGroupJournalScreen.getPopUpCommentForTrainerText(trainingClass.getStartDate()),
        taskReview.getCommentForTrainers(),
        format("The comment for trainer by %s is not displayed in the Comment pop-up!",
            user.getFirstName()));
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteCreatedClassAndSignOutUser() {
    reactGroupJournalService.deleteClassInTableByDate(trainingClass.getStartDate());
    reactLoginService.signOut();
  }

  private void deleteClassIfNeeded() {
    if (reactGroupJournalScreen.isClassInTableByDateDisplayed(trainingClass.getStartDate())) {
      reactGroupJournalService.deleteClassInTableByDate(trainingClass.getStartDate());
    }
  }
}
