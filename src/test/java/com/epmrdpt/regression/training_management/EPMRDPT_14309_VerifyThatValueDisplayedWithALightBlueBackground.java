package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_ASSIGNED;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14309_VerifyThatUsersWhoRegisteredToATrainingBeforeACTestWasLinkedHaveTheirACRatingValueDisplayedWithALightBlueBackground",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_14309_VerifyThatValueDisplayedWithALightBlueBackground {

  private final String TRAINING_NAME = "AutoTest_CheckRatingColour";
  private final String STUDENT_NAME = "Kate LastName283637";
  private final String EXPECTED_RATING_OF_STUDENT = getValueOf(ONLINE_POPUP_STATUS_ASSIGNED);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    new ReactParticipantsService()
        .findParticipantBySearchPhrase(STUDENT_NAME);
  }

  @Test
  public void checkRatingCellOfUserHasCorrectFormat() {
    assertEquals(new ReactParticipantsTrainingScreen()
            .getFirstStudentRatingText(),
        EXPECTED_RATING_OF_STUDENT,
        "Student passed registration test!");
  }
}
