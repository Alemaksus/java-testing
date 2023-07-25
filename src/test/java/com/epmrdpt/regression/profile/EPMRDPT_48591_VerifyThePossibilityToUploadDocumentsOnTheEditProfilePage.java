package com.epmrdpt.regression.profile;

import static com.epmrdpt.bo.user.UserFactory.withSimplePermission;
import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getImportDocumentFolderPath;
import static com.epmrdpt.utils.FileUtils.getNewFile;

import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import java.nio.file.Paths;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_48591_VerifyThePossibilityToUploadDocumentsOnTheEditProfilePage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_48591_VerifyThePossibilityToUploadDocumentsOnTheEditProfilePage {

  private final int permissibleResumeUploadCount = 3;
  private ProfileScreen profileScreen;
  private ProfileEditionScreen profileEditionScreen;
  private SoftAssert softAssert;

  private final String RESUME_FILE_PATH = String
      .format("%s%s%s", getImportDocumentFolderPath(), System.currentTimeMillis(), ".pdf");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    profileScreen = new ProfileScreen();
    profileEditionScreen = new ProfileEditionScreen();
    getNewFile(RESUME_FILE_PATH);
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(withSimplePermission())
        .clickProfileMenu()
        .clickProfileButton()
        .waitEditProfileLinkDisplayed()
        .clickEditProfileLink()
        .waitScreenLoading();
  }

  @BeforeMethod(inheritGroups = false, alwaysRun = true)
  private void removeLastResumeOnExceedingMaxLimit() {
    if (profileEditionScreen.getExistingResumesCount() == permissibleResumeUploadCount) {
      profileEditionScreen.clickLastRemoveResumeButton()
          .clickOkButtonInDeleteResumePopup();
    }
  }

  @Test
  public void checkResumeUpload() {
    softAssert = new SoftAssert();
    profileEditionScreen.uploadResume(RESUME_FILE_PATH);
    profileEditionScreen.clickSaveProfileButton()
        .waitSpinnerOfLoadingInvisibility()
        .waitForProfileScreenCompletelyLoadedAfterRefresh()
        .clickProfessionalInfoButton();
    softAssert.assertTrue(profileScreen.isDocumentsBlockDisplayed(),
        "'Documents' tab isn't displayed under 'Professional Info' section!");
    softAssert.assertTrue(profileScreen.isDownloadAllDocumentsButtonDisplayed(),
        "'Download all' button isn't displayed under 'Documents' tab!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void removeUploadedResume() {
    String fileName = Paths.get(RESUME_FILE_PATH).getFileName().toString();
    if (profileScreen.waitEditProfileLinkDisplayed().clickEditProfileLink()
        .isRemoveResumeButtonByNameDisplayed(fileName)) {
      profileEditionScreen
          .clickRemoveResumeButtonByName(fileName)
          .clickOkButtonInDeleteResumePopup().waitForSaveProfileButtonVisibility()
          .clickSaveProfileButton()
          .waitEditProfileLinkDisplayed();
    }
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void removeResume() {
    deleteFile(RESUME_FILE_PATH);
  }
}
