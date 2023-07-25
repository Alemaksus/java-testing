package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TOPIC;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_CLASS_TRAINER;

import com.epmrdpt.bo.LessonDetails;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactLessonInfoPopUpScreen extends AbstractScreen {

  private static final String LESSON_INFO_POP_UP_LOCATOR = "//div[@id='class-info-popover']";
  private static final String LESSON_MAIN_INFO_LOCATOR = LESSON_INFO_POP_UP_LOCATOR + "/div[1]";
  private static final String LESSON_ADDITIONAL_INFO_LOCATOR =
      LESSON_INFO_POP_UP_LOCATOR + "/div[2]";
  private static final String BUTTON_SECTION_LOCATOR =
      LESSON_INFO_POP_UP_LOCATOR + "/div[1]/div[1]";

  private Element lessonInfo = Element.byXpath(LESSON_INFO_POP_UP_LOCATOR);
  private Element lessonAdditionalInfo = Element.byXpath(LESSON_ADDITIONAL_INFO_LOCATOR);
  private Element recurrentClassIcon = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[1]");
  private Element editIcon = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[2]/div[1]");
  private Element deleteIconButton = Element.byXpath(BUTTON_SECTION_LOCATOR + "/div[3]/div[1]");
  private Element goToJournalButton = Element.byXpath(LESSON_INFO_POP_UP_LOCATOR + "//a");
  private Element lessonDate = Element.byXpath(LESSON_MAIN_INFO_LOCATOR + "/div[2]/div[1]");
  private Element lessonTime = Element.byXpath(LESSON_MAIN_INFO_LOCATOR + "/div[3]/div[1]");
  private Element lessonGroupName = Element
      .byXpath(LESSON_MAIN_INFO_LOCATOR + "/div[3]/div[2]");
  private Element lessonLocationTitle = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
  private Element lessonLocation = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "/div[1]/div[1]/div[1]/div/div/div[2]");
  private Element lessonTypeTitle = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "//div[2]/div[1]/div[1]/div[1]");
  private Element lessonType = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "//div[2]//div[2]");
  private Element lessonTopicTitle = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "//div[3]/div[1]/div[1]/div[1]");
  private Element lessonTopic = Element.byXpath("//div[contains(text(),'"
      + LocaleProperties.getValueOf(REACT_TRAINING_CLASS_TOPIC) + "')]/following-sibling::div");
  private Element lessonDescriptionTitle = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "//div[4]/div[1]/div[1]/div[1]");
  private Element lessonDescription = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "//div[4]//div[2]");
  private Element lessonTrainerImage = Element
      .byXpath(LESSON_INFO_POP_UP_LOCATOR + "//div[text()='%s']/following-sibling::div",
          LocaleProperties.getValueOf(REACT_TRAINING_CLASS_TRAINER));
  private Element lessonTrainerTitle = Element
      .byXpath(LESSON_ADDITIONAL_INFO_LOCATOR + "//div[5]/div[1]/div[1]");
  private Element selectEditingApplyButton = Element.byXpath(
      "//div[contains(@class,'uui-modal-window')]/div[3]//div[2]");
  private Element tooltipOfTrainerName = Element.byCss("div.uui-tooltip-body");

  @Override
  public boolean isScreenLoaded() {
    return isLessonInfoDisplayed();
  }

  public boolean isLessonInfoDisplayed() {
    return lessonInfo.isDisplayed();
  }

  public ReactLessonInfoPopUpScreen waitLessonInfoForVisibility() {
    lessonInfo.waitForVisibility();
    return this;
  }

  public ReactLessonInfoPopUpScreen waitLessonAdditionalInfoForVisibility() {
    lessonAdditionalInfo.waitForVisibility();
    return this;
  }

  public ReactGroupJournalScreen clickGoToJournalButton() {
    goToJournalButton.click();
    return new ReactGroupJournalScreen();
  }

  public boolean isSelectEditingApplyButtonDisplayedNoWait() {
    return selectEditingApplyButton.isDisplayedNoWait();
  }

  public ReactEditClassPopUpScreen clickEditIcon() {
    editIcon.click();
    clickApplyButtonInSelectEditingIfNeeded();
    return new ReactEditClassPopUpScreen();
  }

  public ReactEditClassPopUpScreen clickApplyButtonInSelectEditingIfNeeded() {
    if (isSelectEditingApplyButtonDisplayedNoWait()) {
      selectEditingApplyButton.click();
    }
    return new ReactEditClassPopUpScreen();
  }

  public ReactTrainingScreen clickDeleteIconButton() {
    deleteIconButton.click();
    return new ReactTrainingScreen();
  }

  public ReactGroupJournalScreen clickDeleteIcon() {
    deleteIconButton.click();
    return new ReactGroupJournalScreen();
  }

  public boolean isLessonDateDisplayed() {
    return lessonDate.isDisplayed();
  }

  public String getDateLessonText() {
    return lessonDate.getText();
  }

  public boolean isEditIconButtonDisplayed() {
    return editIcon.isDisplayed();
  }

  public boolean isDeleteIconButtonDisplayed() {
    return deleteIconButton.isDisplayed();
  }

  public boolean isLessonTimeDisplayed() {
    return lessonTime.isDisplayed();
  }

  public String getTimeLessonText() {
    return lessonTime.getText();
  }

  public String getLessonGroupNameText() {
    return lessonGroupName.getText();
  }

  public String getLessonLocationTitleText() {
    return lessonLocationTitle.getText();
  }

  public String getLessonLocationText() {
    return lessonLocation.getText();
  }

  public String getLessonTypeTitleText() {
    return lessonTypeTitle.getText();
  }

  public String getLessonTypeText() {
    return lessonType.getText();
  }

  public String getLessonTopicTitleText() {
    return lessonTopicTitle.getText();
  }

  public ReactLessonInfoPopUpScreen waitLessonTopicForVisibility() {
    lessonTopic.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public String getLessonTopicText() {
    return lessonTopic.getText();
  }

  public String getLessonDescriptionTitleText() {
    return lessonDescriptionTitle.getText();
  }

  public String getLessonDescriptionText() {
    return lessonDescription.getText();
  }

  public String getLessonTrainerTitleText() {
    return lessonTrainerTitle.getText();
  }

  public boolean isLessonTrainerImageDisplayed() {
    return lessonTrainerImage.isDisplayed();
  }

  public String getGoToJournalButtonText() {
    return goToJournalButton.getText();
  }

  public boolean isRecurringClassIconDisplayed() {
    return recurrentClassIcon.isDisplayed();
  }

  public ReactLessonInfoPopUpScreen mouseOverTrainerImage() {
    lessonTrainerImage.mouseOver();
    return this;
  }

  public String getTrainerNameText() {
    return tooltipOfTrainerName.getText();
  }

  public ReactLessonInfoPopUpScreen moveLessonInfoPopUpToTheTopOfTheScreen() {
    lessonTrainerImage.mouseDown();
    return this;
  }

  public LessonDetails getLessonDetails() {
    LessonDetails lessonDetails = new LessonDetails();
    return lessonDetails
        .withTopic(getLessonTopicText())
        .withDescription(getLessonDescriptionText())
        .withType(getLessonTypeText())
        .withLocation(getLessonLocationText());
  }
}
