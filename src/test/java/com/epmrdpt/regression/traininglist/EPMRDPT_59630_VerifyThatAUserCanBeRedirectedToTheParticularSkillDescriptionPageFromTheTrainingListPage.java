package com.epmrdpt.regression.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SkillDescriptionScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_59630_VerifyThatAUserCanBeRedirectedToTheParticularSkillDescriptionPageFromTheTrainingListPage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_59630_VerifyThatAUserCanBeRedirectedToTheParticularSkillDescriptionPageFromTheTrainingListPage {

  private final String skillName = "DevOps";
  private SkillDescriptionScreen skillDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupSkillDescriptionScreen() {
    skillDescriptionScreen = new HeaderScreen().waitTrainingListNavigationLinkVisibility()
        .clickTrainingListNavigationLink()
        .clickOnSkillDescriptionCard(skillName);
  }

  @Test(priority = 1)
  public void checkThatScreenDescriptionPageIsOpened() {
    assertTrue(skillDescriptionScreen.isScreenLoaded(),
        "The skill description page isn't opened!");
  }
}

