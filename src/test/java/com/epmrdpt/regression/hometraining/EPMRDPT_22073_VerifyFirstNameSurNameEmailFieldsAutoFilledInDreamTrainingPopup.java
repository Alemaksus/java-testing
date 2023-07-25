package com.epmrdpt.regression.hometraining;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.SubscribeScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22073_VerifyFirstNameSurNameEmailFieldsAutoFilledInDreamTrainingPopup",
    groups = {"full", "regression"})
public class EPMRDPT_22073_VerifyFirstNameSurNameEmailFieldsAutoFilledInDreamTrainingPopup {

  public static final String EMBEDDED_SYSTEMS_SKILL = "Embedded Systems";
  private String nameInputInSubscribeScreen;
  private String surNameInputInSubscribeScreen;
  private String emailInputInSubscribeScreen;

  private SubscribeScreen subscribeScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermission());
    subscribeScreen = new SubscribeScreen();
  }

  @Test(priority = 1)
  public void checkSubscribeScreenLoadingForRegisteredUser() {
    TrainingBlockScreen trainingBlockScreen = new TrainingBlockScreen();
    trainingBlockScreen
        .clickTrainingsTitle()
        .clickSearchDropDown()
        .clickSkillsFiltersTab()
        .clickSkillCheckboxByName(EMBEDDED_SYSTEMS_SKILL)
        .clickLocationFiltersTab()
        .clickCountryByNameFromDropDown(getValueOf(COUNTRY_NAME_BELARUS))
        .clickCityByNameFromDropDown(getValueOf(CITY_NAME_BELARUS_GOMEL))
        .clickTrainingsTitle();
    assertTrue(
        trainingBlockScreen
            .waitSubscribeButtonVisibility()
            .hoverOverSubscribeButton()
            .clickSubscribeButton()
            .waitUntilSubscribeScreenLoads()
            .isScreenLoaded(),
        "Dream training pop up did not appear!");
  }

  @Test(priority = 2)
  public void checkEmailInputFieldDisabledForRegisteredUser() {
    assertTrue(
        subscribeScreen
            .waitEmailInputVisbility()
            .isEmailFieldForRegisteredUserDisabled(),
        "Email input field is not disabled!");
  }

  @Test(priority = 3)
  public void checkFieldsOfDreamTrainingPopupIsSameAsInfoInUserProfile() {
    SoftAssert softAssert = new SoftAssert();
    nameInputInSubscribeScreen = subscribeScreen.getNameInputAttributeValue();
    surNameInputInSubscribeScreen = subscribeScreen.getSurnameInputAttributeValue();
    emailInputInSubscribeScreen = subscribeScreen.getEmailInputAttributeValue();
    subscribeScreen.clickCloseSubscribeScreen();
    ProfileScreen profileScreen = new HeaderScreen()
        .clickProfileMenu()
        .clickProfileButton()
        .waitScreenLoading();
    softAssert.assertTrue(profileScreen.isScreenLoaded(),
        "Profile page is not loaded!");
    softAssert.assertEquals(profileScreen.getUserFirstNameText(), nameInputInSubscribeScreen,
        "User first name in subscribe page and profile page do not match!");
    softAssert.assertEquals(profileScreen.getUserLastNameText(), surNameInputInSubscribeScreen,
        "User sur name in subscribe page and profile page do not match!");
    softAssert.assertEquals(
        profileScreen
            .waitContactMailIconVisibilty()
            .hoverOverContactMailIcon()
            .getUserEmailText(), emailInputInSubscribeScreen,
        "User email in subscribe page and profile page do not match!");
    softAssert.assertAll();
  }
}
