package com.epmrdpt.smoke.center_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34205_VerifyThatTrainingCenterPageIsAvailableAfterClickingOnTheCardOfCorrespondingCenter",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34205_VerifyThatTrainingCenterPageIsAvailableAfterClickingOnTheCardOfCorrespondingCenter {

  private final String country = getValueOf(COUNTRY_NAME_BELARUS);
  private final String trainingCenter = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private CenterScreen centerScreen;
  private HeaderScreen headerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    centerScreen = new CenterScreen();
    headerScreen = new HeaderScreen();
    headerScreen.clickAboutNavigationLink()
        .waitChosenCountryVisibility(country)
        .clickChosenCountry(country)
        .clickChosenTrainingCenter(trainingCenter)
        .waitCenterTitleForVisibility();
  }

  @Test(priority = 1)
  public void checkTrainingCenterScreenLoading() {
    assertTrue(centerScreen.isScreenLoaded(),
        "Training Center screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkTrainingCenterScreenContainsHeader() {
    assertTrue(headerScreen.isHeaderContainerDisplayed(),
        "Training Center page doesn't contain 'Header' section!");
  }

  @Test(priority = 2)
  public void checkHomeNavigationLinkDisplayedCorrectly() {
    assertEquals(centerScreen.getHomeNavigationLinkText(), LocaleProperties.getValueOf(
        LocalePropertyConst.HOME_NAVIGATION_LINK), "'Home' link isn't displayed correctly!");
  }

  @Test(priority = 2)
  public void checkAboutNavigationLinkDisplayedCorrectly() {
    assertEquals(centerScreen.getAboutNavigationLinkText(), LocaleProperties.getValueOf(
        LocalePropertyConst.HEADER_CONTAINER_ABOUT), "'About' link isn't displayed correctly!");
  }

  @Test(priority = 2)
  public void checkTrainingCenterScreenContainsTitle() {
    assertTrue(centerScreen.isCenterTitleDisplayed(),
        "Training Center page doesn't contain 'Title' section!");
  }

  @Test(priority = 2)
  public void checkTrainingCenterScreenContainsDescription() {
    assertTrue(centerScreen.isCenterDescriptionDisplayed(),
        "Training Center page doesn't contain 'Description' section!");
  }

  @Test(priority = 2)
  public void checkTrainingCenterScreenContainsCooperationWithTheUniversities() {
    assertTrue(centerScreen.isCenterCooperationWithTheUniversitiesDisplayed(),
        "Training Center page doesn't contain 'Cooperation with the universities' section!");
  }

  @Test(priority = 2)
  public void checkTrainingCenterScreenContainsCurrentSkills() {
    assertTrue(centerScreen.isCurrentSkillsSectionDisplayed(),
        "Training Center page doesn't contain 'Current Skills'!");
  }

  @Test(priority = 2)
  public void checkSeeAllTrainingLinkDisplayedCorrectly() {
    assertEquals(centerScreen.getSeeAllTrainingLinkText(), LocaleProperties.getValueOf(
            LocalePropertyConst.SEE_ALL_TRAINING_LINK),
        "'SEE ALL TRAINING' link isn't displayed correctly!");
  }

  @Test(priority = 2)
  public void checkTrainingCenterScreenContainsFeedback() {
    assertTrue(centerScreen.isFeedbackSectionDisplayed(),
        "Training Center' page doesn't contain 'Feedback' section!");
  }

  @Test(priority = 2)
  public void checkTrainingCenterScreenContainsFooterSection() {
    assertTrue(new FooterScreen().isScreenLoaded(),
        "Training Center' page doesn't contain 'Footer' section!");
  }
}
