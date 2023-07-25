package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_TEXT_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_AUTOREPLY_TAB_SCREEN_SEND_TEST_LETTER_BUTTON;

import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactAutoreplyTabScreen extends ReactAdditionalInfoTabScreen {

  private final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private final int NUMBER_OF_TEMPLATE_TEXT_IN_DISABLED_TEXTBOX = 3;

  private Element autoreplyInput = Element.byXpath("//div[@data-placeholder='%s']",
      getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_INPUT));
  private Element editCheckbox = Element.byXpath("//div[contains(@class,'uui-checkbox')]");
  private Element editCheckboxLabel = Element.byXpath("//div[@class='uui-input-label']");
  private Element toolBar = Element.byXpath("//div[contains(@class,'toolbar')]");
  private Element checkedEditCheckbox = Element.byXpath("//div[contains(@class,'uui-checked')]");
  private Element autoreplyTextLabel = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_TEXT_LABEL));
  private Element sendTestLetterButton = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(REACT_AUTOREPLY_TAB_SCREEN_SEND_TEST_LETTER_BUTTON));
  private Element disabledTextArea = Element.byXpath(TEXT_LOCATOR_PATTERN + "/following::div[@font-size]/div",
      getValueOf(REACT_AUTOREPLY_TAB_SCREEN_AUTOREPLY_TEXT_LABEL));
  private Element exportToWordButton = Element.byXpath(TEXT_LOCATOR_PATTERN, "Export to Word");

  public ReactAutoreplyTabScreen waitScreenLoading() {
    editCheckboxLabel.waitForVisibility();
    return this;
  }

  public boolean isToolBarIsPresent() {
    return toolBar.isPresent();
  }

  public ReactAutoreplyTabScreen clickEditCheckBox() {
    editCheckbox.click();
    return this;
  }

  public ReactAutoreplyTabScreen clearAutoreplyInput() {
    autoreplyInput.clearInput();
    return new ReactAutoreplyTabScreen();
  }

  public ReactAutoreplyTabScreen typeTextInAutoreplyInput(String inputText) {
    autoreplyInput.type(inputText);
    return new ReactAutoreplyTabScreen();
  }

  public String getTextFromAutoreplyInput() {
    return autoreplyInput.getText();
  }

  public boolean isEditCheckBoxChecked() {
    return checkedEditCheckbox.isDisplayedNoWait();
  }

  public boolean isAutoreplyTextLabelDisplayed() {
    return autoreplyTextLabel.isDisplayedNoWait();
  }

  public boolean isSendTestLetterButtonDisplayed() {
    return sendTestLetterButton.isDisplayedNoWait();
  }

  public boolean isEditCheckBoxDisplayed() {
    return editCheckboxLabel.isDisplayedNoWait();
  }

  public boolean isDisabledTextAreaHasCorrectNumberOfTemplateTexts() {
    return disabledTextArea.getElementsText().size() == NUMBER_OF_TEMPLATE_TEXT_IN_DISABLED_TEXTBOX;
  }

  public List<String> getLetterTemplates() {
    return disabledTextArea.getElementsText();
  }

  public ReactAutoreplyTabScreen clickExportToWordButton(){
    exportToWordButton.waitForClickableAndClick();
    return this;
  }
}
