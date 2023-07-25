package com.epmrdpt.smoke.traininglist;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.SkillDescriptionScreen;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13179_VerifyTheContentOfSkillsListSection",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13179_VerifyTheContentOfSkillsListSection {

  private SkillDescriptionScreen skillsListScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    skillsListScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickSkillsListNavigationLink();
  }

  @Test
  public void checkOurSkillsTitleDisplaying() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(
        skillsListScreen.isOurSkillsTittleDisplayed(), "Our Skills title isn't displayed!");
    softAssert.assertEquals(
        skillsListScreen.getOurSkillsTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_OUR_SKILLS_TITLE),
        "OUR SKILLS title has incorrect text!");
    softAssert.assertAll("Our Skills title is displayed incorrect!");
  }

  @Test
  public void checkSkillCardsDisplaying() {
    softAssert = new SoftAssert();
    List<Element> skillCardsList = skillsListScreen.getSkillCardsList();
    for (Element element : skillCardsList) {
      softAssert.assertTrue(
          skillsListScreen.isDisplayed(element),
          format("Element %s isn't displayed!", skillCardsList.indexOf(element)));
    }
    softAssert.assertAll("Our Skills Cards aren't displayed!");
  }

  @Test
  public void checkSkillCardLogoDisplaying() {
    assertTrue(
        skillsListScreen.isAllSkillCardIconsDisplayed(),
        "Not all Skill Card's logos are displayed!");
  }

  @Test
  public void checkSkillCardTitleDisplaying() {
    assertTrue(
        skillsListScreen.isAllSkillCardITitlesDisplayed(),
        "Not all Skill Card's titles are displayed!");
  }

  @Test
  public void checkSkillCardDescriptionDisplaying() {
    assertEquals(skillsListScreen.countSkillCardsWithDescriptionDisplayed(
            skillsListScreen.getSkillsCardsDescriptionList()),
        skillsListScreen.getAvailableSkillsCardsCount(),
        "Not all Skill Card's descriptions are displayed!");
  }

  @Test
  public void checkSkillAvailableTrainingsDisplaying() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        skillsListScreen.isAllAvailableTrainingsLinksDisplayed(),
        "Not all 'Available Trainings' links are displayed!");
    softAssert.assertEquals(
        skillsListScreen.getAvailableTrainingsLinkText(),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_LIST_AVAILABLE_TRAININGS_LINK),
        "'Available Trainings' link is displayed incorrect!");
    softAssert.assertAll();
  }
}
