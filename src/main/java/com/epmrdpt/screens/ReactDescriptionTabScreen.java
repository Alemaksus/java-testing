package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_TRAINING_LIST;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_PAID_CONSULTATIONS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_VIDEO_BLOCK_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_OF_SAVING_CHANGES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;

public class ReactDescriptionTabScreen extends ReactAdditionalInfoTabScreen {

  private static final String LABEL_LOCATOR_PATTERN = "//div[@class='uui-label' and contains(text(),'%s')]";
  private final String SECONDARY_BLOCK_LABEL_LOCATOR_PATTERN =
      String.format("(%s)[2]", LABEL_LOCATOR_PATTERN);
  private final String INPUT_LOCATOR_BY_LABEL_PATTERN =
      LABEL_LOCATOR_PATTERN + "/../../../following-sibling::div//input";
  private final String SECONDARY_INPUT_LOCATOR_BY_LABEL_PATTERN =
      SECONDARY_BLOCK_LABEL_LOCATOR_PATTERN + "/../../../following-sibling::div//input";
  private final String TEXT_BLOCK_CONTENT_PATTERN = TEXT_LOCATOR_PATTERN +
      "/../../../following-sibling::div//div[@class='quill ']//div[@class='ql-editor']//span";
  private final String SECONDARY_BLOCK_VALUE_LOCATOR_PATTERN =
      SECONDARY_BLOCK_LABEL_LOCATOR_PATTERN + "/../../../../following-sibling::div/div/div";
  private final String INPUT_ARROW_LOCATOR_PATTERN =
      TEXT_LOCATOR_PATTERN + "/following::*[contains(@*,'arrow')][1]";
  private final String INPUT_EDITOR_BY_LABEL_PATTERN =
      LABEL_LOCATOR_PATTERN + "/../../../following-sibling::div//div[contains(@class,'ql-editor')]";
  private final String ICON_AFTER_TEXT_PATTERN =
      TEXT_LOCATOR_PATTERN + "/../following-sibling::div//div[contains(@class,'uui-icon')]";
  private static final String OPACITY_LABEL_LOCATOR_PATTERN = LABEL_LOCATOR_PATTERN
      + "/../../../following-sibling::div//div[contains(@class, 'quill')]";
  private static final String TEXT_CONTAINER_BY_LABEL_PATTERN = LABEL_LOCATOR_PATTERN
      + "/../../../following-sibling::div//div[contains(@class, 'ql-container')]";
  private static final String TEXT_CONTAINS_PATTERN = "//div[contains(text(),'%s')]";

