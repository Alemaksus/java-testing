package com.epmrdpt.smoke.traininglist;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


@Test(description = "EPMRDPT_71983_VerifyThatCopiedTrainingIsDisplayed",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_71983_VerifyThatCopiedTrainingIsDisplayed {

  private final User user;
  private final String trainingName = "AUTOTEST WITH AC";
  private final String copiedTrainingName = "Copy - " + trainingName;
  private ReactTrainingManagementService reactTrainingManagementService;
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @Factory(dataProvider = "Provider of users with Reports tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_71983_VerifyThatCopiedTrainingIsDisplayed(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactTrainingManagementService = new ReactTrainingManagementService();
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
  }

  @Test()
  public void checkTrainingIsCopied() {
    reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName);
    List<String> originalTrainingData = createListOfDataTrainingFields();
    new ReactDetailTrainingScreen().clickCreateCopyButton()
        .switchToLastWindow();
    List<String> copiedTrainingData = createListOfDataTrainingFields();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactGeneralInfoTabScreen.getTrainingNameText(), copiedTrainingName,
        "Name of the copied training does not match with this name " + copiedTrainingName);
    softAssert.assertEquals(reactGeneralInfoTabScreen.getTextFromDisplayingNameInput(),
        trainingName,
        "Name of the displaying name does not match with this name " + trainingName);
    for (int i = 0; i < originalTrainingData.size(); i++) {
      softAssert.assertEquals(copiedTrainingData.get(i), originalTrainingData.get(i),
          originalTrainingData.get(i) + " is not the same with " + copiedTrainingData.get(i));
    }
    softAssert.assertAll();
  }

  private List<String> createListOfDataTrainingFields() {
    List<String> dataFields = new ArrayList<>();
    dataFields.add(reactGeneralInfoTabScreen.getTrainingTypeValue());
    dataFields.add(reactGeneralInfoTabScreen.getTrainingFormatValue());
    dataFields.add(reactGeneralInfoTabScreen.getTrainingSkillValue());
    dataFields.add(reactGeneralInfoTabScreen.getTrainingEnrollmentTypeValue());
    dataFields.add(reactGeneralInfoTabScreen.getTrainingPricingValue());
    dataFields.add(reactGeneralInfoTabScreen.getTrainingProgramLevelValue());
    return dataFields;
  }
}
