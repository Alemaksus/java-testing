package com.epmrdpt.regression.traininglist;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SubscribeScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22442_VerifyContentSubscribeToTrainingPopUp",
    groups = {"full", "general", "regression"})
public class EPMRDPT_22442_VerifyContentSubscribeToTrainingPopUp {

  private static final String EMAIL_PLACEHOLDER = "mail@example.com";
  private SoftAssert softAssert;
  private SubscribeScreen subscribeScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    subscribeScreen = new SubscribeScreen();
    new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink()
        .waitSkillCardsForVisibility();
  }

  @Test(priority = 1)
  public void checkSubscribePopUpAppearsAfterClickingOnSubscribeButton() {
    softAssert = new SoftAssert();
    new TrainingBlockScreen().clickSearchDropDown()
        .waitDropDownCountryVisibility();
    TrainingBlockScreen trainingBlockScreen = new TrainingCardsSectionService()
        .searchForNoAvailableTrainingsByCountryAndSkill();
    softAssert.assertTrue(trainingBlockScreen.isSubscribeButtonDisplayed(),
        "'Subscribe Button' isn't displayed after searching no available trainings "
            + "By Skills and Location!");
    softAssert.assertTrue(
        trainingBlockScreen.clickSubscribeButton().isScreenLoaded(),
        "'Subscribe to training' pop-up hasn't appeared!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkContentSubscribeScreenName() {
    assertEquals(
        subscribeScreen.getScreenNameText(),
        LocaleProperties.getValueOf(LocalePropertyConst.SUBSCRIBE_SCREEN_NAME),
        "Name of 'Subscribe' pop-up  has incorrect text!");
  }

  @Test(priority = 2)
  public void checkContentInfoText() {
    assertEquals(
        subscribeScreen.getInfoText(),
        LocaleProperties.getValueOf(LocalePropertyConst.SUBSCRIBE_INFO_TEXT),
        "Info text of 'Subscribe' pop-up has incorrect text!");
  }

  @Test(priority = 2)
  public void checkContentSubmitButton() {
    assertEquals(
        subscribeScreen.getSubmitButtonAttributeValue(),
        LocaleProperties.getValueOf(LocalePropertyConst.SUBSCRIBE_SUBMIT_BUTTON),
        "Submit button of 'Subscribe' pop-up has incorrect text!");
  }

  @Test(priority = 2)
  public void checkContentEmailInput() {
    assertEquals(
        subscribeScreen.getEmailInputAttributePlaceholder(),
        EMAIL_PLACEHOLDER,
        "Email field of 'Subscribe' pop-up has incorrect placeholder text!");
  }

  @Test(priority = 2)
  public void checkContentNameInput() {
    assertEquals(
        subscribeScreen.getNameInputAttributePlaceholder(),
        LocaleProperties.getValueOf(LocalePropertyConst.SUBSCRIBE_NAME_INPUT),
        "Name field of 'Subscribe' pop-up has incorrect placeholder text!");
  }

  @Test(priority = 2)
  public void checkContentSurnameInput() {
    assertEquals(
        subscribeScreen.getSurnameInputAttributePlaceholder(),
        LocaleProperties.getValueOf(LocalePropertyConst.SUBSCRIBE_SURNAME_INPUT),
        "Surname field of 'Subscribe' pop-up has incorrect placeholder text!");
  }

  @Test(priority = 2)
  public void checkLocationInputDisplayed() {
    assertTrue(
        subscribeScreen.isLocationInputDisplayed(),
        "Location field of 'Subscribe' pop-up isn't displayed!");
  }

  @Test(priority = 2)
  public void checkSkillInputDisplayed() {
    assertTrue(
        subscribeScreen.isSkillInputDisplayed(),
        "Skill field of 'Subscribe' pop-up isn't displayed!");
  }
}
