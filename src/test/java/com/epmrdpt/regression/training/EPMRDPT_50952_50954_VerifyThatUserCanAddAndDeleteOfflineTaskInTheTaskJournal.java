package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static java.lang.String.format;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.TrainingOfflineTask;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.ReactOfflineTaskInfoPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_50952_50954_VerifyThatUserCanAddAndDeleteOfflineTaskInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50952_50954_VerifyThatUserCanAddAndDeleteOfflineTaskInTheTaskJournal {

  private TrainingOfflineTask trainingOfflineTask;
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private String localDate = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupNewOfflineTask() {
    trainingOfflineTask = new TrainingOfflineTask()
        .withTaskName("AutoTest_ReactOfflineTask_" + localDate)
        .withGroup("AutoTest_GroupWithDeletingClassTicket")
        .withDate(LocalDate.now());
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer()).clickMyGroupsTab();
    new ReactHeaderScreen().waitTrainingTabForVisibility();
    reactTasksJournalScreen = new ReactTrainingService()
        .openTaskJournalByGroupName(trainingOfflineTask.getGroup())
        .addOfflineTaskAndFillAllRequiredFieldsInTaskJournal(trainingOfflineTask);
  }

  @Test(priority = 1)
  public void checkNewOfflineTaskSuccessfullyAdded() {
    assertTrue(reactTasksJournalScreen.isAddTaskNotificationPopUpDisplayed(),
        "'Task has been added' notification isn't displayed!");
  }

  @Test(priority = 2)
  public void checkCreatedOfflineTaskDisplayed() {
    assertTrue(reactTasksJournalScreen.clickCloseNotificationButton()
            .isTaskDisplayedByName(trainingOfflineTask.getTaskName()),
        format("Created offline task with name - %s isn't displayed!",
            trainingOfflineTask.getTaskName()));
  }

  @Test(priority = 3)
  public void checkConfirmDeletionPopupDisplayed() {
    assertTrue(reactTasksJournalScreen
            .clickOfflineTaskInTaskJournalByName(trainingOfflineTask.getTaskName())
            .clickDeleteTaskButton().isConfirmationDeleteTaskButtonDisplayed(),
        "'Confirmation delete' pop up isn't displayed!");
  }

  @Test(priority = 4)
  public void checkNewOfflineTaskSuccessfullyDeleted() {
    new ReactOfflineTaskInfoPopUpScreen().clickConfirmationDeleteTaskButton();
    assertTrue(reactTasksJournalScreen.isDeleteTaskNotificationPopupDisplayed(),
        "'Task is deleted' notification isn't displayed!");
  }

  @Test(priority = 5)
  public void checkCreatedOfflineTaskNotDisplayedAfterDeletion() {
    assertFalse(reactTasksJournalScreen.clickCloseNotificationButton()
            .waitTaskForInvisibility(trainingOfflineTask.getTaskName())
            .isTaskDisplayedByName(trainingOfflineTask.getTaskName()),
        format("Offline task with name - %s isn't deleted!", trainingOfflineTask.getTaskName()));
  }
}
