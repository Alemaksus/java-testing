package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14674_VerifyArticleWithNoImageHasDefaultImageAsPreviewImageOnHomepage",
    groups = {"full", "regression"})
public class EPMRDPT_14674_VerifyArticleWithNoImageHasDefaultImageAsPreviewImageOnHomepage {

  private BlogScreen blogScreen;
  private static final String DEFAULT_IMAGE_SRC = "Content/images/default/news.jpg";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoaded() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen is not loaded!");
  }

  @Test(priority = 2)
  public void checkDefaultImageIsPreviewImageOfArticleWithNoImages() {
    SoftAssert softAssert = new SoftAssert();
    BlogDescriptionScreen blogDescriptionScreen = new BlogDescriptionScreen();
    while (blogScreen.isViewMoreLinkDisplayed()) {
      if (blogScreen.isArticleWithNoImageDisplayed()) {
        break;
      }
      blogScreen.clickViewMoreLink();
    }
    String previewImageOnHomeScreen = blogScreen.getNoImageArticlePreviewImageSrc();
    blogScreen
        .waitArticleWithNoImageVisibility()
        .clickArticleWithNoImage();
    softAssert.assertTrue(blogDescriptionScreen
            .isScreenLoaded(),
        "Blog screen article is not loaded!");
    softAssert.assertFalse(blogDescriptionScreen.isArticleFirstImageInSliderDisplayed(),
        "First image in article is displayed!");
    softAssert
        .assertTrue(previewImageOnHomeScreen.contains(DEFAULT_IMAGE_SRC),
            "Preview image of article on home screen and default image, do not match!");
    softAssert.assertAll();
  }
}
