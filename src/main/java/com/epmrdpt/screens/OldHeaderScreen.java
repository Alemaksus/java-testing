package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_ABOUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_BLOG;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_FAQ;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_PORTAL_ADMINISTRATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_SKILLS_MANAGEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_TRAINING_LIST;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_USER_MANAGEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_DROPDOWN_MANAGEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_DROPDOWN_REPORTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_HEADER_SIGN_OUT_BUTTON;
import static javax.print.attribute.standard.Chromaticity.COLOR;
import static javax.swing.text.html.HTML.Attribute.TITLE;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;

public class OldHeaderScreen extends AbstractScreen {

    private static final String MENU_TAB_BY_NAME_PATTERN = "//*[@class='nav-container']//*[contains(text(),'%s')]";
    private static final String MENU_TAB_PATTERN = "div.nav-container a[href*='%s']";
    private static final String MENU_DROPDOWN_PATTERN = "//div[contains(@class, 'nav-list__item menu-control__toggle') and text()='%s']";
    private static final String MENU_DROPDOWN_ITEM_PATTERN = ".nav-container a.menu-control__item[href*='%s']";
    private static final String USER_PROFILE_TITLE = "title";
    private static final String SIGN_OUT_BUTTON_PATTERN = "//a[contains(text(),'%s')]";

