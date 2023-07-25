package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22447_VerifyTheContentOfRegistrationBannerOfStepsSection",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22447_VerifyTheContentOfRegistrationBannerOfStepsSection {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't load!");
  }

  @Test(priority = 2)
  public void checkThatFirsRegistrationStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.getRegistrationStepCardTitleByIndex(0),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_STEP_ONE),
        "1st step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatSecondRegistrationStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.getRegistrationStepCardTitleByIndex(1),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_STEP_TWO),
        "2nd step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatThirdRegistrationStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.getRegistrationStepCardTitleByIndex(2),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_STEP_THREE),
        "3rd step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatFourthRegistrationStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.getRegistrationStepCardTitleByIndex(3),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_STEP_FOUR),
        "4th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatFifthRegistrationStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.getRegistrationStepCardTitleByIndex(4),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_STEP_FIVE),
        "5th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatSixthRegistrationStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.getRegistrationStepCardTitleByIndex(5),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_STEP_SIX),
        "6th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatSeventhRegistrationStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.getRegistrationStepCardTitleByIndex(6),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_STEP_SEVEN),
        "7th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatFirstRegistrationStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.getRegistrationStepCardDescriptionByIndex(0),
        LocaleProperties
            .getValueOf(
                LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_DESCRIPTION_STEP_ONE),
        "Description of 1st step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatSecondRegistrationStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.getRegistrationStepCardDescriptionByIndex(1),
        LocaleProperties
            .getValueOf(
                LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_DESCRIPTION_STEP_TWO),
        "Description of 2nd step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatThirdRegistrationStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.getRegistrationStepCardDescriptionByIndex(2),
        LocaleProperties
            .getValueOf(
                LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_DESCRIPTION_STEP_THREE),
        "Description of 3rd step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatFourthRegistrationStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.getRegistrationStepCardDescriptionByIndex(3),
        LocaleProperties
            .getValueOf(
                LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_DESCRIPTION_STEP_FOUR),
        "Description of 4th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatFifthRegistrationStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.getRegistrationStepCardDescriptionByIndex(4),
        LocaleProperties
            .getValueOf(
                LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_DESCRIPTION_STEP_FIVE),
        "Description of 5th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatSixthRegistrationStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.getRegistrationStepCardDescriptionByIndex(5),
        LocaleProperties
            .getValueOf(
                LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_DESCRIPTION_STEP_SIX),
        "Description of 6th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatSeventhRegistrationStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.getRegistrationStepCardDescriptionByIndex(6),
        LocaleProperties
            .getValueOf(
                LocalePropertyConst.ABOUT_JOIN_US_SECTION_REGISTRATION_DESCRIPTION_STEP_SEVEN),
        "Description of 7th step isn't correct!");
  }

  @Test(priority = 3)
  public void checkThatAllRegistrationStepCardsHaveIcons() {
    SoftAssert softAssert = new SoftAssert();
    for (int indexRegistrationStepCard = 0;
        indexRegistrationStepCard < aboutScreen.getRegistrationStepCardsQuantity();
        indexRegistrationStepCard++) {
      softAssert.assertTrue(aboutScreen.isRegistrationStepCardHasIcon(indexRegistrationStepCard),
          String.format("Registration step card -%s does't have icon!",
              indexRegistrationStepCard + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatRegistrationSectionBackgroundCorrect() {
    assertTrue(aboutScreen.isRegistrationBannerHasCorrectBackground(),
        "Registration section hasn't incorrect background!");
  }
}
