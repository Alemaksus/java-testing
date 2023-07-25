package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.ADD_IMAGE_BUTTON_TEXT_CREATE_TRAINING_SCREEN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_CITY_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_COUNTRY_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_DESCRIPTION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_HEADER_SECTION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_META_TAG_DESCRIPTION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_META_TAG_KEYWORDS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_META_TAG_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_PREVIEW_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_SAVE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_SKILL_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_STATUS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_TRAINING_CENTER_NAME_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_UNIVERSITIES_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_NOTE_FOR_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTE_ON_UPLOAD_IMAGE_TEXT_CREATE_TRAINING_SCREEN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PREVIEW_CENTER_FAST_FACTS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PREVIEW_CENTER_FEEDBACK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_CANCEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_55896_VerifyElementsOfEditTrainingCenterPage",
    groups = {"full", "regression"})
public class EPMRDPT_55896_VerifyElementsOfEditTrainingCenterPage {

  private final User user;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;
  private final String cityOfCenter = "AutoTestCity";
  private final String countryOfCenter = "AutoTestCountry";
  private boolean readyToDeclineChanges = false;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_55896_VerifyElementsOfEditTrainingCenterPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
    createOrEditTrainingCenterScreen = new TrainingCenterManagementService()
        .openEditCenterScreen(countryOfCenter, cityOfCenter)
        .waitCreateOrEditTrainingCenterHeaderVisibility()
        .clickDeleteImageCrossButton();
  }

  @Test(priority = 1)
  public void checkThatEditTrainingCenterPageIsLoaded() {
    assertTrue(
        createOrEditTrainingCenterScreen.isScreenLoaded(),
        "Create or edit training page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkHeaderSectionName() {
    assertEquals(
        createOrEditTrainingCenterScreen.getHeaderText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_HEADER_SECTION_TITLE),
        "Wrong title of header!");
  }

  @Test(priority = 2)
  public void checkPreviewTopButtonName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isPreviewTopButtonDisplayed(),
        "Preview top button isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getPreviewTopButtonText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_PREVIEW_BUTTON),
        "Wrong title of top preview button!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isPreviewTopButtonClickable(),
        "Preview top button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSaveTopButtonName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isSaveTopButtonDisplayed(),
        "Save top button isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getSaveTopButtonText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_SAVE_BUTTON),
        "Wrong title of top save button!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isSaveTopButtonClickable(),
        "Save top button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkCancelTopButtonName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isFirstSectionCancelButtonDisplayed(),
        "Top cancel button isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getFirstSectionCancelButtonText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_CANCEL),
        "Wrong title of cancel button!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isFirstSectionCancelButtonClickable(),
        "Top cancel button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCountrySection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isCenterCountryNameFieldDisplayed(),
        "Center country name field isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getCountrySectionTitleText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_COUNTRY_TITLE),
        "Wrong title of country section!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getCountryNameOfTrainingCenterText(),
        countryOfCenter,
        "Wrong name of country center name!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkCitySection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isCenterCityNameFieldDisplayed(),
        "Center city name field isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getCitySectionTitleText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_CITY_TITLE),
        "Wrong title of city section!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getCityNameOfTrainingCenter(),
        cityOfCenter,
        "Wrong name of city center name!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkStatusSection() {
    SoftAssert softAssert = new SoftAssert();
    createOrEditTrainingCenterScreen
        .clickToChooseStatus()
        .waitStatusDropdownListForVisibility();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isStatusDropdownSectionDisplayed(),
        "Status dropdown section isn't displayed!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isStatusSectionTitleDisplayed(),
        "Status section isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getStatusSectionTitleText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_STATUS_TITLE),
        "Wrong title of status section!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkSkillSection() {
    SoftAssert softAssert = new SoftAssert();
    createOrEditTrainingCenterScreen
        .clickSelectSkillsField();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isSelectSkillDDlFieldDisplayed(),
        "Dropdown with skills isn't displayed!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isSkillSectionTitleDisplayed(),
        "Skill section title isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getSkillSectionTitleText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_SKILL_TITLE),
        "Wrong title of skill section!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkAddImageButtonTitle() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isAddPictureButtonDisplayed(),
        "Add image button isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getAddPictureButtonText(),
        LocaleProperties.getValueOf(ADD_IMAGE_BUTTON_TEXT_CREATE_TRAINING_SCREEN),
        "Wrong title of add image button!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isAddPictureButtonClickable(),
        "Add image button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkAddImageButtonNote() {
    assertEquals(
        createOrEditTrainingCenterScreen.getNoteOnUploadImageInputText(),
        LocaleProperties.getValueOf(NOTE_ON_UPLOAD_IMAGE_TEXT_CREATE_TRAINING_SCREEN),
        "Wrong text of upload note of image button!");
  }

  @Test(priority = 4)
  public void checkEnglishTab() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isEnglishTabDisplayed(),
        "English tab title isn't displayed!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isEnglishTabClickable(),
        "English tab isn't clickable!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getEnglishTabText(),
        LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH),
        "Wrong title of english tab!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkRussianTab() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isRussianTabDisplayed(),
        "Russian tab title isn't displayed!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isRussianTabClickable(),
        "Russian tab isn't clickable!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getRussianTabText(),
        LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN),
        "Wrong title of russian tab!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkUkrainianTab() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isUkrainianTabDisplayed(),
        "Ukrainian tab title isn't displayed!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isUkrainianTabClickable(),
        "Ukrainian tab isn't clickable!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getUkrainianTabText(),
        LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN),
        "Wrong title of ukrainian tab!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkAddingInformationNote() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isNoteAddingInformationWithoutWarningDisplayed(),
        "Note adding information without warning isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getNoteAddingInformationWithoutWarningText(),
        LocaleProperties.getValueOf(EDIT_CENTER_MANAGEMENT_NOTE_FOR_NAME),
        "Wrong title of note adding information without warning!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTrainingCenterNameTitle() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isTrainingCenterNameTitleDisplayed(),
        "Training center name title isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getTrainingCenterNameTitleText(),
        LocaleProperties.getValueOf(
            CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_TRAINING_CENTER_NAME_TITLE),
        "Wrong title of training center name!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkDescriptionTitle() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isDescriptionInLanguageTabTitleDisplayed(),
        "Description in language tab isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getDescriptionInLanguageTabTitleText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_DESCRIPTION_TITLE),
        "Wrong title of description title in language tab!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkMetaTagSection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isMetaTagTitleDisplayed(),
        "Meta tag title isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getMetaTagTitleText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_META_TAG_TITLE),
        "Wrong title of meta tag!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isMetaTagInputFieldClickable(),
        "Meta tag field isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkMetaTagDescriptionSection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isMetaTagDescriptionTitleDisplayed(),
        "Meta tag description title isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getMetaTagDescriptionTitleText(),
        LocaleProperties.getValueOf(
            CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_META_TAG_DESCRIPTION_TITLE),
        "Wrong title of meta tag description!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isMetaTagDescriptionInputFieldClickable(),
        "Meta tag description field isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkMetaTagKeywordsSection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isMetaTagKeywordsTitleDisplayed(),
        "Meta tag keywords title isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getMetaTagKeywordsTitleText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_META_TAG_KEYWORDS_TITLE),
        "Wrong title of meta tag keywords!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isMetaTagKeywordsInputFieldClickable(),
        "Meta tag keywords field isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkFactsTabSection() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isFastFactTabDisplayed(),
        "Fast facts tab isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getFastFactTabText(),
        LocaleProperties.getValueOf(PREVIEW_CENTER_FAST_FACTS_TITLE),
        "Wrong title of fast facts tab!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isFastFactTabClickable(),
        "Fast facts tab isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkUniversitiesTab() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isUniversitiesTabDisplayed(),
        "Universities tab isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getUniversitiesTabText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_UNIVERSITIES_TITLE),
        "Wrong title of universities tab!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isUniversitiesTabClickable(),
        "Universities tab isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkFeedbackTab() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isFeedbackTabDisplayed(),
        "Feedback tab isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getFeedbackTabText(),
        LocaleProperties.getValueOf(PREVIEW_CENTER_FEEDBACK),
        "Wrong title of feedback tab!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isFeedbackTabClickable(),
        "Feedback tab isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkSaveBottomButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isSaveBottomButtonDisplayed(),
        "Save bottom button isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getSaveBottomButtonText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_SAVE_BUTTON),
        "Wrong title of save bottom button!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isSaveBottomButtonClickable(),
        "Save bottom button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkCancelBottomButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isCancelBottomButtonDisplayed(),
        "Cancel bottom button isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getCancelBottomButtonText(),
        LocaleProperties.getValueOf(PROFILE_ASSIGN_ENGLISH_TEST_WARNING_POPUP_CANCEL),
        "Wrong title of cancel bottom button!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isCancelBottomButtonClickable(),
        "Cancel bottom button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkPreviewBottomButton() {
    readyToDeclineChanges = true;
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isPreviewBottomButtonDisplayed(),
        "Preview bottom button isn't displayed!");
    softAssert.assertEquals(
        createOrEditTrainingCenterScreen.getPreviewBottomButtonText(),
        LocaleProperties.getValueOf(CREATE_OR_EDIT_TRAINING_CENTER_SCREEN_PREVIEW_BUTTON),
        "Wrong title of preview bottom button!");
    softAssert.assertTrue(
        createOrEditTrainingCenterScreen.isPreviewBottomButtonClickable(),
        "Preview bottom button isn't clickable!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void declineChanges() {
    if (readyToDeclineChanges) {
      createOrEditTrainingCenterScreen
          .clickCancelTopButton()
          .clickConfirmationPopupOkButton();
    }
  }
}
