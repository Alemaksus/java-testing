package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84084_VerifyTheAbilityToSelectOneParticipant",
        groups = {"full", "regression", "manager"})
public class EPMRDPT_84084_VerifyTheAbilityToSelectOneParticipant {

    private static final String TRAINING_NAME = "AutoTest_StudentsTask";
    private static final String GROUP_NAME = "Java_Task";
    private static final String STUDENT_NAME = "AA BB";
    private static final String NUMBER_OF_PARTICIPANTS = "1";
    private ReactGroupDetailsScreen reactGroupDetailsScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        reactGroupDetailsScreen = new LoginService().
                loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
                .clickReactTrainingManagementLink()
                .waitAllTrainingNameDisplayed()
                .typeTrainingName(TRAINING_NAME)
                .clickApplyButton()
                .waitAllSpinnersAreNotDisplayed()
                .clickTrainingNameByName(TRAINING_NAME)
                .clickGroupsTabs()
                .clickGroupByName(GROUP_NAME)
                .waitGroupNameTextToBePresent()
                .clickParticipantCheckboxByName(STUDENT_NAME);
    }

    @Test
    public void checkCheckboxTicked() {
        assertTrue(reactGroupDetailsScreen.isParticipantCheckboxChecked(),
                "The checkbox is not ticked!");
    }

    @Test
    public void checkCounterMessage() {
        assertEquals(reactGroupDetailsScreen.getParticipantsSelectedSubtitleText(),
                getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT) + " " + NUMBER_OF_PARTICIPANTS,
                "Counter above list table contains wrong text!");
    }
}
