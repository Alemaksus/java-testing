package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElement;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElementEnum;
import javax.swing.text.html.CSS.Attribute;

public class TrainingDescriptionScreen extends AbstractScreen {

  private static final String HEADER_TRAINING_IMAGE_PATH = "/Content/themes/Redesign/images/pattern-header-training.png";
  private static final String TRAINING_HEADER_LOCATOR = "div.training-detail-header__hint";
  private static final String TRAINING_PRICE_LOCATOR =
      TRAINING_HEADER_LOCATOR + " div:nth-child(1)";
  private static final String TRAINING_FORMAT_LOCATOR =
      TRAINING_HEADER_LOCATOR + " div:nth-child(2)";
  private static final String SKILL_DESCRIPTION_BANNER_PATH = "/Content/themes/Redesign/ico/TrainingPage/skill-background.svg";
  private static final String LOCATION_FIELD_LOCATOR = "div.place-info";
  private static final String LANGUAGE_FIELD_LOCATOR = "div.language-info";
  private static final String TRAINING_CUSTOM_BLOCK_TITLE_LOCATOR =
      "//div[contains(@class, 'training-detail-custom-block__title')]";
  private static final String TRAINING_LOCATION_INFO_PATTERN =
      "//div[contains(@class,'training-location')]//*[contains(text(),'%s')]";

