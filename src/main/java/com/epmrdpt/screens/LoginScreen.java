package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class LoginScreen extends AbstractScreen {

  public static final int LOGIN_TIMEOUT = 10;
  public static final String CAPTCHA_CODE = "11111";

  private Element usernameField = Element.byId("username");
  private Element passwordField = Element.byId("password");
  private Element captchaFrame = Element.byId("mtcaptcha-iframe-1");
  private Element signInButton = Element.byName("login");
  private Element toggleUserIdentitiesButton = Element
      .byXpath("//div[contains(@onclick,'toggleUserIdentities')]");
  private Element nextButton = Element.byId("kc-login-next");

  public boolean isScreenLoaded() {
    return usernameField.isDisplayed();
  }

  public LoginScreen typeLoginName(String username) {
    usernameField.type(username);
    return this;
  }

  public LoginScreen typePassword(String password) {
    passwordField.type(password);
    return this;
  }

  public boolean isCaptchaFrameDisplayed() {
    return captchaFrame.isDisplayed(SHORT_TIME_OUT_IN_SECONDS);
  }

  public CaptchaScreen switchToCaptchaFrame() {
    captchaFrame.switchToFrame();
    return new CaptchaScreen();
  }

  public HeaderScreen clickSignInButton() {
    signInButton.click();
    return new HeaderScreen();
  }

  public boolean isToggleUserIdentitiesDisplayed() {
    return toggleUserIdentitiesButton.isDisplayed(LOGIN_TIMEOUT);
  }

  public LoginScreen clickToggleUserIdentities() {
    if (isToggleUserIdentitiesDisplayed()) {
      toggleUserIdentitiesButton.clickJs();
    }
    return this;
  }

  public LoginScreen clickNext() {
    nextButton.click();
    return this;
  }

  public boolean isNextButtonDisplayed() {
    return nextButton.isDisplayed();
  }
}
