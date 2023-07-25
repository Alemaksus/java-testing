package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_ENROLLMENT_TYPE_DDL_OPTIONS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_FORMAT_DDL_OPTIONS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_PRICING_DDL_OPTIONS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LANGUAGE_DDL_OPTIONS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LEVEL_DDL_OPTIONS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_TYPE_DDL_OPTIONS;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_34560_34420_34455_34419_34418_34477_VerifyDdlOptionsOnCreateNewTrainingPag",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34560_34420_34455_34419_34418_34477_VerifyDdlOptionsOnCreateNewTrainingPage {

  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink()
        .waitPreloadingPictureHidden()
        .clickCreateNewButton()
        .waitScreenLoading();
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
  }

  @Test(priority = 1)
  public void verifyOptionsInTypeDDL() {
    List<String> expectedTypeDdlOptions = StringUtils
        .getListOfStringValues(getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_TYPE_DDL_OPTIONS));
    Assert.assertTrue(
        reactGeneralInfoTabScreen.getTypeDdlOptions().containsAll(expectedTypeDdlOptions),
        "Type DDL doesn't contain all necessary options!");
  }

  @Test(priority = 2)
  public void verifyOptionsInFormatDDl() {
    List<String> expectedFormatDdlOptions = StringUtils
        .getListOfStringValues(getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_FORMAT_DDL_OPTIONS));
    Assert.assertTrue(
        reactGeneralInfoTabScreen.getFormatDdlOptions().containsAll(expectedFormatDdlOptions),
        "Format DDL doesn't contain all necessary options!");
  }

  @Test(priority = 3)
  public void verifyOptionsInEnrollmentTypeDDL() {
    List<String> expectedEnrollmentTypeDdlOptions = StringUtils
        .getListOfStringValues(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_ENROLLMENT_TYPE_DDL_OPTIONS));
    Assert.assertTrue(reactGeneralInfoTabScreen.getEnrollmentTypeDdlOptions()
            .containsAll(expectedEnrollmentTypeDdlOptions),
        "Enrollment type DDL doesn't contain all necessary options!");
  }

  @Test(priority = 4)
  public void verifyOptionsInProgramLevelDDL() {
    List<String> expectedProgramLevelDdlOptions = StringUtils
        .getListOfStringValues(getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LEVEL_DDL_OPTIONS));
    Assert.assertTrue(reactGeneralInfoTabScreen.getProgramLevelDdlOptions()
            .containsAll(expectedProgramLevelDdlOptions),
        "Program level DDL doesn't contain all necessary options!");
  }

  @Test(priority = 5)
  public void verifyOptionsInProgramLanguageDDL() {
    List<String> expectedProgramLanguageDdlOptions = StringUtils
        .getListOfStringValues(
            getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_PROGRAM_LANGUAGE_DDL_OPTIONS));
    Assert.assertTrue(reactGeneralInfoTabScreen.getProgramLanguageDdlOptions()
            .containsAll(expectedProgramLanguageDdlOptions),
        "Program language DDL doesn't contain all necessary options!");
  }

  @Test(priority = 6)
  public void verifyOptionsInPricingDDL() {
    List<String> expectedPricingDdlOptions = StringUtils
        .getListOfStringValues(getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_PRICING_DDL_OPTIONS));
    Assert.assertTrue(
        reactGeneralInfoTabScreen.getPricingDdlOptions().containsAll(expectedPricingDdlOptions),
        "Pricing DDL doesn't contain all necessary options!");
  }
}
