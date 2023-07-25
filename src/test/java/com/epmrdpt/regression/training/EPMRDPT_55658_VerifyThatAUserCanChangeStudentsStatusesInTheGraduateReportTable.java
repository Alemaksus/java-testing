package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_LAB_IN_PROGRESS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_TRAINING_IN_PROGRESS;

import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.services.ReactGraduateReportService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_55658_VerifyThatAUserCanChangeStudentsStatusesInTheGraduateReportTable",
    groups = {"full", "react", "regression"})
public class EPMRDPT_55658_VerifyThatAUserCanChangeStudentsStatusesInTheGraduateReportTable {

  private ReactGraduateReportScreen reactGraduateReportScreen;
  private final String groupName = "GroupForDeletingStudentFromGroup";
  private final String studentName = "Alena LastName233860";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenGraduateJournal() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer()).clickMyGroupsTab();
    new ReactHeaderScreen().waitTrainingTabForVisibility();
    reactGraduateReportScreen = new ReactTrainingService()
        .openTrainingJournalByGroupName(groupName)
        .clickGraduateReportTab()
        .waitSectionTabsVisibility()
        .waitAllSpinnersAreNotDisplayed();
    new ReactGraduateReportService()
        .moveScrollBarToTheEnd();
  }

  @Test
  public void checkThatTrainerCanChangeStudentsStatus() {
    SoftAssert softAssert = new SoftAssert();
    reactGraduateReportScreen.clickOnStudentStatus(studentName);
    softAssert.assertTrue(reactGraduateReportScreen.isStudentStatusPopoverDisplayed(),
        "Popover isn't displayed!");
    reactGraduateReportScreen
        .selectStatusLabInProgressInStudentStatusPopover()
        .waitUntilStudentsStatusChanged(studentName,
            getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_LAB_IN_PROGRESS));
    softAssert.assertEquals(reactGraduateReportScreen.getStudentStatusText(studentName), getValueOf(
            REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_LAB_IN_PROGRESS),
        "Student status isn't changed!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void returnStudentStatusBack() {
    reactGraduateReportScreen
        .clickOnStudentStatus(studentName)
        .selectStatusTrainingInProgressInStudentStatusPopover()
        .waitUntilStudentsStatusChanged(studentName,
            getValueOf(REACT_TRAINING_GRADUATE_JOURNAL_STUDENT_STATUS_TRAINING_IN_PROGRESS));
  }
}
