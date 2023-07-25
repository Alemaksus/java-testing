package com.epmrdpt.regression.about;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.List;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14340_VerifyThatUserCanSearchLocationsInWhereWeAreSection",
    groups = {"full", "regression", "deprecated"})
public class EPMRDPT_14340_VerifyThatUserCanSearchLocationsInWhereWeAreSection {

  private AboutScreen aboutScreen;
  SoftAssert softAssert;
  private static String HIGHLIGHTED_COLOR_RGBA = "rgba(118, 205, 216, 1)";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    aboutScreen = new AboutScreen();
  }

  @Test(priority = 1)
  public void checkAboutScreenIsOpened() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't load!");
  }

  @Test(priority = 2)
  public void checkWhereAreWeSectionIsDisplayed() {
    assertTrue(aboutScreen.isMapSectionDisplayed(),
        "Where are we section isn't displayed!");
  }

  @Test(priority = 3)
  public void checkMapSectionWhenCountryIsSelected() {
    softAssert = new SoftAssert();
    String armeniaCountry = LocaleProperties
        .getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_ARMENIA_TAB);
    softAssert.assertEquals(aboutScreen.clickChosenCountryTab(armeniaCountry)
            .getCountryTabBackgroundColor(armeniaCountry),
        HIGHLIGHTED_COLOR_RGBA,
        "Background color of country tab isn't changed after clicking on it!");
    softAssert.assertTrue(aboutScreen.isMapDisplayed(),
        "Map section isn't displayed after selecting country!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkCitiesAreDisplayedWhenCountryIsSelected() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(aboutScreen.isLocationsListContainerDisplayed(),
        "Locations container of a country isn't unfolded after selecting country!");
    softAssert.assertTrue(aboutScreen.getCitiesCountFromSelectedCountry() > 0,
        "Cities under a country aren't listed!");
    softAssert.assertTrue(aboutScreen.getCitiesFromCountryList().stream()
            .allMatch(city -> aboutScreen.isArrowForCityDisplayed(city)),
        "Each City doesn't has an arrow button!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkCityTextWhenCityIsSelected() {
    softAssert = new SoftAssert();
    List<String> citiesOfCountry = aboutScreen.getCitiesFromCountryList();
    String cityToBeSelected = citiesOfCountry.get(new Random().nextInt(citiesOfCountry.size()));
    String cityTextColor = aboutScreen.getCityTextColor(cityToBeSelected);
    String cityTextColorAfterHover = aboutScreen.hoverOnCity(cityToBeSelected)
        .getCityTextColor(cityToBeSelected);
    softAssert.assertNotEquals(cityTextColor, cityTextColorAfterHover,
        "City text color isn't changed when hovered on it!");
    String cityTextColorAfterClick = aboutScreen.clickOnCity(cityToBeSelected)
        .getCityTextColor(cityToBeSelected);
    softAssert.assertNotEquals(cityTextColor, cityTextColorAfterClick,
        "City text color isn't changed when clicked on it!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkLocationsOfCitySelected() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(aboutScreen.getCountOfStreetsForASelectedCity() > 0,
        "Streets of a city aren't unfolded when city is selected!");
    softAssert.assertEquals(aboutScreen.getCountOfStreetNamesForSelectedCountry(),
        aboutScreen.getCountOfHatsOnMapForSelectedCountry(),
        "Each street location of country isn't marked with hat icon on map!");
    softAssert.assertAll();
  }
}
