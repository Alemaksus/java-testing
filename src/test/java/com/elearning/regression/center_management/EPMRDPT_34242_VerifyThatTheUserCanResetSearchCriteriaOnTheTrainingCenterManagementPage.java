package com.epmrdpt.regression.center_management;

import static com.epmrdpt.services.LanguageSwitchingService.getLocaleLanguage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34242_VerifyThatTheUserCanResetSearchCriteriaOnTheTrainingCenterManagementPage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34242_VerifyThatTheUserCanResetSearchCriteriaOnTheTrainingCenterManagementPage {

  private String countryName = "AutoTestCountry";
  private String cityName = "AutoTestCity";
  private String defaultCountryAreaText;
  private String defaultCityAreaText;
  private User user;
  private TrainingCenterManagementScreen trainingCenterManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34242_VerifyThatTheUserCanResetSearchCriteriaOnTheTrainingCenterManagementPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingCenterManagementScreen() {
    trainingCenterManagementScreen = new TrainingCenterManagementScreen();
    new LoginService()
        .loginAndSetLanguage(user).clickCenterManagementLink();
    defaultCountryAreaText = trainingCenterManagementScreen.getCountryAreaText();
    defaultCityAreaText = trainingCenterManagementScreen.getCityAreaText();
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(countryName, cityName);
  }

  @Test(priority = 1)
  public void checkCountryFilterIsResetAfterResetButtonClicking() {
    assertEquals(trainingCenterManagementScreen.clickResetButton()
            .getCountryAreaText(),
        defaultCountryAreaText,
        "Country filter is not reset!");
  }

  @Test(priority = 2)
  public void checkCityFilterIsResetAfterResetButtonClicking() {
    assertEquals(trainingCenterManagementScreen.getCityAreaText(),
        defaultCityAreaText,
        "City filter is not reset!");
  }

  @Test(priority = 3)
  public void checkCountryAndCityListsAreDisplayed() {
    assertTrue(trainingCenterManagementScreen.isCountryAndCityListsDisplayed(),
        "Country and city lists are not displayed!");
  }

  @Test(priority = 4)
  public void checkCountryColumnIsSortedInAlphabetOrder() {
    List<String> originalCountriesListText = trainingCenterManagementScreen
        .clickContainerOfCentresCount()
        .getCenterCountryListText();
    List<String> sortedCopyCountriesListText = new ArrayList<>(originalCountriesListText)
        .stream()
        .sorted(Collator.getInstance(new Locale(getLocaleLanguage().getLanguageCode())))
        .collect(Collectors.toList());
    assertEquals(originalCountriesListText, sortedCopyCountriesListText,
        "Country column is not sorted in alphabetic order!");
  }
}
