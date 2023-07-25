package com.epmrdpt.smoke.traininglist;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SUBSCRIBE_INFO_MESSAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_SUBSCRIBE_BUTTON;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.SubscribeScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77631_VerifyThatUserCanSubscribeTrainingInStatusPlanned",
    groups = {"full", "smoke"})
public class EPMRDPT_77631_VerifyThatUserCanSubscribeTrainingInStatusPlanned {

  private TrainingBlockScreen trainingBlockScreen;
  private TrainingDescriptionScreen trainingDescriptionScreen;
  private SubscribeScreen subscribeScreen;
  private String trainingName = "AutomatedTesting Training For Checkbox search";
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupTrainingListPage() {
    new LoginService().loginAndSetLanguage(UserFactory.withSimplePermission());
    trainingBlockScreen = new TrainingBlockScreen()
        .waitScreenLoaded()
        .cleanLocationFilterIfNeed()
        .clickViewMoreTrainingsLink();
  }

  @Test(priority = 1)
  public void checkSubscribeButtonOnTheTrainingCard() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingBlockScreen.isTrainingCardButtonPresentNoWait(trainingName),
        "'Subscribe' button is not present on the training card!");
    softAssert.assertEquals(trainingBlockScreen.getTrainingCardButtonText(trainingName),
        getValueOf(TRAINING_LIST_SUBSCRIBE_BUTTON),
        "'Subscribe' button title is displayed incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkSubscribeButtonOnTrainingDetailsPage() {
    trainingDescriptionScreen = trainingBlockScreen.clickTrainingCardByName(trainingName)
        .waitTrainingTitleLabelVisibility();
    softAssert = new SoftAssert();
    softAssert.assertTrue(trainingDescriptionScreen.isSubscribeButtonPresent(),
        "'Subscribe' button is not present on the training details page!");
    softAssert.assertEquals(trainingDescriptionScreen.getSubscribeButtonText(),
        getValueOf(TRAINING_LIST_SUBSCRIBE_BUTTON),
        "'Subscribe' button title is displayed incorrect!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTheUserIsSubscribedToTheTraining() {
    softAssert = new SoftAssert();
    subscribeScreen = trainingDescriptionScreen.clickSubscribeButton().clickSubmitButton();
    softAssert.assertTrue(subscribeScreen.isConfirmationPopupDisplayed(),
        "Confirmation popup is not displayed!");
    softAssert.assertEquals(subscribeScreen.getSubscribeInfoMessageText(),
        getValueOf(SUBSCRIBE_INFO_MESSAGE), "Confirmation popup message isn't correct!");
    softAssert.assertAll();
  }
}
