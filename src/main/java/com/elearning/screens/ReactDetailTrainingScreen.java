package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_CANCEL_TRAINING_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_OF_SAVING_CHANGES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_COPY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_COPY_REGISTRATION_LINK_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_NOTIFY_MEMBERS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_PREVIEW_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SELECT_LANGUAGE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_STAFFING_DESK_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_STAFFING_DESK_POP_UP_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGEMENT_CREATE_NEW;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGEMENT_PLANNING;
import static com.epmrdpt.utils.EColorUtils.BLUE_ACTIVE_COLOUR;
import static javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR;
import static javax.swing.text.html.CSS.Attribute.BORDER_TOP_COLOR;

import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;
import javax.swing.text.html.HTML;

public class ReactDetailTrainingScreen extends ReactAdditionalInfoTabScreen {

  private static final String ADD_LANGUAGE_BUTTON_FROM_DROP_DOWN_XPATH_PATTERN
      = "//div[contains(@class,'button')]//div[text()='%s']";
  protected static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String CROSS_IN_LANGUAGE_BUTTON_XPATH_PATTERN
      = "//div[text()='%s']//following-sibling::div[contains(@class,'uui-icon')]";
  private static final String TRAINING_STATUS_BUTTON_PATTERN
      = "//div[text()='%s']/preceding-sibling::div[@role='button']";
  private static final String TRAINING_ACTIVE_STATUS_BUTTON_PATTERN
      = "//div[text()='%s']/preceding-sibling::div[@role='button']//div";
  private static final String LANGUAGE_SWITCH_PATTERN
      = "//div[contains(text(),'%s')]/parent::div";
  private final String POPUP_PATTERN =
      "(//div[@class='uui-snackbar-item-wrapper-self']//following::div[2])[last()]/preceding::div[1]";
  private static final String TRAINING_STATUSES_TEXT_LOCATOR =
      "//div[(@role='button') and(@tabindex='0')]/following-sibling::div";
  private static final String HOVERED_LINK_COLOR = "rgba(0, 158, 204, 1)";

