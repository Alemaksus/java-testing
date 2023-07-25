package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_94085_VerifyTheAbilityToFilterParticipantsAccordingToMultipleEmails",
    groups = {"full", "regression"})
public class EPMRDPT_94085_VerifyTheAbilityToFilterParticipantsAccordingToMultipleEmails {

  private final static String TRAINING_NAME = "AutoTest_WithGroupAndPatricipantInProgress";
  private final static String TEST_EMAIL_1 = "dub.registereduser@yandex.by";
  private final static String TEST_EMAIL_2 = "hbuserstudent@gmail.com";
  private final static int EMAIL_COLUMN_NUMBER = 10;
  private final static String SEARCH_PHRASE_WITH_MULTIPLE_EMAILS = String.format("%s %s",
      TEST_EMAIL_1, TEST_EMAIL_2);
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToParticipantsPageAddEmailsColumnScrollToEnd() {
    reactParticipantsTrainingScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitAllTrainingNameDisplayed()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .clickTrainingNameByName(TRAINING_NAME)
        .clickReactParticipantsTab()
        .clickEditButton()
        .clickCheckboxFromConfigurationPopUp(EMAIL_COLUMN_NUMBER)
        .clickApplyPopUpScreenButton()
        .waitScreenLoading()
        .clickAfterScrollBarToTheEnd();
  }

  @Test
  public void checkThatParticipantAreFilteredAccordingToTheSpecifiedEmails() {
    new ReactParticipantsService()
        .findParticipantBySearchPhrase(SEARCH_PHRASE_WITH_MULTIPLE_EMAILS)
        .clickApplyButton();
    assertThat("Participants are not filtered according to the specified emails",
        reactParticipantsTrainingScreen.getSearchResultStudentsEmails(),
        containsInAnyOrder(TEST_EMAIL_1, TEST_EMAIL_2));
  }
}
