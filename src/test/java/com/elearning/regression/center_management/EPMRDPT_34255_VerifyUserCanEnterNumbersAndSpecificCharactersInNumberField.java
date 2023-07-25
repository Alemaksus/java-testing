package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.EnvironmentProperty.getEnv;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ParticipantsTrainingScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34255_VerifyUserCanEnterNumbersAndSpecificCharactersInNumberField",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34255_VerifyUserCanEnterNumbersAndSpecificCharactersInNumberField {

  private User user;
  private SoftAssert softAssert;
  private String countryOfTheTrainingCenter = "AutoTestCountry";
  private String cityOfTheTrainingCenter = "AutoTestCity";
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;
  private char[] correctCharacters = {'+', '-', '%', '1'};
  private char incorrectCharacter = 'q';

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34255_VerifyUserCanEnterNumbersAndSpecificCharactersInNumberField(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupEditTrainingCenter() {
    setupTrainingCenterManagement();
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(countryOfTheTrainingCenter, cityOfTheTrainingCenter);
    createOrEditTrainingCenterScreen = trainingCenterManagementScreen
        .clickTrainingCenterMenuButton(cityOfTheTrainingCenter)
        .selectEditTheTrainingCenterButtonDropdownMenu()
        .waitEditAreaForVisibility();
  }

  @Test(priority = 1)
  public void checkCorrectCharactersDoNotTriggerWarningHintInTheExistingTraining() {
    softAssert = new SoftAssert();
    for (int i = 0; i < correctCharacters.length; i++) {
      createOrEditTrainingCenterScreen.fillInNumberField(correctCharacters[i]);
      softAssert.assertFalse(createOrEditTrainingCenterScreen.isWarningHintDisplayed(),
          "A warning hint has appeared in the existing training!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkIncorrectCharacterTriggersWarningHintInTheExistingTraining() {
    createOrEditTrainingCenterScreen.fillInNumberField(incorrectCharacter);
    assertTrue(createOrEditTrainingCenterScreen.isWarningHintDisplayed(),
        "The warning hint did not appear in the existing training!");
  }

  @Test(priority = 3)
  public void checkCorrectCharactersDoNotTriggerWarningHintInTheNewTraining() {
    setupTrainingCenterManagement();
    createOrEditTrainingCenterScreen = trainingCenterManagementScreen
        .clickCreateTrainingCenterButton()
        .waitEditAreaForVisibility();
    softAssert = new SoftAssert();
    for (int i = 0; i < correctCharacters.length; i++) {
      createOrEditTrainingCenterScreen.fillInNumberField(correctCharacters[i]);
      softAssert.assertFalse(createOrEditTrainingCenterScreen.isWarningHintDisplayed(),
          "A warning hint has appeared in the new training!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkIncorrectCharacterTriggersWarningHintInTheNewTraining() {
    createOrEditTrainingCenterScreen.fillInNumberField(incorrectCharacter);
    assertTrue(createOrEditTrainingCenterScreen.isWarningHintDisplayed(),
        "The warning hint did not appear in the new training!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void reopenBrowser(Method method) {
    if (method.getName()
        .equals("checkIncorrectCharacterTriggersWarningHintInTheExistingTraining")) {
      new ParticipantsTrainingScreen().closeBrowser();
      new HeaderScreen().openPage(getEnv());
    }
  }

  private void setupTrainingCenterManagement() {
    trainingCenterManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
  }
}
