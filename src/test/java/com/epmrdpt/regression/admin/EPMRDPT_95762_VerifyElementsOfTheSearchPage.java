package com.epmrdpt.regression.admin;

import static com.epmrdpt.bo.user.UserFactory.asAdmin;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactSearchScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_95762_VerifyElementsOfTheSearchPage",
    groups = {"full", "regression"})
public class EPMRDPT_95762_VerifyElementsOfTheSearchPage {

  private ReactSearchScreen reactSearchScreen;
  private static final String SEARCH_INPUT_PLACEHOLDER_TEXT = "What are you looking for?";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUpSearchPage() {
    reactSearchScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asAdmin())
        .clickSearchLink()
        .waitForScreenLoaded();
  }

  @Test
  public void checkSearchBarIsDisplayed() {
    assertTrue(reactSearchScreen.isSearchInputDisplayed(),
        "Search input is not displayed on the search page!");
  }

  @Test
  public void checkSearchInputPlaceholderText() {
    assertEquals(reactSearchScreen.getSearchInputPlaceholder(), SEARCH_INPUT_PLACEHOLDER_TEXT,
        "Search input placeholder contains unexpected text!");
  }

  @Test
  public void checkFindButtonIsDisplayed() {
    assertTrue(reactSearchScreen.isFindButtonDisplayed(),
        "Find button is not displayed on the search page!");
  }
}
