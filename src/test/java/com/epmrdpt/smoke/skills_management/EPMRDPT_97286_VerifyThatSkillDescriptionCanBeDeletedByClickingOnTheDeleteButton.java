package com.epmrdpt.smoke.skills_management;

import static com.epmrdpt.bo.user.UserFactory.asSuperAdmin;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactSkillsManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import java.lang.reflect.Method;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97286_VerifyThatSkillDescriptionCanBeDeletedByClickingOnTheDeleteButton",
    groups = {"full", "smoke"})
public class EPMRDPT_97286_VerifyThatSkillDescriptionCanBeDeletedByClickingOnTheDeleteButton {

  private static final String SKILL_NAME = "RPA";
  private static final String SKILL_TITLE = "Skill title";
  private static final String SKILL_DESCRIPTION = "Description";
  private static final String SKILL_NUMBER = "55";
  private static final String SKILL_SHORT_DESCRIPTION = "Short description";

  private ReactSkillsManagementScreen reactSkillsManagementScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void openSkillsManagementAsSuperAdmin() {
    reactSkillsManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asSuperAdmin())
        .clickManagementDropDownMenu()
        .clickReactSkillsManagementLink()
        .waitScreenLoading();
  }

  @Test
  public void checkThatDeleteSkillDescriptionPopUpOpened() {
    reactSkillsManagementScreen.clickDeleteButtonBySkillName(SKILL_NAME)
        .waitPopUpWindowVisibility();
    assertTrue(reactSkillsManagementScreen.isPopUpWindowDisplayed(),
        "Delete skill description pop-up is not open!");
  }

  @Test
  public void checkThatSkillDescriptionDeleted() {
    List<String> skillNames = reactSkillsManagementScreen.clickPopUpOkButton()
        .waitAllSpinnersAreNotDisplayed()
        .getSkillNames();
    assertFalse(skillNames.contains(SKILL_NAME), "Skill description is not deleted!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void createNewSkill(Method method) {
    if ("checkThatSkillDescriptionDeleted".equals(method.getName())) {
      reactSkillsManagementScreen.clickCreateSkillDescriptionButton()
          .clickSkillDropDown()
          .chooseSkillByName(SKILL_NAME)
          .typeTitle(SKILL_TITLE)
          .typeNumber(SKILL_NUMBER)
          .typeShortDescription(SKILL_SHORT_DESCRIPTION)
          .typeDescription(SKILL_DESCRIPTION)
          .clickCreateButton()
          .waitPopUpIsDisplayed();
      new ReactLoginService().signOut();
    }
  }
}
