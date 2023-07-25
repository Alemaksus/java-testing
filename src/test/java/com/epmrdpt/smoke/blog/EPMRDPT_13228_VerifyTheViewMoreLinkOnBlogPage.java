package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.BlogService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13228_VerifyTheViewMoreLinkOnBlogPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13228_VerifyTheViewMoreLinkOnBlogPage {

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
  public void checkCountCreatedArticlesCardsEqualsMinimalPermissible() {
    assertTrue(blogScreen.getSizeArticleCardsList() > 0, "Less than 1 created cards!");
  }

  @Test(priority = 3)
  public void checkViewMoreLinkDisplayed() {
    assertTrue(blogScreen.isViewMoreLinkDisplayed(), "View more link isn't displayed!");
  }

  @Test(priority = 4)
  public void checkHighlightedViewMoreLinkWhenHoverOver() {
    blogScreen.scrollToBottom();
    assertFalse(blogScreen.getViewMoreLinkColor()
            .contentEquals(blogScreen.getHoveredViewMoreLinkColor()),
        "Color view more link didn't change!");
  }

  @Test(priority = 5)
  public void checkAfterUploadingAllArticlesViewMoreLinkIsNotDisplayed() {
    assertFalse(new BlogService().loadAllNewsArticleCards().isViewMoreLinkDisplayed(),
        "View more link displayed after uploading all of the articles!");
  }
}
