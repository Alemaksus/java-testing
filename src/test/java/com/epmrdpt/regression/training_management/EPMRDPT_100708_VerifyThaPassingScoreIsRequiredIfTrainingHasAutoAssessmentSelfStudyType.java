package com.epmrdpt.regression.training_management;

import com.epmrdpt.screens.ReactAssignmentContainerPopUpScreen;;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

@Test(description = "EPMRDPT_100708_VerifyThaPassingScoreIsRequiredIfTrainingHasAutoAssessmentSelfStudyType",
        groups = {"full", "manager", "regression"})
public class EPMRDPT_100708_VerifyThaPassingScoreIsRequiredIfTrainingHasAutoAssessmentSelfStudyType {

    private ReactAssignmentContainerPopUpScreen reactAssignmentContainerPopUpScreen;
    private static final String TRAINING_NAME = "Training For Autotest case 100682";
    private static final String CONTAINER_ID = "18";
    private static final String DEADLINE_DATA = "01.01.2026";

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        new ReactLoginService().
                loginAndGoToReactTrainingManagement(asTrainingManager());
        new ReactTrainingManagementService()
                .searchTrainingByName(TRAINING_NAME)
                .clickTrainingNameByName(TRAINING_NAME);
        new ReactSurveyAndTestingTabScreen()
                .clickSurveyAndTestingTabFromTrainingScreen()
                .waitScreenLoading()
                .clickLinkByIdButton();
        reactAssignmentContainerPopUpScreen = new ReactAssignmentContainerPopUpScreen()
                .typeIdValue(CONTAINER_ID)
                .clickSelectIdAndNameFromDDL(CONTAINER_ID, CONTAINER_ID)
                .typeDateInSetDeadLineInput(DEADLINE_DATA)
                .clickConfirmationButton();
    }

    @Test
    public void TestEmptyPassingScoreRequiredWarning() {
        assertTrue(reactAssignmentContainerPopUpScreen.isWarningFieldDisplayed());
    }
}
