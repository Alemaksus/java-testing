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

@Test(description = "EPMRDPT_14308_VerifyThatUsersWithNARatingValueAreDisplayedOnParticipantsTab",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_14308_VerifyThatUsersWithNARatingValueAreDisplayedOnParticipantsTab {

  private static final String TRAINING = "AUTOTEST WITH AC";
  private static final String USER_NAME = "QQ AA";
  private static final String EXPECTED_RATING_OF_USER = getValueOf(ONLINE_POPUP_STATUS_ASSIGNED);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING)
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility()
        .waitScreenLoading();
  }
  @Test
  public void checkStudentHasCorrectRating() {
    new ReactParticipantsService().findParticipantBySearchPhrase(USER_NAME);
    assertEquals(new ReactParticipantsTrainingScreen().getStudentRatingText(USER_NAME),
        EXPECTED_RATING_OF_USER,
        format("Student '%s' hasn't rating '%s'!", USER_NAME,
            EXPECTED_RATING_OF_USER));
  }
}
