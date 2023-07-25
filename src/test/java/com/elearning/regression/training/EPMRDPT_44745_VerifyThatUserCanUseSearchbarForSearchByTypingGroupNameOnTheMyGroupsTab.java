package com.epmrdpt.regression.training;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44745_VerifyThatUserCanUseSearchbarForSearchByTypingGroupNameOnTheMyGroupsTab",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44745_VerifyThatUserCanUseSearchbarForSearchByTypingGroupNameOnTheMyGroupsTab {

  private final String firstGroupName = "AutoTest_GroupWithEverydayClasses";
  private final String secondGroupName = "AutoTest_GroupWithDeletingClassTicket";

  private User user;
  private ReactTrainingScreen reactTrainingScreen;
  private ReactTrainingService reactTrainingService;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44745_VerifyThatUserCanUseSearchbarForSearchByTypingGroupNameOnTheMyGroupsTab(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactGroupsTabInTrainingScreenInitialization() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreenGroupsTab(user)
        .waitGroupsTableFieldForVisible();
    reactTrainingService = new ReactTrainingService();
  }

  @Test(priority = 1)
  public void checkThatInputFieldIsActivatedAfterClick() {
    assertTrue(reactTrainingScreen
            .clickMyGroupsSearchInput()
            .isMyGroupsSearchFieldActivated(),
        "Input field is not activated after click!");
  }

  @Test(priority = 2)
  public void checkThatFirstGroupNameIsDisplayedInTheInput() {
    reactTrainingService.uncheckOnlyMyGroupCheckBox()
        .waitPageNumbersForVisibility();
    assertEquals(reactTrainingScreen
            .typeGroupNameInput(firstGroupName)
            .getMyGroupsSearchInputText(),
        firstGroupName,
        "Group name in the input field is wrong!");
  }

  @Test(priority = 3)
  public void checkThatGroupNameIsDisplayedInTheResultAfterClickSearchIcon() {
    assertEquals(reactTrainingScreen
            .clickSearchIcon()
            .waitPageNumbersForDisappear()
            .getGroupNameButtonText(),
        firstGroupName,
        "Expected group name doesn't displayed in the results after click search icon!");
  }

  @Test(priority = 4)
  public void checkThatSecondGroupNameIsDisplayedInTheInput() {
    reactTrainingService.uncheckOnlyMyGroupCheckBox();
    assertEquals(reactTrainingScreen
            .clickCancelButton()
            .waitForNumberOfSelectedTrainingsToBeMoreThanOne()
            .typeGroupNameInput(secondGroupName)
            .getMyGroupsSearchInputText(),
        secondGroupName,
        "Group name in the input field is wrong!");
  }

  @Test(priority = 5)
  public void checkThatGroupNameIsDisplayedInTheResultAfterClickEnterKeyboard() {
    reactTrainingService.uncheckOnlyMyGroupCheckBox();
    reactTrainingService.selectTrainingByUsingEnterKeyboard(secondGroupName);
    assertEquals(reactTrainingScreen.getGroupNameButtonText(),
        secondGroupName,
        "Expected group name doesn't displayed in the results after click Enter keyboard!");
  }
}
