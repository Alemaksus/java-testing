package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_70509_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterBlogPage",
    groups = {"full", "general"})

public class EPMRDPT_70509_VerifyThatTheSkillsBlockIsPlacedAboveTheFooterBlogPage {

  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void initializeScreen() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen is not opened!");
  }

  @Test(priority = 2)
  public void checkSkillsBlockAboveFooter() {
    assertTrue(blogScreen.isSkillsBlockIsPlacedAboveFooter(),
        "Element 'Skills Block' is not above element 'Footer'!");
  }

  @Test(priority = 3)
  public void checkSkillCardElementsDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    int skillsListSize = blogScreen.getSkillCardCount();
    for (int i = 0; i < skillsListSize; i++) {
      softAssert.assertTrue(blogScreen.isSkillIconDisplayed(),
          "Skill card does not contain icon!");
      softAssert.assertTrue(blogScreen.isSkillNameDisplayed(), "Skill card does not contain text!");
    }
    softAssert.assertAll();
  }
}

