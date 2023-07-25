package com.epmrdpt.smoke.center_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_STATUS_ALL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_STATUS_DRAFT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34198_VerifyThatTheUserCanSearchCreatedTrainingCentersByCountryByCityByStatus",
    groups = {"full", "smoke", "general"})
public class EPMRDPT_34198_VerifyThatTheUserCanSearchCreatedTrainingCentersByCountryByCityByStatus {

  private final String countryOfCenter = getValueOf(COUNTRY_NAME_BELARUS);
  private final String cityOfCenter = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private String statusOfCentre = getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED);
  private Map<String, String> centerStatusIndex = new HashMap<String, String>() {{
    put(getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_ALL), "0");
    put(getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_DRAFT), "1");
    put(getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED), "2");
  }};
  private Map<String, String> centerStatusClassName = new HashMap<String, String>() {{
    put(getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_ALL), "status published status draft");
    put(getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_DRAFT), "status published");
    put(getValueOf(EDIT_CENTER_MANAGEMENT_STATUS_PUBLISHED), "status draft");
  }};

  private User user;
  TrainingCenterManagementScreen trainingCenterManagementScreen;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34198_VerifyThatTheUserCanSearchCreatedTrainingCentersByCountryByCityByStatus(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
    trainingCenterManagementScreen = new TrainingCenterManagementScreen();
  }

  @Test(priority = 1)
  public void checkSelectCenterWithCountryAndCity() {
    trainingCenterManagementScreen = new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(countryOfCenter, cityOfCenter);
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(trainingCenterManagementScreen.getCenterCountryText(),
        countryOfCenter, "Country is wrong!");
    softAssert.assertEquals(trainingCenterManagementScreen.getCityNameOfTrainingCenter(),
        cityOfCenter, "City is wrong!");
    trainingCenterManagementScreen.clickResetButton();
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSelectCenterWithCountry() {
    trainingCenterManagementScreen = new TrainingCenterManagementService()
        .selectCountryInSearchSection(countryOfCenter);
    SoftAssert softAssert = new SoftAssert();
    List<String> listOfCenterCountry = trainingCenterManagementScreen.getCenterCountryListText();
    for (String centerCountry : listOfCenterCountry) {
      softAssert.assertEquals(centerCountry, countryOfCenter, "Country is wrong!");
    }
    trainingCenterManagementScreen.clickResetButton();
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkSelectCenterWithCountryAndStatus() {
    trainingCenterManagementScreen = new TrainingCenterManagementService()
        .selectCountryAndStatusInSearchSection(countryOfCenter,
            centerStatusIndex.get(statusOfCentre));
    SoftAssert softAssert = new SoftAssert();
    List<String> listOfCenterCountry = trainingCenterManagementScreen.getCenterCountryListText();
    for (String centerCountry : listOfCenterCountry) {
      softAssert.assertEquals(centerCountry, countryOfCenter, "Country is wrong!");
    }
    List<String> listOfCenterStatusClass = trainingCenterManagementScreen
        .getCenterStatusClassNames();
    for (String statusClass : listOfCenterStatusClass) {
      softAssert.assertTrue(centerStatusClassName.get(statusOfCentre).contains(statusClass),
          "Status is wrong!");
    }
    trainingCenterManagementScreen.clickResetButton();
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkSelectCenterWithStatus() {
    trainingCenterManagementScreen = new TrainingCenterManagementService()
        .selectStatusInSearchSection(centerStatusIndex.get(statusOfCentre));
    SoftAssert softAssert = new SoftAssert();
    List<String> listOfCenterStatusClass = trainingCenterManagementScreen
        .getCenterStatusClassNames();
    for (String statusClass : listOfCenterStatusClass) {
      softAssert.assertTrue(centerStatusClassName.get(statusOfCentre).contains(statusClass),
          "Status is wrong!");
    }
    trainingCenterManagementScreen.clickResetButton();
    softAssert.assertAll();
  }
}
