package com.epmrdpt.smoke.home;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_70471_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_70471_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterHomePage {

  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkUnregisteredUserHasAccessToHomePage() {
    assertTrue(headerScreen.checkUsersRedirectionToHomePage(),
        "Unregistered user hasn't access to 'Home' page!");
  }

  @Test(priority = 2)
  public void checkOurSkillsSectionIsDisplayed() {
    assertTrue(headerScreen.isOurSkillsSectionDisplayed(),
        "Our Skills section isn't displayed!");
  }

  @Test(priority = 3)
  public void checkSkillsBlockAboveFooter() {
    assertTrue(headerScreen.isSkillsBlockAboveFooterDisplayed(),
        "Element 'Skills Block' is not above element 'Footer'!");
  }

  @Test(priority = 4)
  public void checkSkillCardLogoDisplayed() {
    assertTrue(
        headerScreen.isAllSkillCardIconsAndSkillNameDisplayed(),
        "Not all Skill Card's logos and Skill Name are displayed!");
  }
}
