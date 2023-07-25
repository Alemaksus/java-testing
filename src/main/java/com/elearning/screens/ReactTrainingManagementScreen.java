package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ReactTrainingManagementScreen extends AbstractScreen {

  private static final int MAX_ITERATION_COUNT = 5;

  private static final String TRAINING_STATUS_ELEMENTS_PATTERN = "//div[contains(@class, 'popper')]//div[text()='%s']/ancestor::div[6]";
  private static final String TRAINING_NAME_LOCATOR_PATTERN = "//a/div[text()='%s']";
  private static final String CREATE_NEW_BUTTON_LOCATOR_PATTERN = "//div[text()='%s']/parent::a";
  private static final String DROP_DOWN_OPTION_BY_TEXT_LOCATOR_PATTERN =
      "//div[@class='uui-popper']//div[text()='%s']";
  private static final String TRAINING_TYPE_NAME_LOCATOR_PATTERN =
      "//*[@class='uui-popper']//div[text()='%s']//ancestor::div[6]/div";
  private static final String FILTER_BLOCK_LABEL_BY_NAME_PATTERN = "//div[text()='%s']";
  private static final String FILTER_BLOCK_FILTER_BY_NAME_PATTERN =
      FILTER_BLOCK_LABEL_BY_NAME_PATTERN + "/ancestor::*[@data-id]//div[contains(@class, 'uui-placeholder')]";
  private static final String BUTTON_BY_NAME_PATTERN = "//*[contains(@class, 'uui-button-box') and descendant::div[text()='%s']]";
  private static final String PAGINATION_CONTAINER_PATTERN =
      "//div[contains(@class,'uui-table-header-row')]/following-sibling::div[last()]";
  private static final String COLUMN_HEADERS_PATTERN = "//div[@data-id='training-header-%s']";
  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";
  private final static String CONTAINS_TEXT_LOCATOR_PATTERN = "//*[contains(text(),\"%s\")]";
  private final static String CHECK_BOX_LOCATOR_PATTERN =
      CONTAINS_TEXT_LOCATOR_PATTERN + "/preceding-sibling::div";
  private final static String SBR_BY_TRAINING_NAME_PATTERN = CONTAINS_TEXT_LOCATOR_PATTERN
      + "/ancestor::div[contains(@class,'table-row')]//div[@data-id='sbr-container']";

  private Element filterElementsRowExceptAdvancedFiltersRow = Element.
      byXpath("//form/div/div[2]/div[position()!=3]");
  private Element preloadedTrainingManagerPicture = Element.byXpath(
      "//div[contains(@class, 'uui-spinner-animation')]");
  private Element searchByTrainingNameFiled = Element.byXpath(
      "//div[@id='groups-search-bar']//input[contains(@class, 'input')]");
  private Element creatorCheckBox = Element.byXpath(CHECK_BOX_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_CREATOR_CHECKBOX));
  private Element ownerCheckBox = Element.byXpath(CHECK_BOX_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_OWNER_CHECKBOX));
  private Element searchInputClearButton = Element.byXpath(
      "//div[@id='groups-search-bar']//div/div[3]/div");
  private Element cancelFilterByDateButton = Element.byXpath(
      "//*[local-name()='svg']/*[contains(@*,'btn-cross-18')]");
  private Element searchResultsLabel = Element.byXpath(
      String.format("//form//div[4]/div[text() and not(text()='0')]"));
  private Element applyButton = Element.byXpath(
      "//div[contains(@class, 'button') and child::div[text()='%s']]",
      getValueOf(REACT_TRAINING_MANAGEMENT_APPLY_BUTTON));
  private Element trainingName = Element.byXpath("//div[@data-id='training-table-name']//a/div");
  private Element spinnerContainer = Element.byXpath(
      "//div[@data-id='training-table']/../following-sibling::div/div[contains(@class, 'uui-spinner-container')]");
  private Element countryDropDownElement = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE));
  private Element countryDropDownInput = Element.byXpath("//input[@placeholder='%s']",
      getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_INPUT_DEFAULT_VALUE));
  private Element resetButton = Element.byXpath("//div[contains(text(), '%s')]/..",
      getValueOf(REACT_TRAINING_MANAGEMENT_RESET_BUTTON));
  private Element searchTrainingsNotAvailable = Element
      .byXpath("//*[contains(text(),'%s')]",
          getValueOf(REACT_TRAINING_MANAGEMENT_SEARCH_TRAINING_NOT_AVAILABLE));
  private Element searchResultsTableNames = Element.byXpath("//*[@data-id='training-table-name']");
  private Element trainingTypeDropDown = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_TYPE_DROPDOWN_DEFAULT_VALUE));
  private Element searchResultsTableType = Element.
      byXpath("//*[@data-id='training-table-type']/div/div");
  private Element searchResultsTableCurrentStatus = Element.
      byXpath("//div[@data-id='training-table-status']/div/div");
  private Element searchResultsTableLocations = Element.
      byXpath("//*[@data-id='training-table-location']/div/div");
  private Element toExcelButton = Element
      .byXpath("//div[contains(@class,'uui-button-box')]//div[contains(text(),'Excel')]");
  private Element advancedSearchButton = Element.byXpath(
      "//div[contains(@id, 'groups-search-bar')]/div[2]//div[contains(@class,'caption')]");
  private Element currentStatusDropDown = Element.byXpath(
      "//div[contains(text(),'%s')]/ancestor::div[@data-id]//div[contains(@class, 'uui-input-box')]",
      getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_LABEL));
  private Element trainingTableName = Element.byXpath("//div[@data-id='training-table-name']");
  private Element planningTitle = Element.byXpath("//h2[text()='%s']",
      getValueOf(TRAINING_MANAGEMENT_PLANNING));
  private Element searchIcon = Element.byXpath(
      "//div[@id='groups-search-bar']//div[contains(@class,'input')]/preceding-sibling::div");
  private Element clearSearchInputButton = Element
      .byXpath(
          "//div[@id='groups-search-bar']//div[contains(@class,'input')]/following-sibling::div");
  private Element exactMatchSwitch = Element.byXpath(
      "//div[contains(@class,'uui-switch-body') and following-sibling::div[text()='%s']]",
      getValueOf(REACT_TRAINING_MANAGEMENT_EXACT_MATCH_LABEL));
  private Element dateFromFilter = Element
      .byXpath(FILTER_BLOCK_LABEL_BY_NAME_PATTERN + "/../div[2]//input[@placeholder='%s:']",
          getValueOf(REACT_TRAINING_MANAGEMENT_DATE_LABEL),
          getValueOf(CALENDAR_FROM));
  private Element dateToFilter = Element
      .byXpath(FILTER_BLOCK_LABEL_BY_NAME_PATTERN + "/../div[2]//input[@placeholder='%s:']",
          getValueOf(REACT_TRAINING_MANAGEMENT_DATE_LABEL),
          getValueOf(CALENDAR_TO));
  private Element trainingTableHeaderContainer = Element.byCss(".uui-table-header-row");
  private Element trainingTableContainer = Element.byCss("div.uui-table-header-row+div");
  private Element paginationContainer = Element
      .byXpath(PAGINATION_CONTAINER_PATTERN);
  private Element showLabelOfPaginationBlock = Element.byXpath(
      PAGINATION_CONTAINER_PATTERN + FILTER_BLOCK_LABEL_BY_NAME_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_PAGINATION_SHOW_LABEL));
  private Element elementsPerPageDropDown = Element.byXpath(
      PAGINATION_CONTAINER_PATTERN + "//div[contains(@class,'input-box')]");
  private Element totalLabelOfPaginationBlock = Element.byXpath(
      PAGINATION_CONTAINER_PATTERN + "//div[contains(text(),'%s')]",
      getValueOf(REACT_TRAINING_MANAGEMENT_PAGINATION_TOTAL_LABEL));
  private Element paginationButtons = Element.byXpath(
      PAGINATION_CONTAINER_PATTERN + "//div[contains(@class,'button')]");
  private Element paginationMaxElementsPerPage =
      Element.byXpath("//div[@class='uui-popper']//div[contains(@class,'clickable')][last()]");
  private Element cityDropDown = Element.byXpath(TEXT_LOCATOR_PATTERN,
         getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE));
  private Element trainingDurationDropDown = Element.byXpath("//input[@placeholder = '%s']",
          getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_PLACEHOLDER));
  private Element trainingFormatDropDown = Element
      .byXpath(TEXT_LOCATOR_PATTERN,
          getValueOf(REACT_TRAINING_MANAGEMENT_FORMAT_DROPDOWN_DEFAULT_VALUE));
  private Element searchResultsTableDates = Element.byXpath("//*[@data-id='training-table-dates']");
  private Element child = Element.byXpath("//div[@class='uui-tooltip-body']");
  private Element searchResultsTableFormat = Element
      .byXpath("//*[@data-id='training-table-format']/div");
  private Element elementsFoundTitle = Element
      .byXpath(String.format(CONTAINS_TEXT_LOCATOR_PATTERN, getValueOf(PARTICIPANTS_ELEMENTS_FOUND_LABEL)));
  private Element sbrColumnHeader = Element
      .byXpath("//div[@data-id='training-header-waiting-list']//div[text()]");
  private Element subscribersListButton = Element
      .byXpath(CONTAINS_TEXT_LOCATOR_PATTERN, getValueOf(REACT_TRAINING_MANAGEMENT_SUBSCRIBERS_LIST_BUTTON));
  private Element placeholderDurationOfTraining = Element.byXpath("//div[@data-id]//input");
  private Element placeholderCity = Element.byXpath("//div[@display]//span");
  private Element showOnlySelectedButton = Element.byCss(".uui-switch-body");
  private final Element trainingParticipants = Element.byXpath("//*[@data-id='training-table-pcs']");
  private final Element trainingSubscribers = Element.byXpath("//*[@data-id='sbr-container']");

  @Override
  public boolean isScreenLoaded() {
    return filterElementsRowExceptAdvancedFiltersRow.isDisplayed();
  }

  private List<WebElement> getListElementsOfTrainingTableRows() {
    return webDriver.findElements(By.xpath(
        "//*[contains(@class, 'uui-table-row') and not(contains(@class, 'scroll') or contains(@class, 'header'))]"));
  }

  public ReactTrainingManagementScreen waitFilterBlockDisplayed() {
    filterElementsRowExceptAdvancedFiltersRow.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen clickTrainingNameByName(String trainingName) {
    Element.byXpath(String.format(TRAINING_NAME_LOCATOR_PATTERN, trainingName))
        .waitForClickableAndClick();
    return new ReactDetailTrainingScreen();
  }

  public ReactTrainingManagementScreen waitTrainingScreenIsLoaded() {
    applyButton.waitForVisibility();
    waitFilterBlockDisplayed();
    waitAllSpinnersAreNotDisplayed();
    return this;
  }

  public ReactTrainingManagementScreen waitPreloadingPictureHidden() {
    preloadedTrainingManagerPicture.waitForInvisibilityWithPageLoadTimeout();
    return this;
  }

  public ReactTrainingManagementScreen typeTrainingName(String trainingName) {
    searchByTrainingNameFiled.clearInputUsingBackspace();
    searchByTrainingNameFiled.type(trainingName);
    searchInputClearButton.waitForVisibility();
    return this;
  }

  public boolean isCreatorCheckboxDisplayed() {
    return creatorCheckBox.isDisplayed();
  }

  public boolean isOwnerCheckboxDisplayed() {
    return ownerCheckBox.isDisplayed();
  }

  public ReactTrainingManagementScreen clickCreatorCheckBox() {
    creatorCheckBox.click();
    return this;
  }

  public ReactTrainingManagementScreen clickOwnerCheckBox() {
    creatorCheckBox.click();
    return this;
  }

  public ReactTrainingManagementScreen uncheckCreatorCheckboxIfNeeded() {
    if (creatorCheckBox.isCheckBoxChecked()) {
      return clickCreatorCheckBox();
    }
    return this;
  }

  public ReactTrainingManagementScreen uncheckOwnerCheckboxIfNeeded() {
    if (ownerCheckBox.isCheckBoxChecked()) {
      return clickOwnerCheckBox();
    }
    return this;
  }

  public ReactTrainingManagementScreen clickCancelFilterByDateButtonIfNeeded() {
    if (cancelFilterByDateButton.isPresent()) {
      cancelFilterByDateButton.click();
    }
    return this;
  }

  public ReactTrainingManagementScreen waitSearchResultsLabelIsDisplayed() {
    searchResultsLabel.waitForVisibility();
    return this;
  }

  public ReactTrainingManagementScreen clickMaxElementsPerPageInDropDownIfDisplayedNoWait() {
    if (elementsPerPageDropDown.isDisplayedNoWait()) {
      elementsPerPageDropDown.click();
      paginationMaxElementsPerPage.waitForClickableAndClick();
    }
    return this;
  }

  public ReactTrainingManagementScreen clickApplyButton() {
    applyButton.waitForElementClassEnabled();
    applyButton.clickJs();
    return this;
  }

  public ReactTrainingManagementScreen clickSearchInputClearButton() {
    clearSearchInputButton.waitForClickableAndClick();
    return this;
  }

  public ReactTrainingManagementScreen waitAllTrainingNameDisplayed() {
    trainingName.waitUntilAllElementsLocatedByAreVisible(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    waitAllSpinnersAreNotDisplayed();
    return this;
  }

  public ReactTrainingManagementScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitForDisappear();
    return this;
  }

  public ReactTrainingManagementScreen clickCityNameDropdown() {
    cityDropDown.click();
    return this;
  }

  public ReactTrainingManagementScreen clickResetButton() {
    resetButton.click();
    return this;
  }

  public List<String> getTrainingNamesFromTrainingList() {
    waitAllSpinnersAreNotDisplayed();
    return searchResultsTableNames.getElementsText();
  }

  public ReactTrainingManagementScreen clickTrainingCountryDropDownByCountryName(
      String countryName) {
    Element.byXpath(DROP_DOWN_OPTION_BY_TEXT_LOCATOR_PATTERN, countryName).click();
    waitAllSpinnersAreNotDisplayed();
    return this;
  }

  public ReactTrainingManagementScreen clickTrainingCityDropDownByCityName(String cityName) {
    clickCityNameDropdown();
    Element.byXpath(TEXT_LOCATOR_PATTERN, cityName).click();
    return this;
  }

  public ReactTrainingManagementScreen clickTrainingTypeDropDown() {
    trainingTypeDropDown.click();
    return this;
  }

  public ReactTrainingManagementScreen clickTrainingTypeDropDownTypeName(String typeName) {
    clickTrainingTypeDropDown();
    Element.byXpath(DROP_DOWN_OPTION_BY_TEXT_LOCATOR_PATTERN, typeName).click();
    return this;
  }

  public ReactTrainingManagementScreen uncheckCheckBoxes() {
    uncheckCreatorCheckboxIfNeeded();
    uncheckOwnerCheckboxIfNeeded();
    return this;
  }

  public boolean areAllTrainingsHaveCorrectTypeName(String trainingType) {
    return searchResultsTableType
        .getElements()
        .stream()
        .allMatch(type -> type.getText().equals(trainingType));
  }

  public boolean areAllTrainingsHaveCorrectStatus(String trainingStatus) {
    return searchResultsTableCurrentStatus
        .getElements()
        .stream()
        .allMatch(status -> status.getText().equals(trainingStatus));
  }

  public boolean areAllTrainingsHaveCorrectLocation(String trainingCountryLocation,
      String trainingCityLocation) {
    List<Element> trainingLocations = searchResultsTableLocations.getElements();
    int numberOfCorrespondingResults = 0;
    for (Element element : trainingLocations) {
      if (isCorrectLocationDisplayedForTraining(element, trainingCountryLocation,
          trainingCityLocation) ||
          isCorrectLocationDisplayedOnToolTip(element, trainingCountryLocation,
              trainingCityLocation)) {
        numberOfCorrespondingResults++;
      }
    }
    return numberOfCorrespondingResults == trainingLocations.size();
  }

  private boolean isCorrectLocationDisplayedForTraining(Element element,
      String trainingCountryLocation, String trainingCityLocation) {
    return element.getText().equals(trainingCountryLocation + ": " + trainingCityLocation);
  }

  private boolean isCorrectLocationDisplayedOnToolTip(Element element,
      String trainingCountryLocation, String trainingCityLocation) {
    if (element.getText()
        .equals(getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_LOCATION_MULTI_LOCATION))) {
      String tooltipText = element.getTooltipText(child);
      return tooltipText.contains(trainingCountryLocation + ":") && tooltipText
          .contains(trainingCityLocation);
    }
    return false;
  }

  public boolean areAllTrainingsHaveCorrectDates(String trainingDate) {
    return searchResultsTableDates
        .getElements()
        .stream()
        .allMatch(date -> date.getText().endsWith(trainingDate.toLowerCase(Locale.ROOT)));
  }

  public boolean areAllTrainingsHaveCorrectFormat(String trainingFormat) {
    return searchResultsTableFormat
        .getElements()
        .stream().allMatch(format -> format.getTooltipText(child).equals(trainingFormat));
  }

  public List<String> getLocationsFromTrainingsList() {
    return searchResultsTableLocations
        .getElementsText();
  }

  public ReactCreateTrainingScreen clickCreateNewButton() {
    Element.byXpath(CREATE_NEW_BUTTON_LOCATOR_PATTERN, getValueOf(TRAINING_MANAGEMENT_CREATE_NEW))
        .waitForClickableAndClick();
    return new ReactCreateTrainingScreen();
  }

  public ReactTrainingManagementScreen clickToExcelButton() {
    toExcelButton.click();
    return this;
  }

  public ReactTrainingManagementScreen clickAdvancedSearchButtonIfNeeded() {
    if (!currentStatusDropDown.isDisplayed()) {
      clickAdvancedSearchButton();
    }
    return this;
  }

  public ReactTrainingManagementScreen clickAdvancedSearchButton() {
    advancedSearchButton.mouseOver();
    advancedSearchButton.click();
    return this;
  }

  public ReactTrainingManagementScreen clickCurrentStatusDropdownInSearchFilter() {
    currentStatusDropDown.click();
    return this;
  }

  public ReactTrainingManagementScreen selectTrainingStatusByName(String trainingStatusValue) {
    Element.byXpath(TRAINING_STATUS_ELEMENTS_PATTERN,
        trainingStatusValue)
        .click();
    return this;
  }

  public String getTrainingStatusFromSearchFilterText() {
    return currentStatusDropDown.getAttributeValue(TEXT_CONTENT_CSS_PROPERTY);
  }

  public String getFirstTrainingTableNameText() {
    return trainingTableName.getText();
  }

  public boolean isPlanningTitleDisplayed() {
    return planningTitle.isDisplayed();
  }

  public String getPlanningTitleElementText() {
    return planningTitle.getText();
  }

  public boolean isSearchingByTrainingFieldDisplayed() {
    return searchByTrainingNameFiled.isDisplayed();
  }

  public boolean isSearchIconDisplayed() {
    return searchIcon.isDisplayed();
  }

  public boolean isClearSearchInputButtonDisplayed() {
    return clearSearchInputButton.isDisplayed();
  }

  public boolean isLabelOnFilterBlockByNameDisplayed(String name) {
    return Element.byXpath(FILTER_BLOCK_LABEL_BY_NAME_PATTERN, name).isDisplayed();
  }

  public boolean isDropDownOnFilterBlockByNameDisplayed(String name) {
    if(name.equals(getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_LABEL))) {
      return placeholderDurationOfTraining.isDisplayed();
    }
    if(name.equals(getValueOf(REACT_TRAINING_MANAGEMENT_CITY_LABEL))) {
      return placeholderCity.isDisplayed();
    }
    else
      return Element.byXpath(FILTER_BLOCK_FILTER_BY_NAME_PATTERN, name).isDisplayed();
  }

  public String getValueFromDropdownFilterByName(String name) {
    if(name.equals(getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_LABEL))) {
        return placeholderDurationOfTraining.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
    }
    if(name.equals(getValueOf(REACT_TRAINING_MANAGEMENT_CITY_LABEL))) {
      return placeholderCity.getText();
    }
    else
        return Element.byXpath(FILTER_BLOCK_FILTER_BY_NAME_PATTERN, name).getText();
  }

  public boolean isExactMatchSwitchDisplayed() {
    return exactMatchSwitch.isDisplayed();
  }

  public boolean isDateFromFilterDisplayed() {
    return dateFromFilter.isDisplayed();
  }

  public boolean isDateToFilterDisplayed() {
    return dateToFilter.isDisplayed();
  }

  public boolean isButtonByNameDisplayed(String buttonName) {
    return Element.byXpath(BUTTON_BY_NAME_PATTERN, buttonName).isDisplayed();
  }

  public boolean isTrainingTableHeaderContainerDisplayed() {
    return trainingTableHeaderContainer.isDisplayed();
  }

  public boolean isTrainingTableContainerDisplayed() {
    return trainingTableContainer.isDisplayed();
  }

  public boolean isColumnHeaderDisplayed(ColumnHeaders header) {
    return Element.byXpath(COLUMN_HEADERS_PATTERN, header.getName()).isDisplayed();
  }

  public enum ColumnHeaders {
    NAME("name"), TYPE("type"), LOCATION("location"),
    DATES("dates"), PCS("pcs"), STATUS("status");

    private String name;

    ColumnHeaders(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  public boolean isPaginationContainerDisplayed() {
    return paginationContainer.isDisplayed();
  }

  public boolean isShowLabelOfPaginationBlockDisplayed() {
    return showLabelOfPaginationBlock.isDisplayed();
  }

  public boolean isElementsPerPageDropDownDisplayed() {
    return elementsPerPageDropDown.isDisplayed();
  }

  public boolean isTotalLabelOfPaginationBlockDisplayed() {
    return totalLabelOfPaginationBlock.isDisplayed();
  }

  public boolean isPaginationButtonsDisplayed() {
    return paginationButtons.isAllElementsDisplayed();
  }

  public int getTrainingSearchResultsCount() {
    return getListElementsOfTrainingTableRows().size();
  }

  public ReactDetailTrainingScreen clickTrainingNameByIndex(int index) {
    trainingName.getElements().get(index).click();
    return new ReactDetailTrainingScreen();
  }

  public boolean isSearchTrainingsNotAvailableDisplayedNoWait() {
    return searchTrainingsNotAvailable.isDisplayedNoWait();
  }

  public ReactTrainingManagementScreen clickTrainingDurationDropdown() {
    trainingDurationDropDown.click();
    return this;
  }

  public ReactTrainingManagementScreen clickTrainingDurationDropDownTypeName(String durationName) {
    clickTrainingDurationDropdown();
    Element.byXpath(DROP_DOWN_OPTION_BY_TEXT_LOCATOR_PATTERN, durationName).click();
    return this;
  }

  public ReactTrainingManagementScreen clickTrainingFormatDropdown() {
    trainingFormatDropDown.click();
    return this;
  }

  public ReactTrainingManagementScreen clickTrainingFormatDropDownFormatName(String formatName) {
    clickTrainingFormatDropdown();
    Element.byXpath(DROP_DOWN_OPTION_BY_TEXT_LOCATOR_PATTERN, formatName).click();
    return this;
  }

  public ReactTrainingManagementScreen clickCurrentStatusDropDownCurrentStatusName(
      String currentStatusName) {
    clickCurrentStatusDropdownInSearchFilter();
    Element.byXpath(DROP_DOWN_OPTION_BY_TEXT_LOCATOR_PATTERN, currentStatusName).click();
    return this;
  }

  public ReactTrainingManagementScreen clickExactMatchSwitch() {
    exactMatchSwitch.click();
    return this;
  }

  public String getDropDownDefaultValue(Element dropdown) {
    return dropdown.getAttributeValue("placeholder");
  }

  public boolean areAllSearchToolsReset() {
    return countryDropDownElement.getText()
                .equals(getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE)) &&
           cityDropDown.getText()
                .equals(getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE)) &&
           trainingTypeDropDown.getText()
                .equals(getValueOf(REACT_TRAINING_MANAGEMENT_TYPE_DROPDOWN_DEFAULT_VALUE)) &&
           getDropDownDefaultValue(trainingDurationDropDown)
                .equals(getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_PLACEHOLDER)) &&
           trainingFormatDropDown.getText()
                .equals(getValueOf(REACT_TRAINING_MANAGEMENT_FORMAT_DROPDOWN_DEFAULT_VALUE)) &&
           currentStatusDropDown.getText()
                .equals(getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE))
           && isCheckboxesUnchecked() && isExactMatchSwitchUnchecked();
  }

  public boolean isCheckboxesUnchecked() {
    return !(ownerCheckBox.isCheckBoxChecked() && creatorCheckBox.isCheckBoxChecked());
  }

  public boolean isExactMatchSwitchUnchecked() {
    return !exactMatchSwitch.isCheckBoxChecked();
  }

  public String getAdvancedSearchButtonText() {
    return advancedSearchButton.getText();
  }

  public boolean isElementsFoundTitleDisplayed() {
    return elementsFoundTitle.isDisplayed();
  }

  public boolean isSbrColumnHeaderDisplayed() {
    return sbrColumnHeader.isDisplayed();
  }

  public ReactTrainingManagementScreen clickSBRColumnByTrainingName(String trainingName) {
    Element.byXpath(SBR_BY_TRAINING_NAME_PATTERN, trainingName).click();
    return this;
  }

  public ReactSubscribersExportPopUpScreen clickSubscribersListButton() {
    subscribersListButton.click();
    return new ReactSubscribersExportPopUpScreen();
  }

  public ReactTrainingManagementScreen fillCountryDropDownInput(String country) {
    countryDropDownInput.type(country);
    return this;
  }

  public ReactTrainingManagementScreen clickTrainingCountryDropDown() {
    countryDropDownElement.click();
    return this;
  }

  public ReactTrainingManagementScreen clickShowOnlySelectedButton() {
    showOnlySelectedButton.click();
    return this;
  }

  public String getTrainingSubscribersText() {
    return trainingSubscribers.getText();
  }

  public String getTrainingParticipantsText() {
    return trainingParticipants.getText();
  }
}
