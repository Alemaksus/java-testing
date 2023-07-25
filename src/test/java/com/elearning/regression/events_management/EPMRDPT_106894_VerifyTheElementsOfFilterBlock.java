package com.epmrdpt.regression.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;

import com.epmrdpt.screens.ReactEventsManagementScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_106894_VerifyTheElementsOfFilterBlock",
    groups = {"full", "regression"})
public class EPMRDPT_106894_VerifyTheElementsOfFilterBlock {

  private static final String COUNTRY_DROPDOWN_LIST = "Country";
  private static final String CITY_DROPDOWN_LIST = "City";
  private static final String SKILL_DROPDOWN_LIST = "Skill";
  private static final String FORMAT_DROPDOWN_LIST = "Format";
  private static final String CURRENT_STATUS_DROPDOWN_LIST = "Current status";
  private static final String DATE_FROM_INPUT = "From:";
  private static final String DATE_TO_INPUT = "To:";
  private static final String DATE_LABEL = "Date";
  private static final String APPLY_BUTTON = "Apply";
  private static final String RESET_BUTTON = "Reset";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new ReactLoginService()
        .loginAndGoToReactEventsManagementScreen(asEventManager())
        .waitScreenLoaded();
  }

  @Test
  public void verifyThatFilterBlockConsistsOfTheSpecificElements() {
    ReactEventsManagementScreen reactEventsManagementScreen = new ReactEventsManagementScreen();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactEventsManagementScreen.isSearchByEventNameFieldDisplayed(),
        "'Search by event name' field is not displayed.");
    softAssert.assertTrue(
        reactEventsManagementScreen.isDropdownListDisplayed(COUNTRY_DROPDOWN_LIST),
        "'Country' dropdown list is not displayed.");
    reactEventsManagementScreen
        .chooseDropdownOption(getValueOf(COUNTRY_NAME_BELARUS));
    softAssert.assertTrue(
        reactEventsManagementScreen.isDropdownListDisplayed(CITY_DROPDOWN_LIST),
        "'City' dropdown list is not displayed.");
    softAssert.assertTrue(reactEventsManagementScreen.isDropdownListLabelDisplayed(DATE_LABEL),
        "'Date' label is not displayed.");
    softAssert.assertTrue(reactEventsManagementScreen.isDatePickerDisplayed(DATE_FROM_INPUT),
        "'From' date picker is not displayed.");
    reactEventsManagementScreen.clickDate(DATE_FROM_INPUT);
    softAssert.assertTrue(reactEventsManagementScreen.isDatePickerDisplayed(DATE_TO_INPUT),
        "'To' date picker is not displayed.");
    softAssert.assertTrue(reactEventsManagementScreen.isDropdownListDisplayed(SKILL_DROPDOWN_LIST),
        "'Skill' dropdown list is not displayed.");
    softAssert.assertTrue(
        reactEventsManagementScreen.isDropdownListDisplayed(FORMAT_DROPDOWN_LIST),
        "'Format' dropdown list is not displayed.");
    softAssert.assertTrue(
        reactEventsManagementScreen.isDropdownListDisplayed(CURRENT_STATUS_DROPDOWN_LIST),
        "'Current status' dropdown list is not displayed.");
    softAssert.assertTrue(reactEventsManagementScreen.isMyRelationLabelDisplayed(),
        "'My relation' label is not displayed.");
    softAssert.assertTrue(reactEventsManagementScreen.isIAmCreatorCheckboxDisplayed(),
        "'I am creator' checkbox is not displayed.");
    softAssert.assertTrue(reactEventsManagementScreen.isButtonDisplayed(APPLY_BUTTON),
        "'Apply' button is not displayed.");
    softAssert.assertTrue(reactEventsManagementScreen.isButtonDisplayed(RESET_BUTTON),
        "'Reset' button is not displayed.");
    softAssert.assertAll();
  }
}
