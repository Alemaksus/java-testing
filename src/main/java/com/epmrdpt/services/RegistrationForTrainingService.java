package com.epmrdpt.services;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;

public class RegistrationForTrainingService {

  private RegistrationWizardScreen registrationWizardScreen = new RegistrationWizardScreen();
  private TrainingDescriptionScreen trainingDescriptionScreen = new TrainingDescriptionScreen();

  public User getUserRequiredDataFromRegistrationWizard() {
    User user = new User();
    registrationWizardScreen
        .waitIncorrectValueErrorMessageDisappear()
        .waitFirstNameAttributeValueIsNotEmpty();
    user.setFirstName(registrationWizardScreen.getFirstNameValue());
    user.setLastName(registrationWizardScreen.getLastNameValue());
    user.setBirthDate(registrationWizardScreen.getBirthDateValue());
    user.setPhoneNumber(registrationWizardScreen.getPhoneNumberValue());
    user.setEmail(registrationWizardScreen.getEmailFieldValue());
    registrationWizardScreen.clickNextTabButton().waitEducationFormInputVisibility();
    user.setEducationalEstablishment(
        registrationWizardScreen.getEducationalEstablishmentFieldText());
    user.setFaculty(registrationWizardScreen.getFacultyFieldText());
    user.setDepartment(registrationWizardScreen.getDepartmentFieldText());
    user.setEducationForm(registrationWizardScreen.getEducationFormFieldText());
    user.setCityOfStudy(registrationWizardScreen.getCityOfStudyFieldText());
    user.setDegreeInformation(registrationWizardScreen.getDegreeInformationFieldText());
    user.setUniversityCourseYear(registrationWizardScreen.getUniversityCourseValue());
    user.setGraduationYear(registrationWizardScreen.getGraduationYearValue());
    user.setAssignment(registrationWizardScreen.getAssignmentFieldText());
    user.setEnglishLevel(registrationWizardScreen.getEnglishLevelFieldText());
    return user;
  }

  public void fillUserWorkExperienceForm(User user) {
    registrationWizardScreen
        .typeCompanyName(user.getCompanyName())
        .typePosition(user.getPosition())
        .typeWorkExperienceStartDate(user.getStartDate())
        .typeWorkExperienceEndDate(user.getEndDate());
  }

  public User getUserRequiredDataFromSimplifiedRegistrationWizard() {
    User user = new User();
    user.setFirstName(registrationWizardScreen.getFirstNameValue());
    user.setLastName(registrationWizardScreen.getLastNameValue());
    user.setPhoneNumber(registrationWizardScreen.getPhoneNumberValue());
    user.setEmail(registrationWizardScreen.getEmailFieldValue());
    return user;
  }

  public boolean isRegistrationOnTrainingCancel() {
    return cancelRegistrationForTraining().isRegisterButtonDisplayed();
  }

  public TrainingDescriptionScreen cancelRegistrationForTraining() {
    return trainingDescriptionScreen
        .waitForClickableAndClickCancelButton()
        .waitRegistrationCancellationPopUpVisibility()
        .clickSubmitButtonInCancelRegistrationPopUp()
        .waitRegisterButtonVisibility();
  }

  public TrainingDescriptionScreen registerForTraining(String cityOfResidence, String countryOfResidence) {
    if (trainingDescriptionScreen.isCancelButtonDisplayed()) {
      cancelRegistrationForTraining();
    }
    trainingDescriptionScreen.clickRefreshButton();
    trainingDescriptionScreen
        .waitTrainingStatusTextPresent()
        .clickRegisterButton();
    return submitDetailsInRegistrationWizard(cityOfResidence, countryOfResidence);
  }

  public TrainingDescriptionScreen generateNotificationsMessage(String cityOfResidence, String countryOfResidence) {
    if (trainingDescriptionScreen.isCancelButtonDisplayed()) {
      cancelRegistrationForTraining();
      registerForTraining(cityOfResidence, countryOfResidence);
    } else {
      registerForTraining(cityOfResidence, countryOfResidence);
      cancelRegistrationForTraining();
    }
    return new TrainingDescriptionScreen();
  }

  public TrainingDescriptionScreen submitDetailsInRegistrationWizard(String cityOfResidence, String countryOfResidence) {
    return fillInFieldsInRegistrationWizard(cityOfResidence, countryOfResidence)
        .clickPopUpOkButton()
        .waitCancelButtonVisibility();
  }

  public void clickTrainingRegistrationPopUpOkButton() {
    if (registrationWizardScreen.isRegistrationPopUpOkButtonDisplayed()) {
      registrationWizardScreen.clickRegistrationPopUpOkButton();
    }
  }

  public TrainingDescriptionScreen generateRegistrationSuccessPopUp(String cityOfResidence, String countryOfResidence) {
    if (trainingDescriptionScreen.isCancelButtonDisplayed()) {
      cancelRegistrationForTraining();
    }
    trainingDescriptionScreen
        .waitTrainingStatusTextPresent()
        .clickRegisterButton();
    return fillInFieldsInRegistrationWizard(cityOfResidence, countryOfResidence);
  }

  public TrainingDescriptionScreen fillInFieldsInRegistrationWizard(String cityOfResidence, String countryOfResidence) {
    return registrationWizardScreen
        .waitFirstNameAttributeValueIsNotEmpty()
        .chooseCountryOfResidence(countryOfResidence)
        .chooseCityOfResidence(cityOfResidence)
        .clickNextTabButton()
        .waitEducationFormInputVisibility()
        .clickNextTabButton()
        .waitSkillsLabelVisibility()
        .clickRegisterButton()
        .waitForPopUpOkButtonVisibility();
  }
}
