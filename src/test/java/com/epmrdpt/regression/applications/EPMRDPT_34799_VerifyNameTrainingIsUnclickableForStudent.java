package com.epmrdpt.regression.applications;

import static java.lang.String.format;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34799_VerifyNameTrainingIsUnclickableForStudent",
    groups = {"full", "regression", "student"})
public class EPMRDPT_34799_VerifyNameTrainingIsUnclickableForStudent {

  private final String trainingName = "AutoTestDA";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withDepartmentTraining())
        .waitProfileMenuDisplayed()
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickApplicationsButton()
        .waitScreenLoading();
  }

  @Test
  public void checkNameTrainingDAIsUnclickbaleForStudent() {
    assertFalse(new ApplicationsScreen().isTrainingNameButtonClickableByTrainingName(trainingName),
        format("%s button is clickable!", trainingName));
  }
}
