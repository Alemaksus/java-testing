package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_APPLICATIONS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_ABOUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_BLOG;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_EVENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_PROGRAMS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_DROPDOWN_MANAGEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_PROFILE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_SETTINGS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_SIGN_IN_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HEADER_SIGN_OUT_BUTTON;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import javax.swing.text.html.CSS.Attribute;

public class HeaderScreen extends AbstractScreen {

  private static final String TEXT_LOCATOR_PATTERN = "//*[text()='%s']";
  private static final String MAIN_MENU_ITEM_BY_NAME_PATTERN = "//*[contains(@class,'nav-bar_menu-items') and contains(text(),'%s')]";
  private static final String MENU_DROPDOWN_ITEM_PATTERN = "a[href*='%s']";
  private static final String MANAGEMENT_MENU_DROPDOWN_ITEM_PATTERN = "//*[contains(@class,'styles_managements-dropdown')]//*[contains(text(),'%s')]";
  private Element pageHeader = Element.byXpath("//*[contains(@class,'nav-bar_main-menu')]");
  private Element mainMenuLogo = Element.byXpath("//*[contains(@class,'styles_logo')][@href]");
  private Element programsNavigationLink = Element.byXpath(
      MAIN_MENU_ITEM_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_PROGRAMS));
  private Element eventsNavigationLink = Element.byXpath(
      MAIN_MENU_ITEM_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_EVENTS));
  private Element skillsListNavigationLink = Element.byXpath(
      MAIN_MENU_ITEM_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_SKILLS));
  private Element blogNavigationLink = Element.byXpath(
      MAIN_MENU_ITEM_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_BLOG));
  private Element aboutNavigationLink = Element.byXpath(
      MAIN_MENU_ITEM_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_ABOUT));
  private Element managementNavigationLink = Element.byXpath(
      MANAGEMENT_MENU_DROPDOWN_ITEM_PATTERN, getValueOf(HEADER_DROPDOWN_MANAGEMENT));
  private Element notificationButton = Element.byXpath("//*[contains(@class,'notification_button')]");
  private Element signInButton = Element.byXpath(
      TEXT_LOCATOR_PATTERN, getValueOf(HEADER_SIGN_IN_BUTTON));
  private Element signOutButton = Element.byXpath(
      TEXT_LOCATOR_PATTERN, getValueOf(REACT_HEADER_SIGN_OUT_BUTTON));
  private Element logOutButton = Element.byXpath("//input[@id='kc-logout']");
  private Element profileAvatar = Element.byXpath(
      "//*[@class='uui-caption']//img[contains(@class,'Avatar-module')]");
  private Element arrowButton = Element.byXpath(
      "//*[contains(@class,'nav-bar_lang-switcher')]//div[contains(@class,'IconContainer-module')]");
  private Element managementProgramsLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "trainings");
  private Element managementNewsLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "NewsManagement");
  private Element managementEventsLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "events");
  private Element managementSkillsLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "skills");
  private Element managementUsersLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "users");
  private Element managementAdminPortalLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "Admin");
  private Element managementSearchLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "search");
  private Element profileSettingsLink = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(HEADER_SETTINGS_BUTTON));
  private Element profileApplicationsLink = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(HEADER_APPLICATIONS_BUTTON));
  private Element profileLink = Element.byXpath(TEXT_LOCATOR_PATTERN,
      getValueOf(HEADER_PROFILE_BUTTON));
  private Element profileDropDownMenu = Element.byXpath(
      "//div[contains(@class,'MainMenuDropdown-module_dropdown-body')]");
  private Element languageControlDropdown = Element.byXpath(
      "//*[contains(@class,'styles_locale-switch')]/button");
  private Element menuButtonInMobileNavigation = Element.byCss(".uui-burger-button");
  private Element menuDropDownInMobileNavigation = Element.byCss(".uui-burger-items");
  private Element skillsList = Element.byXpath("//*[contains(@class,'footer_footer-skills_item')]");
  private Element skillsTitle = Element.byXpath("//*[contains(@class,'footer_footer-title')]");

  @Override
  public boolean isScreenLoaded() {
    return isMainMenuLogoDisplayed();
  }

  public HeaderScreen waitScreenLoaded() {
    waitMainMenuLogoVisibility();
    return this;
  }

  public HeaderScreen waitForPageToReload() {
    pageHeader.waitStalenessOfElement();
    return this;
  }

  public boolean isHeaderContainerDisplayed() {
    return pageHeader.isDisplayed();
  }

  public HeaderScreen waitMainMenuLogoVisibility() {
    mainMenuLogo.waitForVisibility();
    return this;
  }

  public boolean isMainMenuLogoDisplayed() {
    return mainMenuLogo.isDisplayedNoWait();
  }

  public boolean isEpamLogoDisplayed() {
    return mainMenuLogo.isDisplayed();
  }

  public HeaderScreen waitDropDownDisplayed() {
    profileDropDownMenu.waitForVisibility();
    return this;
  }

  public boolean isUserDropDownDisplayed() {
    return profileDropDownMenu.isDisplayed();
  }

  public String getDropDownButtonsBackgroundColor() {
    return profileDropDownMenu.getCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public HeaderScreen waitLanguageControlDropdownDisplayed() {
    languageControlDropdown.waitForVisibility();
    return this;
  }

  public HeaderScreen clickLanguageControlDropdown() {
    languageControlDropdown.click();
    return this;
  }

  public HeaderScreen waitForNotificationButtonVisibility() {
    notificationButton.waitForVisibility();
    return this;
  }

  public NotificationModuleScreen clickNotificationButton() {
    notificationButton.clickJs();
    return new NotificationModuleScreen();
  }

  public NotificationScreen clickNotificationButtonNew() {
    notificationButton.clickJs();
    return new NotificationScreen();
  }

  public boolean isNotificationButtonDisplayed() {
    return notificationButton.isDisplayed();
  }

  public TrainingListScreen clickTrainingListNavigationLink() {
    programsNavigationLink.click();
    return new TrainingListScreen();
  }

  public EventsScreen clickEventsNavigationLink() {
    eventsNavigationLink.click();
    return new EventsScreen();
  }

  public SkillDescriptionScreen clickSkillsListNavigationLink() {
    skillsListNavigationLink.clickJs();
    return new SkillDescriptionScreen();
  }

  public String getSkillsNavigationLinkText() {
    return skillsListNavigationLink.getText();
  }

  public boolean isSkillsNavigationLinkDisplayed() {
    return skillsListNavigationLink.isDisplayed();
  }

  public AboutScreen clickAboutNavigationLink() {
    aboutNavigationLink.clickJs();
    return new AboutScreen();
  }

  public boolean isAboutNavigationLinkDisplayed() {
    return aboutNavigationLink.isDisplayed();
  }

  public String getAboutNavigationLinkText() {
    return aboutNavigationLink.getText();
  }

  public HeaderScreen waitLinksToRedirectOnOtherSectionsDisplayed() {
    blogNavigationLink.waitForVisibility();
    return this;
  }

  public BlogScreen clickBlogNavigationLink() {
    blogNavigationLink.clickJs();
    return new BlogScreen();
  }

  public boolean isBlogNavigationLinkDisplayed() {
    return blogNavigationLink.isDisplayed();
  }

  public String getBlogNavigationLinkText() {
    return blogNavigationLink.getText();
  }

  public HeaderScreen clickManagementDropDownMenu() {
    managementNavigationLink.click();
    return this;
  }

  public ReactTrainingManagementScreen clickReactTrainingManagementLink() {
    managementNavigationLink.click();
    managementProgramsLink.click();
    return new ReactTrainingManagementScreen();
  }

  public boolean isTrainingManagementLinkDisplayed() {
    return managementProgramsLink.isDisplayedNoWait();
  }

  public ReactUserManagementScreen clickReactUserManagementLink() {
    managementNavigationLink.click();
    managementUsersLink.click();
    return new ReactUserManagementScreen();
  }

  public boolean isUserManagementLinkDisplayed() {
    return managementUsersLink.isDisplayedNoWait();
  }

  public NewsManagementScreen clickNewsManagementLink() {
    managementNavigationLink.click();
    managementNewsLink.click();
    return new NewsManagementScreen();
  }

  public ReactEventsManagementScreen clickEventsManagementLink() {
    managementNavigationLink.click();
    managementEventsLink.click();
    return new ReactEventsManagementScreen();
  }

  public ReactSkillsManagementScreen clickReactSkillsManagementLink() {
    managementSkillsLink.click();
    return new ReactSkillsManagementScreen();
  }

  public ReactSearchScreen clickSearchLink() {
    managementNavigationLink.click();
    managementSearchLink.click();
    return new ReactSearchScreen();
  }

  public SettingsScreen clickSettingsButton() {
    profileAvatar.click();
    profileSettingsLink.click();
    return new SettingsScreen();
  }

  public ApplicationsScreen clickApplicationsButton() {
    profileApplicationsLink.waitForClickableAndClick();
    return new ApplicationsScreen();
  }

  public boolean isApplicationsButtonDisplayed() {
    return profileApplicationsLink.isDisplayed();
  }

  public String getApplicationsButtonText() {
    return profileApplicationsLink.getText();
  }

  public String getApplicationsButtonTextColor() {
    return profileApplicationsLink.getCssValue(Attribute.COLOR.toString());
  }

  public String getHoveredApplicationsButtonBackgroundColor() {
    return profileApplicationsLink.getHoveredCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public LoginScreen clickSignInButton() {
    signInButton.mouseOver();
    signInButton.click();
    return new LoginScreen();
  }

  public LoginScreen clickSignInButtonUnderMenu() {
    signInButton.click();
    return new LoginScreen();
  }

  public boolean isSignInButtonDisplayed() {
    return signInButton.isDisplayed();
  }

  public String getSignInButtonText() {
    return signInButton.getText();
  }

  public HeaderScreen clickSignOutButton() {
    signOutButton.click();
    return new HeaderScreen();
  }

  public boolean isSignOutButtonDisplayed() {
    return signOutButton.isDisplayed();
  }

  public String getHoveredSignOutButtonBackgroundColor() {
    return signOutButton.getHoveredCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public String getSignOutButtonTextColor() {
    return signOutButton.getCssValue(Attribute.COLOR.toString());
  }

  public String getSignOutButtonText() {
    return signOutButton.getText();
  }

  public HeaderScreen clickLogOutButton() {
    logOutButton.click();
    return this;
  }

  public HeaderScreen waitLogOutButtonVisibility() {
    logOutButton.waitForVisibility();
    return this;
  }

  public HeaderScreen clickMenuButton() {
    menuButtonInMobileNavigation.click();
    return this;
  }

  public boolean isMenuDropdownDisplayed() {
    return menuDropDownInMobileNavigation.isDisplayed();
  }

  public HeaderScreen signOut() {
    return clickSignOutButton()
        .waitLogOutButtonVisibility()
        .clickLogOutButton();
  }

  public boolean isProfilePhotoImageDisplayed() {
    return profileAvatar.isDisplayed();
  }

  public HeaderScreen waitProfileMenuDisplayed() {
    profileAvatar.waitForVisibility();
    return this;
  }

  public HeaderScreen waitProfilePhotoImageVisibility() {
    profileAvatar.waitForVisibility();
    return this;
  }

  public HeaderScreen clickProfileMenu() {
    profileAvatar.mouseOver();
    profileAvatar.waitForClickableAndClick();
    return this;
  }

  public ProfileScreen clickProfileButton() {
    profileLink.click();
    return new ProfileScreen();
  }

  public ProfileScreen clickProfileNavigationLink() {
    profileAvatar.click();
    profileLink.click();
    return new ProfileScreen();
  }

  public boolean isProfileButtonDisplayed() {
    return profileLink.isDisplayed();
  }

  public String getProfileButtonText() {
    return profileLink.getText();
  }

  public String getHoveredProfileButtonBackgroundColor() {
    return profileLink.getHoveredCssValue(Attribute.BACKGROUND_COLOR.toString());
  }

  public String getProfileButtonTextColor() {
    return profileLink.getCssValue(Attribute.COLOR.toString());
  }

  public boolean isSkillsDisplayed() {
    skillsList.mouseOver();
    return skillsList.isAllElementsDisplayed();
  }

  public boolean isPortalAdministrationLinkDisplayed() {
    return managementAdminPortalLink.isDisplayedNoWait();
  }

  public boolean isOurSkillsSectionDisplayed() {
    skillsList.mouseOver();
    return skillsTitle.isDisplayed() && skillsList.isDisplayed();
  }

  public String getSkillsTitleText() {
    return skillsTitle.getText();
  }

  public boolean isArrowButtonDisplayed() {
    return arrowButton.isDisplayed();
  }

  public HeaderScreen waitForClickableAndClickArrowButton() {
    arrowButton.waitForClickableAndClick();
    return this;
  }

  public boolean isMenuTabByNameDisplayed(String tabName) {
    return Element.byXpath(MAIN_MENU_ITEM_BY_NAME_PATTERN, tabName).isDisplayedNoWait();
  }

  //update in next version app
  public HomeTabOnLearningPageScreen clickLearningButton() {
    return new HomeTabOnLearningPageScreen().waitForVisibilityOfDaysInCalendarSection();
  }

  public TrainingCenterManagementScreen clickCenterManagementLink() {
    return new TrainingCenterManagementScreen();
  }

  public FAQScreen clickFAQNavigationLink() {
    return new FAQScreen();
  }

  public HeaderScreen clickUniversityProgramLogo() {
    return this;
  }

  public HeaderScreen waitUntilNewNotificationArrives() {
    return this;
  }

  public HeaderScreen clickEpamLogoLabel() {
    return this;
  }

  public int getNumberOfSkillCardsWithDescription() {
    return 0;
  }

  public int getAvailableSkillsCardsCount() {
    return 0;
  }

  public void clickSkillCardByIndex(int skillIndex) {
  }

  public boolean isUniversityProgramLogoDisplayed() {
    return false;
  }

  public boolean isBannerDisplayed() {
    return false;
  }

  public HeaderScreen waitBannerVisibility() {
    return this;
  }

  public String getBannerText() {
    return "String";
  }

  public boolean isTrainingListNavigationLinkDisplayed() {
    return false;
  }

  public String getTrainingListNavigationLinkText() {
    return "String";
  }

  public boolean isFaqNavigationLinkDisplayed() {
    return false;
  }

  public String getFaqNavigationLinkText() {
    return "String";
  }

  public boolean isLanguageAcronymDisplayed() {
    return false;
  }

  public String getLanguageAcronymText() {
    return "String";
  }

  public boolean isLanguageControlDropdownDisplayed() {
    return false;
  }

  public boolean isEventsLinkDisplayed() {
    return false;
  }

  public String getEventsNavigationLinkText() {
    return "String";
  }

  public String getUserNameTextFromUserProfile() {
    return "String";
  }

  public String getUserNameText() {
    return "String";
  }

  public ReactTrainingScreen clickReactTrainingScreenLink() {
    return new ReactTrainingScreen();
  }

  public ReactReportScreen clickReportsLink() {
    return new ReactReportScreen();
  }

  public HeaderScreen waitTrainingListNavigationLinkVisibility() {
    return this;
  }

  public Boolean checkUsersRedirectionToHomePage() {
    return isTrainingListNavigationLinkDisplayed();
  }

  public boolean isSkillsBlockAboveFooterDisplayed() {
    return false;
  }

  public boolean isAllSkillCardIconsAndSkillNameDisplayed() {
    return false;
  }

  public HeaderScreen waitEpamLogoVisibility() {
    return this;
  }

  public String getBannerBackground() {
    return "String";
  }

  public String getLanguageAcronymsColor() {
    return "String";
  }

  public String getHoveredLanguageAcronymsColor() {
    return "String";
  }

  public boolean isLearningButtonDisplayed() {
    return false;
  }
}
