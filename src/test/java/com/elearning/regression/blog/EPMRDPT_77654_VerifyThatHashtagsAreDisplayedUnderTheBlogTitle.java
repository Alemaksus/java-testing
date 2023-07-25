package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77654_VerifyThatHashtagsAreDisplayedUnderTheBlogTitle",
    groups = {"full", "general", "regression"})
public class EPMRDPT_77654_VerifyThatHashtagsAreDisplayedUnderTheBlogTitle {

  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void initializeScreen() {
    blogScreen = new BlogScreen();
  }

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed().clickBlogNavigationLink();
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen is not opened!");
  }

  @Test(priority = 2)
  public void checkHashtagsAreDisplayedUnderBlog() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(blogScreen.isBlogTitleDisplayed(),
        "'Blog' is not displayed!");
    softAssert.assertTrue(blogScreen.isHashTagsTextDisplayed(),
        "Hash Tag isn't Displayed");
    softAssert.assertTrue(blogScreen.isHashTagsTextUnderBlog(),
        "Element 'HashTags' is not under element 'Blog'");
    softAssert.assertAll();
  }
}
