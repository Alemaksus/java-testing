package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class AffiliateRegistrationFormScreen extends AbstractScreen {

  private static final String LABEL_BY_FOR_ATTRIBUTE_PATTERN = "//label[@for='%s']";
  private static final String FIRST_NAME_ID = "native-first-name";
  private static final String LAST_NAME_ID = "native-last-name";
  private static final String MIDDLE_NAME_ID = "native-middle-name";
  private static final String FIRST_NAME_AS_IN_PASSPORT_ID = "first-name";
  private static final String LAST_NAME_AS_IN_PASSPORT_ID = "last-name";
  private static final String EMAIL_ID = "email";
  private static final String PHONE_ID = "phone";
  private static final String UNIVERSITY_ID = "university";
  private static final String FACULTY_ID = "faculty";
  private static final String DEPARTMENT_ID = "department";
  private static final String STUDENT_GROUP_ID = "studentGroup";
  private final Element registrationForm = Element.byName("affiliateRegForm");
  private final Element lastNameInput = Element.byId(LAST_NAME_ID);
  private final Element firstNameInput = Element.byId(FIRST_NAME_ID);
  private final Element middleNameInput = Element.byId(MIDDLE_NAME_ID);
  private final Element lastNameAsInPassportInput = Element.byId(LAST_NAME_AS_IN_PASSPORT_ID);
  private final Element firstNameAsInPassportInput = Element.byId(FIRST_NAME_AS_IN_PASSPORT_ID);
  private final Element emailInput = Element.byId(EMAIL_ID);
  private final Element phoneNumberInput = Element.byId(PHONE_ID);
  private final Element universityInput = Element.byId(UNIVERSITY_ID);
  private final Element facultyInput = Element.byId(FACULTY_ID);
  private final Element departmentInput = Element.byId(DEPARTMENT_ID);
  private final Element studentGroupDropDown = Element.byId("studentGroup_chosen");
  private final Element studentGroupArrowButton = Element.byXpath(
      "//div[@id='studentGroup_chosen']");
  private final Element studentGroupField = Element.byXpath("//ul[@class='chosen-results']/li[1]");
  private final Element registerButton = Element.byCss("button.save");
  private final Element confirmationRegistrationPopup = Element.byClassName("message-form");
  private final Element registrationHint = Element.byXpath(
      "//div[contains(@class,'registration-info')]");
  private final Element firstNameLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, FIRST_NAME_ID));
  private final Element lastNameLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, LAST_NAME_ID));
  private final Element middleNameLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, MIDDLE_NAME_ID));
  private final Element firstNameAsInPassportLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, FIRST_NAME_AS_IN_PASSPORT_ID));
  private final Element lastNameAsInPassportLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, LAST_NAME_AS_IN_PASSPORT_ID));
  private final Element emailLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, EMAIL_ID));
  private final Element phoneNumberLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, PHONE_ID));
  private final Element universityLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, UNIVERSITY_ID));
  private final Element facultyLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, FACULTY_ID));
  private final Element departmentLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, DEPARTMENT_ID));
  private final Element studentGroupLabel = Element.byXpath(
      String.format(LABEL_BY_FOR_ATTRIBUTE_PATTERN, STUDENT_GROUP_ID));
  private final Element dataProcessingConsentCheckbox = Element.byXpath(
      "//span[@class='check-mark']");
  private final Element dataProcessingConsentText = Element.byXpath(
      "//div[contains(@class,'data-agreement')]/div");

  @Override
  public boolean isScreenLoaded() {
    return registrationForm.isDisplayed();
  }

  public AffiliateRegistrationFormScreen waitScreenLoading() {
    registrationForm.waitForVisibility();
    return this;
  }

  public AffiliateRegistrationFormScreen waitLastNameValueNotEmpty() {
    lastNameInput.waitAttributeValueIsNotEmpty(VALUE_CSS_PROPERTY);
    return this;
  }

  public String getLastNameValue() {
    return lastNameInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public AffiliateRegistrationFormScreen waitFirstNameValueNotEmpty() {
    firstNameInput.waitAttributeValueIsNotEmpty(VALUE_CSS_PROPERTY);
    return this;
  }

  public String getFirstNameValue() {
    return firstNameInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getMiddleNameValue() {
    return middleNameInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getLastNameAsInPassportValue() {
    return lastNameAsInPassportInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getFirstNameAsInPassportValue() {
    return firstNameAsInPassportInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getEmailValue() {
    return emailInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getPhoneNumberValue() {
    return phoneNumberInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getUniversityValue() {
    return universityInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getFacultyValue() {
    return facultyInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public String getDepartmentValue() {
    return departmentInput.getAttributeValue(VALUE_CSS_PROPERTY);
  }

  public AffiliateRegistrationFormScreen clickStudentGroupArrowButton() {
    studentGroupArrowButton.mouseOver();
    studentGroupArrowButton.waitForClickableAndClick();
    if (!studentGroupField.isDisplayed()) {
      studentGroupArrowButton.click();
    }
    return this;
  }

  public AffiliateRegistrationFormScreen clickStudentGroupField() {
    studentGroupField.waitForPresence();
    studentGroupField.click();
    return this;
  }

  public String getStudentGroupText() {
    return studentGroupDropDown.getText();
  }

  public AffiliateRegistrationFormScreen clickRegisterButton() {
    registerButton.click();
    return this;
  }

  public boolean isConfirmationRegistrationPopupDisplayed() {
    return confirmationRegistrationPopup.isDisplayed();
  }

  public String getRegistrationHintText() {
    return registrationHint.getText();
  }

  public String getFirstNameLabelText() {
    return firstNameLabel.getText();
  }

  public String getLastNameLabelText() {
    return lastNameLabel.getText();
  }

  public String getMiddleNameLabelText() {
    return middleNameLabel.getText();
  }

  public String getFirstNameAsInPassportLabelText() {
    return firstNameAsInPassportLabel.getText();
  }

  public String getLastNameAsInPassportLabelText() {
    return lastNameAsInPassportLabel.getText();
  }

  public String getEmailLabelText() {
    return emailLabel.getText();
  }

  public String getPhoneLabelText() {
    return phoneNumberLabel.getText();
  }

  public String getUniversityLabelText() {
    return universityLabel.getText();
  }

  public String getFacultyLabelText() {
    return facultyLabel.getText();
  }

  public String getDepartmentLabelText() {
    return departmentLabel.getText();
  }

  public String getStudentGroupLabelText() {
    return studentGroupLabel.getText();
  }

  public String getRegisterButtonText() {
    return registerButton.getText();
  }

  public String getFirstNamePlaceholder() {
    return firstNameInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getLastNamePlaceholder() {
    return lastNameInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getMiddleNamePlaceholder() {
    return middleNameInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getFirstNameAsInPassportPlaceholder() {
    return firstNameAsInPassportInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getLastNameAsInPassportPlaceholder() {
    return lastNameAsInPassportInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getPhonePlaceholder() {
    return phoneNumberInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getDataProcessingConsentText() {
    return dataProcessingConsentText.getText();
  }

  public boolean isEmailInputPresent() {
    return emailInput.isPresent();
  }

  public AffiliateRegistrationFormScreen clickDataProcessingCheckbox() {
    dataProcessingConsentCheckbox.click();
    return this;
  }

  public boolean isUniversityInputPresent() {
    return universityInput.isPresent();
  }

  public boolean isFacultyInputPresent() {
    return facultyInput.isPresent();
  }

  public boolean isDepartmentInputPresent() {
    return departmentInput.isPresent();
  }

  public boolean isStudentGroupDDLPresent() {
    return studentGroupDropDown.isPresent();
  }

  public boolean isDataProcessingConsentCheckboxPresent() {
    return dataProcessingConsentCheckbox.isPresent();
  }
}
