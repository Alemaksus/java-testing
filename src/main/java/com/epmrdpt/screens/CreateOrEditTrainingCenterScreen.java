package com.epmrdpt.screens;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import com.epmrdpt.services.TrainingCenterManagementService;
import javax.swing.text.html.CSS.Attribute;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML;
import org.openqa.selenium.JavascriptExecutor;

public class CreateOrEditTrainingCenterScreen extends AbstractScreen {

  private static final String COUNTRY_DDL_BY_TEXT_LOCATOR = "//li[contains(@class,'active-result') "
      + "and contains(text(),'%s')]";
  private static final String CITY_BY_TEXT_LOCATOR = "//*[@id='city']"
      + "//descendant::*[contains(text(),'%s')]";
  private static final String SKILL_BY_TEXT_LOCATOR = "//*[@class='chosen-results']"
      + "//descendant::li[text()='%s']";
  private static final String LANGUAGE_TAB_BY_TEXT_LOCATOR =
      "//div[@ng-click='changeTab(tab)' and contains(text(),'%s')]";
  private static final String HEADER_TEXT_CREATE_TRAINING_SCREEN = LocaleProperties
      .getValueOf(LocalePropertyConst.HEADER_TEXT_CREATE_TRAINING_SCREEN);
  private static final String STATUS_DROP_DOWN_LOCATOR_PATTERN = "//li[text()='%s']";
  private static final String LANGUAGE_TAB_PATTERN = "//tabs[@tabs='languageTabs']/div[contains(text(),'%s')]";
  private static final String TRAINING_CENTER_NAME_PATTERN = "//input[contains(@id,'title-%s')]";
  private static final String TRAINING_CENTER_DESCRIPTION_PATTERN = "//*[contains(@id,'shortDescription%s')]/div[@data-placeholder]";