  private Element trainingTitle = Element.byXpath(
      "(//a[@href='/']/following-sibling::div//*[contains(@class, 'text') and not(text()='%s')])[2]",
      getValueOf(TRAINING_MANAGEMENT_CREATE_NEW));
  private Element planningTitle = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(TRAINING_MANAGEMENT_PLANNING));
  private Element createCopyButton = Element.byXpath(
      "//a[contains(@href,'copyFrom=')]/div[text()='%s']",
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_COPY_BUTTON));
  private Element previewButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_PREVIEW_BUTTON));
  private Element detailsTab = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB));
  private Element groupsTab = Element.byXpath("//a[contains(@href,'groups')]/div[@class='uui-caption']");
  private Element participantsTab = Element.byXpath("//a[contains(@href,'participants')]");
  private Element auditTab = Element.byXpath("//a[contains(@href,'Audit')]");
  private Element copyRegistrationLinkButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_COPY_REGISTRATION_LINK_BUTTON));
  private Element saveChangesButton = Element.byXpath(TEXT_LOCATOR_PATTERN + "/parent::div",
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON));
  private Element confirmationOfSavingChanges = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_OF_SAVING_CHANGES));
  private Element russianLanguageFromDDLButton = Element.byXpath(
      ADD_LANGUAGE_BUTTON_FROM_DROP_DOWN_XPATH_PATTERN,
      getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN));
  private Element englishLanguageFromDDLButton = Element.byXpath(
      ADD_LANGUAGE_BUTTON_FROM_DROP_DOWN_XPATH_PATTERN,
      getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH));
  private Element ukrainianLanguageFromDDLButton = Element.byXpath(
      ADD_LANGUAGE_BUTTON_FROM_DROP_DOWN_XPATH_PATTERN,
      getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN));
  private Element selectLanguageButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_SELECT_LANGUAGE_BUTTON));
  private Element homeButton = Element.byXpath("//a[@href='/']//div");
  private Element statusMessage = Element.byXpath(
      "//div[contains(@class,'wrapper')]//div[text()]");
  private Element notifyMembersButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_NOTIFY_MEMBERS_BUTTON));
  private Element crossOnRussianLocalizationButton = Element.byXpath(
      CROSS_IN_LANGUAGE_BUTTON_XPATH_PATTERN,
      getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN));
  private Element crossOnUkrainianLocalizationButton = Element.byXpath(
      CROSS_IN_LANGUAGE_BUTTON_XPATH_PATTERN,
      getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN));
  private Element changeStatusPopUp = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_TEXT));
  private Element changeStatusStaffingDeskPopUp = Element.byXpath("//div[contains(text(), '%s')]",
      getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_STAFFING_DESK_POP_UP_TEXT));
  private Element draftStatusButton = Element.byXpath(TRAINING_STATUS_BUTTON_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS));
  private Element plannedStatusButton = Element.byXpath(TRAINING_STATUS_BUTTON_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS));
  private Element registrationOpenStatusButton = Element.byXpath(TRAINING_STATUS_BUTTON_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS));
  private Element registrationClosedStatusButton = Element.byXpath(TRAINING_STATUS_BUTTON_PATTERN,
      getValueOf(REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS));
  private Element confirmationOfStatusChangingButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_BUTTON));
  private Element confirmationOfStatusChangingStaffingDeskButton = Element.byXpath(
      TEXT_LOCATOR_PATTERN, getValueOf(
          REACT_MODAL_PAGE_FROM_REACT_DETAIL_TRAINING_SCREEN_STAFFING_DESK_CONFIRMATION_BUTTON));
  private Element notificationPopUp = Element.byXpath(POPUP_PATTERN);
  private Element closeNotificationPopUpButton = Element.byXpath(
      "//div[contains(@class,'snackbar-item')]//div[contains(@class,'button')]");
  private Element notificationPopUpForAC = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_OF_SAVING_CHANGES));
  private Element backToTrainingListFromTopButton =
      Element.byXpath("(//a[@href='/Management'])[2]");
  private Element typeField = Element.byXpath("(//div[@data-id])[1]//input/preceding-sibling::div/div");
  private Element displayingNameField = Element.byXpath(
      "(//*[@placeholder='%s'])[2]",
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER));
  private final Element nameField = Element.byXpath(
      "(//*[@placeholder='%s'])[1]",
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER));
  private final Element educationEstablishmentField = Element.byXpath("(//*[contains(@class, 'uui-placeholder')])[2]");
  private final Element departmentField = Element.byXpath("(//*[contains(@class, 'uui-placeholder')])[3]");
  private final Element supervisorsField = Element.byXpath("(//div[@class = 'uui-caption'])[5]");
  private final Element studentGroupField = Element.byXpath("(//div[@display = 'inline-block']//span)[2]");
  private Element spinnerContainer = Element.byXpath(
      "//a[contains(@href,'overview')]/../../following-sibling::div/div[3]/div"
          + "/following-sibling::div/div/div[contains(@class, 'uui-spinner-container')]");
  private Element enabledSaveChangesButton = Element.byXpath(
      "//*[contains(@class, 'uui-enabled')]/div[text()='%s']",
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON));
  private Element cancelTraining = Element.byXpath(TEXT_LOCATOR_PATTERN, getValueOf(REACT_DETAIL_TRAINING_SCREEN_CANCEL_TRAINING_BUTTON));
  private Element crossStartDate =
      Element.byXpath("(//input[@placeholder='DD.MM.YYYY'])[1]/..//div[contains(@class,'uui-icon')][2]");
  private Element startDate = Element.byXpath("(//input[@placeholder='DD.MM.YYYY'])[1]");
  private Element datePicker = Element.byXpath("//header[@class='uui-datepickerheader-header']");
  private Element classesTab = Element.byXpath("//a[contains(@href,'groups')]/div[@class='uui-caption']");

  @Override
  public boolean isScreenLoaded() {
    return trainingTitle.isDisplayed();
  }

  public boolean isEnglishLanguageButtonDisplayedNoWait() {
    return englishLanguageFromDDLButton.isDisplayedNoWait();
  }

  public boolean isRussianLanguageButtonDisplayedNoWait() {
    return russianLanguageFromDDLButton.isDisplayedNoWait();
  }

  public boolean isCrossOnRussianLocalizationButtonDisplayedNoWait() {
    return crossOnRussianLocalizationButton.isDisplayedNoWait();
  }

  public boolean isUkrainianLanguageButtonDisplayedNoWait() {
    return ukrainianLanguageFromDDLButton.isDisplayedNoWait();
  }

  public boolean isCrossOnUkrainianLocalizationButtonDisplayedNoWait() {
    return crossOnUkrainianLocalizationButton.isDisplayedNoWait();
  }

  public boolean isSelectLanguageButtonDisplayed() {
    return selectLanguageButton.isDisplayed();
  }

  public ReactDetailTrainingScreen waitGroupsTabDisplayed() {
    groupsTab.waitForVisibility();
    return this;
  }

  public ReactGroupScreen clickGroupsTabs() {
    groupsTab.click();
    return new ReactGroupScreen();
  }

  public ReactClassesScreen clickClassesTabs() {
    classesTab.click();
    return new ReactClassesScreen();
  }

  public ReactCreateTrainingScreen clickCreateCopyButton() {
    createCopyButton.waitForClickableAndClick();
    return new ReactCreateTrainingScreen();
  }

  public ReactDetailTrainingScreen waitScreenLoaded() {
    trainingTitle.waitForVisibility();
    waitDataLoading();
    return this;
  }

  public ReactDetailTrainingScreen waitTitleForVisibility() {
    trainingTitle.waitForVisibility();
    return this;
  }

  public TrainingListScreen clickHomeButton() {
    homeButton.click();
    return new TrainingListScreen();
  }

  public boolean isHomeButtonDisplayed() {
    return homeButton.isDisplayed();
  }

  public ReactDetailTrainingScreen clickSelectLanguageButton() {
    selectLanguageButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickRussianLanguageButton() {
    russianLanguageFromDDLButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickEnglishLanguageButton() {
    englishLanguageFromDDLButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickByScrollingEnglishLanguageButton() {
    englishLanguageFromDDLButton.scrollAndClick();
    return this;
  }

  public ReactDetailTrainingScreen clickUkrainianLanguageButton() {
    ukrainianLanguageFromDDLButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickSaveChangesButton() {
    saveChangesButton.waitForElementClassEnabled();
    saveChangesButton.clickJs();
    return this;
  }

  public ReactModalPageFromReactDetailTrainingScreen clickCrossOnRussianLocalizationButton() {
    crossOnRussianLocalizationButton.click();
    return new ReactModalPageFromReactDetailTrainingScreen();
  }

  public boolean isConfirmationOfSavingChangesIsDisplayed() {
    return confirmationOfSavingChanges.isDisplayed();
  }

  public boolean isCopyRegistrationLinkButtonDisplayed() {
    return copyRegistrationLinkButton.isDisplayed();
  }

  public ReactDetailTrainingScreen clickCopyRegistrationLinkButton() {
    copyRegistrationLinkButton.click();
    return this;
  }

  public ReactParticipantsTrainingScreen clickReactParticipantsTab() {
    participantsTab.waitForClickableAndClick();
    return new ReactParticipantsTrainingScreen();
  }

  public ReactNotifyMembersPopUpScreen clickNotifyMembersButton() {
    notifyMembersButton.click();
    return new ReactNotifyMembersPopUpScreen();
  }

  public ReactDetailTrainingScreen waitNotifyMembersButtonClickable() {
    notifyMembersButton.waitForClickable();
    return this;
  }

  public String getStatusMessageText() {
    return statusMessage.getText();
  }

  public String getDisplayingNameText() {
    return displayingNameField.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public ReactDetailTrainingScreen clickDraftStatusButton() {
    draftStatusButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickPlannedStatusButton() {
    plannedStatusButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickRegistrationOpenStatusButton() {
    registrationOpenStatusButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickRegistrationClosedStatusButton() {
    registrationClosedStatusButton.click();
    return this;
  }

  public ReactDetailTrainingScreen waitChangeStatusPopUpVisibility() {
    changeStatusPopUp.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen waitChangeStatusStaffingDeskPopUpVisibility() {
    changeStatusStaffingDeskPopUp.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen clickConfirmationOfChangingStatusButton() {
    confirmationOfStatusChangingButton.click();
    return this;
  }

  public ReactDetailTrainingScreen clickConfirmationOfChangingStaffingDeskStatusButton() {
    confirmationOfStatusChangingStaffingDeskButton.click();
    return this;
  }

  public boolean isRussianLanguageFromDDLButtonIsDisplayed() {
    return russianLanguageFromDDLButton.isDisplayed();
  }

  public ReactDetailTrainingScreen waitNotificationPopUpVisibility() {
    notificationPopUp.waitForVisibility();
    return this;
  }

  public Boolean isNotificationPopUpDisplayed() {
    return notificationPopUp.waitForVisibility().isDisplayed();
  }

  public String getTrainingTitleText() {
    return trainingTitle.getText();
  }

  public String getNameText() {
    return nameField.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public String getEducationEstablishmentText() {
    return educationEstablishmentField.getAttributeValue("placeholder");
  }

  public String getDepartmentText() {
    return departmentField.getAttributeValue("placeholder");
  }

  public String getSupervisorsText() {
    return supervisorsField.getText();
  }

  public String getStudentGroupText() {
    return studentGroupField.getText();
  }

  public ReactDetailTrainingScreen waitNotificationPopUpForACVisibility() {
    notificationPopUpForAC.waitForVisibility();
    return this;
  }

  public boolean isNotificationPopUpForACDisplayed() {
    return notificationPopUpForAC.isDisplayed();
  }

  public boolean isPreviewButtonDisplayed() {
    return previewButton.isDisplayed();
  }

  public TrainingDescriptionScreen clickPreviewButton() {
    previewButton.click();
    return new TrainingDescriptionScreen();
  }

  public ReactTrainingManagementScreen clickBackToTrainingListFromTopButton() {
    backToTrainingListFromTopButton.scrollAndClick();
    return new ReactTrainingManagementScreen();
  }

  public ReactDetailTrainingScreen clickTrainingStatusButtonByText(String trainingStatus) {
    Element.byXpath(String.format(TRAINING_STATUS_BUTTON_PATTERN, trainingStatus))
        .waitForClickableAndClick();
    return this;
  }

  public boolean isTrainingStatusByStatusNameActive(String trainingStatus) {
    return Element.byXpath(TRAINING_ACTIVE_STATUS_BUTTON_PATTERN, trainingStatus)
        .getCssValue(BACKGROUND_COLOR.toString()).contains(BLUE_ACTIVE_COLOUR.getColorRgbaFormat());
  }

  public ReactDetailTrainingScreen typeDisplayingName(String name) {
    displayingNameField.type(name);
    return this;
  }

  public ReactDetailTrainingScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitForDisappear();
    return this;
  }

  public ReactDetailTrainingScreen waitSaveChangesButtonEnabled() {
    enabledSaveChangesButton.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen closeNotificationPopUp() {
    closeNotificationPopUpButton.waitForClickableAndClick();
    return this;
  }

  public boolean isPlanningTitleDisplayed() {
    return planningTitle.isDisplayed();
  }

  public ReactTrainingManagementScreen clickPlanningTitle() {
    planningTitle.click();
    return new ReactTrainingManagementScreen();
  }

  public boolean isDetailsTabDisplayed() {
    return detailsTab.isDisplayed();
  }

  public boolean isParticipantsTabDisplayed() {
    return participantsTab.isDisplayed();
  }

  public boolean isGroupsTabDisplayed() {
    return groupsTab.isDisplayed();
  }

  public boolean isAuditTabDisplayed() {
    return auditTab.isDisplayed();
  }

  public String getParticipantsTabBorderColor() {
    return participantsTab.getCssValue(BORDER_TOP_COLOR.toString());
  }

  public String getGroupsTabBorderColor() {
    return groupsTab.getCssValue(BORDER_TOP_COLOR.toString());
  }

  public ReactDetailTrainingScreen waitDataLoading() {
    typeField.waitForVisibility();
    return this;
  }

  public ReactDetailTrainingScreen clickLanguageTab(String language) {
    Element.byXpath(String.format(LANGUAGE_SWITCH_PATTERN, language)).click();
    return this;
  }

  public ReactCancelTrainingPopUpScreen clickCancelTrainingButton() {
    cancelTraining.waitForClickable();
    cancelTraining.mouseOverAndClick();
    return new ReactCancelTrainingPopUpScreen();
  }

  public ReactDetailTrainingScreen clickCrossStartDate() {
    crossStartDate.waitForClickableAndClick();
    return this;
  }

  public ReactDetailTrainingScreen waitUntilStartDateAttributeGetsValue(
      String attributeName,
      String value
  ) {
    startDate.waitUntilAttributeGetsValue(attributeName, value);
    return this;
  }

  public ReactDetailTrainingScreen typeDateToStartDate(String date) {
    startDate.type(date);
    return this;
  }

  public String getTypeText() {
    return typeField.getText();
  }

  public boolean checkNotificationPopUpDisplayed() {
    return notificationPopUp.isDisplayed();
  }

  public List<String> getTrainingStatusesText() {
    return Element.byXpath(TRAINING_STATUSES_TEXT_LOCATOR).getElementsText();
  }

  public boolean isTrainingTitleClickable() {
    return trainingTitle.getCssValue(Attribute.COLOR.toString()).equals(HOVERED_LINK_COLOR);
  }

  public boolean isPlanningLinkClickable() {
    return planningTitle.isClickable();
  }

  public boolean isTabDisplayedByName(String tabName) {
    return Element.byXpath(TEXT_LOCATOR_PATTERN, tabName).isDisplayed();
  }

  public boolean isTabBecomesHighlightedWhenHoveredByName(String tabName) {
    return Element.byXpath(TEXT_LOCATOR_PATTERN, tabName).getHoveredCssValue(Attribute.COLOR.toString())
        .equals(HOVERED_LINK_COLOR);
  }

  public AuditScreen clickAuditTab() {
    auditTab.click();
    return new AuditScreen();
  }

  public String getCopyRegistrationLinkButtonText() {
    return copyRegistrationLinkButton.getAttributeValue(TEXT_CONTENT_CSS_PROPERTY);
  }
}
