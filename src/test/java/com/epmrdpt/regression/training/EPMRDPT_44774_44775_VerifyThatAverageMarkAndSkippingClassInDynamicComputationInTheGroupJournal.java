package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.utils.RandomUtils.getRandomNumberInInterval;
import static org.testng.Assert.assertNotEquals;

import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44774_44775_VerifyThatAverageMarkAndSkippingClassInDynamicComputationInTheGroupJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44774_44775_VerifyThatAverageMarkAndSkippingClassInDynamicComputationInTheGroupJournal {

  private final String className = "AutoTest_EditClass";
  private final String studentName = "QQ AA";
  private double averageMarkBeforeChange;
  private int currentDateFirstIndex;
  private int studentRandomMark;

  private ReactGroupJournalScreen reactGroupJournalScreen;
  private ReactGroupJournalService reactGroupJournalService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupReactGroupJournalPage() {
    reactGroupJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitScheduleForVisibility()
        .clickGoToJournalButtonByClassName(className)
        .waitTableHeaderForVisibility();
    reactGroupJournalService = new ReactGroupJournalService();
    currentDateFirstIndex = reactGroupJournalScreen
        .getCurrentDateIndex();
    averageMarkBeforeChange = reactGroupJournalScreen
        .getStudentAverageMark(studentName);
    studentRandomMark = getRandomNumberInInterval(1, 10);
  }

  @Test(priority = 1)
  public void checkThatAverageMarkIsRecalculated() {
    reactGroupJournalService
        .addStudentMarkByNameAndDateIndex(studentRandomMark, studentName, currentDateFirstIndex)
        .waitMarkForVisibility(studentRandomMark, studentName, currentDateFirstIndex);
    assertNotEquals(reactGroupJournalScreen
            .clickHeaderAverageMarkColumnAndWaitValueVisibility(studentName)
            .getStudentAverageMark(studentName),
        averageMarkBeforeChange, "Average mark doesn't change!");
  }

  @Test(priority = 2)
  public void checkThatAbsenceCountIsRecalculated() {
    int absenceCountBeforeChange = reactGroupJournalScreen
        .getStudentAbsenceCount(studentName);
    reactGroupJournalService
        .addStudentAbsenceByNameAndDateIndex(studentName, currentDateFirstIndex)
        .waitAbsenceSymbolForVisibility(studentName, currentDateFirstIndex);
    assertNotEquals(reactGroupJournalScreen
            .clickHeaderAbsenceColumnAndWaitValueVisibility(studentName)
            .getStudentAbsenceCount(studentName),
        absenceCountBeforeChange, "Absence count doesn't change!");
  }
}
