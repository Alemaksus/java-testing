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

@Test(description = "EPMRDPT_22468_VerifyThatAfterClickingOnTheUkraineButtonTheUkraineLocationsContainerSectionAppearsOnTheLeftSideOfTheMapArea",
    groups = {"full", "general", "smoke", "deprecated"})
public class EPMRDPT_22468_VerifyThatAfterClickingOnTheUkraineButtonTheUkraineLocationsContainerSectionAppearsOnTheLeftSideOfTheMapArea {

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
  public void checkUkraineContainerSectionAppears() {
    SoftAssert softAssert = new SoftAssert();
    String ukraineCountryNameOnSelectedLocale = LocaleProperties
        .getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_UKRAINE_TAB);
    List<String> citiesFromUkraineList = aboutScreen
        .clickChosenCountryTab(ukraineCountryNameOnSelectedLocale)
        .waitLocationsContainerSectionAppers()
        .getCitiesFromCountryList();
    softAssert.assertTrue(citiesFromUkraineList.size() > 0,
        "Cities not found in Ukraine!");
    for (String cityName : citiesFromUkraineList) {
      softAssert.assertTrue(new CountryContainerService()
              .areCheckingCityBelongsChosenCountry(ukraineCountryNameOnSelectedLocale, cityName),
          String.format("The '%s' isn't belonged Ukraine!", cityName));
    }
    softAssert.assertAll();
  }
}
