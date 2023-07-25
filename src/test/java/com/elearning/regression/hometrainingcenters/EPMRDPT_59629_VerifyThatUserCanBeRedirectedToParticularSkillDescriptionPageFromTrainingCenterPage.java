package com.epmrdpt.regression.hometrainingcenters;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.SkillDescriptionScreen;
import com.epmrdpt.screens.TrainingCenterOnStartScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_59629_VerifyThatUserCanBeRedirectedToParticularSkillDescriptionPageFromTrainingCenterPage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_59629_VerifyThatUserCanBeRedirectedToParticularSkillDescriptionPageFromTrainingCenterPage {

  private final String countryOfCenter = getValueOf(COUNTRY_NAME_BELARUS);
  private final String cityOfCenter = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private final String skillName = "Automated Testing";
  private SkillDescriptionScreen skillDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupSkillCard() {
    skillDescriptionScreen = new TrainingCenterOnStartScreen()
        .clickTrainingCenterCountryByName(countryOfCenter)
        .clickTrainingCenterCityByName(cityOfCenter)
        .clickPublishedSkillCardByName(skillName);
  }

  @Test(priority = 1)
  public void checkThatSkillDescriptionPageOpened() {
    assertTrue(skillDescriptionScreen.isScreenLoaded(),
        "The skill description page isn't opened!");
  }

}