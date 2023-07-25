package com.epmrdpt.smoke.participants;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ONLINE_POPUP_STATUS_ASSIGNED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PROFILE_PREFERRED_METHODS_COMMUNICATION_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_DATE_OF_CREATION_SECTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_STUDENT_CARD_POP_UP_SCREEN_TECHNICAL_TEST_RESULT_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION;
import static com.epmrdpt.framework.properties.UserPropertyConst.PROFILE_ENGLISH_TEST_RESULT;

import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.screens.ReactStudentCardPopUpScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_79691_VerifyElementsOfStudentCard",
    groups = {"full", "smoke"})
public class EPMRDPT_79691_VerifyElementsOfStudentCard {

  private final String trainingName = "AUTO_TEST_79691";
  private final String studentName = "Peter Potatov";
  private final String studentDateOfProfileCreation = "03.08.2022";
  private final String studentEnglishTestResult = UserProperty.getValueOf(PROFILE_ENGLISH_TEST_RESULT);
  private final String studentEmail = "peterpotapov8989@yopmail.com";
  private final String studentPhone = "+5955555";
  private final String studentLinkedinProfile = "peterpotapov8989";
  private ReactStudentCardPopUpScreen reactStudentCardPopUpScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndGoToTrainingAndClickParticipant() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickReactParticipantsTab()
        .waitParticipantsTableElementsForVisibility()
        .clickParticipantByName(studentName);
    reactStudentCardPopUpScreen = new ReactStudentCardPopUpScreen();
  }

  @Test(priority = 1)
  public void checkThatPopUpScreenIsLoaded() {
    Assert.assertTrue(
        reactStudentCardPopUpScreen.isScreenLoaded(),
        "Pop-up screen is not loaded!"
    );
  }

  @Test(priority = 2)
  public void checkStudentPhotoBackgroundImageDisplayed() {
    Assert.assertTrue(
        reactStudentCardPopUpScreen.isStudentPhotoBackgroundImageDisplayed(),
        "Student's photo is not displayed!"
    );
  }

  @Test(priority = 3)
  public void checkStudentName() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactStudentCardPopUpScreen.isStudentNameTitleDisplayed(),
        "Name title is not displayed!"
    );
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getStudentNameTitleText(),
        studentName,
        "Student's name is not correct!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkDateOfCreation() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getDateOfCreationTitleText(),
        getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_DATE_OF_CREATION_SECTION),
        "The text of the title is not correct!"
    );
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getDateOfCreationValueText(),
        studentDateOfProfileCreation,
        "The date of creation is not correct!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkPreferredMethodOfCommunication() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getPreferredMethodOfCommunicationTitleText(),
        getValueOf(PROFILE_PREFERRED_METHODS_COMMUNICATION_LABEL),
        "The text of the title of methods of communication is not correct!"
    );
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getMethodOfCommunicationByPhoneText(),
        getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION),
        "Student's preferred method of communication is not correct!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkEnglishTestResult() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getEnglishTestResultTitleText(),
        getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_ENGLISH_TEST_RESULT_TITLE),
        "The title of english test result section is not correct!"
    );
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getEnglishTestResultText(),
        studentEnglishTestResult,
        "Student's english test result is not correct!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkTechnicalTestResult() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getTechnicalTestResultTitleText(),
        getValueOf(REACT_STUDENT_CARD_POP_UP_SCREEN_TECHNICAL_TEST_RESULT_TITLE),
        "The title of technical test result is not correct!"
    );
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getTechnicalTestResultText(),
        getValueOf(ONLINE_POPUP_STATUS_ASSIGNED),
        "The technical test result is not correct!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkEmail() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactStudentCardPopUpScreen.isEmailIconDisplayed(),
        "Email icon is not displayed!"
    );
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getEmailAddressText(),
        studentEmail,
        "Email is not correct!"
    );
    softAssert.assertTrue(
        reactStudentCardPopUpScreen.isEmailCopyButtonDisplayed(),
        "Email copy button is not present!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkPhone() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(
        reactStudentCardPopUpScreen.getContactPhoneNumberText(),
        studentPhone,
        "Phone number is not correct!"
    );
    softAssert.assertTrue(
        reactStudentCardPopUpScreen.isContactPhoneIconDisplayed(),
        "Phone number icon is not displayed!"
    );
    softAssert.assertTrue(
        reactStudentCardPopUpScreen.isContactPhoneCopyButtonDisplayed(),
        "Phone copy button is not present!"
    );
    softAssert.assertAll();
  }

  @Test(priority = 10)
  public void checkLinkedinLink() {
    Assert.assertTrue(
        reactStudentCardPopUpScreen.isLinkedinLinkPresent(studentLinkedinProfile),
        "Linkedin link is not present!"
    );
  }
}
