package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_SCREEN_OUR_SKILLS_TITTLE;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElement;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElementEnum;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.CSS;
import javax.swing.text.html.CSS.Attribute;

public class AboutScreen extends AbstractScreen {

  private static final String URL_IMAGE_REGISTRATION_BANNER = "/Content/themes/Redesign/images/pattern-student-desk.jpg";
  private static final String URL_IMAGE_INTERVIEW_BANNER = "/Content/themes/Redesign/images/pattern-interview-desk.jpg";
  private static final String URL_IMAGE_TRAINING_BANNER = "/Content/themes/Redesign/images/pattern-training-desk.jpg";
  private static final String URL_IMAGE_WORKING_BANNER = "/Content/themes/Redesign/images/pattern-work-desc.jpg";
  private static final String URL_IMAGE_TRAINING_BORDER = "/Content/themes/Redesign/images/step-training-border.svg";
  private static final String TRAINING_STAGES_BORDER_CSS_SELECTOR = "div.steps-list__container--border";
  private static final String ARROWS_ELEMENT_CSS_SELECTOR = "div.steps-list__svg-container--arrows";
  private static final String URL_IMAGE_ARROWS_PATTERN = "/Content/themes/Redesign/images/arrows.svg";
  private static final String COUNTRY_TAB_LOCATOR = "//section[contains(@class,'map-section')]//li[contains(text(),'%s')]";
  private static final String TRAINING_CENTER_CHOOSE = "//span[contains(text(),'%s')]";
  private static final String COUNTRY_CHOSEN_VISIBILITY =
      TRAINING_CENTER_CHOOSE + "/ancestor::*[contains(@class,'tab-nav__item')]";
  private static final String CITY_LOCATOR = "//p[contains(@class,'city-item__city-title') and text()='%s']";
  private static final String CITY_ARROW_BUTTON = CITY_LOCATOR + "/following-sibling::button";
  private static final String TITLE_PATTERN = "//*[contains(@class,'section-ui__title')][contains(text(),'%s')]";
  private static final String TRAINING_CENTER_COUNTRY_PATTERN = "  //*[contains(@tab-title,'OurTrainingCenters')]//*[contains(text(),'%s')]";
  private static final String TRAINING_CENTER_CITY_LINK_PATTERN = "//a[@class='our-centers__item-link']/span[contains(text(),'%s')]";
  private static final String VISIBLE_TRAINING_CENTER_CARDS_PATTERN =
      "//*[contains(@tab-title,'OurTrainingCenters')]"
          + "//*[contains(@class,'ng-isolate-scope')and not(contains(@class,'ng-hide'))]";
  private static final String ZOOM_OUT_BUTTON_LOCATOR_PATTERN = "//button[@class='gm-control-active'and @title='%s']";
  private static final String ZOOM_IN_BUTTON_LOCATOR_PATTERN = "//button[@class='gm-control-active'and @title='%s']";
  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";

