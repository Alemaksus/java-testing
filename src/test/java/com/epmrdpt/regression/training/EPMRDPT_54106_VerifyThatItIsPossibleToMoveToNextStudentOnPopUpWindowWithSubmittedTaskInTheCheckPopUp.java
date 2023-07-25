package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.utils.RandomUtils.getRandomNumberInInterval;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.ReactHomeTaskPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.EColorUtils;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_54106_VerifyThatItIsPossibleToMoveToNextStudentOnPopUpWindowWithSubmittedTaskInTheCheckPopUp",
    groups = {"full", "react", "regression"})
public class EPMRDPT_54106_VerifyThatItIsPossibleToMoveToNextStudentOnPopUpWindowWithSubmittedTaskInTheCheckPopUp {

  private final String taskName = "Online_Task_For_Check_Bulk_Operation";
  private final String groupsName = "AutoTest_GroupWithEverydayClasses";
  private final String commentForStudent = "comment for student";
  private final String commentForTrainer = "comment for trainer";
  private int firstStudentCounter;
  private String firstStudentName;
  private String defaultMarkBackGroundColor = EColorUtils.WHITE_COLOR.getColorRgbaFormat();
  private ReactHomeTaskPopUpScreen reactHomeTaskPopUpScreen;
  private ReactTrainingService reactTrainingService;
  private TrainingOnlineTask trainingOnlineTask;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupHomeTaskPopUpScreen() {
    int passMark = getRandomNumberInInterval(2, 10);
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(taskName)
        .withGroup(groupsName)
        .withCommentForStudentOnReview(commentForStudent)
        .withCommentForTrainerOnReview(commentForTrainer)
        .withMarkOnReviewTask(getRandomNumberInInterval(1, 10))
        .withPassMarkOnReviewTask(passMark);
    new ReactLoginService().loginAndGoToReactTrainingScreen(asTrainer()).clickMyGroupsTab();
    reactTrainingService = new ReactTrainingService();
    reactTrainingService.openTaskJournalByGroupName(trainingOnlineTask.getGroup());
    reactHomeTaskPopUpScreen = new ReactTasksJournalScreen().clickAllPeriodButton()
        .clickOnlineTaskInTaskJournalByName(trainingOnlineTask.getTaskName())
        .waitOnlineTaskNameOnPopUpVisibility()
        .moveTaskInfoPopUpToTheTopOfTheScreen()
        .clickCheckButton()
        .waitSpinnerOfLoadingDisappear();
  }

  @Test(priority = 1)
  public void checkPopUpIsForBulkOperation() {
    assertTrue(reactHomeTaskPopUpScreen.isNextStudentLinkPresent(),
        String.format("'%s' is not for bulk operation!", trainingOnlineTask.getTaskName()));
  }

  @Test(priority = 2)
  public void checkFirstStudentHomeTaskPopUpScreen() {
    reactTrainingService.fillMarkAndCommentsToCheckTaskTicketInScheduleTab(trainingOnlineTask);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactHomeTaskPopUpScreen.getCommentForStudentText(),
        trainingOnlineTask.getCommentForStudentOnReview(),
        "'Comment for student' field on the 'Home task' pop up has incorrect value!");
    softAssert.assertEquals(reactHomeTaskPopUpScreen.getCommentForTrainerText(),
        trainingOnlineTask.getCommentForTrainerOnReview(),
        "'Comment for trainer' field on the 'Home task' pop up has incorrect value!");
    softAssert.assertEquals(reactHomeTaskPopUpScreen.getMarkValue(),
        trainingOnlineTask.getMarkOnReviewTask(),
        "'Mark' field on the 'Home task' pop up has incorrect value!");
    softAssert.assertTrue(reactHomeTaskPopUpScreen.getStatusBlockText()
            .contains(reactHomeTaskPopUpScreen.getCheckedStatusText()),
        "'Status Block' field on the 'Home task' have not 'Checked' value!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkNextStudentHomeTaskPopUpScreen() {
    firstStudentCounter = reactHomeTaskPopUpScreen.getStudentCounterField();
    firstStudentName = reactHomeTaskPopUpScreen.getStudentNameFieldText();
    reactHomeTaskPopUpScreen.clickNextStudentLink().waitSpinnerOfLoadingDisappear();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertNotEquals(firstStudentName, reactHomeTaskPopUpScreen.getStudentNameFieldText(),
        "Student name has not changed after click the 'Next student' button!");
    softAssert.assertEquals(++firstStudentCounter, reactHomeTaskPopUpScreen.getStudentCounterField(),
        "The number on the students counter has not increased by one!");
    softAssert.assertTrue(reactHomeTaskPopUpScreen.getMarksBlocksBackgroundColour().stream()
            .allMatch(color -> color.equals(defaultMarkBackGroundColor)),
        "Mark value on the 'Home task' pop up is checked by default!");
    softAssert.assertTrue(reactHomeTaskPopUpScreen.getCommentForStudentInputText().isEmpty(),
        "'Comment for student' field on the 'Home task' pop up is not empty by default!");
    softAssert.assertTrue(reactHomeTaskPopUpScreen.getCommentForTrainerInputText().isEmpty(),
        "'Comment for trainer' field on the 'Home task' pop up is not empty by default!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void cancelReview(Method method) {
    if("checkNextStudentHomeTaskPopUpScreen".equals(method.getName())) {
      reactHomeTaskPopUpScreen
          .clickPreviousStudentLink()
          .clickCancelReviewButton()
          .clickConfirmationReviewButton()
          .waitSubmitButtonIsPresented()
          .clickClosePopUpButton();
      new ReactLoginService().signOut();
    }
  }
}
