package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CENTER_MANAGEMENT_ACTION_EDIT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CENTER_MANAGEMENT_ACTION_PREVIEW;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_ELLIPSIS_ACTION_MENU_EDIT;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class TrainingCenterManagementScreen extends AbstractScreen {

  private static final String CHOOSE_COUNTRY_LOCATOR_PATTERN = "//li[text()='%s']";
  private static final String CHOOSE_CITY_LOCATOR_PATTERN = "//li[text()='%s']";
  private static final String CONTEXT_MENU_BY_CENTER_NAME_LOCATOR = "//*[contains(text(),'%s')]"
      + "/ancestor::*[contains(@class,'rd-table__row')]"
      + "//descendant::*//*[contains(@class,'rd-table__ellipsis')]";
  private static final String COUNTRY_DDL_BY_TEXT_LOCATOR =
      "//li[contains(@class,'active-result') and contains(text(),'%s')]";
  private static final String CITY_DDL_BY_TEXT_LOCATOR =
  "//div[contains(@id,'city')]//li[contains(@class,'active-result') and contains(text(),'%s')]";
  private static final String CHOOSE_STATUS_LOCATOR_PATTERN =
      "//*[@id='status_chosen']/.//*[@class='chosen-results']/ *[@data-option-array-index='%s']";
  private static final String LIST_ACTIONS_POP_UP_PATTERN = "//div[@class='rd-table--desktop ng-scope']//a[contains(text(),'%s')]";
  private static final String THREE_DOTS_LOCATOR_PATTERN = "//*[contains(@class,'desktop')]//"
      + "div[div[a[text()='%s']]]//span[@ng-click='resources.showTooltip(item)']";
  private static final String THREE_DOTS_MENU_ELEMENT_LOCATOR_PATTERN =
      "//*[contains(@class,'desktop')]//*[contains(text(),'%s')]";
  private static final String STATUS_OF_CENTER_LOCATOR_PATTERN =
      "//*[contains(@class,'desktop')]//div[div[a[text()='%s']]]//div[@class='rd-table__cell ng-binding'][div]";

  private Element centerManagementHeader = Element
      .byCss("div.filters__header.ng-binding");
  private Element createTrainingCenterButton = Element
      .byXpath("//a[contains(@href, 'Create')]");
  private Element centerFilters = Element.byXpath("//div[@class='filters']");
  private Element countrySelect = Element.byXpath("//*[@id='country_chosen']");
  private Element citySelect = Element.byXpath("//*[@id='city_chosen']");
  private Element statusSelect = Element.byXpath("//*[@id='status_chosen']");
  private Element centerLink = Element.byXpath("//*[contains(@class,'desktop')]/"
      + ".//*[contains(@class,'rd-table__cell')]/ *[@class='ng-binding']");
  private Element applyButton = Element.byXpath("//button[@ng-click='applyFilters()']");
  private Element resetButton = Element.byXpath("//button[contains(@class,'filters')]");
  private Element selectCountryFromDDL = Element
      .byXpath("//*[@id='country_chosen']//descendant::*[contains(@class,'active-result')]");
  private Element deleteCenterButton = Element
      .byXpath("//*[contains(@ng-click,'resources.removeTrainingCenter')]");
  private Element modalWindowConfirmationButton = Element
      .byXpath("//*[contains(@class,'message-btn message-btn-ok')]");
  private Element choosingContainerOfCentresCount = Element
      .byXpath("//*[@class='count-picker-text']/ .//*[contains(@class,'chosen-container-single')]");
  private Element labelOfChoseCenter = Element.byXpath("//*[@class='rd-table--desktop ng-scope']/"
      + ".//*[@class='rd-table__cell ng-binding']/ *");
  private Element centerCountry = Element.byXpath(
      "//div[contains(@class, 'rd-table--desktop')]//div[@ng-repeat='item in getCurrentItems()']/div[1]");
  private Element searchSection = Element.byCss("div.filters__choose-container");
  private Element sortingSection = Element.byCss("div.filters__table-container");
  private Element paginationSection = Element.byXpath(
      "//*[contains(@class,'rd-table--desktop')]//descendant::div[contains(@class,'training-centers__pagination')]");
  private Element footerSection = Element.byId("footer");
  private Element threeDotsButton = Element.byCss(".rd-table__ellipsis");
  private Element editTrainingCenterAction = Element
      .byXpath(LIST_ACTIONS_POP_UP_PATTERN, getValueOf(CENTER_MANAGEMENT_ACTION_EDIT));
  private Element selectionCityDDL = Element
      .byXpath("//*[@id='city_chosen']//descendant::*[contains(@class,'active-result')]");
  private Element countryAndCityRow = Element.byXpath("//div[contains(@class, 'rd-table--desktop')]"
      + "/div[contains(@class, 'training-centers-table-row')]/div[@ng-repeat='item in getCurrentItems()']");
  private Element threeDotsListOfAction = Element.byXpath(
      "//div[@ng-include='templateUrls.desktop']//div[@class='training-centers__tooltip ng-scope']");
  private Element confirmationPopUp = Element.byClassName("popup-wrapper");
  private Element successfulNotificationPopUp = Element.byCss(".status-popup");
  private Element viewTrainingCenterAction = Element
      .byXpath(LIST_ACTIONS_POP_UP_PATTERN, getValueOf(CENTER_MANAGEMENT_ACTION_PREVIEW));
  private Element trainingCenterCities = Element.byXpath(
      "//div[@ng-include='templateUrls.desktop']//div[@class='training-centers-table-row ng-scope']//a");

  @Override
  public boolean isScreenLoaded() {
    return isCreateTrainingCenterButtonDisplayed();
  }

  public boolean isCreateTrainingCenterButtonDisplayed() {
    return createTrainingCenterButton.isDisplayed();
  }

  public boolean isCenterManagementHeaderDisplayed() {
    return centerManagementHeader.isDisplayed();
  }

  public String getTrainingCenterManagementHeaderText() {
    return centerManagementHeader.getText();
  }

  public TrainingCenterManagementScreen waitTrainingCenterCitiesVisibility() {
    trainingCenterCities.waitForVisibility();
    return this;
  }

  public TrainingCenterManagementScreen waitCreateTrainingCenterButtonVisibility() {
    createTrainingCenterButton.waitForVisibility();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickCreateTrainingCenterButton() {
    scrollToTop();
    createTrainingCenterButton.click();
    return new CreateOrEditTrainingCenterScreen();
  }

  public TrainingCenterManagementScreen clickCenterThreeDotsContextMenuByCityName(String cityName) {
    Element.byXpath(CONTEXT_MENU_BY_CENTER_NAME_LOCATOR, cityName).click();
    return this;
  }

  public boolean isCenterThreeDotsContextMenuByCityNameDisplayedShortTimeOut(String cityName) {
    return Element.byXpath(CONTEXT_MENU_BY_CENTER_NAME_LOCATOR, cityName).isDisplayedShortTimeOut();
  }

  public TrainingCenterManagementScreen clickDeleteTrainingCenterButton() {
    deleteCenterButton.click();
    return this;
  }

  public TrainingCenterManagementScreen mouseOverDeleteTrainingCenterButton() {
    deleteCenterButton.mouseOver();
    return this;
  }

  public TrainingCenterManagementScreen clickModalWindowConfirmationButton() {
    modalWindowConfirmationButton.click();
    return this;
  }

  public TrainingCenterManagementScreen mouseOverModalWindowConfirmationButton() {
    modalWindowConfirmationButton.mouseOver();
    return this;
  }

  public TrainingCenterManagementScreen selectCountryFromDDL(int dropDownSelectIndex) {
    selectCountryFromDDL.getElements().get(dropDownSelectIndex).click();
    return this;
  }

  public TrainingCenterManagementScreen selectCountryFromDDLByName(String countryName) {
    Element.byXpath(COUNTRY_DDL_BY_TEXT_LOCATOR, countryName).mouseOver();
    Element.byXpath(COUNTRY_DDL_BY_TEXT_LOCATOR, countryName).click();
    Element.byXpath(COUNTRY_DDL_BY_TEXT_LOCATOR, countryName).enter();
    return this;
  }

  public TrainingCenterManagementScreen waitFiltersForVisibility() {
    centerFilters.waitForVisibility();
    return this;
  }

  public TrainingCenterManagementScreen clickCountryDropDown() {
    countrySelect.click();
    return this;
  }

  public TrainingCenterManagementScreen clickCityDropDown() {
    citySelect.click();
    return this;
  }

  public TrainingCenterManagementScreen clickApplyButton() {
    applyButton.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickCenterLink() {
    centerLink.click();
    return new CreateOrEditTrainingCenterScreen();
  }

  public TrainingCenterManagementScreen chooseCountry(String countryOfCenter) {
    Element.byXpath(CHOOSE_COUNTRY_LOCATOR_PATTERN, countryOfCenter).click();
    return this;
  }

  public TrainingCenterManagementScreen chooseCity(String cityOfCenter) {
    Element.byXpath(CHOOSE_CITY_LOCATOR_PATTERN, cityOfCenter).click();
    return this;
  }

  public String getCenterCountryText() {
    return centerCountry.getText();
  }

  public String getCityNameOfTrainingCenter() {
    return centerLink.getText();
  }

  public TrainingCenterManagementScreen clickResetButton() {
    resetButton.click();
    return this;
  }

  public TrainingCenterManagementScreen clickContainerOfCentresCount() {
    choosingContainerOfCentresCount.click();
    return this;
  }

  public List<String> getCenterCountryListText() {
    return centerCountry.getElementsText();
  }

  public List<String> getCenterStatusClassNames() {
    return labelOfChoseCenter.getElementsClassName();
  }

  public TrainingCenterManagementScreen clickStatusDropDown() {
    statusSelect.click();
    return this;
  }

  public TrainingCenterManagementScreen chooseStatus(String statusOfCenter) {
    Element.byXpath(CHOOSE_STATUS_LOCATOR_PATTERN, statusOfCenter).click();
    return this;
  }

  public boolean isSearchSectionDisplayed() {
    return searchSection.isDisplayed();
  }

  public boolean isSortingSectionDisplayed() {
    return sortingSection.isDisplayed();
  }

  public boolean isPaginationSectionDisplayed() {
    return paginationSection.isDisplayed();
  }

  public boolean isFooterSectionDisplayed() {
    return footerSection.isDisplayed();
  }

  public String getCreateTrainingCenterButtonText() {
    return createTrainingCenterButton.getText();
  }

  public TrainingCenterManagementScreen selectCityFromDDL(int dropDownSelectIndex) {
    selectionCityDDL.getElements().get(dropDownSelectIndex).click();
    return this;
  }

  public String getCountryAreaText() {
    return countrySelect.getText();
  }

  public String getCityAreaText() {
    return citySelect.getText();
  }

  public boolean isCountryAndCityListsDisplayed() {
    return countryAndCityRow.isAllElementsDisplayed();
  }

  public TrainingCenterManagementScreen clickTrainingCenterMenuButton(String cityOfCenter) {
    Element.byXpath(THREE_DOTS_LOCATOR_PATTERN, cityOfCenter).click();
    return this;
  }

  public CenterScreen selectTheTrainingCenterButtonDropdownMenu(String element) {
    Element.byXpath(THREE_DOTS_MENU_ELEMENT_LOCATOR_PATTERN, element).click();
    return new CenterScreen();
  }

  public CreateOrEditTrainingCenterScreen selectEditTheTrainingCenterButtonDropdownMenu() {
    Element.byXpath(THREE_DOTS_MENU_ELEMENT_LOCATOR_PATTERN,
        LocaleProperties.getValueOf(NEWS_MANAGEMENT_ELLIPSIS_ACTION_MENU_EDIT)).click();
    return new CreateOrEditTrainingCenterScreen();
  }

  public TrainingCenterManagementScreen waitListOfActionForTrainingCenterMenuButtonForVisibility() {
    threeDotsListOfAction.waitForVisibility();
    return this;
  }

  public boolean isListOfActionsForTrainingCenterContextMenuButtonDisplayed() {
    return threeDotsListOfAction.isDisplayed();
  }

  public TrainingCenterManagementScreen clickEditMenuButton() {
    threeDotsButton.click();
    return this;
  }

  public CreateOrEditTrainingCenterScreen clickEditTrainingCenterAction() {
    editTrainingCenterAction.click();
    return new CreateOrEditTrainingCenterScreen();
  }

  public boolean isConfirmationPopUpDisplayed() {
    return confirmationPopUp.isDisplayed();
  }

  public TrainingCenterManagementScreen waitSuccessfulNotificationPopUpInvisibility() {
    successfulNotificationPopUp.waitForInvisibility();
    return this;
  }

  public String getStatusOfCenterText(String center) {
    return Element.byXpath(STATUS_OF_CENTER_LOCATOR_PATTERN, center).getText();
  }

  public CenterScreen clickViewTrainingCenterAction() {
    viewTrainingCenterAction.waitForClickableAndClick();
    return new CenterScreen();
  }

  public TrainingCenterManagementScreen selectCityFromDDLByName(String cityName) {
    Element element = Element.byXpath(CITY_DDL_BY_TEXT_LOCATOR, cityName);
    element.mouseOver();
    element.click();
    element.enter();
    return this;
  }
}
