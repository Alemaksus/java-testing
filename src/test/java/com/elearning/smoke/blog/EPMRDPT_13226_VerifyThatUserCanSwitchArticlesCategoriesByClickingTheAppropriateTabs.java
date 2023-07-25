package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.BlogService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13226_VerifyThatUserCanSwitchArticlesCategoriesByClickingTheAppropriateTabs",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13226_VerifyThatUserCanSwitchArticlesCategoriesByClickingTheAppropriateTabs {

  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkBlogTitleHasCorrectText() {
    assertEquals(blogScreen.getBlogPageTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_BLOG_TITTLE),
        "Blog tittle has incorrect text!");
  }

  @Test(priority = 2)
  public void checkNewsTabHasCorrectText() {
    assertEquals(blogScreen.getFromNewsTabText(),
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_NEWS_TAB),
        "News tab has incorrect text!");
  }

  @Test(priority = 2)
  public void checkRealStoriesTabHasCorrectText() {
    assertEquals(blogScreen.getRealStoriesTabText(),
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_STORIES_TAB),
        "Real stories has incorrect text!");
  }

  @Test(priority = 2)
  public void checkMaterialsTabHasCorrectText() {
    assertTrue(blogScreen.getMaterialsTabText().contentEquals(
            LocaleProperties.getValueOf(LocalePropertyConst.BLOG_MATERIALS_TAB)),
        "Materials tab has incorrect text!");
  }

  @Test(priority = 2)
  public void checkHardSkillsTabHasCorrectText() {
    assertEquals(blogScreen.getHardSkillsTabText(),
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_HARD_SKILLS_TAB),
        "Hard skills has incorrect text!");
  }

  @Test(priority = 2)
  public void checkSoftSkillsTabHasCorrectText() {
    assertEquals(blogScreen.getSoftSkillsTabText(),
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_SOFT_SKILLS_TAB),
        "Soft skills has incorrect text!");
  }

  @Test(priority = 2)
  public void checkEventsTabHasCorrectText() {
    assertEquals(blogScreen.getEventsTabText(),
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_EVENTS_TAB),
        "Events tab has incorrect text!");
  }

  @Test(priority = 3)
  public void checkHighlightedChosenTab() {
    new BlogService().clickAnyBlogTab()
        .waitPreviewImageDisplayed();
    assertFalse(blogScreen.getBlogPageTitleColor()
            .contentEquals(blogScreen.getActiveBlogTabColor()),
        "Color active tab didn't change!");
  }

  @Test(priority = 4)
  public void checkContentOfChosenTab() {
    assertTrue(blogScreen.isAllCardsHasRequiredCategory(blogScreen.getActiveBlogTabCategory()),
        "Not all articles cards belong chosen tab!");
  }
}
