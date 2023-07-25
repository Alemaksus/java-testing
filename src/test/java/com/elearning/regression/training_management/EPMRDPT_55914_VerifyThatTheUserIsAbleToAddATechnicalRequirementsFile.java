package com.epmrdpt.regression.training_management;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_ADD_TECHNICAL_REQUIREMENTS_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_TECHNICAL_REQUIREMENTS_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_TECHNICAL_REQUIREMENTS_LABEL_TOOLTIP_TEXT;
import static com.epmrdpt.utils.FileUtils.getImportDocumentPath;

import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.ReactLoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_55914_VerifyThatTheUserIsAbleToAddATechnicalRequirementsFile",
    groups = {"full", "regression", "manager", "deprecated"})
public class EPMRDPT_55914_VerifyThatTheUserIsAbleToAddATechnicalRequirementsFile {

  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;
  private SoftAssert softAssert;
  private String technicalRequirementsLabelName = getValueOf(
      REACT_DETAIL_TRAINING_SCREEN_TECHNICAL_REQUIREMENTS_LABEL);
  private String uploadingFileName = "pdfFileForUpload.pdf";
  private String uploadingFilePath = getImportDocumentPath(uploadingFileName);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(asTrainingManager())
        .clickCreateNewButton()
        .waitScreenLoading();
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
  }

  @Test(priority = 1)
  public void verifyTechnicalRequirementsSection() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGeneralInfoTabScreen.isTechnicalRequirementsLabelDisplayed(),
        "'Technical Requirements' label isn't displayed!");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isAddTechnicalRequirementsButtonDisplayed(),
        "'Add Technical Requirements' button isn't displayed!");
    softAssert.assertEquals(reactGeneralInfoTabScreen.getAddTechnicalRequirementsButtonText(),
        getValueOf(REACT_DETAIL_TRAINING_SCREEN_ADD_TECHNICAL_REQUIREMENTS_BUTTON),
        "'Add Technical Requirements' button' has incorrect text!");
    softAssert.assertTrue(reactGeneralInfoTabScreen
            .isQuestionIconDisplayedByLabelName(technicalRequirementsLabelName),
        "'Technical Requirements' question icon isn't displayed!");
    softAssert.assertEquals(
        reactGeneralInfoTabScreen.getToolTipTextByLabelName(technicalRequirementsLabelName),
        getValueOf(REACT_DETAIL_TRAINING_SCREEN_TECHNICAL_REQUIREMENTS_LABEL_TOOLTIP_TEXT),
        "'Technical Requirements' question icon has incorrect tooltip text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyPossibilityToUploadTechnicalRequirementsFile() {
    reactGeneralInfoTabScreen.uploadFile(uploadingFilePath).waitAllSpinnersAreNotDisplayed();
    softAssert = new SoftAssert();
    softAssert
        .assertTrue(reactGeneralInfoTabScreen.isUploadedFileDisplayedByName(uploadingFileName),
            "Uploaded file isn't displayed!");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isDownloadButtonOfUploadedFileDisplayedByName(uploadingFileName),
        "Download button is not displayed!");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.isCrossButtonOfUploadedFileDisplayedByName(uploadingFileName),
        "Cross button is not displayed!");
    softAssert.assertFalse(reactGeneralInfoTabScreen.isAddTechnicalRequirementsButtonEnabled(),
        "'Add Technical Requirement' button is enabled after file uploading!");
    softAssert.assertAll();
  }
}
