package com.epmrdpt.smoke.about;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_ARMENIA;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_BELARUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_GEORGIA;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_KAZAKHSTAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_LITHUANIA;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_POLAND;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_UKRAINE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_UZBEKISTAN;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34204_VerifyOurTrainingCentersSectionOnAboutPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34204_VerifyOurTrainingCentersSectionOnAboutPage {

  private AboutScreen aboutScreen;
  private final String OUR_TRAINING_CENTERS_TITLE = getValueOf(
      ABOUT_OUR_TRAINING_CENTERS_SECTION_TITLE);
  private final List<String> TRAINING_CENTERS_COUNTIES = Arrays
      .asList(getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_ARMENIA),
          getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_BELARUS),
          getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_KAZAKHSTAN),
          getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_POLAND),
          getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_UKRAINE),
          getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_UZBEKISTAN),
          getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_GEORGIA),
          getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_LITHUANIA));
  private final int MIN_NUMBER_FOR_ARROW_PRESENCE = 5;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test(priority = 1)
  public void checkAboutScreenLoading() {
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't load!");
  }

  @Test(priority = 2)
  public void checkThatHeaderDisplayAfterRedirectToAboutPage() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(aboutScreen
            .hoverSectionByTitle(OUR_TRAINING_CENTERS_TITLE)
            .getSectionTitleTextByTitle(OUR_TRAINING_CENTERS_TITLE)
            .equals(OUR_TRAINING_CENTERS_TITLE.toUpperCase()),
        "Our training centers section has another title!");
    for (String country : TRAINING_CENTERS_COUNTIES) {
      softAssert.assertTrue(
          aboutScreen.getTrainingCenterCountriesTextList().contains(country.toUpperCase()),
          "The " + country + " isn't displayed!");
      if (aboutScreen
          .clickTrainingCenterCountryByName(country)
          .getTrainingCenterActiveCountryCardsListSize() > MIN_NUMBER_FOR_ARROW_PRESENCE) {
        softAssert.assertTrue(aboutScreen.isActiveCountrySliderArrowsDisplayed(),
            "Arrows in the " + country + " isn't displayed!");
      }
    }
    softAssert.assertAll();
  }
}
