package com.epmrdpt.regression.center_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34248_VerifyThatUserCanSelectSeveralSkillOnCreateEditTrainingCenterPage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34248_VerifyThatUserCanSelectSeveralSkillOnCreateEditTrainingCenterPage {

  private String firstSkill = "Java";
  private String secondSkill = ".NET";
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34248_VerifyThatUserCanSelectSeveralSkillOnCreateEditTrainingCenterPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenterManagementScreen() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
    createOrEditTrainingCenterScreen = new HeaderScreen()
        .clickCenterManagementLink()
        .clickCreateTrainingCenterButton();
  }

  @Test(priority = 1)
  public void checkThatDDLWithAvailableSkillsAppears() {
    assertTrue(createOrEditTrainingCenterScreen
            .clickSelectSkillsField()
            .isSelectSkillDDlFieldDisplayed(),
        "DLL With available skills isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatSkillFromDLLIsSelected() {
    assertEquals(
        createOrEditTrainingCenterScreen.clickSelectSkillsField()
            .selectSkillFromDDLByName(firstSkill).getSelectedSkillsText(), firstSkill,
        "Skill isn't selected!");
  }

  @Test(priority = 3)
  public void checkThatSelectedSkillIsAbsentInDLL() {
    assertFalse(createOrEditTrainingCenterScreen
            .clickSelectSkillsField().isSelectedSkillDisplayed(firstSkill),
        "Selected skill is displayed in DDL!");
  }

  @Test(priority = 4)
  public void checkThatAnotherSkillFromDLLIsSelected() {
    String anotherSkill = createOrEditTrainingCenterScreen.clickSelectSkillsField()
        .selectSkillFromDDLByName(secondSkill)
        .getSkillsFieldText();
    assertTrue(anotherSkill.equals(firstSkill + "\n" + secondSkill) || anotherSkill
        .equals(secondSkill + "\n" + firstSkill), "Another skill isn't selected!");
  }
}
