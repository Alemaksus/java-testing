package com.epmrdpt.smoke.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactClassService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84055_VerifyThatChangesAreSavedByClickingOnSaveChangesButton",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_84055_VerifyThatChangesAreSavedByClickingOnSaveChangesButton {

  private static final String TRAINING_NAME = "AutoTest_TestForAddingGroupAndDeleteIt";
  private static final String GROUP_NAME = String.format("Automated Group %s",
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
  private static final LocalDate START_DATE = LocalDate.now();
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactGroupScreen reactGroupScreen;
  private boolean isGroupReadyToDeletion = false;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void createNewGroupAndUpdateSomeFields() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactGroupScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickGroupsTabs()
        .waitDataLoading();
    int groupCountBeforeAddingNewGroup = reactGroupScreen.getGroupNameListSize();
    reactGroupScreen.clickAddGroupButton()
        .waitNumberGroupNameToBeMore(groupCountBeforeAddingNewGroup);
    reactGroupDetailsScreen =
        reactGroupScreen
            .clickGroupByIndex(reactGroupScreen.getGroupNameListSize() - 1);
    new ReactClassService().enterStartDate(START_DATE);
    reactGroupDetailsScreen.typeNewGroupName(GROUP_NAME)
        .clickSaveChangesButton();
  }

  @Test(priority = 1)
  public void checkThatSavedSuccessfullyPopUpDisplayed() {
    assertTrue(reactGroupDetailsScreen.isResultOfChangeMessageDisplayed(),
        "Save change successfully pop up isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatGroupNameDisplayed() {
    assertEquals(reactGroupDetailsScreen.getGroupNameText(), GROUP_NAME,
        "Saved group name is not correct!");
  }

  @Test(priority = 3)
  public void checkThatStartDateDisplayed() {
    isGroupReadyToDeletion = true;
    assertEquals(reactGroupDetailsScreen.getStartDateText(),
        START_DATE.format(DateTimeFormatter.ofPattern("dd.MM.YYYY")),
        "Saved start date is not correct!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteGroup() {
    if (isGroupReadyToDeletion) {
      reactGroupDetailsScreen
          .clickToGroupsListLink()
          .waitDataLoading()
          .clickDeleteIconByGroupName(GROUP_NAME)
          .clickRemoveConfirmButtonButton();
    }
  }
}
