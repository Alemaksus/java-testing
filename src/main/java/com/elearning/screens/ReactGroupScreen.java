package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_GROUP_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_JOURNAL_ADD_CLASS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_ADD_GROUP_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_ALREADY_IN_GROUP_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_CURRENT_STATUS_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_GROUP_END_DATE_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_GROUP_START_DATE_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_GROUP_TABLE_NAME_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_PLANNING_SECTION_LINK;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.HTML.Attribute;

public class ReactGroupScreen extends AbstractScreen {

  private static final String GROUP_NAME_STRING_PATTERN = "//div[contains(@class, 'container uui')]";
  private static final String GROUP_BY_NAME_LINK_PATTERN = "//a[text()='%s']";
  private static final String PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION = "//div[text()='%s']";
  private static final String GROUP_STATUS_BY_GROUP_NAME_PATTERN = GROUP_BY_NAME_LINK_PATTERN +
      "//following::div[1]/div/div/div[4]//div[contains(@class, 'text')]";
  private static final String DELETE_BUTTON_BY_GROUP_NAME_LOCATOR = GROUP_BY_NAME_LINK_PATTERN
      + "//ancestor::div[contains(@class,'uui-table-row-container')]//div[contains(@class, 'uui-button-box')]";
  private static final String DELETE_ICON_BY_GROUP_NAME_PATTERN =
      "//a[text()='%s']/ancestor::*[contains(@class,'uui-table-row-container')]//*[contains(@class,'uui-icon')]";

