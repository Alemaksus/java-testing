package com.epmrdpt.smoke.events_management;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_90300_VerifyThatUserCanSaveChangesInTheCreatedEvent",
    groups = {"full", "smoke"})
public class EPMRDPT_90300_VerifyThatUserCanSaveChangesInTheCreatedEvent {

  private static final String EVENT_NAME = "new";
  private static final String DESCRIPTION_TEXT = StringUtils.generateRandomPassword();

  private ReactEventDetailsScreen reactEventDetailsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsManagementLink()
        .typeEventName(EVENT_NAME)
        .clickApplyButton()
        .waitAllSpinnersAreNotDisplayed()
        .clickEventByName(EVENT_NAME)
        .clickDetailsButton()
        .waitSaveChangesButtonClickable()
        .clickDescriptionButton()
        .clearMetaTagDescriptionInputField()
        .typeMetaTagDescriptionTest(DESCRIPTION_TEXT);
    reactEventDetailsScreen = new ReactEventDetailsScreen().clickSaveChangesButton();
  }

  @Test()
  public void checkSuccessPopUpText() {
    assertTrue(reactEventDetailsScreen.isSaveChangesPopupDisplayed(),
        "Success pop-up after click on the 'Save changes' button isn't displayed!");
  }
}
