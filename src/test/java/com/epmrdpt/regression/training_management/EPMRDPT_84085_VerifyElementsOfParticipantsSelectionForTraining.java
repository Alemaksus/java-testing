package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84085_VerifyElementsOfParticipantsSelectionForTraining",
    groups = {"full", "regression"})
public class EPMRDPT_84085_VerifyElementsOfParticipantsSelectionForTraining {

  private final String trainingName = "AutoTestWithGroupInLearning";
  private final String groupName = "Android 1";
  private final String initialCountOfParticipantsSelected = "0";
  private final String countOfParticipantsAfterOneParticipantSelected = "1";
  private int countOfParticipants;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingGroup() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    reactGroupDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectToGroup(trainingName, groupName)
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test(priority = 1)
  public void checkTextOfParticipantsSelectedSubtitle() {
    assertTrue(
        reactGroupDetailsScreen
            .getParticipantsSelectedSubtitleText()
            .startsWith(getValueOf(PARTICIPANTS_SELECTED_PARTICIPANTS_COUNT)),
        "Text in 'Participants selected' is not correct!"
    );
  }

  @Test(priority = 2)
  public void checkInitialCountOfParticipantsSelected() {
    assertEquals(
        reactGroupDetailsScreen.getSelectedParticipantsCount(),
        initialCountOfParticipantsSelected,
        "Initial count of selected participants is not correct!"
    );
  }

  @Test(priority = 3)
  public void checkIsBulkCheckboxPresent() {
    assertTrue(
        reactGroupDetailsScreen.isSelectAllParticipantsCheckboxPresentNoWait(),
        "Bulk checkbox for participants is not present!"
    );
  }

  @Test(priority = 4)
  public void checkCountOfStudentsCheckboxes() {
    countOfParticipants =
        reactGroupDetailsScreen.getStudentsCountInStudentInfoSection();
    assertEquals(
        reactGroupDetailsScreen.getParticipantsCheckboxesCount(),
        countOfParticipants,
        "Participants checkboxes count is not correct!"
    );
  }

  @Test(priority = 5)
  public void checkCountOfParticipantsAfterClickOnFirstParticipantCheckbox() {
    reactGroupDetailsScreen
        .clickFirstParticipantCheckbox();
    assertEquals(
        reactGroupDetailsScreen.getSelectedParticipantsCount(),
        countOfParticipantsAfterOneParticipantSelected,
        "After click on first checkbox, count of participants is not correct!"
    );
  }

  @Test(priority = 6)
  public void checkCountOfParticipantsAfterClickOnBulkCheckbox() {
    reactGroupDetailsScreen
        .clickCheckBoxAllStudentsInParticipants();
    assertEquals(
        reactGroupDetailsScreen.getSelectedParticipantsCount(),
        Integer.toString(countOfParticipants),
        "After click on bulk checkbox, count of participants is not correct!"
    );
  }
}
