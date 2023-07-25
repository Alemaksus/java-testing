package com.epmrdpt.regression.applications;

import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_PASS_ENGLISH_TEST_HINT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_PASS_ENGLISH_TEST_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_PASS_REGISTRATION_TEST_HINT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_PASS_REGISTRATION_TEST_LINK;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13281_13283_13284_CheckPassRegistrationAndEnglishTestLinkAndHint",
    groups = {"full", "regression", "student"})
public class EPMRDPT_13281_13283_13284_CheckPassRegistrationAndEnglishTestLinkAndHint {

  private SoftAssert softAssert;
  private ApplicationsScreen applicationsScreen;
  private static final String TRAINING_WITH_REGISTRATION_TEST = "AUTOTEST WITH AC";
  private static final String TRAINING_WITH_ENGLISH_TEST = "AUTOTEST WITH ENGLISH";
  private static final String TRAINING_WITH_ENGLISH_AND_REGISTRATION_TEST = "AUTOTEST WITH AC AND ENGLISH";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    applicationsScreen = new ApplicationsScreen();
  }

  @Test(priority = 1)
  public void checkApplicationsPageLoaded() {
    assertTrue(new LoginService()
            .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                UserFactory.asLearningStudent())
            .clickProfileMenu()
            .waitDropDownDisplayed()
            .clickApplicationsButton()
            .isScreenLoaded(),
        "Application page isn't loaded!");
  }

  @DataProvider(name = "Provider of trainings with English Test")
  private static Object[][] dataProviderWithEnglishTest() {
    return new Object[][]{
        {TRAINING_WITH_ENGLISH_TEST},
        {TRAINING_WITH_ENGLISH_AND_REGISTRATION_TEST}
    };
  }

  @Test(priority = 2, dataProvider = "Provider of trainings with English Test")
  public void checkPassEnglishTestHintAndLink(String trainingWithEnglishTest) {
    softAssert = new SoftAssert();
    softAssert.assertTrue(applicationsScreen
            .isPassEnglishTestLinkByTrainingNameDisplayed(trainingWithEnglishTest),
        format("'Pass english test' link isn't displayed for training '%s'",
            trainingWithEnglishTest));
    softAssert.assertEquals(applicationsScreen
            .getPassEnglishTestByTrainingNameLinkText(trainingWithEnglishTest),
        LocaleProperties.getValueOf(APPLICATIONS_PASS_ENGLISH_TEST_LINK),
        format("'Pass english test' link description isn't correct for training '%s'",
            trainingWithEnglishTest));
    softAssert.assertTrue(applicationsScreen
            .isPassEnglishTestHintByTrainingNameDisplayed(trainingWithEnglishTest),
        format("'Pass english test' hint isn't displayed for training '%s'",
            trainingWithEnglishTest));
    softAssert.assertEquals(
        applicationsScreen.getPassEnglishTestHintByTrainingNameText(trainingWithEnglishTest),
        LocaleProperties.getValueOf(APPLICATIONS_PASS_ENGLISH_TEST_HINT),
        format("'Pass english test' hint description isn't correct for training '%s'",
            trainingWithEnglishTest));
    softAssert.assertAll();
  }

  @DataProvider(name = "Provider of trainings with Registration Test")
  private static Object[][] dataProviderWithRegistrationTest() {
    return new Object[][]{
        {TRAINING_WITH_REGISTRATION_TEST},
        {TRAINING_WITH_ENGLISH_AND_REGISTRATION_TEST}
    };
  }

  @Test(priority = 2, dataProvider = "Provider of trainings with Registration Test")
  public void checkPassRegistrationTestHintAndLink(String trainingWithRegistrationTest) {
    softAssert = new SoftAssert();
    softAssert.assertTrue(applicationsScreen
            .isPassRegistrationTestLinkByTrainingNameDisplayed(trainingWithRegistrationTest),
        format("'Pass registration test' link isn't displayed for training '%s'",
            trainingWithRegistrationTest));
    softAssert.assertEquals(applicationsScreen
            .getPassRegistrationTestByTrainingNameLinkText(trainingWithRegistrationTest),
        LocaleProperties.getValueOf(APPLICATIONS_PASS_REGISTRATION_TEST_LINK),
        format("'Pass registration test' link description isn't correct for training '%s'",
            trainingWithRegistrationTest));
    softAssert.assertTrue(applicationsScreen
            .isPassRegistrationTestHintByTrainingNameDisplayed(trainingWithRegistrationTest),
        format("'Pass registration test' hint isn't displayed for training '%s'",
            trainingWithRegistrationTest));
    softAssert.assertEquals(applicationsScreen
            .getPassRegistrationTestHintByTrainingNameText(trainingWithRegistrationTest),
        LocaleProperties.getValueOf(APPLICATIONS_PASS_REGISTRATION_TEST_HINT),
        format("'Pass registration test' hint description isn't correct for training '%s'",
            trainingWithRegistrationTest));
    softAssert.assertAll();
  }
}
