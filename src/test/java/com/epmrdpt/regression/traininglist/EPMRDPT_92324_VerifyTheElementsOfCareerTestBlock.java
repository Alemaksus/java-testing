package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CAREER_TEST_BLOCK_SCREEN_CAREER_TEST_BANNER_TEXT;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.CareerTestBlockScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.TrainingCardsSectionService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_92324_VerifyTheElementsOfCareerTestBlock",
    groups = {"full", "general", "regression"})
public class EPMRDPT_92324_VerifyTheElementsOfCareerTestBlock {

  private static final String TRAINING_NAME = "AUTOTEST WITH AC";
  private HeaderScreen headerScreen;
  private CareerTestBlockScreen careerTestBlockScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(UserFactory.withSimplePermission());
    careerTestBlockScreen = new CareerTestBlockScreen();
  }

  @Test(priority = 1)
  public void checkElementsOfCareerTestBlockOnHomePageIsDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(careerTestBlockScreen.isCareerTestButtonDisplayed(),
        "Career Test Button on home page is not displayed!");
    softAssert.assertEquals(careerTestBlockScreen.getTextFromCareerTestBanner(),
        getValueOf(CAREER_TEST_BLOCK_SCREEN_CAREER_TEST_BANNER_TEXT),
        "Wrong text of career test banner on home page!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkElementsOfCareerTestBlockOnUserProfilePageIsDisplayed() {
    SoftAssert softAssert = new SoftAssert();
    headerScreen.clickProfileMenu().clickProfileButton();
    softAssert.assertTrue(careerTestBlockScreen.isCareerTestButtonDisplayed(),
        "Career Test Button on profile page is not displayed!");
    softAssert.assertEquals(careerTestBlockScreen.getTextFromCareerTestBanner(),
        getValueOf(CAREER_TEST_BLOCK_SCREEN_CAREER_TEST_BANNER_TEXT),
        "Wrong text of career test banner on profile page!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkElementsOfCareerTestBlockOnTrainingListPage() {
    SoftAssert softAssert = new SoftAssert();
    headerScreen.clickTrainingListNavigationLink();
    softAssert.assertTrue(careerTestBlockScreen.isCareerTestButtonDisplayed(),
        "Career Test Button on training list page is not displayed!");
    softAssert.assertEquals(careerTestBlockScreen.getTextFromCareerTestBanner(),
        getValueOf(CAREER_TEST_BLOCK_SCREEN_CAREER_TEST_BANNER_TEXT),
        "Wrong text of career test banner on training list page!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkElementsOfCareerTestBlockOnTrainingDetailsPage() {
    SoftAssert softAssert = new SoftAssert();
    new TrainingCardsSectionService().openTrainingDescription(TRAINING_NAME);
    softAssert.assertTrue(careerTestBlockScreen.isCareerTestButtonDisplayed(),
        "Career Test Button on training details page is not displayed!");
    softAssert.assertEquals(careerTestBlockScreen.getTextFromCareerTestBanner(),
        getValueOf(CAREER_TEST_BLOCK_SCREEN_CAREER_TEST_BANNER_TEXT),
        "Wrong text of career test banner on training details page!");
    softAssert.assertAll();
  }
}
