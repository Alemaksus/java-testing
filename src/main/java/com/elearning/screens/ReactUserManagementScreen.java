package com.epmrdpt.screens;

import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactUserManagementScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String CONTAIN_TEXT_LOCATOR_PATTERN = "//*[contains(text(),'%s')]";
  private static final String DROP_DOWN_FOR_FILTER_PATTERN = "//div[@data-id='%s']//div[contains(@class,'input')]";
  private static final String TEXT_ITEM_BY_SEARCH_RESULT_PATTERN =
      "//*[text()='%s']/ancestor::div[contains(@class,'table-row')]//*[contains(text(),'%s')]";
  private static final String FIRST_USER_PATTERN = "//div[contains(@class,'table')]//a";

  private Element searchInput = Element.byCss("input.uui-input");
  private Element userRolesDropDown = Element.byXpath(DROP_DOWN_FOR_FILTER_PATTERN, "roles-picker");
  private Element countryDropDown = Element.byXpath(DROP_DOWN_FOR_FILTER_PATTERN, "country-picker");
  private Element cityDropDown = Element.byXpath(
      "//div[@data-id='city-picker']/div/div[2]", "city-picker");
  private Element accountTypeDropDown = Element.byXpath(DROP_DOWN_FOR_FILTER_PATTERN, "type-picker");
  private Element searchResultsLabel = Element
      .byXpath("//div[contains(text(),'Elements found') and not(text()='0')]");
  private Element resetButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Reset");
  private Element applyButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Apply");
  private Element createUserButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Create User");
  private Element firstUserButton = Element.byXpath(FIRST_USER_PATTERN);
  private Element firstUserFirstName = Element.byXpath(FIRST_USER_PATTERN + "//following::div[2]");
  private Element firstUserRole = Element.byXpath(FIRST_USER_PATTERN + "//following::div[6]");
  private Element firstUserEmail = Element.byXpath(FIRST_USER_PATTERN + "//following::div[10]");
  private Element dropDownInput = Element.byXpath("//input[@placeholder='Search']");
  private Element loadSpinner = Element.byXpath("//div[contains(@class,'uui-table-row-container')]"
      + "/following::div//div[contains(@class,'uui-spinner-animation')]");

  @Override
  public boolean isScreenLoaded() {
    return isApplyButtonDisplayed();
  }

  public ReactUserManagementScreen waitScreenLoading() {
    applyButton.waitForVisibility();
    return this;
  }

  public boolean isTextByNameDisplayed(String name) {
    return Element.byXpath(CONTAIN_TEXT_LOCATOR_PATTERN, name).isDisplayed();
  }

  public boolean isSearchBarDisplayed() {
    return searchInput.isDisplayed();
  }

  public boolean isUserRolesDropDownDisplayed() {
    return userRolesDropDown.isDisplayed();
  }

  public boolean isCountryDropDownDisplayed() {
    return countryDropDown.isDisplayed();
  }

  public boolean isCityDropDownDisplayed() {
    return cityDropDown.isDisplayed();
  }

  public boolean isAccountTypeDropDownDisplayed() {
    return accountTypeDropDown.isDisplayed();
  }

  public boolean isResetButtonDisplayed() {
    return resetButton.isDisplayed();
  }

  public boolean isApplyButtonDisplayed() {
    return applyButton.isDisplayed();
  }

  public boolean isCreateUserButtonDisplayed() {
    return createUserButton.isPresent();
  }

  public boolean isSearchResultByNameDisplayed(String name) {
    return Element.byXpath("//div[contains(@class,'uui-table-row')]//div[text()='%s']", name)
        .isDisplayed();
  }

  public boolean isTextInSearchResultByNameDisplayed(String name, String text) {
    return Element.byXpath(TEXT_ITEM_BY_SEARCH_RESULT_PATTERN, name, text).isDisplayed();
  }

  public boolean isPaginationDropDownDisplayed() {
    return Element.byXpath("//div[text()='Show']/../following-sibling::div").isDisplayed();
  }

  public boolean isPaginationButtonsDisplayed() {
    return Element.byXpath("//div[contains(@class,'pages-buttons-container')]").isDisplayed();
  }

  public String getPlaceholderOfSearchInput() {
    return searchInput.getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public ReactUserManagementScreen typeSearchInputField(String name) {
    searchInput.click();
    searchInput.type(name);
    return this;
  }

  public ReactUserManagementScreen waitSearchResultsLabelIsDisplayed() {
    searchResultsLabel.waitForVisibility();
    return this;
  }

  public ReactUserManagementScreen waitLoadSpinnerInvisibility() {
    loadSpinner.waitForDisappear();
    return this;
  }

  public ReactUserManagementScreen clickApplyButton() {
    applyButton.click();
    return this;
  }

  public ReactUserDetailsScreen clickCreateUserButton() {
    createUserButton.click();
    return new ReactUserDetailsScreen();
  }

  public ReactUserManagementScreen waitSearchResultsDisplayedByName(String name) {
    Element.byXpath(CONTAIN_TEXT_LOCATOR_PATTERN, name).waitForVisibility(DEFAULT_TIME_OUT_IN_SECONDS);
    return this;
  }

  public ReactUserDetailsScreen clickFirstUserButton() {
    firstUserButton.click();
    return new ReactUserDetailsScreen();
  }

  public String getFirstUserLastNameText() {
    return firstUserButton.getText();
  }

  public String getFirstUserFirstNameText() {
    return firstUserFirstName.getText();
  }

  public String getFirstUserRoleText() {
    return firstUserRole.getText();
  }

  public String getFirstUserEmailText() {
    return firstUserEmail.getText();
  }

  public ReactUserManagementScreen chooseCountry(String accountType){
    countryDropDown.click();
    dropDownInput.type(accountType);
    Element.byXpath(format(TEXT_LOCATOR_PATTERN, accountType)).waitForPresence().click();
    return this;
  }
}
