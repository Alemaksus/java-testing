package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_DRAFT_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_MANAGER_PLANNED_TRAINING_STATUS;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactStatusChangePopUpScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14298_VerifyIfTrainingManagerCanChangeTrainingStatusToPlanned",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_14298_VerifyIfTrainingManagerCanChangeTrainingStatusToPlanned {

  private final String trainingName = "AutoTest_WithGroupAndPatricipantInProgress";
  private final String settableStatus = getValueOf(TRAINING_MANAGER_PLANNED_TRAINING_STATUS);
  private final String basicStatus = getValueOf(TRAINING_MANAGER_DRAFT_TRAINING_STATUS);
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactDetailTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitScreenLoaded();
  }

  @Test(priority = 1)
  public void checkTrainingDetailPageLoaded() {
    assertTrue(reactDetailTrainingScreen
            .isScreenLoaded(),
        format("Detail page of '%s' training isn't loaded!", trainingName));
  }

  @Test(priority = 2)
  public void checkTrainingManagerCanChangeTrainingStatus() {
    SoftAssert softAssert = new SoftAssert();
    reactTrainingManagementService
        .setTrainingStatusByText(basicStatus);
    softAssert.assertTrue(reactDetailTrainingScreen
            .isTrainingStatusByStatusNameActive(basicStatus),
        format("Basic status '%s' of '%s' training is not active before test!",
            basicStatus, trainingName));
    reactDetailTrainingScreen
        .clickTrainingStatusButtonByText(settableStatus);
    new ReactStatusChangePopUpScreen()
        .clickConfirmationOfChangingStatusButton()
        .waitNotificationPopUpVisibility();
    softAssert.assertTrue(reactDetailTrainingScreen
            .isTrainingStatusByStatusNameActive(settableStatus),
        format("Settable status '%s' of '%s' training has not been set!",
            settableStatus, trainingName));
    softAssert.assertAll();
  }
}
