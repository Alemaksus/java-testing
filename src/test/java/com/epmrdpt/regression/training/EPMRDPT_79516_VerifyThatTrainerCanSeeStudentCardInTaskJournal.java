package com.epmrdpt.regression.training;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactGroupJournalScreen;
import com.epmrdpt.screens.ReactStudentCardPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_79516_VerifyThatTrainerCanSeeStudentCardInTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_79516_VerifyThatTrainerCanSeeStudentCardInTaskJournal {

  private String studentName = "StudentForTask StudentForTask";
  private String groupName = "AutoTest_GroupWithEverydayClasses";
  private ReactGroupJournalScreen reactGroupJournalScreen;
  private ProfileScreen profileScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79516_VerifyThatTrainerCanSeeStudentCardInTaskJournal(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    profileScreen = new ProfileScreen();
    reactGroupJournalScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .clickMyGroupsTab()
        .typeGroupNameInput(groupName)
        .clickSearchIcon()
        .clickGroupByName(groupName)
        .waitForLoadingSpinnerInvisibility();
  }

  @Test(priority = 1)
  public void checkStudentCardDisplayedAfterClickingOnStudentName() {
    assertTrue(reactGroupJournalScreen
            .clickStudentByName(studentName)
            .isScreenLoaded(),
        "'Student Card' pop up isn't displayed!");
  }

  @Test(priority = 2)
  public void checkStudentProfileDisplayedAfterClickingOnStudentPhoto() {
    new ReactStudentCardPopUpScreen().clickOnStudentPhoto();
    new ProfileScreen().switchToLastWindow();
    assertEquals(profileScreen.getUserFullNameText(), studentName,
        format("Profile screen '%s' isn't loaded!", studentName));
  }
}
