package com.epmrdpt.smoke.edittraining;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTIFY_MEMBERS_POP_UP_ENTER_COMMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_MEMBERS_TITLE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactNotifyMembersPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13260_VerifyNotifyMembersPopUpIsDisplayedOnTheTrainingDetailsPage",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13260_VerifyNotifyMembersPopUpIsDisplayedOnTheTrainingDetailsPage {

  private String trainingName = "AutoTest_RegistrationOpenStatus";
  private ReactNotifyMembersPopUpScreen reactNotifyMembersPopUpScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13260_VerifyNotifyMembersPopUpIsDisplayedOnTheTrainingDetailsPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactNotifyMembersPopUpScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitNotifyMembersButtonClickable()
        .clickNotifyMembersButton();
  }

  @Test(priority = 1)
  public void checkThatNotifyMembersPopUpDisplayed() {
    assertTrue(reactNotifyMembersPopUpScreen.isScreenLoaded(),
        "NOTIFY MEMBERS' popup isn't displayed!");
  }

  @Test(priority = 2)
  public void checkNotifyMembersPopUpHasCrossButton() {
    assertTrue(reactNotifyMembersPopUpScreen.isCrossButtonDisplayed(),
        "'Notify Members' popUp hasn't cross button!");
  }

  @Test(priority = 2)
  public void checkNotifyMembersPopUpHasEnterCommentFieldWithCorrectText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.isCommentFieldDisplayed(),
        "'Notify Members' popUp hasn't 'Edit a a comment' field!");
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.getCommentFieldText().contentEquals(
            getValueOf(NOTIFY_MEMBERS_POP_UP_ENTER_COMMENT)),
        "'Edit a a comment' field has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkNotifyMembersPopUpHasNotifyMembersText() {
    assertEquals(getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_MEMBERS_TITLE),
        reactNotifyMembersPopUpScreen.getPopUpTitleText(),
        "'Notify Members' has incorrect subtitle!");
  }

  @Test(priority = 2)
  public void checkNotifyMembersPopUpHasStartDateCheckboxWithCorrectText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.isStartDateCheckBoxDisplayed(),
        "'Notify Members' popUp hasn't 'Start date' checkbox!");
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.getStartDateText().contentEquals(
            getValueOf(LocalePropertyConst.NOTIFY_MEMBERS_POP_UP_START_DATE)),
        "'Start date' checkbox has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkNotifyMembersPopUpHasNewProgramCheckboxWithCorrectText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.isNewProgramCheckBoxDisplayed(),
        "'Notify Members' popUp hasn't 'New program' checkbox!");
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.getNewProgramText().contentEquals(
            getValueOf(LocalePropertyConst.NOTIFY_MEMBERS_POP_UP_NEW_PROGRAM)),
        "'New program' checkbox has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkNotifyMembersPopUpNotifyButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.isNotifyButtonDisplayed(),
        "'Notify Members' popUp hasn't 'Notify' button!");
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.getNotifyButtonText()
            .equals(getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_NOTIFY_BUTTON)),
        "'Notify' button has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkNotifyMembersPopUpCloseButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.isCancelButtonDisplayed(),
        "'Notify Members' popUp hasn't 'Close' button!");
    softAssert.assertTrue(reactNotifyMembersPopUpScreen.getCancelButtonText()
            .equals(getValueOf(REACT_NOTIFY_MEMBERS_POPUP_SCREEN_CANCEL_BUTTON)),
        "'Cancel' button has incorrect text!");
    softAssert.assertAll();
  }
}
