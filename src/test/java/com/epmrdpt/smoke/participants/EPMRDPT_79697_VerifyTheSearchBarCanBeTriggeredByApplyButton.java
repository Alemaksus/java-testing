package com.epmrdpt.smoke.participants;

import static com.epmrdpt.framework.properties.UserProperty.getValueOf;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.UserPropertyConst;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.screens.ReactStudentCardPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_79697_VerifyTheSearchBarCanBeTriggeredByApplyButton",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_79697_VerifyTheSearchBarCanBeTriggeredByApplyButton {

  private User user;
  private final String trainingName = "AUTOTEST WITH AC";
  private final String studentEmail = getValueOf(UserPropertyConst.USER_LOGIN);
  private final String studentName = "QQ AA";
  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79697_VerifyTheSearchBarCanBeTriggeredByApplyButton(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(user);
    reactParticipantsTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitScreenLoading();
  }

  @Test
  public void checkTheResultDisplayedOfSearchBarAfterClickingApplyButton() {
    ReactStudentCardPopUpScreen reactStudentCardPopUpScreen = reactParticipantsTrainingScreen
        .clickSearchByNameInput()
        .typeSearchByNameInput(studentEmail)
        .clickApplyButton()
        .waitScreenLoading()
        .clickStudentByName(studentName);
    assertEquals(reactStudentCardPopUpScreen.getEmailAddressText(), studentEmail,
        "The email of student is incorrect!");
  }
}
