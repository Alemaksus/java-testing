package com.epmrdpt.regression.traininglist;

import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingCenterOnStartScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34234_VerifyThatPreviousSkillFilterIsResetOnTrainingListAfterClickingLinkAvailableTraining ",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34234_VerifyPreviousSkillFilterIsResetAfterClickingAvailableTraining {

  private String trainingCountryName = "AutoTestCountry";
  private String trainingCityName = "AutoTestCity";
  private TrainingBlockScreen trainingBlockScreen;
  private CenterScreen centerScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupCenterScreen() {
    trainingBlockScreen = new TrainingCardsSectionService().setRandomCountryCitySkillsFilters();
    centerScreen = new TrainingCenterOnStartScreen()
        .clickTrainingCenterCountryByName(trainingCountryName)
        .clickTrainingCenterCityByName(trainingCityName);
  }

  @Test
  public void checkPreviousSkillsFilterIsReset() {
    SoftAssert softAssert = new SoftAssert();
    String availableTrainingName = centerScreen.getAvailableTrainingNameTextByIndex(0);
    centerScreen.clickAvailableTrainingsLinkByIndex(0);
    softAssert.assertTrue(trainingBlockScreen.getSelectedFilterItemLocationFieldText()
            .equalsIgnoreCase(trainingCityName) |
            /*
             *if there is the only one city in the country we'll see the country name in filters, not city
             */
            trainingBlockScreen.getSelectedFilterItemLocationFieldText()
                .equalsIgnoreCase(trainingCountryName),
        "Previous filter 'Location' is not reset!");
    softAssert.assertTrue(
        trainingBlockScreen.getSelectedFilterItemSkillFieldText().equals(availableTrainingName),
        "Previous filter 'Skill' is not reset!");
    softAssert.assertAll();
  }
}
