package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_PROFILE_OPEN_INFORMATION_CONTACT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_PROFILE_OPEN_INFORMATION_EMAIL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_PROFILE_OPEN_INFORMATION_SKYPE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_DROPDOWN_VALUE_NOT_EMPTY;
import static java.lang.String.format;
import static javax.swing.text.html.CSS.Attribute.BACKGROUND_IMAGE;
import static javax.swing.text.html.HTML.Attribute.CLASS;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.utils.RandomUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;

public class ProfileEditionScreen extends AbstractScreen {

  private static final String ATTRIBUTE_VALUE = "value";
  private static final String DROP_DOWN_DISABLE_VALUE = "chosen-disabled";
  private static final String TEXT_CONTENT_VALUE = "textContent";
  private static final String DISABLED_ATTRIBUTE = "disabled";
  private static final String DISPLAY_BLOCK_VALUE = "display: block;";

  private static final String REMOVE_RESUME_BUTTON_BY_NAME_LOCATOR = "//div[text()='%s']"
      + "/following-sibling::div[contains(@class,'resume-item__remove')]";
  private static final String OPEN_INFORMATION_SECTION_LOCATOR = "//div[contains(@class,'edit-profile__open-information')]";
  private static final String EMAIL_FIELD_IN_OPEN_INFORMATION_LOCATOR =
      OPEN_INFORMATION_SECTION_LOCATOR
          + "//div/label[contains(text(),'%s')]/following-sibling::input";
  private static final String CONTACT_FIELD_IN_OPEN_INFORMATION_LOCATOR = EMAIL_FIELD_IN_OPEN_INFORMATION_LOCATOR;
  private static final String SKYPE_FIELD_IN_OPEN_INFORMATION_LOCATOR =
      OPEN_INFORMATION_SECTION_LOCATOR
          + "//div/label[contains(text(),'%s')]/following-sibling::div//input";
  private static final String SKYPE_FIELD_ICON_IN_OPEN_INFORMATION_LOCATOR =
      OPEN_INFORMATION_SECTION_LOCATOR
          + "//div/label[contains(text(),'%s')]/following-sibling::div/div/div";
  private static final String DROP_DOWN_FIELD_PATTERN = "//div[contains(@class,'chosen-with-drop')]//li[contains(text(),'%s')]";
  private static final String DROP_DOWN_FIELD_GRADUATION_YEAR_AND_ASSIGNMENT_PATTERN =
      "//div[contains(@class,'chosen-container')]//li[contains(text(),'%s')]";
  private static final String SELECT_PATTERN = "//select[@*='%s']//following-sibling::div[@style]";
  private static final String INPUT_PATTERN = "//input[@ng-model='%s']";
  private static final String SELECT_TEXT_PATTERN = ".//span";
  private static final String DROP_DOWN_ENGLISH_LEVEL_LOCATOR = "//div[contains(@class,'chosen-drop')]//li[text()='%s']";
  private static final String CITY_OF_RESIDENCE_COMBO_BOX_LOCATOR = "//*[@id='city']";
  private static final String CITY_OF_RESIDENCE_LOCATOR =
      CITY_OF_RESIDENCE_COMBO_BOX_LOCATOR + "//div[@data-id='combobox']";
  private static final String CITY_OF_RESIDENCE_INPUT_LOCATOR = CITY_OF_RESIDENCE_LOCATOR + "//input";
  private static final String CITY_OF_STUDY_LOCATOR = "//*[@option-list='education.cities']";
  private static final String CITY_OF_STUDY_INPUT_LOCATOR = CITY_OF_STUDY_LOCATOR + "//input";
  private static final String CITY_RESIDENCE_DROPDOWN_PATTERN =
      CITY_OF_RESIDENCE_COMBO_BOX_LOCATOR
          + "//div[@class='option-list']/div[contains(text(),'%s')]";
  private static final String CITY_OF_STUDY_DROPDOWN_PATTERN =
      "(//*[@ng-model='education.city']//div[@data-id='combobox']//div[contains(text(),'%s')])[%d]";
  private static final String OPTIONS_IN_COMBO_BOX_LOCATOR = "//div[@ng-repeat='option in optionList']";
  private static final String CITY_OF_STUDY_DROPDOWN_VALUES_BY_INDEX_LOCATOR =
      "(//*[@option-list='education.cities'])[%d]//div[@class='option-list__item' and contains(text(), '%s')]";
  private Element spinnerOfLoading = Element.byXpath("//div[contains(@class,'spinner')]");
  private Element firstNameField = Element.byId("firstName");
  private Element lastNameField = Element.byId("last-name");
  private Element phoneNumberField = Element.byId("phone");
  private Element birthDateField = Element.byId("birth-date");
  private Element saveProfileButton = Element.byCss("#saveProfile");
  private Element nativeNameField = Element.byName("nativeName");
  private Element emailField = Element.byXpath(INPUT_PATTERN, "basicInfo.Email");
  private Element cityOfResidence = Element.byXpath(CITY_OF_RESIDENCE_LOCATOR);
  private Element cityOfResidenceDropdown = Element.byXpath(
      CITY_OF_RESIDENCE_LOCATOR + "/div[@data-id='combobox-dropdown']");
  private Element chosenCityOfResidence = Element.byXpath(
      CITY_OF_RESIDENCE_LOCATOR + "//div[@data-id='combobox-placeholder']");
  private Element cityOfResidenceInput = Element.byXpath(CITY_OF_RESIDENCE_INPUT_LOCATOR);
  private Element cityOfResidenceDropdownValues =
      Element.byXpath("//*[@id='city']//div[@class='option-list']/div[@class='option-list__item']");
  private Element removeResumeButton = Element
      .byXpath("//div[contains(@class,'resume-item__remove')]");
  private Element lastRemoveResumeButton = Element
      .byXpath("//div[1]/div[contains(@class,'resume-item__remove')]");
  private Element okButtonInDeleteResumePopup = Element
      .byXpath("//button[contains(@class,'btn-ok')]");
  private Element resumeFileInputField = Element
      .byXpath("//input[@type='file' and @name='resumeName']");
  private Element allowToShowCheckboxInOpenInformation = Element
      .byXpath(OPEN_INFORMATION_SECTION_LOCATOR + "//span[@class='check-mark']");
  private Element allowToShowCheckboxLabelInOpenInformation = Element.byXpath(
      OPEN_INFORMATION_SECTION_LOCATOR + "//span[@class='check-mark']/parent::label");
  private Element profilePhotoInput = Element
      .byXpath("//input[@type='file' and @ng-model='photo']");
  private Element removeProfilePhoto = Element
      .byXpath("//div[contains(@class,'edit-profile__photo-remove')]");
  private Element socialNetwork = Element.byName("socialNetworkValue");
  private Element socialNetworkContainer = Element.byCss(".social-network--container [ng-click]");
  private Element skypeIcon = Element.byXpath(
      "//div[contains(@class,'social-network--tooltip')]/div[contains(@class,'Skype')]");
  private Element preferredMethodOfCommunication = Element
      .byXpath(SELECT_PATTERN, "basicInfo.PreferredMethodOfCommunication");
  private Element additionalInformationEducationSection = Element.byId("add-info-exp");
  private Element professionalActivities = Element.byId("prof-activities");
  private Element cityOfStudy =
      Element.byXpath(CITY_OF_STUDY_LOCATOR +
          "//div[@data-id='combobox']/div[@data-id='combobox-placeholder']");
  private Element cityOfStudyInput = Element.byXpath(CITY_OF_STUDY_INPUT_LOCATOR);
  private Element cityOfStudyDropdownValues =
      Element.byXpath(
          CITY_OF_STUDY_LOCATOR + "//div[@data-id='combobox']//div[@class='option-list__item']");
  private Element cityOfStudyDropdown =
      Element.byXpath(CITY_OF_STUDY_LOCATOR + "//div[@data-id='combobox-dropdown']");
  private Element educationFormBlock = Element.byXpath(
      "//div[contains(@ng-repeat,'education in educations track by')]");
  private Element educationalEstablishment = Element
      .byXpath(SELECT_PATTERN, "education.EducationEstablishmentId");
  private Element faculty = Element.byXpath(SELECT_PATTERN, "education.FacultyId");
  private Element department = Element.byXpath(SELECT_PATTERN, "education.DepartmentId");
  private Element educationForm = Element.byXpath(SELECT_PATTERN, "education.EducationForm");
  private Element degreeInformation = Element.byXpath(SELECT_PATTERN, "education.Degree");
  private Element courseOfStudy = Element.byXpath(SELECT_PATTERN, "education.EducationYear");
  private Element graduationYear = Element.byXpath(SELECT_PATTERN, "education.GraduationYear");
  private Element assignment = Element.byXpath(SELECT_PATTERN, "education.Assignment");
  private Element englishLevel = Element.byXpath(SELECT_PATTERN, "englishInfo.EnglishLevel");
  private Element addEducationButton =
      Element.byXpath("//button[contains(@ng-click,'addNewEducation')]");
  private Element addJobButton = Element.byXpath("//*[contains(@ng-click,'addNewJob')]");
  private Element company = Element.byXpath(INPUT_PATTERN, "job.NameCompany");
  private Element position = Element.byXpath(INPUT_PATTERN, "job.Position");
  private Element jobStartDate = Element.byXpath(INPUT_PATTERN, "job.DateStart");
  private Element jobEndDate = Element.byXpath(INPUT_PATTERN, "job.DateEnd");
  private Element additionalInformationFromWorkExperience = Element
      .byXpath("//textarea[@ng-model='job.AdditionInformation']");
  private Element tillNowCheckBox = Element.byXpath(INPUT_PATTERN, "job.IsTillNow");
  private Element companyOfEducationSectionInputField = Element
      .byXpath("//input[@ng-model='education.DistributionCompany']");
  private Element companyEpamSystems = Element.byXpath("//label[contains(@class,'company-label')]");
  private Element removeEducationTemplateWithCompanyAsEpamSystems = Element
      .byXpath(
          "//label[contains(@class,'company-label')]/ancestor::div[contains(@class,'registration-form-columns--repeatable')]/div[contains(@class,'registration-form__remove-button')]");
  private Element alertContinueButton = Element.byXpath("//button[contains(@ng-click,'ok')]");
  private Element removeEducationTemplate = Element
      .byXpath("//div[contains(@class,'registration-form__remove-button')]");
  private Element editProfilePhotoIcon = Element
      .byXpath("//div[contains(@class, 'edit-profile__photo-upload')]");
  private Element defaultProfilePhoto = Element
      .byXpath("//div[contains(@style,'default-photo')]");
  private Element nonDefaultProfilePhoto =
      Element.byXpath(
          "//div[(@editable-photo='photo') and not(contains(@style,'default-photo.png'))]");
  private Element removeEducationButton = Element.byXpath("//div[@*='removeEducation($index)']");
  private Element removeJobButton = Element.byXpath("//div[@*='removeJob($index)']");