  private Element paidConsultationInfoLabel = Element.byXpath(LABEL_LOCATOR_PATTERN,
      getValueOf(REACT_DESCRIPTION_TAB_SCREEN_PAID_CONSULTATIONS_LABEL));
  private Element paidConsultationInfoField = Element.byXpath(
      "(//*[contains(@class, 'ql-editor')])[8]");
  private Element trainingProgramField = Element.byXpath(
      "(//*[contains(@class, 'ql-editor')])[6]");
  private Element enabledSaveChangesButton = Element.byXpath(
      "//*[contains(@class, 'uui-enabled')]/div[text()='%s']",
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_SAVE_CHANGES_BUTTON));
  private Element spinnerContainer = Element.byXpath(
      "//*[contains(@class, 'uui-spinner-container')]");
  private Element addImageButton = Element
      .byXpath("//input[@type='file']//parent::div//div[contains(@class,'uui-caption')]");
  private Element videoBlockContent = Element.byXpath(TEXT_LOCATOR_PATTERN +
          "/../../../following-sibling::div//input",
      getValueOf(REACT_DESCRIPTION_TAB_SCREEN_VIDEO_BLOCK_LABEL));
  private Element recommendedTrainingDropDown = Element.byXpath(INPUT_ARROW_LOCATOR_PATTERN,
      getValueOf(REACT_DESCRIPTION_TAB_SCREEN_RECOMMENDED_LABEL));
  private Element ddlValue = Element.byXpath(
      "//div[@class='uui-popper']//div[contains(@class,'-clickable')]//div[@style]//div[text()]");
  private Element changesSavedSign = Element.byXpath(TEXT_CONTAINS_PATTERN,
      getValueOf(REACT_DETAIL_TRAINING_SCREEN_CONFIRMATION_OF_SAVING_CHANGES));
  private Element trainingListTab = Element.byXpath
      ("//div[contains(@class, 'nav-menu')]//a[contains(text(), '%s')]",
          getValueOf(HEADER_CONTAINER_TRAINING_LIST));
  private Element detailsBlockEyeButton =  Element.byXpath
      ("(//div[contains(@class,'editor-wrapper')]//div[contains(@class,'uui-icon uui-enabled')])[1]");

  public ReactDescriptionTabScreen clickSaveChangesButton() {
    enabledSaveChangesButton.waitForClickableAndClick();
    return this;
  }

  public boolean isPaidConsultationLabelDisplayed() {
    return paidConsultationInfoLabel.isDisplayedNoWait();
  }

  public boolean isPaidConsultationInfoFieldDisplayed() {
    return paidConsultationInfoField.isDisplayedNoWait();
  }

  public ReactDescriptionTabScreen typeTrainingProgram(String trainingProgram) {
    trainingProgramField.type(trainingProgram);
    return this;
  }

  public ReactDescriptionTabScreen clearTrainingProgram() {
    trainingProgramField.clearInput();
    return this;
  }

  public String getTrainingProgramText() {
    return trainingProgramField.getText();
  }

  public ReactDescriptionTabScreen waitAllSpinnersAreNotDisplayed() {
    spinnerContainer.waitUntilRequiredElementsAreInvisible(
        spinnerContainer.getWrappedWebElements(DEFAULT_TIME_OUT_IN_SECONDS));
    return this;
  }

  public ReactDescriptionTabScreen clickPaidConsultationInfoField() {
    paidConsultationInfoField.click();
    return this;
  }

  public boolean isInputLabelDisplayedByName(String labelName) {
    return Element.byXpath(String.format(LABEL_LOCATOR_PATTERN, labelName)).isDisplayed();
  }

  public boolean isSecondaryInputLabelDisplayedByName(String labelName) {
    return Element.byXpath(String.format(SECONDARY_BLOCK_LABEL_LOCATOR_PATTERN, labelName))
        .isDisplayed();
  }

  public String getOpacityOfInputBlockByName(String labelName) {
    return Element.byXpath(String.format(OPACITY_LABEL_LOCATOR_PATTERN, labelName)).getCssValue("opacity");
  }

  public boolean isInputFieldDisplayedByName(String labelName) {
    return Element.byXpath(String.format(INPUT_LOCATOR_BY_LABEL_PATTERN, labelName)).isDisplayed();
  }

  public boolean isInputFieldPresentByName(String labelName) {
    return Element.byXpath(String.format(INPUT_LOCATOR_BY_LABEL_PATTERN, labelName))
        .isPresentNoWait();
  }

  public boolean isSecondaryInputFieldPresentByName(String labelName) {
    return Element.byXpath(String.format(SECONDARY_INPUT_LOCATOR_BY_LABEL_PATTERN, labelName))
        .isPresentNoWait();
  }

  public String getSecondaryValueByName(String labelName) {
    return Element.byXpath(String.format(SECONDARY_BLOCK_VALUE_LOCATOR_PATTERN, labelName))
        .getText();
  }

  public boolean isIconPresentAfterRecommendedTraining(String recommendedTraining) {
    return Element.byXpath(String.format(ICON_AFTER_TEXT_PATTERN, recommendedTraining))
        .isPresentNoWait();
  }

  public boolean isInputContainerDisabledByName(String labelName) {
    return Element.byXpath(TEXT_CONTAINER_BY_LABEL_PATTERN, labelName)
        .isElementClassDisabled();
  }

  public boolean isAddImageButtonDisplayed() {
    return addImageButton.isDisplayed();
  }

  public String getAddImageButtonText() {
    return addImageButton.getText();
  }

  public String getInputFieldValueByLabelName(String labelName) {
    return Element.byXpath(INPUT_LOCATOR_BY_LABEL_PATTERN, labelName)
        .getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public String getTextBlockContentByBlockName(String blockName) {
    return Element.byXpath(TEXT_BLOCK_CONTENT_PATTERN, blockName).getText();
  }

  public String getVideoBlockContent() {
    return videoBlockContent.getAttributeValue(AbstractScreen.VALUE_CSS_PROPERTY);
  }

  public List<String> getRecommendedTrainingsDdlOptions() {
    recommendedTrainingDropDown.click();
    List<String> typeDdlOptions = ddlValue.getElementsText();
    recommendedTrainingDropDown.click();
    return typeDdlOptions;
  }

  public ReactDescriptionTabScreen fillInputFieldByLabelNameWithRandomData(String labelName) {
    Element.byXpath(INPUT_LOCATOR_BY_LABEL_PATTERN, labelName)
        .nativeClearAndType(RandomStringUtils.randomAlphabetic(10));
    return this;
  }

  public String getInputFieldPlaceholderByLabelName(String labelName) {
    return Element.byXpath(INPUT_EDITOR_BY_LABEL_PATTERN, labelName)
        .getAttributeValue(AbstractScreen.DATA_PLACEHOLDER_CSS_PROPERTY);
  }

  public ReactDescriptionTabScreen clickDetailsBlockEyeButton() {
    detailsBlockEyeButton.mouseOverAndClick();
        return this;
  }
  public boolean isChangesSaved() {
    return changesSavedSign.isDisplayed();
  }

  public TrainingListScreen clickTrainingListTab() {
   trainingListTab.click();
    return new TrainingListScreen();
  }
}
