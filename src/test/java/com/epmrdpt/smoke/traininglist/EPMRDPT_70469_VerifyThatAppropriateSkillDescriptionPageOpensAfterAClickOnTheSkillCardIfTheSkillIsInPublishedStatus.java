package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertEquals;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SkillDescriptionScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_70469_VerifyThatAppropriateSkillDescriptionPageOpensAfterAClickOnTheSkillCardIfTheSkillIsInPublishedStatus",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_70469_VerifyThatAppropriateSkillDescriptionPageOpensAfterAClickOnTheSkillCardIfTheSkillIsInPublishedStatus {

  private HeaderScreen headerScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    headerScreen = new HeaderScreen();
  }

  @Test(priority = 1)
  public void checkSkillsTitleDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        headerScreen.isSkillsDisplayed());
    softAssert.assertEquals((
            headerScreen.getSkillsTitleText()),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_OUR_SKILLS_TITLE),
        "OUR SKILLS title has incorrect text!");
    softAssert.assertAll("Our Skills title is displayed incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSkillCardDescriptionDisplayed() {
    assertEquals(headerScreen.getNumberOfSkillCardsWithDescription(),
        headerScreen.getAvailableSkillsCardsCount(),
        "Not all Skill Card's descriptions are displayed!");
  }

  @Test(priority = 3)
  public void checkSkillDescriptionPageIsOpen() {
    softAssert = new SoftAssert();
    int skillIndex = new Random().nextInt(headerScreen.getAvailableSkillsCardsCount());
    SkillDescriptionScreen skillDescriptionScreen = new SkillDescriptionScreen();
    headerScreen.clickSkillCardByIndex(skillIndex);
    softAssert.assertTrue(skillDescriptionScreen.isSkillsDisplayed(),
        "Skill description page is not loaded");
    softAssert.assertTrue(skillDescriptionScreen.isDescriptionDisplayed(),
        "No skill description was created for this card");
    softAssert.assertAll();
  }
}
