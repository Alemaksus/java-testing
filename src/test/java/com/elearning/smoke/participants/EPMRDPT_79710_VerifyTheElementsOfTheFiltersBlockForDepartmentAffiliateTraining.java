package com.epmrdpt.smoke.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LEARNING_TASK_TAB_GROUP_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECT_ENGLISH_LEVEL_INPUT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_APPLICATION_TAB_REGISTRATION_DATE_HEADING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_APPLY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CITY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_COUNTRY_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_ELEMENTS_FOUND_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_RESET_BUTTON;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79710_VerifyTheElementsOfTheFiltersBlockForDepartmentAffiliateTraining",
    groups = {"full", "smoke"})
public class EPMRDPT_79710_VerifyTheElementsOfTheFiltersBlockForDepartmentAffiliateTraining {

  private final String trainingName = "Department Af HelVer";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private static final String ENGLISH_TEST_HEADER = "English test";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test
  public void checkDDLElementsOfTheFiltersBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSearchIconDisplayed(),
        "Search icon isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSearchInputDisplayed(),
        "'Search' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE)),
        "'Country' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE)),
        "'City' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE)),
        "'Current status' input isn't displayed!");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isSelectRegistrationDateFromInputDisplayed(),
        "'From' input isn't displayed!");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isSelectRegistrationDateToInputDisplayed(),
        "'To' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFilterBlockInputDisplayed(
            getValueOf(PARTICIPANTS_SELECT_ENGLISH_LEVEL_INPUT)),
        "'English' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isEnglishLevelInputDisplayed(),
        "'English level' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isGroupBlockInputDisplayed(),
        "'Group' input isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isFoundElementsLabelDisplayed(),
        "'Found elements' counter isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isResetButtonDisplayed(),
        "'Reset' button isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isApplyButtonDisplayed(),
        "'Apply' button isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkIfLabelsOfTheFiltersBlock() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactParticipantsTrainingScreen.getFilterBlockLabelText(
            getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_DROPDOWN_DEFAULT_VALUE)),
        getValueOf(REACT_TRAINING_MANAGEMENT_COUNTRY_LABEL),
        "'Country' label is displayed incorrectly!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getFilterBlockLabelText(
            getValueOf(REACT_TRAINING_MANAGEMENT_CITY_DROPDOWN_DEFAULT_VALUE)),
        getValueOf(REACT_TRAINING_MANAGEMENT_CITY_LABEL),
        "'City' label is displayed incorrectly!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getFilterBlockLabelText(
            getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_DROPDOWN_DEFAULT_VALUE)),
        getValueOf(REACT_TRAINING_MANAGEMENT_CURRENT_STATUS_LABEL),
        "'Current status' label is displayed incorrectly!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getRegistrationDateLabelText(),
        getValueOf(PROFILE_APPLICATION_TAB_REGISTRATION_DATE_HEADING),
        "'Registration date' label is displayed incorrectly!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getFilterBlockLabelText(
            getValueOf(PARTICIPANTS_SELECT_ENGLISH_LEVEL_INPUT)), ENGLISH_TEST_HEADER,
                "'English' label is displayed incorrectly!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isEnglishLevelLabelDisplayed(),
        "'English level' label isn't displayed!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getGroupLabelText(),
        getValueOf(LEARNING_TASK_TAB_GROUP_LABEL),
        "'Group' label is displayed incorrectly!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getFoundElementsLabelText(),
        getValueOf(REACT_TRAINING_MANAGEMENT_ELEMENTS_FOUND_LABEL),
        "'Elements found:' label is displayed incorrectly!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getApplyButtonLabelText(),
        getValueOf(REACT_TRAINING_MANAGEMENT_APPLY_BUTTON),
        "'Apply' label is displayed incorrectly!");
    softAssert.assertEquals(reactParticipantsTrainingScreen.getResetButtonLabelText(),
        getValueOf(REACT_TRAINING_MANAGEMENT_RESET_BUTTON),
        "'Reset' label is displayed incorrectly!");
    softAssert.assertAll();
  }
}
