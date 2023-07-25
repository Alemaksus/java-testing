package com.epmrdpt.services;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.screens.ExaminatorScreen;
import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.screens.ProfileScreen;

public class ProfileService {

  private static final int differenceBetweenBirthDateAndStartDate = 7;
  private static final int differenceBetweenBirthDateAndEndDate = 8;
  private static final int numberOfCharacters = 1;

  private ProfileEditionScreen profileEditionScreen = new ProfileEditionScreen();
  private ProfileScreen profileScreen = new ProfileScreen();

  private ProfileEditionScreen fillUserFields(User userWithPredefinedData) {
    profileEditionScreen.typeFirstName(userWithPredefinedData.getFirstName())
        .typeLastName(userWithPredefinedData.getLastName())
        .typeBirthDate(userWithPredefinedData.getBirthDateAsString());
    if (!profileEditionScreen.isCityOfResidenceChosenPresent() && !profileEditionScreen
        .isCityOfResidenceChosenDisabled()) {
      profileEditionScreen.clickCityOfResidenceField()
          .waitCityOfResidenceDropdownGetsPropertyDisplayBlock()
          .clickCityOfResidenceDropDownByText(profileEditionScreen.getCityOfResidenceRandomValue());
    }
    profileEditionScreen.typePhoneNumber(userWithPredefinedData.getPhoneNumberAsString());
    if (!profileEditionScreen.isPreferredMethodOfCommunicationPresent() &&
        !profileEditionScreen.isPreferredMethodOfCommunicationDisabled()) {
      profileEditionScreen.clickPreferredMethodOfCommunication()
          .clickPreferredMethodOfCommunicationDropDownByText(
              profileEditionScreen.getPreferredOfMethodCommunicationRandomValue());
    }
    if (!profileEditionScreen.isCityOfStudyChosenPresent() && !profileEditionScreen
        .isCityOfStudyChosenDisabled()) {
      profileEditionScreen
          .clickCityOfStudyField()
          .waitCityOfStudyInputDisplayed()
          .typeTextToCityOfStudyInput(randomAlphabetic(numberOfCharacters))
          .waitSpinnerOfLoadingInvisibility()
          .waitCityOfStudyDropdownValuesDisplayed()
          .clickCityOfStudyDropDownByText(profileEditionScreen.getCityOfStudyRandomValue());
    }
    if (!profileEditionScreen.isEducationalEstablishmentChosenPresent() && !profileEditionScreen
        .isEducationalEstablishmentChosenDisabled()) {
      profileEditionScreen.clickEducationalEstablishmentField()
          .clickEducationalEstablishmentDropDownByText(
              profileEditionScreen.getEducationalEstablishmentRandomValue());
    }
    if (!profileEditionScreen.isFacultyChosenPresent() && !profileEditionScreen
        .isFacultyChosenDisabled()) {
      profileEditionScreen.clickFacultyField()
          .clickFacultyDropDownByText(profileEditionScreen.getFacultyRandomValue());
    }
    if (!profileEditionScreen.isDepartmentChosenPresent() && !profileEditionScreen
        .isDepartmentChosenDisabled()) {
      profileEditionScreen.clickDepartmentField()
          .clickDepartmentDropDownByText(profileEditionScreen.getDepartmentRandomValue());
    }
    if (!profileEditionScreen.isEducationFormChosenPresent() && !profileEditionScreen
        .isEducationFormChosenDisabled()) {
      profileEditionScreen.clickEducationFormField()
          .clickEducationFormDropDownByText(profileEditionScreen.getEducationFormRandomValue());
    }
    if (!profileEditionScreen.isDegreeInformationChosenPresent() && !profileEditionScreen
        .isDegreeInformationChosenDisabled()) {
      profileEditionScreen.clickDegreeInformationField().clickDegreeInformationDropDownByText(
          profileEditionScreen.getDegreeInformationRandomValue());
    }
    if (!profileEditionScreen.isUniversityCourseChosenPresent() && !profileEditionScreen
        .isUniversityCourseChosenDisabled()) {
      profileEditionScreen.clickYearField().clickUniversityCourseDropDownByText(
          profileEditionScreen.getUniversityCourseRandomValue());
    }
    if (!profileEditionScreen.isGraduationYearChosenPresent() && !profileEditionScreen
        .isGraduationYearChosenDisabled()) {
      profileEditionScreen.clickGraduationYearField().clickGraduationYearDropDownByText(
          String.valueOf(userWithPredefinedData.getBirthDate().getYear() + 1));
    }
    if (!profileEditionScreen.isAssignmentFieldPresent() && !profileEditionScreen
        .isAssignmentFieldDisabled()) {
      profileEditionScreen.clickAssignmentField();
    }
    if (!profileEditionScreen.isEnglishLevelChosenPresent() && !profileEditionScreen
        .isEnglishLevelChosenDisabled()) {
      profileEditionScreen.clickEnglishLevelField()
          .clickEnglishLevelDropDownByText(profileEditionScreen.getEnglishLevelRandomValue());
    }
    profileEditionScreen.typeUserNativeName(userWithPredefinedData.getNativeName())
        .typeCompanyName(userWithPredefinedData.getCompanyName())
        .typePositionName(userWithPredefinedData.getPosition())
        .typeJobStartDate(
            userWithPredefinedData.getBirthDate().plusYears(differenceBetweenBirthDateAndStartDate))
        .typeJobEndDate(
            userWithPredefinedData.getBirthDate().plusYears(differenceBetweenBirthDateAndEndDate));
    return new ProfileEditionScreen();
  }

