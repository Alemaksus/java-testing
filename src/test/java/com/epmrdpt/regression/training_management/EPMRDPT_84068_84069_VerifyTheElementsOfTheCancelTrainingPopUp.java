package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_DEPARTMENT_AFFILIATE_POPUP_SCREEN_BODY_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_BODY_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_GO_TO_CLASSES_TAB_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CANCEL_TRAINING_POPUP_SCREEN_GO_TO_GROUPS_TAB_LINK;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactCancelTrainingPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84068_84069_VerifyTheElementsOfTheCancelTrainingPopUp",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84068_84069_VerifyTheElementsOfTheCancelTrainingPopUp {

  private ReactCancelTrainingPopUpScreen reactCancelTrainingPopUpScreen;
  private String trainingName;
  private String statusChangeLinkText;
  private String popUpBodyText;

  @DataProvider(name = "Provider of training names")
  public static Object[][] provideTrainingNames() {
    return new Object[][]{
        {"AutoTest_StaffedStatus",
            getValueOf(REACT_CANCEL_TRAINING_POPUP_SCREEN_GO_TO_GROUPS_TAB_LINK),
            getValueOf(REACT_CANCEL_TRAINING_POPUP_SCREEN_BODY_TEXT)},
        {"Classes test", getValueOf(REACT_CANCEL_TRAINING_POPUP_SCREEN_GO_TO_CLASSES_TAB_LINK),
            getValueOf(REACT_CANCEL_TRAINING_DEPARTMENT_AFFILIATE_POPUP_SCREEN_BODY_TEXT)}
    };
  }

  @Factory(dataProvider = "Provider of training names")
  public EPMRDPT_84068_84069_VerifyTheElementsOfTheCancelTrainingPopUp(String trainingName,
      String statusChangeLinkText, String popUpBodyText) {
    this.trainingName = trainingName;
    this.statusChangeLinkText = statusChangeLinkText;
    this.popUpBodyText = popUpBodyText;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded();
  }

  @Test(priority = 1)
  public void checkCancelTrainingPopUpOpens() {
    reactCancelTrainingPopUpScreen = new ReactTrainingManagementService()
        .searchTrainingByName(trainingName)
        .clickTrainingNameByName(trainingName)
        .waitScreenLoaded()
        .clickCancelTrainingButton();
    assertTrue(reactCancelTrainingPopUpScreen.isScreenLoaded(),
        "Cancel training Pop-up screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkContentOfCancelTrainingPopUp() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isStatusChangeTitleDisplayed(),
        "Pop-up title isn't displayed!");
    softAssert.assertEquals(reactCancelTrainingPopUpScreen.getPopUpTitleText(),
        getValueOf(REACT_CANCEL_TRAINING_POPUP_SCREEN_CHANGE_STATUS_TITLE),
        "Pop-op has incorrect title!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isCrossButtonDisplayed(),
        "Cross button isn't displayed!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isCrossButtonClickable(),
        "Cross button isn't clickable!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isBodyTextDisplayed(),
        "Body text isn't displayed!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.getBodyText().contains(popUpBodyText),
        "Pop-up has incorrect body text!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isGoToGroupsOrClassesTabLinkClickable(),
        "Go to Groups tab link isn't clickable!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isGoToGroupsOrClassesTabLinkDisplayed(),
        "Go to Groups tab link isn't displayed!");
    softAssert.assertEquals(reactCancelTrainingPopUpScreen.getGoToGroupsOrClassesTabLinkText(),
        statusChangeLinkText, "Go to Groups/Classes tab link has incorrect text!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isOkButtonDisplayed(),
        "OK button isn't displayed!");
    softAssert.assertTrue(reactCancelTrainingPopUpScreen.isOkButtonClickable(),
        "OK button isn't clickable!");
    softAssert.assertAll();
  }
}
