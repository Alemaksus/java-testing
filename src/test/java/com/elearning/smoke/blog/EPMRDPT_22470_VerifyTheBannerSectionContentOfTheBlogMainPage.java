package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22470_VerifyTheBannerSectionContentOfTheBlogMainPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22470_VerifyTheBannerSectionContentOfTheBlogMainPage {

  private final int STARTING_INDEX = 0;
  private final int MAXIMUM_INDEX = 62;
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
  public void checkBannerSectionBlogScreenContainsText() {
    if (blogScreen.getArticleTitleText().length() > MAXIMUM_INDEX) {
      assertTrue(
          (blogScreen.getArticleTitleText()
              .contains(blogScreen.getBannerText().substring(STARTING_INDEX, MAXIMUM_INDEX))),
          "Titles from banner and the last article don't equal!");
    } else {
      assertEquals(
          blogScreen.getBannerText(),
          blogScreen.getArticleTitleText(),
          "'Banner' section of blog page doesn't contain correct text!");
    }
  }

  @Test(priority = 2)
  public void checkReadButtonHasCorrectText() {
    assertEquals(
        blogScreen.getReadButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.BLOG_READ_BUTTON_TEXT),
        "Button from 'banner' section does't contain correct text");
  }
}