  private void fillBaseSection(User user) {
    profileEditionScreen
        .clickCityOfResidenceField()
        .clickInputForCityOfResidence();
    profileEditionScreen
        .waitCityOfResidenceDropdownGetsPropertyDisplayBlock()
        .waitSpinnerOfLoadingInvisibility();
    profileEditionScreen
        .clickCityOfResidenceDropDownByText(user.getCityOfResidence());
    profileEditionScreen
        .clickPreferredMethodOfCommunication()
        .clickPreferredMethodOfCommunicationDropDownByText(
            user.getPreferredMethodOfCommunication());
    profileEditionScreen
        .typeFirstName(user.getFirstName())
        .typeLastName(user.getLastName())
        .typeUserNativeName(user.getNativeName())
        .typeBirthDate(user.getBirthDateAsString())
        .typePhoneNumber(user.getPhoneNumberAsString())
        .selectSkypeAsSocialNetwork()
        .typeSocialNetwork(user.getSocialNetwork());
  }

  private void fillEducationSection(User user) {
    int counter = 0;
    while (user.getCitiesOfStudyQuantity() > counter) {
      if (counter > 0) {
        profileEditionScreen.clickAddEducationButton();
        profileEditionScreen.waitNewEducationSectionBeDisplayed(counter);
        profileEditionScreen.waitCityOfStudyBeClickableByIndex(counter);
        profileEditionScreen.scrollToAddEducationButton();
      }
      profileEditionScreen
          .clickCityOfStudyFieldByIndex(counter)
          .clickInputForCityOfStudyFieldByIndex(counter)
          .typeTextToInputForCityOfStudyFieldByIndex(user.getCityOfStudy(counter), counter)
          .waitSpinnerOfLoadingInvisibility();
      profileEditionScreen
          .clickCityOfStudyInDropdownFieldByIndex(user.getCityOfStudy(counter), counter);
      profileEditionScreen.clickEducationFormFieldByIndex(counter)
          .clickEducationFormDropDownByText(user.getEducationForm(counter));
      if (!profileEditionScreen.isEducationalEstablishmentChosenDisabled()) {
        profileEditionScreen.clickEducationalEstablishmentFieldByIndex(counter)
            .clickEducationalEstablishmentDropDownByText(user.getEducationalEstablishment(counter));
      }
      profileEditionScreen.clickDegreeInformationFieldByIndex(counter)
          .clickDegreeInformationDropDownByText(user.getDegreeInformation(counter));
      profileEditionScreen.scrollToAddEducationButton();
      if (!profileEditionScreen.isFacultyChosenDisabled()) {
        profileEditionScreen.clickFacultyFieldByIndex(counter)
            .clickFacultyDropDownByText(user.getFaculty(counter));
      }
      if (!profileEditionScreen.isDepartmentChosenDisabled()) {
        profileEditionScreen.clickDepartmentFieldByIndex(counter)
            .clickDepartmentDropDownByText(user.getDepartment(counter));
      }
      profileEditionScreen.waitCourseOfStudyAppear(counter);
      if (profileEditionScreen.isUniversityCourseDisplayByIndex(counter)) {
        profileEditionScreen.clickYearFieldByIndex(counter)
            .clickUniversityCourseDropDownByText(user.getUniversityCourseYear(counter));
      }
      profileEditionScreen.clickGraduationYearFieldByIndex(counter)
          .clickGraduationYearDropDownByText(user.getGraduationYear(counter));
      if (profileEditionScreen.isAssignmentFieldDisplayedByIndex(counter)) {
        profileEditionScreen.clickAssignmentFieldByIndex(counter)
            .clickAssignmentDropDownByText(user.getAssignmentByIndex(counter));
      }
      ++counter;
    }
    profileEditionScreen
        .typeAdditionalInformationEducationSection(
            user.getAdditionalInformationFromEducationSectionForm())
        .typeProfessionalActivities(user.getProfessionalActivitiesFromEducationSectionForm())
        .clickEnglishLevelField()
        .clickEnglishLevelDropDownByText(user.getEnglishLevel());
  }

