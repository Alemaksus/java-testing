package com.epmrdpt.smoke.faq;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FAQScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_70510_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterFaqPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_70510_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterFaqPage {

  private FAQScreen faqScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    faqScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickFAQNavigationLink();
  }

  @Test(priority = 1)
  public void checkFAQScreenLoading() {
    assertTrue(faqScreen.isScreenLoaded(), "FAQ screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkOurSkillsSectionIsDisplayed() {
    assertTrue(faqScreen.isOurSkillsSectionDisplayed(),
        "Our Skills section isn't displayed!");
  }

  @Test(priority = 3)
  public void checkSkillsBlockAboveFooter() {
    assertTrue(faqScreen.isSkillsBlockAboveFooterDisplayed(),
        "Element 'Skills Block' is not above element 'Footer'!");
  }

  @Test(priority = 4)
  public void checkSkillCardLogoDisplayed() {
    assertTrue(
        faqScreen.isAllSkillCardIconsAndSkillNameDisplayed(),
        "Not all Skill Card's logos and Skill Name are displayed!");
  }
}
