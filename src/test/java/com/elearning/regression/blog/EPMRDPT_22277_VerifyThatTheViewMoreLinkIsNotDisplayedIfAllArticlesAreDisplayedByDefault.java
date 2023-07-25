package com.epmrdpt.regression.blog;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.BLOG_MATERIALS_TAB;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22277_VerifyThatTheViewMoreLinkIsNotDisplayedIfAllArticlesAreDisplayedByDefault",
    groups = {"full", "regression"})
public class EPMRDPT_22277_VerifyThatTheViewMoreLinkIsNotDisplayedIfAllArticlesAreDisplayedByDefault {

  private final String tabNameMaterials = getValueOf(BLOG_MATERIALS_TAB);
  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkViewMoreLinkAbsent() {
    new BlogScreen().clickTabByCategoryName(tabNameMaterials);
    assertFalse(blogScreen.isViewMoreLinkDisplayed(), "View More link is present!");
  }
}
