package com.epmrdpt.regression.profile;

import static com.epmrdpt.utils.FileUtils.deleteFile;
import static com.epmrdpt.utils.FileUtils.getImportDocumentPath;
import static com.epmrdpt.utils.FileUtils.getNewFile;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import java.io.File;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_48592_VerifyDocumentCanBeUploadedToProfile",
    groups = {"full", "general", "regression"})
public class EPMRDPT_48592_VerifyDocumentCanBeUploadedToProfile {

  private ProfileScreen profileScreen;
  private File file;
  private final String filePath = getImportDocumentPath("epam.docx");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUpProfileScreen() {
    file = getNewFile(filePath);
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(UserFactory.asSuperAdmin())
        .clickProfileMenu()
        .clickProfileButton()
        .waitProfessionalInfoButtonClickable()
        .clickProfessionalInfoButton()
        .waitUploadDocumentButtonClickable();
  }

  @Test
  public void checkDocumentUploading() {
    SoftAssert softAssert;
    profileScreen.uploadDocumentByFilePath(file.getAbsolutePath());
    softAssert = new SoftAssert();
    softAssert.assertEquals(profileScreen.getMessageFileUploadedPopUpText(),
        LocaleProperties.getValueOf(LocalePropertyConst.EDIT_PROFILE_SUCCESSFUL_RESUME_UPLOAD),
        "Invalid message about successful file upload!");
    softAssert.assertTrue(profileScreen.isUploadedDocumentDisplayed(file.getName()),
        "The uploaded file is not displayed!");
    softAssert.assertAll();
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void removeResume() {
    profileScreen.clickRemoveResumeCrossButtonByName(file.getName()).clickPopupOkButton();
    deleteFile(filePath);
  }
}
