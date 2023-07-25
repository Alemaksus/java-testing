package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.BlogScreenAfterSearchHashTagScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.List;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13005_VerifyThatAllListOfHashTagsWithShowLessAppearsAfterClickingOnShowMoreLink",
    groups = {"full", "regression"})
public class EPMRDPT_13005_VerifyThatAllListOfHashTagsWithShowLessAppearsAfterClickingOnShowMoreLink {

  private BlogScreen blogScreen;
  private BlogScreenAfterSearchHashTagScreen blogScreenAfterSearchHashTagScreen;

  private int hashTagListSize;
  private List<String> hashTagList;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
    hashTagList = blogScreen.getHashTagList();
    hashTagListSize = hashTagList.size();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkHashTagRelatedArticlesSectionLoading() {
    int randomArticleIndex = new Random().nextInt(hashTagListSize);
    String hashTag = hashTagList.get(randomArticleIndex);
    blogScreenAfterSearchHashTagScreen = blogScreen.clickHashTagByText(hashTag);
    assertTrue(blogScreenAfterSearchHashTagScreen.isScreenLoaded(),
        "Hash tag related articles section isn't loaded!");
  }

  @Test(priority = 3)
  public void checkListOfHashTagsAndShowLessLinkDisplayed() {
    softAssert = new SoftAssert();
    blogScreenAfterSearchHashTagScreen.clickShowMoreLink();
    for (String hashTag : hashTagList) {
      softAssert.assertTrue(
          blogScreenAfterSearchHashTagScreen.isHashTagLinkInHashTagListDisplayed(hashTag),
          "Hash tag:" + hashTag + " isn't displayed in the hash tag list section!");
    }
    softAssert.assertTrue(blogScreenAfterSearchHashTagScreen.isShowLessLinkDisplayed(),
        "'Show less' link isn't displayed!");
    softAssert.assertAll();
  }
}