  @Override
  public boolean isScreenLoaded() {
    return firstNameField.isDisplayed();
  }

  public ProfileEditionScreen waitScreenLoading() {
    firstNameField.waitForVisibility();
    return this;
  }

  public ProfileEditionScreen typeFirstName(String firstName) {
    firstNameField.type(firstName);
    return this;
  }

  public String getFirstNameText() {
    return firstNameField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public ProfileEditionScreen typeLastName(String lastName) {
    lastNameField.type(lastName);
    return this;
  }

  public int getPhoneNumberText() {
    return Integer.parseInt(phoneNumberField.getAttributeValue(ATTRIBUTE_VALUE));
  }

  public String getLastNameText() {
    return lastNameField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public ProfileEditionScreen typePhoneNumber(String phoneNumber) {
    phoneNumberField.type("+" + phoneNumber);
    return this;
  }

  public ProfileEditionScreen typeBirthDate(String birthDate) {
    birthDateField.type(birthDate);
    return this;
  }

  public LocalDate getBirthDate() {
    return LocalDate.parse(birthDateField.getAttributeValue(ATTRIBUTE_VALUE));
  }

  public ProfileScreen clickSaveProfileButton() {
    saveProfileButton.clickJs();
    return new ProfileScreen();
  }

  public ProfileEditionScreen waitForSaveProfileButtonVisibility() {
    saveProfileButton.waitForVisibility();
    return this;
  }

  public ProfileEditionScreen typeUserNativeName(String nativeName) {
    nativeNameField.type(nativeName);
    return this;
  }

  private String getText(Element element, int index) {
    return element.getElements().get(index).getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getCompanyNameText() {
    return getText(company, 0);
  }

  public String getPositionNameText() {
    return getText(position, 0);
  }

  public LocalDate getStartDate() {
    return LocalDate.parse(getText(jobStartDate, 0));
  }

  public LocalDate getEndDate() {
    return LocalDate.parse(getText(jobEndDate, 0));
  }

  public String getUserNativeNameText() {
    return nativeNameField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public ProfileEditionScreen clickCityOfResidenceField() {
    cityOfResidence.click();
    return this;
  }

  public ProfileEditionScreen clickInputForCityOfResidence() {
    cityOfResidenceInput.click();
    return this;
  }

  public String getCityOfResidenceValue() {
    return chosenCityOfResidence.getText();
  }

  public ProfileEditionScreen clickCityOfResidenceDropDownByText(String text) {
    cityOfResidenceDropdownValues.waitForVisibility();
    getCityOfResidenceDropdownValueStartingWith(text).clickJs();
    return this;
  }

  private Element getCityOfResidenceDropdownValueStartingWith(String text) {
    return cityOfResidenceDropdownValues.getElements()
        .stream()
        .filter(element->element.getText().trim().startsWith(text))
        .findFirst()
        .get();
  }

  public boolean isCityOfResidenceChosenDisabled() {
    return isChosenDisabled(cityOfResidence);
  }

  public boolean isCityOfResidenceChosenPresent() {
    return isChosenPresent(cityOfResidence);
  }

  public String getCityOfResidenceRandomValue() {
    return getRandomValueInComboBox(cityOfResidence);
  }

  public String getCityOfStudyValue() {
    return cityOfStudy.getText();
  }

  public String getCityOfStudyValueByIndex(int cityOfStudyIndex) {
    return cityOfStudy.getElements().get(cityOfStudyIndex).getText();
  }

  public ProfileEditionScreen clickCityOfStudyDropDownByText(String text) {
    return clickCityOfStudyDropDownByIndexAndText(text, 1);
  }

  public ProfileEditionScreen clickCityOfStudyDropDownByIndexAndText(String text, int index) {
    Element dropDownValue = Element.byXpath(CITY_OF_STUDY_DROPDOWN_PATTERN, text, index);
    dropDownValue.waitForVisibility();
    dropDownValue.mouseOver();
    dropDownValue.click();
    return this;
  }

  public boolean isCityOfStudyChosenDisabled() {
    return isChosenDisabled(cityOfStudy);
  }

  public boolean isCityOfStudyChosenPresent() {
    return isChosenPresent(cityOfStudy);
  }

  public String getCityOfStudyRandomValue() {
    return getRandomValueInComboBox(cityOfStudyDropdown);
  }

  public String getEducationalEstablishmentValue() {
    return getValue(educationalEstablishment);
  }

  public String getEducationEstablishmentValueByIndex(int educationIndex) {
    return getValueByIndex(educationalEstablishment, educationIndex);
  }

  public ProfileEditionScreen clickEducationalEstablishmentDropDownByText(String text) {
    return clickDropDownByText(text);
  }

  public boolean isEducationalEstablishmentChosenDisabled() {
    return isChosenDisabled(educationalEstablishment);
  }

  public boolean isEducationalEstablishmentChosenPresent() {
    return isChosenPresent(educationalEstablishment);
  }

  public String getEducationalEstablishmentRandomValue() {
    return getRandomValue(educationalEstablishment);
  }

  public String getEducationalEstablishmentRandomValueByIndex(int educationIndex) {
    return getRandomValueByIndex(educationalEstablishment, educationIndex);
  }

  public String getFacultyValue() {
    return getValue(faculty);
  }

  public String getFacultyValueByIndex(int facultyIndex) {
    return getValueByIndex(faculty, facultyIndex);
  }

  public ProfileEditionScreen clickFacultyDropDownByText(String text) {
    return clickDropDownByText(text);
  }

  public boolean isFacultyChosenDisabled() {
    return isChosenDisabled(faculty);
  }

  public boolean isFacultyChosenDisabledForIndex(int facultyIndex) {
    return isChosenDisabled(faculty, facultyIndex);
  }

  public boolean isFacultyChosenPresent() {
    return isChosenPresent(faculty);
  }

  public boolean isFacultyChosenPresentForIndex(int facultyIndex) {
    return isChosenPresent(faculty, facultyIndex);
  }

  public String getFacultyRandomValue() {
    return getRandomValue(faculty);
  }

  public String getFacultyRandomValueByIndex(int facultyIndex) {
    return getRandomValueByIndex(faculty, facultyIndex);
  }

  public String getDepartmentValue() {
    return getValue(department);
  }

  public String getDepartmentValueByIndex(int departmentIndex) {
    return getValueByIndex(department, departmentIndex);
  }

  public ProfileEditionScreen clickDepartmentDropDownByText(String text) {
    return clickDropDownByText(text);
  }

  public boolean isDepartmentChosenDisabledForIndex(int departmentIndex) {
    return isChosenDisabled(department, departmentIndex);
  }

  public boolean isDepartmentChosenDisabled() {
    return isChosenDisabled(department);
  }

  public boolean isDepartmentChosenPresent() {
    return isChosenPresent(department);
  }

  public boolean isDepartmentChosenPresentForIndex(int departmentIndex) {
    return isChosenPresent(department, departmentIndex);
  }

  public String getDepartmentRandomValue() {
    return getRandomValue(department);
  }

  public String getDepartmentRandomValueByIndex(int departmentIndex) {
    return getRandomValueByIndex(department, departmentIndex);
  }

  public String getEducationFormValue() {
    return getValue(educationForm);
  }

  public String getEducationFormValueByIndex(int educationFormIndex) {
    return getValueByIndex(educationForm, educationFormIndex);
  }

  public ProfileEditionScreen clickEducationFormDropDownByText(String text) {
    return clickDropDownByText(text);
  }

  public boolean isEducationFormChosenDisabled() {
    return isChosenDisabled(educationForm);
  }

  public boolean isEducationFormChosenPresent() {
    return isChosenPresent(educationForm);
  }

  public String getEducationFormRandomValue() {
    return getRandomValue(educationForm);
  }

  public String getEducationFormRandomValueByIndex(int educationIndex) {
    return getRandomValueByIndex(educationForm, educationIndex);
  }

  public String getDegreeInformationValue() {
    return getValue(degreeInformation);
  }

  public String getDegreeInformationValueByIndex(int degreeInformationIndex) {
    return getValueByIndex(degreeInformation, degreeInformationIndex);
  }

  public ProfileEditionScreen clickDegreeInformationDropDownByText(String text) {
    return clickDropDownByText(text);
  }

  public boolean isDegreeInformationChosenDisabled() {
    return isChosenDisabled(degreeInformation);
  }

  public boolean isDegreeInformationChosenPresent() {
    return isChosenPresent(degreeInformation);
  }

  public String getDegreeInformationRandomValue() {
    return getRandomValue(degreeInformation);
  }

  public String getDegreeInformationRandomValueByIndex(int degreeIndex) {
    return getRandomValueByIndex(degreeInformation, degreeIndex);
  }

  public boolean isUniversityCourseDisplay() {
    return courseOfStudy.isDisplayed();
  }

  public boolean isUniversityCourseDisplayByIndex(int index) {
    return courseOfStudy.getElements().get(index).isDisplayed();
  }

  public ProfileEditionScreen waitCourseOfStudyAppear(int index) {
    courseOfStudy.waitNumberOfElementsToBeMoreThan(index);
    return this;
  }

  public String getUniversityCourseValueByIndex(int universityCourseIndex) {
    return getValueByIndex(courseOfStudy, universityCourseIndex);
  }

  public ProfileEditionScreen clickUniversityCourseDropDownByText(String text) {
    return clickDropDownByText(text);
  }

  public boolean isUniversityCourseChosenDisabledForIndex(int universityCourseIndex) {
    return isChosenDisabled(courseOfStudy, universityCourseIndex);
  }

  public boolean isUniversityCourseChosenDisabled() {
    return isChosenDisabled(courseOfStudy);
  }

  public boolean isUniversityCourseChosenPresentForIndex(int universityCourseIndex) {
    return isChosenPresent(courseOfStudy, universityCourseIndex);
  }

  public boolean isUniversityCourseChosenPresent() {
    return isChosenPresent(courseOfStudy);
  }

  public String getUniversityCourseRandomValue() {
    return getRandomValue(courseOfStudy);
  }

  public String getUniversityCourseRandomValueByIndex(int courseIndex) {
    return getRandomValueByIndex(courseOfStudy, courseIndex);
  }

  public String getGraduationYearValue() {
    return getValue(graduationYear);
  }

  public String getGraduationYearValueByIndex(int graduationIndex) {
    return getValueByIndex(graduationYear, graduationIndex);
  }

  public ProfileEditionScreen clickDropDownByPatternAndText(String pattern, String text) {
    Element dropDownValue = Element.byXpath(pattern, text);
    dropDownValue.mouseOver();
    dropDownValue.click();
    dropDownValue.waitForInvisibility();
    return this;
  }

  public ProfileEditionScreen clickGraduationYearDropDownByText(String text) {
    return clickDropDownByPatternAndText(DROP_DOWN_FIELD_GRADUATION_YEAR_AND_ASSIGNMENT_PATTERN,
        text);
  }

  public boolean isGraduationYearChosenDisabled() {
    return isChosenDisabled(graduationYear);
  }

  public boolean isGraduationYearChosenPresent() {
    return isChosenPresent(graduationYear);
  }

  public boolean isCompanyOfEducationSectionInputFieldsDisplayed() {
    return companyOfEducationSectionInputField.isDisplayed();
  }

  public boolean isCompanyOfEducationSectionInputFieldDisplayedForIndex(int companyIndex) {
    return isDisplayed(companyOfEducationSectionInputField, companyIndex);
  }

  public boolean isCompanyOfEducationSectionInputFieldDisabled(int companyIndex) {
    return companyOfEducationSectionInputField.getElements().get(companyIndex)
        .isAttributePresent(DISABLED_ATTRIBUTE);
  }

  public ProfileEditionScreen typeCompanyNameInEducationSection(String companyName,
      int companyIndex) {
    companyOfEducationSectionInputField.getElements().get(companyIndex).type(companyName);
    return this;
  }

  public String getCompanyNameInEducationSection(int companyIndex) {
    return companyOfEducationSectionInputField.getElements().get(companyIndex)
        .getAttributeValue(ATTRIBUTE_VALUE);
  }

  private boolean isDisplayed(Element element, int index) {
    return element.getElements().get(index).isDisplayed();
  }

  public ProfileEditionScreen clickEnglishLevelField() {
    englishLevel.click();
    return this;
  }

  public String getEnglishLevelValue() {
    return getValue(englishLevel);
  }

  public String getEmailFieldValue() {
    return emailField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public ProfileEditionScreen clickEnglishLevelDropDownByText(String text) {
    Element.byXpath(DROP_DOWN_ENGLISH_LEVEL_LOCATOR, text).click();
    return this;
  }

  public boolean isEnglishLevelChosenDisabled() {
    return isChosenDisabled(englishLevel);
  }

  public boolean isEnglishLevelChosenPresent() {
    return isChosenPresent(englishLevel);
  }

  public String getEnglishLevelRandomValue() {
    return getRandomValue(englishLevel);
  }

  private String getValueByIndex(Element element, int index) {
    return element.getElements().get(index).findChild(Element.byXpath(SELECT_TEXT_PATTERN))
        .getAttribute(TEXT_CONTENT_VALUE);
  }

  private String getValue(Element element) {
    return getValueByIndex(element, 0);
  }

  private ProfileEditionScreen clickDropDownByText(String text) {
    return clickDropDownByPatternAndText(DROP_DOWN_FIELD_PATTERN, text);
  }

  private boolean isChosenDisabled(Element element, int index) {
    return element.getElements().get(index).getAttributeValue(CLASS.toString())
        .contains(DROP_DOWN_DISABLE_VALUE);
  }

  private boolean isChosenDisabled(Element element) {
    return element.getAttributeValue(CLASS.toString())
        .contains(DROP_DOWN_DISABLE_VALUE);
  }

  private boolean isChosenPresent(Element element, int index) {
    if (index >= element.getElements().size()) {
      return true;
    }
    return element.getElements().get(index).isAbsent();
  }

  private boolean isChosenPresent(Element element) {
    return element.isAbsent();
  }

  private String getRandomValue(Element element) {
    Random random = new Random();
    List<WebElement> options = element.findChildren(Element.byXpath(".//li"));
    int index = random.nextInt(options.size());
    if (index == 0 && options.size() > 1) {
      index = 1;
    }
    return options.get(index).getAttribute(TEXT_CONTENT_VALUE);
  }

  private String getRandomValueInComboBox(Element element) {
    List<WebElement> options = element
        .findChildren(Element.byXpath(OPTIONS_IN_COMBO_BOX_LOCATOR))
        .stream()
        .filter(option -> (!option.getText().isEmpty()))
        .collect(Collectors.toList());
    int index = RandomUtils.getRandomNumberInInterval(0, options.size() - 1);
    return options.get(index).getText();
  }

  private String getRandomValueByIndex(Element element, int elementIndex) {
    return getRandomValue(element.getElements().get(elementIndex));
  }

  public String getPreferredOfMethodCommunicationRandomValue() {
    return getRandomValue(preferredMethodOfCommunication);
  }

  public ProfileEditionScreen clickLastRemoveResumeButton() {
    lastRemoveResumeButton.click();
    return this;
  }

  public ProfileEditionScreen uploadResume(String path) {
    resumeFileInputField.sendFilePath(path);
    return this;
  }

  public ProfileEditionScreen clickOkButtonInDeleteResumePopup() {
    okButtonInDeleteResumePopup.clickJs();
    return this;
  }

  public int getExistingResumesCount() {
    return removeResumeButton.isDisplayed() ? removeResumeButton.getElements().size() : 0;
  }

  public boolean isRemoveResumeButtonByNameDisplayed(String fileName) {
    return Element.byXpath(REMOVE_RESUME_BUTTON_BY_NAME_LOCATOR, fileName).isDisplayed();
  }

  public ProfileEditionScreen clickRemoveResumeButtonByName(String fileName) {
    Element.byXpath(REMOVE_RESUME_BUTTON_BY_NAME_LOCATOR, fileName).clickJs();
    return this;
  }

  public boolean isEmailFieldInOpenInformationDisplayed() {
    return Element.byXpath(format(EMAIL_FIELD_IN_OPEN_INFORMATION_LOCATOR,
            getValueOf(EDIT_PROFILE_OPEN_INFORMATION_EMAIL)))
        .isDisplayed();
  }

  public boolean isContactFieldInOpenInformationDisplayed() {
    return Element.byXpath(format(CONTACT_FIELD_IN_OPEN_INFORMATION_LOCATOR,
            getValueOf(EDIT_PROFILE_OPEN_INFORMATION_CONTACT)))
        .isDisplayed();
  }

  public boolean isSkypeFieldInOpenInformationDisplayed() {
    return Element.byXpath(format(SKYPE_FIELD_IN_OPEN_INFORMATION_LOCATOR,
            getValueOf(EDIT_PROFILE_OPEN_INFORMATION_SKYPE)))
        .isDisplayed();
  }

  public String getSkypeBackgroundImageInOpenInformation() {
    return Element.byXpath(format(SKYPE_FIELD_ICON_IN_OPEN_INFORMATION_LOCATOR,
            getValueOf(EDIT_PROFILE_OPEN_INFORMATION_SKYPE)))
        .getCssValue(BACKGROUND_IMAGE.toString());
  }

  public boolean isAllowToShowCheckboxInOpenInformationDisplayed() {
    return allowToShowCheckboxInOpenInformation.isDisplayed();
  }

  public String getAllowToShowCheckboxLabelInOpenInformationText() {
    return allowToShowCheckboxLabelInOpenInformation.getText();
  }

  public ProfileEditionScreen uploadProfilePicture(String filePath) {
    profilePhotoInput.sendFilePath(filePath);
    return this;
  }

  public ProfileEditionScreen clickRemoveProfilePhoto() {
    removeProfilePhoto.click();
    return this;
  }

  public ProfileEditionScreen waitForRemoveProfilePhotoVisibility() {
    removeProfilePhoto.waitForVisibility();
    return this;
  }

  public ProfileEditionScreen typeSocialNetwork(String socialValue) {
    socialNetwork.type(socialValue);
    return this;
  }

  public boolean isPreferredMethodOfCommunicationPresent() {
    return isChosenPresent(preferredMethodOfCommunication);
  }

  public boolean isPreferredMethodOfCommunicationDisabled() {
    return isChosenDisabled(preferredMethodOfCommunication);
  }

  public ProfileEditionScreen clickPreferredMethodOfCommunication() {
    preferredMethodOfCommunication.click();
    return this;
  }

  public String getPreferredMethodOfCommunication() {
    return getValue(preferredMethodOfCommunication);
  }

  public ProfileEditionScreen clickPreferredMethodOfCommunicationDropDownByText(String value) {
    return clickDropDownByText(value);
  }

  public ProfileEditionScreen typeAdditionalInformationEducationSection(String information) {
    additionalInformationEducationSection.type(information);
    return this;
  }

  public ProfileEditionScreen typeProfessionalActivities(String activities) {
    professionalActivities.type(activities);
    return this;
  }

  private ProfileEditionScreen clickByFieldAndByIndex(Element element, int index) {
    element.getElements().get(index).click();
    return this;
  }

  public ProfileEditionScreen waitNewEducationSectionBeDisplayed(int sectionsIndex) {
    educationFormBlock.waitNumberOfElementsToBeMoreThan(sectionsIndex);
    return this;
  }

  public ProfileEditionScreen waitCityOfStudyBeClickableByIndex(int sectionIndex) {
    cityOfStudy.getElements().get(sectionIndex).waitForClickable();
    return this;
  }

  public ProfileEditionScreen scrollToAddEducationButton() {
    addEducationButton.mouseOver();
    return this;
  }

  public ProfileEditionScreen clickCityOfStudyFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(cityOfStudy, educationSectionIndex);
  }

  public ProfileEditionScreen clickInputForCityOfStudyFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(cityOfStudyInput, educationSectionIndex);
  }

  public ProfileEditionScreen typeTextToInputForCityOfStudyFieldByIndex(String city,
      int educationSectionIndex) {
    cityOfStudyInput.getElements().get(educationSectionIndex).type(city);
    return this;
  }

  public ProfileEditionScreen clickCityOfStudyInDropdownFieldByIndex(String city,
      int educationSectionIndex) {
    Element.byXpath(
        String.format(
            CITY_OF_STUDY_DROPDOWN_VALUES_BY_INDEX_LOCATOR,
            educationSectionIndex + 1,
            city))
        .click();
    return this;
  }

  public ProfileEditionScreen clickCityOfStudyField() {
    cityOfStudy.click();
    return this;
  }

  public ProfileEditionScreen clickEducationalEstablishmentFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(educationalEstablishment, educationSectionIndex);
  }

  public ProfileEditionScreen clickEducationalEstablishmentField() {
    return clickEducationalEstablishmentFieldByIndex(0);
  }

  public ProfileEditionScreen clickFacultyFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(faculty, educationSectionIndex);
  }

  public ProfileEditionScreen clickFacultyField() {
    return clickFacultyFieldByIndex(0);
  }

  public ProfileEditionScreen clickDepartmentFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(department, educationSectionIndex);
  }

