package com.epmrdpt.smoke.homeblog;


import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.BlogBlockOnStartPage;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14661_VerifyThatAnArticlesDescriptionPageIsOpenedWhenUserClicksOnTheArticlesPreviewImageOnTheHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14661_VerifyThatAnArticlesDescriptionPageIsOpenedWhenUserClicksOnTheArticlesPreviewImageOnTheHomePage {

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
  public void checkAfterClickPreviewImagePortalUploadingDescriptionPage() {
    int articleIndex = new Random().nextInt(blogBlockOnStartPage.getArticleTitlesCount());
    assertTrue(blogBlockOnStartPage.getArticleTitleTextByIndex(articleIndex)
            .contentEquals(blogBlockOnStartPage
                .clickPreviewImageByIndex(articleIndex)
                .waitArticleTitleIsNotEmpty()
                .getArticleTitleOnDescriptionPageText()),
        "After clicking on 'Preview Image' portal doesn't uploading article's description page!");
  }
}
