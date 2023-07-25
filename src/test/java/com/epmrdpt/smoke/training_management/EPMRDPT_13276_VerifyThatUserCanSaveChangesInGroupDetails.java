package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13276_VerifyThatUserCanSaveChangesInGroupDetails",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13276_VerifyThatUserCanSaveChangesInGroupDetails {

  private final String TRAINING_NAME = "AutoTest Planned Status";
  private final String GROUP_NAME_OLD = "Automated Testing 1";
  private final String GROUP_NAME_NEW = "Automated Testing 10";
  private final LocalDate START_DATE = LocalDate.now();
  private final LocalDate END_DATE = LocalDate.now().plusMonths(3);
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactTrainingManagementService reactTrainingManagementService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupDetailsScreen = new ReactGroupDetailsScreen();
    reactTrainingManagementService = new ReactTrainingManagementService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkThatTrainingOpen() {
    assertTrue(reactTrainingManagementService
            .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME).isScreenLoaded(),
        format("Training '%s' isn't opened!", TRAINING_NAME));
  }

  @Test(priority = 2)
  public void checkThatManagerCanReachGroupTab() {
    assertTrue(reactTrainingManagementService.searchReactGroupByNameAndRedirectOnIt(
            GROUP_NAME_OLD).isScreenLoaded(),
        format("Group %s details page isn't displayed!", GROUP_NAME_OLD));
  }

  @Test(priority = 3)
  public void checkThatSavedSuccessfullyPopUpDisplayed() {
    assertTrue(
        reactTrainingManagementService.setReactGroupNameAndDates(GROUP_NAME_NEW, START_DATE,
                END_DATE)
            .isResultOfChangeMessageDisplayed(),
        "Save change successfully pop up isn't displayed!");
  }

  @Test(priority = 4)
  public void checkThatChangesSavedAfterRefreshedPage() {
    reactGroupDetailsScreen.clickOnResultOfChangeMessageCross();
    reactTrainingManagementService.searchReactGroupByNameAndRedirectOnIt(GROUP_NAME_NEW);
    assertEquals(GROUP_NAME_NEW, reactGroupDetailsScreen.getGroupNameText(),
        "Group name isn't changed!");
  }

  @Test(priority = 5)
  public void checkThatChangesCanComeBack() {
    reactTrainingManagementService
        .setReactGroupName(GROUP_NAME_OLD)
        .clickOnResultOfChangeMessageCross();
    reactTrainingManagementService.searchReactGroupByNameAndRedirectOnIt(GROUP_NAME_OLD);
    assertEquals(GROUP_NAME_OLD, reactGroupDetailsScreen.getGroupNameText(),
        "Group name isn't changed!");
  }
}
