package com.epmrdpt.smoke.homeblog;

import com.epmrdpt.screens.BlogBlockOnStartPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14663_VerifyThatArticleCardHasAllRequiredElementsOnTheHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14663_VerifyThatArticleCardHasAllRequiredElementsOnTheHomePage {

  private final String ERROR_MESSAGE = "Article with hashtag by %s index doesn't have %s";
  private BlogBlockOnStartPage blogBlockOnStartPage;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogBlockOnStartPage = new BlogBlockOnStartPage();
    blogBlockOnStartPage.waitScreenLoading();
  }

  @Test
  public void checkAllArticlesWithHashTagHasAllRequiredElementsOnTheHomePage() {
    SoftAssert softAssert = new SoftAssert();
    int articleCardWithHashTagCount = blogBlockOnStartPage
        .getArticlesWithHashTagWithShortTitleQuantity();
    for (int articlesCardIndex = 0;
        articlesCardIndex < articleCardWithHashTagCount;
        articlesCardIndex++) {
      blogBlockOnStartPage.clickRightArrowButtonIfIsNotDisplayedArticleCard(articlesCardIndex);
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasPreviewImage(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Preview image"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasHashTag(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Hash tag"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasHashTagText(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Hash tag text"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasCategory(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Category"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasDate(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Date"));
      softAssert.assertFalse(blogBlockOnStartPage
              .getViewCounterIconOnArticleCardByIndexValue(articlesCardIndex).isEmpty(),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "View counter icon"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasViewCounter(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "View counter number"));
      softAssert.assertFalse(blogBlockOnStartPage
              .getLikeCounterIconOnArticleCardByIndexValue(articlesCardIndex).isEmpty(),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Like counter icon"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasLikeCounter(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Like counter number"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasTitle(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Title link"));
      softAssert.assertTrue(blogBlockOnStartPage
              .isArticleCardWithHashTagAndShortTitleByIndexHasIntroduction(articlesCardIndex),
          String.format(ERROR_MESSAGE, articlesCardIndex + 1, "Article Introduction"));
    }
    softAssert.assertAll();
  }
}
