package com.epmrdpt.regression.admin;

import static org.testng.Assert.*;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactSearchScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_95765_VerifyTheAbilityToOpenUserProfileViaHyperlink",
    groups = {"full", "regression"})
public class EPMRDPT_95765_VerifyTheAbilityToOpenUserProfileViaHyperlink {

  private ReactSearchScreen reactSearchScreen;
  private final String TEST_EMAIL_FOR_SEARCH = "UserEmail6@mail.io";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void signInProfileAndOpenSearchPage() {
    reactSearchScreen = new LoginService()
        .loginAndSetLanguage(UserFactory.asSuperAdmin())
        .clickSearchLink()
        .waitForScreenLoaded()
        .enterSearchInputText(TEST_EMAIL_FOR_SEARCH)
        .clickFindButton()
        .waitForScreenLoaded();
  }

  @Test(priority = 1)
  public void checkUserIsPresentInSearchResults() {
    assertEquals(reactSearchScreen.getSearchResultEmails().get(0), TEST_EMAIL_FOR_SEARCH,
        "Expected user was not found!");
  }

  @Test(priority = 2)
  public void checkUserProfileIsOpened() {
    ProfileScreen profileScreen = reactSearchScreen.clickSearchResultByEmail(TEST_EMAIL_FOR_SEARCH);
    reactSearchScreen.switchToLastWindow();
    assertTrue(profileScreen.isScreenLoaded(), "The user profile is not loaded!");
  }
}
