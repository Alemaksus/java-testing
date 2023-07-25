package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14291_VerifyLinkOfAssignmentContainerIsNotCopiedFromTheOriginalTraining",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_14291_VerifyLinkOfAssignmentContainerIsNotCopiedFromTheOriginalTraining {

  private String originalTrainingName = "AUTOTEST WITH AC";
  private String copiedTrainingName = "Copy - AUTOTEST WITH AC";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test(priority = 1)
  public void checkOriginalTrainingContainsAssignmentContainerLink() {
    assertTrue(new ReactTrainingManagementService()
            .searchTrainingByNameAndRedirectOnIt(originalTrainingName)
            .waitScreenLoaded()
            .clickSurveyAndTestingTabFromTrainingScreen()
            .waitScreenLoading()
            .isAssignmentContainerLinkExists(),
        "Original training does not contain assignment container link");
  }

  @Test(priority = 2)
  public void checkTrainingCopyCreation() {
    new ReactDetailTrainingScreen()
        .clickCreateCopyButton()
        .switchToLastWindow();
    assertEquals(copiedTrainingName,
        new ReactGeneralInfoTabScreen()
            .waitScreenLoading()
            .getTrainingNameText(),
        "A copy of the original training has not been created");
  }

  @Test(priority = 3)
  public void checkCopiedTrainingDoesNotContainAssignmentContainerLink() {
    assertFalse(new ReactSurveyAndTestingTabScreen()
            .clickSurveyAndTestingTabFromTrainingScreen()
            .waitScreenLoading()
            .isAssignmentContainerLinkExists(),
        "Copied training contains assignment container link");
  }
}
