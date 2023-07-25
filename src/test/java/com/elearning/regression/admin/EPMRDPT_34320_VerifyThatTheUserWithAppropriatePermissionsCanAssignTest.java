package com.epmrdpt.regression.admin;

import static com.epmrdpt.bo.user.UserFactory.asLearningStudent;
import static org.testng.Assert.*;
import static com.epmrdpt.framework.properties.LocalePropertyConst.*;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ProfileService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34320_VerifyThatTheUserWithAppropriatePermissionsCanAssignTest",
    groups = {"full", "general", "regression", "admin"})
public class EPMRDPT_34320_VerifyThatTheUserWithAppropriatePermissionsCanAssignTest {

  private ProfileScreen profileScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupUserProfile() {
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndSetLanguage(UserFactory.asRecruiter())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickSearchLink()
        .enterSearchInputText(asLearningStudent().getUsername())
        .clickFindButton()
        .clickSearchResultByEmail(asLearningStudent().getUsername());
    profileScreen.switchToLastWindow();
    profileScreen.waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkEnglishTestAssignedPopUpDisplayed() {
    profileScreen.clickEnglishTestButtonIfExists()
        .waitConfirmationPopUpForVisibility();
    assertTrue(new ProfileService().closeConfirmationPopUpIfTestAlreadyAssigned()
            .isConfirmInformationPopUpDisplayed(),
        "'The English test assigned' pop-up is not displayed!");
  }

  @Test(priority = 2)
  public void checkMessagePopUpText() {
    assertEquals(profileScreen.getMessageEnglishTestAssignedPopUpText(),
        LocaleProperties.getValueOf(PROFILE_ENGLISH_ASSIGNED_POPUP_MESSAGE),
        "'The English test assigned' pop-up has incorrect text!");
  }
}
