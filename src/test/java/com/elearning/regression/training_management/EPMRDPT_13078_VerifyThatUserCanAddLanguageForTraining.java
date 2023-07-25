package com.epmrdpt.regression.training_management;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13078_VerifyThatUserCanAddLanguageForTraining",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_13078_VerifyThatUserCanAddLanguageForTraining {

  private static final String TRAINING_NAME = "Auto Test";
  private String inputTextForEnglishLocalization = "Displaying name";
  private String inputTextForRussianLocalization = "Отображаемое имя";

  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    reactTrainingManagementService = new ReactTrainingManagementService();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
    reactTrainingManagementService.searchTrainingByNameAndRedirectOnIt(TRAINING_NAME);
    reactTrainingManagementService.deleteRussianLocalizationIfPresent();
    reactDetailTrainingScreen = new ReactDetailTrainingScreen()
        .clickSelectLanguageButton()
        .clickRussianLanguageButton();
    reactGeneralInfoTabScreen
        .typeInDisplayingNameInputAfterCleanInput(inputTextForRussianLocalization);
    reactDetailTrainingScreen.clickByScrollingEnglishLanguageButton();
    reactGeneralInfoTabScreen
        .typeInDisplayingNameInputAfterCleanInput(inputTextForEnglishLocalization);
    reactGeneralInfoTabScreen.waitScreenLoading();
    reactDetailTrainingScreen.clickSaveChangesButton();
  }

  @Test(priority = 1)
  public void checkThatTheChangesAreSaved() {
    assertTrue(reactDetailTrainingScreen.isConfirmationOfSavingChangesIsDisplayed(),
        "The changes made were not saved!");
  }

  @Test(priority = 1)
  public void checkUserCanAddAnotherLanguageBesidesEnglishForTraining() {
    assertTrue(reactDetailTrainingScreen.isRussianLanguageFromDDLButtonIsDisplayed(),
        "User can't add Russian language as another language besides English!");
  }

  @Test(priority = 2)
  public void checkThatFieldIsFilledInEnglishLocalization() {
    reactDetailTrainingScreen.clickEnglishLanguageButton();
    assertEquals(reactGeneralInfoTabScreen.getTextFromDisplayingNameInput(),
        inputTextForEnglishLocalization,
        "Field is not filled in English localization!");
  }

  @Test(priority = 3)
  public void checkThatFieldIsFilledInRussianLocalization() {
    reactDetailTrainingScreen.clickRussianLanguageButton();
    assertEquals(reactGeneralInfoTabScreen.getTextFromDisplayingNameInput(),
        inputTextForRussianLocalization,
        "Field is not filled in Russian localization!");
  }
}
