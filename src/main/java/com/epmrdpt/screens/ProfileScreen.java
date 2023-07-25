package com.epmrdpt.screens;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.text.html.HTML;

public class ProfileScreen extends AbstractScreen {

  private static final String TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN
      = "//div[contains(@class,'education-border')]//%s/div[contains(@class,'description__item--row--title')]";
  private static final String UPLOADED_DOCUMENT = "//a[text()='%s']";
  private static final String REMOVE_RESUME_BUTTON = "//a[text()='%s']/following-sibling::div[contains(@class,'resume-item__remove')]";
  private static final String DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN
      = "//div[contains(@class,'education-border')]//%s/div[contains(@class,'description__item--row--descr')]";
  private static final String TITLE_OF_ROW_IN_WORK_SECTION_PATTERN
      = "//div[contains(@class,'content--work')]//%s/div[contains(@class,'description__item--row--title')]";
  private static final String DESCRIPTION_OF_ROW_IN_WORK_SECTION_PATTERN
      = "//div[contains(@class,'content--work')]//%s/div[contains(@class,'description__item--row--descr')]";
  private static final String ROW_OF_BASIC_INFO_SECTION = "//div[@class='user-profile__basic-info__about']/%s/div[contains(@class,'about--info')]";
  private static final String DATE_DELIMITER = " - ";
  private static final String CITY_DELIMITER = ", ";
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
      .ofPattern("d.MM.yyyy");
  private static final String TEXT_CONTENT_ATTRIBUTE = "textContent";
  private static final String DIV_1 = "div[1]";
  private static final String DIV_2 = "div[2]";
  private static final String DIV_3 = "div[3]";
  private static final String DIV_4 = "div[4]";
  private static final String DIV_5 = "div[5]";
  private static final String DIV_6 = "div[6]";
  private static final String DIV_7 = "div[7]";
  private static final String DIV_8 = "div[8]";
  private static final String DIV_9 = "div[9]";
  private static final String DIV_10 = "div[10]";
  private static final String APPLICATION_TAB_HEADER_LOCATOR_PATTERN =
      "//div[contains(@class,'rd-table__header-text')][contains(text(),'%s')]";
  private static final String ATTACHED_DOCUMENT_BY_INDEX_PATTERN = "div.resume-item:nth-child(%d)";
  private static final String NAME_OF_ATTACHED_DOCUMENT_BY_INDEX_PATTERN =
      ATTACHED_DOCUMENT_BY_INDEX_PATTERN + " a";
  private static final String DATE_OF_UPLOADING_DOCUMENT_BY_INDEX_PATTERN =
      ATTACHED_DOCUMENT_BY_INDEX_PATTERN + " div";
  private static final String CROSS_BUTTON_IN_ATTACHED_DOCUMENT_FIELD_BY_INDEX_PATTERN =
      ATTACHED_DOCUMENT_BY_INDEX_PATTERN + " div.resume-item__remove";
  private static final String WARNING_ENGLISH_TEST_TOOLTIP = "div.english-test-tooltip";
  private static final String WARNING_ENGLISH_TEST_ICON = "div.user-profile__basic-info__about--warning-icon";
  private static final String ENGLISH_TEST_EXPIRED_PATTERN = "rd-tooltip[ng-if*='Expired'] ";
  private static final String TRAINING_STATUS_PATTERN = "//div[text() = '%s']//following::span//span[@class = 'ng-binding']";

