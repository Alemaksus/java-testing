package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_54099_VerifyThatAbsenceCellContainsTheSumOfSkippingClassesForAStudentInTheGroupJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_54099_VerifyThatAbsenceCellContainsTheSumOfSkippingClassesForAStudentInTheGroupJournal {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private ReactGroupJournalScreen reactGroupJournalScreen;

  @DataProvider(name = "Provider of student names")
  public Object[][] studentNameProvider() {
    return new Object[][]{{"QQ AA"}, {"Student AutoTest"}, {"Studentka ReactTrainingPage"}};
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUpGroupJournal() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer()).clickMyGroupsTab()
        .clickMyGroupsTab();
    reactGroupJournalScreen = new ReactTrainingService()
        .openGroupJournalByName(groupName);
  }

  @Test(dataProvider = "Provider of student names")
  public void checkThatAbsenceCellContainsTheSumOfSkippingClasses(String studentName) {
    assertEquals(reactGroupJournalScreen.getStudentAbsenceCount(studentName),
        reactGroupJournalScreen.countStudentAbsentCells(studentName),
        "'ABSENCE' cell doesn't contain value equal to quantity of cells that has 'absent' mark");
  }
}
