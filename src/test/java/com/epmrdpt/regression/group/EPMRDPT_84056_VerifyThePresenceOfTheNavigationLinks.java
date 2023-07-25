package com.epmrdpt.regression.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_TO_GROUP_LIST_LINK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_TO_TRAINING_LINK;

import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84056_VerifyThePresenceOfTheNavigationLinks",
    groups = {"full", "regression"})
public class EPMRDPT_84056_VerifyThePresenceOfTheNavigationLinks {

  private static final String TRAINING_NAME = "AutoTest_SecondStage";
  private static final String GROUP_NAME = "AddManyStudents";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    reactGroupDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickGroupsTabs()
        .waitDataLoading()
        .clickGroupByName(GROUP_NAME);
  }

  @Test
  public void checkThatToGroupsListLinkIsDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isToGroupsListLinkDisplayed(),
        String.format("'%s' link isn't displayed!",
            getValueOf(REACT_GROUP_DETAILS_SCREEN_TO_GROUP_LIST_LINK)));
    softAssert.assertEquals(reactGroupDetailsScreen.getToGroupsListLinkText(),
        getValueOf(REACT_GROUP_DETAILS_SCREEN_TO_GROUP_LIST_LINK),
        String.format("'%s' links text is incorrect!",
            getValueOf(REACT_GROUP_DETAILS_SCREEN_TO_GROUP_LIST_LINK)));
    softAssert.assertAll();
  }
}
