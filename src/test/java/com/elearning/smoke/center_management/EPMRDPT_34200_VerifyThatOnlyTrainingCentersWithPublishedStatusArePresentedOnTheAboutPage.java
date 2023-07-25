package com.epmrdpt.smoke.center_management;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34200_VerifyThatOnlyTrainingCentersWithPublishedStatusArePresentedOnTheAboutPage",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34200_VerifyThatOnlyTrainingCentersWithPublishedStatusArePresentedOnTheAboutPage {

  private final String countryAutoTest = "AutoTestCountry";
  private final String trainingCenterPublished = "AutoTestCity";
  private final String trainingCenterDraft = "AutoTestCityDelete";
  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitScreenLoaded()
        .clickAboutNavigationLink()
        .waitChosenCountryVisibility(countryAutoTest)
        .clickChosenCountry(countryAutoTest);
  }

  @Test
  public void checkThatTrainingCenterWithPublishedStatusDisplay() {
    assertTrue(aboutScreen.isTrainingCenterDisplayed(trainingCenterPublished),
        "Training Center with Published status didn't display!");
  }

  @Test
  public void checkThatTrainingCenterWithDraftStatusNotDisplay() {
    assertFalse(aboutScreen.isTrainingCenterDisplayed(trainingCenterDraft),
        "Training Center with Draft status displayed!");
  }
}