    private Element epamLogoLabel = Element.byCss("a.logo--epam");
    private Element pageHeader = Element.byId("header");
    private Element universityProgramLogo = Element.byXpath(
        "//*[contains(@class, 'logo logo--university-program') or contains(@class, 'logo logo--training-center')]");
    private Element trainingListNavigationLink = Element.byXpath(
        MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_TRAINING_LIST));
    private Element skillsListNavigationLink = Element.byXpath(
        MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_SKILLS));
    private Element aboutNavigationLink = Element.byXpath(
        MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_ABOUT));
    private Element blogNavigationLink = Element.byXpath(
        MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_BLOG));
    private Element faqNavigationLink = Element.byXpath(
        MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_FAQ));
    private Element languageControlDropdown = Element.byCss("div.language-control");
    private Element languageAcronym = Element.byCss("div.language-control__toggle");
    private Element signInButton = Element.byCss(".login-control__item");
    private Element textOfBannerOnHomePage = Element.byCss(".hero-banner__heading");
    private Element banner = Element.byCss("div.hero-banner.hero-banner--home");
    private Element headerContainer = Element.byId("header");
    private Element userName = Element.byXpath(
        "//div[contains(@class,'profile-control') and @title]");
    private Element profileMenu = Element.byCss(".profile-photo");
    private Element dropdownMenu = Element.byCss("div.menu-control__list.profile-control__menu");
    private Element signOutButton = Element.byXpath(
        SIGN_OUT_BUTTON_PATTERN, getValueOf(REACT_HEADER_SIGN_OUT_BUTTON));
    private Element logOutButton = Element.byXpath("//input[@id='kc-logout']");
    private Element profilePhotoImage = Element.byCss("div.profile-photo");
    private Element notificationButton = Element.byCss("#notificationBtn");
    private Element applicationsButton =
        Element.byCss("div.profile-control__menu>a[href$='Applications']");
    private Element profileButton = Element.byCss("div.profile-control__menu>a[href*='UserProfile']");
    private Element cogwheelButton = Element.byCss("a.settings-control__toggle");
    private Element learningButton = Element.byCss(MENU_TAB_PATTERN, "StudentLearning");
    private Element applicationsDDlButton = Element
        .byCss("div.profile-control__menu>a[href$='Applications']");
    private Element managementDropdown = Element
        .byXpath(MENU_DROPDOWN_PATTERN, getValueOf(HEADER_DROPDOWN_MANAGEMENT));
    private Element trainingManagementLink = Element
        .byCss(MENU_DROPDOWN_ITEM_PATTERN, "/Management#!");
    private Element reactTrainingLink = Element.byCss(MENU_TAB_PATTERN, "Platform");
    private Element epamLogoOnDarkBlueHeader = Element.byXpath("//div[@class='epam-logo']");
    private Element menuButtonInMobileNavigation = Element
        .byXpath("//div[@class='nav-menu-container']/div");
    private Element signInButtonUnderMenuInMobileNavigation = Element
        .byXpath("//div[@class='login-control']");
    private Element menuDropDownInMobileNavigation = Element.byXpath(
        "//div[@class='nav-menu-container']//div[contains(@class, 'dropdown-menu')]");
    private Element notificationButtonWithUnreadSymbol = Element
        .byXpath("//div[@class='notificationBell'][not(contains(@style,'none'))]");
    private Element newsManagementLink = Element.byCss(MENU_DROPDOWN_ITEM_PATTERN, "NewsManagement");
    private Element userManagementLink = Element.byXpath(
        MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_USER_MANAGEMENT));
    private Element skillsManagementLink = Element.byXpath(
        MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_CONTAINER_SKILLS_MANAGEMENT));
    private Element reportsDropdown = Element
        .byXpath(MENU_DROPDOWN_PATTERN, getValueOf(HEADER_DROPDOWN_REPORTS));
    private Element reportsLink =
        Element.byXpath(MENU_TAB_BY_NAME_PATTERN, getValueOf(HEADER_DROPDOWN_REPORTS));
    private Element biReportsLink = Element.byXpath(
        MENU_DROPDOWN_PATTERN + "/..//a[contains(@href,'powerbi')]",
        getValueOf(HEADER_DROPDOWN_REPORTS));
    private Element searchLink = Element.byCss(MENU_TAB_PATTERN, "search");
    private Element centerManagementLink = Element
        .byCss("div:not([class*='nav-control__menu']) > " + MENU_DROPDOWN_ITEM_PATTERN,
            "CenterManagement");
    private Element arrowButton = Element.byXpath(
        "//div[contains(@class,'menu-control profile-control')]//div[contains(@class, 'profile-photo')]");
    private Element userProfileIcon = Element.byXpath(
        "//div[@class='menu-control__toggle profile-control__toggle dropdown-toggle']");
    private Element portalAdministrationLink =
        Element.byXpath("//*[@class='nav-container']//a[contains(text(),'%s')]",
            getValueOf(HEADER_CONTAINER_PORTAL_ADMINISTRATION));
    private Element skillsList = Element.byXpath("//div[@class='skills-list ng-isolate-scope']");
    private Element skillsTitle = Element
        .byXpath("(//div[@class='section-ui__title ng-binding'])[2]");
    private Element skillCardsWithDescription = Element
        .byXpath(" //a[@class='skill-item ng-scope skill-item--linked']");
    private Element skillsListSection = Element
        .byXpath("//div[@class='skills-list ng-isolate-scope']");
    private Element titleOurSkillsSection = Element
        .byXpath("//div[@class='section-ui__title ng-binding']");
    private Element footer = Element.byXpath("//*[@id='footer']");
    private Element skillsBlock = Element.byXpath("//div[@class='skills-list ng-isolate-scope']");
    private Element skillCardIcon = Element.byXpath("//img[@class='skill-item__icon']");
    private Element skillName = Element.byXpath("//div[@class='skill-item__title ng-binding']");
    private Element eventsNavigationLink = Element
        .byXpath("//div[@class='nav-container']//a[contains(@href,'Events')]");
    private Element eventsManagementLink = Element
        .byCss(MENU_DROPDOWN_ITEM_PATTERN, "Management#!/events");
    private Element profileNavigationLink = Element
        .byXpath("//div[@class='nav-container']//a[contains(@href,'Profile')]");

    public boolean isScreenLoaded() {
      return isUniversityProgramLogoDisplayed() && isTrainingListNavigationLinkDisplayed()
          && isBannerDisplayed();
    }

    public OldHeaderScreen waitScreenLoaded() {
      waitEpamLogoVisibility().waitTrainingListNavigationLinkVisibility().waitBannerVisibility();
      return this;
    }

    public OldHeaderScreen waitForPageToReload() {
      pageHeader.waitStalenessOfElement();
      return this;
    }

    public OldHeaderScreen waitUntilNewNotificationArrives() {
      for (int pageRefreshCounter = 0; pageRefreshCounter < 10; pageRefreshCounter++) {
        clickRefreshButton();
        waitLanguageControlDropdownDisplayed();
        if (notificationButtonWithUnreadSymbol.isDisplayed()) {
          break;
        }
      }
      return this;
    }

    public OldHeaderScreen clickEpamLogoLabel() {
      epamLogoLabel.click();
      return this;
    }

    public OldHeaderScreen clickUniversityProgramLogo() {
      universityProgramLogo.click();
      return this;
    }

    public TrainingListScreen clickTrainingListNavigationLink() {
      trainingListNavigationLink.click();
      return new TrainingListScreen();
    }

    public SkillDescriptionScreen clickSkillsListNavigationLink() {
      skillsListNavigationLink.clickJs();
      return new SkillDescriptionScreen();
    }

    public AboutScreen clickAboutNavigationLink() {
      aboutNavigationLink.clickJs();
      return new AboutScreen();
    }

    public BlogScreen clickBlogNavigationLink() {
      blogNavigationLink.clickJs();
      return new BlogScreen();
    }

    public FAQScreen clickFAQNavigationLink() {
      faqNavigationLink.clickJs();
      return new FAQScreen();
    }

    public OldHeaderScreen clickLanguageControlDropdown() {
      languageControlDropdown.click();
      return this;
    }
    public OldHeaderScreen waitLanguageControlDropdownDisplayed() {
      languageControlDropdown.waitForVisibility();
      return this;
    }

    public LoginScreen clickSignInButton() {
      signInButton.mouseOver();
      signInButton.click();
      return new LoginScreen();
    }

    public boolean isUniversityProgramLogoDisplayed() {
      return universityProgramLogo.isDisplayed();
    }

    public boolean isEpamLogoDisplayed() {
      return epamLogoLabel.isDisplayed();
    }

    public OldHeaderScreen waitEpamLogoVisibility() {
      epamLogoLabel.waitForVisibility();
      return this;
    }

    public boolean isTrainingListNavigationLinkDisplayed() {
      return trainingListNavigationLink.isDisplayed();
    }

    public boolean isSkillsNavigationLinkDisplayed() {
      return skillsListNavigationLink.isDisplayed();
    }

    public OldHeaderScreen waitTrainingListNavigationLinkVisibility() {
      trainingListNavigationLink.waitForVisibility();
      return this;
    }

    public boolean isAboutNavigationLinkDisplayed() {
      return aboutNavigationLink.isDisplayed();
    }

    public boolean isBlogNavigationLinkDisplayed() {
      return blogNavigationLink.isDisplayed();
    }

    public boolean isFaqNavigationLinkDisplayed() {
      return faqNavigationLink.isDisplayed();
    }

    public boolean isLanguageAcronymDisplayed() {
      return languageAcronym.isDisplayed();
    }

    public boolean isLanguageControlDropdownDisplayed() {
      return languageControlDropdown.isDisplayed();
    }

    public boolean isSignInButtonDisplayed() {
      return signInButton.isDisplayed();
    }

    public String getBannerText() {
      return textOfBannerOnHomePage.getText();
    }

    public String getBannerBackground() {
      return banner.getCssValue(Attribute.BACKGROUND.toString());
    }

    public boolean isBannerDisplayed() {
      return banner.isDisplayed();
    }

    public OldHeaderScreen waitBannerVisibility() {
      banner.waitForVisibility();
      return this;
    }

    public String getLanguageAcronymsColor() {
      return languageAcronym.getCssValue(COLOR.toString());
    }

    public String getHoveredLanguageAcronymsColor() {
      return languageAcronym.getHoveredCssValue(COLOR.toString());
    }

    public Boolean checkUsersRedirectionToHomePage() {
      clickBlogNavigationLink();
      clickEpamLogoLabel();
      return isTrainingListNavigationLinkDisplayed();
    }

    public String getSignInButtonText() {
      return signInButton.getText();
    }

    public boolean isHeaderContainerDisplayed() {
      return headerContainer.isDisplayed();
    }

    public OldHeaderScreen waitLinksToRedirectOnOtherSectionsDisplayed() {
      faqNavigationLink.waitForVisibility();
      return this;
    }

    public OldHeaderScreen clickProfileMenu() {
      profileMenu.mouseOver();
      profileMenu.waitForClickableAndClick();
      return new OldHeaderScreen();
    }

    public OldHeaderScreen waitProfileMenuDisplayed() {
      profileMenu.waitForVisibility();
      return new OldHeaderScreen();
    }

    public boolean isUserDropDownDisplayed() {
      return dropdownMenu.isDisplayed();
    }

    public OldHeaderScreen signOut() {
      return clickSignOutButton()
          .waitLogOutButtonVisibility()
          .clickLogOutButton();
    }

    public OldHeaderScreen clickSignOutButton() {
      signOutButton.click();
      return this;
    }

    public OldHeaderScreen waitLogOutButtonVisibility() {
      logOutButton.waitForVisibility();
      return this;
    }

    public OldHeaderScreen clickLogOutButton() {
      logOutButton.click();
      return this;
    }

    public NotificationModuleScreen clickNotificationButton() {
      notificationButton.clickJs();
      return new NotificationModuleScreen();
    }

    public OldHeaderScreen waitForNotificationButtonVisibility() {
      notificationButton.waitForVisibility();
      return this;
    }

    public boolean isApplicationsButtonDisplayed() {
      return applicationsButton.isDisplayed();
    }

    public String getApplicationsButtonText() {
      return applicationsButton.getText();
    }

    public boolean isProfileButtonDisplayed() {
      return profileButton.isDisplayed();
    }

    public String getProfileButtonText() {
      return profileButton.getText();
    }

    public SettingsScreen clickSettingsButton() {
      cogwheelButton.click();
      return new SettingsScreen();
    }

    public boolean isSignOutButtonDisplayed() {
      return signOutButton.isDisplayed();
    }

    public String getSignOutButtonText() {
      return signOutButton.getText();
    }

    public boolean isProfilePhotoImageDisplayed() {
      return profilePhotoImage.isDisplayed();
    }

    public OldHeaderScreen waitProfilePhotoImageVisibility() {
      profilePhotoImage.waitForVisibility();
      return this;
    }

    public ProfileScreen clickProfileButton() {
      profileButton.click();
      return new ProfileScreen();
    }

    public OldHeaderScreen waitDropDownDisplayed() {
      dropdownMenu.waitForVisibility();
      return this;
    }

    public ApplicationsScreen clickApplicationsButton() {
      applicationsDDlButton.waitForClickableAndClick();
      return new ApplicationsScreen();
    }

    public String getUserNameText() {
      return userName.getAttributeValue(TITLE.toString());
    }

    public String getUserNameTextFromUserProfile() {
      return userProfileIcon.getAttributeValue(USER_PROFILE_TITLE);
    }

    public boolean isLearningButtonDisplayed() {
      return learningButton.isDisplayed();
    }

    public HomeTabOnLearningPageScreen clickLearningButton() {
      learningButton.clickJs();
      return new HomeTabOnLearningPageScreen().waitForVisibilityOfDaysInCalendarSection();
    }

    public ReactTrainingManagementScreen clickReactTrainingManagementLink() {
      managementDropdown.click();
      trainingManagementLink.click();
      return new ReactTrainingManagementScreen();
    }

    public TrainingCenterManagementScreen clickCenterManagementLink() {
      managementDropdown.click();
      centerManagementLink.click();
      return new TrainingCenterManagementScreen();
    }

    public ReactTrainingScreen clickReactTrainingScreenLink() {
      reactTrainingLink.click();
      return new ReactTrainingScreen();
    }

    public boolean isEpamLogoOnDarkBlueHeaderDisplayed() {
      return epamLogoOnDarkBlueHeader.isDisplayed();
    }

    public OldHeaderScreen clickMenuButton() {
      menuButtonInMobileNavigation.click();
      return this;
    }

    public boolean isMenuDropdownDisplayed() {
      return menuDropDownInMobileNavigation.isDisplayed();
    }

    public LoginScreen clickSignInButtonUnderMenu() {
      signInButtonUnderMenuInMobileNavigation.click();
      return new LoginScreen();
    }

    public NewsManagementScreen clickNewsManagementLink() {
      managementDropdown.click();
      newsManagementLink.click();
      return new NewsManagementScreen();
    }

    public boolean isMenuTabByNameDisplayed(String tabName) {
      return Element.byXpath(MENU_TAB_BY_NAME_PATTERN, tabName).isDisplayedNoWait();
    }

    public String getHoveredSignOutButtonBackgroundColor() {
      return signOutButton.getHoveredCssValue(Attribute.BACKGROUND_COLOR.toString());
    }

    public String getHoveredApplicationsButtonBackgroundColor() {
      return applicationsButton.getHoveredCssValue(Attribute.BACKGROUND_COLOR.toString());
    }

    public String getHoveredProfileButtonBackgroundColor() {
      return profileButton.getHoveredCssValue(Attribute.BACKGROUND_COLOR.toString());
    }

    public String getDropDownButtonsBackgroundColor() {
      return dropdownMenu.getCssValue(Attribute.BACKGROUND_COLOR.toString());
    }

    public String getSignOutButtonTextColor() {
      return signOutButton.getCssValue(Attribute.COLOR.toString());
    }

    public String getApplicationsButtonTextColor() {
      return applicationsButton.getCssValue(Attribute.COLOR.toString());
    }

    public String getProfileButtonTextColor() {
      return profileButton.getCssValue(Attribute.COLOR.toString());
    }

    public ReactUserManagementScreen clickReactUserManagementLink() {
      managementDropdown.click();
      userManagementLink.click();
      return new ReactUserManagementScreen();
    }

    public ReactSkillsManagementScreen clickReactSkillsManagementLink() {
      skillsManagementLink.click();
      return new ReactSkillsManagementScreen();
    }

    public ReactReportScreen clickReportsLink() {
      reportsLink.click();
      return new ReactReportScreen();
    }

    public ReactSearchScreen clickSearchLink() {
      searchLink.clickJs();
      return new ReactSearchScreen();
    }

    public boolean isNotificationButtonDisplayed() {
      return notificationButton.isDisplayed();
    }

    public boolean isArrowButtonDisplayed() {
      return arrowButton.isDisplayed();
    }

    public String getTrainingListNavigationLinkText() {
      return trainingListNavigationLink.getText();
    }

    public String getSkillsNavigationLinkText() {
      return skillsListNavigationLink.getText();
    }

    public String getAboutNavigationLinkText() {
      return aboutNavigationLink.getText();
    }

    public String getBlogNavigationLinkText() {
      return blogNavigationLink.getText();
    }

    public String getFaqNavigationLinkText() {
      return faqNavigationLink.getText();
    }

    public String getLanguageAcronymText() {
      return languageAcronym.getText();
    }

    public OldHeaderScreen waitForClickableAndClickArrowButton() {
      arrowButton.waitForClickableAndClick();
      return this;
    }

    public OldHeaderScreen clickManagementDropDownMenu() {
      managementDropdown.click();
      return this;
    }

    public OldHeaderScreen clickReportsDropDownMenu() {
      reportsDropdown.click();
      return this;
    }

    public boolean isTrainingManagementLinkDisplayed() {
      return trainingManagementLink.isDisplayedNoWait();
    }

    public boolean isUserManagementLinkDisplayed() {
      return userManagementLink.isDisplayedNoWait();
    }

    public boolean isPortalAdministrationLinkDisplayed() {
      return portalAdministrationLink.isDisplayedNoWait();
    }

    public boolean isExcelReportsLinkDisplayed() {
      return reportsLink.isDisplayedNoWait();
    }

    public boolean isBiReportsLinkDisplayed() {
      return biReportsLink.isDisplayedNoWait();
    }

    public boolean isSkillsDisplayed() {
      skillsList.mouseOver();
      return skillsList.isAllElementsDisplayed();
    }

    public String getSkillsTitleText() {
      return skillsTitle.getText();
    }

    public int getNumberOfSkillCardsWithDescription() {
      return (int) getSkillsCardsDescriptionList().stream().filter(Element::isDisplayedNoWait)
          .count();
    }

    public List<Element> getSkillsCardsDescriptionList() {
      return skillCardsWithDescription.getElements();
    }

    public int getAvailableSkillsCardsCount() {
      return getSkillsCardsDescriptionList().size();
    }

    public void clickSkillCardByIndex(int skillIndex) {
      getSkillsCardsDescriptionList().get(skillIndex).waitForClickableAndClick();
    }

    public boolean isOurSkillsSectionDisplayed() {
      skillsListSection.mouseOver();
      return titleOurSkillsSection.isDisplayed() && skillsListSection.isDisplayed();
    }

    public boolean isSkillsBlockAboveFooterDisplayed() {
      return footer.getWrappedWebElement().getLocation().getY()
          > skillsBlock.getWrappedWebElement().getLocation().getY();
    }

    public boolean isAllSkillCardIconsAndSkillNameDisplayed() {
      return skillCardIcon.isAllElementsDisplayed() && skillName.isAllElementsDisplayed();
    }

    public boolean isEventsLinkDisplayed() {
      return eventsNavigationLink.isDisplayed();
    }

    public EventsScreen clickEventsNavigationLink() {
      eventsNavigationLink.click();
      return new EventsScreen();
    }

    public String getEventsNavigationLinkText() {
      return eventsNavigationLink.getText();
    }

    public ReactEventsManagementScreen clickEventsManagementLink() {
      managementDropdown.click();
      eventsManagementLink.click();
      return new ReactEventsManagementScreen();
    }

    public boolean isProfileLinkDisplayed() {
      return profileNavigationLink.isDisplayed();
    }

    public ProfileScreen clickProfileNavigationLink() {
      profileNavigationLink.click();
      return new ProfileScreen();
    }
  }
