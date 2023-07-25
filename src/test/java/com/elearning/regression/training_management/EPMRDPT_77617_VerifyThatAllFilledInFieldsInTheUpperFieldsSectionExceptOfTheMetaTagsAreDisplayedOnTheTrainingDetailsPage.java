package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DESCRIPTION_TAB_SCREEN_EXPECTED_FREQUENCY_LABEL;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactDescriptionTabScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description =
    "EPMRDPT_77617_VerifyThatAllFilledInFieldsInTheUpperFieldsSectionExceptOfTheMetaTagsAreDisplayedOnTheTrainingDetailsPage",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_77617_VerifyThatAllFilledInFieldsInTheUpperFieldsSectionExceptOfTheMetaTagsAreDisplayedOnTheTrainingDetailsPage {

  private String trainingName = "Auto_Training_With_All_Text_Blocks_Filled_In";
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactDescriptionTabScreen reactDescriptionTabScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private User user;
  private String expectedFrequencyFromDescriptionTab;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_77617_VerifyThatAllFilledInFieldsInTheUpperFieldsSectionExceptOfTheMetaTagsAreDisplayedOnTheTrainingDetailsPage(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingDescriptionScreen() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(user);
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactDescriptionTabScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickDescriptionTabFromTrainingScreen();
    reactTrainingManagementService.fillAllFieldsInTheUpperFieldsSectionByLocalization();
    reactDescriptionTabScreen.clickDescriptionTabFromTrainingScreen();
    expectedFrequencyFromDescriptionTab = reactDescriptionTabScreen
        .getInputFieldValueByLabelName(
            getValueOf(REACT_DESCRIPTION_TAB_SCREEN_EXPECTED_FREQUENCY_LABEL));
    trainingDescriptionScreen = new ReactDetailTrainingScreen().clickSaveChangesButton()
        .waitNotificationPopUpForACVisibility()
        .clickPreviewButton();
    trainingDescriptionScreen.switchToLastWindow();
  }

  @Test
  public void checkTheFilledInFields() {
    assertEquals(expectedFrequencyFromDescriptionTab,
        trainingDescriptionScreen.getTrainingFrequencyText(),
        "'Expected frequency' field on details page does not match the saved one!");
  }
}