  private Element headerTableGroup = Element.byXpath(
      "//div[contains(@class, 'uui-table-row-container')]");
  private Element addGroupButton = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION
          + "/parent::div", getValueOf(REACT_GROUP_SCREEN_ADD_GROUP_BUTTON));
  private Element groupTitle = Element.byXpath(
      "//div[contains(@class, 'uui-table-row-container uui-table-row')]//a");
  private Element currentStatus = Element.byXpath(
      GROUP_NAME_STRING_PATTERN + "/div[2]/div/div/div[4]/div/div/div");
  private Element deleteIcon = Element.byXpath(
      GROUP_NAME_STRING_PATTERN + "//div[contains(@class, 'uui-icon uui-enabled')]");
  private Element removeConfirmButton = Element.byXpath(
      "//div[contains(@class, 'uui-modal-window')]/div[3]/div[3]");
  private Element homeButtonIcon = Element.byXpath(
      "//header/../following-sibling::div//a[@href='/']");
  private Element planningLinkButton = Element.byXpath(
      PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_SCREEN_PLANNING_SECTION_LINK));
  private Element detailsTab = Element.byXpath("//a[contains(@href,'overview')]");
  private Element groupsTab = Element.byXpath(
      "//a[contains(@href,'groups')]/div[@class='uui-caption']");
  private Element participantsTab = Element.byXpath("//a[contains(@href,'participants')]");
  private Element auditTab = Element.byXpath("//a[contains(@href,'Audit')]");
  private Element groupTableNameSection = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_SCREEN_GROUP_TABLE_NAME_SECTION));
  private Element startDateSection = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_SCREEN_GROUP_START_DATE_SECTION));
  private Element endDateSection = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_SCREEN_GROUP_END_DATE_SECTION));
  private Element alreadyInGroupSection = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_SCREEN_ALREADY_IN_GROUP_SECTION));
  private Element currentStatusSection = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_SCREEN_CURRENT_STATUS_SECTION));
  private Element popUpWindow = Element.byXpath("//*[contains(@class,'uui-modal-window')]");
  private Element confirmationToaster = Element.byXpath("//div[@class='uui-snackbar-item-wrapper-self']");
  private Element crossButtonInConfirmationToaster = Element.byXpath(
      "//div[@class='uui-snackbar-item-wrapper-self']//*[contains(@class,'clickable')]//*[contains(@class,'uui-icon')]");
  private Element groupNameThatWasDeletedInConfirmationToaster = Element.byXpath(
      "//*[contains(@class,'uui-snackbar-item-wrapper-self')]//*[starts-with(text(),'%s')]",
      getValueOf(LEARNING_TASK_TAB_GROUP_LABEL));
  private Element deleteGroupButtonTooltip = Element.byXpath("//div[@class='uui-tooltip-body']");
  private Element addClassButton = Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION,
      getValueOf(REACT_GROUP_JOURNAL_ADD_CLASS_BUTTON));

  @Override
  public boolean isScreenLoaded() {
    return isTableGroupDisplayed();
  }

  public boolean isTableGroupDisplayed() {
    return headerTableGroup.isDisplayed();
  }

  public ReactGroupScreen waitDataLoading() {
    addGroupButton.waitForVisibility();
    return this;
  }

  public boolean isGroupNameAbsent() {
    return groupTitle.isAbsent();
  }

  public int getGroupNameListSize() {
    return getGroupNameList().size();
  }

  public List<Element> getGroupNameList() {
    groupTitle.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return groupTitle.getElements();
  }

  public ReactGroupScreen clickAddGroupButton() {
    addGroupButton.waitForElementClassEnabled();
    addGroupButton.click();
    return this;
  }

  public ReactGroupScreen waitNumberGroupNameToBeMore(int currentNumber) {
    groupTitle.waitNumberOfElementsToBeMoreThan(currentNumber);
    return this;
  }

  public String getCurrentStatusByIndexText(int index) {
    return getCurrentStatusList().get(index).getText();
  }

  public List<Element> getCurrentStatusList() {
    return currentStatus.getElements();
  }

  public List<Element> getDeleteButtonList() {
    return deleteIcon.getElements();
  }

  public ReactGroupScreen clickDeleteButtonByIndex(int index) {
    getDeleteButtonList().get(index).click();
    return this;
  }

  public ReactGroupScreen clickRemoveConfirmButtonButton() {
    removeConfirmButton.waitForClickableAndClick();
    return this;
  }

  public ReactGroupScreen waitNumberGroupNameToBeLess(int currentNumber) {
    groupTitle.waitNumberOfElementsToBeLessThan(currentNumber);
    return this;
  }

  public boolean isGroupNameDisplayed() {
    return groupTitle.isDisplayed();
  }

  public String getGroupStatusByName(String groupName) {
    return Element.byXpath(String.format(GROUP_STATUS_BY_GROUP_NAME_PATTERN, groupName))
        .getText();
  }

  public ReactGroupDetailsScreen clickGroupByName(String groupName) {
    Element.byXpath(String.format(GROUP_BY_NAME_LINK_PATTERN, groupName)).clickJs();
    return new ReactGroupDetailsScreen();
  }

  public ReactGroupDetailsScreen clickGroupByIndex(int index) {
    getGroupNameList().get(index).click();
    return new ReactGroupDetailsScreen();
  }

  public boolean isHomeButtonIconDisplayed() {
    return homeButtonIcon.isDisplayed();
  }

  public boolean isPlanningLinkButtonDisplayed() {
    return planningLinkButton.isDisplayed();
  }

  public boolean isPlanningLinkButtonClickable() {
    return planningLinkButton.isClickable();
  }

  public boolean isTrainingNameDisplayed(String trainingName) {
    return Element.byXpath(PATTERN_TO_FIND_BY_NAME_AND_LOCALIZATION, trainingName).isDisplayed();
  }

  public boolean isGroupNameIsDisplayedInGroupList(String groupName) {
    return Element.byXpath(GROUP_BY_NAME_LINK_PATTERN, groupName).isDisplayed();
  }

  public boolean isAddGroupButtonDisplayed() {
    return addGroupButton.isDisplayed();
  }

  public boolean isDetailsTabDisplayed() {
    return detailsTab.isDisplayed();
  }

  public boolean isGroupsTabDisplayed() {
    return groupsTab.isDisplayed();
  }

  public boolean isParticipantsTabDisplayed() {
    return participantsTab.isDisplayed();
  }

  public boolean isAuditTabDisplayed() {
    return auditTab.isDisplayed();
  }

  public boolean isGroupTableNameSectionDisplayed() {
    return groupTableNameSection.isDisplayed();
  }

  public boolean isStartDateSectionDisplayed() {
    return startDateSection.isDisplayed();
  }

  public boolean isEndDateSectionDisplayed() {
    return endDateSection.isDisplayed();
  }

  public boolean isAlreadyInGroupSectionDisplayed() {
    return alreadyInGroupSection.isDisplayed();
  }

  public boolean isCurrentStatusSectionDisplayed() {
    return currentStatusSection.isDisplayed();
  }

  public ReactGroupScreen waitUntilGroupNameByIndexWillBeDeleted(int index) {
    getGroupNameList().get(index).waitForAbsence();
    return this;
  }

  public List<String> getCurrentStatusText() {
    return currentStatus.getElementsText();
  }

  public ReactGroupScreen waitUntilGroupNameByLinkIsDisplayed(String groupName) {
    Element.byXpath(String.format(GROUP_BY_NAME_LINK_PATTERN, groupName))
        .waitForVisibility();
    return this;
  }

  public boolean isPopUpWindowDisplayed() {
    return popUpWindow.isDisplayedShortTimeOut();
  }

  public String hoverOnDeleteButtonByGroupName(String groupName) {
    return Element.byXpath(DELETE_BUTTON_BY_GROUP_NAME_LOCATOR, groupName).getTooltipText(
        deleteGroupButtonTooltip);
  }

  public boolean isDeleteGroupButtonDisabledByGroupName(String groupName) {
    return Element.byXpath(DELETE_BUTTON_BY_GROUP_NAME_LOCATOR, groupName)
        .getAttributeValue(Attribute.CLASS.toString()).contains(Element.DISABLED_ATTRIBUTE);
  }

  public boolean isDeleteGroupButtonEnabledByGroupName(String groupName) {
    return Element.byXpath(DELETE_BUTTON_BY_GROUP_NAME_LOCATOR, groupName)
        .getAttributeValue(Attribute.CLASS.toString()).contains(Element.ENABLED_ATTRIBUTE);
  }

  public boolean isConfirmationToasterDisplayed() {
    return confirmationToaster.isDisplayed();
  }

  public List<String> getGroupTitleText() {
    return groupTitle.getElementsText();
  }

  public boolean isCrossButtonInConfirmationToasterDisplayed() {
    return crossButtonInConfirmationToaster.isDisplayed();
  }

  public String getGroupNameThatWasDeletedInConfirmationToasterText() {
    return groupNameThatWasDeletedInConfirmationToaster.getText();
  }

  public ReactGroupScreen clickDeleteIconByGroupName(String groupName) {
    Element.byXpath(String.format(DELETE_ICON_BY_GROUP_NAME_PATTERN, groupName)).click();
    return this;
  }

  public ReactGroupScreen waitAddClassButtonVisibility() {
    addClassButton.waitForVisibility();
    return this;
  }
}
