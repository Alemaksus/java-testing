package com.epmrdpt.regression.group;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_GROUP_WAS_DELETED;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84073_VerifyAToasterAppearsAfterGroupIsDeleted",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84073_VerifyAToasterAppearsAfterGroupIsDeleted {

  private static final String TRAINING_NAME = "AutoTest_TestForAddingGroupAndDeleteIt";
  private ReactGroupScreen reactGroupScreen;
  private int groupCountBeforeAddingNewGroup;
  private String groupNameToDeleting;
  private final User user;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84073_VerifyAToasterAppearsAfterGroupIsDeleted(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setUp() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickReactTrainingManagementLink();
    reactGroupScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
        .clickGroupsTabs()
        .waitDataLoading();
    groupCountBeforeAddingNewGroup = reactGroupScreen.getGroupNameListSize();
    reactGroupScreen
        .clickAddGroupButton()
        .waitNumberGroupNameToBeMore(groupCountBeforeAddingNewGroup);
  }

  @Test(priority = 1)
  public void checkThatPopUpAppearsAfterClickingOnTheBucketIcon() {
    int groupCountAfterAddingNewGroup = reactGroupScreen.getGroupNameListSize();
    int indexToDeleteGroupAfterAddingNewGroup = groupCountAfterAddingNewGroup - 1;
    groupNameToDeleting = reactGroupScreen
        .getGroupTitleText()
        .get(indexToDeleteGroupAfterAddingNewGroup);
    reactGroupScreen
        .clickDeleteButtonByIndex(indexToDeleteGroupAfterAddingNewGroup);
    assertTrue(
        reactGroupScreen.isPopUpWindowDisplayed(),
        "Pop up window isn't displayed!");
  }

  @Test(priority = 2)
  public void checkThatPopUpIsClosed() {
    reactGroupScreen.clickRemoveConfirmButtonButton();
    assertFalse(
        reactGroupScreen.isPopUpWindowDisplayed(),
        "Pop up window wasn't closed!");
  }

  @Test(priority = 3)
  public void checkThatGroupIsDeleted() {
    int groupCountAfterDeletingNewGroup = reactGroupScreen.getGroupNameListSize();
    assertEquals(
        groupCountAfterDeletingNewGroup,
        groupCountBeforeAddingNewGroup,
        "Group wasn't deleted!");
  }

  @Test(priority = 4)
  public void checkConfirmationToaster() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        reactGroupScreen.isConfirmationToasterDisplayed(),
        "Confirmation toaster isn't displayed!");
    softAssert.assertTrue(
        reactGroupScreen.isCrossButtonInConfirmationToasterDisplayed(),
        "Cross button in confirmation toaster isn't displayed!");
    softAssert.assertEquals(
        reactGroupScreen.getGroupNameThatWasDeletedInConfirmationToasterText(),
        format(getValueOf(REACT_GROUP_SCREEN_GROUP_WAS_DELETED), groupNameToDeleting),
        "Wrong message in confirmation toaster!");
    softAssert.assertAll();
  }
}
