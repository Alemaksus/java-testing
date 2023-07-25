package com.epmrdpt.regression.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;

import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_102792_VerifyThatEnglishLevelDDLContainsEnglishLevelValuesThatCandidatesHaveInThisTraining",
    groups = {"full", "regression"})
public class EPMRDPT_102792_VerifyThatEnglishLevelDDLContainsEnglishLevelValuesThatCandidatesHaveInThisTraining {

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private ReactParticipantsService reactParticipantsService;
  private final String trainingName = "C++ Gomel";
  private List<String> englishLevelList;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {

    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading()
        .clickEditButton()
        .waitConfigureColumnsTabHeaderVisibility()
        .clickConfiguresColumnsTabCheckAllButton()
        .clickConfigureColumnsTabApplyButton()
        .clickEnglishLevelColumn()
        .clickEnglishLevelColumn();
    reactParticipantsService = new ReactParticipantsService();
    englishLevelList = reactParticipantsService.getEnglishLevelListFromTableMenu();
  }

  @Test
    public void verifyThatEnglishLevelDDLContainsEnglishLevelValuesThatCandidatesHaveInThisTraining() {
      SoftAssert softAssert = new SoftAssert();
      for (int englishLevelIndex = 0; englishLevelIndex < englishLevelList.size(); englishLevelIndex++) {
        reactParticipantsService.setEnglishLevelInWindowByIndex(englishLevelIndex + 1);
        softAssert.assertEquals(String.join(" ", englishLevelList.get(englishLevelIndex).replaceAll("\\s*\\(.*\\)", "")),
            reactParticipantsTrainingScreen.getEnglishlevelForFirstStudentText(),
            "The English level does not match !");
      }
    softAssert.assertAll();
    }
}
