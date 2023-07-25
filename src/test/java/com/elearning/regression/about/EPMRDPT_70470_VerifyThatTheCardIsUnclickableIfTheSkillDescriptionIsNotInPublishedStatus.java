package com.epmrdpt.regression.about;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SkillDescriptionScreen;
import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_70470_VerifyThatTheCardIsUnclickableIfTheSkillDescriptionIsNotInPublishedStatus",
    groups = {"full", "regression"})
public class EPMRDPT_70470_VerifyThatTheCardIsUnclickableIfTheSkillDescriptionIsNotInPublishedStatus {

  private AboutScreen aboutScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    aboutScreen = new AboutScreen();
  }

  @Test(priority = 1)
  public void checkAboutScreenIsOpened() {
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
    assertTrue(aboutScreen.isScreenLoaded(), "About page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkOurSkillsSectionIsDisplayed() {
    assertTrue(aboutScreen.isOurSkillsSectionIsDisplayed(),
        "Our Skills section isn't displayed!");
  }

  @Test(priority = 3)
  public void checkSkillCardDescriptionDisplayed() {
    assertEquals(aboutScreen.getNumberOfSkillCardsWithoutDescription(),
        aboutScreen.getAvailableSkillsCardsCount(),
        "Not all Skill Card's without descriptions are displayed!");
  }

  @Test(priority = 4)
  public void checkSkillDescriptionPageIsOpen() {
    int skillIndex = new Random().nextInt(aboutScreen.getAvailableSkillsCardsCount());
    SkillDescriptionScreen skillDescriptionScreen = new SkillDescriptionScreen();
    aboutScreen.clickSkillCardByIndex(skillIndex);
    assertFalse(skillDescriptionScreen.isSkillsDisplayed(),
        "Skill description page loaded");
  }
}
