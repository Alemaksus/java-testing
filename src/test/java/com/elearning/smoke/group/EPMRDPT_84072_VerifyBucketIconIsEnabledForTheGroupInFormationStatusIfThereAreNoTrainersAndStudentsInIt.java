package com.epmrdpt.smoke.group;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84072_VerifyBucketIconIsEnabledForTheGroupInFormationStatusIfThereAreNoTrainersAndStudentsInIt",
    groups = {"full", "smoke", "manager"})
public class EPMRDPT_84072_VerifyBucketIconIsEnabledForTheGroupInFormationStatusIfThereAreNoTrainersAndStudentsInIt {

  private final String trainingName = "AutoTest_StaffedStatus";
  private final String groupName = "Formation";
  private final User user;
  private ReactGroupScreen reactGroupScreen;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84072_VerifyBucketIconIsEnabledForTheGroupInFormationStatusIfThereAreNoTrainersAndStudentsInIt(
      User user) {
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
  public void verifyBucketButtonIsEnabled() {
    Assert.assertTrue(reactGroupScreen.isDeleteGroupButtonEnabledByGroupName(groupName),
        "'Bucket' button is disabled!");
  }
}
