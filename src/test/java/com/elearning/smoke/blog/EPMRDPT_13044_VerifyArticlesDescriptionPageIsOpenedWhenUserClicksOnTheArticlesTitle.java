package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13044_VerifyArticlesDescriptionPageIsOpenedWhenUserClicksOnTheArticlesTitle",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_13044_VerifyArticlesDescriptionPageIsOpenedWhenUserClicksOnTheArticlesTitle {

  private BlogScreen blogScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogScreen = new BlogScreen();
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed().clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkBlogScreenLoading() {
    assertTrue(blogScreen.isScreenLoaded(),
        "Blog screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkSomeArticlesCardsCreated() {
    assertTrue(blogScreen.getSizeArticleCardsList() > 0, "Articles cards not created!");
  }

  @Test(priority = 3)
  public void checkAfterClickArticleTitlePortalUploadingDescriptionPage() {
    int articleIndex = new Random().nextInt(blogScreen.getSizeArticleCardsList());
    assertTrue(blogScreen.getArticleTitleTextByIndex(articleIndex)
            .contentEquals(blogScreen.clickArticleTitleByIndex(articleIndex)
                .waitArticleTitleIsNotEmpty()
                .getArticleTitleOnDescriptionPageText()),
        "After clicking on 'Article title' button portal doesn't uploading article's description page!");
  }
}
