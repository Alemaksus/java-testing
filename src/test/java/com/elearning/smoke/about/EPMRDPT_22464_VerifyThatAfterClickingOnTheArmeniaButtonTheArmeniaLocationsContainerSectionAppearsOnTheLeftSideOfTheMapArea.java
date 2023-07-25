package com.epmrdpt.smoke.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.CountryContainerService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_22464_VerifyThatAfterClickingOnTheArmeniaButtonTheArmeniaLocationsContainerSectionAppearsOnTheLeftSideOfTheMapArea",
    groups = {"full", "general", "smoke", "deprecated"})
public class EPMRDPT_22464_VerifyThatAfterClickingOnTheArmeniaButtonTheArmeniaLocationsContainerSectionAppearsOnTheLeftSideOfTheMapArea {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkMapSectionPresents() {
    assertTrue(aboutScreen.isMapDisplayed(), "Map section isn't presented!");
  }

  @Test(priority = 3)
  public void checkArmeniaContainerSectionAppears() {
    SoftAssert softAssert = new SoftAssert();
    String armeniaCountryNameOnSelectedLocale = LocaleProperties
        .getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_ARMENIA_TAB);
    List<String> citiesFromArmeniaList = aboutScreen
        .clickChosenCountryTab(armeniaCountryNameOnSelectedLocale)
        .waitLocationsContainerSectionAppers()
        .getCitiesFromCountryList();
    softAssert.assertTrue(citiesFromArmeniaList.size() > 0,
        "Cities not found in Armenia!");
    for (String cityName : citiesFromArmeniaList) {
      softAssert.assertTrue(new CountryContainerService()
              .areCheckingCityBelongsChosenCountry(armeniaCountryNameOnSelectedLocale, cityName),
          String.format("The '%s' isn't belonged Armenia!", cityName));
    }
    softAssert.assertAll();
  }
}