  private Element aboutUsBanner = Element.byCss("div.hero-banner--about");
  private Element historyContainer = Element.byClassName("history-section__container");
  private Element historySectionTitle = Element.byXpath("//h5//ancestor::section//h2");
  private Element yearOfEvent = Element.byXpath("//h5");
  private Element eventDescription = Element
      .byXpath("//section[@class='history-section__period']/p");
  private Element aboutUsBannerContainerElement = Element.byClassName("container");
  private Element homeButtonElement = Element.byXpath("//li/a[contains(@href,'Home')]");
  private Element joinUsContainerElement = Element
      .byXpath("//div[@class='tab-component']/parent::section");
  private Element mapSectionElement = Element
      .byXpath("//div[@class='map-component']/ancestor::section");
  private Element joinUsSectionTitle = Element
      .byXpath("//div[@class='tab-component']/preceding-sibling::h2");
  private Element registrationStepLink = Element.byXpath(
      "//div[contains(@class , 'tab-nav__item--interview-active')]//preceding-sibling::div/span");
  private Element interviewStagesLink = Element
      .byXpath("//div[contains(@class , 'tab-nav__item--interview-active')]/span");
  private Element trainingStagesLink = Element
      .byXpath("//div[contains(@class, 'tab-nav__item--training-active')]/span");
  private Element workingAtEpamLink = Element
      .byXpath("//div[contains(@class, 'tab-nav__item--work-active')]/span");
  private Element registrationSectionContainerElement = Element.byXpath("//div[@*='tab-1']");
  private Element interviewSectionContainerElement = Element.byXpath("//div[@*='tab-2']");
  private Element trainingSectionContainerElement = Element.byXpath("//div[@*='tab-3']");
  private Element workingSectionContainerElement = Element.byXpath("//div[@*='tab-4']");
  private Element containerBanner = Element.byClassName("tab-content__list");
  private Element registrationStepCardElement = Element.byXpath
      ("//section[contains(@class,'join-epam__student-step') and contains(@class,'steps-list__item')]");
  private Element interviewStagesCardElement = Element.byXpath(
      ("//section[contains(@class,'join-epam__interview-step') and contains(@class,'steps-list__item')]"));
  private Element trainingStagesCardElement = Element.byXpath(
      "//section[contains(@class, 'join-epam__training-step') and contains(@class, 'steps-list__item')]");
  private Element workingOfferCardElement = Element.byXpath(
      "//section[contains(@class, 'steps-list__item') and contains(@class, 'join-epam__work-step')]");
  private Element titleCard = Element.byXpath(".//h4");
  private Element descriptionCard = Element.byXpath(".//p");
  private Element iconCard = Element.byXpath("//div/div[contains(@class, 'svg-image')]");
  private Element interviewStagesTitleElement = Element
      .byXpath("//div[@*='tab-2']//p[contains(@class,'steps-list__description')]");
  private Element trainingCentersSectionTitle = Element.byCss("div.our-centers h2");
  private Element trainingCentersCountryLink = Element
      .byXpath("//div[contains(@class,'our-centers')]//div[contains(@class,'tab-nav')]//span");
  private Element trainingCentersMapCountryTab = Element
      .byXpath("//section[contains(@class,'map-section')]//li");
  private Element map = Element.byId("map");
  private Element mapButton = Element
      .byXpath("//div[@class='gmnoprint']//div[@class='gm-style-mtc'][1]");
  private Element satelliteButton = Element
      .byXpath("//div[@class='gmnoprint']//div[@class='gm-style-mtc'][2]");
  private Element toggleFullScreenButton = Element
      .byXpath("//button[@class='gm-control-active gm-fullscreen-control']");
  private Element pegmanButton = Element.byXpath("//div[@class='gm-svpc']");
  private Element trainingStagesTitleElement = Element
      .byXpath("//div[@*='tab-3']//p[contains(@class,'steps-list__description')]");
  private Element aboutUsBannerTitle = Element.byCss(".hero-banner__heading");
  private Element aboutUsBannerText = Element
      .byXpath("//div[@class='hero-banner__text ng-binding']");
  private Element cityNameFromMapSection = Element
      .byXpath("//div[contains(@class,'city-item__city-container')]/p");
  private Element locationsContainerSectionAppears = Element
      .byCss("section.cities-container--scroll");
  private Element streetNameOfSelectedCity = Element.byXpath(
      "//li[contains(@class,'city-item--active')]//p[contains(@class,'city-item__street-name')]");
  private Element streetOfAllCities = Element
      .byXpath("//p[contains(@class,'city-item__street-name')]");
  private Element hatImages = Element.byXpath("//div[contains(@style,'z-index: 103')]//img");
  private Element trainingCenterCountries = Element.byXpath(
      "//*[contains(@tab-title,'OurTrainingCenters')]//*[contains(@class,'tab-nav__item')]");
  private Element activeCountryCards = Element.byXpath(
      VISIBLE_TRAINING_CENTER_CARDS_PATTERN + "//*[contains(@class,'owl-item')]");
  private Element activeCountrySliderArrows = Element.byXpath(VISIBLE_TRAINING_CENTER_CARDS_PATTERN
      + "//button[contains(@class,'slider-arrow')]");
  private Element trainingCentersSection = Element
      .byXpath("//section[@default-tab-name='defaultTabName']");
  private Element trainingCenterCitiesSection = Element
      .byXpath(VISIBLE_TRAINING_CENTER_CARDS_PATTERN);
  private Element trainingCentersCityName = Element
      .byXpath(VISIBLE_TRAINING_CENTER_CARDS_PATTERN + "//span");
  private Element trainingCentersPreviewImage = Element
      .byXpath(VISIBLE_TRAINING_CENTER_CARDS_PATTERN + "//div[contains(@style,'image')]");
  private Element titleOurSkillsSection = Element
      .byXpath(String.format(TEXT_LOCATOR_PATTERN, getValueOf(ABOUT_SCREEN_OUR_SKILLS_TITTLE)));
  private Element skillsListSection = Element
      .byXpath("//div[contains(@class,'skills-list') and contains(@class,'ng-isolate-scope')]");
  private Element skillCardWithOutPublishedStatusDescription = Element
      .byXpath("//a[@class='skill-item ng-scope']");
  private Element footer = Element.byXpath("//*[@id='footer']");
  private Element skillsBlock = Element.byXpath("//section[@class='container section-ui']");
  private Element skillCardIcon = Element.byXpath("//img[@class='skill-item__icon']");
  private Element skillName = Element.byXpath("//div[@class='skill-item__title ng-binding']");
  private Element ourTrainingCentersActiveCountry = Element.byXpath(
      TEXT_LOCATOR_PATTERN + "/parent::section//div[contains(@class,'tab-nav__item  active')]/span",
      getValueOf(ABOUT_OUR_TRAINING_CENTERS_SECTION_TITLE));

