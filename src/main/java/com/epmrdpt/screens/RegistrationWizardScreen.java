package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_SOCIAL_NETWORK_LABEL;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistrationWizardScreen extends AbstractScreen {

  public static final String ATTRIBUTE_VALUE = "value";
  private static final String INPUT_PATTERN = "//input[@ng-model='%s']";
  private static final String CITY_OF_RESIDENCE_DDL_VALUE_PATTERN_BY_NAME = "//*[@id='city']//div[normalize-space()='%s']";
  private static final String LABEL_BY_FOR_ATTRIBUTE_PATTERN = "//label[@for='%s']";
  private static final String DDL_BY_ID_PATTERN = "//select[@id='%s']/following-sibling::div[1]";
  private static final String CHECKBOX_PATTERN =
      LABEL_BY_FOR_ATTRIBUTE_PATTERN + "/span";
  private static final String TEXT_FIELD_BY_NAME_PATTERN =
      LABEL_BY_FOR_ATTRIBUTE_PATTERN + "/following::textarea[1]";
  private static final String INPUT_BY_NAME_PATTERN = "//input[@name='%s']";
  private static final String INACTIVE_DDL_BY_ID_PATTERN = "//rd-combo-box[@id='%s']";
  private static final String WORK_DATES_LABEL_PATTERN =
      "(" + LABEL_BY_FOR_ATTRIBUTE_PATTERN + ")[%s]";
  private static final String CALENDAR_BUTTON_PATTERN = "(//button[contains(@class,'button-calendar')])[%s]";
  private static final String ESTABLISHMENT_FOR_ATTRIBUTE = "establishment";
  private static final String FACULTY_FOR_ATTRIBUTE = "faculty";
  private static final String DEPARTMENT_FOR_ATTRIBUTE = "department";
  private static final String EDUCATION_FORM_FOR_ATTRIBUTE = "educationBusy";
  private static final String DEGREE_FOR_ATTRIBUTE = "degree";
  private static final String ADMISSION_YEAR_FOR_ATTRIBUTE = "admissionYear";
  private static final String GRADUATION_YEAR_FOR_ATTRIBUTE = "graduationYear";
  private static final String ASSIGNMENT_FOR_ATTRIBUTE = "assignment";
  private static final String DISTRIBUTION_COMPANY_FOR_ATTRIBUTE = "distributionCompany";
  private static final String IS_DISTRIBUTION_COMPANY_EPAM_FOR_ATTRIBUTE = "isDistributionCompanyEpam";
  private static final String ENGLISH_LEVEL_FOR_ATTRIBUTE = "englishLevel";
  private static final String ADDITIONAL_EDUCATION_INFORMATION_FOR_ATTRIBUTE = "additionalEducationInformation";
  private static final String PROFESSIONAL_ACTIVITIES_FOR_ATTRIBUTE = "professionalActivities";
  private static final String BIRTH_DATE_FOR_ATTRIBUTE = "birthDate";
  private static final String LABEL_BY_TEXT_PATTERN = "//label[contains(text(),'%s')]";
  private static final String SOCIAL_NETWORK_BY_NAME_PATTERN = "//div[contains(@class,'social-network--icon__%s')]";
  private static final String COUNTRY_FOR_ATTRIBUTE = "country";
  private static final String CITY_FOR_ATTRIBUTE = "city";
  private static final String COMMUNICATION_METHOD_FOR_ATTRIBUTE = "communicateMethod";
  private static final String PORTFOLIO_FOR_ATTRIBUTE = "portfolio";
  private static final String COMPANY_NAME_FOR_ATTRIBUTE = "companyName";
  private static final String POSITION_FOR_ATTRIBUTE = "position";
  private static final String START_DATE_FOR_ATTRIBUTE = "workStartDate";
  private static final String TILL_NOW_FOR_ATTRIBUTE = "isTillNow";
  private static final String ADDITIONAL_WORK_INFORMATION_FOR_ATTRIBUTE = "additionalWorkInformation";
  private static final String POPUP_BLOCK_PATTERN = "//div[@class='popup-wrapper']";

  private Element firstNameField = Element.byId("firstName");
  private Element nextTabButton = Element
      .byXpath("//div[@class='step-next button button--green'][@ng-click='navNext()']");
  private Element registerButton = Element.byXpath("//*[contains(@ng-click,'navFinish()')]");
  private Element educationFormField = Element.byId("educationBusy_chosen");
  private Element basicInfoTab = Element.byXpath("//div[@data-tab-id='tab-0']");
  private Element skillsLabel = Element.byXpath(
      "//label[contains(@class,'accordion-item__question')]");
  private Element lastNameField = Element.byId("last-name");
  private Element phoneNumberField = Element.byId("phone");
  private Element countryOfResidenceField = Element.byId("country_chosen");
  private Element cityOfResidenceField = Element.byId(CITY_FOR_ATTRIBUTE);
  private Element birthDateField = Element.byId(BIRTH_DATE_FOR_ATTRIBUTE);
  private Element cityOfStudyField =
      Element.byXpath(
          "//div[1]/div[contains(@class,'chosen-container') and contains(@class,'chosen-container-single')]/a");
  private Element educationalEstablishmentField = Element.byId("establishment_chosen");
  private Element facultyField = Element.byId("faculty_chosen");
  private Element departmentField = Element.byId("department_chosen");
  private Element degreeInformationField = Element.byId("degree_chosen");
  private Element universityCourseField = Element.byId("admissionYear_chosen");
  private Element graduationYearField = Element.byId("graduationYear_chosen");
  private Element assignmentField = Element.byId("assignment_chosen");
  private Element englishLevelField = Element.byId("englishLevel_chosen");
  private Element emailField = Element.byId("email");
  private Element incorrectValueErrorMessage = Element.byXpath("//div[@ng-message='spaceError']");
  private Element firstNameLabel = Element.byCss("*[for*='firstName']");
  private Element lastNameLabel = Element.byCss("*[for*='last-name']");
  private Element emailLabel = Element.byCss("*[for*='email']");
  private Element phoneLabel = Element.byCss("*[for*='phone']");
  private Element documentsLabel = Element.byXpath("//resume//preceding-sibling::label");
  private Element uploadButton = Element.byName("resumeName");
  private Element uploadFilesHint = Element.byCss("*.resume-hint");
  private Element requiredFieldHint = Element.byCss("*.required-info");
  private Element registrationPopUpOkButton = Element.byCss("button.message-btn-ok");
  private Element popUpOkButton = Element.byXpath("//input[@type='button']");
  private Element companyNameField = Element.byXpath(INPUT_PATTERN, "userData.CompanyName");
  private Element positionField = Element.byXpath(INPUT_PATTERN, "userData.Position");
  private Element startDateField = Element.byXpath(INPUT_PATTERN, "userData.StartDate");
  private Element endDateField = Element.byXpath(INPUT_PATTERN, "userData.EndDate");
  private Element cityOfResidenceDDLItem = Element.byXpath("//div[@id='city_chosen']//li");
  private Element choiceCityInput = Element.byXpath("//div[@id='city_chosen']//input");
  private Element choiceCountryInput = Element.byXpath("//div[@id='country_chosen']//input");
  private final Element educationTab = Element.byXpath("//div[@class='steps-nav']/div[2]");
  private final Element cityOfStudyLabel = Element.byXpath(
      "//form[@name='forms.education']/div[1]/label");
  private final Element cityOfStudyDDL = Element.byXpath(
      "//rd-combo-box[@field-name='educationCity']/div");
  private final Element educationalEstablishmentLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, ESTABLISHMENT_FOR_ATTRIBUTE));
  private final Element educationalEstablishmentDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, ESTABLISHMENT_FOR_ATTRIBUTE));
  private final Element facultyLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, FACULTY_FOR_ATTRIBUTE));
  private final Element facultyDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, FACULTY_FOR_ATTRIBUTE));
  private final Element departmentLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, DEPARTMENT_FOR_ATTRIBUTE));
  private final Element departmentDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, DEPARTMENT_FOR_ATTRIBUTE));
  private final Element educationFormLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, EDUCATION_FORM_FOR_ATTRIBUTE));
  private final Element educationFormDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, EDUCATION_FORM_FOR_ATTRIBUTE));
  private final Element degreeInformationLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, DEGREE_FOR_ATTRIBUTE));
  private final Element degreeInformationDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, DEGREE_FOR_ATTRIBUTE));
  private final Element admissionYearLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, ADMISSION_YEAR_FOR_ATTRIBUTE));
  private final Element admissionYearDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, ADMISSION_YEAR_FOR_ATTRIBUTE));
  private final Element graduationYearLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, GRADUATION_YEAR_FOR_ATTRIBUTE));
  private final Element graduationYearDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, GRADUATION_YEAR_FOR_ATTRIBUTE));
  private final Element assignmentLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, ASSIGNMENT_FOR_ATTRIBUTE));
  private final Element assignmentDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, ASSIGNMENT_FOR_ATTRIBUTE));
  private final Element distributionCompanyLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, DISTRIBUTION_COMPANY_FOR_ATTRIBUTE));
  private final Element distributionCompanyInput = Element.byXpath(
      String.format(INPUT_BY_NAME_PATTERN, DISTRIBUTION_COMPANY_FOR_ATTRIBUTE));
  private final Element distributionCompanyEPAMCheckboxLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, IS_DISTRIBUTION_COMPANY_EPAM_FOR_ATTRIBUTE));
  private final Element distributionCompanyEPAMCheckbox = Element.byXpath(
      String.format(CHECKBOX_PATTERN, IS_DISTRIBUTION_COMPANY_EPAM_FOR_ATTRIBUTE));
  private final Element englishLevelLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, ENGLISH_LEVEL_FOR_ATTRIBUTE));
  private final Element englishLevelDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, ENGLISH_LEVEL_FOR_ATTRIBUTE));
  private final Element additionalInformationLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN,
          ADDITIONAL_EDUCATION_INFORMATION_FOR_ATTRIBUTE));
  private final Element additionalInformationTextField = Element.byXpath(
      String.format(TEXT_FIELD_BY_NAME_PATTERN, ADDITIONAL_EDUCATION_INFORMATION_FOR_ATTRIBUTE));
  private final Element professionalActivitiesLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, PROFESSIONAL_ACTIVITIES_FOR_ATTRIBUTE));
  private final Element professionalActivitiesTextField = Element.byXpath(
      String.format(TEXT_FIELD_BY_NAME_PATTERN, PROFESSIONAL_ACTIVITIES_FOR_ATTRIBUTE));
  private final Element birthDateLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, BIRTH_DATE_FOR_ATTRIBUTE));
  private final Element birthDateButton = Element.byXpath(
      String.format(CALENDAR_BUTTON_PATTERN, "1"));
  private final Element socialNetworkLabel = Element.byXpath(
      String.format(LABEL_BY_TEXT_PATTERN,
          getValueOf(REGISTRATION_WIZARD_SCREEN_SOCIAL_NETWORK_LABEL)));
  private final Element socialNetworkDDL = Element.byXpath(
      "//div[@class='social-network--container-icon']");
  private final Element socialNetworkInput = Element.byXpath(
      String.format(INPUT_BY_NAME_PATTERN, "socialNetworkValue"));
  private final Element currentLocationLabel = Element.byXpath(
      "//div[contains(@class,'current-location_title')]");
  private final Element countryLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, COUNTRY_FOR_ATTRIBUTE));
  private final Element countryDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, COUNTRY_FOR_ATTRIBUTE));
  private final Element cityLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, CITY_FOR_ATTRIBUTE));
  private final Element cityDDL = Element.byXpath(
      String.format(INACTIVE_DDL_BY_ID_PATTERN, CITY_FOR_ATTRIBUTE));
  private final Element communicationMethodLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, COMMUNICATION_METHOD_FOR_ATTRIBUTE));
  private final Element communicationMethodDDL = Element.byXpath(
      String.format(DDL_BY_ID_PATTERN, COMMUNICATION_METHOD_FOR_ATTRIBUTE));
  private final Element portfolioLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, PORTFOLIO_FOR_ATTRIBUTE));
  private final Element portfolioInput = Element.byXpath(
      String.format(INPUT_BY_NAME_PATTERN, PORTFOLIO_FOR_ATTRIBUTE));
  private final Element workExperienceTab = Element.byXpath("//div[@class='steps-nav']/div[3]");
  private final Element companyNameLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, COMPANY_NAME_FOR_ATTRIBUTE));
  private final Element positionLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, POSITION_FOR_ATTRIBUTE));
  private final Element startDateLabel = Element.byXpath(
      String.format(WORK_DATES_LABEL_PATTERN, START_DATE_FOR_ATTRIBUTE, "1"));
  private final Element endDateLabel = Element.byXpath(
      String.format(WORK_DATES_LABEL_PATTERN, START_DATE_FOR_ATTRIBUTE, "2"));
  private final Element startDateCalendarButton = Element.byXpath(
      String.format(CALENDAR_BUTTON_PATTERN, "2"));
  private final Element endDateCalendarButton = Element.byXpath(
      String.format(CALENDAR_BUTTON_PATTERN, "3"));
  private final Element tillNowLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, TILL_NOW_FOR_ATTRIBUTE));
  private final Element tillNowCheckbox = Element.byXpath(
      String.format(CHECKBOX_PATTERN, TILL_NOW_FOR_ATTRIBUTE));
  private final Element additionalWorkInformationLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, ADDITIONAL_WORK_INFORMATION_FOR_ATTRIBUTE));
  private final Element additionalWorkInformationTextField = Element.byXpath(
      String.format(TEXT_FIELD_BY_NAME_PATTERN, ADDITIONAL_WORK_INFORMATION_FOR_ATTRIBUTE));
  private final Element skillsLevelDescriptionLabel = Element.byXpath(
      "//div[contains(@class,'skill-level-descr__heading')]");
  private final Element popUpBlock = Element.byXpath(POPUP_BLOCK_PATTERN);
  private final Element popUpBlockText = Element.byXpath(POPUP_BLOCK_PATTERN + "//p");

  @Override
  public boolean isScreenLoaded() {
    return basicInfoTab.isDisplayed(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
  }

  public String getBasicInfoTabText() {
    return basicInfoTab.getText();
  }

  public RegistrationWizardScreen waitBasicInfoTabForVisibility() {
    basicInfoTab.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public RegistrationWizardScreen clickNextTabButton() {
    nextTabButton.mouseOver();
    nextTabButton.clickJs();
    return this;
  }

  public TrainingDescriptionScreen clickRegisterButton() {
    registerButton.mouseOver();
    registerButton.clickJs();
    return new TrainingDescriptionScreen();
  }

  public String getRegistrationButtonText() {
    return registerButton.getText();
  }

  public RegistrationWizardScreen waitIncorrectValueErrorMessageDisappear() {
    incorrectValueErrorMessage.waitForDisappear();
    return this;
  }

  public RegistrationWizardScreen waitEducationFormInputVisibility() {
    educationFormField.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public RegistrationWizardScreen waitSkillsLabelVisibility() {
    skillsLabel.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public String getFirstNameValue() {
    return firstNameField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public int getPhoneNumberValue() {
    return Integer.parseInt(phoneNumberField.getAttributeValue(ATTRIBUTE_VALUE));
  }

  public String getLastNameValue() {
    return lastNameField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getCityOfResidenceFieldText() {
    return cityOfResidenceField.getText();
  }

  public LocalDate getBirthDateValue() {
    return LocalDate.parse(birthDateField.getAttributeValue(ATTRIBUTE_VALUE));
  }

  public String getCityOfStudyFieldText() {
    return cityOfStudyField.getText();
  }

  public String getEducationalEstablishmentFieldText() {
    return educationalEstablishmentField.getText();
  }

  public String getFacultyFieldText() {
    return facultyField.getText();
  }

  public String getDepartmentFieldText() {
    return departmentField.getText();
  }

  public String getEducationFormFieldText() {
    return educationFormField.getText();
  }

  public String getDegreeInformationFieldText() {
    return degreeInformationField.getText();
  }

  public String getUniversityCourseValue() {
    return universityCourseField.getText();
  }

  public String getGraduationYearValue() {
    return graduationYearField.getText();
  }

  public String getAssignmentFieldText() {
    return assignmentField.getText();
  }

  public String getEnglishLevelFieldText() {
    return englishLevelField.getText();
  }

  public String getEmailFieldValue() {
    return emailField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getEmailLabelText() {
    return emailLabel.getText();
  }

  public String getFirstNameLabelText() {
    return firstNameLabel.getText();
  }

  public String getLastNameLabelText() {
    return lastNameLabel.getText();
  }

  public String getPhoneLabelText() {
    return phoneLabel.getText();
  }

  public String getDocumentsLabelText() {
    return documentsLabel.getText();
  }

  public String getUploadButtonText() {
    return uploadButton.getText();
  }

  public String getUploadFilesHintText() {
    return uploadFilesHint.getText();
  }

  public String getRequiredFieldHintText() {
    return requiredFieldHint.getText();
  }

  public String getEducationTabText() {
    return educationTab.getText();
  }

  public String getCityOfStudyLabelText() {
    return cityOfStudyLabel.getText();
  }

  public boolean isCityOfStudyDDLPresent() {
    return cityOfStudyDDL.isPresent();
  }

  public String getEducationalEstablishmentLabelText() {
    return educationalEstablishmentLabel.getText();
  }

  public boolean isEducationalEstablishmentDDLPresent() {
    return educationalEstablishmentDDL.isPresent();
  }

  public String getFacultyLabelText() {
    return facultyLabel.getText();
  }

  public boolean isFacultyDDLPresent() {
    return facultyDDL.isPresent();
  }

  public String getDepartmentLabelText() {
    return departmentLabel.getText();
  }

  public boolean isDepartmentDDLPresent() {
    return departmentDDL.isPresent();
  }

  public String getEducationFormLabelText() {
    return educationFormLabel.getText();
  }

  public boolean isEducationFormDDLPresent() {
    return educationFormDDL.isPresent();
  }

  public String getDegreeInformationLabelText() {
    return degreeInformationLabel.getText();
  }

  public boolean isDegreeInformationDDLPresent() {
    return degreeInformationDDL.isPresent();
  }

  public String getAdmissionYearLabelText() {
    return admissionYearLabel.getText();
  }

  public boolean isAdmissionYearDDLPresent() {
    return admissionYearDDL.isPresent();
  }

  public String getGraduationYearLabelText() {
    return graduationYearLabel.getText();
  }

  public boolean isGraduationYearDDLPresent() {
    return graduationYearDDL.isPresent();
  }

  public String getAssignmentLabelText() {
    return assignmentLabel.getText();
  }

  public boolean isAssignmentDDLPresent() {
    return assignmentDDL.isPresent();
  }

  public String getDistributionCompanyLabelText() {
    return distributionCompanyLabel.getText();
  }

  public boolean isDistributionCompanyInputPresent() {
    return distributionCompanyInput.isPresent();
  }

  public String getDistributionCompanyEPAMCheckboxLabelText() {
    return distributionCompanyEPAMCheckboxLabel.getText();
  }

  public boolean isDistributionCompanyEPAMCheckboxPresent() {
    return distributionCompanyEPAMCheckbox.isPresent();
  }

  public String getEnglishLevelLabelText() {
    return englishLevelLabel.getText();
  }

  public boolean isEnglishLevelDDLPresent() {
    return englishLevelDDL.isPresent();
  }

  public String getAdditionalInformationLabelText() {
    return additionalInformationLabel.getText();
  }

  public boolean isAdditionalInformationTextFieldPresent() {
    return additionalInformationTextField.isPresent();
  }

  public String getProfessionalActivitiesLabelText() {
    return professionalActivitiesLabel.getText();
  }

  public boolean isProfessionalActivitiesTextFieldPresent() {
    return professionalActivitiesTextField.isPresent();
  }

  public String getFirstNameInputPlaceholderText() {
    return firstNameField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getLastNameInputPlaceholderText() {
    return lastNameField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getEmailInputPlaceholderText() {
    return emailField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getContactPhoneInputPlaceholderText() {
    return phoneNumberField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getBirthDateLabelText() {
    return birthDateLabel.getText();
  }

  public boolean isBirthDateCalendarButtonPresent() {
    return birthDateButton.isPresent();
  }

  public String getBirthDateInputPlaceholderText() {
    return birthDateField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isSocialNetworkLabelPresent() {
    return socialNetworkLabel.isPresent();
  }

  public String getSocialNetworkInputPlaceholderText() {
    return socialNetworkInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getCurrentLocationLabelText() {
    return currentLocationLabel.getText();
  }

  public String getCountryLabelText() {
    return countryLabel.getText();
  }

  public boolean isCountryDDLPresent() {
    return countryDDL.isPresent();
  }

  public String getCityLabelText() {
    return cityLabel.getText();
  }

  public boolean isCityDDLPresent() {
    return cityDDL.isPresent();
  }

  public String getCommunicationMethodLabelText() {
    return communicationMethodLabel.getText();
  }

  public boolean isCommunicationMethodDDLPresent() {
    return communicationMethodDDL.isPresent();
  }

  public String getPortfolioLabelText() {
    return portfolioLabel.getText();
  }

  public String getPortfolioInputPlaceholderText() {
    return portfolioInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getWorkExperienceTabText() {
    return workExperienceTab.getText();
  }

  public String getCompanyLabelText() {
    return companyNameLabel.getText();
  }

  public String getCompanyInputPlaceholderText() {
    return companyNameField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getPositionLabelText() {
    return positionLabel.getText();
  }

  public String getPositionInputPlaceholderText() {
    return positionField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getStartDateLabelText() {
    return startDateLabel.getText();
  }

  public String getStartDateInputPlaceholderText() {
    return startDateField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isStartDateCalendarButtonPresent() {
    return startDateCalendarButton.isPresent();
  }

  public String getEndDateLabelText() {
    return endDateLabel.getText();
  }

  public String getEndDateInputPlaceholderText() {
    return endDateField.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isEndDateCalendarButtonPresent() {
    return endDateCalendarButton.isPresent();
  }

  public String getTillNowLabelText() {
    return tillNowLabel.getText();
  }

  public boolean isTillNowCheckboxPresent() {
    return tillNowCheckbox.isPresent();
  }

  public String getAdditionalWorkInformationLabelText() {
    return additionalWorkInformationLabel.getText();
  }

  public boolean isAdditionalWorkInformationTextFieldPresent() {
    return additionalWorkInformationTextField.isPresent();
  }

  public List<String> getSkillsLabelsText() {
    return skillsLabel.getElementsText();
  }

  public String getSkillsLevelDescriptionLabelText() {
    return skillsLevelDescriptionLabel.getText();
  }

  public RegistrationWizardScreen waitFirstNameAttributeValueIsNotEmpty() {
    firstNameField.waitUntilAllElementsLocatedByAreVisible();
    firstNameField.waitAttributeValueIsNotEmpty(ATTRIBUTE_VALUE);
    return this;
  }

  public boolean isRegistrationPopUpOkButtonDisplayed() {
    return registrationPopUpOkButton.isDisplayed();
  }

  public void clickRegistrationPopUpOkButton() {
    registrationPopUpOkButton.click();
  }

  public RegistrationWizardScreen typeCompanyName(String companyName) {
    companyNameField.type(companyName);
    return this;
  }

  public RegistrationWizardScreen typePosition(String Position) {
    positionField.type(Position);
    return this;
  }

  public RegistrationWizardScreen typeWorkExperienceStartDate(LocalDate localDate) {
    startDateField.type(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    return this;
  }

  public RegistrationWizardScreen typeWorkExperienceEndDate(LocalDate localDate) {
    endDateField.type(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    return this;
  }

  public RegistrationWizardScreen chooseCityOfResidence(String cityOfResidence) {
    cityOfResidenceField.click();
    Element.byXpath(CITY_OF_RESIDENCE_DDL_VALUE_PATTERN_BY_NAME, cityOfResidence).click();
    return this;
  }

  public RegistrationWizardScreen chooseCountryOfResidence(String countryOfResidence) {
    countryOfResidenceField.click();
    choiceCountryInput.type(countryOfResidence);
    choiceCountryInput.enter();
    return this;
  }

  public RegistrationWizardScreen chooseSocialNetwork(String socialNetwork) {
    socialNetworkDDL.click();
    Element.byXpath(String.format(SOCIAL_NETWORK_BY_NAME_PATTERN, socialNetwork)).click();
    return this;
  }

  public RegistrationWizardScreen waitEducationTabForVisibility() {
    educationTab.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public RegistrationWizardScreen waitWorkExperienceTabForVisibility() {
    workExperienceTab.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public boolean isPopUpBlockDisplayed() {
    return popUpBlock.isDisplayed();
  }

  public String getTextFromPopUp() {
    return popUpBlockText.getText();
  }

  public RegistrationWizardScreen clickOkButtonInPopUpWindow() {
    popUpOkButton.click();
    return this;
  }

  public boolean isRegistrationButtonPresent() {
    return registerButton.isPresent();
  }
}
