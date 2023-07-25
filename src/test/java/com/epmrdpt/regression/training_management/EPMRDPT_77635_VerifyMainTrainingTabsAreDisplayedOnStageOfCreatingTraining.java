package com.epmrdpt.regression.training_management;

import com.epmrdpt.screens.ReactCreateTrainingScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

@Test(description = "EPMRDPT_77635_VerifyMainTrainingTabsAreDisplayedOnStageOfCreatingTraining",
        groups = {"full", "regression"})
public class EPMRDPT_77635_VerifyMainTrainingTabsAreDisplayedOnStageOfCreatingTraining {

    private ReactCreateTrainingScreen reactCreateTrainingScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void loginAndGoToNewTraining() {
        new ReactLoginService()
                .loginAndGoToReactTrainingManagement(asTrainingManager());
        reactCreateTrainingScreen = new ReactTrainingManagementScreen()
                .clickCreateNewButton()
                .waitScreenLoading();
    }

    @Test
    public void checkTrainingTabsInNewTraining() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(reactCreateTrainingScreen.isScreenLoaded(),
                "Training screen is not loaded");
        softAssert.assertTrue(reactCreateTrainingScreen.isDescriptionTabDisplayedNoWait(),
                "'Description' tab is not displayed");
        softAssert.assertTrue(reactCreateTrainingScreen.isGeneralInfoTabDisplayedNoWait(),
                "'General Info' tab is not displayed");
        softAssert.assertTrue(reactCreateTrainingScreen.isAutoReplyTabDisplayedNoWait(),
                "'Autoreply' tab is not displayed");
        softAssert.assertTrue(reactCreateTrainingScreen.isRegistrationFormTabDisplayedNoWait(),
                "'Registration form' tab is not displayed");
        softAssert.assertTrue(reactCreateTrainingScreen.isSurveyAndTestingTabDisplayedNoWait(),
                "'Survey and Testing' tab is not displayed");
        softAssert.assertTrue(reactCreateTrainingScreen.isIntegrationTabDisplayedNoWait(),
                "'Integration' tab is not displayed");
        softAssert.assertAll();
    }
}
