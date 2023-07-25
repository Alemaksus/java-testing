package com.epmrdpt.regression.home;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SkillDescriptionScreen;
import com.epmrdpt.screens.TrainingListScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_74521_VerifyThatSkillInTheFilterCorrespondsToTheSkillOnTheChosenSkillCard",
    groups = {"full", "regression"})
public class EPMRDPT_74521_VerifyThatSkillInTheFilterCorrespondsToTheSkillOnTheChosenSkillCard {

  private SkillDescriptionScreen skillDescriptionScreen;
  private TrainingListScreen trainingListScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    skillDescriptionScreen = new HeaderScreen().waitScreenLoaded().clickSkillsListNavigationLink();
    trainingListScreen = new TrainingListScreen();
  }

  @Test(priority = 1)
  public void checkSkillsScreenDisplayed() {
    assertTrue(skillDescriptionScreen.isSkillScreenDisplayed(), "Skill Screen isn't  displayed!");
  }

  @Test(priority = 2)
  public void checkSkillCardDescriptionDisplayed() {
    assertEquals(skillDescriptionScreen.getNumberOfSkillCardsDescription(),
        skillDescriptionScreen.getAvailableSkillsCardsDescriptionCount(),
        "Not all Skill Card's descriptions are displayed!");
  }

  @Test(priority = 3)
  public void checkSkillDescriptionPageIsOpen() {
    SoftAssert softAssert = new SoftAssert();
    int skillIndex = new Random().nextInt(skillDescriptionScreen.getAvailableSkillsCardsCount());
    String skillsTitleText = skillDescriptionScreen.getSkillsTitleText(skillIndex);
    skillDescriptionScreen.clickAvailableTrainingButtonByIndex(skillIndex);
    softAssert.assertTrue(trainingListScreen.isSkillsCardTitleInTheSkillsFilterFieldDisplayed(),
        "Skill in filter isn't  displayed!");
    softAssert.assertEquals(trainingListScreen.getSkillTitleInTheSkillFilterFieldText(),
        skillsTitleText,
        "The skill in the training list section filter does not match the skill on the selected skill card");
    softAssert.assertAll();
  }
}
