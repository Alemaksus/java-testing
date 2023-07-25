package com.epmrdpt.regression.training;

import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getDownloadedFilePath;
import static com.epmrdpt.utils.FileUtils.getFileDelimiter;
import static com.epmrdpt.utils.FileUtils.isFileExists;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_86398_VerifyThatSectionAboutPaidDetailsIsDisplayed",
    groups = {"full", "regression"})
public class EPMRDPT_86398_VerifyThatSectionAboutPaidDetailsIsDisplayed {

  private static String DOWNLOADS_PATH = getDownloadedFilePath();
  private static String FILE_DELIMITER = getFileDelimiter();

  private final String trainingName = "Andro";
  private final String trainingPrice = "500";
  private final String trainingCurrency = "USD";
  private final String trainingValueAddedTax = "20";
  private final String trainingPublicOfferFileName = "3.pdf";
  private final String fullPathToDownloadedFile =
      DOWNLOADS_PATH + FILE_DELIMITER + trainingPublicOfferFileName;
  private User user;
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_86398_VerifyThatSectionAboutPaidDetailsIsDisplayed(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTraining() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(user);
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName);
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
  }

  @Test(priority = 1)
  public void checkTrainingPrice() {
    reactGeneralInfoTabScreen.waitTrainingPriceForVisibility();
    assertEquals(
        reactGeneralInfoTabScreen.getTrainingPriceInputFieldValue(),
        trainingPrice,
        "Paid training's price is not correct!"
    );
  }

  @Test(priority = 2)
  public void checkTrainingCurrency() {
    reactGeneralInfoTabScreen.waitTrainingCurrencyForVisibility();
    assertEquals(
        reactGeneralInfoTabScreen.getTrainingCurrencyInputFieldValue(),
        trainingCurrency,
        "Paid training's currency is not correct!"
    );
  }

  @Test(priority = 3)
  public void checkValueAddedTax() {
    reactGeneralInfoTabScreen.waitTrainingValueAddedTaxForVisibility();
    assertEquals(
        reactGeneralInfoTabScreen.getTrainingValueAddedTaxInputFieldValue(),
        trainingValueAddedTax,
        "Paid training's value added tax is not correct!"
    );
  }

  @Test(priority = 4)
  public void checkPublicOfferFileName() {
    assertTrue(
        reactGeneralInfoTabScreen
            .isTrainingPublicOfferFileNameDisplayed(trainingPublicOfferFileName),
        "Paid training's public offer file is not displayed!"
    );
  }

  @Test(priority = 5)
  public void checkThatPublicOfferFileHasBeenDownloaded() {
    reactGeneralInfoTabScreen
        .clickTrainingPublicOfferDownloadButton(trainingPublicOfferFileName);
    waitForFileExistence(fullPathToDownloadedFile);
    assertTrue(
        isFileExists(fullPathToDownloadedFile),
        "Public offer file has not been downloaded!"
    );
  }

  @AfterClass
  public void deleteDownloadedFile() {
    deleteFile(fullPathToDownloadedFile);
  }
}
