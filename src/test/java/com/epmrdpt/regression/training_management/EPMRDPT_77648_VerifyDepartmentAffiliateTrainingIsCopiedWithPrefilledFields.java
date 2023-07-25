package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactCreateTrainingScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77648_VerifyDepartmentAffiliateTrainingIsCopiedWithPrefilledFields",
        groups = {"full", "regression", "manager"})
public class EPMRDPT_77648_VerifyDepartmentAffiliateTrainingIsCopiedWithPrefilledFields {

    private static final String TRAINING_NAME = "Training Department Affiliate Type";
    private static final String COPY_NAME = "Copy - " + TRAINING_NAME;
    private String type;
    private String educationalEstablishment;
    private String department;
    private String displayingName;
    private String supervisors;
    private String studentGroup;
    private ReactDetailTrainingScreen reactDetailTrainingScreen;
    private ReactCreateTrainingScreen reactCreateTrainingScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        reactDetailTrainingScreen = new LoginService()
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
                .clickReactTrainingManagementLink()
                .waitAllTrainingNameDisplayed()
                .typeTrainingName(TRAINING_NAME)
                .clickApplyButton()
                .waitAllSpinnersAreNotDisplayed()
                .clickTrainingNameByName(TRAINING_NAME);
        type = reactDetailTrainingScreen.getTypeText();
        displayingName = reactDetailTrainingScreen.getDisplayingNameText();
        educationalEstablishment = reactDetailTrainingScreen.getEducationEstablishmentText();
        department = reactDetailTrainingScreen.getDepartmentText();
        supervisors = reactDetailTrainingScreen.getSupervisorsText();
        studentGroup = reactDetailTrainingScreen.getStudentGroupText();
        reactCreateTrainingScreen = reactDetailTrainingScreen.clickCreateCopyButton();
        reactCreateTrainingScreen.switchToLastWindow();
    }

    @Test
    public void checkDepartmentAffiliateTrainingFieldsCopied() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(reactCreateTrainingScreen.getNameText(), COPY_NAME,
                "'Name' field is not copied");
        softAssert.assertEquals(reactCreateTrainingScreen.getEducationEstablishmentText(), educationalEstablishment,
                "'Educational establishment' field is not copied");
        softAssert.assertEquals(reactCreateTrainingScreen.getDepartmentText(), department,
                "'Department' field is not copied");
        softAssert.assertEquals(reactCreateTrainingScreen.getDisplayingNameText(), displayingName,
                "'Display name' field is not copied");
        softAssert.assertEquals(reactCreateTrainingScreen.getTypeText(), type,
                "'Type' field is not copied");
        softAssert.assertEquals(reactCreateTrainingScreen.getSupervisorsText(), supervisors,
                "'Supervisors' field is not copied");
        softAssert.assertEquals(reactCreateTrainingScreen.getStudentGroupText(), studentGroup,
                "'Student group' field is not copied");
        softAssert.assertTrue(reactCreateTrainingScreen.isRegistrationFormTabDisplayed(),
                "'Registration form' tab is not displayed");
        softAssert.assertAll();
    }
}
