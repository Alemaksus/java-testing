package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14538_VerifyAfterClickingOnTheViewMoreLinkOnBlogPageTheSectionUploadsYetSeveralArticlesCards",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14538_VerifyAfterClickingOnTheViewMoreLinkOnBlogPageTheSectionUploadsYetSeveralArticlesCards {

  private int minimalCountCreatedCards;
  private int tempCountCreatedCards;
  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkCountCreatedArticlesCardsEqualsMinimalPermissible() {
    minimalCountCreatedCards = obtainingTheMinimumCardsNumberFromTheMonitorSize();
    tempCountCreatedCards = blogScreen.getSizeArticleCardsList();
    assertTrue(tempCountCreatedCards >= minimalCountCreatedCards,
        String.format("Less than %s created cards!", minimalCountCreatedCards));
  }

  @Test(priority = 3)
  public void checkAfterClickingViewMoreLinkRemainingCardsDisplayed() {
    assertTrue(minimalCountCreatedCards < blogScreen.clickViewMoreLink()
            .waitArticleCardsListAfterClickViewMoreButton(tempCountCreatedCards)
            .getSizeArticleCardsList(),
        "After clicking on 'View More' link remaining article cards aren't loaded!");
  }

  private int obtainingTheMinimumCardsNumberFromTheMonitorSize() {
    double width = WebDriverSingleton.getInstance().getDriver().manage().window().getSize()
        .getWidth();
    if (width >= 1240 && width <= 1539) {
      minimalCountCreatedCards = 5;
    } else if (width > 1539) {
      minimalCountCreatedCards = 7;
    }
    return minimalCountCreatedCards;
  }
}
