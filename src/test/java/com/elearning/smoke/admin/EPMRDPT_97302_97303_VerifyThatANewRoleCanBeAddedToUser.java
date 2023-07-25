package com.epmrdpt.smoke.admin;

import static com.epmrdpt.bo.user.UserFactory.asUserManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactUserDetailsScreen;
import com.epmrdpt.screens.ReactUserManagementScreen;
import com.epmrdpt.services.LoginService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97302_97303_VerifyThatANewRoleCanBeAddedToUser",
    groups = {"full", "smoke"})
public class EPMRDPT_97302_97303_VerifyThatANewRoleCanBeAddedToUser {

  private static final String USER_NAME = "TestForUserRoles";
  private static final String USER_ROLE = "EventManager";
  private ReactUserDetailsScreen reactUserDetailsScreen;
  private ReactUserManagementScreen reactUserManagementScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    reactUserManagementScreen = new ReactUserManagementScreen();
    reactUserDetailsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asUserManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactUserManagementLink()
        .waitScreenLoading()
        .typeSearchInputField(USER_NAME)
        .waitSearchResultsLabelIsDisplayed()
        .clickApplyButton()
        .waitSearchResultsDisplayedByName(USER_NAME)
        .clickFirstUserButton();
    reactUserManagementScreen.switchToLastWindow();
  }

  @Test(priority = 1)
  public void editUser() {
    reactUserDetailsScreen.waitScreenLoading()
        .clickAddButton()
        .clickRoleDropDown()
        .clickRoleItemByName(USER_ROLE)
        .clickSaveChangesButton();
    assertTrue(reactUserDetailsScreen.isSuccessfulUserEditionMessageDisplayed(),
        "Successful user edition message isn't displayed!");
  }

  @Test(priority = 2)
  public void checkUserRole() {
    reactUserDetailsScreen.switchToFirstWindowIfMoreThanOne();
    reactUserManagementScreen
        .clickApplyButton()
        .waitSearchResultsDisplayedByName(USER_NAME);
    assertTrue(reactUserManagementScreen.isTextInSearchResultByNameDisplayed(USER_NAME, USER_ROLE),
        String.format("'%s' role don't appear!", USER_ROLE));
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteRole(Method method) {
    if (method.getName().equals("checkUserRole")) {
      reactUserManagementScreen.switchToLastWindow();
      reactUserDetailsScreen.clickBucketButton()
          .clickSaveChangesButton()
          .waitSuccessfulPopUp();
    }
  }
}

