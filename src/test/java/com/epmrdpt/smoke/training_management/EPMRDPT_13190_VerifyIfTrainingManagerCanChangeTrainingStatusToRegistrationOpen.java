package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_TEXT;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactStatusChangePopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13190_VerifyIfTrainingManagerCanChangeTrainingStatusToRegistrationOpen",
    groups = {"full", "manager", "smoke", "critical_path"})
public class EPMRDPT_13190_VerifyIfTrainingManagerCanChangeTrainingStatusToRegistrationOpen {

  private final User user;
  private final String trainingName;
  private final String basicStatus;
  private final String settableStatus;
  private SoftAssert softAssert;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactStatusChangePopUpScreen reactStatusChangePopUpScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab")
  public EPMRDPT_13190_VerifyIfTrainingManagerCanChangeTrainingStatusToRegistrationOpen(
      User user,
      String trainingName,
      String basicStatus,
      String settableStatus) {
    this.user = user;
    this.trainingName = trainingName;
    this.basicStatus = basicStatus;
    this.settableStatus = settableStatus;
  }

  @DataProvider(name = "Provider of users with Training Management tab")
  public static Object[][] provideUsers() {
    return new Object[][]{
        {
            UserFactory.asRecruiter(),
            "AutoTest_TrainingForCopy",
            LocaleProperties.getValueOf(REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS),
            LocaleProperties.getValueOf(
                REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS)},
        {
            UserFactory.asTrainingManager(),
            "AutoTest_TrainingForCopy",
            LocaleProperties.getValueOf(REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS),
            LocaleProperties.getValueOf(
                REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS)}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactStatusChangePopUpScreen = new ReactStatusChangePopUpScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(trainingName);
  }

  @Test(priority = 1)
  public void checkThatTrainingIsOpened() {
    assertEquals(reactDetailTrainingScreen.waitAllSpinnersAreNotDisplayed().getTrainingTitleText(),
        trainingName, trainingName + " isn't opened!");
  }

  @Test(priority = 2)
  public void checkThatStatusChangePopUpHasNecessaryFields() {
    reactDetailTrainingScreen
        .clickTrainingStatusButtonByText(settableStatus);
    reactStatusChangePopUpScreen.waitScreenLoading();
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactStatusChangePopUpScreen.getStatusChangeTitleText(),
        LocaleProperties.getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP),
        "Change status pop-up has incorrect title!");
    softAssert.assertTrue(reactStatusChangePopUpScreen.isCrossButtonDisplayed(),
        "Change status pop-up hasn't cross button!");
    softAssert.assertEquals(reactStatusChangePopUpScreen.getStatusChangeMessageAreaText(),
        LocaleProperties.getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_TEXT),
        "Change status pop-up has incorrect text!");
    softAssert.assertEquals(reactStatusChangePopUpScreen.getCancelButtonText(),
        LocaleProperties.getValueOf(REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CANCEL_BUTTON),
        "Change status pop-up has incorrect 'Cancel' button text!");
    softAssert.assertEquals(
        reactStatusChangePopUpScreen.getConfirmationOfChangingStatusButtonText(),
        LocaleProperties.getValueOf(
            REACT_TRAINING_MANAGEMENT_STATUS_CHANGE_POP_UP_CONFIRMATION_BUTTON),
        "Change status pop-up has 'Yes' button incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatManagerCanChangeStatusToRegistrationOpen() {
    reactStatusChangePopUpScreen
        .clickConfirmationOfChangingStatusButton()
        .waitNotificationPopUpVisibility();
    assertTrue(reactDetailTrainingScreen.isNotificationPopUpDisplayed(),
        format("Settable status '%s' of '%s' training has not been set!",
            settableStatus, trainingName));
    reactDetailTrainingScreen.closeNotificationPopUp();
    reactTrainingManagementService.setTrainingStatusByText(basicStatus);
  }
}
