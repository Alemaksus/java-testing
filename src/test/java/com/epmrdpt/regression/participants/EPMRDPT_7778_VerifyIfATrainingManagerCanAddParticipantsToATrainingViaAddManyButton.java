package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_7778_VerifyIfATrainingManagerCanAddParticipantsToATrainingViaAddManyButton",
    groups = {"full", "regression", "manager", "deprecated"})
public class EPMRDPT_7778_VerifyIfATrainingManagerCanAddParticipantsToATrainingViaAddManyButton {

  private static final String TRAINING_NAME = "AutoTest_WithoutParticipants";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private List<String> searchResultsByName;
  private int maximumNumberStudents = 1000;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab().waitSpinnerOfLoadingInvisibility();
  }

  @Test(priority = 1)
  public void checkParticipantsScreenLoading() {
    assertTrue(reactParticipantsTrainingScreen.isScreenLoaded(),
        "Participant screen isn't loaded!");
  }

  @Test(priority = 2)
  public void checkStudentsAddedToParticipantsListWithNewStatus() {
    searchResultsByName =
        reactParticipantsTrainingScreen
            .waitSpinnerOfLoadingInvisibility()
            .clickAddParticipantButton()
            .waitSpinnerOfLoadingInvisibility()
            .getParticipantNames();
    for (int i = 0; i < 2; i++) {
      reactParticipantsTrainingScreen.waitSpinnerOfLoadingInvisibility()
          .clickSearchByNameInput();
    }
    for (String studentName : searchResultsByName) {
      assertEquals(
          reactParticipantsTrainingScreen.getApplicantStatusByParticipantName(studentName),
          LocaleProperties.getValueOf(LocalePropertyConst.PARTICIPANTS_APPLICANT_STATUS_NEW),
          "Students have incorrect applicant status!");
    }
  }
}
