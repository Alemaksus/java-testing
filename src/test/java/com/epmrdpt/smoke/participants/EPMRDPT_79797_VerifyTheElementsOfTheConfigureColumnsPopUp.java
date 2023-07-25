package com.epmrdpt.smoke.participants;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECKBOX_VALUES;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECK_ALL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_RESET_TO_DEFAULT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_UNCHECK_ALL_BUTTON;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79797_VerifyTheElementsOfTheConfigureColumnsPopUp",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_79797_VerifyTheElementsOfTheConfigureColumnsPopUp {

  private String trainingName = "AutoTest_AddManyStudentsFromAnotherTraining";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private SoftAssert softAssert;

  @BeforeClass
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading()
        .clickEditButton()
        .waitConfigureColumnsTabHeaderVisibility();
  }

  @Test
  public void verifyHeaderOfConfigureColumnsPopUp() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isConfigureColumnsPopUpHeaderDisplayed(),
        "Configure columns pop-up header isn't displayed!");
    softAssert
        .assertTrue(reactParticipantsTrainingScreen.isCrossButtonPopUpInApplicantStatusDisplayed(),
            "Configure columns pop-up cross button isn't displayed!");
    softAssert
        .assertTrue(reactParticipantsTrainingScreen.isCrossButtonPopUpInApplicantStatusClickable(),
            "Configure columns pop-up cross button isn't clickable!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isInfoIconDisplayed(),
        "Configure columns pop-up info icon isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isInformationMessageDisplayed(),
        "Configure columns pop-up information message isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void verifyButtonsOnTheBottomOfConfigureColumnsPopUp() {
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(reactParticipantsTrainingScreen.isConfigureColumnsPopUpApplyButtonDisplayed(),
            "Configure columns pop-up 'Apply' button isn't displayed!");
    softAssert.assertTrue(
        reactParticipantsTrainingScreen.isConfigureColumnsPopUpApplyButtonClickable(),
        "Configure columns pop-up 'Apply' button isn't clickable!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isConfigurationPopUpButtonDisplayedByName(
        getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECK_ALL_BUTTON)),
        "Configure columns pop-up 'Check all' button isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isConfigurationPopUpButtonClickableByName(
        getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECK_ALL_BUTTON)),
        "Configure columns pop-up 'Check all' button isn't clickable!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isConfigurationPopUpButtonDisplayedByName(
        getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_UNCHECK_ALL_BUTTON)),
        "Configure columns pop-up 'Uncheck all' button isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isConfigurationPopUpButtonClickableByName(
        getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_UNCHECK_ALL_BUTTON)),
        "Configure columns pop-up 'Uncheck all' button isn't clickable!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isConfigurationPopUpButtonDisplayedByName(
        getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_RESET_TO_DEFAULT_BUTTON)),
        "Configure columns pop-up 'Reset to default' button isn't displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isConfigurationPopUpButtonClickableByName(
        getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_RESET_TO_DEFAULT_BUTTON)),
        "Configure columns pop-up 'Reset to default' button isn't clickable!");
    softAssert.assertAll();
  }

  @Test
  public void verifyCheckboxesOfConfigureColumnsPopUp() {
    List<String> expectedCheckboxValues = Arrays
        .asList(getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECKBOX_VALUES).split(","));
    softAssert.assertEquals(expectedCheckboxValues,
        reactParticipantsTrainingScreen.getConfigurationPopUpCheckboxValues(),
        "List of checkbox values isn't equal to the expected one!");
    softAssert
        .assertEquals(reactParticipantsTrainingScreen.getNumberOfCheckBoxesInConfigurationPopUp(),
            expectedCheckboxValues.size(),
            "Number of checkboxes isn't equal to number of checkbox values!");
    softAssert.assertAll();
  }
}
