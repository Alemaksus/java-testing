package com.epmrdpt.smoke.homeblog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogBlockOnStartPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14655_VerifyTheBlogSectionOfHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14655_VerifyTheBlogSectionOfHomePage {

  private BlogBlockOnStartPage blogBlockOnStartPage;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogBlockOnStartPage = new BlogBlockOnStartPage();
  }

  @Test
  public void checkBlogTittleIsDisplayed() {
    assertTrue(blogBlockOnStartPage.isBlogTittleDisplayed(), "Blog tittle not found!");
  }

  @Test
  public void checkNewsLinkDisplayed() {
    assertTrue(blogBlockOnStartPage.isNewsLinkDisplayed(), "News link not found!");
  }

  @Test
  public void checkStoriesLinkDisplayed() {
    assertTrue(blogBlockOnStartPage.isStoriesLinkDisplayed(), "Stories link not found!");
  }

  @Test
  public void checkMaterialsLinkDisplayed() {
    assertTrue(blogBlockOnStartPage.isMaterialsLinkDisplayed(), "Materials link not found!");
  }

  @Test
  public void checkHardSkillsLinkDisplayed() {
    assertTrue(blogBlockOnStartPage.isHardSkillsLinkDisplayed(), "Hard skills link not found!");
  }

  @Test
  public void checkSoftSkillsLinkDisplayed() {
    assertTrue(blogBlockOnStartPage.isSoftSkillsLinkDisplayed(), "Soft skills link not found!");
  }

  @Test
  public void checkArticlesCardsListDisplayed() {
    assertTrue(blogBlockOnStartPage.isArticlesCardsListDisplayed(),
        "Articles cards list not found!");
  }

  @Test
  public void checkAllArticlesLinkDisplayed() {
    assertTrue(blogBlockOnStartPage.isAllArticlesLinkDisplayed(), "All articles link not found!");
  }
}
