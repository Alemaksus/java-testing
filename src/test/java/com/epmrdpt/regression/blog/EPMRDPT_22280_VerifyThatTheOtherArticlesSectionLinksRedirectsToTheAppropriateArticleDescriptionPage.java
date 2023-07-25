package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22280_VerifyThatTheOtherArticlesSectionLinksRedirectsToTheAppropriateArticleDescriptionPage",
    groups = {"full", "regression"})
public class EPMRDPT_22280_VerifyThatTheOtherArticlesSectionLinksRedirectsToTheAppropriateArticleDescriptionPage {

  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new BlogScreen();
  }

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    assertTrue(new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink().isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkArticleDescriptionPageIsOpened() {
    int randomArticleIndex = new Random().nextInt(blogScreen.getArticleTitleListSize());
    assertTrue(blogScreen.clickArticleTitleByIndex(randomArticleIndex).isScreenLoaded(),
        "Article description page isn't opened!");
  }

  @Test(priority = 3)
  public void checkSelectedArticleDescriptionPageIsOpened() {
    BlogDescriptionScreen blogDescriptionScreen = new BlogDescriptionScreen();
    int randomOtherArticleIndex = new Random()
        .nextInt(blogDescriptionScreen.getOtherArticlesLinkSize());
    String randomArticleChosenText = blogDescriptionScreen
        .getOtherArticlesLinkTextByIndex(randomOtherArticleIndex);
    assertEquals(blogDescriptionScreen.clickOtherArticleLinkByIndex(randomOtherArticleIndex)
            .getArticleTitleOnDescriptionPageText(), randomArticleChosenText,
        String.format(
            "Article description page isn't opened for chosen %s article from other articles section!",
            randomArticleChosenText));
  }
}
