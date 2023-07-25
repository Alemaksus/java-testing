package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14524_VerifyThatUserCanSelectSkillForTrainingSearchThroughSearchForTrainingsFilterToggle",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_14524_VerifyThatUserCanSelectSkillForTrainingSearchThroughSearchForTrainingsFilterToggle {

  private static final String BUSINESS_ANALYSIS_SKILL = "Business Analysis";
  private static final String SKILL_SRC_VALUE = "Content/images/BigLogo/Business%20Analysis_Icon.png";

  @Test(priority = 1)
  public void checkTrainingListScreenLoading() {
    assertTrue(new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink()
        .waitTrainingCardsForVisibility()
        .isScreenLoaded(), "Training List screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkUserCanSelectSkillForTrainingSearchThroughSearchForTrainingsFilterToggle() {
    TrainingBlockScreen trainingBlockScreen = new TrainingBlockScreen();
    trainingBlockScreen.clickSearchDropDown()
        .waitSkillsFilterTabVisibility()
        .clickSkillsFiltersTab()
        .clickSkillCheckboxByName(BUSINESS_ANALYSIS_SKILL);
    assertTrue(trainingBlockScreen.isAllTrainingsContainSkillImage((getEnv() + SKILL_SRC_VALUE)),
        "Trainings did not appear after clicking on the checkbox in the search for trainings!");
  }
}