  private Element professionalInfoButton =
      Element.byXpath("//tabs/div[contains(@class,'tabs__info-tab')][2]");
  private Element englishTestButton = Element.byXpath("//*[@id='user-profile-basic-info']//button");
  private Element continueEnglishTestButton =
      Element.byXpath("//a[contains(@ng-if,'ContinueEnglishTest')]");
  private Element educationBlockName =
      Element.byCss("div.user-profile-info__content--education div.tablet-collapsed-heading");
  private Element cityOfStudyTitle = Element.byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN,
      DIV_1);
  private Element cityOfStudyDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_1);
  private Element educationalEstablishmentTitle = Element
      .byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_2);
  private Element educationalEstablishmentDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_2);
  private Element facultyTitle = Element.byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_3);
  private Element facultyDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_3);
  private Element departmentTitle = Element
      .byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_4);
  private Element departmentDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_4);
  private Element educationFormTitle = Element
      .byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_5);
  private Element educationFormDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_5);
  private Element degreeInformationTitle = Element
      .byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_6);
  private Element degreeInformationDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_6);
  private Element yearTitle = Element.byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_7);
  private Element yearDescription = Element.byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_7);
  private Element graduationYearTitle = Element
      .byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_8);
  private Element graduationYearDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_8);
  private Element assignmentTitle = Element
      .byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_9);
  private Element assignmentDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_9);
  private Element companyTitle = Element.byXpath(TITLE_OF_ROW_IN_EDUCATION_SECTION_PATTERN, DIV_10);
  private Element companyDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_EDUCATION_PATTERN, DIV_10);
  private Element englishLevelFieldTitle =
      Element.byXpath("//div[@class='user-profile__basic-info__about']/div[3]/div[1]");
  private Element workExperienceBlockName =
      Element.byCss("div.user-profile-info__content--work-experience div.tablet-collapsed-heading");
  private Element companyNameTitle = Element.byXpath(TITLE_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_1);
  private Element companyNameDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_1);
  private Element positionTitle = Element.byXpath(TITLE_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_2);
  private Element positionDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_2);
  private Element periodTitle = Element.byXpath(TITLE_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_3);
  private Element workPeriodDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_3);
  private Element additionalInformationAboutWorkTitle = Element
      .byXpath(TITLE_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_4);
  private Element additionalInformationAboutWorkDescription = Element
      .byXpath(DESCRIPTION_OF_ROW_IN_WORK_SECTION_PATTERN, DIV_4);
  private Element professionalSkillsBlockName = Element
      .byCss("div.user-profile-info__content--professional-skills div.tablet-collapsed-heading");
  private Element professionalSkillsBlockDescription = Element
      .byCss("div.user-profile-info__content--professional-skills div.tablet-collapsed");
  private Element additionalInformationBlockName =
      Element.byCss("div[ng-if*='basicInfo.AdditionalInfo'] div.tablet-collapsed-heading");
  private Element additionalInformationBlockDescription =
      Element.byCss("div[ng-if*='basicInfo.AdditionalInfo'] div.tablet-collapsed");
  private Element professionalActivitiesBlockName =
      Element.byCss("div[ng-if*='basicInfo.ProfessionalActivities'] div.tablet-collapsed-heading");
  private Element professionalActivitiesBlockDescription =
      Element.byCss("div[ng-if*='basicInfo.ProfessionalActivities'] div.tablet-collapsed");
  private Element documentsBlockName = Element
      .byCss("div.user-profile-info__content--resume>div.user-profile-info__content--heading");
  private Element linkToPortfolio = Element
      .byCss(
          "div.user-profile-info__content--resume div.user-profile-info__content--description p a");
  private Element documentsBlockDescription = Element
      .byCss(
          "div.user-profile-info__content--resume div.user-profile-info__content--description p + p");
  private Element uploadDocumentButton = Element.byXpath("//button[@name='resumeName']");
  private Element documentDescription = Element.byCss("div.resume-hint");
  private Element attachedDocumentBlock = Element.byCss("div.field-block");
  private Element attachedDocumentField = Element.byXpath("//div[@class='resume-item ng-scope']");
  private Element downloadAllDocumentsButton = Element.byClassName(
      "user-profile-info__resume-download");
  private Element redirectToEnglishTestPopUp = Element.byCss("div.popup.confirmation-popup");
  private Element redirectToEnglishTestOkButton =
      Element.byCss("button.message-btn.message-btn-ok");
  private Element profilePhoto = Element.byCss("div.user-profile__basic-info__photo > div");
  private Element editProfileLink = Element.byXpath("//a[@href='UserProfile#!/Edit/'][1]");
  private Element contactMailIcon = Element
      .byXpath(
          "//div[@class='user-profile__basic-info__description--contacts-blocks__block icon-mail']/*[@class='ng-isolate-scope']");
  private Element contactPhoneIcon = Element.byXpath("//div[contains(@class,'icon-phone')]");
  private Element contactPhone = Element
      .byXpath("//div[contains(@class, 'icon-phone')]//div[@class='link-from-text']");
  private Element firstName = Element
      .byClassName("user-profile__basic-info__description--first-name");
  private Element lastName = Element
      .byClassName("user-profile__basic-info__description--last-name");
  private Element trainingInfoButton =
      Element.byXpath("//tabs/div[contains(@class,'tabs__info-tab')][1]");
  private Element residenceFieldTitle =
      Element
          .byXpath("//div[contains(@class, 'user-profile__basic-info__description--extra-info')][1]/span[2]");
  private Element birthDateFieldTitle =
      Element
          .byXpath("//div[@class='user-profile__basic-info__description--extra-info']/span[2]");
  private Element profileCreationDateFieldTitle =
      Element.byCss("div:nth-child(1) > div.user-profile__basic-info__about--title");
  private Element preferredMethodsOfCommunicationFieldTitle =
      Element.byXpath("//div[@class='user-profile__basic-info__about']/div[2]/div[1]");
  private Element applicationsHeader =
      Element.byCss("div.tab-headers__header.ng-binding.ng-scope.tab-headers__header--active");
  private Element userFirstName = Element
      .byCss("span.user-profile__basic-info__description--first-name");
  private Element userLastName = Element
      .byCss("span.user-profile__basic-info__description--last-name");
  private Element balloonIcon = Element
      .byClassName("user-profile__basic-info__description--balloon-icon");
  private Element englishTestResultTitle = Element
      .byXpath("//div[@class='user-profile__basic-info__about']/div[4]/div[1]");
  private Element spinnerOfLoading =
      Element.byXpath("//i[contains(@class,'spinner-item')]");
  private Element defaultMainProfilePhoto = Element.byXpath("//div[contains(@style,'default-photo')]");
  private Element defaultAvatarProfilePhoto = Element.byXpath("//div[contains(@style,'background-image')]");

  private Element englishTestAndLevel = Element
      .byXpath("//div[contains(@style,'url(\"blob')]");
  private Element openInformationTab = Element.byXpath("//div[contains(@ng-if,'openInformation')]");
  private Element userEmail = Element.byXpath(
      "//link-from-text[@text-origin='basicInfo.Email']//span[@ng-if='!isValidLink']");
  private Element companyName = Element
      .byXpath(String.format("//div[contains(text(),'%s')]/following-sibling::div",
          LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_COMPANY_TITLE)));
  private Element additionalInformationDescription = Element
      .byXpath("//div[@*='basicInfo.AdditionalInfo']//div[@title]");
  private Element professionalActivities = Element
      .byXpath("//div[@*='basicInfo.ProfessionalActivities']//div[@title]");
  private Element englishLevel = Element.byXpath(
      "//div[@class='user-profile__basic-info__about--extra-info']/span");
  private Element birthDate = Element
      .byXpath("//span[contains(@class, 'balloon')]//following-sibling::span");
  private Element countryAndCityOfResidence = Element
      .byXpath("//span[contains(@class, 'geo')]//following-sibling::span");
  private Element preferredMethodsOfCommunicationField = Element
      .byXpath(ROW_OF_BASIC_INFO_SECTION, DIV_2);
  private Element arrowOfNativeName = Element.byXpath("//span[@*='basicInfo.NativeName']");
  private Element nativeName = Element
      .byXpath("//span[@*='basicInfo.NativeName']//following-sibling::div//div[@ng-bind]");
  private Element skypeIcon = Element
      .byXpath(
          "//div[@*='user-profile__basic-info__description']//div[contains(@class,'Skype')]/..");
  private Element skypeName = Element.byXpath(
      "//div[contains(@class, 'icon-Skype')]//div[@class='link-from-text']");
  private Element workExperienceCompanyName = Element
      .byXpath(String.format("//div[contains(text(),'%s')]/following-sibling::div",
          LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_WORK_EXPERIENCE_COMPANY_NAME)));
  private Element englishResultMessage = Element.byXpath(
      "//div[contains(@ng-if, 'englishInfo.IsAllowedToContinueEnglishTest')]/div[contains(@class, 'user-profile__basic-info__about--info')]");
  private Element infoIconOfEnglishTest = Element
      .byClassName("user-profile__basic-info__about--info-icon");
  private Element infoIconPopUpOfEnglishTest = Element
      .byXpath(
          "//div[contains(@class,'user-profile__basic-info__about--info-icon')]/parent::div//span[@class='ng-binding']");
  private Element mapMarkerIcon = Element.byCss(
      "span.user-profile__basic-info__description--balloon-icon");
  private Element mapMarkerText = Element.byCss(
      "span.user-profile__basic-info__description--balloon-icon+span");
  private Element warningIcon = Element.byCss(WARNING_ENGLISH_TEST_ICON);
  private Element warningExpiredEnglishTestIcon = Element.byCss(
      ENGLISH_TEST_EXPIRED_PATTERN + WARNING_ENGLISH_TEST_ICON);
  private Element warningTooltip = Element.byCss(WARNING_ENGLISH_TEST_TOOLTIP);
  private Element warningExpiredEnglishTestTooltip = Element.byCss(
      ENGLISH_TEST_EXPIRED_PATTERN + WARNING_ENGLISH_TEST_TOOLTIP);
  private Element TrainingInfoSectionApplicationsTab = Element
      .byXpath("//div[@data-id='applications-tab']");
  private Element searchInput = Element.byXpath("//*[contains(@class,'training-search__input')]");
  private Element findButton = Element.byXpath("//*[contains(@class,'find-button')]");
  private Element paginationSector = Element
      .byXpath("//*[contains(@class,'desktop')]/*[contains(@class,'pagination')]");
  private Element englishTestResult = Element.byCss(
      "div.user-profile__basic-info__about--extra-info.ng-scope");
  private Element userOpenInformation = Element
      .byXpath(
          "//div[contains(@ng-if,'openInformation.Email')]//div[contains(@class,'user-profile-info__content--description__item--row--descr')]");
  private Element uploadDocumentsInput = Element.byXpath("//input[@name='resumeName']");
  private Element messageFileUploadedPopUpText = Element.byXpath(
      "//div[@class='status-popup visible-status-popup success']");
  private Element assignEnglishTestButton = Element
      .byXpath("//button[@ng-click='assignEnglishTest()']");
  private Element confirmInformationPopUp = Element.byClassName("popup__container");
  private Element popupHeaderTitle = Element.byXpath("//div[@class='popup-title__name']");
  private Element popupCross = Element.byXpath("//div[@class='popup-title__close']");
  private Element popupOkButton = Element.byXpath("//*[@ng-click='ok()']");
  private Element popupCancelButton = Element.byXpath("//button[@ng-click='cancel()']");
  private Element popupMessage = Element.byXpath("//div[contains(@class,'message-content')]");
  private Element educationTab = Element.byXpath("//div[contains(@class,'education-border')]");
  private Element takeTestButton = Element.byXpath(
      "//button[@ng-if='englishInfo.IsAllowedToTakeEnglishTest && !userId']");
  private Element socialNetworksIcon = Element.byClassName("icon-LinkedIn");
  private Element emailIcon = Element.byClassName("icon-mail");
  private Element applicationsTab = Element.byXpath("//div[contains(text(),'Applications')]");
  private Element profileCreationDateFieldText =
      Element.byCss("div:nth-child(1) > div.user-profile__basic-info__about--info");

  @Override
  public boolean isScreenLoaded() {
    return profilePhoto.isDisplayed(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
  }

  public ProfileScreen waitScreenLoading() {
    profilePhoto.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public ProfileScreen waitEducationTabLoading() {
    educationTab.waitForVisibility();
    return this;
  }

  public ProfileScreen clickProfessionalInfoButton() {
    professionalInfoButton.clickJs();
    return this;
  }

  public boolean isContinueEnglishTestButtonDisplayed() {
    return continueEnglishTestButton.isDisplayed();
  }

  public ProfileScreen clickEnglishTestButtonIfExists() {
    if (englishTestButton.isDisplayed(SHORT_TIME_OUT_IN_SECONDS)) {
      englishTestButton.click();
    } else {
      continueEnglishTestButton.click();
    }
    return this;
  }

  public ProfileScreen waitForAssignEnglishTestButtonVisibility() {
    assignEnglishTestButton.waitForVisibility();
    return this;
  }

  public ProfileScreen clickAssignEnglishTestButton() {
    assignEnglishTestButton.click();
    return this;
  }


  public ProfileScreen clickEnglishTestButton() {
    englishTestButton.click();
    return this;
  }

  public boolean isEditProfileLinkDisplayed() {
    return editProfileLink.isDisplayed();
  }

  public String getEditProfileLinkText() {
    return editProfileLink.getText();
  }

  public boolean isProfilePhotoDisplayed() {
    return profilePhoto.isDisplayed();
  }

  public boolean isContactEmailIconDisplayed() {
    return contactMailIcon.isDisplayed();
  }

  public boolean isContactPhoneIconDisplayed() {
    return contactPhoneIcon.isDisplayed();
  }

  public boolean isEducationBlockDisplayed() {
    return educationBlockName.isDisplayed();
  }

  public String getEducationBlockText() {
    return educationBlockName.getText();
  }

  public boolean isCityOfStudyTitleDisplayed() {
    return cityOfStudyTitle.isDisplayed();
  }

  public String getCityOfStudyTitleText() {
    return cityOfStudyTitle.getText();
  }

  public String getCityOfStudyDescriptionText() {
    return cityOfStudyDescription.getText();
  }

  public String getMessageFileUploadedPopUpText() {
    return messageFileUploadedPopUpText.waitForVisibility().getText();
  }

  public boolean isEducationalEstablishmentTitleDisplayed() {
    return educationalEstablishmentTitle.isDisplayed();
  }

  public String getEducationalEstablishmentTitleText() {
    return educationalEstablishmentTitle.getText();
  }

  public String getEducationalEstablishmentDescriptionText() {
    return educationalEstablishmentDescription.getText();
  }

  public boolean isFacultyTitleDisplayed() {
    return facultyTitle.isDisplayed();
  }

  public String getFacultyFieldFTitleText() {
    return facultyTitle.getText();
  }

  public String getFacultyDescriptionText() {
    return facultyDescription.getText();
  }

  public String getDepartmentDescriptionText() {
    return departmentDescription.getText();
  }

  public boolean isDepartmentTitleDisplayed() {
    return departmentTitle.isDisplayed();
  }

  public String getDepartmentTitleText() {
    return departmentTitle.getText();
  }

  public boolean isEducationFormTitleDisplayed() {
    return educationFormTitle.isDisplayed();
  }

  public String getEducationFormTitleText() {
    return educationFormTitle.getText();
  }

  public String getEducationFormDescriptionText() {
    return educationFormDescription.getText();
  }

  public boolean isDegreeInformationTitleDisplayed() {
    return degreeInformationTitle.isDisplayed();
  }

  public String getDegreeInformationTitleText() {
    return degreeInformationTitle.getText();
  }

  public String getDegreeInformationDescriptionText() {
    return degreeInformationDescription.getText();
  }

  public boolean isYearTitleDisplayed() {
    return yearTitle.isDisplayed();
  }

  public String getYearTitleText() {
    return yearTitle.getText();
  }

  public String getYearDescriptionText() {
    return yearDescription.getText();
  }

  public boolean isGraduationYearTitleDisplayed() {
    return graduationYearTitle.isDisplayed();
  }

  public String getGraduationYearTitleText() {
    return graduationYearTitle.getText();
  }

  public String getGraduationYearDescriptionText() {
    return graduationYearDescription.getText();
  }

  public boolean isAssignmentTitleDisplayed() {
    return assignmentTitle.isDisplayed();
  }

  public String getAssignmentTitleText() {
    return assignmentTitle.getText();
  }

  public boolean isCompanyTitleDisplayed() {
    return companyTitle.isDisplayed();
  }

  public String getCompanyTitleText() {
    return companyTitle.getText();
  }

  public String getCompanyDescriptionText() {
    return companyDescription.getText();
  }

  public boolean isEnglishLevelFieldTitleDisplayed() {
    return englishLevelFieldTitle.isDisplayed();
  }

  public String getEnglishLevelFieldTitleText() {
    return englishLevelFieldTitle.getText();
  }

  public boolean isWorkExperienceDisplayed() {
    return workExperienceBlockName.isDisplayed();
  }

  public String getWorkExperienceBlockText() {
    return workExperienceBlockName.getText();
  }

  public boolean isCompanyNameTitleDisplayed() {
    return companyNameTitle.isDisplayed();
  }

  public String getCompanyNameTitleText() {
    return companyNameTitle.getText();
  }

  public String getCompanyNameDescriptionText() {
    return companyNameDescription.getText();
  }

  public boolean isPositionTitleDisplayed() {
    return positionTitle.isDisplayed();
  }

  public String getPositionTitleText() {
    return positionTitle.getText();
  }

  public String getPositionDescriptionText() {
    return positionDescription.getText();
  }

  public boolean isPeriodTitleDisplayed() {
    return periodTitle.isDisplayed();
  }

  public String getPeriodTitleText() {
    return periodTitle.getText();
  }

  public String getStartWorkPeriodDescriptionText() {
    int startWorkIndex = 0;
    return workPeriodDescription.getText().trim().split(DATE_DELIMITER)[startWorkIndex];
  }

  public String getEndWorkPeriodDescriptionText() {
    int endWorkIndex = 1;
    return workPeriodDescription.getText().trim().split(DATE_DELIMITER)[endWorkIndex];
  }

  public boolean isAdditionalInformationAboutWorkTitleDisplayed() {
    return additionalInformationAboutWorkTitle.isDisplayed();
  }

  public String getAdditionalInformationAboutWorkTitleText() {
    return additionalInformationAboutWorkTitle.getText();
  }

  public String getAdditionalInformationAboutWorkDescriptionText() {
    return additionalInformationAboutWorkDescription.getText();
  }

  public boolean isProfessionalSkillsBlockDisplayed() {
    return professionalSkillsBlockName.isDisplayed();
  }

  public String getProfessionalSkillsBlockText() {
    return professionalSkillsBlockName.getText();
  }

  public String getProfessionalSkillsDescriptionText() {
    return professionalSkillsBlockDescription.getText();
  }

  public String getAdditionalInformationBlockTitle() {
    return additionalInformationBlockName.getText();
  }

  public String getAdditionalInformationBlockDescription() {
    return additionalInformationBlockDescription.getText();
  }

  public String getProfessionalActivitiesBlockTitle() {
    return professionalActivitiesBlockName.getText();
  }

  public String getProfessionalActivitiesBlockDescription() {
    return professionalActivitiesBlockDescription.getText();
  }

  public boolean isEnglishTestButtonDisplayed() {
    return englishTestButton.isDisplayed();
  }

  public boolean isRedirectToEnglishTestPopUpDisplayed() {
    return redirectToEnglishTestPopUp.isDisplayed();
  }

  public ProfileScreen clickIfDisplayedRedirectToEnglishTestOkButton() {
    if (redirectToEnglishTestOkButton.isDisplayed()) {
      redirectToEnglishTestOkButton.scrollAndClick();
    }
    return this;
  }

  public boolean isConfirmInformationPopUpDisplayedNotWait() {
    return confirmInformationPopUp.isDisplayedNoWait();
  }

  public ProfileScreen waitPopUpDisappear() {
    confirmInformationPopUp.waitForInvisibility();
    return this;
  }

  public ProfileScreen waitPopUpAppear() {
    confirmInformationPopUp.waitForVisibility();
    return this;
  }

  public boolean isFirstNameDisplayed() {
    return firstName.isDisplayed();
  }

  public boolean isLastNameDisplayed() {
    return lastName.isDisplayed();
  }

  public String getUserFirstNameText() {
    return userFirstName.getText();
  }

  public String getUserLastNameText() {
    return userLastName.getText();
  }

  public String getUserFullNameText() {
    return (getUserFirstNameText() + " " + getUserLastNameText());
  }

  public boolean isTrainingInfoButtonDisplayed() {
    return trainingInfoButton.isDisplayed();
  }

  public ProfileEditionScreen clickEditProfileLink() {
    editProfileLink.click();
    return new ProfileEditionScreen();
  }

  public ProfileScreen waitEditProfileLinkDisplayed() {
    editProfileLink.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public boolean isProfessionalInfoButtonDisplayed() {
    return professionalInfoButton.isDisplayed();
  }

  public ProfileScreen waitProfessionalInfoButtonClickable() {
    professionalInfoButton.waitForClickable();
    return this;
  }

  public ProfileScreen waitUploadDocumentButtonClickable() {
    uploadDocumentButton.waitForClickable();
    return this;
  }

  public boolean isResidenceFieldTitleDisplayed() {
    return residenceFieldTitle.isDisplayed();
  }

  public String getTrainingInfoButtonText() {
    return trainingInfoButton.getText();
  }

  public String getProfessionalInfoButtonText() {
    return professionalInfoButton.getText();
  }

  public String getResidenceFieldTitleText() {
    return residenceFieldTitle.getText();
  }

  public boolean isBirthDateFieldTitleDisplayed() {
    return birthDateFieldTitle.isDisplayed();
  }

  public String getProfileCreationDateFieldTitleText() {
    return profileCreationDateFieldTitle.getText();
  }

  public boolean isProfileCreationDateFieldTitleDisplayed() {
    return profileCreationDateFieldTitle.isDisplayed();
  }

  public String getPreferredMethodsOfCommunicationFieldTitleText() {
    return preferredMethodsOfCommunicationFieldTitle.getText();
  }

  public boolean isPreferredMethodsOfCommunicationFieldTitleDisplayed() {
    return preferredMethodsOfCommunicationFieldTitle.isDisplayed();
  }

  public boolean isActiveApplicationsPresentNoWait() {
    return applicationsHeader.isPresentNoWait();
  }

  public boolean isBalloonIconDisplayed() {
    return balloonIcon.isDisplayed();
  }

  public boolean isTestResultTitleDisplayed() {
    return englishTestResultTitle.isDisplayed();
  }

  public String getTestResultTitleText() {
    return englishTestResultTitle.getText();
  }

  public String getEnglishTestButtonText() {
    return englishTestButton.getText();
  }

  public boolean isDocumentsBlockDisplayed() {
    return documentsBlockName.isDisplayed();
  }

  public String getDocumentsBlockTitleText() {
    return documentsBlockName.getText();
  }

  public boolean isLinkToPortfolioDisplayed() {
    return linkToPortfolio.isDisplayed();
  }

  public String getLinkToPortfolioText() {
    return linkToPortfolio.getText();
  }

  public String getDocumentsBlockDescriptionText() {
    return documentsBlockDescription.getText();
  }

  public boolean isUploadButtonDisplayed() {
    return uploadDocumentButton.isDisplayed();
  }

  public String getUploadButtonText() {
    return uploadDocumentButton.getText();
  }

  public String getDocumentDescriptionText() {
    return documentDescription.getText();
  }

  public boolean isAttachedDocumentBlockDisplayed() {
    return attachedDocumentBlock.isDisplayed();
  }

  public int getAttachedDocumentCount() {
    return attachedDocumentField.getElements().size();
  }

  public boolean isNameOfAttachedDocumentDisplayedByIndex(int index) {
    return Element.byCss(NAME_OF_ATTACHED_DOCUMENT_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public boolean isDateOfUploadingDocumentDisplayedByIndex(int index) {
    return Element.byCss(DATE_OF_UPLOADING_DOCUMENT_BY_INDEX_PATTERN, index).isDisplayed();
  }

  public String getDateOfUploadingDocumentTextByIndex(int index) {
    return Element.byCss(DATE_OF_UPLOADING_DOCUMENT_BY_INDEX_PATTERN, index).getText();
  }

  public boolean isCrossButtonInAttachedDocumentFieldDisplayedByIndex(int index) {
    return Element.byCss(CROSS_BUTTON_IN_ATTACHED_DOCUMENT_FIELD_BY_INDEX_PATTERN, index)
        .isDisplayed();
  }

  public boolean isDownloadAllDocumentsButtonDisplayed() {
    return downloadAllDocumentsButton.isDisplayed();
  }

  public String getDownloadAllDocumentsButtonText() {
    return downloadAllDocumentsButton.getText();
  }

  public ProfileScreen waitEnglishTestButtonVisibility() {
    englishTestButton.waitForVisibility();
    return this;
  }

  public TrainingInfoTabOnProfilePageScreen clickTrainingInfo() {
    trainingInfoButton.click();
    return new TrainingInfoTabOnProfilePageScreen();
  }

  public ProfileScreen waitTrainingInfoDisplayed() {
    trainingInfoButton.waitForVisibility();
    return this;
  }

  public ProfileScreen waitSpinnerOfLoadingInvisibility() {
    spinnerOfLoading.waitForInvisibility();
    return this;
  }

  public boolean isDefaultAvatarProfilePhotoDisplayed() {
    return defaultAvatarProfilePhoto.isDisplayed();
  }

  public boolean waitDefaultMainProfilePhotoForInvisibility() {
    return defaultMainProfilePhoto.waitForInvisibility();
  }

  public boolean isDefaultMainProfilePhotoDisplayed() {
    return defaultMainProfilePhoto.isDisplayed();
  }

  public ProfileScreen waitForProfileScreenCompletelyLoadedAfterRefresh() {
    englishLevel.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public boolean isOpenInformationTabDisplayed() {
    return openInformationTab.isDisplayed();
  }

  public ProfileScreen waitContactMailIconVisibilty() {
    contactMailIcon.waitForVisibility();
    return this;
  }

  public String getUserEmailText() {
    return userEmail.getText();
  }

  public String getCompanyName() {
    return companyName.getText();
  }

  public ProfileScreen hoverOverContactMailIcon() {
    contactMailIcon.mouseOver();
    return this;
  }

  public String getFirstNameText() {
    return firstName.getText();
  }

  public String getLastNameText() {
    return lastName.getText();
  }

  public int getPhoneNumberText() {
    contactPhone.waitForVisibility();
    return Integer.parseInt(contactPhone.getText());
  }

  public int getEducationSectionsQuantity() {
    return cityOfStudyDescription.getElements().size();
  }

  public String getCityOfStudyValueByIndex(int cityOfStudyIndex) {
    return getValueByIndex(cityOfStudyDescription, cityOfStudyIndex);
  }

  public String getEducationEstablishmentValueByIndex(int educationEstablishmentIndex) {
    return getValueByIndex(educationalEstablishmentDescription, educationEstablishmentIndex);
  }

  public String getFacultyValueByIndex(int facultyIndex) {
    return getValueByIndex(facultyDescription, facultyIndex);
  }

  public String getDepartmentValueByIndex(int departmentIndex) {
    return getValueByIndex(departmentDescription, departmentIndex);
  }

  public String getEducationFormValueByIndex(int educationFormIndex) {
    return getValueByIndex(educationFormDescription, educationFormIndex);
  }

  public String getDegreeInformationValueByIndex(int degreeInformationIndex) {
    return getValueByIndex(degreeInformationDescription, degreeInformationIndex);
  }

  public String getUniversityCourseValueByIndex(int courseIndex) {
    return getValueByIndex(yearDescription, courseIndex);
  }

  public String getGraduationYearValueByIndex(int graduationYearIndex) {
    return getValueByIndex(graduationYearDescription, graduationYearIndex);
  }

  public String getAssignmentValueByIndex(int assignmentIndex) {
    return getValueByIndex(assignmentDescription, assignmentIndex);
  }

  public String getWorkExperienceCompanyNameTextByIndex(int companyNameIndex) {
    return getValueByIndex(workExperienceCompanyName, companyNameIndex);
  }

  public String getAdditionalInformationEducationSection() {
    return additionalInformationDescription.getText();
  }

  public String getProfessionalActivities() {
    return professionalActivities.getText();
  }

  public String getEnglishLevelValue() {
    return englishLevel.getText();
  }

  private String getValueByIndex(Element element, int index) {
    return element.getElements().get(index).getText();
  }

  public int getWorkSectionsQuantity() {
    if (!workExperienceCompanyName.isDisplayedNoWait()) {
      return 0;
    }
    return workExperienceCompanyName.getElements().size();
  }

  public String getPositionNameTextByIndex(int positionIndex) {
    return getValueByIndex(positionDescription, positionIndex);
  }

  public String getAdditionalInformationWorkByIndex(int additionalInfoIndex) {
    return additionalInformationAboutWorkDescription.getElements().get(additionalInfoIndex)
        .getAttributeValue(TEXT_CONTENT_ATTRIBUTE);
  }

  public LocalDate getStartDateByIndex(int workIndex) {
    int startWorkIndex = 0;
    return LocalDate
        .parse(getValueByIndex(workPeriodDescription, workIndex).trim()
                .split(DATE_DELIMITER)[startWorkIndex],
            DATE_TIME_FORMATTER);
  }

  public LocalDate getEndDateByIndex(int workIndex) {
    int endWorkIndex = 1;
    return LocalDate
        .parse(getValueByIndex(workPeriodDescription, workIndex).trim()
                .split(DATE_DELIMITER)[endWorkIndex],
            DATE_TIME_FORMATTER);
  }

  public LocalDate getBirthDate() {
    return LocalDate.parse(birthDate.getText(), DATE_TIME_FORMATTER);
  }

  public String getCityOfResidenceValue() {
    int cityIndex = 1;
    return countryAndCityOfResidence.getText().split(CITY_DELIMITER)[cityIndex];
  }

  public String getPreferredMethodOfCommunication() {
    return preferredMethodsOfCommunicationField.getText();
  }

  public String getNativeName() {
    return nativeName.getText();
  }

  public ProfileScreen clickArrowOfNativeName() {
    arrowOfNativeName.click();
    return this;
  }

  public String getSkypeLogin() {
    skypeIcon.mouseOver();
    skypeName.waitForVisibility();
    return skypeName.getText();
  }

  public String getEnglishResultMessageText() {
    return englishResultMessage.getText().trim();
  }

  public ProfileScreen hoverOnInfoIconOfEnglishTest() {
    infoIconOfEnglishTest.mouseOver();
    return this;
  }

  public String getInfoIconPopUpOfEnglishTest() {
    return infoIconPopUpOfEnglishTest.getText();
  }

  public boolean isMapMarkerIconDisplayed() {
    return mapMarkerIcon.isDisplayed();
  }

  public boolean isMapMarkerTextDisplayed() {
    return mapMarkerText.isDisplayed();
  }

  public boolean isWarningIconDisplayed() {
    return warningIcon.isDisplayed();
  }

  public ProfileScreen hoverOverWarningIcon() {
    warningIcon.mouseOver();
    return this;
  }

  private void hoverOverExpiredEnglishTestWarningIcon() {
    warningExpiredEnglishTestIcon.mouseOver();
  }

  public boolean isWarningTooltipDisplayed() {
    hoverOverWarningIcon();
    return warningTooltip.isDisplayed();
  }

  public String getWarningTooltipText() {
    hoverOverWarningIcon();
    return warningTooltip.getText();
  }

  public String getWarningExpiredEnglishTestTooltipText() {
    hoverOverExpiredEnglishTestWarningIcon();
    return warningExpiredEnglishTestTooltip.getText();
  }

  public ProfileScreen clickApplicationsTab() {
    TrainingInfoSectionApplicationsTab.click();
    return this;
  }

  public boolean isApplicationTabHeaderDisplayed(String headerName) {
    return Element.byXpath(APPLICATION_TAB_HEADER_LOCATOR_PATTERN, headerName).isDisplayed();
  }

  public boolean isSearchInputDisplayed() {
    return searchInput.isDisplayed();
  }

  public boolean isFindButtonDisplayed() {
    return findButton.isDisplayed();
  }

  public boolean isPaginationSectorDisplayed() {
    return paginationSector.isDisplayed();
  }

  public boolean isEnglishTestResultDisplayed() {
    return englishTestResult.isDisplayed();
  }

  private List<String> getUserOpenInformationList() {
    return userOpenInformation.getElementsText();
  }

  public String getUserEmailFromOpenInformation() {
    return getUserOpenInformationList().get(0);
  }

  public String getUserPhoneNumberFromOpenInformation() {
    return getUserOpenInformationList().get(1);
  }

  public ProfileScreen waitConfirmationPopUpForVisibility() {
    confirmInformationPopUp.waitForVisibility();
    return this;
  }

  public boolean isConfirmationPopUpCancelButtonDisplayed() {
    return popupCancelButton.isDisplayed();
  }

  public String getMessageEnglishTestAssignedPopUpText() {
    return popupMessage.getText();
  }

  public ProfileScreen uploadDocumentByFilePath(String filePath) {
    uploadDocumentsInput.sendFilePath(filePath);
    return this;
  }

  public boolean isUploadedDocumentDisplayed(String documentName) {
    return Element.byXpath(UPLOADED_DOCUMENT, documentName).isDisplayed();
  }

  public ProfileScreen clickRemoveResumeCrossButtonByName(String documentName) {
    Element.byXpath(REMOVE_RESUME_BUTTON, documentName).waitForClickable().click();
    return this;
  }

  public boolean isConfirmInformationPopUpDisplayed() {
    return confirmInformationPopUp.isDisplayed();
  }

  public boolean isConfirmInformationPopUpDisplayedNoWait() {
    return confirmInformationPopUp.isDisplayedNoWait();
  }

  public ProfileScreen clickConfirmInformationPopUpOkButton() {
    popupOkButton.scrollAndClick();
    return this;
  }

  public String getPopupHeaderTitle() {
    return popupHeaderTitle.getText();
  }

  public boolean isPopupCrossDisplayed() {
    return popupCross.isDisplayed();
  }

  public String getPopupOkButtonText() {
    return popupOkButton.getText();
  }

  public String getPopupOkButtonAttributeValueText() {
    return popupOkButton.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public String getPopupCancelButtonText() {
    return popupCancelButton.getText();
  }

  public String getPopupMessageText() {
    return popupMessage.getText();
  }

  public ProfileScreen clickPopupCloseButton() {
    popupCross.click();
    return this;
  }

  public ProfileScreen clickPopupOkButton() {
    popupOkButton.click();
    return this;
  }

  public ProfileScreen clickTakeTestButton() {
    takeTestButton.click();
    return this;
  }

  public boolean isTakeTestButtonDisplayed() {
    return takeTestButton.isDisplayed();
  }

  public String getTrainingStatusByTrainingName(String trainingName) {
    return Element.byXpath(TRAINING_STATUS_PATTERN, trainingName).getText();
  }

  public boolean isSocialNetworksIconDisplayed() {
    return socialNetworksIcon.isDisplayed();
  }

  public boolean isEmailIconDisplayed() {
    return emailIcon.isDisplayed();
  }

  public String getTakeTestButtonText() {
    return takeTestButton.getText();
  }

  public boolean isApplicationTabDisplayed() {
    return applicationsTab.isDisplayed();
  }

  public String getProfileCreationDateFieldText() {
    return profileCreationDateFieldText.getText();
  }
}
