package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_REGISTER_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_SUBSCRIBE_BUTTON;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ReactDetailTrainingPopUpScreen;
import com.epmrdpt.screens.ReactHeaderScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactDetailTrainingService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.time.LocalDate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77628_VerifyThatTimeLimitedTrainingsDisplayInTrainingListOnlyIfTheyHaveStartDateInFuture",
    groups = {"full", "smoke"})
public class EPMRDPT_77628_VerifyThatTimeLimitedTrainingsDisplayInTrainingListOnlyIfTheyHaveStartDateInFuture {

  private static LocalDate currentLocalDate;
  private static LocalDate previousLocalDate;
  private static LocalDate nextLocalDate;

  static {
    currentLocalDate = LocalDate.now();
    previousLocalDate = currentLocalDate.minusDays(1);
    nextLocalDate = currentLocalDate.plusDays(1);
  }

  @DataProvider(name = "Data provider with training names")
  private static Object[][] dataProviderWithTrainingNames() {
    return new Object[][]{
        {"AUTOTEST_77628_PLANNED", "AUTOTEST_77628_OPEN", nextLocalDate},
        {"AUTOTEST_77628_PLANNED", "AUTOTEST_77628_OPEN", currentLocalDate},
        {"AUTOTEST_77628_PLANNED", "AUTOTEST_77628_OPEN", previousLocalDate}
    };
  }

  @Test(dataProvider = "Data provider with training names")
  public void checkPresenceOfTrainingsDependingOnStartDates(
      String trainingPlanned,
      String trainingOpen,
      LocalDate localDate
  ) {
    setStartDateOfExistingTrainingsAsTrainingManager(trainingPlanned, trainingOpen, localDate);

    LoginService loginService = new LoginService();
    loginService
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(withSimplePermission());
    TrainingBlockScreen trainingBlockScreen = new TrainingBlockScreen()
        .waitTrainingCardsVisibility()
        .clickViewMoreTrainingsLink()
        .waitViewMoreTrainingsLinkAbsent()
        .waitTrainingCardsVisibility();

    SoftAssert softAssert = new SoftAssert();
    if (localDate.equals(previousLocalDate) || localDate.equals(currentLocalDate)) {
      softAssert.assertFalse(
          trainingBlockScreen.isTrainingCardByNameDisplayedNoWait(trainingPlanned),
          "The training should not be present!"
      );
      softAssert.assertFalse(
          trainingBlockScreen.isTrainingCardByNameDisplayedNoWait(trainingOpen),
          "The training should not be present!"
      );
    } else {
      softAssert.assertEquals(
          trainingBlockScreen.getTrainingCardButtonText(trainingPlanned),
          LocaleProperties.getValueOf(TRAINING_DESCRIPTION_SUBSCRIBE_BUTTON),
          "The action status of the training is not correct!"
      );
      softAssert.assertEquals(
          trainingBlockScreen.getTrainingCardButtonText(trainingOpen),
          LocaleProperties.getValueOf(TRAINING_DESCRIPTION_REGISTER_BUTTON),
          "The action status of the training is not correct!"
      );
    }
    loginService
        .logout();
    softAssert
        .assertAll();
  }

  private void setStartDateOfExistingTrainingsAsTrainingManager(
      String trainingPlanned,
      String trainingOpen,
      LocalDate localDate
  ) {
    ReactLoginService reactLoginService = new ReactLoginService();
    reactLoginService
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    ReactTrainingManagementService reactTrainingManagementService =
        new ReactTrainingManagementService();
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingPlanned)
        .waitAllSpinnersAreNotDisplayed();
    ReactDetailTrainingService reactDetailTrainingService = new ReactDetailTrainingService();
    reactDetailTrainingService
        .enterStartDate(localDate)
        .clickSaveChangesButton()
        .waitNotificationPopUpVisibility();
    new ReactDetailTrainingPopUpScreen()
        .closeNotificationPopUp();
    new ReactHeaderScreen()
        .clickTrainingManagementLink();
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingOpen);
    reactDetailTrainingService
        .enterStartDate(localDate)
        .clickSaveChangesButton()
        .waitNotificationPopUpVisibility();
    new ReactDetailTrainingPopUpScreen()
        .closeNotificationPopUp();
    reactLoginService
        .signOut();
  }
}
