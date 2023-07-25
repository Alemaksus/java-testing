package com.epmrdpt.regression.admin;

import static com.epmrdpt.bo.user.UserFactory.asUserManager;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactUserDetailsScreen;
import com.epmrdpt.screens.ReactUserManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14120_VerifyThePossibilityToCreateUser",
    groups = {"full", "regression", "admin"})
public class EPMRDPT_14120_VerifyThePossibilityToCreateUser {

  private static final int RANDOM_LENGTH = 6;
  private static final String EMAIL = StringUtils.getGeneratedEmail();
  private static final String USER_ROLE = "RegisteredUser";
  private ReactUserManagementScreen reactUserManagementScreen;
  private ReactUserDetailsScreen reactUserDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    reactUserDetailsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asUserManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactUserManagementLink()
        .waitScreenLoading()
        .clickCreateUserButton();
  }

  @Test(priority = 1)
  public void checkCreateUserScreenLoaded() {
    assertTrue(reactUserDetailsScreen.isScreenLoaded(),
        "Create user page isn't opened!");
  }

  @Test(priority = 2)
  public void checkUserCreationSuccessful() {
    assertTrue(reactUserDetailsScreen
            .typeFirstNameInputField(randomAlphabetic(RANDOM_LENGTH))
            .typeLastNameInputField(randomAlphabetic(RANDOM_LENGTH))
            .typeEmailInputField(EMAIL)
            .clickCreateButton()
            .isSuccessfulUserCreationMessageDisplayed(),
        "Successful user creation message isn't displayed!");
  }

  @Test(priority = 3)
  public void checkUserManagementLoaded() {
    reactUserManagementScreen = reactUserDetailsScreen.clickUserListButton();
    assertTrue(reactUserManagementScreen.isScreenLoaded(),
        "User management page isn't opened!");
  }

  @Test(priority = 4)
  public void checkUserFound() {
    SoftAssert softAssert = new SoftAssert();
    reactUserManagementScreen.typeSearchInputField(EMAIL)
        .clickApplyButton()
        .waitLoadSpinnerInvisibility();
    softAssert.assertTrue(reactUserManagementScreen.isSearchResultByNameDisplayed(EMAIL),
        String.format("User with email '%s' not found", EMAIL));
    softAssert
        .assertTrue(reactUserManagementScreen.isTextInSearchResultByNameDisplayed(EMAIL, USER_ROLE),
        String.format("User doesn't have the '%s' role", USER_ROLE));
    softAssert.assertAll();
  }
}
