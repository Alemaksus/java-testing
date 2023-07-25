package com.epmrdpt.regression.training;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactGraduateReportScreen;
import com.epmrdpt.screens.ReactStudentCardPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_79518_VerifyTrainerCanSeeStudentCardInGraduateReportTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_79518_VerifyTrainerCanSeeStudentCardInGraduateReportTab {

  private String studentName = "StudentForTask StudentForTask";
  private String groupName = "AutoTest_GroupWithEverydayClasses";
  private ReactGraduateReportScreen reactGraduateReportScreen;
  private ProfileScreen profileScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79518_VerifyTrainerCanSeeStudentCardInGraduateReportTab(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenGraduateReport() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .clickMyGroupsTab();
    new ReactTrainingService().openGraduateReportByGroupName(groupName)
        .waitAllSpinnersAreNotDisplayed();
    reactGraduateReportScreen = new ReactGraduateReportScreen();
  }

  @Test(priority = 1)
  public void checkStudentCardDisplayedAfterClickingOnStudentName() {
    assertTrue(reactGraduateReportScreen
            .clickStudentByName(studentName)
            .isScreenLoaded(),
        "'Student Card' pop up isn't displayed!");
  }

  @Test(priority = 2)
  public void checkStudentProfileDisplayedAfterClickingOnStudentPhoto() {
    profileScreen = new ProfileScreen();
    new ReactStudentCardPopUpScreen().clickOnStudentPhoto();
    profileScreen.switchToLastWindow();
    assertEquals(profileScreen.getUserFullNameText(), studentName,
        format("Profile screen '%s' isn't loaded!", studentName));
  }
}