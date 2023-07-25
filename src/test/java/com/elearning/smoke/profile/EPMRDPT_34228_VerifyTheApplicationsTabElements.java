package com.epmrdpt.smoke.profile;

import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_APPLICATION_TAB_CONTACT_PERSON_HEADING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_APPLICATION_TAB_REGISTRATION_DATE_HEADING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_APPLICATION_TAB_STUDENT_STATUS_HEADING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_APPLICATION_TAB_TRAINING_NAME_HEADING;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34228_VerifyTheApplicationsTabElements",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34228_VerifyTheApplicationsTabElements {

  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(UserFactory
            .withSimplePermissionAndWithTraining())
        .clickProfileMenu()
        .clickProfileButton()
        .clickApplicationsTab();
  }

  @Test
  public void checkApplicationsTabElements() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isApplicationTabHeaderDisplayed(
            LocaleProperties.
                getValueOf(PROFILE_APPLICATION_TAB_TRAINING_NAME_HEADING)),
        "Training name header on Applications tab isn't displayed!");
    softAssert.assertTrue(profileScreen.isApplicationTabHeaderDisplayed(
            LocaleProperties.
                getValueOf(PROFILE_APPLICATION_TAB_STUDENT_STATUS_HEADING)),
        "Student status header on Applications tab isn't displayed!");
    softAssert.assertTrue(profileScreen.isApplicationTabHeaderDisplayed(
            LocaleProperties
                .getValueOf(PROFILE_APPLICATION_TAB_REGISTRATION_DATE_HEADING)),
        "Registration date header on Applications tab isn't displayed!");
    softAssert.assertTrue(profileScreen.isApplicationTabHeaderDisplayed(
            LocaleProperties
                .getValueOf(PROFILE_APPLICATION_TAB_CONTACT_PERSON_HEADING)),
        "Contact person header on Applications tab isn't displayed!");
    softAssert.assertTrue(profileScreen.isSearchInputDisplayed(),
        "Search input field on Applications tab isn't displayed!");
    softAssert.assertTrue(profileScreen.isFindButtonDisplayed(),
        "Find button on Applications tab isn't displayed!");
    softAssert.assertTrue(profileScreen.isPaginationSectorDisplayed(),
        "Pagination buttons on Applications tab isn't displayed!");
    softAssert.assertAll();
  }
}
