package com.epmrdpt.smoke.training;

import static com.epmrdpt.api.Endpoints.TRAINING_PROCESS_ONLINE_TASKS;
import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.tokens_storage.TokensStorage.getAdminAuthorizationToken;
import static com.epmrdpt.framework.listeners.RetrieveAuthorizationTokenListener.setUpRestAssuredForUI;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_TASK_STATUS_ASSIGNED;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.http.ContentType.JSON;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.bo.task_journal.OnlineTask;
import com.epmrdpt.bo.task_journal.OnlineTaskFactory;
import com.epmrdpt.screens.ReactAddAssignmentPopUpScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import io.restassured.internal.RequestSpecificationImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_54100_VerifyThatUserCanAssignOnlineTaskForStudentOnTheTaskJournal",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_54100_VerifyThatUserCanAssignOnlineTaskForStudentOnTheTaskJournal {

  private final String studentName = "QQ AA";
  private final String groupName = "AutoTest_Trainer_Workflow_Group";
  private final String taskName = "AutoTest_Task_";
  private TrainingOnlineTask trainingOnlineTask;
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private ReactAddAssignmentPopUpScreen reactAddAssignmentPopUpScreen;
  private int groupID = 4062;
  private int trainerId = 325913;
  private OnlineTask onlineTask;

  @BeforeTest()
  public void addOnlineTask() {
    setUpRestAssuredForUI();
    ((RequestSpecificationImpl) requestSpecification).cookie("MvcToken", getAdminAuthorizationToken());
    onlineTask = OnlineTaskFactory.generateOnlineTask(taskName + LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("y.M.d_H:m:s")), List.of(groupID), trainerId);
    given()
        .body(onlineTask)
        .contentType(JSON)
        .when()
        .post(TRAINING_PROCESS_ONLINE_TASKS);
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndSetupOnlineTask() {
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName(onlineTask.getName())
        .withGroup(groupName)
        .withStudent(studentName);
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactHeaderScreen().waitTrainingTabForVisibility();
    new ReactTrainingService()
        .openTaskJournalByGroupName(groupName);
    reactTasksJournalScreen = new ReactTasksJournalScreen();
    reactTasksJournalScreen.clickAllPeriodButton().waitTableHeaderForVisibility();
    reactTasksJournalScreen
        .clickTaskStatus(trainingOnlineTask);
    reactAddAssignmentPopUpScreen = new ReactAddAssignmentPopUpScreen();
  }

  @Test()
  public void checkThatOnlineTaskIsAssignedForStudent() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactAddAssignmentPopUpScreen.isScreenLoaded(),
        " 'Add assignment' pop-up isn't displayed");
    reactAddAssignmentPopUpScreen
        .clickDeadlineNotificationCheckbox()
        .clickAssignButton();
    softAssert.assertTrue(reactTasksJournalScreen.waitAssignedNotificationPopupVisibility().
            isAssignedNotificationPopupDisplayed(),
        "Notification 'online task had been assigned' isn't appeared");
    softAssert.assertEquals(reactTasksJournalScreen.clickCloseNotificationButton()
            .getTaskStatusTextByStudentNameAndTaskId(studentName, trainingOnlineTask),
        getValueOf(ONLINE_TASK_STATUS_ASSIGNED),
        "Online task status isn't changed to 'Assigned'");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteOnlineTask() {
    new ReactTaskJournalService()
        .unassignOnlineTask(trainingOnlineTask)
        .deleteOnlineTask(trainingOnlineTask);
  }
}
