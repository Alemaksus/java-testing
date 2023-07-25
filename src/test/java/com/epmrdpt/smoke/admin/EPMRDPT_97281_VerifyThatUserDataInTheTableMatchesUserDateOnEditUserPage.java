package com.epmrdpt.smoke.admin;

import static com.epmrdpt.bo.user.UserFactory.asUserManager;

import com.epmrdpt.screens.ReactUserDetailsScreen;
import com.epmrdpt.screens.ReactUserManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_97281_VerifyThatUserDataInTheTableMatchesUserDateOnEditUserPage",
        groups = {"full", "smoke"})
public class EPMRDPT_97281_VerifyThatUserDataInTheTableMatchesUserDateOnEditUserPage {

    private String firstUserLastName;
    private String firstUserFirstName;
    private String firstUserRole;
    private String firstUserEmail;
    private ReactUserDetailsScreen reactUserDetailsScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    private void setup() {
        ReactUserManagementScreen reactUserManagementScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asUserManager())
                .waitLinksToRedirectOnOtherSectionsDisplayed()
                .clickReactUserManagementLink()
                .waitScreenLoading();
        firstUserLastName = reactUserManagementScreen.getFirstUserLastNameText();
        firstUserFirstName = reactUserManagementScreen.getFirstUserFirstNameText();
        firstUserRole = reactUserManagementScreen.getFirstUserRoleText();
        firstUserEmail = reactUserManagementScreen.getFirstUserEmailText();
        reactUserDetailsScreen = reactUserManagementScreen
                .clickFirstUserButton();
        reactUserDetailsScreen.switchToLastWindow();
    }

    @Test
    public void checkUserData() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(reactUserDetailsScreen.getLastNameFieldText(), firstUserLastName,
                "The user last name isn't correct");
        softAssert.assertEquals(reactUserDetailsScreen.getFirstNameFieldText(), firstUserFirstName,
                "The user first name isn't correct");
        softAssert.assertEquals(reactUserDetailsScreen.getRoleFieldText(), firstUserRole,
                "The user role isn't correct");
        softAssert.assertEquals(reactUserDetailsScreen.getEmailFieldText(), firstUserEmail,
                "The user email isn't correct");
        softAssert.assertAll();
    }
}
