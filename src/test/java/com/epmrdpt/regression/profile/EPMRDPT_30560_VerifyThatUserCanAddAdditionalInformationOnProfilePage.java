package com.epmrdpt.regression.profile;

import static com.epmrdpt.framework.properties.UserPropertyConst.USER_LOGIN_WITH_RANDOM_DATES;
import static com.epmrdpt.framework.properties.UserPropertyConst.USER_PASSWORD_WITH_RANDOM_DATES;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ProfileService;
import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_30560_VerifyThatUserCanAddAdditionalInformationOnProfilePage",
    groups = {"full", "general", "regression"})
public class EPMRDPT_30560_VerifyThatUserCanAddAdditionalInformationOnProfilePage {

  private HeaderScreen headerScreen;
  private ProfileEditionScreen profileEditionScreen;
  private User user;
  private ProfileService profileService;
  private static final int REMOVE_SECTION_INDEX = 1;
  private static final String TEST_METHOD_FOR_CHECKING_FILL_FIELDS = "checkUserCanFillFields";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    headerScreen = new HeaderScreen();
    profileEditionScreen = new ProfileEditionScreen();
    profileService = new ProfileService();
    user = UserFactory.userWithTwoEducationsAndWorkExperiences();
    user.setUserName(UserProperty.getValueOf(USER_LOGIN_WITH_RANDOM_DATES));
    user.setPassword(UserProperty.getValueOf(USER_PASSWORD_WITH_RANDOM_DATES));
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user);
  }

  @BeforeMethod(inheritGroups = false, alwaysRun = true)
  public void deleteExtraEducationAndWorkExperienceSections(Method method) {
    if (method.getName().equals(TEST_METHOD_FOR_CHECKING_FILL_FIELDS)) {
      int educationSectionQuantity = profileEditionScreen.getEducationSectionsQuantity();
      int workSectionsQuantity = profileEditionScreen.getWorkSectionsQuantity();
      if (educationSectionQuantity > 1 || workSectionsQuantity > 1) {
        while (educationSectionQuantity > 1) {
          profileService.deleteEducationSectionByIndex(REMOVE_SECTION_INDEX);
          educationSectionQuantity--;
        }
        while (workSectionsQuantity > 1) {
          profileService.deleteJobSectionByIndex(REMOVE_SECTION_INDEX);
          workSectionsQuantity--;
        }
        profileEditionScreen.clickSaveProfileButton()
            .waitEditProfileLinkDisplayed()
            .clickEditProfileLink();
      }
    }
  }

  @Test(priority = 1)
  public void checkEditProfileOfUserOpened() {
    headerScreen
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickProfileButton()
        .waitEditProfileLinkDisplayed()
        .clickEditProfileLink();
    assertTrue(profileEditionScreen.isScreenLoaded(), "Edit profile screen isn't opened!");
  }

  @Test(priority = 2)
  public void checkUserCanFillFields() {
    profileEditionScreen
        .waitCityOfResidenceNotEmpty()
        .waitCityOfStudyNotEmpty();
    profileService.fillUserWithNotOneEducationAndWork(user);
    profileEditionScreen.clickSaveProfileButton()
        .waitSpinnerOfLoadingInvisibility()
        .waitEditProfileLinkDisplayed()
        .clickProfessionalInfoButton()
        .waitForProfileScreenCompletelyLoadedAfterRefresh();
    assertEquals(profileService.readUserFromProfileScreen(), user, "Fields are filled incorrect!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  public void deleteSecondEducationAndWorkExperienceSections(Method method) {
    if (method.getName().equals(TEST_METHOD_FOR_CHECKING_FILL_FIELDS)) {
      new ProfileScreen().clickEditProfileLink();
      profileService.deleteEducationSectionByIndex(REMOVE_SECTION_INDEX);
      profileService.deleteJobSectionByIndex(REMOVE_SECTION_INDEX);
      profileEditionScreen.clickSaveProfileButton()
          .waitEditProfileLinkDisplayed();
    }
  }
}
