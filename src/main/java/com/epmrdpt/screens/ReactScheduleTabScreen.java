package com.epmrdpt.screens;

import static com.epmrdpt.framework.ui.element.Element.DISABLED_ATTRIBUTE;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;

public class ReactScheduleTabScreen extends AbstractScreen {

  private static final String NUMBER_DELIMITER = "/";
  private static final String SWITCHER_BETWEEN_GROUPS =
      "//div[text()='/']/..";

  private Element scheduleForDay = Element.byCss("div.rbc-time-view");
  private Element arrowsOfSwitcherBetweenGroups = Element.byXpath(SWITCHER_BETWEEN_GROUPS +
      "//div[contains(@class, 'uui-button-box')]");
  private Element groupNameFromSwitcher = Element.byXpath(
      SWITCHER_BETWEEN_GROUPS + "/a[contains(@class,' active')]");
  private Element groupNumbersFromSwitcher = Element.byXpath(SWITCHER_BETWEEN_GROUPS + "/div");
  private Element addClassButton = Element.byXpath(
      "//div[@class='section-tabs__content']/div/div[1]//div[starts-with(@class,'uui-caption')]");
  private Element scheduleTable = Element.byXpath("//div[@class='time-schedule-wrapper']");
  private Element breadCrumbsLevel = Element.byXpath(
      "//*[@class='bread-crumbs__left']/div/child::*");
  private Element breadCrumbsClassPath = Element.byXpath("//div[@class='bread-crumbs__left']");
  private Element homeIcon = Element.byCss("a.home-page svg");
  private Element groupSwitcher = Element.byXpath(SWITCHER_BETWEEN_GROUPS);
  private Element trainingNameFromScheduleTab = Element.byXpath(
      "//*[contains(@class,' home-page')]/../following-sibling::div");
  private Element calendarIcon = Element.byXpath
      ("(//div[@id='schedule-calendar-header']//following::div[contains(@class,'uui-enabled')])[2]");
  private Element dayButton = Element.byXpath
      ("(//div[@id='schedule-calendar-header']//following::div[contains(@class,'uui-caption')])[1]");
  private Element leftArrow = Element.byXpath
      ("(//div[@id='schedule-calendar-header']//following::div[contains(@class,'uui-icon')])[2]");
  private Element rightArrow = Element.byXpath
      ("(//div[@id='schedule-calendar-header']//following::div[contains(@class,'uui-icon')])[3]");
  private Element monthAndYearIndicator = Element.byXpath
      ("(//div[@id='schedule-calendar-date-picker']/span)[1]");
  private Element dayOfWeekIndicator = Element.byXpath
      ("(//div[@id='schedule-calendar-current-day']/span)[2]");
  private Element dayIndicator = Element.byXpath
      ("(//div[@id='schedule-calendar-current-day']/span)[1]");
  private Element viewMode = Element.byXpath
      ("(//div[@id='schedule-calendar-header']//following::div[contains(@class,'uui-caption')])[2]");
  private Element listViewMode = Element.byXpath
      ("//div[@id='schedule-calendar-header']//following::div[@class='uui-input-label']");
  private Element scheduleComponent = Element.byXpath
      ("//div[@class='rbc-events-container']");
  private Element activeClassTicket = Element.byXpath
      ("(//div[@class='rbc-events-container']//following::div[@class='rbc-event-content'])[1]");
  private Element currentTimeIndicator = Element.byXpath
      ("//div[@class='rbc-events-container']//following::div[@class='rbc-current-time-indicator']");

  @Override
  public boolean isScreenLoaded() {
    return isScheduleForDayDisplayed();
  }

  public boolean isScheduleForDayDisplayed() {
    return scheduleForDay.isDisplayed();
  }

  public boolean isSwitchBetweenGroupsArrowsDisplayed() {
    return arrowsOfSwitcherBetweenGroups.isAllElementsDisplayed(LONG_TIME_OUT_IN_SECONDS);
  }

  public boolean isGroupSwitcherDisplayed() {
    return groupSwitcher.isDisplayed();
  }

  public boolean isGroupSwitcherNotDisplayed() {
    return groupSwitcher.isNotDisplayed();
  }

  private List<Element> getSwitchBetweenGroupsArrowsList() {
    return arrowsOfSwitcherBetweenGroups.getElements();
  }

  public ReactScheduleTabScreen clickGroupNameFromSwitcher() {
    groupNameFromSwitcher.click();
    return this;
  }

  public ReactScheduleTabScreen clickTrainingNameFromScheduleJournal() {
    trainingNameFromScheduleTab.click();
    return this;
  }

  public ReactScheduleTabScreen clickOnEnabledArrowSwitcherBetweenGroups() {
    if (getSwitchBetweenGroupsArrowsList().get(1).getAttributeValue(Element.ATTRIBUTE_CLASS)
        .contains(DISABLED_ATTRIBUTE)) {
      getSwitchBetweenGroupsArrowsList().get(0).click();
    } else {
      getSwitchBetweenGroupsArrowsList().get(1).click();
    }
    return this;
  }

  public String getGroupNameFromSwitcherText() {
    return groupNameFromSwitcher.getText();
  }

  public int getCountOfGroupsFromSwitcher() {
    int endNumberIndex = 1;
    return Integer.parseInt(
        groupNumbersFromSwitcher.getText().trim().split(NUMBER_DELIMITER)[endNumberIndex]);
  }

  public int getBreadCrumbsLevelCount() {
    return breadCrumbsLevel.getElements().size();
  }

  public String getTextFromBredCrumbs() {
    return breadCrumbsClassPath.getAttributeValue("innerText");
  }

  public boolean isHomeIconDisplayed() {
    return homeIcon.isDisplayed();
  }

  public ReactAddClassPopUpScreen clickAddClassButton() {
    addClassButton.click();
    return new ReactAddClassPopUpScreen();
  }

  public ReactScheduleTabScreen waitScheduleTableForVisibility() {
    scheduleTable.waitForVisibility();
    return this;
  }

  public boolean isCalendarIconDisplayed() {
    return calendarIcon.isDisplayed();
  }

  public String getDayButtonText() {
    return dayButton.getText();
  }

  public boolean isLeftArrowButtonDisplayed() {
    return leftArrow.isDisplayed();
  }

  public boolean isRightArrowButtonDisplayed() {
    return rightArrow.isDisplayed();
  }

  public String getMonthButtonText() {
    return monthAndYearIndicator.getText();
  }

  public String getDayOfWeekButtonText() {
    return dayOfWeekIndicator.getText();
  }

  public String getDayValueButtonText() {
    return dayIndicator.getText();
  }

  public String getDayButtonColour() {
    return dayIndicator.getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public String getViewModeText() {
    return viewMode.getText();
  }

  public ReactScheduleTabScreen clickViewModeDDL() {
    viewMode.click();
    return this;
  }

  public String getListElementsOfViewMode() {
    return listViewMode.getElementsText().toString().replace("[", "").replace("]", "");
  }

  public boolean isScheduleComponentFieldDisplayed() {
   return scheduleComponent.isDisplayed();
  }

  public boolean isCurrentTimeIndicatorDisplayed() {
    return currentTimeIndicator.isDisplayed();
  }

  public boolean isActiveClassTicketDisplayed() {
    return activeClassTicket.isDisplayed();
  }
}
