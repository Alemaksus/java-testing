package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.utils.DoubleUtils.roundToTwoDecimalPlaceDouble;

import com.epmrdpt.bo.Student;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTaskJournalService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_54102_VerifyTheCalculationOfTheAverageMarkValueInTheCellInAverageMarkColumnInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_54102_VerifyTheCalculationOfTheAverageMarkValueInTheCellInAverageMarkColumnInTheTaskJournal {

  private final String groupName = "For_54102_test";
  private ReactTasksJournalScreen reactTasksJournalScreen;
  private List<String> taskName;
  private List<Student> students;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUpTestData() {
    reactTasksJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .clickMyGroupsTab()
        .typeGroupNameInput(groupName)
        .clickSearchIcon()
        .clickGroupNameButton()
        .clickTaskJournalTab()
        .clickAllPeriodButton();
    taskName = new ArrayList<>(Arrays.asList(
        "OffLineFor_54102",
        "2OffLineFor_54102",
        "OnlineForAutoTest_54102",
        "2OnlineForAutoTest_54102"));
    students = reactTasksJournalScreen.getStudentsDataList();
  }

  @Test
  public void checkAverageMarksForStudents() {
    softAssert = new SoftAssert();
    students.forEach(student -> softAssert.assertEquals(
        roundToTwoDecimalPlaceDouble(new ReactTaskJournalService()
            .countAverageMarkForStudent(student)),
        student.getAverageMark(),
        String.format("Average mark for '%s' count incorrectly!", student.getName())));
    softAssert.assertAll();
  }

  @Test
  public void checkAverageMarksDependsOnTaskWeightForStudents() {
    Map<String, Integer> tasksMarkWeight = reactTasksJournalScreen.getTasksMarkWeight(taskName);
    softAssert = new SoftAssert();
    students.forEach(student -> softAssert.assertEquals(
        roundToTwoDecimalPlaceDouble(new ReactTaskJournalService()
            .countAverageMarkDependsOnTaskWeightForStudent(student, tasksMarkWeight)),
        student.getAverageMarkDependsOnTaskWeight(),
        String.format("Average mark depends on task weight for '%s' count incorrectly!",
            student.getName())));
    softAssert.assertAll();
  }
}
