package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactFooterScreen extends AbstractScreen {

  private Element epamLogo = Element.byCss("footer div");
  private Element footerContainer = Element.byTagName("footer");
  private Element navigationLinks = Element.byXpath("//footer//li/a[@href]");
  private Element copyrightSignLabel = Element.byCss("footer div:last-of-type");
  private Element twitterLink = Element.byXpath("//a[contains(@href,'twitter')]");
  private Element facebookLink = Element.byXpath("//a[contains(@href,'facebook')]");
  private Element linkedInLink = Element.byXpath("//a[contains(@href,'linkedin')]");

  @Override
  public boolean isScreenLoaded() {
    return isFooterContainerDisplayed();
  }

  public boolean isEpamLogoDisplayed() {
    return epamLogo.isDisplayed();
  }

  public boolean isFooterContainerDisplayed() {
    return footerContainer.isDisplayed();
  }

  public boolean isAllNavigationLinksDisplayed() {
    return navigationLinks.isAllElementsDisplayed();
  }

  public String getCopyrightSignText() {
    return copyrightSignLabel.getText();
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
}
