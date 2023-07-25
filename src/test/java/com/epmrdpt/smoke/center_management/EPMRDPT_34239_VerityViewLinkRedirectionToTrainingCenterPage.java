package com.epmrdpt.smoke.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.CENTER_MANAGEMENT_TITLE_FOR_AUTOTEST;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_ELLIPSIS_ACTION_MENU_VIEW;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34239_VerityViewLinkRedirectionToTrainingCenterPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_34239_VerityViewLinkRedirectionToTrainingCenterPage {

  private User user;
  private String countryOfTheTrainingCenter = "AutoTestCountry";
  private String cityOfTheTrainingCenter = "AutoTestCity";
  private TrainingCenterManagementScreen trainingCenterManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34239_VerityViewLinkRedirectionToTrainingCenterPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupTrainingCenter() {
    trainingCenterManagementScreen = new LoginService()
        .loginAndSetLanguage(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(countryOfTheTrainingCenter, cityOfTheTrainingCenter);
    trainingCenterManagementScreen.clickTrainingCenterMenuButton(cityOfTheTrainingCenter);
  }

  @Test(priority = 1)
  public void checkListOfActionAppears() {
    assertTrue(
        trainingCenterManagementScreen.waitListOfActionForTrainingCenterMenuButtonForVisibility()
            .isListOfActionsForTrainingCenterContextMenuButtonDisplayed(),
        "Action list is not displayed.");
  }

  @Test(priority = 2)
  public void checkViewLinkRedirectsToDesiredTrainingCenterPage() {
    CenterScreen centerScreen = trainingCenterManagementScreen.selectTheTrainingCenterButtonDropdownMenu(
        LocaleProperties.getValueOf(NEWS_MANAGEMENT_ELLIPSIS_ACTION_MENU_VIEW));
    assertEquals(centerScreen.waitTitleForVisibility().getTextTitle(),
        LocaleProperties.getValueOf(CENTER_MANAGEMENT_TITLE_FOR_AUTOTEST),
        "There was no redirection to the correct page of the city training center.");
  }
}
