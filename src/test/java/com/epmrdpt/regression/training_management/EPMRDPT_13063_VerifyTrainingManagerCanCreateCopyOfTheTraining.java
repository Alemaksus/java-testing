package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactCreateTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13063_VerifyTrainingManagerCanCreateCopyOfTheTraining",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_13063_VerifyTrainingManagerCanCreateCopyOfTheTraining {

  @Test
  public void checkTrainingManagerCanCreateCopyOfTheTraining() {
    String originalTrainingName = "AutoTest_RegistrationOpenStatus";
    String copiedTrainingName = "Copy - AutoTest_RegistrationOpenStatus";
    SoftAssert softAssert = new SoftAssert();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(originalTrainingName)
        .clickCreateCopyButton().switchToLastWindow();
    softAssert.assertFalse(
        new ReactCreateTrainingScreen().waitScreenLoading().isTrainingStatusButtonDisplayedNoWait(),
        "Training Manager can't create copy of the training!");
    softAssert.assertEquals(new ReactGeneralInfoTabScreen().getTrainingNameText(),
        copiedTrainingName,
        "Copied training name not equals to copied from original");
    softAssert.assertAll();
  }
}
