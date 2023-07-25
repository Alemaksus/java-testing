package com.epmrdpt.smoke.profile;

import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_EDIT_PROFILE_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ENGLISH_LEVEL_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_PREFERRED_METHODS_COMMUNICATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_PROFESSIONAL_INFO_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_PROFILE_CREATION_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_TAKE_TEST_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_TEST_RESULT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_TRAINING_INFO_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_WARNING_TOOLTIP;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13234_VerifyInformationAboutUserIsDisplayedOnProfilePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13234_VerifyInformationAboutUserIsDisplayedOnProfilePage {

  private SoftAssert softAssert;
  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    profileScreen = new ProfileScreen();
  }

  @Test(priority = 1)
  public void checkUserLogIn() {
    assertTrue(new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithoutTrainingsNew())
        .clickProfileMenu().clickProfileButton().isScreenLoaded(), "Profile page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainingInfoTabDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isTrainingInfoButtonDisplayed(), "'Training info' tab isn't displayed!");
    softAssert.assertEquals(
        LocaleProperties.getValueOf(PROFILE_TRAINING_INFO_TAB),
        profileScreen.getTrainingInfoButtonText(),
        "'Training Info' tab has incorrect text!");
    softAssert.assertFalse(
        profileScreen.isActiveApplicationsPresentNoWait(), "'Training Info' tab isn't empty!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkProfessionalInfoTabCorrectDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isProfessionalInfoButtonDisplayed(),
        "'Professional info' tab isn't displayed!");
    softAssert.assertEquals(
        LocaleProperties.getValueOf(PROFILE_PROFESSIONAL_INFO_TAB),
        profileScreen.getProfessionalInfoButtonText(),
        "'Professional Info' tab has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkEditProfileLinkCorrectDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isEditProfileLinkDisplayed(), "'Edit profile' link isn't displayed!");
    softAssert.assertEquals(
        profileScreen.getEditProfileLinkText(),
        LocaleProperties.getValueOf(PROFILE_EDIT_PROFILE_LINK),
        "'Edit Profile' link has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkProfilePhotoDisplayed() {
    assertTrue(profileScreen.isProfilePhotoDisplayed(), "Profile photo isn't displayed!");
  }


  @Test(priority = 4)
  public void checkFirstNameDisplayed() {
    assertTrue(profileScreen.isFirstNameDisplayed(), "First name isn't displayed!");
  }

  @Test(priority = 4)
  public void checkLastNameDisplayed() {
    assertTrue(profileScreen.isLastNameDisplayed(), "Last name isn't displayed!");
  }

  @Test(priority = 4)
  public void checkMapMarkersSectionDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isMapMarkerIconDisplayed(),
        "Map marker icon isn't displayed!");
    softAssert.assertTrue(
        profileScreen.isMapMarkerTextDisplayed(),
        "Map marker text isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkBirthDateFieldDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isBirthDateFieldTitleDisplayed(),
        "'Birth date' field title isn't displayed!");
    softAssert.assertTrue(profileScreen.isBalloonIconDisplayed(), "Balloon icon isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkContactEmailIconDisplayed() {
    assertTrue(profileScreen.isContactEmailIconDisplayed(), "Contact Email icon isn't displayed!");
  }

  @Test(priority = 4)
  public void checkContactPhoneIconDisplayed() {
    assertTrue(profileScreen.isContactPhoneIconDisplayed(), "Contact phone icon isn't displayed!");
  }

  @Test(priority = 4)
  public void checkProfileCreationDateFieldTitleCorrectDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isProfileCreationDateFieldTitleDisplayed(),
        "'Profile Creation Date' isn't displayed!");
    assertEquals(
        profileScreen.getProfileCreationDateFieldTitleText(),
        LocaleProperties.getValueOf(PROFILE_PROFILE_CREATION_DATE_LABEL),
        "'Profile Creation Date' field title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkPreferredMethodsOfCommunicationFieldTitleCorrectDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isPreferredMethodsOfCommunicationFieldTitleDisplayed(),
        "'Preferred methods of communication' field title isn't displayed!");
    softAssert.assertEquals(
        profileScreen.getPreferredMethodsOfCommunicationFieldTitleText(),
        LocaleProperties.getValueOf(PROFILE_PREFERRED_METHODS_COMMUNICATION_LABEL),
        "'Preferred Methods Of Communication 'field title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkEnglishLevelFieldDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isEnglishLevelFieldTitleDisplayed(), "English level title isn't displayed!");
    softAssert.assertEquals(
        profileScreen.getEnglishLevelFieldTitleText(),
        LocaleProperties.getValueOf(PROFILE_ENGLISH_LEVEL_TITLE),
        "English level title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkEnglishTestResultFieldDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isTestResultTitleDisplayed(), "Test result title isn't displayed!");
    softAssert.assertEquals(
        profileScreen.getTestResultTitleText(),
        LocaleProperties.getValueOf(PROFILE_TEST_RESULT),
        "Test result title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkWarningSectionDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isWarningIconDisplayed(), "Warning icon isn't displayed!");
    softAssert.assertTrue(
        profileScreen.isWarningTooltipDisplayed(), "Warning tooltip isn't displayed!");
    softAssert.assertEquals(
        profileScreen.getWarningTooltipText(), LocaleProperties.getValueOf(PROFILE_WARNING_TOOLTIP),
        "Test result title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTakeTestButtonDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        profileScreen.isEnglishTestButtonDisplayed(), "Take test button isn't displayed!");
    softAssert.assertEquals(
        profileScreen.getEnglishTestButtonText(),
        LocaleProperties.getValueOf(PROFILE_TAKE_TEST_BUTTON),
        "Take test button has incorrect text!");
    softAssert.assertAll();
  }
}
