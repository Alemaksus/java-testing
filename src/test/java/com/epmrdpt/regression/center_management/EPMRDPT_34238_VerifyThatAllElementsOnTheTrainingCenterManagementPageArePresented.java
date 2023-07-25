package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.CENTER_MANAGEMENT_CREATE_TRAINING_CENTER_BUTTON_TEXT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;


@Test(description = "EPMRDPT_34238_VerifyThatAllElementsOnTheTrainingCenterManagementPageArePresented",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34238_VerifyThatAllElementsOnTheTrainingCenterManagementPageArePresented {

  private TrainingCenterManagementScreen centerManagementScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34238_VerifyThatAllElementsOnTheTrainingCenterManagementPageArePresented(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenterManagementScreen() {
    new LoginService()
        .loginAndSetLanguage(user);
    centerManagementScreen = new HeaderScreen()
        .clickCenterManagementLink();
  }

  @Test(priority = 1)
  public void checkThatHeaderIsDisplayedOnCenterManagementScreen() {
    assertTrue(centerManagementScreen.isCenterManagementHeaderDisplayed(),
        "Center Management Header isn't displayed!");
  }

  @Test(priority = 2)
  public void checkTextOnCreateManagementCenterButton() {
    assertEquals(centerManagementScreen.getCreateTrainingCenterButtonText(),
        LocaleProperties.getValueOf(CENTER_MANAGEMENT_CREATE_TRAINING_CENTER_BUTTON_TEXT),
        "Text on Create new Training Center button is displayed!");
  }

  @Test(priority = 3)
  public void checkThatSearchSectionIsDisplayedOnCenterManagementScreen() {
    assertTrue(centerManagementScreen.isSearchSectionDisplayed(),
        "Search section isn't displayed!");
  }

  @Test(priority = 4)
  public void checkThatSortingSectionIsDisplayedOnCenterManagementScreen() {
    assertTrue(centerManagementScreen.isSortingSectionDisplayed(),
        "Sorting section isn't displayed!");
  }

  @Test(priority = 5)
  public void checkThatPaginationSectionIsDisplayedOnCenterManagementScreen() {
    assertTrue(centerManagementScreen.isPaginationSectionDisplayed(),
        "Pagination section isn't displayed!");
  }

  @Test(priority = 6)
  public void checkThatFooterSectionIsDisplayedOnCenterManagementScreen() {
    assertTrue(centerManagementScreen.isFooterSectionDisplayed(),
        "Footer section isn't displayed!");
  }
}