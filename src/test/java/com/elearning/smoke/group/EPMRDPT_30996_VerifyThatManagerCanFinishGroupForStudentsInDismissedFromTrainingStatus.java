package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asRecruiter;
import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_SCREEN_FINISHED_CURRENT_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_TAB_APPLICATION_STATUS_DISMISSED_FROM_TRAINING;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.GroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_30996_VerifyThatTrainingManagerCanFinishGroupForStudentsInDismissedFromTrainingStatus",
    groups = {"full", "smoke", "manager", "deprecated"})
public class EPMRDPT_30996_VerifyThatManagerCanFinishGroupForStudentsInDismissedFromTrainingStatus {

  private User user;
  private String trainingName;
  private String groupName = "Group1";
  private String studentName = "QQ AA";
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactGroupScreen reactGroupScreen;

  @Factory(dataProvider = "Provider of training names and users")
  public EPMRDPT_30996_VerifyThatManagerCanFinishGroupForStudentsInDismissedFromTrainingStatus(
      User user, String trainingName) {
    this.user = user;
    this.trainingName = trainingName;
  }

  @DataProvider(name = "Provider of training names and users")
  public static Object[][] provideTabNames() {
    return new Object[][]{
        {asTrainingManager(), "AutoTest_1RegistrationOpen"},
        {asRecruiter(), "AutoTest_2RegistrationOpen"}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactGroupScreen = new ReactGroupScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickGroupsTabs()
        .waitDataLoading();
    if (reactGroupScreen
        .getGroupStatusByName(groupName)
        .equals(LocaleProperties.getValueOf(GROUP_SCREEN_FINISHED_CURRENT_STATUS))) {
      reactGroupScreen.clickGroupByName(groupName).waitDataLoading();
      reactTrainingManagementService.changeGroupStatusOnLearning();
    } else {
      reactGroupScreen.clickGroupByName(groupName).waitDataLoading();
    }
  }

  @Test
  public void checkManagerCanFinishedGroup() {
    GroupDetailsScreen groupDetailsScreen = new GroupDetailsScreen();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        groupDetailsScreen.isStudentInParticipantSectionDisplayed(studentName),
        "Student is absent!");
    softAssert.assertTrue(groupDetailsScreen.isTrainersDisplayed(), "Trainer is absent!");
    softAssert.assertEquals(
        groupDetailsScreen.getStudentStatusFromParticipantSection(studentName),
        LocaleProperties.getValueOf(PARTICIPANTS_TAB_APPLICATION_STATUS_DISMISSED_FROM_TRAINING),
        "Student has incorrect status!");
    reactTrainingManagementService.changeGroupStatusOnFinished();
    softAssert.assertTrue(
        groupDetailsScreen
            .getCurrentStatus()
            .contains(LocaleProperties.getValueOf(GROUP_SCREEN_FINISHED_CURRENT_STATUS)),
        "Group status 'Finished' is not set!");
    softAssert.assertAll();
  }
}
