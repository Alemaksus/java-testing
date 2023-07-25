package com.epmrdpt.regression.admin;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactUserManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_70226_VerifyTheContentOfUserManagementPage",
    groups = {"full", "regression", "admin"})
public class EPMRDPT_70226_VerifyTheContentOfUserManagementPage {

  private ReactUserManagementScreen reactUserManagementScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asUserManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed();
    reactUserManagementScreen = new ReactUserManagementScreen();
  }

  @Test(priority = 1)
  public void checkUserManagementPageIsOpened() {
    assertTrue(new HeaderScreen().clickReactUserManagementLink()
            .isScreenLoaded(),
        "User Management page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkUsersListLabel() {
    assertTrue(reactUserManagementScreen.isTextByNameDisplayed("Users list"),
        "Users list label isn't present!");
  }

  @Test(priority = 2)
  public void checkSearchBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactUserManagementScreen.isSearchBarDisplayed(),
        "Search bar isn't present!");
    softAssert.assertEquals(reactUserManagementScreen.getPlaceholderOfSearchInput(),
        "Find by name, e-mail, phone number",
        "Incorrect placeholder of search bar!");
    softAssert.assertTrue(reactUserManagementScreen.isUserRolesDropDownDisplayed(),
        "User roles dropdown isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isCountryDropDownDisplayed(),
        "Country dropdown isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isCityDropDownDisplayed(),
        "City dropdown isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isAccountTypeDropDownDisplayed(),
        "Account Type dropdown isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("Elements found"),
        "'Elements found' title isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isResetButtonDisplayed(),
        "Reset button isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isApplyButtonDisplayed(),
        "Apply button isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCreateUserButton() {
    assertTrue(reactUserManagementScreen.isCreateUserButtonDisplayed(),
        "Create button isn't displayed!");
  }

  @Test(priority = 3)
  public void checkColumns() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("Last Name"),
        "Last name column isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("First Name"),
        "First name column isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("Roles"),
        "Roles column isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("Email"),
        "E-mail column isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("Account Type"),
        "Account type column isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkPaginationFilters() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("Show"),
        "'Show' label isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isPaginationDropDownDisplayed(),
        "Pagination dropdown isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isTextByNameDisplayed("elements. Total:"),
        "'elements. Total:' label isn't displayed!");
    softAssert.assertTrue(reactUserManagementScreen.isPaginationButtonsDisplayed(),
        "Pagination buttons isn't displayed!");
    softAssert.assertAll();
  }
}
