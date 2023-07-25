package com.epmrdpt.smoke.homeblog;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.BlogBlockOnStartPage;
import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14657_VerifyAllArticlesLinkInBlogSectionOnHomePageIsWorking",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14657_VerifyAllArticlesLinkInBlogSectionOnHomePageIsWorking {

  private BlogBlockOnStartPage blogBlockOnStartPage;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    new TrainingBlockScreen().waitTrainingCardsVisibility();
    blogBlockOnStartPage = new BlogBlockOnStartPage();
  }

  @Test(priority = 1)
  public void checkHighlightedAllArticlesLinkWhenHoverOver() {
    blogBlockOnStartPage.scrollToBottom();
    assertFalse(
        blogBlockOnStartPage.getAllArticlesLinkColor()
            .contentEquals(blogBlockOnStartPage.getHoveredAllArticlesLinkColor()),
        "Color all articles link didn't change!");
  }

  @Test(priority = 2)
  public void checkAllArticlesLinkRedirectsOnCorrectPage() {
    assertTrue(blogBlockOnStartPage
            .clickAllArticlesLink()
            .waitPreviewImageDisplayed()
            .getBlogPageTitleText()
            .contentEquals(LocaleProperties.getValueOf(LocalePropertyConst.BLOG_BLOG_TITTLE)),
        "Page doesn't correct!");
  }
}
