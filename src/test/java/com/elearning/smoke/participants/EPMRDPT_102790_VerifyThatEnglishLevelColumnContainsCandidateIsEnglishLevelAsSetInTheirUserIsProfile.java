package com.epmrdpt.smoke.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_102790_VerifyThatEnglishLevelColumnContainsCandidateIsEnglishLevelAsSetInTheirUserIsProfile",
    groups = {"full", "smoke"})
public class EPMRDPT_102790_VerifyThatEnglishLevelColumnContainsCandidateIsEnglishLevelAsSetInTheirUserIsProfile {

  private final String trainingName = "C++ Gomel";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

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

  @Test
  public void verifyThatEnglishLevelColumnContainsCandidateIsEnglishLevelAsSetInTheirUserIsProfile() {
    reactParticipantsTrainingScreen.clickEnglishTestColumn()
        .clickEnglishTestColumn();
    assertEquals(reactParticipantsTrainingScreen.getEnglishResultForFirstStudenText(),
        reactParticipantsTrainingScreen.getEnglishTestResultInUserProfileText(),
        "The results of the English test are different!");
  }
}
