package com.epmrdpt.smoke.profile;

import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingInfoTabOnProfilePageScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_28149_VerifyTheContentOfTrainingInfoTabOnProfilePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_28149_VerifyTheContentOfTrainingInfoTabOnProfilePage {

  private HeaderScreen headerScreen;
  private SoftAssert softAssert;
  private TrainingInfoTabOnProfilePageScreen trainingInfoTabOnProfilePageScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new HeaderScreen();
    trainingInfoTabOnProfilePageScreen = new TrainingInfoTabOnProfilePageScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asLearningStudent());
  }

  @Test(priority = 1)
  public void checkThatTrainingTabInfoDisplayed() {
    headerScreen.clickProfileMenu()
        .waitDropDownDisplayed()
        .clickProfileButton()
        .clickTrainingInfo();
    assertTrue(trainingInfoTabOnProfilePageScreen.isScreenLoaded(),
        "Training tan info isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatStudentGroupTabContainMainElements() {
    softAssert = new SoftAssert();
    trainingInfoTabOnProfilePageScreen.clickStudentGroupTab();
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isSearchBarDisplayed(),
        "Search bar isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isFindButtonDisplayed(),
        "'Find' button isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isGroupNameHeaderDisplayed(),
        "Group name header isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isTrainingNameHeaderDisplayed(),
        "Training name header isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isGroupStatusHeaderDisplayed(),
        "Group status header isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isLearningPeriodDisplayed(),
        "Learning period isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isStudentStatusHeaderOnStudentGroupTabDisplayed(),
        "Student's status header isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatApplicationTabContainMainElements() {
    softAssert = new SoftAssert();
    trainingInfoTabOnProfilePageScreen.waitApplicationHeaderDisplayed().clickApplicationTab();
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isSearchBarDisplayed(),
        "Search bar isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isFindButtonDisplayed(),
        "'Find' button isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isTrainingNameHeaderDisplayed(),
        "Training name header isn't displayed!");
    softAssert
        .assertTrue(trainingInfoTabOnProfilePageScreen.isStudentStatusOnApplicationTabDisplayed(),
            "Student status header isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isRegistrationDateHeaderDisplayed(),
        "Registration date header isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isContactPersonHeaderDisplayed(),
        "Contact person header isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatCurrentTrainingTabContainMainElements() {
    softAssert = new SoftAssert();
    trainingInfoTabOnProfilePageScreen.clickCurrentTestingTab();
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isSearchBarDisplayed(),
        "Search bar isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isFindButtonDisplayed(),
        "'Find' button isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isTrainingNameHeaderDisplayed(),
        "Training name header isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen.isDeadlineHeaderDisplayed(),
        "Deadline header isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatPaginationDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen
            .clickApplicationTab()
            .isPaginationBlockDisplayed(),
        "Pagination block on 'Application Tab' isn't displayed!");
    softAssert.assertTrue(trainingInfoTabOnProfilePageScreen
            .clickStudentGroupTab()
            .isPaginationBlockDisplayed(),
        "Pagination block on 'Student's Group Tab' isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkThatTrainingHasAllDescriptionItemOnStudentGroupTab() {
    softAssert = new SoftAssert();
    trainingInfoTabOnProfilePageScreen.clickStudentGroupTab();
    for (int trainingIndex = 0;
        trainingIndex < trainingInfoTabOnProfilePageScreen.getQuantityTrainingOnTab();
        trainingIndex++) {
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isTrainingNameDisplayedByIndex(trainingIndex),
          format("Training name %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isGroupNameDisplayedByIndex(trainingIndex),
          format("Group name %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isGroupStatusDisplayedByIndex(trainingIndex),
          format("Group status %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isLearningPeriodDisplayedByIndex(trainingIndex),
          format("Learning period %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isStudentStatusDisplayedByIndex(trainingIndex),
          format("Student status %d isn't displayed!", trainingIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkThatTrainingHasAllDescriptionItemOnApplicationTab() {
    softAssert = new SoftAssert();
    trainingInfoTabOnProfilePageScreen.clickApplicationTab();
    trainingInfoTabOnProfilePageScreen.clickTrainingNameHeaderElement();
    for (int trainingIndex = 0;
        trainingIndex < trainingInfoTabOnProfilePageScreen.getQuantityTrainingOnTab();
        trainingIndex++) {
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isTrainingNameDisplayedByIndex(trainingIndex),
          format("Training name %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isStudentStatusApplicationTabDisplayedByIndex(trainingIndex),
          format("Status name %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isRegistrationDateDisplayedByIndex(trainingIndex),
          format("Registration date  %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isContactPersonDisplayedByIndex(trainingIndex),
          format("Contact person  %d isn't displayed!", trainingIndex + 1));
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkThatTrainingHasAllDescriptionItemOnCurrentTestingTab() {
    softAssert = new SoftAssert();
    trainingInfoTabOnProfilePageScreen.clickCurrentTestingTab();
    for (int trainingIndex = 0;
        trainingIndex < trainingInfoTabOnProfilePageScreen.getQuantityTrainingOnTab();
        trainingIndex++) {
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isTrainingNameDisplayedByIndex(trainingIndex),
          format("Training name %d isn't displayed!", trainingIndex + 1));
      softAssert.assertTrue(
          trainingInfoTabOnProfilePageScreen.isDeadLineDisplayedByIndex(trainingIndex),
          format("Deadline %d isn't displayed!", trainingIndex + 1));
    }
    softAssert.assertTrue(
        trainingInfoTabOnProfilePageScreen.isGoToTestButtonDisplayedByIndex(0),
        "Go to test button isn't displayed!");
    softAssert.assertAll();
  }
}
