package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CAREER_TEST_GET_RESULTS_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class CareerTestQuestionnaireScreen extends AbstractScreen {

  private static final String QUESTION_BLOCK_DISPLAYED_ON_CURRENT_PAGE_XPATH =
      "(//div[@ng-include='templateUrls.desktop'])[1]//div[@class='chooser-block']";
  private static final String QUESTION_BLOCK_LOCATOR_PATTERN =
      "(" + QUESTION_BLOCK_DISPLAYED_ON_CURRENT_PAGE_XPATH + ")[%s]";
  private static final String QUESTION_ANSWER_LOCATOR_PATTERN =
      QUESTION_BLOCK_LOCATOR_PATTERN + "/label[%s]/span";
  private static final String BUTTON_WITH_TEXT_LOCATOR_PATTERN = "//button[contains(text(),'%s')]";

  private final Element firstQuestionBlockOnPage = Element.byXpath(
      String.format(QUESTION_BLOCK_LOCATOR_PATTERN, 1));
  private final Element nextQuestionsPageButton = Element.byXpath(
      "//li[contains(@class,'pagination-next')]/a");
  private final Element getTestResultButton = Element.byXpath(
      String.format(BUTTON_WITH_TEXT_LOCATOR_PATTERN, getValueOf(CAREER_TEST_GET_RESULTS_BUTTON)));
  private final Element answeredQuestionBlock = Element.byXpath(
      "//div[contains(@class,'question-row-answered')]");
  private final Element questionBlockDisplayedOnCurrentPage = Element.byXpath(
      QUESTION_BLOCK_DISPLAYED_ON_CURRENT_PAGE_XPATH);

  @Override
  public boolean isScreenLoaded() {
    return firstQuestionBlockOnPage.isDisplayed();
  }

  public CareerTestQuestionnaireScreen waitScreenLoading() {
    firstQuestionBlockOnPage.waitForVisibility();
    return this;
  }

  public void clickAnswerInQuestion(int questionNumber, int answerNumber) {
    Element.byXpath(QUESTION_ANSWER_LOCATOR_PATTERN, questionNumber, answerNumber)
        .clickJs();
  }

  public void clickNextQuestionsPageButton() {
    nextQuestionsPageButton.clickJs();
  }

  public CareerTestResultScreen clickGetTestResultButton() {
    getTestResultButton.clickJs();
    return new CareerTestResultScreen();
  }

  public boolean isAnsweredQuestionPresent() {
    return answeredQuestionBlock.isPresent();
  }

  public int getQuestionsQuantityOnCurrentPage() {
    return questionBlockDisplayedOnCurrentPage.getElements().size();
  }
}
