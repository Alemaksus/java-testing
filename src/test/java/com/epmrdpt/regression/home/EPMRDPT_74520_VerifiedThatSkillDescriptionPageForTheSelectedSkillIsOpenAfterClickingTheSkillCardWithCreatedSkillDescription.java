package com.epmrdpt.regression.home;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SkillDescriptionScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_74520_VerifiedThatSkillDescriptionPageForTheSelectedSkillIsOpenAfterClickingTheSkillCardWithCreatedSkillDescription",
    groups = {"full", "general", "regression"})
public class EPMRDPT_74520_VerifiedThatSkillDescriptionPageForTheSelectedSkillIsOpenAfterClickingTheSkillCardWithCreatedSkillDescription {

  private SkillDescriptionScreen skillDescriptionScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    skillDescriptionScreen = new HeaderScreen().waitScreenLoaded().clickSkillsListNavigationLink();
  }

  @Test(priority = 1)
  public void checkSkillsScreenDisplayed() {
    assertTrue(skillDescriptionScreen.isSkillScreenDisplayed(), "Skill Screen isn't  displayed!");
  }

  @Test(priority = 2)
  public void checkSkillCardDescriptionDisplayed() {
    assertEquals(skillDescriptionScreen.getNumberOfSkillCardsWithDescription(),
        skillDescriptionScreen.getAvailableSkillsCardsWithDescriptionCount(),
        "Not all Skill Card's descriptions are displayed!");
  }

  @Test(priority = 3)
  public void checkSkillDescriptionPageIsOpen() {
    SoftAssert softAssert = new SoftAssert();
    int skillIndex = new Random().nextInt(
        skillDescriptionScreen.getAvailableSkillsCardsWithDescriptionCount());
    skillDescriptionScreen.clickSkillCardByIndex(skillIndex);
    softAssert.assertTrue(skillDescriptionScreen.isSkillsDisplayed(),
        "Skill description page not loaded");
    softAssert.assertTrue(skillDescriptionScreen.isDescriptionDisplayed(),
        "No skill description was created for this card");
    softAssert.assertAll();
  }
}
