package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.BlogScreenAfterSearchHashTagScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.BlogService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13004_VerifyTheContentOfNextSectionAfterClickingOnAnyHashtag",
    groups = {"full", "general", "regression"})
public class EPMRDPT_13004_VerifyTheContentOfNextSectionAfterClickingOnAnyHashtag {

  private BlogScreen blogScreen;
  private BlogScreenAfterSearchHashTagScreen blogScreenAfterSearchHashTagScreen;
  private String hashTag = "#JAVA";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkIfHashTagRelatedArticlesAreLoading() {
    blogScreenAfterSearchHashTagScreen = new BlogService().searchForHashTagByText(hashTag)
        .clickHashTagByText(hashTag);
    assertTrue(blogScreenAfterSearchHashTagScreen.isScreenLoaded(),
        "Hash tag related articles are not loaded!");
  }

  @Test(priority = 2)
  public void checkIfHashTagListAndLinksDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(blogScreenAfterSearchHashTagScreen.isHashTagListDisplayed(),
        "List of hash tags is not displayed!");
    softAssert.assertTrue(blogScreenAfterSearchHashTagScreen.isShowMoreLinkDisplayed(),
        "'Show more' link in hash tag related articles page is not displayed!");
    blogScreenAfterSearchHashTagScreen.clickShowMoreLink();
    softAssert.assertTrue(blogScreenAfterSearchHashTagScreen.isShowLessLinkDisplayed(),
        "'Show Less' link in hash tag related articles page is not displayed!");
    softAssert.assertTrue(blogScreenAfterSearchHashTagScreen.isViewMoreLinkDisplayed(),
        "'View More' link in hash tag related articles page is not displayed!");
    softAssert.assertAll();
  }
}

