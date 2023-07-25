package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.CHOOSE_FIELD_REQUIRED_ERROR;
import static org.testng.Assert.assertEquals;
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

@Test(description = "EPMRDPT_34246_VerifyThatUserCannotCreateTrainingCenterWithoutAnImage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34246_VerifyThatUserCannotCreateTrainingCenterWithoutAnImage {

  private final static String EXPECTED_HINT_COLOR = "rgba(211, 93, 71, 1)";
  private CreateOrEditTrainingCenterScreen createTrainingCenterScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34246_VerifyThatUserCannotCreateTrainingCenterWithoutAnImage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupCreateTrainingCenterScreen() {
    TrainingCenter trainingCenter = TrainingCenterFactory
        .createTrainingCenter();
    new LoginService()
        .loginAndSetLanguage(user).clickCenterManagementLink();
    new TrainingCenterManagementService()
        .deleteTrainingCenterIfPresent(trainingCenter);
    createTrainingCenterScreen = new TrainingCenterManagementService()
        .fillTheFieldsCreateTrainingCenterScreen(trainingCenter)
        .clickDeleteImageCrossButton()
        .clickCreateOrSaveTrainingCenterTopButton();
  }

  @Test(priority = 1)
  public void checkTrainingCenterIsNotCreated() {
    assertTrue(createTrainingCenterScreen.isCreateTrainingCenterScreenOpened(),
        "Training center is created!");
  }

  @Test(priority = 2)
  public void checkChooseImgFieldErrorHintAppeared() {
    assertTrue(createTrainingCenterScreen.isChooseImgFieldIsRequiredErrorHintDisplayed(),
        "'This field is required' hint on image uploading area isn't displayed!");
  }

  @Test(priority = 3)
  public void checkChooseFieldErrorHintText() {
    assertEquals(createTrainingCenterScreen.getChooseImgFieldIsRequiredErrorHintText(),
        LocaleProperties.getValueOf(CHOOSE_FIELD_REQUIRED_ERROR),
        "Text from error hint on image uploading area is not matched!");
  }

  @Test(priority = 4)
  public void checkChooseFieldErrorHintHasRedColor() {
    assertEquals(createTrainingCenterScreen.getChooseImgFieldIsRequiredErrorHintColor(),
        EXPECTED_HINT_COLOR,
        "Color of text from error hint on image uploading area is not red as expected!");
  }
}