  public void fillWorkSection(User user) {
    int counter = 0;
    while (user.getCompanyNamesQuantity() > counter) {
      if (counter > 0) {
        profileEditionScreen.clickAddJobButton();
      }
      profileEditionScreen.typeCompanyNameByIndex(counter, user.getCompanyNameByIndex(counter));
      profileEditionScreen.typePositionNameByIndex(counter, user.getPositionByIndex(counter));
      profileEditionScreen.typeJobStartDateByIndex(counter, user.getStartDateByIndex(counter));
      if (user.getTillNowCheckBoxByIndex(counter)) {
        profileEditionScreen.clickTillNowCheckBoxByIndex(counter);
      }
      if (!profileEditionScreen.isJobEndDateByIndex(counter)) {
        profileEditionScreen.typeJobEndDateByIndex(counter, user.getEndDateByIndex(counter));
      }
      profileEditionScreen.typeAdditionalInformationFromWorkExperienceByIndex(counter,
          user.getAdditionalInformationFromWorkExperienceSectionFormByIndex(counter));
      ++counter;
    }
  }

  public User readUserFromEditProfile() {
    User user = new User();
    user.setFirstName(profileEditionScreen.getFirstNameText());
    user.setLastName(profileEditionScreen.getLastNameText());
    user.setBirthDate(profileEditionScreen.getBirthDate());
    user.setCityOfResidence(profileEditionScreen.getCityOfResidenceValue());
    user.setPreferredMethodOfCommunication(
        profileEditionScreen.getPreferredMethodOfCommunication());
    user.setCityOfStudy(profileEditionScreen.getCityOfStudyValue());
    user.setEducationalEstablishment(
        profileEditionScreen.getEducationalEstablishmentValue());
    user.setFaculty(profileEditionScreen.getFacultyValue());
    user.setDepartment(profileEditionScreen.getDepartmentValue());
    user.setEducationForm(profileEditionScreen.getEducationFormValue());
    user.setDegreeInformation(profileEditionScreen.getDegreeInformationValue());
    if (!profileEditionScreen.isGraduationYearChosenPresent()) {
      user.setGraduationYear(profileEditionScreen.getGraduationYearValue());
    }
    if (!profileEditionScreen.isAssignmentFieldPresent()) {
      user.setAssignment(profileEditionScreen.getAssignmentText());
    }
    user.setEnglishLevel(profileEditionScreen.getEnglishLevelValue());
    user.setPhoneNumber(profileEditionScreen.getPhoneNumberText());
    user.setNativeName(profileEditionScreen.getUserNativeNameText());
    user.setCompanyName(profileEditionScreen.getCompanyNameText());
    user.setStartDate(profileEditionScreen.getStartDate());
    user.setPosition(profileEditionScreen.getPositionNameText());
    user.setEndDate(profileEditionScreen.getEndDate());
    return user;
  }

  public User readUserFromProfileScreen() {
    return readWorkSectionFromProfileScreen(
        readEducationSectionFromProfileScreen(readBasicSectionFromProfileScreen(new User())));
  }

  public User fillUserFieldsAndReadData(User usedUser) {
    fillUserFields(usedUser);
    return readUserFromEditProfile();
  }

  public void fillUserWithNotOneEducationAndWork(User user) {
    fillBaseSection(user);
    fillEducationSection(user);
    fillWorkSection(user);
  }

  public void deleteEducationSectionByIndex(int sectionIndex) {
    if (profileEditionScreen.isDeleteEducationButtonDisplayedByIndex(sectionIndex)) {
      profileEditionScreen.clickDeleteEducationSectionByIndex(sectionIndex)
          .clickContinueOnDeleteAlert()
          .waitForContinueOnDeleteAlertDisappear();
    }
  }

