package com.epmrdpt.regression.training_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.TrainingCenterOnStartScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34233_VerifyThatUserCanOpenAnotherCardOnFastFactsTabByClickingTheCorrespondingDot",
    groups = {"full", "general", "regression", "deprecated"})
public class EPMRDPT_34233_VerifyThatUserCanOpenAnotherCardOnFastFactsTabByClickingTheCorrespondingDot {

  private final String countryOfCenter = "AutoTestCountry";
  private final String cityOfCenter = "AutoTestCity";
  private CenterScreen centerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void openTrainingCenter() {
    centerScreen = new TrainingCenterOnStartScreen()
        .clickTrainingCenterCountryByName(countryOfCenter)
        .clickTrainingCenterCityByName(cityOfCenter);
  }

  @Test
  public void checkThatOpenAnotherCardOnFastFactsTab() {
    int navigationButtonForFastFactsCount = centerScreen.getCarouselNavigationForFastFactsQuantity();
    for (int buttonDotIndex = 0; buttonDotIndex < navigationButtonForFastFactsCount;
        buttonDotIndex++) {
      centerScreen.clickCarouselNavigationButtonForFastFactByIndex(buttonDotIndex);
      assertTrue(centerScreen.isCarouselNavigationButtonActive(buttonDotIndex),
          String.format("Fast fact by %s navigation button isn't displayed!", buttonDotIndex + 1));
    }
  }
}
