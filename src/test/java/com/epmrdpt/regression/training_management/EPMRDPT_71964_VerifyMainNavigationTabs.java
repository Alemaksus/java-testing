package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_AUDIT_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_CLASSES_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_DETAIL_TRAINING_SCREEN_GROUPS_TAB;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GROUP_DETAILS_SCREEN_PARTICIPANTS_SECTION_TITLE;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.AuditScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_71964_VerifyMainNavigationTabs",
    groups = {"full", "react", "regression"})
public class EPMRDPT_71964_VerifyMainNavigationTabs {

  private User user;
  private ReactTrainingManagementService reactTrainingManagementService;

  @Factory(dataProvider = "Provider of users with Training Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_71964_VerifyMainNavigationTabs(User user) {
    this.user = user;
  }

  @DataProvider(name = "Provider of training names and tab names")
  private static Object[][] dataProviderTrainingsAndTabNames() {
    return new Object[][]{
        {"AutoTest_NotificationLanguageVerification", Arrays.asList(
            getValueOf(REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB),
            getValueOf(REACT_GROUP_DETAILS_SCREEN_PARTICIPANTS_SECTION_TITLE),
            getValueOf(REACT_DETAIL_TRAINING_SCREEN_GROUPS_TAB),
            getValueOf(REACT_DETAIL_TRAINING_SCREEN_AUDIT_TAB))},
        {"AutoTestDA", Arrays.asList(
            getValueOf(REACT_DETAIL_TRAINING_SCREEN_DETAILS_TAB),
            getValueOf(REACT_GROUP_DETAILS_SCREEN_PARTICIPANTS_SECTION_TITLE),
            getValueOf(REACT_DETAIL_TRAINING_SCREEN_CLASSES_TAB),
            getValueOf(REACT_DETAIL_TRAINING_SCREEN_AUDIT_TAB))}
    };
  }

  @BeforeClass(alwaysRun = true, inheritGroups = false)
  public void loginAndGoToTrainingManagementPage() {
    new ReactLoginService().loginAndGoToReactTrainingManagement(user);
    reactTrainingManagementService = new ReactTrainingManagementService();
  }

  @Test(dataProvider = "Provider of training names and tab names")
  public void verifyTrainingNavigationTabs(String trainingName, List<String> listOfTabNames) {
    SoftAssert softAssert = new SoftAssert();
    ReactDetailTrainingScreen reactDetailTrainingScreen = reactTrainingManagementService
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitAllSpinnersAreNotDisplayed();
    softAssert.assertTrue(reactDetailTrainingScreen.isScreenLoaded(),
        "'Detail' screen isn't loaded!");
    for (String tabName : listOfTabNames) {
      softAssert.assertTrue(reactDetailTrainingScreen.isTabDisplayedByName(tabName),
          String.format("'%s' tab isn't displayed!", tabName));
      softAssert
          .assertTrue(reactDetailTrainingScreen.isTabBecomesHighlightedWhenHoveredByName(tabName),
              String.format("'%s' tab isn't highlighted when hovered!", tabName));
    }
    softAssert.assertTrue(
        reactDetailTrainingScreen.clickReactParticipantsTab().waitSpinnerOfLoadingInvisibility()
            .isScreenLoaded(), "'Participants' tab isn't loaded!");
    if (trainingName.equals("AutoTest_NotificationLanguageVerification")) {
      softAssert
          .assertTrue(
              reactDetailTrainingScreen.clickGroupsTabs().waitDataLoading().isScreenLoaded(),
              "'Groups' tab isn't loaded!");
    } else {
      softAssert
          .assertTrue(
              reactDetailTrainingScreen.clickClassesTabs().waitDataLoading().isScreenLoaded(),
              "'Classes' tab isn't loaded!");
    }
    AuditScreen auditScreen = reactDetailTrainingScreen.clickAuditTab().waitScreenLoading();
    softAssert.assertTrue(auditScreen.isScreenLoaded(), "'Audit' page isn't loaded!");
    auditScreen.clickBackButtonOnTheTop()
        .clickPlanningTitle()
        .waitAllSpinnersAreNotDisplayed();
    softAssert.assertAll();
  }
}
