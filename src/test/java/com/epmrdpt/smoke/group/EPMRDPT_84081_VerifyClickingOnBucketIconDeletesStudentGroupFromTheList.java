package com.epmrdpt.smoke.group;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_DELETE_POPUP_BODY_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_DELETE_POPUP_CANCEL_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_DELETE_POPUP_CONFIRMATION_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_CLASSES_SCREEN_DELETE_POPUP_HEADER;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactClassesScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84081_VerifyClickingOnBucketIconDeletesStudentGroupFromTheList",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_84081_VerifyClickingOnBucketIconDeletesStudentGroupFromTheList {

  private static final String TRAINING_NAME = "AutoTest_TestForAddingClassAndDeleteIt";
  private static final String CLASS_NAME = String.format("Automated Class %s",
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
  private final User user;
  private ReactClassesScreen reactClassesScreen;
  private int classCountBeforeAddingNewClass;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84081_VerifyClickingOnBucketIconDeletesStudentGroupFromTheList(
      User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void createNewClass() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactClassesScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickClassesTabs()
        .waitDataLoading();
    classCountBeforeAddingNewClass = reactClassesScreen.getClassNamesListSize();
    reactClassesScreen.clickAddClassButton()
        .waitNumberClassNamesToBeMore(classCountBeforeAddingNewClass)
        .clickClassByIndex(reactClassesScreen.getClassNamesListSize() - 1)
        .typeNewClassName(CLASS_NAME)
        .clickSaveChangesButton()
        .waitConfirmationToasterForVisibility()
        .clickOnConfirmationToasterCross()
        .clickToClassesListLink()
        .waitDataLoading();
  }

  @Test(priority = 1)
  public void verifyDeletePopUpIsDisplayed() {
    reactClassesScreen.clickDeleteIconByClassName(CLASS_NAME);
    Assert.assertTrue(reactClassesScreen.isPopUpWindowDisplayed(),
        "Delete pop-up isn't displayed!");
  }

  @Test(priority = 2)
  public void verifyDeletePopUpHeader() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactClassesScreen.isDeletePopUpHeaderDisplayed(),
        "Pop-up header isn't displayed!");
    softAssert.assertEquals(reactClassesScreen.getDeletePopUpHeaderText(),
        getValueOf(REACT_CLASSES_SCREEN_DELETE_POPUP_HEADER),
        "Pop-up header text isn't equal to the expected!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyDeletePopUpCrossButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactClassesScreen.isDeletePopUpCrossButtonDisplayed(),
        "Pop-up Cross button isn't displayed!");
    softAssert.assertTrue(reactClassesScreen.isDeletePopUpCrossButtonClickable(),
        "Pop-up Cross button isn't clickable!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyDeletePopUpBodyText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactClassesScreen.isDeletePopUpBodyTextDisplayed(),
        "Pop-up body text isn't displayed!");
    softAssert.assertEquals(reactClassesScreen.getDeletePopUpBodyText(),
        format(getValueOf(REACT_CLASSES_SCREEN_DELETE_POPUP_BODY_TEXT), CLASS_NAME),
        "Pop-up body text isn't equal to the expected!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyDeletePopUpCancelButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactClassesScreen.isDeletePopUpCancelButtonDisplayed(),
        "Pop-up Cancel button isn't displayed!");
    softAssert.assertTrue(reactClassesScreen.isDeletePopUpCancelButtonClickable(),
        "Pop-up Cancel button isn't clickable!");
    softAssert.assertEquals(reactClassesScreen.getDeletePopUpCancelButtonText(),
        getValueOf(REACT_CLASSES_SCREEN_DELETE_POPUP_CANCEL_BUTTON),
        "Pop-up Cancel button isn't equal to the expected!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void verifyDeletePopUpConfirmationButton() {
    SoftAssert softAssert = new SoftAssert();
    softAssert
        .assertTrue(reactClassesScreen.isDeletePopUpConfirmationButtonDisplayed(),
            "Pop-up Yes button isn't displayed!");
    softAssert
        .assertTrue(reactClassesScreen.isDeletePopUpConfirmationButtonClickable(),
            "Pop-up Yes button isn't clickable!");
    softAssert.assertEquals(reactClassesScreen.getDeletePopUpConfirmationButtonText(),
        getValueOf(REACT_CLASSES_SCREEN_DELETE_POPUP_CONFIRMATION_BUTTON),
        "Pop-up Yes button isn't equal to the expected!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void verifyThatPopUpIsClosed() {
    reactClassesScreen.clickPopUpConfirmationButton();
    assertFalse(reactClassesScreen.isPopUpWindowDisplayed(),
        "Pop up window wasn't closed!");
  }

  @Test(priority = 4)
  public void verifyThatClassIsDeleted() {
    int classCountAfterDeletingNewClass = reactClassesScreen.getClassNamesListSize();
    assertEquals(classCountAfterDeletingNewClass, classCountBeforeAddingNewClass,
        "Class wasn't deleted!");
  }

  @Test(priority = 5)
  public void verifyConfirmationToasterIsDisplayed() {
    Assert.assertTrue(reactClassesScreen.isConfirmationToasterDisplayed(),
        "Confirmation toaster isn't displayed!");
  }
}
