package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22476__VerifyThatAfterClickingOnTheHardSkillsTabOnBlogPageTheSectionUploadSeveralArticleCards",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22476_VerifyThatAfterClickingOnTheHardSkillsTabOnBlogPageTheSectionUploadSeveralArticleCards {

  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkSomeArticlesCardsCreated() {
    assertTrue(blogScreen.getSizeArticleCardsList() > 0, "Article cards not created!");
  }

  @Test(priority = 3)
  public void checkAfterClickNewsTabArticlesByNewsTypeAreUploaded() {
    assertTrue(blogScreen
            .clickTabByCategoryName(LocaleProperties.getValueOf(LocalePropertyConst.BLOG_STORIES_TAB))
            .waitPreviewImageDisplayed()
            .clickTabByCategoryName(
                LocaleProperties.getValueOf(LocalePropertyConst.BLOG_HARD_SKILLS_TAB))
            .isAllCardsHasRequiredCategory(LocaleProperties
                .getValueOf(LocalePropertyConst.BLOG_HARD_SKILLS_TAB)),
        "After clicking on 'HARD SKILLS' tab article cards aren't have 'Hard skills' category!");
  }
}
