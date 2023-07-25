package com.epmrdpt.smoke.training_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_79686_VerifyTheAbilityToSelectOneParticipant",
    groups = {"full", "smoke"})
public class EPMRDPT_79686_VerifyTheAbilityToSelectOneParticipant {

  private static final String TRAINING_NAME = "Auto-assessment Self-study";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void chooseTraining() {
    ReactTrainingManagementScreen reactTrainingManagementScreen
     = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitAllTrainingNameDisplayed()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .waitSearchResultsLabelIsDisplayed();
    reactDetailTrainingScreen = reactTrainingManagementScreen
        .clickTrainingNameByName(TRAINING_NAME);
  }

  @Test
  public void checkCheckBoxTicked() {
    reactParticipantsTrainingScreen = reactDetailTrainingScreen.clickReactParticipantsTab()
        .clickFirstStudentCheckBox();
    assertTrue(reactParticipantsTrainingScreen.isFirstStudentCheckboxChecked(),
        "The checkbox is not ticked");
  }

  @Test
  public void checkCounterMessage() {
    assertEquals(reactParticipantsTrainingScreen.getSearchResultText(),
        getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT) + " " +
            reactParticipantsTrainingScreen.getNumberOfSelectedParticipantsFromLabel(),
        "Counter above list table contains wrong text");
  }
}
