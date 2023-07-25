package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13225_VerifyReadButtonOnBlogPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13225_VerifyReadButtonOnBlogPage {

  private BlogScreen blogScreen;
  private final int STARTING_INDEX = 0;
  private final int MAXIMUM_INDEX = 62;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen doesn't load!");
  }


  @Test(priority = 3)
  public void checkAfterClickReadButtonUploadingLastPublishedArticle() {
    if (blogScreen.getArticleTitleText().length() > MAXIMUM_INDEX) {
      String bannerText = blogScreen.getBannerText().substring(STARTING_INDEX, MAXIMUM_INDEX);
      assertTrue((blogScreen.clickReadButton()
              .waitArticleTitleIsNotEmpty()
              .getArticleTitleOnDescriptionPageText()
              .contains(bannerText)),
          "After clicking on 'Read' button "
              + "portal doesn't redirect to the latest published article page!");
    } else {
      assertTrue(blogScreen.getBannerText().contentEquals(blogScreen.clickReadButton()
          .waitArticleTitleIsNotEmpty()
          .getArticleTitleOnDescriptionPageText()), "After clicking on 'Read' button "
          + "portal doesn't redirect to the latest published article page!");
    }
  }
}
