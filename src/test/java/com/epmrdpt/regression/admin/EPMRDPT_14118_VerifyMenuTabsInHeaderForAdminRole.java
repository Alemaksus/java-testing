package com.epmrdpt.regression.admin;

import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_ABOUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_BLOG;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_EVENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_FAQ;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_MANAGEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_SEARCH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_SKILLS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_CONTAINER_TRAINING_LIST;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HEADER_DROPDOWN_REPORTS;
import static java.lang.String.format;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14118_VerifyMenuTabsInHeaderForAdminRole",
    groups = {"full", "regression", "admin"})
public class EPMRDPT_14118_VerifyMenuTabsInHeaderForAdminRole {

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asAdmin())
        .waitLinksToRedirectOnOtherSectionsDisplayed();
  }

  @Test
  public void checkMenuTabsDisplaying() {
    HeaderScreen headerScreen = new HeaderScreen();
    SoftAssert softAssert = new SoftAssert();
    for (String tabName : getTabsList()) {
      softAssert.assertTrue(headerScreen.isMenuTabByNameDisplayed(tabName),
          format("'%s' menu tab is not present in Header!", tabName));
    }
    headerScreen.clickManagementDropDownMenu();
    softAssert.assertTrue(headerScreen.isTrainingManagementLinkDisplayed(),
        "Training management link is not present in management drop-down menu!");
    softAssert.assertTrue(headerScreen.isUserManagementLinkDisplayed(),
        "User management link is not present in management drop-down menu!");
    softAssert.assertTrue(headerScreen.isPortalAdministrationLinkDisplayed(),
        "Portal administration link is not present in management drop-down menu!");
    softAssert.assertAll();
  }

  private List<String> getTabsList() {
    List<String> tabNames = new ArrayList<>();
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_TRAINING_LIST));
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_SKILLS));
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_ABOUT));
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_BLOG));
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_FAQ));
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_SEARCH));
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_MANAGEMENT));
    tabNames.add(LocaleProperties.getValueOf(HEADER_DROPDOWN_REPORTS));
    tabNames.add(LocaleProperties.getValueOf(HEADER_CONTAINER_EVENTS));
    return tabNames;
  }
}
