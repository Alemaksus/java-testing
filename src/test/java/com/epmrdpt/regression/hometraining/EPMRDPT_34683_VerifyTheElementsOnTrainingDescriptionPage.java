package com.epmrdpt.regression.hometraining;

import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_LANGUAGE_ACRONYM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HOME_NAVIGATION_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_CONTACT_US;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_DIVE_INTO_ADVANCED_TECHNOLOGY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_HOW_TO_JOIN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_NOT_REQUIREMENTS_QUESTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_REQUIRED_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_TECHNICAL_REQUIREMENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_TRAINING_DETAILS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_TRAINING_LIST_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_VIEW;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_WANT_TO_SEE_DESCRIPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_WHO_IS_TRAINING_FOR;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34683_VerifyTheElementsOnTrainingDescriptionPage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34683_VerifyTheElementsOnTrainingDescriptionPage {

  private final String trainingName = "STI FOR autotest";

  private HeaderScreen headerScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingDescription() {
    headerScreen = new HeaderScreen();
    trainingDescriptionScreen = new TrainingCardsSectionService()
        .openTrainingDescription(trainingName);
  }

  @Test(priority = 1)
  public void checkHeaderContainerDisplayed() {
    assertTrue(headerScreen.isHeaderContainerDisplayed(),
        "Header container on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkLanguageAcronymDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(headerScreen.isLanguageAcronymDisplayed(),
        "Language isn't displayed!");
    softAssert.assertTrue(headerScreen.isLanguageControlDropdownDisplayed(),
        "Language DDL isn't displayed!");
    softAssert.assertTrue(headerScreen.getLanguageAcronymText().equalsIgnoreCase(
            LocaleProperties.getValueOf(HEADER_CONTAINER_LANGUAGE_ACRONYM)),
        "Language acronym has incorrect name!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkNavigationLinksHasCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getHomeNavigationLinkText(),
        LocaleProperties.getValueOf(HOME_NAVIGATION_LINK),
        "Home navigation link on Training description page has incorrect text!");
    softAssert.assertEquals(trainingDescriptionScreen.getTrainingListNavigationLinkText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_TRAINING_LIST_LINK),
        "Training list navigation link on Training description page has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkBannerBackgroundImageDisplayed() {
    assertTrue(trainingDescriptionScreen.isTrainingBannerPresent(),
        "Banner on Training description page isn't displayed correct!");
  }

  @Test(priority = 1)
  public void checkTrainingInformationBlockDisplayed() {
    assertTrue(trainingDescriptionScreen.isTrainingInformationSectionDisplayed(),
        "Training information block on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkContactPersonSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getContactTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_CONTACT_US),
        "Contact title on Training description page has incorrect text!");
    softAssert.assertFalse(trainingDescriptionScreen.getContactNameFieldText().isEmpty(),
        "Contact name on Training description page isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.isContactPhoneLinkDisplayed(),
        "Contact phone link on Training description page isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.isContactEmailLinkDisplayed(),
        "Contact email link on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkTrainingDetailsSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getTrainingDetailsTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_TRAINING_DETAILS),
        "Training details title on Training description page has incorrect text!");
    softAssert.assertFalse(trainingDescriptionScreen.getTrainingDetailsFieldText().isEmpty(),
        "Training details section on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkSkillDescriptionSection() {
    softAssert = new SoftAssert();
    softAssert.assertFalse(trainingDescriptionScreen.getTrainingDetailsTitleText().isEmpty(),
        "Skill description title on Training description page isn't displayed!");
    softAssert.assertFalse(trainingDescriptionScreen.getTrainingDetailsFieldText().isEmpty(),
        "Skill description section on Training description page isn't displayed!");
    softAssert.assertTrue(trainingDescriptionScreen.isSkillDescriptionBannerPresent(),
        "Skill description banner on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkRequiredSkillsSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getRequiredSkillsTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_REQUIRED_SKILLS),
        "Required skills title on Training description page has incorrect text!");
    softAssert.assertFalse(trainingDescriptionScreen.getRequiredSkillsFieldText().isEmpty(),
        "Required skills section on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkTechnicalRequirementsButtonHasCorrectText() {
    assertEquals(trainingDescriptionScreen.getTechnicalRequirementsButtonText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_TECHNICAL_REQUIREMENTS),
        "Technical requirements button on Training description page has incorrect text!");
  }

  @Test(priority = 1)
  public void checkNotMeetRequirementsSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getNotMeetRequirementsTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_NOT_REQUIREMENTS_QUESTION),
        "Not meet requirements title on Training description page has incorrect text!");
    softAssert.assertFalse(trainingDescriptionScreen.getNotMeetRequirementsFieldText().isEmpty(),
        "Not meet requirements section on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkWhoIsThisTrainingForSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getWhoIsTrainingForTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_WHO_IS_TRAINING_FOR),
        "For who training title on Training description page has incorrect text!");
    softAssert.assertFalse(trainingDescriptionScreen.getForWhoTrainingFieldText().isEmpty(),
        "Who is this training for section on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkMoreTrainingDetailsHasCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getMoreTrainingDetailsTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_WANT_TO_SEE_DESCRIPTION),
        "More training details title on Training description page has incorrect text!");
    softAssert.assertEquals(trainingDescriptionScreen.getViewMoreTrainingDetailsButtonText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_VIEW),
        "View more training details button on Training description page has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkVideoSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getVideoTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_DIVE_INTO_ADVANCED_TECHNOLOGY),
        "Video title on Training description page has incorrect text!");
    softAssert.assertTrue(trainingDescriptionScreen.isVideoContentDisplayed(),
        "Video content on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkHowToJoinSection() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingDescriptionScreen.getHowToJoinTitleText(),
        LocaleProperties.getValueOf(TRAINING_DESCRIPTION_HOW_TO_JOIN),
        "How to join title on Training description page has incorrect text!");
    softAssert.assertFalse(trainingDescriptionScreen.getHowToJoinFieldText().isEmpty(),
        "How to join section on Training description page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 1)
  public void checkPricingSection() {
    assertTrue(trainingDescriptionScreen.isPricingSectionDisplayed(),
        "Pricing section on Training description page isn't displayed!");
  }

  @Test(priority = 1)
  public void checkFooterContainerDisplayed() {
    assertTrue(new FooterScreen().isScreenLoaded(),
        "Footer container on Training description page isn't displayed!");
  }
}
