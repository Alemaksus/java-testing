package com.epmrdpt.smoke.skills_management;

import static com.epmrdpt.utils.StringUtils.generateRandomPassword;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.EditSkillDescriptionScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97315_VerifyChangesAreSavedAfterClickingOnSaveChangesButton",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_97315_VerifyChangesAreSavedAfterClickingOnSaveChangesButton {

  private final String TITLE_NAME = generateRandomPassword();
  private EditSkillDescriptionScreen editSkillDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void signInProfileAndOpenSkillsManagementPage() {
    editSkillDescriptionScreen = new LoginService()
        .loginAndSetLanguage(UserFactory.asSuperAdmin())
        .clickManagementDropDownMenu()
        .clickReactSkillsManagementLink()
        .waitScreenLoading()
        .clickRandomSkillInSkillsList();
  }

  @Test
  public void makeChangeInOpenedSkillAndTryToSave() {
    editSkillDescriptionScreen.typeTitleField(TITLE_NAME)
        .clickSaveChangesButton();
    assertTrue(editSkillDescriptionScreen.isSuccessfulChangesPopUpDisplayed(),
        "Message about saving was not shown");
  }
}
