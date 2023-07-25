package com.epmrdpt.smoke.profile;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_EDIT_PROFILE_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ENGLISH_LEVEL_RESULT_A2;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ENGLISH_LEVEL_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_PREFERRED_METHODS_COMMUNICATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_PROFILE_CREATION_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_TAKE_TEST_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_TEST_RESULT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_WARNING_TOOLTIP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77746_VerifyTheContentOfTheGeneralInfoOnProfilePage",
    groups = {"smoke", "full"})

public class EPMRDPT_77746_VerifyTheContentOfTheGeneralInfoOnProfilePage {

  private final String fullNameValues = "newuserwithouttraining";
  private final String cityValue = "AutoTestCity";
  private final String emailValue = "newuserwithouttraining@mail.ru";
  private final String profileCreationDateFieldText = "15.12.2022";
  private ProfileScreen profileScreen;
  private SoftAssert softAssert;

  @BeforeClass
  public void login() {
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithoutTrainingsNew())
        .clickProfileMenu()
        .clickProfileButton()
        .waitTrainingInfoDisplayed();
  }

  @Test(priority = 1)
  public void checkIconIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isMapMarkerIconDisplayed(),
        "'MapMarker' Icon is not displayed!");
    softAssert.assertTrue(profileScreen.isBalloonIconDisplayed(),
        "'Balloon' Icon is not displayed!");
    softAssert.assertTrue(profileScreen.isSocialNetworksIconDisplayed(),
        "'SocialNetworks' Icon is not displayed!");
    softAssert.assertTrue(profileScreen.isEmailIconDisplayed(), "'Email' Icon is not displayed!");
    softAssert.assertTrue(profileScreen.isContactPhoneIconDisplayed(),
        "'Phone' Icon is not displayed!");
    softAssert.assertTrue(profileScreen.isWarningIconDisplayed(),
        "'Warning' Icon is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTitleIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isProfileCreationDateFieldTitleDisplayed(),
        "'Profile Creation Date' Title is not displayed!");
    softAssert.assertTrue(profileScreen.isPreferredMethodsOfCommunicationFieldTitleDisplayed(),
        "'Preferred method of communication' Title is not displayed!");
    softAssert.assertTrue(profileScreen.isEnglishLevelFieldTitleDisplayed(),
        "'English Level' Title is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkButtonIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isTakeTestButtonDisplayed(),
        "'Take Test' Button is not displayed!");
    softAssert.assertTrue(profileScreen.isEditProfileLinkDisplayed(),
        "'Edit Test' Button is not displayed!");
    softAssert.assertTrue(profileScreen.isProfilePhotoDisplayed(),
        "'Profile Photo' is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTabIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isTrainingInfoButtonDisplayed(),
        "'Training info' Tab is not displayed!");
    softAssert.assertTrue(profileScreen.isProfessionalInfoButtonDisplayed(),
        "'Professional info' Tab is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkTrainingInfoTabIsEmpty() {
    assertFalse(profileScreen.isApplicationTabDisplayed(),
        "Training Info Tab is not empty!");
  }

  @Test(priority = 6)
  public void checkTextIsCorrect() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(profileScreen.getProfileCreationDateFieldTitleText(),
        getValueOf(PROFILE_PROFILE_CREATION_DATE_LABEL),
        "'Profile Creation Date' Text is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getPreferredMethodsOfCommunicationFieldTitleText(),
        getValueOf(PROFILE_PREFERRED_METHODS_COMMUNICATION_LABEL),
        "'Preferred Method Of Communication' Title is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getPreferredMethodOfCommunication(),
        getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION),
        "'Preferred Method Of Communication' Text is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getEnglishLevelFieldTitleText(),
        getValueOf(PROFILE_ENGLISH_LEVEL_TITLE),
        "'English Level' Title is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getWarningTooltipText(),
        getValueOf(PROFILE_WARNING_TOOLTIP),
        "'Warning' Text is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getTestResultTitleText(),
        LocaleProperties.getValueOf(PROFILE_TEST_RESULT),
        "'English Test Result' Text is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getEditProfileLinkText(),
        LocaleProperties.getValueOf(PROFILE_EDIT_PROFILE_LINK),
        "'Edit Profile' Text is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getFirstNameText(),
        fullNameValues, "'First' Name is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getLastNameText(),
        fullNameValues, "'Last' Name is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getCityOfResidenceValue(), cityValue,
        "'City' Values doesn't correspond!");
    softAssert.assertEquals(profileScreen.getUserEmailText(), emailValue,
        "'Email' Value doesn't correspond!");
    softAssert.assertEquals(profileScreen.getProfileCreationDateFieldText(),
        profileCreationDateFieldText,
        "'Email' Value doesn't correspond!");
    softAssert.assertEquals(profileScreen.getTakeTestButtonText(),
        LocaleProperties.getValueOf(PROFILE_TAKE_TEST_BUTTON),
        "'Take Test' Button Text is not displayed correctly!");
    softAssert.assertEquals(profileScreen.getEnglishLevelValue(),
        getValueOf(PROFILE_ENGLISH_LEVEL_RESULT_A2),
        "'English Level' Result is not displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkEditLink() {
    ProfileEditionScreen profileEditionScreen = profileScreen.clickEditProfileLink();
    assertTrue(profileEditionScreen.isScreenLoaded(),
        "The 'Profile Edition' Screen fails to load!");
  }
}
