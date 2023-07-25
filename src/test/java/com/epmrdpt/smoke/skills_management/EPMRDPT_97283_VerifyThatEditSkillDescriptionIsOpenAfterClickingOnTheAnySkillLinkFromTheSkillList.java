package com.epmrdpt.smoke.skills_management;

import static com.epmrdpt.bo.user.UserFactory.asSuperAdmin;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactSkillsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97283_VerifyThatEditSkillDescriptionIsOpenAfterClickingOnTheAnySkillLinkFromTheSkillList",
    groups = {"full", "smoke"})
public class EPMRDPT_97283_VerifyThatEditSkillDescriptionIsOpenAfterClickingOnTheAnySkillLinkFromTheSkillList {

  private ReactSkillsManagementScreen reactSkillsManagementScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    reactSkillsManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asSuperAdmin())
        .clickManagementDropDownMenu()
        .clickReactSkillsManagementLink()
        .waitScreenLoading();
  }

  @Test
  public void checkThatEditSkillDescriptionIsOpen() {
    assertTrue(reactSkillsManagementScreen.clickRandomSkillInSkillsList().isScreenLoaded(),
        "Edit skill description is not opened");
  }
}
