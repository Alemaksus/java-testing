package com.epmrdpt.regression.blog;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.BlogDescriptionScreen;
import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_55959_VerifyThatInformationAboutNewsViewsIsSavedForAnyUsers",
    groups = {"full", "regression"})
public class EPMRDPT_55959_VerifyThatInformationAboutNewsViewsIsSavedForAnyUsers {

  private BlogScreen blogScreen;
  private BlogDescriptionScreen blogDescriptionScreen;
  private final int increasedNumber = 1;
  private int randomArticleIndex;
  private int numberOfViewsBefore;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    blogScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickBlogNavigationLink();
  }

  @Test(priority = 1)
  public void checkThatViewsNumberIncreasedOnBlogDescriptionPage() {
    randomArticleIndex = new Random().nextInt(blogScreen.getArticleTitleListSize());
    numberOfViewsBefore = blogScreen.getViewIconCountByIndex(randomArticleIndex);
    blogDescriptionScreen = blogScreen.clickArticleTitleByIndex(randomArticleIndex);
    assertEquals(Integer.parseInt(blogDescriptionScreen.getViewIconText()) - numberOfViewsBefore,
        increasedNumber, "Unexpected number of views(not increased by one)!");
  }

  @Test(priority = 2)
  public void checkThatViewsNumberIncreasedOnBlogPage() {
    blogDescriptionScreen.clickAllArticlesLink().clickRefreshButton();
    assertEquals(blogScreen.getViewIconCountByIndex(randomArticleIndex) - numberOfViewsBefore,
        increasedNumber, "Unexpected number of views(not increased by one)!");
  }
}
