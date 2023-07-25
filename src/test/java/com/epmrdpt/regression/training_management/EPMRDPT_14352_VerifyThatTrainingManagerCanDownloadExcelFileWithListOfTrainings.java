package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_SEARCH_BY_COUNTRY_RUSSIA;
import static com.epmrdpt.framework.ui.AbstractScreen.DEFAULT_TIMEOUT_FOR_PAGE_LOAD;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.nio.file.FileSystems;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_14352_VerifyThatTrainingManagerCanDownloadExcelFileWithListOfTrainings",
    groups = {"manager", "full", "regression"})
public class EPMRDPT_14352_VerifyThatTrainingManagerCanDownloadExcelFileWithListOfTrainings {

  private static String DOWNLOADS_PATH = getDownloadedFilePath();
  private final String trainingListFile =
      DOWNLOADS_PATH + FileSystems.getDefault().getSeparator() + "PlansAndGroups.xlsx";
  private final int timeForWaitingDownloadFile = DEFAULT_TIMEOUT_FOR_PAGE_LOAD * 5;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    deleteFile(trainingListFile);
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByCountry(getValueOf(TRAINING_SEARCH_BY_COUNTRY_RUSSIA));
  }

  @Test
  public void checkThatTrainingManagerCanDownloadExcelFileWithLisOfTraining() {
    new ReactTrainingManagementScreen().clickToExcelButton();
    waitForFileExistence(trainingListFile, timeForWaitingDownloadFile);
    assertTrue(isFileExists(trainingListFile), "List of training isn't downloaded!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteDownloadedFiles() {
    deleteFile(trainingListFile);
  }
}
