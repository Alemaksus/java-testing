package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Test(description = "EPMRDPT_34284_VerifyThatTheUserCanFilterAllTrainingProgramsByAttributes",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34284_VerifyThatTheUserCanFilterAllTrainingProgramsByAttributes {

  private TrainingBlockScreen trainingBlockScreen;
  private final String countryName = getValueOf(COUNTRY_NAME_BELARUS);
  private final String cityName = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private final String skillName = "Automated Testing";
  private final String trainingName = "AUTO_Training_For_Finding";
  private int numberOfTrainingsCards;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink()
        .waitSkillCardsForVisibility();
    trainingBlockScreen = new TrainingBlockScreen().clickViewMoreTrainingsLink();
    numberOfTrainingsCards = trainingBlockScreen.getTrainingsCardsCount();
  }

  @Test(priority = 1)
  public void checkLocationFilterTab() {
    trainingBlockScreen
        .clickSearchDropDown()
        .clickLocationFiltersTab()
        .clickCountryByNameFromDropDown(countryName)
        .clickCityByNameFromDropDown(cityName)
        .clickSkillsFiltersTab()
        .clickSkillCheckboxByName(skillName);
    Assert.assertTrue(trainingBlockScreen.getTrainingsCardsCount() < numberOfTrainingsCards,
        "Applying the location filter did not work");
  }

  @Test(priority = 2)
  public void checkSkillsFilterTab() {
    trainingBlockScreen
        .clickSkillsFiltersTab()
        .clickSkillCheckboxByName(skillName);
    Assert.assertTrue(trainingBlockScreen.getTrainingsCardsCount() < numberOfTrainingsCards,
        "Applying the skills filter did not work");
  }

  @Test(priority = 3)
  public void checkTypesFilterTab() {
    trainingBlockScreen
        .clickTypesFiltersTab();
    Assert.assertTrue(new ReactTrainingManagementService().selectMoreSearchCheckboxesByType()
            .clickSearchDropDown().clickTrainingCardByName(trainingName).isScreenLoaded(),
        "Applying the types filter did not work");
  }
}

