package com.epmrdpt.smoke.blog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13046_VerifyAnArticleDescriptionPageIsOpenedWhenUserClickOnTheArticlesPreviewImage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13046_VerifyAnArticleDescriptionPageIsOpenedWhenUserClickOnTheArticlesPreviewImage {

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
  public void checkAfterClickPreviewImagePortalUploadingDescriptionPage() {
    int articleIndex = new Random().nextInt(blogScreen.getSizeArticleCardsList());
    assertTrue(blogScreen.getArticleTitleTextByIndex(articleIndex).contentEquals(blogScreen
            .clickPreviewImageByIndex(articleIndex)
            .waitArticleTitleIsNotEmpty()
            .getArticleTitleOnDescriptionPageText()),
        "After clicking on 'Preview Image' portal doesn't uploading article's description page!");
  }
}
