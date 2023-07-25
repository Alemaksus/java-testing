package com.epmrdpt.regression.profile;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_48590_VerifyTheContentOfTheDocumentsSectionInTheProfile",
    groups = {"full", "general", "regression"})
public class EPMRDPT_48590_VerifyTheContentOfTheDocumentsSectionInTheProfile {

  private ProfileScreen profileScreen;
  private SoftAssert softAssert;

  private int numberOfAttachedDocuments;
  private String patternDate = "dd/mm/yyyy";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupProfessionalInfoTab() {
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithoutTrainings())
        .clickProfileMenu()
        .clickProfileButton()
        .clickProfessionalInfoButton();
    numberOfAttachedDocuments = profileScreen.getAttachedDocumentCount();
  }

  @Test
  public void checkDocumentsSectionHeader() {
    assertEquals(profileScreen.getDocumentsBlockTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_DOCUMENTS_TITLE),
        "'Documents' block has incorrect title!");
  }

  @Test
  public void checkLinkToPortfolio() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isLinkToPortfolioDisplayed(),
        "Link to portfolio isn't displayed!");
    softAssert.assertEquals(profileScreen.getLinkToPortfolioText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_LINK_TO_PORTFOLIO),
        "Link to portfolio has incorrect text!");
    softAssert.assertAll();
  }

  @Test
  public void checkDocumentsSectionDescription() {
    assertEquals(profileScreen.getDocumentsBlockDescriptionText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_DOCUMENTS_DESCRIPTION),
        "'Documents' block has incorrect description!");
  }

  @Test
  public void checkUploadButtonBlock() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(profileScreen.getUploadButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_UPLOAD_ONE_MORE_BUTTON),
        "'Upload' button in 'Documents' block has incorrect text!");
    softAssert.assertEquals(profileScreen.getDocumentDescriptionText(),
        LocaleProperties.getValueOf(
            LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_UPLOAD_FILE_HINT),
        "'Upload' button in 'Documents' block has incorrect description!");
    softAssert.assertAll();
  }

  @Test
  public void checkNameOfAttachedDocuments() {
    softAssert = new SoftAssert();
    for (int index = 0; index < numberOfAttachedDocuments; index++) {
      softAssert.assertTrue(profileScreen.isNameOfAttachedDocumentDisplayedByIndex(index + 1),
          format("Name in %d attached document in 'Documents' block isn't displayed!", index + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkDateOfUploadingDocuments() {
    softAssert = new SoftAssert();
    for (int index = 0; index < numberOfAttachedDocuments; index++) {
      softAssert.assertTrue(profileScreen.isDateOfUploadingDocumentDisplayedByIndex(index + 1),
          format("Date in %d attached document in 'Documents' block isn't displayed!", index + 1));
      softAssert.assertTrue(StringUtils.isDateMatchExpectedPattern(
          profileScreen.getDateOfUploadingDocumentTextByIndex(index + 1), patternDate));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkCrossButtonInAttachedDocuments() {
    softAssert = new SoftAssert();
    for (int index = 0; index < numberOfAttachedDocuments; index++) {
      softAssert.assertTrue(
          profileScreen.isCrossButtonInAttachedDocumentFieldDisplayedByIndex(index + 1),
          format("Cross button in %d attached document field in 'Documents' block isn't displayed!",
              index + 1));
    }
    softAssert.assertAll();
  }

  @Test
  public void checkDownloadAllButtonText() {
    assertEquals(profileScreen.getDownloadAllDocumentsButtonText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_DOWNLOAD_ALL_DOCUMENTS_BUTTON),
        "'Download all' button in 'Documents' block has incorrect text!");
  }
}
