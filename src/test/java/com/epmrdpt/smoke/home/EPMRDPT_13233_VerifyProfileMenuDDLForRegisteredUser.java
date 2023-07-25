package com.epmrdpt.smoke.home;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.EColorUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13233_VerifyProfileMenuDDLForRegisteredUser",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13233_VerifyProfileMenuDDLForRegisteredUser {

  private static final String WHITE_COLOR = EColorUtils.WHITE_COLOR.getColorRgbaFormat();
  private SoftAssert softAssert;
  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen().waitScreenLoaded();
  }

  @Test(priority = 1)
  public void checkLoginAndHoveredApplicationsButton() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                UserFactory.asAdmin())
            .clickProfileMenu().isUserDropDownDisplayed(), "‘Profile’ menu isn't opened.");
    softAssert.assertTrue(
        headerScreen.isApplicationsButtonDisplayed(), "Applications Button isn't displayed!");
    softAssert.assertEquals(
        headerScreen.getApplicationsButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.HEADER_APPLICATIONS_BUTTON),
        "Applications Button hasn't correct text!");
    softAssert.assertEquals(
        headerScreen.getApplicationsButtonTextColor(),
        WHITE_COLOR,
        "'Applications' button text color is not white!");
    softAssert.assertNotEquals(
        headerScreen.getDropDownButtonsBackgroundColor(),
        headerScreen.getHoveredApplicationsButtonBackgroundColor(),
        "'Applications' button  background color did not change when mouse hover!");
    softAssert.assertEquals(
        headerScreen.getApplicationsButtonTextColor(),
        WHITE_COLOR,
        "'Applications' button text color is not white when mouse hover!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkChosenApplicationsButtonWithCorrectText() {
    headerScreen.clickApplicationsButton().waitScreenLoading();
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        headerScreen.clickProfileMenu().isUserDropDownDisplayed(), "‘Profile’ menu isn't opened.");
    softAssert.assertTrue(
        headerScreen.isApplicationsButtonDisplayed(), "Applications Button isn't displayed!");
    softAssert.assertNotEquals(
        headerScreen.getApplicationsButtonTextColor(),
        WHITE_COLOR,
        "'Applications' button text color is white when chosen!");
    softAssert.assertEquals(
        headerScreen.getApplicationsButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.HEADER_APPLICATIONS_BUTTON),
        "Applications Button hasn't correct text!");
    softAssert.assertNotEquals(
        headerScreen.getDropDownButtonsBackgroundColor(),
        headerScreen.getHoveredApplicationsButtonBackgroundColor(),
        "'Applications' button  background color did not change when mouse hover!");
    softAssert.assertEquals(
        headerScreen.getApplicationsButtonTextColor(),
        WHITE_COLOR,
        "'Applications' button text color is not white when chosen and mouse hover!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkProfileButtonDisplayedWithCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        headerScreen.isProfileButtonDisplayed(), "Profile Button isn't displayed!");
    softAssert.assertEquals(
        headerScreen.getProfileButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.HEADER_PROFILE_BUTTON),
        "Profile Button hasn't correct text!");
    softAssert.assertEquals(
        headerScreen.getProfileButtonTextColor(),
        WHITE_COLOR,
        "'Profile' button text color is not white!");
    softAssert.assertNotEquals(
        headerScreen.getDropDownButtonsBackgroundColor(),
        headerScreen.getHoveredProfileButtonBackgroundColor(),
        "'Profile' button color did not change when mouse hover!");
    softAssert.assertEquals(
        headerScreen.getProfileButtonTextColor(),
        WHITE_COLOR,
        "'Profile' button text color is not white when mouse hover!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkChosenProfileButtonWithCorrectText() {
    headerScreen.clickProfileButton().waitScreenLoading();
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        headerScreen.clickProfileMenu().isUserDropDownDisplayed(), "‘Profile’ menu isn't opened.");
    softAssert.assertTrue(
        headerScreen.isProfileButtonDisplayed(), "Profile Button isn't displayed!");
    softAssert.assertEquals(
        headerScreen.getProfileButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.HEADER_PROFILE_BUTTON),
        "Profile Button hasn't correct text!");
    softAssert.assertNotEquals(
        headerScreen.getProfileButtonTextColor(),
        WHITE_COLOR,
        "'Profile' button text color is white when chosen!");
    softAssert.assertNotEquals(
        headerScreen.getDropDownButtonsBackgroundColor(),
        headerScreen.getHoveredProfileButtonBackgroundColor(),
        "'Profile' button color did not change when mouse hover!");
    softAssert.assertEquals(
        headerScreen.getProfileButtonTextColor(),
        WHITE_COLOR,
        "'Profile' button text color is not white when chosen and mouse hover!");
    softAssert.assertAll();
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkSignOutButtonDisplayedWithCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        headerScreen.isSignOutButtonDisplayed(), "Sign out Button isn't displayed!");
    softAssert.assertEquals(
        headerScreen.getSignOutButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.HEADER_SIGN_OUT_BUTTON),
        "Sign out Button hasn't correct text!");
    softAssert.assertEquals(
        headerScreen.getSignOutButtonTextColor(),
        WHITE_COLOR,
        "'Sing out' button color text is not white!");
    softAssert.assertNotEquals(
        headerScreen.getDropDownButtonsBackgroundColor(),
        headerScreen.getHoveredSignOutButtonBackgroundColor(),
        "'Sign out' button color did not change when mouse hover!");
    softAssert.assertEquals(
        headerScreen.getSignOutButtonTextColor(),
        WHITE_COLOR,
        "'Sing out' button color text is not white when mouse hover!");
    softAssert.assertAll();
  }
}
