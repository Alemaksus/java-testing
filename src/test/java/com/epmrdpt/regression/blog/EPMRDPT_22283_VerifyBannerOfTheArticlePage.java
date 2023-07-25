package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Random;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22283_VerifyBannerOfTheArticlePage",
    groups = {"full", "regression"})
public class EPMRDPT_22283_VerifyBannerOfTheArticlePage {

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    assertTrue(new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink().isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkArticleDescriptionPageIsOpenedWithTitle() {
    SoftAssert softAssert = new SoftAssert();
    BlogScreen blogScreen = new BlogScreen();
    int randomArticleIndex = new Random().nextInt(blogScreen.getArticleTitleListSize());
    String articleNameToBeOpened = blogScreen.getArticleTitleTextByIndex(randomArticleIndex);
    softAssert.assertTrue(blogScreen.clickArticleTitleByIndex(randomArticleIndex).isScreenLoaded(),
        "Blog description page isn't loaded with title of article!");
    softAssert.assertEquals(new BlogDescriptionScreen().getArticleTitleOnDescriptionPageText(),
        articleNameToBeOpened, "Title on banner isn't same as chosen article title!");
    softAssert.assertAll();
  }
}
