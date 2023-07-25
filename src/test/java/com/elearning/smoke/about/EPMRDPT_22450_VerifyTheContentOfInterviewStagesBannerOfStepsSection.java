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

@Test(description = "EPMRDPT_22450_VerifyTheContentOfInterviewStagesBannerOfStepsSection",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22450_VerifyTheContentOfInterviewStagesBannerOfStepsSection {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkThatInterviewStagesSectionHasCorrectBackground() {
    assertTrue(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .isInterviewBannerHasCorrectBackground(),
        "Interview stages section has incorrect background!");
  }

  @Test(priority = 2)
  public void checkThatInterviewStagesSectionHasCorrectTitle() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStagesTitleText(),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_TITLE),
        "Interview stages section has incorrect title!");
  }

  @Test(priority = 3)
  public void checkThatAllInterviewStagesCardHaveIcons() {
    SoftAssert softAssert = new SoftAssert();
    for (int indexInterviewStageCard = 0;
        indexInterviewStageCard < aboutScreen.clickInterviewLink()
            .waitUntilInterviewStagesSectionLoaded().getInterviewStepCardsQuantity();
        indexInterviewStageCard++) {
      softAssert.assertTrue(aboutScreen.isInterviewStepCardHasIcon(indexInterviewStageCard),
          String.format("Registration step card -%s does't have icon!",
              indexInterviewStageCard + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatFirstInterviewStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardTitleByIndex(0),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_STEP_ONE),
        "1st step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatSecondInterviewStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardTitleByIndex(1),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_STEP_TWO),
        "2nd step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatThirdInterviewStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardTitleByIndex(2),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_STEP_THREE),
        "3rd step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatFourthInterviewStepCardHasCorrectTitle() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardTitleByIndex(3),
        LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_STEP_FOUR),
        "4th step isn't correct!");
  }

  @Test(priority = 2)
  public void checkThatFirstInterviewStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardDescriptionByIndex(0),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_DESCRIPTION_STEP_ONE),
        "1st step ins't correct!");
  }

  @Test(priority = 2)
  public void checkThatSecondInterviewStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardDescriptionByIndex(1),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_DESCRIPTION_STEP_TWO),
        "2nd step ins't correct!");
  }

  @Test(priority = 2)
  public void checkThatThirdInterviewStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardDescriptionByIndex(2),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_DESCRIPTION_STEP_THREE),
        "3rd step ins't correct!");
  }

  @Test(priority = 2)
  public void checkThatFourthInterviewStepCardHasCorrectDescription() {
    assertEquals(aboutScreen.clickInterviewLink().waitUntilInterviewStagesSectionLoaded()
            .getInterviewStepCardDescriptionByIndex(3),
        LocaleProperties.getValueOf(
            LocalePropertyConst.ABOUT_JOIN_US_SECTION_INTERVIEW_STAGES_DESCRIPTION_STEP_FOUR),
        "4th step ins't correct!");
  }
}
