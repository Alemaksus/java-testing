package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34244_VerifyThatEnglishTabIsOpenedByDefaultOnTheCreateTrainingCenterPage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34244_VerifyThatEnglishTabIsOpenedByDefaultOnTheCreateTrainingCenterPage {

  private User user;
  private CreateOrEditTrainingCenterScreen createTrainingCenterScreen;
  private static final String ACTIVE_CONDITION_OF_TAB = "active-tab";
  private static final String EXPECTED_COLOR_OF_TAB = "rgb(119, 206, 217)";

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34244_VerifyThatEnglishTabIsOpenedByDefaultOnTheCreateTrainingCenterPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupCreateTrainingCenter() {
    createTrainingCenterScreen = new LoginService()
        .loginAndSetLanguage(user)
        .clickCenterManagementLink()
        .waitCreateTrainingCenterButtonVisibility()
        .clickCreateTrainingCenterButton();
  }

  @Test(priority = 1)
  public void checkThatEnglishTabIsOpenedByDefault() {
    assertTrue(createTrainingCenterScreen.getLanguageTabClassValue(
        LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH))
        .contains(ACTIVE_CONDITION_OF_TAB), "English tab is not open by default!");
  }

  @Test(priority = 2)
  public void checkThatEnglishTabHasABlueColor() {
    assertTrue(createTrainingCenterScreen.getLanguageTabBackground(
        LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH))
        .contains(EXPECTED_COLOR_OF_TAB), "The tab has an incorrect color!");
  }
}