  public ProfileEditionScreen clickDepartmentField() {
    return clickDepartmentFieldByIndex(0);
  }

  public ProfileEditionScreen clickEducationFormFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(educationForm, educationSectionIndex);
  }

  public ProfileEditionScreen clickEducationFormField() {
    return clickEducationFormFieldByIndex(0);
  }

  public ProfileEditionScreen clickDegreeInformationFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(degreeInformation, educationSectionIndex);
  }

  public ProfileEditionScreen clickDegreeInformationField() {
    return clickDegreeInformationFieldByIndex(0);
  }

  public ProfileEditionScreen clickYearFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(courseOfStudy, educationSectionIndex);
  }

  public ProfileEditionScreen clickYearField() {
    return clickYearFieldByIndex(0);
  }

  public ProfileEditionScreen clickGraduationYearFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(graduationYear, educationSectionIndex);
  }

  public ProfileEditionScreen clickGraduationYearField() {
    return clickGraduationYearFieldByIndex(0);
  }

  public ProfileEditionScreen clickAssignmentFieldByIndex(int educationSectionIndex) {
    return clickByFieldAndByIndex(assignment, educationSectionIndex);
  }

  public ProfileEditionScreen clickAssignmentDropDownByText(String text) {
    return clickDropDownByPatternAndText(DROP_DOWN_FIELD_GRADUATION_YEAR_AND_ASSIGNMENT_PATTERN,
        text);
  }

  public boolean isAssignmentFieldDisplayedByIndex(int assignmentIndex) {
    return assignment.getElements().get(assignmentIndex).isDisplayed();
  }

  public String getAssignmentValueByIndex(int assignmentIndex) {
    return getValue(assignment.getElements().get(assignmentIndex));
  }

  public String getAssignmentText() {
    return getValue(assignment);
  }

  public boolean isAssignmentFieldPresent() {
    return isChosenPresent(assignment);
  }

  public boolean isAssignmentFieldDisabled() {
    return isChosenDisabled(assignment);
  }

  public ProfileEditionScreen clickAssignmentField() {
    return clickAssignmentFieldByIndex(0);
  }

  public ProfileEditionScreen clickAddEducationButton() {
    addEducationButton.waitForClickableAndClick();
    return this;
  }

  public ProfileEditionScreen clickAddJobButton() {
    addJobButton.click();
    return this;
  }

  private ProfileEditionScreen typeTextByElementAndIndex(Element element, int index, String text) {
    element.getElements().get(index).type(text);
    return this;
  }

  public ProfileEditionScreen typeCompanyNameByIndex(int jobSectionIndex, String companyName) {
    return typeTextByElementAndIndex(company, jobSectionIndex, companyName);
  }

  public ProfileEditionScreen typeCompanyName(String companyName) {
    return typeCompanyNameByIndex(0, companyName);
  }

  public ProfileEditionScreen typePositionNameByIndex(int jobSectionIndex, String positionValue) {
    return typeTextByElementAndIndex(position, jobSectionIndex, positionValue);
  }

  public ProfileEditionScreen typePositionName(String positionValue) {
    return typePositionNameByIndex(0, positionValue);
  }

  public ProfileEditionScreen typeJobStartDateByIndex(int jobSectionIndex,
      LocalDate localDate) {
    return typeTextByElementAndIndex(jobStartDate, jobSectionIndex,
        localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
    );
  }

  public ProfileEditionScreen typeJobStartDate(LocalDate localDate) {
    return typeJobStartDateByIndex(0, localDate);
  }

  public ProfileEditionScreen typeJobEndDateByIndex(int jobSectionIndex,
      LocalDate localDate) {
    return typeTextByElementAndIndex(jobEndDate, jobSectionIndex,
        localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
  }

  public boolean isJobEndDateByIndex(int workSectionIndex) {
    return isChosenDisabled(jobEndDate.getElements().get(workSectionIndex));
  }

  public ProfileEditionScreen typeJobEndDate(LocalDate localDate) {
    return typeJobEndDateByIndex(0, localDate);
  }

  public ProfileEditionScreen typeAdditionalInformationFromWorkExperienceByIndex(
      int jobSectionIndex, String additionalInformation) {
    return typeTextByElementAndIndex(additionalInformationFromWorkExperience, jobSectionIndex,
        additionalInformation);
  }

  public ProfileEditionScreen clickTillNowCheckBoxByIndex(int workSectionIndex) {
    tillNowCheckBox.getElements().get(workSectionIndex).click();
    return this;
  }

  public int getEducationSectionsQuantity() {
    return educationFormBlock.getElements().size();
  }

  public int getWorkSectionsQuantity() {
    return company.getElements().size();
  }

  public int getYearFieldCount() {
    if (!isUniversityCourseDisplay()) {
      return 0;
    }
    return courseOfStudy.getElements().size();
  }

  public int getCompanyOfEducationSectionFieldCount() {
    if (!isCompanyOfEducationSectionInputFieldsDisplayed()) {
      return 0;
    }
    return companyOfEducationSectionInputField.getElements().size();
  }

  public int getAssignmentFieldCount() {
    if (!assignment.isDisplayed()) {
      return 0;
    }
    return assignment.getElements().size();
  }

  public ProfileEditionScreen clickCompanyAsEpamSystemsByIndex(int companyIndex) {
    companyEpamSystems.getElements()
        .get(companyIndex)
        .click();
    return this;
  }

  public ProfileEditionScreen clickRemoveEducationTemplateWithCompanyAsEpamSystems() {
    removeEducationTemplateWithCompanyAsEpamSystems.click();
    return this;
  }

  public ProfileEditionScreen clickContinueOnDeleteAlert() {
    alertContinueButton.clickJs();
    return this;
  }

  public ProfileEditionScreen waitForContinueOnDeleteAlertDisappear() {
    alertContinueButton.waitForDisappear();
    return this;
  }

  public int getIndexOfCompanyWithCompanyName(String companyName) {
    List<Element> companyInputFields = companyOfEducationSectionInputField.getElements();
    for (int index = 0; index < companyInputFields.size(); index++) {
      if (companyInputFields.get(index).getAttributeValue(ATTRIBUTE_VALUE).equals(companyName)) {
        return index;
      }
    }
    return -1;
  }

  public ProfileEditionScreen removeEducationTemplateWithCompanyName(String companyName) {
    int indexOfCompanyInputField = getIndexOfCompanyWithCompanyName(companyName);
    if (indexOfCompanyInputField != -1) {
      removeEducationTemplate.getElements().get(indexOfCompanyInputField).click();
    }
    return this;
  }

  public ProfileEditionScreen waitForDefaultProfilePhotoVisibility() {
    defaultProfilePhoto.waitForVisibility();
    return this;
  }

  public boolean isDefaultProfilePhotoDisplayed() {
    return defaultProfilePhoto.isDisplayed();
  }

  public ProfileEditionScreen waitForNonDefaultProfilePhotoVisibility() {
    nonDefaultProfilePhoto.waitForVisibility();
    return this;
  }

  public ProfileEditionScreen waitForEditProfilePhotoIconVisibility() {
    editProfilePhotoIcon.waitForVisibility();
    return this;
  }

  public ProfileEditionScreen clickDeleteEducationSectionByIndex(int educationSectionIndex) {
    removeEducationButton.getElements().get(educationSectionIndex).click();
    return this;
  }

  public ProfileEditionScreen clickDeleteJobSectionByIndex(int jobSectionIndex) {
    removeJobButton.getElements().get(jobSectionIndex).click();
    return this;
  }

  public boolean isDeleteEducationButtonDisplayedByIndex(int educationSectionIndex) {
    return removeEducationButton.getElements().get(educationSectionIndex).isDisplayed();
  }

  public boolean isDeleteWorkExperienceButtonDisplayedByIndex(int jobSectionIndex) {
    return removeJobButton.getElements().get(jobSectionIndex).isDisplayed();
  }

  public ProfileEditionScreen clickSocialNetworkContainer() {
    socialNetworkContainer.click();
    return this;
  }

  public ProfileEditionScreen clickSkypeIconInSocialNetworkContainer() {
    skypeIcon.click();
    return this;
  }

  public ProfileEditionScreen selectSkypeAsSocialNetwork() {
    clickSocialNetworkContainer();
    clickSkypeIconInSocialNetworkContainer();
    return this;
  }

  public ProfileEditionScreen waitCityOfResidenceNotEmpty() {
    chosenCityOfResidence.waitTextNotToBePresentInElement(
        getValueOf(PROFILE_DROPDOWN_VALUE_NOT_EMPTY));
    return this;
  }

  public ProfileEditionScreen waitCityOfStudyNotEmpty() {
    cityOfStudy.waitTextNotToBePresentInElement(getValueOf(PROFILE_DROPDOWN_VALUE_NOT_EMPTY));
    return this;
  }

  public ProfileEditionScreen waitCityOfResidenceDropdownGetsPropertyDisplayBlock() {
    cityOfResidenceDropdown
        .waitUntilAttributeGetsValue(STYLE_PROPERTY, DISPLAY_BLOCK_VALUE);
    return this;
  }

  public ProfileEditionScreen waitCityOfStudyInputDisplayed() {
    cityOfStudyInput.waitForVisibility();
    return this;
  }

  public ProfileEditionScreen typeTextToCityOfStudyInput(String text) {
    return typeTextToCityOfStudyInputByIndex(text, 1);
  }

  public ProfileEditionScreen typeTextToCityOfStudyInputByIndex(String text, int index) {
    Element.byXpath(String.format("(%s)[%d]", CITY_OF_STUDY_INPUT_LOCATOR, index)).type(text);
    return this;
  }

  public ProfileEditionScreen waitSpinnerOfLoadingInvisibility() {
    spinnerOfLoading.waitUntilRequiredElementsAreInvisible(
        spinnerOfLoading.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public ProfileEditionScreen waitCityOfStudyDropdownValuesDisplayed() {
    cityOfStudyDropdownValues.waitForVisibility();
    return this;
  }
}
