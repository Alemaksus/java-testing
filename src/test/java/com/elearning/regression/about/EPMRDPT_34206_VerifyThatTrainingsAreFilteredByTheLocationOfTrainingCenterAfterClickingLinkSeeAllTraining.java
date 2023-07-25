package com.epmrdpt.regression.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.AboutService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34206_VerifyThatTrainingsAreFilteredByTheLocationOfTrainingCenterAfterClickingLinkSeeAllTraining",
    groups = {"full", "regression"})
public class EPMRDPT_34206_VerifyThatTrainingsAreFilteredByTheLocationOfTrainingCenterAfterClickingLinkSeeAllTraining {

  private TrainingBlockScreen trainingBlockScreen;
  private final String countryOfCenter = "AutoTestCountry";
  private final String cityOfCenter = "AutoTestCity";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink()
        .waitForTrainingCenterSectionVisibility();
    AboutService aboutService = new AboutService();
    aboutService.openTrainingCenter(countryOfCenter, cityOfCenter);
    trainingBlockScreen = aboutService.openSeeAllTrainingsLink()
        .clickViewMoreTrainingsLink().waitTrainingCardListDisplayed();
  }

  @Test
  public void checkTrainingCardsFilteredByLocation() {
    assertTrue(trainingBlockScreen
            .isTrainingCardsFilteredByCountryAndCity(countryOfCenter, cityOfCenter),
        "Some cards with ordinary location distinguishing from '" + countryOfCenter + ","
            + cityOfCenter + "'!");
  }

  @Test
  public void checkMultiLocationTrainingCardsContainHintWithLocation() {
    assertTrue(trainingBlockScreen
            .isAllMultiLocationLabelHintsContainOneOfLocations(countryOfCenter, cityOfCenter),
        String.format(
            "Not all hints of multi-location cards contain either country '%s' or city '%s'!",
            countryOfCenter, cityOfCenter));
  }
}
