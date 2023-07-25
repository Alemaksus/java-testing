package com.epmrdpt.regression.participants;

import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79707_VerifyTheClickableElementsInTheBreadcrumbs",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_79707_VerifyTheClickableElementsInTheBreadcrumbs {

  private User user;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private ReactTrainingManagementService reactTrainingManagementService;
  private SoftAssert softAssert;
  private String trainingName = "AutoTest_NotificationLanguageVerification";

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_79707_VerifyTheClickableElementsInTheBreadcrumbs(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(user);
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactDetailTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName);
  }

  @Test(priority = 1)
  public void verifyTrainingNameIsNotClickable() {
    assertFalse(reactDetailTrainingScreen.isTrainingTitleClickable(),
        "Training name is clickable!");
  }

  @Test(priority = 2)
  public void verifyPlanningLinkIsClickable() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactDetailTrainingScreen.isPlanningLinkClickable(),
        "Planning link isn't clickable!");
    ReactTrainingManagementScreen reactTrainingManagementScreen = reactDetailTrainingScreen
        .clickPlanningTitle();
    softAssert.assertTrue(reactTrainingManagementScreen.isScreenLoaded(),
        "Planning page isn't loaded!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifyHomeIconIsClickable() {
    ReactParticipantsTrainingScreen reactParticipantsTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitSpinnerOfLoadingInvisibility();
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactParticipantsTrainingScreen.isScreenLoaded(),
        "Participants page isn't loaded!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.isHomeIconClickable(),
        "'Home' icon isn't clickable!");
    softAssert.assertTrue(reactParticipantsTrainingScreen.clickHomeIcon().isScreenLoaded(),
        "Home page isn't loaded after click on 'Home' icon!");
    softAssert.assertAll();
  }
}
