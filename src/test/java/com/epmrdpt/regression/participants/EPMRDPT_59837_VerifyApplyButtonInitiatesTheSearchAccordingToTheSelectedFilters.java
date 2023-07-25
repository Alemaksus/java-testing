package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_RATING_DROPDOWN_DEFAULT_VALUE;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_59837_VerifyApplyButtonInitiatesTheSearchAccordingToTheSelectedFilters",
    groups = {"full", "regression"})
public class EPMRDPT_59837_VerifyApplyButtonInitiatesTheSearchAccordingToTheSelectedFilters {

  private final String TRAINING_NAME = "NEW_TRAINING_FOR_TEST";
  private final String PARTICIPANT_NAME = "Falco Varnik";
  private final String COUNTRY = getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_VALUE);
  private final String CITY = getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_VALUE);
  private final String CURRENT_STATUS = "New";
  private final String ENGLISH_LEVEL = "Not specified";
  private final String RATING = getValueOf(REACT_TRAINING_MANAGEMENT_RATING_DROPDOWN_DEFAULT_VALUE);
  ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;


  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToParticipantsPage() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    reactParticipantsTrainingScreen = new ReactParticipantsTrainingScreen();
  }

  @Test(priority = 1)
  public void isParticipantCountryDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    reactParticipantsTrainingScreen.clickSelectApplicationCountryInput()
        .clickItemInDropDownByName(COUNTRY)
        .clickSelectApplicationCityInput()
        .clickItemInDropDownByName(CITY)
        .clickApplyButton();
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isSelectApplicationDisplayed(COUNTRY),
        "DDL Country is empty");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isSelectApplicationDisplayed(CITY),
        "DDL City is empty");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isParticipantFindByNameDisplayed(PARTICIPANT_NAME),
        "Participant isn't displayed");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void isParticipantByCurrentStatusDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    reactParticipantsTrainingScreen.clickSelectApplicationStatusInput()
        .clickItemInDropDownByName(CURRENT_STATUS)
        .clickApplyButton();
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isSelectApplicationDisplayed(CURRENT_STATUS),
        "DDL current status is empty");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isParticipantFindByNameDisplayed(PARTICIPANT_NAME),
        "Participant isn't displayed");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void isParticipantByEnglishDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    reactParticipantsTrainingScreen.clickSelectApplicationEnglishInput()
        .clickItemInDropDownByName(ENGLISH_LEVEL)
        .clickApplyButton();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSelectApplicationDisplayed(
            ENGLISH_LEVEL),
        "DDL english is empty");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isParticipantFindByNameDisplayed(PARTICIPANT_NAME),
        "Participant isn't displayed");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void isParticipantByRatingDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    reactParticipantsTrainingScreen.clickSelectApplicationRatingInput()
        .clickItemInDropDownByName(RATING)
        .clickApplyButton();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSelectApplicationDisplayed(RATING),
        "DDL rating is empty");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isParticipantFindByNameDisplayed(PARTICIPANT_NAME),
        "Participant isn't displayed");
    softAssert.assertAll();
  }

  @AfterMethod
  public void cleanInput() {
    reactParticipantsTrainingScreen.clickRestButton();
  }
}
