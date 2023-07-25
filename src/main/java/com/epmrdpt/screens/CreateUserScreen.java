package com.epmrdpt.screens;

import static java.lang.String.format;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class CreateUserScreen extends AbstractScreen {

  private static final String USER_ADDRESS_DROP_DOWN_ITEM_LOCATOR = "div#userRoleAddress_chzn>a.chzn-single~div>ul>li:nth-child(%d)";
  private static final String USER_ROLE_DROP_DOWN_ITEM_LOCATOR = "div#Role_chzn>a.chzn-single~div>ul>li:nth-child(%d)";

  private Element addUserBlock = Element.byCss("div#add-user-block");
  private Element emailInputField = Element.byCss("input#Email");
  private Element lastNameInputField = Element.byCss("input#LastName");
  private Element firstNameInputField = Element.byCss("input#FirstName");
  private Element mobileNumberInputField = Element.byCss("input#PhoneMob");
  private Element userRoleDropDown = Element.byCss("div#Role_chzn");
  private Element saveButton = Element.byCss("div.edit-buttons>input");
  private Element addRoleButton = Element.byCss("div[ng-click|='addRole()']");
  private Element userAddressDropDown = Element.byCss("div#userRoleAddress_chzn");
  private Element userAddressInputField = Element
      .byCss("div#userRoleAddress_chzn>a.chzn-single~div>div>input");
  private Element successfulUserCreationMessage = Element.byXpath(format("//span[text()='%s']",
      LocaleProperties
          .getValueOf(LocalePropertyConst.CREATE_USER_SUCCESSFUL_USER_CREATION_MESSAGE)));
  private Element deleteUserRoleButton = Element.byCss("span.delete-role-link");
  private Element errorMessage = Element.byCss("div.status-message.error");

  @Override
  public boolean isScreenLoaded() {
    return addUserBlock.isDisplayed();
  }

  public CreateUserScreen typeEmailInputField(String email) {
    emailInputField.type(email);
    return this;
  }

  public CreateUserScreen typeLastNameInputField(String lastName) {
    lastNameInputField.type(lastName);
    return this;
  }

  public CreateUserScreen typeFirstNameInputField(String firstName) {
    firstNameInputField.type(firstName);
    return this;
  }

  public CreateUserScreen typeMobileNumberInputField(String mobileNumber) {
    mobileNumberInputField.type(mobileNumber);
    return this;
  }

  public CreateUserScreen clickUserRoleDropDown() {
    userRoleDropDown.waitForClickableAndClick();
    return this;
  }

  public CreateUserScreen clickUserAddressDropDown() {
    userAddressDropDown.waitForClickableAndClick();
    return this;
  }

  public CreateUserScreen clickUserAddressDropDownItemByIndex(int index) {
    Element.byCss(format(USER_ADDRESS_DROP_DOWN_ITEM_LOCATOR, index)).click();
    return this;
  }

  public CreateUserScreen clickUserRoleDropDownItemByIndex(int index) {
    Element.byCss(format(USER_ROLE_DROP_DOWN_ITEM_LOCATOR, index)).click();
    return this;
  }

  public CreateUserScreen waitUserAddressInputFieldVisibility() {
    userAddressInputField.waitForVisibility();
    return this;
  }

  public CreateUserScreen typeUserAddressInputField(String address) {
    userAddressInputField.type(address);
    return this;
  }

  public boolean isSuccessfulUserCreationMessageDisplayed() {
    return successfulUserCreationMessage.isDisplayed();
  }

  public CreateUserScreen clickAddRoleButton() {
    addRoleButton.click();
    return this;
  }

  public CreateUserScreen clickSaveButton() {
    saveButton.click();
    return this;
  }

  public CreateUserScreen waitDeleteUserRoleButtonForVisibility() {
    deleteUserRoleButton.waitForVisibility();
    return this;
  }

  public CreateUserScreen waitErrorMessageForVisibility() {
    errorMessage.waitForVisibility();
    return this;
  }

  public CreateUserScreen waitErrorMessageForInvisibility() {
    errorMessage.waitForInvisibility();
    return this;
  }

  public CreateUserScreen waitSaveButtonForAbsence() {
    saveButton.waitForAbsence();
    return this;
  }
}
