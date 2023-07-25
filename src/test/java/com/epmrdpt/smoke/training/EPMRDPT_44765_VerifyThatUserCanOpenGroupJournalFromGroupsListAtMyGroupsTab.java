package com.epmrdpt.smoke.training;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_44765_VerifyThatUserCanOpenGroupJournalFromGroupsListAtMyGroupsTab",
    groups = {"full", "react", "smoke"})
public class EPMRDPT_44765_VerifyThatUserCanOpenGroupJournalFromGroupsListAtMyGroupsTab {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String trainingName = "AutoTest_React";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void reactGroupsTabInTrainingScreenInitialization() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(UserFactory.asTrainer())
        .waitScheduleForVisibility()
        .clickMyGroupsTab()
        .waitGroupsTableFieldForVisible();
  }

  @Test
  public void checkThatGroupJournalPageOpensForExpectedGroup() {
    assertEquals(new ReactTrainingService().openGroupJournalByName(groupName)
            .waitTableHeaderForVisibility()
            .getTextFromBredCrumbs(),
        trainingName + "\n" + groupName, "Group journal page didn't open for the expected group!");
  }
}
