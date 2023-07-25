package com.epmrdpt.regression.center_management;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34263_VerifyTrainingCenterCardsContainAllRequiredElements",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34263_VerifyTrainingCenterCardsContainAllRequiredElements {

  private final String expectedBackgroundColor = "rgba(118, 205, 216, 1)";
  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    aboutScreen = new AboutScreen();
    new HeaderScreen().clickAboutNavigationLink();
  }

  @Test
  public void checkTrainingCenterCardsContainCityName() {
    SoftAssert softAssert = new SoftAssert();
    int countryCount = aboutScreen.getTrainingCentersCountryLinksListSize();
    for (int countryIndex = 0; countryIndex < countryCount; countryIndex++) {
      softAssert.assertEquals(aboutScreen.getTrainingCenterActiveCountryCityNamesListSize(),
          aboutScreen.getTrainingCenterActiveCountryCardsListSize(),
          String.format("Not all training center cards contain city name from %s country!",
              aboutScreen.getTrainingCentersCountryLinkByIndex(countryIndex)));
      softAssert.assertEquals(aboutScreen.getTrainingCenterActiveCountryPreviewImagesListSize(),
          aboutScreen.getTrainingCenterActiveCountryCardsListSize(),
          String.format("Not all training center cards contain preview image from %s country!",
              aboutScreen.getTrainingCentersCountryLinkByIndex(countryIndex)));
      softAssert.assertTrue(aboutScreen
              .areTrainingCenterActiveCountryCityNameButtonsHaveCorrectColor(
                  expectedBackgroundColor),
          String.format(
              "Not all cities from training center cards contain correct background from %s country!",
              aboutScreen.getTrainingCentersCountryLinkByIndex(countryIndex)));
      aboutScreen.clickTrainingCentersCountryLinkByIndex(countryIndex);
    }
    softAssert.assertAll();
  }
}
