package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.BlogService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14672_VerifyFirstPhotoUploadedInSliderOfArticleIsDisplayedAsPreviewImageOnHomepage",
    groups = {"full", "regression"})
public class EPMRDPT_14672_VerifyFirstPhotoUploadedInSliderOfArticleIsDisplayedAsPreviewImageOnHomepage {

  private BlogScreen blogScreen;
  private String articleTitle = "AutoTest_Abstract Painting";

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
  public void checkBlogScreenArticleLoadedAndFirstImageDisplayedAsPreviewImage() {
    SoftAssert softAssert = new SoftAssert();
    BlogDescriptionScreen blogDescriptionScreen = new BlogDescriptionScreen();
    String previewImageOnHomeScreen = new BlogService().searchForArticleByText(articleTitle)
        .getArticlePreviewImageSrc();
    blogScreen.waitArticleWithSeveralImagesVisibility().clickArticleWithSeveralImages();
    softAssert.assertTrue(blogDescriptionScreen.isScreenLoaded(),
        "Blog screen article is not loaded!");
    softAssert.assertTrue(blogDescriptionScreen.isArticleFirstImageInSliderDisplayed(),
        "First image in article is not displayed!");
    softAssert.assertEquals(previewImageOnHomeScreen,
        blogDescriptionScreen.getArticleFirstImageInSliderSrcAttribute(),
        "Preview image on home screen and first image in slider of article, do not match!");
    softAssert.assertAll();
  }
}
