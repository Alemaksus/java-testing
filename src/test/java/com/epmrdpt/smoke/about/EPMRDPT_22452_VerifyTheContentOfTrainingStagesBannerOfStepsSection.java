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

@Test(description = "EPMRDPT_22452_VerifyTheContentOfTrainingStagesBannerOfStepsSection",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22452_VerifyTheContentOfTrainingStagesBannerOfStepsSection {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkThatAboutPageLoaded() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page  isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatTrainingStagesSectionHasCorrectBackground() {
    assertTrue(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .isTrainingBannerHasCorrectBackground(),
        "Training stages section has incorrect background!");
  }

  @Test(priority = 2)
  public void checkThatTrainingStagesSectionHasCorrectTitle() {
    assertEquals(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .getTrainingStagesTitleText(),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_TRAINING_STAGES_TITLE),
        "Training stages section has incorrect title!");
  }

  @Test(priority = 3)
  public void checkThatAllTrainingStagesCardHaveIcons() {
    SoftAssert softAssert = new SoftAssert();
    for (int indexTrainingStageCard = 0;
        indexTrainingStageCard < aboutScreen.getTrainingStepCardsQuantity();
        indexTrainingStageCard++) {
      softAssert.assertTrue(
          aboutScreen.clickTrainingLink().isTrainingStepCardHasIcon(indexTrainingStageCard),
          String
              .format("Registration step card -%s does't have icon!", indexTrainingStageCard + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatFirstTrainingStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .getTrainingStepCardTitleByIndex(0),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_TRAINING_STAGES_STEP_ONE),
        "1st step has incorrect title!");
  }

  @Test(priority = 2)
  public void checkThatSecondTrainingStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .getTrainingStepCardTitleByIndex(1),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_TRAINING_STAGES_STEP_TWO),
        "2nd step has incorrect title!");
  }

  @Test(priority = 2)
  public void checkThatThirdTrainingStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .getTrainingStepCardTitleByIndex(2),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_TRAINING_STAGES_STEP_THREE),
        "3rd step has incorrect title!");
  }

  @Test(priority = 2)
  public void checkThatFirstTrainingStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .getTrainingStepCardDescriptionByIndex(0),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_TRAINING_STAGES_DESCRIPTION_STEP_ONE),
        "1st step has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatSecondTrainingStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .getTrainingStepCardDescriptionByIndex(1),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_TRAINING_STAGES_DESCRIPTION_STEP_TWO),
        "2nd step has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatThirdTrainingStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .getTrainingStepCardDescriptionByIndex(2),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_TRAINING_STAGES_DESCRIPTION_STEP_THREE),
        "3rd step has incorrect description!");
  }

  @Test(priority = 2)
  public void checkThatTrainingStepSectionHasBorder() {
    assertTrue(aboutScreen.clickTrainingLink().waitUntilTrainingStagesSectionLoaded()
            .isTrainingStagesSectionHasCorrectBorder(),
        "Training stages section has incorrect border!");
  }
}
