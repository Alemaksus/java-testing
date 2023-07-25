package com.epmrdpt.regression.participants;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

@Test(description = "EPMRDPT_105589_VerifySyncToSDColumnIsDisplayed",
        groups = {"full", "regression", "manager"})

public class EPMRDPT_105589_VerifySyncToSDColumnIsDisplayed {

    private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
    private static final String COLUMN_NAME = "Sync to SD";
    private static final String TRAINING_NAME ="Training For Autotest case 100682";

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
                .clickReactTrainingManagementLink();
        reactParticipantsTrainingScreen = new ReactTrainingManagementService()
                .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
                .clickReactParticipantsTab()
                .clickConfigureColumnsButton()
                .clickConfigureColumnsCheckBoxByName(COLUMN_NAME)
                .clickConfigureColumnsTabApplyButton();
    }

    @Test
    public void TestCheckingSyncToSD() {
        assertTrue(reactParticipantsTrainingScreen.isParticipantFindByNameDisplayed(COLUMN_NAME));
    }
}
