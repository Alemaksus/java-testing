package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class AddQuestionFAQScreen extends AbstractScreen {

  private Element addNewQuestionTitle = Element.byXpath("//h4[contains(@class,'modal-title')]");
  private Element russianLanguageDropDownItem = Element
      .byXpath("//div[@class='chosen-drop']//ul[@class='chosen-results']/li[1]");
  private Element ukrainianLanguageDropDownItem = Element
      .byXpath("//div[@class='chosen-drop']//ul[@class='chosen-results']/li[2]");
  private Element languageInput = Element.byXpath("//input[contains(@class,'chosen-search-input')]");
  private Element questionInEnglishInput = Element.byName("main_question");
  private Element answerInEnglishInput = Element.byName("main_answer");
  private Element questionInRussiaInput = Element.byName("ru-RU");
  private Element answerInRussiaInput = Element.byName("Russian (Russia)");
  private Element questionInUkraineInput = Element.byName("uk-UA");
  private Element answerInUkraineInput = Element.byName("Ukrainian (Ukraine)");
  private Element saveButton = Element.byXpath("//button[@ng-click='ok()']");

  @Override
  public boolean isScreenLoaded() {
    return addNewQuestionTitle.isDisplayed();
  }

  public AddQuestionFAQScreen clickLanguageInputDropDown() {
    languageInput.clickJs();
    return this;
  }

  public AddQuestionFAQScreen clickRussianLanguageDropDownItem() {
    russianLanguageDropDownItem.mouseDown();
    russianLanguageDropDownItem.click();
    return this;
  }

  public AddQuestionFAQScreen clickUkrainianLanguageDropDownItem() {
    ukrainianLanguageDropDownItem.click();
    return this;
  }

  public AddQuestionFAQScreen enterQuestionInEnglishInput(String question) {
    questionInEnglishInput.type(question);
    return this;
  }

  public AddQuestionFAQScreen enterAnswerInEnglishInput(String answer) {
    answerInEnglishInput.type(answer);
    return this;
  }

  public AddQuestionFAQScreen enterQuestionInRussiaInput(String question) {
    questionInRussiaInput.type(question);
    return this;
  }

  public AddQuestionFAQScreen enterAnswerInRussiaInput(String answer) {
    answerInRussiaInput.type(answer);
    return this;
  }

  public AddQuestionFAQScreen enterQuestionInUkraineInput(String question) {
    questionInUkraineInput.type(question);
    return this;
  }

  public AddQuestionFAQScreen enterAnswerInUkraineInput(String answer) {
    answerInUkraineInput.type(answer);
    return this;
  }

  public AddQuestionFAQScreen clickSaveButton() {
    saveButton.click();
    return this;
  }
}
