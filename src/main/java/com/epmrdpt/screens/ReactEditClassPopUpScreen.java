package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON;
import static javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.text.html.HTML;

public class ReactEditClassPopUpScreen extends AbstractScreen {

  private static final String DATE_PATTERN = "dd.MM.yyyy";
  private static final String CLASS_DATE_FILED_LOCATOR = "//div[@id='add-class-date-field']";
  private String CLASS_TYPE_ELEMENT_PATTERN =
      "(//div[@class='uui-popper']//div[contains(@class, 'clickable')]/div/div/div/div)[%s]/div/div";
  private static final String ACTIVE_BUTTON_COLOR = "rgba(103, 163, 0, 1)";

  private Element editContainer = Element.byXpath("//*[@class='lesson-form__header']/../div[2]");
  private Element classTopicFromEdit = Element.byXpath(
      "//div[@class='lesson-form__header']/../div[2]//input[1]");
  private Element classDescriptionTextArea = Element.byXpath(
      "//div[@class='lesson-form__header']/../div[2]//textarea");
  private Element classTypeInput = Element.byXpath(
      "(//div[@class='lesson-form__header']/../div[2]//input)[2]");
  private Element classTypeElements= Element.byXpath(
      "//div[@class='uui-popper']//div[contains(@class, 'clickable')]/div/div/div/div/div/div");
  private Element classLocationInput = Element.byXpath(
      "(//div[@class='lesson-form__header']/../div[2]//input)[3]");
  private Element closeEditPopUp = Element.byXpath(
      "//*[@class='lesson-form__header']//div[3]");
  private Element dateInCalendarInput = Element
      .byXpath(CLASS_DATE_FILED_LOCATOR + "/div[1]//input");
  private Element startClassTimeInput = Element
      .byXpath(CLASS_DATE_FILED_LOCATOR + "/div[2]//input");
  private Element endClassTimeInput = Element
      .byXpath(CLASS_DATE_FILED_LOCATOR + "/div[3]//input");
  private Element groupNameField = Element
      .byXpath("//div[@class='lesson-form__header']/..//span/..");
  private Element trainerInput = Element
      .byXpath("//div[@id='add-class-advanced-column']/div[1]//input");
  private Element saveChangesButton = Element
      .byXpath("//div[@id='add-class-footer']/div[2]",
          REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON);

  @Override
  public boolean isScreenLoaded() {
    return isEditContainerDisplayed();
  }

  public boolean isEditContainerDisplayed() {
    return editContainer.isDisplayed();
  }

  public ReactEditClassPopUpScreen waitClassTopicFromEditNotEmpty() {
    classTopicFromEdit.waitAttributeValueIsNotEmpty(HTML.Attribute.VALUE.toString());
    return this;
  }

  public ReactTrainingScreen clickCloseEditClassPopUp() {
    closeEditPopUp.click();
    return new ReactTrainingScreen();
  }

  public String getClassTopicFromEditValue() {
    return classTopicFromEdit.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public String getClassDescriptionText() {
    return classDescriptionTextArea.getText();
  }

  public String getClassTypeText() {
    return classTypeInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getClassLocationValue() {
    return classLocationInput.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public LocalDate getTrainingClassDate() {
    return StringUtils.getLocaleDateFromString(
        dateInCalendarInput.getAttributeValue(HTML.Attribute.VALUE.toString()), DATE_PATTERN);
  }

  public LocalTime getTrainingClassStartTime() {
    return LocalTime.parse(startClassTimeInput.getAttributeValue(HTML.Attribute.VALUE.toString()));
  }

  public LocalTime getTrainingClassEndTime() {
    return LocalTime.parse(endClassTimeInput.getAttributeValue(HTML.Attribute.VALUE.toString()));
  }

  public String getGroupNameFieldText() {
    return groupNameField.getText();
  }

  public String getTrainerInputText() {
    return trainerInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public ReactEditClassPopUpScreen waitMainTrainerFromEditNotEmpty() {
    trainerInput.waitAttributeValueIsNotEmpty(PLACEHOLDER_CSS_PROPERTY);
    return this;
  }

  public List<String> getAllTypesOfClasses() {
    classTypeInput.click();
    return classTypeElements.getElementsText();
  }

  public ReactEditClassPopUpScreen clickClassTypeByIndex(int ind) {
    Element.byXpath(String.format(CLASS_TYPE_ELEMENT_PATTERN, ind)).click();
    return this;
  }

  public ReactEditClassPopUpScreen typeClassLocation(String location) {
    classLocationInput.clearInputUsingBackspace();
    classLocationInput.type(location);
    return this;
  }

  public ReactEditClassPopUpScreen typeClassTopic(String topic) {
    classTopicFromEdit.clearInputUsingBackspace();
    classTopicFromEdit.type(topic);
    return this;
  }

  public ReactEditClassPopUpScreen typeClassDescription(String description) {
    classDescriptionTextArea.clearInputUsingBackspace();
    classDescriptionTextArea.type(description);
    return this;
  }

  public ReactEditClassPopUpScreen waitTasksButtonChangedColor() {
    saveChangesButton.waitUntilAttributeGetsValue(
        BACKGROUND_COLOR.toString(), ACTIVE_BUTTON_COLOR, DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public ReactTrainingScreen clickSaveChangesButton() {
    saveChangesButton.mouseOverAndClick();
    return new ReactTrainingScreen();
  }
}
