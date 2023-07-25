package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.bo.user.UserFactory.asSuperAdmin;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_LOCATION_POPUP_NOTIFICATION;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.RegistrationWizardScreen;
import com.epmrdpt.screens.TrainingListScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_97306_VerifyElementsOfThePopUp",
    groups = {"full", "regression"})
public class EPMRDPT_97306_VerifyElementsOfThePopUp {

  private final String TRAINING_NAME = "Test Automation (TAT22)";
  private RegistrationWizardScreen registrationWizardScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void signInAsUserWithLocation() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asSuperAdmin());
  }

  @Test(priority = 1)
  public void checkPopUpFromHomePage() {
    new TrainingCardsSectionService().clearAllFilters();
    registrationWizardScreen = new TrainingListScreen()
        .clickViewMoreTrainings()
        .clickOnRegistrationButtonInTrainingCard(TRAINING_NAME);
    assertEquals(registrationWizardScreen.getTextFromPopUp(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_LOCATION_POPUP_NOTIFICATION),
        "Location error message on home page is missing");
  }

  @Test(priority = 2)
  public void checkPopUpFromTrainingListPage() {
    clearFiltersAndExpandTrainingList()
        .clickOnRegistrationButtonInTrainingCard(TRAINING_NAME);
    assertEquals(registrationWizardScreen.getTextFromPopUp(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_LOCATION_POPUP_NOTIFICATION),
        "Location error message on training list page is missing");
  }

  @Test(priority = 3)
  public void checkPopUpFromTrainingDetailsPage() {
    clearFiltersAndExpandTrainingList()
        .chooseTrainingCardByName(TRAINING_NAME)
        .waitRegisterButtonVisibility()
        .clickRegisterButton();
    assertEquals(registrationWizardScreen.getTextFromPopUp(),
        LocaleProperties.getValueOf(REGISTRATION_WIZARD_SCREEN_LOCATION_POPUP_NOTIFICATION),
        "Location error message on training details page is missing");
  }

  @AfterMethod
  public void closePopUpWindow() {
    registrationWizardScreen.clickOkButtonInPopUpWindow();
  }

  private TrainingListScreen clearFiltersAndExpandTrainingList() {
    new HeaderScreen().waitTrainingListNavigationLinkVisibility().clickTrainingListNavigationLink()
        .waitBannerTitleVisibility();
    new TrainingCardsSectionService().clearAllFilters();
    return new TrainingListScreen().clickViewMoreTrainings()
        .waitTrainingCardsForVisibility();
  }
}
