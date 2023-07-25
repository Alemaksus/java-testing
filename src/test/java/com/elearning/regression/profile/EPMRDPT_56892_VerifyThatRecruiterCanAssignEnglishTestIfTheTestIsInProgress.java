package com.epmrdpt.regression.profile;

import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_CONFIRMATION_POPUP_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_CONFIRMATION_POPUP_OK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_POPUP_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_CANCEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_OK;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ProfileService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56892_VerifyThatRecruiterCanAssignEnglishTestIfTheTestIsInProgress",
    groups = {"full", "regression"})
public class EPMRDPT_56892_VerifyThatRecruiterCanAssignEnglishTestIfTheTestIsInProgress {

  private User student = UserFactory.withSimplePermissionAndWithoutTrainings();
  private User recruiter = UserFactory.asRecruiter();
  private HeaderScreen headerScreen;
  private LoginService loginService;
  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    loginService = new LoginService();
    headerScreen = loginService.loginAndSetLanguage(student);
    headerScreen.waitForClickableAndClickArrowButton().clickProfileButton();
    new ProfileService().clickContinueOrPassEnglishTestButton();
    headerScreen.closeLastWindowAndSwitchToPreviousIfMoreThanOne();
    loginService.logout().loginAndSetLanguage(recruiter);
    profileScreen = headerScreen.clickSearchLink()
        .waitForScreenLoaded()
        .enterSearchInputText(student.getUsername())
        .clickFindButton()
        .clickSearchResultByEmail(student.getUsername());
    profileScreen.switchToLastWindow();
    profileScreen = profileScreen.waitForAssignEnglishTestButtonVisibility()
        .clickAssignEnglishTestButton()
        .waitPopUpAppear();
  }

  @Test(priority = 1)
  public void checkTheWarningPopUpText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(profileScreen.getPopupHeaderTitle(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_POPUP_HEADER));
    softAssert.assertTrue(profileScreen.isPopupCrossDisplayed());
    softAssert.assertEquals(profileScreen.getPopupMessageText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_MESSAGE));
    softAssert.assertEquals(profileScreen.getPopupOkButtonText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_OK));
    softAssert.assertEquals(profileScreen.getPopupCancelButtonText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_CANCEL));
    softAssert.assertAll();
  }

  @Test(dependsOnMethods = {"checkTheWarningPopUpText"})
  public void checkTheConfirmationPopUpText() {
    profileScreen.clickPopupOkButton().waitPopUpAppear();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(profileScreen.getPopupHeaderTitle(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_POPUP_HEADER));
    softAssert.assertTrue(profileScreen.isPopupCrossDisplayed());
    softAssert.assertEquals(profileScreen.getPopupMessageText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_CONFIRMATION_POPUP_MESSAGE));
    softAssert.assertEquals(profileScreen.getPopupOkButtonAttributeValueText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_CONFIRMATION_POPUP_OK));
    softAssert.assertAll();
  }
}
