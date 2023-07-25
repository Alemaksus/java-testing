package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermissionAndWithTraining;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_REGISTRATION_IS_CANCELLED;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_99125_VerifyTheRegistrationIsCancelledStatusIsDisplayedForUser",
        groups = {"full", "manager", "regression"})
public class EPMRDPT_99125_VerifyTheRegistrationIsCancelledStatusIsDisplayedForUser {

    private static final String TRAINING_NAME = "VM self-study with hidden AC results";
    private static final String USER = "UserWithTraining UserWithTraining";
    private static final String APPLICANT_STATUS = "Registration is cancelled";
    private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        reactParticipantsTrainingScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
                .clickReactTrainingManagementLink()
                .waitAllTrainingNameDisplayed()
                .typeTrainingName(TRAINING_NAME)
                .clickApplyButton()
                .waitAllSpinnersAreNotDisplayed()
                .clickTrainingNameByName(TRAINING_NAME)
                .clickReactParticipantsTab()
                .waitScreenLoading();
    }

    @Test(priority = 1)
    public void checkRegistrationIsCancelledStatusIsDisplayedForTrainingManager() {
        assertEquals(reactParticipantsTrainingScreen.getApplicantStatusByParticipantName(USER), APPLICANT_STATUS,
                "Registration is cancelled status is not displayed!");
    }

    @Test(priority = 2)
    public void checkRegistrationIsCancelledStatusIsDisplayedForUser() {
        new ReactLoginService().signOut();
        ProfileScreen profileScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(withSimplePermissionAndWithTraining())
                .clickProfileMenu()
                .waitProfileMenuDisplayed()
                .clickProfileButton()
                .waitTrainingInfoDisplayed();
        assertEquals(profileScreen.getTrainingStatusByTrainingName(TRAINING_NAME),
                getValueOf(APPLICATIONS_STATUS_REGISTRATION_IS_CANCELLED),
                "Registration is cancelled status is not displayed!");
    }
}
