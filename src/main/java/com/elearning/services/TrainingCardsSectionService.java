package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_ONLINE;
import static com.epmrdpt.utils.RandomUtils.getRandomNumber;
import static com.epmrdpt.utils.RandomUtils.getRandomNumberList;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.utils.RandomUtils;
import java.util.List;
import java.util.NoSuchElementException;

public class TrainingCardsSectionService {

  private TrainingBlockScreen trainingBlockScreen = new TrainingBlockScreen();
  private TrainingDescriptionScreen trainingDescriptionScreen = new TrainingDescriptionScreen();

  public String getLocationWithActiveTrainings() {
    trainingBlockScreen.clickSearchDropDown().waitDropDownVisibility();
    List<Integer> randomNumberCountryList =
        getRandomNumberList(
            trainingBlockScreen.waitDropDownCountryVisibility().getDropDownCountryCount());
    for (Integer countryIndex : randomNumberCountryList) {
      trainingBlockScreen.clickDropDownCountryByIndex(countryIndex)
          .clickChooseAllCitiesInDropDown();
      if (trainingBlockScreen.isTrainingCardAfterShortTimeoutDisplayed()) {
        return trainingBlockScreen.getDropDownCountryByIndexText(countryIndex);
      }
    }
    throw new NoSuchElementException("There are no countries with training cards");
  }

  public boolean isNoAvailableTrainingsBySkillsFound() {
    clickDropDownArrowButtonIfNeeded();
    List<Integer> randomNumbersSkills =
        getRandomNumberList(trainingBlockScreen.clickSkillsFiltersTab().getBySkillsCheckboxCount());
    for (Integer randomNumbersSkill : randomNumbersSkills) {
      trainingBlockScreen.waitDropDownVisibility();
      trainingBlockScreen.clickBySkillsCheckboxByIndex(randomNumbersSkill);
      if (trainingBlockScreen.isTrainingCardAfterShortTimeoutDisplayed()) {
        trainingBlockScreen.clickBySkillsCheckboxByIndex(randomNumbersSkill);
      } else {
        break;
      }
    }
    return trainingBlockScreen.isSubscribeButtonDisplayed();
  }

  public TrainingBlockScreen searchForNoAvailableTrainingsByCountryAndSkill() {
    List<Integer> randomNumberCountryList = getRandomNumberList(
        trainingBlockScreen.getDropDownCountryCount());
    for (Integer countryIndex : randomNumberCountryList) {
      trainingBlockScreen.clickDropDownCountryByIndex(countryIndex)
          .clickChooseAllCitiesInDropDown()
          .clickSkillsFiltersTab();
      for (String skillName : trainingBlockScreen.getDropDownSkillText()) {
        trainingBlockScreen.clickSkillCheckboxByName(skillName);
        if (!trainingBlockScreen.isTrainingCardAfterShortTimeoutDisplayed()) {
          return trainingBlockScreen;
        }
        trainingBlockScreen.clickSkillCheckboxByName(skillName);
      }
    }
    throw new NoSuchElementException("There are available trainings for all combinations of "
        + "country and skill");
  }

  public TrainingBlockScreen clearAllFilters() {
    if (trainingBlockScreen.isArrowButtonWithActiveFormDisplayed()) {
      trainingBlockScreen.clickDropDownArrowButton();
    }
    if (trainingDescriptionScreen.isCloseCommonIconDisplayed()) {
      trainingDescriptionScreen.clickCloseCommonIcon();
    }
    return trainingBlockScreen;
  }

  public TrainingDescriptionScreen openTrainingDescription(int countryIndex, int cityIndex,
      int skillIndex, String trainingName) {
    trainingBlockScreen.waitTrainingCardsVisibility();
    return clearAllFilters()
        .clickDropDownArrowButton()
        .clickLocationFiltersTab()
        .clickDropDownCountryByIndex(countryIndex)
        .clickDropDownCityByIndex(cityIndex)
        .clickSkillsFiltersTab()
        .clickBySkillsCheckboxByIndex(skillIndex)
        .clickDropDownArrowButton()
        .clickTrainingCardByName(trainingName);
  }

