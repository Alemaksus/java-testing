package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_HINT_FOR_NAME_AND_DESCRIPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.EDIT_CENTER_MANAGEMENT_NOTE_FOR_NAME;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CreateOrEditTrainingCenterScreen;
import com.epmrdpt.screens.TrainingCenterManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCenterManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34254_VerifyWhenUserSavesWithoutFillingRequiredFieldsInEnglishAHintAppears",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34254_VerifyWhenUserSavesWithoutFillingRequiredFieldsInEnglishAHintAppears {

  private final String expectedColor = "rgba(211, 93, 71, 1)";
  private User user;
  private String trainingCenterCountry = "AutoTestCountry";
  private String trainingCenterCity = "AutoTestCity";
  private TrainingCenterManagementScreen trainingCenterManagementScreen;
  private CreateOrEditTrainingCenterScreen createOrEditTrainingCenterScreen;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34254_VerifyWhenUserSavesWithoutFillingRequiredFieldsInEnglishAHintAppears(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupTrainingCenter() {
    trainingCenterManagementScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .waitFiltersForVisibility();
    new TrainingCenterManagementService()
        .selectCountryAndCityInSearchSection(trainingCenterCountry, trainingCenterCity);
    createOrEditTrainingCenterScreen = trainingCenterManagementScreen.clickCenterLink()
        .waitEditAreaForVisibility()
        .clearTrainingCenterName()
        .clearTrainingCenterDescription();
  }

  @Test(priority = 1)
  public void checkThatTrainingCenterNameIsEmpty() {
    assertTrue(createOrEditTrainingCenterScreen.getTrainingCenterNameText().isEmpty(),
        "The required TrainingCenterName field on the English tab must be cleared!");
  }

  @Test(priority = 2)
  public void checkThatTrainingCenterDescriptionIsEmpty() {
    assertTrue(createOrEditTrainingCenterScreen.getTrainingCenterDescriptionText().isEmpty(),
        "The required TrainingCenterDescription field on the English tab must be cleared!");
  }

  @Test(priority = 3)
  public void checkThatTheNoteHasCorrectText() {
    createOrEditTrainingCenterScreen.clickAtTheTopSaveButton();
    assertEquals(createOrEditTrainingCenterScreen.getNoteAddingInformationText(),
        LocaleProperties.getValueOf(EDIT_CENTER_MANAGEMENT_NOTE_FOR_NAME),
        "The note message did not appear!");
  }

  @Test(priority = 4)
  public void checkNoteColor() {
    assertEquals(createOrEditTrainingCenterScreen.getNoteAddingInformationColor(), expectedColor,
        "Note is not highlighted red!");
  }

  @Test(priority = 5)
  public void checkThatTheHintForNameHasCorrectText() {
    assertEquals(createOrEditTrainingCenterScreen.getTrainingCenterNameHintText(),
        LocaleProperties.getValueOf(EDIT_CENTER_MANAGEMENT_HINT_FOR_NAME_AND_DESCRIPTION),
        "The hint message for the trainingCenterNameField did not appear!");
  }

  @Test(priority = 6)
  public void checkNameHintColor() {
    assertEquals(createOrEditTrainingCenterScreen.getNameHintColor(), expectedColor,
        "Name hint is not highlighted red!");
  }

  @Test(priority = 7)
  public void checkThatTheHintForDescriptionHasCorrectText() {
    assertEquals(createOrEditTrainingCenterScreen.getDescriptionHintText(),
        LocaleProperties.getValueOf(EDIT_CENTER_MANAGEMENT_HINT_FOR_NAME_AND_DESCRIPTION),
        "The hint message for trainingCenterDescriptionField did not appear!");
  }

  @Test(priority = 8)
  public void checkDescriptionHintColor() {
    assertEquals(createOrEditTrainingCenterScreen.getDescriptionHintColor(), expectedColor,
        "Description hint is not highlighted red!");
  }

  @Test(priority = 9)
  public void checkThatItIsImpossibleToSaveChanges() {
    assertFalse(createOrEditTrainingCenterScreen.isSaveChangesOrCreatePopUpDisplayed(),
        "It is possible to save the changes!");
  }
}
