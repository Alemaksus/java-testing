package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class EventsScreen extends AbstractScreen {

  private static final String DROPDOWN_LIST_OPTION_PATTERN =
      "//*[contains(@class,'filter-row_filter-row')]/div[text()='%s']";
  private static final String DROPDOWN_LIST_PATTERN = "//*[contains(@class,'rd-filter_rd-filter_')]//div[text()='%s']";
  private static final String EVENT_NAME_PATTERN = "//*[contains(@class,'event-card-title')][text()='%s']";
  private static final String EVENT_DETAILS_PATTERN =
      EVENT_NAME_PATTERN + "/..//*[contains(@class,'%s')]";
  private static final String EVENT_DATE_LOCATOR_PATTERN = String.format(EVENT_DETAILS_PATTERN,
      "%s", "event-card-date-value");
  private static final String EVENT_LOCATION_LOCATOR_PATTERN = String.format(EVENT_DETAILS_PATTERN,
      "%s", "event-card-location-value");
  private static final String EVENT_LANGUAGE_LOCATOR_PATTERN =String.format(EVENT_DETAILS_PATTERN,
      "%s", "event-card-language-value");
  private static final String EVENT_HASHTAG_LOCATOR_PATTERN = String.format(EVENT_DETAILS_PATTERN,
      "%s", "event-card-hashtags");
  private static final String EVENT_STATUS = "//*[contains(@class,'event-card-title')][text()='%s']/preceding::div[text()='%s']";

  private final Element countryDropDown = Element.byXpath(
      "//div[contains(@class,'country-toggle')]");
  private final Element passedEventsButton = Element.byXpath("//div[contains(@class,'past')]");
  private final Element eventCards = Element.byXpath(
      "//*[contains(@class,'events-catalog-cards-block-card')]");
  private final Element filtersDropDowns = Element.byXpath("//div[@ng-repeat='filter in filters']");
  private final Element filtersTimelineSwitcher = Element.byXpath(
      "//div[contains(@class, 'tabs__info-tab')]");
  private final Element activeTab = Element.byCss("div.active-tab");
  private final Element clearAllFoundedResultsButton = Element.byCss(
      "div.founded-result i.fa-clear-all");
  private final Element upcomingEventsShowMoreButton = Element.byXpath(
      "//*[@class='events-catalog-cards-block-upcoming']//*[contains(@class,'show-more-button')]");
  private final Element passedEventsShowMoreButton = Element.byXpath(
      "//*[@class='events-catalog-cards-block-passed']//*[contains(@class,'show-more-button')]");
  private final Element acceptCookiesButton = Element.byId("onetrust-accept-btn-handler");
  private final Element upcomingEventCards = Element.byXpath
      ("//*[contains(@class,'upcoming-cards')]//div[contains(@class,'event-card_event-card-title')]");
  private final Element passedEventCards = Element.byXpath
      ("//*[contains(@class,'passed-cards')]//div[contains(@class,'event-card_event-card-title')]");

  @Override
  public boolean isScreenLoaded() {
    return eventCards.isAllElementsDisplayed();
  }

  public EventsScreen waitEventCardsVisibility() {
    eventCards.waitForPresenceOfAllElements(SHORT_TIME_OUT_IN_SECONDS);
    return this;
  }

  public String getEventStatusText(String eventName, String status) {
    return Element.byXpath(String.format(EVENT_STATUS, eventName, status)).getText();
  }

  public EventsScreen clickDropdownList(String dropdownListName) {
    Element.byXpath(String.format(DROPDOWN_LIST_PATTERN, dropdownListName))
        .waitForClickableAndClick();
    return this;
  }

  public EventsScreen chooseDropdownListOption(String dropdownListName, String option) {
    Element.byXpath(String.format(DROPDOWN_LIST_PATTERN, dropdownListName))
        .waitForClickableAndClick();
    Element.byXpath(String.format(DROPDOWN_LIST_OPTION_PATTERN, option)).waitForClickableAndClick();
    return this;
  }

  public EventsScreen clickCountryDropDown() {
    countryDropDown.click();
    return this;
  }

  public boolean isEventDisplayedByName(String eventName) {
    return Element.byXpath(EVENT_NAME_PATTERN, eventName).isDisplayedShortTimeOut();
  }

  public List<String> getDropDownFilterElementsText() {
    return filtersDropDowns.getElementsText();
  }

  public List<String> getSwitcherFilterElementsText() {
    return filtersTimelineSwitcher.getElementsText();
  }

  public EventPreviewScreen clickEventByName(String eventName) {
    Element.byXpath(EVENT_NAME_PATTERN, eventName).click();
    return new EventPreviewScreen();
  }

  public EventsScreen clickPassedEventsButton() {
    passedEventsButton.click();
    return this;
  }

  public boolean isLanguageOfEventDisplayedByName(String eventName) {
    return Element.byXpath(EVENT_LANGUAGE_LOCATOR_PATTERN, eventName).isDisplayedNoWait();
  }

  public boolean isDateOfEventDisplayedByName(String eventName) {
    return Element.byXpath(EVENT_DATE_LOCATOR_PATTERN, eventName).isDisplayedNoWait();
  }

  public boolean isLocationOfEventDisplayedByName(String eventName) {
    return Element.byXpath(EVENT_LOCATION_LOCATOR_PATTERN, eventName).isDisplayedNoWait();
  }

  public boolean isHashtagOfEventDisplayedByName(String eventName) {
    return Element.byXpath(EVENT_HASHTAG_LOCATOR_PATTERN, eventName).isDisplayedNoWait();
  }

  public String getTextByActiveTab() {
    return activeTab.getText();
  }

  public EventsScreen clickClearAllFoundedResultsButton() {
    clearAllFoundedResultsButton.click();
    return this;
  }

  public boolean isUpcomingEventsCardsDisplayed() {
    return upcomingEventCards.getElements()
        .stream()
        .allMatch(Element::isDisplayed);
  }

  public boolean isPassedEventsCardsDisplayed() {
    return passedEventCards.getElements()
        .stream()
        .allMatch(Element::isDisplayed);
  }

  public EventsScreen clickUpcomingEventsShowMoreButton() {
    upcomingEventsShowMoreButton.waitForClickableAndClick();
    return this;
  }

  public EventsScreen clickPassedEventsShowMoreButton() {
    passedEventsShowMoreButton.waitForClickableAndClick();
    return this;
  }
}
