package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.BlogScreenAfterSearchHashTagScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13003_VerifyTheContentOfTheBannerOfTheNewsHashtagPage",
    groups = {"full", "regression"})
public class EPMRDPT_13003_VerifyTheContentOfTheBannerOfTheNewsHashtagPage {

  private BlogScreen blogScreen;
  private BlogScreenAfterSearchHashTagScreen blogScreenAfterSearchHashTagScreen;
  private String randomHashTagText;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void initializeScreen() {
    blogScreen = new BlogScreen();
    blogScreenAfterSearchHashTagScreen = new BlogScreenAfterSearchHashTagScreen();
  }

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed().clickBlogNavigationLink();
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen is not opened!");
  }

  @Test(priority = 2)
  public void checkBlogScreenAfterSearchHashTagIsOpened() {
    List<String> hashTagsText = blogScreen.getHashTagList();
    randomHashTagText = hashTagsText.get(new Random().nextInt(hashTagsText.size()));
    blogScreen.clickHashTagByText(randomHashTagText);
    assertTrue(blogScreenAfterSearchHashTagScreen.isScreenLoaded(),
        "BlogScreen After Hashtag Search is not opened!");
  }

  @Test(priority = 3)
  public void checkTitleOfBlogScreenAfterHashTagPage() {
    assertEquals(
        blogScreenAfterSearchHashTagScreen.waitNewCardElementVisibility().getTitleHashTagText(),
        randomHashTagText, String
            .format("Title of BlogScreen After Hashtag Search is not same as chosen Hashtag - %s!",
                randomHashTagText));
  }

  @Test(priority = 4)
  public void checkTotalItemsOfHashTagScreen() {
    SoftAssert softAssert = new SoftAssert();
    String totalItemsTextOnBanner = blogScreenAfterSearchHashTagScreen.getTotalItemsText();
    int totalItems = Integer.parseInt(totalItemsTextOnBanner.split(": ")[1]);
    for (int newsCardCount = blogScreenAfterSearchHashTagScreen.getNewsCardsCount();
        newsCardCount < totalItems; ) {
      blogScreenAfterSearchHashTagScreen.clickViewMoreLink()
          .waitForNewsCardsToLoadAfterViewMoreClicked(newsCardCount);
      newsCardCount = blogScreenAfterSearchHashTagScreen.getNewsCardsCount();
    }
    softAssert.assertFalse(blogScreenAfterSearchHashTagScreen.isViewMoreLinkDisplayed());
    String totalItemsTextByNewsCardsCount = String.format("%s: %s",
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_AFTER_HASHTAG_SEARCH_TOTAL_ITEMS),
        blogScreenAfterSearchHashTagScreen.getNewsCardsCount());
    softAssert.assertEquals(totalItemsTextOnBanner, totalItemsTextByNewsCardsCount,
        String.format("%s is not displayed!", totalItemsTextByNewsCardsCount));
    softAssert.assertAll();
  }
}
