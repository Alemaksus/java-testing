package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_SCREEN_DELETE_GROUP_IN_FINISHED_STATUS_TOOLTIP_MESSAGE;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84075_VerifyHintAppearsOnHoveringBucketIconForTheGroupInFinishedStatus",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84075_VerifyHintAppearsOnHoveringBucketIconForTheGroupInFinishedStatus {

  private User user;
  private String trainingName = "AutoTest Planned Status";
  private String groupName = "Automated Testing 2";
  private ReactGroupScreen reactGroupScreen;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84075_VerifyHintAppearsOnHoveringBucketIconForTheGroupInFinishedStatus(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(user)
        .waitAllSpinnersAreNotDisplayed();
    reactGroupScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .clickGroupsTabs()
        .waitDataLoading();
  }

  @Test
  public void checkTheHintHasCorrectText() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(reactGroupScreen.hoverOnDeleteButtonByGroupName(groupName),
        getValueOf(REACT_GROUP_SCREEN_DELETE_GROUP_IN_FINISHED_STATUS_TOOLTIP_MESSAGE),
        "Hovered 'Bucket' button hint text isn't equal to the expected one!");
    softAssert.assertTrue(reactGroupScreen.isDeleteGroupButtonDisabledByGroupName(groupName),
        "'Bucket' button is clickable!");
    softAssert.assertAll();
  }
}
