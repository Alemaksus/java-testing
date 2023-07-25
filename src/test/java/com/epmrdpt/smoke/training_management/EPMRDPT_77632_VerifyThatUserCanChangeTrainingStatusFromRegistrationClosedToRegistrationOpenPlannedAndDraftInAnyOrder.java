package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Test(description = "EPMRDPT_77632_VerifyThatUserCanChangeTrainingStatusFromRegistrationClosedToRegistrationOpenPlannedAndDraftInAnyOrder",
        groups = {"full", "smoke"})
public class EPMRDPT_77632_VerifyThatUserCanChangeTrainingStatusFromRegistrationClosedToRegistrationOpenPlannedAndDraftInAnyOrder {

    private static final String TRAINING_NAME = "Software testing  Test Training";
    private ReactDetailTrainingScreen reactDetailTrainingScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
                .clickReactTrainingManagementLink();
        reactDetailTrainingScreen = new ReactTrainingManagementService()
                .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
                .waitScreenLoaded();
    }

    @Test(priority = 1)
    public void checkTrainingHasStatusRegistrationClosed() {
        assertTrue(reactDetailTrainingScreen.isTrainingStatusByStatusNameActive(
                        getValueOf(REACT_TRAINING_MANAGEMENT_REGISTRATION_CLOSED_TRAINING_STATUS)),
                "Training status not registration closed");
    }

    @Test(priority = 2)
    public void checkTrainingManagerCanSetRegistrationOpenAfterRegistrationClosedStatusForTraining() {
        reactDetailTrainingScreen.clickRegistrationOpenStatusButton();
        confirmStatusChangeToRegistrationOpen();
        assertTrue(reactDetailTrainingScreen.isTrainingStatusByStatusNameActive(getValueOf(
                REACT_TRAINING_MANAGEMENT_REGISTRATION_OPEN_TRAINING_STATUS)),
                "Manager can not set registration open status after registration closed status to training!");
    }

    @Test(priority = 3)
    public void checkTrainingManagerCanSetPlannedAfterRegistrationOpenStatusForTraining() {
        reactDetailTrainingScreen.clickPlannedStatusButton();
        confirmStatusChange();
        assertTrue(reactDetailTrainingScreen.isTrainingStatusByStatusNameActive(
                getValueOf(REACT_TRAINING_MANAGEMENT_PLANNED_TRAINING_STATUS)),
                "Manager can not set planned status after registration open status to training!");
    }

    @Test(priority = 4)
    public void checkTrainingManagerCanSetDraftAfterPlannedStatusForTraining() {
        reactDetailTrainingScreen.clickDraftStatusButton();
        confirmStatusChange();
        assertTrue(reactDetailTrainingScreen.isTrainingStatusByStatusNameActive(
                getValueOf(REACT_TRAINING_MANAGEMENT_DRAFT_TRAINING_STATUS)),
                "Manager can not set draft status after planned status to training!");

    }

    @AfterMethod(inheritGroups = false, alwaysRun = true)
    private void changeTrainingStatusBack(Method method) {
        if (method.getName().equals("checkTrainingManagerCanSetDraftAfterPlannedStatusForTraining")) {
            reactDetailTrainingScreen.clickPlannedStatusButton();
            confirmStatusChange();
            reactDetailTrainingScreen.clickRegistrationOpenStatusButton();
            confirmStatusChangeToRegistrationOpen();
            reactDetailTrainingScreen.clickRegistrationClosedStatusButton();
            confirmStatusChange();
        }
    }

    private void confirmStatusChange() {
        reactDetailTrainingScreen.waitChangeStatusPopUpVisibility()
                .clickConfirmationOfChangingStatusButton()
                .waitNotificationPopUpVisibility()
                .closeNotificationPopUp();
    }

    private void confirmStatusChangeToRegistrationOpen() {
        reactDetailTrainingScreen.waitChangeStatusStaffingDeskPopUpVisibility()
                .clickConfirmationOfChangingStaffingDeskStatusButton()
                .waitNotificationPopUpVisibility()
                .closeNotificationPopUp();
    }
}
