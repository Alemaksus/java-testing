package com.epmrdpt.screens;

import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElement;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElementEnum;
import java.time.Duration;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FAQScreen extends AbstractScreen {

  private static final String QUESTION_CROSS_ELEMENT_CSS_SELECTOR = "div.accordion-item__opener";
  private static final String CSS_TRANSFORM_PROPERTY_NAME = "transform";
  private static final String NOT_ROTATED_CROSS_TRANSFORM_PROPERTY_VALUE = "matrix(0, 1, -1, 0, 0, 0)";
  private static final String ROTATED_CROSS_TRANSFORM_PROPERTY_VALUE = "matrix(0.707107, 0.707107, -0.707107, 0.707107, 0, 0)";
  private static final String BLOCK_DISPLAY_CSS_VALUE = "block";
  private static final String QUESTION_LOCATOR = "//label[contains(text(),'%s')]";
  private static final String ANSWER_LOCATOR = "//label[contains(text(),'%s')]/parent::div/following-sibling::div/article";
  private static final String ANSWER_EXPANDER = "//label[contains(text(),'%s')]/div";
  private static final String EDIT_BUTTON_LOCATOR = "//label[contains(text(),'%s')]/parent::div/following-sibling::div/button[1]";
  private static final String DELETE_BUTTON_LOCATOR = "//label[contains(text(),'%s')]/parent::div/following-sibling::div/button[2]";
  private static final String TEXT_LOCATOR_PATTERN = "//*[contains(text(),'%s')]";

  private Element bannerText = Element.byCss("div h1");
  private Element questionsContainer = Element.byClassName("panel");
  private Element banner = Element.byCss("div.hero-banner.hero-banner--faq");
  private Element homeNavigationLinkElement = Element.byXpath("//li/a[@href='/#!/Home']");
  private Element questionAccordionWElement = Element.byXpath("//div[@*='collapse']");
  private Element answerAccordionElement = Element.byXpath("//article/parent::*");
  private Element plusItemQuestion = Element.byXpath("//div[@class='accordion-item__opener']");
  private Element addQuestionButton = Element.byXpath("//button[@ng-click='displayModal()']");
  private Element deleteTitle = Element.byXpath("//h4[contains(@class,'modal-title')]");
  private Element deleteButtonInPopUp = Element.byXpath("//button[@ng-click='ok()']");
  private Element skillsListSection = Element.byXpath("//div[@class='skills-list ng-isolate-scope']");
  private Element titleOurSkillsSection = Element.byXpath("//div[@class='section-ui__title ng-binding']");
  private Element footer = Element.byXpath("//*[@id='footer']");
  private Element skillsBlock = Element.byXpath("//section[@class='container section-ui']");
  private Element skillCardIcon = Element.byXpath("//img[@class='skill-item__icon']");
  private Element skillName = Element.byXpath("//div[@class='skill-item__title ng-binding']");
  private Element timeZoneCrossButton = Element.byXpath("//i[contains(@class,'timezone-modal__close')]");

  public boolean isBannerFAQDisplayed() {
    return bannerText.isDisplayed();
  }

  public boolean isScreenLoaded() {
    return isQuestionsContainerDisplayed() && isBannerFAQDisplayed();
  }

  public String getBannerText() {
    return bannerText.getText();
  }

  public String getBannerBackground() {
    return banner.getCssValue(Attribute.BACKGROUND.toString());
  }

  public boolean isQuestionsContainerDisplayed() {
    return questionsContainer.isDisplayed();
  }

  public boolean isHomeNavigationLinkDisplayed() {
    return homeNavigationLinkElement.isDisplayed();
  }

  public String getHomeNavigationLinkText() {
    return homeNavigationLinkElement.getText();
  }

  public List<Element> getAllQuestionsElements() {
    return questionAccordionWElement.getElements();
  }

  private List<Element> getAllElementForAnswer() {
    return answerAccordionElement.getElements();
  }


  public boolean isQuestionCrossElementRotated(int index) {
    String actual = getPseudoElementCssPropertyValue(QUESTION_CROSS_ELEMENT_CSS_SELECTOR,
        CSS_TRANSFORM_PROPERTY_NAME, PseudoElementEnum.BEFORE, index);
    switch (actual) {
      case ROTATED_CROSS_TRANSFORM_PROPERTY_VALUE:
        return true;
      case NOT_ROTATED_CROSS_TRANSFORM_PROPERTY_VALUE:
        return false;
      default:
        throw new IllegalStateException(
            "Cross is in unexpected state. 'Transform' property value: " + actual);
    }
  }

  public int getQuestionsQuantity() {
    return getAllQuestionsElements().size();
  }

  private boolean isCrossRotated(int questionIndex) {
    String actual = getPseudoElementCssPropertyValue(QUESTION_CROSS_ELEMENT_CSS_SELECTOR,
        CSS_TRANSFORM_PROPERTY_NAME, PseudoElementEnum.BEFORE, questionIndex);
    return actual.equals(ROTATED_CROSS_TRANSFORM_PROPERTY_VALUE);
  }

  public void expandCollapseQuestionElement(int questionIndex) {
    if (!isCrossRotated(questionIndex)) {
      getAllQuestionsElements().get(questionIndex).click();
      waitUntilPseudoElementGetValue(QUESTION_CROSS_ELEMENT_CSS_SELECTOR,
          CSS_TRANSFORM_PROPERTY_NAME, ROTATED_CROSS_TRANSFORM_PROPERTY_VALUE,
          PseudoElementEnum.BEFORE,
          questionIndex);
    } else {
      getAllQuestionsElements().get(questionIndex).click();
      waitUntilPseudoElementGetValue(QUESTION_CROSS_ELEMENT_CSS_SELECTOR,
          CSS_TRANSFORM_PROPERTY_NAME, NOT_ROTATED_CROSS_TRANSFORM_PROPERTY_VALUE,
          PseudoElementEnum.BEFORE, questionIndex);
    }
  }

  public boolean isAnswerDisplayedByIndex(int index) {
    return getAllElementForAnswer().get(index).getCssValue(Attribute.DISPLAY.toString())
        .equals(BLOCK_DISPLAY_CSS_VALUE);
  }

  public boolean isQuestionHasPlusButton(int index) {
    getAllQuestionsElements().get(index).click();
    return getAllPlusFromQuestions().get(index).getCssValue(Attribute.DISPLAY.toString())
        .equals(BLOCK_DISPLAY_CSS_VALUE);
  }

  private List<Element> getAllPlusFromQuestions() {
    return plusItemQuestion.getElements();
  }

  public boolean isQuestionsHaveTitle(int index) {
    return getAllQuestionsElements().get(index).getText().length() > 0;
  }

  private String getPseudoElementCssPropertyValue(String elementCssSelector,
      String propertyName, PseudoElementEnum pseudoElement, int index) {
    return new PseudoElement()
        .getPseudoElementCssPropertyValueByIndex(elementCssSelector, propertyName, pseudoElement,
            index);
  }

  private void waitUntilPseudoElementGetValue(String cssSelector, String propertyName,
      String value, PseudoElementEnum pseudoElement, int questionIndex) {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver webDriver) {
            if (getPseudoElementCssPropertyValue(cssSelector,
                propertyName, pseudoElement, questionIndex).equals(value)) {
              return true;
            }
            return false;
          }
        });
  }

  public FAQScreen clickAddQuestionButton() {
    addQuestionButton.click();
    return this;
  }

  public boolean isQuestionByNameDisplayed(String question) {
    return Element.byXpath(format(QUESTION_LOCATOR, question)).isDisplayed();
  }

  public FAQScreen clickAnswerExpanderByQuestion(String question) {
    Element.byXpath(format(ANSWER_EXPANDER, question)).mouseOverAndClick();
    return this;
  }

  public String getAnswerInputByQuestionName(String question) {
    return Element.byXpath(format(ANSWER_LOCATOR, question)).getText();
  }

  public FAQScreen clickEditButtonByQuestion(String question) {
    Element.byXpath(format(EDIT_BUTTON_LOCATOR, question)).click();
    return this;
  }

  public FAQScreen clickDeleteButtonByQuestion(String question) {
    Element.byXpath(format(DELETE_BUTTON_LOCATOR, question)).click();
    return this;
  }

  public boolean isDeletePopUpDisplayed() {
    return deleteTitle.isDisplayed();
  }

  public FAQScreen clickDeleteButtonInPopUp() {
    deleteButtonInPopUp.click();
    return this;
  }

  public boolean isOurSkillsSectionDisplayed() {
    skillsListSection.mouseOver();
    return titleOurSkillsSection.isDisplayed() && skillsListSection.isDisplayed();
  }

  public boolean isSkillsBlockAboveFooterDisplayed() {
    return footer.getWrappedWebElement().getLocation().getY()
        > skillsBlock.getWrappedWebElement().getLocation().getY();
  }

  public boolean isAllSkillCardIconsAndSkillNameDisplayed() {
    return skillCardIcon.isAllElementsDisplayed() && skillName.isAllElementsDisplayed();
  }

  public FAQScreen closeTimeZonePopUpIfDisplayed() {
    if(timeZoneCrossButton.isDisplayed()) {
      timeZoneCrossButton.click();
    }
    return this;
  }

  public FAQScreen refreshPageUntilElementByTextAppear(String elementText) {
    for (int i = 0; i < 3; i++) {
      clickRefreshButton();
      this.isScreenLoaded();
      if (isElementByTextPresentNoWait(elementText)) {
        break;
      }
    }
    return this;
  }

  public FAQScreen refreshPageUntilElementByTextDisappear(String elementText) {
    for (int i = 0; i < 3; i++) {
      clickRefreshButton();
      this.isScreenLoaded();
      if (!isElementByTextPresentNoWait(elementText)) {
        break;
      }
    }
    return this;
  }

  public boolean isElementByTextPresentNoWait(String elementText) {
    return Element.byXpath(format(TEXT_LOCATOR_PATTERN, elementText)).isPresentNoWait();
  }
}
