package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14539_VerifyThatAfterClickingOnTheNewsTabOnBlogPageTheSectionUploadSeveralArticleCards",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14539_VerifyThatAfterClickingOnTheNewsTabOnBlogPageTheSectionUploadSeveralArticleCards {

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
            .clickTabByCategoryName(LocaleProperties.getValueOf(LocalePropertyConst.BLOG_EVENTS_TAB))
            .waitPreviewImageDisplayed()
            .clickTabByCategoryName(LocaleProperties.getValueOf(LocalePropertyConst.BLOG_NEWS_TAB))
            .isAllCardsHasRequiredCategory(LocaleProperties
                .getValueOf(LocalePropertyConst.BLOG_NEWS_TAB)),
        "After clicking on 'News' tab article cards aren't have 'News' category!");
  }
}
