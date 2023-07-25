package com.epmrdpt.regression.center_management;

import static com.epmrdpt.framework.properties.LocalePropertyConst.PREVIEW_CENTER_BACK_TO_EDIT_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PREVIEW_CENTER_COOPERATION_WITH_UNIVERSITIES_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PREVIEW_CENTER_CURRENT_SKILLS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PREVIEW_CENTER_FAST_FACTS_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.PREVIEW_CENTER_FEEDBACK;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.CenterScreen;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.services.LoginService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34260_VerifyTheContentOfThePreviewPage",
    groups = {"full", "regression", "general"})
public class EPMRDPT_34260_VerifyTheContentOfThePreviewPage {

  private final String countryName = "AutoTestCountry";
  private final String cityName = "AutoTestCity";
  private final String expectedEnglishTabColorByDefault = "rgba(119, 206, 217, 1)";
  private final String expectedRussianAndUkrainianTabColorByDefault = "rgba(255, 255, 255, 1)";
  private CenterScreen centerScreen;
  private User user;
  private SoftAssert softAssert;

  @Factory(dataProvider = "Provider of users with News Management tab",
      dataProviderClass = DataProviderSource.class)
  public EPMRDPT_34260_VerifyTheContentOfThePreviewPage(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    centerScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickCenterManagementLink()
        .clickCountryDropDown()
        .selectCountryFromDDLByName(countryName)
        .clickApplyButton()
        .clickCenterThreeDotsContextMenuByCityName(cityName)
        .clickEditTrainingCenterAction()
        .clickPreviewButton();
  }

  @Test(priority = 1)
  public void checkHeaderDisplayedOnPreviewPage() {
    assertTrue(new HeaderScreen().isHeaderContainerDisplayed(),
        "Header on preview page is not displayed!");
  }

  @Test(priority = 2)
  public void checkBackToEditButton() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isBackToEditButtonDisplayed(),
        "'Back to edit' button is not displayed!");
    softAssert.assertEquals(centerScreen.getBackToEditButtonText(),
        LocaleProperties.getValueOf(PREVIEW_CENTER_BACK_TO_EDIT_BUTTON),
        "'Back to edit' button has incorrect text!");
    softAssert.assertAll();
  }


  @Test(priority = 3)
  public void checkEnglishTab() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isEnglishTabDisplayed(),
        "English tab is not displayed!");
    softAssert.assertEquals(centerScreen.getEnglishTabText(), LocaleProperties.getValueOf(
            SETTINGS_LANGUAGE_DROPDOWN_ENGLISH),
        "English tab has incorrect text!");
    softAssert.assertEquals(centerScreen.getEnglishTabColor(), expectedEnglishTabColorByDefault,
        "English tab is not blue by default!");
    softAssert.assertAll();
  }


  @Test(priority = 3)
  public void checkRussianTab() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isRussianTabDisplayed(),
        "Russian tab is not displayed!");
    softAssert.assertEquals(centerScreen.getRussianTabText(), LocaleProperties.getValueOf(
            SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN),
        "Russian tab has incorrect text!");
    softAssert.assertEquals(centerScreen.getRussianTabColor(),
        expectedRussianAndUkrainianTabColorByDefault,
        "Russian tab is not white by default!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkUkrainianTab() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isUkrainianTabDisplayed(),
        "Ukrainian tab is not displayed!");
    softAssert.assertEquals(centerScreen.getUkrainianTabText(), LocaleProperties.getValueOf(
            SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN),
        "Ukrainian tab has incorrect text!");
    softAssert.assertEquals(centerScreen.getUkrainianTabColor(),
        expectedRussianAndUkrainianTabColorByDefault,
        "Ukrainian tab is not white by default!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTrainingName() {
    assertTrue(centerScreen.isCenterTitleDisplayed(),
        "Center name is not displayed!");
  }

  @Test(priority = 5)
  public void checkFastFacts() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isFastFactsTitleDisplayed(),
        "Fast facts title is displayed!");
    softAssert.assertEquals(centerScreen.getFastFactsTitleText(),
        LocaleProperties.getValueOf(PREVIEW_CENTER_FAST_FACTS_TITLE),
        "Fast facts title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkTrainingDescription() {
    assertTrue(centerScreen.isTrainingCenterDescriptionDisplayed());
  }

  @Test(priority = 7)
  public void checkCooperationWithUniversities() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isCenterCooperationWithTheUniversitiesDisplayed(),
        "'Cooperation with universities' block is not displayed!");
    softAssert.assertEquals(centerScreen.getCooperationWithUniversitiesTitleText(),
        LocaleProperties.getValueOf(PREVIEW_CENTER_COOPERATION_WITH_UNIVERSITIES_TITLE),
        "'Cooperation with universities' title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkCurrentSkills() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isCurrentSkillsSectionDisplayed(),
        "'Current skills' block is not displayed!");
    softAssert.assertEquals(centerScreen.getCurrentSkillsTitleText(),
        LocaleProperties.getValueOf(PREVIEW_CENTER_CURRENT_SKILLS_TITLE),
        "'Current skills' title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkFeedback() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(centerScreen.isFeedbackSectionDisplayed(),
        "Feedback section is not displayed!");
    softAssert.assertEquals(centerScreen.getFeedbackTitleText(),
        LocaleProperties.getValueOf(PREVIEW_CENTER_FEEDBACK),
        "'Feedback' title has incorrect text!");
    softAssert.assertAll();
  }

  @Test(priority = 10)
  public void checkFooter() {
    assertTrue(new FooterScreen().isFooterContainerDisplayed(),
        "Footer is not displayed!");
  }
}
