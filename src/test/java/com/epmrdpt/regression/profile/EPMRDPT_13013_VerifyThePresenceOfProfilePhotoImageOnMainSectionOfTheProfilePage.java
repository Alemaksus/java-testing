package com.epmrdpt.regression.profile;

import static com.epmrdpt.utils.FileUtils.getImportDocumentPath;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13013_VerifyThePresenceOfProfilePhotoImageOnMainSectionOfTheProfilePage",
    groups = {"regression", "full"})
public class EPMRDPT_13013_VerifyThePresenceOfProfilePhotoImageOnMainSectionOfTheProfilePage {

  private ProfileScreen profileScreen;
  private ProfileEditionScreen profileEditionScreen;
  private static final String PROFILE_PHOTO_IMAGE_PATH = getImportDocumentPath("epam.jpg");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setUp() {
    profileScreen = new ProfileScreen();
    profileEditionScreen = new ProfileEditionScreen();
    new LoginService()
        .loginAndSetLanguage(UserFactory.withSimplePermissionAndWithoutTrainings());
  }

  @Test(priority = 1)
  public void checkProfileScreenIsOpened() {
    assertTrue(new HeaderScreen()
            .clickProfileMenu()
            .clickProfileButton()
            .isScreenLoaded(),
        "Profile page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkDefaultAvatarPictureIsDisplayed() {
    assertTrue(profileScreen.isDefaultAvatarProfilePhotoDisplayed(),
        "Default profile photo isn't displayed!");
  }

  @Test(priority = 3)
  public void checkEditProfilePageIsDisplayed() {
    assertTrue(profileScreen
            .clickEditProfileLink()
            .isScreenLoaded(),
        "Edit profile page isn't displayed!");
  }

  @Test(priority = 4)
  public void checkProfilePhotoIsChanged() {
    SoftAssert softAssert = new SoftAssert();
    profileEditionScreen
        .uploadProfilePicture(PROFILE_PHOTO_IMAGE_PATH)
        .waitForEditProfilePhotoIconVisibility();
    softAssert.assertFalse(profileEditionScreen.isDefaultProfilePhotoDisplayed(),
        "Profile photo isn't changed in profile edition page!");
    profileEditionScreen
        .waitForSaveProfileButtonVisibility()
        .clickSaveProfileButton()
        .waitSpinnerOfLoadingInvisibility()
        .waitForProfileScreenCompletelyLoadedAfterRefresh();
    softAssert.assertTrue(profileScreen.isScreenLoaded(),
        "Profile screen isn't opened after clicking save changes");
    profileScreen.waitDefaultMainProfilePhotoForInvisibility();
    softAssert.assertFalse(profileScreen.isDefaultMainProfilePhotoDisplayed(),
        "Profile photo isn't changed!");
    removeUploadedProfilePhoto();
    softAssert.assertAll();
  }

  private void removeUploadedProfilePhoto() {
    profileScreen.clickEditProfileLink()
        .waitForNonDefaultProfilePhotoVisibility()
        .waitForRemoveProfilePhotoVisibility()
        .clickRemoveProfilePhoto()
        .waitForDefaultProfilePhotoVisibility()
        .waitForEditProfilePhotoIconVisibility()
        .waitForSaveProfileButtonVisibility()
        .clickSaveProfileButton()
        .waitSpinnerOfLoadingInvisibility()
        .waitForProfileScreenCompletelyLoadedAfterRefresh();
  }
}
