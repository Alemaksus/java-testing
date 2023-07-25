package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;

public class CenterScreen extends AbstractScreen {

  private static final String ATTRIBUTE_CLASS = "class";
  private static final String ACTIVE_LOCATOR = "active";
  private static final String CREATED_CENTER_NAME_IN_PREVIEW_PATTERN =
      "//div[contains(@class,'center-title') and text()='%s']";
  private static final String SKILL_BY_NAME_PATTERN =
      "//div[text()='%s' and contains(@class,'skill-name')]";
  private Element backToEditButton = Element.byXpath("//button[@ng-click='goBack()']");
  private Element centerItem = Element.byXpath("//*[contains(@class,'tab-item')]");
  private Element title = Element.byXpath("//div[contains(@class,'center-title')]");
  private Element description = Element.byXpath("//p[contains(@class,'description')]"
      + "[contains(@class,'ql-editor')]");
  private Element currentSkillsSection = Element.byCss("div.skill-carousel");
  private Element availableTrainingsLink = Element
      .byXpath("//a[contains(@class,'trainings-link')]");
  private Element locationDescription = Element
      .byXpath("//div[contains(@class,'locations-description')]");
  private Element seeAllTrainingLink = Element
      .byXpath("//a[contains(@href,'TrainingList')][@class='ng-binding']");
  private Element universityCard = Element.byClassName("university-card");
  private Element universityDescription = Element
      .byXpath("//div[contains(@class,'university-item-content')]");
  private Element universityShortName = Element
      .byXpath("//div/p[@class = 'university-card-title ng-binding']");
  private Element universityName = Element.
      byXpath("//div[@class = 'university-item-title ng-binding']");
  private Element universityDetailedDescription = Element
      .byXpath("//div[@class = 'university-item-body ql-editor ng-binding']");
  private Element carouselNavigationFastFactsButton = Element.byXpath(
      "//div[@class='locations-slider']//*[@class='owl-dots']/button");
  private Element fastFactsDescription = Element.byXpath(
      "//*[@class='locations-slider']//*[@class='owl-stage']/div");
  private Element homeNavigationLink = Element.byXpath(
      "//a[@href='/#!/Home' and @class='ng-binding']");
  private Element aboutNavigationLink = Element.byXpath(
      "//a[@href='/#!/About' and @class='ng-binding']");
  private Element centerCooperationWithTheUniversities = Element.byXpath(
      "//div[contains(@class,'university-header')]/ancestor::div[contains(@class, 'university')]");
  private Element feedbackSection = Element.byClassName("feedback-slider");
  private Element feedbackDescription = Element.byXpath(
      "//div[@class='owl-item active']//div[@class='feedback-text ng-binding']");
  private Element feedbackAuthorsName = Element.byXpath(
      "//div[@class='owl-item active']//div[@class='feedback-name ng-binding']");
  private Element feedbackJobFunctional = Element
      .byXpath("//div[@class='owl-item active']//div[@class='feedback-role ng-binding']");
  private Element englishTab = Element.byXpath("//*[@tabs='languageTabs']/div[1]");
  private Element russianTab = Element.byXpath("//*[@tabs='languageTabs']/div[2]");
  private Element ukrainianTab = Element.byXpath("//*[@tabs='languageTabs']/div[3]");
  private Element fastFactsTitle = Element.byClassName("locations-title");
  private Element trainingCenterDescription = Element.byXpath(
      "//p[contains(@class, 'description')]");
  private Element cooperationWithUniversitiesTitle = Element.byXpath(
      "//div[contains(@class, 'university-header')]");
  private Element currentSkillsTitle = Element.byCss("div.skills-header ");
  private Element feedbackTitle = Element.byClassName("feedback-title");
  private Element availableTrainingName = Element.byXpath("//div[contains(@class, 'skill-name')]");
  private Element feedbackWhiteDot = Element.byXpath(
      "//div[@class='feedback-block']//button[@class='owl-dot']");

  @Override
  public boolean isScreenLoaded() {
    return isCenterTitleDisplayed();
  }

  public CenterScreen waitCenterItemForVisibility() {
    centerItem.waitForVisibility();
    return this;
  }

  public boolean isTitlePresent() {
    return title.isPresent();
  }

  public boolean isCenterTitleDisplayed() {
    return title.isDisplayed();
  }

  public CenterScreen waitCenterTitleForVisibility() {
    title.waitForVisibility();
    return this;
  }

  public boolean isDescriptionPresent() {
    return description.isPresent();
  }

  public boolean isCenterDescriptionDisplayed() {
    return description.isDisplayed();
  }

  public String getTextDescription() {
    return description.getText();
  }

  public boolean isSkillsPresent() {
    return currentSkillsSection.isPresent();
  }

  public boolean isCurrentSkillsSectionDisplayed() {
    return currentSkillsSection.isDisplayed();
  }

  private List<Element> getAvailableTrainingsLinks() {
    return availableTrainingsLink.getElements();
  }

  private Element getAvailableTrainingsElementByIndex(int index) {
    return getAvailableTrainingsLinks().get(index);
  }

