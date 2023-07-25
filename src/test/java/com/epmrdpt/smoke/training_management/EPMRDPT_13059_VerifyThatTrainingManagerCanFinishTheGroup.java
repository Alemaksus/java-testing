package com.epmrdpt.smoke.training_management;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13059_VerifyThatTrainingManagerCanFinishTheGroup",
    groups = {"full", "manager", "smoke", "deprecated"})
public class EPMRDPT_13059_VerifyThatTrainingManagerCanFinishTheGroup {

  private final String TRAINING_NAME = "Auto Test";
  private final String GROUP_NAME = "Change_Learning_Finished";
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactGroupScreen reactGroupScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupScreen = new ReactGroupScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
        UserFactory.asTrainingManager()).clickReactTrainingManagementLink();
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(TRAINING_NAME);
  }

  @Test(priority = 1)
  public void checkThatDetailsPageLoaded() {
    assertEquals(reactTrainingManagementService.searchReactGroupByNameAndRedirectOnIt(GROUP_NAME)
            .waitGroupNameTextToBePresent().getGroupNameText(), GROUP_NAME,
        format("Group '%s' isn't loaded!", GROUP_NAME));
  }

  @Test(priority = 2)
  public void checkThatManagerCanSetFinishedForGroup() {
    reactTrainingManagementService.changeGroupStatusOnFinished();
    assertEquals(reactGroupScreen.getGroupStatusByName(GROUP_NAME),
        LocaleProperties.getValueOf(LocalePropertyConst.TRAINING_MANAGER_STATUS_FINISHED),
        format("Group '%s' didn't change the status 'finished'", GROUP_NAME));
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void changeGroupStatusBack(Method method) {
    if (method.getName().equals("checkThatManagerCanSetFinishedForGroup")) {
      reactGroupScreen
          .clickGroupByName(GROUP_NAME)
          .waitAllSpinnersAreNotDisplayed();
      reactTrainingManagementService.changeGroupStatusOnLearning();
    }
  }
}