  private Element createOrEditTrainingCenterHeader = Element
      .byXpath("//header[contains(@class,'create-training-item__header')]");
  private Element selectCountryField = Element.byId("country_chosen");
  private Element selectCountryFromDDL = Element
      .byXpath(
          "//*[@id='country_chosen']//descendant::*[contains(@class,'active-result')]");
  private Element selectCityField = Element.byXpath("//*[@id='city']/div");
  private Element selectSkillsField = Element.byId("selectedSkills_chosen");
  private Element selectSkillDDlField = Element
      .byXpath(
          "//*[contains(@class,'chosen-choices')]/following-sibling::div[contains(@class,'chosen-drop')]");
  private Element selectedSkills = Element.byXpath("//ul[contains(@class,'chosen-choices')]");
  private Element factDateOnEnglishTab = Element.byId("factTitle0en");
  private Element factDescriptionOnEnglishTab = Element.byId("factDescription0en");
  private Element addPictureButton = Element.byXpath("//button[@ng-click='addImage()']");
  private Element createOrSaveTrainingCenterTopButton = Element
      .byXpath("//*[contains(@class,'author')]/button[@type='submit']");
  private Element createConfirmationButton = Element
      .byXpath("//*[contains(@ng-click,'ok')]");
  private Element confirmationPopup = Element.byClassName("popup-wrapper");
  private Element firstSectionCancelButton = Element
      .byXpath("//*[@id='trainingForm']/section//a[contains(@class, 'cancel')]");
  private Element confirmationPopupMessage = Element.byXpath(
      "//div[@class='message-content ng-binding']");
  private Element confirmationPopupOkButton = Element.byClassName("message-btn-ok");
  private Element chooseImgFieldIsRequiredErrorHint = Element.byXpath(
      "//*[@class='choose-field__item-error ng-binding']");
  private Element deleteImageCrossButton = Element.byXpath(
      "//div[@ng-click='deleteCurrentImage()']");
  private Element editArea = Element.byXpath("//*[@class='create-training-item']");
  private Element previewTopButton = Element
      .byXpath("//*[contains(@class,'author')]//button[@ng-click='goToPreview()']");
  private Element factDescriptionOnRussianTab = Element.byId("factDescription0ru");
  private Element factDescriptionOnUkrainianTab = Element.byId("factDescription0ua");
  private Element saveTopButton = Element
      .byXpath("//*[contains(@class,'author')]/button[@type='submit']");
  private Element cancelTopButton = Element
      .byXpath("//*[@id='trainingForm']/section/div/a");
  private Element saveChangesOrCreatePopUp = Element
      .byXpath("//*[contains(@class,'status-popup')]/p");
  private Element centerCityNameField = Element.byXpath(
      "//*[@id='city']//*[@data-id='combobox-placeholder']");
  private Element chooseStatus = Element.byXpath("//div[@id='statusId_chosen']");
  private Element statusDropdownList = Element
      .byXpath("//div[@id='statusId_chosen']//div[@class='chosen-drop']");
  private Element trainingPortalMessagePopUp = Element
      .byXpath("//div[@class='modal-title popup-title popup__container']");
  private Element okButtonTrainingPortalMessagePopUp = Element
      .byXpath("//div[@class='message-form']//button[@ng-click='ok()']");
  private Element addPictureButtonText = Element.byXpath("//button[@ng-click='addImage()']");
  private Element plusCircleIcon = Element
      .byXpath("//*[@class='add-image-row__button ng-binding']");
  private Element uploadedPicture = Element.byXpath("//div[@class='add-image-row__image']");
  private Element noteOnUploadImageInput = Element
      .byXpath("//span[@class='add-image-row__subtitle ng-binding']");
  private Element centerCountryNameField = Element.byCss("#country_chosen span");
  private Element centerCountry = Element.byXpath("//div[@id='country_chosen']");
  private Element noteAddingInformation = Element
      .byXpath("//span[contains(@class,'create-center-item__subtitle-error')]");
  private Element nameHint = Element
      .byXpath("//div[@ng-show='!languageContentTab.Title.length']");
  private Element descriptionHint = Element
      .byXpath("//div[@ng-show='!languageContentTab.Description']");
  private Element headerContainer = Element.byCss("#header> div.container");
  private Element navigationLink = Element.byCss(".breadcrumb-wrapper .ng-binding");
  private Element highPartFirstSection = Element.byCss("#trainingForm>section");
  private Element lowerPartFirstSection = Element.byCss("#trainingForm center-chosen-area section");
  private Element secondSection = Element
      .byXpath("//section[@class='create-training-item__tab-item']//center-header-content");
  private Element fastFactTab = Element.byXpath(
      "//section[@class='create-training-item__tab-item']//rd-tab-toggle[@header-text=\"'KeyFacts'\"]");
  private Element universitiesTab = Element.byXpath(
      "//section[@class='create-training-item__tab-item']//rd-tab-toggle[@header-text=\"'CenterManagement_Universities'\"]");
  private Element universityNameField = Element.byId("universityName0en");
  private Element universityShortNameField = Element.byId("universityShortName0en");
  private Element universityDescriptionField = Element.byXpath(
      "//*[@id='universityDescription0en']//descendant::*[contains(@class,'ql-editor ql-blank')]");
  private Element universityAddButton = Element
      .byXpath("//*[@ng-click='createNewUniversity()']/span");
  private Element feedbackTab = Element.byXpath(
      "//section[@class='create-training-item__tab-item']//rd-tab-toggle[@header-text=\"'CenterManagement_Feedback'\"]");
  private Element feedbackAuthorsNameField = Element.byId("feedbackAuthorName0en");
  private Element feedbackJobFunctionalField = Element.byId("feedbackJobTitle0en");
  private Element feedbackField = Element.byId("feedbackText0en");
  private Element feedbackAddButton = Element.byXpath("//*[@ng-click='createNewFeedback()']/span");
  private Element footerContainer = Element.byCss("#footer div.container");
  private Element numberField = Element.byXpath("//input[@id='factTitle0en']");
  private Element warningHint = Element.byXpath("//div[@class='ng-binding ng-scope']");
  private Element notificationPopUpAboutSuccess = Element.byCss(".status-popup");
  private Element countrySectionTitle = Element.byXpath("//label[@for='country']");
  private Element citySectionTitle = Element.byXpath("//label[@for='city']");
  private Element statusSectionTitle = Element.byXpath("//label[@for='statusId']");
  private Element skillSectionTitle = Element.byXpath("//label[@for='selectedSkills']");
  private Element englishTab = Element.byXpath("//*[@tabs='languageTabs']/child::*[1]");
  private Element russianTab = Element.byXpath("//*[@tabs='languageTabs']/child::*[2]");
  private Element ukrainianTab = Element.byXpath("//*[@tabs='languageTabs']/child::*[3]");
  private Element noteAddingInformationWithoutWarning = Element
      .byXpath("//span[(@class='create-center-item__subtitle ng-binding')]");
  private Element trainingCenterNameTitle = Element.byXpath(
      "//input[contains(@id,'title-')]/preceding-sibling::*");
  private Element descriptionInLanguageTabTitle = Element.byXpath(
      "//*[contains(@area-id,'languageContentTab.description')]//*[contains(@ng-class,'labelClass')]");
  private Element metaTagTitleInputField = Element.byXpath("//input[@id='metaTagTitleEn']");
  private Element metaTagTitle = Element.byXpath(
      "//input[@id='metaTagTitleEn']/preceding-sibling::*");
  private Element metaTagDescriptionInputField = Element.byXpath(
      "//*[contains(@id,'metaTagDescription')]");
  private Element metaTagDescriptionTitle = Element.byXpath(
      "//*[contains(@id,'metaTagDescription')]/preceding-sibling::*");
  private Element metaTagKeywordsInputField = Element.byXpath(
      "//*[contains(@id,'metaKeywords')]");
  private Element metaTagKeyWordsTitle = Element.byXpath(
      "//*[contains(@id,'metaKeywords')]/preceding-sibling::*");
  private Element saveBottomButton = Element.byXpath(
      "//*[contains(@class,'create-training-item__buttons')]//button[@type='submit']");
  private Element cancelBottomButton = Element.byXpath(
      "//*[contains(@class,'create-training-item__buttons')]//a[contains(@class,'cancel')]");
  private Element previewBottomButton = Element.byXpath(
      "//*[contains(@class,'create-training-item__buttons')]//button[@ng-click='goToPreview()']");
  private Element centerCityPlaceholder =
      Element.byXpath("//*[@id='city']//*[@class='combobox-placeholder']");

