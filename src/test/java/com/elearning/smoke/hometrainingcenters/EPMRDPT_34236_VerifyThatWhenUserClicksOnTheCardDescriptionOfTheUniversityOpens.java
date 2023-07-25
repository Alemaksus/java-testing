package com.epmrdpt.smoke.hometrainingcenters;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.TrainingCenterOnStartScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34236_VerifyThatWhenTheUserClicksOnTheCardDescriptionOfTheUniversityOpensBelowTheCard",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34236_VerifyThatWhenUserClicksOnTheCardDescriptionOfTheUniversityOpens {

  private final String countryOfCenter = "AutoTestCountry";
  private final String cityOfCenter = "AutoTestCity";
  private CenterScreen centerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    centerScreen = new TrainingCenterOnStartScreen()
        .clickTrainingCenterCountryByName(countryOfCenter)
        .clickTrainingCenterCityByName(cityOfCenter);
  }

  @Test(priority = 1)
  public void checkThatUniversityAddedToTrainingCenter() {
    assertTrue(centerScreen.isUniversityCardDisplayed(),
        "University isn't added to Training Center!");
  }

  @Test(priority = 2)
  public void checkThatDescriptionOfUniversityOpened() {
    assertTrue(centerScreen.clickUniversityCard().isUniversityDescriptionDisplayed(),
        "The description of the university isn't presented!");
  }
}
