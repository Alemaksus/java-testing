package com.epmrdpt.regression.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.SkillDescriptionScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_60430_VerifyThatUserCanBeRedirectedToParticularSkillDescriptionPageFromTrainingDescriptionPageByClickingMoreLink",
    groups = {"full", "general", "regression"})
public class EPMRDPT_60430_VerifyThatUserCanBeRedirectedToParticularSkillDescriptionPageFromTrainingDescriptionPageByClickingMoreLink {

  private SkillDescriptionScreen skillDescriptionScreen;
  private final String trainingName = "AutoTest_TrainingWithSkillDescription";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void trainingBlockOnStartScreenInitialization() {
    new TrainingBlockScreen().waitTrainingCardsVisibility();
    skillDescriptionScreen = new TrainingCardsSectionService()
        .openTrainingDescription(trainingName)
        .clickMoreLinkOnSkillsSection();
    skillDescriptionScreen.switchToLastWindow();
  }

  @Test
  public void checkThatSkillDescriptionPageOpened() {
    assertTrue(skillDescriptionScreen.isScreenLoaded(),
        "The skill description page isn't opened!");
  }
}
