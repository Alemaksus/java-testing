package com.epmrdpt.regression.participants;


import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_99128_VerifyTheStatusesThatAreDisplayedOnGeneralInformationCard",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_99128_VerifyTheStatusesThatAreDisplayedOnGeneralInformationCard {

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private static final String TRAINING_NAME = "VM examinator";
  private static final List<String> STATUSES_TO_CHECK = Arrays.asList(
      "New",
      "Auto-accepted",
      "Auto-finished",
      "Registration is cancelled");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test
  public void checkStatusesThatAreDisplayedOnGeneralInformationCard() {
    List<String> statusesFromParticipantsScreen = reactParticipantsTrainingScreen
        .getStatusesFromGeneralInformationTab();
    Assert.assertEquals(statusesFromParticipantsScreen, STATUSES_TO_CHECK,
        "Not all the statuses displayed!");
  }
}
