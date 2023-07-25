package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElement;
import com.epmrdpt.framework.ui.pseudoelement.PseudoElementEnum;
import javax.swing.text.html.CSS.Attribute;
import org.openqa.selenium.Keys;

public class FooterScreen extends AbstractScreen {

  private static final String ATTRIBUTE_FILTER = "filter";
  private static final String UNDERLINE_CSS_ATTRIBUTE_NAME = "text-decoration-line";
  private static final String UNDERLINED_CSS_ATTRIBUTE_VALUE = "underline";
  private static final String TWITTER_ICON_IMAGE_URL = "/Content/themes/Redesign/ico/twitter-icon.svg";
  private static final String FACEBOOK_ICON_IMAGE_URL = "/Content/themes/Redesign/ico/facebook-icon.svg";
  private static final String LINKED_IN_ICON_IMAGE_URL = "/Content/themes/Redesign/ico/linkedin-icon.svg";
  private static final String TWITTER_LINK_LOCATOR = "a.social-item--twitter";
  private static final String FACEBOOK_LINK_LOCATOR = "a.social-item--facebook";
  private static final String LINKED_IN_LINK_LOCATOR = "a.social-item--linkedin";
  private static final String HIGHLIGHT_COLOR_SOCIAL_NETWORK_ICON = "invert(0.5) sepia(0.7) saturate(54) hue-rotate(176deg) brightness(0.92)";

  private Element copyrightSignLabel = Element.byCss("div.footer-copyrights > p");
  private Element investorRelationsLink =
      Element.byXpath("//a[@href='https://investors.epam.com/investors']");
  private Element contactLink =
      Element.byXpath("//a[@href='https://www.epam.com/about/who-we-are/contact']");
  private Element privacyPolicyLink = Element.byXpath(
          "//a[contains(@href, 'https://privacy.epam') and contains (@href, 'PrivacyPolicy')]");
  private Element privacyNoticeLink = Element.byXpath(
      "//a[contains(@href, 'https://privacy.epam') and contains (@href, 'PrivacyNotice')]");
  private Element twitterLink = Element.byCss(TWITTER_LINK_LOCATOR);
  private Element facebookLink = Element.byCss(FACEBOOK_LINK_LOCATOR);
  private Element linkedInLink = Element.byCss(LINKED_IN_LINK_LOCATOR);
  private Element feedback = Element.byClassName("ask-question");
  private Element investorsRelationsPageTitle = Element.byXpath("//span[text()='Investors']");
  private Element contactsPageTitle =
      Element.byXpath(
          "//meta[@name='title' and @content='Learn more about EPAM and Contact Us | EPAM']");
  private Element privacyPolicyPageTitle = Element.byXpath("//body[@class='privacy-policy']");
  private Element privacyNoticePageTitle = Element.byXpath("//strong[text()='PRIVACY NOTICE']");
  private Element footerContainer = Element.byId("footer");
  private Element footerContentContainer = Element.byClassName("content-container");
  private Element allCookiesMessage = Element.byCss("#onetrust-policy>#onetrust-policy-text");
  private final Element acceptCookiesButton = Element.byId("onetrust-accept-btn-handler");

  public FooterScreen scrollToFooter() {
    footerContainer.mouseOver();
    return this;
  }

  @Override
  public boolean isScreenLoaded() {
    return isFooterContainerDisplayed() && isCopyrightSignDisplayed();
  }

  public boolean isFooterContainerDisplayed() {
    return footerContainer.isDisplayed();
  }

  public boolean isFooterContentContainerDisplayed() {
    return footerContentContainer.isDisplayed();
  }

  public boolean isCopyrightSignDisplayed() {
    return copyrightSignLabel.isDisplayed();
  }

  public String getCopyrightSignText() {
    return copyrightSignLabel.getText();
  }

  public boolean isInvestorRelationsLinkDisplayed() {
    return investorRelationsLink.isDisplayed();
  }

  public String getInvestorRelationsLinkText() {
    return investorRelationsLink.getText();
  }

  public String getContactLinkText() {
    return contactLink.getText();
  }

  public String getPrivacyPolicyLinkText() {
    return privacyPolicyLink.getText();
  }

  public String getPrivacyNoticeLinkText() {
    return privacyNoticeLink.getText();
  }

  public boolean isContactLinkDisplayed() {
    return contactLink.isDisplayed();
  }

  public boolean isPrivacyPolicyLinkDisplayed() {
    return privacyPolicyLink.isDisplayed();
  }

  public boolean isPrivacyNoticeLinkDisplayed() {
    return privacyNoticeLink.isDisplayed();
  }

  public boolean isTwitterLinkDisplayed() {
    return twitterLink.isDisplayed();
  }

  public boolean isFacebookLinkDisplayed() {
    return facebookLink.isDisplayed();
  }

  public boolean isLinkedInLinkDisplayed() {
    return linkedInLink.isDisplayed();
  }

  public boolean isFeedbackDisplayed() {
    return feedback.isDisplayed();
  }

