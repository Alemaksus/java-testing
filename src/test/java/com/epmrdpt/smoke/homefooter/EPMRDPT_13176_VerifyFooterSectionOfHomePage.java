package com.epmrdpt.smoke.homefooter;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.FOOTER_COPYRIGHT_SIGN_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.FOOTER_OF_HOME_SCREEN_CONTACT_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.FOOTER_OF_HOME_SCREEN_INVESTOR_RELATIONS_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.FOOTER_OF_HOME_SCREEN_PRIVACY_NOTICE_TEXT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.FOOTER_OF_HOME_SCREEN_PRIVACY_POLICY_TEXT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13176_VerifyFooterSectionOfHomePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_13176_VerifyFooterSectionOfHomePage {

  private FooterScreen footerScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    footerScreen = new FooterScreen();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new TrainingBlockScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkCopyrightSignDisplayed() {
    assertTrue(footerScreen.isCopyrightSignDisplayed(), "Copyright Sign isn't displayed!");
  }

  @Test(priority = 2)
  public void checkCopyrightSignCorrect() {
    assertEquals(footerScreen.getCopyrightSignText(), getValueOf(FOOTER_COPYRIGHT_SIGN_TEXT),
        "Copyright sign isn't displayed correctly!");
  }

  @Test(priority = 2)
  public void checkCurrentYearInFooterDisplayed() {
    assertTrue(
        footerScreen.getCopyrightSignText().contains(String.valueOf(LocalDate.now().getYear())),
        "Year in footer isn't correct!");
  }

  @Test(priority = 2)
  public void checkInvestorRelationsLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.isInvestorRelationsLinkDisplayed(),
        "Investor Relations link isn't displayed!");
    softAssert.assertEquals(footerScreen.getInvestorRelationsLinkText(),
        getValueOf(FOOTER_OF_HOME_SCREEN_INVESTOR_RELATIONS_TEXT),
        "'Investors relations' link isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkContactLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.isContactLinkDisplayed(), "Contact link isn't displayed!");
    softAssert.assertEquals(footerScreen.getContactLinkText(),
        getValueOf(FOOTER_OF_HOME_SCREEN_CONTACT_TEXT),
        "'Contact' link isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkPrivacyPolicyLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.isPrivacyPolicyLinkDisplayed(),
        "Privacy policy link isn't displayed!");
    softAssert.assertEquals(footerScreen.getPrivacyPolicyLinkText(),
        getValueOf(FOOTER_OF_HOME_SCREEN_PRIVACY_POLICY_TEXT),
        "'Privacy policy' link isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkPrivacyNoticeLinkDisplayed() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(footerScreen.isPrivacyNoticeLinkDisplayed(),
        "'Privacy notice' link isn't displayed!");
    softAssert.assertEquals(footerScreen.getPrivacyNoticeLinkText(),
        getValueOf(FOOTER_OF_HOME_SCREEN_PRIVACY_NOTICE_TEXT),
        "'Privacy notice' link isn't displayed correctly!");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTwitterLinkDisplayed() {
    assertTrue(footerScreen.isTwitterLinkDisplayed(), "Twitter Link isn't displayed!");
  }

  @Test(priority = 2)
  public void checkFacebookLinkDisplayed() {
    assertTrue(footerScreen.isFacebookLinkDisplayed(), "Facebook link isn't displayed!");
  }

  @Test(priority = 2)
  public void checkLinkedInLinkDisplayed() {
    assertTrue(footerScreen.isLinkedInLinkDisplayed(), " Linked link isn't displayed!");
  }
}
