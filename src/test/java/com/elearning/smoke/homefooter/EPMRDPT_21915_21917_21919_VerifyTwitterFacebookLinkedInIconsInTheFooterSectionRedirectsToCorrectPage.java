package com.epmrdpt.smoke.homefooter;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.TrainingBlockScreen;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_21915_21917_21919_VerifyTwitterFacebookLinkedInIconsInTheFooterSectionRedirectsToCorrectPage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_21915_21917_21919_VerifyTwitterFacebookLinkedInIconsInTheFooterSectionRedirectsToCorrectPage {

  private FooterScreen footerScreen;
  private static final Pattern TWITTER_LINK = Pattern.compile( "^https://twitter\\.com.+epamsystems");
  private static final Pattern FACEBOOK_LINK = Pattern.compile("^https://www\\.facebook\\.com/.");
  private static final Pattern LINKED_IN_LINK = Pattern.compile("^https://www\\.linkedin\\.com/.");

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void screenInitialization() {
    footerScreen = new FooterScreen();
  }

  @BeforeMethod(inheritGroups = false, alwaysRun = true)
  public void switchToFirstWindow() {
    footerScreen.deleteAllCookies();
    footerScreen.switchToFirstWindowIfMoreThanOne();
  }

  @Test(priority = 1)
  public void checkHomeScreenLoading() {
    assertTrue(new TrainingBlockScreen().isScreenLoaded(), "Home page isn't loaded!");
  }

  @Test(priority = 2)
  public void checkRedirectedOnTwitterAfterClickIcon() {
    footerScreen.clickTwitterIcon();
    footerScreen.switchToLastWindow();
    assertTrue(TWITTER_LINK.matcher(footerScreen.getCurrentWindowUrl()).find(),
        format("Twitter icon does't redirect on %s!", TWITTER_LINK));
  }

  @Test(priority = 2)
  public void checkRedirectedOnFacebookAfterClickIcon() {
    footerScreen.scrollIntoViewFacebookLink();
    footerScreen.clickFacebookIcon();
    footerScreen.switchToLastWindow();
    assertTrue(FACEBOOK_LINK.matcher(footerScreen.getCurrentWindowUrl()).find(),
        format("Facebook icon does't redirect on %s!", FACEBOOK_LINK));
  }

  @Test(priority = 2)
  public void checkRedirectedOnLinkedInAfterClickIcon() {
    footerScreen.clickLinkedInIcon();
    footerScreen.switchToLastWindow();
    assertTrue(LINKED_IN_LINK.matcher(footerScreen.getCurrentWindowUrl()).find(),
        format("LinkedIn icon does't redirect on %s!", LINKED_IN_LINK));
  }
}
