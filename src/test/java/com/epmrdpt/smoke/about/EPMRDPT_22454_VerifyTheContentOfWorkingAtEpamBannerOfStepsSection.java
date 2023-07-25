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

@Test(description = "EPMRDPT_22454_VerifyTheContentOfWorkingAtEpamBannerOfStepsSection",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22454_VerifyTheContentOfWorkingAtEpamBannerOfStepsSection {

  private AboutScreen aboutScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkThatAboutPageLoaded() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkWorkingAtEpamSectionBackground() {
    assertTrue(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .isWorkingBannerHasCorrectBackground(),
        "Working at Epam section  has incorrect background!");
  }

  @Test(priority = 3)
  public void checkThatAllWorkingAtEpamCardsHaveIcon() {
    softAssert = new SoftAssert();
    for (int CardIndex = 0;
        CardIndex < aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOfferStepCardQuantity();
        CardIndex++) {
      softAssert.assertTrue(aboutScreen.isWorkingOfferStepCardHasIcon(CardIndex),
          String.format("Working at epam card -%s does't have icon!", CardIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatAllWorkingAtEpamCardsHaveArrowPattern() {
    softAssert = new SoftAssert();
    for (int cardIndex = 0;
        cardIndex < aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOfferStepCardQuantity(); cardIndex++) {
      softAssert.assertTrue(aboutScreen.isWorkingOfferStepCardHasArrowPattern(cardIndex),
          String.format("Working offer card %d hasn't arrow pattern!", cardIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatFirstWorkingOfferCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOffersStepCardDescriptionByIndex(0),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_WORKING_DESCRIPTION_ONE),
        "1st offer card has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatSecondWorkingOfferCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOffersStepCardDescriptionByIndex(1),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_WORKING_DESCRIPTION_TWO),
        "2nd offer card has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatThirdWorkingOfferCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOffersStepCardDescriptionByIndex(2),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_WORKING_DESCRIPTION_THREE),
        "3rd offer card has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatFourthWorkingOfferCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOffersStepCardDescriptionByIndex(3),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_WORKING_DESCRIPTION_FOUR),
        "4th offer card has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatFifthWorkingOfferCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOffersStepCardDescriptionByIndex(4),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_WORKING_DESCRIPTION_FIVE),
        "5th offer card has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatSixthWorkingOfferCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickWorkingLink().waitUntilWorkingAtEpamSectionLoaded()
            .getWorkingOffersStepCardDescriptionByIndex(5),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_WORKING_DESCRIPTION_SIX),
        "6th offer card has incorrect description!");
  }
}
