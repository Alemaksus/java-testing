package com.epmrdpt.regression.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactSearchScreen;
import com.epmrdpt.services.LoginService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14119_VerifyGlobalSearchFeature",
    groups = {"full", "regression", "admin"})
public class EPMRDPT_14119_VerifyGlobalSearchFeature {

  private static final String SEARCH_INPUT = "autotest";
  private static final String SEARCH_PLACEHOLDER =  "What are you looking for?";

  private ReactSearchScreen searchScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Search tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14119_VerifyGlobalSearchFeature(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .waitLinksToRedirectOnOtherSectionsDisplayed();
    searchScreen = new HeaderScreen().clickSearchLink();
    searchScreen.switchToLastWindow();
  }

  @Test(priority = 1)
  public void checkIfSearchScreenIsOpened() {
    assertTrue(searchScreen.isScreenLoaded(),
        "Search page isn't opened!");
  }

  @Test(priority = 2)
  public void checkSearchHint() {
    assertEquals(searchScreen.getSearchInputPlaceholder(), SEARCH_PLACEHOLDER,
        "Hint in search input field is not present!");
  }

  @Test(priority = 3)
  public void checkSearchFunctionality() {
    SoftAssert softAssert = new SoftAssert();
    searchScreen.enterSearchInputText(SEARCH_INPUT)
        .clickFindButton();
    List<String> fullNameList = searchScreen.getSearchResultFullNames();
    List<String> emailList = searchScreen.getSearchResultEmails();
    for (int rowNumber = 0; rowNumber < fullNameList.size(); rowNumber++) {
      softAssert.assertTrue(
          fullNameList.get(rowNumber).toLowerCase().contains(SEARCH_INPUT)
              || emailList.get(rowNumber).toLowerCase().contains(SEARCH_INPUT),
          "Search results are not relevant to search performed!");
    }
    softAssert.assertAll();
  }
}