  @Override
  public boolean isScreenLoaded() {
    return isCreateOrEditTrainingCenterHeaderDisplayed();
  }

  public boolean isCreateTrainingCenterScreenOpened() {
    return getHeaderText().equals(HEADER_TEXT_CREATE_TRAINING_SCREEN);
  }

  public boolean isCreateOrEditTrainingCenterHeaderDisplayed() {
    return createOrEditTrainingCenterHeader.isDisplayed();
  }

  public boolean isSelectSkillDDlFieldDisplayed() {
    return selectSkillDDlField.isDisplayed();
  }

  public boolean isSelectedSkillDisplayed(String skill) {
    return Element.byXpath(SKILL_BY_TEXT_LOCATOR, skill).isDisplayed();
  }

  public CreateOrEditTrainingCenterScreen waitCreateOrEditTrainingCenterHeaderVisibility() {
    createOrEditTrainingCenterHeader.waitForVisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitForTrainingCenterNameVisibility() {
    trainingCenterNameTitle.waitForVisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitCreateOrEditTrainingCenterHeaderText(
      String expectedText) {
    createOrEditTrainingCenterHeader.waitTextToBeAfterScreenRefresh(expectedText);
    return this;
  }

  public String getHeaderText() {
    return createOrEditTrainingCenterHeader.getText();
  }

  public CreateOrEditTrainingCenterScreen clickSelectCountryField() {
    selectCountryField.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen selectCountryFromDDL(int dropDownSelectIndex) {
    selectCountryFromDDL.getElements().get(dropDownSelectIndex).click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen selectCountryFromDDLByName(String countryName) {
    Element.byXpath(COUNTRY_DDL_BY_TEXT_LOCATOR, countryName).click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickSelectCityField() {
    selectCityField.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen selectCityFromDDLByName(String cityName) {
    Element.byXpath(CITY_BY_TEXT_LOCATOR, cityName).click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickSelectSkillsField() {
    selectSkillsField.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeFactDateOnEnglishTab(Integer date) {
    factDateOnEnglishTab.type(date.toString());
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeFactDescriptionOnEnglishTab(String description) {
    factDescriptionOnEnglishTab.type(description);
    return this;
  }

  public CreateOrEditTrainingCenterScreen openTab(LanguageEnum tabLocale) {
    Element.byXpath(LANGUAGE_TAB_PATTERN,
        TrainingCenterManagementService.getLanguageTabName(tabLocale)).click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeTrainingCenterNameOnTab(LanguageEnum tabLocale,
      String trainingCenterName) {
    Element.byXpath(TRAINING_CENTER_NAME_PATTERN, tabLocale.getLanguageCode())
        .type(trainingCenterName);
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeTrainingCenterDescriptionOnTab(LanguageEnum tabLocale,
      String centerDescription) {
    Element.byXpath(TRAINING_CENTER_DESCRIPTION_PATTERN, tabLocale.name().charAt(0))
        .type(centerDescription);
    return this;
  }

  public CreateOrEditTrainingCenterScreen setTrainingCenterPictureLogo(String imageBase64) {
    addPictureButton.mouseOver();
    ((JavascriptExecutor) webDriver).executeScript(String.format(
        "angular.element('.create-training-item__choose-field-image')"
            + ".scope().img = {MimeType: \"image/jpeg\", ContentBase64: "
            + "\"%s\"}", imageBase64));
    ((JavascriptExecutor) webDriver).executeScript(
        "angular.element('.create-training-item__choose-field-image').scope().$apply()");
    return this;
  }

  public boolean isAddPictureButtonDisplayed() {
    return addPictureButton.isDisplayed();
  }

  public CreateOrEditTrainingCenterScreen mouseOverCreateOrSaveTrainingCenterTopButton() {
    createOrSaveTrainingCenterTopButton.mouseOver();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickCreateOrSaveTrainingCenterTopButton() {
    createOrSaveTrainingCenterTopButton.clickJs();
    return this;
  }

  public CreateOrEditTrainingCenterScreen mouseOverCreateConfirmationButton() {
    createConfirmationButton.mouseOver();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickCreateConfirmationButton() {
    createConfirmationButton.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitForClickableAndClickCreateConfirmationButton() {
    createConfirmationButton.waitForClickableAndClick();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitConfirmationPopupForVisibility() {
    confirmationPopup.waitForVisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitConfirmationPopupMessageVisibility() {
    confirmationPopupMessage.waitForVisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitCenterCityPlaceholderVisibility(){
    centerCityPlaceholder.waitForVisibility();
    return this;
  }

  public TrainingCenterManagementScreen clickFirstSectionCancelButton() {
    firstSectionCancelButton.click();
    return new TrainingCenterManagementScreen();
  }

  public boolean isConfirmationPopupDisplayed() {
    return confirmationPopup.isDisplayed();
  }

  public String getConfirmationPopupMessageText() {
    return confirmationPopupMessage.getText();
  }

  public CreateOrEditTrainingCenterScreen clickConfirmationPopupOkButton() {
    confirmationPopupOkButton.clickJs();
    return this;
  }

  public String getSkillsFieldText() {
    return selectSkillsField.getText();
  }

  public String getSelectedSkillsText() {
    return selectedSkills.getText();
  }

  public String getChooseImgFieldIsRequiredErrorHintText() {
    return chooseImgFieldIsRequiredErrorHint.getText();
  }

  public boolean isChooseImgFieldIsRequiredErrorHintDisplayed() {
    return chooseImgFieldIsRequiredErrorHint.isDisplayed();
  }

  public String getChooseImgFieldIsRequiredErrorHintColor() {
    return chooseImgFieldIsRequiredErrorHint.getCssValue(Attribute.COLOR.toString());
  }

  public CreateOrEditTrainingCenterScreen clickDeleteImageCrossButton() {
    deleteImageCrossButton.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen selectSkillFromDDLByName(String skill) {
    Element.byXpath(SKILL_BY_TEXT_LOCATOR, skill).click();
    return this;
  }

  public String getLanguageTabClassValue(String language) {
    return Element.byXpath(LANGUAGE_TAB_BY_TEXT_LOCATOR, language)
        .getAttributeValue(HTML.Attribute.CLASS.toString());
  }

  public String getLanguageTabBackground(String language) {
    return Element.byXpath(LANGUAGE_TAB_BY_TEXT_LOCATOR, language)
        .getCssValue(CSS.Attribute.BACKGROUND.toString());
  }

  public CreateOrEditTrainingCenterScreen waitEditAreaForVisibility() {
    editArea.waitForVisibility();
    return this;
  }

  public CenterScreen clickPreviewButton() {
    previewTopButton.mouseOver();
    previewTopButton.mouseDown();
    previewTopButton.click();
    return new CenterScreen();
  }

  public CreateOrEditTrainingCenterScreen mouseOverPreviewButton() {
    previewTopButton.mouseOver();
    return new CreateOrEditTrainingCenterScreen();
  }

  public CreateOrEditTrainingCenterScreen typeFactDescriptionOnRussianTab(String description) {
    factDescriptionOnRussianTab.type(description);
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeFactDescriptionOnUkrainianTab(String description) {
    factDescriptionOnUkrainianTab.type(description);
    return this;
  }

  public String getSaveChangesOrCreatePopUpTitleText() {
    return saveChangesOrCreatePopUp.getText();
  }

  public boolean isSaveChangesOrCreatePopUpDisplayed() {
    return saveChangesOrCreatePopUp.isDisplayed();
  }

  public CreateOrEditTrainingCenterScreen waitSaveChangesOrCreatePopUpForVisibility() {
    saveChangesOrCreatePopUp.waitForVisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitSaveChangesOrCreatePopUpForInVisibility() {
    saveChangesOrCreatePopUp.waitForInvisibility();
    return this;
  }

  public String getCityNameOfTrainingCenter() {
    return centerCityNameField.getText();
  }

  public boolean isHeaderDisplayed() {
    return headerContainer.isDisplayed();
  }

  public boolean isNavigationLinkDisplayed() {
    return navigationLink.isDisplayed();
  }

  public boolean isFirstSectionWithBasicInfoDisplayed() {
    return highPartFirstSection.isDisplayed() && lowerPartFirstSection.isDisplayed();
  }

  public boolean isSecondSectionWithAdditionalInformationDisplayed() {
    return secondSection.isDisplayed();
  }

  public boolean isFastFactTabDisplayed() {
    return fastFactTab.isDisplayed();
  }

  public boolean isUniversitiesTabDisplayed() {
    return universitiesTab.isDisplayed();
  }

  public boolean isFeedbackTabDisplayed() {
    return feedbackTab.isDisplayed();
  }

  public boolean isFooterDisplayed() {
    return footerContainer.isDisplayed();
  }

  public CreateOrEditTrainingCenterScreen clickToChooseStatus() {
    chooseStatus.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen selectStatusFromDropDownList(String status) {
    Element.byXpath(STATUS_DROP_DOWN_LOCATOR_PATTERN, status).click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickAtTheTopSaveButton() {
    saveTopButton.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitStatusDropdownListForVisibility() {
    statusDropdownList.waitForVisibility();
    return this;
  }

  public String getChooseStatusText() {
    return chooseStatus.getText();
  }

  public boolean isTrainingPortalMessagePopUpDisplayed() {
    return trainingPortalMessagePopUp.isDisplayedShortTimeOut();
  }

  public CreateOrEditTrainingCenterScreen confirmMessageTrainingPortalPopUp() {
    okButtonTrainingPortalMessagePopUp.waitForClickableAndClick();
    return this;
  }

  public CreateOrEditTrainingCenterScreen fillInNumberField(char symbol) {
    numberField.clearAndSendFilePath(Character.toString(symbol));
    return this;
  }

  public boolean isWarningHintDisplayed() {
    return warningHint.isDisplayedNoWait();
  }

  public String getAddPictureButtonText() {
    return addPictureButtonText.getText();
  }

  public boolean isPlusCircleIconDisplayed() {
    return plusCircleIcon.isDisplayed();
  }

  public boolean isUploadedPictureDisplayed() {
    return uploadedPicture.isDisplayed();
  }

  public String getNoteOnUploadImageInputText() {
    return noteOnUploadImageInput.getText();
  }

  public CreateOrEditTrainingCenterScreen waitForTrainingPortalMessagePopUpVsibility() {
    trainingPortalMessagePopUp.waitForVisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickCancelTopButton() {
    cancelTopButton.click();
    return this;
  }

  public String getCountryNameOfTrainingCenterText() {
    return centerCountryNameField.getText();
  }

  public String getCenterCityClassValue() {
    return centerCityNameField.getAttributeValue(HTML.Attribute.CLASS.toString());
  }

  public String getCenterCountryClassValue() {
    return centerCountry.getAttributeValue(HTML.Attribute.CLASS.toString());
  }

  public CreateOrEditTrainingCenterScreen clearTrainingCenterName() {
    Element.byXpath(TRAINING_CENTER_NAME_PATTERN, LanguageEnum.ENGLISH.getLanguageCode())
        .clearInput();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clearTrainingCenterDescription() {
    Element.byXpath(TRAINING_CENTER_DESCRIPTION_PATTERN, LanguageEnum.ENGLISH.name().charAt(0))
        .clearInput();
    return this;
  }

  public String getTrainingCenterNameText() {
    return Element.byXpath(TRAINING_CENTER_NAME_PATTERN, LanguageEnum.ENGLISH.getLanguageCode())
        .getText();
  }

  public String getTrainingCenterDescriptionText() {
    return Element
        .byXpath(TRAINING_CENTER_DESCRIPTION_PATTERN, LanguageEnum.ENGLISH.name().charAt(0))
        .getText();
  }

  public String getNoteAddingInformationText() {
    return noteAddingInformation.getText();
  }

  public String getNoteAddingInformationColor() {
    return noteAddingInformation.getCssValue(Attribute.COLOR.toString());
  }

  public String getTrainingCenterNameHintText() {
    return nameHint.getText();
  }

  public String getDescriptionHintText() {
    return descriptionHint.getText();
  }

  public String getDescriptionHintColor() {
    return descriptionHint.getCssValue(Attribute.COLOR.toString());
  }

  public String getNameHintColor() {
    return nameHint.getCssValue(Attribute.COLOR.toString());
  }

  public CreateOrEditTrainingCenterScreen clickOnUniversityTab() {
    universitiesTab.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeUniversityName(String universityName) {
    universityNameField.type(universityName);
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeUniversityShortName(String universityShortName) {
    universityShortNameField.type(universityShortName);
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeUniversityDescription(String description) {
    universityDescriptionField.type(description);
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickAddUniversityButton() {
    universityAddButton.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickOnFeedbackTab() {
    feedbackTab.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeFeedbackAuthorsName(String feedbackAuthorsName) {
    feedbackAuthorsNameField.type(feedbackAuthorsName);
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeFeedbackJobFunctional(String feedbackJobFunctional) {
    feedbackJobFunctionalField.type(feedbackJobFunctional);
    return this;
  }

  public CreateOrEditTrainingCenterScreen typeFeedback(String feedback) {
    feedbackField.type(feedback);
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickAddFeedbackButton() {
    feedbackAddButton.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitConfirmationPopupForInVisibility() {
    confirmationPopup.waitForInvisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen waitNotificationPopUpAboutSuccessVisibilityAndInvisibility() {
    notificationPopUpAboutSuccess.waitForVisibility();
    notificationPopUpAboutSuccess.waitForInvisibility();
    return this;
  }

  public String getPreviewTopButtonText() {
    return previewTopButton.getText();
  }

  public boolean isPreviewTopButtonDisplayed() {
    return previewTopButton.isDisplayed();
  }

  public boolean isPreviewTopButtonClickable() {
    return previewTopButton.isClickable();
  }

  public String getSaveTopButtonText() {
    return saveTopButton.getText();
  }

  public boolean isSaveTopButtonDisplayed() {
    return saveTopButton.isDisplayed();
  }

  public boolean isSaveTopButtonClickable() {
    return saveTopButton.isClickable();
  }

  public String getFirstSectionCancelButtonText() {
    return firstSectionCancelButton.getText();
  }

  public boolean isFirstSectionCancelButtonDisplayed() {
    return firstSectionCancelButton.isDisplayed();
  }

  public boolean isFirstSectionCancelButtonClickable() {
    return firstSectionCancelButton.isClickable();
  }

  public String getCountrySectionTitleText() {
    return countrySectionTitle.getText();
  }

  public boolean isCenterCountryNameFieldDisplayed() {
    return  centerCountryNameField.isDisplayed();
  }

  public String getCitySectionTitleText() {
    return citySectionTitle.getText();
  }

  public boolean isCenterCityNameFieldDisplayed() {
    return centerCityNameField.isDisplayed();
  }

  public String getStatusSectionTitleText() {
    return statusSectionTitle.getText();
  }

  public boolean isStatusSectionTitleDisplayed() {
    return statusSectionTitle.isDisplayed();
  }

  public boolean isStatusDropdownSectionDisplayed() {
    return statusDropdownList.isDisplayed();
  }

  public String getSkillSectionTitleText() {
    return skillSectionTitle.getText();
  }

  public boolean isSkillSectionTitleDisplayed() {
    return skillSectionTitle.isDisplayed();
  }

  public boolean isAddPictureButtonClickable() {
    return addPictureButton.isClickable();
  }

  public boolean isEnglishTabDisplayed() {
    return englishTab.isDisplayed();
  }

  public boolean isEnglishTabClickable() {
    return englishTab.isClickable();
  }

  public String getEnglishTabText() {
    return englishTab.getText();
  }

  public boolean isRussianTabDisplayed() {
    return russianTab.isDisplayed();
  }

  public boolean isRussianTabClickable() {
    return russianTab.isClickable();
  }

  public String getRussianTabText() {
    return russianTab.getText();
  }

  public boolean isUkrainianTabDisplayed() {
    return ukrainianTab.isDisplayed();
  }

  public boolean isUkrainianTabClickable() {
    return ukrainianTab.isClickable();
  }

  public String getUkrainianTabText() {
    return ukrainianTab.getText();
  }

  public String  getNoteAddingInformationWithoutWarningText() {
    return noteAddingInformationWithoutWarning.getText();
  }

  public boolean isNoteAddingInformationWithoutWarningDisplayed() {
    return noteAddingInformationWithoutWarning.isDisplayed();
  }

  public String getTrainingCenterNameTitleText() {
    return trainingCenterNameTitle.getText();
  }

  public boolean isTrainingCenterNameTitleDisplayed() {
    return trainingCenterNameTitle.isDisplayed();
  }

  public boolean isDescriptionInLanguageTabTitleDisplayed() {
    return descriptionInLanguageTabTitle.isDisplayed();
  }

  public String getDescriptionInLanguageTabTitleText() {
    return descriptionInLanguageTabTitle.getText();
  }

  public boolean isMetaTagTitleDisplayed() {
    return metaTagTitle.isDisplayed();
  }

  public String getMetaTagTitleText() {
    return metaTagTitle.getText();
  }

  public boolean isMetaTagInputFieldClickable() {
    return metaTagTitleInputField.isClickable();
  }

  public boolean isMetaTagDescriptionTitleDisplayed() {
    return metaTagDescriptionTitle.isDisplayed();
  }

  public String getMetaTagDescriptionTitleText() {
    return metaTagDescriptionTitle.getText();
  }

  public boolean isMetaTagDescriptionInputFieldClickable() {
    return metaTagTitleInputField.isClickable();
  }

  public boolean isMetaTagKeywordsTitleDisplayed() {
    return metaTagKeyWordsTitle.isDisplayed();
  }

  public String getMetaTagKeywordsTitleText() {
    return metaTagKeyWordsTitle.getText();
  }

  public boolean isMetaTagKeywordsInputFieldClickable() {
    return metaTagKeywordsInputField.isClickable();
  }

  public String getFastFactTabText() {
    return fastFactTab.getText();
  }

  public boolean isFastFactTabClickable() {
    return fastFactTab.isClickable();
  }

  public String getUniversitiesTabText() {
    return universitiesTab.getText();
  }

  public boolean isUniversitiesTabClickable() {
    return universitiesTab.isClickable();
  }

  public String getFeedbackTabText() {
    return feedbackTab.getText();
  }

  public boolean isFeedbackTabClickable() {
    return feedbackTab.isClickable();
  }

  public String getSaveBottomButtonText() {
    return saveBottomButton.getText();
  }

  public boolean isSaveBottomButtonDisplayed() {
    return saveBottomButton.isDisplayed();
  }

  public boolean isSaveBottomButtonClickable() {
    return saveBottomButton.isClickable();
  }

  public String getCancelBottomButtonText() {
    return cancelBottomButton.getText();
  }

  public boolean isCancelBottomButtonDisplayed() {
    return cancelBottomButton.isDisplayed();
  }

  public boolean isCancelBottomButtonClickable() {
    return cancelBottomButton.isClickable();
  }

  public String getPreviewBottomButtonText() {
    return previewBottomButton.getText();
  }

  public boolean isPreviewBottomButtonDisplayed() {
    return previewBottomButton.isDisplayed();
  }

  public boolean isPreviewBottomButtonClickable() {
    return previewBottomButton.isClickable();
  }
}
