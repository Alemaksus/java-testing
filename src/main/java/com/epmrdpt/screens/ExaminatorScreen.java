package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ExaminatorScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String BEFORE_START_POPUP_PATTERN =
      "//div[contains(@class,'ModalConfirm__modalWrapper__wrap')]";
  private Element examinatorHeader = Element.byCss("div.Header__header__title___OWw88");
  private Element registrationTestDetails = Element
      .byXpath("//div[@class='page__content']/div[contains(@class,'PassingAttendanceDetails')]");
  private Element pageEnglishTestTitle = Element
      .byXpath("//div[contains(text(),'english')]");
  private final Element firstRadioButton = Element.byXpath("(//div[@class='uui-radioinput'])[1]");
  private final Element startButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Start");
  private final Element currentQuestion = Element.byXpath("//div[contains(@class,'current-question')]");
  private final Element nextQuestionButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Next question");
  private final Element submitButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Submit");
  private final Element finishButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Finish");
  private final Element resultButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Result");
  private final Element popUpStartButton = Element.byXpath(BEFORE_START_POPUP_PATTERN +
      TEXT_LOCATOR_PATTERN, "Start");
  private final Element beforeStartEnglishTestPopUp = Element.byXpath(BEFORE_START_POPUP_PATTERN);

  @Override
  public boolean isScreenLoaded() {
    return examinatorHeader.isDisplayed();
  }

  public ExaminatorScreen waitScreenLoaded() {
    examinatorHeader.waitForVisibility();
    return this;
  }

  public boolean isEnglishTestPageLoaded() {
    return pageEnglishTestTitle.isDisplayed();
  }

  public boolean isRegistrationTestPageLoaded() {
    return registrationTestDetails.isDisplayed();
  }

  public ExaminatorScreen waitPageEnglishTestTitleForVisibility() {
    pageEnglishTestTitle.waitForVisibility(LONG_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public ExaminatorScreen clickStartButton() {
    startButton.clickJs();
    return this;
  }

  public ExaminatorScreen waitCurrentQuestionVisibility() {
    currentQuestion.waitForVisibility();
    return this;
  }

  public ExaminatorScreen clickSubmitButton() {
    submitButton.clickJs();
    return this;
  }

  public ExaminatorScreen clickNextQuestionButton() {
    nextQuestionButton.clickJs();
    return this;
  }

  public ExaminatorScreen clickFirstRadioButton() {
    firstRadioButton.clickJs();
    return this;
  }

  public ExaminatorScreen clickFinishButton() {
    finishButton.clickJs();
    return this;
  }

  public ExaminatorScreen clickResultButton() {
    resultButton.clickJs();
    return this;
  }

  public boolean isFinishButtonDisplayed() {
    return finishButton.isDisplayed(SMALL_TIME_OUT_IN_SECONDS);
  }

  public ExaminatorScreen waitBeforeStartEnglishTestVisibility() {
    beforeStartEnglishTestPopUp.waitForVisibility();
    return this;
  }

  public ExaminatorScreen clickPopUpStartButton() {
    popUpStartButton.click();
    return this;
  }
}