  public TrainingDescriptionScreen openTrainingDescription(String trainingName) {
    trainingBlockScreen.waitTrainingCardsVisibility();
    clearAllFilters();
    if (trainingBlockScreen.waitTrainingCardsVisibility()
        .isViewMoreTrainingsLinkDisplayedNoWait()) {
      trainingBlockScreen.cleanLocationFilterIfNeed().clickViewMoreTrainingsLink()
          .waitViewMoreTrainingsLinkAbsent();
    }
    return trainingBlockScreen.clickTrainingCardByName(trainingName)
        .waitTrainingStatusTextPresent();
  }

  public TrainingBlockScreen clickSkillFromDDLByText(String skillName) {
    List<String> skillsListFromDDLFilter = trainingBlockScreen.getDropDownSkillText();
    for (int i = 0; i < skillsListFromDDLFilter.size(); i++) {
      if (skillsListFromDDLFilter.get(i).contains(skillName)) {
        trainingBlockScreen.clickSkillFromDDLByIndex(i);
      }
    }
    return trainingBlockScreen;
  }

  public TrainingDescriptionScreen clickAnyTrainingCardOnTrainingBlock() {
    return trainingBlockScreen.openTrainingCardByIndex(
        RandomUtils.getRandomNumber(trainingBlockScreen.getTrainingTittlesCount()));
  }

  public TrainingListScreen openTrainingListScreen() {
    return new HeaderScreen()
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickTrainingListNavigationLink();
  }

  public RegistrationWizardScreen clickRegisterButtonByTrainingName(String trainingName) {
    return trainingBlockScreen
        .checkAndClickViewMoreTrainingsLink()
        .clickRegisterButtonOnTheTrainingByName(trainingName);
  }

  public TrainingBlockScreen setOnlineQuickFilter() {
    return trainingBlockScreen
        .waitTrainingCardsVisibility()
        .clickQuickFilterByName(LocaleProperties.getValueOf(TRAINING_LIST_QUIT_FILTER_ONLINE))
        .clickViewMoreTrainingsLink()
        .waitTrainingCardsVisibility();
  }

  public TrainingBlockScreen setRandomCountryCitySkillsFilters() {
    return trainingBlockScreen.clickSearchDropDown()
        .clickDropDownCountryByIndex(getRandomNumber(trainingBlockScreen.getDropDownCountryCount()))
        .clickCityByIndexFromDropDown(getRandomNumber(trainingBlockScreen.getDropDownCityCount()))
        .clickSkillsFiltersTab()
        .clickBySkillsCheckboxByIndex(
            getRandomNumber(trainingBlockScreen.getBySkillsCheckboxCount()))
        .clickBySkillsCheckboxByIndex(
            getRandomNumber(trainingBlockScreen.getBySkillsCheckboxCount()));
  }

  public TrainingBlockScreen setCountryFilterByIndex(int index) {
    return trainingBlockScreen.clickDropDownCountryByIndex(index);
  }

  public TrainingBlockScreen selectAllCitiesCheckboxIfNeeded() {
    return clickDropDownArrowButtonIfNeeded()
        .clickAllCitiesCheckboxIfNeeded()
        .checkAndClickViewMoreTrainingsLink();
  }

  public TrainingBlockScreen selectLocationFiltersTab() {
    return clickDropDownArrowButtonIfNeeded().
        clickLocationFiltersTab();
  }

  public TrainingBlockScreen clickDropDownArrowButtonIfNeeded() {
    if (!trainingBlockScreen.isArrowButtonWithActiveFormDisplayed()) {
      trainingBlockScreen.clickDropDownArrowButton();
    }
    return new TrainingBlockScreen();
  }
}
