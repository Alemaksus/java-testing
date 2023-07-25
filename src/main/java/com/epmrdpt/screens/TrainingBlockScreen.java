package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_BUTTON_PAID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_STATUS_TRAINING_FREE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_STATUS_TRAINING_FREEMIUM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_STATUS_TRAINING_PAID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_ONGOING_DURATION;
import static java.lang.String.format;
import static javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR;
import static javax.swing.text.html.CSS.Attribute.COLOR;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.text.html.HTML.Attribute;

public class TrainingBlockScreen extends AbstractScreen {

  public static final String BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREE = "rgba(118, 205, 216, 1)";
  public static final String BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_FREEMIUM = "rgba(206, 219, 86, 1)";
  public static final String BACKGROUND_COLOR_TRAINING_CARD_HEADER_PRICE_PAID = "rgba(255, 192, 0, 1)";
  public static final String ENGLISH_LANGUAGE_ABBREVIATION = "EN";
  public static final String RUSSIAN_LANGUAGE_ABBREVIATION = "RU";
  public static final String UKRAINIAN_LANGUAGE_ABBREVIATION = "UA";
  private static final String FILTER_CITY_COUNTRY_LOCATOR = "//div[@class='filter-field__input-city-country']/span";
  private static final String FILTER_ITEM_LOCATOR =
      FILTER_CITY_COUNTRY_LOCATOR + "[contains(text(),\"%s\")]";
  private static final String DROPDOWN_COUNTRY_BY_NAME_LOCATOR = "//div[contains(text(),\"%s\")]";
  private static final String DROPDOWN_CITY_BY_NAME_LOCATOR =
      "//li[contains(@class,'cities') and contains(@class,'ng-scope')]//label[text()[contains(.,'%s')]]";
  private static final String CHECKBOX_CITY_BY_NAME_LOCATOR =
      "//li[contains(@class,'cities') and contains(@class,'ng-scope')]//label[text()[contains(.,'%s')]]/input";
  private static final String TRAINING_CARD_BY_NAME_PATTERN =
      "//div[contains(@class, 'training-item') and text()='%s']";
  private static final String TRAINING_CARD_LANGUAGE_BY_NAME_PATTERN =
      "/../../preceding-sibling::div//span[@class='training-item__language']/span[contains(text(),'%s')]";
  private static final String TRAINING_CARD_ELEMENT_PATTERN =
      "//div[contains(@class,'desktop')]//*[contains(@class,'training-item__%s')]";
  private static final String COLUMN_BY_TYPE_PATTERN = "//div[@class='filter-type']/div[%d]";
  private static final String TITLE_IN_COLUMN_BY_TYPE_PATTERN = COLUMN_BY_TYPE_PATTERN + "/div";
  private static final String FIELD_IN_COLUMN_BY_TYPE_PATTERN =
      COLUMN_BY_TYPE_PATTERN + "//li//span[2]";
  private static final String TYPE_TITLE_COLUMN_LOCATOR = format(TITLE_IN_COLUMN_BY_TYPE_PATTERN,
      1);
  private static final String FIELD_IN_TYPE_COLUMN_LOCATOR = format(FIELD_IN_COLUMN_BY_TYPE_PATTERN,
      1);
  private static final String FORMAT_TITLE_COLUMN_LOCATOR = format(TITLE_IN_COLUMN_BY_TYPE_PATTERN,
      2);
  private static final String FIELD_IN_FORMAT_COLUMN_LOCATOR = format(
      FIELD_IN_COLUMN_BY_TYPE_PATTERN, 2);
  private static final String PRICING_TITLE_COLUMN_LOCATOR = format(TITLE_IN_COLUMN_BY_TYPE_PATTERN,
      3);
  private static final String FIELD_IN_PRICING_COLUMN_LOCATOR = format(
      FIELD_IN_COLUMN_BY_TYPE_PATTERN, 3);
  private static final String LANGUAGE_TITLE_COLUMN_LOCATOR = format(
      TITLE_IN_COLUMN_BY_TYPE_PATTERN, 4);
  private static final String FIELD_IN_LANGUAGE_COLUMN_LOCATOR = format(
      FIELD_IN_COLUMN_BY_TYPE_PATTERN, 4);
  private static final String LEVEL_TITLE_COLUMN_LOCATOR = format(TITLE_IN_COLUMN_BY_TYPE_PATTERN,
      5);
  private static final String FIELD_IN_LEVEL_COLUMN_LOCATOR = format(
      FIELD_IN_COLUMN_BY_TYPE_PATTERN, 5);
  private static final String CROSS_IN_TIME_ZONE_MENU_LOCATOR =
      "//i[contains(@class,'timezone-modal__close')]";
  private static final String TRAINING_REGISTER_BUTTON_PATTERN =
      "//div[contains(@class,'training-list__container')]//*[text()='%s']/"
          + "../../../following-sibling::*[@class='training-item__button']";
  private static final String TRAINING_LOCATION_PATTERN =
      "//div[contains(@class,'training-list__container')]//*[text()='%s']/"
          + "../following-sibling::*[contains(@class,'training-item__location')]";
  private static final String TRAINING_ICON_PATTERN =
      "//div[contains(@class,'training-list__container')]//img[@class='training-icon'][contains(@src,'%s')]";
  private static final String QUICK_FILTER_BY_NAME_PATTERN =
      "//p[contains(@class,'training-list__quick-filter')][contains(text(),'%s')]";
  private static final int TIMEOUT_FOR_TRAINING_CONTAINER_VISIBILITY_SEC = 90;
  private static final String CHECKBOX_CHECKED_ATTRIBUTE_NAME = "checked";
  private static final String BUTTON_PAID_PATTERN = "//p[contains(@class,'training-list__quick-filter ng-binding ng-scope')][contains(text(),'%s')]";
  private static final String MULTI_LOCATION_LABEL_PATTERN = "//div[contains(@class,'training-list__container')]//rd-tooltip[contains(.,'Multi-location')]";
  private static final String LOCATION_FROM_HINT_PATTERN = ".//*[contains(text(),'%s')]";
  private static final String SKILL_CHECKBOX_BY_NAME_PATTERN = "//label[contains(.,'%s')]/span";
  private static final String TRAINING_CARDS_LOCATION_GLOBAL_LOCATION = "Global";
  private static final String TRAINING_CARDS_LOCATION_MULTI_LOCATION = "Multi-location";
  private static final String TYPE_CHECKBOX_BY_NAME_PATTERN = "//li[contains(@class,'filter-type')]//span[text()='%s']";
  private final String TRAINING_CARD_BUTTON_BY_TRAINING_NAME_PATTERN =
      TRAINING_CARD_BY_NAME_PATTERN +
          "/ancestor::a/following-sibling::div/button[not (contains(@class,'ng-hide'))]";
  private static final String TRAINING_BY_NAME_PATTERN =
      "//div[contains(@class,'training-item__title') and contains(text(),'%s')]";
  private static final String TRAINING_ACTION_BY_TRAINING_NAME_PATTERN =
      TRAINING_BY_NAME_PATTERN +
          "/ancestor::a/following-sibling::div/button[not (contains(@class,'ng-hide'))]";
  private static final String TRAINING_ICON_BY_TRAINING_NUMBER_PATTERN =
      "(//div[contains(@class,'__inner')])[%d]//img[@class='training-icon' or contains(@ng-show,'AdditionalSkills')]";

