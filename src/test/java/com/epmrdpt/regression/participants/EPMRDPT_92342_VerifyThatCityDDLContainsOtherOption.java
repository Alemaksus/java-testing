package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CITY_DROPDOWN_VALUE_OTHER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_COUNTRY_DROPDOWN_VALUE_RUSSIA;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_92342_VerifyThatCityDDLContainsOtherOption",
    groups = {"full", "regression"})
public class EPMRDPT_92342_VerifyThatCityDDLContainsOtherOption {

  private final static String TRAINING_NAME = "Vk auto-assessment type training";
  private final static String PARTICIPANT_COUNTRY = getValueOf(
      PARTICIPANTS_COUNTRY_DROPDOWN_VALUE_RUSSIA);
  private final static String OTHER_OPTION = getValueOf(PARTICIPANTS_CITY_DROPDOWN_VALUE_OTHER);
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginChooseTrainingAndRedirectToParticipantsPage() {
    reactParticipantsTrainingScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitAllTrainingNameDisplayed()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .waitTrainingScreenIsLoaded()
        .clickTrainingNameByName(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkThatCountryFilledInWithEnteredOption() {
    assertEquals(reactParticipantsTrainingScreen
            .clickSelectApplicationCountryInput()
            .clickItemInDropDownByName(PARTICIPANT_COUNTRY)
            .getChosenCountryNameFromCountryDropDown(), PARTICIPANT_COUNTRY,
        "The 'country' field is not filled in with the entered option");
  }

  @Test(priority = 2)
  public void checkThatCityDropDownContainsOtherOption() {
    assertTrue(reactParticipantsTrainingScreen
            .clickSelectApplicationCityInput()
            .getCitiesFromCityDropDown()
            .contains(OTHER_OPTION),
        "'Other' city option is not present on the list");
  }
}