  public CenterScreen clickAvailableTrainingsLinkByIndex(int index) {
    getAvailableTrainingsElementByIndex(index).click();
    return this;
  }

  public CenterScreen clickAvailableTrainingsFirstLink() {
    getAvailableTrainingsElementByIndex(0).click();
    return this;
  }

  public String getLocationDescriptionText() {
    return locationDescription.getText();
  }

  public TrainingBlockScreen clickSeeAllTrainingLink() {
    seeAllTrainingLink.click();
    return new TrainingBlockScreen();
  }

  public CreateOrEditTrainingCenterScreen clickBackToEditButton() {
    backToEditButton.click();
    return new CreateOrEditTrainingCenterScreen();
  }

  public String getSeeAllTrainingLinkText() {
    return seeAllTrainingLink.getText();
  }

  public boolean isUniversityCardDisplayed() {
    return universityCard.isDisplayed();
  }

  public CenterScreen clickUniversityCard() {
    universityCard.clickJs();
    return this;
  }

  public boolean isUniversityDescriptionDisplayed() {
    return universityDescription.isDisplayed();
  }

  public SkillDescriptionScreen clickPublishedSkillCardByName(String skillName) {
    Element.byXpath(SKILL_BY_NAME_PATTERN, skillName).click();
    return new SkillDescriptionScreen();
  }

  public List<Element> getCarouselNavigationButtonsForFastFactsElements() {
    return carouselNavigationFastFactsButton.getElements();
  }

  public List<Element> getCarouselNavigationFastFactsList() {
    return fastFactsDescription.getElements();
  }

  public int getCarouselNavigationForFastFactsQuantity() {
    return getCarouselNavigationButtonsForFastFactsElements().size();
  }

  public CenterScreen clickCarouselNavigationButtonForFastFactByIndex(int index) {
    getCarouselNavigationButtonsForFastFactsElements().get(index).click();
    return this;
  }

  public boolean isCarouselNavigationButtonActive(int index) {
    return getCarouselNavigationFastFactsList().get(index).getAttributeValue(ATTRIBUTE_CLASS)
        .contains(ACTIVE_LOCATOR);
  }

  public String getHomeNavigationLinkText() {
    return homeNavigationLink.getText();
  }

  public String getAboutNavigationLinkText() {
    return aboutNavigationLink.getText();
  }

  public boolean isCenterCooperationWithTheUniversitiesDisplayed() {
    return centerCooperationWithTheUniversities.isDisplayed();
  }

  public boolean isFeedbackSectionDisplayed() {
    return feedbackSection.isDisplayed();
  }

  public CenterScreen waitTitleForVisibility() {
    title.waitForVisibility();
    return this;
  }

  public String getTextTitle() {
    return title.getText();
  }

  public String getUniversityShortNameText() {
    return universityShortName.getText();
  }

  public String getUniversityNameText() {
    return universityName.getText();
  }

  public String getUniversityDescriptionText() {
    return universityDetailedDescription.getText();
  }

  public String getFeedbackDescriptionText() {
    return feedbackDescription.getText();
  }

  public String getFeedbackAuthorsNameText() {
    return feedbackAuthorsName.getText();
  }

  public String getFeedbackJobFunctionalText() {
    return feedbackJobFunctional.getText();
  }

  public boolean isBackToEditButtonDisplayed() {
    return backToEditButton.isDisplayed();
  }

  public String getBackToEditButtonText() {
    return backToEditButton.getText();
  }

  public String getEnglishTabText() {
    return englishTab.getText();
  }

  public String getEnglishTabColor() {
    return englishTab.getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public String getRussianTabText() {
    return russianTab.getText();
  }

  public String getRussianTabColor() {
    return russianTab.getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public String getUkrainianTabText() {
    return ukrainianTab.getText();
  }

  public String getUkrainianTabColor() {
    return ukrainianTab.getCssValue(Attribute.BACKGROUND_COLOR.toString());
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

  public boolean isFastFactsTitleDisplayed() {
    return fastFactsTitle.isDisplayed();
  }

  public String getFastFactsTitleText() {
    return fastFactsTitle.getText();
  }

  public boolean isTrainingCenterDescriptionDisplayed() {
    return trainingCenterDescription.isDisplayed();
  }

  public String getCooperationWithUniversitiesTitleText() {
    return cooperationWithUniversitiesTitle.getText();
  }

  public String getCurrentSkillsTitleText() {
    return currentSkillsTitle.getText();
  }

  public String getFeedbackTitleText() {
    return feedbackTitle.getText();
  }

  public String getAvailableTrainingNameTextByIndex(int index) {
    return availableTrainingName.getElements().get(index).getText();
  }

  public CenterScreen clickFeedbackWhiteDot() {
    feedbackWhiteDot.click();
    return this;
  }

  public void waitCreatedCenterNameInPreviewVisibility(String trainingCenterName) {
    Element.byXpath(CREATED_CENTER_NAME_IN_PREVIEW_PATTERN, trainingCenterName)
        .waitForVisibility();
  }
}
