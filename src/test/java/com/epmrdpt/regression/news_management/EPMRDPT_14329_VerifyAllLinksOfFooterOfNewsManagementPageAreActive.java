package com.epmrdpt.regression.news_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.NEWS_MANAGEMENT_PRIVACY_POLICY_COUNTRY;
import static java.lang.String.format;

import com.epmrdpt.bo.user.User;
import com.epmrdpt.data_providers.DataProviderSource;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.NewsManagementScreen;
import com.epmrdpt.services.LoginService;
import java.time.LocalDate;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_14329_VerifyAllLinksOfFooterOfNewsManagementPageAreActive",
    groups = {"full", "regression", "news_management"})
public class EPMRDPT_14329_VerifyAllLinksOfFooterOfNewsManagementPageAreActive {

  private User user;
  private NewsManagementScreen newsManagementScreen;
  private FooterScreen footerScreen;
  private SoftAssert softAssert;

  private static final String COPY_RIGHT_SIGN = "©";
  private static final String CURRENT_YEAR = String.valueOf(LocalDate.now().getYear());
  private static final String INVESTOR_RELATIONS_LINK = "https://investors.epam.com/investors";
  private static final String CONTACT_LINK = "https://www.epam.com/about/who-we-are/contact";
  private static final String PRIVACY_POLICY_LINK = "https://privacy.epam.com/core/interaction/showpolicy?type=";
  private static final String PRIVACY_NOTICE_LINK
      = "https://privacy.epam.com/core/interaction/showpolicy?type=CommonPrivacyNotice";
  private static final String FACEBOOK_LINK = "https://www.facebook.com/EPAM.Global";
  private static final Pattern TWITTER_LINK = Pattern.compile("^https://twitter\\.com.+epamsystems");
  private static final Pattern LINKED_IN_LINK = Pattern.compile("^https://www\\.linkedin\\.com/.");

  @Factory(dataProvider = "Provider of users with News Management tab", dataProviderClass = DataProviderSource.class)
  public EPMRDPT_14329_VerifyAllLinksOfFooterOfNewsManagementPageAreActive(User user) {
    this.user = user;
  }

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setup() {
    newsManagementScreen = new NewsManagementScreen();
    footerScreen = new FooterScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(user)
        .clickNewsManagementLink()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkCopyrightDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.getCopyrightSignText().contains(COPY_RIGHT_SIGN),
        "Copyright sign isn't present in footer!");
    softAssert.assertTrue(footerScreen.getCopyrightSignText().contains(CURRENT_YEAR),
        "Current year isn't present in copyright sign in footer!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkInvestorRelationsLinkRedirection() {
    newsManagementScreen.scrollToBottom();
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickInvestorRelationLink()
            .isTitleInvestorRelationsPagePresent(),
        "'INVESTOR RELATIONS' link doesn't redirect correctly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), INVESTOR_RELATIONS_LINK,
        format("'INVESTOR RELATIONS' link does't redirect to %s!", INVESTOR_RELATIONS_LINK));
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkContactLinkRedirection() {
    footerScreen.clickBackButton();
    newsManagementScreen.waitScreenLoading();
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickContactLink()
            .isTitleContactPagePresent(),
        "'CONTACT' link doesn't redirect correctly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), CONTACT_LINK,
        format("'CONTACT' link does't redirect to %s!", CONTACT_LINK));
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkPrivacyPolicyLinkRedirection() {
    footerScreen.clickBackButton();
    newsManagementScreen.waitScreenLoading();
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickPrivacyPolicyLink()
            .isTitlePrivacyPolicyPagePresent(),
        "'PRIVACY POLICY' link doesn't redirect correctly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), PRIVACY_POLICY_LINK
            + getValueOf(NEWS_MANAGEMENT_PRIVACY_POLICY_COUNTRY),
        format("'PRIVACY POLICY' link does't redirect to %s!", PRIVACY_POLICY_LINK
            + getValueOf(NEWS_MANAGEMENT_PRIVACY_POLICY_COUNTRY)));
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkPrivacyNoticeLinkRedirection() {
    footerScreen.clickBackButton();
    newsManagementScreen.waitScreenLoading();
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen
            .clickPrivacyNoticeLink()
            .isTitlePrivacyNoticePagePresent(),
        "The 'PRIVACY NOTICE' link redirects incorrectly!");
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), PRIVACY_NOTICE_LINK,
        format("'PRIVACY NOTICE' link does't redirect to %s!", PRIVACY_NOTICE_LINK));
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkTwitterIconRedirection() {
    footerScreen.clickBackButton();
    newsManagementScreen.waitScreenLoading();
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.isTwitterIconPresent(), "'Twitter' icon isn't present!");
    footerScreen
        .clickTwitterIcon()
        .switchToLastWindow();
    softAssert.assertTrue(TWITTER_LINK.matcher(footerScreen.getCurrentWindowUrl()).find(),
        format("Twitter icon does't redirect to %s!", TWITTER_LINK));
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkFacebookIconRedirection() {
    newsManagementScreen.switchToFirstWindowIfMoreThanOne();
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.isFacebookIconPresent(), "'Facebook' icon isn't present!");
    footerScreen
        .clickFacebookIcon()
        .switchToLastWindow();
    softAssert.assertEquals(footerScreen.getCurrentWindowUrl(), FACEBOOK_LINK,
        format("Facebook icon does't redirect to %s!", FACEBOOK_LINK));
    softAssert.assertAll();
  }

  @Test(priority = 8)
  public void checkLinkedInIconRedirection() {
    newsManagementScreen.switchToFirstWindowIfMoreThanOne();
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.isLinkedInIconPresent(), "'Linked In' icon isn't present!");
    footerScreen
        .clickLinkedInIcon()
        .switchToLastWindow();
    softAssert.assertTrue(LINKED_IN_LINK.matcher(footerScreen.getCurrentWindowUrl()).find(),
        format("LinkedIn icon does't redirect to %s!", LINKED_IN_LINK));
    softAssert.assertAll();
  }
}
