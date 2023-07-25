package com.epmrdpt.smoke.training;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_ONLINE_TASKS;
import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_TASK_JOURNAL_ALL_ASSIGNMENT;
import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.tokens_storage.TokensStorage.getAdminAuthorizationToken;
import static com.epmrdpt.framework.listeners.RetrieveAuthorizationTokenListener.setUpRestAssuredForUI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.http.ContentType.JSON;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.bo.task_journal.Assignment;
import com.epmrdpt.bo.task_journal.AssignmentFactory;
import com.epmrdpt.bo.task_journal.OnlineTask;
import com.epmrdpt.bo.task_journal.OnlineTaskFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactHomeTaskPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.LearningService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.RandomUtils;
import io.restassured.internal.RequestSpecificationImpl;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_39933_VerifyThatUserCanCheckSubmittedTaskUsingCheckPopUpWindow",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_39933_VerifyThatUserCanCheckSubmittedTaskUsingCheckPopUpWindow {

  private ReactHomeTaskPopUpScreen reactHomeTaskPopUpScreen;
  private TrainingOnlineTask trainingOnlineTask;
  private final String studentName = "Student AutoTest";
  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private OnlineTask onlineTask;
  private Assignment assignment;
  private String onlineTaskId;
  private int groupID = 4094;
  private int trainerId = 325913;

  @BeforeTest()
  public void addOnlineTask() {
    setUpRestAssuredForUI();
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getAdminAuthorizationToken());
    onlineTask = OnlineTaskFactory.generateOnlineTask(
        "Online_task_from_API_" + LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("y.M.d_H:m:s")),
        List.of(groupID), trainerId);
    onlineTaskId = given()
        .body(onlineTask)
        .contentType(JSON)
        .when()
        .post(TRAINING_PROCESS_ONLINE_TASKS)
        .getBody()
        .asString();
  }

  @BeforeTest(dependsOnMethods = "addOnlineTask")
  public void addAllAssignment() {
    assignment = AssignmentFactory
        .setAssignment(Integer.parseInt(onlineTaskId), groupID, List.of(trainerId));
    given()
        .body(assignment)
        .contentType(JSON)
        .when()
        .post(TRAINING_PROCESS_TASK_JOURNAL_ALL_ASSIGNMENT);
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupStudentAnswer() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asLearningStudent())
        .clickLearningButton();
    new LearningService()
        .navigateToTaskOfEpamTrainingInUrgentTab(groupName, onlineTask.getName())
        .typeComment("Answer from Student AutoTest")
        .clickSubmitButton()
        .waitPopUpWindowDisappear();
    new HeaderScreen()
        .waitForClickableAndClickArrowButton()
        .signOut();
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true, dependsOnMethods = "setupStudentAnswer")
  public void setupCheckingOnlineTask() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer());
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(onlineTask.getName())
        .withGroup(groupName)
        .withMarkOnReviewTask(
            RandomUtils.getRandomNumberInInterval(1, onlineTask.getMaxMark()))
        .withCommentForStudentOnReview("Comment for a student")
        .withCommentForTrainerOnReview("Comment for trainers");
    new ReactTrainingScreen()
        .clickCheckButtonInTasksSectionByTaskName(onlineTask.getName())
        .fillMarkAndCommentsToCheckTaskTicketInScheduleTab(trainingOnlineTask);
  }

  @Test
  public void checkReviewInformation() {
    reactHomeTaskPopUpScreen = new ReactHomeTaskPopUpScreen();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactHomeTaskPopUpScreen.getCommentForStudentText(),
        trainingOnlineTask.getCommentForStudentOnReview(),
        "'Comment for a student' field on the 'Home task' pop up has incorrect text!");
    softAssert.assertEquals(reactHomeTaskPopUpScreen.getCommentForTrainerText(),
        trainingOnlineTask.getCommentForTrainerOnReview(),
        "'Comment for a trainer' field on the 'Home task' pop up has incorrect text!");
    softAssert.assertEquals(reactHomeTaskPopUpScreen.getMarkValue(),
        trainingOnlineTask.getMarkOnReviewTask(),
        "'Mark' field on the 'Home task' pop up has incorrect value!");
    reactHomeTaskPopUpScreen.clickClosePopUpButton();
    softAssert.assertFalse(new ReactTrainingScreen()
            .waitTaskTicketInTaskSectionForInvisibility(trainingOnlineTask.getTaskName())
            .isTaskTicketInTasksSectionByNameDisplayed(trainingOnlineTask.getTaskName()),
        "Task ticket is not disappear after checking in 'Tasks' section!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteCheckedOnlineTask(Method method) {
    new ReactTrainingScreen().clickMyGroupsTab();
    ReactTaskJournalService reactTaskJournalService = new ReactTrainingService()
        .openTaskJournalByGroupName(groupName);
    new ReactTasksJournalScreen().clickAllPeriodButton();
    reactTaskJournalService.cancelTaskCheck(studentName, trainingOnlineTask)
        .reassignTask(studentName, trainingOnlineTask)
        .unassignTaskForAllStudents(trainingOnlineTask)
        .deleteOnlineTask(trainingOnlineTask);
  }
}
