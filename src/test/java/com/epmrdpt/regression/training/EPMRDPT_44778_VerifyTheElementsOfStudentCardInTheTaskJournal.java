package com.epmrdpt.regression.training;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_DATE_OF_CREATION_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_METHOD_OF_COMMUNICATION_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_TECHNICAL_TEST_RESULT_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION;
import static com.epmrdpt.utils.StringUtils.isDateMatchExpectedPattern;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactStudentCardPopUpScreen;
import com.epmrdpt.screens.ReactTasksJournalScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_44778_VerifyTheElementsOfStudentCardInTheTaskJournal",
    groups = {"full", "react", "regression"})
public class EPMRDPT_44778_VerifyTheElementsOfStudentCardInTheTaskJournal {

  private final String groupName = "AutoTest_GroupWithEverydayClasses";
  private final String studentName = "QQ AA";
  private final String dateFormat = "dd.MM.yyyy";
  private final String contactPhoneNumber = "+375111000";
  private final String emailAddress = "redadi4761@proton.me";
  private ReactStudentCardPopUpScreen reactStudentCardPopUpScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_44778_VerifyTheElementsOfStudentCardInTheTaskJournal(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupStudentCardInTasksJournalTab() {
    new ReactLoginService()
        .loginAndGoToReactTrainingScreen(user)
        .clickMyGroupsTab();
    new ReactTrainingService()
        .openGroupJournalByName(groupName)
        .clickTaskJournalTab()
        .waitGroupJournalTableForVisibility();
    reactStudentCardPopUpScreen = new ReactTasksJournalScreen()
        .clickStudentByName(studentName);
  }

  @Test(priority = 1)
  public void checkStudentCardPopUpDisplayed() {
    assertTrue(reactStudentCardPopUpScreen.isScreenLoaded(),
        "'Student Card' pop up isn't displayed!");
  }

  @Test(priority = 2)
  public void checkStudentPhotoDisplayed() {
    assertTrue(reactStudentCardPopUpScreen.isStudentPhotoBackgroundImageDisplayed(),
        "'Student's photo' in the Student Card Pop Up page isn't displayed!");
  }

  @Test(priority = 2)
  public void checkStudentName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactStudentCardPopUpScreen.isStudentNameTitleDisplayed(),
        "'Student Name Title' in the Student Card Pop Up page isn't displayed!");
    softAssert.assertFalse(reactStudentCardPopUpScreen.isStudentNameTitleHasNoLink(),
        "'Student Name Title' in the Student Card Pop Up page hasn't link!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkDateOfProfileCreationLabel() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactStudentCardPopUpScreen.getDateOfCreationTitleText(),
        getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_DATE_OF_CREATION_SECTION),
        "'Date Of Profile Creation' title in the Student Card Pop Up has incorrect text!");
    softAssert.assertTrue(isDateMatchExpectedPattern(
            reactStudentCardPopUpScreen.getDateOfCreationValueText(), dateFormat),
        "'Date Of Profile Creation' value in the Student Card Pop Up has incorrect format!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkPreferredMethodOfCommunicationTitlesHaveCorrectText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getPreferredMethodOfCommunicationTitleText(),
        getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_METHOD_OF_COMMUNICATION_TITLE),
        "'Preferred Method Of Communication' title in the Student Card has incorrect text!");
    softAssert.assertEquals(reactStudentCardPopUpScreen.getMethodOfCommunicationByPhoneText(),
        getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION),
        "Selected Method Of Communication in the Student Card has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkEnglishTestResultText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactStudentCardPopUpScreen.getEnglishTestResultTitleText(),
        getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE),
        "'English test result' title in the Student Card has incorrect text!");
    softAssert.assertFalse(reactStudentCardPopUpScreen.getEnglishTestResultText().isEmpty(),
        "'English test result' title in the Student Card is empty!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTechnicalTestResultText() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(reactStudentCardPopUpScreen.getTechnicalTestResultTitleText(),
        getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_TECHNICAL_TEST_RESULT_TITLE),
        "'Technical test result' title in the Student Card has incorrect text!");
    softAssert.assertFalse(reactStudentCardPopUpScreen.getTechnicalTestResultText().isEmpty(),
        "Technical test result in the Student Card is empty!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkEmailDataDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactStudentCardPopUpScreen.isEmailIconDisplayed(),
        "'Email Icon' in the Student Card Pop Up page isn't displayed!");
    softAssert.assertEquals(reactStudentCardPopUpScreen.getEmailAddressText(), emailAddress,
        "Email in the Student Card has incorrect text!");
    softAssert.assertTrue(reactStudentCardPopUpScreen.isEmailCopyButtonDisplayed(),
        "'Email copy' button in the Student Card Pop Up page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkContactPhoneDataDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactStudentCardPopUpScreen.isContactPhoneIconDisplayed(),
        "'Contact Phone Icon' in the Student Card Pop Up page isn't displayed!");
    softAssert.assertEquals(reactStudentCardPopUpScreen.getContactPhoneNumberText(),
        contactPhoneNumber,
        "Contact phone number in the Student Card has incorrect text!");
    softAssert.assertTrue(reactStudentCardPopUpScreen.isContactPhoneCopyButtonDisplayed(),
        "'Contact Phone copy' button in the Student Card Pop Up page isn't displayed!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSocialNetworksLogosDisplayed() {
    assertTrue(reactStudentCardPopUpScreen.isSocialNetworksLogosDisplayed(),
        "'Social Networks Logos' in the Student Card Pop Up page isn't displayed!");
  }
}
