package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.BlogScreenAfterSearchHashTagScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.BlogService;
import com.epmrdpt.utils.RandomUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22284_VerifyThatArticlesSectionContainsAllNecessaryElements",
    groups = {"full", "regression"})
public class EPMRDPT_22284_VerifyThatArticlesSectionContainsAllNecessaryElements {

  private static final int EXPECTED_NUMBER_OF_OTHER_ARTICLES = 3;
  private BlogScreen blogScreen;
  private BlogDescriptionScreen blogDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    assertTrue(blogScreen.isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkRandomArticleScreenIsOpened() {
    BlogScreenAfterSearchHashTagScreen blogScreenAfterSearchHashTagScreen = new BlogService()
        .clickRandomHashTag();
    int numberOfArticlesOnScreen = blogScreenAfterSearchHashTagScreen.waitUntilPageLoaded()
        .getNewsCardsCount();
    blogDescriptionScreen = blogScreenAfterSearchHashTagScreen.waitUntilPageLoaded()
        .clickOnNewsCardByIndex(RandomUtils.getRandomNumber(numberOfArticlesOnScreen));
    assertTrue(blogDescriptionScreen.isScreenLoaded(), "Blog description page isn't loaded!");
  }

  @Test(priority = 3)
  public void checkArticleDescriptionIsDisplayed() {
    assertTrue(blogDescriptionScreen.isArticleDescriptionDisplayed(),
        "Article description isn't displayed!");
  }

  @Test(priority = 4)
  public void checkArticleTitleIsDisplayed() {
    assertTrue(blogDescriptionScreen.isArticleTitleDisplayed(), "Article title isn't displayed!");
  }

  @Test(priority = 5)
  public void checkArticleLabelIsDisplayed() {
    assertTrue(blogDescriptionScreen.isCategoryLabelDisplayed(),
        "Article's label isn't displayed!");
  }

  @Test(priority = 6)
  public void checkHashTagIsDisplayedAtTheBeginningAndInTheEndOfScreen() {
    assertTrue(blogDescriptionScreen.areAllHashtagsElementsDisplayed(),
        "Hashtags aren't displayed at the beginning and in the end of screen!");
  }

  @Test(priority = 7)
  public void checkIfPublicationDateIsDisplayed() {
    assertTrue(blogDescriptionScreen.areAllPublicationDateElementsDisplayed(),
        "Publication date isn't displayed!");
  }

  @Test(priority = 8)
  public void checkIfViewsAreDisplayed() {
    assertTrue(blogDescriptionScreen.areAllViewIconElementsDisplayed(), "Views aren't displayed!");
  }

  @Test(priority = 9)
  public void checkIfLikesAreDisplayed() {
    assertTrue(blogDescriptionScreen.areAllLikeIconElementsDisplayed(), "Likes aren't displayed!");
  }

  @Test(priority = 10)
  public void checkIfOtherArticlesLinkIsDisplayed() {
    assertTrue(blogDescriptionScreen.getOtherArticlesLabelText().length() > 0,
        "Other articles title isn't displayed!");
  }

  @Test(priority = 11)
  public void checkIfAllOtherArticlesLinksAreDisplayed() {
    assertEquals(blogDescriptionScreen.getAllOtherArticleTitles().size(),
        EXPECTED_NUMBER_OF_OTHER_ARTICLES,
        "Number of other articles doesn't equal 3!");
  }

  @Test(priority = 12)
  public void checkIfOtherArticlesLinksAreUnderlinedWhenHover() {
    assertTrue(blogDescriptionScreen.isOtherArticlesLinkHasUnderlineWhileHovered(),
        "Other articles links don't have underline while hovered");
  }

  @Test(priority = 13)
  public void checkIfAllArticlesLinkIsDisplayed() {
    assertTrue(blogDescriptionScreen.isAllArticlesLinkDisplayed(),
        "All articles link isn't displayed!");
  }

  @Test(priority = 14)
  public void checkIfAllArticlesLinkIsUnderlinedWhenHover() {
    assertTrue(blogDescriptionScreen.isAllArticlesLinkHasUnderlineWhileHovered(),
        "All articles link isn't underlined when hover!");
  }
}
