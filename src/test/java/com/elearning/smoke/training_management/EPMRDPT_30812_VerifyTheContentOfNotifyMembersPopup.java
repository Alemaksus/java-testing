package com.epmrdpt.smoke.training_management;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactNotifyMembersPopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_30812_VerifyTheContentOfNotifyMembersPopup",
    groups = {"full", "manager", "smoke", "deprecated"})

public class EPMRDPT_30812_VerifyTheContentOfNotifyMembersPopup {

  private User user;
  private String trainingName = "AutoTest_AutomaticReplyText";
  private ReactNotifyMembersPopUpScreen reactNotifyMembersPopUpScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_30812_VerifyTheContentOfNotifyMembersPopup(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink().waitAllSpinnersAreNotDisplayed();
    reactNotifyMembersPopUpScreen = new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(
            trainingName)
        .waitGroupsTabDisplayed().clickNotifyMembersButton().waitScreenLoading();
  }

  @Test
  public void checkPopUpContainsNotifyMembersTitle() {
    assertTrue(reactNotifyMembersPopUpScreen.isNotifyMembersTitleDisplayed(),
        "Notify members title is not displayed");
  }

  @Test
  public void checkPopUpContainsCrossButton() {
    assertTrue(reactNotifyMembersPopUpScreen.isCrossButtonDisplayed(),
        "Cross button is not displayed");
  }

  @Test
  public void checkPopUpContainsEnterCommentField() {
    assertTrue(reactNotifyMembersPopUpScreen.isCommentFieldDisplayed(),
        "Enter comment field is not displayed");
  }

  @Test
  public void checkPopUpContainsNotifyMembersText() {
    assertTrue(reactNotifyMembersPopUpScreen.isNotifyMembersTextDisplayed(),
        "Notify members text is not displayed");
  }

  @Test
  public void checkPopUpContainsStartDateCheckbox() {
    assertTrue(reactNotifyMembersPopUpScreen.isStartDateCheckBoxDisplayed(),
        "Start date checkbox is not displayed");
  }

  @Test
  public void checkPopUpContainsNewProgramCheckbox() {
    assertTrue(reactNotifyMembersPopUpScreen.isNewProgramCheckBoxDisplayed(),
        "New program checkbox is not displayed");
  }

  @Test
  public void checkPopUpContainsNotifyButton() {
    assertTrue(reactNotifyMembersPopUpScreen.isNotifyButtonDisplayed(),
        "Notify button is not displayed");
  }

  @Test
  public void checkPopUpContainsCancelButton() {
    assertTrue(reactNotifyMembersPopUpScreen.isCancelButtonDisplayed(),
        "Cancel button is not displayed");
  }
}
