package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_DELETE_STUDENT_BUTTON_HINT;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_84076_VerifyThatBucketIconIsDisabledIfGroupContainsOnlyOneParticipant",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84076_VerifyThatBucketIconIsDisabledIfGroupContainsOnlyOneParticipant {

  private User user;
  private ReactGroupDetailsScreen reactGroupDetailsScreen;
  private String trainingName = "Autotest_ForLearningTabTesting";
  private String groupName = "LearningTabTesting_Group";
  private String studentName = "QQ AA";

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_84076_VerifyThatBucketIconIsDisabledIfGroupContainsOnlyOneParticipant(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(user)
        .waitAllSpinnersAreNotDisplayed();
    reactGroupDetailsScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectToGroup(trainingName, groupName)
        .waitDataLoading();
  }

  @Test
  public void verifyBucketIcon() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGroupDetailsScreen.isDeleteStudentByNameButtonDisabled(studentName),
        "'Bucket' button is clickable!");
    softAssert.assertEquals(reactGroupDetailsScreen.getDeleteStudentByNameIconHintText(studentName),
            getValueOf(REACT_GROUP_DETAILS_SCREEN_DELETE_STUDENT_BUTTON_HINT),
            "'Bucket' button hint has incorrect message!");
    softAssert.assertAll();
  }
}
