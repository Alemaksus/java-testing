package com.epmrdpt.regression.admin;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactCreateSkillDescriptionScreen;
import com.epmrdpt.screens.ReactSkillsManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_97314_VerifyTheElementsOfThePage",
    groups = {"full", "regression"})
public class EPMRDPT_97314_VerifyTheElementsOfThePage {

  private static final String PLACEHOLDER_TEXT = "Enter text";
  private static final String STATUS_DROPDOWN_LIST_PLACEHOLDER_TEXT = "Draft";
  private static final String SKILL_DROPDOWN_LIST_PLACEHOLDER_TEXT = "Select from the list";
  private static final String NUMBER_INPUT_TEXT = "Enter number";
  private static final String CUSTOM_IMAGE_FOR_SHARING_FIELD = "Custom image for sharing";
  private static final String SKILL_COVER_FIELD = "Skill cover";
  private static final String IMAGE_FOR_DESCRIPTION_FIELD = "Image for description";
  private static final String LINK_TO_VIDEO_FIELD = "Link to video";
  private static final String LINK_TO_VIDEO_INPUT_FIELD = "https://example.com";
  private ReactCreateSkillDescriptionScreen reactCreateSkillDescriptionScreen;
  SoftAssert softAssert = new SoftAssert();


  @BeforeClass
  public void setUpSkillManagementScreen() {
    ReactSkillsManagementScreen reactSkillsManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asSuperAdmin())
        .clickManagementDropDownMenu()
        .clickReactSkillsManagementLink()
        .waitScreenLoading();
    reactCreateSkillDescriptionScreen = reactSkillsManagementScreen.clickCreateSkillDescriptionButton()
        .waitScreenLoading();
  }

  @Test
  public void checkCreateSkillDescriptionScreenIsLoaded() {
    assertTrue(reactCreateSkillDescriptionScreen.isScreenLoaded(), "Screen is not loaded");
  }

  @Test
  public void checkButtonIsDisplayed() {
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isCreateButtonDisplayed(),
        "'Create' Button is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isCancelButtonDisplayed(),
        "'Cancel' Button is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isSkillCoverAddImageButtonDisplayed(),
        "'Skill Cover' Add Image Button is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isAddFactButtonDisplayed(),
        "'Add Fact' Button is not displayed");
    softAssert.assertTrue(
        reactCreateSkillDescriptionScreen.isCustomImageForSharingAddImageButtonDisplayed(),
        "'Custom Image For Sharing' Add Image Button is not displayed");
    softAssert.assertTrue(
        reactCreateSkillDescriptionScreen.isImageForDescriptionAddImageButtonDisplayed(),
        "'Image For Description' Add Image Button is not displayed");
    softAssert.assertAll();
  }

  @Test
  public void checkLabelIsDisplayed() {
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isStatusLabelDisplayed(),
        "'Status' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isSkillLabelDisplayed(),
        "'Skill' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isSkillCoverLabelDisplayed(),
        "'Skill Cover' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isCustomImageForSharingLabelDisplayed(),
        "'Custom Image For Sharing' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isTitleLabelDisplayed(),
        "'Title' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isFastFactsLabelDisplayed(),
        "'Fast Facts' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isNumberLabelDisplayed(),
        "'Number' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isShortDescriptionLabelDisplayed(),
        "'Short Description' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isDescriptionLabelDisplayed(),
        "'Description' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isImageForDescriptionLabelDisplayed(),
        "'Image For Description' Label is not displayed");
    softAssert.assertTrue(
        reactCreateSkillDescriptionScreen.isCoreSkillsAndTechnologiesLabelDisplayed(),
        "'Core Skills And Technologies' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isKnowledgeDescriptionLabelDisplayed(),
        "'Knowledge Description' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isAdditionalInformationLabelDisplayed(),
        "'AdditionalInformation' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isUsefulMaterialsLabelDisplayed(),
        "'UsefulMaterials' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isBannerLabelDisplayed(),
        "'Banner' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isLinkToVideoLabelDisplayed(),
        "'Link To Video' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isMetaTagTitleLabelDisplayed(),
        "'Meta Tag Title' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isMetaTagDescriptionLabelDisplayed(),
        "'Meta Tag Description' Label is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isMetaTagKeywordsLabelDisplayed(),
        "'Meta Tag Keywords' Label is not displayed");
    softAssert.assertAll();
  }

  @Test
  public void checkTabIsDisplayed() {
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isEnglishTabDisplayed(),
        "'English' Tab is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isRussianTabDisplayed(),
        "'Russian' Tab is not displayed");
    softAssert.assertTrue(reactCreateSkillDescriptionScreen.isUkrainianTabDisplayed(),
        "'Ukrainian' Tab is not displayed");
    softAssert.assertAll();
  }

  @Test
  public void checkPlaceholderText() {
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getTitleInputText(),
        PLACEHOLDER_TEXT, "'Title' Input text does not correspond to the required text");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getNumberPlaceholderText(),
        NUMBER_INPUT_TEXT, "'Number' Input text does not correspond to the required text");
    softAssert.assertEquals(
        reactCreateSkillDescriptionScreen.getShortDescriptionInputPlaceholderText(),
        PLACEHOLDER_TEXT,
        "'Short Description' Input text does not correspond to the required text");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getDescriptionInputPlaceholderText(),
        PLACEHOLDER_TEXT, "'Description' Input text does not correspond to the required text");
    softAssert.assertEquals(
        reactCreateSkillDescriptionScreen.getCoreSkillsAndTechnologiesPlaceholderText(),
        PLACEHOLDER_TEXT,
        "'CoreSkillsAndTechnologies' Input text does not correspond to the required text");
    softAssert.assertEquals(
        reactCreateSkillDescriptionScreen.getKnowledgeDescriptionPlaceholderText(),
        PLACEHOLDER_TEXT,
        "'Knowledge Description' Input text does not correspond to the required text");
    softAssert.assertEquals(
        reactCreateSkillDescriptionScreen.getAdditionalInformationPlaceholderText(),
        PLACEHOLDER_TEXT,
        "'Additional Information' Input text does not correspond to the required text");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getUsefulMaterialsPlaceholderText(),
        PLACEHOLDER_TEXT, "'UsefulMaterials' Input text does not correspond to the required text");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getBannerInputPlaceholderText(),
        PLACEHOLDER_TEXT, "'Banner' Input text does not correspond to the required text");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getLinkToVideoPlaceholderText(),
        LINK_TO_VIDEO_INPUT_FIELD,
        "'LinkToVideo' Input text does not correspond to the required text");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getMetaTagTitlePlaceholderText(),
        PLACEHOLDER_TEXT, "'MetaTagTitle' Input text does not correspond to the required text");
    softAssert.assertEquals(
        reactCreateSkillDescriptionScreen.getMetaTagDescriptionPlaceholderText(),
        PLACEHOLDER_TEXT,
        "'MetaTagDescription' Input text does not correspond to the required text");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getMetaTagKeywordsPlaceholderText(),
        PLACEHOLDER_TEXT, "'MetaTagKeywords' Input text does not correspond to the required text");
    softAssert.assertAll();
  }

  @Test
  public void checkDropdownIsPresent() {
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getSkillDropdownInputText(),
        SKILL_DROPDOWN_LIST_PLACEHOLDER_TEXT, "'Skill' DDL is not displayed");
    softAssert.assertEquals(reactCreateSkillDescriptionScreen.getStatusDropdownInputText(),
        STATUS_DROPDOWN_LIST_PLACEHOLDER_TEXT, "'Status' DDL is not displayed");
    softAssert.assertAll();
  }

  @Test
  public void checkQuestionIconIsDisplayed() {
    softAssert.assertTrue(
        reactCreateSkillDescriptionScreen.isQuestionIconDisplayed(SKILL_COVER_FIELD),
        "'Skill cover' Question Icon is not displayed");
    softAssert.assertTrue(
        reactCreateSkillDescriptionScreen.isQuestionIconDisplayed(CUSTOM_IMAGE_FOR_SHARING_FIELD),
        "'Custom image for sharing' Question Icon is not displayed");
    softAssert.assertTrue(
        reactCreateSkillDescriptionScreen.isQuestionIconDisplayed(IMAGE_FOR_DESCRIPTION_FIELD),
        "'Image for description' Question Icon is not displayed");
    softAssert.assertTrue(
        reactCreateSkillDescriptionScreen.isQuestionIconDisplayed(LINK_TO_VIDEO_FIELD),
        "'Link to video' Question Icon is not displayed");
    softAssert.assertAll();
  }
}
