package com.epmrdpt.smoke.homefooter;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_PRIVACY_POLICY_COUNTRY;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13237_VerifyTheLinksInTheFooter",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13237_VerifyTheLinksInTheFooter {

  private static final String INVESTOR_RELATIONS_LINK = "https://investors.epam.com/investors";
  private static final String CONTACT_LINK = "https://www.epam.com/about/who-we-are/contact";
  private static final String PRIVACY_POLICY_LINK = "https://privacy.epam.com/core/interaction/showpolicy?type=";
  private static final String PRIVACY_NOTICE_LINK
      = "https://privacy.epam.com/core/interaction/showpolicy?type=CommonPrivacyNotice";
  private FooterScreen footerScreen;
  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    footerScreen = new FooterScreen();
    trainingBlockScreen = new TrainingBlockScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(trainingBlockScreen.isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkUnderlineInvestorsLink() {
    softAssert = new SoftAssert();
    softAssert.assertFalse(footerScreen.isInvestorsLinkHasUnderline(),
        "Link 'INVESTORS RELATIONS' has underline before hover over!");
    footerScreen.hoverOnInvestorRelationsLink();
    softAssert.assertTrue(footerScreen.isInvestorsLinkHasUnderline(),
        "Link 'INVESTORS RELATIONS' hasn't underline after hover over!");
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkRedirectInvestorsLinkCorrectly() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickInvestorRelationLink()
            .isTitleInvestorRelationsPagePresent(),
        "The 'INVESTORS RELATIONS' link redirects incorrectly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), INVESTOR_RELATIONS_LINK,
        format("'INVESTOR RELATIONS' link does't redirect to %s!", INVESTOR_RELATIONS_LINK));
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkUnderlineContactLink() {
    footerScreen.clickBackButton();
    trainingBlockScreen.waitScreenLoaded();
    softAssert = new SoftAssert();
    softAssert.assertFalse(footerScreen.isContactLinkHasUnderline(),
        "Link 'CONTACT' has underline before hover over!");
    footerScreen.hoverOnContactLink();
    softAssert.assertTrue(footerScreen.isContactLinkHasUnderline(),
        "Link 'CONTACT' hasn't underline after hover over!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkRedirectContactLinkCorrect() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickContactLink()
            .isTitleContactPagePresent(),
        "The 'CONTACT' link redirects incorrectly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), CONTACT_LINK,
        format("'CONTACT' link does't redirect to %s!", CONTACT_LINK));
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkUnderlinePrivacyPolicyLink() {
    footerScreen.clickBackButton();
    trainingBlockScreen.waitScreenLoaded();
    softAssert = new SoftAssert();
    softAssert.assertFalse(footerScreen.isPrivacyPoliceLinkHasUnderline(),
        "Link 'PRIVACY POLICY' has underline before hover over!");
    footerScreen.hoverOnPrivacyPolicyLink();
    softAssert.assertTrue(footerScreen.isPrivacyPoliceLinkHasUnderline(),
        "Link 'PRIVACY POLICY' hasn't underline after hover over!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkRedirectPrivacyPolicyCorrect() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickPrivacyPolicyLink()
            .isTitlePrivacyPolicyPagePresent(),
        "The 'PRIVACY POLICY' link redirects incorrectly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), PRIVACY_POLICY_LINK
            + getValueOf(NEWS_MANAGEMENT_PRIVACY_POLICY_COUNTRY),
        format("'PRIVACY POLICY' link does't redirect to %s!", PRIVACY_POLICY_LINK
            + getValueOf(NEWS_MANAGEMENT_PRIVACY_POLICY_COUNTRY)));
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkUnderlinePrivacyNoticeLink() {
    footerScreen.clickBackButton();
    trainingBlockScreen.waitScreenLoaded();
    softAssert = new SoftAssert();
    softAssert.assertFalse(footerScreen.isPrivacyNoticeLinkHasUnderline(),
        "Link 'PRIVACY NOTICE' has underline before hover over!");
    footerScreen.hoverOnPrivacyNoticeLink();
    softAssert.assertTrue(footerScreen.isPrivacyNoticeLinkHasUnderline(),
        "Link 'PRIVACY NOTICE' hasn't underline after hover over!");
    softAssert.assertAll();
  }

  @Test(priority = 9)
  public void checkRedirectPrivacyNoticeCorrect() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickPrivacyNoticeLink()
            .isTitlePrivacyNoticePagePresent(),
        "The 'PRIVACY NOTICE' link redirects incorrectly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), PRIVACY_NOTICE_LINK,
        format("'PRIVACY NOTICE' link does't redirect to %s!", PRIVACY_NOTICE_LINK));
    softAssert.assertAll();
  }
}
