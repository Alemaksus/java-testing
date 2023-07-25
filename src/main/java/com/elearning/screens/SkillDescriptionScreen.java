package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class SkillDescriptionScreen extends AbstractScreen {

  private static final String SKILL_CARD_DESCRIPTION_BY_NAME_PATTERN =
      "//div[@class='skill-item__title' and text()='%s']//following::div";

  private final Element headerSection = Element.byId("header");
  private final Element ourSkillsTitle = Element.byCss("h1.section-ui__title");
  private final Element skillCards =
      Element.byXpath(
          "//div[contains(@class,'our-skills-list')]/div[contains(@class,'skill-item')]");
  private final Element skillCardIcon =
      Element.byXpath(
          "//div[contains(@class,'our-skills-list')]//img[@class='skill-item__icon']");
  private final Element skillCardsTitle =
      Element.byXpath(
          "//div[contains(@class,'our-skills-list')]//div[@class='skill-item__title']");
  private final Element skillCardsDescription =
      Element.byXpath(
          "//div[contains(@class,'skills-list')]//div[@class='skill-item__title']");
  private final Element availableTrainingsLink =
      Element.byXpath(
          "//div[contains(@class,'our-skills-list')]//a[@class='skill-item__trainings-link']");
  private Element descriptionText = Element.byXpath("//div[@class='skill-details-header']");
  private Element skillsDetails = Element.byXpath("//div[@class='skill-details']");
  private Element skillsSectionTitle = Element.byXpath("//h1[@class='section-ui__title']");
  private Element cardsSection = Element.byXpath("//div[@class='our-skills-list']");
  private Element skillCardsWithDescription = Element.byXpath(
      "//div[contains(@class,'skill-item') and contains(@class,'skill-item__link--linked')]");
  private Element availableTrainingButton = Element.byXpath(
      "//a[@class='skill-item__trainings-link']");
  private Element skillsCards = Element.byXpath("//div[@class='our-skills-list']");
  private Element skillLinc = Element.byXpath("//div[@class='skill-item__title']");

  @Override
  public boolean isScreenLoaded() {
    return headerSection.isDisplayed();
  }

  public SkillDescriptionScreen waitScreenLoaded() {
    skillCards.waitForVisibility();
    return this;
  }

  public boolean isOurSkillsTittleDisplayed() {
    return ourSkillsTitle.isDisplayed();
  }

  public String getOurSkillsTitleText() {
    return ourSkillsTitle.getText();
  }

  public List<Element> getSkillCardsList() {
    return skillCards.getElements();
  }

  public List<Element> getSkillsCardsDescriptionList() {
    return skillCardsDescription.getElements();
  }

  public List<Element> getSkillsCardsWithDescriptionList() {
    return skillCardsWithDescription.getElements();
  }

  public List<Element> getSkillsCardsLincList() {
    return skillLinc.getElements();
  }

  public int getAvailableSkillsCardsCount() {
    return getSkillsCardsDescriptionList().size();
  }

  public int getAvailableSkillsCardsWithDescriptionCount() {
    return getSkillsCardsWithDescriptionList().size();
  }

  public boolean isDisplayed(Element element) {
    return element.isDisplayed();
  }

  public boolean isAllSkillCardIconsDisplayed() {
    return skillCardIcon.isAllElementsDisplayed();
  }

  public boolean isAllSkillCardITitlesDisplayed() {
    return skillCardsTitle.isAllElementsDisplayed();
  }

  public int countSkillCardsWithDescriptionDisplayed(List<Element> skillsCardsDescriptionList) {
    return (int) skillsCardsDescriptionList.stream().filter(Element::isDisplayedNoWait).count();
  }

  public boolean isAllAvailableTrainingsLinksDisplayed() {
    return availableTrainingsLink.isAllElementsDisplayed();
  }

  public String getSkillCardDescriptionByName(String cardName) {
    return Element.byXpath(SKILL_CARD_DESCRIPTION_BY_NAME_PATTERN, cardName).getText();
  }

  public String getAvailableTrainingsLinkText() {
    return availableTrainingsLink.getText();
  }

  public boolean isDescriptionDisplayed() {
    return descriptionText.isDisplayed();
  }

  public boolean isSkillsDisplayed() {
    return skillsDetails.isDisplayed();
  }

  public int getNumberOfSkillCardsWithDescription() {
    return (int) getSkillsCardsWithDescriptionList().stream().filter(Element::isDisplayedNoWait)
        .count();
  }

  public void clickSkillCardByIndex(int skillIndex) {
    getSkillsCardsWithDescriptionList().get(skillIndex).waitForClickableAndClick();
  }

  public List<Element> getAvailableTrainingButtonsList() {
    return availableTrainingButton.getElements();
  }

  public void clickAvailableTrainingButtonByIndex(int skillIndex) {
    getAvailableTrainingButtonsList().get(skillIndex).waitForClickableAndClick();
  }

  public boolean isSkillScreenDisplayed() {
    return skillsSectionTitle.isDisplayed() && skillsCards.isDisplayed();
  }

  public int getNumberOfSkillCardsDescription() {
    return (int) getSkillsCardsDescriptionList().stream().filter(Element::isDisplayedNoWait)
        .count();
  }

  public int getAvailableSkillsCardsDescriptionCount() {
    return getSkillsCardsDescriptionList().size();
  }

  public String getSkillsTitleText(int skillIndex) {
    return getSkillsCardsLincList().get(skillIndex).getText();
  }
}
