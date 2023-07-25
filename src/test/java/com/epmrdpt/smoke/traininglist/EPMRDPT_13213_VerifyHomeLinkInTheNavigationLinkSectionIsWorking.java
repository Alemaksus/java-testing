package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingListScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13213_VerifyHomeLinkInTheNavigationLinkSectionIsWorking",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13213_VerifyHomeLinkInTheNavigationLinkSectionIsWorking {

  private TrainingListScreen trainingListScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingListScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink();
  }

  @Test(priority = 1)
  public void checkTrainingScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(), "Training screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkHomeNavigateLinkIsHighlightedWhenHoverOver() {
    assertFalse(trainingListScreen.getHomeNavigateLinkColor()
            .equals(trainingListScreen.getHoveredHomeNavigateLinkColor()),
        "Home navigation link doesn't change color!");
  }

  @Test(priority = 3)
  public void checkThatAfterClickingOnHomeLinkOnTrainingListRedirectsHomeScreen() {
    assertTrue(trainingListScreen.clickHomeNavigateLink().isScreenLoaded(),
        "After clicking on the 'Home' link on the 'Training List' page the portal doesn't redirect to 'Home' pag!");
  }
}
