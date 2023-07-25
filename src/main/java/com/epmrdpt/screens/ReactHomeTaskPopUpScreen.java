package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_MARK_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HOME_TASK_POPUP_SCREEN_HISTORY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HOME_TASK_POPUP_SCREEN_NEXT_STUDENT_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HOME_TASK_POPUP_SCREEN_PREVIOUS_STUDENT_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_TASK_CHECKED;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.CSS;

public class ReactHomeTaskPopUpScreen extends AbstractScreen {

  private static final String HOMETASK_MAIN_FORM_LOCATOR = "//div[contains(@class,'slick-slider')]/../../..";
  private static final String ESTIMATION_FORM_LOCATOR = HOMETASK_MAIN_FORM_LOCATOR + "/div[2]";
  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";

  private static final String ESTIMATION_BUTTON_OF_ONLINE_TASK_BY_MARK =
      ESTIMATION_FORM_LOCATOR + "/div[1]//div[text()='%d']";
  private static final String HOME_TASK_POP_UP = "//div[contains(@class,'uui-modal-window')]";
  private static final String ATTACHED_FILE_LOCATOR = HOME_TASK_POP_UP + "//div[text()='%s']";

  private Element closePopUpButton = Element
      .byXpath(HOME_TASK_POP_UP + "/div[1]//div[contains(@class,'uui-icon')]");
  private Element commentForStudentInput = Element
      .byXpath(ESTIMATION_FORM_LOCATOR + "/div[2]//*[contains(@class,'uui-input')]/*");
  private Element commentForTrainerInput = Element
      .byXpath(ESTIMATION_FORM_LOCATOR + "/div[3]//*[contains(@class,'uui-input')]/*");
  private Element submitButton = Element
      .byXpath(HOME_TASK_POP_UP + "/div[2]/div[2]/div/div[2]");
  private Element commentForStudentField = Element
      .byXpath(HOMETASK_MAIN_FORM_LOCATOR + "/div/div[2]//div[@class='comment-wrapper']");
  private Element commentForTrainerField = Element
      .byXpath(HOMETASK_MAIN_FORM_LOCATOR + "/div/div[4]//div[@class='comment-wrapper']");
  private Element markField = Element
      .byXpath(ESTIMATION_FORM_LOCATOR + "//span[@id='submitted-mark']");
  private Element cancelReviewButton = Element
      .byXpath(HOME_TASK_POP_UP + "/div[2]//div[contains(@class,'button')]/div[text()]");
  private Element confirmationCancelReviewButton = Element
      .byXpath("//div[contains(@class,'uui-modal-window')]/div[3]/div[1]/div[1]/div[2]");
  private Element statusBlock = Element
      .byXpath(HOMETASK_MAIN_FORM_LOCATOR + "//div[@class='status-block']");
  private Element taskHistoryButton = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_HOME_TASK_POPUP_SCREEN_HISTORY_BUTTON));
  private Element spinnerOfLoading = Element.byXpath("//div[contains(@class,'uui-modal-window')]"
      + "//div[contains(@class,'uui-spinner-container')]");
  private Element downloadButton = Element
      .byXpath("//div[contains(@class,'uui-modal-window')]//div[@class='files-wrap']//a");
  private Element studentCounterField = Element.byXpath(HOME_TASK_POP_UP +
          "/child::div[3]/div/div/div/div[2]");
  private Element studentNameField = Element.byXpath("//*[@data-index=0]//div[text()][1]");
  private Element marksBlock = Element.byXpath(TEXT_LOCATOR_PATTERN +
          "/following-sibling::div/div/div", getValueOf(LEARNING_TASK_TAB_MARK_LABEL));

  @Override
  public boolean isScreenLoaded() {
    return submitButton.isPresent();
  }

  public ReactHomeTaskPopUpScreen clickEstimationButtonByMark(int mark) {
    Element.byXpath(ESTIMATION_BUTTON_OF_ONLINE_TASK_BY_MARK, mark).scrollAndClick();
    return this;
  }

  public ReactHomeTaskPopUpScreen typeCommentForStudent(String text) {
    commentForStudentInput.type(text);
    return this;
  }

  public ReactHomeTaskPopUpScreen typeCommentForTrainer(String text) {
    commentForTrainerInput.type(text);
    return this;
  }

  public ReactHomeTaskPopUpScreen clickSubmitButton() {
    submitButton.click();
    return this;
  }

  public ReactHomeTaskPopUpScreen clickClosePopUpButton() {
    closePopUpButton.click();
    return this;
  }

  public String getCommentForStudentText() {
    commentForStudentField.scrollIntoView();
    return commentForStudentField.getText();
  }

  public String getCommentForTrainerText() {
    return commentForTrainerField.getText();
  }

  public int getMarkValue() {
    return Integer.parseInt(markField.getText());
  }

  public ReactHomeTaskPopUpScreen clickCancelReviewButton() {
    cancelReviewButton.click();
    return this;
  }

  public ReactHomeTaskPopUpScreen clickConfirmationReviewButton() {
    confirmationCancelReviewButton.click();
    return this;
  }

  public ReactHomeTaskPopUpScreen waitForInvisibleEstimationButtonByMark(int mark) {
    Element.byXpath(ESTIMATION_BUTTON_OF_ONLINE_TASK_BY_MARK, mark).waitForDisappear();
    return this;
  }

  public ReactHomeTaskPopUpScreen waitForCheckedStatusHasDisappeared() {
    statusBlock.waitTextNotToBePresentInElement(getCheckedStatusText());
    return this;
  }

  public String getCheckedStatusText() {
    return getValueOf(REACT_TRAINING_TASK_CHECKED).split(":")[0];
  }

  public String getStatusBlockText() {
    return statusBlock.getText();
  }

  public ReactTaskHistoryPopUpScreen clickTaskHistoryButton() {
    taskHistoryButton.waitForClickableAndClick();
    return new ReactTaskHistoryPopUpScreen();
  }

  public ReactHomeTaskPopUpScreen waitSubmitButtonIsPresented() {
    submitButton.waitForVisibility();
    return this;
  }

  public ReactHomeTaskPopUpScreen waitSpinnerOfLoadingDisappear() {
    spinnerOfLoading.waitForDisappear();
    return this;
  }

  public boolean isAttachedFileDisplayed(String fileName) {
    return Element.byXpath(String.format(ATTACHED_FILE_LOCATOR, fileName)).isDisplayed();
  }

  public ReactHomeTaskPopUpScreen clickDownloadButton() {
    downloadButton.click();
    return this;
  }

  public boolean isNextStudentLinkPresent() {
    return Element.byXpath(TEXT_LOCATOR_PATTERN,
        getValueOf(REACT_HOME_TASK_POPUP_SCREEN_NEXT_STUDENT_LINK)).isPresent();
  }

  public ReactHomeTaskPopUpScreen clickNextStudentLink() {
    Element.byXpath(TEXT_LOCATOR_PATTERN,
        getValueOf(REACT_HOME_TASK_POPUP_SCREEN_NEXT_STUDENT_LINK)).click();
    return this;
  }

  public ReactHomeTaskPopUpScreen clickPreviousStudentLink() {
    Element.byXpath(TEXT_LOCATOR_PATTERN,
        getValueOf(REACT_HOME_TASK_POPUP_SCREEN_PREVIOUS_STUDENT_LINK)).click();
    return this;
  }

  public String getStudentCounterText(){
    return studentCounterField.getText();
  }

  public int getStudentCounterField() {
    return Integer.parseInt(Arrays.stream(getStudentCounterText().split(" / "))
        .findFirst().get());
  }

  public String getStudentNameFieldText() {
    return studentNameField.getText();
  }

  public String getCommentForStudentInputText(){
    return commentForStudentInput.getText();
  }

  public String getCommentForTrainerInputText(){
    return commentForTrainerInput.getText();
  }

  public List<String> getMarksBlocksBackgroundColour() {
    return marksBlock.getElements().stream()
        .map(i -> i.getCssValue(CSS.Attribute.BACKGROUND_COLOR.toString()))
        .collect(Collectors.toList());
  }
}
