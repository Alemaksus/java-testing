package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22469_VerifyThatAfterClickingOnTheHomeLinkOfTheBlogMainPageThePortalRedirectsToHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22469_VerifyThatAfterClickingOnTheHomeLinkOfTheBlogMainPageThePortalRedirectsToHomePage {

  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    blogScreen = new BlogScreen();
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed().clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog page didn't load!");
  }

  @Test(priority = 2)
  public void checkRedirectionOnHomePageAfterClickHomeLink() {
    assertFalse(blogScreen.clickHomeLink().getBannerText().isEmpty(),
        "Home link on blog page doesn't redirect on home page");
  }
}
