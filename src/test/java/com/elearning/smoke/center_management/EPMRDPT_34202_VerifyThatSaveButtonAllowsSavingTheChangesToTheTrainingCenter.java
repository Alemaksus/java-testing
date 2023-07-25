package com.epmrdpt.smoke.center_management;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LanguageSwitchingService.LanguageEnum;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34202_VerifyThatSaveButtonAllowsSavingTheChangesToTheTrainingCenter",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34202_VerifyThatSaveButtonAllowsSavingTheChangesToTheTrainingCenter {

  private static String countryOfCenter = "AutoTestCountry";
  private static String cityOfCenter = "AutoTestCity";
  private static int randomNumberLength = 5;

  private User user;
  private CreateOrEditTrainingCenterScreen editTrainingCenterScreen;

  private static final String randomDisplayingDescription =
      cityOfCenter + randomNumeric(randomNumberLength);

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34202_VerifyThatSaveButtonAllowsSavingTheChangesToTheTrainingCenter(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
    new TrainingCenterManagementService()
        .openEditCenterScreen(countryOfCenter, cityOfCenter);
  }

  @Test(priority = 1)
  public void checkPopUpTitle() {
    editTrainingCenterScreen = new CreateOrEditTrainingCenterScreen()
        .openTab(LanguageEnum.ENGLISH)
        .typeFactDescriptionOnEnglishTab(randomDisplayingDescription)
        .openTab(LanguageEnum.RUSSIAN)
        .typeFactDescriptionOnRussianTab(randomDisplayingDescription)
        .openTab(LanguageEnum.UKRAINE)
        .typeFactDescriptionOnUkrainianTab(randomDisplayingDescription)
        .clickCreateOrSaveTrainingCenterTopButton();
    assertEquals(editTrainingCenterScreen.getSaveChangesOrCreatePopUpTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.EDIT_CENTER_MANAGEMENT_POPUP_SAVED_CHANGES),
        "Saved changes pop-up title isn't correct!");
  }

  @Test(priority = 2)
  public void checkUserRedirectedToCenterManagementPage() {
    assertTrue(new TrainingCenterManagementScreen().isScreenLoaded(),
        "User isn't redirected to ‘Center management’ page");
  }

  @Test(priority = 3)
  public void checkSavedChangesIsDisplayed() {
    new HeaderScreen()
        .clickAboutNavigationLink();
    new AboutScreen()
        .clickTrainingCenterCountryByName(countryOfCenter)
        .clickTrainingCenterCityByName(cityOfCenter);
    assertEquals(new CenterScreen().getLocationDescriptionText(), randomDisplayingDescription,
        "Description isn't correct! Changes isn't saved!");
  }
}
