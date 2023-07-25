package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;

import com.epmrdpt.bo.Student;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import com.epmrdpt.utils.DoubleUtils;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_53949_VerifyThatBasicDataCorrespondsSelectedForValueOfTheDesiredGroupInTheGraduateReport",
    groups = {"full", "react", "regression"})
public class EPMRDPT_53949_VerifyThatBasicDataCorrespondsSelectedForValueOfTheDesiredGroupInTheGraduateReport {

  private final String groupName = "GroupForStatisticsAutomationTest";
  private List<Student> studentFromGroupJournalTabList;
  private List<Student> studentFromTaskJournalTabList;
  private List<Student> studentFromGraduateReportTabList;
  private SoftAssert softAssert;
  private final int coefficientForConversionToPercentageOfMaxGradeTen = 10;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenGroupJournalGetData() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreenGroupsTab(asTrainer());
    studentFromGroupJournalTabList = new ReactTrainingService()
        .openGroupJournalByNameAndChooseAllPeriod(groupName)
        .getStudentsDataList();
    studentFromTaskJournalTabList = new ReactGroupJournalScreen()
        .clickTaskJournalTab()
        .waitTableHeaderForVisibility()
        .clickAllPeriodButton()
        .waitForLoadingSpinnerInvisibility()
        .getStudentsDataList();
    studentFromGraduateReportTabList = new ReactGroupJournalScreen().
        clickGraduateReportTab()
        .waitSectionTabsVisibility()
        .getStudentsDataList();
  }

  @Test(priority = 1)
  public void verifyThatBasicDataFromGroupJournalTabCorrespondsInTheGraduateReportTab() {
    softAssert = new SoftAssert();
    for (int i = 0; i < studentFromGraduateReportTabList.size(); i++) {
      softAssert.assertEquals(studentFromGroupJournalTabList.get(i).getNumber(),
          studentFromGraduateReportTabList.get(i).getNumber(),
          "Student 'numbers' from 'GroupJournalTab' is not corresponds from 'GraduateReportTab'");
      softAssert.assertEquals(studentFromGroupJournalTabList.get(i).getName(),
          studentFromGraduateReportTabList.get(i).getName(),
          "Student 'names' from 'GroupJournalTab' is not corresponds from 'GraduateReportTab'");
      softAssert.assertEquals(studentFromGroupJournalTabList.get(i).getAbsence(),
          getAbsenceFromAttendance(studentFromGraduateReportTabList.get(i)),
          "Student 'attendance' from 'GroupJournalTab' is not corresponds "
              + "from Student 'absence' from 'GraduateReportTab'");
      softAssert.assertEquals(studentFromGroupJournalTabList.get(i).getAverageMark(),
          studentFromGraduateReportTabList.get(i).getAverageMark(),
          "Student 'average marks' from 'GroupJournalTab' is not corresponds from 'GraduateReportTab'");
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyThatBasicDataFromTaskJournalTabCorrespondsInTheGraduateReportTab() {
    softAssert = new SoftAssert();
    for (int i = 0; i < studentFromGraduateReportTabList.size(); i++) {
      softAssert.assertEquals(studentFromTaskJournalTabList.get(i).getNumber(),
          studentFromGraduateReportTabList.get(i).getNumber(),
          "Students 'numbers' from 'TaskJournalTab' is not corresponds from 'GraduateReportTab'");
      softAssert.assertEquals(studentFromTaskJournalTabList.get(i).getName(),
          studentFromGraduateReportTabList.get(i).getName(),
          "Students 'names' from 'TaskJournalTab' is not corresponds from 'GraduateReportTab'");
      softAssert.assertEquals(
          getAverageTaskMarkFromTaskMarkList(studentFromTaskJournalTabList.get(i)),
          studentFromGraduateReportTabList.get(i).getAverageTaskMark(),
          "Students 'average task marks' from 'TaskJournalTab' is not corresponds "
              + "from 'GraduateReportTab'");
    }
    softAssert.assertAll();
  }

  private double getAverageTaskMarkFromTaskMarkList(Student student) {
    return DoubleUtils.roundToOneDecimalPlaceDouble(student
        .getTaskMarkList()
        .stream()
        .mapToDouble(mark -> mark * coefficientForConversionToPercentageOfMaxGradeTen).sum()
        / student.getTaskMarkList().size());
  }

  private int getAbsenceFromAttendance(Student student) {
    return student.getAttendance().get(1) - student.getAttendance().get(0);
  }
}
