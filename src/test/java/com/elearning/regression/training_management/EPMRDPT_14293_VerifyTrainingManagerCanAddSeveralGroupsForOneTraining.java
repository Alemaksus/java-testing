package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_STATUS_FORMATION;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14293_VerifyTrainingManagerCanAddSeveralGroupsForOneTraining",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_14293_VerifyTrainingManagerCanAddSeveralGroupsForOneTraining {

  private int maxGroupCount = 3;
  private String trainingName = "AutoTest_TestForAddingGroupAndDeleteIt";
  private ReactGroupScreen reactGroupScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupScreen = new ReactGroupScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndSetLanguage(asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkThatTrainingManagerCanAddSeveralGroupsForOneTraining() {
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(trainingName);
    new ReactDetailTrainingScreen().clickGroupsTabs();
    if (reactGroupScreen.isGroupNameDisplayed()) {
      int groupCount = reactGroupScreen.getGroupNameListSize();
      for (int groupIndex = (groupCount - 1); groupIndex >= 0; groupIndex--) {
        reactGroupScreen.clickDeleteButtonByIndex(groupIndex)
            .clickRemoveConfirmButtonButton()
            .waitUntilGroupNameByIndexWillBeDeleted(groupIndex);
      }
    }
    for (int countOfAddedGroup = 0; countOfAddedGroup < maxGroupCount; countOfAddedGroup++) {
      reactGroupScreen
          .clickAddGroupButton()
          .waitNumberGroupNameToBeMore(countOfAddedGroup);
    }
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactGroupScreen.getGroupNameListSize(), maxGroupCount,
        "Training Manager can't add several groups for one training!");
    reactGroupScreen.getCurrentStatusText()
        .forEach(currentStatus
            -> softAssert.assertEquals(currentStatus, getValueOf(TRAINING_MANAGER_STATUS_FORMATION),
            "Created groups haven't 'Formation' status!"));
    softAssert.assertAll();
  }
}
