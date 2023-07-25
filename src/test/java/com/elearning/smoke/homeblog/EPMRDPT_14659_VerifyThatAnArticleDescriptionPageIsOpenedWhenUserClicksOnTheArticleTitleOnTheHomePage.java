package com.epmrdpt.smoke.homeblog;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogBlockOnStartPage;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14659_VerifyThatAnArticleDescriptionPageIsOpenedWhenUserClicksOnTheArticleTitleOnTheHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14659_VerifyThatAnArticleDescriptionPageIsOpenedWhenUserClicksOnTheArticleTitleOnTheHomePage {

  private BlogBlockOnStartPage blogBlockOnStartPage;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    blogBlockOnStartPage = new BlogBlockOnStartPage();
  }

  @Test(priority = 1)
  public void checkSomeArticlesCardsCreated() {
    assertTrue(blogBlockOnStartPage.getArticleTitlesCount() > 0, "Articles cards not created!");
  }

  @Test(priority = 2)
  public void checkAfterClickTitlePortalUploadingDescriptionPage() {
    int articleIndex = new Random().nextInt(blogBlockOnStartPage.getArticleTitlesCount());
    assertTrue(blogBlockOnStartPage.getArticleTitleTextByIndex(articleIndex)
            .contentEquals(blogBlockOnStartPage
                .clickArticleByIndex(articleIndex)
                .waitArticleTitleIsNotEmpty()
                .getArticleTitleOnDescriptionPageText()),
        "After clicking on title portal doesn't uploading article's description page!");
  }
}
