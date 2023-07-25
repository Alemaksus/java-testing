package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.UserPropertyConst.USER_CAPTCHA_CODE;

import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class CaptchaScreen extends AbstractScreen {

  private Element captchaCard = Element.byId("mtcap-card-1");
  private Element captchaField = Element.byId("mtcap-inputtext-1");
  private Element captchaCheckMark = Element.byId("mtcap-statusbutton-1");

  @Override
  public boolean isScreenLoaded() {
    return captchaCard.isDisplayed();
  }

  public CaptchaScreen clickOnCaptchaField() {
    captchaField.click();
    return this;
  }

  public CaptchaScreen typeCaptcha() {
    captchaField.type(UserProperty.getValueOf(USER_CAPTCHA_CODE));
    return this;
  }

  public CaptchaScreen waitCaptchaCheckMarkVisibility() {
    captchaCheckMark.waitForVisibility();
    return this;
  }
}
