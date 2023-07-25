package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.BlogScreenAfterSearchHashTagScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.List;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13002_VerifyUserCanSearchAnArticleByHashTag",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13002_VerifyUserCanSearchAnArticleByHashTag {

  private int hashTagListSize;
  private String hashTag;
  private List<String> hashTagList;
  private BlogScreen blogScreen;
  private BlogScreenAfterSearchHashTagScreen blogScreenAfterSearchHashTagScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
    hashTagList = blogScreen.getHashTagList();
    hashTagListSize = hashTagList.size();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkArticlesWithHashTagCreated() {
    assertFalse(hashTagList.isEmpty(),
        "Articles with hashTag isn't created!");
  }

  @Test(priority = 3)
  public void checkThatUserIsRedirectedAfterClickOnHashTag() {
    int randomArticleIndex = new Random().nextInt(hashTagListSize);
    hashTag = hashTagList.get(randomArticleIndex);
    blogScreenAfterSearchHashTagScreen = blogScreen.clickHashTagByText(hashTag)
        .waitUntilPageLoaded();
    assertEquals(blogScreenAfterSearchHashTagScreen.waitTitleTextPresent().getTitleHashTagText(),
        hashTag,
        "Title doesn't contains " + hashTag);
  }

  @Test(priority = 4)
  public void checkAllNewsCardsAfterRedirectedContainsHashTag() {
    SoftAssert softAssert = new SoftAssert();
    blogScreenAfterSearchHashTagScreen.waitNewCardElementVisibility();
    for (int newsCardNumber = 0;
        newsCardNumber < blogScreenAfterSearchHashTagScreen.getNewsCardsCount(); newsCardNumber++) {
      boolean resultCheckingArticleByIndexHasHashTag = blogScreenAfterSearchHashTagScreen
          .isNewsCardByIndexHasHashTagOnHashTagScreen(hashTag, newsCardNumber);
      if (!resultCheckingArticleByIndexHasHashTag) {
        resultCheckingArticleByIndexHasHashTag = blogScreenAfterSearchHashTagScreen
            .clickArticleTittleByHashTag(
                hashTag).isNewsHasHashTagOnDescriptionScreen(hashTag);
        blogScreenAfterSearchHashTagScreen.clickBackButton();
      }
      softAssert.assertTrue(resultCheckingArticleByIndexHasHashTag,
          String.format("News card by %d index doesn't have %s hashTag", newsCardNumber + 1,
              hashTag));
    }
    softAssert.assertAll();
  }
}
