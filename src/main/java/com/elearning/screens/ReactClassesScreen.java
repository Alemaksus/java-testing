package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_DELETE_POPUP_CANCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_DELETE_POPUP_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_DELETE_POPUP_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_ADD_CLASS_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactClassesScreen extends AbstractScreen {

  private static final String LINK_PATTERN = "//a[text()='%s']";
  private static final String PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION = "//div[text()='%s']";
  private static final String DELETE_ICON_BY_GROUP_NAME_PATTERN =
      "//a[text()='%s']/ancestor::*[contains(@class,'uui-table-row-container')]//*[contains(@class,'uui-icon')]";

  private Element headerTableClasses = Element.byXpath(
      "//div[contains(@class,'uui-table-row-container')]");
  private Element spinnerOfLoading = Element.byXpath("//div[contains(@class,'uui-table-row-container')]"
      + "/following::div//div[contains(@class,'uui-spinner-animation')]");
  private Element classTitle = Element.byXpath(
      "//div[contains(@class, 'uui-table-row-container uui-table-row')]//a");
  private Element addClassButton = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_JOURNAL_ADD_CLASS_BUTTON));
  private Element popUpWindow = Element.byXpath("//*[contains(@class,'uui-modal-window')]");
  private Element deletePopUpHeader = Element.byXpath(
      String.format(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
          getValueOf(REACT_CLASSES_SCREEN_DELETE_POPUP_HEADER)));
  private Element deletePopUpBody = Element.byXpath(
      "//*[contains(@class,'uui-modal-window')]/div[2]/div/div");
  private Element deletePopUpCrossButton = Element
      .byXpath("//div[contains(@class,'uui-modal-window')]//div[contains(@class,'uui-icon')]");
  private Element deletePopUpCancelButton = Element.byXpath(String
      .format(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
          getValueOf(REACT_CLASSES_SCREEN_DELETE_POPUP_CANCEL_BUTTON)));
  private Element deletePopUpConfirmationButton = Element.byXpath(String
      .format(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
          getValueOf(REACT_CLASSES_SCREEN_DELETE_POPUP_CONFIRMATION_BUTTON)));
  private Element confirmationToaster = Element.byXpath(
      "//div[@class='uui-snackbar-item-wrapper-self']");

  @Override
  public boolean isScreenLoaded() {
    return isTableClassesDisplayed();
  }

  public boolean isTableClassesDisplayed() {
    return headerTableClasses.isDisplayed();
  }

  public ReactClassesScreen waitDataLoading() {
    spinnerOfLoading.waitForDisappear();
    return this;
  }

  public ReactClassesDetailsScreen clickClassesByName(String groupName) {
    Element.byXpath(String.format(LINK_PATTERN, groupName)).click();
    return new ReactClassesDetailsScreen();
  }

  public ReactClassesDetailsScreen clickClassByIndex(int index) {
    getClassNamesList().get(index).click();
    return new ReactClassesDetailsScreen();
  }

  public int getClassNamesListSize() {
    return getClassNamesList().size();
  }

  public List<Element> getClassNamesList() {
    classTitle.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return classTitle.getElements();
  }

  public List<String> getClassNames() {
    classTitle.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return classTitle.getElementsText();
  }

  public ReactClassesScreen clickAddClassButton() {
    addClassButton.click();
    return this;
  }

  public ReactClassesScreen waitNumberClassNamesToBeMore(int currentNumber) {
    classTitle.waitNumberOfElementsToBeMoreThan(currentNumber);
    return this;
  }

  public ReactClassesScreen clickDeleteIconByClassName(String className) {
    Element.byXpath(String.format(DELETE_ICON_BY_GROUP_NAME_PATTERN, className)).click();
    return this;
  }

  public boolean isPopUpWindowDisplayed() {
    return popUpWindow.isDisplayedShortTimeOut();
  }

  public ReactClassesScreen waitPopUpWindowVisibility() {
    popUpWindow.waitForVisibility();
    return this;
  }

  public String getDeletePopUpHeaderText() {
    return deletePopUpHeader.getText();
  }

  public boolean isDeletePopUpHeaderDisplayed() {
    return deletePopUpHeader.isDisplayed();
  }

  public boolean isDeletePopUpCrossButtonDisplayed() {
    return deletePopUpCrossButton.isDisplayed();
  }

  public boolean isDeletePopUpCrossButtonClickable() {
    return deletePopUpCrossButton.isClickable();
  }

  public String getDeletePopUpBodyText() {
    return deletePopUpBody.getText();
  }

  public boolean isDeletePopUpBodyTextDisplayed() {
    return deletePopUpBody.isDisplayed();
  }

  public boolean isDeletePopUpCancelButtonDisplayed() {
    return deletePopUpCancelButton.isDisplayed();
  }

  public boolean isDeletePopUpCancelButtonClickable() {
    return deletePopUpCancelButton.isClickable();
  }

  public String getDeletePopUpCancelButtonText() {
    return deletePopUpCancelButton.getText();
  }

  public boolean isDeletePopUpConfirmationButtonDisplayed() {
    return deletePopUpConfirmationButton.isDisplayed();
  }

  public boolean isDeletePopUpConfirmationButtonClickable() {
    return deletePopUpConfirmationButton.isClickable();
  }

  public String getDeletePopUpConfirmationButtonText() {
    return deletePopUpConfirmationButton.getText();
  }

  public ReactClassesScreen clickPopUpConfirmationButton() {
    deletePopUpConfirmationButton.waitForClickableAndClick();
    return this;
  }

  public boolean isConfirmationToasterDisplayed() {
    return confirmationToaster.isDisplayed();
  }
}
