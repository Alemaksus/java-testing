package com.epmrdpt.smoke.traininglist;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_56108_VerifyTheTrainingManagerCanForbidSelfRegistrationToTheTrainingOnTheTrainingDetailsPage",
    groups = {"smoke", "full"})

public class EPMRDPT_56108_VerifyTheTrainingManagerCanForbidSelfRegistrationToTheTrainingOnTheTrainingDetailsPage {

  private static final String PLANNED_TEST = "DubLinkCheck";
  private HeaderScreen headerScreen;
  private TrainingListScreen trainingListScreen;
  private SoftAssert softAssert;
  private final User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_56108_VerifyTheTrainingManagerCanForbidSelfRegistrationToTheTrainingOnTheTrainingDetailsPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void login() {
    headerScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
  }

  @Test(priority = 1)
  public void checkPlannedTrainingCard() {
    softAssert = new SoftAssert();
    trainingListScreen = headerScreen.clickTrainingListNavigationLink()
        .clickPlannedButton()
        .waitTrainingCardsForVisibility();
    clickCloseLocationSearchIfSearchFieldPresent();
    softAssert.assertTrue(trainingListScreen.isTrainingPresentByName(PLANNED_TEST),
        "The required training is not displayed!");
    softAssert.assertTrue(trainingListScreen.isSubscribePresent(PLANNED_TEST),
        "The 'Subscribe' Button is not displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkPlannedTrainingDetailsPage() {
    TrainingDescriptionScreen trainingDescriptionScreen
        = trainingListScreen.chooseTrainingCardByName(PLANNED_TEST);
    assertTrue(trainingDescriptionScreen.isSubscribeButtonPresent(),
        "'Subscribe' Button is not displayed!");
  }

  private void clickCloseLocationSearchIfSearchFieldPresent() {
    if (trainingListScreen.isLocationSearchPresent()) {
      trainingListScreen.clickLocationSearchClose();
    }
  }
}
