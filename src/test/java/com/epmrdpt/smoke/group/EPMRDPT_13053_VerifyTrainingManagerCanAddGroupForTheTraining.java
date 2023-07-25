package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FORMATION;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13053_VerifyTrainingManagerCanAddGroupForTheTraining",
    groups = {"full", "manager", "smoke", "critical_path"})
public class EPMRDPT_13053_VerifyTrainingManagerCanAddGroupForTheTraining {


  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactGroupScreen reactGroupScreen;
  private int deltaBetweenGroupCountBeforeAndAfterAdding = 1;
  private int groupCountBeforeGroupAddingNewGroup = 0;
  private int groupCountAfterDeletedGroup = 0;
  private String trainingName = "AutoTest_TestForAddingGroupAndDeleteIt";

  @Test
  public void checkManagerCanAddGroupWithCorrectStatus() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactGroupScreen = new ReactGroupScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickGroupsTabs()
        .waitDataLoading();
    if (!reactGroupScreen.isGroupNameAbsent()) {
      groupCountBeforeGroupAddingNewGroup = reactGroupScreen.getGroupNameListSize();
    }
    reactGroupScreen
        .clickAddGroupButton()
        .waitNumberGroupNameToBeMore(groupCountBeforeGroupAddingNewGroup);
    int groupCountAfterGroupAddingNewGroup =
        groupCountBeforeGroupAddingNewGroup + deltaBetweenGroupCountBeforeAndAfterAdding;
    assertEquals(reactGroupScreen.getGroupNameListSize(), groupCountAfterGroupAddingNewGroup,
        "The group isn't added!");
    assertEquals(reactGroupScreen.getCurrentStatusByIndexText(groupCountBeforeGroupAddingNewGroup),
        getValueOf(TRAINING_MANAGER_STATUS_FORMATION),
        "The group isn't displayed in 'Formation' status!");
    reactTrainingManagementService.deleteGroupByIndex(groupCountBeforeGroupAddingNewGroup);
    if (reactGroupScreen.isGroupNameDisplayed()) {
      groupCountAfterDeletedGroup = reactGroupScreen.getGroupNameListSize();
    }
    assertEquals(groupCountAfterDeletedGroup, groupCountBeforeGroupAddingNewGroup,
        "The group isn't deleted!");
  }
}
