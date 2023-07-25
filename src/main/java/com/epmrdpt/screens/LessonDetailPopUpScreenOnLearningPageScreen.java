package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_DEPARTMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_FACULTY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_GROUP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LESSON_DETAIL_POP_UP_LOCATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TYPE_OF_CLASS;
import static java.lang.String.format;

import com.epmrdpt.bo.LessonDetails;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class LessonDetailPopUpScreenOnLearningPageScreen extends AbstractScreen {

  private static final String LESSON_DETAILS_LOCATOR = "//div[@class='lesson-info']//div[contains(text(),'%s')]";
  private static final String LESSON_SUBTITLE_LOCATOR =
      "//div[contains(@class,'lesson-info')]/div[contains(@class,'title') and contains(text(), '%s')]";
  private static final String DEPARTMENT = getValueOf(LESSON_DETAIL_POP_UP_DEPARTMENT);
  private static final String FACULTY = getValueOf(LESSON_DETAIL_POP_UP_FACULTY);
  private static final String GROUP = getValueOf(LESSON_DETAIL_POP_UP_GROUP);
  private static final String LOCATION = getValueOf(LESSON_DETAIL_POP_UP_LOCATION);
  private static final String TYPE_OF_CLASS = getValueOf(REACT_TRAINING_TYPE_OF_CLASS);

  private Element popUpContainer = Element.byClassName("modal-content");
  private Element lessonDetailTitle = Element.byClassName("popup-title__name");
  private Element trainingOrClassName = Element.byClassName("task-header__subject");
  private Element topicName = Element.byXpath("//div[@class='task-header']/div[1]");
  private Element trainingType = Element.byClassName("speciality");
  private Element dateAndTime = Element.byClassName("task-header__date-time");
  private Element lessonDate = Element.byCss("div.task-header__wrapper div.date");
  private Element actualTime = Element.byCss("div.task-header__wrapper div.time");
  private Element trainersName = Element.byCss("p.name");
  private Element trainersProfileImage = Element.byCss("div.icon-trainer");
  private Element userRole = Element.byXpath("//div[contains(@class,'name-trainer')]//p[2]");
  private Element groupSubtitle = Element
      .byXpath(format(LESSON_SUBTITLE_LOCATOR, GROUP));
  private Element lessonGroup = Element.byXpath(
      "//div[contains(@class,'lesson-info')]/div[contains(@ng-if,'!calendarEvent.isSubject')]");
  private Element lessonGroupForDAClass = Element.byXpath(
      "//div[contains(text(),'%s')]/following-sibling::div[contains(@class,'info')]", GROUP);
  private Element locationSubtitle = Element
      .byXpath(format(LESSON_SUBTITLE_LOCATOR, LOCATION));
  private Element lessonLocation = Element
      .byXpath("//div[contains(@class,'lesson-info')]/div[contains(@ng-if,'locationName') and contains(@class, 'info')]");
  private Element lessonType = Element
      .byXpath("//div[contains(@class,'lesson-info')]/div[contains(@class,'info ng-binding')][1]");
  private Element lessonDescription = Element
      .byXpath("//div[contains(@class,'lesson-info')]/div[contains(@class,'info ng-scope')][1]/span");
  private Element closeLessonDetailsPopUp = Element.byClassName("popup-title__close");
  private Element departmentInput = Element.byXpath("//div[@class='info ng-binding']");
  private Element facultyInput = Element.byXpath("(//div[@class='info ng-binding'])[2]");
  private Element typeOfClassInput = Element
      .byXpath("//div[contains(@class,'lesson-info')]/div[contains(@class,'info ng-binding')]");
  private Element groupInput = Element
      .byXpath("//div[contains(@class,'lesson-info')]/div[contains(@class,'info ng-binding')][2]");
  private Element locationInput = Element.byXpath("//span[contains(@class,'plan-text')]");


  @Override
  public boolean isScreenLoaded() {
    return popUpContainer.isDisplayed();
  }

  public boolean isLessonDetailTitleDisplayed() {
    return lessonDetailTitle.isDisplayed();
  }

  public String getLessonDetailTitleText() {
    return lessonDetailTitle.getText();
  }

  public String getLessonDetailTopicText() {
    return topicName.getText().split("\\s")[0];
  }

  public String getLessonDetailLocationText() {
    return lessonLocation.getText();
  }

  public String getLessonDetailTypeText() {
    return lessonType.getText();
  }

  public String getLessonDetailDescriptionText() {
    return lessonDescription.getText();
  }

  public boolean isTrainingOrClassNameDisplayed() {
    return trainingOrClassName.isDisplayed();
  }

  public boolean isTopicNameDisplayed() {
    return topicName.isDisplayed();
  }

  public boolean isTrainingTypeDisplayed() {
    return trainingType.isDisplayed();
  }

  public boolean isDateAndTimeDisplayed() {
    return dateAndTime.isDisplayed();
  }

  public String getDateAndTimeText() {
    return dateAndTime.getText();
  }

  public boolean isTrainersNameDisplayed() {
    return trainersName.isDisplayed();
  }

  public boolean isTrainersProfileImageDisplayed() {
    return trainersProfileImage.isDisplayed();
  }

  public boolean isUserRoleDisplayed() {
    return userRole.isDisplayed();
  }

  public boolean isGroupSubtitleDisplayed() {
    return groupSubtitle.isDisplayed();
  }

  public String getGroupSubtitleText() {
    return groupSubtitle.getText();
  }

  public boolean isLessonGroupDisplayed() {
    return lessonGroup.isDisplayed();
  }

  public boolean isLessonGroupForDAClassDisplayed() {
    return lessonGroupForDAClass.isDisplayed();
  }

  public boolean isLocationSubtitleDisplayed() {
    return locationSubtitle.isDisplayed();
  }

  public String getLocationSubtitleText() {
    return locationSubtitle.getText();
  }

  public boolean isLessonLocationDisplayed() {
    return lessonLocation.isDisplayed();
  }

  public boolean isLessonDateDisplayed() {
    return lessonDate.isDisplayed();
  }

  public String getLessonDateText() {
    return lessonDate.getText();
  }

  public boolean isActualTimeDisplayed() {
    return actualTime.isDisplayed();
  }

  public String getActualTimeText() {
    return actualTime.getText();
  }

  public boolean isPopUpTitleDisplayed() {
    return lessonDetailTitle.isDisplayed();
  }

  public boolean isGroupTitleInDALessonDisplayed() {
    return Element.byXpath(LESSON_DETAILS_LOCATOR, GROUP).isDisplayed();
  }

  public boolean isLocationTitleInDALessonDisplayed() {
    return Element.byXpath(LESSON_DETAILS_LOCATOR, LOCATION).isDisplayed();
  }

  public boolean isDepartmentTitleInDALessonDisplayed() {
    return Element.byXpath(LESSON_DETAILS_LOCATOR, DEPARTMENT).isDisplayed();
  }

  public boolean isFacultyTitleInDALessonDisplayed() {
    return Element.byXpath(LESSON_DETAILS_LOCATOR, FACULTY).isDisplayed();
  }

  public LessonDetailPopUpScreenOnLearningPageScreen clickCrossButtonOfPopUp() {
    closeLessonDetailsPopUp.click();
    return this;
  }

  public String getTrainingTypeText() {
    return trainingType.getText();
  }

  public boolean isTypeOfClassDisplayed() {
    return Element.byXpath(LESSON_DETAILS_LOCATOR, TYPE_OF_CLASS).isDisplayed();
  }

  public String getDepartmentInputText() {
    return departmentInput.getText();
  }

  public String getFacultyInputText() {
    return facultyInput.getText();
  }

  public String getTypeOfClassInputText() {
    return typeOfClassInput.getText();
  }

  public String getGroupInputText() {
    return groupInput.getText();
  }

  public String getLocationInputText() {
    return locationInput.getText();
  }

  public LessonDetails getLessonDetailsFromPopUp() {
    LessonDetails lessonDetails = new LessonDetails();
    return lessonDetails
        .withTopic(getLessonDetailTopicText())
        .withDescription(getLessonDetailDescriptionText())
        .withType(getLessonDetailTypeText())
        .withLocation(getLessonDetailLocationText());
  }
}
