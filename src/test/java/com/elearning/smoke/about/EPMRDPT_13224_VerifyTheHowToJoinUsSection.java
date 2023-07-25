package com.epmrdpt.smoke.about;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13224_VerifyTheHowToJoinUsSection",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13224_VerifyTheHowToJoinUsSection {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page didn't load!");
  }

  @Test(priority = 2)
  public void checkThatJoinUsTitleDisplayed() {
    assertTrue(aboutScreen.isJoinUsTitleDisplayed(),
        "'Join us' title on about page didn't display!");
  }

  @Test(priority = 2)
  public void checkThatJoinUsTitleCorrect() {
    String actualTitle = aboutScreen.getJoinUsTitleText();
    assertEquals(actualTitle, LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_JOIN_US_TITLE),
        format("'Join us' title isn't correct! Expected title - %s, but actual title - %s",
            LocalePropertyConst.ABOUT_JOIN_US_TITLE, actualTitle));
  }

  @Test(priority = 2)
  public void checkThatRegistrationStepDisplayed() {
    assertTrue(aboutScreen.isRegistrationStepLinkDisplayed(),
        "Registration step on join us container didn't display!");
  }

  @Test(priority = 2)
  public void checkThatRegistrationStepCorrect() {
    String actualRegistrationStepTitle = aboutScreen.getRegistrationStepText();
    assertEquals(actualRegistrationStepTitle,
        LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_REGISTRATION_STEP),
        format("Registration step isn't correct! Expected text - %s, actual text - %s",
            LocalePropertyConst.ABOUT_REGISTRATION_STEP, actualRegistrationStepTitle));
  }

  @Test(priority = 2)
  public void checkThatInterviewStepDisplayed() {
    assertTrue(aboutScreen.isInterviewStagesLinkDisplayed(),
        "Interview stages on join us container didn't display!");
  }

  @Test(priority = 2)
  public void checkThatInterviewStepCorrect() {
    String actualInterviewStep = aboutScreen.getInterviewStagesText();
    assertEquals(actualInterviewStep,
        LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_INTERVIEW_STAGES),
        format("Interview stages isn't correct! Expected text - %s, actual text - %s",
            LocalePropertyConst.ABOUT_INTERVIEW_STAGES, actualInterviewStep));
  }

  @Test(priority = 2)
  public void checkThatTrainingStagesDisplayed() {
    assertTrue(aboutScreen.isTrainingStagesLinkDisplayed(),
        "Training stages on join us container didn't display!");
  }

  @Test(priority = 2)
  public void checkThatTrainingStageCorrect() {
    String actualTrainingText = aboutScreen.getTrainingStagesText();
    assertEquals(actualTrainingText,
        LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_TRAINING_STAGES),
        format("Training stages isn't correct! Expected text - %s, actual text - %s",
            LocalePropertyConst.ABOUT_TRAINING_STAGES, actualTrainingText));
  }

  @Test(priority = 2)
  public void checkThatWorkingAtEpamDisplayed() {
    assertTrue(aboutScreen.isWorkingOnEpamLinkDisplayed(),
        "Working at epam on join us container didn't display!");
  }

  @Test(priority = 2)
  public void checkThatWorkingAtEpamCorrect() {
    String actualText = aboutScreen.getWorkingOnEpamText();
    assertEquals(actualText, LocaleProperties.getValueOf(LocalePropertyConst.ABOUT_WORKING_AT_EPAM),
        format("'Work at epam' title isn't correct! Expected text - %s, actual text - %s",
            LocalePropertyConst.ABOUT_WORKING_AT_EPAM, actualText));
  }

  @Test(priority = 2)
  public void checkThatBannerContainerDisplayed() {
    assertTrue(aboutScreen.isBannerOfStepsDisplayed(), "Container of banner didn't display!");
  }
}
