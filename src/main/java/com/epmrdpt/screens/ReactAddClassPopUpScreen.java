package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReactAddClassPopUpScreen extends AbstractScreen {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_TIME);

  private static final String PATTERN_TIME = "HHmm";
  private static final String LESSON_MAIN_FORM_LOCATOR = "//div[@class='lesson-form__header']/..";
  private static final String CLASS_DATE_FILED_LOCATOR = "//div[@id='add-class-date-field']";
  private static final String POP_UP_LOCATOR = "//div[@class='uui-popper']";
  private static final String LESSON_ADVANCED_FORM_LOCATOR = "//div[@id='add-class-advanced-column']";
  private static final String TRAINER_NAME_PATTERN = POP_UP_LOCATOR + "//div[text()='%s']";
  private static final String FOOTER_LOCATOR = "//div[@id='add-class-footer']";
  private static final String DAY_OF_MONTH_PATTERN = "//div[@class='uui-calendar-day'][text()='%s']";
  private static final String MONTH_IN_CALENDAR_PATTERN =
      "//div[contains(@class,'uui-monthselection-month')][starts-with(text(),'%s')]";
  private static final String DAY_IN_CALENDAR_PATTERN =
      "//div[@class='uui-calendar-day'][text()='%s']";
  private static final int MAX_INDEX_OF_WEEK_DAY = 6;

  private Element classHeader = Element.byCss("div.lesson-form__header");
  private Element classForm = Element.byXpath(LESSON_MAIN_FORM_LOCATOR);
  private Element advancedSettingsSwitch = Element.byXpath(
      "//*[@class='lesson-form__header']/..//div[@class='uui-switch-toggler']");
  private Element classNameInput = Element
      .byXpath(LESSON_MAIN_FORM_LOCATOR + "/div[2]//input");
  private Element startDateInCalendarInput = Element
      .byXpath(CLASS_DATE_FILED_LOCATOR + "/div[1]//input");
  private Element startClassTimeInput = Element
      .byXpath(CLASS_DATE_FILED_LOCATOR + "/div[2]//input");
  private Element endClassTimeInput = Element
      .byXpath(CLASS_DATE_FILED_LOCATOR + "/div[3]//input");
  private Element currentDayButton = Element
      .byXpath(POP_UP_LOCATOR + "//div[contains(@class,'uui-calendar-current-day')]");
  private Element trainerInput = Element
      .byXpath(LESSON_ADVANCED_FORM_LOCATOR + "/div[1]//input");
  private Element addClassButton = Element
      .byXpath(FOOTER_LOCATOR + "//div[2]");
  private Element recurringClass = Element.byXpath(LESSON_ADVANCED_FORM_LOCATOR +
      "/following-sibling::div//div[@class='uui-checkbox']");
  private Element daysOfWeek = Element.byXpath(
      "//div[contains(@class,'repeat_label')]//div[contains(@class,'uui-button-box uui-enabled')]");
  private Element classRepeatCountField = Element.byXpath(LESSON_ADVANCED_FORM_LOCATOR +
      "/following-sibling::div//div[3]//input[@type='number']");
  private Element monthAndYearInCalendar = Element.byCss("div.uui-datepickerheader-nav-title");
  private Element monthSelectionContainer = Element.byCss("div.uui-monthselection-container");
  private Element datesOfMonthInCalendar = Element.byCss("div.uui-calendar-days");
  private Element endBy = Element.byXpath(LESSON_ADVANCED_FORM_LOCATOR +
      "/following-sibling::div//div[4]//div[contains(@class,'uui-radioinput')]");
  private Element endDateInCalendarInput = Element.byXpath(LESSON_ADVANCED_FORM_LOCATOR +
      "/following-sibling::div//div[4]//input");

  @Override
  public boolean isScreenLoaded() {
    return classHeader.isDisplayed();
  }

  public ReactAddClassPopUpScreen waitClassFormForVisibility() {
    classForm.waitForVisibility();
    return this;
  }

  public ReactAddClassPopUpScreen clickAdvancedSettingSwitch() {
    advancedSettingsSwitch.click();
    return this;
  }

  public ReactAddClassPopUpScreen typeClassName(String topicName) {
    classNameInput.type(topicName);
    return this;
  }

  public ReactAddClassPopUpScreen clickStartDateInCalendarInput() {
    startDateInCalendarInput.click();
    return this;
  }

  public ReactAddClassPopUpScreen clickEndDateInCalendarInput() {
    endDateInCalendarInput.click();
    return this;
  }

  public ReactAddClassPopUpScreen clickCurrentDayButton() {
    currentDayButton.click();
    return this;
  }

  public ReactAddClassPopUpScreen clickDayOfMonth(String dayOfMonth) {
    Element.byXpath(DAY_OF_MONTH_PATTERN, dayOfMonth).click();
    return this;
  }

  public ReactAddClassPopUpScreen clearAndTypeStartClassTimeInput(LocalTime startTime) {
    startClassTimeInput.nativeClearAndType(formatter.format(startTime));
    return this;
  }

  public ReactAddClassPopUpScreen clearAndTypeEndClassTimeInput(LocalTime endTime) {
    endClassTimeInput.nativeClearAndType(formatter.format(endTime));
    return this;
  }

  public ReactAddClassPopUpScreen clickHeadTrainerInput() {
    trainerInput.scrollAndClick();
    return this;
  }

  public ReactAddClassPopUpScreen clickTrainerField(String trainerName) {
    Element.byXpath(TRAINER_NAME_PATTERN, trainerName).click();
    return this;
  }

  public ReactTrainingScreen clickAddClassButton() {
    addClassButton.click();
    return new ReactTrainingScreen();
  }

  public ReactAddClassPopUpScreen clickRecurringClass() {
    recurringClass.click();
    return this;
  }

  public List<Element> getWeekDaysList() {
    return daysOfWeek.getElements();
  }

  public ReactAddClassPopUpScreen clickDaysOfRepeated(int count, int currentDayOfWeek) {
    for (int index = currentDayOfWeek;
        index < currentDayOfWeek + count && index <= MAX_INDEX_OF_WEEK_DAY; index++) {
      getWeekDaysList().get(index).click();
    }
    return this;
  }

  public ReactAddClassPopUpScreen clickDaysOfRepeatedIfCurrentIndexOfWeekDayMoreThanFour(int count,
      int currentDayOfWeek) {
    if (currentDayOfWeek > 4) {
      for (int index = 0; index < ((currentDayOfWeek + count) - 1) % MAX_INDEX_OF_WEEK_DAY;
          index++) {
        getWeekDaysList().get(index).click();
      }
    }
    return this;
  }

  public ReactAddClassPopUpScreen typeClassRepeatCount(int count) {
    classRepeatCountField.nativeClearAndType(Integer.toString(count));
    return this;
  }

  public ReactAddClassPopUpScreen clickMonthAndYearInCalendar() {
    monthAndYearInCalendar.click();
    return this;
  }

  public ReactAddClassPopUpScreen clickMonthInCalendar(String month) {
    Element.byXpath(MONTH_IN_CALENDAR_PATTERN, month).click();
    return this;
  }

  public ReactAddClassPopUpScreen clickDayInCalendar(int day) {
    Element.byXpath(DAY_IN_CALENDAR_PATTERN, day).click();
    return this;
  }

  public ReactAddClassPopUpScreen waitMonthSelectionContainerForVisibility() {
    monthSelectionContainer.waitForVisibility();
    return this;
  }

  public ReactAddClassPopUpScreen waitDatesOfMonthInCalendarForVisibility() {
    datesOfMonthInCalendar.waitForVisibility();
    return this;
  }

  public ReactAddClassPopUpScreen clickChoiceEndBy() {
    endBy.click();
    return this;
  }

}
