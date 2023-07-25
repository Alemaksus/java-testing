package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;

public class TrainingCenterManagementService {

  private TrainingCenterManagementScreen trainingCenterManagementScreen;

  public TrainingCenterManagementService() {
    trainingCenterManagementScreen = new TrainingCenterManagementScreen();
  }

  public void deleteTrainingCenter(String trainingCenterCity) {
    trainingCenterManagementScreen
        .clickCenterThreeDotsContextMenuByCityName(trainingCenterCity)
        .mouseOverDeleteTrainingCenterButton()
        .clickDeleteTrainingCenterButton()
        .mouseOverModalWindowConfirmationButton()
        .clickModalWindowConfirmationButton()
        .waitSuccessfulNotificationPopUpInvisibility();
  }

  public CreateOrEditTrainingCenterScreen openEditCenterScreen(String country, String city) {
    trainingCenterManagementScreen
        .clickCountryDropDown()
        .chooseCountry(country)
        .clickCityDropDown()
        .chooseCity(city)
        .clickApplyButton()
        .clickCenterLink();
    return new CreateOrEditTrainingCenterScreen();
  }

  public CenterScreen openPreviewCenterScreen(String country, String city) {
    trainingCenterManagementScreen
        .clickCountryDropDown()
        .chooseCountry(country)
        .clickCityDropDown()
        .chooseCity(city)
        .clickApplyButton()
        .clickCenterThreeDotsContextMenuByCityName(city)
        .clickViewTrainingCenterAction();
    return new CenterScreen();
  }

  public TrainingCenterManagementScreen selectCountryAndCityInSearchSection(String country,
      String city) {
    trainingCenterManagementScreen
        .clickCountryDropDown()
        .chooseCountry(country)
        .clickCityDropDown()
        .chooseCity(city)
        .clickApplyButton();
    return trainingCenterManagementScreen;
  }

  public TrainingCenterManagementScreen selectCountryInSearchSection(String country) {
    trainingCenterManagementScreen
        .clickCountryDropDown()
        .chooseCountry(country)
        .clickContainerOfCentresCount()
        .clickApplyButton();
    return trainingCenterManagementScreen;
  }

  public TrainingCenterManagementScreen selectCountryAndStatusInSearchSection(String country,
      String indexOfCenterStatus) {
    trainingCenterManagementScreen
        .clickCountryDropDown()
        .chooseCountry(country)
        .clickStatusDropDown()
        .chooseStatus(indexOfCenterStatus)
        .clickContainerOfCentresCount()
        .clickApplyButton();
    return trainingCenterManagementScreen;
  }

  public TrainingCenterManagementScreen selectStatusInSearchSection(String indexOfCenterCountry) {
    trainingCenterManagementScreen
        .clickStatusDropDown()
        .chooseStatus(indexOfCenterCountry)
        .clickContainerOfCentresCount()
        .clickApplyButton();
    return trainingCenterManagementScreen;
  }

  public TrainingCenterManagementScreen selectCountryAndCityInSearchSectionByIndex(
      int dropDownSelectIndex) {
    return trainingCenterManagementScreen
        .clickCountryDropDown()
        .selectCountryFromDDL(dropDownSelectIndex)
        .clickCityDropDown()
        .selectCityFromDDL(dropDownSelectIndex);
  }

  public CreateOrEditTrainingCenterScreen fillTheFieldsCreateTrainingCenterScreen(
      TrainingCenter trainingCenter) {
    return trainingCenterManagementScreen
        .waitCreateTrainingCenterButtonVisibility()
        .clickCreateTrainingCenterButton()
        .waitCreateOrEditTrainingCenterHeaderVisibility()
        .clickSelectCountryField()
        .selectCountryFromDDLByName(trainingCenter.getCountry())
        .waitCenterCityPlaceholderVisibility()
        .clickSelectCityField()
        .selectCityFromDDLByName(trainingCenter.getCity())
        .clickSelectSkillsField()
        .selectSkillFromDDLByName(trainingCenter.getSkill())
        .typeTrainingCenterDescriptionOnTab(LanguageEnum.ENGLISH, trainingCenter.getDescription())
        .typeTrainingCenterNameOnTab(LanguageEnum.ENGLISH, trainingCenter.getName())
        .typeFactDateOnEnglishTab(trainingCenter.getFactNumber())
        .typeFactDescriptionOnEnglishTab(trainingCenter.getFactDescription())
        .setTrainingCenterPictureLogo(trainingCenter.getImgBase64());
  }

  public CreateOrEditTrainingCenterScreen createTrainingCenter(TrainingCenter trainingCenter) {
    return fillTheFieldsCreateTrainingCenterScreen(trainingCenter)
        .mouseOverCreateOrSaveTrainingCenterTopButton()
        .clickCreateOrSaveTrainingCenterTopButton()
        .waitConfirmationPopupForVisibility()
        .mouseOverCreateConfirmationButton()
        .clickCreateConfirmationButton()
        .waitNotificationPopUpAboutSuccessVisibilityAndInvisibility();
  }

  public CreateOrEditTrainingCenterScreen createTrainingCenterWithUniversityAndFeedback(
      TrainingCenter trainingCenter) {
    return fillTheFieldsCreateTrainingCenterScreen(trainingCenter)
        .clickOnUniversityTab()
        .typeUniversityName(trainingCenter.getUniversityName())
        .typeUniversityShortName(trainingCenter.getUniversityShortName())
        .typeUniversityDescription(trainingCenter.getUniversityDescription())
        .clickAddUniversityButton()
        .clickOnFeedbackTab()
        .typeFeedbackAuthorsName(trainingCenter.getFeedbackAuthorsName())
        .typeFeedbackJobFunctional(trainingCenter.getFeedbackJobFunctional())
        .typeFeedback(trainingCenter.getFeedbackDescription())
        .clickAddFeedbackButton()
        .clickCreateOrSaveTrainingCenterTopButton();
  }

  public TrainingCenterManagementScreen deleteTrainingCenterIfPresent(
      TrainingCenter trainingCenter) {
    if (isTrainingCenterPresent(trainingCenter)) {
      deleteTrainingCenter(trainingCenter.getCity());
    }
    return trainingCenterManagementScreen;
  }

  public boolean isTrainingCenterPresent(TrainingCenter trainingCenter) {
    return trainingCenterManagementScreen
        .clickCountryDropDown()
        .selectCountryFromDDLByName(trainingCenter.getCountry())
        .clickApplyButton()
        .isCenterThreeDotsContextMenuByCityNameDisplayedShortTimeOut(trainingCenter.getCity());
  }

  public CreateOrEditTrainingCenterScreen fillTrainingCenterNameAndDescriptionOnTab(
      LanguageEnum tabLocale, String name, String description) {
    return new CreateOrEditTrainingCenterScreen()
        .openTab(tabLocale)
        .typeTrainingCenterNameOnTab(tabLocale, name)
        .typeTrainingCenterDescriptionOnTab(tabLocale, description);
  }

  public static String getLanguageTabName(LanguageEnum tabLocale) {
    switch (tabLocale) {
      case ENGLISH:
        return LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH);
      case RUSSIAN:
        return LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN);
      case UKRAINE:
        return LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN);
      default:
        throw new IllegalArgumentException(
            "Unknown language '" + tabLocale.getLanguageCode() + "'");
    }
  }
}
