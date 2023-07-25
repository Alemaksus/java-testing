package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_BARANOVICHI;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CITY_NAME_BELARUS_GOMEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COUNTRY_NAME_BELARUS;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactCreateTrainingScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactDetailTrainingCalendarService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.time.LocalTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13064_VerifyThatTrainingManagerCanEditInformationInEveryFieldInCopiedTraining",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_13064_VerifyThatTrainingManagerCanEditInformationInEveryFieldInCopiedTraining {

  private String copiedTrainingName;
  private final String trainingForCopying = "AutoTest_TrainingForCopy";
  private final String time = LocalTime.now().toString();
  private final String plannedStudentsCount = "5";
  private final String trainingCountryName = getValueOf(COUNTRY_NAME_BELARUS);
  private final String trainingCityName = getValueOf(CITY_NAME_BELARUS_GOMEL);
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;
  private ReactDetailTrainingCalendarService reactDetailTrainingCalendarService;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    reactDetailTrainingCalendarService = new ReactDetailTrainingCalendarService();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
  }

  @Test
  public void checkThatCopiedTrainingCreated() {
    copiedTrainingName = "Copy - " + trainingForCopying + time;
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingForCopying)
        .clickCreateCopyButton()
        .switchToLastWindow();
    new ReactCreateTrainingScreen().waitCreateButtonClickable();
    reactGeneralInfoTabScreen
        .waitDisplayedNameHasText(trainingForCopying)
        .clickOnStartDateInput()
        .isScreenLoaded();
    reactDetailTrainingCalendarService
        .setCurrentDateTraining();
    reactGeneralInfoTabScreen
        .setEndlessCheckBox()
        .typeTrainingName(time)
        .typePlannedStudentsCountField(plannedStudentsCount)
        .setFirstTrainingOwner()
        .setTrainingCountryByName(trainingCountryName)
        .setTrainingCityByName(trainingCityName)
        .waitAllSpinnersAreNotDisplayed()
        .clickCreateButton()
        .waitScreenLoaded();
    assertEquals(copiedTrainingName,
        new ReactDetailTrainingScreen().getTrainingTitleText(),
        "Page hasn't copied training name!");
  }
}
