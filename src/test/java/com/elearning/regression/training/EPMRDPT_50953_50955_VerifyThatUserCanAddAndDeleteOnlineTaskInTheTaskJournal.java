package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingOnlineTask;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactOnlineTaskInfoPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_50953_50955_VerifyThatUserCanAddAndDeleteOnlineTaskInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50953_50955_VerifyThatUserCanAddAndDeleteOnlineTaskInTheTaskJournal {

  private TrainingOnlineTask trainingOnlineTask;
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private ReactOnlineTaskInfoPopUpScreen reactOnlineTaskInfoPopUpScreen;
  private String localDate = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupNewOnlineTask() {
    trainingOnlineTask = new TrainingOnlineTask()
        .withTaskName("AutoTest_ReactOnlineTask_" + localDate)
        .withGroup("AutoTest_GroupWithDeletingClassTicket");
    reactTasksJournalScreen = new ReactTasksJournalScreen();
    reactOnlineTaskInfoPopUpScreen = new ReactOnlineTaskInfoPopUpScreen();
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    new ReactHeaderScreen()
        .waitTrainingTabForVisibility();
    new ReactTrainingService()
        .openTaskJournalByGroupName(trainingOnlineTask.getGroup());
    reactTasksJournalScreen
        .waitTableHeaderForVisibility();
    new ReactTaskJournalService()
        .addOnlineTaskAndFillAllRequiredFieldsInTaskJournal(trainingOnlineTask);
  }

  @Test(priority = 1)
  public void checkNewOnlineTaskSuccessfullyAdded() {
    assertTrue(reactTasksJournalScreen.isAddTaskNotificationPopUpDisplayed(),
        "'Task has been added' notification isn't displayed!");
  }

  @Test(priority = 2)
  public void checkCreatedOnlineTaskDisplayed() {
    assertTrue(reactTasksJournalScreen
            .clickCloseNotificationButton()
            .isTaskDisplayedByName(trainingOnlineTask.getTaskName()),
        format("Created online task with name - %s isn't displayed!",
            trainingOnlineTask.getTaskName()));
  }

  @Test(priority = 3)
  public void checkNotAssignedStatusDisplayed() {
    assertTrue(reactTasksJournalScreen.isNotAssignedStatusButtonDisplayed(),
        "'Not assigned' status button isn't displayed!");
  }

  @Test(priority = 4)
  public void checkDeleteIconEnabledOnOnlineTaskInfoPopup() {
    assertTrue(reactTasksJournalScreen
            .clickOnlineTaskInTaskJournalByName(trainingOnlineTask.getTaskName())
            .isDeleteIconEnabled(),
        "'Delete icon' on 'Online task info' pop up isn't enabled!");
  }

  @Test(priority = 5)
  public void checkConfirmDeletionPopupDisplayed() {
    assertTrue(reactOnlineTaskInfoPopUpScreen.clickDeleteTaskButton()
            .isConfirmationDeleteTaskButtonDisplayed(),
        "'Confirmation delete' pop up isn't displayed!");
  }

  @Test(priority = 6)
  public void checkNewOnlineTaskSuccessfullyDeleted() {
    reactOnlineTaskInfoPopUpScreen.clickConfirmationDeleteTaskButton();
    assertTrue(reactTasksJournalScreen.isDeleteTaskNotificationPopupDisplayed(),
        "'Task is deleted' notification isn't displayed!");
  }

  @Test(priority = 7)
  public void checkCreatedOnlineTaskNotDisplayedAfterDeletion() {
    assertFalse(reactTasksJournalScreen.clickCloseNotificationButton()
            .waitTaskForInvisibility(trainingOnlineTask.getTaskName())
            .isTaskDisplayedByName(trainingOnlineTask.getTaskName()),
        format("Online task with name - %s isn't deleted!", trainingOnlineTask.getTaskName()));
  }
}