  public FooterScreen clickInvestorRelationLink() {
    investorRelationsLink.click();
    return this;
  }

  public FooterScreen clickContactLink() {
    contactLink.click();
    return this;
  }

  public FooterScreen clickPrivacyPolicyLink() {
    privacyPolicyLink.click();
    return this;
  }

  public FooterScreen clickPrivacyNoticeLink() {
    privacyNoticeLink.click();
    return this;
  }

  public boolean isTitleInvestorRelationsPagePresent() {
    return investorsRelationsPageTitle.isPresent();
  }

  public boolean isTitleContactPagePresent() {
    return contactsPageTitle.isPresent();
  }

  public boolean isTitlePrivacyNoticePagePresent() {
    return privacyNoticePageTitle.isPresent();
  }

  public FooterScreen hoverOnInvestorRelationsLink() {
    investorRelationsLink.mouseOver();
    return this;
  }

  public FooterScreen hoverOnContactLink() {
    contactLink.mouseOver();
    return this;
  }

  public FooterScreen hoverOnPrivacyPolicyLink() {
    privacyPolicyLink.mouseOver();
    return this;
  }

  public FooterScreen hoverOnPrivacyNoticeLink() {
    privacyNoticeLink.mouseOver();
    return this;
  }

  private boolean isElementHasUnderline(Element element) {
    return element.getCssValue(UNDERLINE_CSS_ATTRIBUTE_NAME).equals(UNDERLINED_CSS_ATTRIBUTE_VALUE);
  }

  public boolean isInvestorsLinkHasUnderline() {
    return isElementHasUnderline(investorRelationsLink);
  }

  public boolean isContactLinkHasUnderline() {
    return isElementHasUnderline(contactLink);
  }

  public boolean isPrivacyPoliceLinkHasUnderline() {
    return isElementHasUnderline(privacyPolicyLink);
  }

  public boolean isPrivacyNoticeLinkHasUnderline() {
    return isElementHasUnderline(privacyNoticeLink);
  }

  public boolean isTitlePrivacyPolicyPagePresent() {
    return privacyPolicyPageTitle.isPresent();
  }

  public FooterScreen clickTwitterIcon() {
    twitterLink.click();
    return this;
  }

  public FooterScreen mouseOverTwitterIcon() {
    twitterLink.mouseOver();
    return this;
  }

  public boolean isTwitterIconHighlighted() {
    return twitterLink.getCssValue(ATTRIBUTE_FILTER)
        .equals(HIGHLIGHT_COLOR_SOCIAL_NETWORK_ICON);
  }

  public FooterScreen clickFacebookIcon() {
    facebookLink.click();
    return this;
  }

  public FooterScreen mouseOverFacebookIcon() {
    facebookLink.mouseOver();
    return this;
  }

  public boolean isFacebookIconHighlighted() {
    return facebookLink.getCssValue(ATTRIBUTE_FILTER)
        .equals(HIGHLIGHT_COLOR_SOCIAL_NETWORK_ICON);
  }

  public FooterScreen clickLinkedInIcon() {
    linkedInLink.click();
    return this;
  }

  public FooterScreen mouseOverLinkedInIcon() {
    linkedInLink.mouseOver();
    return this;
  }

  public boolean isLinkedInIconHighlighted() {
    return linkedInLink.getCssValue(ATTRIBUTE_FILTER)
        .equals(HIGHLIGHT_COLOR_SOCIAL_NETWORK_ICON);
  }

  public boolean isTwitterIconPresent() {
    return new PseudoElement().getPseudoElementCssPropertyValue(TWITTER_LINK_LOCATOR,
        Attribute.BACKGROUND.toString(), PseudoElementEnum.BEFORE).contains(TWITTER_ICON_IMAGE_URL);
  }

  public boolean isFacebookIconPresent() {
    return new PseudoElement().getPseudoElementCssPropertyValue(FACEBOOK_LINK_LOCATOR,
            Attribute.BACKGROUND.toString(), PseudoElementEnum.BEFORE)
        .contains(FACEBOOK_ICON_IMAGE_URL);
  }

  public boolean isLinkedInIconPresent() {
    return new PseudoElement().getPseudoElementCssPropertyValue(LINKED_IN_LINK_LOCATOR,
            Attribute.BACKGROUND.toString(), PseudoElementEnum.BEFORE)
        .contains(LINKED_IN_ICON_IMAGE_URL);
  }

  public FooterScreen scrollIntoViewFacebookLink() {
    facebookLink.getWrappedWebElement().sendKeys(Keys.PAGE_DOWN);
    return this;
  }

  public boolean isAllCookiesMessageDisplayed() {
    return allCookiesMessage.isDisplayed(SHORT_TIME_OUT_IN_SECONDS);
  }

  public FooterScreen waitAllCookiesMessageForInVisibility() {
    allCookiesMessage.waitForInvisibility();
    return this;
  }

  public FooterScreen clickAcceptAllCookiesButton() {
      acceptCookiesButton.click();
    return this;
  }
}
