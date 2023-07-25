package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingListScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_22433_VerifyAfterUploadingAllOurSkillsSectionViewMoreSkillsLinkNotDisplayed",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_22433_VerifyAfterUploadingAllOurSkillsSectionViewMoreSkillsLinkNotDisplayed {

  private TrainingListScreen trainingListScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    trainingListScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink();
  }

  @Test(priority = 1)
  public void checkTrainingScreenLoading() {
    assertTrue(trainingListScreen.isScreenLoaded(), "Training screen doesn't load!");
  }

  @Test(priority = 2)
  public void checkAfterUploadingAllOurSkillsSectionViewMoreSkillsLinkNotDisplayed() {
    trainingListScreen.hoverViewMoreSkillsLink().clickViewMoreSkillsLink();
    assertTrue(
        trainingListScreen.isViewMoreSkillsLinkAbsent(),
        "After uploading all skills on 'OUR SKILLS' section the 'VIEW MORE SKILLS' link isn't disappeared!");
  }
}
