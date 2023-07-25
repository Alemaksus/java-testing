package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactUserDetailsScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String ROLE_ITEM_PATTERN = "//div[@class='uui-popper']" + TEXT_LOCATOR_PATTERN;

  private Element userListButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Users list");
  private Element firstNameField = Element.byXpath("//div[@id='firstName']//input");
  private Element lastNameField = Element.byXpath("//div[@id='lastName']//input");
  private Element emailField = Element.byXpath("//div[@id='email']//input");
  private Element addButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Add");
  private Element bucketButton = Element.byXpath("//div[contains(@class,'bucket-icon')]/..");
  private Element roleDropDown = Element.byCss("div.roles .uui-input-box.-clickable");
  private Element roleField = Element.byXpath("//input[contains(@class, 'uui-placeholder')]");
  private Element createButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Create");
  private Element saveChangesButton = Element.byXpath(TEXT_LOCATOR_PATTERN,"Save changes");
  private Element successfulCreationPopUp = Element
      .byXpath(TEXT_LOCATOR_PATTERN, "User has been created.");
  private Element successfulEditionPopUp = Element
      .byXpath(TEXT_LOCATOR_PATTERN, "Changes have been saved.");

  @Override
  public boolean isScreenLoaded() {
    return firstNameField.isDisplayed();
  }

  public ReactUserDetailsScreen waitScreenLoading() {
    firstNameField.waitForVisibility();
    return this;
  }

  public ReactUserManagementScreen clickUserListButton() {
    userListButton.click();
    return new ReactUserManagementScreen();
  }

  public ReactUserDetailsScreen typeFirstNameInputField(String firstName) {
    firstNameField.type(firstName);
    return this;
  }

  public ReactUserDetailsScreen typeLastNameInputField(String lastName) {
    lastNameField.type(lastName);
    return this;
  }

  public ReactUserDetailsScreen typeEmailInputField(String email) {
    emailField.type(email);
    return this;
  }

  public ReactUserDetailsScreen clickAddButton() {
    addButton.click();
    return this;
  }

  public ReactUserDetailsScreen clickBucketButton() {
    bucketButton.click();
    return this;
  }

  public ReactUserDetailsScreen clickRoleDropDown() {
    roleDropDown.click();
    return this;
  }

  public ReactUserDetailsScreen clickRoleItemByName(String name) {
    Element.byXpath(ROLE_ITEM_PATTERN, name).click();
    return this;
  }

  public ReactUserDetailsScreen clickCreateButton() {
    createButton.click();
    return this;
  }

  public ReactUserDetailsScreen clickSaveChangesButton() {
    saveChangesButton.click();
    return this;
  }

  public boolean isSuccessfulUserCreationMessageDisplayed() {
    return successfulCreationPopUp.isDisplayed();
  }

  public boolean isSuccessfulUserEditionMessageDisplayed() {
    return successfulEditionPopUp.isDisplayed();
  }

  public ReactUserDetailsScreen waitSuccessfulPopUp() {
    successfulEditionPopUp.waitForVisibility();
    return this;
  }

  public String getLastNameFieldText() {
    return lastNameField.getAttributeValue("value");
  }

  public String getFirstNameFieldText() {
    return firstNameField.getAttributeValue("value");
  }

  public String getRoleFieldText() {
    return roleField.getAttributeValue("placeholder");
  }

  public String getEmailFieldText() {
    return emailField.getAttributeValue("value");
  }
}