  private Element titleLabel = Element.byXpath("//*[@id='__next']//div[contains(@class,'header_training-header-main-info-title')]");
  private Element trainingStatus = Element.byClassName("training-banner__status");
  private Element registerButton = Element.byCss(".training-detail-header__button .register:not([class~='ng-hide'])");
  private Element popUpOkButton = Element.byClassName("message-btn");
  private Element cancelButton = Element.byCss(".training-detail-header__button .cancel");
  private Element submitButtonInCancelRegistrationPopUp = Element
      .byClassName("modal-body__submit-reason");
  private Element registrationResultPopUp =
      Element.byXpath("//div[@class='modal-body']//div[@class='message-content']");
  private Element describeReasonRegistrationCancellationPopUp =
      Element.byCss("div.modal-body.ng-scope");
  private Element registrationCancellationPopUpWarning = Element
      .byXpath("//div[contains(@class,'modal-body')]/div[contains(@class,'warning')]");
  private Element testingSystemPageLogo =
      Element.byXpath("//div[@class='Header__header__title___OWw88']");
  private Element examinatorPageAssignmentDetails =
      Element.byXpath("//div[contains(@class,'AssignmentDetails')]");
  private Element assignmentContainerLink = Element.byXpath(
      "//div[@class='container']//a[@class='test-link']");
  private Element englishTestLink =
      Element.byXpath("//div[@class='container']//a[@class='english-link']");
  private Element redirectToTestPopUp = Element.byClassName("message-form");
  private Element redirectToTestOkButton =
      Element.byCss("button.message-btn.message-btn-ok");
  private Element redirectToEnglishTestWarningMessage = Element.byCss("div.message-content");
  private Element examinatorPageEnglishTestTitle = Element
      .byXpath(
          "//div[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'english')]");
  private Element popUpSubmitButton = Element.byCss("button.popup-subscribe__submit");
  private Element popUp = Element.byClassName("modal-content");
  private Element popUpSurveySelectOptionInput = Element.byClassName("chosen-single");
  private Element popUpSurveyAnswerOptions = Element.byCss("ul.chosen-results > li");
  private Element viewButton = Element.byXpath("//*[@data-target='#trainingProgramModal']");
  private Element planLanguage = Element.byXpath("//training-language//div");
  private Element locationOfTraining = Element
      .byXpath("//div[@class='contacts-short-info-item__header']");
  private Element ongoingDurationOFTraining = Element
      .byXpath("//div[@ng-show='!endDate && !isPlannedTraining()']");
  private Element priorityNotification = Element
      .byXpath("//div[contains(@class,'training-detail-training-alert__content')]");
  private Element changeResourceNotificationMessage = Element
      .byXpath("//*[contains(@class,'message-content')]");
  private Element contactNameField = Element.byXpath("//span[contains(@class,'rd-contact')]");
  private Element contactInformationTooltip = Element.byCss("div.rd-contact-tooltip-info");
  private Element emailField = Element.byXpath("//div[@ng-if='email']/a");
  private Element phoneNumberField = Element.byXpath("//div[@ng-if='phone']/a");
  private Element headerSection = Element.byCss("div.training-detail-header.ng-scope");
  private Element formatField = Element.byCss(TRAINING_FORMAT_LOCATOR);
  private Element additionalInformationField = Element.byCss(
      "div.training-detail-header__additional-info");
  private Element priceField = Element.byCss("div.training-detail-header__price");
  private Element homeNavigationLink = Element.byCss("li.breadcrumb__item a[href*=Home]");
  private Element trainingListNavigationLink = Element.byCss(
      "li.breadcrumb__item a[href*=TrainingList]");
  private Element trainingInformationSection = Element.byCss("div.contacts-short-info");
  private Element contactTitle = Element
      .byXpath("(//div[contains(@class,'contacts-general-contact-hint')])[2]");
  private Element contactPhoneLink = Element.byXpath("(//a[contains(@class, 'info__phone')])[2]");
  private Element contactEmailLink = Element
      .byXpath("(//a[@class='contacts-general-contact-info__email'])[2]");
  private Element trainingDetailsTitle = Element.byCss(
      "div.training-detail-details__main-info-title");
  private Element trainingDetailsField = Element.byCss(
      "div.training-detail-details__main-info-text");
  private Element skillDescriptionSection = Element.byCss("div.training-detail-skills");
  private Element requiredSkillsTitle = Element.byCss("div.training-detail-required-skills__title");
  private Element requiredSkillsField = Element.byCss(
      "div.training-detail-required-skills__content");
  private Element technicalRequirementsButton = Element.byCss(
      "a.training-detail-required-skills__view-button");
  private Element notMeetRequirementsTitle = Element.byCss(
      "div.training-detail-additional-skills__title");
  private Element notMeetRequirementsField = Element.byCss(
      "div.training-detail-additional-skills__content");
  private Element whoIsTrainingForTitle = Element.byCss("div.training-detail-who__title");
  private Element forWhoTrainingField = Element.byCss("div.training-detail-who__content");
  private Element moreTrainingDetailsTitle = Element.byCss(
      "div.training-detail-more-details__title");
  private Element viewMoreTrainingDetailsButton = Element.byCss(
      "div.training-detail-more-details__view-button");
  private Element videoTitle = Element.byCss("div.training-detail-intro__title");
  private Element videoContent = Element.byCss("div.training-detail-intro");
  private Element howToJoinTitle = Element.byCss("div.training-detail-join__title");
  private Element howToJoinField = Element.byCss("div.training-detail-join__content");
  private Element paidConsultationsContent = Element.byCss("div.training-detail-paid-consultations__content");
  private Element pricingSection = Element.byCss("div.training-detail-pricing");
  private Element trainingStartDate = Element.byXpath(
      "//div[contains(@ng-show,'startDate')]/div[contains(@class,'contacts-short-info-item__header')]");
  private Element trainingDurationField = Element
      .byXpath(
          "//div[contains(@ng-show,'startDate')]/div[contains(@class,'contacts-short-info-item__line')][not(contains(@class,'ng-hide'))]");
  private Element trainingFrequencyField = Element.byCss("div[class^='price-block_price-block-additional-info-start-frequency']");
  private Element trainingCenterAddressField = Element
      .byXpath(
          "//div[contains(@class,'place-info')]/div[contains(@class,'contacts-short-info-item__line')]");
  private Element moreLinkOnSkillsSection = Element.byXpath("//p[@class='training-detail-skills__link']");
  private Element closeCommonIcon = Element.byXpath(
      "//span[contains(@class,'close-icon--common')]");
  private Element languageInfoSection = Element.byCss(LANGUAGE_FIELD_LOCATOR);
  private Element englishLanguageInInfoSection = Element.byXpath("//training-language[@language-id='English']");
  private Element russianLanguageInInfoSection = Element.byXpath("//training-language[@language-id='Russian']");
  private Element ukrainianLanguageInInfoSection = Element.byXpath("//training-language[@language-id='Ukrainian']");
  private Element trainingCustomBlockTitle = Element.byXpath(TRAINING_CUSTOM_BLOCK_TITLE_LOCATOR);
  private Element trainingCustomBlockInformation =
      Element.byXpath(TRAINING_CUSTOM_BLOCK_TITLE_LOCATOR + "/following-sibling::div//p");
  private Element subscribeButton = Element.byXpath("//*[@data-action='subscribe']");
  private Element detailsBlock = Element.byXpath("//div[@class= 'training-detail-details__main-info-title']");

  public String getTrainingDescriptionTitleText() {
    return titleLabel.getText();
  }

