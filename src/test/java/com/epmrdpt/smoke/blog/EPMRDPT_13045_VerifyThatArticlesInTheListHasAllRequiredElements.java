package com.epmrdpt.smoke.blog;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13045_VerifyThatArticlesInTheListHasAllRequiredElements",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13045_VerifyThatArticlesInTheListHasAllRequiredElements {

  private static final String ERROR_MESSAGE = "Article with hashtag  by %s index"
      + " doesn't have %s";
  private BlogScreen blogScreen;
  private int articleCardWithHashTagCount;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new BlogScreen();
    new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink()
        .isScreenLoaded();
  }

  @Test
  public void checkAllArticlesWithHashTagHasAllRequiredParts() {
    SoftAssert softAssert = new SoftAssert();
    articleCardWithHashTagCount = blogScreen.getArticlesWithHashTagWithShortTitleQuantity();
    for (int articlesCardIndex = 0;
        articlesCardIndex < articleCardWithHashTagCount;
        articlesCardIndex++) {
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithHashTagAndShortTitleByIndexHasPreviewImage(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Preview image"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithHashTagAndShortTitleByIndexHasHashTag(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Hash tag"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithHashTagAndShortTitleByIndexHasHashTagText(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Hash tag text"));
      softAssert.assertTrue(
          blogScreen.isArticleCardWithHashTagAndShortTitleByIndexHasCategory(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Category"));
      softAssert.assertTrue(
          blogScreen.isArticleCardWithHashTagShortTimeByIndexHasDate(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Date"));
      softAssert.assertFalse(
          blogScreen
              .isArticleCardWithHashTagAndShortTimeByIndexHasViewCounterIcon(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "View counter icon"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithHashTagAndShortTitleByIndexHasViewCounter(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "View counter number"));
      softAssert.assertFalse(
          blogScreen
              .isArticleCardWithHashTagAndShortTitleByIndexHasLikeCounterIcon(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Like counter icon"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithHashTagAndShortTitleByIndexHasLikeCounter(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Like counter number"));
      softAssert.assertTrue(
          blogScreen.isArticleCardWithHashTagAndShortTitleByIndexHasTitle(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Title link"));
      softAssert.assertTrue(
          blogScreen
              .isArticleCardWithHashTagAndShowTitleByIndexHasDescription(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex, "Description"));
    }
    softAssert.assertAll();
  }
}
