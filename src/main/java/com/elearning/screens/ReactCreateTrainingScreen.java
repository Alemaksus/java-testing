package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.element.Element;

import javax.swing.text.html.HTML;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CREATE_TRAINING_SCREEN_CREATE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGEMENT_CREATE_NEW;

public class ReactCreateTrainingScreen extends ReactAdditionalInfoTabScreen {

  protected Element createNewTitle = Element.byXpath("//div[@font-size]/div[text()='%s']",
      getValueOf(TRAINING_MANAGEMENT_CREATE_NEW));
  private final Element spinnerContainer = Element.byXpath(
      "//*[contains(@class, 'uui-spinner-container')]");
  private Element createButton = Element
      .byXpath("//*[contains(@class, 'uui-button-box')]//*[text()='%s']",
          getValueOf(REACT_CREATE_TRAINING_SCREEN_CREATE_BUTTON));
  private Element trainingStatusButton = Element.byXpath("//div[@role='button']");
  private final Element typeField = Element.byXpath("(//div[@data-id])[1]//input/preceding-sibling::div/div");
  private final Element displayingNameField = Element.byXpath(
          "(//*[@placeholder='%s'])[2]",
          getValueOf(REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER));
  private final Element nameField = Element.byXpath(
          "(//*[@placeholder='%s'])[1]",
          getValueOf(REACT_DETAIL_TRAINING_SCREEN_DISPLAY_NAME_PLACEHOLDER));
  private final Element educationEstablishmentField = Element.byXpath("(//*[contains(@class, 'uui-placeholder')])[2]");
  private final Element departmentField = Element.byXpath("(//*[contains(@class, 'uui-placeholder')])[3]");
  private final Element supervisorsField = Element.byXpath("(//div[@class = 'uui-caption'])[2]");
  private final Element studentGroupField = Element.byXpath("//div[@display = 'inline-block']//span");
  private final Element registrationFormTab = Element.byXpath("(//div[@display = 'grid']//div)[4]");

  @Override
  public boolean isScreenLoaded() {
    return createNewTitle.isDisplayed();
  }

  public ReactCreateTrainingScreen waitScreenLoading() {
    return waitCreateButtonClickable();
  }

  public ReactCreateTrainingScreen waitCreateButtonClickable() {
    createButton.waitForClickable(LONG_TIME_OUT_IN_SECONDS);
    return this;
  }

  public boolean isTrainingStatusButtonDisplayedNoWait() {
    return trainingStatusButton.isDisplayedNoWait();
  }

  public ReactCreateTrainingScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public String getDisplayingNameText() {
    return displayingNameField.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public String getNameText() {
    return nameField.getAttributeValue(HTML.Attribute.VALUE.toString());
  }

  public String getEducationEstablishmentText() {
    return educationEstablishmentField.getAttributeValue("placeholder");
  }

  public String getDepartmentText() {
    return departmentField.getAttributeValue("placeholder");
  }

  public String getSupervisorsText() {
    return supervisorsField.getText();
  }

  public String getStudentGroupText() {
    return studentGroupField.getText();
  }

  public String getTypeText() {
    return typeField.getText();
  }

  public boolean isRegistrationFormTabDisplayed() {
    return registrationFormTab.isDisplayed();
  }
}