  @Override
  public boolean isScreenLoaded() {
    return titleLabel.isDisplayed(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
  }

  public boolean isLanguageInfoSectionDisplayed() {
    return languageInfoSection.isDisplayed();
  }

  public boolean isEnglishLanguageInInfoSectionDisplayed() {
    return englishLanguageInInfoSection.isDisplayed();
  }

  public boolean isRussianLanguageInInfoSectionDisplayed() {
    return russianLanguageInInfoSection.isDisplayed();
  }

  public boolean isUkrainianLanguageInInfoSectionDisplayed() {
    return ukrainianLanguageInInfoSection.isDisplayed();
  }

  public TrainingDescriptionScreen waitTrainingTitleLabelVisibility() {
    titleLabel.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public TrainingDescriptionScreen waitTrainingStatusTextPresent() {
    trainingStatus.waitForTextToBePresent();
    return this;
  }

  public RegistrationWizardScreen clickRegisterButton() {
    registerButton.click();
    return new RegistrationWizardScreen();
  }

  public TrainingDescriptionScreen clickSubmitButtonInCancelRegistrationPopUp() {
    submitButtonInCancelRegistrationPopUp.clickJs();
    return this;
  }

  public boolean isRegisterButtonDisplayed() {
    return registerButton.isDisplayed();
  }

  public TrainingDescriptionScreen waitRegisterButtonVisibility() {
    registerButton.waitForVisibility();
    return this;
  }

  public boolean isCancelButtonDisplayed() {
    return cancelButton.isDisplayed();
  }

  public TrainingDescriptionScreen waitCancelButtonVisibility() {
    cancelButton.waitForVisibility();
    return this;
  }

  public TrainingDescriptionScreen waitForClickableAndClickCancelButton() {
    cancelButton.mouseOver();
    cancelButton.waitForClickableAndClick();
    return this;
  }

  public String getRegistrationCancellationPopUpText() {
    return registrationCancellationPopUpWarning.getText();
  }

  public boolean isRegistrationCancellationPopUpDisplayed() {
    return describeReasonRegistrationCancellationPopUp.isDisplayed();
  }

  public TrainingDescriptionScreen waitRegistrationCancellationPopUpVisibility() {
    describeReasonRegistrationCancellationPopUp.waitForVisibility();
    return this;
  }

  public RegistrationWizardScreen clickPopUpSubmitButton() {
    popUpSubmitButton.click();
    return new RegistrationWizardScreen();
  }

  public boolean isPopUpSurveyDisplayed() {
    return popUp.isDisplayed();
  }

  public TrainingDescriptionScreen clickPopUpSelectOptionInput() {
    popUpSurveySelectOptionInput.click();
    return this;
  }

  public TrainingDescriptionScreen chosePopUpAnswerOption() {
    popUpSurveyAnswerOptions.click();
    return this;
  }

  public boolean isRegistrationSuccessfullyPopUpDisplayed() {
    return registrationResultPopUp.isDisplayed();
  }

  public String getRegistrationSuccessfulPopUpText() {
    return registrationResultPopUp.getText().replaceAll("\\s+", " ");
  }

  public TrainingDescriptionScreen clickPopUpOkButton() {
    popUpOkButton.clickJs();
    return this;
  }

  public boolean isTestingSystemPageLogoDisplayed() {
    return testingSystemPageLogo.isDisplayed();
  }

  public TrainingDescriptionScreen clickAssignmentContainerLink() {
    assignmentContainerLink.click();
    return this;
  }

  public boolean isExaminatorAssignmentDetailsDisplayed() {
    return examinatorPageAssignmentDetails.isDisplayed();
  }

  public TrainingDescriptionScreen waitRegistrationSuccessfullyPopUpDisappear() {
    registrationResultPopUp.waitForDisappear();
    return this;
  }

  public TrainingDescriptionScreen clickEnglishTestLink() {
    englishTestLink.click();
    return this;
  }

  public boolean isRedirectToTestPopUpDisplayed() {
    return redirectToTestPopUp.isDisplayed();
  }

  public TrainingDescriptionScreen clickRedirectToTestOkButton() {
    redirectToTestOkButton.scrollAndClick();
    return this;
  }

  public String getRedirectToEnglishTestWarningText() {
    return redirectToEnglishTestWarningMessage.getText();
  }

  public boolean isEnglishTestTitleDisplayed() {
    return examinatorPageEnglishTestTitle.waitForPresence().isDisplayed();
  }

  public TrainingProgramScreen clickViewButton() {
    viewButton.click();
    return new TrainingProgramScreen();
  }

  public boolean isOngoingDurationDisplayed() {
    return ongoingDurationOFTraining.isDisplayed();
  }

  public boolean isLocationOfTrainingDisplayed() {
    return locationOfTraining.isDisplayed();
  }

  public boolean isPlannedLanguageDisplayed() {
    return planLanguage.isDisplayed();
  }

  public String getOngoingDurationText() {
    return ongoingDurationOFTraining.getText().trim();
  }

  public String getLocationOfTrainingText() {
    return locationOfTraining.getText().trim();
  }

  public String getPlannedLanguageText() {
    return planLanguage.getText();
  }

  public boolean isPriorityNotificationDisplayed() {
    return priorityNotification.isDisplayed();
  }

  public String getPriorityNotificationText() {
    return priorityNotification.getText();
  }

  public String getChangeResourceNotificationText() {
    return changeResourceNotificationMessage.getText();
  }

  public TrainingDescriptionScreen mouseOverContactNameField() {
    contactNameField.mouseOverAndClickAndHold();
    return this;
  }

  public boolean isContactInformationTooltipDisplayed() {
    return contactInformationTooltip.isDisplayed();
  }

  public String getEmailFieldText() {
    return emailField.getText();
  }

  public String getPhoneNumberFieldText() {
    return phoneNumberField.getText();
  }

  public boolean isTrainingBannerPresent() {
    return headerSection.getCssValue(Attribute.BACKGROUND.toString())
        .contains(HEADER_TRAINING_IMAGE_PATH);
  }

  public String getPricingBarColor() {
    return Element.byCss(TRAINING_HEADER_LOCATOR)
        .getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public String getTrainingPricingText() {
    return Element.byCss(TRAINING_FORMAT_LOCATOR).getText();
  }

  public String getTrainingFormatText() {
    return formatField.getText();
  }

  public String getTrainingAdditionalInfoText() {
    return additionalInformationField.getText();
  }

  public String getPricingBackgroundImageValue() {
    return new PseudoElement().getPseudoElementCssPropertyValue(TRAINING_PRICE_LOCATOR,
        Attribute.BACKGROUND_IMAGE.toString(), PseudoElementEnum.BEFORE);
  }

  public String getFormatBackgroundImageValue() {
    return new PseudoElement().getPseudoElementCssPropertyValue(TRAINING_FORMAT_LOCATOR,
        Attribute.BACKGROUND_IMAGE.toString(), PseudoElementEnum.BEFORE);
  }

  public String getPriceText() {
    return priceField.getText();
  }

  public String getHomeNavigationLinkText() {
    return homeNavigationLink.getText();
  }

  public String getTrainingListNavigationLinkText() {
    return trainingListNavigationLink.getText();
  }

  public boolean isTrainingInformationSectionDisplayed() {
    return trainingInformationSection.isDisplayed();
  }

  public String getContactTitleText() {
    return contactTitle.getText();
  }

  public boolean isContactPhoneLinkDisplayed() {
    return contactPhoneLink.isDisplayed();
  }

  public boolean isContactEmailLinkDisplayed() {
    return contactEmailLink.isDisplayed();
  }

  public String getContactNameFieldText() {
    return contactNameField.getText();
  }

  public String getTrainingDetailsFieldText() {
    return trainingDetailsField.getText();
  }

  public String getTrainingDetailsTitleText() {
    return trainingDetailsTitle.getText();
  }

  public SkillDescriptionScreen clickMoreLinkOnSkillsSection() {
    moreLinkOnSkillsSection.click();
    return new SkillDescriptionScreen();
  }

  public boolean isSkillDescriptionBannerPresent() {
    return skillDescriptionSection.getCssValue(Attribute.BACKGROUND.toString())
        .contains(SKILL_DESCRIPTION_BANNER_PATH);
  }

  public String getRequiredSkillsFieldText() {
    return requiredSkillsField.getText();
  }

  public String getRequiredSkillsTitleText() {
    return requiredSkillsTitle.getText();
  }

  public String getTechnicalRequirementsButtonText() {
    return technicalRequirementsButton.getText();
  }

  public String getNotMeetRequirementsFieldText() {
    return notMeetRequirementsField.getText();
  }

  public String getNotMeetRequirementsTitleText() {
    return notMeetRequirementsTitle.getText();
  }

  public String getForWhoTrainingFieldText() {
    return forWhoTrainingField.getText();
  }

  public String getWhoIsTrainingForTitleText() {
    return whoIsTrainingForTitle.getText();
  }

  public String getMoreTrainingDetailsTitleText() {
    return moreTrainingDetailsTitle.getText();
  }

  public String getViewMoreTrainingDetailsButtonText() {
    return viewMoreTrainingDetailsButton.getText();
  }

  public String getVideoTitleText() {
    return videoTitle.getText();
  }

  public boolean isVideoContentDisplayed() {
    return videoContent.isDisplayed();
  }

  public String getHowToJoinTitleText() {
    return howToJoinTitle.getText();
  }

  public String getHowToJoinFieldText() {
    return howToJoinField.getText();
  }

  public String getPaidConsultationsContentText() {
    return paidConsultationsContent.getText();
  }

  public boolean isPricingSectionDisplayed() {
    return pricingSection.isDisplayed();
  }

  public TrainingDescriptionScreen waitTrainingInformationSectionForVisibility() {
    trainingInformationSection.waitForVisibility();
    return new TrainingDescriptionScreen();
  }

  public String getTrainingStartDateText() {
    return trainingStartDate.getText();
  }

  public String getTrainingDurationText() {
    return trainingDurationField.getText();
  }

  public String getTrainingFrequencyText() {
    return trainingFrequencyField.getText();
  }

  public String getLocationBackgroundImageValue() {
    return new PseudoElement().getPseudoElementCssPropertyValue(LOCATION_FIELD_LOCATOR,
        Attribute.BACKGROUND_IMAGE.toString(), PseudoElementEnum.BEFORE);
  }

  public String getTrainingCenterAddressText() {
    return trainingCenterAddressField.getText();
  }

  public String getLanguageBackgroundImageValue() {
    return new PseudoElement().getPseudoElementCssPropertyValue(LANGUAGE_FIELD_LOCATOR,
        Attribute.BACKGROUND_IMAGE.toString(), PseudoElementEnum.BEFORE);
  }

  public boolean isCloseCommonIconDisplayed() {
    return closeCommonIcon.isDisplayedNoWait();
  }

  public TrainingDescriptionScreen clickCloseCommonIcon() {
    closeCommonIcon.clickJs();
    return new TrainingDescriptionScreen();
  }

  public TrainingDescriptionScreen waitForPopUpOkButtonVisibility() {
    popUpOkButton.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public TrainingDescriptionScreen waitTrainingCustomBlockTitleVisibility() {
    trainingCustomBlockTitle.waitForVisibility();
    return this;
  }

  public String getTrainingCustomBlockTitle() {
    return trainingCustomBlockTitle.getText();
  }

  public TrainingDescriptionScreen waitTrainingCustomBlockInformationVisibility() {
    trainingCustomBlockInformation.waitForVisibility();
    return this;
  }

  public String getTrainingCustomBlockInformation() {
    return trainingCustomBlockInformation.getText();
  }

  public boolean isSubscribeButtonPresent() {
    return subscribeButton.isPresent();
  }

  public String getSubscribeButtonText() {
    return subscribeButton.getText();
  }

  public SubscribeScreen clickSubscribeButton(){
    subscribeButton.click();
    return new SubscribeScreen();
  }

  public TrainingDescriptionScreen cancelRegistrationIfNeed() {
    if(cancelButton.isDisplayed()) {
      cancelButton.mouseOverAndClick();
      submitButtonInCancelRegistrationPopUp.waitForClickableAndClick();
      registerButton.waitForVisibility();
    }
    return this;
  }

  public TrainingDescriptionScreen tryClickOnCityWithoutTrainingCenterOnContactsShortInfo(
      String cityName) {
    Element.byXpath(TRAINING_LOCATION_INFO_PATTERN, cityName).tryClick();
    return this;
  }

  public AboutCityTrainingCenterScreen clickOnCityWithTrainingCenterOnContactsShortInfo(
      String cityName) {
    clickLocationOnContactsShortInfo(cityName);
    return new AboutCityTrainingCenterScreen();
  }

  public AboutScreen clickOnCountryOnContactsShortInfo(String countryName) {
    clickLocationOnContactsShortInfo(countryName);
    return new AboutScreen();
  }

  private TrainingDescriptionScreen clickLocationOnContactsShortInfo(String location) {
    Element.byXpath(TRAINING_LOCATION_INFO_PATTERN, location).click();
    return this;
  }

  public boolean isDetailsBlockDisplayed() {
    return detailsBlock.isDisplayed();
  }
}
