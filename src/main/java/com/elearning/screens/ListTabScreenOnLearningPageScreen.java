package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LIST_TAB_LEARNING_HOME_CLASS_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LIST_TAB_LEARNING_HOME_LESSON_DATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TOPIC_HEADER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MY_GROUPS_TAB_GROUP_NAME;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TYPE_OF_CLASS;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ListTabScreenOnLearningPageScreen extends AbstractScreen {

  private static final String LESSON_BY_NAME_IN_LIST_TAB = "//div[@ng-include='templateUrls.desktop']//a[contains(text(),'%s')]";
  private static final String LESSON_DETAILS_HEADER_LOCATOR = "(//div[@class='list-tab']//div[contains(text(),'%s')])[1]";

  private Element listContainer = Element.byClassName("list-tab");
  private Element lessonTopic = Element.byXpath(
      "//div[contains(@class,'rd-table--desktop')]//a[contains(@class,'link-button')]");
  private Element lessonCalendar = Element.byXpath("//div[@class='month-event-container']");

  @Override
  public boolean isScreenLoaded() {
    return listContainer.isDisplayed();
  }

  private List<Element> getLessonTopicList() {
    return lessonTopic.getElements();
  }

  public int getLessonTopicListSize() {
    return getLessonTopicList().size();
  }

  private List<Element> getLessonCalendarList() {
    return lessonCalendar.getElements();
  }

  public int getLessonCalendarListSize() {
    return getLessonCalendarList().size();
  }

  public boolean isClassDateHeaderDisplayed() {
    return Element.byXpath(LESSON_DETAILS_HEADER_LOCATOR,
            getValueOf(LIST_TAB_LEARNING_HOME_LESSON_DATE)).isDisplayed();
  }

  public boolean isLessonNameHeaderDisplayed() {
    return Element.byXpath(LESSON_DETAILS_HEADER_LOCATOR,
            getValueOf(LIST_TAB_LEARNING_HOME_CLASS_NAME)).isDisplayed();
  }

  public LessonDetailPopUpScreenOnLearningPageScreen clickLessonTopicByIndex(int index) {
    getLessonTopicList().get(index).click();
    return new LessonDetailPopUpScreenOnLearningPageScreen();
  }

  public LessonDetailPopUpScreenOnLearningPageScreen clickLessonCalendarByIndex(int index) {
    getLessonCalendarList().get(index).click();
    return new LessonDetailPopUpScreenOnLearningPageScreen();
  }

  public LessonDetailPopUpScreenOnLearningPageScreen clickLessonTopicByName(String lessonName) {
    Element.byXpath(LESSON_BY_NAME_IN_LIST_TAB, lessonName).click();
    return new LessonDetailPopUpScreenOnLearningPageScreen();
  }

  public ListTabScreenOnLearningPageScreen waitLessonTopicVisibility() {
    lessonTopic.waitForVisibility();
    return this;
  }

  public boolean isTopicHeaderDisplayed() {
    return Element.byXpath(LESSON_DETAILS_HEADER_LOCATOR,
            getValueOf(REACT_TRAINING_CLASS_TOPIC_HEADER)).isDisplayed();
  }

  public boolean isTypeOfClassHeaderDisplayed() {
    return Element.byXpath(LESSON_DETAILS_HEADER_LOCATOR,
            getValueOf(REACT_TRAINING_TYPE_OF_CLASS)).isDisplayed();
  }

  public boolean isGroupNameHeaderDisplayed() {
    return Element.byXpath(LESSON_DETAILS_HEADER_LOCATOR,
            getValueOf(REACT_TRAINING_MY_GROUPS_TAB_GROUP_NAME)).isDisplayed();
  }
}
