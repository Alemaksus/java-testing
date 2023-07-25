package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_DROPDOWN_MANAGEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HEADER_MANAGEMENT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HEADER_SIGN_OUT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HEADER_TRAINING_MANAGEMENT_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactHeaderScreen extends AbstractScreen {

  private static final String MENU_DROPDOWN_PATTERN = "//div[contains(@class, 'nav-list__item menu-control__toggle') and text()='%s']";
  private static final String MENU_DROPDOWN_ITEM_PATTERN = ".nav-container a.menu-control__item[href*='%s']";

  private Element universityProgramLogo = Element.byCss("a[href='/']");
  private Element headerContainer = Element.byTagName("header");
  private Element trainingTab = Element.byXpath(
      "//a[contains(@href,'Platform')and not(contains(@class,'sc-'))]");
  private Element userInfoToggle = Element.byXpath("//img[contains(@src,'avatar')]");
  private Element signOutButton = Element.byXpath(
      String.format("//a[text()='%s']", getValueOf(REACT_HEADER_SIGN_OUT_BUTTON)));
  private Element navigationTab = Element.byXpath("//div[@class ='nav-menu']/*");
  private Element managementDropdown = Element
      .byXpath("//*[text()='%s']/..//*[name()='svg']",
          getValueOf(REACT_HEADER_MANAGEMENT_BUTTON));
  private Element trainingManagementLink = Element
      .byXpath("//div[@class='uui-popper']//a[contains(@href,'/Management') and text()='%s']",
          getValueOf(REACT_HEADER_TRAINING_MANAGEMENT_BUTTON));
  private Element trainingListLink = Element.byXpath(
      "(//a[contains(@href,'TrainingList')and not(contains(@class,'sc-'))])[2]");
  private Element languageDropdown = Element.byXpath("//div[contains(@class,'uui-button-box')]");
  private Element cogwheelButton = Element
      .byXpath("//a[contains(@class,'button') and contains(@class,'settings')]//div");
  private Element eventsLink = Element.byXpath("//div[@class='nav-menu']/a[contains(@href,'/event')]");
  private Element languageControlDropdown = Element.byXpath("//div[contains(@class,'language-control dropdown')]");
  private Element managementNavigationLink = Element.byXpath(
      MENU_DROPDOWN_PATTERN, getValueOf(HEADER_DROPDOWN_MANAGEMENT));
  private Element managementProgramsLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "trainings");

  @Override
  public boolean isScreenLoaded() {
    return isEpamLogoDisplayed();
  }

  public boolean isHeaderContainerDisplayed() {
    return headerContainer.isDisplayed();
  }

  public boolean isEpamLogoDisplayed() {
    return universityProgramLogo.isDisplayed();
  }

  public boolean isAllNavigationTabsDisplayed() {
    return navigationTab.isAllElementsDisplayed();
  }

  public ReactHeaderScreen waitTrainingTabTextToBePresent(String expectedText) {
    trainingTab.waitTextToBeAfterScreenRefresh(expectedText);
    return this;
  }

  public ReactHeaderScreen waitTrainingTabForVisibility() {
    trainingTab.waitForVisibility(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
    return this;
  }

  public String getTrainingTabText() {
    return trainingTab.getText();
  }

  public ReactHeaderScreen clickUserInfoToggle() {
    userInfoToggle.click();
    return this;
  }

  public HeaderScreen signOut() {
    return clickSignOutButton()
        .waitLogOutButtonVisibility()
        .clickLogOutButton();
  }

  public HeaderScreen clickSignOutButton() {
    signOutButton.click();
    return new HeaderScreen();
  }

  public ReactTrainingManagementScreen clickTrainingManagementLink() {
    managementDropdown.click();
    trainingManagementLink.click();
    return new ReactTrainingManagementScreen();
  }

  public TrainingListScreen clickTrainingListLink() {
    trainingListLink.click();
    return new TrainingListScreen();
  }

  public boolean isLanguageDropdownDisplayed() {
    return languageDropdown.isDisplayed();
  }

  public boolean isCogwheelButtonDisplayed() {
    return cogwheelButton.isDisplayed();
  }

  public boolean isUserInfoToggleDisplayed() {
    return userInfoToggle.isDisplayed();
  }

  public EventsScreen clickEventLink() {
    eventsLink.click();
    return new EventsScreen();
  }

  public ReactHeaderScreen waitLanguageControlDropdownDisplayed() {
    languageControlDropdown.waitForVisibility();
    return this;
  }

  public ReactHeaderScreen clickLanguageControlDropdown() {
    languageControlDropdown.click();
    return this;
  }

  public ReactTrainingManagementScreen clickReactTrainingManagementLink() {
    managementNavigationLink.click();
    managementProgramsLink.click();
    return new ReactTrainingManagementScreen();
  }
}
