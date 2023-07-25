package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactDetailTrainingCalendarScreen extends AbstractScreen {

  private Element monthAndYearDate = Element
      .byXpath("//*[@class='uui-datepickerheader-nav-title']");
  private Element rightArrowDate = Element.byXpath(
      "//*[contains(@class, 'header-nav-icon-right')]");
  private Element leftArrowDate = Element.byXpath("//*[contains(@class, 'header-nav-icon-left')]");
  private static final String CONCRETE_DAY_OF_MONTH_PATTERN = "//*[@class='uui-calendar-day-cell']//*[@class='uui-calendar-day' and text()='%s']";
  private Element currentDay = Element.byXpath("//*[contains(@class, 'current-day')]");
  private final Element calendarDayButton = Element.byXpath(
      "//div[contains(@class,'uui-calendar-clickable-day')]");

  @Override
  public boolean isScreenLoaded() {
    return monthAndYearDate.isDisplayed();
  }

  public String getMonthAndYearDateText() {
    return monthAndYearDate.getText();
  }

  public ReactDetailTrainingCalendarScreen clickRightArrowDate() {
    rightArrowDate.click();
    return this;
  }

  public ReactDetailTrainingCalendarScreen clickLeftArrowDate() {
    leftArrowDate.click();
    return this;
  }

  public ReactDetailTrainingCalendarScreen waitGetMonthAndYearDateChanged(String value) {
    monthAndYearDate.waitTextNotToBePresentInElement(value);
    return this;
  }

  public ReactDetailTrainingCalendarScreen clickDayOfMonthDateButton(int day) {
    Element.byXpath(CONCRETE_DAY_OF_MONTH_PATTERN, day).click();
    return this;
  }

  public ReactDetailTrainingCalendarScreen clickCurrentDayOfMonthDateButton() {
    currentDay.click();
    return this;
  }

  public ReactDetailTrainingCalendarScreen waitDateCalendarCollapsed() {
    monthAndYearDate.waitForInvisibility();
    return this;
  }

  public ReactDetailTrainingCalendarScreen clickLastDayOfMonth() {
    List<Element> calendarDays = calendarDayButton.getElements();
    calendarDays.get(calendarDays.size() - 1).click();
    return this;
  }
}
