package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactGroupJournalService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_54098_VerifyThatAverageColumnContainsAverageMarkForStudentInTheGroupJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_54098_VerifyThatAverageColumnContainsAverageMarkForStudentInTheGroupJournal {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String studentName = "Student AutoTest";
  private ReactGroupJournalScreen reactGroupJournalScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupGroupJournalPage() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(groupName);
    new ReactGroupJournalService()
        .fillStudentRandomMarkIfRequired(studentName, RandomUtils.getRandomNumberInInterval(3, 6));
  }

  @Test
  public void checkAverageMarkForStudentInTheGroupJournal() {
    assertEquals(
        String.format("%.2f", reactGroupJournalScreen.calculateStudentAverageMark(studentName)),
        String.format("%.2f", reactGroupJournalScreen.getStudentAverageMark(studentName)));
  }
}
