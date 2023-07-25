package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_ASSIGNED;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14307_VerifyThatUsersAddedToTheTrainingByTheTrainingManagerHaveTheirResultsDisplayedAsA",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_14307_VerifyThatUsersAddedToTheTrainingByTheTrainingManagerHaveTheirResultsDisplayedAsA {

  private final String TRAINING = "AutoTest_VCH AC";
  private final String USER_NAME = "Alexandra LastName297734";
  private final String EXPECTED_RATING_OF_USER = getValueOf(ONLINE_POPUP_STATUS_ASSIGNED);

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING)
        .clickReactParticipantsTab()
        .waitScreenLoading();
    new ReactParticipantsService()
        .findParticipantBySearchPhrase(USER_NAME);
  }

  @Test(priority = 1)
  public void checkStudentHasCorrectRating() {
    assertEquals(reactParticipantsTrainingScreen.getStudentRatingText(USER_NAME),
        EXPECTED_RATING_OF_USER,
        format("Student '%s' hasn't rating '%s'!", USER_NAME, EXPECTED_RATING_OF_USER));
  }
}