  public void deleteJobSectionByIndex(int sectionIndex) {
    if (profileEditionScreen.isDeleteWorkExperienceButtonDisplayedByIndex(sectionIndex)) {
      profileEditionScreen.clickDeleteJobSectionByIndex(sectionIndex)
          .clickContinueOnDeleteAlert()
          .waitForContinueOnDeleteAlertDisappear();
    }
  }

  public ProfileScreen closeConfirmationPopUpIfTestAlreadyAssigned() {
    if (profileScreen.isConfirmationPopUpCancelButtonDisplayed()) {
      profileScreen.clickConfirmInformationPopUpOkButton();
    }
    return profileScreen;
  }

  private User readBasicSectionFromProfileScreen(User userForSetting) {
    userForSetting.setFirstName(profileScreen.getFirstNameText());
    userForSetting.setLastName(profileScreen.getLastNameText());
    userForSetting.setSocialNetwork(profileScreen.getSkypeLogin());
    userForSetting.setBirthDate(profileScreen.getBirthDate());
    userForSetting.setCityOfResidence(profileScreen.getCityOfResidenceValue());
    userForSetting.setNativeName(profileScreen.clickArrowOfNativeName().getNativeName());
    profileScreen.clickArrowOfNativeName();
    userForSetting.setPhoneNumber(profileScreen.getPhoneNumberText());
    userForSetting
        .setPreferredMethodOfCommunication(profileScreen.getPreferredMethodOfCommunication());
    userForSetting.setEnglishLevel(profileScreen.getEnglishLevelValue());
    return userForSetting;
  }

  private User readEducationSectionFromProfileScreen(User userForSetting) {
    int counter = profileScreen.getEducationSectionsQuantity() - 1;
    while (counter >= 0) {
      userForSetting.setCityOfStudy(profileScreen.getCityOfStudyValueByIndex(counter).split(",")[0]);
      userForSetting.setEducationalEstablishment(
          profileScreen.getEducationEstablishmentValueByIndex(counter));
      userForSetting.setFaculty(profileScreen.getFacultyValueByIndex(counter));
      userForSetting.setDepartment(profileScreen.getDepartmentValueByIndex(counter));
      userForSetting.setEducationForm(profileScreen.getEducationFormValueByIndex(counter));
      userForSetting.setDegreeInformation(profileScreen.getDegreeInformationValueByIndex(counter));
      userForSetting
          .setUniversityCourseYear(profileScreen.getUniversityCourseValueByIndex(counter));
      userForSetting.setGraduationYear(profileScreen.getGraduationYearValueByIndex(counter));
      userForSetting.setAssignment(profileScreen.getAssignmentValueByIndex(counter));
      --counter;
    }
    userForSetting.setAdditionalInformationFromEducationSectionForm(
        profileScreen.getAdditionalInformationEducationSection());
    userForSetting.setProfessionalActivitiesFromEducationSectionForm(
        profileScreen.getProfessionalActivities());
    userForSetting.setEnglishLevel(profileScreen.getEnglishLevelValue());
    return userForSetting;
  }

  private User readWorkSectionFromProfileScreen(User userForSetting) {
    int counter = profileScreen.getWorkSectionsQuantity() - 1;
    while (counter >= 0) {
      userForSetting.setCompanyName(profileScreen.getWorkExperienceCompanyNameTextByIndex(counter));
      userForSetting.setStartDate(profileScreen.getStartDateByIndex(counter));
      userForSetting.setPosition(profileScreen.getPositionNameTextByIndex(counter));
      userForSetting.setEndDate(profileScreen.getEndDateByIndex(counter));
      userForSetting.setAdditionalInformationFromWorkExperienceSectionForm(
          profileScreen.getAdditionalInformationWorkByIndex(counter));
      userForSetting.setTillNowCheckBox(false);
      --counter;
    }
    return userForSetting;
  }

  public void clickRedirectToExaminatorIfTakeTestButtonExistsAndCloseBrowser() {
    if (profileScreen.isEnglishTestButtonDisplayed()) {
      profileScreen
          .clickEnglishTestButton()
          .clickIfDisplayedRedirectToEnglishTestOkButton()
          .switchToLastWindow();
      new ExaminatorScreen()
          .waitPageEnglishTestTitleForVisibility();
    }
    profileScreen.closeBrowser();
  }

  public ProfileScreen clickContinueOrPassEnglishTestButton() {
    profileScreen.clickEnglishTestButtonIfExists();
    if (profileScreen.isConfirmInformationPopUpDisplayed()) {
      profileScreen.waitConfirmationPopUpForVisibility()
          .clickPopupOkButton();
    }
    return profileScreen;
  }
}
