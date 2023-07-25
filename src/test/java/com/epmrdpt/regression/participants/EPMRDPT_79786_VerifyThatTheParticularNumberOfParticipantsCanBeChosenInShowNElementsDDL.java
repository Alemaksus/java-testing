package com.epmrdpt.regression.participants;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79786_VerifyThatTheParticularNumberOfParticipantsCanBeChosenInShowNElementsDDL",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_79786_VerifyThatTheParticularNumberOfParticipantsCanBeChosenInShowNElementsDDL {

  private static final String TRAINING_NAME = "RS School Autumn 2019";
  private static final String LIMIT_OF_PARTICIPANT_10 = "10";
  private static final String LIMIT_OF_PARTICIPANT_25 = "25";
  private static final String LIMIT_OF_PARTICIPANT_50 = "50";
  private static final String LIMIT_OF_PARTICIPANT_100 = "100";
  private static final String LIMIT_OF_PARTICIPANT_300 = "300";
  private static final String LIMIT_OF_PARTICIPANT_500 = "500";
  private static final String LIMIT_OF_PARTICIPANT_1000 = "1000";

  private final User user;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79786_VerifyThatTheParticularNumberOfParticipantsCanBeChosenInShowNElementsDDL(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void goToParticipantsPage() {
    softAssert = new SoftAssert();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink()
        .waitAllSpinnersAreNotDisplayed();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickReactParticipantsTab()
        .waitParticipantsTableElementsForVisibility();
  }

  @Test
  public void checkThatParticularNumberOfParticipantsCanBeChosenInShowNElementsDDL() {
    assertNumberOfParticipants(LIMIT_OF_PARTICIPANT_10);
    assertNumberOfParticipants(LIMIT_OF_PARTICIPANT_25);
    assertNumberOfParticipants(LIMIT_OF_PARTICIPANT_50);
    assertNumberOfParticipants(LIMIT_OF_PARTICIPANT_100);
    assertNumberOfParticipants(LIMIT_OF_PARTICIPANT_300);
    assertNumberOfParticipants(LIMIT_OF_PARTICIPANT_500);
    assertNumberOfParticipants(LIMIT_OF_PARTICIPANT_1000);
    softAssert.assertAll();
  }

  private void assertNumberOfParticipants(String limitOfParticipants) {
    String actualParticipantsNumber = chooseParticipantsLimitAndCheckAll(limitOfParticipants)
        .getSelectedParticipantsCountText()
        .replace(getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT), EMPTY)
        .trim();
    softAssert.assertEquals(actualParticipantsNumber, limitOfParticipants,
        String.format("Chosen '%s', but found '%s'!", limitOfParticipants,
            actualParticipantsNumber));
  }

  private ReactParticipantsTrainingScreen chooseParticipantsLimitAndCheckAll(
      String limitOfParticipant) {
    return reactParticipantsTrainingScreen.clickPaginationArrowButton()
        .chooseParticipantsLimit(limitOfParticipant)
        .waitSpinnerOfLoadingInvisibility()
        .clickCheckAllCheckbox();
  }
}
