package com.epmrdpt.regression.profile;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12993_VerifyThatUserLocationIsAutomaticallyUploadedFromTheProfileToTheLocationsDropdown",
    groups = {"regression", "full", "deprecated"})
public class EPMRDPT_12993_VerifyThatUserLocationIsAutomaticallyUploadedFromTheProfileToTheLocationsDropdown {

  private HeaderScreen headerScreen;
  private String locationNameOfUser;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkHomePageIsOpenedScreenAfterLogin() {
    new LoginService().loginAndSetLanguage(UserFactory.withSimplePermission());
    assertTrue(headerScreen.isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkLocationPresenceInProfilePage() {
    ProfileScreen profileScreen = new ProfileScreen();
    headerScreen.clickProfileMenu().clickProfileButton();
    locationNameOfUser = profileScreen.waitTrainingInfoDisplayed().getResidenceFieldTitleText();
    assertTrue(profileScreen.isResidenceFieldTitleDisplayed(),
        "Location isn't present in profile page!");
  }

  @Test(priority = 3)
  public void checkHomePageIsOpenedFromProfilePage() {
    headerScreen.clickUniversityProgramLogo();
    assertTrue(headerScreen.isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 4)
  public void checkLocationSelectedInDropdownIsSameAsProfileLocation() {
    SoftAssert softAssert = new SoftAssert();
    TrainingBlockScreen trainingBlockScreen = new TrainingBlockScreen();
    String countryName = locationNameOfUser.split(", ")[0];
    String cityName = locationNameOfUser.split(", ")[1];
    softAssert.assertTrue(
        trainingBlockScreen.clickDropDownArrowButton().isFilterItemDisplayed(cityName),
        String.format("%s city name isn't displayed in filter section of locations!", cityName));
    softAssert
        .assertEquals(
            trainingBlockScreen.getDropDownHighlightedCountry(),
            countryName,
            String.format("%s country name isn't selected in locations dropdown!", countryName));
    softAssert.assertTrue(trainingBlockScreen.isCityCheckBoxSelected(cityName),
        String.format("%s city isn't selected in locations dropdown!", cityName));
    softAssert.assertAll();
  }
}
