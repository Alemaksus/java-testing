package com.epmrdpt.regression.feedback;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.FeedbackScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14334_VerifyTheContentOfFeedbackForm",
    groups = {"full", "regression"})
public class EPMRDPT_14334_VerifyTheContentOfFeedbackForm {

  private FeedbackScreen feedbackScreen;
  private SoftAssert softAssert;
  private String nameCountry = "AutoTestCountry";
  private int randomStringLength = 10;
  private String randomCityName = RandomStringUtils.randomAlphabetic(randomStringLength);

  private static final String ENVELOPE_ICON = "mail.svg";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    feedbackScreen = new FeedbackScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new HeaderScreen().waitEpamLogoVisibility().isScreenLoaded(),
        "Home page hasn't loaded!");
  }

  @Test(priority = 2)
  public void checkFeedbackPopupLoading() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(feedbackScreen.clickFeedbackButton().isIConfirmCheckboxDisplayed(),
        "'I confirm' checkbox of feedback screen is not displayed");
    softAssert.assertTrue(feedbackScreen.clickIConfirmCheckbox().waitCaptchaVisibility().isScreenLoaded(),
        "Feedback form hasn't appeared after clicking feedback button!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkEmailField() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(feedbackScreen.isEmailAddressFieldDisplayed(),
        "'Email' field isn't displayed!");
    softAssert.assertTrue(feedbackScreen.getEmailSpanBackgroundOfAfter().contains(ENVELOPE_ICON),
        "'Envelope' icon in 'email' field isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkCountryField() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(feedbackScreen.clickCountryDropdown().isCountryFieldDisplayed(),
        "'Country' field isn't displayed!");
    softAssert.assertTrue(feedbackScreen.clickCountryDropdown().isCountryDDLItemByTextDisplayed(
        LocaleProperties.getValueOf(LocalePropertyConst.FEEDBACK_COUNTRY_OTHER_COUNTRY)),
        "'Other country' option in 'country' dropdown isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkCityField() {
    feedbackScreen.clickCountryDropdown().selectCountry(nameCountry);
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        feedbackScreen.clickCityDropdown().typeCityName(randomCityName).isOtherCityFieldDisplayed(),
        "'City' field isn't displayed!");
    softAssert.assertTrue(feedbackScreen.isCityDDLItemByTextDisplayed(
        LocaleProperties.getValueOf(LocalePropertyConst.FEEDBACK_CITY_OTHER_CITY)),
        "'Other city' option in 'city' dropdown isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkCategoryField() {
    feedbackScreen.clickCityDropdown();
    assertTrue(feedbackScreen.isCategoryFieldDisplayed(), "'Category' field isn't displayed!");
  }

  @Test(priority = 7)
  public void checkMessageField() {
    assertTrue(feedbackScreen.isMessageFieldDisplayed(), "'Message' field isn't displayed!");
  }

  @Test(priority = 8)
  public void checkImNotRobotCheckbox() {
    softAssert = new SoftAssert();
    feedbackScreen.switchToRecaptchaFrame();
    softAssert.assertTrue(feedbackScreen.isImNotRobotCheckboxDisplayed(),
        "'I'm not a robot' checkbox isn't displayed!");
    softAssert.assertTrue(feedbackScreen.isImNotRobotTextDisplayed(
        LocaleProperties.getValueOf(LocalePropertyConst.FEEDBACK_I_AM_NOT_A_ROBOT)),
        "'I'm not a robot' message isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkSendButton() {
    feedbackScreen.switchToDefault();
    assertTrue(feedbackScreen.isSendButtonDisplayed(), "'Send' button isn't displayed!");
  }
}
