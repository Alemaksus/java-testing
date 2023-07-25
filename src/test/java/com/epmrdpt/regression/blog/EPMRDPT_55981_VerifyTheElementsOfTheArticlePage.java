package com.epmrdpt.regression.blog;

import static com.epmrdpt.framework.properties.LocalePropertyConst.BLOG_DESCRIPTION_PAGE_OTHER_ARTICLES_TITLE;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.BlogService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_55981_VerifyTheElementsOfTheArticlePage",
    groups = {"full", "regression", "deprecated"})
public class EPMRDPT_55981_VerifyTheElementsOfTheArticlePage {

  private BlogDescriptionScreen blogDescriptionScreen;
  private final String articleTitle = "AutoTest_Abstract Painting";
  private final String blueRgbColor = "rgba(118, 205, 216, 1)";
  private final int doubleNumberOfDisplay = 2;
  private final int tripletNumberOfDisplay = 3;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
    blogDescriptionScreen = new BlogService().searchForArticleByText(articleTitle)
        .clickArticleByTitle(articleTitle);
  }

  @Test(priority = 1)
  public void checkDisplayedElements() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(blogDescriptionScreen.isArticleTitleDisplayed(),
        "Article Title is not displayed on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.isCategoryLabelDisplayed(),
        "Category label is not displayed on blog description page!");
    softAssert.assertEquals(blogDescriptionScreen.getHashtagsListSize(), doubleNumberOfDisplay,
        "Unexpected number of hashtags on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.areAllHashtagsElementsDisplayed(),
        "Hashtags are not double(at the beginning and at the end) displayed on blog description page!");
    softAssert.assertEquals(blogDescriptionScreen.getPublicationDateListSize(),
        doubleNumberOfDisplay,
        "Unexpected number of publication dates on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.areAllPublicationDateElementsDisplayed(),
        "Publication date is not double(at the beginning and at the end) displayed on blog description page!");
    softAssert.assertEquals(blogDescriptionScreen.getViewIconListSize(), doubleNumberOfDisplay,
        "Unexpected number of view icons on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.areAllViewIconElementsDisplayed(),
        "View icon is not double(at the beginning and at the end) displayed on blog description page!");
    softAssert.assertFalse(blogDescriptionScreen.getViewIconText().isEmpty(),
        "View icon text is empty on blog description page!");
    softAssert.assertEquals(blogDescriptionScreen.getLikeIconListSize(), doubleNumberOfDisplay,
        "Unexpected number of like icons on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.areAllLikeIconElementsDisplayed(),
        "Like icon is not double(at the beginning and at the end) displayed on blog description page!");
    softAssert.assertFalse(blogDescriptionScreen.getLikeIconText().isEmpty(),
        "Like icon text is empty on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.isArticleDescriptionDisplayed(),
        "Article description is not displayed on blog description page!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkOtherArticlesLinks() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(blogDescriptionScreen.getOtherArticlesLabelText(),
        LocaleProperties.getValueOf(BLOG_DESCRIPTION_PAGE_OTHER_ARTICLES_TITLE),
        "Other articles label title on blog description page has incorrect text!");
    softAssert.assertEquals(blogDescriptionScreen.getOtherArticlesLinkSize(),
        tripletNumberOfDisplay,
        "Unexpected number of article links on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.areAllOtherArticlesLinkElementsDisplayed(),
        "All other articles links are not displayed on blog description page!");
    softAssert.assertEquals(blogDescriptionScreen.getOtherArticlesLinkColor(), blueRgbColor,
        "All other articles links colors are not blue on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.isOtherArticlesLinkHasUnderlineWhileHovered(),
        "Other article link hasn't underline when hovered on blog description page!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkAllArticlesLink() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(blogDescriptionScreen.isAllArticlesLinkDisplayed(),
        "All articles link is not displayed on blog description page!");
    softAssert.assertTrue(blogDescriptionScreen.isAllArticlesLinkHasUnderlineWhileHovered(),
        "All articles link hasn't underline while hovered on blog description page!");
    softAssert.assertAll();
  }
}
