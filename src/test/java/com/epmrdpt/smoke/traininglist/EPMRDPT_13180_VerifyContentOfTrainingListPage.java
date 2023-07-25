package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingListScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13180_VerifyContentOfTrainingListPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13180_VerifyContentOfTrainingListPage {

  private HeaderScreen headerScreen;
  private TrainingListScreen trainingListScreen;
  private TrainingBlockScreen trainingBlockScreen;
  private FooterScreen footerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
    trainingListScreen = headerScreen.waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink();
    trainingBlockScreen = new TrainingBlockScreen();
    footerScreen = new FooterScreen();
  }

  @Test(priority = 1)
  public void checkTrainingScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(), "Training screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainingScreenContainsHeader() {
    assertTrue(
        headerScreen.isHeaderContainerDisplayed(),
        "Training List page doesn't contain 'Header' section!");
  }

  @Test(priority = 2)
  public void checkHomeNavigationLinkDisplayedCorrectly() {
    assertEquals(trainingListScreen.getHomeNavigationLinkText(),
        LocaleProperties.getValueOf(LocalePropertyConst.HOME_NAVIGATION_LINK),
        "'Home' link isn't displayed correctly!");
  }

  @Test(priority = 2)
  public void checkTrainingScreenContainsBanner() {
    assertTrue(
        trainingListScreen.isBannerDisplayed(),
        "Training List page doesn't contain 'Banner' section!");
  }

  @Test(priority = 2)
  public void checkTrainingScreenContainsOurSkillsSection() {
    assertTrue(
        trainingListScreen.isOurSkillSectionDisplayed(),
        "Training List' page doesn't contain 'Our Skills' section!");
  }

  @Test(priority = 2)
  public void checkTrainingScreenContainsTrainingsSection() {
    assertTrue(
        trainingBlockScreen.isTrainingSectionDisplayed(),
        "Training List' page doesn't contain 'Training' section!");
  }

  @Test(priority = 2)
  public void checkTrainingScreenContainsViewMoreTrainingsLink() {
    assertTrue(
        trainingBlockScreen.isViewMoreTrainingsLinkDisplayed(),
        "Training List page doesn't contain 'View more trainings ‘number’' link!");
  }

  @Test(priority = 2)
  public void checkTrainingScreenContainsFooterSection() {
    assertTrue(
        footerScreen.isScreenLoaded(),
        "Training List' page doesn't contain 'Footer' section!");
  }

  @Test(priority = 2)
  public void checkTrainingScreenContainsFeedback() {
    assertTrue(
        footerScreen.isFeedbackDisplayed(),
        "Training List' page doesn't contain 'Feedback' section!");
  }
}
