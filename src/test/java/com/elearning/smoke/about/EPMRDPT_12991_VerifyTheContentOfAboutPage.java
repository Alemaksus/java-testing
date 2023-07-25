package com.epmrdpt.smoke.about;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ABOUT_OUR_TRAINING_CENTERS_SECTION_TITLE;

import com.epmrdpt.screens.AboutScreen;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.HeaderScreen;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_12991_VerifyTheContentOfAboutPage",
    groups = {"full", "general", "smoke", "critical_path"})
public class EPMRDPT_12991_VerifyTheContentOfAboutPage {

  private AboutScreen aboutScreen;
  private FooterScreen footerScreen;
  private final String OUR_TRAINING_CENTERS_TITLE = getValueOf(
      ABOUT_OUR_TRAINING_CENTERS_SECTION_TITLE);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    footerScreen = new FooterScreen();
    aboutScreen = new HeaderScreen().waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickAboutNavigationLink();
  }

  @Test
  public void checkAboutScreenContent() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(aboutScreen.isScreenLoaded(), "About page isn't load!");
    softAssert.assertTrue(new HeaderScreen().isHeaderContainerDisplayed(),
        "'Header' didn't display after redirecting to About us page!");
    softAssert.assertTrue(aboutScreen.isBannerContainerDisplayed(),
        "Banner section didn't displayed!");
    softAssert.assertTrue(aboutScreen.isHomeButtonDisplayed(),
        "Button 'home' didn't displayed!");
    softAssert.assertTrue(aboutScreen.isJoinUSContainerDisplayed(),
        "Join us section didn't displayed!");
    softAssert.assertTrue(aboutScreen
            .hoverSectionByTitle(OUR_TRAINING_CENTERS_TITLE)
            .getSectionTitleTextByTitle(OUR_TRAINING_CENTERS_TITLE)
            .equals(OUR_TRAINING_CENTERS_TITLE.toUpperCase()),
        "Our training centers section has another title!");
    softAssert.assertTrue(footerScreen.isFeedbackDisplayed(),
        "'Feedback' button didn't displayed after redirect to About page!");
    softAssert.assertTrue(footerScreen.isFooterContainerDisplayed(),
        "'Footer' didn't displayed after redirecting to About us page!");
    softAssert.assertAll();
  }
}
