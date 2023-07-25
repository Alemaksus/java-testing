package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactOfflineTaskInfoPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_50946_VerifyTheElementsOfOfflineTaskPopoverInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_50946_VerifyTheElementsOfOfflineTaskPopoverInTheTaskJournal {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String taskName = "AutoTest_React_OfflineTask WithMark";
  private final LocalDate deadlineDate = LocalDate.of(2023, 12, 30);
  private final double averageMarkOfOfflineTask = 7.5;
  private final int specificWeight = 1;

  private ReactOfflineTaskInfoPopUpScreen reactOfflineTaskInfoPopUpScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupOfflineTaskInTaskJournal() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility()
        .clickMyGroupsTab();
    reactOfflineTaskInfoPopUpScreen = new ReactTrainingService()
        .openGroupJournalByName(groupName)
        .clickTaskJournalTab()
        .waitGroupJournalTableForVisibility()
        .clickAllPeriodButton()
        .clickOfflineTaskInTaskJournalByName(taskName);
  }

  @Test
  public void checkTaskNameOfOfflineTask() {
    assertEquals(reactOfflineTaskInfoPopUpScreen.getOfflineTaskName(), taskName,
        "'Task name' on the 'Offline task info pop up' is incorrect!");
  }

  @Test
  public void checkButtonSectionInOfflineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isEditButtonDisplayed(),
        "'Edit' button on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isDeleteButtonDisplayed(),
        "'Delete' button on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkDateFieldInOfflineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isDateTitleDisplayed(),
        "'Date' title on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertEquals(reactOfflineTaskInfoPopUpScreen.getDateValue(), deadlineDate,
        "'Date' value on the 'Offline task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkAverageMarkFieldInOfflineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isAverageMarkTitleDisplayed(),
        "'Average mark' title on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertEquals(reactOfflineTaskInfoPopUpScreen.getAverageMarkValue(),
        averageMarkOfOfflineTask,
        "'Average mark' value on the 'Offline task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkSpecificWeightFieldInOfflineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isSpecificWeightTitleDisplayed(),
        "'Specific weight' title on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertEquals(reactOfflineTaskInfoPopUpScreen.getSpecificWeightValue(),
        specificWeight,
        "'Specific weight' value on the 'Offline task info pop up' is incorrect!");
    softAssert.assertAll();
  }

  @Test
  public void checkTaskDescriptionFieldInOfflineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isTaskDescriptionTitleDisplayed(),
        "'Task description' title on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertFalse(reactOfflineTaskInfoPopUpScreen.getTaskDescriptionValue().isEmpty(),
        "'Task description' field on the 'Offline task info pop up' is empty!");
    softAssert.assertAll();
  }

  @Test
  public void checkReviewerFieldInOfflineTaskInfoPopup() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isReviewerTitleDisplayed(),
        "'Reviewer' title on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertTrue(reactOfflineTaskInfoPopUpScreen.isReviewerIconDisplayed(),
        "'Reviewer' icon on the 'Offline task info pop up' isn't displayed!");
    softAssert.assertAll();
  }
}
