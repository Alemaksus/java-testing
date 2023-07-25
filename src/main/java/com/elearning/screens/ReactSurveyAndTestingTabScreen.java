package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DETAIL_TRAINING_SCREEN_ASSIGNMENT_CUSTOM_DEADLINE_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_LINK_BY_ID_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_STANDARD_SURVEY_TYPE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SURVEY_SCREEN_QUESTION;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReactSurveyAndTestingTabScreen extends ReactAdditionalInfoTabScreen {

  private static final String COLUMN_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String TEXT_LOCATOR_PATTERN = "//*[contains(text(),'%s')]";
  private static final String PRE_FILLED_TEXT_LOCATOR_PATTERN = TEXT_LOCATOR_PATTERN +
      "/../../../following-sibling::div//*[contains(@class,'uui-input')]//*";
  private final String LABEL_LOCATOR_PATTERN = "//div[@class='uui-label' and contains(text(),'%s')]";
  private final String INPUT_LOCATOR_BY_LABEL_PATTERN =
      LABEL_LOCATOR_PATTERN + "/../../../following-sibling::div//input";

  private Element useEnglishResultsCheckBox = Element.byXpath(
      "//*[contains(@class, 'uui-checkbox')]");
  private Element minimumEnglishLevelInput = Element.byXpath("//input[@placeholder='%s']",
      getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_INPUT));
  private Element assignmentContainerLink = Element.byXpath(
      "//a[contains(@href,'AssignmentContainersList')]");
  private Element linkByIdButton = Element.byXpath("//div[contains(text(),'%s')]",
      getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LINK_BY_ID_BUTTON));
  private Element bucketButton = Element
      .byXpath("//div[contains(@class,'uui-table-row-container')]//div[contains(@class,'button')]"
          + "//div[contains(@class,'icon')]//*[contains(@d,'M14')]");
  private Element confirmationDeleteButton = Element.byXpath("(//div[contains(text(),'%s')])[2]",
      getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_CONFIRMATION_BUTTON));
  private Element iDColumnInput = Element.byXpath("//a[contains(@href,'/details')]");
  private Element titleColumnInput = Element.byXpath("(//a[contains(@href,'/details')])[2]");
  private Element subtitleColumnInput = Element.byXpath(
      "//a[contains(@href,'/details')]/../..//div//span");
  private Element customDeadlineColumn = Element.byXpath("//span[text()='%s']",
      getValueOf(DETAIL_TRAINING_SCREEN_ASSIGNMENT_CUSTOM_DEADLINE_LABEL));
  private Element customDeadlineColumnInput = Element.byXpath(
      "(//a[contains(@href,'/details')]/../..//div//span)[2]");
  private Element deadlineColumnInput = Element.byXpath(
      "(//a[contains(@href,'/details')]/../..//div//span)[3]");
  private Element englishLevelTitle = Element.byXpath("//div[text()='%s']",
      getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_LEVEL_TITLE));
  private Element surveyTypeDropDown = Element.byXpath("//input[@placeholder='%s']",
      getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_SURVEY_TYPE_INPUT));
  private Element standardSurveyType = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_SELECT_STANDARD_SURVEY_TYPE_INPUT));
  private Element DDL = Element.byXpath("//div[@class='uui-popper']");
  private Element answerOptionLabel = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_LABEL));
  private Element questionLabel = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(SURVEY_SCREEN_QUESTION));
  private Element confirmationToaster = Element.byXpath(
      "//div[@class='uui-snackbar-item-wrapper-self']");
  private Element confirmationToasterCrossButton = Element.byXpath(
      "//*[contains(@class, 'uui-snackbar-item-wrapper')]//div[contains(@class, 'uui-button-box')]");

  public ReactSurveyAndTestingTabScreen clickUseEnglishResultsCheckbox() {
    useEnglishResultsCheckBox.click();
    return this;
  }

  public boolean isEnglishLevelTitleDisplayed() {
    return englishLevelTitle.isDisplayed();
  }

  public boolean isMinimumEnglishLevelInputDisplayed() {
    return minimumEnglishLevelInput.isDisplayed();
  }

  public Boolean isAssignmentContainerLinkExists() {
    return assignmentContainerLink.isDisplayedNoWait();
  }

  public ReactSurveyAndTestingTabScreen waitScreenLoading() {
    useEnglishResultsCheckBox.waitForVisibility();
    return this;
  }

  public ReactAssignmentContainerPopUpScreen clickLinkByIdButton() {
    linkByIdButton.click();
    return new ReactAssignmentContainerPopUpScreen();
  }

  public boolean isColumnDisplayed(String name) {
    return Element.byXpath(String.format(COLUMN_LOCATOR_PATTERN, name)).isDisplayed();
  }

  public ReactSurveyAndTestingTabScreen clickBusketButton() {
    bucketButton.click();
    return this;
  }

  public ReactSurveyAndTestingTabScreen clickConfirmationDeleteButton() {
    confirmationDeleteButton.click();
    return this;
  }

  public String getIDColumnInputText() {
    return iDColumnInput.getText();
  }

  public String getTitleColumnInputText() {
    return titleColumnInput.getText();
  }

  public String getSubtitleColumnInputText() {
    return subtitleColumnInput.getText();
  }

  public boolean isCustomDeadlineColumnDisplayed() {
    return customDeadlineColumn.isDisplayed();
  }

  public String getCustomDeadlineColumnInputText() {
    return customDeadlineColumnInput.getText();
  }

  public String getDeadlineColumnInputText() {
    return deadlineColumnInput.getText();
  }

  public ReactSurveyAndTestingTabScreen clickSurveyTypeDDL() {
    surveyTypeDropDown.click();
    return waitDDLForVisibility();
  }

  public ReactSurveyAndTestingTabScreen waitDDLForVisibility() {
    DDL.waitForVisibility();
    return this;
  }

  public ReactSurveyAndTestingTabScreen selectStandardSurveyType() {
    clickSurveyTypeDDL();
    standardSurveyType.click();
    return this;
  }

  public Set<String> getAnswerOptionLabelText() {
    return new HashSet<>(answerOptionLabel.getElementsText());
  }

  public Set<String> getQuestionLabelText() {
    return new HashSet<>(questionLabel.getElementsText());
  }

  public List<String> getAnswerAndQuestionPreFilledText(String text) {
    return Element.byXpath(String.format(PRE_FILLED_TEXT_LOCATOR_PATTERN, text))
        .getElements()
        .stream()
        .map(x -> x.getAttributeValue("value"))
        .collect(Collectors.toList());
  }

  public Map<String, List<String>> getPreFilledTextByLabelName() {
    Map<String, List<String>> answerAndQuestionLabelsWithPreFilledText = new HashMap<>();
    getAnswerOptionLabelText()
        .forEach(answerLabel -> answerAndQuestionLabelsWithPreFilledText
            .put(answerLabel.replace("*", "").trim(),
                getAnswerAndQuestionPreFilledText(answerLabel
                    .replace("*", "")
                    .trim())));
    getQuestionLabelText()
        .forEach(questionLabel -> answerAndQuestionLabelsWithPreFilledText
            .put(getValueOf(SURVEY_SCREEN_QUESTION),
                getAnswerAndQuestionPreFilledText(getValueOf(SURVEY_SCREEN_QUESTION))));
    return answerAndQuestionLabelsWithPreFilledText;
  }

  public boolean isUseEnglishResultsCheckBoxChecked() {
    return useEnglishResultsCheckBox.isCheckBoxChecked();
  }

  public String getInputFieldValueByLabelName(String labelName) {
    return Element.byXpath(INPUT_LOCATOR_BY_LABEL_PATTERN, labelName)
        .getAttributeValue(AbstractScreen.PLACEHOLDER_CSS_PROPERTY);
  }

  public ReactSurveyAndTestingTabScreen clickDropDownByLabelName(String labelName) {
    Element.byXpath(String.format(INPUT_LOCATOR_BY_LABEL_PATTERN, labelName)).click();
    return this;
  }

  public ReactSurveyAndTestingTabScreen clickSurveyTypeDropDownByName(String surveyTypeName) {
    Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, surveyTypeName)).click();
    return this;
  }

  public ReactSurveyAndTestingTabScreen waitConfirmationToasterForVisibility() {
    confirmationToaster.waitForVisibility();
    return this;
  }

  public ReactSurveyAndTestingTabScreen clickOnConfirmationToasterCross() {
    confirmationToasterCrossButton.mouseOverAndClick();
    return this;
  }

  public boolean isSurveyTemplateDisplayed() {
    return Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, getValueOf(SURVEY_SCREEN_QUESTION)))
        .isDisplayed();
  }

  public boolean isNoSurveyPlaceholderDisplayedShortTimeOut() {
    return surveyTypeDropDown.isDisplayedShortTimeOut();
  }

  public int getAnswerOptionLabelsSize() {
    return answerOptionLabel.getElements().size();
  }

  public int getQuestionOptionLabelsSize() {
    return questionLabel.getElements().size();
  }
}
