package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.element.Element;

public class AttendanceJournalScreen extends GroupsCommonTabScreen {

  private Element addLesson = Element.byXpath("//button[@ng-click='openLessonDialog()']");

  @Override
  public boolean isScreenLoaded() {
    return isAddLessonDisplayed();
  }

  public boolean isAddLessonDisplayed() {
    return addLesson.isDisplayed();
  }

}
