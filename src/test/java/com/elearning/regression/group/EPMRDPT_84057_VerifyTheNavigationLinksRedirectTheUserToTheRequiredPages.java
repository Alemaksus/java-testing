package com.epmrdpt.regression.group;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84057_VerifyTheNavigationLinksRedirectTheUserToTheRequiredPages",
    groups = {"full", "regression"})
public class EPMRDPT_84057_VerifyTheNavigationLinksRedirectTheUserToTheRequiredPages {

  private String trainingName = "AutoTest_AddStudentsFromAnotherTraining";
  private String groupName = ".NET 2";
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private ReactGroupScreen reactGroupScreen;
  private User user;

  @Factory(dataProvider = "Provider of users with React Training Management",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84057_VerifyTheNavigationLinksRedirectTheUserToTheRequiredPages(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupGroupDetailsScreen() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(user);
    reactGroupDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectToGroup(trainingName, groupName)
        .waitDataLoading();
  }

  @Test(priority = 1)
  public void checkToGroupListLink() {
    reactGroupScreen = reactGroupDetailsScreen.clickToGroupsListLink();
    Assert.assertTrue(reactGroupScreen.isScreenLoaded(),
        "'To group list' link doesn't redirect the user to 'Group list' page!");
  }
}
