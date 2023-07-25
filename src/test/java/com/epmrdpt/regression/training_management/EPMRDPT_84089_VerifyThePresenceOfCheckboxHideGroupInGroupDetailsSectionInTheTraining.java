package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84089_VerifyThePresenceOfCheckboxHideGroupInGroupDetailsSectionInTheTraining",
        groups = {"full", "regression"})
public class EPMRDPT_84089_VerifyThePresenceOfCheckboxHideGroupInGroupDetailsSectionInTheTraining {

    private static final String TRAINING_NAME = "AutoTest_StudentsTask";
    private static final String GROUP_NAME = "Java_Task";
    private ReactGroupDetailsScreen reactGroupDetailsScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        reactGroupDetailsScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
                .clickReactTrainingManagementLink()
                .waitAllTrainingNameDisplayed()
                .typeTrainingName(TRAINING_NAME)
                .clickApplyButton()
                .waitAllSpinnersAreNotDisplayed()
                .clickTrainingNameByName(TRAINING_NAME)
                .clickGroupsTabs()
                .clickGroupByName(GROUP_NAME);
        makeHideGroupCheckboxUncheckedIfChecked();
        reactGroupDetailsScreen.clickHideGroupCheckbox();
    }

    @Test
    public void checkSelectedHideGroupCheckbox() {
        assertTrue(reactGroupDetailsScreen.isHideGroupCheckboxChecked(),
                "Checkbox 'Hide group' not selected!");
    }

    private void makeHideGroupCheckboxUncheckedIfChecked() {
        reactGroupDetailsScreen = new ReactGroupDetailsScreen();
        if (reactGroupDetailsScreen.isHideGroupCheckboxChecked()) {
            reactGroupDetailsScreen.clickHideGroupCheckbox()
                    .clickSaveChangesButton();
        }
    }
}
