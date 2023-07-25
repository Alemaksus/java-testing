package com.epmrdpt.regression.hometraining;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22272_VerifyDreamTrainingPopUpIsDisplayedWhenWeSubscribeToTrainings",
    groups = {"full", "general", "regression"})
public class EPMRDPT_22272_VerifyDreamTrainingPopUpIsDisplayedWhenWeSubscribeToTrainings {

  public static final String EMBEDDED_SYSTEMS_SKILL = "Embedded Systems";

  private TrainingBlockScreen trainingBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    trainingBlockScreen = new TrainingBlockScreen()
        .clickTrainingsTitle()
        .clickSearchDropDown()
        .clickSkillsFiltersTab()
        .clickSkillCheckboxByName(EMBEDDED_SYSTEMS_SKILL)
        .clickLocationFiltersTab()
        .clickCountryByNameFromDropDown(getValueOf(COUNTRY_NAME_BELARUS))
        .clickCityByNameFromDropDown(getValueOf(CITY_NAME_BELARUS_GOMEL))
        .clickDropDownArrowButton();
  }

  @Test
  public void checkIfDreamTrainingPopUpDisplayed() {
    assertTrue(trainingBlockScreen.waitSubscribeButtonVisibility().hoverOverSubscribeButton()
        .clickSubscribeButton().isScreenLoaded(), "Dream training pop up did not appear!");
  }
}
