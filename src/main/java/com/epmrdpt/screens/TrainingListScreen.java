package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SUBSCRIBE_SUBMIT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_BANNER_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_QUIT_FILTER_PLANNED;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import javax.swing.text.html.CSS;

public class TrainingListScreen extends AbstractScreen {

  private static final String SKILL_DESCRIPTION_PATTERN =
      "//div[contains(@class,'skills-list')]/a/div[text()='%s']";
  private static final String TRAINING_BY_NAME_PATTERN =
      "//div[contains(@class,'training-item__title') and text() = '%s']";
  private static final String TRAINING_ACTION_BY_TRAINING_NAME_PATTERN =
      TRAINING_BY_NAME_PATTERN +
          "/ancestor::a/following-sibling::div/button[not (contains(@class,'ng-hide'))]";
  private static final String TRAINING_CARD_BY_NAME_PATTERN =
      TRAINING_BY_NAME_PATTERN + "//parent::div//parent::div//parent::a";
  private static final String TEXT_PATTERN = "//div[contains(text(), '%s')]";
  private static final String SUBSCRIBE_BUTTON_PATTERN = "//following::button[contains(text(), '%s')]";
  private Element homeNavigationLink =
      Element.byXpath("//a[@href='/#!/Home' and @class='ng-binding']");
  private Element banner = Element.byXpath("//*[@class='hero-banner hero-banner--trainings']");
  private Element ourSkillSection = Element.byXpath("//*[@class='container section-ui']");
  private Element skillCards =
      Element.byXpath(
          "//*[contains(@class,'skills-block_skills-block-list')]/div[contains(@class,'skill-card_skill__')]");
  private Element trainingCards =
      Element.byXpath(
          "//div[contains(@class,'training-list__container')]//div[@class='item-content--desktop']");
  private Element viewMoreSkillsLinkXpath = Element
      .byXpath("//span[contains(@class,'more-trainings-link')]");
  private Element bannerTitle = Element.byXpath(
      "//div[contains(@class, 'hero')]/h2[contains(text(), '%s')]",
      getValueOf(TRAINING_LIST_BANNER_TITLE));
  private Element bannerDescriptionText =
      Element.byCss("div.hero-banner__content>div");
  private Element skillsFilterField = Element
      .byXpath("//span[@class='filter-field__input-item-name ng-binding']");
  private Element viewMoreTrainings = Element
      .byXpath("//div[@class='training-list__more-trainings']/span");
  private Element plannedStatusTraining = Element.byXpath(String.format("//p[contains(text(), '%s')]",
      getValueOf(TRAINING_LIST_QUIT_FILTER_PLANNED)));

  @Override
  public boolean isScreenLoaded() {
    return skillCards.isDisplayed(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
  }

  public boolean isTrainingCardsLoaded() {
    return trainingCards.isDisplayed(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
  }

  public TrainingListScreen waitSkillCardsForVisibility() {
    skillCards.waitForVisibility();
    return this;
  }

  public HeaderScreen clickHomeNavigateLink() {
    homeNavigationLink.click();
    return new HeaderScreen();
  }

  public String getHomeNavigateLinkColor() {
    return homeNavigationLink.getCssValue(CSS.Attribute.COLOR.toString());
  }

  public String getHoveredHomeNavigateLinkColor() {
    return homeNavigationLink.getHoveredCssValue(CSS.Attribute.COLOR.toString());
  }

  public boolean isBannerDisplayed() {
    return banner.isDisplayed();
  }

  public boolean isOurSkillSectionDisplayed() {
    return ourSkillSection.isDisplayed();
  }

  public String getHomeNavigationLinkText() {
    return homeNavigationLink.getText();
  }

  public boolean isDisplayed(Element element) {
    return element.isDisplayed();
  }

  public boolean isViewMoreSkillsLinkAbsent() {
    return viewMoreSkillsLinkXpath.isAbsent(DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public TrainingListScreen clickViewMoreSkillsLink() {
    viewMoreSkillsLinkXpath.click();
    return this;
  }

  public TrainingListScreen hoverViewMoreSkillsLink() {
    viewMoreSkillsLinkXpath.mouseOver();
    return this;
  }

  public boolean isBannerTextLabelDisplayed() {
    return bannerDescriptionText.isDisplayed();
  }

  public boolean isBannerTitleLabelDisplayed() {
    return bannerTitle.isDisplayed();
  }

  public TrainingListScreen waitBannerTitleVisibility() {
    bannerTitle.waitForVisibility();
    return this;
  }

  public String getBannerTitleText() {
    return bannerTitle.getText();
  }

  public String getBannerTextLabelText() {
    return bannerDescriptionText.getText();
  }

  public SkillDescriptionScreen clickOnSkillDescriptionCard(String skillName) {
    Element.byXpath(SKILL_DESCRIPTION_PATTERN, skillName).click();
    return new SkillDescriptionScreen();
  }

  public boolean isSkillsCardTitleInTheSkillsFilterFieldDisplayed() {
    return skillsFilterField.isDisplayed();
  }

  public String getSkillTitleInTheSkillFilterFieldText() {
    return skillsFilterField.getText();
  }

  public String getActionOfTrainingByTrainingName(String trainingName) {
    return Element
        .byXpath(TRAINING_ACTION_BY_TRAINING_NAME_PATTERN, trainingName)
        .getText();
  }

  public boolean isTrainingPresentByName(String trainingName) {
    return Element
        .byXpath(TRAINING_ACTION_BY_TRAINING_NAME_PATTERN, trainingName)
        .isPresentNoWait();
  }

  public TrainingListScreen clickViewMoreTrainings() {
    viewMoreTrainings.mouseOver();
    viewMoreTrainings.click();
    return this;
  }

  public TrainingListScreen waitTrainingCardsForVisibility() {
    trainingCards.waitForVisibility();
    return this;
  }

  public RegistrationWizardScreen chooseTrainingByName(String trainingName) {
    Element
        .byXpath(TRAINING_ACTION_BY_TRAINING_NAME_PATTERN, trainingName).click();
    return new RegistrationWizardScreen();
  }

  public TrainingDescriptionScreen chooseTrainingCardByName(String trainingName) {
    Element.byXpath(TRAINING_CARD_BY_NAME_PATTERN, trainingName).click();
    return new TrainingDescriptionScreen();
  }

  public RegistrationWizardScreen clickOnRegistrationButtonInTrainingCard(String trainingName) {
    return new TrainingBlockScreen()
        .clickRegisterButtonOnTheTrainingByName(trainingName);
  }

  public TrainingListScreen clickPlannedButton() {
     plannedStatusTraining.click();
    return this;
  }

  public boolean isSubscribePresent(String trainingName) {
    return Element.byXpath(String.format("(" + TEXT_PATTERN + SUBSCRIBE_BUTTON_PATTERN + ")[1]", trainingName, getValueOf(SUBSCRIBE_SUBMIT_BUTTON))).isDisplayed();
  }

  public boolean isLocationSearchPresent() {
   return Element.byXpath("//div[contains(text(), 'Selected locations:')]").isDisplayed();
   }

   public TrainingListScreen clickLocationSearchClose() {
     Element.byXpath("//span[@ng-click='clearAllLocations(totalLocationList)']").click();
     return this;
   }
}
