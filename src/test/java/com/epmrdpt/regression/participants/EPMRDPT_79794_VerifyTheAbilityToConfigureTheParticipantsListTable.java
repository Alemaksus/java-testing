package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SUCCESSFUL_STATUS_CHANGED_POP_UP;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79794_VerifyTheAbilityToConfigureTheParticipantsListTable ",
        groups = {"full", "regression"})
public class EPMRDPT_79794_VerifyTheAbilityToConfigureTheParticipantsListTable {

    private final String trainingName = "AutoTest_WithParticipants";
    private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
    private ReactParticipantsService reactParticipantsService;
    private int numberColumnSource = 5;
    private int numberColumnTarget = 1;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        reactParticipantsTrainingScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
                .clickReactTrainingManagementLink()
                .waitAllTrainingNameDisplayed()
                .typeTrainingName(trainingName)
                .clickApplyButton()
                .clickTrainingNameByName(trainingName)
                .clickReactParticipantsTab()
                .waitScreenLoading();
        reactParticipantsService = new ReactParticipantsService();
    }

    @Test
    public void checkThatNewColumnsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        int sizeListOfColumnsFromTable = reactParticipantsTrainingScreen.getSizeListOfColumnsFromTable();
        reactParticipantsService.clickCheckboxInTheConfigurationPopUpScreen();
        String confirmationToasterText = reactParticipantsTrainingScreen.getConfirmationToasterText();
        reactParticipantsTrainingScreen.clickCrossButtonPopUp();
        softAssert.assertNotEquals(sizeListOfColumnsFromTable,
                reactParticipantsTrainingScreen.getSizeListOfColumnsFromTable(),
        "There are not any changes in the table");
        softAssert.assertEquals(confirmationToasterText,
                getValueOf(PARTICIPANTS_SUCCESSFUL_STATUS_CHANGED_POP_UP),
                "Confirmation toaster text is incorrect");
        softAssert.assertAll();
    }

    @Test
    public void checkThatColumnIsRepositionedToTheExpectedPlace() {
        String nameColumnSource = reactParticipantsTrainingScreen.getNameColumnOfTable(numberColumnSource);
        reactParticipantsTrainingScreen.moveColumnToAnotherPosition(numberColumnSource, numberColumnTarget);
        assertEquals(nameColumnSource,
                reactParticipantsTrainingScreen.getNameColumnOfTable(numberColumnTarget),
                "Column is not repositioned");
    }
}
