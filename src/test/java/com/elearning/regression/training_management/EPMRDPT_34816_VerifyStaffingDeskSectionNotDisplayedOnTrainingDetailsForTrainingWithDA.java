package com.epmrdpt.regression.training_management;

import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34816_VerifyStaffingDeskSectionNotDisplayedOnTrainingDetailsForTrainingWithDA",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_34816_VerifyStaffingDeskSectionNotDisplayedOnTrainingDetailsForTrainingWithDA {

  private String trainingName = "AutoTestDA";

  @Test
  public void checkStaffingDeskSectionNotDisplayedOnTrainingDetailsForTrainingWithDA() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
    assertFalse(new ReactTrainingManagementService()
            .searchTrainingByNameAndRedirectOnIt(trainingName)
            .isIntegrationTabDisplayedNoWait(),
        "Integration tab with the Recruiting integration (Staffing Desk) section is displayed!");
  }
}
