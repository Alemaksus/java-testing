package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_21930_VerifyThatArticleWithoutHashTagInTheListHasAllRequiredElements",
    groups = {"full", "regression"})
public class EPMRDPT_21930_VerifyThatArticleWithoutHashTagInTheListHasAllRequiredElements {

  private static final String ERROR_MESSAGE = "Article without hashtag  by %s index"
      + " doesn't have %s";
  private BlogScreen blogScreen;
  private int articleCardWithoutHashTagCount;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new BlogScreen();
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed().clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(),
        "Blog page didn't load!");
  }

  @Test(priority = 2)
  public void checkSeveralArticlesWithoutHashTagsAreCreated() {
    assertNotEquals(blogScreen.getArticleCardsWithoutHashTagCount(), 0,
        "Articles without hashtag aren't created!");
  }

  @Test(priority = 3)
  public void checkAllArticlesWithoutHashTagHaveRequireParts() {
    SoftAssert softAssert = new SoftAssert();
    articleCardWithoutHashTagCount = blogScreen.getArticlesWithoutHashTagWithShortTitleQuantity();
    for (int articlesCardIndex = 0;
        articlesCardIndex < articleCardWithoutHashTagCount;
        articlesCardIndex++) {
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithoutHashTagAndShortTitleByIndexHasPreviewImage(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "preview image"));
      softAssert.assertTrue(
          blogScreen.isArticleCardWithoutHashTagAndShortTitleByIndexHasCategory(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "category"));
      softAssert.assertTrue(
          blogScreen.isArticleCardWithoutHashTagShortTimeByIndexHasDate(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "date"));
      softAssert.assertFalse(
          blogScreen
              .isArticleCardWithoutHashTagAndShortTimeByIndexHasViewCounterIcon(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "view counter icon"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithoutHashTagAndShortTitleByIndexHasViewCounter(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "view counter number"));
      softAssert.assertFalse(
          blogScreen
              .isArticleCardWithoutHashTagAndShortTitleByIndexHasLikeCounterIcon(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "like counter icon"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithoutHashTagAndShortTitleByIndexHasLikeCounter(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "like counter number"));
      softAssert.assertTrue(
          blogScreen.isArticleCardWithoutHashTagAndShortTitleByIndexHasTitle(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "title"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithoutHashTagAndShowTitleByIndexHasDescription(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "description"));
    }
    softAssert.assertAll();
  }
}
