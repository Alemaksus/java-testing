package com.epmrdpt.smoke.training_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13055_VerifyTrainerManagerCanViewStudentsDetails",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13055_VerifyTrainerManagerCanViewStudentsDetails {

  private final static String TRAINING_NAME = "AutoTest_RegistrationOpenStatus";
  private final static String GROUP_NAME = "Automated Testing 3";
  private static String firstStudentName;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13055_VerifyTrainerManagerCanViewStudentsDetails(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
  }

  @Test(priority = 1)
  public void checkThatPopUpHintIsDisplayed() {
    new HeaderScreen().clickReactTrainingManagementLink().waitPreloadingPictureHidden();
    firstStudentName = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickGroupsTabs().clickGroupByName(GROUP_NAME)
        .waitDataLoading().waitAllSpinnersAreNotDisplayed()
        .getFirstStudentName();
    assertTrue(reactGroupDetailsScreen.clickOnFirstStudent()
            .isUsersPopUpHintAppeared(),
        "Students Pop-Up Hint isn't appeared!");
  }

  @Test(priority = 2)
  public void checkThatStudentNameInGroupSectionAndInStudentProfileAreEquals() {
    reactGroupDetailsScreen
        .clickOnUserNameInPopUpHint()
        .switchToLastWindow();
    assertEquals(firstStudentName, new ProfileScreen().getUserFullNameText(),
        "Student name in group section and in profile is different!");
  }
}
