package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.FeedbackScreen;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13227_VerifyTheContentBlogPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13227_VerifyTheContentBlogPage {

  private HeaderScreen headerScreen;
  private BlogScreen blogScreen;
  private final int MAXIMUM_INDEX = 62;
  private final int STARTING_INDEX = 0;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupBlogPage() {
    blogScreen = new BlogScreen();
    headerScreen = new HeaderScreen();
    headerScreen.waitLinksToRedirectOnOtherSectionsDisplayed().clickBlogNavigationLink();
  }

  @Test
  public void checkHeaderDisplayedOnBlogPage() {
    assertTrue(headerScreen.isEpamLogoDisplayed(),
        "Header isn't displayed!");
  }

  @Test
  public void checkHomeLinkDisplayedOnBlogPage() {
    assertTrue(blogScreen.isHomeLinkDisplayed(),
        "Home link isn't displayed!");
  }

  @Test
  public void checkReadButtonDisplayedOnBlogPage() {
    assertTrue(blogScreen.isReadButtonDisplayed(),
        "'Read' button isn't displayed!");
  }

  @Test
  public void checkBannerDisplayedOnBlogPage() {
    assertTrue(blogScreen.isBannerDisplayed(),
        "Banner isn't displayed!");
  }

  @Test
  public void checkBlogTitleDisplayedOnBlogPage() {
    assertTrue(blogScreen.isBlogTitleDisplayed(),
        "Blog title isn't displayed!");
  }

  @Test
  public void checkViewMoreLinkDisplayedOnBlogPage() {
    assertTrue(blogScreen.isViewMoreLinkDisplayed(),
        "View more link isn't displayed!");
  }

  @Test
  public void checkFeedbackDisplayedOnBlogPage() {
    assertTrue(new FeedbackScreen().isFeedbackButtonDisplayed(),
        "Feedback isn't displayed!");
  }

  @Test
  public void checkFooterDisplayedOnBlogPage() {
    assertTrue(new FooterScreen().isCopyrightSignDisplayed(),
        "Footer isn't displayed!");
  }

  @Test
  public void checkBlogTitleEqualsLastArticleTitle() {
    if (blogScreen.getArticleTitleText().length() > MAXIMUM_INDEX) {
      assertTrue(
          (blogScreen.getArticleTitleText()
              .contains(blogScreen.getBannerText().substring(STARTING_INDEX, MAXIMUM_INDEX))),
          "Titles from banner and the last article don't equal!");
    } else {
      assertTrue(blogScreen.getBannerText().contentEquals(blogScreen.getArticleTitleText()),
          "Titles from banner and the last article don't equal!");
    }
  }
}
