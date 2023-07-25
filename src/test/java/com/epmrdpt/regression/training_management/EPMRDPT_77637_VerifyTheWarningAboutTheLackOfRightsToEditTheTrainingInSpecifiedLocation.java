package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_MAP_SECTION_ARMENIA_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_NO_PERMISSION_TO_EDIT_LOCATION_POPUP_TEXT;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77637_VerifyTheWarningAboutTheLackOfRightsToEditTheTrainingInSpecifiedLocation",
    groups = {"full", "regression"})
public class EPMRDPT_77637_VerifyTheWarningAboutTheLackOfRightsToEditTheTrainingInSpecifiedLocation {

  private final String trainingName = "AUTOTEST WITH AC";
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(UserFactory.asTrainingManagerLocatedRoleAutoTestCity());
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test(priority = 1)
  public void checkUserCantChooseCountryWhichDoesNotHaveRights() {
    reactGeneralInfoTabScreen.setTrainingCountryByName(getValueOf(ABOUT_MAP_SECTION_ARMENIA_TAB))
        .waitAllSpinnersAreNotDisplayed();
    reactDetailTrainingScreen.clickSaveChangesButton();
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactGeneralInfoTabScreen.getTextOfPopup(),
        getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_NO_PERMISSION_TO_EDIT_LOCATION_POPUP_TEXT),
        "'No permission to edit location' pop-up has incorrect text!");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isCrossIconOfPopupDisplayed(),
        "Cross icon of 'No permission to edit location' pop-up is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkUserCantLeaveCountryFieldEmpty() {
    reactGeneralInfoTabScreen.clickCrossIconOfPopup()
        .clearSelectedInputsOfCountryField()
        .waitAllSpinnersAreNotDisplayed();
    reactDetailTrainingScreen.clickSaveChangesButton();
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactGeneralInfoTabScreen.getTextOfPopup(),
        getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_NO_PERMISSION_TO_EDIT_LOCATION_POPUP_TEXT),
        "'No permission to edit location' pop-up has incorrect text!");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isCrossIconOfPopupDisplayed(),
        "Cross icon of 'No permission to edit location' pop-up is not displayed!");
    softAssert.assertAll();
  }
}