  public boolean isAboutUsBannerDisplayed() {
    return aboutUsBanner.isDisplayed();
  }

  public boolean isScreenLoaded() {
    return isJoinUSContainerDisplayed();
  }

  public boolean isHistoryTitleDisplayed() {
    return historySectionTitle.isDisplayed();
  }

  public String getHistoryTitleText() {
    return historySectionTitle.getText();
  }

  private List<Element> getAllEventYears() {
    return yearOfEvent.getElements();
  }

  private List<Element> getAllElementsEventDescription() {
    return eventDescription.getElements();
  }

  public boolean isAllYearsDisplayed() {
    return getAllEventYears().stream().allMatch(e -> e.isDisplayed());
  }

  public boolean isAllEventDescriptionsDisplayed() {
    return getAllElementsEventDescription().stream().allMatch(e -> e.isDisplayed());
  }

  public boolean isBannerContainerDisplayed() {
    return aboutUsBannerContainerElement.isDisplayed();
  }

  public boolean isHomeButtonDisplayed() {
    return homeButtonElement.isDisplayed();
  }

  public boolean isHistoryContainerDisplayed() {
    return historyContainer.isDisplayed();
  }

  public boolean isJoinUSContainerDisplayed() {
    return joinUsContainerElement.isDisplayed(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
  }

  public boolean isTrainingCenterDisplayed(String chosenTrainingCenterName) {
    return Element.byXpath(TRAINING_CENTER_CHOOSE, chosenTrainingCenterName).isDisplayed();
  }

  public boolean isMapSectionDisplayed() {
    return mapSectionElement.isDisplayed();
  }

  public boolean isJoinUsTitleDisplayed() {
    return joinUsSectionTitle.isDisplayed();
  }

  public boolean isRegistrationStepLinkDisplayed() {
    return registrationStepLink.isDisplayed();
  }

  public boolean isInterviewStagesLinkDisplayed() {
    return interviewStagesLink.isDisplayed();
  }

  public boolean isTrainingStagesLinkDisplayed() {
    return trainingStagesLink.isDisplayed();
  }

  public boolean isWorkingOnEpamLinkDisplayed() {
    return workingAtEpamLink.isDisplayed();
  }

  public String getJoinUsTitleText() {
    return joinUsSectionTitle.getText();
  }

  public String getRegistrationStepText() {
    return registrationStepLink.getText();
  }

  public String getInterviewStagesText() {
    return interviewStagesLink.getText();
  }

  public String getTrainingStagesText() {
    return trainingStagesLink.getText();
  }

  public String getWorkingOnEpamText() {
    return workingAtEpamLink.getText();
  }

  public boolean isBannerOfStepsDisplayed() {
    return containerBanner.isDisplayed();
  }

  public AboutScreen clickRegistrationLink() {
    registrationStepLink.click();
    return this;
  }

  public AboutScreen clickInterviewLink() {
    interviewStagesLink.click();
    return this;
  }

  public AboutScreen clickTrainingLink() {
    trainingStagesLink.click();
    return this;
  }

  public AboutScreen clickWorkingLink() {
    workingAtEpamLink.click();
    return this;
  }

  public AboutScreen waitUntilInterviewStagesSectionLoaded() {
    interviewSectionContainerElement.waitUntilAttributeGetsValue(Attribute.DISPLAY.toString(),
        Attribute.DISPLAY.getDefaultValue());
    return this;
  }

  public AboutScreen waitUntilTrainingStagesSectionLoaded() {
    trainingSectionContainerElement.waitUntilAttributeGetsValue(Attribute.DISPLAY.toString(),
        Attribute.DISPLAY.getDefaultValue());
    return this;
  }

  public AboutScreen waitUntilWorkingAtEpamSectionLoaded() {
    workingSectionContainerElement.waitUntilAttributeGetsValue(Attribute.DISPLAY.toString(),
        Attribute.DISPLAY.getDefaultValue());
    return this;
  }

  private boolean isBannerHasCorrectURL(Element element, String urlImage) {
    return element.findChild(Element.byXpath("./div"))
        .getCssValue(Attribute.BACKGROUND_IMAGE.toString()).contains(urlImage);

  }

  public boolean isRegistrationBannerHasCorrectBackground() {
    return isBannerHasCorrectURL(registrationSectionContainerElement,
        URL_IMAGE_REGISTRATION_BANNER);
  }

  public boolean isInterviewBannerHasCorrectBackground() {
    return isBannerHasCorrectURL(interviewSectionContainerElement, URL_IMAGE_INTERVIEW_BANNER);
  }

  public boolean isTrainingBannerHasCorrectBackground() {
    return isBannerHasCorrectURL(trainingSectionContainerElement, URL_IMAGE_TRAINING_BANNER);
  }

  public boolean isWorkingBannerHasCorrectBackground() {
    return isBannerHasCorrectURL(workingSectionContainerElement, URL_IMAGE_WORKING_BANNER);
  }

  public boolean isRegistrationSectionDisplayed() {
    return registrationSectionContainerElement.getCssValue(Attribute.DISPLAY.toString())
        .equals(Attribute.DISPLAY.getDefaultValue());
  }

  public boolean isTrainingStagesSectionHasCorrectBorder() {
    return new PseudoElement().getPseudoElementCssPropertyValue(TRAINING_STAGES_BORDER_CSS_SELECTOR,
            Attribute.BACKGROUND_IMAGE.toString(), PseudoElementEnum.AFTER)
        .contains(URL_IMAGE_TRAINING_BORDER);
  }

  public boolean isInterviewSectionDisplayed() {
    return interviewSectionContainerElement.getCssValue(Attribute.DISPLAY.toString())
        .equals(Attribute.DISPLAY.getDefaultValue());
  }

  public boolean isTrainingStagesSectionDisplay() {
    return trainingSectionContainerElement.getCssValue(Attribute.DISPLAY.toString())
        .equals(Attribute.DISPLAY.getDefaultValue());
  }

  public boolean isWorkingAtEpamSectionDisplayed() {
    return workingSectionContainerElement.getCssValue(Attribute.DISPLAY.toString())
        .equals(Attribute.DISPLAY.getDefaultValue());
  }

  private List<Element> getAllRegistrationStepCards() {
    return registrationStepCardElement.getElements();
  }

  private List<Element> getAllInterviewStepCards() {
    return interviewStagesCardElement.getElements();
  }

  private List<Element> getAllTrainingStepCards() {
    return trainingStagesCardElement.getElements();
  }

  private List<Element> getAllWorkingOfferCards() {
    return workingOfferCardElement.getElements();
  }

  public int getRegistrationStepCardsQuantity() {
    return getAllRegistrationStepCards().size();
  }

  public int getInterviewStepCardsQuantity() {
    return getAllInterviewStepCards().size();
  }

  public int getTrainingStepCardsQuantity() {
    return getAllTrainingStepCards().size();
  }

  public int getWorkingOfferStepCardQuantity() {
    return getAllWorkingOfferCards().size();
  }

  private String getChildElementText(Element parent, Element child) {
    return parent.findChild(child).getText();
  }

  public String getRegistrationStepCardTitleByIndex(int cardIndex) {
    return getChildElementText(getAllRegistrationStepCards().get(cardIndex), titleCard);
  }

  public String getRegistrationStepCardDescriptionByIndex(int cardIndex) {
    return getChildElementText(getAllRegistrationStepCards().get(cardIndex), descriptionCard);
  }

  public String getInterviewStepCardTitleByIndex(int cardIndex) {
    return getChildElementText(getAllInterviewStepCards().get(cardIndex), titleCard);
  }

  public String getInterviewStepCardDescriptionByIndex(int cardIndex) {
    return getChildElementText(getAllInterviewStepCards().get(cardIndex), descriptionCard);
  }

  public String getTrainingStepCardTitleByIndex(int index) {
    return getChildElementText(getAllTrainingStepCards().get(index), titleCard);
  }

  public String getTrainingStepCardDescriptionByIndex(int index) {
    return getChildElementText(getAllTrainingStepCards().get(index), descriptionCard);
  }

  public String getWorkingOffersStepCardDescriptionByIndex(int cardIndex) {
    return getChildElementText(getAllWorkingOfferCards().get(cardIndex), descriptionCard);
  }

  private boolean isCardHasIcon(List<Element> cards, int index) {
    return cards.get(index)
        .findChild(iconCard)
        .getCssValue(Attribute.BACKGROUND_IMAGE.toString())
        .length() > 0;
  }

  public boolean isRegistrationStepCardHasIcon(int cardIndex) {
    return isCardHasIcon(getAllRegistrationStepCards(), cardIndex)
        && isRegistrationSectionDisplayed();
  }

  public boolean isInterviewStepCardHasIcon(int cardIndex) {
    return isCardHasIcon(getAllInterviewStepCards(), cardIndex) && isInterviewSectionDisplayed();
  }

  public boolean isTrainingStepCardHasIcon(int cardIndex) {
    return isCardHasIcon(getAllTrainingStepCards(), cardIndex) && isTrainingStagesSectionDisplay();
  }

  public boolean isWorkingOfferStepCardHasIcon(int cardIndex) {
    return isCardHasIcon(getAllWorkingOfferCards(), cardIndex)
        && isWorkingAtEpamSectionDisplayed();
  }

  public String getInterviewStagesTitleText() {
    return interviewStagesTitleElement.getText();
  }

  public String getTrainingStagesTitleText() {
    return trainingStagesTitleElement.getText();
  }

  public boolean isWorkingOfferStepCardHasArrowPattern(int cardIndex) {
    return new PseudoElement().getPseudoElementCssPropertyValueByIndex(ARROWS_ELEMENT_CSS_SELECTOR,
            Attribute.BACKGROUND_IMAGE.toString(), PseudoElementEnum.AFTER, cardIndex)
        .contains(URL_IMAGE_ARROWS_PATTERN);
  }

  public String getTrainingCentersSectionTitleText() {
    return trainingCentersSectionTitle.getText();
  }

  public AboutScreen clickChosenCountry(String chosenCountryName) {
    Element.byXpath(TRAINING_CENTER_CHOOSE, chosenCountryName).click();
    return this;
  }

  public AboutScreen waitChosenCountryVisibility(String chosenCountryName) {
    Element.byXpath(COUNTRY_CHOSEN_VISIBILITY, chosenCountryName)
        .waitForVisibility(LONG_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public CenterScreen clickChosenTrainingCenter(String chosenTrainingCenterName) {
    Element.byXpath(TRAINING_CENTER_CHOOSE, chosenTrainingCenterName).click();
    return new CenterScreen();
  }

  private List<Element> getTrainingCentersMapCountryTabList() {
    return trainingCentersMapCountryTab.getElements();
  }

  private List<Element> getTrainingCentersCountryLinkList() {
    return trainingCentersCountryLink.getElements();
  }

  public int getTrainingCentersCountryLinksListSize() {
    return getTrainingCentersCountryLinkList().size();
  }

  public String getTrainingCentersCountryLinkByIndex(int countryIndex) {
    return getTrainingCentersCountryLinkList().get(countryIndex).getText();
  }

  public AboutScreen clickTrainingCentersCountryLinkByIndex(int countryLink) {
    getTrainingCentersCountryLinkList().get(countryLink).click();
    return this;
  }

  public boolean isTrainingCentersMapCountryTabHasCorrectText(String countryName) {
    List<Element> tabList = getTrainingCentersMapCountryTabList();
    for (Element element : tabList) {
      if (element.getText().equalsIgnoreCase(countryName)) {
        return true;
      }
    }
    return false;
  }

  public boolean isTrainingCentersCountryLinkHasCorrectText(String countryName) {
    List<Element> tabList = getTrainingCentersCountryLinkList();
    for (Element element : tabList) {
      if (element.getText().equalsIgnoreCase(countryName)) {
        return true;
      }
    }
    return false;
  }

  public boolean isMapDisplayed() {
    return map.isDisplayed();
  }

  public boolean isMapButtonDisplayed() {
    return mapButton.isDisplayed();
  }

  public boolean isSatelliteButtonDisplayed() {
    return satelliteButton.isDisplayed();
  }

  public boolean isToggleFullScreenButtonDisplayed() {
    return toggleFullScreenButton.isDisplayed();
  }

  public boolean isPegmanButtonDisplayed() {
    return pegmanButton.isDisplayed();
  }

  public boolean isZoomInButtonDisplayed(String locatorTitle) {
    return Element.byXpath(ZOOM_IN_BUTTON_LOCATOR_PATTERN, locatorTitle).isDisplayed();
  }

  public boolean isZoomOutButtonDisplayed(String locatorTitle) {
    return Element.byXpath(ZOOM_OUT_BUTTON_LOCATOR_PATTERN, locatorTitle).isDisplayed();
  }

  public HeaderScreen clickHomeButton() {
    homeButtonElement.click();
    return new HeaderScreen();
  }

  public String getAboutUsBannerBackground() {
    return aboutUsBanner.getCssValue(Attribute.BACKGROUND.toString());
  }

  public String getAboutUsBannerTitleText() {
    return aboutUsBannerTitle.getText();
  }

  public String getAboutUsBannerQuestionText() {
    return aboutUsBannerText.getText();
  }

  public String getAboutUsBannerDescriptionText() {
    return aboutUsBannerText.getText();
  }

  public AboutScreen clickChosenCountryTab(String chosenCountryName) {
    Element.byXpath(String.format(COUNTRY_TAB_LOCATOR, chosenCountryName)).click();
    return new AboutScreen();
  }

  public String getCountryTabBackgroundColor(String chosenCountryName) {
    return Element.byXpath(String.format(COUNTRY_TAB_LOCATOR, chosenCountryName))
        .getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public AboutScreen waitLocationsContainerSectionAppers() {
    locationsContainerSectionAppears.waitForVisibility();
    return new AboutScreen();
  }

  public boolean isLocationsListContainerDisplayed() {
    return locationsContainerSectionAppears.isDisplayed();
  }

  public ArrayList<String> getCitiesFromCountryList() {
    ArrayList<String> cityNamesList = new ArrayList<>();
    List<Element> cityList = cityNameFromMapSection.getElements();
    for (Element city : cityList) {
      cityNamesList.add(city.getText());
    }
    return cityNamesList;
  }

  public int getCitiesCountFromSelectedCountry() {
    return cityNameFromMapSection.getElements().size();
  }

  public boolean isArrowForCityDisplayed(String cityName) {
    return Element.byXpath(CITY_ARROW_BUTTON, cityName).isDisplayed();
  }

  public AboutScreen hoverOnCity(String cityName) {
    Element.byXpath(CITY_LOCATOR, cityName).mouseOver();
    return this;
  }

  public AboutScreen clickOnCity(String cityName) {
    Element.byXpath(CITY_LOCATOR, cityName).click();
    return this;
  }

  public String getCityTextColor(String cityName) {
    return Element.byXpath(CITY_LOCATOR, cityName).getCssValue(CSS.Attribute.COLOR.toString());
  }

  public int getCountOfStreetsForASelectedCity() {
    return streetNameOfSelectedCity.getElements().size();
  }

  public int getCountOfStreetNamesForSelectedCountry() {
    return streetOfAllCities.getElements().size();
  }

  public int getCountOfHatsOnMapForSelectedCountry() {
    return hatImages.getElements().size();
  }

  public AboutScreen hoverSectionByTitle(String title) {
    Element.byXpath(TITLE_PATTERN, title).mouseOver();
    return this;
  }

  public String getSectionTitleTextByTitle(String title) {
    return Element.byXpath(TITLE_PATTERN, title).getText();
  }

  public List<String> getTrainingCenterCountriesTextList() {
    return trainingCenterCountries.getElementsText();
  }

  public AboutScreen clickTrainingCenterCountryByName(String countryName) {
    Element.byXpath(TRAINING_CENTER_COUNTRY_PATTERN, countryName)
        .waitForVisibility(LONG_TIMEOUT_FOR_PAGE_LOAD).click();
    return this;
  }

  public AboutScreen clickTrainingCenterCityByName(String city) {
    Element.byXpath(TRAINING_CENTER_CITY_LINK_PATTERN, city).clickJs();
    return this;
  }

  public int getTrainingCenterActiveCountryCardsListSize() {
    return activeCountryCards.getElements().size();
  }

  public int getTrainingCenterActiveCountryCityNamesListSize() {
    return getTrainingCenterActiveCountryCityNamesList().size();
  }

  private List<Element> getTrainingCenterActiveCountryCityNamesList() {
    return trainingCentersCityName.getElements();
  }

  public boolean areTrainingCenterActiveCountryCityNameButtonsHaveCorrectColor(
      String backgroundColor) {
    return getTrainingCenterActiveCountryCityNamesList().stream()
        .allMatch(trainingCentersCityName -> trainingCentersCityName.
            getCssValue(Attribute.BACKGROUND_COLOR.toString()).equals(backgroundColor));
  }

  public int getTrainingCenterActiveCountryPreviewImagesListSize() {
    return trainingCentersPreviewImage.getElements().size();
  }

  public boolean isActiveCountrySliderArrowsDisplayed() {
    return activeCountrySliderArrows.isAllElementsDisplayed();
  }

  public AboutScreen waitForTrainingCenterSectionVisibility() {
    trainingCentersSection.waitForVisibility();
    return this;
  }

  public AboutScreen waitForTrainingCenterCitiesSectionVisibility() {
    trainingCenterCitiesSection.waitForVisibility();
    return this;
  }

  public boolean isOurSkillsSectionIsDisplayed() {
    skillsListSection.waitForVisibility();
    return titleOurSkillsSection.isDisplayed() && skillsListSection.isDisplayed();
  }

  public int getNumberOfSkillCardsWithoutDescription() {
    return (int) skillCardWithOutPublishedStatusDescription().stream().filter(Element::isDisplayedNoWait)
        .count();
  }

  public List<Element> skillCardWithOutPublishedStatusDescription() {
    return skillCardWithOutPublishedStatusDescription.getElements();
  }

  public int getAvailableSkillsCardsCount() {
      return skillCardWithOutPublishedStatusDescription().size();
  }

  public void clickSkillCardByIndex(int skillIndex) {
    skillCardWithOutPublishedStatusDescription().get(skillIndex).waitForClickableAndClick();
  }

  public boolean isSkillsBlockAboveFooterDisplayed() {
    return footer.getWrappedWebElement().getLocation().getY()
        > skillsBlock.getWrappedWebElement().getLocation().getY();
  }

  public boolean isAllSkillCardIconsAndSkillNameDisplayed() {
    return skillCardIcon.isAllElementsDisplayed() && skillName.isAllElementsDisplayed();
  }

  public String getTrainingCenterActiveCountryName() {
    return ourTrainingCentersActiveCountry.getText();
  }
}
