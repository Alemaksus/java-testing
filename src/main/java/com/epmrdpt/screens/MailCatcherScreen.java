package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import javax.swing.text.html.HTML;

public class MailCatcherScreen extends AbstractScreen {

  private static final String RESET_PASSWORD_MAIL_LOCATOR =
      "//nav[contains(@id,'messages')]//tr//td[contains(text(),'<%s>')]"
          + "/following-sibling::td[contains(text(),'Reset password')]";

  private Element mailCatcherHeader = Element.byXpath("//header/h1/a");
  private Element messagesNav = Element.byXpath("//nav[@id='messages']");
  private Element mailFrame = Element.byXpath("//iframe");
  private Element confirmButtonInConfirmMailScreen = Element.byCss("#kc-info-message a");
  private Element confirmationButton = Element.byXpath("//p/a");
  private Element resetPasswordButton = confirmationButton;

  public MailCatcherScreen clickResetPasswordMailByUserEmail(String userEmailID) {
    Element.byXpath(String.format(RESET_PASSWORD_MAIL_LOCATOR, userEmailID)).click();
    return this;
  }

  public MailCatcherScreen switchToMailFrame() {
    mailFrame.switchToFrame();
    return this;
  }

  public String copyResetPasswordLink() {
    return resetPasswordButton.getAttributeValue(HTML.Attribute.HREF.toString());
  }

  public MailCatcherScreen clickConfirmationButton() {
    confirmButtonInConfirmMailScreen.click();
    return this;
  }

  @Override
  public boolean isScreenLoaded() {
    return mailCatcherHeader.isDisplayed() && messagesNav.isDisplayed();
  }
}
