package com.epmrdpt.smoke.group;

import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_DETAIL_NOTIFY_NO_STUDENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GROUP_DETAIL_NOTIFY_NO_TRAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_LEARNING;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.GroupDetailsScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13058_VerifyThatTrainingManagerCanSetLearningStatusToTheGroup",
    groups = {"full", "manager", "smoke", "critical_path", "deprecated"})
public class EPMRDPT_13058_VerifyThatTrainingManagerCanSetLearningStatusToTheGroup {

  private final String TRAINING_NAME = "AutoTest_RegistrationOpenStatus";
  private final String GROUP_NAME = "GroupWithStudentAndTrainerAndFormationStatus";
  private final String TRAINER_NAME = "TrainerForAdding";
  private final String STUDENT_NAME = "Student AutoTest";
  private GroupDetailsScreen groupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    groupDetailsScreen = new GroupDetailsScreen();
    reactTrainingManagementService = new ReactTrainingManagementService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkGroupDetailScreenLoading() {
    reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(TRAINING_NAME);
    assertTrue(new ReactDetailTrainingScreen()
        .clickGroupsTabs()
        .waitDataLoading()
        .clickGroupByName(GROUP_NAME)
        .isScreenLoaded(), "Group Detail screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainerNotPresent() {
    if (groupDetailsScreen.isTrainerByNameDisplayed(TRAINER_NAME)) {
      groupDetailsScreen
          .clickDeleteIconByTrainerName(TRAINER_NAME)
          .clickDeleteButtonInPopUp()
          .clickRefreshButton();
      groupDetailsScreen.waitScreenLoading();
    }
    assertFalse(groupDetailsScreen.isTrainersDisplayed(),
        "Trainer is present in the group!");
  }

  @Test(priority = 3)
  public void checkParticipantNotPresent() {
    if (groupDetailsScreen.isStudentInParticipantSectionDisplayed(STUDENT_NAME)) {
      groupDetailsScreen
          .clickDeleteIconByStudentName(STUDENT_NAME)
          .clickDeleteButtonInPopUp()
          .clickRefreshButton();
      groupDetailsScreen.waitScreenLoading();
    }
    assertFalse(groupDetailsScreen.isParticipantsDisplayed(),
        "Student is present in the group!");
  }

  @Test(priority = 4)
  public void checkCannotSetLearningStatusToGroupWithoutTrainer() {
    groupDetailsScreen.clickLearningButton().clickPopUpOkButton();
    assertEquals(groupDetailsScreen.waitResultOfChangeMessageDisplayed()
            .getResultOfChangeMessageText(),
        LocaleProperties.getValueOf(GROUP_DETAIL_NOTIFY_NO_TRAINER),
        "Message about the group hasn't trainer is incorrect!");
  }

  @Test(priority = 5)
  public void checkCannotSetLearningStatusToGroupWithoutStudent() {
    groupDetailsScreen.waitResultOfChangeMessageInvisibility();
    reactTrainingManagementService
        .addTrainerToGroup(TRAINER_NAME)
        .isTrainerByNameDisplayed(TRAINER_NAME);
    groupDetailsScreen.clickLearningButton().clickPopUpOkButton();
    assertEquals(groupDetailsScreen.waitResultOfChangeMessageDisplayed()
            .getResultOfChangeMessageText(),
        LocaleProperties.getValueOf(GROUP_DETAIL_NOTIFY_NO_STUDENTS),
        "Message about the group hasn't student is incorrect!");
  }

  @Test(priority = 6)
  public void checkCanSetLearningStatusToTheGroup() {
    groupDetailsScreen.waitResultOfChangeMessageInvisibility();
    reactTrainingManagementService
        .addStudentInGroup(STUDENT_NAME)
        .isStudentInParticipantSectionDisplayed(STUDENT_NAME);
    groupDetailsScreen.clickLearningButton().clickPopUpOkButton();
    String status = groupDetailsScreen.waitDataLoading().getCurrentStatus();
    status = status.substring(status.lastIndexOf(" ")).trim();
    groupDetailsScreen.clickFormationButton().clickPopUpOkButton().waitDataLoading();
    assertEquals(status, LocaleProperties.getValueOf(TRAINING_MANAGER_STATUS_LEARNING),
        "Current status isn't changed to 'Learning'!");
  }
}
