package com.epmrdpt.smoke.center_management;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactUserManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "VerifyTheAbilityToSearchForUserViaSearchBarAndFilters",
    groups = {"full", "smoke"})
public class EPMRDPT_97295_VerifyTheAbilityToSearchForUserViaSearchBarAndFilters {

  private final static String INPUT_TEXT = "LastName306387";
  private final static String COUNTRY = "Ukraine";
  private final static String EXPECTED_RESULT = "Alex";
  private ReactUserManagementScreen reactUserManagementScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void fillInSearchFilters() {
    reactUserManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asUserManager())
        .clickReactUserManagementLink()
        .waitScreenLoading()
        .typeSearchInputField(INPUT_TEXT)
        .chooseCountry(COUNTRY)
        .clickApplyButton();
  }

  @Test
  public void checkFilter() {
    reactUserManagementScreen
        .waitSearchResultsDisplayedByName(INPUT_TEXT);
    assertEquals(reactUserManagementScreen.getFirstUserFirstNameText(), EXPECTED_RESULT,
        "User list is not updated according to the entered text and chosen filters");
  }
}
