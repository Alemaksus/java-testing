
package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactCreateSkillDescriptionScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String INPUT_PATTERN = "//form/div/div[.//div[text()='%s']]//div[@data-placeholder='Enter text']";
  private static final String INPUT_PLACEHOLDER_PATTERN = "//input[@placeholder='%s']";
  private static final String QUESTION_ICON = "//div[text()='%s']/following::div[3]//ancestor::*[local-name()='svg']";
  private static final String TAG_INPUT_PATTERN = "//form/div/div[.//div[text()='%s']]//textarea[@placeholder='Enter text']";
  private static final String ADD_IMAGE_BUTTON = "(//div[contains(text(), 'Add image')][1])";
  private static final String CREATE_LABEL = "Create";
  private static final String STATUS_LABEL = "Status";
  private static final String SKILL_LABEL = "Skill";
  private static final String DESCRIPTION_LABEL_TEXT = "Description";
  private static final String CORE_SKILLS_AND_TECHNOLOGIES_LABEL_TEXT = "Core skills and technologies";
  private static final String KNOWLEDGE_DESCRIPTION_LABEL_TEXT = "Knowledge description";
  private static final String ADDITIONAL_INFORMATION_LABEL_TEXT = "Additional information (e.g. responsibilities, requirements)";
  private static final String USEFUL_MATERIALS_LABEL_TEXT = "Useful materials";
  private static final String BANNER_LABEL_TEXT = "Banner";
  private static final String META_TAG_DESCRIPTION_LABEL_TEXT = "Meta tag Description";
  private static final String META_TAG_KEYWORDS_LABEL_TEXT = "Meta tag Keywords";
  private static final String META_TAG_TITLE_LABEL_TEXT = "Meta Tag Title";
  private static final String PLACEHOLDER_CSS_PROPERTY = "placeholder";
  private static final String DATA_PLACEHOLDER_CSS_PROPERTY = "data-placeholder";

  private final Element popUpWindow = Element.byXpath(
      "//div[@class='uui-snackbar-item-wrapper-self']");
  private Element createSkillDescriptionTitle = Element.byXpath(
      "//h2[contains(text(), 'Create skill description')]");
  private Element createButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, CREATE_LABEL));
  private Element cancelButton = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, "Cancel"));
  private Element statusTitle = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, STATUS_LABEL));
  private Element statusTitleDropdown = Element.byXpath(
      "//input[@class='uui-input _1Wc1b uui-placeholder']");
  private Element skillTitle = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, SKILL_LABEL));
  private Element skillDropdownPlaceholder = Element.byXpath(
      String.format(INPUT_PLACEHOLDER_PATTERN, "Select from the list"));
  private Element skillCoverLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Skill cover"));
  private Element customImageForSharingLabel = Element.byXpath(
      TEXT_LOCATOR_PATTERN, "Custom image for sharing");
  private Element skillCoverAddImageButton = Element.byXpath(ADD_IMAGE_BUTTON + "[1]");
  private Element imageForDescriptionAddImageButton = Element.byXpath(ADD_IMAGE_BUTTON + "[3]");
  private Element englishTab = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, "English"));
  private Element russianTab = Element.byXpath(String.format(TEXT_LOCATOR_PATTERN, "Russian"));
  private Element ukrainianTab = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Ukrainian"));
  private Element titleLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Title"));
  private Element titleInput = Element.byXpath(INPUT_PLACEHOLDER_PATTERN + "[1]", "Enter text");
  private Element fastFactsLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Fast facts"));
  private Element numberLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Number"));
  private Element numberInput = Element.byXpath(
      String.format(INPUT_PLACEHOLDER_PATTERN, "Enter number"));
  private Element shortDescriptionLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Short description"));
  private Element shortDescriptionInput = Element.byXpath
      ("//div[text()='Short description']/following::input[1]");
  private Element addFactButton = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Add Fact"));
  private Element descriptionLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, DESCRIPTION_LABEL_TEXT));
  private Element descriptionInput = Element.byXpath(
      INPUT_PATTERN, DESCRIPTION_LABEL_TEXT);
  private Element imageForDescriptionLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Image for description"));
  private Element customImageForSharingAddImageButton = Element.byXpath(
      ADD_IMAGE_BUTTON + "[2]");
  private Element coreSkillsAndTechnologiesLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, CORE_SKILLS_AND_TECHNOLOGIES_LABEL_TEXT));
  private Element coreSkillsAndTechnologiesInput = Element.byXpath(
      INPUT_PATTERN, CORE_SKILLS_AND_TECHNOLOGIES_LABEL_TEXT);
  private Element knowledgeDescriptionLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, KNOWLEDGE_DESCRIPTION_LABEL_TEXT));
  private Element knowledgeDescriptionInput = Element.byXpath(
      INPUT_PATTERN, KNOWLEDGE_DESCRIPTION_LABEL_TEXT);
  private Element additionalInformationLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, ADDITIONAL_INFORMATION_LABEL_TEXT));
  private Element additionalInformationInput = Element.byXpath(
      INPUT_PATTERN, ADDITIONAL_INFORMATION_LABEL_TEXT);
  private Element usefulMaterialsLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, USEFUL_MATERIALS_LABEL_TEXT));
  private Element usefulMaterialsInput = Element.byXpath(
      INPUT_PATTERN, USEFUL_MATERIALS_LABEL_TEXT);
  private Element linkToVideoLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, "Link to video"));
  private Element linkToVideoInput = Element.byXpath(
      String.format(INPUT_PLACEHOLDER_PATTERN, "https://example.com"));
  private Element bannerLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, BANNER_LABEL_TEXT));
  private Element bannerInput = Element.byXpath(
      INPUT_PATTERN, BANNER_LABEL_TEXT);
  private Element metaTagTitleLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, META_TAG_TITLE_LABEL_TEXT));
  private Element metaTagTitleInput = Element.byXpath(TAG_INPUT_PATTERN, META_TAG_TITLE_LABEL_TEXT);
  private Element metaTagDescriptionLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, META_TAG_DESCRIPTION_LABEL_TEXT));
  private Element metaTagDescriptionInput = Element.byXpath(TAG_INPUT_PATTERN,
      META_TAG_DESCRIPTION_LABEL_TEXT);
  private Element metaTagKeywordsLabel = Element.byXpath(
      String.format(TEXT_LOCATOR_PATTERN, META_TAG_KEYWORDS_LABEL_TEXT));
  private Element metaTagKeywordsInput = Element.byXpath(TAG_INPUT_PATTERN,
      META_TAG_KEYWORDS_LABEL_TEXT);
  private Element skillDropdownInput = Element.byXpath(String.format(
      TEXT_LOCATOR_PATTERN + "/following::input[1]", SKILL_LABEL));
  private Element statusDropdownInput = Element.byXpath(String.format(
      TEXT_LOCATOR_PATTERN + "/following::input[1]", STATUS_LABEL));

  @Override
  public boolean isScreenLoaded() {
    return createSkillDescriptionTitle.isDisplayed();
  }

  public ReactCreateSkillDescriptionScreen waitScreenLoading() {
    createSkillDescriptionTitle.waitForVisibility();
    return this;
  }

  public boolean isCreateButtonDisplayed() {
    return createButton.isDisplayed();
  }

  public boolean isCancelButtonDisplayed() {
    return cancelButton.isDisplayed();
  }

  public boolean isStatusLabelDisplayed() {
    return statusTitle.isDisplayed();
  }

  public boolean isSkillLabelDisplayed() {
    return skillTitle.isDisplayed();
  }

  public boolean isCustomImageForSharingLabelDisplayed() {
    return customImageForSharingLabel.isDisplayed();
  }

  public boolean isSkillCoverLabelDisplayed() {
    return skillCoverLabel.isDisplayed();
  }

  public boolean isSkillCoverAddImageButtonDisplayed() {
    return skillCoverAddImageButton.isDisplayed();
  }

  public boolean isImageForDescriptionAddImageButtonDisplayed() {
    return imageForDescriptionAddImageButton.isDisplayed();
  }

  public boolean isEnglishTabDisplayed() {
    return englishTab.isDisplayed();
  }

  public boolean isRussianTabDisplayed() {
    return russianTab.isDisplayed();
  }

  public boolean isUkrainianTabDisplayed() {
    return ukrainianTab.isDisplayed();
  }

  public boolean isTitleLabelDisplayed() {
    return titleLabel.isDisplayed();
  }

  public String getTitleInputText() {
    return titleInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isFastFactsLabelDisplayed() {
    return fastFactsLabel.isDisplayed();
  }

  public boolean isNumberLabelDisplayed() {
    return numberLabel.isDisplayed();
  }

  public String getNumberPlaceholderText() {
    return numberInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isShortDescriptionLabelDisplayed() {
    return shortDescriptionLabel.isDisplayed();
  }

  public String getShortDescriptionInputPlaceholderText() {
    return shortDescriptionInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getLinkToVideoPlaceholderText() {
    return linkToVideoInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getMetaTagTitlePlaceholderText() {
    return metaTagTitleInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getMetaTagDescriptionPlaceholderText() {
    return metaTagDescriptionInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getMetaTagKeywordsPlaceholderText() {
    return metaTagKeywordsInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isAddFactButtonDisplayed() {
    return addFactButton.isDisplayed();
  }

  public boolean isDescriptionLabelDisplayed() {
    return descriptionLabel.isDisplayed();
  }

  public String getDescriptionInputPlaceholderText() {
    return descriptionInput.getAttributeValue(DATA_PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isImageForDescriptionLabelDisplayed() {
    return imageForDescriptionLabel.isDisplayed();
  }

  public boolean isCustomImageForSharingAddImageButtonDisplayed() {
    return customImageForSharingAddImageButton.isDisplayed();
  }

  public boolean isCoreSkillsAndTechnologiesLabelDisplayed() {
    return coreSkillsAndTechnologiesLabel.isDisplayed();
  }

  public String getCoreSkillsAndTechnologiesPlaceholderText() {
    return coreSkillsAndTechnologiesInput.getAttributeValue(DATA_PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isKnowledgeDescriptionLabelDisplayed() {
    return knowledgeDescriptionLabel.isDisplayed();
  }

  public String getKnowledgeDescriptionPlaceholderText() {
    return knowledgeDescriptionInput.getAttributeValue(DATA_PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isAdditionalInformationLabelDisplayed() {
    return additionalInformationLabel.isDisplayed();
  }

  public String getAdditionalInformationPlaceholderText() {
    return additionalInformationInput.getAttributeValue(DATA_PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isUsefulMaterialsLabelDisplayed() {
    return usefulMaterialsLabel.isDisplayed();
  }

  public String getUsefulMaterialsPlaceholderText() {
    return usefulMaterialsInput.getAttributeValue(DATA_PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isLinkToVideoLabelDisplayed() {
    return linkToVideoLabel.isDisplayed();
  }

  public boolean isBannerLabelDisplayed() {
    return bannerLabel.isDisplayed();
  }

  public String getBannerInputPlaceholderText() {
    return bannerInput.getAttributeValue(DATA_PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isMetaTagTitleLabelDisplayed() {
    return metaTagTitleLabel.isDisplayed();
  }

  public boolean isMetaTagDescriptionLabelDisplayed() {
    return metaTagDescriptionLabel.isDisplayed();
  }

  public boolean isMetaTagKeywordsLabelDisplayed() {
    return metaTagKeywordsLabel.isDisplayed();
  }

  public String getStatusDropdownInputText() {
    return statusDropdownInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public String getSkillDropdownInputText() {
    return skillDropdownInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public boolean isQuestionIconDisplayed(String field) {
    return Element.byXpath(QUESTION_ICON, field).isDisplayed();
  }

  public ReactCreateSkillDescriptionScreen clickSkillDropDown() {
    skillDropdownInput.click();
    return this;
  }

  public ReactCreateSkillDescriptionScreen chooseSkillByName(String skillName) {
    skillDropdownInput.type(skillName);
    Element.byXpath(TEXT_LOCATOR_PATTERN, skillName).clickJs();
    return this;
  }

  public ReactCreateSkillDescriptionScreen typeTitle(String titleText) {
    titleInput.click();
    titleInput.type(titleText);
    return this;
  }

  public ReactCreateSkillDescriptionScreen typeNumber(String number) {
    numberInput.click();
    numberInput.type(number);
    return this;
  }

  public ReactCreateSkillDescriptionScreen typeShortDescription(String shortDescription) {
    shortDescriptionInput.click();
    shortDescriptionInput.type(shortDescription);
    return this;
  }

  public ReactCreateSkillDescriptionScreen typeDescription(String description) {
    descriptionInput.click();
    descriptionInput.type(description);
    return this;
  }

  public ReactCreateSkillDescriptionScreen clickCreateButton() {
    createButton.click();
    return this;
  }

  public ReactCreateSkillDescriptionScreen waitPopUpIsDisplayed() {
    popUpWindow.waitForVisibility();
    return this;
  }
}
