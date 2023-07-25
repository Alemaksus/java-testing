package com.epmrdpt.regression.events_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DESCRIPTION_YOU_CAN_UPLOAD_PHOTO_REQUIREMENTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactEventDescriptionScreen;

import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_90292_VerifyElementsMeetOurSpeakersBlock",
    groups = {"full", "regression"})
public class EPMRDPT_90292_VerifyMeetOurSpeakersBlock {
  private static final String EVENT_NAME = "nix 1";
  private User user;
  private ReactEventDescriptionScreen reactEventDescriptionScreen;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of users with events management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_90292_VerifyMeetOurSpeakersBlock(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    reactEventDescriptionScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickEventsManagementLink()
        .waitAllSpinnersAreNotDisplayed()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton()
        .clickDescriptionButton();
  }

  @Test()
  public void verifyLabelDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactEventDescriptionScreen.isMeetOurSpeakersLabelDisplayed(),
        "'Meet our speakers' label is not displayed!");
    softAssert.assertTrue(reactEventDescriptionScreen.isSpeakerNumberLabelDisplayed(),
        "'Speaker number' label is not displayed!");
    softAssert.assertTrue(reactEventDescriptionScreen.isSpeakerRolePositionLabelDisplayed(),
        "'Role/Position' label is not displayed!");
    softAssert.assertTrue(reactEventDescriptionScreen.isSpeakerRoleDescriptionLabelDisplayed(),
        "'Speaker role' description is not displayed!");
    softAssert.assertAll();
  }

  @Test()
  public void verifyEnterTextPlaceholderDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactEventDescriptionScreen.isSpeakerNumberEnterTextPlaceholderDisplayed(),
        "'Speaker number' placeholder is not displayed!");
    softAssert.assertTrue(reactEventDescriptionScreen.isSpeakerRoleEnterTextPlaceholderDisplayed(),
        "'Speaker role' placeholder is not displayed!");
    softAssert.assertTrue(
        reactEventDescriptionScreen.isSpeakerRoleDescriptionEnterTextPlaceholderDisplayed(),
        "'Speaker role Description' placeholder is not displayed!");
    softAssert.assertAll();
  }

  @Test()
  public void verifyButtonDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactEventDescriptionScreen.isAddPhotoButtonDisplayed(),
        "'Add photo' button is not displayed!");
    softAssert.assertTrue(reactEventDescriptionScreen.isAddSpeakerButtonDisplayed(),
        "'Add speaker' button is not displayed!");
    softAssert.assertAll();
  }

  @Test()
  public void checkFileFormatAndSizeTooltipDisplayed() {
    assertEquals(reactEventDescriptionScreen.getToolTipTextByQuestionElement(),
        getValueOf(REACT_EVENTS_MANAGEMENT_DESCRIPTION_YOU_CAN_UPLOAD_PHOTO_REQUIREMENTS),
        "Uploaded photo file format and size tooltip is not displayed!");
  }

  @Test()
  public void checkSpeakerBucketIconDisplayed() {
    assertTrue(reactEventDescriptionScreen.isBucketOfSpeakerIconDisplayed(),
        "Bucket icon is not displayed!");
  }
}
