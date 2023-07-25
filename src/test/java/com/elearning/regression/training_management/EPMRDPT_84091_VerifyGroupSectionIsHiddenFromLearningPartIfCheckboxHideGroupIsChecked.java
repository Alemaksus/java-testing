package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asStudentWithTasks;
import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84091_VerifyGroupSectionIsHiddenFromLearningPartIfCheckboxHideGroupIsChecked",
        groups = {"full", "regression"})
public class EPMRDPT_84091_VerifyGroupSectionIsHiddenFromLearningPartIfCheckboxHideGroupIsChecked {

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
        reactGroupDetailsScreen.clickHideGroupCheckbox()
                .clickSaveChangesButton();
    }

    @Test(priority = 1)
    public void checkSelectedHideGroupCheckbox() {
        assertTrue(reactGroupDetailsScreen.isHideGroupCheckboxChecked(),
                "Checkbox 'Hide group' not selected!");
    }

    @Test(priority = 2)
    public void checkSaveChangesPopUpHintIsDisplayed() {
        assertTrue(reactGroupDetailsScreen.isSaveChangesPopUpDisplayed(),
                "Save changes Pop-Up Hint isn't appeared!");
    }

    @Test(priority = 3)
    public void checkLearningPartIsNotDisplayed() {
        new ReactLoginService().signOut();
        HeaderScreen headerScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asStudentWithTasks());
        assertFalse(headerScreen.isLearningButtonDisplayed(),
                "Learning part is displayed");
    }

    private void makeHideGroupCheckboxUncheckedIfChecked() {
        reactGroupDetailsScreen = new ReactGroupDetailsScreen();
        if (reactGroupDetailsScreen.isHideGroupCheckboxChecked()) {
            reactGroupDetailsScreen.clickHideGroupCheckbox()
                    .clickSaveChangesButton()
                    .waitResultOfChangeMessageDisplayed()
                    .closeSaveChangesPopup()
                    .waitResultOfChangeMessageInvisibility();
        }
    }
}
