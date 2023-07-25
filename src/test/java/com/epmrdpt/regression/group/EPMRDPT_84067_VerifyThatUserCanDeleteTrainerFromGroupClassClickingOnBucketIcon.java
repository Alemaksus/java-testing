package com.epmrdpt.regression.group;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84067_VerifyThatUserCanDeleteTrainerFromGroupClassClickingOnBucketIcon",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84067_VerifyThatUserCanDeleteTrainerFromGroupClassClickingOnBucketIcon {

  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactGroupScreen reactGroupScreen;
  private static final String TRAINING_NAME = "AutoTest_TestForAddingGroupAndDeleteIt";
  private final String trainerName = "AutoTrainer AutoTrainer";
  private boolean isGroupReadyToDeletion = false;
  private String groupNameToDeleting;
  private final User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84067_VerifyThatUserCanDeleteTrainerFromGroupClassClickingOnBucketIcon(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void createNewGroupAndAddTrainer() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactGroupScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickGroupsTabs()
        .waitDataLoading();
    int groupCountBeforeAddingNewGroup = reactGroupScreen.getGroupNameListSize();
    reactGroupScreen
        .clickAddGroupButton()
        .waitNumberGroupNameToBeMore(groupCountBeforeAddingNewGroup);
    int groupCountAfterAddingNewGroup = reactGroupScreen.getGroupNameListSize();
    int indexToDeleteGroupAfterAddingNewGroup = groupCountAfterAddingNewGroup - 1;
    groupNameToDeleting = reactGroupScreen
        .getGroupTitleText()
        .get(indexToDeleteGroupAfterAddingNewGroup);
    reactGroupDetailsScreen = reactGroupScreen
        .clickGroupByName(groupNameToDeleting)
        .typeAddTrainerInput(trainerName)
        .clickResultFromSearchByName(trainerName)
        .clickAddTrainerButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickDeleteIconByTrainerName(trainerName);
  }

  @Test(priority = 1)
  public void checkThatPopUpAppearsAfterClickingOnTheBucketIcon() {
    assertTrue(
        reactGroupDetailsScreen.isStatusChangePopUpDisplayed(),
        "Pop up window doesn't appears after clicking delete trainer!");
  }

  @Test(priority = 2)
  public void checkThatTrainerWasDeleted() {
    isGroupReadyToDeletion = true;
    reactGroupDetailsScreen
        .clickDeleteButtonInPopUp()
        .clickRefreshButton();
    assertFalse(
        reactGroupDetailsScreen
            .waitGroupNameTextToBePresent()
            .isTrainerByNameDisplayed(trainerName),
        "Trainer wasn't deleted!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteTestGroup() {
    if (isGroupReadyToDeletion) {
      reactGroupDetailsScreen
          .clickToGroupsListLink()
          .waitDataLoading()
          .clickDeleteIconByGroupName(groupNameToDeleting)
          .clickRemoveConfirmButtonButton();
    }
  }
}
