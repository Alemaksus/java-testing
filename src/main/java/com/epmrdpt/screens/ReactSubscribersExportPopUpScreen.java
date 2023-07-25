package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CALENDAR_FROM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CALENDAR_TO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_DATE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SUBSCRIBERS_EXPORT_POP_UP_EXPORT_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactSubscribersExportPopUpScreen extends AbstractScreen {

  private static final String POP_UP_PATTERN = "//div[contains(@class,'uui-modal-window')]";
  private static final String TEXT_LOCATOR_PATTERN = POP_UP_PATTERN + "//div[text()='%s']";
  private static final String MULTI_SELECT_FIELD_PATTERN = "//ancestor::body//div[@class='uui-popper']";
  private static final String DDL_CHECKBOX_BY_LABEL_NAME_PATTERN = TEXT_LOCATOR_PATTERN
      + MULTI_SELECT_FIELD_PATTERN + "//div[@class='uui-checkbox']";
  private static final String DDL_PLACEHOLDER_BY_LABEL_NAME_PATTERN =
      TEXT_LOCATOR_PATTERN + "//ancestor::div[@data-id='']//div[contains(@class,'placeholder')]";
  private static final String DDL_VALUE_BY_TEXT_PATTERN = TEXT_LOCATOR_PATTERN
      + MULTI_SELECT_FIELD_PATTERN + "//div[text()='%s']";
  private static final String DDL_ARROW_BY_LABEL_NAME_PATTERN =
      TEXT_LOCATOR_PATTERN + "//ancestor::div[@data-id='']//*[@*[contains(.,'arrow')]]";
  private static final String DATE_PLACEHOLDER_LOCATOR = "/..//input[contains(@placeholder,'%s')]";

  private Element popUpTitle = Element.byXpath(POP_UP_PATTERN + "/div/div[text()]");
  private Element exportButton = Element.byXpath(TEXT_LOCATOR_PATTERN, getValueOf(
      SUBSCRIBERS_EXPORT_POP_UP_EXPORT_BUTTON));
  private Element crossButton = Element.byXpath("//*[name()='use' and @*[contains(.,'close')]]");
  private Element cancelButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON));
  private Element startDateField = Element
      .byXpath(TEXT_LOCATOR_PATTERN + DATE_PLACEHOLDER_LOCATOR,
          getValueOf(REACT_TRAINING_MANAGEMENT_DATE_LABEL), getValueOf(CALENDAR_FROM));
  private Element endDateField = Element
      .byXpath(TEXT_LOCATOR_PATTERN + DATE_PLACEHOLDER_LOCATOR,
          getValueOf(REACT_TRAINING_MANAGEMENT_DATE_LABEL), getValueOf(CALENDAR_TO));

  @Override
  public boolean isScreenLoaded() {
    return popUpTitle.isDisplayed();
  }

  public ReactSubscribersExportPopUpScreen waitScreenLoaded() {
    exportButton.waitForVisibility();
    return this;
  }

  public boolean isPopUpTitleDisplayed() {
    return popUpTitle.isDisplayed();
  }

  public String getPopUpTitleText() {
    return popUpTitle.getText();
  }

  public boolean isCrossButtonDisplayed() {
    return crossButton.isDisplayed();
  }

  public boolean isCancelButtonDisplayed() {
    return cancelButton.isDisplayed();
  }

  public boolean isExportButtonDisplayed() {
    return exportButton.isDisplayed();
  }

  public boolean isMultiSelectFieldDisplayedByLabelName(String labelName) {
    return !Element.byXpath(DDL_CHECKBOX_BY_LABEL_NAME_PATTERN, labelName)
        .waitUntilAllElementsLocatedByAreVisible().isEmpty();
  }

  public String getDdlPlaceholderTextByLabelName(String labelName) {
    return Element.byXpath(DDL_PLACEHOLDER_BY_LABEL_NAME_PATTERN, labelName).getText();
  }

  public ReactSubscribersExportPopUpScreen clickDdlByLabelName(String labelName) {
    Element.byXpath(DDL_ARROW_BY_LABEL_NAME_PATTERN, labelName).click();
    return this;
  }

  public ReactSubscribersExportPopUpScreen selectDdlValueByLabelName(String value,
      String labelName) {
    Element.byXpath(DDL_VALUE_BY_TEXT_PATTERN, labelName, value).mouseOverAndClick();
    return this;
  }

  public boolean isDateLabelDisplayed() {
    return Element.byXpath(TEXT_LOCATOR_PATTERN, getValueOf(REACT_TRAINING_MANAGEMENT_DATE_LABEL))
        .isDisplayed();
  }

  public ReactSubscribersExportPopUpScreen typeStartDate(String startDate) {
    startDateField.type(startDate);
    startDateField.click();
    return this;
  }

  public ReactSubscribersExportPopUpScreen typeEndDate(String endDate) {
    endDateField.type(endDate);
    endDateField.click();
    return this;
  }

  public ReactTrainingManagementScreen clickExportButton() {
    exportButton.waitForClickableAndClick();
    return new ReactTrainingManagementScreen();
  }
}
