package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactClassesDetailsScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84050_VerifyThatSelectedGroupsAreAddedToClassViaAddButtonInDATraining",
    groups = {"full", "regression"})
public class EPMRDPT_84050_VerifyThatSelectedGroupsAreAddedToClassViaAddButtonInDATraining {

  private static final String TRAINING_NAME = "AutoTestDepartmentAffiliateFormGroup";
  private static final Integer FIRST_CLASS_BY_INDEX = 0;
  private boolean isNeedToDelete = false;
  private ReactClassesDetailsScreen reactClassesDetailScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void openDepartmentAffiliateTrainingAsTrainingManager() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded();
    new ReactTrainingManagementService()
        .searchTrainingByName(TRAINING_NAME)
        .clickTrainingNameByName(TRAINING_NAME);
    reactClassesDetailScreen = new ReactDetailTrainingScreen()
        .clickClassesTabs()
        .clickClassByIndex(FIRST_CLASS_BY_INDEX);
  }

  @Test(priority = 1)
  public void checkThatChosenGroupsArePreselected() {
    assertTrue(reactClassesDetailScreen
            .clickParticipantsInputPlaceholder()
            .clickParticipantsSelectAllOption()
            .isParticipantsStudentGroupPreselected(),
        "Participants student groups are not preselected!");
  }

  @Test(priority = 2)
  public void checkThatChosenGroupsAreAddedToTheStudentGroupsList() {
    isNeedToDelete = true;
    boolean isParticularGroupsAreAddedToListTable = true;
    List<String> addedStudentGroupNames = reactClassesDetailScreen.getStudentGroupsNamesFromSelectStudentGroupDDL();
    reactClassesDetailScreen.clickAddButton();
    List<String> extendedStudentGroupNames = reactClassesDetailScreen.getStudentGroupsNamesFromList();
    for(String addedStudentGroupName:addedStudentGroupNames){
      if(!extendedStudentGroupNames.contains(addedStudentGroupName)){
        isParticularGroupsAreAddedToListTable =  false;
        break;
      }
    }
    assertTrue(isParticularGroupsAreAddedToListTable,
        "Particular groups are not added  to the list table!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteAllClassesFromList() {
    if(isNeedToDelete) {
      reactClassesDetailScreen
          .clickSelectAllStudentGroupsCheckbox()
          .waitSettingsButtonToChangeColor()
          .clickSettingsButton()
          .clickDeleteButton()
          .clickPopUpDeleteButton();
    }
  }
}
