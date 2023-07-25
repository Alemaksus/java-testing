package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_FINISHED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_FORM_GROUP_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_STARTED_TRAINING_STATUS;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_77627_VerifyAllStatusesOfTrainingInStatusBar",
    groups = {"full", "smoke"})
public class EPMRDPT_77627_VerifyAllStatusesOfTrainingInStatusBar {

  private final String trainingName = "AutoTest_TrainerWorkflow";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private List<String> expectedStatuses;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingAndSetupStatuses() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitScreenLoaded();
    expectedStatuses = new ArrayList<>();
    expectedStatuses.add(getValueOf(REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS));
    expectedStatuses.add(getValueOf(REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS));
    expectedStatuses.add(getValueOf(REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS));
    expectedStatuses.add(getValueOf(REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS));
    expectedStatuses.add(getValueOf(REACT_TRAINING_MANAGEMENT_FORM_GROUP_TRAINING_STATUS));
    expectedStatuses.add(getValueOf(REACT_TRAINING_MANAGEMENT_STARTED_TRAINING_STATUS));
    expectedStatuses.add(getValueOf(REACT_TRAINING_MANAGEMENT_FINISHED_TRAINING_STATUS));
  }

  @Test
  public void checkTrainingStatuses() {
    assertEquals(
        reactDetailTrainingScreen.getTrainingStatusesText(),
        expectedStatuses,
        "Training's statuses are not correct!"
    );
  }
}
