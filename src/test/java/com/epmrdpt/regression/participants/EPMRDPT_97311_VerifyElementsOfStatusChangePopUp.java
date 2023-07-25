package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_DISMISSED_FROM_TRAINING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_DISMISSED_FROM_LAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_STATUS_REJECTED;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Test(description = "EPMRDPT_97311_VerifyElementsOfStatusChangePopUp",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_97311_VerifyElementsOfStatusChangePopUp {

  private final String trainingName = "C++ Gomel";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private SoftAssert softAssert;

  @DataProvider(name = "provider of name applicant status")
  public Object[][] applicantStatusNameProvider() {
    return new Object[][]{
        {getValueOf(APPLICATIONS_STATUS_DISMISSED_FROM_TRAINING)},
        {getValueOf(APPLICATIONS_STATUS_DISMISSED_FROM_LAB)},
        {getValueOf(APPLICATIONS_STATUS_REJECTED)}
    };
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {

    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test(dataProvider = "provider of name applicant status")
  public void verifyTitleInThePopUp(String nameOfApplicantStatus) {
    reactParticipantsTrainingScreen.clickFirstStudentStatus()
        .clickApplicantStatusInChangeStatusWindowByName(nameOfApplicantStatus);
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isTitlePopUpInApplicantStatusDisplayed(),
        "The title is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isInitiatedByFieldPopUpInApplicantStatusDisplayed(),
        "The initiated by field is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isEpamButtonPopUpInApplicantStatusDisplayed(),
        "The epam button is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isCandidateButtonPopUpInApplicantStatusDisplayed(),
        "The candidate button is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isRejectionReasonPopUpInApplicantStatusDisplayed(),
        "The rejection reason field is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isOKButtonPopUpInApplicantStatusDisplayed(),
        "The OK button is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isCancelButtonPopUpInApplicantStatusDisplayed(),
        "The Cancel button is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isCrossButtonPopUpInApplicantStatusDisplayed(),
        "Configure columns pop-up cross button isn't displayed!");
    softAssert.assertAll();
  }

  @AfterMethod
  public void closePopUpWindow() {
    reactParticipantsTrainingScreen.clickCrossButtonPopUpInApplicantStatus();
  }
}

