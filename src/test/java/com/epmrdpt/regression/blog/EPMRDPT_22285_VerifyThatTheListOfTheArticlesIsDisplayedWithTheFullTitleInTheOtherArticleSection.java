package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22285_VerifyThatTheListOfTheArticlesIsDisplayedWithTheFullTitleInTheOtherArticleSection",
    groups = {"full", "regression"})
public class EPMRDPT_22285_VerifyThatTheListOfTheArticlesIsDisplayedWithTheFullTitleInTheOtherArticleSection {

  private BlogScreen blogScreen;
  private static final int ARTICLES_IN_OTHER_ARTICLES_SECTION_COUNT = 3;
  private static final int ARTICLE_INDEX_TO_BE_OPENED = 3;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new BlogScreen();
  }

  @Test(priority = 1)
  public void checkBlogScreenIsOpened() {
    assertTrue(new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink().isScreenLoaded(), "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkMoreThanThreeArticlesAreCreated() {
    assertTrue(blogScreen.getArticleTitleListSize() > ARTICLES_IN_OTHER_ARTICLES_SECTION_COUNT,
        "More than three articles aren't created!");
  }

  @Test(priority = 3)
  public void checkArticlesTitleDisplayedInOtherArticleSection() {
    SoftAssert softAssert = new SoftAssert();
    Set<String> firstThreeArticleTitles = blogScreen.getArticleTitleTextList().stream()
        .limit(ARTICLES_IN_OTHER_ARTICLES_SECTION_COUNT).collect(Collectors.toSet());
    softAssert.assertEquals(
        blogScreen.clickArticleTitleByIndex(ARTICLE_INDEX_TO_BE_OPENED)
            .getOtherArticlesLinkSize(), ARTICLES_IN_OTHER_ARTICLES_SECTION_COUNT,
        "Three articles aren't present in other articles section!");
    softAssert.assertEquals(new HashSet<>(new BlogDescriptionScreen().getAllOtherArticleTitles()),
        firstThreeArticleTitles,
        "Recently created first three articles aren't displayed in other articles section!");
    softAssert.assertAll();
  }
}
