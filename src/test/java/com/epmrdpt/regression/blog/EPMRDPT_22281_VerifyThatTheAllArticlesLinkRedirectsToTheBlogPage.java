package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Random;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22281_VerifyThatTheAllArticlesLinkRedirectsToTheBlogPage",
    groups = {"full", "regression"})
public class EPMRDPT_22281_VerifyThatTheAllArticlesLinkRedirectsToTheBlogPage {

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    assertTrue(new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink().isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkArticleDescriptionPageIsOpened() {
    BlogScreen blogScreen = new BlogScreen();
    int randomArticleIndex = new Random().nextInt(blogScreen.getArticleTitleListSize());
    assertTrue(blogScreen.clickArticleTitleByIndex(randomArticleIndex).isScreenLoaded(),
        "Blog description page isn't loaded!");
  }

  @Test(priority = 3)
  public void checkBlogPageIsOpenedAfterClickingOnAllArticles() {
    assertTrue(new BlogDescriptionScreen().clickAllArticlesLink().isScreenLoaded(),
        "Blog screen isn't opened by clicking all articles from blog description page!");
  }
}
