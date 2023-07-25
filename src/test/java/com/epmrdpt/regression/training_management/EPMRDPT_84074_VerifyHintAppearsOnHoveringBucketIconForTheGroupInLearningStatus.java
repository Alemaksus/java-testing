package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_DELETE_GROUP_IN_LEARNING_STATUS_TOOLTIP_MESSAGE;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84074_VerifyHintAppearsOnHoveringBucketIconForTheGroupInLearningStatus",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84074_VerifyHintAppearsOnHoveringBucketIconForTheGroupInLearningStatus {

  private User user;
  private String trainingName = "AutoTest_NotificationLanguageVerification";
  private String groupName = "AutoTest_Group_10";
  private ReactGroupScreen reactGroupScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84074_VerifyHintAppearsOnHoveringBucketIconForTheGroupInLearningStatus(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded();
    reactGroupScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickGroupsTabs()
        .waitDataLoading();
  }

  @Test
  public void verifyHintOnHoveringBucketIcon() {
    Assert.assertEquals(
        getValueOf(REACT_GROUP_SCREEN_DELETE_GROUP_IN_LEARNING_STATUS_TOOLTIP_MESSAGE),
        reactGroupScreen.hoverOnDeleteButtonByGroupName(groupName),
        "Hovered 'Bucket' button hint text isn't equal to the expected one!");
  }

  @Test
  public void verifyBucketButtonIsDisabled() {
    Assert.assertTrue(reactGroupScreen.isDeleteGroupButtonDisabledByGroupName(groupName),
        "'Bucket' button is clickable!");
  }
}
