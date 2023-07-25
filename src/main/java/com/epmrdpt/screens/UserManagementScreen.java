package com.epmrdpt.screens;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class UserManagementScreen extends AbstractScreen {

  private static final String SEARCH_RESULTS_BY_NAME_PATTERN =
      "//div[@class='name-container']/a[contains(text(),'%s')]";

  Element createUser = Element.byXpath("//a[@ui-sref='users.create']");
  Element mergeUser = Element.byXpath("//a[@ui-sref='users.merge']");
  Element searchInput = Element.byName("SearchName");
  Element userRolesDropDown = Element.byXpath("//div[contains(@class,'role')]");
  Element userPermissionDropDown = Element.byXpath("//div[contains(@class,'permission')]");
  Element countryDropDown = Element.byXpath("//div[contains(@class,'country')]");
  Element cityDropDown = Element.byXpath("//div[contains(@class,'city')]");
  Element skillDropDown = Element.byXpath("//div[contains(@class,'skill')]");
  Element graduationYearDropDown = Element.byXpath("//div[contains(@class,'graduation')]");
  Element accountTypeDropDown = Element.byXpath("//div[contains(@class,'account-type')]");
  Element applyButton = Element.byXpath(
      String.format("//button[text()='%s']", LocaleProperties.getValueOf(
          LocalePropertyConst.USER_MANAGEMENT_APPLY)));
  Element resetButton = Element.byXpath(
      String.format("//button[text()='%s']", LocaleProperties.getValueOf(
          LocalePropertyConst.USER_MANAGEMENT_RESET)));
  Element fullNameColumnName = Element.byXpath(
      String.format("//th[text()[contains(.,\"%s\")]]", LocaleProperties.getValueOf(
          LocalePropertyConst.USER_MANAGEMENT_FULL_NAME)));
  Element rolesColumnName = Element.byXpath(
      String.format("//th[text()='%s']", LocaleProperties.getValueOf(
          LocalePropertyConst.USER_MANAGEMENT_ROLES)));
  Element accountTypeColumnName = Element.byXpath(
      String.format("//th[text()='%s']", LocaleProperties.getValueOf(
          LocalePropertyConst.USER_MANAGEMENT_ACCOUNT_TYPE)));
  Element fullName = Element.byXpath("//a[contains(@class,'link-name')]");
  Element roles = Element.byXpath("//td[contains(@class,'col-roles')]");
  Element accountType = Element.byXpath("//td[contains(@class,'col-account-type')]");
  Element profileIcon = Element.byXpath(
      String.format("//i[@title='%s']", LocaleProperties.getValueOf(
          LocalePropertyConst.USER_MANAGEMENT_PROFILE)));
  Element blockIcon = Element.byXpath(String.format("//i[@title='%s']", LocaleProperties.getValueOf(
      LocalePropertyConst.USER_MANAGEMENT_BLOCK)));
  Element editIcon = Element.byXpath(String.format("//i[@title='%s']", LocaleProperties.getValueOf(
      LocalePropertyConst.USER_MANAGEMENT_EDIT)));
  Element usersCountTextInPagination = Element.byXpath("//li[contains(@class,'pagin-text')]");
  Element eightUsersPerPage = Element.byXpath("//a[text()='8']");
  Element sixteenUsersPerPage = Element.byXpath("//a[text()='16']");
  Element thirtyTwoUsersPerPage = Element.byXpath("//a[text()='32']");
  Element showAllUsers = Element.byXpath("//a[contains(@ng-click,'pagin.count')]");
  Element previousPage = Element.byXpath("//a[@ng-click='setPage(pagin.current-1)']");
  Element nextPage = Element.byXpath("//a[@ng-click='setPage(pagin.current+1)']");
  private Element createUserButton = Element.byCss("a[href$='Create']");
  private Element searchInputField = Element.byCss("input[name|='SearchName']");
  private Element searchResultsByName = Element.byCss("div.photo~div.name-container>a");
  private Element searchResultsByEmail = Element.byCss("div.photo~div.name-container>span");
  private Element userEmail = Element.byCss("div.name-container span");
  private Element profileMenuButton = Element.byCss("li.uui-profile-menu");
  private Element logOutButton = Element.byCss("a[href*=logout]");

  @Override
  public boolean isScreenLoaded() {
    return isCreateUserDisplayed();
  }

  public UserManagementScreen waitScreenLoading() {
    createUser.waitForVisibility();
    return this;
  }

  public boolean isCreateUserDisplayed() {
    return createUser.isDisplayed();
  }

  public boolean isMergeUserDisplayed() {
    return mergeUser.isDisplayed();
  }

  public boolean isSearchInputDisplayed() {
    return searchInput.isDisplayed();
  }

  public String getTextFromSearchInput() {
    return searchInput.getAttributeValue("value");
  }

  public boolean isUserRolesDropDownDisplayed() {
    return userRolesDropDown.isDisplayed();
  }

  public boolean isUserPermissionsDropDownDisplayed() {
    return userPermissionDropDown.isDisplayed();
  }

  public boolean isCountryDropDownDisplayed() {
    return countryDropDown.isDisplayed();
  }

  public boolean isCityDropDownDisplayed() {
    return cityDropDown.isDisplayed();
  }

  public boolean isSkillDropDownDisplayed() {
    return skillDropDown.isDisplayed();
  }

  public boolean isGraduationYearDisplayed() {
    return graduationYearDropDown.isDisplayed();
  }

  public boolean isAccountTypeDropDownDisplayed() {
    return accountTypeDropDown.isDisplayed();
  }

  public boolean isApplyButtonDisplayed() {
    return applyButton.isDisplayed();
  }

  public boolean isResetButtonDisplayed() {
    return resetButton.isDisplayed();
  }

  public boolean isFullNameColumnHeaderDisplayed() {
    return fullNameColumnName.isDisplayed();
  }

  public boolean isRolesColumnHeaderDisplayed() {
    return rolesColumnName.isDisplayed();
  }

  public boolean isAccountTypeColumnHeaderDisplayed() {
    return accountTypeColumnName.isDisplayed();
  }

  public boolean isFullNamesDisplayed() {
    return fullName.isDisplayed();
  }

  public boolean isRolesDisplayed() {
    return roles.isDisplayed();
  }

  public boolean isAccountTypesDisplayed() {
    return accountType.isDisplayed();
  }

  public boolean isProfileIconDisplayed() {
    return profileIcon.isDisplayed();
  }

  public boolean isBlockIconDisplayed() {
    return blockIcon.isDisplayed();
  }

  public boolean isEditIconDisplayed() {
    return editIcon.isDisplayed();
  }

  public boolean isUsersCountTextOfPaginationDisplayed() {
    return usersCountTextInPagination.isDisplayed();
  }

  public boolean isEightUsersPerPageButtonDisplayed() {
    return eightUsersPerPage.isDisplayed();
  }

  public boolean isSixteenUsersPerPageButtonDisplayed() {
    return sixteenUsersPerPage.isDisplayed();
  }

  public boolean isThirtyTwoUsersButtonDisplayed() {
    return thirtyTwoUsersPerPage.isDisplayed();
  }

  public boolean isShowAllUsersButtonDisplayed() {
    return showAllUsers.isDisplayed();
  }

  public boolean isPreviousPageButtonDisplayed() {
    return previousPage.isDisplayed();
  }

  public boolean isNextPageButtonDisplayed() {
    return nextPage.isDisplayed();
  }

  public CreateUserScreen clickCreateUserButton() {
    createUserButton.clickJs();
    return new CreateUserScreen();
  }

  public UserManagementScreen typeSearchInputField(String name) {
    searchInputField.type(name);
    return this;
  }

  public UserManagementScreen clickApplyButton() {
    applyButton.click();
    return this;
  }

  public UserManagementScreen waitSearchResultsLoading() {
    searchResultsByEmail.waitForVisibility();
    return this;
  }

  public UserManagementScreen waitSearchResultsByNameLoading(String name) {
    Element.byXpath(SEARCH_RESULTS_BY_NAME_PATTERN, name).waitForVisibility();
    return this;
  }

  public boolean isSearchResultByNameDisplayed() {
    return searchResultsByName.isDisplayed();
  }

  public boolean isUserEmailDisplayed() {
    return userEmail.isDisplayed();
  }

  public UserManagementScreen clickProfileMenuButton() {
    profileMenuButton.click();
    return this;
  }

  public HeaderScreen clickSignOutButton() {
    logOutButton.clickJs();
    return new HeaderScreen();
  }
}
