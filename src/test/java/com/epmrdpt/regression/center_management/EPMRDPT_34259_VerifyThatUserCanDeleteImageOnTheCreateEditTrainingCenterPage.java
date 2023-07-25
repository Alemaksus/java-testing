package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.ADD_IMAGE_BUTTON_TEXT_CREATE_TRAINING_SCREEN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NOTE_ON_UPLOAD_IMAGE_TEXT_CREATE_TRAINING_SCREEN;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.training_center.TrainingCenter;
import com.epmrdpt.bo.training_center.TrainingCenterFactory;
import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34259_VerifyThatUserCanDeleteImageOnTheCreateEditTrainingCenterPage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34259_VerifyThatUserCanDeleteImageOnTheCreateEditTrainingCenterPage {

  private final String expectedAddImageButtonText = LocaleProperties
      .getValueOf(ADD_IMAGE_BUTTON_TEXT_CREATE_TRAINING_SCREEN);
  private final String expectedNoteOnUploadImageText = LocaleProperties
      .getValueOf(NOTE_ON_UPLOAD_IMAGE_TEXT_CREATE_TRAINING_SCREEN);
  private final String trainingCenterCountry = "AutoTestCountry";
  private String trainingCenterCity;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;
  private User user;
  private TrainingCenter trainingCenter;

  @Factory(dataProvider = "Provider of users with different cities options",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34259_VerifyThatUserCanDeleteImageOnTheCreateEditTrainingCenterPage
      (User user, String trainingCenterCity) {
    this.user = user;
    this.trainingCenterCity = trainingCenterCity;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupCreateTrainingCenterScreen() {
    TrainingCenterManagementService trainingCenterManagementService = new TrainingCenterManagementService();
    trainingCenter = TrainingCenterFactory
        .createTrainingCenterWithCountryAndCityName(trainingCenterCountry, trainingCenterCity);
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink();
    trainingCenterManagementService.deleteTrainingCenterIfPresent(trainingCenter);
    createOrEditTrainingCenterScreen = trainingCenterManagementService
        .fillTheFieldsCreateTrainingCenterScreen(trainingCenter);
  }

  @Test(priority = 1)
  public void checkImageAppearsOnCreatePage() {
    assertTrue(createOrEditTrainingCenterScreen.isUploadedPictureDisplayed(),
        "Image is not displayed on Create page!");
  }

  @Test(priority = 2)
  public void checkImageIsDeletedOnCreatePage() {
    createOrEditTrainingCenterScreen.clickDeleteImageCrossButton();
    assertFalse(createOrEditTrainingCenterScreen.isUploadedPictureDisplayed(),
        "Image is not deleted after clicking 'Delete cross' button on Create page!");
  }

  @Test(priority = 3)
  public void checkAddAnImageTextAppearsOnCreatePage() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(createOrEditTrainingCenterScreen.isAddPictureButtonDisplayed(),
        "Image has not been deleted on Create page!");
    softAssert.assertEquals(createOrEditTrainingCenterScreen.getAddPictureButtonText(),
        expectedAddImageButtonText,
        "The 'Add an image' text is not appeared after deleting picture on Create page!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkIconPlusCircleAppearsOnCreatePage() {
    assertTrue(createOrEditTrainingCenterScreen.isPlusCircleIconDisplayed(),
        "The icon 'plus circle' is not appeared after deleting picture on Create page!");
  }

  @Test(priority = 3)
  public void checkNoteTextAppearsOnCreatePage() {
    assertEquals(createOrEditTrainingCenterScreen.getNoteOnUploadImageInputText(),
        expectedNoteOnUploadImageText,
        "The note text is not appeared after deleting picture on Create page!");
  }

  @Test(priority = 4)
  public void checkImageIsDeletedOnEditPage() {
    createOrEditTrainingCenterScreen.setTrainingCenterPictureLogo(trainingCenter.getImgBase64())
        .clickCreateOrSaveTrainingCenterTopButton()
        .waitConfirmationPopupMessageVisibility()
        .clickConfirmationPopupOkButton()
        .waitNotificationPopUpAboutSuccessVisibilityAndInvisibility()
        .clickDeleteImageCrossButton();
    assertFalse(createOrEditTrainingCenterScreen.isUploadedPictureDisplayed(),
        "Image is not deleted after clicking 'Delete cross' button on Edit page!");
  }

  @Test(priority = 5)
  public void checkAddAnImageTextAppearsOnEditPage() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(createOrEditTrainingCenterScreen.isAddPictureButtonDisplayed(),
        "Image has not been deleted on Edit page!");
    softAssert.assertEquals(createOrEditTrainingCenterScreen.getAddPictureButtonText(),
        expectedAddImageButtonText,
        "The 'Add an image' text is not appeared after deleting picture on Edit page!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkIconPlusCircleAppearsOnEditPage() {
    assertTrue(createOrEditTrainingCenterScreen.isPlusCircleIconDisplayed(),
        "The icon 'plus circle' is not appeared after deleting picture on Edit page!");
  }

  @Test(priority = 5)
  public void checkNoteTextAppearsOnEditPage() {
    assertEquals(createOrEditTrainingCenterScreen.getNoteOnUploadImageInputText(),
        expectedNoteOnUploadImageText,
        "The note text is not appeared after deleting picture on Edit page!");
  }
}

