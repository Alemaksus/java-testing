package com.epmrdpt.regression.participants;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECK_BOX_GROUP;
import static com.epmrdpt.utils.RandomUtils.getRandomNumber;
import static org.testng.Assert.*;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.Random;
import javax.swing.plaf.PanelUI;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79799_VerifyTheAbilityToMoveParticipantsFromOneGroupToAnother",
    groups = {"full", "regression"})
public class EPMRDPT_79799_VerifyTheAbilityToMoveParticipantsFromOneGroupToAnother {

  private final String trainingName = "Classes test";
  private final String studentName = "Anna LastName215";
  private final String groupNumber = String.valueOf(getRandomNumber(2) + 1) ;
  private boolean isReadyToDeclineChanges = false;
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndRedirectToParticipantsPage() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(UserFactory.asTrainingManager());
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading()
        .clickStudentCheckBoxByStudentName(studentName);
  }

  @Test(priority = 1)
  public void checkCogwheelButtonIsEnabled() {
    assertTrue(reactParticipantsTrainingScreen.isCogwheelButtonEnabled(),
        "Cogwheel button is disabled after student's checkbox checked!");
  }

  @Test(priority = 2)
  public void checkListOfOptionsAppears() {
    reactParticipantsTrainingScreen.clickCogwheelButton();
    assertTrue(reactParticipantsTrainingScreen.isCogwheelListOfOptionsDisplayed(),
        "Cogwheel list of options is not displayed!");
  }

  @Test(priority = 3)
  public void checkSuccessfulChangesPopUpIsDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    reactParticipantsTrainingScreen
        .clickMoveApplicantButton()
        .clickGroupByNumber(groupNumber);
    softAssert.assertTrue(reactParticipantsTrainingScreen.isSuccessfulChangesPopUpDisplayed(),
        "Successful changes pop-up is not displayed!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isStatusPopUpCrossButtonDisplayed(),
        "Status pop up cross button is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTheNumberInGroupColumnIsDisplayed() {
    toggleCheckBox();
    isReadyToDeclineChanges = true;
    assertTrue(reactParticipantsTrainingScreen.isStudentsGroupNumberDisplayed(studentName, groupNumber),
        String.format("Group of student %s is not displayed of incorrect", studentName));
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void declineChangesOfConfigureColumns() {
    if (isReadyToDeclineChanges) {
      toggleCheckBox();
    }
  }

  private void toggleCheckBox() {
    new ReactParticipantsService().toggleCheckBoxByName(getValueOf(PARTICIPANTS_CONFIGURE_COLUMNS_TAB_CHECK_BOX_GROUP));
  }
}
