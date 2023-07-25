package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactClassesScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactLoginService;
import java.util.List;
import java.util.Optional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84046_VerifyThatAClassCanBeCreatedIfDATrainingIsInFormGroupStatus",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84046_VerifyThatAClassCanBeCreatedIfDATrainingIsInFormGroupStatus {

  private static final String TRAINING_NAME = "AutoTestDepartmentAffiliateFormGroup";
  private Optional<String> addedClassName;
  private ReactClassesScreen reactClassesScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void openDepartmentAffiliateTrainingAsTrainingManager() {
    reactClassesScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .typeTrainingName(TRAINING_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickTrainingNameByName(TRAINING_NAME)
        .waitAllSpinnersAreNotDisplayed()
        .clickClassesTabs();
  }

  @Test
  public void checkThatClassIsCreatedAndDisplayedInListOfClasses() {
    List<String> oldClassNames = reactClassesScreen.getClassNames();
    reactClassesScreen.clickAddClassButton()
        .waitNumberClassNamesToBeMore(oldClassNames.size());
    addedClassName = reactClassesScreen.getClassNames()
        .stream()
        .filter(className -> !oldClassNames.contains(className))
        .findAny();
    assertTrue(addedClassName.isPresent(), "New class is not created!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteAddedClass() {
    addedClassName.ifPresent(className -> reactClassesScreen
        .clickDeleteIconByClassName(className)
        .waitPopUpWindowVisibility()
        .clickPopUpConfirmationButton());
    new ReactLoginService().signOut();
  }
}
