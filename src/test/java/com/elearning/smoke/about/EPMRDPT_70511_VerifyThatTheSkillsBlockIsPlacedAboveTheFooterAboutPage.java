package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_70511_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterAboutPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_70511_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterAboutPage {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't load!");
  }

  @Test(priority = 2)
  public void checkOurSkillsSectionIsDisplayed() {
    assertTrue(aboutScreen.isOurSkillsSectionIsDisplayed(),
        "Our Skills section isn't displayed!");
  }

  @Test(priority = 3)
  public void checkSkillsBlockAboveFooter() {
    assertTrue(aboutScreen.isSkillsBlockAboveFooterDisplayed(),
        "Element 'Skills Block' is not above element 'Footer'!");
  }

  @Test(priority = 4)
  public void checkSkillCardLogoDisplayed() {
    assertTrue(
        aboutScreen.isAllSkillCardIconsAndSkillNameDisplayed(),
        "Not all Skill Card's logos and Skill Name are displayed!");
  }
}
