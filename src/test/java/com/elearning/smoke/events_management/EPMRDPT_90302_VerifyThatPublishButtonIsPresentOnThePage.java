package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90302_VerifyThatPublishButtonIsPresentOnThePage",
    groups = {"full", "smoke"})
public class EPMRDPT_90302_VerifyThatPublishButtonIsPresentOnThePage {

  private ReactEventDetailsScreen reactEventDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void loginAndOpenDraftEventDetails() {
    reactEventDetailsScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink()
        .clickOnFirstEventWithDraftStatus()
        .clickDetailsButton();
  }

  @Test
  public void checkPublishButtonIsDisplayed() {
    assertTrue(reactEventDetailsScreen.isPublishButtonDisplayed(),
        "The 'Publish' button is not displayed on the right under the status bar");
  }
}