  private Element trainingSection = Element
      .byCss("div.training-list__container");
  private Element trainingCards = Element.byXpath(
      "//*[contains(@class, 'skills-block-list')]//*[contains(@class, 'skill-card_skill-link')]");
  private Element subscribeButton = Element.byClassName("training-list__subscribe-btn");
  private Element trainingSectionTitle = Element.byCss("h1.section-ui__title");
  private Element viewMoreTrainingsLink = Element.byCss("div.training-list__more-trainings > span");
  private Element filtersDropDown = Element.byCss("input[name='training-filter-input']");
  private Element skillsFiltersTab = Element.byXpath(
      "//div[contains(@class,'navigation-item')][2]");
  private Element skillsCheckbox = Element
      .byXpath("//li[contains(@class,'cities')]//input[@class='our-skills']/ancestor::label");
  private Element typesCheckbox = Element.byXpath("//li[contains(@class,'type')]/label");
  private Element trainingsIcon = Element
      .byXpath("//div[contains(@class,'training-list__container')]//img[@class='training-icon']");
  private Element viewMoreTrainingsLinkNumberLabel = Element
      .byCss("div.training-list__more-trainings > span > span");
  private Element filtersDropDownOpened = Element
      .byCss("div.drop-down.drop-down-choose.drop-down-visibility");
  private Element dropDownCountry = Element
      .byXpath("//div[contains(@class,'location__not-active-label')]");
  private Element cityCheckbox = Element.byXpath(
      "//ul[contains(@class,'location__cities-list-cities') and contains(@class,'ng-scope')]//label");
  private Element trainingCardLocation = Element.byXpath(
      "//div[contains(@class,'desktop')]//div[@class='training-item__location']/span");
  private Element multiLocationLabels = Element.byXpath(MULTI_LOCATION_LABEL_PATTERN);
  private Element subscribeOnTrainingUpdatesLabel = Element.byXpath(
      "//div[@class='training-list__subscribe-text']/span[@class='ng-binding']");
  private Element locationsFiltersTab = Element
      .byXpath("//div[@ng-click=\"changeTab('Location')\"]");
  private Element typesFiltersTab = Element
      .byXpath("//div[contains(@class,'navigation-item')][3]");
  private Element dropDownCountryHighlighted = Element
      .byXpath("//div[contains(@class,'location__location-active-label-country')]");
  private Element dropDownChooseAllCities = Element.byXpath(
      "//div[contains(@class,'location__cities') and contains(@class,'ng-scope')]//label");
  private Element arrowButton = Element.byXpath("//form//div[contains(@class,'filter')]");
  private Element arrowButtonWithActiveForm = Element.byXpath(
      "//form//div[contains(@class,'rotate')]");
  private Element inputForm = Element.byTagName("form");
  private Element selectedSkillsUnderDropDown = Element
      .byXpath("//span[@class='filter-field__input-item-name ng-binding']");
  private Element selectedSkillsInDropDown = Element
      .byXpath("//input[@class='our-skills' and @checked='checked']/ancestor::label");
  private Element selectedTypeInDropDown = Element
      .byXpath("//li[contains(@class,'filter-type')]//input[@checked='checked']/ancestor::label");
  private Element selectedCityInDropDown = Element.byXpath(
      "//li[contains(@class,'cities')]//input[@checked='checked'][@ng-checked='city.isChecked']/ancestor::label");
  private Element filterType = Element
      .byXpath("//div[@class='filter-type-view__training-type-name ng-binding']");
  private Element noTrainingSection = Element.byXpath(
      "//div[contains(@ng-class, 'training-list__no-trainings')]//span[contains(@ng-show,'== 0')]");
  private Element locationFilterClearButton = Element
      .byXpath("//div[contains(@ng-if,'Location')]/div/span[contains(@class,'close')]");
  private Element trainingCardTitle = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "title"));
  private Element trainingCardType = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "info-type"));
  private Element trainingCardLevel = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "info-level"));
  private Element trainingCardFormat = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "format"));
  private Element trainingCardLanguage = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "language"));
  private Element trainingCardPrice = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "price"));
  private Element trainingCardButton = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "button"));
  private Element trainingCardIcon = Element
      .byXpath(format(TRAINING_CARD_ELEMENT_PATTERN, "icons") + "/img");
  private Element trainingCardDuration = Element.byXpath(
      "//div[contains(@class,'desktop')]/.//*[contains(@class,'training-item__duration')][not(contains(@class,'ng-hide'))]");
  private Element quickFilterTab = Element
      .byXpath("//p[contains(@class,'training-list__quick-filter')]");
  private Element typeTitleColumn = Element.byXpath(TYPE_TITLE_COLUMN_LOCATOR);
  private Element filterTypeField = Element.byXpath(FIELD_IN_TYPE_COLUMN_LOCATOR);
  private Element formatTitleColumn = Element.byXpath(FORMAT_TITLE_COLUMN_LOCATOR);
  private Element filterFormatField = Element.byXpath(FIELD_IN_FORMAT_COLUMN_LOCATOR);
  private Element pricingTitleColumn = Element.byXpath(PRICING_TITLE_COLUMN_LOCATOR);
  private Element filterPricingField = Element.byXpath(FIELD_IN_PRICING_COLUMN_LOCATOR);
  private Element languageTitleColumn = Element.byXpath(LANGUAGE_TITLE_COLUMN_LOCATOR);
  private Element filterLanguageField = Element.byXpath(FIELD_IN_LANGUAGE_COLUMN_LOCATOR);
  private Element levelTitleColumn = Element.byXpath(LEVEL_TITLE_COLUMN_LOCATOR);
  private Element filterLevelField = Element.byXpath(FIELD_IN_LEVEL_COLUMN_LOCATOR);
  private Element trainingCardHeader = Element.byXpath(
      "//*[contains(@class,'item-content--desktop')]//*[contains(@class,'training-item__header')]");
  private Element trainingSkillIFromDDL = Element
      .byXpath("//*[contains(@class,'location__skills')]//descendant::label[contains(text(),'')]");
  private Element allSelectedFilters = Element.byXpath(
      "//div[contains(@class,'filter-field__input')]/span|//div[contains(@class,'training-type-name')]");
  private Element selectedFilterItemLocationField = Element.byXpath(FILTER_CITY_COUNTRY_LOCATOR);
  private Element selectedFilterItemSkillField = Element
      .byXpath("//div[contains(@ng-repeat, 'skillName')]");
  private Element universityProgramLink = Element
      .byXpath(
          "//*[contains(@class, 'logo logo--university-program') or contains(@class, 'logo logo--training-center')]");
  private Element trainingsPaymentStatuses = Element
      .byXpath(
          "//div[contains(@class,'training-list__container')]//span[@class='training-item__price ng-binding']");
  private Element trainingsStartDate =
      Element.byXpath(
          "//div[contains(@class,'training-list__container')]//div[contains(@class,'duration')][@ng-hide]");
  private Element mainMenuLogo = Element.byXpath("//*[contains(@class,'styles_logo')][@href]");
  private Element crossInTimeZoneMenu = Element.byXpath(CROSS_IN_TIME_ZONE_MENU_LOCATOR);

  @Override
  public boolean isScreenLoaded() {
    return isTrainingCardsDisplayed();
  }

  public TrainingBlockScreen waitScreenLoaded() {
    trainingCards.waitForVisibility();
    return this;
  }

  public boolean isTrainingSectionDisplayed() {
    return trainingSection.isDisplayed();
  }

  public boolean isTrainingCardsDisplayed() {
    return trainingCards.isDisplayed(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
  }

  public boolean isSubscribeButtonDisplayed() {
    return subscribeButton.isDisplayed();
  }

  public String getTrainingsTitleText() {
    return trainingSectionTitle.getText();
  }

  public String getViewMoreTrainingsLinkText() {
    return viewMoreTrainingsLink.getText();
  }

  private List<Element> getTrainingsCardsList() {
    return trainingCards.getElements(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
  }

  public int getTrainingsCardsCount() {
    return getTrainingsCardsList().size();
  }

  public boolean isFiltersDropDownDisplayed() {
    return filtersDropDown.isDisplayed();
  }

  public TrainingBlockScreen clickTrainingsTitle() {
    trainingSectionTitle.click();
    return this;
  }

  public String getTextFromFiltersDropDown() {
    trainingSectionTitle.click();
    return filtersDropDown.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isArrowButtonDisplayed() {
    return arrowButton.isDisplayed();
  }

  public TrainingBlockScreen openFiltersDropDownAndClickLocationsFiltersTab() {
    clickDropDownArrowButton();
    locationsFiltersTab.waitForClickableAndClick();
    return this;
  }

  public TrainingBlockScreen openFiltersDropDownAndClickSkillsFiltersTab() {
    clickDropDownArrowButton();
    skillsFiltersTab.waitForClickableAndClick();
    return this;
  }

  public boolean isViewMoreTrainingsLinkDisplayed() {
    return viewMoreTrainingsLink.isDisplayed(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
  }

  public boolean isViewMoreTrainingsLinkDisplayedNoWait() {
    return viewMoreTrainingsLink.isDisplayedNoWait();
  }

  public boolean isTrainingsTitleDisplayed() {
    return trainingSectionTitle.isDisplayed();
  }

  public boolean isTrainingCardAfterShortTimeoutDisplayed() {
    return trainingCards.isDisplayed(SHORT_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllTrainingsCardsDisplayed() {
    return trainingCards.isAllElementsDisplayed();
  }

  public boolean isAllSkillsCheckBoxesDisplayed() {
    return skillsCheckbox.isAllElementsDisplayed();
  }

  public boolean isAllCityCheckBoxesForChosenCountryDisplayed() {
    return cityCheckbox.isAllElementsDisplayed();
  }

  public TrainingBlockScreen waitTrainingCardListDisplayed() {
    trainingCards.waitUntilAllElementsLocatedByAreVisible(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public TrainingBlockScreen clickSearchDropDown() {
    filtersDropDown.click();
    return this;
  }

  public TrainingBlockScreen waitSkillsFilterTabVisibility() {
    skillsFiltersTab.waitForVisibility();
    return this;
  }

  public TrainingBlockScreen clickSkillsFiltersTab() {
    skillsFiltersTab.click();
    return this;
  }

  public String getSkillsFilterTabText() {
    return skillsFiltersTab.getText();
  }

  private List<Element> getBySkillsCheckboxList() {
    return skillsCheckbox.getElements();
  }

  public int getBySkillsCheckboxCount() {
    return getBySkillsCheckboxList().size();
  }

  public TrainingBlockScreen clickBySkillsCheckboxByIndex(int bySkillsCheckboxIndex) {
    getBySkillsCheckboxElementByIndex(bySkillsCheckboxIndex).clickJs();
    return this;
  }

  public TrainingBlockScreen clickSkillCheckboxByName(String skillName) {
    Element.byXpath(SKILL_CHECKBOX_BY_NAME_PATTERN, skillName).click();
    return this;
  }

  private Element getBySkillsCheckboxElementByIndex(int bySkillsCheckboxIndex) {
    return getBySkillsCheckboxList().get(bySkillsCheckboxIndex);
  }

  private List<Element> getByTypesCheckboxList() {
    return typesCheckbox.getElements();
  }

  private Element getByTypesCheckboxElementByIndex(int byTypeCheckboxIndex) {
    return getByTypesCheckboxList().get(byTypeCheckboxIndex);
  }

  public TrainingBlockScreen clickByTypesCheckboxByIndex(int byTypeCheckboxIndex) {
    getByTypesCheckboxElementByIndex(byTypeCheckboxIndex).clickJs();
    return this;
  }

  public TrainingBlockScreen clickByTypesCheckboxByName(String checkboxName) {
    Element.byXpath(TYPE_CHECKBOX_BY_NAME_PATTERN, checkboxName).click();
    return this;
  }

  private List<Element> getTrainingsIconList() {
    return trainingsIcon.getElements();
  }

  public List<String> getTrainingCardSrcAttributeList() {
    return getTrainingsIconList().stream()
        .map(e -> e.getAttributeValue(Attribute.SRC.toString()))
        .collect(Collectors.toList());
  }

  public TrainingBlockScreen clickViewMoreTrainingsLink() {
    viewMoreTrainingsLink.mouseOverJS();
    viewMoreTrainingsLink.clickJs();
    return this;
  }

  public TrainingBlockScreen checkAndClickViewMoreTrainingsLink() {
    if (isViewMoreTrainingsLinkDisplayedNoWait()) {
      viewMoreTrainingsLink.click();
    }
    return this;
  }

  public TrainingBlockScreen hoverOnMoreTrainingLink() {
    viewMoreTrainingsLink.mouseOver();
    return this;
  }

  public String getColorMoreTrainingsLink() {
    return viewMoreTrainingsLink.getCssValue(COLOR.toString());
  }

  public TrainingBlockScreen waitViewMoreTrainingsLinkAbsent() {
    viewMoreTrainingsLink.waitForAbsence(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public boolean isViewMoreTrainingsLinkAbsent() {
    return viewMoreTrainingsLink.isAbsent();
  }

  public String getViewMoreTrainingsNumberLabelText() {
    return viewMoreTrainingsLinkNumberLabel.getText();
  }

  public TrainingBlockScreen waitDropDownVisibility() {
    filtersDropDownOpened.waitForVisibility();
    return this;
  }

  public boolean isOpenedFilterDropDownDisplayed() {
    return filtersDropDownOpened.isDisplayed();
  }

  public TrainingBlockScreen waitDropDownCountryVisibility() {
    dropDownCountry.waitForVisibility();
    return this;
  }

  public TrainingBlockScreen waitAllDropDownCountriesVisibility() {
    dropDownCountry.waitUntilAllElementsLocatedByAreVisible(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  private List<Element> getDropDownCountriesList() {
    return dropDownCountry.getElements();
  }

  public int getDropDownCountryCount() {
    return getDropDownCountriesList().size();
  }

  public Element getDropDownCountryByIndex(int countryIndex) {
    return getDropDownCountriesList().get(countryIndex);
  }

  public TrainingBlockScreen clickDropDownCountryByIndex(int countryIndex) {
    Element dropDownCountry = getDropDownCountryByIndex(countryIndex);
    dropDownCountry.waitForClickable();
    dropDownCountry.clickJs();
    return this;
  }

  private List<Element> getDropDownCityList() {
    return cityCheckbox.getElements();
  }

  public int getDropDownCityCount() {
    return getDropDownCityList().size();
  }

  private Element getDropDownCityByIndex(int countryIndex) {
    return getDropDownCityList().get(countryIndex);
  }

  public TrainingBlockScreen clickDropDownCityByIndex(int cityIndex) {
    getDropDownCityByIndex(cityIndex).click();
    return this;
  }

  public String getDropDownCityByIndexText(int cityIndex) {
    return getDropDownCityByIndex(cityIndex).getText();
  }

  public String getDropDownCountryByIndexText(int countryIndex) {
    return getDropDownCountriesList().get(countryIndex).getText();
  }

  public List<String> getTrainingCardsLocationsText() {
    return trainingCardLocation.getElementsText();
  }

  public TrainingBlockScreen waitTrainingCardsVisibility() {
    trainingCards.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public TrainingBlockScreen waitTrainingCardsVisibilityShortTimeOut() {
    trainingCards.waitForVisibility(SHORT_TIME_OUT_IN_SECONDS);
    return this;
  }

  public boolean isAnyTrainingCardDisplayed() {
    return !trainingCards.isAbsent() || !noTrainingSection.isDisplayedShortTimeOut();
  }

  public String getSubscribeButtonText() {
    return subscribeButton.getText();
  }

  public String getSubscribeOnTrainingUpdatesText() {
    return subscribeOnTrainingUpdatesLabel.getElementsText().stream()
        .collect(Collectors.joining(" "));
  }

  public TrainingBlockScreen clickLocationFiltersTab() {
    locationsFiltersTab.click();
    return this;
  }

  public TrainingBlockScreen waitLocationFilterTabVisibility() {
    locationsFiltersTab.waitForVisibility();
    return this;
  }

  public String getLocationsFiltersTabText() {
    return locationsFiltersTab.getText();
  }

  public TrainingBlockScreen clickTypesFiltersTab() {
    typesFiltersTab.click();
    return this;
  }

  public String getTypesFiltersTabText() {
    return typesFiltersTab.getText();
  }

  public SubscribeScreen clickSubscribeButton() {
    subscribeButton.clickJs();
    return new SubscribeScreen();
  }

  public List<String> getDropDownCitiesText() {
    return cityCheckbox.getElementsText();
  }

  public boolean isDropDownCountryDisplayed() {
    return dropDownCountry.isDisplayed();
  }

  public TrainingBlockScreen clickDropDownArrowButton() {
    arrowButton.mouseOver();
    arrowButton.click();
    return this;
  }

  public boolean isFilterItemDisplayed(String filterName) {
    return Element.byXpath(String.format(DROPDOWN_CITY_BY_NAME_LOCATOR, filterName)).isDisplayed();
  }

  public TrainingBlockScreen waitForFilterItemDisplayedShortTimeout(String filterName) {
    Element.byXpath(String.format(FILTER_ITEM_LOCATOR, filterName))
        .waitForPresenceOfAllElements(SHORT_TIME_OUT_IN_SECONDS);
    Element.byXpath(String.format(FILTER_ITEM_LOCATOR, filterName)).isDisplayedShortTimeOut();
    return this;
  }

  public TrainingBlockScreen waitForFilterItemDisappearShortTimeout(String filterName) {
    Element.byXpath(String.format(FILTER_ITEM_LOCATOR, filterName))
        .waitForAbsence(SHORT_TIME_OUT_IN_SECONDS);
    return this;
  }

  public boolean isFilterItemDisplayedShortTimeout(String filterName) {
    Element.byXpath(String.format(FILTER_ITEM_LOCATOR, filterName))
        .waitForPresenceOfAllElements(SHORT_TIME_OUT_IN_SECONDS);
    return Element.byXpath(String.format(FILTER_ITEM_LOCATOR, filterName))
        .isDisplayedShortTimeOut();
  }

  public boolean isFilterItemDisappearShortTimeout(String filterName) {
    Element.byXpath(String.format(FILTER_ITEM_LOCATOR, filterName)).waitForAbsence();
    return Element.byXpath(String.format(FILTER_ITEM_LOCATOR, filterName)).isAbsent();
  }

  public TrainingBlockScreen waitForQuickFilterTab() {
    quickFilterTab.waitForVisibility();
    return this;
  }

  public List<String> getQuickFilterTabsText() {
    return quickFilterTab.getElementsText();
  }

  public String getDropDownHighlightedCountry() {
    return dropDownCountryHighlighted.getText();
  }

  public boolean areCitiesAndCheckBoxesPresentForCountry() {
    return cityCheckbox.isDisplayed();
  }

  public TrainingBlockScreen waitForDropDownCities() {
    cityCheckbox.waitForVisibility();
    return this;
  }

  public boolean isChooseAllCitiesCheckBoxDisplayed() {
    return dropDownChooseAllCities.isDisplayed();
  }

  public TrainingBlockScreen clickChooseAllCitiesInDropDown() {
    dropDownChooseAllCities.isVisibleInViewPoint();
    dropDownChooseAllCities.waitForClickableAndClick();
    return this;
  }

  public String getChooseAllCitiesCheckBoxText() {
    return dropDownChooseAllCities.getText();
  }

  public TrainingBlockScreen clickCountryByNameFromDropDown(String countryName) {
    Element.byXpath(String.format(DROPDOWN_COUNTRY_BY_NAME_LOCATOR, countryName))
        .clickJs();
    return this;
  }

  public TrainingBlockScreen clickCityByNameFromDropDown(String cityName) {
    Element.byXpath(String.format(DROPDOWN_CITY_BY_NAME_LOCATOR, cityName))
        .waitForClickableAndClick();
    return this;
  }

  public TrainingBlockScreen clickCityByIndexFromDropDown(int index) {
    cityCheckbox.getElements().get(index).waitForClickableAndClick();
    return this;
  }

  public TrainingBlockScreen selectAllCitiesInDropDownByCountryNameAndCloseDropDown(
      String countryName) {
    waitAllDropDownCountriesVisibility()
        .clickCountryByNameFromDropDown(countryName)
        .waitForDropDownCities()
        .clickChooseAllCitiesInDropDown()
        .clickDropDownArrowButton();
    return this;
  }

  public TrainingBlockScreen waitForSelectedSkillsInDropDownVisibility() {
    selectedSkillsInDropDown.waitForVisibility();
    return this;
  }

  public List<String> getSelectedSkillsInDropDownList() {
    return selectedSkillsInDropDown.getElementsText();
  }

  public boolean isSelectedSkillsInDropDownDisplayed() {
    return selectedSkillsInDropDown.isDisplayed();
  }

  public boolean isSelectedTypeInDropDownDisplayed() {
    return selectedTypeInDropDown.isDisplayed();
  }

  public List<String> getSelectedTypeInDropDownList() {
    return selectedTypeInDropDown.getElementsText();
  }

  public List<String> getSelectedCityInDropDownList() {
    return selectedCityInDropDown.getElementsText();
  }

  public List<String> getSelectedSkillsUnderDropDown() {
    return selectedSkillsUnderDropDown.getElementsText();
  }

  public List<String> getFilterTypesText() {
    return filterType.getElementsText();
  }

  public boolean isCityCheckBoxSelected(String cityName) {
    return Element.byXpath(String.format(CHECKBOX_CITY_BY_NAME_LOCATOR, cityName))
        .isAttributePresent(CHECKBOX_CHECKED_ATTRIBUTE_NAME);
  }

  public TrainingBlockScreen waitSubscribeButtonVisibility() {
    subscribeButton.waitForVisibility();
    return this;
  }

  public TrainingBlockScreen hoverOverSubscribeButton() {
    subscribeButton.mouseOver();
    return this;
  }

  public String getSubscribeButtonColor() {
    return subscribeButton.getCssValue(BACKGROUND_COLOR.toString());
  }

  public String getHoveredSubscribeButtonColor() {
    return subscribeButton.getHoveredCssValue(BACKGROUND_COLOR.toString());
  }

  public boolean isLocationFilterClearButtonDisplayed() {
    return locationFilterClearButton.isDisplayed();
  }

  public TrainingBlockScreen clickLocationFilterClearButton() {
    locationFilterClearButton.clickJs();
    return this;
  }

  public TrainingBlockScreen waitLocationFilterClearButtonForDisappear() {
    locationFilterClearButton.waitForDisappear();
    return this;
  }

  public TrainingBlockScreen closeTimeZoneMenuIfNeeded() {
    if (isCrossInTimeZoneMenuDisplayedShortTimeout()) {
      closeTimeZoneMenu();
    }
    return this;
  }

  public boolean isCrossInTimeZoneMenuDisplayedShortTimeout() {
    return crossInTimeZoneMenu.isDisplayedShortTimeOut();
  }

  public TrainingBlockScreen closeTimeZoneMenu() {
    crossInTimeZoneMenu.click();
    return this;
  }

  public TrainingBlockScreen cleanLocationFilterIfNeed() {
    if (isLocationFilterClearButtonDisplayedNoWait()) {
      clickLocationFilterClearButton();
    }
    return this;
  }

  public boolean isLocationFilterClearButtonDisplayedNoWait() {
    return locationFilterClearButton.isDisplayedNoWait();
  }

  public boolean isTrainingCardByNameDisplayed(String trainingName) {
    return Element.byXpath(TRAINING_CARD_BY_NAME_PATTERN, trainingName)
        .isDisplayed();
  }

  public boolean isTrainingCardByNameDisplayedNoWait(String trainingName) {
    return Element.byXpath(TRAINING_CARD_BY_NAME_PATTERN, trainingName).isDisplayedNoWait();
  }

  public TrainingDescriptionScreen clickTrainingCardByName(String trainingName) {
    Element trainingCard = Element.byXpath(TRAINING_CARD_BY_NAME_PATTERN, trainingName);
    trainingCard.mouseOver();
    trainingCard.clickJs();
    return new TrainingDescriptionScreen();
  }

  public boolean isEnglishLanguageOnCardDisplayed(String trainingName) {
    return Element.byXpath(TRAINING_CARD_BY_NAME_PATTERN +
        TRAINING_CARD_LANGUAGE_BY_NAME_PATTERN, trainingName, ENGLISH_LANGUAGE_ABBREVIATION)
        .isDisplayed();
  }

  public boolean isRussianLanguageOnCardDisplayed(String trainingName) {
    return Element.byXpath(TRAINING_CARD_BY_NAME_PATTERN +
        TRAINING_CARD_LANGUAGE_BY_NAME_PATTERN, trainingName, RUSSIAN_LANGUAGE_ABBREVIATION)
        .isDisplayed();
  }

  public boolean isUkrainianLanguageOnCardDisplayed(String trainingName) {
    return Element.byXpath(TRAINING_CARD_BY_NAME_PATTERN +
        TRAINING_CARD_LANGUAGE_BY_NAME_PATTERN, trainingName, UKRAINIAN_LANGUAGE_ABBREVIATION)
        .isDisplayed();
  }

  public boolean isAllIconsOfTrainingCardsDisplayed() {
    return trainingsIcon.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllTitlesOfTrainingCardsDisplayed() {
    return trainingCardTitle.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllTypesOfTrainingCardsDisplayed() {
    return trainingCardType.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllLevelsOfTrainingCardsDisplayed() {
    return trainingCardLevel.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllFormatsOfTrainingCardsDisplayed() {
    return trainingCardFormat.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllLanguagesOfTrainingCardsDisplayed() {
    return trainingCardLanguage.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllPricesOfTrainingCardsDisplayed() {
    return trainingCardPrice.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public List<String> getListOfTrainingCardPriceText() {
    return trainingCardPrice.getElementsText();
  }

  public boolean isAllLocationsOfTrainingCardsDisplayed() {
    return trainingCardLocation.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllDurationsOfTrainingCardsDisplayed() {
    return trainingCardDuration.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllButtonOfTrainingCardsDisplayed() {
    return trainingCardButton.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isAllTypesCheckboxesDisplayed() {
    return typesCheckbox.isAllElementsDisplayed();
  }

  public TrainingBlockScreen waitTrainingCardLocationVisibility() {
    trainingCardLocation.waitForVisibility(TIMEOUT_FOR_TRAINING_CONTAINER_VISIBILITY_SEC);
    return this;
  }

  public TrainingBlockScreen waitLocationFilterClearButtonVisibility() {
    locationFilterClearButton.waitForVisibility();
    return this;
  }

  public String getTypeColumnText() {
    return typeTitleColumn.getText();
  }

  public String getFormatColumnText() {
    return formatTitleColumn.getText();
  }

  public String getPricingColumnText() {
    return pricingTitleColumn.getText();
  }

  public String getLanguageColumnText() {
    return languageTitleColumn.getText();
  }

  public String getLevelColumnText() {
    return levelTitleColumn.getText();
  }

  public List<String> getAllFilterTypesText() {
    return filterTypeField.getElementsText();
  }

  public List<String> getAllFilterFormatsText() {
    return filterFormatField.getElementsText();
  }

  public List<String> getAllFilterPricingText() {
    return filterPricingField.getElementsText();
  }

  public List<String> getAllFilterLanguagesText() {
    return filterLanguageField.getElementsText();
  }

  public List<String> getAllFilterLevelsText() {
    return filterLevelField.getElementsText();
  }

  public List<String> getListOfTrainingCardButtonText() {
    return trainingCardButton.getElementsText();
  }

  public String getTrainingCardButtonText(String trainingName) {
    return Element.byXpath(TRAINING_CARD_BUTTON_BY_TRAINING_NAME_PATTERN, trainingName).getText();
  }

  public boolean isTrainingCardButtonPresentNoWait(String trainingName) {
    return Element.byXpath(TRAINING_CARD_BUTTON_BY_TRAINING_NAME_PATTERN, trainingName)
        .isPresentNoWait();
  }

  public List<Element> getListOfTrainingCardHeaders() {
    return trainingCardHeader.getElements();
  }

  public TrainingDescriptionScreen openTrainingCardByIndex(int cardIndex) {
    getTrainingsCardsList().get(cardIndex).click();
    return new TrainingDescriptionScreen();
  }

  public RegistrationWizardScreen clickRegisterButtonOnTheTrainingByName(String trainingName) {
    Element.byXpath(TRAINING_REGISTER_BUTTON_PATTERN, trainingName).click();
    return new RegistrationWizardScreen();
  }

  public String getTrainingLocationTextByName(String trainingName) {
    return Element.byXpath(TRAINING_LOCATION_PATTERN, trainingName).getText();
  }

  public List<String> getFilterCityCountryList() {
    return Element.byXpath(FILTER_CITY_COUNTRY_LOCATOR).getElementsText();
  }

  public List<String> getDropDownSkillText() {
    return trainingSkillIFromDDL.getElementsText();
  }

  public TrainingBlockScreen clickSkillFromDDLByIndex(int index) {
    trainingSkillIFromDDL.getElements().get(index).mouseOverAndClick();
    return this;
  }

  public TrainingBlockScreen waitTrainingIconsAreDisplayedBySkillName(
      String skillNameInIconPath) {
    Element.byXpath(TRAINING_ICON_PATTERN, skillNameInIconPath)
        .waitUntilAllElementsLocatedByAreVisible();
    return this;
  }

  public boolean isTrainingIconsAreDisplayedBySkillName(String skillNameInIconPath) {
    return Element.byXpath(TRAINING_ICON_PATTERN, skillNameInIconPath).isAllElementsDisplayed();
  }

  public int getTrainingTittlesCount() {
    return trainingCardTitle.getElements().size();
  }

  public String getTrainingCardFullTitleByIndexText(int index) {
    return trainingsIcon.getElements().get(index).getAttributeValue(Attribute.ALT.toString());
  }

  public TrainingBlockScreen clickQuickFilterByName(String filter) {
    Element.byXpath(QUICK_FILTER_BY_NAME_PATTERN, filter).click();
    return this;
  }

  public List<String> getListOfTrainingCardFormatText() {
    return trainingCardFormat.getElementsText();
  }

  public String getSelectedFilterItemLocationFieldText() {
    return selectedFilterItemLocationField.getText();
  }

  public String getSelectedFilterItemSkillFieldText() {
    return selectedFilterItemSkillField.getText();
  }

  public TrainingBlockScreen clickUniversityProgramLink() {
    mainMenuLogo.click();
    return this;
  }

  public TrainingBlockScreen clickPaidButton() {
    Element.byXpath(BUTTON_PAID_PATTERN, getValueOf(HEADER_BUTTON_PAID)).click();
    return this;
  }

  public List<String> sortStatusesValuesByPaymentType(List<String> list) {
    return Stream.concat(Stream.concat(
        list.stream().filter((string) -> string.equals(getValueOf(HEADER_STATUS_TRAINING_PAID)))
        , list.stream()
            .filter((string) -> string.equals(getValueOf(HEADER_STATUS_TRAINING_FREEMIUM)))),
        list.stream()
            .filter((string) -> string.equals(getValueOf(HEADER_STATUS_TRAINING_FREE))))
        .collect(Collectors.toList());
  }

  public List<String> getAllTrainingPaymentStatusesList() {
    List<String> paymentStatusesValues = new ArrayList<>();
    trainingsPaymentStatuses.getElements()
        .forEach(status -> paymentStatusesValues.add(status.getAttributeValue("innerHTML")));
    return paymentStatusesValues;
  }

  public boolean isTrainingCardsFilteredByCountryAndCity(String country, String city) {
    List<String> trainingCardsLocationsText = getTrainingCardsLocationsText();
    return trainingCardsLocationsText.stream().allMatch(location ->
        location.contains(country) || location.contains(city)
            || location.equals(TRAINING_CARDS_LOCATION_GLOBAL_LOCATION)
            || location.equals(TRAINING_CARDS_LOCATION_MULTI_LOCATION));
  }

  public boolean isAllMultiLocationLabelHintsContainOneOfLocations(String... locations) {
    return multiLocationLabels.getElements().stream().allMatch(
        multiLocationLabel -> isMultiLocationLabelHintContainsOneOfLocations(multiLocationLabel,
            locations));
  }

  private boolean isMultiLocationLabelHintContainsOneOfLocations(Element multiLocationLabel,
      String... locations) {
    return Arrays.stream(locations).anyMatch(
        location -> multiLocationLabel.hasChild(
            Element.byXpath(LOCATION_FROM_HINT_PATTERN, location)));
  }

  private List<String> getTrainingStartDatesText() {
    List<String> trainingStartDatesText = new ArrayList<>();
    trainingsStartDate.getElements().stream()
        .filter(element -> element.isDisplayedNoWait())
        .forEach(element -> trainingStartDatesText.add(element.getText()));
    return trainingStartDatesText;
  }

  public boolean isTrainingCardsOrdinaryAndOngoingDisplayedByTurn() {
    List<String> trainingStartDatesText = getTrainingStartDatesText();
    for (int i = 1; i < trainingStartDatesText.size(); i = i + 2) {
      if (!trainingStartDatesText.get(i)
          .equals(LocaleProperties.getValueOf(TRAINING_DESCRIPTION_ONGOING_DURATION))) {
        return false;
      }
    }
    return true;
  }

  public List<String> getTrainingStartDatesTextWithDate(){
    return getTrainingStartDatesText()
        .stream()
        .filter(
            date -> !date.equals(LocaleProperties.getValueOf(TRAINING_DESCRIPTION_ONGOING_DURATION)))
        .collect(Collectors.toList());
  }

  public TrainingBlockScreen clickAllCitiesCheckboxIfNeeded() {
    if (!dropDownChooseAllCities.getAttributeValue("class")
        .contains("location__location-active-label")) {
      dropDownChooseAllCities.click();
    }
    return this;
  }

  public boolean isArrowButtonWithActiveFormDisplayed() {
    return arrowButtonWithActiveForm.isDisplayedNoWait();
  }

  public boolean isAppropriateTrainingDisplayed(String trainingSkill, String iconName,
      String expectedCardLanguage) {
    List<String> allSelectedAttributes = getAllSelectedAttributes();
    return allSelectedAttributes.contains(getTrainingCardType()) &&
        allSelectedAttributes.contains(getTrainingCardFormat()) &&
        allSelectedAttributes.contains(getTrainingCardLevel()) &&
        allSelectedAttributes.contains(getTrainingCardPrice()) &&
        getTrainingCardLanguage().equals(expectedCardLanguage) &&
        allSelectedAttributes.contains(
            getValueOf(LocalePropertyConst.TRAINING_DESCRIPTION_PLANNED_LANGUAGE).toUpperCase()) &&
        allSelectedAttributes.contains(getTrainingCardLocation()) &&
        allSelectedAttributes.contains(trainingSkill.toUpperCase()) &&
        getTrainingCardSkill().contains(iconName);
  }

  public String getTrainingCardType() {
    return trainingCardType.getText().toUpperCase();
  }

  public String getTrainingCardLanguage() {
    return trainingCardLanguage.getText().toUpperCase();
  }

  public String getTrainingCardLevel() {
    return trainingCardLevel.getText().toUpperCase().split(" ")[0];
  }

  public String getTrainingCardPrice() {
    return trainingCardPrice.getText().toUpperCase();
  }

  public String getTrainingCardFormat() {
    return trainingCardFormat.getText().toUpperCase();
  }

  public String getTrainingCardLocation() {
    return trainingCardLocation.getText().contains(",") ? trainingCardLocation.getText()
        .split(", ")[1].toUpperCase() : trainingCardLocation.getText().toUpperCase();
  }

  public String getTrainingCardSkill() {
    return trainingCardIcon.getAttributeValue("src");
  }

  public List<String> getAllSelectedAttributes() {
    return allSelectedFilters.getElementsText();
  }

  public TrainingBlockScreen waitTrainingByNameDisplayed(String trainingName) {
    Element
        .byXpath(TRAINING_ACTION_BY_TRAINING_NAME_PATTERN, trainingName)
        .isDisplayed();
    return this;
  }

  public boolean isAllTrainingsContainSkillImage(String imageSrc) {
    return IntStream.rangeClosed(1, getTrainingsCardsCount())
        .mapToObj(trainingIndex -> Element.byXpath(TRAINING_ICON_BY_TRAINING_NUMBER_PATTERN,
            trainingIndex))
        .map(trainingCardSkills -> getSkillImageSrc(trainingCardSkills.getElements()))
        .allMatch(skillImageSrcs -> skillImageSrcs.contains(imageSrc));
  }

  private List<String> getSkillImageSrc(List<Element> trainingCardSkills) {
    return trainingCardSkills.stream()
        .map(trainingSkill -> trainingSkill.getAttributeValue(Attribute.SRC.toString()))
        .collect(Collectors.toList());
  }
}
