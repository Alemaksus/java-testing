package com.epmrdpt.regression.applications;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static java.lang.String.format;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ApplicationsScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34798_VerifyContentOfTheCardWithInformationAboutDepartmentAffiliateTraining",
    groups = {"full", "regression", "student"})
public class EPMRDPT_34798_VerifyContentOfTheCardWithInformationAboutDepartmentAffiliateTraining {

  private final String departmentAffiliateTrainingName = "AutoTestDA";
  private ApplicationsScreen applicationsScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    applicationsScreen = new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withDepartmentTraining())
        .waitProfileMenuDisplayed()
        .clickProfileMenu()
        .waitDropDownDisplayed()
        .clickApplicationsButton()
        .waitScreenLoading();
  }

  @Test
  public void checkContentDepartmentAffiliateCard() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(
        applicationsScreen
            .isDepartmentAffiliateTrainingDisplayedByTrainingName(departmentAffiliateTrainingName),
        format("%s isn't displayed!", departmentAffiliateTrainingName));
    softAssert.assertEquals(applicationsScreen
            .getDepartmentAffiliateTrainingTextByTrainingName(departmentAffiliateTrainingName),
        getValueOf(LocalePropertyConst.APPLICATIONS_DEPARTMENT_AFFILIATE),
        format("Status under training name '%s' isn't correct!", departmentAffiliateTrainingName));
    softAssert.assertTrue(
        applicationsScreen.isTrainingLogoDisplayedByTrainingName(departmentAffiliateTrainingName),
        format("Logo '%s' is not displayed!", departmentAffiliateTrainingName));
    softAssert.assertAll();
  }
}
