package com.epmrdpt.smoke.edittraining;

import static com.epmrdpt.utils.RandomUtils.getRandomNumberList;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactTrainingManagementScreen;
import com.epmrdpt.screens.TrainingDescriptionScreen;
import com.epmrdpt.services.LoginService;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_13282_VerifyPreviewButtonOnTrainingDetailsPage",
    groups = {"full", "manager", "smoke"})
public class EPMRDPT_13282_VerifyPreviewButtonOnTrainingDetailsPage {

  private ReactTrainingManagementScreen reactTrainingManagementScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with Training Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_13282_VerifyPreviewButtonOnTrainingDetailsPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    reactTrainingManagementScreen = new ReactTrainingManagementScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
  }

  @Test(priority = 1)
  public void checkThatTrainingDescriptionScreenDisplayed() {
    new HeaderScreen()
        .waitBannerVisibility()
        .clickReactTrainingManagementLink()
        .uncheckCheckBoxes()
        .waitAllTrainingNameDisplayed()
        .clickApplyButton()
        .waitPreloadingPictureHidden();
    int trainingNameCount = reactTrainingManagementScreen.getTrainingSearchResultsCount();
    List<Integer> randomNumberList = getRandomNumberList(trainingNameCount);
    for (int nameIndex = 0; nameIndex < randomNumberList.size(); nameIndex++) {
      reactTrainingManagementScreen.clickTrainingNameByIndex(randomNumberList.get(nameIndex));
      reactDetailTrainingScreen.waitScreenLoaded();
      if (reactDetailTrainingScreen.isPreviewButtonDisplayed()) {
        reactDetailTrainingScreen.clickPreviewButton();
        reactDetailTrainingScreen.switchToLastWindow();
        break;
      } else {
        reactDetailTrainingScreen.clickBackToTrainingListFromTopButton()
            .waitPreloadingPictureHidden();
      }
    }
    assertTrue(new TrainingDescriptionScreen().isScreenLoaded(),
        "Training description page (as for candidates) isn't displayed in new window!");
  }
}
